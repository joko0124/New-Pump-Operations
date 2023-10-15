B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=9.9
@EndOfDesignText@
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

	Private TabMenu As WobbleMenu
	
	Private pnlChlorinator As B4XView
	Private pnlFMRdg As B4XView
	Private pnlPSIDist As B4XView
	Private pnlPSIRdg As B4XView
	Private PnlTime As B4XView
	Dim theDate As Long
	
	'Pump Time On Off
	Type TimeRecords (ID As Int, sTimeOn As String, sTimeOff As String, iTotOpHrs As Int, _
					 iPowerSource As Int, iPumpStatus As Int, iDrainCum As Int, sRem As String)
	Private clvTime As CustomListView
	Private lblOpHrs As B4XView
	Private lblTimeOff As B4XView
	Private lblTimeOn As B4XView
	Private btnAddTime As DSFloatingActionButton
	
	'Flow Meter Reading
	Type FMRecords (ID As Int, sRdgTime As String, iPrevRdg As Int, iPresRdg As Int, _
					 iTotProd As Int)
	Private clvFM As CustomListView
	Private lblPresCuM As B4XView
	Private lblPrevCuM As B4XView
	Private lblRdgTime As B4XView
	Private lblTotProd As B4XView
	Private btnAddFM As DSFloatingActionButton

	'PSI Reading
	Type PSIRecords (ID As Int, sRdgTime As String, iPSIRdg As Int)
	Private clvPSI As CustomListView
	Private lblPSIRdgTime As B4XView
	Private lblPSIRdg As B4XView
	Private btnAddPSI As DSFloatingActionButton

	'Chlorinator
	Type ChlorineRecords (ID As Int, sTimeRep As String, sChlorineType As String, iVolume As Int)
	Private clvChlorine As CustomListView
	Private lblTimeRep As B4XView
	Private lblChlorineType As B4XView
	Private lblVolume As B4XView
	Private btnAddChlorine As DSFloatingActionButton

	'PSI Dist
	Type PSIDistRecords (ID As Int, sPSIDistRdgTime As String, sPSIDistLoc As String, iPSIDistRdg As Int)
	Private clvPSIDist As CustomListView
	Private lblDistTimeRead As B4XView
	Private lblLocation As B4XView
	Private lblDistPSIRdg As B4XView
	Private btnAddPSIDist As DSFloatingActionButton
End Sub
#End Region

#Region Activity Events
Sub Activity_Create(FirstTime As Boolean)
	MyScale.SetRate(0.5)
	Activity.LoadLayout("Production")

	DateTime.DateFormat = "MM/dd/yyyy"
	theDate = DateTime.DateParse(GlobalVar.TranDate)
	GlobalVar.TranDate = DateTime.Date(theDate)

	GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"PUMP 001"$).PopAll
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
	

	If FirstTime Then
	End If
	
	TabMenu.SetTabTextIcon(1, "ON/OFF", Chr(0xF017), Typeface.FONTAWESOME)
	TabMenu.SetTabTextIcon(2, "FM RDG", Chr(0xF0E4), Typeface.FONTAWESOME)
	TabMenu.SetTabTextIcon(3, "PSI RDG", Chr(0xF2C7), Typeface.FONTAWESOME)
	TabMenu.SetTabTextIcon(4, "CHLORINE", Chr(0xF187), Typeface.FONTAWESOME)
	TabMenu.SetTabTextIcon(5, "DIST. PSI", Chr(0xF2C7), Typeface.FONTAWESOME)
	
	TabMenu.SetCurrentTab(1)
	
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
	GetPumpTimeRec("06/28/2020",1)
	GetFMRdgRec("06/28/2020",1)
	GetPSIRdgRec("06/28/2020",1)
	GetChlorineRec("06/28/2020",1)
	GetPSIDistRec("06/28/2020",1)
End Sub

Sub Activity_Pause (UserClosed As Boolean)
End Sub

#End Region

#Region Toolbar
Sub Activity_CreateMenu(Menu As ACMenu)
	Dim Item As ACMenuItem
	Menu.Clear
	'Chr(0xF274)
'	Menu.Add2(1, 1, "Transaction Date",xmlIcon.GetDrawable("ic_date_range_white_24dp")).ShowAsAction = Item.SHOW_AS_ACTION_IF_ROOM
'	Menu.Add2(2, 2, "Settings",xmlIcon.GetDrawable("ic_settings_white_24dp")).ShowAsAction = Item.SHOW_AS_ACTION_ALWAYS
End Sub

Sub ToolBar_NavigationItemClick 'Toolbar Arrow
	Activity.Finish
End Sub

Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Icon Menus
End Sub

#End Region

#Region Pump Time
Sub TabMenu_Tab1Click
	PnlTime.Visible = True
	pnlFMRdg.Visible = False
	pnlFMRdg.Visible = False
	pnlPSIRdg.Visible = False
	pnlChlorinator.Visible = False
	pnlPSIDist.Visible = False
	GetPumpTimeRec("06/28/2020",1)
End Sub

Sub GetPumpTimeRec(sTrandate As String, iPumpID As Int)
	Dim SenderFilter As Object
	clvTime.Clear
	Try
		Starter.strCriteria = "SELECT Header.HeaderID, Header.PumpID, Details.DetailID, Details.PumpOnTime AS TimeOn, Details.PumpOffTime AS TimeOff, Details.TotOpHrs " & _
							  "FROM TranHeader AS Header " & _
							  "INNER JOIN OnOffDetails AS Details ON Header.HeaderID = Details.HeaderID " & _
							  "WHERE Header.PumpID = " & iPumpID & " " & _
							  "AND Header.TranDate = '" & sTrandate & "' "  & _
							  "ORDER BY Details.DetailID, TimeOn ASC"
							  
		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			Dim StartTime As Long = DateTime.Now
			Do While RS.NextRow
				Dim TR As TimeRecords
				TR.Initialize
				TR.ID = RS.GetInt("DetailID")
				TR.sTimeOn = RS.GetString("TimeOn")
				TR.sTimeOff = RS.GetString("TimeOff")
				TR.iTotOpHrs = RS.GetInt("TotOpHrs")

				Dim Pnl As B4XView = xui.CreatePanel("")
				Pnl.SetLayoutAnimated(0, 10dip, 0, clvTime.AsView.Width - 10dip, 30dip) 'Panel height + 4 for drop shadow
				clvTime.Add(Pnl, TR)
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
				Dim TR As TimeRecords = clvTime.GetValue(i)
				Pnl.LoadLayout("ListPumpTimeRecords")
				lblTimeOn.TextColor = GlobalVar.PriColor
				lblTimeOff.TextColor = GlobalVar.PriColor
				lblOpHrs.TextColor = GlobalVar.PriColor
				
				lblTimeOn.Text = TR.sTimeOn
				lblTimeOff.Text = TR.sTimeOff
				lblOpHrs.Text = TR.iTotOpHrs & $" Hr(s)."$
			End If
		Else 'Not visible
			If Pnl.NumberOfViews > 0 Then
				Pnl.RemoveAllViews 'Remove none visable item/layouts from the list/main layout
			End If
		End If
	Next

End Sub

Sub clvTime_ItemClick (Index As Int, Value As Object)
	Dim Rec As TimeRecords = Value
	Log(Rec.ID)
	ShowPumpTimeRecDetails(Rec.ID)
	GlobalVar.TimeDetailID = Rec.ID
End Sub

Sub ShowPumpTimeRecDetails (iID As Int)
	Dim csTitle As CSBuilder
	Dim SenderFilter As Object
	Dim sDate, sPCode, sTimeOn, sTimeOff, sPowerSource, sRem As String
	Dim iTotOPHrs, iPowerSource, iDrainTime, iDrainCum As Int
	
	Starter.strCriteria = "SELECT Header.TranDate, " & _
						  "Pump.PumpCode, Details.PumpOnTime, Details.PumpOffTime, Details.TotOpHrs, " & _
						  "Details.PowerSourceID, Details.DrainTime, Details.DrainCum, Details.Remarks " & _
						  "FROM OnOffDetails AS Details " & _
						  "INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID " & _
						  "INNER JOIN tblPumpStation AS Pump ON Pump.PumpID = Header.PumpID " & _
						  "WHERE Details.DetailID = " & iID
							  
	SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
	Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
	If Success Then
		RS.Position = 0
		sDate = RS.GetString("TranDate")
		sPCode = RS.GetString("PumpCode")
		sTimeOn = RS.GetString("PumpOnTime")
		sTimeOff = RS.GetString("PumpOffTime")
		iTotOPHrs = RS.GetInt("TotOpHrs")
		iPowerSource = RS.GetInt("PowerSourceID")
		If iPowerSource = 1 Then
			sPowerSource = $"Electricity"$
		Else
			sPowerSource = $"Generator"$
		End If
		iDrainTime = RS.GetInt("DrainTime")
		iDrainCum = RS.GetInt("DrainCum")
		sRem = RS.GetString("Remarks")
	Else
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End If
	
	csTitle.Initialize.Size(18).Bold.Color(GlobalVar.BlueColor).Append($"PUMP ON/OFF DETAILS"$).PopAll

	MatDialogBuilder.Initialize("EditTime")
	MatDialogBuilder.PositiveText("OK").PositiveColor(GlobalVar.BlueColor)
	MatDialogBuilder.NegativeText("EDIT").NegativeColor(GlobalVar.GreenColor)
	MatDialogBuilder.NeutralText("DELETE").NeutralColor(GlobalVar.RedColor)
	MatDialogBuilder.Title(csTitle)
	MatDialogBuilder.Content($"  Pump: ${sPCode}
	Transaction Date: ${sDate}

	Time On: ${sTimeOn}
	Time Off: ${sTimeOff}
	Total Op. Hrs: ${iTotOPHrs}
	Power Source: ${sPowerSource}
	Drain Time: ${iDrainTime} Min(s).
	Drain CuM: ${iDrainCum} CuM
	Remarks: ${sRem}"$)
	MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIGHT)
	MatDialogBuilder.CanceledOnTouchOutside(False)
	MatDialogBuilder.Cancelable(True)
	MatDialogBuilder.Show
End Sub
Private Sub EditTime_ButtonPressed(mDialog As MaterialDialog, sAction As String)
	Select Case sAction
		Case mDialog.ACTION_POSITIVE	
		Case mDialog.ACTION_NEGATIVE
			GlobalVar.blnNewTime = False
			StartActivity(AddEditTimeRecord)
		Case mDialog.ACTION_NEUTRAL
'			StartActivity(SetTranDate)
	End Select
End Sub

Sub btnAddTime_Click
	GlobalVar.blnNewTime = True
	StartActivity(AddEditTimeRecord)
End Sub
#End Region

#Region FM Reading
Sub TabMenu_Tab2Click
	PnlTime.Visible = False
	pnlFMRdg.Visible = True
	pnlPSIRdg.Visible = False
	pnlChlorinator.Visible = False
	pnlPSIDist.Visible = False
	
	GetFMRdgRec("06/28/2020",1)	
End Sub

Sub GetFMRdgRec(sTrandate As String, iPumpID As Int)
	Dim SenderFilter As Object
	clvFM.Clear
	Try
		Starter.strCriteria = "SELECT Details.DetailID, Details.RdgTime, " & _
							  "Details.PrevRdg, Details.PresRdg, Details.PresCum " & _
							  "FROM ProductionDetails AS Details " & _
							  "INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID " & _
							  "WHERE Header.PumpID = " & iPumpID & " " & _
							  "AND Header.TranDate = '" & sTrandate & "' "  & _
							  "ORDER BY Details.DetailID, Details.RdgTime ASC"
							  
		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			Dim StartTime As Long = DateTime.Now
			Do While RS.NextRow
				Dim FMRec As FMRecords
				FMRec.Initialize
				FMRec.ID = RS.GetInt("DetailID")
				FMRec.sRdgTime = RS.GetString("RdgTime")
				FMRec.iPrevRdg = RS.GetInt("PrevRdg")
				FMRec.iPresRdg = RS.GetInt("PresRdg")
				FMRec.iTotProd = RS.GetInt("PresCum")

				Dim Pnl As B4XView = xui.CreatePanel("")
				Pnl.SetLayoutAnimated(0, 10dip, 0, clvFM.AsView.Width - 10dip, 30dip) 'Panel height + 4 for drop shadow
				clvFM.Add(Pnl, FMRec)
			Loop
		Else
			snack.Initialize("", Activity,$""$ & LastException.Message,5000)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
			snack.Show
			Log(LastException)
		End If

		Log($"List of FM Reading Records = ${NumberFormat2((DateTime.Now - StartTime) / 1000, 0, 2, 2, False)} seconds to populate ${clvFM.Size} Flow Meter Reading Records"$)

	Catch
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End Try
End Sub

Sub clvFM_VisibleRangeChanged (FirstIndex As Int, LastIndex As Int)
	Dim ExtraSize As Int = 15 'List size
	For i = Max(0, FirstIndex - ExtraSize) To Min(LastIndex + ExtraSize, clvFM.Size - 1)
		Dim Pnl As B4XView = clvFM.GetPanel(i)
		If i > FirstIndex - ExtraSize And i < LastIndex + ExtraSize Then
			If Pnl.NumberOfViews = 0 Then 'Add each item/layout to the list/main layout
				Dim FMRec As FMRecords = clvFM.GetValue(i)
				Pnl.LoadLayout("ListFMRdg")
				lblRdgTime.TextColor = GlobalVar.PriColor
				lblPrevCuM.TextColor = GlobalVar.PriColor
				lblPresCuM.TextColor = GlobalVar.PriColor
				lblTotProd.TextColor = GlobalVar.PriColor
				
				lblRdgTime.Text = FMRec.sRdgTime
				lblPrevCuM.Text = FMRec.iPrevRdg
				lblPresCuM.Text = FMRec.iPresRdg
				lblTotProd.Text = NumberFormat(FMRec.iTotProd,0,0) & $" CuM"$
			End If
		Else 'Not visible
			If Pnl.NumberOfViews > 0 Then
				Pnl.RemoveAllViews 'Remove none visable item/layouts from the list/main layout
			End If
		End If
	Next

End Sub

Sub clvFM_ItemClick (Index As Int, Value As Object)
	Dim Rec As FMRecords = Value
	Log(Rec.ID)
	ShowFMRdgRecDetails(Rec.ID)
	GlobalVar.FMRdgDetailID = Rec.ID
End Sub

Sub ShowFMRdgRecDetails (iID As Int)
	Dim csTitle As CSBuilder
	Dim SenderFilter As Object
	Dim sDate, sPCode, sReadTime, sRem As String
	Dim iPreviousRdg, iPresentRdg, iTotCum As Int
	
	Starter.strCriteria = "SELECT Header.TranDate, Pump.PumpCode, " & _
						  "Details.RdgTime, Details.PrevRdg, Details.PresRdg, " & _
						  "Details.PresCum, Details.Remarks " & _
						  "FROM ProductionDetails AS Details " & _
						  "INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID " & _
						  "INNER JOIN tblPumpStation AS Pump ON Pump.StationID = Header.PumpID " & _
						  "WHERE Details.DetailID = " & iID
							  
	SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
	Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
	If Success Then
		RS.Position = 0
		sDate = RS.GetString("TranDate")
		sPCode = RS.GetString("PumpCode")
		sReadTime = RS.GetString("RdgTime")
		iPreviousRdg = RS.GetInt("PrevRdg")
		iPresentRdg = RS.GetInt("PresRdg")
		iTotCum = RS.GetInt("PresCum")
		sRem = RS.GetString("Remarks")
	Else
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End If
	
	csTitle.Initialize.Size(18).Bold.Color(GlobalVar.BlueColor).Append($"FLOW METER READING DETAILS"$).PopAll

	MatDialogBuilder.Initialize("EditFMRdg")
	MatDialogBuilder.PositiveText("OK").PositiveColor(GlobalVar.BlueColor)
	MatDialogBuilder.NegativeText("EDIT").NegativeColor(GlobalVar.GreenColor)
	MatDialogBuilder.NeutralText("DELETE").NeutralColor(GlobalVar.RedColor)
	MatDialogBuilder.Title(csTitle)
	MatDialogBuilder.Content($"  Pump: ${sPCode}
	Transaction Date: ${sDate}

	Reading Time: ${sReadTime}
	Present Reading: ${iPresentRdg}
	Previous Reading: ${iPreviousRdg}
	Total Prod.: ${NumberFormat(iTotCum,0,0)} CuM
	Remarks: ${sRem}"$)
	MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIGHT)
	MatDialogBuilder.CanceledOnTouchOutside(False)
	MatDialogBuilder.Cancelable(True)
	MatDialogBuilder.Show
End Sub
Private Sub EditFMRdg_ButtonPressed(mDialog As MaterialDialog, sAction As String)
	Select Case sAction
		Case mDialog.ACTION_POSITIVE	
		Case mDialog.ACTION_NEGATIVE
			GlobalVar.blnNewFMRdg = False
'			StartActivity(AddEditTimeRecord)
		Case mDialog.ACTION_NEUTRAL
'			StartActivity(SetTranDate)
	End Select
End Sub

Sub btnAddFM_Click
	GlobalVar.blnNewFMRdg = True
'	StartActivity(AddEditTimeRecord)
End Sub

#End Region

#Region PSI Reading
Sub TabMenu_Tab3Click
	PnlTime.Visible = False
	pnlFMRdg.Visible = False
	pnlPSIRdg.Visible = True
	pnlChlorinator.Visible = False
	pnlPSIDist.Visible = False
	
	GetPSIRdgRec("06/28/2020",1)
End Sub

Sub GetPSIRdgRec(sTrandate As String, iPumpID As Int)
	Dim SenderFilter As Object
	clvPSI.Clear
	Try
		Starter.strCriteria = "SELECT Details.DetailID, Details.RdgTime, Details.PSIReading " & _
							  "FROM PressureRdgDetails AS Details " & _
							  "INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID " & _
							  "WHERE Header.PumpID = " & iPumpID & " " & _
							  "AND Header.TranDate = '" & sTrandate & "' "  & _
							  "ORDER BY Details.DetailID, Details.RdgTime ASC"
							  
		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			Dim StartTime As Long = DateTime.Now
			Do While RS.NextRow
				Dim PSIRec As PSIRecords
				PSIRec.Initialize
				PSIRec.ID = RS.GetInt("DetailID")
				PSIRec.sRdgTime = RS.GetString("RdgTime")
				PSIRec.iPSIRdg = RS.GetInt("PSIReading")

				Dim Pnl As B4XView = xui.CreatePanel("")
				Pnl.SetLayoutAnimated(0, 10dip, 0, clvPSI.AsView.Width - 10dip, 30dip) 'Panel height + 4 for drop shadow
				clvPSI.Add(Pnl, PSIRec)
			Loop
		Else
			snack.Initialize("", Activity,$""$ & LastException.Message,5000)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
			snack.Show
			Log(LastException)
		End If

		Log($"List of PSI Reading Records = ${NumberFormat2((DateTime.Now - StartTime) / 1000, 0, 2, 2, False)} seconds to populate ${clvPSI.Size} PSI Reading Records"$)

	Catch
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End Try
End Sub

Sub clvPSI_VisibleRangeChanged (FirstIndex As Int, LastIndex As Int)
	Dim ExtraSize As Int = 15 'List size
	For i = Max(0, FirstIndex - ExtraSize) To Min(LastIndex + ExtraSize, clvPSI.Size - 1)
		Dim Pnl As B4XView = clvPSI.GetPanel(i)
		If i > FirstIndex - ExtraSize And i < LastIndex + ExtraSize Then
			If Pnl.NumberOfViews = 0 Then 'Add each item/layout to the list/main layout
				Dim PSIRec As PSIRecords = clvPSI.GetValue(i)
				Pnl.LoadLayout("ListPSIRecords")
				lblPSIRdgTime.TextColor = GlobalVar.PriColor
				lblPSIRdg.TextColor = GlobalVar.PriColor
				
				lblPSIRdgTime.Text = PSIRec.sRdgTime
				lblPSIRdg.Text = NumberFormat(PSIRec.iPSIRdg,0,2) & $" PSI"$
			End If
		Else 'Not visible
			If Pnl.NumberOfViews > 0 Then
				Pnl.RemoveAllViews 'Remove none visable item/layouts from the list/main layout
			End If
		End If
	Next

End Sub

Sub clvPSI_ItemClick (Index As Int, Value As Object)
	Dim Rec As PSIRecords = Value
	Log(Rec.ID)
	ShowPSIRdgRecDetails(Rec.ID)
	GlobalVar.PSIRdgDetailID = Rec.ID
End Sub

Sub ShowPSIRdgRecDetails (iID As Int)
	Dim csTitle As CSBuilder
	Dim SenderFilter As Object
	Dim sDate, sPCode, sReadTime, sRem As String
	Dim iPressureRdg As Int
	
	Starter.strCriteria = "SELECT Header.TranDate, Pump.PumpCode, " & _
						  "Details.RdgTime, Details.PSIReading, Details.Remarks " & _
						  "FROM PressureRdgDetails AS Details " & _
						  "INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID " & _
						  "INNER JOIN tblPumpStation AS Pump ON Pump.StationID = Header.PumpID " & _
						  "WHERE Details.DetailID = " & iID
							  
	SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
	Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
	If Success Then
		RS.Position = 0
		sDate = RS.GetString("TranDate")
		sPCode = RS.GetString("PumpCode")
		sReadTime = RS.GetString("RdgTime")
		iPressureRdg = RS.GetInt("PSIReading")
		sRem = RS.GetString("Remarks")
	Else
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End If
	
	csTitle.Initialize.Size(18).Bold.Color(GlobalVar.BlueColor).Append($"PRESSURE READING DETAILS"$).PopAll

	MatDialogBuilder.Initialize("EditPSIRdg")
	MatDialogBuilder.PositiveText("OK").PositiveColor(GlobalVar.BlueColor)
	MatDialogBuilder.NegativeText("EDIT").NegativeColor(GlobalVar.GreenColor)
	MatDialogBuilder.NeutralText("DELETE").NeutralColor(GlobalVar.RedColor)
	MatDialogBuilder.Title(csTitle)
	MatDialogBuilder.Content($"  Pump: ${sPCode}
	Transaction Date: ${sDate}

	Reading Time: ${sReadTime}
	Pressure Reading: ${iPressureRdg}  PSI
	Remarks: ${sRem}"$)
	MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIGHT)
	MatDialogBuilder.CanceledOnTouchOutside(False)
	MatDialogBuilder.Cancelable(True)
	MatDialogBuilder.Show
End Sub

Private Sub EditPSIRdg_ButtonPressed(mDialog As MaterialDialog, sAction As String)
	Select Case sAction
		Case mDialog.ACTION_POSITIVE	
		Case mDialog.ACTION_NEGATIVE
			GlobalVar.blnNewPSIRdg = False
'			StartActivity(AddEditTimeRecord)
		Case mDialog.ACTION_NEUTRAL
'			StartActivity(SetTranDate)
	End Select
End Sub

Sub btnAddPSI_Click
	GlobalVar.blnNewPSIRdg = True
'	StartActivity(AddEditTimeRecord)
End Sub


#End Region

#Region Chlorinator
Sub TabMenu_Tab4Click
	PnlTime.Visible = False
	pnlFMRdg.Visible = False
	pnlPSIRdg.Visible = False
	pnlChlorinator.Visible = True
	pnlPSIDist.Visible = False
	
	GetChlorineRec("06/28/2020",1)
End Sub

Sub GetChlorineRec(sTrandate As String, iPumpID As Int)
	Dim SenderFilter As Object
	clvChlorine.Clear
	Try
		Starter.strCriteria = "SELECT Details.DetailID, Details.TimeReplenished, Details.ChlorineType, Details.Volume " & _
							  "FROM ChlorineDetails AS Details " & _
							  "INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID " & _
							  "WHERE Header.PumpID = " & iPumpID & " " & _
							  "AND Header.TranDate = '" & sTrandate & "' "  & _
							  "ORDER BY Details.DetailID, TimeReplenished ASC"
							  
		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			Dim StartTime As Long = DateTime.Now
			Do While RS.NextRow
				Dim CR As ChlorineRecords
				CR.Initialize
				CR.ID = RS.GetInt("DetailID")
				CR.sTimeRep = RS.GetString("TimeReplenished")
				CR.sChlorineType = RS.GetString("ChlorineType")
				CR.iVolume = RS.GetInt("Volume")

				Dim Pnl As B4XView = xui.CreatePanel("")
				Pnl.SetLayoutAnimated(0, 10dip, 0, clvChlorine.AsView.Width - 10dip, 30dip) 'Panel height + 4 for drop shadow
				clvChlorine.Add(Pnl, CR)
			Loop
		Else
			snack.Initialize("", Activity,$""$ & LastException.Message,5000)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
			snack.Show
			Log(LastException)
		End If

		Log($"List of Time Records = ${NumberFormat2((DateTime.Now - StartTime) / 1000, 0, 2, 2, False)} seconds to populate ${clvChlorine.Size} Chlorine Records"$)

	Catch
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End Try
End Sub

Sub clvChlorine_VisibleRangeChanged (FirstIndex As Int, LastIndex As Int)
	Dim ExtraSize As Int = 15 'List size
	For i = Max(0, FirstIndex - ExtraSize) To Min(LastIndex + ExtraSize, clvChlorine.Size - 1)
		Dim Pnl As B4XView = clvChlorine.GetPanel(i)
		If i > FirstIndex - ExtraSize And i < LastIndex + ExtraSize Then
			If Pnl.NumberOfViews = 0 Then 'Add each item/layout to the list/main layout
				Dim CR As ChlorineRecords = clvChlorine.GetValue(i)
				Pnl.LoadLayout("ListChlorinatorRecords")
				lblTimeRep.TextColor = GlobalVar.PriColor
				lblChlorineType.TextColor = GlobalVar.PriColor
				lblVolume.TextColor = GlobalVar.PriColor
				
				lblTimeRep.Text = CR.sTimeRep
				lblChlorineType.Text = CR.sChlorineType
				lblVolume.Text = CR.iVolume & $" Kg(s)."$
			End If
		Else 'Not visible
			If Pnl.NumberOfViews > 0 Then
				Pnl.RemoveAllViews 'Remove none visable item/layouts from the list/main layout
			End If
		End If
	Next

End Sub

Sub clvChlorine_ItemClick (Index As Int, Value As Object)
	Dim Rec As ChlorineRecords = Value
	Log(Rec.ID)
	ShowChlorinatorRecDetails(Rec.ID)
	GlobalVar.ChlorineDetailID = Rec.ID
End Sub

Sub ShowChlorinatorRecDetails (iID As Int)
	Dim csTitle As CSBuilder
	Dim SenderFilter As Object
	Dim sDate, sPCode, sTimeReplenished, sChloType, sRem As String
	Dim iKG As Int
	
	Starter.strCriteria = "SELECT Header.TranDate, Pump.PumpCode, " & _
						  "Details.TimeReplenished, Details.ChlorineType, Details.Volume, Details.Remarks " & _
						  "FROM ChlorineDetails AS Details " & _
						  "INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID " & _
						  "INNER JOIN tblPumpStation AS Pump ON Pump.StationID = Header.PumpID " & _
						  "WHERE Details.DetailID = " & iID
							  
	SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
	Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
	If Success Then
		RS.Position = 0
		sDate = RS.GetString("TranDate")
		sPCode = RS.GetString("PumpCode")
		sTimeReplenished = RS.GetString("TimeReplenished")
		sChloType = RS.GetString("ChlorineType")
		iKG = RS.GetInt("Volume")
		sRem = RS.GetString("Remarks")
	Else
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End If
	
	csTitle.Initialize.Size(18).Bold.Color(GlobalVar.BlueColor).Append($"CHLORINATOR RECORD DETAILS"$).PopAll

	MatDialogBuilder.Initialize("EditChlorineTime")
	MatDialogBuilder.PositiveText("OK").PositiveColor(GlobalVar.BlueColor)
	MatDialogBuilder.NegativeText("EDIT").NegativeColor(GlobalVar.GreenColor)
	MatDialogBuilder.NeutralText("DELETE").NeutralColor(GlobalVar.RedColor)
	MatDialogBuilder.Title(csTitle)
	MatDialogBuilder.Content($"  Pump: ${sPCode}
	Transaction Date: ${sDate}

	Time Replenished: ${sTimeReplenished}
	Chlorine Type: ${sChloType}
	Volume: ${iKG} Kg(s).
	Remarks: ${sRem}"$)
	MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIGHT)
	MatDialogBuilder.CanceledOnTouchOutside(False)
	MatDialogBuilder.Cancelable(True)
	MatDialogBuilder.Show
End Sub

Private Sub EditChlorineTime_ButtonPressed(mDialog As MaterialDialog, sAction As String)
	Select Case sAction
		Case mDialog.ACTION_POSITIVE	
		Case mDialog.ACTION_NEGATIVE
			GlobalVar.blnNewChlorine = False
'			StartActivity(AddEditTimeRecord)
		Case mDialog.ACTION_NEUTRAL
'			StartActivity(SetTranDate)
	End Select
End Sub

Sub btnAddChlorine_Click
	GlobalVar.blnNewChlorine = True
'	StartActivity(AddEditTimeRecord)
End Sub

#End Region

#Region PSI Distribution
Sub TabMenu_Tab5Click
	PnlTime.Visible = False
	pnlFMRdg.Visible = False
	pnlPSIRdg.Visible = False
	pnlChlorinator.Visible = False
	pnlPSIDist.Visible = True
	
	GetPSIDistRec("06/28/2020",1)
End Sub

Sub GetPSIDistRec(sTrandate As String, iPumpID As Int)
	Dim SenderFilter As Object
	clvPSIDist.Clear
	Try
		Starter.strCriteria = "SELECT Details.DetailID, Details.LocID, " & _
							  "Details.RdgLoc, Details.RdgTime, Details.PSIReading " & _
							  "FROM PSIRdgDistDetails As Details " & _
							  "INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID " & _
							  "WHERE Header.PumpID = " & iPumpID & " " & _
							  "AND Header.TranDate = '" & sTrandate & "' "  & _
							  "ORDER BY Details.DetailID, RdgTime ASC"
							  
		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			Dim StartTime As Long = DateTime.Now
			Do While RS.NextRow
				Dim PR As PSIDistRecords
				PR.Initialize
				PR.ID = RS.GetInt("DetailID")
				PR.sPSIDistRdgTime = RS.GetString("RdgTime")
				PR.sPSIDistLoc = RS.GetString("RdgLoc")
				PR.iPSIDistRdg = RS.GetInt("PSIReading")

				Dim Pnl As B4XView = xui.CreatePanel("")
				Pnl.SetLayoutAnimated(0, 10dip, 0, clvPSIDist.AsView.Width - 10dip, 60dip) 'Panel height + 4 for drop shadow
				clvPSIDist.Add(Pnl, PR)
			Loop
		Else
			snack.Initialize("", Activity,$""$ & LastException.Message,5000)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
			snack.Show
			Log(LastException)
		End If

		Log($"List of Time Records = ${NumberFormat2((DateTime.Now - StartTime) / 1000, 0, 2, 2, False)} seconds to populate ${clvPSIDist.Size} Chlorine Records"$)

	Catch
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End Try
End Sub

Sub clvPSIDist_VisibleRangeChanged (FirstIndex As Int, LastIndex As Int)
	Dim ExtraSize As Int = 15 'List size
	For i = Max(0, FirstIndex - ExtraSize) To Min(LastIndex + ExtraSize, clvPSIDist.Size - 1)
		Dim Pnl As B4XView = clvPSIDist.GetPanel(i)
		If i > FirstIndex - ExtraSize And i < LastIndex + ExtraSize Then
			If Pnl.NumberOfViews = 0 Then 'Add each item/layout to the list/main layout
				Dim PR As PSIDistRecords = clvPSIDist.GetValue(i)
				Pnl.LoadLayout("ListPSIDistRecords")
				lblDistTimeRead.TextColor = GlobalVar.PriColor
'				lblLocation.TextColor = GlobalVar.PriColor
				lblDistPSIRdg.TextColor = GlobalVar.PriColor
				
				lblDistTimeRead.Text = PR.sPSIDistRdgTime
				lblLocation.Text = PR.sPSIDistLoc
				lblDistPSIRdg.Text = PR.iPSIDistRdg
			End If
		Else 'Not visible
			If Pnl.NumberOfViews > 0 Then
				Pnl.RemoveAllViews 'Remove none visable item/layouts from the list/main layout
			End If
		End If
	Next

End Sub

Sub clvPSIDist_ItemClick (Index As Int, Value As Object)
	Dim Rec As PSIDistRecords = Value
	Log(Rec.ID)
	ShowPSIDistRecDetails(Rec.ID)
	GlobalVar.PSIDistDetailID = Rec.ID
End Sub

Sub ShowPSIDistRecDetails (iID As Int)
	Dim csTitle As CSBuilder
	Dim SenderFilter As Object
	Dim sDate, sPCode, sDistributionTime, sLocation, sRem As String
	Dim iPSIDistRdg As Int
	
	Starter.strCriteria = "SELECT Header.TranDate, Pump.PumpCode, " & _
						  "Details.RdgLoc, Details.RdgTime, Details.PSIReading, Details.Remarks " & _
						  "FROM PSIRdgDistDetails AS Details " & _
						  "INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID " & _
						  "INNER JOIN tblPumpStation AS Pump ON Pump.StationID = Header.PumpID " & _
						  "WHERE Details.DetailID = " & iID
							  
	SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
	Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
	If Success Then
		RS.Position = 0
		sDate = RS.GetString("TranDate")
		sPCode = RS.GetString("PumpCode")
		sDistributionTime = RS.GetString("RdgTime")
		sLocation = RS.GetString("RdgLoc")
		iPSIDistRdg = RS.GetInt("PSIReading")
		sRem = RS.GetString("Remarks")
	Else
		snack.Initialize("", Activity,$""$ & LastException.Message,5000)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		snack.Show
		Log(LastException)
	End If
	
	csTitle.Initialize.Size(18).Bold.Color(GlobalVar.BlueColor).Append($"PRESSURE (Distribution) RECORD DETAILS"$).PopAll

	MatDialogBuilder.Initialize("EditPSIDistRec")
	MatDialogBuilder.PositiveText("OK").PositiveColor(GlobalVar.BlueColor)
	MatDialogBuilder.NegativeText("EDIT").NegativeColor(GlobalVar.GreenColor)
	MatDialogBuilder.NeutralText("DELETE").NeutralColor(GlobalVar.RedColor)
	MatDialogBuilder.Title(csTitle)
	MatDialogBuilder.Content($"  Pump: ${sPCode}
	Transaction Date: ${sDate}

	Time Read: ${sDistributionTime}
	Location: ${sLocation}
	Pressure Reading: ${iPSIDistRdg} PSI
	Remarks: ${sRem}"$)
	MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIGHT)
	MatDialogBuilder.CanceledOnTouchOutside(False)
	MatDialogBuilder.Cancelable(True)
	MatDialogBuilder.Show
End Sub

Private Sub EditPSIDistRec_ButtonPressed(mDialog As MaterialDialog, sAction As String)
	Select Case sAction
		Case mDialog.ACTION_POSITIVE	
		Case mDialog.ACTION_NEGATIVE
			GlobalVar.blnNewPSIDist = False
'			StartActivity(AddEditTimeRecord)
		Case mDialog.ACTION_NEUTRAL
'			StartActivity(SetTranDate)
	End Select
End Sub

Sub btnAddPSIDist_Click
	GlobalVar.blnNewPSIDist = True
'	StartActivity(AddEditTimeRecord)
End Sub

#End Region
