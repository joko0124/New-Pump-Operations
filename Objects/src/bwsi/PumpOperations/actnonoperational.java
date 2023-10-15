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

public class actnonoperational extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static actnonoperational mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.actnonoperational");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actnonoperational).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.actnonoperational");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.actnonoperational", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actnonoperational) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actnonoperational) Resume **");
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
		return actnonoperational.class;
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
            BA.LogInfo("** Activity (actnonoperational) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (actnonoperational) Pause event (activity is not paused). **");
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
            actnonoperational mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (actnonoperational) Resume **");
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
public static bwsi.PumpOperations.slinptypeconst _inptyp = null;
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
public anywheresoftware.b4a.objects.B4XViewWrapper _pnltime = null;
public static long _thedate = 0L;
public static String _sreccount = "";
public b4a.example3.customlistview _clvtime = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lbltimeoff = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lbltimeon = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblophrs = null;
public de.amberhome.objects.FloatingActionButtonWrapper _btnaddtime = null;
public static long _lselectedrectimeon = 0L;
public static boolean _blnaddtime = false;
public static int _ilastreading = 0;
public bwsi.PumpOperations.bctoast _mytoast = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdreading = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdrem = null;
public com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fontbold = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstatus = null;
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
public bwsi.PumpOperations.firebasemessaging _firebasemessaging = null;
public bwsi.PumpOperations.globalvar _globalvar = null;
public bwsi.PumpOperations.mainscreen _mainscreen = null;
public bwsi.PumpOperations.miscfunctions _miscfunctions = null;
public bwsi.PumpOperations.myfunctions _myfunctions = null;
public bwsi.PumpOperations.myscale _myscale = null;
public bwsi.PumpOperations.starter _starter = null;
public bwsi.PumpOperations.validation _validation = null;
public bwsi.PumpOperations.httputils2service _httputils2service = null;
public bwsi.PumpOperations.b4xcollections _b4xcollections = null;
public static class _timenonoprecords{
public boolean IsInitialized;
public int ID;
public long lTimeOff;
public long lTimeOn;
public double dTotNonOpHrs;
public String sNonOpReason;
public String sNonOpRemarks;
public void Initialize() {
IsInitialized = true;
ID = 0;
lTimeOff = 0L;
lTimeOn = 0L;
dTotNonOpHrs = 0;
sNonOpReason = "";
sNonOpRemarks = "";
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
 //BA.debugLineNum = 66;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 67;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 68;BA.debugLine="Activity.LoadLayout(\"NonOperational\")";
mostCurrent._activity.LoadLayout("NonOperational",mostCurrent.activityBA);
 //BA.debugLineNum = 70;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 72;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Append";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("PUMP - ")+mostCurrent._globalvar._pumphousecode /*String*/ +(" - NON OPERATIONAL"))).PopAll();
 //BA.debugLineNum = 73;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append($";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 //BA.debugLineNum = 75;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 76;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 77;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 79;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 80;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 81;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 82;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 83;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 84;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 86;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 87;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 89;BA.debugLine="kboard.Initialize(\"KeyBoard\")";
mostCurrent._kboard.Initialize("KeyBoard");
 //BA.debugLineNum = 91;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 92;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeade";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 93;BA.debugLine="MyToast.Initialize(Activity)";
mostCurrent._mytoast._initialize /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())));
 };
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
 //BA.debugLineNum = 121;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 122;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 98;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 99;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 100;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 101;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 103;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 115;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 107;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 108;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 109;BA.debugLine="clvTime.Clear";
mostCurrent._clvtime._clear();
 //BA.debugLineNum = 111;BA.debugLine="If GlobalVar.TranHeaderID > 0 Then	GetPumpNonOPTi";
if (mostCurrent._globalvar._tranheaderid /*int*/ >0) { 
_getpumpnonoptimerec(mostCurrent._globalvar._tranheaderid /*int*/ );};
 //BA.debugLineNum = 112;BA.debugLine="MyToast.Initialize(Activity)";
mostCurrent._mytoast._initialize /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())));
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddtime_click() throws Exception{
 //BA.debugLineNum = 320;BA.debugLine="Sub btnAddTime_Click";
 //BA.debugLineNum = 321;BA.debugLine="GlobalVar.blnNewNonOp = True";
mostCurrent._globalvar._blnnewnonop /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 322;BA.debugLine="StartActivity(AddEditNonOperational)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addeditnonoperational.getObject()));
 //BA.debugLineNum = 323;BA.debugLine="End Sub";
return "";
}
public static String  _clvtime_itemclick(int _index,Object _value) throws Exception{
bwsi.PumpOperations.actnonoperational._timenonoprecords _rec = null;
 //BA.debugLineNum = 233;BA.debugLine="Sub clvTime_ItemClick (Index As Int, Value As Obje";
 //BA.debugLineNum = 234;BA.debugLine="Dim Rec As TimeNonOPRecords = Value";
_rec = (bwsi.PumpOperations.actnonoperational._timenonoprecords)(_value);
 //BA.debugLineNum = 235;BA.debugLine="Log(Rec.ID)";
anywheresoftware.b4a.keywords.Common.LogImpl("045809666",BA.NumberToString(_rec.ID /*int*/ ),0);
 //BA.debugLineNum = 238;BA.debugLine="GlobalVar.NonOpDetailID = Rec.ID";
mostCurrent._globalvar._nonopdetailid /*int*/  = _rec.ID /*int*/ ;
 //BA.debugLineNum = 239;BA.debugLine="ShowNonOpeDetails(Rec.ID)";
_shownonopedetails(_rec.ID /*int*/ );
 //BA.debugLineNum = 240;BA.debugLine="End Sub";
return "";
}
public static String  _clvtime_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
int _extrasize = 0;
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
bwsi.PumpOperations.actnonoperational._timenonoprecords _timerec = null;
 //BA.debugLineNum = 202;BA.debugLine="Sub clvTime_VisibleRangeChanged (FirstIndex As Int";
 //BA.debugLineNum = 204;BA.debugLine="Dim ExtraSize As Int = 15 'List size";
_extrasize = (int) (15);
 //BA.debugLineNum = 205;BA.debugLine="For i = Max(0, FirstIndex - ExtraSize) To Min(Las";
{
final int step2 = 1;
final int limit2 = (int) (anywheresoftware.b4a.keywords.Common.Min(_lastindex+_extrasize,mostCurrent._clvtime._getsize()-1));
_i = (int) (anywheresoftware.b4a.keywords.Common.Max(0,_firstindex-_extrasize)) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 206;BA.debugLine="Dim Pnl As B4XView = clvTime.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._clvtime._getpanel(_i);
 //BA.debugLineNum = 207;BA.debugLine="If i > FirstIndex - ExtraSize And i < LastIndex";
if (_i>_firstindex-_extrasize && _i<_lastindex+_extrasize) { 
 //BA.debugLineNum = 208;BA.debugLine="If Pnl.NumberOfViews = 0 Then 'Add each item/la";
if (_pnl.getNumberOfViews()==0) { 
 //BA.debugLineNum = 209;BA.debugLine="Dim TimeRec As TimeNonOPRecords = clvTime.GetV";
_timerec = (bwsi.PumpOperations.actnonoperational._timenonoprecords)(mostCurrent._clvtime._getvalue(_i));
 //BA.debugLineNum = 211;BA.debugLine="Pnl.LoadLayout(\"ListNonOperationalRecords\")";
_pnl.LoadLayout("ListNonOperationalRecords",mostCurrent.activityBA);
 //BA.debugLineNum = 212;BA.debugLine="lblTimeOff.TextColor = GlobalVar.PriColor";
mostCurrent._lbltimeoff.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 213;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 214;BA.debugLine="lblTimeOff.Text = DateTime.Time(TimeRec.lTimeO";
mostCurrent._lbltimeoff.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(_timerec.lTimeOff /*long*/ )));
 //BA.debugLineNum = 216;BA.debugLine="lblTimeOn.TextColor = GlobalVar.PriColor";
mostCurrent._lbltimeon.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 217;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 218;BA.debugLine="lblTimeOn.Text = DateTime.Time(TimeRec.lTimeOn";
mostCurrent._lbltimeon.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(_timerec.lTimeOn /*long*/ )));
 //BA.debugLineNum = 220;BA.debugLine="lblOpHrs.TextColor = GlobalVar.PriColor";
mostCurrent._lblophrs.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 221;BA.debugLine="lblOpHrs.Text = NumberFormat2(TimeRec.dTotNonO";
mostCurrent._lblophrs.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.NumberFormat2(_timerec.dTotNonOpHrs /*double*/ ,(int) (1),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.True)+(" Hr(s).")));
 };
 }else {
 //BA.debugLineNum = 225;BA.debugLine="If Pnl.NumberOfViews > 0 Then";
if (_pnl.getNumberOfViews()>0) { 
 //BA.debugLineNum = 226;BA.debugLine="Pnl.RemoveAllViews 'Remove none visable item/l";
_pnl.RemoveAllViews();
 };
 };
 }
};
 //BA.debugLineNum = 231;BA.debugLine="End Sub";
return "";
}
public static String  _confirmpumpoff() throws Exception{
 //BA.debugLineNum = 325;BA.debugLine="Private Sub ConfirmPumpOff";
 //BA.debugLineNum = 357;BA.debugLine="End Sub";
return "";
}
public static String  _editnonop_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 310;BA.debugLine="Private Sub EditNonOP_ButtonPressed(mDialog As Mat";
 //BA.debugLineNum = 311;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE,_mdialog.ACTION_NEUTRAL)) {
case 0: {
 break; }
case 1: {
 //BA.debugLineNum = 314;BA.debugLine="GlobalVar.blnNewNonOp = False";
mostCurrent._globalvar._blnnewnonop /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 315;BA.debugLine="StartActivity(AddEditNonOperational)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addeditnonoperational.getObject()));
 break; }
case 2: {
 break; }
}
;
 //BA.debugLineNum = 318;BA.debugLine="End Sub";
return "";
}
public static void  _getpumpnonoptimerec(int _itranheaderid) throws Exception{
ResumableSub_GetPumpNonOPTimeRec rsub = new ResumableSub_GetPumpNonOPTimeRec(null,_itranheaderid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetPumpNonOPTimeRec extends BA.ResumableSub {
public ResumableSub_GetPumpNonOPTimeRec(bwsi.PumpOperations.actnonoperational parent,int _itranheaderid) {
this.parent = parent;
this._itranheaderid = _itranheaderid;
}
bwsi.PumpOperations.actnonoperational parent;
int _itranheaderid;
Object _senderfilter = null;
String _stimeoff = "";
String _stimeon = "";
int _ipowerstatus = 0;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actnonoperational._timenonoprecords _tnonoprec = null;
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
 //BA.debugLineNum = 138;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 139;BA.debugLine="Dim sTimeOFF, sTimeON As String";
_stimeoff = "";
_stimeon = "";
 //BA.debugLineNum = 140;BA.debugLine="Dim iPowerStatus As Int";
_ipowerstatus = 0;
 //BA.debugLineNum = 142;BA.debugLine="clvTime.Clear";
parent.mostCurrent._clvtime._clear();
 //BA.debugLineNum = 144;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 24;
this.catchState = 23;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 23;
 //BA.debugLineNum = 145;BA.debugLine="Starter.strCriteria = \"SELECT Details.HeaderID,";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Details.HeaderID, Details.DetailID, "+"Details.OffTime, Details.OnTime, Details.TotNonOpHrs, Details.NonOpReason, Details.Remarks "+"FROM NonOpDetails AS Details "+"INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID"+" "+"ORDER BY substr(OffTime,7,2) || (Case WHEN substr(OffTime,1,2) = '12' AND substr(OffTime,7,2) ='AM' THEN '00' ELSE substr(OffTime,1,2) END) || ' ' || substr(OffTime,4,2), Details.DetailID ASC";
 //BA.debugLineNum = 151;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 152;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 25;
return;
case 25:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 154;BA.debugLine="If Success Then";
if (true) break;

case 4:
//if
this.state = 21;
if (_success) { 
this.state = 6;
}else {
this.state = 20;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 155;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 156;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 157;BA.debugLine="Dim TNonOPRec As TimeNonOPRecords";
_tnonoprec = new bwsi.PumpOperations.actnonoperational._timenonoprecords();
 //BA.debugLineNum = 158;BA.debugLine="TNonOPRec.Initialize";
_tnonoprec.Initialize();
 //BA.debugLineNum = 159;BA.debugLine="TNonOPRec.ID = RS.GetInt(\"DetailID\")";
_tnonoprec.ID /*int*/  = _rs.GetInt("DetailID");
 //BA.debugLineNum = 160;BA.debugLine="sTimeOFF = RS.GetString(\"OffTime\")";
_stimeoff = _rs.GetString("OffTime");
 //BA.debugLineNum = 161;BA.debugLine="sTimeON = RS.GetString(\"OnTime\")";
_stimeon = _rs.GetString("OnTime");
 //BA.debugLineNum = 162;BA.debugLine="TNonOPRec.dTotNonOpHrs = RS.GetDouble(\"TotNonO";
_tnonoprec.dTotNonOpHrs /*double*/  = _rs.GetDouble("TotNonOpHrs");
 //BA.debugLineNum = 163;BA.debugLine="TNonOPRec.sNonOpReason= RS.GetString(\"NonOpRea";
_tnonoprec.sNonOpReason /*String*/  = _rs.GetString("NonOpReason");
 //BA.debugLineNum = 164;BA.debugLine="TNonOPRec.sNonOpRemarks = RS.GetString(\"Remark";
_tnonoprec.sNonOpRemarks /*String*/  = _rs.GetString("Remarks");
 //BA.debugLineNum = 171;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 172;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0dip, clvTime.";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),(int) (parent.mostCurrent._clvtime._asview().getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 173;BA.debugLine="clvTime.Add(Pnl, TNonOPRec)";
parent.mostCurrent._clvtime._add(_pnl,(Object)(_tnonoprec));
 if (true) break;

case 10:
//C
this.state = 11;
;
 //BA.debugLineNum = 175;BA.debugLine="sRecCount = RS.RowCount";
parent.mostCurrent._sreccount = BA.NumberToString(_rs.getRowCount());
 //BA.debugLineNum = 176;BA.debugLine="If sRecCount <= 0 Then";
if (true) break;

case 11:
//if
this.state = 18;
if ((double)(Double.parseDouble(parent.mostCurrent._sreccount))<=0) { 
this.state = 13;
}else if((parent.mostCurrent._sreccount).equals(BA.NumberToString(1))) { 
this.state = 15;
}else if((double)(Double.parseDouble(parent.mostCurrent._sreccount))>1) { 
this.state = 17;
}if (true) break;

case 13:
//C
this.state = 18;
 //BA.debugLineNum = 177;BA.debugLine="lblStatus.Text = $\"No Record Found\"$";
parent.mostCurrent._lblstatus.setText(BA.ObjectToCharSequence(("No Record Found")));
 if (true) break;

case 15:
//C
this.state = 18;
 //BA.debugLineNum = 179;BA.debugLine="lblStatus.Text = sRecCount &  $\" Record Found";
parent.mostCurrent._lblstatus.setText(BA.ObjectToCharSequence(parent.mostCurrent._sreccount+(" Record Found")));
 if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 181;BA.debugLine="lblStatus.Text = sRecCount &  $\" Records Foun";
parent.mostCurrent._lblstatus.setText(BA.ObjectToCharSequence(parent.mostCurrent._sreccount+(" Records Found")));
 if (true) break;

case 18:
//C
this.state = 21;
;
 if (true) break;

case 20:
//C
this.state = 21;
 //BA.debugLineNum = 184;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 185;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 186;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 187;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 188;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("045678643",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 21:
//C
this.state = 24;
;
 //BA.debugLineNum = 191;BA.debugLine="Log($\"List of Time Records = ${NumberFormat2((Da";
anywheresoftware.b4a.keywords.Common.LogImpl("045678646",("List of Time Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvtime._getsize()))+" Time Records"),0);
 if (true) break;

case 23:
//C
this.state = 24;
this.catchState = 0;
 //BA.debugLineNum = 194;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 195;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 196;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 197;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 198;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("045678653",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 24:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 200;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 24;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 25;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 26;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private xmlIcon As XmlLayoutBuilder";
mostCurrent._xmlicon = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 28;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private CD As ColorDrawable";
mostCurrent._cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 30;BA.debugLine="Private vibration As B4Avibrate";
mostCurrent._vibration = new com.johan.Vibrate.Vibrate();
 //BA.debugLineNum = 31;BA.debugLine="Private vibratePattern() As Long";
_vibratepattern = new long[(int) (0)];
;
 //BA.debugLineNum = 33;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private csAns As CSBuilder";
mostCurrent._csans = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 35;BA.debugLine="Dim kboard As IME";
mostCurrent._kboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 37;BA.debugLine="Private PnlTime As B4XView";
mostCurrent._pnltime = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Dim theDate As Long";
_thedate = 0L;
 //BA.debugLineNum = 41;BA.debugLine="Type TimeNonOPRecords (ID As Int, lTimeOff As Lon";
;
 //BA.debugLineNum = 42;BA.debugLine="Private sRecCount As String";
mostCurrent._sreccount = "";
 //BA.debugLineNum = 43;BA.debugLine="Private clvTime As CustomListView";
mostCurrent._clvtime = new b4a.example3.customlistview();
 //BA.debugLineNum = 44;BA.debugLine="Private lblTimeOff As B4XView";
mostCurrent._lbltimeoff = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private lblTimeOn As B4XView";
mostCurrent._lbltimeon = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private lblOpHrs As B4XView";
mostCurrent._lblophrs = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private btnAddTime As DSFloatingActionButton";
mostCurrent._btnaddtime = new de.amberhome.objects.FloatingActionButtonWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private lSelectedRecTimeOn As Long";
_lselectedrectimeon = 0L;
 //BA.debugLineNum = 50;BA.debugLine="Private blnAddTime As Boolean";
_blnaddtime = false;
 //BA.debugLineNum = 53;BA.debugLine="Private iLastReading = 0 As Int";
_ilastreading = (int) (0);
 //BA.debugLineNum = 54;BA.debugLine="Private MyToast As BCToast";
mostCurrent._mytoast = new bwsi.PumpOperations.bctoast();
 //BA.debugLineNum = 56;BA.debugLine="Dim cdReading, cdRem As ColorDrawable";
mostCurrent._cdreading = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdrem = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 57;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 58;BA.debugLine="Private Font As Typeface = Typeface.LoadFromAsset";
mostCurrent._font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont.ttf")));
 //BA.debugLineNum = 59;BA.debugLine="Private FontBold As Typeface = Typeface.LoadFromA";
mostCurrent._fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont_bold.ttf")));
 //BA.debugLineNum = 61;BA.debugLine="Private lblStatus As Label";
mostCurrent._lblstatus = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static boolean  _islastfmreading(int _idetailsid,int _iheaderid) throws Exception{
boolean _bretval = false;
int _idcheck = 0;
 //BA.debugLineNum = 414;BA.debugLine="Private Sub isLastFMReading(iDetailsID As Int, iHe";
 //BA.debugLineNum = 415;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 416;BA.debugLine="Dim idCheck As Int";
_idcheck = 0;
 //BA.debugLineNum = 417;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 419;BA.debugLine="Try";
try { //BA.debugLineNum = 420;BA.debugLine="idCheck = Starter.DBCon.ExecQuerySingleResult(\"S";
_idcheck = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT Max(DetailID) FROM ProductionDetails WHERE HeaderID = "+BA.NumberToString(_iheaderid)+" "+"GROUP BY HeaderID")));
 //BA.debugLineNum = 422;BA.debugLine="LogColor($\"Selected ID: \"$ & iDetailsID & $\" - L";
anywheresoftware.b4a.keywords.Common.LogImpl("046399496",("Selected ID: ")+BA.NumberToString(_idetailsid)+(" - Last ID: ")+BA.NumberToString(_idcheck),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 424;BA.debugLine="If iDetailsID = idCheck Then";
if (_idetailsid==_idcheck) { 
 //BA.debugLineNum = 425;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 427;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e13) {
			processBA.setLastException(e13); //BA.debugLineNum = 430;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 431;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("046399505",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 433;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 434;BA.debugLine="End Sub";
return false;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 21;BA.debugLine="Private InpTyp As SLInpTypeConst";
_inptyp = new bwsi.PumpOperations.slinptypeconst();
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public static String  _pumpoff_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 359;BA.debugLine="Private Sub PumpOff_OnNegativeClicked (View As Vie";
 //BA.debugLineNum = 362;BA.debugLine="End Sub";
return "";
}
public static String  _pumpoff_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 364;BA.debugLine="Private Sub PumpOff_OnPositiveClicked (View As Vie";
 //BA.debugLineNum = 370;BA.debugLine="End Sub";
return "";
}
public static String  _shownonopedetails(int _iid) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
anywheresoftware.b4a.objects.CSBuilder _csmsg = null;
String _sdate = "";
String _spcode = "";
String _splocation = "";
String _stimeoff = "";
String _stimeon = "";
String _sreason = "";
String _srem = "";
double _itotophrs = 0;
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsdetails = null;
 //BA.debugLineNum = 242;BA.debugLine="Sub ShowNonOpeDetails (iID As Int)";
 //BA.debugLineNum = 243;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 244;BA.debugLine="Dim csMSg As CSBuilder";
_csmsg = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 246;BA.debugLine="Dim sDate, sPCode, sPLocation, sTimeOff, sTimeOn,";
_sdate = "";
_spcode = "";
_splocation = "";
_stimeoff = "";
_stimeon = "";
_sreason = "";
_srem = "";
 //BA.debugLineNum = 247;BA.debugLine="Dim iTotOPHrs As Double";
_itotophrs = 0;
 //BA.debugLineNum = 248;BA.debugLine="Dim rsDetails As Cursor";
_rsdetails = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 250;BA.debugLine="LogColor (iID, Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("045875208",BA.NumberToString(_iid),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 251;BA.debugLine="Try";
try { //BA.debugLineNum = 252;BA.debugLine="Starter.strCriteria = \"SELECT Details.DetailID,";
mostCurrent._starter._strcriteria /*String*/  = "SELECT Details.DetailID, Details.HeaderID, Header.TranDate, "+"Pump.PumpHouseCode, Pump.PumpLocation, "+"Details.OffTime, Details.OnTime, Details.TotNonOpHrs, "+"Details.NonOpReason, Details.Remarks "+"FROM NonOpDetails AS Details "+"INNER JOIN TranHeader AS Header ON Header.HeaderID = Details.HeaderID "+"INNER JOIN tblPumpStation AS Pump ON Pump.StationID = Header.PumpID "+"WHERE Details.DetailID = "+BA.NumberToString(_iid);
 //BA.debugLineNum = 261;BA.debugLine="rsDetails = Starter.DBCon.ExecQuery(Starter.strC";
_rsdetails = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 262;BA.debugLine="If rsDetails.RowCount > 0 Then";
if (_rsdetails.getRowCount()>0) { 
 //BA.debugLineNum = 263;BA.debugLine="rsDetails.Position = 0";
_rsdetails.setPosition((int) (0));
 //BA.debugLineNum = 264;BA.debugLine="sDate = rsDetails.GetString(\"TranDate\")";
_sdate = _rsdetails.GetString("TranDate");
 //BA.debugLineNum = 265;BA.debugLine="sPCode = rsDetails.GetString(\"PumpHouseCode\")";
_spcode = _rsdetails.GetString("PumpHouseCode");
 //BA.debugLineNum = 266;BA.debugLine="sPLocation = rsDetails.GetString(\"PumpLocation\"";
_splocation = _rsdetails.GetString("PumpLocation");
 //BA.debugLineNum = 267;BA.debugLine="sTimeOff = rsDetails.GetString(\"OffTime\")";
_stimeoff = _rsdetails.GetString("OffTime");
 //BA.debugLineNum = 268;BA.debugLine="sTimeOn = rsDetails.GetString(\"OnTime\")";
_stimeon = _rsdetails.GetString("OnTime");
 //BA.debugLineNum = 269;BA.debugLine="sReason = rsDetails.GetString(\"NonOpReason\")";
_sreason = _rsdetails.GetString("NonOpReason");
 //BA.debugLineNum = 270;BA.debugLine="sRem = rsDetails.GetString(\"Remarks\")";
_srem = _rsdetails.GetString("Remarks");
 //BA.debugLineNum = 271;BA.debugLine="iTotOPHrs = rsDetails.GetDouble(\"TotNonOpHrs\")";
_itotophrs = _rsdetails.GetDouble("TotNonOpHrs");
 }else {
 //BA.debugLineNum = 273;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e24) {
			processBA.setLastException(e24); //BA.debugLineNum = 277;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("045875235",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 279;BA.debugLine="rsDetails.Close";
_rsdetails.Close();
 //BA.debugLineNum = 281;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar.";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("NON-OPERATIONAL DETAILS"))).PopAll();
 //BA.debugLineNum = 282;BA.debugLine="csMSg.Initialize.Size(17).Color(GlobalVar.GreenCo";
_csmsg.Initialize().Size((int) (17)).Color((int) (mostCurrent._globalvar._greencolor /*double*/ )).Append(BA.ObjectToCharSequence(_spcode));
 //BA.debugLineNum = 284;BA.debugLine="If isLastFMReading(GlobalVar.FMRdgDetailID, Globa";
if (_islastfmreading(mostCurrent._globalvar._fmrdgdetailid /*int*/ ,mostCurrent._globalvar._tranheaderid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 285;BA.debugLine="MatDialogBuilder.NegativeText(\"EDIT\").NegativeCo";
mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence("EDIT")).NegativeColor((int) (mostCurrent._globalvar._greencolor /*double*/ ));
 }else {
 //BA.debugLineNum = 287;BA.debugLine="MatDialogBuilder.NegativeText(\"\")";
mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence(""));
 };
 //BA.debugLineNum = 290;BA.debugLine="MatDialogBuilder.Initialize(\"EditNonOP\")";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"EditNonOP");
 //BA.debugLineNum = 291;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._bluecolor /*double*/ ));
 //BA.debugLineNum = 292;BA.debugLine="MatDialogBuilder.NegativeText(\"EDIT\").NegativeCol";
mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence("EDIT")).NegativeColor((int) (mostCurrent._globalvar._greencolor /*double*/ ));
 //BA.debugLineNum = 293;BA.debugLine="MatDialogBuilder.NeutralText(\"DELETE\").NeutralCol";
mostCurrent._matdialogbuilder.NeutralText(BA.ObjectToCharSequence("DELETE")).NeutralColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 294;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 295;BA.debugLine="MatDialogBuilder.Content($\"  Pump: ${csMSg} 	Loca";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(("  Pump: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_csmsg.getObject()))+"\n"+"	Location:	"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_splocation))+"\n"+"	Transaction Date: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sdate))+"\n"+"	\n"+"	Start Time:	"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_stimeoff))+"\n"+"	End Time:	"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_stimeon))+"\n"+"	Total Non Operating Hrs:	"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2(_itotophrs,(int) (1),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.True)))+" Hr(s).\n"+"	Reason:	"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sreason))+"\n"+"	Remarks:	"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_srem))+"")));
 //BA.debugLineNum = 304;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 305;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 306;BA.debugLine="MatDialogBuilder.Cancelable(True)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 307;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 308;BA.debugLine="End Sub";
return "";
}
public static String  _showwarning() throws Exception{
 //BA.debugLineNum = 375;BA.debugLine="Private Sub ShowWarning()";
 //BA.debugLineNum = 404;BA.debugLine="End Sub";
return "";
}
public static String  _success_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 408;BA.debugLine="Private Sub Success_OnPositiveClicked (View As Vie";
 //BA.debugLineNum = 412;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 129;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 130;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 124;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 125;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 126;BA.debugLine="kboard.HideKeyboard";
mostCurrent._kboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 127;BA.debugLine="End Sub";
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
