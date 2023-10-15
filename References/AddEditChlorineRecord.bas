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
	Private snack As DSSnackbar
		
	Private btnSave As ACButton

	Private cboType As ACSpinner
	Private lblBrand As Label
	Private lblCode As Label
	Private lblModel As Label
	Private lblSerial As Label
	Private lblSPM As Label
	Private lblUnit As Label
	Private txtRemarks As EditText
	Private txtSPMPercent As EditText
	Private txtSPMRate As EditText
	Private txtVolume As EditText
	
	Private SPMRate = 0 As Int
	Private ToastMsg As BCToast
	Private kBoard As IME
	Private sChloType As String
End Sub
#End Region

#Region Activity Events
Sub Activity_Create(FirstTime As Boolean)
	MyScale.SetRate(0.5)
	Activity.LoadLayout("ChlorinatorRecords")

	lblCode.Text = ""
	lblBrand.Text = ""
	lblModel.Text = ""
	lblSerial.Text = ""
	lblSPM.Text = ""

	CD.Initialize2(GlobalVar.GreenColor, 30, 0, Colors.Transparent)
	btnSave.Background = CD
	InpTyp.SetInputType(txtRemarks,Array As Int(InpTyp.TYPE_CLASS_TEXT, InpTyp.TYPE_TEXT_FLAG_AUTO_CORRECT, InpTyp.TYPE_TEXT_FLAG_CAP_SENTENCES))

	CDtxtBox.Initialize(Colors.Transparent,0)
	txtVolume.Background = CDtxtBox
	txtSPMRate.Background = CDtxtBox
	txtSPMPercent.Background = CDtxtBox
	txtRemarks.Background = CDtxtBox
	
	GetChlorinatorData(GlobalVar.PumpHouseID)
	
	If GlobalVar.blnNewChlorine = True Then
		GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"ADD NEW CHLORINE RECORD"$).PopAll
		GlobalVar.CSSubTitle.Initialize.Size(12).Append($"Transaction Date: "$ & GlobalVar.TranDate).PopAll
		btnSave.Text = Chr(0xE161) & $" SAVE"$
		txtVolume.Text = ""
		txtSPMRate.Text = ""
		txtSPMPercent.Text = 0
		FillChlorineType
	Else
		GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"EDIT CHLORINE RECORD"$).PopAll
		GlobalVar.CSSubTitle.Initialize.Size(12).Append($"Transaction Date: "$ & GlobalVar.TranDate).PopAll
		btnSave.Text = Chr(0xE161) & $" UPDATE"$
		FillChlorineType
		ReturnChlorineDetails(GlobalVar.ChlorineDetailID)
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
	kBoard.Initialize("")
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

Sub cboType_ItemClick (Position As Int, Value As Object)
	LogColor($"Selected "$ & Position & " - " & Value,Colors.Yellow)
	lblUnit.Text = GetUOM(Value)
End Sub

Sub btnSave_Click
	If ToastMsg.IsInitialized = False Then ToastMsg.Initialize(Activity)
	If GlobalVar.SF.Len(GlobalVar.SF.Trim(cboType.SelectedItem)) <= 0 Then
		vibration.vibrateOnce(2000)
		ToastMsg.DefaultTextColor = Colors.White
		ToastMsg.pnl.Color = GlobalVar.RedColor
		MyFunctions.MyToastMsg(ToastMsg, $"Please select Chlorine Type!"$)
		cboType.RequestFocus
		Return
	Else If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtVolume.Text)) <= 0 Then
		vibration.vibrateOnce(2000)
		ToastMsg.DefaultTextColor = Colors.White
		ToastMsg.pnl.Color = GlobalVar.RedColor
		MyFunctions.MyToastMsg(ToastMsg, $"Please enter chlorine volume!"$)
		txtVolume.RequestFocus
		kBoard.ShowKeyboard(txtVolume)
		Return
	Else If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtSPMRate.Text)) <= 0 Then
		vibration.vibrateOnce(2000)
		ToastMsg.DefaultTextColor = Colors.White
		ToastMsg.pnl.Color = GlobalVar.RedColor
		MyFunctions.MyToastMsg(ToastMsg, $"Please enter chlorinator stroke per minute!"$ & LastException.Message)
		txtSPMRate.RequestFocus
		kBoard.ShowKeyboard(txtSPMRate)
		Return
	End If
	
	GlobalVar.TranHeaderID = DBaseFunctions.GetHeaderID(GlobalVar.PumpHouseID, GlobalVar.TranDate)
	LogColor($"Header ID: "$ & GlobalVar.TranHeaderID, Colors.Yellow)

	Try
		Select Case GlobalVar.blnNewChlorine
			Case True
				If GlobalVar.TranHeaderID = 0 Then
					If Not(SaveTransHeader) Then Return
					If Not(SaveChlorineDetails) Then Return
				Else
					If Not(SaveChlorineDetails) Then Return
					If Not(UpdateTranHeaderChlorine(GlobalVar.TranHeaderID)) Then Return
				End If
				ShowSaveSuccess
			Case False
				If Not(UpdateChlorineDetails(GlobalVar.TranHeaderID, GlobalVar.ChlorineDetailID)) Then Return
				If Not(UpdateTranHeaderChlorine(GlobalVar.TranHeaderID)) Then Return
				ShowSaveSuccess
		End Select	
	Catch
		ToastMsg.DefaultTextColor = Colors.White
		ToastMsg.pnl.Color = GlobalVar.RedColor
		MyFunctions.MyToastMsg(ToastMsg, $"Unable to save/update due to "$ & LastException.Message)
		Log(LastException)
	End Try
End Sub

Sub txtSPMRate_TextChanged (Old As String, New As String)
	If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtSPMRate.Text)) <= 0 Or SPMRate = 0 Then
		txtSPMPercent.Text = 0
	Else
		txtSPMPercent.Text = Round2(((GlobalVar.SF.Val(txtSPMRate.Text) / SPMRate) * 100),2)
	End If
End Sub

#Region NEW Chlorine Data
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
		
		Starter.DBCon.ExecNonQuery2("INSERT INTO TranHeader VALUES (" & Null & ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", _
														Array As Object(GlobalVar.PumpHouseID, GlobalVar.TranDate, $"0"$, $"0"$, $"0"$, $"0"$, $"0"$, $"0"$, $"0"$, $"1"$, $"0"$, GlobalVar.UserID, GlobalVar.UserID, $"0"$, GlobalVar.UserID, sAddedAt, Null, Null))
		Starter.DBCon.TransactionSuccessful
		bRetVal = True
	Catch
		Log(LastException)
		bRetVal = False
	End Try
	Starter.DBCon.EndTransaction
	Return bRetVal
End Sub

Private Sub SaveChlorineDetails() As Boolean
	Dim bRetVal As Boolean
	Dim lTime As Long
	Dim sRepTime As String
	
	lTime = DateTime.TimeParse(DateTime.Time(DateTime.Now))
	DateTime.TimeFormat = "hh:mm a"
	sRepTime = DateTime.Time(lTime)
	
	GlobalVar.TranHeaderID = DBaseFunctions.GetHeaderID(GlobalVar.PumpHouseID, GlobalVar.TranDate)
	LogColor($"Header ID: "$ & GlobalVar.TranHeaderID, Colors.Yellow)

	Starter.DBCon.BeginTransaction
	Try
		Starter.DBCon.ExecNonQuery2("INSERT INTO ChlorineDetails VALUES (" & Null & ", ?, ?, ?, ?, ?, ?, ?, ?, ?)", _
								   Array As Object(GlobalVar.TranHeaderID, sRepTime, cboType.SelectedItem, txtVolume.Text, lblUnit.Text, txtSPMRate.Text, txtSPMPercent.Text, txtRemarks.Text, $"0"$))
		Starter.DBCon.TransactionSuccessful
		bRetVal = True
	Catch
		Log(LastException)
		ToastMsg.DefaultTextColor = Colors.White
		ToastMsg.pnl.Color = GlobalVar.RedColor
		MyFunctions.MyToastMsg(ToastMsg, $"Unable to save Flow Meter Reading due to "$ & LastException.Message)
		bRetVal = False
	End Try
	Starter.DBCon.EndTransaction
	Return bRetVal
End Sub

Private Sub UpdateTranHeaderChlorine(iTranHeaderID As Int) As Boolean
	Dim bRetVal As Boolean

	Dim lngDateTime As Long
	Dim sModifiedAt As String
	
	DateTime.DateFormat = "yyyy-MM-dd"
	DateTime.TimeFormat = "hh:mm:ss a"
	lngDateTime = DateTime.Now
	sModifiedAt = DateTime.Date(lngDateTime) & $" "$ & DateTime.Time(lngDateTime)

	bRetVal = False
	Starter.DBCon.BeginTransaction
	Try
		Starter.strCriteria = "UPDATE TranHeader SET " & _
							  "IsChlorinated = ?, " & _
							  "ModifiedBy = ?, " & _
							  "ModifiedAt = ? " & _
							  "WHERE HeaderID = " & iTranHeaderID
		LogColor(Starter.strCriteria, Colors.Yellow)
		Starter.DBCon.ExecNonQuery2(Starter.strCriteria, Array As String($"1"$, GlobalVar.UserID, sModifiedAt))
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

#Region Edit Chlorine Data
Private Sub ReturnChlorineDetails(iDetailID As Int)
	Try
		Dim SenderFilter As Object
	
		Starter.strCriteria = "SELECT ChlorineType, Volume, SPMRate, Remarks " & _
						  	  "FROM ChlorineDetails " & _
							  "WHERE DetailID = " & iDetailID
							  
		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			RS.Position = 0
			sChloType = RS.GetString("ChlorineType")
			cboType.SelectedIndex = cboType.IndexOf(sChloType)
			txtVolume.Text = RS.GetInt("Volume")
			txtSPMRate.Text = RS.GetInt("SPMRate")
			txtRemarks.Text = RS.GetString("Remarks")
		Else
			sChloType = ""
			cboType.SelectedIndex = 0
			txtVolume.Text = "0"
			txtSPMRate.Text = "0"
			txtRemarks.Text = ""

			snack.Initialize("", Activity,$""$ & LastException.Message,5000)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
			snack.Show
			Log(LastException)
		End If

		Starter.strCriteria = ""
		LogColor(Starter.strCriteria, Colors.Magenta)
		
	Catch
		Log(LastException)
	End Try
End Sub

Private Sub UpdateChlorineDetails(iTranHeaderID As Int, iDetailID As Int) As Boolean
	Dim bRetVal As Boolean

	bRetVal = False
	Starter.DBCon.BeginTransaction
	Try
		Starter.strCriteria = "UPDATE ChlorineDetails SET " & _
							  "ChlorineType = ?, " & _
							  "Volume = ?, " & _
							  "UoM = ?, " & _
							  "SPMRate = ?, " & _
							  "SPMPercent = ?, " & _
							  "Remarks = ? " & _
							  "WHERE HeaderID = " & iTranHeaderID & " " & _
							  "AND DetailID = " & iDetailID
		LogColor(Starter.strCriteria, Colors.Yellow)
		
		Starter.DBCon.ExecNonQuery2(Starter.strCriteria, Array As String(cboType.SelectedItem, txtVolume.Text, lblUnit.Text, txtSPMRate.Text, txtSPMPercent.Text, txtRemarks.Text))
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
Sub GetChlorinatorData(iPumpID As Int)
	Dim SenderFilter As Object
	Try
		Starter.strCriteria = "SELECT Chlorinator.code AS ChloCode, Chlorinator.brand_name AS BrandName, " & _
							  "Chlorinator.model_no AS ModelNo, Chlorinator.serial_no AS SerialNo, Chlorinator.stroke_per_minute AS SPM " & _
							  "FROM tblChlorinator AS Chlorinator " & _
							  "INNER JOIN tblPumpStation AS Station ON Chlorinator.id = Station.ChlorinatorID " & _
							  "WHERE Station.StationID = 1 = " & iPumpID

		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			RS.Position = 0
			lblCode.Text = GlobalVar.SF.Upper(RS.GetString("ChloCode"))
			lblBrand.Text = GlobalVar.SF.Upper(RS.GetString("BrandName"))
			lblModel.Text = GlobalVar.SF.Upper(RS.GetString("ModelNo"))
			lblSerial.Text = GlobalVar.SF.Upper(RS.GetString("SerialNo"))
			SPMRate = RS.GetString("SPM")
			lblSPM.Text = SPMRate & " SPM"
		Else
			lblCode.Text = ""
			lblBrand.Text = ""
			lblModel.Text = ""
			lblSerial.Text = ""
			lblSPM.Text = 0
			SPMRate = 0
			snack.Initialize("", Activity, $"Cannot get Chlorinator Data due to "$ & LastException.Message,5000)
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

Sub FillChlorineType()
	Dim SenderFilter As Object
	cboType.Clear
	Try
		Starter.strCriteria = "SELECT Chlorine.ChlorineType FROM cons_chlorine AS Chlorine"

		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			Do While RS.NextRow
				cboType.Add(GlobalVar.SF.Upper(RS.GetString("ChlorineType")))
			Loop
		Else
			snack.Initialize("", Activity, $"Cannot get Chlorine Type due to "$ & LastException.Message,5000)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
			snack.Show
			Log(LastException)
		End If
		lblUnit.Text = GetUOM(cboType.SelectedItem)

	Catch
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End Try
End Sub

Private Sub GetUOM (sValue As String) As String
	Dim sRetval As String
	sRetval = ""
	Try
		Starter.strCriteria = "SELECT UOM FROM cons_chlorine WHERE UPPER(ChlorineType) = '" & sValue & "'"
		sRetval = Starter.DBCon.ExecQuerySingleResult(Starter.strCriteria)
	Catch
		sRetval = ""
		Log(LastException)
	End Try
	Return sRetval
End Sub

Private Sub ShowSaveSuccess()
	Dim csTitle As CSBuilder
	Dim csContent As CSBuilder
	
	If GlobalVar.blnNewChlorine = True Then
		csTitle.Initialize.Size(18).Bold.Color(GlobalVar.PosColor).Append($"S U C C E S S!"$).PopAll
		csContent.Initialize.Size(14).Color(Colors.Black).Append($"New Chlorine Record has been successfully saved!"$).PopAll
	Else
		csTitle.Initialize.Size(18).Bold.Color(GlobalVar.PosColor).Append($"S U C C E S S!"$).PopAll
		csContent.Initialize.Size(14).Color(Colors.Black).Append($"Chlorine Record has been successfully updated!"$).PopAll
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

#End Region