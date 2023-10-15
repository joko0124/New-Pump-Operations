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
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font = null;
public de.amberhome.objects.SnackbarWrapper _mysnack = null;
public anywheresoftware.b4a.objects.IME _imekeyboard = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _btnlogin = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _chkshowpass = null;
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
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fontbold = null;
public static boolean _readstoragepermission = false;
public static boolean _writestoragepermission = false;
public static boolean _coarselocpermission = false;
public static boolean _finelocpermission = false;
public bwsi.PumpOperations.keyvaluestore _kvs = null;
public b4a.example.dateutils _dateutils = null;
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

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (actnewproduction.mostCurrent != null);
vis = vis | (actpumpoff.mostCurrent != null);
vis = vis | (addedittimerecord.mostCurrent != null);
vis = vis | (actcmjofindings.mostCurrent != null);
vis = vis | (actcriticalpoint.mostCurrent != null);
vis = vis | (actdccrjofindings.mostCurrent != null);
vis = vis | (actdcdajofindings.mostCurrent != null);
vis = vis | (actdebugkeyboard.mostCurrent != null);
vis = vis | (actgpmcalc.mostCurrent != null);
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
vis = vis | (edittimerecord.mostCurrent != null);
vis = vis | (mainscreen.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 72;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 74;BA.debugLine="Activity.LoadLayout(\"LoginNew\")";
mostCurrent._activity.LoadLayout("LoginNew",mostCurrent.activityBA);
 //BA.debugLineNum = 75;BA.debugLine="DBaseFunctions.GetParameters";
mostCurrent._dbasefunctions._getparameters /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 77;BA.debugLine="InpTyp.Initialize";
_inptyp._initialize /*String*/ (processBA);
 //BA.debugLineNum = 78;BA.debugLine="InpTyp.SetInputType(txtPassword,Array As Int(InpT";
_inptyp._setinputtype /*String*/ (mostCurrent._txtpassword,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_variation_password /*int*/ ()});
 //BA.debugLineNum = 80;BA.debugLine="CDtxtBox.Initialize(Colors.Transparent,0)";
mostCurrent._cdtxtbox.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 82;BA.debugLine="txtUserName.Background = CDtxtBox";
mostCurrent._txtusername.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 83;BA.debugLine="txtPassword.Background = CDtxtBox";
mostCurrent._txtpassword.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 84;BA.debugLine="chkShowPass.Checked = False";
mostCurrent._chkshowpass.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 85;BA.debugLine="isChecked = False";
_ischecked = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 86;BA.debugLine="lblCheck.Text = \"\"";
mostCurrent._lblcheck.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 87;BA.debugLine="txtPassword.PasswordMode = True";
mostCurrent._txtpassword.setPasswordMode(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 88;BA.debugLine="txtUserName.Text = \"joko\"";
mostCurrent._txtusername.setText(BA.ObjectToCharSequence("joko"));
 //BA.debugLineNum = 89;BA.debugLine="txtPassword.Text = \"0124\"";
mostCurrent._txtpassword.setText(BA.ObjectToCharSequence("0124"));
 //BA.debugLineNum = 91;BA.debugLine="If FirstTime = True Then";
if (_firsttime==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 92;BA.debugLine="GlobalVar.DBVersion = DBaseFunctions.GetDBVersio";
mostCurrent._globalvar._dbversion /*int*/  = mostCurrent._dbasefunctions._getdbversionno /*int*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 93;BA.debugLine="LogColor ($\"App Version: \"$ & Application.Versio";
anywheresoftware.b4a.keywords.Common.LogImpl("0131093",("App Version: ")+BA.NumberToString(anywheresoftware.b4a.keywords.Common.Application.getVersionCode()),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 94;BA.debugLine="LogColor ($\"DBase Version: \"$ & GlobalVar.DBVers";
anywheresoftware.b4a.keywords.Common.LogImpl("0131094",("DBase Version: ")+BA.NumberToString(mostCurrent._globalvar._dbversion /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 95;BA.debugLine="If GlobalVar.DBVersion = 0 Or GlobalVar.DBVersio";
if (mostCurrent._globalvar._dbversion /*int*/ ==0 || mostCurrent._globalvar._dbversion /*int*/ !=anywheresoftware.b4a.keywords.Common.Application.getVersionCode()) { 
 //BA.debugLineNum = 96;BA.debugLine="TheToast.Initialize(Activity)";
mostCurrent._thetoast._initialize /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())));
 //BA.debugLineNum = 97;BA.debugLine="TheToast.DefaultTextColor = Colors.White";
mostCurrent._thetoast._defaulttextcolor /*int*/  = anywheresoftware.b4a.keywords.Common.Colors.White;
 //BA.debugLineNum = 98;BA.debugLine="TheToast.pnl.Color = GlobalVar.RedColor";
mostCurrent._thetoast._pnl /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 99;BA.debugLine="TheToast.DurationMs = 1600";
mostCurrent._thetoast._durationms /*int*/  = (int) (1600);
 //BA.debugLineNum = 100;BA.debugLine="TheToast.Show($\"Database Version not match!\"$ &";
mostCurrent._thetoast._show /*void*/ (("Database Version not match!")+anywheresoftware.b4a.keywords.Common.CRLF+("Please contact Central Billing & IT Department."));
 //BA.debugLineNum = 101;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 };
 //BA.debugLineNum = 104;BA.debugLine="imeKeyboard.Initialize(\"ime\")";
mostCurrent._imekeyboard.Initialize("ime");
 //BA.debugLineNum = 105;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 106;BA.debugLine="csAns.Initialize.Color(Colors.White).Bold.Append(";
mostCurrent._csans.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Bold().Append(BA.ObjectToCharSequence(("YES"))).PopAll();
 //BA.debugLineNum = 108;BA.debugLine="KVS.Initialize(File.DirInternal, \"operations.dat\"";
mostCurrent._kvs._initialize /*String*/ (processBA,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"operations.dat");
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 138;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 139;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 140;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 141;BA.debugLine="snack.Initialize(\"btnClose\", Activity, $\"Close P";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"btnClose",(android.view.View)(mostCurrent._activity.getObject()),("Close Pump Operations?"),mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 142;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 143;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 144;BA.debugLine="snack.SetAction(csAns)";
mostCurrent._snack.SetAction(BA.ObjectToString(mostCurrent._csans));
 //BA.debugLineNum = 145;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 146;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 148;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 150;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 134;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 135;BA.debugLine="CallSubDelayed(Starter, \"StopGPS\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._starter.getObject()),"StopGPS");
 //BA.debugLineNum = 136;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 167;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 168;BA.debugLine="If Result Then";
if (_result) { 
 //BA.debugLineNum = 169;BA.debugLine="If Permission = Starter.RTP.PERMISSION_READ_EXTE";
if ((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 170;BA.debugLine="LogColor($\"Permission to Read External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("0458755",("Permission to Read External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 171;BA.debugLine="ReadStoragePermission = True";
_readstoragepermission = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 173;BA.debugLine="LogColor($\"Permission to Write External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("0458758",("Permission to Write External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 174;BA.debugLine="WriteStoragePermission = True";
_writestoragepermission = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION)) { 
 //BA.debugLineNum = 176;BA.debugLine="LogColor($\"Permission to Access Coarse Location";
anywheresoftware.b4a.keywords.Common.LogImpl("0458761",("Permission to Access Coarse Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 177;BA.debugLine="CoarseLocPermission = True";
_coarselocpermission = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION)) { 
 //BA.debugLineNum = 179;BA.debugLine="LogColor($\"Permission to Access Fine Location G";
anywheresoftware.b4a.keywords.Common.LogImpl("0458764",("Permission to Access Fine Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 180;BA.debugLine="FineLocPermission = True";
_finelocpermission = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 182;BA.debugLine="Starter.StartFLP";
mostCurrent._starter._startflp /*void*/ ();
 }else {
 //BA.debugLineNum = 184;BA.debugLine="ReadStoragePermission = False";
_readstoragepermission = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 185;BA.debugLine="WriteStoragePermission = False";
_writestoragepermission = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 186;BA.debugLine="CoarseLocPermission = False";
_coarselocpermission = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 187;BA.debugLine="FineLocPermission = False";
_finelocpermission = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 188;BA.debugLine="Result = False";
_result = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 190;BA.debugLine="Log (Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("0458775",_permission,0);
 //BA.debugLineNum = 191;BA.debugLine="End Sub";
return "";
}
public static void  _activity_resume() throws Exception{
ResumableSub_Activity_Resume rsub = new ResumableSub_Activity_Resume(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Resume extends BA.ResumableSub {
public ResumableSub_Activity_Resume(bwsi.PumpOperations.main parent) {
this.parent = parent;
}
bwsi.PumpOperations.main parent;
String _permission = "";
boolean _result = false;
boolean _bretval = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 112;BA.debugLine="If Starter.GPS1.GPSEnabled = False Then";
if (true) break;

case 1:
//if
this.state = 12;
if (parent.mostCurrent._starter._gps1 /*anywheresoftware.b4a.gps.GPS*/ .getGPSEnabled()==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 12;
 //BA.debugLineNum = 113;BA.debugLine="ShowGPSWarning";
_showgpswarning();
 //BA.debugLineNum = 114;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 116;BA.debugLine="Starter.rtp.CheckAndRequest(Starter.rtp.PERMISSI";
parent.mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,parent.mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 117;BA.debugLine="Wait For Activity_PermissionResult (Permission A";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 26;
return;
case 26:
//C
this.state = 6;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 118;BA.debugLine="If Result Then CallSubDelayed(Starter, \"StartGPS";
if (true) break;

case 6:
//if
this.state = 11;
if (_result) { 
this.state = 8;
;}if (true) break;

case 8:
//C
this.state = 11;
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(parent.mostCurrent._starter.getObject()),"StartGPS");
if (true) break;

case 11:
//C
this.state = 12;
;
 if (true) break;
;
 //BA.debugLineNum = 121;BA.debugLine="If KVS.IsInitialized = False Then";

case 12:
//if
this.state = 25;
if (parent.mostCurrent._kvs.IsInitialized /*boolean*/ ()==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 14;
}else {
this.state = 16;
}if (true) break;

case 14:
//C
this.state = 25;
 //BA.debugLineNum = 122;BA.debugLine="KVS.Initialize(File.DirInternal,\"operations.dat\"";
parent.mostCurrent._kvs._initialize /*String*/ (processBA,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"operations.dat");
 if (true) break;

case 16:
//C
this.state = 17;
 //BA.debugLineNum = 124;BA.debugLine="If IsThereUserData = True Then";
if (true) break;

case 17:
//if
this.state = 24;
if (_isthereuserdata()==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 19;
}if (true) break;

case 19:
//C
this.state = 20;
 //BA.debugLineNum = 125;BA.debugLine="Wait For(RetrieveUserData) Complete(bRetVal As";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, this, _retrieveuserdata());
this.state = 27;
return;
case 27:
//C
this.state = 20;
_bretval = (Boolean) result[0];
;
 //BA.debugLineNum = 126;BA.debugLine="If bRetVal = True Then";
if (true) break;

case 20:
//if
this.state = 23;
if (_bretval==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 22;
}if (true) break;

case 22:
//C
this.state = 23;
 //BA.debugLineNum = 127;BA.debugLine="StartActivity(MainScreen)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._mainscreen.getObject()));
 if (true) break;

case 23:
//C
this.state = 24;
;
 if (true) break;

case 24:
//C
this.state = 25;
;
 if (true) break;

case 25:
//C
this.state = -1;
;
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _complete(boolean _bretval) throws Exception{
}
public static String  _allowgps_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 402;BA.debugLine="Private Sub AllowGPS_OnNegativeClicked (View As Vi";
 //BA.debugLineNum = 403;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 404;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 406;BA.debugLine="StartActivity(Starter.GPS1.LocationSettingsIntent";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._starter._gps1 /*anywheresoftware.b4a.gps.GPS*/ .getLocationSettingsIntent()));
 //BA.debugLineNum = 407;BA.debugLine="End Sub";
return "";
}
public static String  _allowgps_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 409;BA.debugLine="Private Sub AllowGPS_OnPositiveClicked (View As Vi";
 //BA.debugLineNum = 410;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 411;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 412;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 //BA.debugLineNum = 413;BA.debugLine="End Sub";
return "";
}
public static String  _btnbranchsettings_click() throws Exception{
 //BA.debugLineNum = 284;BA.debugLine="Sub btnBranchSettings_Click";
 //BA.debugLineNum = 285;BA.debugLine="MsgboxAsync($\"Branch Settings button clicked...\"$";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence(("Branch Settings button clicked...")),BA.ObjectToCharSequence(("BRANCH")),processBA);
 //BA.debugLineNum = 286;BA.debugLine="End Sub";
return "";
}
public static String  _btnclose_click() throws Exception{
 //BA.debugLineNum = 193;BA.debugLine="Private Sub btnClose_Click()";
 //BA.debugLineNum = 194;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 //BA.debugLineNum = 195;BA.debugLine="End Sub";
return "";
}
public static String  _btnipsettings_click() throws Exception{
 //BA.debugLineNum = 280;BA.debugLine="Sub btnIPSettings_Click";
 //BA.debugLineNum = 281;BA.debugLine="MsgboxAsync($\"IP Settings button clicked...\"$,$\"I";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence(("IP Settings button clicked...")),BA.ObjectToCharSequence(("IP")),processBA);
 //BA.debugLineNum = 282;BA.debugLine="End Sub";
return "";
}
public static String  _btnlogin_click() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsuser = null;
 //BA.debugLineNum = 207;BA.debugLine="Sub btnLogin_Click";
 //BA.debugLineNum = 208;BA.debugLine="Dim rsUser As Cursor";
_rsuser = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 210;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtUserName";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtusername.getText()))==0) { 
 //BA.debugLineNum = 211;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 214;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtPassword";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtpassword.getText()))==0) { 
 //BA.debugLineNum = 215;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 218;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblUsers \" &";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblUsers "+"WHERE UserName = '"+mostCurrent._txtusername.getText()+"'";
 //BA.debugLineNum = 220;BA.debugLine="rsUser = Starter.DBCon.ExecQuery(Starter.strCrite";
_rsuser = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 221;BA.debugLine="If rsUser.RowCount > 0 Then";
if (_rsuser.getRowCount()>0) { 
 //BA.debugLineNum = 222;BA.debugLine="rsUser.Position = 0";
_rsuser.setPosition((int) (0));
 //BA.debugLineNum = 223;BA.debugLine="If rsUser.GetString(\"UserPassword\") <> txtPasswo";
if ((_rsuser.GetString("UserPassword")).equals(mostCurrent._txtpassword.getText()) == false) { 
 //BA.debugLineNum = 225;BA.debugLine="mySnack.Initialize(\"\", Activity, \"Password is I";
mostCurrent._mysnack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),"Password is Incorrect",mostCurrent._mysnack.DURATION_SHORT);
 //BA.debugLineNum = 226;BA.debugLine="MyFunctions.SetSnackBarTextColor(mySnack, Color";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._mysnack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 227;BA.debugLine="MyFunctions.SetSnackBarBackground(mySnack, Glob";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._mysnack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 228;BA.debugLine="mySnack.Show";
mostCurrent._mysnack.Show();
 //BA.debugLineNum = 230;BA.debugLine="txtPassword.Text = \"\"";
mostCurrent._txtpassword.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 231;BA.debugLine="txtPassword.RequestFocus";
mostCurrent._txtpassword.RequestFocus();
 //BA.debugLineNum = 232;BA.debugLine="imeKeyboard.ShowKeyboard(txtPassword)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._txtpassword.getObject()));
 //BA.debugLineNum = 233;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 235;BA.debugLine="GlobalVar.UserID = DBaseFunctions.GetUserID(txt";
mostCurrent._globalvar._userid /*int*/  = mostCurrent._dbasefunctions._getuserid /*int*/ (mostCurrent.activityBA,mostCurrent._txtusername.getText(),mostCurrent._txtpassword.getText());
 //BA.debugLineNum = 236;BA.debugLine="If DBaseFunctions.isGetUserInfo(GlobalVar.UserI";
if (mostCurrent._dbasefunctions._isgetuserinfo /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._userid /*int*/ )==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 237;BA.debugLine="If DBaseFunctions.isGetBranchInfo(GlobalVar.Bra";
if (mostCurrent._dbasefunctions._isgetbranchinfo /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._branchid /*int*/ )==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 238;BA.debugLine="GlobalVar.SysMode = DBaseFunctions.GetSystemMod";
mostCurrent._globalvar._sysmode /*int*/  = mostCurrent._dbasefunctions._getsystemmode /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._branchid /*int*/ );
 //BA.debugLineNum = 239;BA.debugLine="If DBaseFunctions.IsMultiPos(GlobalVar.UserID)";
if (mostCurrent._dbasefunctions._ismultipos /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._userid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 240;BA.debugLine="ShowPositionList(GlobalVar.UserID)";
_showpositionlist(mostCurrent._globalvar._userid /*int*/ );
 };
 };
 }else {
 //BA.debugLineNum = 244;BA.debugLine="mySnack.Initialize(\"TryOtherUser\", Activity, \"Us";
mostCurrent._mysnack.Initialize(mostCurrent.activityBA,"TryOtherUser",(android.view.View)(mostCurrent._activity.getObject()),"User Name not Found on Local Database",mostCurrent._mysnack.DURATION_SHORT);
 //BA.debugLineNum = 245;BA.debugLine="MyFunctions.SetSnackBarTextColor(mySnack, Colors";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._mysnack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 246;BA.debugLine="MyFunctions.SetSnackBarBackground(mySnack, Globa";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._mysnack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 247;BA.debugLine="mySnack.SetAction($\"Retry\"$)";
mostCurrent._mysnack.SetAction(("Retry"));
 //BA.debugLineNum = 248;BA.debugLine="mySnack.Show";
mostCurrent._mysnack.Show();
 //BA.debugLineNum = 249;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 251;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.ColorDrawable  _cd() throws Exception{
anywheresoftware.b4a.objects.drawable.ColorDrawable _mcd = null;
 //BA.debugLineNum = 333;BA.debugLine="Private Sub CD As ColorDrawable";
 //BA.debugLineNum = 334;BA.debugLine="Private mCD As ColorDrawable";
_mcd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 335;BA.debugLine="mCD.Initialize(Colors.RGB(240,240,240),0)";
_mcd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (240),(int) (240),(int) (240)),(int) (0));
 //BA.debugLineNum = 336;BA.debugLine="Return mCD";
if (true) return _mcd;
 //BA.debugLineNum = 337;BA.debugLine="End Sub";
return null;
}
public static String  _checkpermissions() throws Exception{
 //BA.debugLineNum = 152;BA.debugLine="Private Sub CheckPermissions";
 //BA.debugLineNum = 153;BA.debugLine="Log(\"Checking Permissions\")";
anywheresoftware.b4a.keywords.Common.LogImpl("0393217","Checking Permissions",0);
 //BA.debugLineNum = 155;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE);
 //BA.debugLineNum = 156;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE);
 //BA.debugLineNum = 157;BA.debugLine="Starter.RTP.GetAllSafeDirsExternal(\"\")";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .GetAllSafeDirsExternal("");
 //BA.debugLineNum = 159;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION);
 //BA.debugLineNum = 160;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 162;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_CAMERA);
 //BA.debugLineNum = 163;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_BODY_SENSORS);
 //BA.debugLineNum = 164;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 165;BA.debugLine="End Sub";
return "";
}
public static String  _chkshowpass_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 197;BA.debugLine="Sub chkShowPass_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 198;BA.debugLine="txtPassword.PasswordMode = Checked";
mostCurrent._txtpassword.setPasswordMode(_checked);
 //BA.debugLineNum = 199;BA.debugLine="If Checked = True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 200;BA.debugLine="lblCheck.Text = \"\"";
mostCurrent._lblcheck.setText(BA.ObjectToCharSequence(""));
 }else {
 //BA.debugLineNum = 202;BA.debugLine="lblCheck.Text = Chr(0xE5CA)";
mostCurrent._lblcheck.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe5ca))));
 };
 //BA.debugLineNum = 204;BA.debugLine="isChecked = Checked";
_ischecked = _checked;
 //BA.debugLineNum = 205;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 33;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 37;BA.debugLine="Dim mySnack As DSSnackbar";
mostCurrent._mysnack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Dim imeKeyboard As IME";
mostCurrent._imekeyboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 40;BA.debugLine="Private btnLogin As B4XView";
mostCurrent._btnlogin = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private chkShowPass As B4XView";
mostCurrent._chkshowpass = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private txtPassword As EditText";
mostCurrent._txtpassword = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private txtUserName As EditText";
mostCurrent._txtusername = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Dim CDtxtBox As ColorDrawable";
mostCurrent._cdtxtbox = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 46;BA.debugLine="Private lblCheck As B4XView";
mostCurrent._lblcheck = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Dim isChecked As Boolean";
_ischecked = false;
 //BA.debugLineNum = 50;BA.debugLine="Private csAns As CSBuilder";
mostCurrent._csans = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 51;BA.debugLine="Dim snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private vibration As B4Avibrate";
mostCurrent._vibration = new com.johan.Vibrate.Vibrate();
 //BA.debugLineNum = 53;BA.debugLine="Private vibratePattern() As Long";
_vibratepattern = new long[(int) (0)];
;
 //BA.debugLineNum = 54;BA.debugLine="Private TheToast As BCToast";
mostCurrent._thetoast = new bwsi.PumpOperations.bctoast();
 //BA.debugLineNum = 56;BA.debugLine="Private btnBranchSettings As Panel";
mostCurrent._btnbranchsettings = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private btnIPSettings As Panel";
mostCurrent._btnipsettings = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Private pnlSearch As Panel";
mostCurrent._pnlsearch = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private FontBold As Typeface = Typeface.LoadFromA";
mostCurrent._fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont_bold.ttf")));
 //BA.debugLineNum = 63;BA.debugLine="Private ReadStoragePermission As Boolean";
_readstoragepermission = false;
 //BA.debugLineNum = 64;BA.debugLine="Private WriteStoragePermission As Boolean";
_writestoragepermission = false;
 //BA.debugLineNum = 65;BA.debugLine="Private CoarseLocPermission As Boolean";
_coarselocpermission = false;
 //BA.debugLineNum = 66;BA.debugLine="Private FineLocPermission As Boolean";
_finelocpermission = false;
 //BA.debugLineNum = 68;BA.debugLine="Private KVS As KeyValueStore";
mostCurrent._kvs = new bwsi.PumpOperations.keyvaluestore();
 //BA.debugLineNum = 70;BA.debugLine="End Sub";
return "";
}
public static String  _gpsfontsizebinder_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 415;BA.debugLine="Private Sub GPSFontSizeBinder_OnBindView (View As";
 //BA.debugLineNum = 416;BA.debugLine="Dim alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 417;BA.debugLine="alert.Initialize";
_alert.Initialize();
 //BA.debugLineNum = 418;BA.debugLine="If ViewType = alert.VIEW_TITLE Then ' Title";
if (_viewtype==_alert.VIEW_TITLE) { 
 //BA.debugLineNum = 419;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 424;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 428;BA.debugLine="CS.Initialize.Typeface(Typeface.DEFAULT_BOLD).Ty";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe1b3)))+"  "));
 //BA.debugLineNum = 429;BA.debugLine="CS.Typeface(Font).Size(22).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(_font.getObject())).Size((int) (22)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 431;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 433;BA.debugLine="If ViewType = alert.VIEW_MESSAGE Then";
if (_viewtype==_alert.VIEW_MESSAGE) { 
 //BA.debugLineNum = 434;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 435;BA.debugLine="lbl.TextSize = 16";
_lbl.setTextSize((float) (16));
 //BA.debugLineNum = 436;BA.debugLine="lbl.TextColor = Colors.Gray";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 };
 //BA.debugLineNum = 438;BA.debugLine="End Sub";
return "";
}
public static String  _gpsstatus(anywheresoftware.b4a.objects.collections.List _satellites) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
int _i = 0;
anywheresoftware.b4a.gps.GpsSatelliteWrapper _satellite = null;
 //BA.debugLineNum = 356;BA.debugLine="Public Sub GPSStatus (Satellites As List)";
 //BA.debugLineNum = 357;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 358;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 359;BA.debugLine="sb.Append(\"Satellites:\").Append(CRLF)";
_sb.Append("Satellites:").Append(anywheresoftware.b4a.keywords.Common.CRLF);
 //BA.debugLineNum = 360;BA.debugLine="For i = 0 To Satellites.Size - 1";
{
final int step4 = 1;
final int limit4 = (int) (_satellites.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit4 ;_i = _i + step4 ) {
 //BA.debugLineNum = 361;BA.debugLine="Dim Satellite As GPSSatellite = Satellites.Get(i";
_satellite = new anywheresoftware.b4a.gps.GpsSatelliteWrapper();
_satellite = (anywheresoftware.b4a.gps.GpsSatelliteWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.gps.GpsSatelliteWrapper(), (android.location.GpsSatellite)(_satellites.Get(_i)));
 //BA.debugLineNum = 362;BA.debugLine="sb.Append(CRLF).Append(Satellite.Prn).Append($\"";
_sb.Append(anywheresoftware.b4a.keywords.Common.CRLF).Append(BA.NumberToString(_satellite.getPrn())).Append((" "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.2",(Object)(_satellite.getSnr()))+"")).Append(" ").Append(BA.ObjectToString(_satellite.getUsedInFix()));
 //BA.debugLineNum = 363;BA.debugLine="sb.Append(\" \").Append($\" $1.2{Satellite.Azimuth}";
_sb.Append(" ").Append((" "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.2",(Object)(_satellite.getAzimuth()))+"")).Append((" "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.2",(Object)(_satellite.getElevation()))+""));
 }
};
 //BA.debugLineNum = 365;BA.debugLine="Log(sb.ToString)";
anywheresoftware.b4a.keywords.Common.LogImpl("01376265",_sb.ToString(),0);
 //BA.debugLineNum = 367;BA.debugLine="End Sub";
return "";
}
public static boolean  _isthereuserdata() throws Exception{
boolean _bretval = false;
 //BA.debugLineNum = 508;BA.debugLine="Private Sub IsThereUserData As Boolean";
 //BA.debugLineNum = 509;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 510;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 511;BA.debugLine="Try";
try { //BA.debugLineNum = 512;BA.debugLine="If KVS.ContainsKey(\"user_data\") Then";
if (mostCurrent._kvs._containskey /*boolean*/ ("user_data")) { 
 //BA.debugLineNum = 513;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 515;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e10) {
			processBA.setLastException(e10); //BA.debugLineNum = 518;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("01966090",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 520;BA.debugLine="Log($\"User Data \"$ & bRetVal)";
anywheresoftware.b4a.keywords.Common.LogImpl("01966092",("User Data ")+BA.ObjectToString(_bretval),0);
 //BA.debugLineNum = 521;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 522;BA.debugLine="End Sub";
return false;
}
public static String  _lblcheck_click() throws Exception{
 //BA.debugLineNum = 260;BA.debugLine="Sub lblCheck_Click";
 //BA.debugLineNum = 261;BA.debugLine="If isChecked = False Then";
if (_ischecked==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 262;BA.debugLine="isChecked = True";
_ischecked = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 263;BA.debugLine="lblCheck.Text = \"\"";
mostCurrent._lblcheck.setText(BA.ObjectToCharSequence(""));
 }else {
 //BA.debugLineNum = 265;BA.debugLine="isChecked = False";
_ischecked = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 266;BA.debugLine="lblCheck.Text = Chr(0xE5CA)";
mostCurrent._lblcheck.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe5ca))));
 };
 //BA.debugLineNum = 268;BA.debugLine="chkShowPass_CheckedChange(isChecked)";
_chkshowpass_checkedchange(_ischecked);
 //BA.debugLineNum = 269;BA.debugLine="End Sub";
return "";
}
public static String  _locationchanged(anywheresoftware.b4a.gps.LocationWrapper _location1) throws Exception{
 //BA.debugLineNum = 369;BA.debugLine="Public Sub LocationChanged(Location1 As Location)";
 //BA.debugLineNum = 370;BA.debugLine="Log($\"Latitude = \"$ & Location1.ConvertToMinutes(";
anywheresoftware.b4a.keywords.Common.LogImpl("01441793",("Latitude = ")+_location1.ConvertToMinutes(_location1.getLatitude())+" - Longitude = "+_location1.ConvertToMinutes(_location1.getLongitude())+("Speed = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.2",(Object)(_location1.getSpeed()))+" m/s "),0);
 //BA.debugLineNum = 374;BA.debugLine="End Sub";
return "";
}
public static String  _pnlsearch_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 288;BA.debugLine="Sub pnlSearch_Touch (Action As Int, X As Float, Y";
 //BA.debugLineNum = 290;BA.debugLine="End Sub";
return "";
}
public static String  _posfontsizebinder_onbindactionsheetview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _position,boolean _isnewitem) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
 //BA.debugLineNum = 460;BA.debugLine="Private Sub PosFontSizeBinder_OnBindActionsheetVie";
 //BA.debugLineNum = 461;BA.debugLine="If IsNewItem Then Return";
if (_isnewitem) { 
if (true) return "";};
 //BA.debugLineNum = 464;BA.debugLine="Dim Lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 466;BA.debugLine="Lbl.Typeface = Font";
_lbl.setTypeface((android.graphics.Typeface)(_font.getObject()));
 //BA.debugLineNum = 467;BA.debugLine="Lbl.TextSize = 20";
_lbl.setTextSize((float) (20));
 //BA.debugLineNum = 472;BA.debugLine="End Sub";
return "";
}
public static String  _posfontsizebinder_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 440;BA.debugLine="Private Sub PosFontSizeBinder_OnBindView (View As";
 //BA.debugLineNum = 441;BA.debugLine="Dim alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 442;BA.debugLine="alert.Initialize";
_alert.Initialize();
 //BA.debugLineNum = 443;BA.debugLine="If ViewType = alert.VIEW_TITLE Then ' Title";
if (_viewtype==_alert.VIEW_TITLE) { 
 //BA.debugLineNum = 444;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 449;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 453;BA.debugLine="CS.Initialize.Typeface(Typeface.DEFAULT_BOLD).Ty";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (24)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe7fd)))+" "));
 //BA.debugLineNum = 454;BA.debugLine="CS.Typeface(Font).Size(20).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(_font.getObject())).Size((int) (20)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 456;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 458;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 342;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 343;BA.debugLine="Alert.Initialize.Dismiss2";
_alert.Initialize().Dismiss2();
 //BA.debugLineNum = 344;BA.debugLine="GlobalVar.UserPosID = DBaseFunctions.GetIDByCode(";
parent.mostCurrent._globalvar._userposid /*int*/  = parent.mostCurrent._dbasefunctions._getidbycode /*int*/ (mostCurrent.activityBA,"PosID","tblPositions","PosDesc",_selection);
 //BA.debugLineNum = 345;BA.debugLine="GlobalVar.UserPos = Selection";
parent.mostCurrent._globalvar._userpos /*String*/  = _selection;
 //BA.debugLineNum = 346;BA.debugLine="LogColor(GlobalVar.UserPosID & \" - \" & GlobalVar.";
anywheresoftware.b4a.keywords.Common.LogImpl("01310726",BA.NumberToString(parent.mostCurrent._globalvar._userposid /*int*/ )+" - "+parent.mostCurrent._globalvar._userpos /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 347;BA.debugLine="TheToast.Initialize(Activity)";
parent.mostCurrent._thetoast._initialize /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(parent.mostCurrent._activity.getObject())));
 //BA.debugLineNum = 348;BA.debugLine="TheToast.DefaultTextColor = Colors.White";
parent.mostCurrent._thetoast._defaulttextcolor /*int*/  = anywheresoftware.b4a.keywords.Common.Colors.White;
 //BA.debugLineNum = 349;BA.debugLine="TheToast.pnl.Color = GlobalVar.BlueColor";
parent.mostCurrent._thetoast._pnl /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor((int) (parent.mostCurrent._globalvar._bluecolor /*double*/ ));
 //BA.debugLineNum = 350;BA.debugLine="TheToast.Show($\"Login as: \"$ & Selection)";
parent.mostCurrent._thetoast._show /*void*/ (("Login as: ")+_selection);
 //BA.debugLineNum = 352;BA.debugLine="Sleep(1000)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (1000));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 353;BA.debugLine="StartActivity(MainScreen)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._mainscreen.getObject()));
 //BA.debugLineNum = 354;BA.debugLine="End Sub";
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
actnewproduction._process_globals();
actpumpoff._process_globals();
dbasefunctions._process_globals();
addedittimerecord._process_globals();
actcmjofindings._process_globals();
actcriticalpoint._process_globals();
actdccrjofindings._process_globals();
actdcdajofindings._process_globals();
actdebugkeyboard._process_globals();
actgpmcalc._process_globals();
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
dbutils._process_globals();
edittimerecord._process_globals();
firebasemessaging._process_globals();
globalvar._process_globals();
mainscreen._process_globals();
miscfunctions._process_globals();
myfunctions._process_globals();
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
 //BA.debugLineNum = 28;BA.debugLine="Public Permissions As RuntimePermissions";
_permissions = new anywheresoftware.b4a.objects.RuntimePermissions();
 //BA.debugLineNum = 29;BA.debugLine="Dim InpTyp As SLInpTypeConst";
_inptyp = new bwsi.PumpOperations.slinptypeconst();
 //BA.debugLineNum = 30;BA.debugLine="Private Font As Typeface = Typeface.LoadFromAsset";
_font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont.ttf")));
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 479;BA.debugLine="Dim blnRetVal As Boolean";
_blnretval = false;
 //BA.debugLineNum = 480;BA.debugLine="Dim UserData, TransDate As Map";
_userdata = new anywheresoftware.b4a.objects.collections.Map();
_transdate = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 482;BA.debugLine="blnRetVal = False";
_blnretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 483;BA.debugLine="UserData.Initialize";
_userdata.Initialize();
 //BA.debugLineNum = 484;BA.debugLine="UserData = KVS.Get(\"user_data\")";
_userdata = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(parent.mostCurrent._kvs._get /*Object*/ ("user_data")));
 //BA.debugLineNum = 487;BA.debugLine="GlobalVar.UserID = UserData.Get(\"UserID\")";
parent.mostCurrent._globalvar._userid /*int*/  = (int)(BA.ObjectToNumber(_userdata.Get((Object)("UserID"))));
 //BA.debugLineNum = 488;BA.debugLine="GlobalVar.UserName = UserData.Get(\"UserName\")";
parent.mostCurrent._globalvar._username /*String*/  = BA.ObjectToString(_userdata.Get((Object)("UserName")));
 //BA.debugLineNum = 490;BA.debugLine="GlobalVar.UserPW = UserData.Get(\"UserPassword\")";
parent.mostCurrent._globalvar._userpw /*String*/  = BA.ObjectToString(_userdata.Get((Object)("UserPassword")));
 //BA.debugLineNum = 491;BA.debugLine="GlobalVar.EmpName = UserData.Get(\"EmpName\")";
parent.mostCurrent._globalvar._empname /*String*/  = BA.ObjectToString(_userdata.Get((Object)("EmpName")));
 //BA.debugLineNum = 492;BA.debugLine="GlobalVar.UserAvatar = UserData.Get(\"UserAvatar\")";
parent.mostCurrent._globalvar._useravatar /*String*/  = BA.ObjectToString(_userdata.Get((Object)("UserAvatar")));
 //BA.debugLineNum = 494;BA.debugLine="GlobalVar.BranchID = UserData.Get(\"BranchID\")";
parent.mostCurrent._globalvar._branchid /*int*/  = (int)(BA.ObjectToNumber(_userdata.Get((Object)("BranchID"))));
 //BA.debugLineNum = 495;BA.debugLine="GlobalVar.BranchName = UserData.Get(\"BranchName\")";
parent.mostCurrent._globalvar._branchname /*String*/  = BA.ObjectToString(_userdata.Get((Object)("BranchName")));
 //BA.debugLineNum = 496;BA.debugLine="GlobalVar.UserPosID = UserData.Get(\"UserPosID\")";
parent.mostCurrent._globalvar._userposid /*int*/  = (int)(BA.ObjectToNumber(_userdata.Get((Object)("UserPosID"))));
 //BA.debugLineNum = 497;BA.debugLine="GlobalVar.UserPos = UserData.Get(\"UserPosition\")";
parent.mostCurrent._globalvar._userpos /*String*/  = BA.ObjectToString(_userdata.Get((Object)("UserPosition")));
 //BA.debugLineNum = 498;BA.debugLine="GlobalVar.SysMode = UserData.Get(\"SysMode\")";
parent.mostCurrent._globalvar._sysmode /*int*/  = (int)(BA.ObjectToNumber(_userdata.Get((Object)("SysMode"))));
 //BA.debugLineNum = 500;BA.debugLine="blnRetVal = True";
_blnretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 501;BA.debugLine="Return blnRetVal";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(_blnretval));return;};
 //BA.debugLineNum = 506;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper  _roundbd(anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper _bd,int _dp) throws Exception{
 //BA.debugLineNum = 275;BA.debugLine="Sub RoundBD(BD As BigDecimal, DP As Int) As BigDec";
 //BA.debugLineNum = 276;BA.debugLine="BD.Round(BD.Precision - BD.Scale + DP, BD.ROUND_H";
_bd.Round((int) (_bd.Precision()-_bd.Scale()+_dp),_bd.ROUND_HALF_UP);
 //BA.debugLineNum = 277;BA.debugLine="Return BD";
if (true) return _bd;
 //BA.debugLineNum = 278;BA.debugLine="End Sub";
return null;
}
public static String  _showgpswarning() throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 376;BA.debugLine="Private Sub ShowGPSWarning";
 //BA.debugLineNum = 377;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 379;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(("Enable GPS")).SetMessage(("Please Turn On Location Services to,")+anywheresoftware.b4a.keywords.Common.CRLF+("Allow \"BWSI APP\" to")+anywheresoftware.b4a.keywords.Common.CRLF+("Determine Your Location.")).SetPositiveText("Cancel").SetPositiveColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetNegativeText("Settings").SetNegativeColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(_font.getObject())).SetTitleTypeface((android.graphics.Typeface)(_font.getObject())).SetMessageTypeface((android.graphics.Typeface)(_font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"AllowGPS").SetOnNegativeClicked(mostCurrent.activityBA,"AllowGPS").SetOnViewBinder(mostCurrent.activityBA,"GPSFontSizeBinder");
 //BA.debugLineNum = 397;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 398;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 400;BA.debugLine="End Sub";
return "";
}
public static String  _showpositionlist(int _iuserid) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
anywheresoftware.b4a.sql.SQL.CursorWrapper _rspos = null;
anywheresoftware.b4a.objects.collections.List _listdestructive = null;
anywheresoftware.b4a.objects.collections.List _listitems = null;
int _i = 0;
 //BA.debugLineNum = 292;BA.debugLine="Private Sub ShowPositionList (iUserID As Int)";
 //BA.debugLineNum = 293;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 294;BA.debugLine="Private rsPos As Cursor";
_rspos = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 295;BA.debugLine="Dim ListDestructive,ListItems As List";
_listdestructive = new anywheresoftware.b4a.objects.collections.List();
_listitems = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 296;BA.debugLine="ListDestructive.Initialize : ListItems.Initialize";
_listdestructive.Initialize();
 //BA.debugLineNum = 296;BA.debugLine="ListDestructive.Initialize : ListItems.Initialize";
_listitems.Initialize();
 //BA.debugLineNum = 298;BA.debugLine="Try";
try { //BA.debugLineNum = 299;BA.debugLine="Starter.strCriteria = \"SELECT UserPos.UserID, Po";
mostCurrent._starter._strcriteria /*String*/  = "SELECT UserPos.UserID, Positions.PosCode, Positions.PosDesc "+"FROM tblUserPosition AS UserPos "+"INNER JOIN tblPositions AS Positions ON UserPos.PosID = Positions.PosID "+"WHERE UserPos.UserID = "+BA.NumberToString(_iuserid);
 //BA.debugLineNum = 303;BA.debugLine="LogColor(Starter.strCriteria, Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("01179659",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 305;BA.debugLine="rsPos = Starter.DBCon.ExecQuery(Starter.strCrite";
_rspos = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 307;BA.debugLine="If rsPos.RowCount > 0 Then";
if (_rspos.getRowCount()>0) { 
 //BA.debugLineNum = 309;BA.debugLine="For i = 0 To rsPos.RowCount - 1";
{
final int step11 = 1;
final int limit11 = (int) (_rspos.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit11 ;_i = _i + step11 ) {
 //BA.debugLineNum = 310;BA.debugLine="rsPos.Position = i";
_rspos.setPosition(_i);
 //BA.debugLineNum = 311;BA.debugLine="ListItems.Add(rsPos.GetString(\"PosDesc\"))";
_listitems.Add((Object)(_rspos.GetString("PosDesc")));
 }
};
 }else {
 };
 } 
       catch (Exception e18) {
			processBA.setLastException(e18); //BA.debugLineNum = 316;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("01179672",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 318;BA.debugLine="rsPos.Close";
_rspos.Close();
 //BA.debugLineNum = 320;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialog").SetTitle("Select your Position").SetStyle(_alert.getSTYLE_SELECTOR()).SetOthers((java.util.ArrayList)(_listitems.getObject())).SetActionsheetTypeface((android.graphics.Typeface)(_font.getObject())).SetOnItemClickListener(mostCurrent.activityBA,"PositionSelect").SetOnViewBinder(mostCurrent.activityBA,"PosFontSizeBinder");
 //BA.debugLineNum = 329;BA.debugLine="Alert.SetDialogBackground(CD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(_cd().getObject()));
 //BA.debugLineNum = 330;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 331;BA.debugLine="End Sub";
return "";
}
public static String  _tryotheruser_click() throws Exception{
 //BA.debugLineNum = 253;BA.debugLine="Private Sub TryOtherUser_Click";
 //BA.debugLineNum = 254;BA.debugLine="txtUserName.Text = \"\"";
mostCurrent._txtusername.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 255;BA.debugLine="txtPassword.Text = \"\"";
mostCurrent._txtpassword.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 256;BA.debugLine="txtUserName.RequestFocus";
mostCurrent._txtusername.RequestFocus();
 //BA.debugLineNum = 257;BA.debugLine="imeKeyboard.ShowKeyboard(txtUserName)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._txtusername.getObject()));
 //BA.debugLineNum = 258;BA.debugLine="End Sub";
return "";
}
public static String  _txtpassword_enterpressed() throws Exception{
 //BA.debugLineNum = 271;BA.debugLine="Sub txtPassword_EnterPressed";
 //BA.debugLineNum = 272;BA.debugLine="btnLogin_Click";
_btnlogin_click();
 //BA.debugLineNum = 273;BA.debugLine="End Sub";
return "";
}
}
