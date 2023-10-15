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
	Private InpTyp As SLInpTypeConst
End Sub

Sub Globals
	Dim ActionBarButton As ACActionBar
	Private ToolBar As ACToolBarDark

	Private MatDialogBuilder As MaterialDialogBuilder
	Private CD, CDtxtBox As ColorDrawable

	Private vibration As B4Avibrate
	Private snack As DSSnackbar
	Private cdReading As ColorDrawable

	Private btnSave As ACButton

	Private cboPSIPoint As ACSpinner
	Private lblCode As Label
	Private lblAddress As Label
	Private txtPSIRdg As EditText
	Private txtRemarks As EditText
	Private txtLocation As EditText
	
	Private ToastMsg As BCToast
	Private kBoard As IME
	
	Private PointNo As B4XDialog
	Private sPointNo As String
	Private SaveButton As GradientDrawable
End Sub
#End Region

#Region Activity Events
Sub Activity_Create(FirstTime As Boolean)
	MyScale.SetRate(0.5)
	Activity.LoadLayout("PSIDistRecords")

	lblCode.Text = GlobalVar.PumpHouseCode
	lblAddress.Text = GetPumpLocation(GlobalVar.PumpHouseID)

'	Dim Gradient1 As GradientDrawable
'	Dim Clrs(2) As Int
'	Clrs(0) = GlobalVar.GreenColor
'	Clrs(1) = GlobalVar.GreenColor2
'	Gradient1.Initialize("TOP_BOTTOM", Clrs)
	
'	SaveButton.Initialize("TR_BL",Clrs)
'	CD.Initialize2(GlobalVar.GreenColor, 30, 0, Colors.Transparent)
'	btnSave.Background = SaveButton
	
	InpTyp.Initialize
	InpTyp.SetInputType(txtRemarks,Array As Int(InpTyp.TYPE_CLASS_TEXT, InpTyp.TYPE_TEXT_FLAG_AUTO_CORRECT, InpTyp.TYPE_TEXT_FLAG_CAP_SENTENCES))

	CDtxtBox.Initialize(Colors.Transparent,0)
'	cboPSIPoint.Background = CDtxtBox
	txtLocation.Background = CDtxtBox
	txtRemarks.Background = CDtxtBox
	
	cdReading.Initialize2(Colors.Black,0,0,0)
	txtPSIRdg.Background = cdReading

	If GlobalVar.blnNewPSIDist = True Then
		GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"NEW PSI UPON DISTRIBUTION READING"$).PopAll
		GlobalVar.CSSubTitle.Initialize.Size(12).Append($"Transaction Date: "$ & GlobalVar.TranDate).PopAll
		btnSave.Text = Chr(0xE161) & $" SAVE"$
		FillPressurePoint(GlobalVar.PumpHouseID)
		txtPSIRdg.Text = ""
		txtRemarks.Text = ""
	Else
		GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"EDIT PSI UPON DISTRIBUTION READING"$).PopAll
		GlobalVar.CSSubTitle.Initialize.Size(12).Append($"Transaction Date: "$ & GlobalVar.TranDate).PopAll
		btnSave.Text = Chr(0xE161) & $" UPDATE"$
		FillPressurePoint(GlobalVar.PumpHouseID)
		ReturnPSIDistRdgDetails(GlobalVar.PSIDistDetailID)
	End If

	ToolBar.InitMenuListener
	ToolBar.Title = GlobalVar.CSTitle
	ToolBar.SubTitle = GlobalVar.CSSubTitle
	
	Dim jo As JavaObject
	Dim xl As XmlLayoutBuilder
	jo = ToolBar
	jo.RunMethod("setPopupTheme", Array(xl.GetResourceId("style", "ToolbarMenu")))
	jo.RunMethod("setContentInsetStartWithNavigation", Array(1dip))
	jo.RunMethod("setTitleMarginStart", Array(0dip))

	ActionBarButton.Initialize
	ActionBarButton.ShowUpIndicator = True
	
'	PointNo.Base = txtRemarks
'	PointNo.

	If FirstTime Then
	End If
	kBoard.Initialize("")
	MyFunctions.SetButton(btnSave, 25, 25, 25, 25, 25, 25, 25, 25)
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
End Sub

Sub Activity_Pause (UserClosed As Boolean)
End Sub

#End Region

#Region Toolbar
Sub Activity_CreateMenu(Menu As ACMenu)
'	Dim Item As ACMenuItem
	Menu.Clear
End Sub

Sub ToolBar_NavigationItemClick 'Toolbar Arroww
	kBoard.HideKeyboard
	Activity.Finish
End Sub

Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Icon Menus
End Sub
#End Region

Sub cboPSIPoint_ItemClick (Position As Int, Value As Object)
	LogColor($"Selected "$ & Position & " - " & Value,Colors.Yellow)
	txtLocation.Text = GetLocation(Value)
End Sub

Sub btnSave_Click
	If ToastMsg.IsInitialized = False Then ToastMsg.Initialize(Activity)
	If GlobalVar.SF.Len(GlobalVar.SF.Trim(cboPSIPoint.SelectedItem)) <= 0 Then
		vibration.vibrateOnce(2000)
		ToastMsg.DefaultTextColor = Colors.White
		ToastMsg.pnl.Color = GlobalVar.RedColor
		MyFunctions.MyToastMsg(ToastMsg, $"Please select Pressure Point No. Type!"$)
		cboPSIPoint.RequestFocus
		Return
	Else If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtPSIRdg.Text)) <= 0 Then
		vibration.vibrateOnce(2000)
		ToastMsg.DefaultTextColor = Colors.White
		ToastMsg.pnl.Color = GlobalVar.RedColor
		MyFunctions.MyToastMsg(ToastMsg, $"Please enter PSI Reading!"$)
		txtPSIRdg.RequestFocus
		kBoard.ShowKeyboard(txtPSIRdg)
		Return
	End If
	
	Try
		Select Case GlobalVar.blnNewPSIDist
			Case True
				If Not(SavePSIDistRdg) Then Return
				ShowSaveSuccess
			Case False
				If Not(UpdatePSIDistRdg(GlobalVar.PSIDistDetailID)) Then Return
				ShowSaveSuccess
		End Select
	Catch
		ToastMsg.DefaultTextColor = Colors.White
		ToastMsg.pnl.Color = GlobalVar.RedColor
		MyFunctions.MyToastMsg(ToastMsg, $"Unable to Save/Update Reading due to "$ & LastException.Message)
		Log(LastException)
	End Try
End Sub

#Region NEW PSI Reading
Private Sub SavePSIDistRdg() As Boolean
	Dim bRetVal As Boolean
	Dim lngDateTime As Long
	Dim DateRead, TimeRead As String
	Dim PSIPointID As Int
	Dim sDateTime As String
	Dim lDate As Long
	Dim iPSI As Int
	Dim sLocation, sRemarks As String
	
	lDate = DateTime.Now
	DateTime.DateFormat = "yyyy-MM-dd HH:mm:ss a"
	sDateTime = DateTime.Date(lDate)

	'Reading Date and Time**************************************
	lngDateTime = DateTime.Now
	DateTime.TimeFormat = "HH:mm a"
	TimeRead = DateTime.Time(lngDateTime)
	DateTime.DateFormat = "yyyy-MM-dd"
	DateRead = DateTime.Date(lngDateTime)
	'***********************************************************
	
	PSIPointID = GetPointID(cboPSIPoint.SelectedItem)
	iPSI = GlobalVar.SF.Val(txtPSIRdg.Text)
	sRemarks = txtRemarks.Text
	sLocation = ""
	Starter.DBCon.BeginTransaction
	Try
		Starter.DBCon.ExecNonQuery2("INSERT INTO PressureDistReadings VALUES (" & Null & ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", _
							  Array As Object(GlobalVar.BranchID, PSIPointID, DateRead, TimeRead, iPSI, sRemarks, GlobalVar.UserID, sDateTime, sLocation, Null, Null, $""$, $"0"$, Null, Null))
		Starter.DBCon.TransactionSuccessful
		bRetVal = True
	Catch
		Log(LastException)
		ToastMsg.DefaultTextColor = Colors.White
		ToastMsg.pnl.Color = GlobalVar.RedColor
		MyFunctions.MyToastMsg(ToastMsg, $"Unable to save Pressure from Distribution Reading due to "$ & LastException.Message)
		bRetVal = False
	End Try
	Starter.DBCon.EndTransaction
	Return bRetVal
End Sub

#End Region

#Region Edit PSI Reading
Private Sub ReturnPSIDistRdgDetails(iRdgID As Int)
	Try
		Dim SenderFilter As Object
	
		Starter.strCriteria = "SELECT PressureDistReadings.PSIPointID, PressurePoint.PPointNo, PressurePoint.PLocation, " & _
						  	  "PressureDistReadings.PSIReading, PressureDistReadings.Remarks " & _
						  	  "FROM PressureDistReadings " & _
						  	  "INNER JOIN tblPressurePoint AS PressurePoint ON PressureDistReadings.PSIPointID = PressurePoint.ID " & _
							  "WHERE PressureDistReadings.RdgID = " & iRdgID
							  
		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			RS.Position = 0
			sPointNo = RS.GetString("PPointNo")
			cboPSIPoint.SelectedIndex = cboPSIPoint.IndexOf(sPointNo)
			txtLocation.Text = RS.GetString("PLocation")
			txtPSIRdg.Text = RS.GetString("PSIReading")
			txtRemarks.Text = RS.GetString("Remarks")
		Else
			sPointNo = ""
			cboPSIPoint.SelectedIndex = 0
			txtLocation.Text = ""
			txtPSIRdg.Text = ""
			txtRemarks.Text = ""

			snack.Initialize("", Activity,$"Cannot Return PSI Distribution Reading due to "$ & LastException.Message,5000)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
			snack.Show
			Log(LastException)
		End If

		Starter.strCriteria = ""
		LogColor(Starter.strCriteria, Colors.Magenta)
		
	Catch
		snack.Initialize("", Activity,$"Cannot Return PSI Distribution Reading due to "$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End Try
End Sub

Private Sub UpdatePSIDistRdg(iRdgID As Int) As Boolean
	Dim bRetVal As Boolean
	Dim PSIPointID As Int
	
	PSIPointID = GetPointID(cboPSIPoint.SelectedItem)

	bRetVal = False
	Starter.DBCon.BeginTransaction
	Try
		Starter.strCriteria = "UPDATE PressureDistReadings SET " & _
							  "PSIPointID = ?, " & _
							  "PSIReading = ?, " & _
							  "Remarks = ? " & _
							  "WHERE RdgID = " & iRdgID
		LogColor(Starter.strCriteria, Colors.Yellow)
		
		Starter.DBCon.ExecNonQuery2(Starter.strCriteria, Array As String(PSIPointID, txtPSIRdg.Text, txtRemarks.Text))
		Starter.DBCon.TransactionSuccessful
		bRetVal = True
	Catch
		Log(LastException)
		bRetVal = False
	End Try
	Starter.DBCon.EndTransaction
	Return bRetVal
End Sub
#End Region

#Region Misc Function

Sub FillPressurePoint(iPumpID As Int)
	Dim SenderFilter As Object
	cboPSIPoint.Clear
	Try
		Starter.strCriteria = "SELECT PPointNo FROM tblPressurePoint WHERE PumpHouseID = " & iPumpID

		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			Do While RS.NextRow
				cboPSIPoint.Add(GlobalVar.SF.Upper(RS.GetString("PPointNo")))
			Loop
		Else
			snack.Initialize("", Activity, $"Cannot get Pressure Point due to "$ & LastException.Message,5000)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
			snack.Show
			Log(LastException)
		End If
		txtLocation.Text = GetLocation(cboPSIPoint.SelectedItem)

	Catch
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End Try
End Sub

Private Sub GetLocation (sValue As String) As String
	Dim sRetval As String
	sRetval = ""
	Try
		Starter.strCriteria = "SELECT PLocation FROM tblPressurePoint WHERE UPPER(PPointNo) = '" & sValue & "'"
		sRetval = Starter.DBCon.ExecQuerySingleResult(Starter.strCriteria)
	Catch
		sRetval = ""
		Log(LastException)
	End Try
	Return sRetval
End Sub

Private Sub GetPointID (sValue As String) As Int
	Dim iRetval As Int
	iRetval = 0
	Try
		Starter.strCriteria = "SELECT ID FROM tblPressurePoint WHERE UPPER(PPointNo) = '" & sValue & "'"
		iRetval = Starter.DBCon.ExecQuerySingleResult(Starter.strCriteria)
	Catch
		iRetval = 0
		Log(LastException)
	End Try
	Return iRetval
End Sub

Private Sub ShowSaveSuccess()
	Dim csTitle As CSBuilder
	Dim csContent As CSBuilder
	
	If GlobalVar.blnNewPSIDist = True Then
		csTitle.Initialize.Size(18).Bold.Color(GlobalVar.PosColor).Append($"S U C C E S S!"$).PopAll
		csContent.Initialize.Size(14).Color(Colors.Black).Append($"New PSI Distibution Reading Record has been successfully saved!"$).PopAll
	Else
		csTitle.Initialize.Size(18).Bold.Color(GlobalVar.PosColor).Append($"S U C C E S S!"$).PopAll
		csContent.Initialize.Size(14).Color(Colors.Black).Append($"PSI Distibution Reading Record has been successfully updated!"$).PopAll
	End If
	
	MatDialogBuilder.Initialize("SaveSuccess")
	MatDialogBuilder.PositiveText("OK").PositiveColor(GlobalVar.PosColor)
	MatDialogBuilder.Title(csTitle)
	MatDialogBuilder.Content(csContent)
	MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIGHT)
	MatDialogBuilder.CanceledOnTouchOutside(False)
	MatDialogBuilder.Cancelable(False)
	MatDialogBuilder.Show
End Sub

Private Sub SaveSuccess_ButtonPressed(mDialog As MaterialDialog, sAction As String)
	Select Case sAction
		Case mDialog.ACTION_POSITIVE
			Activity.Finish
		Case mDialog.ACTION_NEGATIVE
	End Select
End Sub

Private Sub GetPumpLocation (iPumpID As Int) As String
	Dim sRetval As String
	sRetval = ""
	Try
		Starter.strCriteria = "SELECT PumpLocation FROM tblPumpStation WHERE StationID = " & iPumpID
		sRetval = Starter.DBCon.ExecQuerySingleResult(Starter.strCriteria)
	Catch
		sRetval = ""
		Log(LastException)
	End Try
	Return sRetval
End Sub

#End Region