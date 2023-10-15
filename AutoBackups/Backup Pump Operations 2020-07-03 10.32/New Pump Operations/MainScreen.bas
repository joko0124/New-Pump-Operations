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

Sub Activity_CreateMenu(Menu As ACMenu)
	Dim Item As ACMenuItem
	Menu.Clear
	'Chr(0xF274)
	Menu.Add2(1, 1, "Transaction Date",xmlIcon.GetDrawable("ic_date_range_white_24dp")).ShowAsAction = Item.SHOW_AS_ACTION_IF_ROOM
	Menu.Add2(2, 2, "Settings",xmlIcon.GetDrawable("ic_settings_white_24dp")).ShowAsAction = Item.SHOW_AS_ACTION_ALWAYS
End Sub

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

	Private Drawer As B4XDrawer
	Private lvMenus As ListView
	Private pnlMenuHeader As Panel
	Private lblUserBranch As Label
	Private lblUserFullName As Label

	Private img1 As ImageView
	Private img2 As ImageView
	Private img3 As ImageView
	Private img4 As ImageView
	Private img5 As ImageView
	Private img6 As ImageView
	Private pnlJO As Panel
	Private pnlNonOp As Panel
	Private pnlProd As Panel
	Private pnlRepair As Panel
	Private pnlStatus As Panel
	Private lblBranchName As Label
	Private lblEmpName As Label
	Private pnlWB As Panel
	Private pnlReports As Panel
	
	Private SelectedWBEntry As Int
	Private SelectedRepairs As Int
	Private lblReadingPeriod As Label
	Private lblStatus As Label

	Dim theDate As Long
	
	Dim dblInitRdg, dblLastRdg As Double
End Sub

Sub Activity_Create(FirstTime As Boolean)
	MyScale.SetRate(0.5)
	Drawer.Initialize(Me,"MainMenu",Activity, 82%x)
	Drawer.CenterPanel.LoadLayout("MainScreen")

	GlobalVar.CSTitle.Initialize.Size(15).Bold.Append(Application.LabelName).PopAll
	GlobalVar.CSSubTitle.Initialize.Size(12).Append(Application.VersionName).PopAll
	
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
	ActionBarButton.UpIndicatorBitmap = LoadBitmapSample(File.DirAssets, "hamburger.png", 80dip, 80dip)

	If FirstTime Then
'		CreateMainMenu(GlobalVar.Mod1, GlobalVar.Mod2, GlobalVar.Mod3, GlobalVar.Mod4, GlobalVar.Mod5, GlobalVar.Mod6, GlobalVar.Mod7, GlobalVar.Mod8)
		CreateMainMenu(1, 1, 1, 1, 1, 1, 1, 1)

'		If GlobalVar.Mod5 = 1 Then
'			ShowWelcomeAdminDialog
'		Else
'			ShowWelcomeDialog
'		End If
		'
'		snack.Initialize("",Activity, $"Welcome back to Meter Reader on Android!"$,snack.DURATION_LONG)
'		myfunctions.SetSnackBarBackground(snack,GlobalVar.PriColor)
'		myfunctions.SetSnackBarTextColor(snack,Colors.White)
'		snack.Show
		GlobalVar.TranDate = DateTime.Date(DateTime.Now)
		ShowWelcomeDialog
	End If
	csAns.Initialize.Color(Colors.White).Bold.Append($"YES"$).PopAll
	Drawer.LeftPanel.LoadLayout("MainMenu")
	
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = 4 Then
		Drawer.LeftOpen = False
		vibration.vibrateOnce(1000)
		snack.Initialize("LogOFF", Activity, $"Sure to Log Off now?"$, snack.DURATION_SHORT)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.RedColor)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		snack.SetAction(csAns)
		snack.Show
		Return True
	Else
		Return False
	End If
End Sub

Sub Activity_Resume
	CreateMainMenu(1, 1, 1, 1, 1, 1, 1, 1)
	lblBranchName.Text = GlobalVar.BranchName
	lblReadingPeriod.Text = $"PERIOD COVERED: "$ & GlobalVar.RdgFrm & " - " & GlobalVar.RdgTo

	DateTime.DateFormat = "MM/dd/yyyy"
	theDate = DateTime.DateParse(GlobalVar.TranDate)
	GlobalVar.TranDate = DateTime.Date(theDate)
	lblStatus.Text =$"TRANSACTION DATE: "$ & GlobalVar.TranDate
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	If UserClosed Then ExitApplication
End Sub


Sub ToolBar_NavigationItemClick
'	If MatDrawer.IsInitialized Then
'		MatDrawer.OpenDrawer
'	End If
	Drawer.LeftOpen = Not(Drawer.LeftOpen)
End Sub

Sub ToolBar_MenuItemClick (Item As ACMenuItem)
	Select Case Item.Id
		Case 1 'Transaction Date
'			StartActivity(SetTranDate)
		Case 2 'Settings
'			StartActivity(ReadingSettings)
'			StartActivity(Settings)
	End Select
End Sub

#Region MainMenu
Private Sub CreateMainMenu(iMod1 As Int, iMod2 As Int, iMod3 As Int, iMod4 As Int, iMod5 As Int, iMod6 As Int, iMod7 As Int, iMod8 As Int)
	Dim civ As CircularImageView
	Dim imgBack As BitmapDrawable
	Dim CDPressed,CDNormal As ColorDrawable
	Dim SLD As StateListDrawable
	Dim csMenu1, csMenu2, csMenu3, csMenu4, csMenu5, csMenu6, csMenu7, csMenu8 As CSBuilder
	Dim sIcon1, sIcon2, sIcon3, sIcon4, sIcon5, sIcon6, sIcon7, sIcon8 As Object
	
	Dim fMainMenu, fSecMenu As Typeface
		
	'User Icon
	imgBack.Initialize( LoadBitmap(File.DirAssets,"profile3.jpg"))

	If pnlMenuHeader.IsInitialized = False Then pnlMenuHeader.Initialize("")
	If civ.IsInitialized = False Then civ.Initialize("")

	civ.BorderWidth = 2dip
	civ.BorderColor = GlobalVar.PriColor
	civ.Color = Colors.Transparent
	civ.SetDrawable ( imgBack )
	pnlMenuHeader.AddView(civ,20,2%y,100,100)
	
	'Menu State colors
	CDNormal.Initialize(Colors.White,0) 'Normal Color
	CDPressed.Initialize(0xFFD3D3D3,0)  'Pressed Color

	SLD.Initialize
	SLD.AddState(SLD.State_Pressed, CDNormal)
	SLD.AddState(-SLD.State_Pressed, CDPressed)
	
	If lblUserFullName.IsInitialized = False Then lblUserFullName.Initialize("")
	If lblUserBranch.IsInitialized = False Then lblUserBranch.Initialize("")
	If lvMenus.IsInitialized = False Then lvMenus.Initialize("lvMenus")
	
	Dim LVO As JavaObject = lvMenus
	LVO.RunMethod("setSelector",Array As Object(SLD))
	lvMenus.FastScrollEnabled=True
	
	'Header Panel
	lblUserFullName.Text = GlobalVar.SF.Upper(GlobalVar.EmpName)
	lblUserBranch.Text = GlobalVar.BranchName
	
	fMainMenu = Typeface.LoadFromAssets("Roboto-Bold.ttf")
	fSecMenu = Typeface.LoadFromAssets("roboto-regular.ttf")

	'Menu Colors
	If iMod1 = 1 Then
		csMenu1.Initialize.Color(GlobalVar.BlueColor).Append($"Pump Production"$).PopAll
		sIcon1 = MyFunctions.FontBit(Chr(0xF043),17,GlobalVar.BlueColor, True)
	Else
		csMenu1.Initialize.Color(Colors.LightGray).Append($"Pump Production"$).PopAll
		sIcon1 = MyFunctions.FontBit(Chr(0xF043),17,Colors.LightGray, True)
	End If
	
	If iMod2 = 1 Then
		csMenu2.Initialize.Color(GlobalVar.BlueColor).Append($"Repair and Maintenance"$).PopAll
		sIcon2 = MyFunctions.FontBit(Chr(0xF0AD),17,GlobalVar.BlueColor, True)
	Else
		csMenu2.Initialize.Color(Colors.LightGray).Append($"Repair and Maintenance"$).PopAll
		sIcon2 = MyFunctions.FontBit(Chr(0xF0AD),17,Colors.LightGray, True)
	End If
	
	If iMod3 = 1 Then
		csMenu3.Initialize.Color(GlobalVar.BlueColor).Append($"Pump Non-operational"$).PopAll
		sIcon3 = MyFunctions.FontBit(Chr(0xE0C4),17,GlobalVar.BlueColor, False)
	Else
		csMenu3.Initialize.Color(Colors.LightGray).Append($"Pump Non-operational"$).PopAll
		sIcon3 = MyFunctions.FontBit(Chr(0xE0C4),17,Colors.LightGray, False)
	End If
	
	If iMod4 = 1 Then
		csMenu4.Initialize.Color(GlobalVar.BlueColor).Append($"Job Orders"$).PopAll
		sIcon4 = MyFunctions.FontBit(Chr(0xF022),17,GlobalVar.BlueColor, True)
	Else
		csMenu4.Initialize.Color(Colors.LightGray).Append($"Job Orders"$).PopAll
		sIcon4 = MyFunctions.FontBit(Chr(0xF022),17,Colors.LightGray, True)
	End If
	
	If iMod5 = 1 Then
		csMenu5.Initialize.Color(GlobalVar.BlueColor).Append($"Water Balance Entry"$).PopAll
		sIcon5 = MyFunctions.FontBit(Chr(0xF201),17,GlobalVar.BlueColor, True)
	Else
		csMenu5.Initialize.Color(Colors.LightGray).Append($"Water Balance Entry"$).PopAll
		sIcon5 = MyFunctions.FontBit(Chr(0xF201),17,Colors.LightGray, True)
	End If
	
	If iMod6 = 1 Then
		csMenu6.Initialize.Color(GlobalVar.BlueColor).Append($"Data Syncing"$).PopAll
		sIcon6 = MyFunctions.FontBit(Chr(0xE8D5),17,GlobalVar.BlueColor,False)
	Else
		csMenu6.Initialize.Color(Colors.LightGray).Append($"Data Syncing"$).PopAll
		sIcon6 = MyFunctions.FontBit(Chr(0xE8D5),17,Colors.LightGray,False)
	End If

	If iMod7 = 1 Then
		csMenu7.Initialize.Color(GlobalVar.BlueColor).Append($"Reading Settings"$).PopAll
		sIcon7 = MyFunctions.FontBit(Chr(0xF073),17,GlobalVar.BlueColor, True)
	Else
		csMenu7.Initialize.Color(Colors.LightGray).Append($"Reading Settings"$).PopAll
		sIcon7 = MyFunctions.FontBit(Chr(0xF073),17,Colors.LightGray, True)
	End If

	If iMod8 = 1 Then
		csMenu8.Initialize.Color(GlobalVar.BlueColor).Append($"User Settings"$).PopAll
		sIcon8 = MyFunctions.FontBit(Chr(0xE851),17,GlobalVar.BlueColor,False)
	Else
		csMenu8.Initialize.Color(Colors.LightGray).Append($"User Settings"$).PopAll
		sIcon8 = MyFunctions.FontBit(Chr(0xE851),17,Colors.LightGray,False)
	End If
	lvMenus.Clear
	
	'Add Menu to list
	lvMenus.AddTwoLinesAndBitmap2(csMenu1, $"Input Time On/Off, Flow Meter and PSI Reading"$, sIcon1, 1)
	lvMenus.AddTwoLinesAndBitmap2(csMenu2, $"Allow to Add Repair & Maintenance Entry"$, sIcon2, 2)
	lvMenus.AddTwoLinesAndBitmap2(csMenu3, $"Specify Date, Time & Reason for Pump non operational"$, sIcon3, 3)
	lvMenus.AddTwoLinesAndBitmap2(csMenu4, $"Allow to post JO findings"$, sIcon4, 4)
	lvMenus.AddTwoLinesAndBitmap2(csMenu5, $"Allow to Input Water Balance Entries"$, sIcon5, 5)
	lvMenus.AddTwoLinesAndBitmap2(csMenu6, $"Download/Upload Data from/to Database Server"$, sIcon6, 6)
	lvMenus.AddTwoLinesAndBitmap2(csMenu7, $"Allow to Set Current Reading Date"$, sIcon7, 7)
	lvMenus.AddTwoLinesAndBitmap2(csMenu8, $"Change User Name and/or User Password"$, sIcon8, 8)
	lvMenus.AddTwoLinesAndBitmap2($"Log Out "$ & GlobalVar.SF.Upper(GlobalVar.UserName),$"Log-out Session"$,MyFunctions.FontBit(Chr(0xF08B),17,GlobalVar.BlueColor,True), 9)
	lvMenus.AddTwoLinesAndBitmap2($"Close App"$,$"Close Pump Operation App"$,MyFunctions.FontBit(Chr(0xF2D4),17,GlobalVar.BlueColor,True), 10)

	lvMenus.TwoLinesAndBitmap.Label.TextColor = GlobalVar.BlueColor
	lvMenus.TwoLinesAndBitmap.Label.TextSize = 13
	lvMenus.TwoLinesAndBitmap.SecondLabel.TextSize = 8
	lvMenus.TwoLinesAndBitmap.SecondLabel.TextColor = 0xFF808080
	lvMenus.TwoLinesAndBitmap.SecondLabel.Typeface =fSecMenu
	lvMenus.TwoLinesAndBitmap.Label.Typeface = fMainMenu
	lvMenus.TwoLinesAndBitmap.ItemHeight = 50dip
End Sub

Sub lvMenus_ItemClick (Position As Int, Value As Object)
	LogColor(Value, Colors.Red)
	Select Case Value
		Case 1
'			If GlobalVar.Mod1 = 1 Then
'				If DBaseFunctions.IsThereBookAssignments(GlobalVar.BranchID, GlobalVar.BillYear, GlobalVar.BillMonth, GlobalVar.UserID) = False Then
'					snack.Initialize("",Activity,$"No Assigned book(s) for this Reader!"$,snack.DURATION_LONG)
'					myfunctions.SetSnackBarBackground(snack,Colors.Red)
'					myfunctions.SetSnackBarTextColor(snack,Colors.White)
'					snack.Show
'					Return
'				End If
'				StartActivity(ReadingBooks)
'			Else
'				Return
'			End If
		
		Case 2
'			If GlobalVar.Mod2 = 1 Then
'				StartActivity(CustomerList)
'				ProgressDialogShow2($"Loading Customer's Billing Data..."$, True)
'			Else
'				Return
'			End If
		
		Case 3
'			If GlobalVar.Mod3 = 1 Then
'				StartActivity(CMRVR)
'			Else
'				Return
'			End If

		Case 4
'			If GlobalVar.Mod4 = 1 Then
'				StartActivity(ReadingSettings)
'			Else
'				Return
'			End If
		
		Case 5
'			If GlobalVar.Mod5 = 1 Then
'				StartActivity(DataSyncing)
'			Else
'				Return
'			End If
		
		Case 6
'			If GlobalVar.Mod6 = 1 Then
'				StartActivity(UserAccountSettings)
'			Else
'				Return
'			End If
		
		Case 7
'			vibration.vibrateOnce(1000)
'			snack.Initialize("LogOFF", Activity, $"Sure to Log Off now?"$, snack.DURATION_SHORT)
'			myfunctions.SetSnackBarBackground(snack, Colors.Red)
'			myfunctions.SetSnackBarTextColor(snack, Colors.White)
'			snack.SetAction(csAns)
'			snack.Show
		
		Case 8
'			vibration.vibrateOnce(1000)
'			snack.Initialize("CloseButton", Activity, $"Close "$ & Application.LabelName & $"?"$,snack.DURATION_LONG)
'			snack.SetAction(csAns)
'			myfunctions.SetSnackBarBackground(snack, Colors.Red)
'			myfunctions.SetSnackBarTextColor(snack, Colors.White)
'			snack.Show

		Case 9
			vibration.vibrateOnce(1000)
			snack.Initialize("LogOFF", Activity, $"Sure to Log Off now?"$, snack.DURATION_SHORT)
			MyFunctions.SetSnackBarBackground(snack, Colors.Red)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			snack.SetAction(csAns)
			snack.Show
		
		Case 10
			vibration.vibrateOnce(1000)
			snack.Initialize("CloseButton", Activity, $"Close "$ & Application.LabelName & $"?"$,snack.DURATION_LONG)
			snack.SetAction(csAns)
			MyFunctions.SetSnackBarBackground(snack, Colors.Red)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			snack.Show
	End Select
	
	Dim CDBack As ColorDrawable
	CDBack.Initialize(Colors.Transparent,0)
	lvMenus.Background = CDBack
	Drawer.LeftOpen = False
End Sub

Private Sub LogOFF_Click()
	Activity.Finish
	StartActivity(Main)
End Sub

Private Sub CloseButton_Click()
	ExitApplication
End Sub

#End Region

#Region Assignments

#End Region

#Region Welcome
Private Sub ShowWelcomeDialog()
	Dim theDate As Long
	Dim sTranDate As String
	
	Dim csTitle As CSBuilder
	Dim csContent As CSBuilder
	Dim csDate As CSBuilder
	
	theDate = DateTime.DateParse(DateTime.Date(DateTime.Now))
	DateTime.DateFormat = "MMM. dd, yyyy"
	sTranDate = DateTime.Date(theDate)
	
	csTitle.Initialize.Size(18).Bold.Color(GlobalVar.PosColor).Append($"W E L C O M E!"$).PopAll
'	csDate.Initialize.Size(14).Color(GlobalVar.PosColor).Append(sTranDate).PopAll
	csContent.Initialize.Size(14).Color(Colors.Black).Append($"Welcome to BWSI's Pump Operations Application!
	
	Current Date: "$).Pop
	csContent.Bold.Color(GlobalVar.NegColor).Append(sTranDate).PopAll
	
'	78, 205, 196
	MatDialogBuilder.Initialize("ShowDate")
	MatDialogBuilder.PositiveText("OK").PositiveColor(GlobalVar.PosColor)
	MatDialogBuilder.NeutralText("SET DATE").NeutralColor(GlobalVar.NegColor)
	MatDialogBuilder.Title(csTitle)
	MatDialogBuilder.Content(csContent)
	MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIGHT)
	MatDialogBuilder.CanceledOnTouchOutside(False)
	MatDialogBuilder.Cancelable(False)
	MatDialogBuilder.Show
	
End Sub

Private Sub ShowDate_ButtonPressed(mDialog As MaterialDialog, sAction As String)
	Select Case sAction
		Case mDialog.ACTION_POSITIVE
	
			theDate = DateTime.DateParse(DateTime.Date(DateTime.Now))
			DateTime.DateFormat = "MM/dd/yyyy"
			GlobalVar.TranDate = DateTime.Date(theDate)
			lblStatus.Text =$"TRANSACTION DATE: "$ & GlobalVar.TranDate
		Case mDialog.ACTION_NEGATIVE
		Case mDialog.ACTION_NEUTRAL
'			StartActivity(SetTranDate)
	End Select
End Sub
#End Region

#Region Repairs and Maintenance
Sub pnlRepair_Click
	ShowRepairLists
End Sub

Private Sub ShowRepairLists
	Dim csTitle As CSBuilder

	csTitle.Initialize.Color(GlobalVar.PriColor).Bold.Size(16).Append($"SELECT REPAIR AND MAINTENANCE CATEGORY"$).PopAll
	MatDialogBuilder.Initialize("RepairAndMaintenance")
	MatDialogBuilder.Title(csTitle)
	MatDialogBuilder.Items(Array As String($"Pump and Motors"$,$"Well"$, $"Control Panel"$, $"Chlorinator"$, $"Generator"$, $"Meter Set Assembly"$, $"Change Pump Flow Meter"$))
	MatDialogBuilder.PositiveText($"SELECT"$).PositiveColor(GlobalVar.PosColor)
	MatDialogBuilder.NegativeText($"CANCEL"$).NegativeColor(GlobalVar.NegColor)
	MatDialogBuilder.Cancelable(False)
	MatDialogBuilder.CanceledOnTouchOutside(False)
	MatDialogBuilder.itemsCallbackSingleChoice(0)
	MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIGHT)
	MatDialogBuilder.Show
End Sub

Private Sub RepairAndMaintenance_OnDismiss (Dialog As MaterialDialog)
	Log("Dialog dismissed")
End Sub

Private Sub RepairAndMaintenance_SingleChoiceItemSelected (Dialog As MaterialDialog, Position As Int, Text As String)
	SelectedRepairs = Position
End Sub
	
Private Sub RepairAndMaintenance_ButtonPressed (mDialog As MaterialDialog, sAction As String)
	Select Case sAction
		Case mDialog.ACTION_POSITIVE
			LogColor(SelectedRepairs, Colors.Red)
			Select Case SelectedRepairs
				Case 0
				Case 1
				Case 2
				Case 3
				Case 4
				Case 5
				Case 6
			End Select
		Case mDialog.ACTION_NEGATIVE
	End Select
End Sub

#End Region

#Region Production
Sub pnlProd_Click
'	ShowPumpList(1)
	StartActivity(actProduction)
End Sub

Private Sub ShowPumpList (iUserID As Double)
	Dim rsPumps As Cursor
	Dim csTitle As CSBuilder
	Dim Pumps As String
	Dim PumpList() As String
	Dim pCount As Int
	
	Try
		Starter.strCriteria = "SELECT Assigned.StationID, Station.PumpHouseCode FROM tblPumpStation AS Station " & _
							  "INNER JOIN tblAssignedStation AS Assigned ON Station.StationID = Assigned.StationID " & _
							  "WHERE Assigned.OpID = " & iUserID & " " & _
							  "ORDER BY Station.StationID ASC"
							  
		LogColor(Starter.strCriteria, Colors.Blue)
		
		rsPumps =  Starter.DBCon.ExecQuery (Starter.strCriteria)
		If rsPumps.RowCount > 0 Then
			pCount = rsPumps.RowCount
			Dim PumpList(pCount) As String
			
			For i = 0 To rsPumps.RowCount - 1
				rsPumps.Position = i
				Pumps = rsPumps.GetString("PumpHouseCode")
				PumpList(i) = Pumps
			Next
		Else
			snack.Initialize("", Activity, "No Assigned Pump(s) found!",snack.DURATION_SHORT)
			MyFunctions.SetSnackBarBackground(snack, Colors.Red)
			MyFunctions.SetSnackBarTextColor(snack, Colors.White)
			snack.Show
			Return
		End If
	Catch
		snack.Initialize("", Activity, LastException,snack.DURATION_SHORT)
		MyFunctions.SetSnackBarBackground(snack, Colors.Red)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		snack.Show
		Return
	End Try
	
	csTitle.Initialize.Color(GlobalVar.PriColor).Bold.Size(16).Append($"SELECT A PUMP"$).PopAll
	MatDialogBuilder.Initialize("SelectedPump")
	MatDialogBuilder.Title(csTitle)
	MatDialogBuilder.Items(PumpList)
	MatDialogBuilder.PositiveText($"SELECT"$).PositiveColor(GlobalVar.PosColor)
	MatDialogBuilder.NegativeText($"CANCEL"$).NegativeColor(GlobalVar.NegColor)
	MatDialogBuilder.Cancelable(False)
	MatDialogBuilder.CanceledOnTouchOutside(False)
	MatDialogBuilder.itemsCallbackSingleChoice(0)
	MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIGHT)
	MatDialogBuilder.Show
End Sub

Private Sub SelectedPump_OnDismiss (Dialog As MaterialDialog)
	Log("Dialog dismissed")
End Sub

Private Sub SelectedPump_SingleChoiceItemSelected (Dialog As MaterialDialog, Position As Int, Text As String)
	snack.Initialize("", Activity, $"Pump Selected : "$ & Text,snack.DURATION_SHORT)
	MyFunctions.SetSnackBarBackground(snack, GlobalVar.PriColor)
	MyFunctions.SetSnackBarTextColor(snack, Colors.White)
	snack.Show
	GlobalVar.PumpHouseCode = Text
	GlobalVar.PumpHouseID = DBaseFunctions.GetPumpHouseID(GlobalVar.PumpHouseCode)
End Sub

Private Sub SelectedPump_ButtonPressed (mDialog As MaterialDialog, sAction As String)
	Select Case sAction
		Case mDialog.ACTION_POSITIVE
			LogColor(GlobalVar.PumpHouseID, Colors.Blue)
'			StartActivity(modProduction)
			
		Case mDialog.ACTION_NEGATIVE
			Return
'			mDialog.ClearSelectedIndices
	End Select
End Sub
#End Region

Sub pnlNonOp_Click
	
End Sub

Sub pnlJO_Click
	
End Sub

#Region Water Balance
Sub pnlWB_Click
	ShowWaterBalance
End Sub

Private Sub ShowWaterBalance
	Dim csTitle As CSBuilder

	csTitle.Initialize.Color(GlobalVar.PriColor).Bold.Size(16).Append($"SELECT WATER BALANCE ENTRY"$).PopAll
	MatDialogBuilder.Initialize("WaterBalance")
	MatDialogBuilder.Title(csTitle)
	MatDialogBuilder.Items(Array As String($"I. Supply"$,$"II. Authorized Consumption"$, $"III. Water Losses"$))
	MatDialogBuilder.PositiveText($"SELECT"$).PositiveColor(GlobalVar.PosColor)
	MatDialogBuilder.NegativeText($"CANCEL"$).NegativeColor(GlobalVar.NegColor)
	MatDialogBuilder.Cancelable(False)
	MatDialogBuilder.CanceledOnTouchOutside(False)
	MatDialogBuilder.itemsCallbackSingleChoice(0)
	MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIGHT)
	MatDialogBuilder.Show
End Sub

Private Sub WaterBalance_OnDismiss (Dialog As MaterialDialog)
	Log("Dialog dismissed")
End Sub

Private Sub WaterBalance_SingleChoiceItemSelected (Dialog As MaterialDialog, Position As Int, Text As String)
	SelectedWBEntry = Position
End Sub
	
Private Sub WaterBalance_ButtonPressed (mDialog As MaterialDialog, sAction As String)
	Select Case sAction
		Case mDialog.ACTION_POSITIVE
			LogColor(SelectedWBEntry, Colors.Red)
			Select Case SelectedWBEntry
				Case 0'Supply
				Case 1'Authorized Consumption
				Case 2'Water Losses
			End Select
		Case mDialog.ACTION_NEGATIVE
	End Select
End Sub
#End Region

Sub pnlReports_Click
	
End Sub

Private Sub GetPumpInfo (iPumpStationID As Int)
	Dim rsPumpInfo As Cursor
	Try
		Starter.strCriteria = "SELECT Station.PumpHouseCode, Station.PumpLocation, " & _
							  "Station.PumpHouseID, Station.FMID, Station.FMNo, Station.LastRdg " & _
							  "FROM tblPumpStation AS Station " & _
							  "WHERE Station.StationID = " & iPumpStationID
		rsPumpInfo = Starter.DBCon.ExecQuery(Starter.strCriteria)
		
		If rsPumpInfo.RowCount > 0 Then
			rsPumpInfo.Position = 0
			
		End If
	Catch
		Log(LastException)
	End Try
End Sub