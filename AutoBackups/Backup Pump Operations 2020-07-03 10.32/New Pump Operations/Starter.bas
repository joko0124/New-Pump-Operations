B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=9.9
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	#ExcludeFromLibrary: True
#End Region

Sub Process_Globals
	'Database
	Public DBCon As SQL
	Public strCriteria As String
	Public DBPath As String
	Public RTP As RuntimePermissions

	Public SafeDirectory As String
	Public DBName As String = "MasterDB.db"

End Sub

Sub Service_Create
'	rp.CheckAndRequest(rp.PERMISSION_WRITE_EXTERNAL_STORAGE)
'	rp.CheckAndRequest(rp.PERMISSION_READ_EXTERNAL_STORAGE)
	Log(RTP.GetSafeDirDefaultExternal(""))
'	DBPath = DBUtils.CopyDBFromAssets("MasterDB.db")

	Dim jo As JavaObject
	jo.InitializeStatic("java.util.Locale").RunMethod("setDefault", Array(jo.GetField("US")))
	
	SafeDirectory = RTP.GetSafeDirDefaultExternal("")
	
	If File.Exists(SafeDirectory, DBName) = False Then
		Wait For (File.CopyAsync(File.DirAssets, DBName, SafeDirectory, DBName)) Complete (Success As Boolean) 'File.DirInternal
		ToastMessageShow($"DB Copied = ${Success}"$, False)
'		Log("Success: " & Success)
	End If
	
	ConnectToDatabase
	Wait For SQLite_Ready (Success As Boolean)

End Sub
'SetManifestAttribute(android:installLocation, "auto")
Sub Service_Start (StartingIntent As Intent)

End Sub

Sub Service_TaskRemoved
	'This event will be raised when the user removes the app from the recent apps list.
End Sub

'Return true to allow the OS default exceptions handler to handle the uncaught exception.
Sub Application_Error (Error As Exception, StackTrace As String) As Boolean
	Return True
End Sub

Sub Service_Destroy

End Sub
'CONNECT TO DATABASE A LOAD SETTINGS
Public Sub ConnectToDatabase
'	DBCon.Initialize(DBPath, "MasterDB.db",False)
	DBCon.Initialize(SafeDirectory, DBName, False)
End Sub