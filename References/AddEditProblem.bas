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
	Private xmlIcon As XmlLayoutBuilder
	Private MatDialogBuilder As MaterialDialogBuilder
	Private CD, CDtxtBox As ColorDrawable
	Private vibration As B4Avibrate
	Private vibratePattern() As Long
	
	Private snack As DSSnackbar
	Private csAns As CSBuilder

	Private TabMenu As WobbleMenu
	
	Private btnSaveUpdate As ACButton
	Private chkCritical As B4XView
	Private mskTimeEncountered As MaskedEditText
	Private txtProbTitle As EditText
	Private txtProbDetails As EditText
	
	Private SaveHeaderSuccess, SaveDetailSuccess As Boolean
	Private sTimeProbEnc As String
	Private iHrProb As Int

	Private cboPumpArea As ACSpinner
End Sub
#End Region

#Region Activity Events
Sub Activity_Create(FirstTime As Boolean)
	MyScale.SetRate(0.5)
	Activity.LoadLayout("ProblemRecords")

	InpTyp.Initialize
	InpTyp.SetInputType(txtProbTitle,Array As Int(InpTyp.TYPE_CLASS_TEXT, InpTyp.TYPE_TEXT_FLAG_AUTO_CORRECT, InpTyp.TYPE_TEXT_FLAG_CAP_SENTENCES))
	InpTyp.SetInputType(txtProbDetails,Array As Int(InpTyp.TYPE_CLASS_TEXT, InpTyp.TYPE_TEXT_FLAG_AUTO_CORRECT, InpTyp.TYPE_TEXT_FLAG_CAP_SENTENCES))

	CDtxtBox.Initialize(Colors.Transparent,0)
	mskTimeEncountered.Background = CDtxtBox
	txtProbTitle.Background = CDtxtBox
	txtProbDetails.Background = CDtxtBox
	FillPumpAreas
	
	If GlobalVar.blnNewProblem = True Then
		GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"NEW PROBLEM ENCOUNTERED RECORD"$).PopAll
		GlobalVar.CSSubTitle.Initialize.Size(12).Append($"Transaction Date: "$ & GlobalVar.TranDate).PopAll
		ClearDisplay
		btnSaveUpdate.Text = Chr(0xE161) & $" SAVE"$
	Else
		GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"EDIT PROBLEM ENCOUNTERED RECORD"$).PopAll
		GlobalVar.CSSubTitle.Initialize.Size(12).Append($"Transaction Date: "$ & GlobalVar.TranDate).PopAll
		btnSaveUpdate.Text = Chr(0xE161) & $" UPDATE"$
		FillValues(GlobalVar.ProblemDetailID)
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
	

	If FirstTime Then
	End If
	MyFunctions.SetButton(btnSaveUpdate, 25, 25, 25, 25, 25, 25, 25, 25)
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

Sub ToolBar_NavigationItemClick 'Toolbar Arrow
	Activity.Finish
End Sub

Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Icon Menus
End Sub

#End Region

Sub btnSaveUpdate_Click
	If Not(IsValidEntries) Then Return
	
	Dim Matcher1 As Matcher
	Dim Matcher2 As Matcher
	Dim sMin1, sMin2 As String

	Matcher1 = Regex.Matcher("(\d\d):(\d\d)", mskTimeEncountered.Text)
	If Matcher1.Find Then
		Dim iHrs1, iMins1 As Int
		iHrs1 = Matcher1.Group(1)
		iMins1 = Matcher1.Group(2)
		
		If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins1)) = 1 Then
			sMin1 = $"0"$ & iMins1
		Else
			sMin1 = iMins1
		End If

		If iHrs1 = 0 Then
			iHrProb = 12
			sTimeProbEnc = iHrProb & ":" & sMin1 & " AM"
		Else If iHrs1 > 0 And iHrs1 < 12 Then
			iHrProb = iHrs1
			If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrProb)) = 1 Then
				sTimeProbEnc = $"0"$ & iHrProb & ":" & sMin1 & " AM"
			Else
				sTimeProbEnc = iHrProb & ":" & sMin1 & " AM"
			End If
		Else If iHrs1 = 12 Then
			iHrProb = 12
			sTimeProbEnc = iHrProb & ":" & sMin1 & " PM"
		Else
			iHrProb = iHrs1 - 12
			If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrProb)) = 1 Then
				sTimeProbEnc = $"0"$ & iHrProb & ":" & sMin1 & " PM"
			Else
				sTimeProbEnc = iHrProb & ":" & sMin1 & " PM"
			End If
		End If
	End If
	
	LogColor(sTimeProbEnc,Colors.Yellow)
	
	DateTime.TimeFormat = "hh:mm a"

	
	If chkCritical.Checked = True Then
	Else
	End If

	Select GlobalVar.blnNewProblem
		Case True
			LogColor($"New Time Record"$, Colors.Blue)
			LogColor(sTimeProbEnc,Colors.White)

			GlobalVar.TranHeaderID = DBaseFunctions.GetHeaderID(GlobalVar.PumpHouseID, GlobalVar.TranDate)
			LogColor($"Header ID: "$ & GlobalVar.TranHeaderID, Colors.Yellow)
	
			If GlobalVar.TranHeaderID = 0 Then
				If Not(SaveTransHeader) Then Return
				If Not(InsertNewProbEnc) Then Return
				ShowSaveSuccess
			Else
				If Not(InsertNewProbEnc) Then Return
				If Not(UpdateTranHeader(GlobalVar.TranHeaderID)) Then Return
				ShowSaveSuccess
			End If
			
		Case False
			GlobalVar.TranHeaderID = DBaseFunctions.GetHeaderID(GlobalVar.PumpHouseID, GlobalVar.TranDate)
			LogColor($"Header ID: "$ & GlobalVar.TranHeaderID, Colors.Yellow)

			If Not(UpdateProbEnc(GlobalVar.ProblemDetailID)) Then Return
			If Not(UpdateTranHeader(GlobalVar.TranHeaderID)) Then Return
			ShowSaveSuccess
	End Select
End Sub

Private Sub FillValues(iDetailedID As Int)
	Dim iPowerSource As Int
	Dim SenderFilter As Object
	Dim sTimeStart, sTimeOff As String
	Dim iHrStart, iMinStart As  Int
	Dim Matcher1 As Matcher
	
	Try
	
		Starter.strCriteria = "SELECT ProblemDetails.TimeStart, ProblemDetails.AreaID, ProblemDetails.IsCritical, " & _
						      "ProblemDetails.ProblemTitle, ProblemDetails.ProbDesc " & _
						      "FROM ProblemDetails " & _
						      "WHERE ProblemDetails.DetailID = "  & iDetailedID

		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			RS.Position = 0
			sTimeStart = RS.GetString("TimeStart")
			
			DateTime.TimeFormat = "HH:mm"
			Matcher1 = Regex.Matcher("(\d\d):(\d\d) (\S\S)", sTimeStart)
			
			If Matcher1.Find Then
				Dim iHrS, iMinS As Int
				Dim AmPm As String
				Dim sMin As String
				
				iHrS = Matcher1.Group(1)
				iMinS = Matcher1.Group(2)
				AmPm = Matcher1.Group(3)
				
				LogColor(AmPm,Colors.Cyan)
				
				If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMinS)) = 1 Then
					sMin = $"0"$ & iMinS
				Else
					sMin = iMinS
				End If

				If AmPm = "AM" Then
					If iHrS = 12 Then
						iHrStart = 0
						mskTimeEncountered.Text = $"0"$ & iHrStart & $":"$ & sMin
					Else
						iHrStart = iHrS
						If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrStart)) = 1 Then
							mskTimeEncountered.Text = $"0"$ & iHrStart & $":"$ & sMin
						Else
							mskTimeEncountered.Text = iHrStart & $":"$ & sMin
						End If
					End If
				Else
					If iHrS < 12 Then
						iHrStart = iHrS + 12
						mskTimeEncountered.Text = iHrStart & $":"$ & sMin
					Else
						iHrStart = iHrS
						mskTimeEncountered.Text = iHrStart & $":"$ & sMin
					End If
					iHrStart = iHrS + 12
					mskTimeEncountered.Text = iHrStart & $":"$ & sMin
				End If
			End If
			cboPumpArea.SelectedIndex = RS.GetInt("AreaID")
			If RS.GetInt("IsCritical") = 1 Then
				chkCritical.Checked = True
			Else
				chkCritical.Checked = False
			End If
			txtProbTitle.Text = RS.GetString("ProblemTitle")
			txtProbDetails.Text = RS.GetString("ProbDesc")
		Else
			snack.Initialize("", Activity,$""$ & LastException.Message,5000)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
			snack.Show
			Log(LastException)
		End If
	Catch
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End Try
End Sub

Private Sub IsValidEntries() As Boolean
	Try
	Catch
		Log(LastException)
		Return False
	End Try
	Return True
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
		
		Starter.DBCon.ExecNonQuery2("INSERT INTO TranHeader VALUES (" & Null & ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", _
							   Array As Object(GlobalVar.PumpHouseID, GlobalVar.BranchID, GlobalVar.TranDate, Null, Null, Null, Null, $"0"$, $"0"$, $"0"$, $"0"$, GlobalVar.UserID, $""$, $"0"$, GlobalVar.UserID, sAddedAt, Null, Null))
								   
		Starter.DBCon.TransactionSuccessful
		bRetVal = True
	Catch
		Log(LastException)
		bRetVal = False
	End Try
	Starter.DBCon.EndTransaction
	Return bRetVal
End Sub

Private Sub InsertNewProbEnc() As Boolean
	Dim bRetVal As Boolean
	Dim pAreaID As Int
	Dim isCritical As Int
	Dim sDateTime As String
	Dim lDate As Long
	
	lDate = DateTime.Now
	DateTime.DateFormat = "yyyy-MM-dd hh:mm:ss a"
	sDateTime = DateTime.Date(lDate)
	isCritical = 0
	
	If chkCritical.Checked = True Then
		isCritical = 1
	Else
		isCritical = 0
	End If

	GlobalVar.TranHeaderID = DBaseFunctions.GetHeaderID(GlobalVar.PumpHouseID, GlobalVar.TranDate)
	LogColor($"Header ID: "$ & GlobalVar.TranHeaderID, Colors.Yellow)

	pAreaID = DBaseFunctions.GetIDByCode("ID","PumpAreas", "PumpArea", cboPumpArea.SelectedItem)

	Starter.DBCon.BeginTransaction
	Try
		Starter.DBCon.ExecNonQuery2("INSERT INTO ProblemDetails VALUES (" & Null & ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", _
								   Array As Object(GlobalVar.TranHeaderID, sTimeProbEnc, $""$, pAreaID, isCritical, txtProbTitle.Text, txtProbDetails.Text, $""$, $"0"$, $""$, $""$, $"0"$, Null, $""$, GlobalVar.UserID, sDateTime, Null, Null))
		Starter.DBCon.TransactionSuccessful
		bRetVal = True
	Catch
		Log(LastException)
		bRetVal = False
	End Try
	Starter.DBCon.EndTransaction
	Return bRetVal
End Sub

Private Sub UpdateProbEnc(iDetailID As Int) As Boolean
	Dim bRetVal As Boolean
	Dim pAreaID As Int
	Dim isCritical As Int
	Dim sDateTime As String
	Dim lDate As Long
	
	lDate = DateTime.Now
	DateTime.DateFormat = "yyyy-MM-dd hh:mm:ss a"
	sDateTime = DateTime.Date(lDate)

	If chkCritical.Checked = True Then
		isCritical = 1
	Else
		isCritical = 0
	End If

	GlobalVar.TranHeaderID = DBaseFunctions.GetHeaderID(GlobalVar.PumpHouseID, GlobalVar.TranDate)
	LogColor($"Header ID: "$ & GlobalVar.TranHeaderID, Colors.Yellow)

	pAreaID = DBaseFunctions.GetIDByCode("ID","PumpAreas", "PumpArea", cboPumpArea.SelectedItem)

	bRetVal = False
	Starter.DBCon.BeginTransaction
	Try
		Starter.strCriteria = "UPDATE ProblemDetails SET " & _
							  "TimeStart = ?, " & _
							  "AreaID = ?, " & _
							  "IsCritical = ?, " & _
							  "ProblemTitle = ?, " & _
							  "ProbDesc = ?, " & _
							  "ModifiedBy = ?, " & _
							  "ModifiedAt = ? " & _
							  "WHERE DetailID = " & iDetailID
							  
		Starter.DBCon.ExecNonQuery2(Starter.strCriteria, Array As String(sTimeProbEnc, pAreaID, isCritical, txtProbTitle.Text, txtProbDetails.Text, GlobalVar.UserID, sDateTime))
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
	Dim sModifiedAt As String
	Dim lDate As Long
	
	lDate = DateTime.Now
	DateTime.DateFormat = "yyyy-MM-dd hh:mm:ss a"
	sModifiedAt = DateTime.Date(lDate)

	bRetVal = False

	Starter.DBCon.BeginTransaction
	Try
		Starter.strCriteria = "UPDATE TranHeader SET " & _
							  "ModifiedBy = ?, " & _
							  "ModifiedAt = ? " & _
							  "WHERE HeaderID = " & iTranHeaderID
							  
		Starter.DBCon.ExecNonQuery2(Starter.strCriteria, Array As String(GlobalVar.UserID, sModifiedAt))
		Starter.DBCon.TransactionSuccessful
		bRetVal = True
	Catch
		Log(LastException)
		bRetVal = False
	End Try
	Starter.DBCon.EndTransaction
	Return bRetVal
End Sub

Private Sub ShowSaveSuccess()
	Dim csTitle As CSBuilder
	Dim csContent As CSBuilder
	
	If GlobalVar.blnNewProblem = True Then
		csTitle.Initialize.Size(18).Bold.Color(GlobalVar.PosColor).Append($"S U C C E S S!"$).PopAll
		csContent.Initialize.Size(14).Color(Colors.Black).Append($"New problem encountered record has been successfully saved!"$).PopAll
	Else
		csTitle.Initialize.Size(18).Bold.Color(GlobalVar.PosColor).Append($"S U C C E S S!"$).PopAll
		csContent.Initialize.Size(14).Color(Colors.Black).Append($"Problem encountered record has been successfully updated!"$).PopAll
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

Private Sub FillPumpAreas ()
	Dim SenderFilter As Object
	cboPumpArea.Clear
	Try
		Starter.strCriteria = "SELECT * FROM PumpAreas " & _
							  "ORDER BY PumpAreas.ID ASC"

		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			Do While RS.NextRow
				cboPumpArea.Add(RS.GetString("PumpArea"))
			Loop
		Else
			snack.Initialize("", Activity, $"Cannot get Pump Areas due to "$ & LastException.Message,5000)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
			snack.Show
			Log(LastException)
		End If
'		lblUnit.Text = GetUOM(cboPumpArea.SelectedItem)

	Catch
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End Try
End Sub

Private Sub ClearDisplay
	Try
		mskTimeEncountered.Text = "__:__"
		chkCritical.Checked = False
		txtProbTitle.Text=	 ""
		txtProbDetails.Text = ""
	Catch
		Log(LastException)
	End Try
End Sub