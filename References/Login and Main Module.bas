#Region  Project Attributes 
	#ApplicationLabel: BWSI Operations
	#VersionCode: 1
	#VersionName: Version 1.0
	'SupportedOrientations possible values: unspecified, landscape or portrait.
	#SupportedOrientations: unspecified
	#CanInstallToExternalStorage: False
	#AdditionalRes: C:\New-Pump-Operations\resource
	#AdditionalRes: C:\New-Pump-Operations\md-app\res
	#AdditionalRes: C:\New-Pump-Operations\md-lib\res
	#AdditionalRes: ..\resAX_CustomAlertViewDialog
	
#End Region
	#Extends: android.support.v4.widget.DrawerLayout
	#Extends: android.support.v7.app.AppCompatActivity
	#AdditionalJar: com.android.support:support-compat
	#AdditionalJar: com.google.android.gms:play-services-location
	#MultiDex: True
	#BridgeLogger: True
#Region  Activity Attributes
	#FullScreen: True
	#IncludeTitle: True
#End Region

Sub Process_Globals
	Public Permissions As RuntimePermissions
	Dim InpTyp As SLInpTypeConst
	Dim tmrSplash As Timer
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Dim mySnack As DSSnackbar
	Dim imeKeyboard As IME

	Private btnLogin As B4XView
	Private chkShowPass As B4XView
	Private txtPassword As EditText
	Private txtUserName As EditText
	
	Dim CDtxtBox As ColorDrawable
	Private lblCheck As B4XView
	
	Dim isChecked As Boolean
	
	Private csAns As CSBuilder	
	Dim snack As DSSnackbar
	Private vibration As B4Avibrate
	Private vibratePattern() As Long
	Private TheToast As BCToast
	
	Private btnBranchSettings As Panel
	Private btnIPSettings As Panel

	Private pnlSearch As Panel

	'Permissions
	Private ReadStoragePermission As Boolean
	Private WriteStoragePermission As Boolean
	Private CoarseLocPermission As Boolean
	Private FineLocPermission As Boolean
	
	Private KVS As KeyValueStore
	Private pnlSplash As Panel

End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("LoginNew")
	DBaseFunctions.GetParameters
	
	InpTyp.Initialize
	InpTyp.SetInputType(txtPassword,Array As Int(InpTyp.TYPE_CLASS_TEXT,InpTyp.TYPE_TEXT_VARIATION_PASSWORD))

	CDtxtBox.Initialize(Colors.Transparent,0)
	
	txtUserName.Background = CDtxtBox
	txtPassword.Background = CDtxtBox
	chkShowPass.Checked = False
	isChecked = False
	lblCheck.Text = ""
	txtPassword.PasswordMode = True
	txtUserName.Text = "joko"
	txtPassword.Text = "0124"
	
	Log(FirstTime)
	If FirstTime = True Then
		ShowSplash
		tmrSplash.Initialize("DoSomething",300)
		tmrSplash.Enabled = True
		GlobalVar.DBVersion = DBaseFunctions.GetDBVersionNo
		LogColor ($"App Version: "$ & Application.VersionCode, Colors.Yellow)
		LogColor ($"DBase Version: "$ & GlobalVar.DBVersion, Colors.Blue)
		If GlobalVar.DBVersion = 0 Or GlobalVar.DBVersion <> Application.VersionCode Then
			TheToast.Initialize(Activity)
			TheToast.DefaultTextColor = Colors.White
			TheToast.pnl.Color = GlobalVar.RedColor
			TheToast.DurationMs = 1600
			TheToast.Show($"Database Version not match!"$ & CRLF & $"Please contact Central Billing & IT Department."$)
			ExitApplication
		End If
	End If
	imeKeyboard.Initialize("ime")
	CheckPermissions
	csAns.Initialize.Bold.Size(16).Color(Colors.White).Append($"YES"$).PopAll
	
	KVS.Initialize(File.DirInternal, "operations.dat")
End Sub

Sub Activity_Resume
'	pnlSplash.Visible = False
'	tmrSplash.Enabled = False
'	If Starter.GPS1.GPSEnabled = False Then
'		ShowGPSWarning
'		Return
'	Else
'		Starter.rtp.CheckAndRequest(Starter.rtp.PERMISSION_ACCESS_FINE_LOCATION)
'		Wait For Activity_PermissionResult (Permission As String, Result As Boolean)
'		If Result Then CallSubDelayed(Starter, "StartGPS")
'	End If
'	
'	If KVS.IsInitialized = False Then
'		KVS.Initialize(File.DirInternal,"operations.dat")
'	Else
'		If IsThereUserData = True Then
'			Wait For(RetrieveUserData) Complete(bRetVal As Boolean)
'			If bRetVal = True Then
'				StartActivity(MainScreen)
'			End If
'		End If
'	End If
'	CheckPermissions
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	CallSubDelayed(Starter, "StopGPS")
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = 4 Then
		If pnlSplash.Visible = True Then Return False
		vibration.vibrateOnce(1000)
		snack.Initialize("btnClose", Activity, $"Close Pump Operations?"$, snack.DURATION_SHORT)
		MyFunctions.SetSnackBarBackground(snack, GlobalVar.PriColor)
		MyFunctions.SetSnackBarTextColor(snack, Colors.White)
		snack.SetAction(csAns)
		snack.Show
		Return True
	Else
		Return False
	End If
End Sub

Private Sub CheckPermissions
	Log("Checking Permissions")
	
	Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSION_READ_EXTERNAL_STORAGE)
	Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSION_WRITE_EXTERNAL_STORAGE)
	Starter.RTP.GetAllSafeDirsExternal("")

	Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSION_ACCESS_COARSE_LOCATION)
	Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSION_ACCESS_FINE_LOCATION)

	Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSION_CAMERA)
	Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSION_BODY_SENSORS)
	Return
End Sub

Sub Activity_PermissionResult (Permission As String, Result As Boolean)
	If Result Then
		If Permission = Starter.RTP.PERMISSION_READ_EXTERNAL_STORAGE Then
			LogColor($"Permission to Read External Storage GRANTED"$, Colors.Yellow)
			ReadStoragePermission = True
		Else If Permission = Starter.RTP.PERMISSION_WRITE_EXTERNAL_STORAGE Then
			LogColor($"Permission to Write External Storage GRANTED"$, Colors.White)
			WriteStoragePermission = True
		Else If Permission = Starter.RTP.PERMISSION_ACCESS_COARSE_LOCATION Then
			LogColor($"Permission to Access Coarse Location GRANTED"$, Colors.Magenta)
			CoarseLocPermission = True
		Else If Permission = Starter.RTP.PERMISSION_ACCESS_FINE_LOCATION Then
			LogColor($"Permission to Access Fine Location GRANTED"$, Colors.Cyan)
			FineLocPermission = True
		End If
		Starter.StartFLP
	Else
		ReadStoragePermission = False
		WriteStoragePermission = False
		CoarseLocPermission = False
		FineLocPermission = False
		Result = False
	End If
	Log (Permission)
End Sub

Private Sub btnClose_Click()
	ExitApplication
End Sub

Sub chkShowPass_CheckedChange(Checked As Boolean)
	txtPassword.PasswordMode = Checked
	If Checked = True Then
		lblCheck.Text = ""
	Else
		lblCheck.Text = Chr(0xE5CA)
	End If
	isChecked = Checked
End Sub

Sub btnLogin_Click
	Dim rsUser As Cursor
	
	If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtUserName.Text)) = 0 Then
		Return
	End If
	
	If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtPassword.Text)) = 0 Then
		Return
	End If
	
	
	Starter.strCriteria = "SELECT * FROM tblUsers " & _
					  "WHERE UserName = '" & txtUserName.Text & "'"
	rsUser = Starter.DBCon.ExecQuery(Starter.strCriteria)
	If rsUser.RowCount > 0 Then
		rsUser.Position = 0
		If rsUser.GetString("UserPassword") <> txtPassword.Text Then
			
			mySnack.Initialize("", Activity, "Password is Incorrect", mySnack.DURATION_SHORT)
			MyFunctions.SetSnackBarTextColor(mySnack, Colors.White)
			MyFunctions.SetSnackBarBackground(mySnack, GlobalVar.RedColor)
			mySnack.Show

			txtPassword.Text = ""
			txtPassword.RequestFocus
			imeKeyboard.ShowKeyboard(txtPassword)
			Return
		Else
			GlobalVar.UserID = DBaseFunctions.GetUserID(txtUserName.Text, txtPassword.Text)
			If DBaseFunctions.isGetUserInfo(GlobalVar.UserID) = False Then Return
			If DBaseFunctions.isGetBranchInfo(GlobalVar.BranchID)  = False Then Return
			GlobalVar.SysMode = DBaseFunctions.GetSystemMode(GlobalVar.BranchID)
			If DBaseFunctions.IsMultiPos(GlobalVar.UserID) = True Then
				ShowPositionList(GlobalVar.UserID)
			End If
			tmrSplash.Enabled = False
		End If
	Else 'User already in Local Database
		mySnack.Initialize("TryOtherUser", Activity, "User Name not Found on Local Database", mySnack.DURATION_SHORT)
		MyFunctions.SetSnackBarTextColor(mySnack, Colors.White)
		MyFunctions.SetSnackBarBackground(mySnack, GlobalVar.RedColor)
		mySnack.SetAction($"Retry"$)
		mySnack.Show
		Return
	End If
End Sub

Private Sub TryOtherUser_Click
	txtUserName.Text = ""
	txtPassword.Text = ""
	txtUserName.RequestFocus
	imeKeyboard.ShowKeyboard(txtUserName)
End Sub

Sub lblCheck_Click
	If isChecked = False Then
		isChecked = True
		lblCheck.Text = ""
	Else
		isChecked = False
		lblCheck.Text = Chr(0xE5CA)
	End If
	chkShowPass_CheckedChange(isChecked)
End Sub

Sub txtPassword_EnterPressed
	btnLogin_Click
End Sub

Sub RoundBD(BD As BigDecimal, DP As Int) As BigDecimal
	BD.Round(BD.Precision - BD.Scale + DP, BD.ROUND_HALF_UP )
	Return BD
End Sub

Sub btnIPSettings_Click
	MsgboxAsync($"IP Settings button clicked..."$,$"IP"$)
End Sub

Sub btnBranchSettings_Click
	MsgboxAsync($"Branch Settings button clicked..."$,$"BRANCH"$)
End Sub

Sub pnlSearch_Touch (Action As Int, X As Float, Y As Float)
	
End Sub

Private Sub ShowPositionList (iUserID As Int)
	Dim Alert As AX_CustomAlertDialog
	Private rsPos As Cursor
	Dim ListDestructive,ListItems As List
	ListDestructive.Initialize : ListItems.Initialize

	Try
		Starter.strCriteria = "SELECT UserPos.UserID, Positions.PosCode, Positions.PosDesc " & _
						  "FROM tblUserPosition AS UserPos " & _
						  "INNER JOIN tblPositions AS Positions ON UserPos.PosID = Positions.PosID " & _
						  "WHERE UserPos.UserID = "  & iUserID 
		LogColor(Starter.strCriteria, Colors.White)
		
		rsPos = Starter.DBCon.ExecQuery(Starter.strCriteria)
		
		If rsPos.RowCount > 0 Then
'			rsPos.Position = 0
			For i = 0 To rsPos.RowCount - 1
				rsPos.Position = i
				ListItems.Add(rsPos.GetString("PosDesc"))
			Next
		Else
		End If
	Catch
		Log(LastException)
	End Try
	rsPos.Close
	
	Alert.Initialize.Create _
			.SetDialogStyleName("MyDialog") _	'Manifest style name
			.SetTitle("Select your Position") _
			.SetStyle(Alert.STYLE_SELECTOR) _
			.SetOthers(ListItems) _
			.SetActionsheetTypeface(Font) _
			.SetOnItemClickListener("PositionSelect") _
			.SetOnViewBinder("PosFontSizeBinder") 'listeners
		
	Alert.SetDialogBackground(CD)
	Alert.Build.Show
End Sub

Private Sub CD As ColorDrawable
	Private mCD As ColorDrawable
	mCD.Initialize(Colors.RGB(240,240,240),0)
	Return mCD
End Sub

'Listeners
Private Sub PositionSelect_OnItemClick (View As View, Selection As String, Position As Int, Id As Long)
'	ToastMessageShow(Selection&" Selected! (Position : "& Position &")",False)
	Dim Alert As AX_CustomAlertDialog
	Alert.Initialize.Dismiss2
	GlobalVar.UserPosID = DBaseFunctions.GetIDByCode("PosID","tblPositions","PosDesc",Selection)
	GlobalVar.UserPos = Selection
	LogColor(GlobalVar.UserPosID & " - " & GlobalVar.UserPos , Colors.Yellow)
	TheToast.Initialize(Activity)
	TheToast.DefaultTextColor = Colors.White
	TheToast.pnl.Color = GlobalVar.BlueColor
	TheToast.Show($"Login as: "$ & Selection)

	Sleep(1000)
	StartActivity(MainScreen)
End Sub

Public Sub GPSStatus (Satellites As List)
	Dim sb As StringBuilder
	sb.Initialize
	sb.Append("Satellites:").Append(CRLF)
	For i = 0 To Satellites.Size - 1
		Dim Satellite As GPSSatellite = Satellites.Get(i)
		sb.Append(CRLF).Append(Satellite.Prn).Append($" $1.2{Satellite.Snr}"$).Append(" ").Append(Satellite.UsedInFix)
		sb.Append(" ").Append($" $1.2{Satellite.Azimuth}"$).Append($" $1.2{Satellite.Elevation}"$)
	Next
	Log(sb.ToString)
'	lblSatellites.Text = sb.ToString
End Sub

Public Sub LocationChanged(Location1 As Location)
	Log($"Latitude = "$ & Location1.ConvertToMinutes(Location1.Latitude) & " - Longitude = " & Location1.ConvertToMinutes(Location1.Longitude) & $"Speed = $1.2{Location1.Speed} m/s "$)
'	lblLat.Text = "Lat = " & Location1.ConvertToMinutes(Location1.Latitude)
'	lblLon.Text = "Lon = " & Location1.ConvertToMinutes(Location1.Longitude)
'	lblSpeed.Text = $"Speed = $1.2{Location1.Speed} m/s "$
End Sub

Private Sub ShowGPSWarning
	Dim Alert As AX_CustomAlertDialog

	Alert.Initialize.Create _
			.SetDialogStyleName("MyDialogDisableStatus") _	'Manifest style name
			.SetStyle(Alert.STYLE_DIALOGUE) _
			.SetCancelable(False) _
			.SetTitle($"Enable GPS"$) _
			.SetMessage($"Please Turn On Location Services to,"$ & CRLF & $"Allow "BWSI APP" to"$ & CRLF &  $"Determine Your Location."$) _
			.SetPositiveText("Cancel") _
			.SetPositiveColor(GlobalVar.NegColor) _
			.SetPositiveTypeface(GlobalVar.FontBold) _
			.SetNegativeText("Settings") _
			.SetNegativeColor(GlobalVar.PosColor) _
			.SetNegativeTypeface(GlobalVar.Font) _
			.SetTitleTypeface(GlobalVar.Font) _
			.SetMessageTypeface(GlobalVar.Font) _
			.SetOnPositiveClicked("AllowGPS") _	'listeners
			.SetOnNegativeClicked("AllowGPS") _
			.SetOnViewBinder("GPSFontSizeBinder") 'listeners
			
	Alert.SetDialogBackground(MyFunctions.myCD)
	Alert.Build.Show

End Sub
'Listeners
Private Sub AllowGPS_OnNegativeClicked (View As View, Dialog As Object)
	Dim Alert As AX_CustomAlertDialog
	Alert.Initialize.Dismiss(Dialog)
	
	StartActivity(Starter.GPS1.LocationSettingsIntent) 'Will open the relevant settings screen.
End Sub

Private Sub AllowGPS_OnPositiveClicked (View As View, Dialog As Object)
	Dim Alert As AX_CustomAlertDialog
	Alert.Initialize.Dismiss(Dialog)
	ExitApplication
End Sub

Private Sub GPSFontSizeBinder_OnBindView (View As View, ViewType As Int)
	Dim alert As AX_CustomAlertDialog
	alert.Initialize
	If ViewType = alert.VIEW_TITLE Then ' Title
		Dim lbl As Label = View
'		lbl.TextSize = 30
'		lbl.SetTextColorAnimated(2000,Colors.Magenta)
		
		
		Dim CS As CSBuilder
'		CS.Initialize.Typeface(Font).Append(lbl.Text & " ").Pop
'		CS.Typeface(Typeface.MATERIALICONS).Size(36).Color(Colors.Red).Append(Chr(0xE190))

		CS.Initialize.Typeface(Typeface.DEFAULT_BOLD).Typeface(Typeface.MATERIALICONS).Size(26).Color(Colors.Red).Append(Chr(0xE1B3) & "  ")
		CS.Typeface(Font).Size(22).Append(lbl.Text).Pop

		lbl.Text = CS.PopAll
	End If
	If ViewType = alert.VIEW_MESSAGE Then
		Dim lbl As Label = View
		lbl.TextSize = 16
		lbl.TextColor = Colors.Gray
	End If
End Sub

Private Sub PosFontSizeBinder_OnBindView (View As View, ViewType As Int)
	Dim alert As AX_CustomAlertDialog
	alert.Initialize
	If ViewType = alert.VIEW_TITLE Then ' Title
		Dim lbl As Label = View
'		lbl.TextSize = 30
'		lbl.SetTextColorAnimated(2000,Colors.Magenta)
		
		
		Dim CS As CSBuilder
'		CS.Initialize.Typeface(Font).Append(lbl.Text & " ").Pop
'		CS.Typeface(Typeface.MATERIALICONS).Size(36).Color(Colors.Red).Append(Chr(0xE190))

		CS.Initialize.Typeface(Typeface.DEFAULT_BOLD).Typeface(Typeface.MATERIALICONS).Size(24).Color(Colors.Black).Append(Chr(0xE7FD) & " ")
		CS.Typeface(Font).Size(20).Append(lbl.Text).Pop

		lbl.Text = CS.PopAll
	End If
End Sub

Private Sub PosFontSizeBinder_OnBindActionsheetView (View As View, Position As Int,IsNewItem As Boolean)
	If IsNewItem Then Return
'	Dim Color As Int = Colors.RGB(Rnd(0,255),Rnd(0,255),Rnd(0,255))
	
	Dim Lbl As Label = View
'	Lbl.TextColor = Colors.Gray
	Lbl.Typeface = Font
	Lbl.TextSize = 20
	
'	Dim Parent As Panel = View.Parent
'	Dim Divider As View = Parent.GetView(0)
'	Divider.Color = Colors.DarkGray
End Sub

'Key Value Store
#Region KeyValueStore

Private Sub RetrieveUserData As ResumableSub
	' Retrieve user data from KVS
	Dim blnRetVal As Boolean
	Dim UserData, TransDate As Map
	
	blnRetVal = False
	UserData.Initialize
	UserData = KVS.Get("user_data")

	' Display user data in a toast message
	GlobalVar.UserID = UserData.Get("UserID")
	GlobalVar.UserName = UserData.Get("UserName")

	GlobalVar.UserPW = UserData.Get("UserPassword")
	GlobalVar.EmpName = UserData.Get("EmpName")
	GlobalVar.UserAvatar = UserData.Get("UserAvatar")

	GlobalVar.BranchID = UserData.Get("BranchID")
	GlobalVar.BranchName = UserData.Get("BranchName")
	GlobalVar.UserPosID = UserData.Get("UserPosID")
	GlobalVar.UserPos = UserData.Get("UserPosition")
	GlobalVar.SysMode = UserData.Get("SysMode")
	
	blnRetVal = True
	Return blnRetVal
'	TransDate.Initialize
'	TransDate = KVS.Get("trans_date")
'	GlobalVar.TranDate = TransDate.Get("TransDate")

End Sub

Private Sub IsThereUserData As Boolean
	Dim bRetVal As Boolean
	bRetVal = False
	Try
		If KVS.ContainsKey("user_data") Then
			bRetVal = True
		Else
			bRetVal = False
		End If
	Catch
		Log(LastException)
	End Try
	Log($"User Data "$ & bRetVal)
	Return bRetVal
End Sub

#End Region
#Region Splash
Private Sub DoSomething_Tick
	Log("WORKING...")
End Sub

Private Sub ShowSplash
	pnlSplash.Visible = True
	Sleep(3000)
	pnlSplash.Visible = False
	tmrSplash.Enabled = False
End Sub
Private Sub pnlSplash_Touch (Action As Int, X As Float, Y As Float)
	
End Sub
#End Region

#Region User Message Box
Private Sub UserWarning(sTitle As String, sMsg As String)
	Dim Alert As AX_CustomAlertDialog

	Alert.Initialize.Create _
			.SetDialogStyleName("MyDialogDisableStatus") _	'Manifest style name
			.SetStyle(Alert.STYLE_DIALOGUE) _
			.SetCancelable(False) _
			.SetTitle(sTitle) _
			.SetMessage(sMsg) _
			.SetPositiveText("Turn On WiFi") _
			.SetPositiveColor(Colors.Blue) _
			.SetPositiveTypeface(GlobalVar.FontBold) _
			.SetNegativeText("Cancel") _
			.SetNegativeColor(Colors.Red) _
			.SetNegativeTypeface(GlobalVar.Font) _
			.SetTitleTypeface(GlobalVar.Font) _
			.SetMessageTypeface(GlobalVar.Font) _
			.SetOnPositiveClicked("WarningUser") _	'listeners
			.SetOnNegativeClicked("WarningUser") _
			.SetOnViewBinder("WarningUser") 'listeners
			
	Alert.SetDialogBackground(myCD)
	Alert.Build.Show

End Sub
'Listeners
Private Sub WarningUser_OnNegativeClicked (View As View, Dialog As Object)
	Dim Alert As AX_CustomAlertDialog
	Alert.Initialize.Dismiss(Dialog)
	blnWifiOn = False

	vibration.vibrateOnce(2000)
	snack.Initialize("", Activity,$"Unable to log due to NO INTERNET CONNECTION."$,snack.DURATION_LONG)
	SetSnackBarBackground(snack,Colors.Red)
	SetSnackBarTextColor(snack,Colors.White)
	snack.Show

	mySnack.Initialize("TryOtherUser", Activity, "User Name not Found on Local Database", mySnack.DURATION_SHORT)
	MyFunctions.SetSnackBarTextColor(mySnack, Colors.White)
	MyFunctions.SetSnackBarBackground(mySnack, GlobalVar.RedColor)
	mySnack.SetAction($"Retry"$)
	mySnack.Show
	
	ProgressDialogHide

End Sub

Private Sub WarningUser_OnPositiveClicked (View As View, Dialog As Object)
	Dim Alert As AX_CustomAlertDialog
	Alert.Initialize.Dismiss(Dialog)
	Sleep(1000)
	blnWifiOn = True
	If blnWifiOn = True Then
		tglWifi.toggleWIFI
		ProgressDialogShow2($"Checking Database Server Connection."$ & CRLF & $"Please Wait..."$, False)
		GetUserAccount(GlobalVar.BranchID, txtUserName.Text, txtPassword.Text)
	End If
End Sub

Private Sub WarningUser_OnBindView (View As View, ViewType As Int)
	Dim Alert As AX_CustomAlertDialog
	Alert.Initialize
	If ViewType = Alert.VIEW_TITLE Then ' Title
		Dim lbl As Label = View
'		lbl.TextSize = 30
'		lbl.SetTextColorAnimated(2000,Colors.Magenta)
		
		
		Dim CS As CSBuilder
'		CS.Initialize.Typeface(Font).Append(lbl.Text & " ").Pop
'		CS.Typeface(Typeface.MATERIALICONS).Size(36).Color(Colors.Red).Append(Chr(0xE190))

		CS.Initialize.Typeface(Typeface.DEFAULT_BOLD).Typeface(Typeface.MATERIALICONS).Size(26).Color(Colors.Red).Append(Chr(0xE1B3) & "  ")
		CS.Typeface(GlobalVar.Font).Size(22).Append(lbl.Text).Pop

		lbl.Text = CS.PopAll
	End If
	If ViewType = Alert.VIEW_MESSAGE Then
		Dim lbl As Label = View
		lbl.TextSize = 16
		lbl.TextColor = Colors.Gray
	End If
End Sub

Private Sub myCD As ColorDrawable
	Dim mCD As ColorDrawable
	mCD.Initialize(Colors.RGB(240,240,240),0)
	Return mCD
End Sub

#End Region