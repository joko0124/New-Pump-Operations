B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=9.9
@EndOfDesignText@
Sub Process_Globals
	Public CSTitle As CSBuilder
	Public CSSubtitle As CSBuilder
	
	Public PriColor = 0xFF007BFF As Double 'primary
	Public SecColor = 0xFF7FBDFF As Double
	
	Public PosColor = 0xFF007BFF As Double
	Public NegColor = 0xFFDC3545 As Double
	Public NuetColor = 0xFF7FBDFF As Double
	
	Public BlueColor = 0xFF17A2B7 As Double 'info color
	Public GreenColor = 0xFF28A745 As Double 'success color
	Public RedColor = 0xFFDC3545 As Double 'danger color
	Public YellowColor = 0xFFFFC107 As Double 'warning color
	
	Public SF As StringFunctions
	
	Public UserID As Int
	Public UserName As String
	Public UserPW As String
	Public EmpName As String
	
	Public BranchID As Int
	Public BranchCode As String
	Public BranchName As String
	
	Public RdgFrm, RdgTo As String
	Public TranDate As String
	Public PumpHouseID As Int
	Public PumpHouseCode As String
	
	'Pump Time On Off
	Public blnNewTime As Boolean
	Public TimeDetailID As Int

	'FM Reading
	Public blnNewFMRdg As Boolean
	Public FMRdgDetailID As Int

	'PSI Reading
	Public blnNewPSIRdg As Boolean
	Public PSIRdgDetailID As Int

	'Chlorinator
	Public blnNewChlorine As Boolean
	Public ChlorineDetailID As Int

	'PSI Dist
	Public blnNewPSIDist As Boolean
	Public PSIDistDetailID As Int
End Sub