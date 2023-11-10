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

public class actnewproduction extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static actnewproduction mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.actnewproduction");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actnewproduction).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.actnewproduction");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.actnewproduction", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actnewproduction) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actnewproduction) Resume **");
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
		return actnewproduction.class;
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
            BA.LogInfo("** Activity (actnewproduction) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (actnewproduction) Pause event (activity is not paused). **");
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
            actnewproduction mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (actnewproduction) Resume **");
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
public bwsi.PumpOperations.wobblemenu _tabmenu = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _pnltime = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _pnlfmrdg = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _pnlpsirdg = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _pnlchlorinator = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _pnlconcerns = null;
public static long _thedate = 0L;
public b4a.example3.customlistview _clvtime = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblophrs = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lbltimeoff = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lbltimeon = null;
public de.amberhome.objects.FloatingActionButtonWrapper _btnaddtime = null;
public static long _lselectedrectimeon = 0L;
public static boolean _blnaddtime = false;
public b4a.example3.customlistview _clvfm = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblprescum = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblprevcum = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblrdgtime = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lbltotprod = null;
public de.amberhome.objects.FloatingActionButtonWrapper _btnaddfm = null;
public b4a.example3.customlistview _clvpsi = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblpsirdgtime = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblpsirdg = null;
public de.amberhome.objects.FloatingActionButtonWrapper _btnaddpsi = null;
public b4a.example3.customlistview _clvchlorine = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lbltimerep = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblchlorinetype = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblvolume = null;
public de.amberhome.objects.FloatingActionButtonWrapper _btnaddchlorine = null;
public static String _sunit = "";
public b4a.example3.customlistview _clvconcerns = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltimeenc = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblproblems = null;
public de.amberhome.objects.FloatingActionButtonWrapper _btnaddconcerns = null;
public static int _ilastreading = 0;
public bwsi.PumpOperations.bctoast _mytoast = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdreading = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdrem = null;
public com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fontbold = null;
public b4a.example.dateutils _dateutils = null;
public bwsi.PumpOperations.main _main = null;
public bwsi.PumpOperations.globalvar _globalvar = null;
public bwsi.PumpOperations.myfunctions _myfunctions = null;
public bwsi.PumpOperations.dbasefunctions _dbasefunctions = null;
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
public bwsi.PumpOperations.starter _starter = null;
public bwsi.PumpOperations.validation _validation = null;
public bwsi.PumpOperations.httputils2service _httputils2service = null;
public bwsi.PumpOperations.b4xcollections _b4xcollections = null;
public static class _timerecords{
public boolean IsInitialized;
public int ID;
public long lTimeOn;
public long lTimeOff;
public double iTotOpHrs;
public void Initialize() {
IsInitialized = true;
ID = 0;
lTimeOn = 0L;
lTimeOff = 0L;
iTotOpHrs = 0;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _fmrecords{
public boolean IsInitialized;
public int ID;
public String sRdgTime;
public int iPrevRdg;
public int iPresRdg;
public int iTotProd;
public int iBackFlow;
public void Initialize() {
IsInitialized = true;
ID = 0;
sRdgTime = "";
iPrevRdg = 0;
iPresRdg = 0;
iTotProd = 0;
iBackFlow = 0;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _psirecords{
public boolean IsInitialized;
public int ID;
public String sRdgTime;
public int iPrevRdg;
public int iPSIRdg;
public void Initialize() {
IsInitialized = true;
ID = 0;
sRdgTime = "";
iPrevRdg = 0;
iPSIRdg = 0;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _chlorinerecords{
public boolean IsInitialized;
public int ID;
public String sTimeRep;
public String sChlorineType;
public int iVolume;
public void Initialize() {
IsInitialized = true;
ID = 0;
sTimeRep = "";
sChlorineType = "";
iVolume = 0;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _concernsrecords{
public boolean IsInitialized;
public int ID;
public String sTimeEnc;
public String sProblem;
public void Initialize() {
IsInitialized = true;
ID = 0;
sTimeEnc = "";
sProblem = "";
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
 //BA.debugLineNum = 101;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 102;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 103;BA.debugLine="Activity.LoadLayout(\"Production\")";
mostCurrent._activity.LoadLayout("Production",mostCurrent.activityBA);
 //BA.debugLineNum = 105;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Append";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("PUMP - ")+mostCurrent._globalvar._pumphousecode /*String*/ )).PopAll();
 //BA.debugLineNum = 106;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append($";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 //BA.debugLineNum = 108;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 109;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 110;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 112;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 113;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 114;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 115;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 116;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 117;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 119;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 120;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 122;BA.debugLine="kboard.Initialize(\"KeyBoard\")";
mostCurrent._kboard.Initialize("KeyBoard");
 //BA.debugLineNum = 123;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 };
 //BA.debugLineNum = 126;BA.debugLine="TabMenu.SetTabTextIcon(1, \"ON/OFF\", Chr(0xF017),";
mostCurrent._tabmenu._settabtexticon /*String*/ ((int) (1),"ON/OFF",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf017))),(anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME())));
 //BA.debugLineNum = 127;BA.debugLine="TabMenu.SetTabTextIcon(2, \"FM RDG\", Chr(0xF0E4),";
mostCurrent._tabmenu._settabtexticon /*String*/ ((int) (2),"FM RDG",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf0e4))),(anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME())));
 //BA.debugLineNum = 128;BA.debugLine="TabMenu.SetTabTextIcon(3, \"PSI RDG\", Chr(0xF012),";
mostCurrent._tabmenu._settabtexticon /*String*/ ((int) (3),"PSI RDG",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf012))),(anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME())));
 //BA.debugLineNum = 129;BA.debugLine="TabMenu.SetTabTextIcon(4, \"CHLORINE\", Chr(0xF171)";
mostCurrent._tabmenu._settabtexticon /*String*/ ((int) (4),"CHLORINE",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf171))),(anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME())));
 //BA.debugLineNum = 130;BA.debugLine="TabMenu.SetTabTextIcon(5, \"CONCERNS\", Chr(0xF044)";
mostCurrent._tabmenu._settabtexticon /*String*/ ((int) (5),"CONCERNS",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf044))),(anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME())));
 //BA.debugLineNum = 132;BA.debugLine="TabMenu.SetCurrentTab(1)";
mostCurrent._tabmenu._setcurrenttab /*String*/ ((int) (1));
 //BA.debugLineNum = 133;BA.debugLine="MyToast.Initialize(Activity)";
mostCurrent._mytoast._initialize /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())));
 //BA.debugLineNum = 134;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
 //BA.debugLineNum = 161;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 167;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 136;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 137;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 138;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 139;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 141;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 143;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 155;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 145;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 146;BA.debugLine="GetPumpTimeRec(GlobalVar.TranDate, GlobalVar.Pump";
_getpumptimerec(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 147;BA.debugLine="GetFMRdgRec(GlobalVar.TranDate, GlobalVar.PumpHou";
_getfmrdgrec(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 148;BA.debugLine="GetPSIRdgRec(GlobalVar.TranDate, GlobalVar.PumpHo";
_getpsirdgrec(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 149;BA.debugLine="GetChlorineRec(GlobalVar.TranDate, GlobalVar.Pump";
_getchlorinerec(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 150;BA.debugLine="GetProblemsRec(GlobalVar.TranDate, GlobalVar.Pump";
_getproblemsrec(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 151;BA.debugLine="MyToast.Initialize(Activity)";
mostCurrent._mytoast._initialize /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())));
 //BA.debugLineNum = 153;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddchlorine_click() throws Exception{
 //BA.debugLineNum = 1058;BA.debugLine="Sub btnAddChlorine_Click";
 //BA.debugLineNum = 1059;BA.debugLine="GlobalVar.blnNewChlorine = True";
mostCurrent._globalvar._blnnewchlorine /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1060;BA.debugLine="StartActivity(AddEditChlorineRecord)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addeditchlorinerecord.getObject()));
 //BA.debugLineNum = 1061;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddconcerns_click() throws Exception{
 //BA.debugLineNum = 1067;BA.debugLine="Sub btnAddConcerns_Click";
 //BA.debugLineNum = 1068;BA.debugLine="GlobalVar.blnNewProblem = True";
mostCurrent._globalvar._blnnewproblem /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1069;BA.debugLine="StartActivity(AddEditProblem)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addeditproblem.getObject()));
 //BA.debugLineNum = 1070;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddfm_click() throws Exception{
 //BA.debugLineNum = 623;BA.debugLine="Sub btnAddFM_Click";
 //BA.debugLineNum = 624;BA.debugLine="GlobalVar.blnNewFMRdg = True";
mostCurrent._globalvar._blnnewfmrdg /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 625;BA.debugLine="StartActivity(AddEditFMRdg)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addeditfmrdg.getObject()));
 //BA.debugLineNum = 626;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddpsi_click() throws Exception{
 //BA.debugLineNum = 883;BA.debugLine="Sub btnAddPSI_Click";
 //BA.debugLineNum = 884;BA.debugLine="GlobalVar.blnNewPSIRdg = True";
mostCurrent._globalvar._blnnewpsirdg /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 885;BA.debugLine="StartActivity(AddEditPSIRdg)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addeditpsirdg.getObject()));
 //BA.debugLineNum = 886;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddtime_click() throws Exception{
 //BA.debugLineNum = 438;BA.debugLine="Sub btnAddTime_Click";
 //BA.debugLineNum = 439;BA.debugLine="GlobalVar.blnNewTime = True";
mostCurrent._globalvar._blnnewtime /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 440;BA.debugLine="If DBaseFunctions.GetPumpPowerStatus(GlobalVar.Pu";
if (mostCurrent._dbasefunctions._getpumppowerstatus /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ )==1) { 
 //BA.debugLineNum = 441;BA.debugLine="GlobalVar.TimeDetailID = DBaseFunctions.GetLastT";
mostCurrent._globalvar._timedetailid /*int*/  = mostCurrent._dbasefunctions._getlasttimeonid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 442;BA.debugLine="blnAddTime = True";
_blnaddtime = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 443;BA.debugLine="ConfirmPumpOff";
_confirmpumpoff();
 }else {
 //BA.debugLineNum = 445;BA.debugLine="blnAddTime = False";
_blnaddtime = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 446;BA.debugLine="StartActivity(AddEditTimeRecord)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addedittimerecord.getObject()));
 };
 //BA.debugLineNum = 448;BA.debugLine="End Sub";
return "";
}
public static String  _clvchlorine_itemclick(int _index,Object _value) throws Exception{
bwsi.PumpOperations.actnewproduction._chlorinerecords _rec = null;
 //BA.debugLineNum = 988;BA.debugLine="Sub clvChlorine_ItemClick (Index As Int, Value As";
 //BA.debugLineNum = 989;BA.debugLine="Dim Rec As ChlorineRecords = Value";
_rec = (bwsi.PumpOperations.actnewproduction._chlorinerecords)(_value);
 //BA.debugLineNum = 990;BA.debugLine="Log(Rec.ID)";
anywheresoftware.b4a.keywords.Common.LogImpl("89306114",BA.NumberToString(_rec.ID /*int*/ ),0);
 //BA.debugLineNum = 991;BA.debugLine="ShowChlorinatorRecDetails(Rec.ID)";
_showchlorinatorrecdetails(_rec.ID /*int*/ );
 //BA.debugLineNum = 992;BA.debugLine="GlobalVar.ChlorineDetailID = Rec.ID";
mostCurrent._globalvar._chlorinedetailid /*int*/  = _rec.ID /*int*/ ;
 //BA.debugLineNum = 993;BA.debugLine="End Sub";
return "";
}
public static String  _clvchlorine_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
int _extrasize = 0;
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
bwsi.PumpOperations.actnewproduction._chlorinerecords _cr = null;
 //BA.debugLineNum = 963;BA.debugLine="Sub clvChlorine_VisibleRangeChanged (FirstIndex As";
 //BA.debugLineNum = 964;BA.debugLine="Dim ExtraSize As Int = 15 'List size";
_extrasize = (int) (15);
 //BA.debugLineNum = 965;BA.debugLine="For i = Max(0, FirstIndex - ExtraSize) To Min(Las";
{
final int step2 = 1;
final int limit2 = (int) (anywheresoftware.b4a.keywords.Common.Min(_lastindex+_extrasize,mostCurrent._clvchlorine._getsize()-1));
_i = (int) (anywheresoftware.b4a.keywords.Common.Max(0,_firstindex-_extrasize)) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 966;BA.debugLine="Dim Pnl As B4XView = clvChlorine.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._clvchlorine._getpanel(_i);
 //BA.debugLineNum = 967;BA.debugLine="If i > FirstIndex - ExtraSize And i < LastIndex";
if (_i>_firstindex-_extrasize && _i<_lastindex+_extrasize) { 
 //BA.debugLineNum = 968;BA.debugLine="If Pnl.NumberOfViews = 0 Then 'Add each item/la";
if (_pnl.getNumberOfViews()==0) { 
 //BA.debugLineNum = 969;BA.debugLine="Dim CR As ChlorineRecords = clvChlorine.GetVal";
_cr = (bwsi.PumpOperations.actnewproduction._chlorinerecords)(mostCurrent._clvchlorine._getvalue(_i));
 //BA.debugLineNum = 970;BA.debugLine="Pnl.LoadLayout(\"ListChlorinatorRecords\")";
_pnl.LoadLayout("ListChlorinatorRecords",mostCurrent.activityBA);
 //BA.debugLineNum = 971;BA.debugLine="lblTimeRep.TextColor = GlobalVar.PriColor";
mostCurrent._lbltimerep.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 972;BA.debugLine="lblChlorineType.TextColor = GlobalVar.PriColor";
mostCurrent._lblchlorinetype.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 973;BA.debugLine="lblVolume.TextColor = GlobalVar.PriColor";
mostCurrent._lblvolume.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 975;BA.debugLine="lblTimeRep.Text = CR.sTimeRep";
mostCurrent._lbltimerep.setText(BA.ObjectToCharSequence(_cr.sTimeRep /*String*/ ));
 //BA.debugLineNum = 976;BA.debugLine="lblChlorineType.Text = CR.sChlorineType";
mostCurrent._lblchlorinetype.setText(BA.ObjectToCharSequence(_cr.sChlorineType /*String*/ ));
 //BA.debugLineNum = 977;BA.debugLine="lblVolume.Text = CR.iVolume & $\" \"$ & sUnit &";
mostCurrent._lblvolume.setText(BA.ObjectToCharSequence(BA.NumberToString(_cr.iVolume /*int*/ )+(" ")+mostCurrent._sunit+("(s)")));
 };
 }else {
 //BA.debugLineNum = 980;BA.debugLine="If Pnl.NumberOfViews > 0 Then";
if (_pnl.getNumberOfViews()>0) { 
 //BA.debugLineNum = 981;BA.debugLine="Pnl.RemoveAllViews 'Remove none visable item/l";
_pnl.RemoveAllViews();
 };
 };
 }
};
 //BA.debugLineNum = 986;BA.debugLine="End Sub";
return "";
}
public static String  _clvconcerns_itemclick(int _index,Object _value) throws Exception{
bwsi.PumpOperations.actnewproduction._concernsrecords _rec = null;
 //BA.debugLineNum = 1138;BA.debugLine="Sub clvConcerns_ItemClick (Index As Int, Value As";
 //BA.debugLineNum = 1139;BA.debugLine="Dim Rec As ConcernsRecords = Value";
_rec = (bwsi.PumpOperations.actnewproduction._concernsrecords)(_value);
 //BA.debugLineNum = 1140;BA.debugLine="Log(Rec.ID)";
anywheresoftware.b4a.keywords.Common.LogImpl("89764866",BA.NumberToString(_rec.ID /*int*/ ),0);
 //BA.debugLineNum = 1141;BA.debugLine="GlobalVar.ProblemDetailID = Rec.ID";
mostCurrent._globalvar._problemdetailid /*int*/  = _rec.ID /*int*/ ;
 //BA.debugLineNum = 1142;BA.debugLine="ShowProblemRecDetails(GlobalVar.ProblemDetailID)";
_showproblemrecdetails(mostCurrent._globalvar._problemdetailid /*int*/ );
 //BA.debugLineNum = 1143;BA.debugLine="End Sub";
return "";
}
public static String  _clvconcerns_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
int _extrasize = 0;
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
bwsi.PumpOperations.actnewproduction._concernsrecords _concernrec = null;
 //BA.debugLineNum = 1115;BA.debugLine="Sub clvConcerns_VisibleRangeChanged (FirstIndex As";
 //BA.debugLineNum = 1116;BA.debugLine="Dim ExtraSize As Int = 15 'List size";
_extrasize = (int) (15);
 //BA.debugLineNum = 1117;BA.debugLine="For i = Max(0, FirstIndex - ExtraSize) To Min(Las";
{
final int step2 = 1;
final int limit2 = (int) (anywheresoftware.b4a.keywords.Common.Min(_lastindex+_extrasize,mostCurrent._clvconcerns._getsize()-1));
_i = (int) (anywheresoftware.b4a.keywords.Common.Max(0,_firstindex-_extrasize)) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 1118;BA.debugLine="Dim Pnl As B4XView = clvConcerns.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._clvconcerns._getpanel(_i);
 //BA.debugLineNum = 1119;BA.debugLine="If i > FirstIndex - ExtraSize And i < LastIndex";
if (_i>_firstindex-_extrasize && _i<_lastindex+_extrasize) { 
 //BA.debugLineNum = 1120;BA.debugLine="If Pnl.NumberOfViews = 0 Then 'Add each item/la";
if (_pnl.getNumberOfViews()==0) { 
 //BA.debugLineNum = 1121;BA.debugLine="Dim ConcernRec As ConcernsRecords = clvConcern";
_concernrec = (bwsi.PumpOperations.actnewproduction._concernsrecords)(mostCurrent._clvconcerns._getvalue(_i));
 //BA.debugLineNum = 1122;BA.debugLine="Pnl.LoadLayout(\"ListProblemsRecords\")";
_pnl.LoadLayout("ListProblemsRecords",mostCurrent.activityBA);
 //BA.debugLineNum = 1123;BA.debugLine="lblTimeEnc.TextColor = GlobalVar.PriColor";
mostCurrent._lbltimeenc.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 1124;BA.debugLine="lblProblems.TextColor = GlobalVar.PriColor";
mostCurrent._lblproblems.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 1126;BA.debugLine="lblTimeEnc.Text = ConcernRec.sTimeEnc";
mostCurrent._lbltimeenc.setText(BA.ObjectToCharSequence(_concernrec.sTimeEnc /*String*/ ));
 //BA.debugLineNum = 1127;BA.debugLine="lblProblems.Text = ConcernRec.sProblem";
mostCurrent._lblproblems.setText(BA.ObjectToCharSequence(_concernrec.sProblem /*String*/ ));
 };
 }else {
 //BA.debugLineNum = 1130;BA.debugLine="If Pnl.NumberOfViews > 0 Then";
if (_pnl.getNumberOfViews()>0) { 
 //BA.debugLineNum = 1131;BA.debugLine="Pnl.RemoveAllViews 'Remove none visable item/l";
_pnl.RemoveAllViews();
 };
 };
 }
};
 //BA.debugLineNum = 1136;BA.debugLine="End Sub";
return "";
}
public static String  _clvfm_itemclick(int _index,Object _value) throws Exception{
bwsi.PumpOperations.actnewproduction._fmrecords _rec = null;
 //BA.debugLineNum = 628;BA.debugLine="Sub clvFM_ItemClick (Index As Int, Value As Object";
 //BA.debugLineNum = 629;BA.debugLine="Dim Rec As FMRecords = Value";
_rec = (bwsi.PumpOperations.actnewproduction._fmrecords)(_value);
 //BA.debugLineNum = 630;BA.debugLine="Log(Rec.ID)";
anywheresoftware.b4a.keywords.Common.LogImpl("88388610",BA.NumberToString(_rec.ID /*int*/ ),0);
 //BA.debugLineNum = 631;BA.debugLine="ShowFMRdgRecDetails(Rec.ID)";
_showfmrdgrecdetails(_rec.ID /*int*/ );
 //BA.debugLineNum = 632;BA.debugLine="End Sub";
return "";
}
public static String  _clvfm_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
int _extrasize = 0;
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
String _smin = "";
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
bwsi.PumpOperations.actnewproduction._fmrecords _fmrec = null;
 //BA.debugLineNum = 552;BA.debugLine="Sub clvFM_VisibleRangeChanged (FirstIndex As Int,";
 //BA.debugLineNum = 553;BA.debugLine="Dim ExtraSize As Int = 15 'List size";
_extrasize = (int) (15);
 //BA.debugLineNum = 555;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 556;BA.debugLine="Dim sMin As String";
_smin = "";
 //BA.debugLineNum = 559;BA.debugLine="For i = Max(0, FirstIndex - ExtraSize) To Min(Las";
{
final int step4 = 1;
final int limit4 = (int) (anywheresoftware.b4a.keywords.Common.Min(_lastindex+_extrasize,mostCurrent._clvfm._getsize()-1));
_i = (int) (anywheresoftware.b4a.keywords.Common.Max(0,_firstindex-_extrasize)) ;
for (;_i <= limit4 ;_i = _i + step4 ) {
 //BA.debugLineNum = 560;BA.debugLine="Dim Pnl As B4XView = clvFM.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._clvfm._getpanel(_i);
 //BA.debugLineNum = 561;BA.debugLine="If i > FirstIndex - ExtraSize And i < LastIndex";
if (_i>_firstindex-_extrasize && _i<_lastindex+_extrasize) { 
 //BA.debugLineNum = 562;BA.debugLine="If Pnl.NumberOfViews = 0 Then 'Add each item/la";
if (_pnl.getNumberOfViews()==0) { 
 //BA.debugLineNum = 563;BA.debugLine="Dim FMRec As FMRecords = clvFM.GetValue(i)";
_fmrec = (bwsi.PumpOperations.actnewproduction._fmrecords)(mostCurrent._clvfm._getvalue(_i));
 //BA.debugLineNum = 564;BA.debugLine="Pnl.LoadLayout(\"ListFMRdg\")";
_pnl.LoadLayout("ListFMRdg",mostCurrent.activityBA);
 //BA.debugLineNum = 565;BA.debugLine="If FMRec.iBackFlow >0 Then";
if (_fmrec.iBackFlow /*int*/ >0) { 
 //BA.debugLineNum = 566;BA.debugLine="lblRdgTime.TextColor = GlobalVar.RedColor";
mostCurrent._lblrdgtime.setTextColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 567;BA.debugLine="lblPrevCuM.TextColor = GlobalVar.RedColor";
mostCurrent._lblprevcum.setTextColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 568;BA.debugLine="lblPresCuM.TextColor = GlobalVar.RedColor";
mostCurrent._lblprescum.setTextColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 569;BA.debugLine="lblTotProd.TextColor = GlobalVar.RedColor";
mostCurrent._lbltotprod.setTextColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 }else {
 //BA.debugLineNum = 571;BA.debugLine="lblRdgTime.TextColor = GlobalVar.PriColor";
mostCurrent._lblrdgtime.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 572;BA.debugLine="lblPrevCuM.TextColor = GlobalVar.PriColor";
mostCurrent._lblprevcum.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 573;BA.debugLine="lblPresCuM.TextColor = GlobalVar.PriColor";
mostCurrent._lblprescum.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 574;BA.debugLine="lblTotProd.TextColor = GlobalVar.PriColor";
mostCurrent._lbltotprod.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 };
 //BA.debugLineNum = 609;BA.debugLine="lblRdgTime.Text = FMRec.sRdgTime";
mostCurrent._lblrdgtime.setText(BA.ObjectToCharSequence(_fmrec.sRdgTime /*String*/ ));
 //BA.debugLineNum = 610;BA.debugLine="lblPrevCuM.Text = FMRec.iPrevRdg";
mostCurrent._lblprevcum.setText(BA.ObjectToCharSequence(_fmrec.iPrevRdg /*int*/ ));
 //BA.debugLineNum = 611;BA.debugLine="lblPresCuM.Text = FMRec.iPresRdg";
mostCurrent._lblprescum.setText(BA.ObjectToCharSequence(_fmrec.iPresRdg /*int*/ ));
 //BA.debugLineNum = 612;BA.debugLine="lblTotProd.Text = NumberFormat(FMRec.iTotProd,";
mostCurrent._lbltotprod.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.NumberFormat(_fmrec.iTotProd /*int*/ ,(int) (0),(int) (0))+(" CuM")));
 };
 }else {
 //BA.debugLineNum = 615;BA.debugLine="If Pnl.NumberOfViews > 0 Then";
if (_pnl.getNumberOfViews()>0) { 
 //BA.debugLineNum = 616;BA.debugLine="Pnl.RemoveAllViews 'Remove none visable item/l";
_pnl.RemoveAllViews();
 };
 };
 }
};
 //BA.debugLineNum = 621;BA.debugLine="End Sub";
return "";
}
public static String  _clvpsi_itemclick(int _index,Object _value) throws Exception{
bwsi.PumpOperations.actnewproduction._psirecords _rec = null;
 //BA.debugLineNum = 812;BA.debugLine="Sub clvPSI_ItemClick (Index As Int, Value As Objec";
 //BA.debugLineNum = 813;BA.debugLine="Dim Rec As PSIRecords = Value";
_rec = (bwsi.PumpOperations.actnewproduction._psirecords)(_value);
 //BA.debugLineNum = 814;BA.debugLine="Log(Rec.ID)";
anywheresoftware.b4a.keywords.Common.LogImpl("88847362",BA.NumberToString(_rec.ID /*int*/ ),0);
 //BA.debugLineNum = 815;BA.debugLine="ShowPSIRdgRecDetails(Rec.ID)";
_showpsirdgrecdetails(_rec.ID /*int*/ );
 //BA.debugLineNum = 816;BA.debugLine="GlobalVar.PSIRdgDetailID = Rec.ID";
mostCurrent._globalvar._psirdgdetailid /*int*/  = _rec.ID /*int*/ ;
 //BA.debugLineNum = 817;BA.debugLine="End Sub";
return "";
}
public static String  _clvpsi_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
int _extrasize = 0;
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
bwsi.PumpOperations.actnewproduction._psirecords _psirec = null;
 //BA.debugLineNum = 789;BA.debugLine="Sub clvPSI_VisibleRangeChanged (FirstIndex As Int,";
 //BA.debugLineNum = 790;BA.debugLine="Dim ExtraSize As Int = 15 'List size";
_extrasize = (int) (15);
 //BA.debugLineNum = 791;BA.debugLine="For i = Max(0, FirstIndex - ExtraSize) To Min(Las";
{
final int step2 = 1;
final int limit2 = (int) (anywheresoftware.b4a.keywords.Common.Min(_lastindex+_extrasize,mostCurrent._clvpsi._getsize()-1));
_i = (int) (anywheresoftware.b4a.keywords.Common.Max(0,_firstindex-_extrasize)) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 792;BA.debugLine="Dim Pnl As B4XView = clvPSI.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._clvpsi._getpanel(_i);
 //BA.debugLineNum = 793;BA.debugLine="If i > FirstIndex - ExtraSize And i < LastIndex";
if (_i>_firstindex-_extrasize && _i<_lastindex+_extrasize) { 
 //BA.debugLineNum = 794;BA.debugLine="If Pnl.NumberOfViews = 0 Then 'Add each item/la";
if (_pnl.getNumberOfViews()==0) { 
 //BA.debugLineNum = 795;BA.debugLine="Dim PSIRec As PSIRecords = clvPSI.GetValue(i)";
_psirec = (bwsi.PumpOperations.actnewproduction._psirecords)(mostCurrent._clvpsi._getvalue(_i));
 //BA.debugLineNum = 796;BA.debugLine="Pnl.LoadLayout(\"ListPSIRecords\")";
_pnl.LoadLayout("ListPSIRecords",mostCurrent.activityBA);
 //BA.debugLineNum = 797;BA.debugLine="lblPSIRdgTime.TextColor = GlobalVar.PriColor";
mostCurrent._lblpsirdgtime.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 798;BA.debugLine="lblPSIRdg.TextColor = GlobalVar.PriColor";
mostCurrent._lblpsirdg.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 800;BA.debugLine="lblPSIRdgTime.Text = PSIRec.sRdgTime";
mostCurrent._lblpsirdgtime.setText(BA.ObjectToCharSequence(_psirec.sRdgTime /*String*/ ));
 //BA.debugLineNum = 801;BA.debugLine="lblPSIRdg.Text = NumberFormat(PSIRec.iPSIRdg,0";
mostCurrent._lblpsirdg.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.NumberFormat(_psirec.iPSIRdg /*int*/ ,(int) (0),(int) (2))+(" PSI")));
 };
 }else {
 //BA.debugLineNum = 804;BA.debugLine="If Pnl.NumberOfViews > 0 Then";
if (_pnl.getNumberOfViews()>0) { 
 //BA.debugLineNum = 805;BA.debugLine="Pnl.RemoveAllViews 'Remove none visable item/l";
_pnl.RemoveAllViews();
 };
 };
 }
};
 //BA.debugLineNum = 810;BA.debugLine="End Sub";
return "";
}
public static String  _clvtime_itemclick(int _index,Object _value) throws Exception{
bwsi.PumpOperations.actnewproduction._timerecords _rec = null;
 //BA.debugLineNum = 337;BA.debugLine="Sub clvTime_ItemClick (Index As Int, Value As Obje";
 //BA.debugLineNum = 338;BA.debugLine="Dim Rec As TimeRecords = Value";
_rec = (bwsi.PumpOperations.actnewproduction._timerecords)(_value);
 //BA.debugLineNum = 339;BA.debugLine="Log(Rec.ID)";
anywheresoftware.b4a.keywords.Common.LogImpl("87733250",BA.NumberToString(_rec.ID /*int*/ ),0);
 //BA.debugLineNum = 342;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 343;BA.debugLine="GlobalVar.TimeDetailID = Rec.ID";
mostCurrent._globalvar._timedetailid /*int*/  = _rec.ID /*int*/ ;
 //BA.debugLineNum = 344;BA.debugLine="GlobalVar.SelectedPumpTime = Rec.lTimeOn";
mostCurrent._globalvar._selectedpumptime /*long*/  = _rec.lTimeOn /*long*/ ;
 //BA.debugLineNum = 346;BA.debugLine="If Rec.lTimeOff = 0 Then";
if (_rec.lTimeOff /*long*/ ==0) { 
 //BA.debugLineNum = 347;BA.debugLine="blnAddTime = False";
_blnaddtime = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 348;BA.debugLine="ConfirmPumpOff";
_confirmpumpoff();
 }else {
 //BA.debugLineNum = 350;BA.debugLine="ShowPumpTimeRecDetails(GlobalVar.TimeDetailID)";
_showpumptimerecdetails(mostCurrent._globalvar._timedetailid /*int*/ );
 };
 //BA.debugLineNum = 352;BA.debugLine="End Sub";
return "";
}
public static String  _clvtime_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
int _extrasize = 0;
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
bwsi.PumpOperations.actnewproduction._timerecords _tr = null;
 //BA.debugLineNum = 305;BA.debugLine="Sub clvTime_VisibleRangeChanged (FirstIndex As Int";
 //BA.debugLineNum = 306;BA.debugLine="Dim ExtraSize As Int = 15 'List size";
_extrasize = (int) (15);
 //BA.debugLineNum = 307;BA.debugLine="For i = Max(0, FirstIndex - ExtraSize) To Min(Las";
{
final int step2 = 1;
final int limit2 = (int) (anywheresoftware.b4a.keywords.Common.Min(_lastindex+_extrasize,mostCurrent._clvtime._getsize()-1));
_i = (int) (anywheresoftware.b4a.keywords.Common.Max(0,_firstindex-_extrasize)) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 308;BA.debugLine="Dim Pnl As B4XView = clvTime.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._clvtime._getpanel(_i);
 //BA.debugLineNum = 309;BA.debugLine="If i > FirstIndex - ExtraSize And i < LastIndex";
if (_i>_firstindex-_extrasize && _i<_lastindex+_extrasize) { 
 //BA.debugLineNum = 310;BA.debugLine="If Pnl.NumberOfViews = 0 Then 'Add each item/la";
if (_pnl.getNumberOfViews()==0) { 
 //BA.debugLineNum = 311;BA.debugLine="Dim TR As TimeRecords = clvTime.GetValue(i)";
_tr = (bwsi.PumpOperations.actnewproduction._timerecords)(mostCurrent._clvtime._getvalue(_i));
 //BA.debugLineNum = 312;BA.debugLine="Pnl.LoadLayout(\"ListPumpTimeRecords\")";
_pnl.LoadLayout("ListPumpTimeRecords",mostCurrent.activityBA);
 //BA.debugLineNum = 313;BA.debugLine="lblTimeOn.TextColor = GlobalVar.PriColor";
mostCurrent._lbltimeon.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 314;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 315;BA.debugLine="lblTimeOn.Text = DateTime.Time(TR.lTimeOn)";
mostCurrent._lbltimeon.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(_tr.lTimeOn /*long*/ )));
 //BA.debugLineNum = 316;BA.debugLine="If TR.lTimeOff = 0 Then";
if (_tr.lTimeOff /*long*/ ==0) { 
 //BA.debugLineNum = 317;BA.debugLine="lblTimeOff.TextColor = GlobalVar.NegColor";
mostCurrent._lbltimeoff.setTextColor((int) (mostCurrent._globalvar._negcolor /*double*/ ));
 //BA.debugLineNum = 318;BA.debugLine="lblOpHrs.TextColor = GlobalVar.NegColor";
mostCurrent._lblophrs.setTextColor((int) (mostCurrent._globalvar._negcolor /*double*/ ));
 //BA.debugLineNum = 319;BA.debugLine="lblTimeOff.Text = \"Running...\"";
mostCurrent._lbltimeoff.setText(BA.ObjectToCharSequence("Running..."));
 //BA.debugLineNum = 320;BA.debugLine="lblOpHrs.Text = \"---\"";
mostCurrent._lblophrs.setText(BA.ObjectToCharSequence("---"));
 }else {
 //BA.debugLineNum = 322;BA.debugLine="lblTimeOff.TextColor = GlobalVar.PriColor";
mostCurrent._lbltimeoff.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 323;BA.debugLine="lblOpHrs.TextColor = GlobalVar.PriColor";
mostCurrent._lblophrs.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 324;BA.debugLine="lblTimeOff.Text = DateTime.Time(TR.lTimeOff)";
mostCurrent._lbltimeoff.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Time(_tr.lTimeOff /*long*/ )));
 //BA.debugLineNum = 325;BA.debugLine="lblOpHrs.Text = NumberFormat2(TR.iTotOpHrs,1,";
mostCurrent._lblophrs.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.NumberFormat2(_tr.iTotOpHrs /*double*/ ,(int) (1),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.True)+(" Hr(s).")));
 };
 };
 }else {
 //BA.debugLineNum = 329;BA.debugLine="If Pnl.NumberOfViews > 0 Then";
if (_pnl.getNumberOfViews()>0) { 
 //BA.debugLineNum = 330;BA.debugLine="Pnl.RemoveAllViews 'Remove none visable item/l";
_pnl.RemoveAllViews();
 };
 };
 }
};
 //BA.debugLineNum = 335;BA.debugLine="End Sub";
return "";
}
public static String  _confirmpumpoff() throws Exception{
String _stitle = "";
String _scontent = "";
 //BA.debugLineNum = 450;BA.debugLine="Private Sub ConfirmPumpOff";
 //BA.debugLineNum = 451;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 453;BA.debugLine="Dim sTitle, sContent As String";
_stitle = "";
_scontent = "";
 //BA.debugLineNum = 455;BA.debugLine="If blnAddTime = True Then";
if (_blnaddtime==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 456;BA.debugLine="sTitle = $\"CONFIRM PUMP OFF TIME\"$";
_stitle = ("CONFIRM PUMP OFF TIME");
 //BA.debugLineNum = 457;BA.debugLine="sContent = $\"Cannot add new Time record due to P";
_scontent = ("Cannot add new Time record due to Pump is currently running...")+anywheresoftware.b4a.keywords.Common.CRLF+("Do you want to Record the Pump Off Time Now?");
 }else {
 //BA.debugLineNum = 459;BA.debugLine="sTitle = $\"CONFIRM PUMP OFF TIME\"$";
_stitle = ("CONFIRM PUMP OFF TIME");
 //BA.debugLineNum = 460;BA.debugLine="sContent = $\"Do you want to Record the Pump Off";
_scontent = ("Do you want to Record the Pump Off Time Now?");
 };
 //BA.debugLineNum = 463;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetMessage(_scontent).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"PumpOff").SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetOnNegativeClicked(mostCurrent.activityBA,"PumpOff");
 //BA.debugLineNum = 480;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 481;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 482;BA.debugLine="End Sub";
return "";
}
public static String  _editchlorinetime_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 1047;BA.debugLine="Private Sub EditChlorineTime_ButtonPressed(mDialog";
 //BA.debugLineNum = 1048;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE,_mdialog.ACTION_NEUTRAL)) {
case 0: {
 break; }
case 1: {
 //BA.debugLineNum = 1051;BA.debugLine="GlobalVar.blnNewChlorine = False";
mostCurrent._globalvar._blnnewchlorine /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1052;BA.debugLine="StartActivity(AddEditChlorineRecord)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addeditchlorinerecord.getObject()));
 break; }
case 2: {
 break; }
}
;
 //BA.debugLineNum = 1056;BA.debugLine="End Sub";
return "";
}
public static String  _editfmrdg_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 702;BA.debugLine="Private Sub EditFMRdg_ButtonPressed(mDialog As Mat";
 //BA.debugLineNum = 703;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE,_mdialog.ACTION_NEUTRAL)) {
case 0: {
 break; }
case 1: {
 //BA.debugLineNum = 706;BA.debugLine="GlobalVar.blnNewFMRdg = False";
mostCurrent._globalvar._blnnewfmrdg /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 707;BA.debugLine="StartActivity(AddEditFMRdg)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addeditfmrdg.getObject()));
 break; }
case 2: {
 break; }
}
;
 //BA.debugLineNum = 711;BA.debugLine="End Sub";
return "";
}
public static String  _editproblemrecord_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 1225;BA.debugLine="Private Sub EditProblemRecord_ButtonPressed(mDialo";
 //BA.debugLineNum = 1226;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_NEGATIVE)) {
case 0: {
 //BA.debugLineNum = 1228;BA.debugLine="GlobalVar.blnNewProblem = False";
mostCurrent._globalvar._blnnewproblem /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1229;BA.debugLine="StartActivity(AddEditProblem)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addeditproblem.getObject()));
 break; }
}
;
 //BA.debugLineNum = 1231;BA.debugLine="End Sub";
return "";
}
public static String  _editpsirdg_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 875;BA.debugLine="Private Sub EditPSIRdg_ButtonPressed(mDialog As Ma";
 //BA.debugLineNum = 876;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_NEGATIVE)) {
case 0: {
 //BA.debugLineNum = 878;BA.debugLine="GlobalVar.blnNewPSIRdg = False";
mostCurrent._globalvar._blnnewpsirdg /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 879;BA.debugLine="StartActivity(AddEditPSIRdg)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addeditpsirdg.getObject()));
 break; }
}
;
 //BA.debugLineNum = 881;BA.debugLine="End Sub";
return "";
}
public static String  _edittime_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 425;BA.debugLine="Private Sub EditTime_ButtonPressed(mDialog As Mate";
 //BA.debugLineNum = 426;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE,_mdialog.ACTION_NEUTRAL)) {
case 0: {
 break; }
case 1: {
 //BA.debugLineNum = 429;BA.debugLine="GlobalVar.blnNewTime = False";
mostCurrent._globalvar._blnnewtime /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 430;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeader";
anywheresoftware.b4a.keywords.Common.LogImpl("87864325",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 431;BA.debugLine="LogColor($\"Detail ID: \"$ & GlobalVar.TimeDetail";
anywheresoftware.b4a.keywords.Common.LogImpl("87864326",("Detail ID: ")+BA.NumberToString(mostCurrent._globalvar._timedetailid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 432;BA.debugLine="StartActivity(EditTimeRecord)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._edittimerecord.getObject()));
 break; }
case 2: {
 break; }
}
;
 //BA.debugLineNum = 436;BA.debugLine="End Sub";
return "";
}
public static void  _getchlorinerec(String _strandate,int _ipumpid) throws Exception{
ResumableSub_GetChlorineRec rsub = new ResumableSub_GetChlorineRec(null,_strandate,_ipumpid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetChlorineRec extends BA.ResumableSub {
public ResumableSub_GetChlorineRec(bwsi.PumpOperations.actnewproduction parent,String _strandate,int _ipumpid) {
this.parent = parent;
this._strandate = _strandate;
this._ipumpid = _ipumpid;
}
bwsi.PumpOperations.actnewproduction parent;
String _strandate;
int _ipumpid;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actnewproduction._chlorinerecords _cr = null;
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
 //BA.debugLineNum = 916;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 917;BA.debugLine="clvChlorine.Clear";
parent.mostCurrent._clvchlorine._clear();
 //BA.debugLineNum = 918;BA.debugLine="Try";
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
 //BA.debugLineNum = 919;BA.debugLine="Starter.strCriteria = \"SELECT Details.DetailID,";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Details.DetailID, Details.TimeReplenished, Details.ChlorineType, Details.Volume, Details.UoM "+"FROM ChlorineDetails AS Details "+"INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID "+"WHERE Header.PumpID = "+BA.NumberToString(_ipumpid)+" "+"AND Header.TranDate = '"+_strandate+"' "+"ORDER BY Details.DetailID, TimeReplenished ASC";
 //BA.debugLineNum = 926;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 927;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 17;
return;
case 17:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 929;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 930;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 931;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 932;BA.debugLine="Dim CR As ChlorineRecords";
_cr = new bwsi.PumpOperations.actnewproduction._chlorinerecords();
 //BA.debugLineNum = 933;BA.debugLine="CR.Initialize";
_cr.Initialize();
 //BA.debugLineNum = 934;BA.debugLine="CR.ID = RS.GetInt(\"DetailID\")";
_cr.ID /*int*/  = _rs.GetInt("DetailID");
 //BA.debugLineNum = 935;BA.debugLine="CR.sTimeRep = RS.GetString(\"TimeReplenished\")";
_cr.sTimeRep /*String*/  = _rs.GetString("TimeReplenished");
 //BA.debugLineNum = 936;BA.debugLine="CR.sChlorineType = RS.GetString(\"ChlorineType\"";
_cr.sChlorineType /*String*/  = _rs.GetString("ChlorineType");
 //BA.debugLineNum = 937;BA.debugLine="CR.iVolume = RS.GetInt(\"Volume\")";
_cr.iVolume /*int*/  = _rs.GetInt("Volume");
 //BA.debugLineNum = 938;BA.debugLine="sUnit = RS.GetString(\"UoM\")";
parent.mostCurrent._sunit = _rs.GetString("UoM");
 //BA.debugLineNum = 940;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 941;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0, clvChlorine";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (parent.mostCurrent._clvchlorine._asview().getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 942;BA.debugLine="clvChlorine.Add(Pnl, CR)";
parent.mostCurrent._clvchlorine._add(_pnl,(Object)(_cr));
 if (true) break;

case 10:
//C
this.state = 13;
;
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 945;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 946;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 947;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 948;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 949;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("89175074",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 13:
//C
this.state = 16;
;
 //BA.debugLineNum = 952;BA.debugLine="Log($\"List of Time Records = ${NumberFormat2((Da";
anywheresoftware.b4a.keywords.Common.LogImpl("89175077",("List of Time Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvchlorine._getsize()))+" Chlorine Records"),0);
 if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
 //BA.debugLineNum = 955;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 956;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 957;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 958;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 959;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("89175084",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 961;BA.debugLine="End Sub";
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
public static void  _getfmrdgrec(String _strandate,int _ipumpid) throws Exception{
ResumableSub_GetFMRdgRec rsub = new ResumableSub_GetFMRdgRec(null,_strandate,_ipumpid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetFMRdgRec extends BA.ResumableSub {
public ResumableSub_GetFMRdgRec(bwsi.PumpOperations.actnewproduction parent,String _strandate,int _ipumpid) {
this.parent = parent;
this._strandate = _strandate;
this._ipumpid = _ipumpid;
}
bwsi.PumpOperations.actnewproduction parent;
String _strandate;
int _ipumpid;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actnewproduction._fmrecords _fmrec = null;
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
 //BA.debugLineNum = 503;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 504;BA.debugLine="clvFM.Clear";
parent.mostCurrent._clvfm._clear();
 //BA.debugLineNum = 505;BA.debugLine="Try";
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
 //BA.debugLineNum = 506;BA.debugLine="Starter.strCriteria = \"SELECT Details.DetailID,";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Details.DetailID, Details.RdgTime, "+"Details.PrevRdg, Details.PresRdg, Details.PresCum, Details.BackFlowCum "+"FROM ProductionDetails AS Details "+"INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID "+"WHERE Header.PumpID = "+BA.NumberToString(_ipumpid)+" "+"AND Header.TranDate = '"+_strandate+"' "+"ORDER BY Details.DetailID, Details.RdgTime ASC";
 //BA.debugLineNum = 514;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 515;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 17;
return;
case 17:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 517;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 518;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 519;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 520;BA.debugLine="Dim FMRec As FMRecords";
_fmrec = new bwsi.PumpOperations.actnewproduction._fmrecords();
 //BA.debugLineNum = 521;BA.debugLine="FMRec.Initialize";
_fmrec.Initialize();
 //BA.debugLineNum = 522;BA.debugLine="FMRec.ID = RS.GetInt(\"DetailID\")";
_fmrec.ID /*int*/  = _rs.GetInt("DetailID");
 //BA.debugLineNum = 523;BA.debugLine="FMRec.sRdgTime = RS.GetString(\"RdgTime\")";
_fmrec.sRdgTime /*String*/  = _rs.GetString("RdgTime");
 //BA.debugLineNum = 524;BA.debugLine="FMRec.iPrevRdg = RS.GetInt(\"PrevRdg\")";
_fmrec.iPrevRdg /*int*/  = _rs.GetInt("PrevRdg");
 //BA.debugLineNum = 525;BA.debugLine="FMRec.iPresRdg = RS.GetInt(\"PresRdg\")";
_fmrec.iPresRdg /*int*/  = _rs.GetInt("PresRdg");
 //BA.debugLineNum = 526;BA.debugLine="FMRec.iTotProd = RS.GetInt(\"PresCum\")";
_fmrec.iTotProd /*int*/  = _rs.GetInt("PresCum");
 //BA.debugLineNum = 527;BA.debugLine="FMRec.iBackFlow = RS.GetInt(\"BackFlowCum\")";
_fmrec.iBackFlow /*int*/  = _rs.GetInt("BackFlowCum");
 //BA.debugLineNum = 529;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 530;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0, clvFM.AsVie";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (parent.mostCurrent._clvfm._asview().getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 531;BA.debugLine="clvFM.Add(Pnl, FMRec)";
parent.mostCurrent._clvfm._add(_pnl,(Object)(_fmrec));
 if (true) break;

case 10:
//C
this.state = 13;
;
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 534;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 535;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 536;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 537;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 538;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("88192036",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 13:
//C
this.state = 16;
;
 //BA.debugLineNum = 541;BA.debugLine="Log($\"List of FM Reading Records = ${NumberForma";
anywheresoftware.b4a.keywords.Common.LogImpl("88192039",("List of FM Reading Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvfm._getsize()))+" Flow Meter Reading Records"),0);
 if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
 //BA.debugLineNum = 544;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 545;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 546;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 547;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 548;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("88192046",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 550;BA.debugLine="End Sub";
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
public static void  _getproblemsrec(String _strandate,int _ipumpid) throws Exception{
ResumableSub_GetProblemsRec rsub = new ResumableSub_GetProblemsRec(null,_strandate,_ipumpid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetProblemsRec extends BA.ResumableSub {
public ResumableSub_GetProblemsRec(bwsi.PumpOperations.actnewproduction parent,String _strandate,int _ipumpid) {
this.parent = parent;
this._strandate = _strandate;
this._ipumpid = _ipumpid;
}
bwsi.PumpOperations.actnewproduction parent;
String _strandate;
int _ipumpid;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actnewproduction._concernsrecords _concernrec = null;
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
 //BA.debugLineNum = 1073;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 1074;BA.debugLine="clvConcerns.Clear";
parent.mostCurrent._clvconcerns._clear();
 //BA.debugLineNum = 1075;BA.debugLine="Try";
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
 //BA.debugLineNum = 1076;BA.debugLine="Starter.strCriteria = \"SELECT Problem.DetailID,";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Problem.DetailID, Problem.TimeStart, Problem.ProblemTitle "+"FROM ProblemDetails AS Problem "+"INNER JOIN TranHeader AS Header ON Problem.HeaderID = Header.HeaderID "+"WHERE Header.PumpID = "+BA.NumberToString(_ipumpid)+" "+"AND Header.TranDate = '"+_strandate+"' "+"ORDER BY Problem.DetailID, Problem.TimeStart ASC";
 //BA.debugLineNum = 1083;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 1084;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 17;
return;
case 17:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 1086;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 1087;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 1088;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 1089;BA.debugLine="Dim ConcernRec As ConcernsRecords";
_concernrec = new bwsi.PumpOperations.actnewproduction._concernsrecords();
 //BA.debugLineNum = 1090;BA.debugLine="ConcernRec.Initialize";
_concernrec.Initialize();
 //BA.debugLineNum = 1091;BA.debugLine="ConcernRec.ID = RS.GetInt(\"DetailID\")";
_concernrec.ID /*int*/  = _rs.GetInt("DetailID");
 //BA.debugLineNum = 1092;BA.debugLine="ConcernRec.sTimeEnc = RS.GetString(\"TimeStart\"";
_concernrec.sTimeEnc /*String*/  = _rs.GetString("TimeStart");
 //BA.debugLineNum = 1093;BA.debugLine="ConcernRec.sProblem= RS.GetString(\"ProblemTitl";
_concernrec.sProblem /*String*/  = _rs.GetString("ProblemTitle");
 //BA.debugLineNum = 1095;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 1096;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0, clvConcerns";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (parent.mostCurrent._clvconcerns._asview().getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 1097;BA.debugLine="clvConcerns.Add(Pnl, ConcernRec)";
parent.mostCurrent._clvconcerns._add(_pnl,(Object)(_concernrec));
 if (true) break;

case 10:
//C
this.state = 13;
;
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 1100;BA.debugLine="ToastMessageShow($\"Unable to fetch Problem Enco";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Problem Encountered Record due to \"")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1101;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("89633821",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 13:
//C
this.state = 16;
;
 //BA.debugLineNum = 1104;BA.debugLine="Log($\"List of Problem Encountered Records = ${Nu";
anywheresoftware.b4a.keywords.Common.LogImpl("89633824",("List of Problem Encountered Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvconcerns._getsize()))+" PSI Reading Records"),0);
 if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
 //BA.debugLineNum = 1107;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 1108;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1109;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 1110;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 1111;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("89633831",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 1113;BA.debugLine="End Sub";
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
public static void  _getpsirdgrec(String _strandate,int _ipumpid) throws Exception{
ResumableSub_GetPSIRdgRec rsub = new ResumableSub_GetPSIRdgRec(null,_strandate,_ipumpid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetPSIRdgRec extends BA.ResumableSub {
public ResumableSub_GetPSIRdgRec(bwsi.PumpOperations.actnewproduction parent,String _strandate,int _ipumpid) {
this.parent = parent;
this._strandate = _strandate;
this._ipumpid = _ipumpid;
}
bwsi.PumpOperations.actnewproduction parent;
String _strandate;
int _ipumpid;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actnewproduction._psirecords _psirec = null;
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
 //BA.debugLineNum = 744;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 745;BA.debugLine="clvPSI.Clear";
parent.mostCurrent._clvpsi._clear();
 //BA.debugLineNum = 746;BA.debugLine="Try";
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
 //BA.debugLineNum = 747;BA.debugLine="Starter.strCriteria = \"SELECT Details.DetailID,";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Details.DetailID, Details.RdgTime, Details.PSIReading "+"FROM PressureRdgDetails AS Details "+"INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID "+"WHERE Header.PumpID = "+BA.NumberToString(_ipumpid)+" "+"AND Header.TranDate = '"+_strandate+"' "+"ORDER BY Details.DetailID, Details.RdgTime ASC";
 //BA.debugLineNum = 754;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 755;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 17;
return;
case 17:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 757;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 758;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 759;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 760;BA.debugLine="Dim PSIRec As PSIRecords";
_psirec = new bwsi.PumpOperations.actnewproduction._psirecords();
 //BA.debugLineNum = 761;BA.debugLine="PSIRec.Initialize";
_psirec.Initialize();
 //BA.debugLineNum = 762;BA.debugLine="PSIRec.ID = RS.GetInt(\"DetailID\")";
_psirec.ID /*int*/  = _rs.GetInt("DetailID");
 //BA.debugLineNum = 763;BA.debugLine="PSIRec.sRdgTime = RS.GetString(\"RdgTime\")";
_psirec.sRdgTime /*String*/  = _rs.GetString("RdgTime");
 //BA.debugLineNum = 764;BA.debugLine="PSIRec.iPSIRdg = RS.GetInt(\"PSIReading\")";
_psirec.iPSIRdg /*int*/  = _rs.GetInt("PSIReading");
 //BA.debugLineNum = 766;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 767;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0, clvPSI.AsVi";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (parent.mostCurrent._clvpsi._asview().getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 768;BA.debugLine="clvPSI.Add(Pnl, PSIRec)";
parent.mostCurrent._clvpsi._add(_pnl,(Object)(_psirec));
 if (true) break;

case 10:
//C
this.state = 13;
;
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 771;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 772;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 773;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 774;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 775;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("88716320",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 13:
//C
this.state = 16;
;
 //BA.debugLineNum = 778;BA.debugLine="Log($\"List of PSI Reading Records = ${NumberForm";
anywheresoftware.b4a.keywords.Common.LogImpl("88716323",("List of PSI Reading Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvpsi._getsize()))+" PSI Reading Records"),0);
 if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
 //BA.debugLineNum = 781;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 782;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 783;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 784;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 785;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("88716330",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 787;BA.debugLine="End Sub";
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
public static void  _getpumptimerec(String _strandate,int _ipumpid) throws Exception{
ResumableSub_GetPumpTimeRec rsub = new ResumableSub_GetPumpTimeRec(null,_strandate,_ipumpid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetPumpTimeRec extends BA.ResumableSub {
public ResumableSub_GetPumpTimeRec(bwsi.PumpOperations.actnewproduction parent,String _strandate,int _ipumpid) {
this.parent = parent;
this._strandate = _strandate;
this._ipumpid = _ipumpid;
}
bwsi.PumpOperations.actnewproduction parent;
String _strandate;
int _ipumpid;
Object _senderfilter = null;
String _stimeon = "";
String _stimeoff = "";
int _ipowerstatus = 0;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actnewproduction._timerecords _tr = null;
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
 //BA.debugLineNum = 236;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 237;BA.debugLine="Dim sTimeON, sTimeOFF As String";
_stimeon = "";
_stimeoff = "";
 //BA.debugLineNum = 238;BA.debugLine="Dim iPowerStatus As Int";
_ipowerstatus = 0;
 //BA.debugLineNum = 240;BA.debugLine="clvTime.Clear";
parent.mostCurrent._clvtime._clear();
 //BA.debugLineNum = 242;BA.debugLine="If DBaseFunctions.GetPumpPowerStatus(iPumpID) = 0";
if (true) break;

case 1:
//if
this.state = 6;
if (parent.mostCurrent._dbasefunctions._getpumppowerstatus /*int*/ (mostCurrent.activityBA,_ipumpid)==0) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 243;BA.debugLine="iPowerStatus = 0";
_ipowerstatus = (int) (0);
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 245;BA.debugLine="iPowerStatus = 1";
_ipowerstatus = (int) (1);
 if (true) break;
;
 //BA.debugLineNum = 248;BA.debugLine="Try";

case 6:
//try
this.state = 27;
this.catchState = 26;
this.state = 8;
if (true) break;

case 8:
//C
this.state = 9;
this.catchState = 26;
 //BA.debugLineNum = 249;BA.debugLine="Starter.strCriteria = \"SELECT Header.HeaderID, H";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Header.HeaderID, Header.PumpID, Details.DetailID, Details.PumpOnTime AS TimeOn, Details.PumpOffTime AS TimeOff, Details.TotOpHrs "+"FROM TranHeader AS Header "+"INNER JOIN OnOffDetails AS Details ON Header.HeaderID = Details.HeaderID "+"WHERE Header.PumpID = "+BA.NumberToString(_ipumpid)+" "+"AND Header.TranDate = '"+_strandate+"' "+"ORDER BY Details.DetailID, TimeOn ASC";
 //BA.debugLineNum = 256;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 257;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 28;
return;
case 28:
//C
this.state = 9;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 259;BA.debugLine="If Success Then";
if (true) break;

case 9:
//if
this.state = 24;
if (_success) { 
this.state = 11;
}else {
this.state = 23;
}if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 260;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 261;BA.debugLine="Do While RS.NextRow";
if (true) break;

case 12:
//do while
this.state = 21;
while (_rs.NextRow()) {
this.state = 14;
if (true) break;
}
if (true) break;

case 14:
//C
this.state = 15;
 //BA.debugLineNum = 262;BA.debugLine="Dim TR As TimeRecords";
_tr = new bwsi.PumpOperations.actnewproduction._timerecords();
 //BA.debugLineNum = 263;BA.debugLine="TR.Initialize";
_tr.Initialize();
 //BA.debugLineNum = 264;BA.debugLine="TR.ID = RS.GetInt(\"DetailID\")";
_tr.ID /*int*/  = _rs.GetInt("DetailID");
 //BA.debugLineNum = 266;BA.debugLine="sTimeON = RS.GetString(\"TimeOn\")";
_stimeon = _rs.GetString("TimeOn");
 //BA.debugLineNum = 267;BA.debugLine="sTimeOFF = RS.GetString(\"TimeOff\")";
_stimeoff = _rs.GetString("TimeOff");
 //BA.debugLineNum = 269;BA.debugLine="LogColor(sTimeON, Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("87602210",_stimeon,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 270;BA.debugLine="LogColor(sTimeOFF, Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("87602211",_stimeoff,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 272;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 273;BA.debugLine="TR.lTimeOn = DateTime.TimeParse(sTimeON)";
_tr.lTimeOn /*long*/  = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_stimeon);
 //BA.debugLineNum = 274;BA.debugLine="If sTimeOFF = \"\" Or sTimeOFF = Null Then";
if (true) break;

case 15:
//if
this.state = 20;
if ((_stimeoff).equals("") || _stimeoff== null) { 
this.state = 17;
}else {
this.state = 19;
}if (true) break;

case 17:
//C
this.state = 20;
 //BA.debugLineNum = 275;BA.debugLine="TR.lTimeOff = 0";
_tr.lTimeOff /*long*/  = (long) (0);
 //BA.debugLineNum = 276;BA.debugLine="TR.iTotOpHrs = 0";
_tr.iTotOpHrs /*double*/  = 0;
 if (true) break;

case 19:
//C
this.state = 20;
 //BA.debugLineNum = 278;BA.debugLine="TR.lTimeOff = DateTime.TimeParse(sTimeOFF)";
_tr.lTimeOff /*long*/  = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_stimeoff);
 //BA.debugLineNum = 279;BA.debugLine="TR.iTotOpHrs = RS.GetDouble(\"TotOpHrs\")";
_tr.iTotOpHrs /*double*/  = _rs.GetDouble("TotOpHrs");
 if (true) break;

case 20:
//C
this.state = 12;
;
 //BA.debugLineNum = 282;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 283;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0dip, clvTime.";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),(int) (parent.mostCurrent._clvtime._asview().getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 284;BA.debugLine="clvTime.Add(Pnl, TR)";
parent.mostCurrent._clvtime._add(_pnl,(Object)(_tr));
 if (true) break;

case 21:
//C
this.state = 24;
;
 if (true) break;

case 23:
//C
this.state = 24;
 //BA.debugLineNum = 287;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 288;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 289;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 290;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 291;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("87602232",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 24:
//C
this.state = 27;
;
 //BA.debugLineNum = 294;BA.debugLine="Log($\"List of Time Records = ${NumberFormat2((Da";
anywheresoftware.b4a.keywords.Common.LogImpl("87602235",("List of Time Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvtime._getsize()))+" Time Records"),0);
 if (true) break;

case 26:
//C
this.state = 27;
this.catchState = 0;
 //BA.debugLineNum = 297;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 298;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 299;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 300;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 301;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("87602242",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 27:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 303;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 37;BA.debugLine="Private TabMenu As WobbleMenu";
mostCurrent._tabmenu = new bwsi.PumpOperations.wobblemenu();
 //BA.debugLineNum = 39;BA.debugLine="Private PnlTime As B4XView";
mostCurrent._pnltime = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private pnlFMRdg As B4XView";
mostCurrent._pnlfmrdg = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private pnlPSIRdg As B4XView";
mostCurrent._pnlpsirdg = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private pnlChlorinator As B4XView";
mostCurrent._pnlchlorinator = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private pnlConcerns As B4XView";
mostCurrent._pnlconcerns = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Dim theDate As Long";
_thedate = 0L;
 //BA.debugLineNum = 47;BA.debugLine="Type TimeRecords (ID As Int, lTimeOn As Long, lTi";
;
 //BA.debugLineNum = 48;BA.debugLine="Private clvTime As CustomListView";
mostCurrent._clvtime = new b4a.example3.customlistview();
 //BA.debugLineNum = 49;BA.debugLine="Private lblOpHrs As B4XView";
mostCurrent._lblophrs = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private lblTimeOff As B4XView";
mostCurrent._lbltimeoff = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private lblTimeOn As B4XView";
mostCurrent._lbltimeon = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private btnAddTime As DSFloatingActionButton";
mostCurrent._btnaddtime = new de.amberhome.objects.FloatingActionButtonWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private lSelectedRecTimeOn As Long";
_lselectedrectimeon = 0L;
 //BA.debugLineNum = 54;BA.debugLine="Private blnAddTime As Boolean";
_blnaddtime = false;
 //BA.debugLineNum = 57;BA.debugLine="Type FMRecords (ID As Int, sRdgTime As String, iP";
;
 //BA.debugLineNum = 59;BA.debugLine="Private clvFM As CustomListView";
mostCurrent._clvfm = new b4a.example3.customlistview();
 //BA.debugLineNum = 60;BA.debugLine="Private lblPresCuM As B4XView";
mostCurrent._lblprescum = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Private lblPrevCuM As B4XView";
mostCurrent._lblprevcum = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Private lblRdgTime As B4XView";
mostCurrent._lblrdgtime = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Private lblTotProd As B4XView";
mostCurrent._lbltotprod = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Private btnAddFM As DSFloatingActionButton";
mostCurrent._btnaddfm = new de.amberhome.objects.FloatingActionButtonWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Type PSIRecords (ID As Int, sRdgTime As String, i";
;
 //BA.debugLineNum = 68;BA.debugLine="Private clvPSI As CustomListView";
mostCurrent._clvpsi = new b4a.example3.customlistview();
 //BA.debugLineNum = 69;BA.debugLine="Private lblPSIRdgTime As B4XView";
mostCurrent._lblpsirdgtime = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 70;BA.debugLine="Private lblPSIRdg As B4XView";
mostCurrent._lblpsirdg = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 71;BA.debugLine="Private btnAddPSI As DSFloatingActionButton";
mostCurrent._btnaddpsi = new de.amberhome.objects.FloatingActionButtonWrapper();
 //BA.debugLineNum = 74;BA.debugLine="Type ChlorineRecords (ID As Int, sTimeRep As Stri";
;
 //BA.debugLineNum = 75;BA.debugLine="Private clvChlorine As CustomListView";
mostCurrent._clvchlorine = new b4a.example3.customlistview();
 //BA.debugLineNum = 76;BA.debugLine="Private lblTimeRep As B4XView";
mostCurrent._lbltimerep = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 77;BA.debugLine="Private lblChlorineType As B4XView";
mostCurrent._lblchlorinetype = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 78;BA.debugLine="Private lblVolume As B4XView";
mostCurrent._lblvolume = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 79;BA.debugLine="Private btnAddChlorine As DSFloatingActionButton";
mostCurrent._btnaddchlorine = new de.amberhome.objects.FloatingActionButtonWrapper();
 //BA.debugLineNum = 80;BA.debugLine="Private sUnit As String";
mostCurrent._sunit = "";
 //BA.debugLineNum = 83;BA.debugLine="Type ConcernsRecords (ID As Int, sTimeEnc As Stri";
;
 //BA.debugLineNum = 84;BA.debugLine="Private clvConcerns As CustomListView";
mostCurrent._clvconcerns = new b4a.example3.customlistview();
 //BA.debugLineNum = 85;BA.debugLine="Private lblTimeEnc As Label";
mostCurrent._lbltimeenc = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 86;BA.debugLine="Private lblProblems As B4XView";
mostCurrent._lblproblems = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 87;BA.debugLine="Private btnAddConcerns As DSFloatingActionButton";
mostCurrent._btnaddconcerns = new de.amberhome.objects.FloatingActionButtonWrapper();
 //BA.debugLineNum = 89;BA.debugLine="Private iLastReading = 0 As Int";
_ilastreading = (int) (0);
 //BA.debugLineNum = 90;BA.debugLine="Private MyToast As BCToast";
mostCurrent._mytoast = new bwsi.PumpOperations.bctoast();
 //BA.debugLineNum = 92;BA.debugLine="Dim cdReading, cdRem As ColorDrawable";
mostCurrent._cdreading = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdrem = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 93;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 94;BA.debugLine="Private Font As Typeface = Typeface.LoadFromAsset";
mostCurrent._font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont.ttf")));
 //BA.debugLineNum = 95;BA.debugLine="Private FontBold As Typeface = Typeface.LoadFromA";
mostCurrent._fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont_bold.ttf")));
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return "";
}
public static boolean  _islastfmreading(int _idetailsid,int _iheaderid) throws Exception{
boolean _bretval = false;
int _idcheck = 0;
 //BA.debugLineNum = 717;BA.debugLine="Private Sub isLastFMReading(iDetailsID As Int, iHe";
 //BA.debugLineNum = 718;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 719;BA.debugLine="Dim idCheck As Int";
_idcheck = 0;
 //BA.debugLineNum = 720;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 722;BA.debugLine="Try";
try { //BA.debugLineNum = 723;BA.debugLine="idCheck = Starter.DBCon.ExecQuerySingleResult(\"S";
_idcheck = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT Max(DetailID) FROM ProductionDetails WHERE HeaderID = "+BA.NumberToString(_iheaderid)+" "+"GROUP BY HeaderID")));
 //BA.debugLineNum = 725;BA.debugLine="LogColor($\"Selected ID: \"$ & iDetailsID & $\" - L";
anywheresoftware.b4a.keywords.Common.LogImpl("88650760",("Selected ID: ")+BA.NumberToString(_idetailsid)+(" - Last ID: ")+BA.NumberToString(_idcheck),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 727;BA.debugLine="If iDetailsID = idCheck Then";
if (_idetailsid==_idcheck) { 
 //BA.debugLineNum = 728;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 730;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e13) {
			processBA.setLastException(e13); //BA.debugLineNum = 733;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 734;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("88650769",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 736;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 737;BA.debugLine="End Sub";
return false;
}
public static boolean  _islastpsireading(int _idetailsid,int _iheaderid) throws Exception{
boolean _bretval = false;
int _idcheck = 0;
 //BA.debugLineNum = 888;BA.debugLine="Private Sub isLastPSIReading(iDetailsID As Int, iH";
 //BA.debugLineNum = 889;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 890;BA.debugLine="Dim idCheck As Int";
_idcheck = 0;
 //BA.debugLineNum = 891;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 893;BA.debugLine="Try";
try { //BA.debugLineNum = 894;BA.debugLine="idCheck = Starter.DBCon.ExecQuerySingleResult(\"S";
_idcheck = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT Max(DetailID) FROM PressureRdgDetails WHERE HeaderID = "+BA.NumberToString(_iheaderid)+" "+"GROUP BY HeaderID")));
 //BA.debugLineNum = 896;BA.debugLine="LogColor($\"Selected ID: \"$ & iDetailsID & $\" - L";
anywheresoftware.b4a.keywords.Common.LogImpl("89109512",("Selected ID: ")+BA.NumberToString(_idetailsid)+(" - Last ID: ")+BA.NumberToString(_idcheck),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 898;BA.debugLine="If iDetailsID = idCheck Then";
if (_idetailsid==_idcheck) { 
 //BA.debugLineNum = 899;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 901;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e13) {
			processBA.setLastException(e13); //BA.debugLineNum = 904;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 905;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("89109521",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 907;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 908;BA.debugLine="End Sub";
return false;
}
public static String  _pnladdeditfmrdg_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 713;BA.debugLine="Sub pnlAddEditFMRdg_Touch (Action As Int, X As Flo";
 //BA.debugLineNum = 715;BA.debugLine="End Sub";
return "";
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
 //BA.debugLineNum = 485;BA.debugLine="Private Sub PumpOff_OnNegativeClicked (View As Vie";
 //BA.debugLineNum = 487;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 488;BA.debugLine="End Sub";
return "";
}
public static String  _pumpoff_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 490;BA.debugLine="Private Sub PumpOff_OnPositiveClicked (View As Vie";
 //BA.debugLineNum = 493;BA.debugLine="LogColor(GlobalVar.SelectedJOID, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("88126467",BA.NumberToString(mostCurrent._globalvar._selectedjoid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 494;BA.debugLine="Alert.Initialize.Dismiss2";
mostCurrent._alert.Initialize().Dismiss2();
 //BA.debugLineNum = 495;BA.debugLine="StartActivity(actPumpOff)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actpumpoff.getObject()));
 //BA.debugLineNum = 496;BA.debugLine="End Sub";
return "";
}
public static void  _showchlorinatorrecdetails(int _iid) throws Exception{
ResumableSub_ShowChlorinatorRecDetails rsub = new ResumableSub_ShowChlorinatorRecDetails(null,_iid);
rsub.resume(processBA, null);
}
public static class ResumableSub_ShowChlorinatorRecDetails extends BA.ResumableSub {
public ResumableSub_ShowChlorinatorRecDetails(bwsi.PumpOperations.actnewproduction parent,int _iid) {
this.parent = parent;
this._iid = _iid;
}
bwsi.PumpOperations.actnewproduction parent;
int _iid;
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
Object _senderfilter = null;
String _sdate = "";
String _spcode = "";
String _stimereplenished = "";
String _schlotype = "";
String _srem = "";
int _ikg = 0;
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
 //BA.debugLineNum = 996;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 997;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 998;BA.debugLine="Dim sDate, sPCode, sTimeReplenished, sChloType, s";
_sdate = "";
_spcode = "";
_stimereplenished = "";
_schlotype = "";
_srem = "";
 //BA.debugLineNum = 999;BA.debugLine="Dim iKG As Int";
_ikg = 0;
 //BA.debugLineNum = 1001;BA.debugLine="Starter.strCriteria = \"SELECT Header.TranDate, Pu";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Header.TranDate, Pump.PumpHouseCode, "+"Details.TimeReplenished, Details.ChlorineType, Details.Volume, Details.Remarks "+"FROM ChlorineDetails AS Details "+"INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID "+"INNER JOIN tblPumpStation AS Pump ON Pump.StationID = Header.PumpID "+"WHERE Details.DetailID = "+BA.NumberToString(_iid);
 //BA.debugLineNum = 1008;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL\"";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 1009;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succes";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 7;
return;
case 7:
//C
this.state = 1;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 1011;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 1012;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 1013;BA.debugLine="sDate = RS.GetString(\"TranDate\")";
_sdate = _rs.GetString("TranDate");
 //BA.debugLineNum = 1014;BA.debugLine="sPCode = RS.GetString(\"PumpHouseCode\")";
_spcode = _rs.GetString("PumpHouseCode");
 //BA.debugLineNum = 1015;BA.debugLine="sTimeReplenished = RS.GetString(\"TimeReplenished";
_stimereplenished = _rs.GetString("TimeReplenished");
 //BA.debugLineNum = 1016;BA.debugLine="sChloType = RS.GetString(\"ChlorineType\")";
_schlotype = _rs.GetString("ChlorineType");
 //BA.debugLineNum = 1017;BA.debugLine="iKG = RS.GetInt(\"Volume\")";
_ikg = _rs.GetInt("Volume");
 //BA.debugLineNum = 1018;BA.debugLine="sRem = RS.GetString(\"Remarks\")";
_srem = _rs.GetString("Remarks");
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 1020;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 1021;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1022;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 1023;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 1024;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("89371677",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 1027;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar.";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (parent.mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("CHLORINATOR RECORD DETAILS"))).PopAll();
 //BA.debugLineNum = 1029;BA.debugLine="MatDialogBuilder.Initialize(\"EditChlorineTime\")";
parent.mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"EditChlorineTime");
 //BA.debugLineNum = 1030;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
parent.mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (parent.mostCurrent._globalvar._bluecolor /*double*/ ));
 //BA.debugLineNum = 1031;BA.debugLine="MatDialogBuilder.NegativeText(\"EDIT\").NegativeCol";
parent.mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence("EDIT")).NegativeColor((int) (parent.mostCurrent._globalvar._greencolor /*double*/ ));
 //BA.debugLineNum = 1032;BA.debugLine="MatDialogBuilder.NeutralText(\"DELETE\").NeutralCol";
parent.mostCurrent._matdialogbuilder.NeutralText(BA.ObjectToCharSequence("DELETE")).NeutralColor((int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 1033;BA.debugLine="MatDialogBuilder.Title(csTitle)";
parent.mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 1034;BA.debugLine="MatDialogBuilder.Content($\"  Pump: ${sPCode} 	Tra";
parent.mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(("  Pump: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_spcode))+"\n"+"	Transaction Date: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sdate))+"\n"+"\n"+"	Time Replenished: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_stimereplenished))+"\n"+"	Chlorine Type: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_schlotype))+"\n"+"	Volume: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_ikg))+" Kg(s).\n"+"	Remarks: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_srem))+"")));
 //BA.debugLineNum = 1041;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
parent.mostCurrent._matdialogbuilder.Theme(parent.mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 1042;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
parent.mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1043;BA.debugLine="MatDialogBuilder.Cancelable(True)";
parent.mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1044;BA.debugLine="MatDialogBuilder.Show";
parent.mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 1045;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _showfmrdgrecdetails(int _iid) throws Exception{
ResumableSub_ShowFMRdgRecDetails rsub = new ResumableSub_ShowFMRdgRecDetails(null,_iid);
rsub.resume(processBA, null);
}
public static class ResumableSub_ShowFMRdgRecDetails extends BA.ResumableSub {
public ResumableSub_ShowFMRdgRecDetails(bwsi.PumpOperations.actnewproduction parent,int _iid) {
this.parent = parent;
this._iid = _iid;
}
bwsi.PumpOperations.actnewproduction parent;
int _iid;
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
Object _senderfilter = null;
String _sdate = "";
String _spcode = "";
String _sreadtime = "";
String _srem = "";
int _ipreviousrdg = 0;
int _ipresentrdg = 0;
int _itotcum = 0;
int _ibackflow = 0;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;

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
 //BA.debugLineNum = 635;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 636;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 637;BA.debugLine="Dim sDate, sPCode, sReadTime, sRem As String";
_sdate = "";
_spcode = "";
_sreadtime = "";
_srem = "";
 //BA.debugLineNum = 638;BA.debugLine="Dim iPreviousRdg, iPresentRdg, iTotCum, iBackFlow";
_ipreviousrdg = 0;
_ipresentrdg = 0;
_itotcum = 0;
_ibackflow = 0;
 //BA.debugLineNum = 640;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
parent.mostCurrent._globalvar._tranheaderid /*int*/  = parent.mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,parent.mostCurrent._globalvar._pumphouseid /*int*/ ,parent.mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 641;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 12;
this.catchState = 11;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 11;
 //BA.debugLineNum = 642;BA.debugLine="Starter.strCriteria = \"SELECT Header.TranDate, P";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Header.TranDate, Pump.PumpHouseCode, "+"Details.DetailID, Details.RdgTime, Details.PrevRdg, Details.PresRdg, "+"Details.PresCum, Details.Remarks, BackFlowCum "+"FROM ProductionDetails As Details "+"INNER JOIN TranHeader As Header ON Details.HeaderID = Header.HeaderID "+"INNER JOIN tblPumpStation AS Pump ON Pump.StationID = Header.PumpID "+"WHERE Details.DetailID = "+BA.NumberToString(_iid);
 //BA.debugLineNum = 650;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 651;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 19;
return;
case 19:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 653;BA.debugLine="If Success Then";
if (true) break;

case 4:
//if
this.state = 9;
if (_success) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
 //BA.debugLineNum = 654;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 655;BA.debugLine="GlobalVar.FMRdgDetailID = RS.GetInt(\"DetailID\")";
parent.mostCurrent._globalvar._fmrdgdetailid /*int*/  = _rs.GetInt("DetailID");
 //BA.debugLineNum = 656;BA.debugLine="sDate = RS.GetString(\"TranDate\")";
_sdate = _rs.GetString("TranDate");
 //BA.debugLineNum = 657;BA.debugLine="sPCode = RS.GetString(\"PumpHouseCode\")";
_spcode = _rs.GetString("PumpHouseCode");
 //BA.debugLineNum = 658;BA.debugLine="sReadTime = RS.GetString(\"RdgTime\")";
_sreadtime = _rs.GetString("RdgTime");
 //BA.debugLineNum = 659;BA.debugLine="iPreviousRdg = RS.GetInt(\"PrevRdg\")";
_ipreviousrdg = _rs.GetInt("PrevRdg");
 //BA.debugLineNum = 660;BA.debugLine="iPresentRdg = RS.GetInt(\"PresRdg\")";
_ipresentrdg = _rs.GetInt("PresRdg");
 //BA.debugLineNum = 661;BA.debugLine="iTotCum = RS.GetInt(\"PresCum\")";
_itotcum = _rs.GetInt("PresCum");
 //BA.debugLineNum = 662;BA.debugLine="sRem = RS.GetString(\"Remarks\")";
_srem = _rs.GetString("Remarks");
 //BA.debugLineNum = 663;BA.debugLine="iBackFlow = RS.GetInt(\"BackFlowCum\")";
_ibackflow = _rs.GetInt("BackFlowCum");
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 665;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 666;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 667;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 668;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 669;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("88454179",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 9:
//C
this.state = 12;
;
 if (true) break;

case 11:
//C
this.state = 12;
this.catchState = 0;
 //BA.debugLineNum = 673;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("88454183",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 12:
//C
this.state = 13;
this.catchState = 0;
;
 //BA.debugLineNum = 676;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar.";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (parent.mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("FLOW METER READING DETAILS"))).PopAll();
 //BA.debugLineNum = 678;BA.debugLine="MatDialogBuilder.Initialize(\"EditFMRdg\")";
parent.mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"EditFMRdg");
 //BA.debugLineNum = 679;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
parent.mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (parent.mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 680;BA.debugLine="If isLastFMReading(GlobalVar.FMRdgDetailID, Globa";
if (true) break;

case 13:
//if
this.state = 18;
if (_islastfmreading(parent.mostCurrent._globalvar._fmrdgdetailid /*int*/ ,parent.mostCurrent._globalvar._tranheaderid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 15;
}else {
this.state = 17;
}if (true) break;

case 15:
//C
this.state = 18;
 //BA.debugLineNum = 681;BA.debugLine="MatDialogBuilder.NegativeText(\"EDIT\").NegativeCo";
parent.mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence("EDIT")).NegativeColor((int) (parent.mostCurrent._globalvar._greencolor /*double*/ ));
 if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 683;BA.debugLine="MatDialogBuilder.NegativeText(\"\")";
parent.mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence(""));
 if (true) break;

case 18:
//C
this.state = -1;
;
 //BA.debugLineNum = 685;BA.debugLine="MatDialogBuilder.NeutralText(\"DELETE\").NeutralCol";
parent.mostCurrent._matdialogbuilder.NeutralText(BA.ObjectToCharSequence("DELETE")).NeutralColor((int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 686;BA.debugLine="MatDialogBuilder.Title(csTitle)";
parent.mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 687;BA.debugLine="MatDialogBuilder.Content($\"  Pump: ${sPCode} 	Tra";
parent.mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(("  Pump: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_spcode))+"\n"+"	Transaction Date: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sdate))+"\n"+"\n"+"	Reading Time: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sreadtime))+"\n"+"	Present Reading: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_ipresentrdg))+"\n"+"	Previous Reading: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_ipreviousrdg))+"\n"+"	Total Prod.: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat(_itotcum,(int) (0),(int) (0))))+" CuM\n"+"	Backflow: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat(_ibackflow,(int) (0),(int) (0))))+" CuM\n"+"	Remarks: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_srem))+"")));
 //BA.debugLineNum = 696;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
parent.mostCurrent._matdialogbuilder.Theme(parent.mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 697;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
parent.mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 698;BA.debugLine="MatDialogBuilder.Cancelable(True)";
parent.mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 699;BA.debugLine="MatDialogBuilder.Show";
parent.mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 700;BA.debugLine="End Sub";
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
public static void  _showproblemrecdetails(int _iid) throws Exception{
ResumableSub_ShowProblemRecDetails rsub = new ResumableSub_ShowProblemRecDetails(null,_iid);
rsub.resume(processBA, null);
}
public static class ResumableSub_ShowProblemRecDetails extends BA.ResumableSub {
public ResumableSub_ShowProblemRecDetails(bwsi.PumpOperations.actnewproduction parent,int _iid) {
this.parent = parent;
this._iid = _iid;
}
bwsi.PumpOperations.actnewproduction parent;
int _iid;
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
Object _senderfilter = null;
String _strandate = "";
String _spumpcode = "";
String _spumparea = "";
int _iiscritical = 0;
int _iwassolved = 0;
String _siscritical = "";
String _swassolved = "";
String _sprobtitle = "";
String _sprobdesc = "";
String _stimestart = "";
String _stimefinished = "";
String _sactiontaken = "";
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;

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
 //BA.debugLineNum = 1146;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 1147;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 1148;BA.debugLine="Dim sTrandate, sPumpCode, sPumpArea As String";
_strandate = "";
_spumpcode = "";
_spumparea = "";
 //BA.debugLineNum = 1149;BA.debugLine="Dim iIsCritical, iWasSolved As Int";
_iiscritical = 0;
_iwassolved = 0;
 //BA.debugLineNum = 1150;BA.debugLine="Dim sIsCritical, sWasSolved, sProbTitle, sProbDes";
_siscritical = "";
_swassolved = "";
_sprobtitle = "";
_sprobdesc = "";
_stimestart = "";
_stimefinished = "";
_sactiontaken = "";
 //BA.debugLineNum = 1152;BA.debugLine="LogColor(iID, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("89830407",BA.NumberToString(_iid),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 1153;BA.debugLine="Try";
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
 //BA.debugLineNum = 1154;BA.debugLine="Starter.strCriteria = \"SELECT Header.TranDate, S";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Header.TranDate, Station.PumpHouseCode, Areas.PumpArea, "+"ProblemDtls.IsCritical, ProblemDtls.ProblemTitle, ProblemDtls.ProbDesc, "+"ProblemDtls.TimeStart, ProblemDtls.TimeFinished, "+"ProblemDtls.WasSolved, ProblemDtls.ActionTaken "+"FROM ProblemDetails AS ProblemDtls "+"INNER JOIN TranHeader AS Header ON ProblemDtls.HeaderID = Header.HeaderID "+"INNER JOIN tblPumpStation AS Station ON Header.PumpID = Station.StationID "+"INNER JOIN PumpAreas AS Areas ON ProblemDtls.AreaID = Areas.ID "+"WHERE ProblemDtls.DetailID = "+BA.NumberToString(_iid);
 //BA.debugLineNum = 1164;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 1165;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 25;
return;
case 25:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 1167;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 1168;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 1169;BA.debugLine="sTrandate = RS.GetString(\"TranDate\")";
_strandate = _rs.GetString("TranDate");
 //BA.debugLineNum = 1170;BA.debugLine="sPumpCode = RS.GetString(\"PumpHouseCode\")";
_spumpcode = _rs.GetString("PumpHouseCode");
 //BA.debugLineNum = 1171;BA.debugLine="sPumpArea = RS.GetString(\"PumpArea\")";
_spumparea = _rs.GetString("PumpArea");
 //BA.debugLineNum = 1172;BA.debugLine="iIsCritical = RS.GetInt(\"IsCritical\")";
_iiscritical = _rs.GetInt("IsCritical");
 //BA.debugLineNum = 1173;BA.debugLine="If iIsCritical = 1 Then";
if (true) break;

case 7:
//if
this.state = 12;
if (_iiscritical==1) { 
this.state = 9;
}else {
this.state = 11;
}if (true) break;

case 9:
//C
this.state = 12;
 //BA.debugLineNum = 1174;BA.debugLine="sIsCritical = $\"YES\"$";
_siscritical = ("YES");
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 1176;BA.debugLine="sIsCritical = $\"NO\"$";
_siscritical = ("NO");
 if (true) break;

case 12:
//C
this.state = 13;
;
 //BA.debugLineNum = 1178;BA.debugLine="sProbTitle = RS.GetString(\"ProblemTitle\")";
_sprobtitle = _rs.GetString("ProblemTitle");
 //BA.debugLineNum = 1179;BA.debugLine="sProbDesc = RS.GetString(\"ProbDesc\")";
_sprobdesc = _rs.GetString("ProbDesc");
 //BA.debugLineNum = 1180;BA.debugLine="sTimeStart = RS.GetString(\"TimeStart\")";
_stimestart = _rs.GetString("TimeStart");
 //BA.debugLineNum = 1181;BA.debugLine="sTimeFinished = RS.GetString(\"TimeFinished\")";
_stimefinished = _rs.GetString("TimeFinished");
 //BA.debugLineNum = 1182;BA.debugLine="iWasSolved = RS.GetInt(\"WasSolved\")";
_iwassolved = _rs.GetInt("WasSolved");
 //BA.debugLineNum = 1183;BA.debugLine="If iWasSolved = 1 Then";
if (true) break;

case 13:
//if
this.state = 18;
if (_iwassolved==1) { 
this.state = 15;
}else {
this.state = 17;
}if (true) break;

case 15:
//C
this.state = 18;
 //BA.debugLineNum = 1184;BA.debugLine="sWasSolved = $\"YES\"$";
_swassolved = ("YES");
 if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 1186;BA.debugLine="sWasSolved = $\"NO\"$";
_swassolved = ("NO");
 if (true) break;

case 18:
//C
this.state = 21;
;
 //BA.debugLineNum = 1188;BA.debugLine="sActionTaken = RS.GetString(\"ActionTaken\")";
_sactiontaken = _rs.GetString("ActionTaken");
 if (true) break;

case 20:
//C
this.state = 21;
 //BA.debugLineNum = 1190;BA.debugLine="ToastMessageShow($\"Unable to fetch Problem Deta";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Problem Details due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1191;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("89830446",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 1192;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 21:
//C
this.state = 24;
;
 if (true) break;

case 23:
//C
this.state = 24;
this.catchState = 0;
 //BA.debugLineNum = 1196;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("89830451",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 24:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 1200;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar.";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (parent.mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("PROBLEM ENCOUNTERED DETAILS"))).PopAll();
 //BA.debugLineNum = 1202;BA.debugLine="MatDialogBuilder.Initialize(\"EditProblemRecord\")";
parent.mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"EditProblemRecord");
 //BA.debugLineNum = 1203;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
parent.mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (parent.mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 1204;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
parent.mostCurrent._globalvar._tranheaderid /*int*/  = parent.mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,parent.mostCurrent._globalvar._pumphouseid /*int*/ ,parent.mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 1205;BA.debugLine="MatDialogBuilder.NegativeText(\"EDIT\").NegativeCol";
parent.mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence("EDIT")).NegativeColor((int) (parent.mostCurrent._globalvar._greencolor /*double*/ ));
 //BA.debugLineNum = 1206;BA.debugLine="MatDialogBuilder.NeutralText(\"DELETE\").NeutralCol";
parent.mostCurrent._matdialogbuilder.NeutralText(BA.ObjectToCharSequence("DELETE")).NeutralColor((int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 1207;BA.debugLine="MatDialogBuilder.Title(csTitle)";
parent.mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 1208;BA.debugLine="MatDialogBuilder.Content($\"  Pump: ${sPumpCode}";
parent.mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(("  Pump: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_spumpcode))+"\n"+"	Transaction Date: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_strandate))+"\n"+"\n"+"	Pump Area: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_spumparea))+"\n"+"	Is Critical: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_siscritical))+"\n"+"	Time Started: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_stimestart))+"\n"+"	Time Finished: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_stimefinished))+"\n"+"	Problem Title: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sprobtitle))+"\n"+"	Problem Detail: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sprobdesc))+"\n"+"	Was Solved?: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_swassolved))+"\n"+"	Action Taken: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sactiontaken))+"")));
 //BA.debugLineNum = 1219;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
parent.mostCurrent._matdialogbuilder.Theme(parent.mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 1220;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
parent.mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1221;BA.debugLine="MatDialogBuilder.Cancelable(True)";
parent.mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1222;BA.debugLine="MatDialogBuilder.Show";
parent.mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 1223;BA.debugLine="End Sub";
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
public static void  _showpsirdgrecdetails(int _iid) throws Exception{
ResumableSub_ShowPSIRdgRecDetails rsub = new ResumableSub_ShowPSIRdgRecDetails(null,_iid);
rsub.resume(processBA, null);
}
public static class ResumableSub_ShowPSIRdgRecDetails extends BA.ResumableSub {
public ResumableSub_ShowPSIRdgRecDetails(bwsi.PumpOperations.actnewproduction parent,int _iid) {
this.parent = parent;
this._iid = _iid;
}
bwsi.PumpOperations.actnewproduction parent;
int _iid;
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
Object _senderfilter = null;
String _sdate = "";
String _spcode = "";
String _sreadtime = "";
String _srem = "";
int _ipressurerdg = 0;
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
 //BA.debugLineNum = 820;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 821;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 822;BA.debugLine="Dim sDate, sPCode, sReadTime, sRem As String";
_sdate = "";
_spcode = "";
_sreadtime = "";
_srem = "";
 //BA.debugLineNum = 823;BA.debugLine="Dim iPressureRdg As Int";
_ipressurerdg = 0;
 //BA.debugLineNum = 825;BA.debugLine="Starter.strCriteria = \"SELECT Header.TranDate, Pu";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Header.TranDate, Pump.PumpHouseCode, "+"Details.RdgTime, Details.PSIReading, Details.Remarks "+"FROM PressureRdgDetails AS Details "+"INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID "+"INNER JOIN tblPumpStation AS Pump ON Pump.StationID = Header.PumpID "+"WHERE Details.DetailID = "+BA.NumberToString(_iid);
 //BA.debugLineNum = 832;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL\"";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 833;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succes";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 13;
return;
case 13:
//C
this.state = 1;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 835;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 836;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 837;BA.debugLine="sDate = RS.GetString(\"TranDate\")";
_sdate = _rs.GetString("TranDate");
 //BA.debugLineNum = 838;BA.debugLine="sPCode = RS.GetString(\"PumpHouseCode\")";
_spcode = _rs.GetString("PumpHouseCode");
 //BA.debugLineNum = 839;BA.debugLine="sReadTime = RS.GetString(\"RdgTime\")";
_sreadtime = _rs.GetString("RdgTime");
 //BA.debugLineNum = 840;BA.debugLine="iPressureRdg = RS.GetInt(\"PSIReading\")";
_ipressurerdg = _rs.GetInt("PSIReading");
 //BA.debugLineNum = 841;BA.debugLine="sRem = RS.GetString(\"Remarks\")";
_srem = _rs.GetString("Remarks");
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 843;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 844;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 845;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 846;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 847;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("88912924",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 6:
//C
this.state = 7;
;
 //BA.debugLineNum = 850;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar.";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (parent.mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("PRESSURE READING DETAILS"))).PopAll();
 //BA.debugLineNum = 852;BA.debugLine="MatDialogBuilder.Initialize(\"EditPSIRdg\")";
parent.mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"EditPSIRdg");
 //BA.debugLineNum = 853;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
parent.mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (parent.mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 854;BA.debugLine="LogColor(GlobalVar.PSIRdgDetailID, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("88912931",BA.NumberToString(parent.mostCurrent._globalvar._psirdgdetailid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 855;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
parent.mostCurrent._globalvar._tranheaderid /*int*/  = parent.mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,parent.mostCurrent._globalvar._pumphouseid /*int*/ ,parent.mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 856;BA.debugLine="If isLastPSIReading(GlobalVar.PSIRdgDetailID, Glo";
if (true) break;

case 7:
//if
this.state = 12;
if (_islastpsireading(parent.mostCurrent._globalvar._psirdgdetailid /*int*/ ,parent.mostCurrent._globalvar._tranheaderid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 9;
}else {
this.state = 11;
}if (true) break;

case 9:
//C
this.state = 12;
 //BA.debugLineNum = 857;BA.debugLine="MatDialogBuilder.NegativeText(\"EDIT\").NegativeCo";
parent.mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence("EDIT")).NegativeColor((int) (parent.mostCurrent._globalvar._greencolor /*double*/ ));
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 859;BA.debugLine="MatDialogBuilder.NegativeText(\"\")";
parent.mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence(""));
 if (true) break;

case 12:
//C
this.state = -1;
;
 //BA.debugLineNum = 861;BA.debugLine="MatDialogBuilder.NeutralText(\"DELETE\").NeutralCol";
parent.mostCurrent._matdialogbuilder.NeutralText(BA.ObjectToCharSequence("DELETE")).NeutralColor((int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 862;BA.debugLine="MatDialogBuilder.Title(csTitle)";
parent.mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 863;BA.debugLine="MatDialogBuilder.Content($\"  Pump: ${sPCode} 	Tra";
parent.mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(("  Pump: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_spcode))+"\n"+"	Transaction Date: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sdate))+"\n"+"\n"+"	Reading Time: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sreadtime))+"\n"+"	Pressure Reading: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_ipressurerdg))+"  PSI\n"+"	Remarks: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_srem))+"")));
 //BA.debugLineNum = 869;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
parent.mostCurrent._matdialogbuilder.Theme(parent.mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 870;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
parent.mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 871;BA.debugLine="MatDialogBuilder.Cancelable(True)";
parent.mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 872;BA.debugLine="MatDialogBuilder.Show";
parent.mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 873;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _showpumptimerecdetails(int _iid) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
String _sdate = "";
String _spcode = "";
String _stimeon = "";
String _stimeoff = "";
String _spowersource = "";
String _srem = "";
int _ipowersource = 0;
int _idraintime = 0;
int _idraincum = 0;
double _itotophrs = 0;
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsdetails = null;
 //BA.debugLineNum = 354;BA.debugLine="Sub ShowPumpTimeRecDetails (iID As Int)";
 //BA.debugLineNum = 355;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 356;BA.debugLine="Dim sDate, sPCode, sTimeOn, sTimeOff, sPowerSourc";
_sdate = "";
_spcode = "";
_stimeon = "";
_stimeoff = "";
_spowersource = "";
_srem = "";
 //BA.debugLineNum = 357;BA.debugLine="Dim iPowerSource, iDrainTime, iDrainCum As Int";
_ipowersource = 0;
_idraintime = 0;
_idraincum = 0;
 //BA.debugLineNum = 358;BA.debugLine="Dim iTotOPHrs As Double";
_itotophrs = 0;
 //BA.debugLineNum = 359;BA.debugLine="Dim rsDetails As Cursor";
_rsdetails = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 361;BA.debugLine="LogColor (iID, Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("87798791",BA.NumberToString(_iid),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 362;BA.debugLine="Try";
try { //BA.debugLineNum = 363;BA.debugLine="Starter.strCriteria = \"SELECT Header.TranDate, \"";
mostCurrent._starter._strcriteria /*String*/  = "SELECT Header.TranDate, "+"Pump.PumpHouseCode, Details.PumpOnTime, Details.PumpOffTime, Details.TotOpHrs, "+"Details.PowerSourceID, Details.DrainTime, Details.DrainCum, Details.TimeOnRemarks, Details.TimeOffRemarks "+"FROM OnOffDetails AS Details "+"INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID "+"INNER JOIN tblPumpStation AS Pump ON Pump.StationID = Header.PumpID "+"WHERE Details.DetailID = "+BA.NumberToString(_iid);
 //BA.debugLineNum = 371;BA.debugLine="rsDetails = Starter.DBCon.ExecQuery(Starter.strC";
_rsdetails = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 372;BA.debugLine="If rsDetails.RowCount > 0 Then";
if (_rsdetails.getRowCount()>0) { 
 //BA.debugLineNum = 373;BA.debugLine="rsDetails.Position = 0";
_rsdetails.setPosition((int) (0));
 //BA.debugLineNum = 374;BA.debugLine="sDate = rsDetails.GetString(\"TranDate\")";
_sdate = _rsdetails.GetString("TranDate");
 //BA.debugLineNum = 375;BA.debugLine="sPCode = rsDetails.GetString(\"PumpHouseCode\")";
_spcode = _rsdetails.GetString("PumpHouseCode");
 //BA.debugLineNum = 376;BA.debugLine="sTimeOn = rsDetails.GetString(\"PumpOnTime\")";
_stimeon = _rsdetails.GetString("PumpOnTime");
 //BA.debugLineNum = 377;BA.debugLine="sTimeOff = rsDetails.GetString(\"PumpOffTime\")";
_stimeoff = _rsdetails.GetString("PumpOffTime");
 //BA.debugLineNum = 378;BA.debugLine="iTotOPHrs = rsDetails.GetDouble(\"TotOpHrs\")";
_itotophrs = _rsdetails.GetDouble("TotOpHrs");
 //BA.debugLineNum = 379;BA.debugLine="iPowerSource = rsDetails.GetInt(\"PowerSourceID\"";
_ipowersource = _rsdetails.GetInt("PowerSourceID");
 //BA.debugLineNum = 380;BA.debugLine="If iPowerSource = 1 Then";
if (_ipowersource==1) { 
 //BA.debugLineNum = 381;BA.debugLine="sPowerSource = $\"Electricity\"$";
_spowersource = ("Electricity");
 }else {
 //BA.debugLineNum = 383;BA.debugLine="sPowerSource = $\"Generator\"$";
_spowersource = ("Generator");
 };
 //BA.debugLineNum = 385;BA.debugLine="iDrainTime = rsDetails.GetInt(\"DrainTime\")";
_idraintime = _rsdetails.GetInt("DrainTime");
 //BA.debugLineNum = 386;BA.debugLine="iDrainCum = rsDetails.GetInt(\"DrainCum\")";
_idraincum = _rsdetails.GetInt("DrainCum");
 //BA.debugLineNum = 387;BA.debugLine="sRem = rsDetails.GetString(\"TimeOnRemarks\") & \"";
_srem = _rsdetails.GetString("TimeOnRemarks")+" / "+_rsdetails.GetString("TimeOffRemarks");
 }else {
 //BA.debugLineNum = 394;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e30) {
			processBA.setLastException(e30); //BA.debugLineNum = 398;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("87798828",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 400;BA.debugLine="rsDetails.Close";
_rsdetails.Close();
 //BA.debugLineNum = 402;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar.";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("PUMP ON/OFF DETAILS"))).PopAll();
 //BA.debugLineNum = 404;BA.debugLine="MatDialogBuilder.Initialize(\"EditTime\")";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"EditTime");
 //BA.debugLineNum = 405;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._bluecolor /*double*/ ));
 //BA.debugLineNum = 406;BA.debugLine="MatDialogBuilder.NegativeText(\"EDIT\").NegativeCol";
mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence("EDIT")).NegativeColor((int) (mostCurrent._globalvar._greencolor /*double*/ ));
 //BA.debugLineNum = 407;BA.debugLine="MatDialogBuilder.NeutralText(\"DELETE\").NeutralCol";
mostCurrent._matdialogbuilder.NeutralText(BA.ObjectToCharSequence("DELETE")).NeutralColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 408;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 409;BA.debugLine="MatDialogBuilder.Content($\"  Pump: ${sPCode} 	Tra";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(("  Pump: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_spcode))+"\n"+"	Transaction Date: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sdate))+"\n"+"\n"+"	Time On:	"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_stimeon))+"\n"+"	Time Off:	"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_stimeoff))+"\n"+"	Total Operating Hrs:	"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2(_itotophrs,(int) (1),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.True)))+" Hr(s).\n"+"	Power Source:	"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_spowersource))+"\n"+"	Drain Time:	"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_idraintime))+" Min(s).\n"+"	Drain CuM:	"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_idraincum))+" CuM\n"+"	Remarks:	"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_srem))+"")));
 //BA.debugLineNum = 419;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 420;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 421;BA.debugLine="MatDialogBuilder.Cancelable(True)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 422;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 423;BA.debugLine="End Sub";
return "";
}
public static String  _tabmenu_tab1click() throws Exception{
 //BA.debugLineNum = 181;BA.debugLine="Sub TabMenu_Tab1Click 'Pump On/Off Time";
 //BA.debugLineNum = 182;BA.debugLine="PnlTime.Visible = True";
mostCurrent._pnltime.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 183;BA.debugLine="GetPumpTimeRec(GlobalVar.TranDate, GlobalVar.Pump";
_getpumptimerec(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 184;BA.debugLine="pnlFMRdg.Visible = False";
mostCurrent._pnlfmrdg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 185;BA.debugLine="pnlFMRdg.Visible = False";
mostCurrent._pnlfmrdg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 186;BA.debugLine="pnlPSIRdg.Visible = False";
mostCurrent._pnlpsirdg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 187;BA.debugLine="pnlChlorinator.Visible = False";
mostCurrent._pnlchlorinator.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 188;BA.debugLine="pnlConcerns.Visible = False";
mostCurrent._pnlconcerns.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return "";
}
public static String  _tabmenu_tab2click() throws Exception{
 //BA.debugLineNum = 191;BA.debugLine="Sub TabMenu_Tab2Click ' Flow Meter Reading";
 //BA.debugLineNum = 192;BA.debugLine="PnlTime.Visible = False";
mostCurrent._pnltime.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 193;BA.debugLine="pnlPSIRdg.Visible = False";
mostCurrent._pnlpsirdg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 194;BA.debugLine="pnlChlorinator.Visible = False";
mostCurrent._pnlchlorinator.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 195;BA.debugLine="pnlConcerns.Visible = False";
mostCurrent._pnlconcerns.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 197;BA.debugLine="pnlFMRdg.Visible = True";
mostCurrent._pnlfmrdg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 198;BA.debugLine="GetFMRdgRec(GlobalVar.TranDate, GlobalVar.PumpHou";
_getfmrdgrec(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 199;BA.debugLine="End Sub";
return "";
}
public static String  _tabmenu_tab3click() throws Exception{
 //BA.debugLineNum = 201;BA.debugLine="Sub TabMenu_Tab3Click ' PSI Reading";
 //BA.debugLineNum = 202;BA.debugLine="PnlTime.Visible = False";
mostCurrent._pnltime.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 203;BA.debugLine="pnlFMRdg.Visible = False";
mostCurrent._pnlfmrdg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 204;BA.debugLine="pnlChlorinator.Visible = False";
mostCurrent._pnlchlorinator.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 205;BA.debugLine="pnlConcerns.Visible = False";
mostCurrent._pnlconcerns.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 207;BA.debugLine="pnlPSIRdg.Visible = True";
mostCurrent._pnlpsirdg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 208;BA.debugLine="GetPSIRdgRec(GlobalVar.TranDate, GlobalVar.PumpHo";
_getpsirdgrec(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 209;BA.debugLine="End Sub";
return "";
}
public static String  _tabmenu_tab4click() throws Exception{
 //BA.debugLineNum = 211;BA.debugLine="Sub TabMenu_Tab4Click ' Chlorinator";
 //BA.debugLineNum = 212;BA.debugLine="PnlTime.Visible = False";
mostCurrent._pnltime.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 213;BA.debugLine="pnlFMRdg.Visible = False";
mostCurrent._pnlfmrdg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 214;BA.debugLine="pnlPSIRdg.Visible = False";
mostCurrent._pnlpsirdg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 215;BA.debugLine="pnlConcerns.Visible = False";
mostCurrent._pnlconcerns.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 217;BA.debugLine="pnlChlorinator.Visible = True";
mostCurrent._pnlchlorinator.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 218;BA.debugLine="GetChlorineRec(GlobalVar.TranDate, GlobalVar.Pump";
_getchlorinerec(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 219;BA.debugLine="End Sub";
return "";
}
public static String  _tabmenu_tab5click() throws Exception{
 //BA.debugLineNum = 221;BA.debugLine="Sub TabMenu_Tab5Click ' Problems Encountered";
 //BA.debugLineNum = 222;BA.debugLine="PnlTime.Visible = False";
mostCurrent._pnltime.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 223;BA.debugLine="pnlFMRdg.Visible = False";
mostCurrent._pnlfmrdg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 224;BA.debugLine="pnlPSIRdg.Visible = False";
mostCurrent._pnlpsirdg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 225;BA.debugLine="pnlChlorinator.Visible = False";
mostCurrent._pnlchlorinator.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 227;BA.debugLine="pnlConcerns.Visible = True";
mostCurrent._pnlconcerns.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 228;BA.debugLine="GetProblemsRec(GlobalVar.TranDate, GlobalVar.Pump";
_getproblemsrec(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 229;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 174;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 169;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 170;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 171;BA.debugLine="kboard.HideKeyboard";
mostCurrent._kboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
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
