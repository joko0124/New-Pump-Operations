package bwsi.PumpOperations;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class firebasemessaging extends  android.app.Service{
	public static class firebasemessaging_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (firebasemessaging) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, firebasemessaging.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, false, BA.class);
		}

	}
    static firebasemessaging mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return firebasemessaging.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.firebasemessaging");
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
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.firebasemessaging", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!false && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (firebasemessaging) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (false) {
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
                    BA.LogInfo("** Service (firebasemessaging) Create **");
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
        if (false)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (firebasemessaging) Start **");
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
        if (false) {
            BA.LogInfo("** Service (firebasemessaging) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (firebasemessaging) Destroy **");
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
public static anywheresoftware.b4a.objects.FirebaseNotificationsService.FirebaseMessageWrapper _fm = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _myicon = null;
public static int _grpnotifyid = 0;
public static int _sumnotifyid = 0;
public static String _groupid = "";
public static String _groupname = "";
public static String _grpchnid = "";
public static String _grpchnname = "";
public static String _sumchnid = "";
public static String _sumchnname = "";
public static int _curnotifyid = 0;
public static int _remnotifyid = 0;
public b4a.example.dateutils _dateutils = null;
public bwsi.PumpOperations.main _main = null;
public bwsi.PumpOperations.actnewproduction _actnewproduction = null;
public bwsi.PumpOperations.actpumpoff _actpumpoff = null;
public bwsi.PumpOperations.dbasefunctions _dbasefunctions = null;
public bwsi.PumpOperations.addedittimerecord _addedittimerecord = null;
public bwsi.PumpOperations.actcmjofindings _actcmjofindings = null;
public bwsi.PumpOperations.actcriticalpoint _actcriticalpoint = null;
public bwsi.PumpOperations.actdccrjofindings _actdccrjofindings = null;
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
public bwsi.PumpOperations.dbutils _dbutils = null;
public bwsi.PumpOperations.edittimerecord _edittimerecord = null;
public bwsi.PumpOperations.globalvar _globalvar = null;
public bwsi.PumpOperations.mainscreen _mainscreen = null;
public bwsi.PumpOperations.miscfunctions _miscfunctions = null;
public bwsi.PumpOperations.myfunctions _myfunctions = null;
public bwsi.PumpOperations.myscale _myscale = null;
public bwsi.PumpOperations.starter _starter = null;
public bwsi.PumpOperations.validation _validation = null;
public bwsi.PumpOperations.httputils2service _httputils2service = null;
public bwsi.PumpOperations.b4xcollections _b4xcollections = null;
public static String  _fm_messagearrived(anywheresoftware.b4a.objects.FirebaseNotificationsService.RemoteMessageWrapper _message) throws Exception{
bwsi.PumpOperations.nb6 _n2 = null;
bwsi.PumpOperations.nb6 _nsum = null;
String _contenttext = "";
String _contenttitle = "";
 //BA.debugLineNum = 38;BA.debugLine="Sub fm_MessageArrived (Message As RemoteMessage)";
 //BA.debugLineNum = 39;BA.debugLine="Log(\"Message arrived\")";
anywheresoftware.b4a.keywords.Common.LogImpl("079953921","Message arrived",0);
 //BA.debugLineNum = 40;BA.debugLine="Log($\"Message data: ${Message.GetData}\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("079953922",("Message data: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_message.GetData().getObject()))+""),0);
 //BA.debugLineNum = 50;BA.debugLine="MyIcon = LoadBitmapResize(File.DirAssets, \"icon.p";
_myicon = anywheresoftware.b4a.keywords.Common.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"icon.png",anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (24)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (24)),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 52;BA.debugLine="Dim n2 As NB6";
_n2 = new bwsi.PumpOperations.nb6();
 //BA.debugLineNum = 53;BA.debugLine="n2.Initialize(grpChnId, grpChnName, \"DEFAULT\",Tru";
_n2._initialize /*bwsi.PumpOperations.nb6*/ (processBA,_grpchnid,(Object)(_grpchnname),"DEFAULT",anywheresoftware.b4a.keywords.Common.True)._smallicon /*bwsi.PumpOperations.nb6*/ (_myicon);
 //BA.debugLineNum = 54;BA.debugLine="n2.SetDefaults(True, True, True)";
_n2._setdefaults /*bwsi.PumpOperations.nb6*/ (anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 55;BA.debugLine="n2.NotificationChannelGroup(groupID, groupName)";
_n2._notificationchannelgroup /*String*/ (_groupid,_groupname);
 //BA.debugLineNum = 57;BA.debugLine="n2.GroupSet(groupID)";
_n2._groupset /*bwsi.PumpOperations.nb6*/ (_groupid);
 //BA.debugLineNum = 58;BA.debugLine="n2.GroupSummary(False)";
_n2._groupsummary /*bwsi.PumpOperations.nb6*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 59;BA.debugLine="n2.CustomLight(Colors.ARGB(255,255,0,0),10,10)";
_n2._customlight /*bwsi.PumpOperations.nb6*/ (anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (255),(int) (0),(int) (0)),(int) (10),(int) (10));
 //BA.debugLineNum = 61;BA.debugLine="n2.Build(Message.GetData.Get(\"title\"),Message.Get";
_n2._build /*anywheresoftware.b4a.objects.NotificationWrapper*/ (_message.GetData().Get((Object)("title")),_message.GetData().Get((Object)("body")),"tag",(Object)(mostCurrent._main.getObject())).Notify(_curnotifyid);
 //BA.debugLineNum = 62;BA.debugLine="curNotifyID = curNotifyID + 1";
_curnotifyid = (int) (_curnotifyid+1);
 //BA.debugLineNum = 64;BA.debugLine="Dim nSum As NB6";
_nsum = new bwsi.PumpOperations.nb6();
 //BA.debugLineNum = 65;BA.debugLine="nSum.Initialize(sumChnId, sumChnName, \"LOW\",True)";
_nsum._initialize /*bwsi.PumpOperations.nb6*/ (processBA,_sumchnid,(Object)(_sumchnname),"LOW",anywheresoftware.b4a.keywords.Common.True)._autocancel /*bwsi.PumpOperations.nb6*/ (anywheresoftware.b4a.keywords.Common.True)._smallicon /*bwsi.PumpOperations.nb6*/ (_myicon);
 //BA.debugLineNum = 67;BA.debugLine="nSum.SetDefaults(False, False, False)";
_nsum._setdefaults /*bwsi.PumpOperations.nb6*/ (anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 69;BA.debugLine="nSum.NotificationChannelGroup(groupID, groupName)";
_nsum._notificationchannelgroup /*String*/ (_groupid,_groupname);
 //BA.debugLineNum = 71;BA.debugLine="nSum.GroupSet(groupID)";
_nsum._groupset /*bwsi.PumpOperations.nb6*/ (_groupid);
 //BA.debugLineNum = 72;BA.debugLine="nSum.GroupSummary(True)";
_nsum._groupsummary /*bwsi.PumpOperations.nb6*/ (anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 74;BA.debugLine="Dim contentText As String = \"Summary Notification";
_contenttext = "Summary Notification";
 //BA.debugLineNum = 75;BA.debugLine="Dim contentTitle As String = \"Group \" & grpChnNam";
_contenttitle = "Group "+_grpchnname;
 //BA.debugLineNum = 76;BA.debugLine="nSum.Build(contentTitle, contentText, \"Tag\", Main";
_nsum._build /*anywheresoftware.b4a.objects.NotificationWrapper*/ ((Object)(_contenttitle),(Object)(_contenttext),"Tag",(Object)(mostCurrent._main.getObject())).Notify(_sumnotifyid);
 //BA.debugLineNum = 77;BA.debugLine="nSum.BadgeIconType(\"LARGE\").Number(sumNotifyID)";
_nsum._badgeicontype /*bwsi.PumpOperations.nb6*/ ("LARGE")._number /*bwsi.PumpOperations.nb6*/ (_sumnotifyid);
 //BA.debugLineNum = 78;BA.debugLine="Log($\"Token: \"$ & fm.Token)";
anywheresoftware.b4a.keywords.Common.LogImpl("079953960",("Token: ")+_fm.getToken(),0);
 //BA.debugLineNum = 79;BA.debugLine="End Sub";
return "";
}
public static String  _fm_tokenrefresh(String _token) throws Exception{
 //BA.debugLineNum = 81;BA.debugLine="Sub fm_TokenRefresh (Token As String)";
 //BA.debugLineNum = 82;BA.debugLine="Log(\"TokenRefresh: \" & Token)";
anywheresoftware.b4a.keywords.Common.LogImpl("080019457","TokenRefresh: "+_token,0);
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Private fm As FirebaseMessaging";
_fm = new anywheresoftware.b4a.objects.FirebaseNotificationsService.FirebaseMessageWrapper();
 //BA.debugLineNum = 8;BA.debugLine="Public MyIcon As Bitmap";
_myicon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Public const grpNotifyID As Int = 101";
_grpnotifyid = (int) (101);
 //BA.debugLineNum = 11;BA.debugLine="Public const sumNotifyID As Int = 100";
_sumnotifyid = (int) (100);
 //BA.debugLineNum = 12;BA.debugLine="Public const groupID As String = \"test_group\"";
_groupid = "test_group";
 //BA.debugLineNum = 13;BA.debugLine="Public const groupName As String = \"Test Group\"";
_groupname = "Test Group";
 //BA.debugLineNum = 14;BA.debugLine="Public const grpChnId As String = \"chnTest\"";
_grpchnid = "chnTest";
 //BA.debugLineNum = 15;BA.debugLine="Public const grpChnName As String = Application.L";
_grpchnname = anywheresoftware.b4a.keywords.Common.Application.getLabelName();
 //BA.debugLineNum = 16;BA.debugLine="Public const sumChnId As String = \"chnSummary\"";
_sumchnid = "chnSummary";
 //BA.debugLineNum = 17;BA.debugLine="Public const sumChnName As String = \"Summary Chan";
_sumchnname = "Summary Channel";
 //BA.debugLineNum = 18;BA.debugLine="Public curNotifyID As Int = grpNotifyID";
_curnotifyid = _grpnotifyid;
 //BA.debugLineNum = 20;BA.debugLine="Public remNotifyID As Int = 0";
_remnotifyid = (int) (0);
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 25;BA.debugLine="fm.Initialize(\"fm\")";
_fm.Initialize(processBA,"fm");
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 84;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
public static void  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
ResumableSub_Service_Start rsub = new ResumableSub_Service_Start(null,_startingintent);
rsub.resume(processBA, null);
}
public static class ResumableSub_Service_Start extends BA.ResumableSub {
public ResumableSub_Service_Start(bwsi.PumpOperations.firebasemessaging parent,anywheresoftware.b4a.objects.IntentWrapper _startingintent) {
this.parent = parent;
this._startingintent = _startingintent;
}
bwsi.PumpOperations.firebasemessaging parent;
anywheresoftware.b4a.objects.IntentWrapper _startingintent;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 33;BA.debugLine="If StartingIntent.IsInitialized Then fm.HandleInt";
if (true) break;

case 1:
//if
this.state = 6;
if (_startingintent.IsInitialized()) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
parent._fm.HandleIntent((android.content.Intent)(_startingintent.getObject()));
if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 34;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 7;
return;
case 7:
//C
this.state = -1;
;
 //BA.debugLineNum = 35;BA.debugLine="Service.StopAutomaticForeground 'remove if not us";
parent.mostCurrent._service.StopAutomaticForeground();
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _subscribetotopics() throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Public Sub SubscribeToTopics";
 //BA.debugLineNum = 29;BA.debugLine="fm.SubscribeToTopic(\"general\") 'you can subscribe";
_fm.SubscribeToTopic("general");
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return "";
}
}
