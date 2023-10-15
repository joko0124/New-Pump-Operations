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
	Private CD As ColorDrawable
	Private vibration As B4Avibrate
	Private vibratePattern() As Long
	
	Private snack As DSSnackbar
	Private csAns As CSBuilder
	Dim kboard As IME
	
	Private PnlTime As B4XView
	Dim theDate As Long

	'Pump Time On/Off
	Type TimeNonOPRecords (ID As Int, lTimeOff As Long, lTimeOn As Long, dTotNonOpHrs As Double, sNonOpDesc As String, sNonOpReason As String, sNonOpRemarks)
	
	Private clvTime As CustomListView
	Private lblTimeOff As B4XView
	Private lblTimeOn As B4XView
	Private lblOpHrs As B4XView
	Private btnAddTime As DSFloatingActionButton

	Private lSelectedRecTimeOn As Long
	Private blnAddTime As Boolean


	Private iLastReading = 0 As Int
	Private MyToast As BCToast
		
	Dim cdReading, cdRem As ColorDrawable
	Dim Alert As AX_CustomAlertDialog
	Private Font As Typeface = Typeface.LoadFromAssets("myfont.ttf")
	Private FontBold As Typeface = Typeface.LoadFromAssets("myfont_bold.ttf")

End Sub
#End Region

#Region Activity Events
Sub Activity_Create(FirstTime As Boolean)
	MyScale.SetRate(0.5)
	Activity.LoadLayout("NonOperational")

	DateTime.DateFormat = "yyyy-MM-dd"
	
	GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"PUMP - "$ & GlobalVar.PumpHouseCode & $" - NON OPERATIONAL"$).PopAll
	GlobalVar.CSSubTitle.Initialize.Size(12).Append($"Transaction Date: "$ & GlobalVar.TranDate).PopAll
	
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
	
	kboard.Initialize("KeyBoard")
	If FirstTime Then
		GlobalVar.TranHeaderID = DBaseFunctions.GetHeaderID(GlobalVar.PumpHouseID, GlobalVar.TranDate)
		MyToast.Initialize(Activity)
	End If
	
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
	GlobalVar.TranHeaderID = DBaseFunctions.GetHeaderID(GlobalVar.PumpHouseID, GlobalVar.TranDate)
	clvTime.Clear

	If GlobalVar.TranHeaderID > 0 Then	GetPumpNonOPTimeRec(GlobalVar.TranHeaderID)
	MyToast.Initialize(Activity)
End Sub

Sub Activity_Pause (UserClosed As Boolean)
End Sub

#End Region

#Region Toolbar
Sub Activity_CreateMenu(Menu As ACMenu)
'	Dim Item As ACMenuItem
'	Menu.Clear
	'Chr(0xF274)
'	Menu.Add2(1, 1, "Transaction Date",xmlIcon.GetDrawable("ic_date_range_white_24dp")).ShowAsAction = Item.SHOW_AS_ACTION_IF_ROOM
'	Menu.Add2(2, 2, "Settings",xmlIcon.GetDrawable("ic_settings_white_24dp")).ShowAsAction = Item.SHOW_AS_ACTION_ALWAYS
End Sub

Sub ToolBar_NavigationItemClick 'Toolbar Arrow
	Activity.Finish
	kboard.HideKeyboard
End Sub

Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Icon Menus
End Sub

#End Region


#Region Pump Time

Sub GetPumpNonOPTimeRec(iTranHeaderID As Int)
	Dim SenderFilter As Object
	Dim sTimeOFF, sTimeON As String
	Dim iPowerStatus As Int
	
	clvTime.Clear
	
	Try
		Starter.strCriteria = "SELECT Header.HeaderID, Details.DetailID, " & _
						  "Details.PumpOffTime, Details.PumpOnTime, " & _
						  "Details.TotNonOpHrs, Details.NonOpDesc, " & _
						  "Details.NonOpReason, Details.NonOpRemarks " & _
						  "FROM TranHeader AS Header " & _
						  "INNER JOIN NonOpDetails AS Details ON Header.HeaderID = Details.HeaderID " & _
						  "WHERE Header.HeaderID = " & iTranHeaderID & " " & _
						  "ORDER BY Details.DetailID, PumpOffTime ASC"
							  
		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			Dim StartTime As Long = DateTime.Now
			Do While RS.NextRow
				Dim TNonOPRec As TimeNonOPRecords
				TNonOPRec.Initialize
				TNonOPRec.ID = RS.GetInt("DetailID")
				sTimeOFF = RS.GetString("PumpOffTime")
				sTimeON = RS.GetString("PumpOnTime")
				TNonOPRec.dTotNonOpHrs = RS.GetDouble("TotNonOpHrs")
				TNonOPRec.sNonOpDesc = RS.GetString("NonOpDesc")
				TNonOPRec.sNonOpReason= RS.GetString("NonOpReason")
				TNonOPRec.sNonOpRemarks = RS.GetString("NonOpRemarks")
				
				DateTime.TimeFormat = "hh:mm a"

				TNonOPRec.lTimeOff = DateTime.TimeParse(sTimeOFF)
				TNonOPRec.lTimeOn = DateTime.TimeParse(sTimeON)
				
				Dim Pnl As B4XView = xui.CreatePanel("")
				Pnl.SetLayoutAnimated(0, 10dip, 0dip, clvTime.AsView.Width - 10dip, 30dip) 'Panel height + 4 for drop shadow
				clvTime.Add(Pnl, TNonOPRec)
			Loop
		Else
			snack.Initialize("", Activity,$""$ & LastException.Message,5000)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
			snack.Show
			Log(LastException)
		End If

		Log($"List of Time Records = ${NumberFormat2((DateTime.Now - StartTime) / 1000, 0, 2, 2, False)} seconds to populate ${clvTime.Size} Time Records"$)

	Catch
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End Try
End Sub

Sub clvTime_VisibleRangeChanged (FirstIndex As Int, LastIndex As Int)

	Dim ExtraSize As Int = 15 'List size
	For i = Max(0, FirstIndex - ExtraSize) To Min(LastIndex + ExtraSize, clvTime.Size - 1)
		Dim Pnl As B4XView = clvTime.GetPanel(i)
		If i > FirstIndex - ExtraSize And i < LastIndex + ExtraSize Then
			If Pnl.NumberOfViews = 0 Then 'Add each item/layout to the list/main layout
				Dim TimeRec As TimeNonOPRecords = clvTime.GetValue(i)
				
				Pnl.LoadLayout("ListPumpTimeRecords")
				lblTimeOff.TextColor = GlobalVar.PriColor
				DateTime.TimeFormat = "hh:mm a"
				lblTimeOff.Text = DateTime.Time(TimeRec.lTimeOff)
				
				lblTimeOn.TextColor = GlobalVar.PriColor
				DateTime.TimeFormat = "hh:mm a"
				lblTimeOn.Text = DateTime.Time(TimeRec.lTimeOn)
				
				lblOpHrs.Text = TimeRec.dTotNonOpHrs

			End If
		Else 'Not visible
			If Pnl.NumberOfViews > 0 Then
				Pnl.RemoveAllViews 'Remove none visable item/layouts from the list/main layout
			End If
		End If
	Next

End Sub

Sub clvTime_ItemClick (Index As Int, Value As Object)
	Dim Rec As TimeNonOPRecords = Value
	Log(Rec.ID)


	GlobalVar.TimeDetailID = Rec.ID
	GlobalVar.SelectedPumpTime = Rec.lTimeOn

	If Rec.lTimeOff = 0 Then
		blnAddTime = False
		ConfirmPumpOff
	Else
		ShowPumpTimeRecDetails(Rec.ID)
	End If
End Sub

Sub ShowPumpTimeRecDetails (iID As Int)
'	Dim csTitle As CSBuilder
'	Dim sDate, sPCode, sTimeOn, sTimeOff, sPowerSource, sRem As String
'	Dim iPowerSource, iDrainTime, iDrainCum As Int
'	Dim iTotOPHrs As Double
'	Dim rsDetails As Cursor
'	
'	LogColor (iID, Colors.White)
'	Try
'		Starter.strCriteria = "SELECT Header.TranDate, " & _
'						  "Pump.PumpHouseCode, Details.PumpOnTime, Details.PumpOffTime, Details.TotOpHrs, " & _
'						  "Details.PowerSourceID, Details.DrainTime, Details.DrainCum, Details.TimeOnRemarks, Details.TimeOffRemarks " & _
'						  "FROM OnOffDetails AS Details " & _
'						  "INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID " & _
'						  "INNER JOIN tblPumpStation AS Pump ON Pump.StationID = Header.PumpID " & _
'						  "WHERE Details.DetailID = " & iID
'							  
'		rsDetails = Starter.DBCon.ExecQuery(Starter.strCriteria)
'		If rsDetails.RowCount > 0 Then
'			rsDetails.Position = 0
'			sDate = rsDetails.GetString("TranDate")
'			sPCode = rsDetails.GetString("PumpHouseCode")
'			sTimeOn = rsDetails.GetString("PumpOnTime")
'			sTimeOff = rsDetails.GetString("PumpOffTime")
'			iTotOPHrs = rsDetails.GetDouble("TotOpHrs")
'			iPowerSource = rsDetails.GetInt("PowerSourceID")
'			If iPowerSource = 1 Then
'				sPowerSource = $"Electricity"$
'			Else
'				sPowerSource = $"Generator"$
'			End If
'			iDrainTime = rsDetails.GetInt("DrainTime")
'			iDrainCum = rsDetails.GetInt("DrainCum")
'			sRem = rsDetails.GetString("TimeOnRemarks") & " / " & rsDetails.GetString("TimeOffRemarks")
'		Else
''			snack.Initialize("", Activity,$"Cannot fetch detail information due to "$ & LastException.Message,5000)
''			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
''			MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
''			snack.Show
''			Log(LastException)
'			Return
'		End If
'		
'	Catch
'		Log(LastException)
'	End Try
'	rsDetails.Close
'	
'	csTitle.Initialize.Size(18).Bold.Color(GlobalVar.BlueColor).Append($"PUMP ON/OFF DETAILS"$).PopAll
'
'	MatDialogBuilder.Initialize("EditTime")
'	MatDialogBuilder.PositiveText("OK").PositiveColor(GlobalVar.BlueColor)
'	MatDialogBuilder.NegativeText("EDIT").NegativeColor(GlobalVar.GreenColor)
'	MatDialogBuilder.NeutralText("DELETE").NeutralColor(GlobalVar.RedColor)
'	MatDialogBuilder.Title(csTitle)
'	MatDialogBuilder.Content($"  Pump: ${sPCode}
'	Transaction Date: ${sDate}
'
'	Time On:	${sTimeOn}
'	Time Off:	${sTimeOff}
'	Total Operating Hrs:	${NumberFormat2(iTotOPHrs,1, 2, 2,True)} Hr(s).
'	Power Source:	${sPowerSource}
'	Drain Time:	${iDrainTime} Min(s).
'	Drain CuM:	${iDrainCum} CuM
'	Remarks:	${sRem}"$)
'	MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIGHT)
'	MatDialogBuilder.CanceledOnTouchOutside(False)
'	MatDialogBuilder.Cancelable(True)
'	MatDialogBuilder.Show
End Sub

Private Sub EditTime_ButtonPressed(mDialog As MaterialDialog, sAction As String)
'	Select Case sAction
'		Case mDialog.ACTION_POSITIVE
'		Case mDialog.ACTION_NEGATIVE
'			GlobalVar.blnNewTime = False
'			StartActivity(AddEditTimeRecord)
'		Case mDialog.ACTION_NEUTRAL
''			StartActivity(SetTranDate)
'	End Select
End Sub

Sub btnAddTime_Click
	GlobalVar.blnNewTime = True
	If DBaseFunctions.GetPumpPowerStatus(GlobalVar.PumpHouseID) = 1 Then
		blnAddTime = True
		ConfirmPumpOff
	Else
		blnAddTime = False
		StartActivity(AddEditTimeRecord)
	End If
End Sub

Private Sub ConfirmPumpOff
'	Dim Alert As AX_CustomAlertDialog
'	
'	Dim sTitle, sContent As String
'
'	If blnAddTime = True Then
'		sTitle = $"CONFIRM PUMP OFF TIME"$
'		sContent = $"Cannot add new Time record due to Pump is currently running..."$ & CRLF & $"Do you want to Record the Pump Off Time Now?"$
'	Else
'		sTitle = $"CONFIRM PUMP OFF TIME"$
'		sContent = $"Do you want to Record the Pump Off Time Now?"$
'	End If
'	
'	Alert.Initialize.Create _
'			.SetDialogStyleName("MyDialogDisableStatus") _	'Manifest style name
'			.SetStyle(Alert.STYLE_DIALOGUE) _
'			.SetCancelable(False) _
'			.SetTitleTypeface(FontBold) _
'			.SetTitle(sTitle) _
'			.SetTitleColor(GlobalVar.NegColor) _
'			.SetMessageTypeface(Font) _
'			.SetMessage(sContent) _
'			.SetPositiveText("Confirm") _
'			.SetPositiveColor(GlobalVar.PosColor) _
'			.SetPositiveTypeface(FontBold) _
'			.SetOnPositiveClicked("PumpOff") _	'listeners
'			.SetNegativeText("Cancel") _
'			.SetNegativeColor(GlobalVar.NegColor) _
'			.SetNegativeTypeface(FontBold) _
'			.SetOnNegativeClicked("PumpOff")	'listeners
'	Alert.SetDialogBackground(MyFunctions.myCD)
'	Alert.Build.Show
End Sub

'Listeners
Private Sub PumpOff_OnNegativeClicked (View As View, Dialog As Object)
''	ToastMessageShow("Negative Button Clicked!",False)
'	Alert.Initialize.Dismiss(Dialog)
End Sub

Private Sub PumpOff_OnPositiveClicked (View As View, Dialog As Object)
''	ToastMessageShow("Positive Button Clicked!",False)
'	
'	LogColor(GlobalVar.SelectedJOID, Colors.Cyan)
'	Alert.Initialize.Dismiss2
'	StartActivity(actPumpOff)
End Sub

#End Region


Private Sub ShowWarning()
'	Dim csTitle As CSBuilder
'	Dim csContent As CSBuilder
'	
'	If GlobalVar.blnNewTime = True Then
'		csTitle.Initialize.Size(18).Bold.Color(GlobalVar.NegColor).Append($"W A R N I N G!"$).PopAll
'		csContent.Initialize.Size(14).Color(Colors.Black).Append($"New pump time has been successfully saved!"$).PopAll
'	Else
'		csTitle.Initialize.Size(18).Bold.Color(GlobalVar.NegColor).Append($"W A R N I N G!"$).PopAll
'		csContent.Initialize.Size(14).Color(Colors.Black).Append($"Pump time has been successfully updated!"$).PopAll
'	End If
'	
'	
'	Alert.Initialize.Dismiss2
'	Alert.Initialize.Create _
'			.SetDialogStyleName("SaveSuccess") _	'Manifest style name
'			.SetStyle(Alert.STYLE_DIALOGUE) _
'			.SetTitle(csTitle) _
'			.SetMessage(csContent) _
'			.SetPositiveText("OK") _
'			.SetPositiveColor(GlobalVar.PosColor) _
'			.SetPositiveTypeface(FontBold) _
'			.SetTitleTypeface(Font) _
'			.SetMessageTypeface(Font) _
'			.SetOnPositiveClicked("Success") _	'listeners
'			.SetOnViewBinder("FontSizeBinder") _ 'listeners
'			.SetDialogBackground(MyFunctions.myCD)
'	
'	Alert.Build.Show
End Sub



Private Sub Success_OnPositiveClicked (View As View, Dialog As Object)
'	ToastMessageShow("Positive Button Clicked!",False)
'	Dim Alert As AX_CustomAlertDialog
'	Alert.Initialize.Dismiss2
End Sub