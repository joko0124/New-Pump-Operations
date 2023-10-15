#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region
#Extends: android.support.v7.app.AppCompatActivity
#If Java

public boolean _onCreateOptionsMenu(android.view.Menu menu) {
	if (processBA.subExists("activity_createmenu")) {
		processBA.raiseEvent2(null, true, "activity_createmenu", false, new de.amberhome.objects.appcompat.ACMenuWrapper(menu));
		return true;
	}
	else
		return false;
}
#End If
#Region Variable Declarations

Sub Process_Globals
	Private xui As XUI
	Private Font As Typeface = Typeface.LoadFromAssets("myfont.ttf")
	Private FontBold As Typeface = Typeface.LoadFromAssets("myfont_bold.ttf")
	Private InpTyp As SLInpTypeConst
End Sub

Sub Globals
	Dim ActionBarButton As ACActionBar
	Private ToolBar As ACToolBarDark
	Private xmlIcon As XmlLayoutBuilder
	Private MatDialogBuilder As MaterialDialogBuilder
	Private CD, CDtxtBox, cdFixedText As ColorDrawable
	Private vibration As B4Avibrate
	Private vibratePattern() As Long
	
	Private snack As DSSnackbar
	Private csAns As CSBuilder

	Private TabMenu As WobbleMenu
	
	Private imeKeyboard As IME
	Private Alert As AX_CustomAlertDialog

	Private btnCancel As ACButton
	Private btnSaveUpdate As ACButton
	Private chkDefaultTimeRead As CheckBox
	Private mskTimeRead As MaskedEditText
	Private sRdgTime As String

	Private txtPSIRdg As EditText
	Private txtPSIRdgRemarks As EditText
	Private dLastFMRdg As Double
	
	Dim sRdgTime, sRemarks As String
	
	Dim dPrevRdg, dPresentRdg As Double

End Sub
#End Region

#Region Activity Events
Sub Activity_Create(FirstTime As Boolean)
	MyScale.SetRate(0.5)
	Activity.LoadLayout("PSIReading")
	
	Dim jo As JavaObject
	Dim xl As XmlLayoutBuilder
	jo = ToolBar
	jo.RunMethod("setPopupTheme", Array(xl.GetResourceId("style", "ToolbarMenu")))
	jo.RunMethod("setContentInsetStartWithNavigation", Array(1dip))
	jo.RunMethod("setTitleMarginStart", Array(0dip))

	ActionBarButton.Initialize
	ActionBarButton.ShowUpIndicator = True

	ToolBar.InitMenuListener
	ToolBar.Title = GlobalVar.CSTitle
	ToolBar.SubTitle = GlobalVar.CSSubTitle

	InpTyp.Initialize
	InpTyp.SetInputType(txtPSIRdgRemarks,Array As Int(InpTyp.TYPE_CLASS_TEXT, InpTyp.TYPE_TEXT_FLAG_AUTO_CORRECT, InpTyp.TYPE_TEXT_FLAG_CAP_SENTENCES))

	CDtxtBox.Initialize(Colors.Transparent,0)
	cdFixedText.Initialize2(Colors.Black,0,0,0)
	txtPSIRdg.Background = cdFixedText

	mskTimeRead.Background = CDtxtBox
	txtPSIRdgRemarks.Background = CDtxtBox
	
	imeKeyboard.Initialize("")
	
	MyFunctions.SetButton(btnSaveUpdate, 25, 25, 25, 25, 25, 25, 25, 25)
	MyFunctions.SetCancelButton(btnCancel, 25, 25, 25, 25, 25, 25, 25, 25)

	If GlobalVar.blnNewFMRdg = True Then
		GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"ADD NEW PUMP PRESSURE READING RECORD"$).PopAll
		GlobalVar.CSSubTitle.Initialize.Size(12).Append($"Transaction Date: "$ & GlobalVar.TranDate).PopAll
		ClearDisplay
		btnSaveUpdate.Text = Chr(0xE161) & $"  SAVE"$
		btnCancel.Text = Chr(0xE5C9) & $"  CANCEL"$
		dLastFMRdg = DBaseFunctions.GetLastFMReading(GlobalVar.PumpHouseID)
	Else
		GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"EDIT PUMP PRESSURE READING RECORD"$).PopAll
		GlobalVar.CSSubTitle.Initialize.Size(12).Append($"Transaction Date: "$ & GlobalVar.TranDate).PopAll
		btnSaveUpdate.Text = Chr(0xE161) & $" UPDATE"$
		btnCancel.Text = Chr(0xE5C9) & $"  CANCEL"$
		ClearDisplay
		GetPSIRdgRecord(GlobalVar.FMRdgDetailID)
'		dLastFMRdg = GetPrevFMReading(GlobalVar.FMRdgDetailID)
	End If
	
	If FirstTime Then
	End If
	csAns.Initialize.Color(Colors.White).Bold.Append($"YES"$).PopAll

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = 4 Then
		ToolBar_NavigationItemClick
		Return True
	Else
		Return False
	End If
End Sub

Sub Activity_Resume
	If GlobalVar.blnNewFMRdg = True Then
		GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"ADD NEW PUMP PRESSURE READING RECORD"$).PopAll
		GlobalVar.CSSubTitle.Initialize.Size(12).Append($"Transaction Date: "$ & GlobalVar.TranDate).PopAll
		ClearDisplay
		btnSaveUpdate.Text = Chr(0xE161) & $" SAVE"$
'		dLastFMRdg = DBaseFunctions.GetLastFMReading(GlobalVar.PumpHouseID)
	Else
		GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"EDIT PUMP PRESSURE READING RECORD"$).PopAll
		GlobalVar.CSSubTitle.Initialize.Size(12).Append($"Transaction Date: "$ & GlobalVar.TranDate).PopAll
		btnSaveUpdate.Text = Chr(0xE161) & $" UPDATE"$
		ClearDisplay
		GetPSIRdgRecord(GlobalVar.FMRdgDetailID)
'		dLastFMRdg = GetPrevFMReading(GlobalVar.FMRdgDetailID)
	End If
End Sub

Sub Activity_Pause (UserClosed As Boolean)
End Sub

#End Region

#Region Toolbar
Sub Activity_CreateMenu(Menu As ACMenu)
'	Dim Item As ACMenuItem
	Menu.Clear
End Sub

Sub ToolBar_NavigationItemClick 'Toolbar Arrow
	Activity.Finish
End Sub

Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Icon Menus
End Sub

#End Region


Private Sub GetPSIRdgRecord(iDetailedID As Int)
	Dim SenderFilter As Object
	
	GlobalVar.TranHeaderID = DBaseFunctions.GetHeaderID(GlobalVar.PumpHouseID, GlobalVar.TranDate)
	Try
		Starter.strCriteria = "SELECT RdgTime, PrevRdg, PSIReading, Remarks " & _
						  "FROM PressureRdgDetails " & _
						  "WHERE DetailID = " & iDetailedID
							  
		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			RS.Position = 0
			sRdgTime = RS.GetString("RdgTime")
			dPresentRdg= RS.GetInt("PSIReading")
			dPrevRdg = RS.GetInt("PrevRdg")
			sRemarks = RS.GetString("Remarks")
		Else
			snack.Initialize("", Activity,$"Unable to Fetch Flow Meter Reading Details due to "$ & LastException.Message,5000)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
			snack.Show
			Return
			Log(LastException)
		End If
		
	Catch
		Log(LastException)
	End Try
	chkDefaultTimeRead.Enabled = False
	mskTimeRead.Text = sRdgTime
	txtPSIRdg.Text = dPresentRdg
	txtPSIRdgRemarks.Text = sRemarks

End Sub

Private Sub ClearDisplay
	Try
		chkDefaultTimeRead.Checked = False
		mskTimeRead.Text = "__:__"
		txtPSIRdgRemarks.Text = ""
		txtPSIRdg.Text = ""
	Catch
		Log(LastException)
	End Try
End Sub

Private Sub IsValidEntries() As Boolean
	Dim bRetVal As Boolean
	LogColor(mskTimeRead.Text, Colors.Yellow)
	
	bRetVal = True
	Try
		If chkDefaultTimeRead.Checked = True Then
			If Validation.IsTime(mskTimeRead.Text) = False Then
				ToastMessageShow($"Invalid Reading Time!"$, True)
				mskTimeRead.RequestFocus
				bRetVal = False
			End If
		Else
			If GlobalVar.SF.Len(GlobalVar.SF.Trim(mskTimeRead.Text)) <= 0 Or mskTimeRead.Text = "__:__" Then
				ToastMessageShow($"Reading Time cannot be blank!"$, True)
				mskTimeRead.RequestFocus
				bRetVal = False
			
			Else If Validation.IsTime(mskTimeRead.Text) = False Then
				ToastMessageShow($"Invalid Reading Time!"$, True)
				mskTimeRead.RequestFocus
				bRetVal = False
			End If
		End If
	Catch
		Log(LastException)
		Return False
	End Try
	Return bRetVal
End Sub

Private Sub SaveTransHeader() As Boolean
	Dim bRetVal As Boolean
	Dim lngDateTime As Long
	Dim sAddedAt As String
	
	lngDateTime = DateTime.Now
	DateTime.DateFormat = "yyyy-MM-dd HH:mm:ss a"
	sAddedAt= DateTime.Date(lngDateTime)

	bRetVal = False
	
	Starter.DBCon.BeginTransaction
	Try
		
		Starter.DBCon.ExecNonQuery2("INSERT INTO TranHeader VALUES (" & Null & ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", _
							   Array As Object(GlobalVar.PumpHouseID, GlobalVar.TranDate, $"0"$, $"0"$, $"0"$, $"0"$, $"0"$, $"0"$, $"0"$, $"0"$, GlobalVar.UserID, $""$, $"0"$, GlobalVar.UserID, sAddedAt, Null, Null))
		Starter.DBCon.TransactionSuccessful
		bRetVal = True
	Catch
		Log(LastException)
		bRetVal = False
	End Try
	Starter.DBCon.EndTransaction
	Return bRetVal
End Sub

Private Sub UpdateTranHeader(iTranHeaderID As Int) As Boolean
	Dim bRetVal As Boolean
	Dim dMinPSI, dMaxPSI, dAvePSI As Double

	Dim lngDateTime As Long
	Dim sModifiedAt As String
	
	'Get Modification Date and Time
	DateTime.DateFormat = "yyyy-MM-dd"
	DateTime.TimeFormat = "hh:mm:ss a"
	lngDateTime = DateTime.Now
	sModifiedAt = DateTime.Date(lngDateTime) & $" "$ & DateTime.Time(lngDateTime)

	'Initialize variables
	dMinPSI = 0
	dMaxPSI = 0
	dAvePSI = 0
	bRetVal = False
	
	'Get values from database
	dMinPSI = DBaseFunctions.GetMinPSI(iTranHeaderID)
	dMaxPSI = DBaseFunctions.GetMaxPSI(iTranHeaderID)
	dAvePSI = DBaseFunctions.GetAvePSI(iTranHeaderID)
	
	'Check returned values
	LogColor($"Minimum PSI: "$ & dMinPSI, Colors.Magenta)
	LogColor($"Maximum PSI: "$ & dMaxPSI, Colors.Magenta)
	LogColor($"Average PSI: "$ & dAvePSI, Colors.Magenta)
	
	Starter.DBCon.BeginTransaction
	Try
		Starter.strCriteria = "UPDATE TranHeader SET " & _
							  "MinPSI = ?, " & _
							  "MaxPSI = ?, " & _
							  "AvePSI	 = ?, " & _
							  "ModifiedBy = ?, " & _
							  "ModifiedAt = ? " & _
							  "WHERE HeaderID = " & iTranHeaderID
							  
		Starter.DBCon.ExecNonQuery2(Starter.strCriteria, Array As String(dMinPSI, dMaxPSI, dAvePSI, GlobalVar.UserID, sModifiedAt))
		Starter.DBCon.TransactionSuccessful
		
		bRetVal = True
	Catch
		Log(LastException)
		bRetVal = False
	End Try
	Starter.DBCon.EndTransaction

	Return bRetVal
End Sub

Private Sub EditTranHeader(iTranHeaderID As Int) As Boolean
End Sub

Private Sub ShowSaveSuccess()
	Dim csTitle As CSBuilder
	Dim csContent As CSBuilder
	
	If GlobalVar.blnNewTime = True Then
		csTitle.Initialize.Size(18).Bold.Color(GlobalVar.PosColor).Append($"S U C C E S S!"$).PopAll
		csContent.Initialize.Size(14).Color(Colors.Black).Append($"New Pump Pressure Reading has been successfully saved!"$).PopAll
	Else
		csTitle.Initialize.Size(18).Bold.Color(GlobalVar.PosColor).Append($"S U C C E S S!"$).PopAll
		csContent.Initialize.Size(14).Color(Colors.Black).Append($"Pump Pressure Reading has been successfully updated!"$).PopAll
	End If

	Alert.Initialize.Create _
			.SetDialogStyleName("MyDialogDisableStatus") _	'Manifest style name
			.SetStyle(Alert.STYLE_DIALOGUE) _
			.SetTitle(csTitle) _
			.SetMessage(csContent) _
			.SetPositiveText("OK") _
			.SetPositiveColor(GlobalVar.PosColor) _
			.SetPositiveTypeface(FontBold) _
			.SetTitleTypeface(Font) _
			.SetMessageTypeface(Font) _
			.SetOnPositiveClicked("Success") _	'listeners
			.SetOnViewBinder("FontSizeBinder") _ 'listeners
			.SetDialogBackground(MyFunctions.myCD)
	Alert.Build.Show
End Sub

Private Sub Success_OnPositiveClicked (View As View, Dialog As Object)
'	ToastMessageShow("Positive Button Clicked!",False)
	Dim Alert As AX_CustomAlertDialog
	Activity.Finish
	Alert.Initialize.Dismiss(Dialog)
End Sub

Sub chkDefaultTimeRead_CheckedChange(Checked As Boolean)
	Dim sHour As String
	Dim sMin As String
	Dim lHour, lMin As Long
	
	If Checked = True Then
		DateTime.TimeFormat = "hh:mm a"
		lHour = DateTime.GetHour(DateTime.Now)
		lMin = DateTime.GetMinute(DateTime.Now)
		
		If GlobalVar.SF.Len(lHour) = 1 Then
			sHour = $"0"$ & lHour
		Else
			sHour = lHour
		End If

		If GlobalVar.SF.Len(lMin) = 1 Then
			sMin = $"0"$ & lMin
		Else
			sMin = lMin
		End If

		mskTimeRead.Text = sHour & ":" & sMin
		txtPSIRdg.RequestFocus
		imeKeyboard.ShowKeyboard(txtPSIRdg)
		mskTimeRead.Enabled = False
	Else
		mskTimeRead.Enabled = True
		mskTimeRead.Text = "__:__"
		mskTimeRead.RequestFocus
		imeKeyboard.ShowKeyboard(mskTimeRead)
	End If
End Sub

Sub txtPSIRdg_EnterPressed
	
End Sub

Sub mskTimeRead_EnterPressed
	txtPSIRdg.RequestFocus
End Sub

Sub btnSaveUpdate_Click
	Dim Matcher1 As Matcher
	Dim sMin, sHr As String

	If Not(IsValidEntries) Then Return 'Check Entries
	
	Matcher1 = Regex.Matcher("(\d\d):(\d\d)", mskTimeRead.Text)
	
	If Matcher1.Find Then 'Split
		Dim iHrs, iMins As Int
		
		iHrs = Matcher1.Group(1)
		iMins = Matcher1.Group(2)
		
		If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins)) = 1 Then 'Test length of mins
			sMin = $"0"$ & iMins
		Else
			sMin = iMins
		End If

		If iHrs = 0 Then '12 AM
			sHr = 12
			sRdgTime = sHr & ":" & sMin & " AM"
		Else If iHrs > 0 And iHrs < 12 Then '1 to 11 AM
			sHr = iHrs
			If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr)) = 1 Then
				sRdgTime = $"0"$ & sHr & ":" & sMin & " AM"
			Else
				sRdgTime = sHr & ":" & sMin & " AM"
			End If
			
		Else If iHrs = 12 Then '12 Noon
			sHr = 12
			sRdgTime = sHr & ":" & sMin & " PM"
		Else ' 1 to 11 PM
			sHr = iHrs - 12
			If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr)) = 1 Then
				sRdgTime = $"0"$ & sHr & ":" & sMin & " PM"
			Else
				sRdgTime = sHr & ":" & sMin & " PM"
			End If
		End If
	End If
	
	
	LogColor(sRdgTime,Colors.Yellow)

	Select Case GlobalVar.blnNewFMRdg
		Case True
			GlobalVar.TranHeaderID = DBaseFunctions.GetHeaderID(GlobalVar.PumpHouseID, GlobalVar.TranDate)
			LogColor(GlobalVar.TranHeaderID, Colors.Magenta)
			
			If GlobalVar.TranHeaderID = 0 Then
				If Not(SaveTransHeader) Then Return
				GlobalVar.TranHeaderID = DBaseFunctions.GetHeaderID(GlobalVar.PumpHouseID, GlobalVar.TranDate)
				LogColor(GlobalVar.TranHeaderID, Colors.Cyan)
				
				If Not(InsertNewPSIReading) Then
					vibration.vibrateOnce(1000)
					snack.Initialize("", Activity, $"Unable to Add New Flow Meter Reading due to "$ & LastException.Message, snack.DURATION_LONG)
					MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
					MyFunctions.SetSnackBarTextColor(snack, Colors.White)
					snack.Show
					Return
				End If
				
				UpdateLastFMReadings(GlobalVar.PumpHouseID)
				ShowSaveSuccess
			Else
				If Not(InsertNewPSIReading) Then
					vibration.vibrateOnce(1000)
					snack.Initialize("", Activity, $"Unable to Add New Flow Meter Reading due to "$ & LastException.Message, snack.DURATION_LONG)
					MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
					MyFunctions.SetSnackBarTextColor(snack, Colors.White)
					snack.Show
					Return
				End If
				If Not(UpdateTranHeader(GlobalVar.TranHeaderID)) Then
					Return
				End If
				UpdateLastFMReadings(GlobalVar.PumpHouseID)
				ShowSaveSuccess
			End If
			
		Case False
			
	End Select
End Sub


Sub btnCancel_Click
	
End Sub

Private Sub InsertNewPSIReading() As Boolean
	Dim bRetVal As Boolean
	
	Dim sDateTime As String
	Dim lDate As Long
	
	lDate = DateTime.Now
	DateTime.DateFormat = "yyyy-MM-dd hh:mm:ss a"
	sDateTime = DateTime.Date(lDate)
	dTotCum = 0
	dTotCum = GlobalVar.SF.Val(txtPSIRdg.Text) - dLastFMRdg
	
	Starter.DBCon.BeginTransaction
	Try
		Starter.DBCon.ExecNonQuery2("INSERT INTO ProductionDetails VALUES (" & Null & ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", _
							  Array As Object(GlobalVar.TranHeaderID, sRdgTime, dLastFMRdg, txtPSIRdg.Text, dTotCum, txtPSIRdgRemarks.Text, $"0"$, $""$, GlobalVar.UserID, sDateTime, Null, Null))
		Starter.DBCon.TransactionSuccessful
		bRetVal = True
	Catch
		Log(LastException)
		bRetVal = False
	End Try
	Starter.DBCon.EndTransaction
	Return bRetVal
End Sub

Private Sub GetPrevFMReading(iDetailID As Int) As Double
	'Get Pump House Flow Meter Last Reading
	Dim dRetval As Double
	Try
		Starter.strCriteria = "SELECT PrevRdg FROM ProductionDetails WHERE DetailID = " & iDetailID
		LogColor(Starter.strCriteria, Colors.Blue)
		
		dRetval = Starter.DBCon.ExecQuerySingleResult(Starter.strCriteria)
	Catch
		ToastMessageShow($"Unable to fetch Pump Last Reading due to "$ & LastException.Message, False)
		Log(LastException)
		dRetval = 0
	End Try
	Return dRetval
End Sub

Private Sub UpdateLastFMReadings(iPumpID As Int)
	
	Starter.DBCon.BeginTransaction
	Try
		Starter.strCriteria = "UPDATE tblPumpStation SET " & _
							  "LastRdg = ? " & _
							  "WHERE StationID = " & iPumpID
							  
		Starter.DBCon.ExecNonQuery2(Starter.strCriteria, Array As String(txtPSIRdg.Text))
		Starter.DBCon.TransactionSuccessful
	Catch
		Log(LastException)
	End Try
	Starter.DBCon.EndTransaction
End Sub

Private Sub FontSizeBinder_OnBindView (View As View, ViewType As Int)
	Dim Alert As AX_CustomAlertDialog
	Alert.Initialize
	If ViewType = Alert.VIEW_TITLE Then ' Title
		Dim lbl As Label = View
		lbl.TextSize = 30
'		lbl.SetTextColorAnimated(2000,Colors.Magenta)
		
		
		Dim CS As CSBuilder
'		CS.Initialize.Typeface(Font).Append(lbl.Text & " ").Pop
'		CS.Typeface(Typeface.MATERIALICONS).Size(36).Color(Colors.Red).Append(Chr(0xE190))

		CS.Initialize.Typeface(Typeface.MATERIALICONS).Size(26).Color(Colors.Red).Append(Chr(0xE88E) & "  ")
		CS.Typeface(Font).Size(26).Append(lbl.Text).Pop

		lbl.Text = CS.PopAll
	End If
End Sub