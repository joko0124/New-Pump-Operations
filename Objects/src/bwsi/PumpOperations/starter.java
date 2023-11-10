package bwsi.PumpOperations;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class starter extends  android.app.Service{
	public static class starter_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (starter) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, starter.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, true, BA.class);
		}

	}
    static starter mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return starter.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.starter");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.starter", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!true && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (starter) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (true) {
			ServiceHelper.StarterHelper.runWaitForLayouts();
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA, new Runnable() {
            public void run() {
                handleStart(intent);
            }}))
			;
		else {
			ServiceHelper.StarterHelper.addWaitForLayout (new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (starter) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
                    ServiceHelper.StarterHelper.removeWaitForLayout();
				}
			});
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (true)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (starter) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = ServiceHelper.StarterHelper.handleStartIntent(intent, _service, processBA);
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	
	@Override
	public void onDestroy() {
        super.onDestroy();
        if (true) {
            BA.LogInfo("** Service (starter) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (starter) Destroy **");
		    processBA.raiseEvent(null, "service_destroy");
            processBA.service = null;
		    mostCurrent = null;
		    processBA.setActivityPaused(true);
            processBA.runHook("ondestroy", this, null);
        }
	}

@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.sql.SQL _dbcon = null;
public static String _strcriteria = "";
public static String _dbpath = "";
public static anywheresoftware.b4a.objects.RuntimePermissions _rtp = null;
public static String _safedirectory = "";
public static String _dbname = "";
public static anywheresoftware.b4a.gps.GPS _gps1 = null;
public static boolean _gpsstarted = false;
public static uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper _flp = null;
public static boolean _flpstarted = false;
public b4a.example.dateutils _dateutils = null;
public bwsi.PumpOperations.main _main = null;
public bwsi.PumpOperations.globalvar _globalvar = null;
public bwsi.PumpOperations.myfunctions _myfunctions = null;
public bwsi.PumpOperations.dbasefunctions _dbasefunctions = null;
public bwsi.PumpOperations.actnewproduction _actnewproduction = null;
public bwsi.PumpOperations.mainscreen _mainscreen = null;
public bwsi.PumpOperations.actcmjofindings _actcmjofindings = null;
public bwsi.PumpOperations.actdccrjofindings _actdccrjofindings = null;
public bwsi.PumpOperations.actcriticalpoint _actcriticalpoint = null;
public bwsi.PumpOperations.actdcdajofindings _actdcdajofindings = null;
public bwsi.PumpOperations.actdebugkeyboard _actdebugkeyboard = null;
public bwsi.PumpOperations.actgpmcalc _actgpmcalc = null;
public bwsi.PumpOperations.actgpmhistory _actgpmhistory = null;
public bwsi.PumpOperations.actjo _actjo = null;
public bwsi.PumpOperations.actjoaccomplishedsas _actjoaccomplishedsas = null;
public bwsi.PumpOperations.actjodetails _actjodetails = null;
public bwsi.PumpOperations.actjonotification _actjonotification = null;
public bwsi.PumpOperations.actjoreasons _actjoreasons = null;
public bwsi.PumpOperations.actjosummary _actjosummary = null;
public bwsi.PumpOperations.actjowithreasons _actjowithreasons = null;
public bwsi.PumpOperations.actmcjofindings _actmcjofindings = null;
public bwsi.PumpOperations.actncjofindings _actncjofindings = null;
public bwsi.PumpOperations.actnonoperational _actnonoperational = null;
public bwsi.PumpOperations.actproduction _actproduction = null;
public bwsi.PumpOperations.actpumpoff _actpumpoff = null;
public bwsi.PumpOperations.actrcjofindings _actrcjofindings = null;
public bwsi.PumpOperations.actrepmain _actrepmain = null;
public bwsi.PumpOperations.actsasjofindings _actsasjofindings = null;
public bwsi.PumpOperations.actwaterbalance _actwaterbalance = null;
public bwsi.PumpOperations.addeditchlorinerecord _addeditchlorinerecord = null;
public bwsi.PumpOperations.addeditfmrdg _addeditfmrdg = null;
public bwsi.PumpOperations.addeditnonoperational _addeditnonoperational = null;
public bwsi.PumpOperations.addeditproblem _addeditproblem = null;
public bwsi.PumpOperations.addeditpsidistrecord _addeditpsidistrecord = null;
public bwsi.PumpOperations.addeditpsirdg _addeditpsirdg = null;
public bwsi.PumpOperations.addedittimerecord _addedittimerecord = null;
public bwsi.PumpOperations.dbutils _dbutils = null;
public bwsi.PumpOperations.edittimerecord _edittimerecord = null;
public bwsi.PumpOperations.firebasemessaging _firebasemessaging = null;
public bwsi.PumpOperations.miscfunctions _miscfunctions = null;
public bwsi.PumpOperations.myscale _myscale = null;
public bwsi.PumpOperations.validation _validation = null;
public bwsi.PumpOperations.httputils2service _httputils2service = null;
public bwsi.PumpOperations.b4xcollections _b4xcollections = null;
public static boolean  _application_error(anywheresoftware.b4a.objects.B4AException _error,String _stacktrace) throws Exception{
 //BA.debugLineNum = 63;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
 //BA.debugLineNum = 64;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return false;
}
public static String  _connecttodatabase() throws Exception{
 //BA.debugLineNum = 71;BA.debugLine="Public Sub ConnectToDatabase";
 //BA.debugLineNum = 73;BA.debugLine="If DBCon.IsInitialized = False Then";
if (_dbcon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 74;BA.debugLine="DBCon.Initialize(SafeDirectory, DBName, False)";
_dbcon.Initialize(_safedirectory,_dbname,anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 76;BA.debugLine="DBCon.Initialize(SafeDirectory, DBName, False)";
_dbcon.Initialize(_safedirectory,_dbname,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static uk.co.martinpearman.b4a.fusedlocationprovider.LocationRequest  _createlocationrequest() throws Exception{
uk.co.martinpearman.b4a.fusedlocationprovider.LocationRequest _lr = null;
 //BA.debugLineNum = 162;BA.debugLine="Private Sub CreateLocationRequest As LocationReque";
 //BA.debugLineNum = 163;BA.debugLine="Dim lr As LocationRequest";
_lr = new uk.co.martinpearman.b4a.fusedlocationprovider.LocationRequest();
 //BA.debugLineNum = 164;BA.debugLine="lr.Initialize";
_lr.Initialize();
 //BA.debugLineNum = 165;BA.debugLine="lr.SetSmallestDisplacement(100)   '<-------------";
_lr.SetSmallestDisplacement((float) (100));
 //BA.debugLineNum = 166;BA.debugLine="lr.SetInterval(1)";
_lr.SetInterval((long) (1));
 //BA.debugLineNum = 167;BA.debugLine="lr.SetFastestInterval(lr.GetInterval / 2)";
_lr.SetFastestInterval((long) (_lr.GetInterval()/(double)2));
 //BA.debugLineNum = 168;BA.debugLine="lr.SetPriority(lr.Priority.PRIORITY_HIGH_ACCURACY";
_lr.SetPriority(_lr.Priority.PRIORITY_HIGH_ACCURACY);
 //BA.debugLineNum = 169;BA.debugLine="Return lr";
if (true) return _lr;
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
return null;
}
public static String  _flp_connectionfailed(int _connectionresult1) throws Exception{
 //BA.debugLineNum = 122;BA.debugLine="Sub flp_ConnectionFailed(ConnectionResult1 As Int)";
 //BA.debugLineNum = 123;BA.debugLine="Log(\"Failed to connect to location provider\")";
anywheresoftware.b4a.keywords.Common.LogImpl("893913089","Failed to connect to location provider",0);
 //BA.debugLineNum = 124;BA.debugLine="Select ConnectionResult1";
switch (BA.switchObjectToInt(_connectionresult1,_flp.ConnectionResult.NETWORK_ERROR)) {
case 0: {
 //BA.debugLineNum = 128;BA.debugLine="FLP.Connect";
_flp.Connect();
 break; }
default: {
 break; }
}
;
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static String  _flp_connectionsuccess() throws Exception{
anywheresoftware.b4a.gps.LocationWrapper _location1 = null;
 //BA.debugLineNum = 107;BA.debugLine="Sub flp_ConnectionSuccess";
 //BA.debugLineNum = 108;BA.debugLine="Log(\"Connected to location provider\")";
anywheresoftware.b4a.keywords.Common.LogImpl("893847553","Connected to location provider",0);
 //BA.debugLineNum = 109;BA.debugLine="Dim location1 As Location";
_location1 = new anywheresoftware.b4a.gps.LocationWrapper();
 //BA.debugLineNum = 110;BA.debugLine="location1.Initialize";
_location1.Initialize();
 //BA.debugLineNum = 112;BA.debugLine="Log(\"Connected to location provider\")";
anywheresoftware.b4a.keywords.Common.LogImpl("893847557","Connected to location provider",0);
 //BA.debugLineNum = 113;BA.debugLine="LogColor(location1.Latitude,Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("893847558",BA.NumberToString(_location1.getLatitude()),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 114;BA.debugLine="LogColor(location1.Longitude,Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("893847559",BA.NumberToString(_location1.getLongitude()),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 116;BA.debugLine="GlobalVar.Lat = $\"$1.4{location1.Latitude}\"$";
mostCurrent._globalvar._lat /*String*/  = (""+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.4",(Object)(_location1.getLatitude()))+"");
 //BA.debugLineNum = 117;BA.debugLine="GlobalVar.Lon = $\"$1.4{location1.Longitude}\"$";
mostCurrent._globalvar._lon /*String*/  = (""+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.4",(Object)(_location1.getLongitude()))+"");
 //BA.debugLineNum = 118;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("893847563",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 119;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("893847564",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 120;BA.debugLine="End Sub";
return "";
}
public static String  _flp_connectionsuspended(int _suspendedcause1) throws Exception{
 //BA.debugLineNum = 134;BA.debugLine="Sub flp_ConnectionSuspended(SuspendedCause1 As Int";
 //BA.debugLineNum = 135;BA.debugLine="Log(\"FusedLocationProvider1_ConnectionSuspended\")";
anywheresoftware.b4a.keywords.Common.LogImpl("893978625","FusedLocationProvider1_ConnectionSuspended",0);
 //BA.debugLineNum = 139;BA.debugLine="Select SuspendedCause1";
switch (BA.switchObjectToInt(_suspendedcause1,_flp.SuspendedCause.CAUSE_NETWORK_LOST,_flp.SuspendedCause.CAUSE_SERVICE_DISCONNECTED)) {
case 0: {
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 145;BA.debugLine="End Sub";
return "";
}
public static String  _flp_locationchanged(anywheresoftware.b4a.gps.LocationWrapper _location1) throws Exception{
 //BA.debugLineNum = 157;BA.debugLine="Private Sub flp_LocationChanged (Location1 As Loca";
 //BA.debugLineNum = 158;BA.debugLine="GlobalVar.Lat = $\"$1.4{Location1.Latitude}\"$";
mostCurrent._globalvar._lat /*String*/  = (""+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.4",(Object)(_location1.getLatitude()))+"");
 //BA.debugLineNum = 159;BA.debugLine="GlobalVar.Lon = $\"$1.4{Location1.Longitude}\"$";
mostCurrent._globalvar._lon /*String*/  = (""+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.4",(Object)(_location1.getLongitude()))+"");
 //BA.debugLineNum = 160;BA.debugLine="End Sub";
return "";
}
public static String  _gps_gpsstatus(anywheresoftware.b4a.objects.collections.List _satellites) throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub GPS_GpsStatus (Satellites As List)";
 //BA.debugLineNum = 100;BA.debugLine="CallSub2(Main, \"GPSStatus\", Satellites)";
anywheresoftware.b4a.keywords.Common.CallSubNew2(processBA,(Object)(mostCurrent._main.getObject()),"GPSStatus",(Object)(_satellites));
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public static String  _gps_locationchanged(anywheresoftware.b4a.gps.LocationWrapper _location1) throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Sub GPS_LocationChanged (Location1 As Location)";
 //BA.debugLineNum = 96;BA.debugLine="CallSub2(Main, \"LocationChanged\", Location1)";
anywheresoftware.b4a.keywords.Common.CallSubNew2(processBA,(Object)(mostCurrent._main.getObject()),"LocationChanged",(Object)(_location1));
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Public DBCon As SQL";
_dbcon = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 9;BA.debugLine="Public strCriteria As String";
_strcriteria = "";
 //BA.debugLineNum = 10;BA.debugLine="Public DBPath As String";
_dbpath = "";
 //BA.debugLineNum = 11;BA.debugLine="Public RTP As RuntimePermissions";
_rtp = new anywheresoftware.b4a.objects.RuntimePermissions();
 //BA.debugLineNum = 13;BA.debugLine="Public SafeDirectory As String";
_safedirectory = "";
 //BA.debugLineNum = 14;BA.debugLine="Public DBName As String = \"MasterDB.db\"";
_dbname = "MasterDB.db";
 //BA.debugLineNum = 16;BA.debugLine="Public GPS1 As GPS";
_gps1 = new anywheresoftware.b4a.gps.GPS();
 //BA.debugLineNum = 17;BA.debugLine="Private GPSStarted As Boolean";
_gpsstarted = false;
 //BA.debugLineNum = 19;BA.debugLine="Public FLP As FusedLocationProvider";
_flp = new uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private flpStarted As Boolean";
_flpstarted = false;
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static void  _service_create() throws Exception{
ResumableSub_Service_Create rsub = new ResumableSub_Service_Create(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_Service_Create extends BA.ResumableSub {
public ResumableSub_Service_Create(bwsi.PumpOperations.starter parent) {
this.parent = parent;
}
bwsi.PumpOperations.starter parent;
anywheresoftware.b4j.object.JavaObject _jo = null;
boolean _success = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 27;BA.debugLine="Log(RTP.GetSafeDirDefaultExternal(\"\"))";
anywheresoftware.b4a.keywords.Common.LogImpl("893192196",parent._rtp.GetSafeDirDefaultExternal(""),0);
 //BA.debugLineNum = 28;BA.debugLine="DBPath = DBUtils.CopyDBFromAssets(\"MasterDB.db\")";
parent._dbpath = parent.mostCurrent._dbutils._copydbfromassets /*String*/ (processBA,"MasterDB.db");
 //BA.debugLineNum = 30;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 31;BA.debugLine="If jo.IsInitialized = False Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_jo.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 32;BA.debugLine="jo.InitializeStatic(\"java.util.Locale\").RunMetho";
_jo.InitializeStatic("java.util.Locale").RunMethod("setDefault",new Object[]{_jo.GetField("US")});
 if (true) break;

case 4:
//C
this.state = 5;
;
 //BA.debugLineNum = 35;BA.debugLine="SafeDirectory = RTP.GetSafeDirDefaultExternal(\"\")";
parent._safedirectory = parent._rtp.GetSafeDirDefaultExternal("");
 //BA.debugLineNum = 37;BA.debugLine="If File.Exists(SafeDirectory, DBName) = False The";
if (true) break;

case 5:
//if
this.state = 8;
if (anywheresoftware.b4a.keywords.Common.File.Exists(parent._safedirectory,parent._dbname)==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 7;
}if (true) break;

case 7:
//C
this.state = 8;
 //BA.debugLineNum = 38;BA.debugLine="Wait For (File.CopyAsync(File.DirAssets, DBName,";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, this, anywheresoftware.b4a.keywords.Common.File.CopyAsync(processBA,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),parent._dbname,parent._safedirectory,parent._dbname));
this.state = 9;
return;
case 9:
//C
this.state = 8;
_success = (Boolean) result[0];
;
 //BA.debugLineNum = 39;BA.debugLine="ToastMessageShow($\"DB Copied = ${Success}\"$, Fal";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("DB Copied = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_success))+"")),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 8:
//C
this.state = -1;
;
 //BA.debugLineNum = 42;BA.debugLine="CallSubDelayed(FirebaseMessaging, \"SubscribeToTop";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(parent.mostCurrent._firebasemessaging.getObject()),"SubscribeToTopics");
 //BA.debugLineNum = 44;BA.debugLine="GPS1.Initialize(\"GPS\")";
parent._gps1.Initialize("GPS");
 //BA.debugLineNum = 45;BA.debugLine="FLP.Initialize(\"flp\")";
parent._flp.Initialize(processBA,"flp");
 //BA.debugLineNum = 46;BA.debugLine="FLP.Connect";
parent._flp.Connect();
 //BA.debugLineNum = 48;BA.debugLine="ConnectToDatabase";
_connecttodatabase();
 //BA.debugLineNum = 49;BA.debugLine="Wait For SQLite_Ready (Success As Boolean)";
anywheresoftware.b4a.keywords.Common.WaitFor("sqlite_ready", processBA, this, null);
this.state = 10;
return;
case 10:
//C
this.state = -1;
_success = (Boolean) result[0];
;
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _complete(boolean _success) throws Exception{
}
public static void  _sqlite_ready(boolean _success) throws Exception{
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 67;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 68;BA.debugLine="StopGps";
_stopgps();
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 53;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 54;BA.debugLine="Service.StopAutomaticForeground";
mostCurrent._service.StopAutomaticForeground();
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return "";
}
public static String  _service_taskremoved() throws Exception{
 //BA.debugLineNum = 58;BA.debugLine="Sub Service_TaskRemoved";
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return "";
}
public static void  _startflp() throws Exception{
ResumableSub_StartFLP rsub = new ResumableSub_StartFLP(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_StartFLP extends BA.ResumableSub {
public ResumableSub_StartFLP(bwsi.PumpOperations.starter parent) {
this.parent = parent;
}
bwsi.PumpOperations.starter parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 148;BA.debugLine="Do While FLP.IsConnected = False";
if (true) break;

case 1:
//do while
this.state = 4;
while (parent._flp.IsConnected()==anywheresoftware.b4a.keywords.Common.False) {
this.state = 3;
if (true) break;
}
if (true) break;

case 3:
//C
this.state = 1;
 //BA.debugLineNum = 149;BA.debugLine="Sleep(1000)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (1000));
this.state = 8;
return;
case 8:
//C
this.state = 1;
;
 if (true) break;
;
 //BA.debugLineNum = 151;BA.debugLine="If flpStarted = False Then";

case 4:
//if
this.state = 7;
if (parent._flpstarted==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 152;BA.debugLine="FLP.RequestLocationUpdates(CreateLocationRequest";
parent._flp.RequestLocationUpdates((com.google.android.gms.location.LocationRequest)(_createlocationrequest().getObject()));
 //BA.debugLineNum = 153;BA.debugLine="flpStarted = True";
parent._flpstarted = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 7:
//C
this.state = -1;
;
 //BA.debugLineNum = 155;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _startgps() throws Exception{
 //BA.debugLineNum = 81;BA.debugLine="Public Sub StartGps";
 //BA.debugLineNum = 82;BA.debugLine="If GPSStarted = False Then";
if (_gpsstarted==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 83;BA.debugLine="GPS1.Start(0, 0)";
_gps1.Start(processBA,(long) (0),(float) (0));
 //BA.debugLineNum = 84;BA.debugLine="GPSStarted = True";
_gpsstarted = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
public static String  _startlocationupdates() throws Exception{
anywheresoftware.b4a.gps.LocationWrapper _location1 = null;
 //BA.debugLineNum = 179;BA.debugLine="Public Sub StartLocationUpdates ()";
 //BA.debugLineNum = 180;BA.debugLine="Dim Location1 As Location";
_location1 = new anywheresoftware.b4a.gps.LocationWrapper();
 //BA.debugLineNum = 181;BA.debugLine="Location1.Initialize";
_location1.Initialize();
 //BA.debugLineNum = 183;BA.debugLine="FLP.RequestLocationUpdates(CreateLocationRequest)";
_flp.RequestLocationUpdates((com.google.android.gms.location.LocationRequest)(_createlocationrequest().getObject()));
 //BA.debugLineNum = 184;BA.debugLine="GlobalVar.Lat = $\"$1.4{Location1.Latitude}\"$";
mostCurrent._globalvar._lat /*String*/  = (""+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.4",(Object)(_location1.getLatitude()))+"");
 //BA.debugLineNum = 185;BA.debugLine="GlobalVar.Lon = $\"$1.4{Location1.Longitude}\"$";
mostCurrent._globalvar._lon /*String*/  = (""+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.4",(Object)(_location1.getLongitude()))+"");
 //BA.debugLineNum = 186;BA.debugLine="End Sub";
return "";
}
public static String  _stopflp() throws Exception{
 //BA.debugLineNum = 172;BA.debugLine="Public Sub StopFLP";
 //BA.debugLineNum = 173;BA.debugLine="If flpStarted Then";
if (_flpstarted) { 
 //BA.debugLineNum = 174;BA.debugLine="FLP.RemoveLocationUpdates";
_flp.RemoveLocationUpdates();
 //BA.debugLineNum = 175;BA.debugLine="flpStarted = False";
_flpstarted = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 177;BA.debugLine="End Sub";
return "";
}
public static String  _stopgps() throws Exception{
 //BA.debugLineNum = 88;BA.debugLine="Public Sub StopGps";
 //BA.debugLineNum = 89;BA.debugLine="If GPSStarted Then";
if (_gpsstarted) { 
 //BA.debugLineNum = 90;BA.debugLine="GPS1.Stop";
_gps1.Stop();
 //BA.debugLineNum = 91;BA.debugLine="GPSStarted = False";
_gpsstarted = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}
}
