package bwsi.PumpOperations;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class actgpmhistory extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static actgpmhistory mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.actgpmhistory");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actgpmhistory).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.actgpmhistory");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.actgpmhistory", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actgpmhistory) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actgpmhistory) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return actgpmhistory.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (actgpmhistory) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (actgpmhistory) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            actgpmhistory mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (actgpmhistory) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public de.amberhome.objects.appcompat.ACActionBar _actionbarbutton = null;
public de.amberhome.objects.appcompat.ACToolbarDarkWrapper _toolbar = null;
public anywheresoftware.b4a.object.XmlLayoutBuilder _xmlicon = null;
public de.amberhome.materialdialogs.MaterialDialogBuilderWrapper _matdialogbuilder = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
public com.johan.Vibrate.Vibrate _vibration = null;
public static long[] _vibratepattern = null;
public de.amberhome.objects.SnackbarWrapper _snack = null;
public anywheresoftware.b4a.objects.CSBuilder _csans = null;
public anywheresoftware.b4a.objects.IME _kboard = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _pnlpsidist = null;
public static long _thedate = 0L;
public b4a.example3.customlistview _clvlist = null;
public bwsi.PumpOperations.bctoast _mytoast = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lbltestdate = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblwaterquality = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblgpmres = null;
public de.amberhome.objects.FloatingActionButtonWrapper _btnaddgpm = null;
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
public bwsi.PumpOperations.starter _starter = null;
public bwsi.PumpOperations.validation _validation = null;
public bwsi.PumpOperations.httputils2service _httputils2service = null;
public bwsi.PumpOperations.b4xcollections _b4xcollections = null;
public static class _gpmrecords{
public boolean IsInitialized;
public int ID;
public String sTrandate;
public int iBucketSize;
public String sUOM;
public double dResult;
public String sWaterQuality;
public void Initialize() {
IsInitialized = true;
ID = 0;
sTrandate = "";
iBucketSize = 0;
sUOM = "";
dResult = 0;
sWaterQuality = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4a.object.XmlLayoutBuilder _xl = null;
 //BA.debugLineNum = 51;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 52;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 53;BA.debugLine="Activity.LoadLayout(\"GPMHistory\")";
mostCurrent._activity.LoadLayout("GPMHistory",mostCurrent.activityBA);
 //BA.debugLineNum = 55;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Append";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("GPM History"))).PopAll();
 //BA.debugLineNum = 56;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append($";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("PUMP - ")+mostCurrent._globalvar._pumphousecode /*String*/ )).PopAll();
 //BA.debugLineNum = 58;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 59;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 60;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 62;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 63;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 64;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 65;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 66;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 67;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 69;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 70;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 72;BA.debugLine="kboard.Initialize(\"KeyBoard\")";
mostCurrent._kboard.Initialize("KeyBoard");
 //BA.debugLineNum = 73;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 };
 //BA.debugLineNum = 77;BA.debugLine="MyToast.Initialize(Activity)";
mostCurrent._mytoast._initialize /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())));
 //BA.debugLineNum = 79;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
 //BA.debugLineNum = 100;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 81;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 82;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 83;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 84;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 86;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 88;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 90;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 91;BA.debugLine="GetGPMRec(GlobalVar.PumpHouseID)";
_getgpmrec(mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddgpm_click() throws Exception{
 //BA.debugLineNum = 119;BA.debugLine="Sub btnAddGPM_Click";
 //BA.debugLineNum = 120;BA.debugLine="GlobalVar.blnNewGPM = True";
mostCurrent._globalvar._blnnewgpm /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 121;BA.debugLine="StartActivity(actGPMCalc)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actgpmcalc.getObject()));
 //BA.debugLineNum = 122;BA.debugLine="End Sub";
return "";
}
public static String  _clvlist_itemclick(int _index,Object _value) throws Exception{
bwsi.PumpOperations.actgpmhistory._gpmrecords _rec = null;
 //BA.debugLineNum = 196;BA.debugLine="Sub clvList_ItemClick (Index As Int, Value As Obje";
 //BA.debugLineNum = 197;BA.debugLine="Dim Rec As GPMRecords = Value";
_rec = (bwsi.PumpOperations.actgpmhistory._gpmrecords)(_value);
 //BA.debugLineNum = 198;BA.debugLine="Log(Rec.ID)";
anywheresoftware.b4a.keywords.Common.LogImpl("828770306",BA.NumberToString(_rec.ID /*int*/ ),0);
 //BA.debugLineNum = 199;BA.debugLine="ShowGPMResDetails(Rec.ID)";
_showgpmresdetails(_rec.ID /*int*/ );
 //BA.debugLineNum = 200;BA.debugLine="GlobalVar.GPMId = Rec.ID";
mostCurrent._globalvar._gpmid /*int*/  = _rec.ID /*int*/ ;
 //BA.debugLineNum = 201;BA.debugLine="End Sub";
return "";
}
public static String  _clvlist_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
int _extrasize = 0;
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
bwsi.PumpOperations.actgpmhistory._gpmrecords _gpmr = null;
 //BA.debugLineNum = 172;BA.debugLine="Sub clvList_VisibleRangeChanged (FirstIndex As Int";
 //BA.debugLineNum = 173;BA.debugLine="Dim ExtraSize As Int = 15 'List size";
_extrasize = (int) (15);
 //BA.debugLineNum = 174;BA.debugLine="For i = Max(0, FirstIndex - ExtraSize) To Min(Las";
{
final int step2 = 1;
final int limit2 = (int) (anywheresoftware.b4a.keywords.Common.Min(_lastindex+_extrasize,mostCurrent._clvlist._getsize()-1));
_i = (int) (anywheresoftware.b4a.keywords.Common.Max(0,_firstindex-_extrasize)) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 175;BA.debugLine="Dim Pnl As B4XView = clvList.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._clvlist._getpanel(_i);
 //BA.debugLineNum = 176;BA.debugLine="If i > FirstIndex - ExtraSize And i < LastIndex";
if (_i>_firstindex-_extrasize && _i<_lastindex+_extrasize) { 
 //BA.debugLineNum = 177;BA.debugLine="If Pnl.NumberOfViews = 0 Then 'Add each item/la";
if (_pnl.getNumberOfViews()==0) { 
 //BA.debugLineNum = 178;BA.debugLine="Dim GPMR As GPMRecords = clvList.GetValue(i)";
_gpmr = (bwsi.PumpOperations.actgpmhistory._gpmrecords)(mostCurrent._clvlist._getvalue(_i));
 //BA.debugLineNum = 179;BA.debugLine="Pnl.LoadLayout(\"ListGPMResults\")";
_pnl.LoadLayout("ListGPMResults",mostCurrent.activityBA);
 //BA.debugLineNum = 180;BA.debugLine="lblTestDate.TextColor = GlobalVar.PriColor";
mostCurrent._lbltestdate.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 181;BA.debugLine="lblGPMRes.TextColor = GlobalVar.PriColor";
mostCurrent._lblgpmres.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 182;BA.debugLine="lblWaterQuality.TextColor = GlobalVar.PriColor";
mostCurrent._lblwaterquality.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 184;BA.debugLine="lblTestDate.Text = GPMR.sTrandate";
mostCurrent._lbltestdate.setText(BA.ObjectToCharSequence(_gpmr.sTrandate /*String*/ ));
 //BA.debugLineNum = 185;BA.debugLine="lblGPMRes.Text = GPMR.dResult & $\" GPM\"$";
mostCurrent._lblgpmres.setText(BA.ObjectToCharSequence(BA.NumberToString(_gpmr.dResult /*double*/ )+(" GPM")));
 //BA.debugLineNum = 186;BA.debugLine="lblWaterQuality.Text = GPMR.sWaterQuality";
mostCurrent._lblwaterquality.setText(BA.ObjectToCharSequence(_gpmr.sWaterQuality /*String*/ ));
 };
 }else {
 //BA.debugLineNum = 189;BA.debugLine="If Pnl.NumberOfViews > 0 Then";
if (_pnl.getNumberOfViews()>0) { 
 //BA.debugLineNum = 190;BA.debugLine="Pnl.RemoveAllViews 'Remove none visable item/l";
_pnl.RemoveAllViews();
 };
 };
 }
};
 //BA.debugLineNum = 194;BA.debugLine="End Sub";
return "";
}
public static String  _editgpmrec_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 264;BA.debugLine="Private Sub EditGPMRec_ButtonPressed(mDialog As Ma";
 //BA.debugLineNum = 265;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE,_mdialog.ACTION_NEUTRAL)) {
case 0: {
 break; }
case 1: {
 //BA.debugLineNum = 268;BA.debugLine="GlobalVar.blnNewGPM = False";
mostCurrent._globalvar._blnnewgpm /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 269;BA.debugLine="StartActivity(actGPMCalc)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actgpmcalc.getObject()));
 break; }
case 2: {
 break; }
}
;
 //BA.debugLineNum = 272;BA.debugLine="End Sub";
return "";
}
public static void  _getgpmrec(int _ipumpid) throws Exception{
ResumableSub_GetGPMRec rsub = new ResumableSub_GetGPMRec(null,_ipumpid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetGPMRec extends BA.ResumableSub {
public ResumableSub_GetGPMRec(bwsi.PumpOperations.actgpmhistory parent,int _ipumpid) {
this.parent = parent;
this._ipumpid = _ipumpid;
}
bwsi.PumpOperations.actgpmhistory parent;
int _ipumpid;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actgpmhistory._gpmrecords _gpmr = null;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 125;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 126;BA.debugLine="clvList.Clear";
parent.mostCurrent._clvlist._clear();
 //BA.debugLineNum = 127;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 16;
this.catchState = 15;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 15;
 //BA.debugLineNum = 128;BA.debugLine="Starter.strCriteria = \"SELECT GPMID, TranDate, \"";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT GPMID, TranDate, "+"BucketSize, UnitOfMeasurement, GPMResult, WaterQuality "+"FROM tblGPMHistory "+"WHERE PumpID = "+BA.NumberToString(_ipumpid)+" "+"ORDER BY TranDate, GPMID ASC";
 //BA.debugLineNum = 134;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 135;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 17;
return;
case 17:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 137;BA.debugLine="If Success Then";
if (true) break;

case 4:
//if
this.state = 13;
if (_success) { 
this.state = 6;
}else {
this.state = 12;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 138;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 139;BA.debugLine="Do While RS.NextRow";
if (true) break;

case 7:
//do while
this.state = 10;
while (_rs.NextRow()) {
this.state = 9;
if (true) break;
}
if (true) break;

case 9:
//C
this.state = 7;
 //BA.debugLineNum = 140;BA.debugLine="Dim GPMR As GPMRecords";
_gpmr = new bwsi.PumpOperations.actgpmhistory._gpmrecords();
 //BA.debugLineNum = 141;BA.debugLine="GPMR.Initialize";
_gpmr.Initialize();
 //BA.debugLineNum = 142;BA.debugLine="GPMR.ID = RS.GetInt(\"GPMID\")";
_gpmr.ID /*int*/  = _rs.GetInt("GPMID");
 //BA.debugLineNum = 143;BA.debugLine="GPMR.sTrandate = RS.GetString(\"TranDate\")";
_gpmr.sTrandate /*String*/  = _rs.GetString("TranDate");
 //BA.debugLineNum = 144;BA.debugLine="GPMR.iBucketSize = RS.GetInt(\"BucketSize\")";
_gpmr.iBucketSize /*int*/  = _rs.GetInt("BucketSize");
 //BA.debugLineNum = 145;BA.debugLine="GPMR.sUOM = RS.GetString(\"UnitOfMeasurement\")";
_gpmr.sUOM /*String*/  = _rs.GetString("UnitOfMeasurement");
 //BA.debugLineNum = 146;BA.debugLine="GPMR.dResult = RS.GetDouble(\"GPMResult\")";
_gpmr.dResult /*double*/  = _rs.GetDouble("GPMResult");
 //BA.debugLineNum = 147;BA.debugLine="GPMR.sWaterQuality = RS.GetString(\"WaterQualit";
_gpmr.sWaterQuality /*String*/  = _rs.GetString("WaterQuality");
 //BA.debugLineNum = 149;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 150;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0, clvList.AsV";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (parent.mostCurrent._clvlist._asview().getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 151;BA.debugLine="clvList.Add(Pnl, GPMR)";
parent.mostCurrent._clvlist._add(_pnl,(Object)(_gpmr));
 if (true) break;

case 10:
//C
this.state = 13;
;
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 154;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 155;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 156;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 157;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 158;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("828639266",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 13:
//C
this.state = 16;
;
 //BA.debugLineNum = 161;BA.debugLine="Log($\"List of Time Records = ${NumberFormat2((Da";
anywheresoftware.b4a.keywords.Common.LogImpl("828639269",("List of Time Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvlist._getsize()))+" Chlorine Records"),0);
 if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
 //BA.debugLineNum = 164;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 165;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 166;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 167;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 168;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("828639276",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _sql_querycomplete(boolean _success,anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs) throws Exception{
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 22;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 23;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 24;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private xmlIcon As XmlLayoutBuilder";
mostCurrent._xmlicon = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 26;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private CD As ColorDrawable";
mostCurrent._cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 28;BA.debugLine="Private vibration As B4Avibrate";
mostCurrent._vibration = new com.johan.Vibrate.Vibrate();
 //BA.debugLineNum = 29;BA.debugLine="Private vibratePattern() As Long";
_vibratepattern = new long[(int) (0)];
;
 //BA.debugLineNum = 31;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private csAns As CSBuilder";
mostCurrent._csans = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 33;BA.debugLine="Dim kboard As IME";
mostCurrent._kboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 35;BA.debugLine="Private pnlPSIDist As B4XView";
mostCurrent._pnlpsidist = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Dim theDate As Long";
_thedate = 0L;
 //BA.debugLineNum = 39;BA.debugLine="Type GPMRecords (ID As Int, sTrandate As String,i";
;
 //BA.debugLineNum = 40;BA.debugLine="Private clvList As CustomListView";
mostCurrent._clvlist = new b4a.example3.customlistview();
 //BA.debugLineNum = 41;BA.debugLine="Private MyToast As BCToast";
mostCurrent._mytoast = new bwsi.PumpOperations.bctoast();
 //BA.debugLineNum = 43;BA.debugLine="Private lblTestDate As B4XView";
mostCurrent._lbltestdate = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private lblWaterQuality As B4XView";
mostCurrent._lblwaterquality = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private lblGPMRes As B4XView";
mostCurrent._lblgpmres = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private btnAddGPM As DSFloatingActionButton";
mostCurrent._btnaddgpm = new de.amberhome.objects.FloatingActionButtonWrapper();
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static void  _showgpmresdetails(int _iid) throws Exception{
ResumableSub_ShowGPMResDetails rsub = new ResumableSub_ShowGPMResDetails(null,_iid);
rsub.resume(processBA, null);
}
public static class ResumableSub_ShowGPMResDetails extends BA.ResumableSub {
public ResumableSub_ShowGPMResDetails(bwsi.PumpOperations.actgpmhistory parent,int _iid) {
this.parent = parent;
this._iid = _iid;
}
bwsi.PumpOperations.actgpmhistory parent;
int _iid;
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
Object _senderfilter = null;
String _spcode = "";
String _sdate = "";
String _sbucketsize = "";
String _itry1 = "";
String _itry2 = "";
String _itry3 = "";
String _suom = "";
String _swaterquality = "";
String _srem = "";
double _dres = 0;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 204;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 205;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 206;BA.debugLine="Dim sPCode, sDate, sBucketSize As String";
_spcode = "";
_sdate = "";
_sbucketsize = "";
 //BA.debugLineNum = 207;BA.debugLine="Dim iTry1, iTry2, iTry3 As String";
_itry1 = "";
_itry2 = "";
_itry3 = "";
 //BA.debugLineNum = 208;BA.debugLine="Dim sUOM, sWaterQuality, sRem As String";
_suom = "";
_swaterquality = "";
_srem = "";
 //BA.debugLineNum = 209;BA.debugLine="Dim dRes As Double";
_dres = 0;
 //BA.debugLineNum = 211;BA.debugLine="Starter.strCriteria = \"SELECT Station.PumpHouseCo";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Station.PumpHouseCode, GPMHist.TranDate, "+"GPMHist.BucketSize, GPMHist.UnitOfMeasurement, "+"GPMHist.Trial1, GPMHist.Trial2, GPMHist.Trial3, "+"GPMHist.GPMResult, GPMHist.WaterQuality, GPMHist.Remarks "+"FROM tblGPMHistory AS GPMHist "+"INNER JOIN tblPumpStation AS Station ON GPMHist.PumpID = Station.StationID "+"WHERE GPMHist.GPMID = "+BA.NumberToString(_iid);
 //BA.debugLineNum = 219;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL\"";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 220;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succes";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 7;
return;
case 7:
//C
this.state = 1;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 222;BA.debugLine="If Success Then";
if (true) break;

case 1:
//if
this.state = 6;
if (_success) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 223;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 224;BA.debugLine="sDate = RS.GetString(\"TranDate\")";
_sdate = _rs.GetString("TranDate");
 //BA.debugLineNum = 225;BA.debugLine="sPCode = RS.GetString(\"PumpHouseCode\")";
_spcode = _rs.GetString("PumpHouseCode");
 //BA.debugLineNum = 226;BA.debugLine="sBucketSize = RS.GetInt(\"BucketSize\") & $\" \"$ &";
_sbucketsize = BA.NumberToString(_rs.GetInt("BucketSize"))+(" ")+_rs.GetString("UnitOfMeasurement");
 //BA.debugLineNum = 227;BA.debugLine="iTry1 = RS.GetString(\"Trial1\")";
_itry1 = _rs.GetString("Trial1");
 //BA.debugLineNum = 228;BA.debugLine="iTry2 = RS.GetString(\"Trial2\")";
_itry2 = _rs.GetString("Trial2");
 //BA.debugLineNum = 229;BA.debugLine="iTry3 = RS.GetString(\"Trial3\")";
_itry3 = _rs.GetString("Trial3");
 //BA.debugLineNum = 230;BA.debugLine="dRes = RS.GetDouble(\"GPMResult\")";
_dres = _rs.GetDouble("GPMResult");
 //BA.debugLineNum = 231;BA.debugLine="sWaterQuality = RS.GetString(\"WaterQuality\")";
_swaterquality = _rs.GetString("WaterQuality");
 //BA.debugLineNum = 232;BA.debugLine="sRem = RS.GetString(\"Remarks\")";
_srem = _rs.GetString("Remarks");
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 234;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 235;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 236;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 237;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 238;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("828835875",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 241;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar.";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (parent.mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("GPM RESULT DETAILS"))).PopAll();
 //BA.debugLineNum = 243;BA.debugLine="MatDialogBuilder.Initialize(\"EditGPMRec\")";
parent.mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"EditGPMRec");
 //BA.debugLineNum = 244;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
parent.mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (parent.mostCurrent._globalvar._bluecolor /*double*/ ));
 //BA.debugLineNum = 245;BA.debugLine="MatDialogBuilder.NegativeText(\"EDIT\").NegativeCol";
parent.mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence("EDIT")).NegativeColor((int) (parent.mostCurrent._globalvar._greencolor /*double*/ ));
 //BA.debugLineNum = 246;BA.debugLine="MatDialogBuilder.NeutralText(\"DELETE\").NeutralCol";
parent.mostCurrent._matdialogbuilder.NeutralText(BA.ObjectToCharSequence("DELETE")).NeutralColor((int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 247;BA.debugLine="MatDialogBuilder.Title(csTitle)";
parent.mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 248;BA.debugLine="MatDialogBuilder.Content($\"  Pump: ${sPCode} 	Tra";
parent.mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(("  Pump: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_spcode))+"\n"+"	Transaction Date: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sdate))+"\n"+"	\n"+"	Bucket Size: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sbucketsize))+"\n"+"	1st Trial: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_itry1))+" sec(s).\n"+"	2nd Trial: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_itry2))+" sec(s).\n"+"	3rd Trial: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_itry3))+" sec(s).\n"+"	GPM Result: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_dres))+" GPM\n"+"	Water Quality: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_swaterquality))+"\n"+"	Remarks: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_srem))+"")));
 //BA.debugLineNum = 258;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
parent.mostCurrent._matdialogbuilder.Theme(parent.mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 259;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
parent.mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 260;BA.debugLine="MatDialogBuilder.Cancelable(True)";
parent.mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 261;BA.debugLine="MatDialogBuilder.Show";
parent.mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 262;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 113;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 108;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 109;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 110;BA.debugLine="kboard.HideKeyboard";
mostCurrent._kboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return "";
}

public boolean _onCreateOptionsMenu(android.view.Menu menu) {
	if (processBA.subExists("activity_createmenu")) {
		processBA.raiseEvent2(null, true, "activity_createmenu", false, new de.amberhome.objects.appcompat.ACMenuWrapper(menu));
		return true;
	}
	else
		return false;
}
}
