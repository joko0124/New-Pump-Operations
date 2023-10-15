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
	Private CD, CDtxtBox As ColorDrawable
	Private vibration As B4Avibrate
	Private vibratePattern() As Long
	
	Private snack As DSSnackbar
	Private csAns As CSBuilder

	Private TabMenu As WobbleMenu
	
	Private btnSave As ACButton
	Private chkDrain As B4XView
	Private mskTimeOff As MaskedEditText
	Private mskTimeOn As MaskedEditText
	Private optElectricity As B4XView
	Private optGenerator As B4XView
	Private txtDrain As EditText
	Private txtDuration As EditText
	Private txtOnOffRemarks As EditText
End Sub
#End Region

#Region Activity Events
Sub Activity_Create(FirstTime As Boolean)
	MyScale.SetRate(0.5)
	Activity.LoadLayout("PumpTimeRecords")
	CD.Initialize2(GlobalVar.GreenColor, 30, 0, Colors.Transparent)
	btnSave.Background = CD

	CDtxtBox.Initialize(Colors.Transparent,0)	
	mskTimeOn.Background = CDtxtBox
	mskTimeOff.Background = CDtxtBox
	txtDuration.Background = CDtxtBox
	txtDrain.Background = CDtxtBox
	txtOnOffRemarks.Background = CDtxtBox
	
	If GlobalVar.blnNewTime = True Then
		GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"ADD NEW PUMP TIME RECORD"$).PopAll
		GlobalVar.CSSubTitle.Initialize.Size(12).Append($"Transaction Date: "$ & GlobalVar.TranDate).PopAll
		btnSave.Text = Chr(0xE161) & $" SAVE"$
	Else
		GlobalVar.CSTitle.Initialize.Size(15).Bold.Append($"EDIT PUMP TIME RECORD"$).PopAll
		GlobalVar.CSSubTitle.Initialize.Size(12).Append($"Transaction Date: "$ & GlobalVar.TranDate).PopAll
		btnSave.Text = Chr(0xE161) & $" UPDATE"$
		GetTimeRecord(GlobalVar.TimeDetailID)
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
	Dim Item As ACMenuItem
	Menu.Clear
End Sub

Sub ToolBar_NavigationItemClick 'Toolbar Arrow
	Activity.Finish
End Sub

Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Icon Menus
End Sub

#End Region

Sub chkDrain_CheckedChange(Checked As Boolean)
	If Checked Then
		txtDuration.Enabled = True
		txtDrain.Enabled = True
	Else
		txtDuration.Enabled = False
		txtDrain.Enabled = False
		txtDuration.Text = ""
		txtDrain.Text = ""
	End If
End Sub

Sub btnSave_Click
	Select GlobalVar.blnNewTime
		Case True
			LogColor($"New Time Record"$, Colors.Blue)
		Case False
			LogColor($"Edit Time Record"$, Colors.Red)
	End Select
End Sub

Private Sub GetTimeRecord(iDetailedID As Int)
	Dim SenderFilter As Object
	Try
	
		Starter.strCriteria = "SELECT Header.TranDate, " & _
						      "Pump.PumpCode, Details.PumpOnTime, Details.PumpOffTime, Details.TotOpHrs, " & _
							  "Details.PowerSourceID, Details.DrainTime, Details.DrainCum, Details.Remarks " & _
							  "FROM OnOffDetails AS Details " & _
							  "INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID " & _
							  "INNER JOIN tblPumpStation AS Pump ON Pump.PumpID = Header.PumpID " & _
							  "WHERE Details.DetailID = " & iDetailedID
							  
		SenderFilter = Starter.DBCon.ExecQueryAsync("SQL", Starter.strCriteria, Null)
		Wait For (SenderFilter) SQL_QueryComplete (Success As Boolean, RS As ResultSet)
		
		If Success Then
			RS.Position = 0
			mskTimeOn.Text = RS.GetString("PumpOnTime")
			mskTimeOff.Text = RS.GetString("PumpOffTime")
			iPowerSource = RS.GetInt("PowerSourceID")
			If iPowerSource = 1 Then
				optElectricity.Checked = True
				optGenerator.Checked = False
			Else
				optElectricity.Checked = False
				optGenerator.Checked = True
			End If
			txtDuration.Text = RS.GetInt("DrainTime")
			txtDrain.Text = RS.GetInt("DrainCum")
			If RS.GetInt("DrainTime") <> Null Or RS.GetInt("DrainCum") <> Null Then
				chkDrain.Checked = True
			Else
				chkDrain.Checked = False
				txtDuration.Text = ""
				txtDrain.Text = ""
			End If
			txtOnOffRemarks.Text = RS.GetString("Remarks")
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