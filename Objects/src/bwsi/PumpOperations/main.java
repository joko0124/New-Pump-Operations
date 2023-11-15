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

public class main extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
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
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
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
public static anywheresoftware.b4a.objects.RuntimePermissions _permissions = null;
public static bwsi.PumpOperations.slinptypeconst _inptyp = null;
public static anywheresoftware.b4a.objects.Timer _tmrsplash = null;
public de.amberhome.objects.SnackbarWrapper _mysnack = null;
public anywheresoftware.b4a.objects.IME _imekeyboard = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _btnlogin = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtpassword = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtusername = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdtxtbox = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblcheck = null;
public static boolean _ischecked = false;
public anywheresoftware.b4a.objects.CSBuilder _csans = null;
public de.amberhome.objects.SnackbarWrapper _snack = null;
public com.johan.Vibrate.Vibrate _vibration = null;
public static long[] _vibratepattern = null;
public bwsi.PumpOperations.bctoast _thetoast = null;
public anywheresoftware.b4a.objects.PanelWrapper _btnbranchsettings = null;
public anywheresoftware.b4a.objects.PanelWrapper _btnipsettings = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlsearch = null;
public static boolean _readstoragepermission = false;
public static boolean _writestoragepermission = false;
public static boolean _coarselocpermission = false;
public static boolean _finelocpermission = false;
public bwsi.PumpOperations.keyvaluestore _kvs = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlsplash = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblshow = null;
public b4a.example.dateutils _dateutils = null;
public bwsi.PumpOperations.globalvar _globalvar = null;
public bwsi.PumpOperations.myfunctions _myfunctions = null;
public bwsi.PumpOperations.dbasefunctions _dbasefunctions = null;
public bwsi.PumpOperations.actnewproduction _actnewproduction = null;
public bwsi.PumpOperations.mainscreen _mainscreen = null;
public bwsi.PumpOperations.actcmjofindings _actcmjofindings = null;
public bwsi.PumpOperations.actdccrjofindings _actdccrjofindings = null;
public bwsi.PumpOperations.actgpmcalc _actgpmcalc = null;
public bwsi.PumpOperations.actcriticalpoint _actcriticalpoint = null;
public bwsi.PumpOperations.actdcdajofindings _actdcdajofindings = null;
public bwsi.PumpOperations.actdebugkeyboard _actdebugkeyboard = null;
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

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (actnewproduction.mostCurrent != null);
vis = vis | (mainscreen.mostCurrent != null);
vis = vis | (actcmjofindings.mostCurrent != null);
vis = vis | (actdccrjofindings.mostCurrent != null);
vis = vis | (actgpmcalc.mostCurrent != null);
vis = vis | (actcriticalpoint.mostCurrent != null);
vis = vis | (actdcdajofindings.mostCurrent != null);
vis = vis | (actdebugkeyboard.mostCurrent != null);
vis = vis | (actgpmhistory.mostCurrent != null);
vis = vis | (actjo.mostCurrent != null);
vis = vis | (actjoaccomplishedsas.mostCurrent != null);
vis = vis | (actjodetails.mostCurrent != null);
vis = vis | (actjonotification.mostCurrent != null);
vis = vis | (actjoreasons.mostCurrent != null);
vis = vis | (actjosummary.mostCurrent != null);
vis = vis | (actjowithreasons.mostCurrent != null);
vis = vis | (actmcjofindings.mostCurrent != null);
vis = vis | (actncjofindings.mostCurrent != null);
vis = vis | (actnonoperational.mostCurrent != null);
vis = vis | (actproduction.mostCurrent != null);
vis = vis | (actpumpoff.mostCurrent != null);
vis = vis | (actrcjofindings.mostCurrent != null);
vis = vis | (actrepmain.mostCurrent != null);
vis = vis | (actsasjofindings.mostCurrent != null);
vis = vis | (actwaterbalance.mostCurrent != null);
vis = vis | (addeditchlorinerecord.mostCurrent != null);
vis = vis | (addeditfmrdg.mostCurrent != null);
vis = vis | (addeditnonoperational.mostCurrent != null);
vis = vis | (addeditproblem.mostCurrent != null);
vis = vis | (addeditpsidistrecord.mostCurrent != null);
vis = vis | (addeditpsirdg.mostCurrent != null);
vis = vis | (addedittimerecord.mostCurrent != null);
vis = vis | (edittimerecord.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 67;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 69;BA.debugLine="Activity.LoadLayout(\"LoginNew\")";
mostCurrent._activity.LoadLayout("LoginNew",mostCurrent.activityBA);
 //BA.debugLineNum = 70;BA.debugLine="DBaseFunctions.GetParameters";
mostCurrent._dbasefunctions._getparameters /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 72;BA.debugLine="InpTyp.Initialize";
_inptyp._initialize /*String*/ (processBA);
 //BA.debugLineNum = 73;BA.debugLine="InpTyp.SetInputType(txtPassword,Array As Int(InpT";
_inptyp._setinputtype /*String*/ (mostCurrent._txtpassword,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_variation_password /*int*/ ()});
 //BA.debugLineNum = 75;BA.debugLine="CDtxtBox.Initialize(Colors.Transparent,0)";
mostCurrent._cdtxtbox.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 77;BA.debugLine="txtUserName.Background = CDtxtBox";
mostCurrent._txtusername.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 78;BA.debugLine="txtPassword.Background = CDtxtBox";
mostCurrent._txtpassword.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 79;BA.debugLine="isChecked = False";
_ischecked = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 80;BA.debugLine="lblCheck.Text = \"\"";
mostCurrent._lblcheck.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 81;BA.debugLine="txtPassword.PasswordMode = True";
mostCurrent._txtpassword.setPasswordMode(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 83;BA.debugLine="txtUserName.Text = \"arnold.fernandez@bwsi.com.ph\"";
mostCurrent._txtusername.setText(BA.ObjectToCharSequence("arnold.fernandez@bwsi.com.ph"));
 //BA.debugLineNum = 84;BA.debugLine="txtPassword.Text = \"3923bwsi12\"";
mostCurrent._txtpassword.setText(BA.ObjectToCharSequence("3923bwsi12"));
 //BA.debugLineNum = 85;BA.debugLine="txtUserName.Padding = Array As Int(3dip, 0dip,0di";
mostCurrent._txtusername.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0))});
 //BA.debugLineNum = 86;BA.debugLine="txtPassword.Padding = Array As Int(3dip, 0dip,0di";
mostCurrent._txtpassword.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0))});
 //BA.debugLineNum = 88;BA.debugLine="Log(FirstTime)";
anywheresoftware.b4a.keywords.Common.LogImpl("7131093",BA.ObjectToString(_firsttime),0);
 //BA.debugLineNum = 89;BA.debugLine="If FirstTime = True Then";
if (_firsttime==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 90;BA.debugLine="ShowSplash";
_showsplash();
 //BA.debugLineNum = 91;BA.debugLine="tmrSplash.Initialize(\"DoSomething\",300)";
_tmrsplash.Initialize(processBA,"DoSomething",(long) (300));
 //BA.debugLineNum = 92;BA.debugLine="tmrSplash.Enabled = True";
_tmrsplash.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 93;BA.debugLine="GlobalVar.DBVersion = DBaseFunctions.GetDBVersio";
mostCurrent._globalvar._dbversion /*int*/  = mostCurrent._dbasefunctions._getdbversionno /*int*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 94;BA.debugLine="LogColor ($\"App Version: \"$ & Application.Versio";
anywheresoftware.b4a.keywords.Common.LogImpl("7131099",("App Version: ")+BA.NumberToString(anywheresoftware.b4a.keywords.Common.Application.getVersionCode()),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 95;BA.debugLine="LogColor ($\"DBase Version: \"$ & GlobalVar.DBVers";
anywheresoftware.b4a.keywords.Common.LogImpl("7131100",("DBase Version: ")+BA.NumberToString(mostCurrent._globalvar._dbversion /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 96;BA.debugLine="If GlobalVar.DBVersion = 0 Or GlobalVar.DBVersio";
if (mostCurrent._globalvar._dbversion /*int*/ ==0 || mostCurrent._globalvar._dbversion /*int*/ !=anywheresoftware.b4a.keywords.Common.Application.getVersionCode()) { 
 //BA.debugLineNum = 97;BA.debugLine="TheToast.Initialize(Activity)";
mostCurrent._thetoast._initialize /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())));
 //BA.debugLineNum = 98;BA.debugLine="TheToast.DefaultTextColor = Colors.White";
mostCurrent._thetoast._defaulttextcolor /*int*/  = anywheresoftware.b4a.keywords.Common.Colors.White;
 //BA.debugLineNum = 99;BA.debugLine="TheToast.pnl.Color = GlobalVar.RedColor";
mostCurrent._thetoast._pnl /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 100;BA.debugLine="TheToast.DurationMs = 1600";
mostCurrent._thetoast._durationms /*int*/  = (int) (1600);
 //BA.debugLineNum = 101;BA.debugLine="TheToast.Show($\"Database Version not match!\"$ &";
mostCurrent._thetoast._show /*void*/ (("Database Version not match!")+anywheresoftware.b4a.keywords.Common.CRLF+("Please contact Central Billing & IT Department."));
 //BA.debugLineNum = 102;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 };
 //BA.debugLineNum = 106;BA.debugLine="imeKeyboard.Initialize(\"ime\")";
mostCurrent._imekeyboard.Initialize("ime");
 //BA.debugLineNum = 107;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 108;BA.debugLine="csAns.Initialize.Bold.Color(0xFFF5F5DC).Size(16).";
mostCurrent._csans.Initialize().Bold().Color((int) (0xfff5f5dc)).Size((int) (16)).Append(BA.ObjectToCharSequence(("YES"))).PopAll();
 //BA.debugLineNum = 110;BA.debugLine="KVS.Initialize(File.DirInternal, \"operations.dat\"";
mostCurrent._kvs._initialize /*String*/ (processBA,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"operations.dat");
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 122;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 123;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 124;BA.debugLine="If pnlSplash.Visible = True Then Return False";
if (mostCurrent._pnlsplash.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
if (true) return anywheresoftware.b4a.keywords.Common.False;};
 //BA.debugLineNum = 125;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 126;BA.debugLine="snack.Initialize(\"btnClose\", Activity, $\"Close P";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"btnClose",(android.view.View)(mostCurrent._activity.getObject()),("Close Pump Operations?"),mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 127;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 128;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 129;BA.debugLine="snack.SetAction(csAns)";
mostCurrent._snack.SetAction(BA.ObjectToString(mostCurrent._csans));
 //BA.debugLineNum = 131;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 132;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 134;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 136;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 118;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 119;BA.debugLine="CallSubDelayed(Starter, \"StopGPS\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._starter.getObject()),"StopGPS");
 //BA.debugLineNum = 120;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 153;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 154;BA.debugLine="If Result Then";
if (_result) { 
 //BA.debugLineNum = 155;BA.debugLine="If Permission = Starter.RTP.PERMISSION_READ_EXTE";
if ((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 156;BA.debugLine="LogColor($\"Permission to Read External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("7458755",("Permission to Read External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 157;BA.debugLine="ReadStoragePermission = True";
_readstoragepermission = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 159;BA.debugLine="LogColor($\"Permission to Write External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("7458758",("Permission to Write External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 160;BA.debugLine="WriteStoragePermission = True";
_writestoragepermission = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION)) { 
 //BA.debugLineNum = 162;BA.debugLine="LogColor($\"Permission to Access Coarse Location";
anywheresoftware.b4a.keywords.Common.LogImpl("7458761",("Permission to Access Coarse Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 163;BA.debugLine="CoarseLocPermission = True";
_coarselocpermission = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION)) { 
 //BA.debugLineNum = 165;BA.debugLine="LogColor($\"Permission to Access Fine Location G";
anywheresoftware.b4a.keywords.Common.LogImpl("7458764",("Permission to Access Fine Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 166;BA.debugLine="FineLocPermission = True";
_finelocpermission = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 168;BA.debugLine="Starter.StartFLP";
mostCurrent._starter._startflp /*void*/ ();
 }else {
 //BA.debugLineNum = 170;BA.debugLine="ReadStoragePermission = False";
_readstoragepermission = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 171;BA.debugLine="WriteStoragePermission = False";
_writestoragepermission = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 172;BA.debugLine="CoarseLocPermission = False";
_coarselocpermission = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 173;BA.debugLine="FineLocPermission = False";
_finelocpermission = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 174;BA.debugLine="Result = False";
_result = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 176;BA.debugLine="Log (Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("7458775",_permission,0);
 //BA.debugLineNum = 177;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 113;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 114;BA.debugLine="tmrSplash.Enabled = False";
_tmrsplash.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 115;BA.debugLine="pnlSplash.Visible = False";
mostCurrent._pnlsplash.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return "";
}
public static String  _allowgps_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 363;BA.debugLine="Private Sub AllowGPS_OnNegativeClicked (View As Vi";
 //BA.debugLineNum = 364;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 365;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 367;BA.debugLine="StartActivity(Starter.GPS1.LocationSettingsIntent";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._starter._gps1 /*anywheresoftware.b4a.gps.GPS*/ .getLocationSettingsIntent()));
 //BA.debugLineNum = 368;BA.debugLine="End Sub";
return "";
}
public static String  _allowgps_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 370;BA.debugLine="Private Sub AllowGPS_OnPositiveClicked (View As Vi";
 //BA.debugLineNum = 371;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 372;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 373;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 //BA.debugLineNum = 374;BA.debugLine="End Sub";
return "";
}
public static String  _btnclose_click() throws Exception{
 //BA.debugLineNum = 179;BA.debugLine="Private Sub btnClose_Click()";
 //BA.debugLineNum = 180;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 //BA.debugLineNum = 181;BA.debugLine="End Sub";
return "";
}
public static String  _btnlogin_click() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsuser = null;
 //BA.debugLineNum = 183;BA.debugLine="Sub btnLogin_Click";
 //BA.debugLineNum = 185;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtUserName";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtusername.getText()))==0) { 
 //BA.debugLineNum = 186;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 189;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtPassword";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtpassword.getText()))==0) { 
 //BA.debugLineNum = 190;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 193;BA.debugLine="Try";
try { //BA.debugLineNum = 194;BA.debugLine="If DBaseFunctions.IsUserExists(txtUserName.Text)";
if (mostCurrent._dbasefunctions._isuserexists /*boolean*/ (mostCurrent.activityBA,mostCurrent._txtusername.getText())==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 195;BA.debugLine="Dim rsUser As Cursor";
_rsuser = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 196;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblUsers \"";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblUsers "+"WHERE UserName = '"+mostCurrent._txtusername.getText()+"'";
 //BA.debugLineNum = 198;BA.debugLine="rsUser = Starter.DBCon.ExecQuery(Starter.strCri";
_rsuser = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 199;BA.debugLine="LogColor(Starter.strCriteria, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("7589840",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 201;BA.debugLine="If rsUser.RowCount > 0 Then";
if (_rsuser.getRowCount()>0) { 
 //BA.debugLineNum = 202;BA.debugLine="rsUser.Position = 0";
_rsuser.setPosition((int) (0));
 //BA.debugLineNum = 203;BA.debugLine="If rsUser.GetString(\"UserPassword\") <> txtPass";
if ((_rsuser.GetString("UserPassword")).equals(mostCurrent._txtpassword.getText()) == false) { 
 //BA.debugLineNum = 205;BA.debugLine="txtPassword.Text = \"\"";
mostCurrent._txtpassword.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 206;BA.debugLine="txtPassword.RequestFocus";
mostCurrent._txtpassword.RequestFocus();
 //BA.debugLineNum = 207;BA.debugLine="imeKeyboard.ShowKeyboard(txtPassword)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._txtpassword.getObject()));
 //BA.debugLineNum = 208;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 210;BA.debugLine="GlobalVar.UserID = DBaseFunctions.GetUserID(t";
mostCurrent._globalvar._userid /*int*/  = mostCurrent._dbasefunctions._getuserid /*int*/ (mostCurrent.activityBA,mostCurrent._txtusername.getText(),mostCurrent._txtpassword.getText());
 //BA.debugLineNum = 211;BA.debugLine="If DBaseFunctions.isGetUserInfo(GlobalVar.Use";
if (mostCurrent._dbasefunctions._isgetuserinfo /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._userid /*int*/ )==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 212;BA.debugLine="If DBaseFunctions.isGetBranchInfo(GlobalVar.B";
if (mostCurrent._dbasefunctions._isgetbranchinfo /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._branchid /*int*/ )==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 213;BA.debugLine="GlobalVar.SysMode = DBaseFunctions.GetSystemM";
mostCurrent._globalvar._sysmode /*int*/  = mostCurrent._dbasefunctions._getsystemmode /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._branchid /*int*/ );
 //BA.debugLineNum = 214;BA.debugLine="If DBaseFunctions.IsMultiPos(GlobalVar.UserID";
if (mostCurrent._dbasefunctions._ismultipos /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._userid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 215;BA.debugLine="ShowPositionList(GlobalVar.UserID)";
_showpositionlist(mostCurrent._globalvar._userid /*int*/ );
 };
 //BA.debugLineNum = 217;BA.debugLine="tmrSplash.Enabled = False";
_tmrsplash.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 }else {
 //BA.debugLineNum = 220;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 223;BA.debugLine="CheckUser";
_checkuser();
 //BA.debugLineNum = 224;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e38) {
			processBA.setLastException(e38); //BA.debugLineNum = 233;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("7589874",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 235;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.ColorDrawable  _cd() throws Exception{
anywheresoftware.b4a.objects.drawable.ColorDrawable _mcd = null;
 //BA.debugLineNum = 296;BA.debugLine="Private Sub CD As ColorDrawable";
 //BA.debugLineNum = 297;BA.debugLine="Private mCD As ColorDrawable";
_mcd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 298;BA.debugLine="mCD.Initialize(Colors.RGB(240,240,240),0)";
_mcd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (240),(int) (240),(int) (240)),(int) (0));
 //BA.debugLineNum = 299;BA.debugLine="Return mCD";
if (true) return _mcd;
 //BA.debugLineNum = 300;BA.debugLine="End Sub";
return null;
}
public static String  _checkpermissions() throws Exception{
 //BA.debugLineNum = 138;BA.debugLine="Private Sub CheckPermissions";
 //BA.debugLineNum = 139;BA.debugLine="Log(\"Checking Permissions\")";
anywheresoftware.b4a.keywords.Common.LogImpl("7393217","Checking Permissions",0);
 //BA.debugLineNum = 141;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE);
 //BA.debugLineNum = 142;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE);
 //BA.debugLineNum = 143;BA.debugLine="Starter.RTP.GetAllSafeDirsExternal(\"\")";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .GetAllSafeDirsExternal("");
 //BA.debugLineNum = 145;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION);
 //BA.debugLineNum = 146;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 148;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_CAMERA);
 //BA.debugLineNum = 149;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_BODY_SENSORS);
 //BA.debugLineNum = 150;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return "";
}
public static String  _checkuser() throws Exception{
bwsi.PumpOperations.httpjob _job = null;
String _poststring = "";
 //BA.debugLineNum = 589;BA.debugLine="Sub CheckUser";
 //BA.debugLineNum = 590;BA.debugLine="Dim job As HttpJob";
_job = new bwsi.PumpOperations.httpjob();
 //BA.debugLineNum = 591;BA.debugLine="job.Initialize(\"AuthorizedUser\",Me)";
_job._initialize /*String*/ (processBA,"AuthorizedUser",main.getObject());
 //BA.debugLineNum = 593;BA.debugLine="Dim postString As String";
_poststring = "";
 //BA.debugLineNum = 594;BA.debugLine="postString = $\"grant_type=password&client_id=1&cl";
_poststring = ("grant_type=password&client_id=1&client_secret=&"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._globalvar._clientsecretkey /*String*/ ))+"&username="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txtusername.getText()))+"&password="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txtpassword.getText()))+"");
 //BA.debugLineNum = 595;BA.debugLine="LogColor(postString, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("72162694",_poststring,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 597;BA.debugLine="job.PostString(\"https://rcis.bwsi.com.ph/oauth/to";
_job._poststring /*String*/ ("https://rcis.bwsi.com.ph/oauth/token","grant_type=password&client_id=1&client_secret=hMdFbFiwKznxdgzoGAZBLAzv6sLN9TFKlBk4Esz3&username=arnold.fernandez@bwsi.com.ph&password=3923bwsi12");
 //BA.debugLineNum = 600;BA.debugLine="job.GetRequest.SetHeader(\"Content-Type\", \"applica";
_job._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ ().SetHeader("Content-Type","application/json");
 //BA.debugLineNum = 601;BA.debugLine="ProgressDialogShow(\"Sending authentication reques";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("Sending authentication request..."));
 //BA.debugLineNum = 602;BA.debugLine="End Sub";
return "";
}
public static String  _dosomething_tick() throws Exception{
 //BA.debugLineNum = 487;BA.debugLine="Private Sub DoSomething_Tick";
 //BA.debugLineNum = 488;BA.debugLine="Log(\"WORKING...\")";
anywheresoftware.b4a.keywords.Common.LogImpl("71703937","WORKING...",0);
 //BA.debugLineNum = 489;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 32;BA.debugLine="Dim mySnack As DSSnackbar";
mostCurrent._mysnack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Dim imeKeyboard As IME";
mostCurrent._imekeyboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 35;BA.debugLine="Private btnLogin As B4XView";
mostCurrent._btnlogin = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private txtPassword As EditText";
mostCurrent._txtpassword = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private txtUserName As EditText";
mostCurrent._txtusername = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Dim CDtxtBox As ColorDrawable";
mostCurrent._cdtxtbox = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 40;BA.debugLine="Private lblCheck As B4XView";
mostCurrent._lblcheck = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Dim isChecked As Boolean";
_ischecked = false;
 //BA.debugLineNum = 44;BA.debugLine="Private csAns As CSBuilder";
mostCurrent._csans = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 45;BA.debugLine="Dim snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private vibration As B4Avibrate";
mostCurrent._vibration = new com.johan.Vibrate.Vibrate();
 //BA.debugLineNum = 47;BA.debugLine="Private vibratePattern() As Long";
_vibratepattern = new long[(int) (0)];
;
 //BA.debugLineNum = 48;BA.debugLine="Private TheToast As BCToast";
mostCurrent._thetoast = new bwsi.PumpOperations.bctoast();
 //BA.debugLineNum = 50;BA.debugLine="Private btnBranchSettings As Panel";
mostCurrent._btnbranchsettings = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private btnIPSettings As Panel";
mostCurrent._btnipsettings = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private pnlSearch As Panel";
mostCurrent._pnlsearch = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private ReadStoragePermission As Boolean";
_readstoragepermission = false;
 //BA.debugLineNum = 57;BA.debugLine="Private WriteStoragePermission As Boolean";
_writestoragepermission = false;
 //BA.debugLineNum = 58;BA.debugLine="Private CoarseLocPermission As Boolean";
_coarselocpermission = false;
 //BA.debugLineNum = 59;BA.debugLine="Private FineLocPermission As Boolean";
_finelocpermission = false;
 //BA.debugLineNum = 61;BA.debugLine="Private KVS As KeyValueStore";
mostCurrent._kvs = new bwsi.PumpOperations.keyvaluestore();
 //BA.debugLineNum = 62;BA.debugLine="Private pnlSplash As Panel";
mostCurrent._pnlsplash = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Private lblShow As B4XView";
mostCurrent._lblshow = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static String  _gpsfontsizebinder_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 376;BA.debugLine="Private Sub GPSFontSizeBinder_OnBindView (View As";
 //BA.debugLineNum = 377;BA.debugLine="Dim alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 378;BA.debugLine="alert.Initialize";
_alert.Initialize();
 //BA.debugLineNum = 379;BA.debugLine="If ViewType = alert.VIEW_TITLE Then ' Title";
if (_viewtype==_alert.VIEW_TITLE) { 
 //BA.debugLineNum = 380;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 385;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 389;BA.debugLine="CS.Initialize.Typeface(Typeface.DEFAULT_BOLD).Ty";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe1b3)))+"  "));
 //BA.debugLineNum = 390;BA.debugLine="CS.Typeface(GlobalVar.Font).Size(22).Append(lbl.";
_cs.Typeface((android.graphics.Typeface)(mostCurrent._globalvar._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (22)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 392;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 394;BA.debugLine="If ViewType = alert.VIEW_MESSAGE Then";
if (_viewtype==_alert.VIEW_MESSAGE) { 
 //BA.debugLineNum = 395;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 396;BA.debugLine="lbl.TextSize = 16";
_lbl.setTextSize((float) (16));
 //BA.debugLineNum = 397;BA.debugLine="lbl.TextColor = Colors.Gray";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 };
 //BA.debugLineNum = 399;BA.debugLine="End Sub";
return "";
}
public static String  _gpsstatus(anywheresoftware.b4a.objects.collections.List _satellites) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
int _i = 0;
anywheresoftware.b4a.gps.GpsSatelliteWrapper _satellite = null;
 //BA.debugLineNum = 320;BA.debugLine="Public Sub GPSStatus (Satellites As List)";
 //BA.debugLineNum = 321;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 322;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 323;BA.debugLine="sb.Append(\"Satellites:\").Append(CRLF)";
_sb.Append("Satellites:").Append(anywheresoftware.b4a.keywords.Common.CRLF);
 //BA.debugLineNum = 324;BA.debugLine="For i = 0 To Satellites.Size - 1";
{
final int step4 = 1;
final int limit4 = (int) (_satellites.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit4 ;_i = _i + step4 ) {
 //BA.debugLineNum = 325;BA.debugLine="Dim Satellite As GPSSatellite = Satellites.Get(i";
_satellite = new anywheresoftware.b4a.gps.GpsSatelliteWrapper();
_satellite = (anywheresoftware.b4a.gps.GpsSatelliteWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.gps.GpsSatelliteWrapper(), (android.location.GpsSatellite)(_satellites.Get(_i)));
 //BA.debugLineNum = 326;BA.debugLine="sb.Append(CRLF).Append(Satellite.Prn).Append($\"";
_sb.Append(anywheresoftware.b4a.keywords.Common.CRLF).Append(BA.NumberToString(_satellite.getPrn())).Append((" "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.2",(Object)(_satellite.getSnr()))+"")).Append(" ").Append(BA.ObjectToString(_satellite.getUsedInFix()));
 //BA.debugLineNum = 327;BA.debugLine="sb.Append(\" \").Append($\" $1.2{Satellite.Azimuth}";
_sb.Append(" ").Append((" "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.2",(Object)(_satellite.getAzimuth()))+"")).Append((" "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.2",(Object)(_satellite.getElevation()))+""));
 }
};
 //BA.debugLineNum = 329;BA.debugLine="Log(sb.ToString)";
anywheresoftware.b4a.keywords.Common.LogImpl("71048585",_sb.ToString(),0);
 //BA.debugLineNum = 331;BA.debugLine="End Sub";
return "";
}
public static boolean  _isthereuserdata() throws Exception{
boolean _bretval = false;
 //BA.debugLineNum = 469;BA.debugLine="Private Sub IsThereUserData As Boolean";
 //BA.debugLineNum = 470;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 471;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 472;BA.debugLine="Try";
try { //BA.debugLineNum = 473;BA.debugLine="If KVS.ContainsKey(\"user_data\") Then";
if (mostCurrent._kvs._containskey /*boolean*/ ("user_data")) { 
 //BA.debugLineNum = 474;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 476;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e10) {
			processBA.setLastException(e10); //BA.debugLineNum = 479;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("71638410",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 481;BA.debugLine="Log($\"User Data \"$ & bRetVal)";
anywheresoftware.b4a.keywords.Common.LogImpl("71638412",("User Data ")+BA.ObjectToString(_bretval),0);
 //BA.debugLineNum = 482;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 483;BA.debugLine="End Sub";
return false;
}
public static String  _jobdone(bwsi.PumpOperations.httpjob _job) throws Exception{
String _retval = "";
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 604;BA.debugLine="Sub JobDone (Job As HttpJob)";
 //BA.debugLineNum = 606;BA.debugLine="Dim RetVal As String";
_retval = "";
 //BA.debugLineNum = 608;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 609;BA.debugLine="Select Job.JobName";
switch (BA.switchObjectToInt(_job._jobname /*String*/ ,"GetAccessToken","AuthorizedUser")) {
case 0: {
 //BA.debugLineNum = 611;BA.debugLine="If Job.Success Then";
if (_job._success /*boolean*/ ) { 
 //BA.debugLineNum = 612;BA.debugLine="Dim Parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 613;BA.debugLine="Parser.Initialize(Job.GetString)";
_parser.Initialize(_job._getstring /*String*/ ());
 //BA.debugLineNum = 614;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 615;BA.debugLine="m = Parser.NextObject";
_m = _parser.NextObject();
 }else {
 //BA.debugLineNum = 617;BA.debugLine="ToastMessageShow(\"Error getting access token\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Error getting access token"),anywheresoftware.b4a.keywords.Common.True);
 };
 break; }
case 1: {
 //BA.debugLineNum = 620;BA.debugLine="If Job.Success Then";
if (_job._success /*boolean*/ ) { 
 //BA.debugLineNum = 621;BA.debugLine="ToastMessageShow($\"Authorized User!\"$,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Authorized User!")),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 622;BA.debugLine="RetVal = Job.GetString";
_retval = _job._getstring /*String*/ ();
 //BA.debugLineNum = 623;BA.debugLine="Log(RetVal)";
anywheresoftware.b4a.keywords.Common.LogImpl("72228243",_retval,0);
 //BA.debugLineNum = 624;BA.debugLine="If RetVal = \"[]\" Then";
if ((_retval).equals("[]")) { 
 //BA.debugLineNum = 625;BA.debugLine="Job.Release";
_job._release /*String*/ ();
 //BA.debugLineNum = 626;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 628;BA.debugLine="If SaveUserToken(RetVal, txtUserName.Text, tx";
if (_saveusertoken(_retval,mostCurrent._txtusername.getText(),mostCurrent._txtpassword.getText())==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 629;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 };
 //BA.debugLineNum = 631;BA.debugLine="GlobalVar.UserID = DBaseFunctions.GetUserID(t";
mostCurrent._globalvar._userid /*int*/  = mostCurrent._dbasefunctions._getuserid /*int*/ (mostCurrent.activityBA,mostCurrent._txtusername.getText(),mostCurrent._txtpassword.getText());
 //BA.debugLineNum = 632;BA.debugLine="If DBaseFunctions.isGetUserInfo(GlobalVar.Use";
if (mostCurrent._dbasefunctions._isgetuserinfo /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._userid /*int*/ )==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 633;BA.debugLine="If DBaseFunctions.isGetBranchInfo(GlobalVar.B";
if (mostCurrent._dbasefunctions._isgetbranchinfo /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._branchid /*int*/ )==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 634;BA.debugLine="GlobalVar.SysMode = DBaseFunctions.GetSystemM";
mostCurrent._globalvar._sysmode /*int*/  = mostCurrent._dbasefunctions._getsystemmode /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._branchid /*int*/ );
 //BA.debugLineNum = 635;BA.debugLine="If DBaseFunctions.IsMultiPos(GlobalVar.UserID";
if (mostCurrent._dbasefunctions._ismultipos /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._userid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 636;BA.debugLine="ShowPositionList(GlobalVar.UserID)";
_showpositionlist(mostCurrent._globalvar._userid /*int*/ );
 };
 //BA.debugLineNum = 638;BA.debugLine="tmrSplash.Enabled = False";
_tmrsplash.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 }else {
 //BA.debugLineNum = 642;BA.debugLine="ToastMessageShow($\"Not Authorized User!\"$,True";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Not Authorized User!")),anywheresoftware.b4a.keywords.Common.True);
 };
 break; }
}
;
 //BA.debugLineNum = 645;BA.debugLine="Job.Release";
_job._release /*String*/ ();
 //BA.debugLineNum = 646;BA.debugLine="End Sub";
return "";
}
public static String  _lblcheck_click() throws Exception{
 //BA.debugLineNum = 237;BA.debugLine="Sub lblCheck_Click";
 //BA.debugLineNum = 238;BA.debugLine="If lblCheck.Text = \"\" Then";
if ((mostCurrent._lblcheck.getText()).equals("")) { 
 //BA.debugLineNum = 239;BA.debugLine="lblCheck.Text = Chr(0xE5CA)";
mostCurrent._lblcheck.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe5ca))));
 //BA.debugLineNum = 240;BA.debugLine="txtPassword.PasswordMode = False";
mostCurrent._txtpassword.setPasswordMode(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 242;BA.debugLine="lblCheck.Text = \"\"";
mostCurrent._lblcheck.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 243;BA.debugLine="txtPassword.PasswordMode = True";
mostCurrent._txtpassword.setPasswordMode(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 245;BA.debugLine="End Sub";
return "";
}
public static String  _lblshow_click() throws Exception{
 //BA.debugLineNum = 676;BA.debugLine="Sub lblShow_Click";
 //BA.debugLineNum = 677;BA.debugLine="lblCheck_Click";
_lblcheck_click();
 //BA.debugLineNum = 678;BA.debugLine="End Sub";
return "";
}
public static String  _locationchanged(anywheresoftware.b4a.gps.LocationWrapper _location1) throws Exception{
 //BA.debugLineNum = 333;BA.debugLine="Public Sub LocationChanged(Location1 As Location)";
 //BA.debugLineNum = 334;BA.debugLine="Log($\"Latitude = \"$ & Location1.ConvertToMinutes(";
anywheresoftware.b4a.keywords.Common.LogImpl("71114113",("Latitude = ")+_location1.ConvertToMinutes(_location1.getLatitude())+" - Longitude = "+_location1.ConvertToMinutes(_location1.getLongitude())+("Speed = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.2",(Object)(_location1.getSpeed()))+" m/s "),0);
 //BA.debugLineNum = 335;BA.debugLine="End Sub";
return "";
}
public static String  _pnlsearch_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 251;BA.debugLine="Sub pnlSearch_Touch (Action As Int, X As Float, Y";
 //BA.debugLineNum = 253;BA.debugLine="End Sub";
return "";
}
public static String  _pnlsplash_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 497;BA.debugLine="Private Sub pnlSplash_Touch (Action As Int, X As F";
 //BA.debugLineNum = 499;BA.debugLine="End Sub";
return "";
}
public static String  _posfontsizebinder_onbindactionsheetview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _position,boolean _isnewitem) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
 //BA.debugLineNum = 421;BA.debugLine="Private Sub PosFontSizeBinder_OnBindActionsheetVie";
 //BA.debugLineNum = 422;BA.debugLine="If IsNewItem Then Return";
if (_isnewitem) { 
if (true) return "";};
 //BA.debugLineNum = 425;BA.debugLine="Dim Lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 427;BA.debugLine="Lbl.Typeface = GlobalVar.Font";
_lbl.setTypeface((android.graphics.Typeface)(mostCurrent._globalvar._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 428;BA.debugLine="Lbl.TextSize = 20";
_lbl.setTextSize((float) (20));
 //BA.debugLineNum = 433;BA.debugLine="End Sub";
return "";
}
public static String  _posfontsizebinder_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 401;BA.debugLine="Private Sub PosFontSizeBinder_OnBindView (View As";
 //BA.debugLineNum = 402;BA.debugLine="Dim alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 403;BA.debugLine="alert.Initialize";
_alert.Initialize();
 //BA.debugLineNum = 404;BA.debugLine="If ViewType = alert.VIEW_TITLE Then ' Title";
if (_viewtype==_alert.VIEW_TITLE) { 
 //BA.debugLineNum = 405;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 410;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 414;BA.debugLine="CS.Initialize.Typeface(Typeface.DEFAULT_BOLD).Ty";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (24)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe7fd)))+" "));
 //BA.debugLineNum = 415;BA.debugLine="CS.Typeface(GlobalVar.Font).Size(20).Append(lbl.";
_cs.Typeface((android.graphics.Typeface)(mostCurrent._globalvar._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (20)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 417;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 419;BA.debugLine="End Sub";
return "";
}
public static void  _positionselect_onitemclick(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,String _selection,int _position,long _id) throws Exception{
ResumableSub_PositionSelect_OnItemClick rsub = new ResumableSub_PositionSelect_OnItemClick(null,_view,_selection,_position,_id);
rsub.resume(processBA, null);
}
public static class ResumableSub_PositionSelect_OnItemClick extends BA.ResumableSub {
public ResumableSub_PositionSelect_OnItemClick(bwsi.PumpOperations.main parent,anywheresoftware.b4a.objects.ConcreteViewWrapper _view,String _selection,int _position,long _id) {
this.parent = parent;
this._view = _view;
this._selection = _selection;
this._position = _position;
this._id = _id;
}
bwsi.PumpOperations.main parent;
anywheresoftware.b4a.objects.ConcreteViewWrapper _view;
String _selection;
int _position;
long _id;
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 305;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 306;BA.debugLine="Alert.Initialize.Dismiss2";
_alert.Initialize().Dismiss2();
 //BA.debugLineNum = 307;BA.debugLine="GlobalVar.UserPosID = DBaseFunctions.GetIDByCode(";
parent.mostCurrent._globalvar._userposid /*int*/  = parent.mostCurrent._dbasefunctions._getidbycode /*int*/ (mostCurrent.activityBA,"PosID","tblPositions","PosDesc",_selection);
 //BA.debugLineNum = 308;BA.debugLine="GlobalVar.UserPos = Selection";
parent.mostCurrent._globalvar._userpos /*String*/  = _selection;
 //BA.debugLineNum = 309;BA.debugLine="LogColor(GlobalVar.UserPosID & \" - \" & GlobalVar.";
anywheresoftware.b4a.keywords.Common.LogImpl("7983046",BA.NumberToString(parent.mostCurrent._globalvar._userposid /*int*/ )+" - "+parent.mostCurrent._globalvar._userpos /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 311;BA.debugLine="TheToast.Initialize(Activity)";
parent.mostCurrent._thetoast._initialize /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._activity.getObject())));
 //BA.debugLineNum = 312;BA.debugLine="TheToast.DefaultTextColor = Colors.White";
parent.mostCurrent._thetoast._defaulttextcolor /*int*/  = anywheresoftware.b4a.keywords.Common.Colors.White;
 //BA.debugLineNum = 313;BA.debugLine="TheToast.pnl.Color = GlobalVar.BlueColor";
parent.mostCurrent._thetoast._pnl /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor((int) (parent.mostCurrent._globalvar._bluecolor /*double*/ ));
 //BA.debugLineNum = 314;BA.debugLine="TheToast.Show($\"Login as: \"$ & Selection)";
parent.mostCurrent._thetoast._show /*void*/ (("Login as: ")+_selection);
 //BA.debugLineNum = 316;BA.debugLine="Sleep(1000)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (1000));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 317;BA.debugLine="StartActivity(MainScreen)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._mainscreen.getObject()));
 //BA.debugLineNum = 318;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        b4a.example.dateutils._process_globals();
main._process_globals();
globalvar._process_globals();
myfunctions._process_globals();
dbasefunctions._process_globals();
actnewproduction._process_globals();
mainscreen._process_globals();
actcmjofindings._process_globals();
actdccrjofindings._process_globals();
actgpmcalc._process_globals();
actcriticalpoint._process_globals();
actdcdajofindings._process_globals();
actdebugkeyboard._process_globals();
actgpmhistory._process_globals();
actjo._process_globals();
actjoaccomplishedsas._process_globals();
actjodetails._process_globals();
actjonotification._process_globals();
actjoreasons._process_globals();
actjosummary._process_globals();
actjowithreasons._process_globals();
actmcjofindings._process_globals();
actncjofindings._process_globals();
actnonoperational._process_globals();
actproduction._process_globals();
actpumpoff._process_globals();
actrcjofindings._process_globals();
actrepmain._process_globals();
actsasjofindings._process_globals();
actwaterbalance._process_globals();
addeditchlorinerecord._process_globals();
addeditfmrdg._process_globals();
addeditnonoperational._process_globals();
addeditproblem._process_globals();
addeditpsidistrecord._process_globals();
addeditpsirdg._process_globals();
addedittimerecord._process_globals();
dbutils._process_globals();
edittimerecord._process_globals();
firebasemessaging._process_globals();
miscfunctions._process_globals();
myscale._process_globals();
starter._process_globals();
validation._process_globals();
httputils2service._process_globals();
b4xcollections._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 25;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 26;BA.debugLine="Public Permissions As RuntimePermissions";
_permissions = new anywheresoftware.b4a.objects.RuntimePermissions();
 //BA.debugLineNum = 27;BA.debugLine="Dim InpTyp As SLInpTypeConst";
_inptyp = new bwsi.PumpOperations.slinptypeconst();
 //BA.debugLineNum = 28;BA.debugLine="Dim tmrSplash As Timer";
_tmrsplash = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _retrieveuserdata() throws Exception{
ResumableSub_RetrieveUserData rsub = new ResumableSub_RetrieveUserData(null);
rsub.resume(processBA, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_RetrieveUserData extends BA.ResumableSub {
public ResumableSub_RetrieveUserData(bwsi.PumpOperations.main parent) {
this.parent = parent;
}
bwsi.PumpOperations.main parent;
boolean _blnretval = false;
anywheresoftware.b4a.objects.collections.Map _userdata = null;
anywheresoftware.b4a.objects.collections.Map _transdate = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = -1;
 //BA.debugLineNum = 440;BA.debugLine="Dim blnRetVal As Boolean";
_blnretval = false;
 //BA.debugLineNum = 441;BA.debugLine="Dim UserData, TransDate As Map";
_userdata = new anywheresoftware.b4a.objects.collections.Map();
_transdate = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 443;BA.debugLine="blnRetVal = False";
_blnretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 444;BA.debugLine="UserData.Initialize";
_userdata.Initialize();
 //BA.debugLineNum = 445;BA.debugLine="UserData = KVS.Get(\"user_data\")";
_userdata = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(parent.mostCurrent._kvs._get /*Object*/ ("user_data")));
 //BA.debugLineNum = 448;BA.debugLine="GlobalVar.UserID = UserData.Get(\"UserID\")";
parent.mostCurrent._globalvar._userid /*int*/  = (int)(BA.ObjectToNumber(_userdata.Get((Object)("UserID"))));
 //BA.debugLineNum = 449;BA.debugLine="GlobalVar.UserName = UserData.Get(\"UserName\")";
parent.mostCurrent._globalvar._username /*String*/  = BA.ObjectToString(_userdata.Get((Object)("UserName")));
 //BA.debugLineNum = 451;BA.debugLine="GlobalVar.UserPW = UserData.Get(\"UserPassword\")";
parent.mostCurrent._globalvar._userpw /*String*/  = BA.ObjectToString(_userdata.Get((Object)("UserPassword")));
 //BA.debugLineNum = 452;BA.debugLine="GlobalVar.EmpName = UserData.Get(\"EmpName\")";
parent.mostCurrent._globalvar._empname /*String*/  = BA.ObjectToString(_userdata.Get((Object)("EmpName")));
 //BA.debugLineNum = 453;BA.debugLine="GlobalVar.UserAvatar = UserData.Get(\"UserAvatar\")";
parent.mostCurrent._globalvar._useravatar /*String*/  = BA.ObjectToString(_userdata.Get((Object)("UserAvatar")));
 //BA.debugLineNum = 455;BA.debugLine="GlobalVar.BranchID = UserData.Get(\"BranchID\")";
parent.mostCurrent._globalvar._branchid /*int*/  = (int)(BA.ObjectToNumber(_userdata.Get((Object)("BranchID"))));
 //BA.debugLineNum = 456;BA.debugLine="GlobalVar.BranchName = UserData.Get(\"BranchName\")";
parent.mostCurrent._globalvar._branchname /*String*/  = BA.ObjectToString(_userdata.Get((Object)("BranchName")));
 //BA.debugLineNum = 457;BA.debugLine="GlobalVar.UserPosID = UserData.Get(\"UserPosID\")";
parent.mostCurrent._globalvar._userposid /*int*/  = (int)(BA.ObjectToNumber(_userdata.Get((Object)("UserPosID"))));
 //BA.debugLineNum = 458;BA.debugLine="GlobalVar.UserPos = UserData.Get(\"UserPosition\")";
parent.mostCurrent._globalvar._userpos /*String*/  = BA.ObjectToString(_userdata.Get((Object)("UserPosition")));
 //BA.debugLineNum = 459;BA.debugLine="GlobalVar.SysMode = UserData.Get(\"SysMode\")";
parent.mostCurrent._globalvar._sysmode /*int*/  = (int)(BA.ObjectToNumber(_userdata.Get((Object)("SysMode"))));
 //BA.debugLineNum = 461;BA.debugLine="blnRetVal = True";
_blnretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 462;BA.debugLine="Return blnRetVal";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(_blnretval));return;};
 //BA.debugLineNum = 467;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static boolean  _saveusertoken(String _sdata,String _susername,String _spass) throws Exception{
boolean _bretval = false;
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
String _stokentype = "";
int _iexpiresin = 0;
String _saccesstoken = "";
String _srefreshtoken = "";
 //BA.debugLineNum = 648;BA.debugLine="Private Sub SaveUserToken(sData As String, sUserNa";
 //BA.debugLineNum = 649;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 650;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 651;BA.debugLine="Dim root As Map";
_root = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 653;BA.debugLine="parser.Initialize(sData)";
_parser.Initialize(_sdata);
 //BA.debugLineNum = 654;BA.debugLine="root = parser.NextObject";
_root = _parser.NextObject();
 //BA.debugLineNum = 655;BA.debugLine="Dim sTokenType As String = root.Get(\"token_type\")";
_stokentype = BA.ObjectToString(_root.Get((Object)("token_type")));
 //BA.debugLineNum = 656;BA.debugLine="Dim iExpiresIn As Int = root.Get(\"expires_in\")";
_iexpiresin = (int)(BA.ObjectToNumber(_root.Get((Object)("expires_in"))));
 //BA.debugLineNum = 657;BA.debugLine="Dim sAccessToken As String = root.Get(\"access_tok";
_saccesstoken = BA.ObjectToString(_root.Get((Object)("access_token")));
 //BA.debugLineNum = 658;BA.debugLine="Dim sRefreshToken As String = root.Get(\"refresh_t";
_srefreshtoken = BA.ObjectToString(_root.Get((Object)("refresh_token")));
 //BA.debugLineNum = 660;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 661;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 662;BA.debugLine="Try";
try { //BA.debugLineNum = 664;BA.debugLine="Starter.strCriteria=\"INSERT INTO tblUsers VALUES";
mostCurrent._starter._strcriteria /*String*/  = "INSERT INTO tblUsers VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 //BA.debugLineNum = 665;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(("1")),(Object)(("5")),(Object)(("Fernandez")),(Object)(("Arnold")),(Object)(("Bangit")),anywheresoftware.b4a.keywords.Common.Null,(Object)(("Arnold B. Fernandez")),(Object)(_susername),(Object)(_spass),(Object)(("1")),(Object)(("0")),(Object)(_stokentype),(Object)(_iexpiresin),(Object)(_saccesstoken),(Object)(_srefreshtoken)}));
 //BA.debugLineNum = 666;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 667;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e18) {
			processBA.setLastException(e18); //BA.debugLineNum = 669;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 670;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("72293782",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 672;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 673;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 674;BA.debugLine="End Sub";
return false;
}
public static String  _setsnackbaractioncolor(int _icolor) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.LabelWrapper _textv = null;
 //BA.debugLineNum = 680;BA.debugLine="Private Sub SetSnackBarActionColor(iColor As Int)";
 //BA.debugLineNum = 681;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 683;BA.debugLine="p = snack.View";
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._snack.getView()));
 //BA.debugLineNum = 684;BA.debugLine="For Each v As View In p.GetAllViewsRecursive";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
{
final anywheresoftware.b4a.BA.IterableList group3 = _p.GetAllViewsRecursive();
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_v = (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(group3.Get(index3)));
 //BA.debugLineNum = 685;BA.debugLine="If v Is Label Then";
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 686;BA.debugLine="Dim textv As Label";
_textv = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 687;BA.debugLine="textv = v";
_textv = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_v.getObject()));
 //BA.debugLineNum = 688;BA.debugLine="textv.TextColor = iColor";
_textv.setTextColor(_icolor);
 //BA.debugLineNum = 689;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 692;BA.debugLine="End Sub";
return "";
}
public static String  _showgpswarning() throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 337;BA.debugLine="Private Sub ShowGPSWarning";
 //BA.debugLineNum = 338;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 340;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(("Enable GPS")).SetMessage(("Please Turn On Location Services to,")+anywheresoftware.b4a.keywords.Common.CRLF+("Allow \"BWSI APP\" to")+anywheresoftware.b4a.keywords.Common.CRLF+("Determine Your Location.")).SetPositiveText("Cancel").SetPositiveColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._globalvar._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).SetNegativeText("Settings").SetNegativeColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(mostCurrent._globalvar._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._globalvar._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._globalvar._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"AllowGPS").SetOnNegativeClicked(mostCurrent.activityBA,"AllowGPS").SetOnViewBinder(mostCurrent.activityBA,"GPSFontSizeBinder");
 //BA.debugLineNum = 358;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 359;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 361;BA.debugLine="End Sub";
return "";
}
public static String  _showpositionlist(int _iuserid) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
anywheresoftware.b4a.sql.SQL.CursorWrapper _rspos = null;
anywheresoftware.b4a.objects.collections.List _listdestructive = null;
anywheresoftware.b4a.objects.collections.List _listitems = null;
int _i = 0;
 //BA.debugLineNum = 255;BA.debugLine="Private Sub ShowPositionList (iUserID As Int)";
 //BA.debugLineNum = 256;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 257;BA.debugLine="Private rsPos As Cursor";
_rspos = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 258;BA.debugLine="Dim ListDestructive,ListItems As List";
_listdestructive = new anywheresoftware.b4a.objects.collections.List();
_listitems = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 259;BA.debugLine="ListDestructive.Initialize : ListItems.Initialize";
_listdestructive.Initialize();
 //BA.debugLineNum = 259;BA.debugLine="ListDestructive.Initialize : ListItems.Initialize";
_listitems.Initialize();
 //BA.debugLineNum = 261;BA.debugLine="Try";
try { //BA.debugLineNum = 262;BA.debugLine="Starter.strCriteria = \"SELECT UserPos.UserID, Po";
mostCurrent._starter._strcriteria /*String*/  = "SELECT UserPos.UserID, Positions.PosCode, Positions.PosDesc "+"FROM tblUserPosition AS UserPos "+"INNER JOIN tblPositions AS Positions ON UserPos.PosID = Positions.PosID "+"WHERE UserPos.UserID = "+BA.NumberToString(_iuserid);
 //BA.debugLineNum = 266;BA.debugLine="LogColor(Starter.strCriteria, Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("7851979",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 268;BA.debugLine="rsPos = Starter.DBCon.ExecQuery(Starter.strCrite";
_rspos = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 270;BA.debugLine="If rsPos.RowCount > 0 Then";
if (_rspos.getRowCount()>0) { 
 //BA.debugLineNum = 272;BA.debugLine="For i = 0 To rsPos.RowCount - 1";
{
final int step11 = 1;
final int limit11 = (int) (_rspos.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit11 ;_i = _i + step11 ) {
 //BA.debugLineNum = 273;BA.debugLine="rsPos.Position = i";
_rspos.setPosition(_i);
 //BA.debugLineNum = 274;BA.debugLine="ListItems.Add(rsPos.GetString(\"PosDesc\"))";
_listitems.Add((Object)(_rspos.GetString("PosDesc")));
 }
};
 }else {
 };
 } 
       catch (Exception e18) {
			processBA.setLastException(e18); //BA.debugLineNum = 279;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("7851992",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 281;BA.debugLine="rsPos.Close";
_rspos.Close();
 //BA.debugLineNum = 283;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialog").SetTitle("Select your Position").SetStyle(_alert.getSTYLE_SELECTOR()).SetOthers((java.util.ArrayList)(_listitems.getObject())).SetActionsheetTypeface((android.graphics.Typeface)(mostCurrent._globalvar._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).SetOnItemClickListener(mostCurrent.activityBA,"PositionSelect").SetOnViewBinder(mostCurrent.activityBA,"PosFontSizeBinder");
 //BA.debugLineNum = 292;BA.debugLine="Alert.SetDialogBackground(CD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(_cd().getObject()));
 //BA.debugLineNum = 293;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 294;BA.debugLine="End Sub";
return "";
}
public static void  _showsplash() throws Exception{
ResumableSub_ShowSplash rsub = new ResumableSub_ShowSplash(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_ShowSplash extends BA.ResumableSub {
public ResumableSub_ShowSplash(bwsi.PumpOperations.main parent) {
this.parent = parent;
}
bwsi.PumpOperations.main parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 492;BA.debugLine="pnlSplash.Visible = True";
parent.mostCurrent._pnlsplash.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 493;BA.debugLine="Sleep(3000)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (3000));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 494;BA.debugLine="pnlSplash.Visible = False";
parent.mostCurrent._pnlsplash.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 495;BA.debugLine="tmrSplash.Enabled = False";
parent._tmrsplash.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 496;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _txtpassword_enterpressed() throws Exception{
 //BA.debugLineNum = 247;BA.debugLine="Sub txtPassword_EnterPressed";
 //BA.debugLineNum = 248;BA.debugLine="btnLogin_Click";
_btnlogin_click();
 //BA.debugLineNum = 249;BA.debugLine="End Sub";
return "";
}
public static String  _userwarning(String _stitle,String _smsg) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 503;BA.debugLine="Private Sub UserWarning(sTitle As String, sMsg As";
 //BA.debugLineNum = 504;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 506;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetMessage(_smsg).SetPositiveText("Turn On WiFi").SetPositiveColor(anywheresoftware.b4a.keywords.Common.Colors.Blue).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._globalvar._fontbold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).SetNegativeText("Cancel").SetNegativeColor(anywheresoftware.b4a.keywords.Common.Colors.Red).SetNegativeTypeface((android.graphics.Typeface)(mostCurrent._globalvar._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._globalvar._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._globalvar._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"WarningUser").SetOnNegativeClicked(mostCurrent.activityBA,"WarningUser").SetOnViewBinder(mostCurrent.activityBA,"WarningUser");
 //BA.debugLineNum = 524;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 525;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 527;BA.debugLine="End Sub";
return "";
}
public static String  _warninguser_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 562;BA.debugLine="Private Sub WarningUser_OnBindView (View As View,";
 //BA.debugLineNum = 563;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 564;BA.debugLine="Alert.Initialize";
_alert.Initialize();
 //BA.debugLineNum = 565;BA.debugLine="If ViewType = Alert.VIEW_TITLE Then ' Title";
if (_viewtype==_alert.VIEW_TITLE) { 
 //BA.debugLineNum = 566;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 571;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 575;BA.debugLine="CS.Initialize.Typeface(Typeface.DEFAULT_BOLD).Ty";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe1b3)))+"  "));
 //BA.debugLineNum = 576;BA.debugLine="CS.Typeface(GlobalVar.Font).Size(22).Append(lbl.";
_cs.Typeface((android.graphics.Typeface)(mostCurrent._globalvar._font /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (22)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 578;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 580;BA.debugLine="If ViewType = Alert.VIEW_MESSAGE Then";
if (_viewtype==_alert.VIEW_MESSAGE) { 
 //BA.debugLineNum = 581;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 582;BA.debugLine="lbl.TextSize = 16";
_lbl.setTextSize((float) (16));
 //BA.debugLineNum = 583;BA.debugLine="lbl.TextColor = Colors.Gray";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 };
 //BA.debugLineNum = 585;BA.debugLine="End Sub";
return "";
}
public static String  _warninguser_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 529;BA.debugLine="Private Sub WarningUser_OnNegativeClicked (View As";
 //BA.debugLineNum = 548;BA.debugLine="End Sub";
return "";
}
public static String  _warninguser_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 550;BA.debugLine="Private Sub WarningUser_OnPositiveClicked (View As";
 //BA.debugLineNum = 560;BA.debugLine="End Sub";
return "";
}
}
