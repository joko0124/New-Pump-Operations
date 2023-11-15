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

public class edittimerecord extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static edittimerecord mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.edittimerecord");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (edittimerecord).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.edittimerecord");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.edittimerecord", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (edittimerecord) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (edittimerecord) Resume **");
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
		return edittimerecord.class;
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
            BA.LogInfo("** Activity (edittimerecord) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (edittimerecord) Pause event (activity is not paused). **");
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
            edittimerecord mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (edittimerecord) Resume **");
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
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font = null;
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fontbold = null;
public static bwsi.PumpOperations.slinptypeconst _inptyp = null;
public de.amberhome.objects.appcompat.ACActionBar _actionbarbutton = null;
public de.amberhome.objects.appcompat.ACToolbarDarkWrapper _toolbar = null;
public anywheresoftware.b4a.object.XmlLayoutBuilder _xmlicon = null;
public de.amberhome.materialdialogs.MaterialDialogBuilderWrapper _matdialogbuilder = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdtxtbox = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdfixedtext = null;
public com.johan.Vibrate.Vibrate _vibration = null;
public static long[] _vibratepattern = null;
public de.amberhome.objects.SnackbarWrapper _snack = null;
public anywheresoftware.b4a.objects.CSBuilder _csans = null;
public flm.b4a.maskededittext.MaskedEditTextWrapper _msktimeon = null;
public flm.b4a.maskededittext.MaskedEditTextWrapper _msktimeoff = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _optelectricity = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _optgenerator = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _chkdrain = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtduration = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtpsi = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtdraincum = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtonremarks = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtoffremarks = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnsave = null;
public static String _stimeon = "";
public static String _stimeoff = "";
public static int _ihron = 0;
public static int _ihroff = 0;
public static double _totdrainhrs = 0;
public static double _totdraincum = 0;
public static int _powersourceid = 0;
public static double _totophrs = 0;
public static int _pumppowerstatus = 0;
public anywheresoftware.b4a.objects.IME _imekeyboard = null;
public com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
public b4a.example.dateutils _dateutils = null;
public bwsi.PumpOperations.main _main = null;
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
public bwsi.PumpOperations.firebasemessaging _firebasemessaging = null;
public bwsi.PumpOperations.miscfunctions _miscfunctions = null;
public bwsi.PumpOperations.myscale _myscale = null;
public bwsi.PumpOperations.starter _starter = null;
public bwsi.PumpOperations.validation _validation = null;
public bwsi.PumpOperations.httputils2service _httputils2service = null;
public bwsi.PumpOperations.b4xcollections _b4xcollections = null;

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
 //BA.debugLineNum = 75;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 76;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 77;BA.debugLine="Activity.LoadLayout(\"EditPumpTimeRecords\")";
mostCurrent._activity.LoadLayout("EditPumpTimeRecords",mostCurrent.activityBA);
 //BA.debugLineNum = 79;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 80;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 82;BA.debugLine="GlobalVar.blnNewTime = False";
mostCurrent._globalvar._blnnewtime /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 83;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Append";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("EDIT PUMP TIME RECORD"))).PopAll();
 //BA.debugLineNum = 84;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append($";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 //BA.debugLineNum = 85;BA.debugLine="btnSave.Text = Chr(0xE161) & $\" UPDATE\"$";
mostCurrent._btnsave.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" UPDATE")));
 //BA.debugLineNum = 86;BA.debugLine="ClearDisplay";
_cleardisplay();
 //BA.debugLineNum = 87;BA.debugLine="GetTimeRecord(GlobalVar.TimeDetailID)";
_gettimerecord(mostCurrent._globalvar._timedetailid /*int*/ );
 //BA.debugLineNum = 89;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 90;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 91;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 92;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 94;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 95;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 97;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 98;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 99;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 101;BA.debugLine="InpTyp.Initialize";
_inptyp._initialize /*String*/ (processBA);
 //BA.debugLineNum = 102;BA.debugLine="InpTyp.SetInputType(txtOnRemarks,Array As Int(Inp";
_inptyp._setinputtype /*String*/ (mostCurrent._txtonremarks,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_correct /*int*/ (),_inptyp._type_text_flag_cap_sentences /*int*/ ()});
 //BA.debugLineNum = 104;BA.debugLine="CDtxtBox.Initialize(Colors.Transparent,0)";
mostCurrent._cdtxtbox.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 105;BA.debugLine="cdFixedText.Initialize(GlobalVar.BlueColor,0)";
mostCurrent._cdfixedtext.Initialize((int) (mostCurrent._globalvar._bluecolor /*double*/ ),(int) (0));
 //BA.debugLineNum = 106;BA.debugLine="mskTimeOn.Background = CDtxtBox";
mostCurrent._msktimeon.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 107;BA.debugLine="txtDuration.Background = CDtxtBox";
mostCurrent._txtduration.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 108;BA.debugLine="txtDrainCum.Background = cdFixedText";
mostCurrent._txtdraincum.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdfixedtext.getObject()));
 //BA.debugLineNum = 109;BA.debugLine="txtOnRemarks.Background = CDtxtBox";
mostCurrent._txtonremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 110;BA.debugLine="txtOffRemarks.Background = CDtxtBox";
mostCurrent._txtoffremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 111;BA.debugLine="txtPSI.Background = CDtxtBox";
mostCurrent._txtpsi.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 112;BA.debugLine="imeKeyboard.Initialize(\"\")";
mostCurrent._imekeyboard.Initialize("");
 //BA.debugLineNum = 114;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 115;BA.debugLine="PumpPowerStatus = DBaseFunctions.GetPumpPowerSta";
_pumppowerstatus = mostCurrent._dbasefunctions._getpumppowerstatus /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ );
 };
 //BA.debugLineNum = 118;BA.debugLine="MyFunctions.SetButton(btnSave, 25, 25, 25, 25, 25";
mostCurrent._myfunctions._setbutton /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btnsave.getObject())),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25));
 //BA.debugLineNum = 119;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 120;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
 //BA.debugLineNum = 180;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 182;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 183;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 160;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 161;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 162;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 163;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 165;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 167;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 174;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 134;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 135;BA.debugLine="If Result Then";
if (_result) { 
 //BA.debugLineNum = 136;BA.debugLine="If Permission = Starter.RTP.PERMISSION_READ_EXTE";
if ((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 137;BA.debugLine="LogColor($\"Permission to Read External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("782837507",("Permission to Read External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 138;BA.debugLine="GlobalVar.ReadStoragePermission = True";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 140;BA.debugLine="LogColor($\"Permission to Write External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("782837510",("Permission to Write External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 141;BA.debugLine="GlobalVar.WriteStoragePermission = True";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION)) { 
 //BA.debugLineNum = 143;BA.debugLine="LogColor($\"Permission to Access Coarse Location";
anywheresoftware.b4a.keywords.Common.LogImpl("782837513",("Permission to Access Coarse Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 144;BA.debugLine="GlobalVar.CoarseLocPermission = True";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION)) { 
 //BA.debugLineNum = 146;BA.debugLine="LogColor($\"Permission to Access Fine Location G";
anywheresoftware.b4a.keywords.Common.LogImpl("782837516",("Permission to Access Fine Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 147;BA.debugLine="GlobalVar.FineLocPermission = True";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 149;BA.debugLine="Starter.StartFLP";
mostCurrent._starter._startflp /*void*/ ();
 }else {
 //BA.debugLineNum = 151;BA.debugLine="GlobalVar.ReadStoragePermission = False";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 152;BA.debugLine="GlobalVar.WriteStoragePermission = False";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 153;BA.debugLine="GlobalVar.CoarseLocPermission = False";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 154;BA.debugLine="GlobalVar.FineLocPermission = False";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 155;BA.debugLine="Result = False";
_result = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 157;BA.debugLine="Log (Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("782837527",_permission,0);
 //BA.debugLineNum = 158;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 169;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 170;BA.debugLine="PumpPowerStatus = DBaseFunctions.GetPumpPowerStat";
_pumppowerstatus = mostCurrent._dbasefunctions._getpumppowerstatus /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 171;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
return "";
}
public static String  _addpumptimeonrecords_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 969;BA.debugLine="Private Sub AddPumpTimeOnRecords_ButtonPressed(mDi";
 //BA.debugLineNum = 970;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE)) {
case 0: {
 //BA.debugLineNum = 972;BA.debugLine="imeKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 973;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
}
;
 //BA.debugLineNum = 975;BA.debugLine="End Sub";
return "";
}
public static String  _btnsave_click() throws Exception{
 //BA.debugLineNum = 208;BA.debugLine="Sub btnSave_Click";
 //BA.debugLineNum = 209;BA.debugLine="If Not(IsValidEntries) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_isvalidentries())) { 
if (true) return "";};
 //BA.debugLineNum = 210;BA.debugLine="ConfirmSaveRecords(GlobalVar.blnNewTime)";
_confirmsaverecords(mostCurrent._globalvar._blnnewtime /*boolean*/ );
 //BA.debugLineNum = 211;BA.debugLine="End Sub";
return "";
}
public static String  _checkpermissions() throws Exception{
 //BA.debugLineNum = 122;BA.debugLine="Private Sub CheckPermissions";
 //BA.debugLineNum = 123;BA.debugLine="Log(\"Checking Permissions\")";
anywheresoftware.b4a.keywords.Common.LogImpl("782771969","Checking Permissions",0);
 //BA.debugLineNum = 125;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE);
 //BA.debugLineNum = 126;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE);
 //BA.debugLineNum = 127;BA.debugLine="Starter.RTP.GetAllSafeDirsExternal(\"\")";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .GetAllSafeDirsExternal("");
 //BA.debugLineNum = 129;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION);
 //BA.debugLineNum = 130;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 131;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static String  _chkdefaulttimeon_checkedchange(boolean _checked) throws Exception{
String _shour = "";
String _smin = "";
long _lhour = 0L;
long _lmin = 0L;
 //BA.debugLineNum = 768;BA.debugLine="Sub chkDefaultTimeOn_CheckedChange(Checked As Bool";
 //BA.debugLineNum = 769;BA.debugLine="Dim sHour As String";
_shour = "";
 //BA.debugLineNum = 770;BA.debugLine="Dim sMin As String";
_smin = "";
 //BA.debugLineNum = 771;BA.debugLine="Dim lHour, lMin As Long";
_lhour = 0L;
_lmin = 0L;
 //BA.debugLineNum = 773;BA.debugLine="If Checked = True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 774;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 775;BA.debugLine="lHour = DateTime.GetHour(DateTime.Now)";
_lhour = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 776;BA.debugLine="lMin = DateTime.GetMinute(DateTime.Now)";
_lmin = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 778;BA.debugLine="If GlobalVar.SF.Len(lHour) = 1 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_lhour))==1) { 
 //BA.debugLineNum = 779;BA.debugLine="sHour = $\"0\"$ & lHour";
_shour = ("0")+BA.NumberToString(_lhour);
 }else {
 //BA.debugLineNum = 781;BA.debugLine="sHour = lHour";
_shour = BA.NumberToString(_lhour);
 };
 //BA.debugLineNum = 784;BA.debugLine="If GlobalVar.SF.Len(lMin) = 1 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_lmin))==1) { 
 //BA.debugLineNum = 785;BA.debugLine="sMin = $\"0\"$ & lMin";
_smin = ("0")+BA.NumberToString(_lmin);
 }else {
 //BA.debugLineNum = 787;BA.debugLine="sMin = lMin";
_smin = BA.NumberToString(_lmin);
 };
 //BA.debugLineNum = 790;BA.debugLine="mskTimeOn.Text = sHour & \":\" & sMin";
mostCurrent._msktimeon.setText((Object)(_shour+":"+_smin));
 }else {
 //BA.debugLineNum = 792;BA.debugLine="mskTimeOn.Text = \"__:__\"";
mostCurrent._msktimeon.setText((Object)("__:__"));
 };
 //BA.debugLineNum = 794;BA.debugLine="End Sub";
return "";
}
public static String  _chkdrain_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 194;BA.debugLine="Sub chkDrain_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 195;BA.debugLine="If Checked =True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 196;BA.debugLine="txtDuration.Enabled = True";
mostCurrent._txtduration.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 197;BA.debugLine="txtDuration.RequestFocus";
mostCurrent._txtduration.RequestFocus();
 //BA.debugLineNum = 198;BA.debugLine="imeKeyboard.ShowKeyboard(txtDuration)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._txtduration.getObject()));
 //BA.debugLineNum = 199;BA.debugLine="txtPSI.Enabled = True";
mostCurrent._txtpsi.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 201;BA.debugLine="txtDuration.Enabled = False";
mostCurrent._txtduration.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 202;BA.debugLine="txtPSI.Enabled = False";
mostCurrent._txtpsi.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 203;BA.debugLine="txtDuration.Text = \"\"";
mostCurrent._txtduration.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 204;BA.debugLine="txtDrainCum.Text = \"\"";
mostCurrent._txtdraincum.setText(BA.ObjectToCharSequence(""));
 };
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
return "";
}
public static String  _cleardisplay() throws Exception{
 //BA.debugLineNum = 368;BA.debugLine="Private Sub ClearDisplay";
 //BA.debugLineNum = 369;BA.debugLine="mskTimeOn.Text = \"__:__\"";
mostCurrent._msktimeon.setText((Object)("__:__"));
 //BA.debugLineNum = 370;BA.debugLine="mskTimeOff.Text = \"__:__\"";
mostCurrent._msktimeoff.setText((Object)("__:__"));
 //BA.debugLineNum = 371;BA.debugLine="optElectricity.Checked = True";
mostCurrent._optelectricity.setChecked(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 372;BA.debugLine="txtDuration.Text = \"\"";
mostCurrent._txtduration.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 373;BA.debugLine="txtDrainCum.Text = \"\"";
mostCurrent._txtdraincum.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 374;BA.debugLine="txtOnRemarks.Text = \"\"";
mostCurrent._txtonremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 375;BA.debugLine="txtOffRemarks.Text = \"\"";
mostCurrent._txtoffremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 376;BA.debugLine="End Sub";
return "";
}
public static float  _computetothrs(String _t1,String _t2) throws Exception{
float _dretval = 0f;
long _starttime = 0L;
long _endtime = 0L;
b4a.example.dateutils._period _p = null;
 //BA.debugLineNum = 977;BA.debugLine="Private Sub ComputeTotHrs(T1 As String, T2 As Stri";
 //BA.debugLineNum = 978;BA.debugLine="Dim dRetVal As Float";
_dretval = 0f;
 //BA.debugLineNum = 979;BA.debugLine="Dim StartTime, EndTime As Long";
_starttime = 0L;
_endtime = 0L;
 //BA.debugLineNum = 981;BA.debugLine="Try";
try { //BA.debugLineNum = 982;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 983;BA.debugLine="StartTime = DateTime.TimeParse(T1)";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_t1);
 //BA.debugLineNum = 984;BA.debugLine="EndTime = DateTime.TimeParse(T2)";
_endtime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_t2);
 //BA.debugLineNum = 986;BA.debugLine="Dim p As Period = DateUtils.PeriodBetween(StartT";
_p = mostCurrent._dateutils._periodbetween(mostCurrent.activityBA,_starttime,_endtime);
 //BA.debugLineNum = 988;BA.debugLine="dRetVal = p.Hours + (p.Minutes/60)";
_dretval = (float) (_p.Hours+(_p.Minutes/(double)60));
 } 
       catch (Exception e10) {
			processBA.setLastException(e10); //BA.debugLineNum = 990;BA.debugLine="dRetVal = 0";
_dretval = (float) (0);
 //BA.debugLineNum = 991;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("785000206",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 993;BA.debugLine="Return dRetVal";
if (true) return _dretval;
 //BA.debugLineNum = 994;BA.debugLine="End Sub";
return 0f;
}
public static String  _confirmsaverecords(boolean _baddedit) throws Exception{
String _stitle = "";
String _scontent = "";
 //BA.debugLineNum = 892;BA.debugLine="Private Sub ConfirmSaveRecords(bAddEdit As Boolean";
 //BA.debugLineNum = 894;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 895;BA.debugLine="Dim sTitle, sContent As String";
_stitle = "";
_scontent = "";
 //BA.debugLineNum = 897;BA.debugLine="Select bAddEdit";
switch (BA.switchObjectToInt(_baddedit,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False)) {
case 0: {
 //BA.debugLineNum = 899;BA.debugLine="sTitle = $\"CONFIRM SAVE?\"$";
_stitle = ("CONFIRM SAVE?");
 //BA.debugLineNum = 900;BA.debugLine="sContent = $\"Save the Pump Time On?\"$";
_scontent = ("Save the Pump Time On?");
 break; }
case 1: {
 //BA.debugLineNum = 902;BA.debugLine="sTitle = $\"CONFIRM UPDATE?\"$";
_stitle = ("CONFIRM UPDATE?");
 //BA.debugLineNum = 903;BA.debugLine="sContent = $\"Modified the Pump Time On?\"$";
_scontent = ("Modified the Pump Time On?");
 break; }
}
;
 //BA.debugLineNum = 906;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitleTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetMessageTypeface((android.graphics.Typeface)(_font.getObject())).SetMessage(_scontent).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"PumpOn").SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetOnNegativeClicked(mostCurrent.activityBA,"PumpOn");
 //BA.debugLineNum = 923;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 924;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 925;BA.debugLine="End Sub";
return "";
}
public static boolean  _edittranheader(int _itranheaderid) throws Exception{
boolean _bretval = false;
double _gtotophrs = 0;
double _gtotdrain = 0;
double _gtotduration = 0;
long _lngdatetime = 0L;
String _smodifiedat = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsdetail = null;
 //BA.debugLineNum = 713;BA.debugLine="Private Sub EditTranHeader(iTranHeaderID As Int) A";
 //BA.debugLineNum = 714;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 715;BA.debugLine="Dim GTotOPHrs As Double";
_gtotophrs = 0;
 //BA.debugLineNum = 716;BA.debugLine="Dim GTotDrain, GTotDuration As Double";
_gtotdrain = 0;
_gtotduration = 0;
 //BA.debugLineNum = 718;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 719;BA.debugLine="Dim sModifiedAt As String";
_smodifiedat = "";
 //BA.debugLineNum = 721;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 722;BA.debugLine="DateTime.TimeFormat = \"hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm:ss a");
 //BA.debugLineNum = 723;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 724;BA.debugLine="sModifiedAt = DateTime.Date(lngDateTime) & $\" \"$";
_smodifiedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime)+(" ")+anywheresoftware.b4a.keywords.Common.DateTime.Time(_lngdatetime);
 //BA.debugLineNum = 726;BA.debugLine="Dim rsDetail As Cursor";
_rsdetail = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 728;BA.debugLine="Starter.strCriteria = \"SELECT sum(TotOpHrs) as GT";
mostCurrent._starter._strcriteria /*String*/  = "SELECT sum(TotOpHrs) as GTotOpHrs, sum(DrainTime) as GTotDrainTime, sum(DrainCum) as GTotDrain "+"FROM OnOffDetails WHERE HeaderID = "+BA.NumberToString(_itranheaderid)+" "+"GROUP BY HeaderID";
 //BA.debugLineNum = 732;BA.debugLine="rsDetail = Starter.DBCon.ExecQuery(Starter.strCri";
_rsdetail = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 734;BA.debugLine="If rsDetail.RowCount > 0 Then";
if (_rsdetail.getRowCount()>0) { 
 //BA.debugLineNum = 735;BA.debugLine="rsDetail.Position = 0";
_rsdetail.setPosition((int) (0));
 //BA.debugLineNum = 736;BA.debugLine="GTotOPHrs = rsDetail.GetDouble(\"GTotOpHrs\")";
_gtotophrs = _rsdetail.GetDouble("GTotOpHrs");
 //BA.debugLineNum = 737;BA.debugLine="GTotDuration = rsDetail.GetInt(\"GTotDrainTime\")";
_gtotduration = _rsdetail.GetInt("GTotDrainTime");
 //BA.debugLineNum = 738;BA.debugLine="GTotDrain = rsDetail.GetInt(\"GTotDrain\")";
_gtotdrain = _rsdetail.GetInt("GTotDrain");
 }else {
 //BA.debugLineNum = 740;BA.debugLine="GTotOPHrs = TotOpHrs";
_gtotophrs = _totophrs;
 //BA.debugLineNum = 741;BA.debugLine="GTotDuration = TotDrainHrs";
_gtotduration = _totdrainhrs;
 //BA.debugLineNum = 742;BA.debugLine="GTotDrain = TotDrainCum";
_gtotdrain = _totdraincum;
 };
 //BA.debugLineNum = 744;BA.debugLine="rsDetail.Close";
_rsdetail.Close();
 //BA.debugLineNum = 746;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 747;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 748;BA.debugLine="Try";
try { //BA.debugLineNum = 749;BA.debugLine="Starter.strCriteria = \"UPDATE TranHeader SET \" &";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE TranHeader SET "+"TotOpHrs = ?, "+"TotDrainHrs = ?, "+"TotDrain = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 757;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_gtotophrs),BA.NumberToString(_gtotduration),BA.NumberToString(_gtotdrain),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_smodifiedat}));
 //BA.debugLineNum = 758;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 759;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e32) {
			processBA.setLastException(e32); //BA.debugLineNum = 761;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("783886128",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 762;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 764;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 765;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 766;BA.debugLine="End Sub";
return false;
}
public static void  _gettimerecord(int _idetailedid) throws Exception{
ResumableSub_GetTimeRecord rsub = new ResumableSub_GetTimeRecord(null,_idetailedid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetTimeRecord extends BA.ResumableSub {
public ResumableSub_GetTimeRecord(bwsi.PumpOperations.edittimerecord parent,int _idetailedid) {
this.parent = parent;
this._idetailedid = _idetailedid;
}
bwsi.PumpOperations.edittimerecord parent;
int _idetailedid;
int _ipowersource = 0;
Object _senderfilter = null;
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher2 = null;
int _ihrson = 0;
int _iminson = 0;
String _ampmon = "";
String _sminon = "";
int _ihrsoff = 0;
int _iminsoff = 0;
String _ampmoff = "";
String _sminoff = "";
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
 //BA.debugLineNum = 214;BA.debugLine="Dim iPowerSource As Int";
_ipowersource = 0;
 //BA.debugLineNum = 215;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 216;BA.debugLine="Dim sTimeOn, sTimeOff As String";
parent.mostCurrent._stimeon = "";
parent.mostCurrent._stimeoff = "";
 //BA.debugLineNum = 218;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 219;BA.debugLine="Dim Matcher2 As Matcher";
_matcher2 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 221;BA.debugLine="Dim iHrsOn, iMinsOn As Int";
_ihrson = 0;
_iminson = 0;
 //BA.debugLineNum = 222;BA.debugLine="Dim AmPmOn As String";
_ampmon = "";
 //BA.debugLineNum = 223;BA.debugLine="Dim sMinOn As String";
_sminon = "";
 //BA.debugLineNum = 225;BA.debugLine="Dim iHrsOff, iMinsOff As Int";
_ihrsoff = 0;
_iminsoff = 0;
 //BA.debugLineNum = 226;BA.debugLine="Dim AmPmOff As String";
_ampmoff = "";
 //BA.debugLineNum = 227;BA.debugLine="Dim sMinOff As String";
_sminoff = "";
 //BA.debugLineNum = 229;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 90;
this.catchState = 89;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 89;
 //BA.debugLineNum = 231;BA.debugLine="Starter.strCriteria = \"SELECT Details.DetailID,";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Details.DetailID, Details.HeaderID, Details.PumpOnTime, Details.PumpOffTime, "+"Details.TotOpHrs, Details.PowerSourceID, Details.DrainPSI, Details.DrainTime, Details.DrainCum, "+"Details.TimeOnRemarks, Details.TimeOffRemarks "+"FROM OnOffDetails AS Details "+"WHERE Details.DetailID = "+BA.NumberToString(_idetailedid);
 //BA.debugLineNum = 237;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 238;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 91;
return;
case 91:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 240;BA.debugLine="If Success Then";
if (true) break;

case 4:
//if
this.state = 87;
if (_success) { 
this.state = 6;
}else {
this.state = 86;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 241;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 242;BA.debugLine="sTimeOn = RS.GetString(\"PumpOnTime\")";
parent.mostCurrent._stimeon = _rs.GetString("PumpOnTime");
 //BA.debugLineNum = 243;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 244;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d) (\\S\\S)\"";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d) (\\S\\S)",parent.mostCurrent._stimeon);
 //BA.debugLineNum = 245;BA.debugLine="If Matcher1.Find Then";
if (true) break;

case 7:
//if
this.state = 39;
if (_matcher1.Find()) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 247;BA.debugLine="iHrsOn = Matcher1.Group(1)";
_ihrson = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 248;BA.debugLine="iMinsOn = Matcher1.Group(2)";
_iminson = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 249;BA.debugLine="AmPmOn = Matcher1.Group(3)";
_ampmon = _matcher1.Group((int) (3));
 //BA.debugLineNum = 251;BA.debugLine="LogColor(AmPmOn, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("783427366",_ampmon,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 253;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMinsOn)";
if (true) break;

case 10:
//if
this.state = 15;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_iminson)))==1) { 
this.state = 12;
}else {
this.state = 14;
}if (true) break;

case 12:
//C
this.state = 15;
 //BA.debugLineNum = 254;BA.debugLine="sMinOn = $\"0\"$ & iMinsOn";
_sminon = ("0")+BA.NumberToString(_iminson);
 if (true) break;

case 14:
//C
this.state = 15;
 //BA.debugLineNum = 256;BA.debugLine="sMinOn = iMinsOn";
_sminon = BA.NumberToString(_iminson);
 if (true) break;
;
 //BA.debugLineNum = 259;BA.debugLine="If AmPmOn = \"AM\" Then";

case 15:
//if
this.state = 38;
if ((_ampmon).equals("AM")) { 
this.state = 17;
}else {
this.state = 31;
}if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 260;BA.debugLine="If iHrsOn = 12 Then";
if (true) break;

case 18:
//if
this.state = 29;
if (_ihrson==12) { 
this.state = 20;
}else {
this.state = 22;
}if (true) break;

case 20:
//C
this.state = 29;
 //BA.debugLineNum = 261;BA.debugLine="iHrOn = 0";
parent._ihron = (int) (0);
 //BA.debugLineNum = 262;BA.debugLine="mskTimeOn.Text = $\"0\"$ & iHrOn & $\":\"$ & sMi";
parent.mostCurrent._msktimeon.setText((Object)(("0")+BA.NumberToString(parent._ihron)+(":")+_sminon));
 if (true) break;

case 22:
//C
this.state = 23;
 //BA.debugLineNum = 264;BA.debugLine="iHrOn = iHrsOn";
parent._ihron = _ihrson;
 //BA.debugLineNum = 265;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrOn)";
if (true) break;

case 23:
//if
this.state = 28;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(parent._ihron)))==1) { 
this.state = 25;
}else {
this.state = 27;
}if (true) break;

case 25:
//C
this.state = 28;
 //BA.debugLineNum = 266;BA.debugLine="mskTimeOn.Text = $\"0\"$ & iHrOn & $\":\"$ & sM";
parent.mostCurrent._msktimeon.setText((Object)(("0")+BA.NumberToString(parent._ihron)+(":")+_sminon));
 if (true) break;

case 27:
//C
this.state = 28;
 //BA.debugLineNum = 268;BA.debugLine="mskTimeOn.Text = iHrOn & $\":\"$ & sMinOn";
parent.mostCurrent._msktimeon.setText((Object)(BA.NumberToString(parent._ihron)+(":")+_sminon));
 if (true) break;

case 28:
//C
this.state = 29;
;
 if (true) break;

case 29:
//C
this.state = 38;
;
 if (true) break;

case 31:
//C
this.state = 32;
 //BA.debugLineNum = 272;BA.debugLine="If iHrsOn < 12 Then";
if (true) break;

case 32:
//if
this.state = 37;
if (_ihrson<12) { 
this.state = 34;
}else {
this.state = 36;
}if (true) break;

case 34:
//C
this.state = 37;
 //BA.debugLineNum = 273;BA.debugLine="iHrOn = iHrsOn + 12";
parent._ihron = (int) (_ihrson+12);
 //BA.debugLineNum = 274;BA.debugLine="mskTimeOn.Text = iHrOn & $\":\"$ & sMinOn";
parent.mostCurrent._msktimeon.setText((Object)(BA.NumberToString(parent._ihron)+(":")+_sminon));
 if (true) break;

case 36:
//C
this.state = 37;
 //BA.debugLineNum = 276;BA.debugLine="iHrOn = iHrsOn";
parent._ihron = _ihrson;
 //BA.debugLineNum = 277;BA.debugLine="mskTimeOn.Text = iHrOn & $\":\"$ & sMinOn";
parent.mostCurrent._msktimeon.setText((Object)(BA.NumberToString(parent._ihron)+(":")+_sminon));
 if (true) break;

case 37:
//C
this.state = 38;
;
 //BA.debugLineNum = 279;BA.debugLine="iHrOn = iHrsOn + 12";
parent._ihron = (int) (_ihrson+12);
 //BA.debugLineNum = 280;BA.debugLine="mskTimeOn.Text = iHrOn & $\":\"$ & sMinOn";
parent.mostCurrent._msktimeon.setText((Object)(BA.NumberToString(parent._ihron)+(":")+_sminon));
 if (true) break;

case 38:
//C
this.state = 39;
;
 if (true) break;

case 39:
//C
this.state = 40;
;
 //BA.debugLineNum = 284;BA.debugLine="sTimeOff = RS.GetString(\"PumpOffTime\")";
parent.mostCurrent._stimeoff = _rs.GetString("PumpOffTime");
 //BA.debugLineNum = 285;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 286;BA.debugLine="Matcher2 = Regex.Matcher(\"(\\d\\d):(\\d\\d) (\\S\\S)\"";
_matcher2 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d) (\\S\\S)",parent.mostCurrent._stimeoff);
 //BA.debugLineNum = 287;BA.debugLine="If Matcher2.Find Then";
if (true) break;

case 40:
//if
this.state = 72;
if (_matcher2.Find()) { 
this.state = 42;
}if (true) break;

case 42:
//C
this.state = 43;
 //BA.debugLineNum = 289;BA.debugLine="iHrsOff = Matcher2.Group(1)";
_ihrsoff = (int)(Double.parseDouble(_matcher2.Group((int) (1))));
 //BA.debugLineNum = 290;BA.debugLine="iMinsOff = Matcher2.Group(2)";
_iminsoff = (int)(Double.parseDouble(_matcher2.Group((int) (2))));
 //BA.debugLineNum = 291;BA.debugLine="AmPmOff = Matcher2.Group(3)";
_ampmoff = _matcher2.Group((int) (3));
 //BA.debugLineNum = 293;BA.debugLine="LogColor(AmPmOff, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("783427408",_ampmoff,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 295;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMinsOff";
if (true) break;

case 43:
//if
this.state = 48;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_iminsoff)))==1) { 
this.state = 45;
}else {
this.state = 47;
}if (true) break;

case 45:
//C
this.state = 48;
 //BA.debugLineNum = 296;BA.debugLine="sMinOff = $\"0\"$ & iMinsOff";
_sminoff = ("0")+BA.NumberToString(_iminsoff);
 if (true) break;

case 47:
//C
this.state = 48;
 //BA.debugLineNum = 298;BA.debugLine="sMinOff = iMinsOff";
_sminoff = BA.NumberToString(_iminsoff);
 if (true) break;
;
 //BA.debugLineNum = 301;BA.debugLine="If AmPmOff = \"AM\" Then";

case 48:
//if
this.state = 71;
if ((_ampmoff).equals("AM")) { 
this.state = 50;
}else {
this.state = 64;
}if (true) break;

case 50:
//C
this.state = 51;
 //BA.debugLineNum = 302;BA.debugLine="If iHrsOff = 12 Then";
if (true) break;

case 51:
//if
this.state = 62;
if (_ihrsoff==12) { 
this.state = 53;
}else {
this.state = 55;
}if (true) break;

case 53:
//C
this.state = 62;
 //BA.debugLineNum = 303;BA.debugLine="iHrOff = 0";
parent._ihroff = (int) (0);
 //BA.debugLineNum = 304;BA.debugLine="mskTimeOff.Text = $\"0\"$ & iHrOff & $\":\"$ & s";
parent.mostCurrent._msktimeoff.setText((Object)(("0")+BA.NumberToString(parent._ihroff)+(":")+_sminoff));
 if (true) break;

case 55:
//C
this.state = 56;
 //BA.debugLineNum = 306;BA.debugLine="iHrOff = iHrsOff";
parent._ihroff = _ihrsoff;
 //BA.debugLineNum = 307;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrOff";
if (true) break;

case 56:
//if
this.state = 61;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(parent._ihroff)))==1) { 
this.state = 58;
}else {
this.state = 60;
}if (true) break;

case 58:
//C
this.state = 61;
 //BA.debugLineNum = 308;BA.debugLine="mskTimeOff.Text = $\"0\"$ & iHrOn & $\":\"$ & s";
parent.mostCurrent._msktimeoff.setText((Object)(("0")+BA.NumberToString(parent._ihron)+(":")+_sminoff));
 if (true) break;

case 60:
//C
this.state = 61;
 //BA.debugLineNum = 310;BA.debugLine="mskTimeOff.Text = iHrOff & $\":\"$ & sMinOff";
parent.mostCurrent._msktimeoff.setText((Object)(BA.NumberToString(parent._ihroff)+(":")+_sminoff));
 if (true) break;

case 61:
//C
this.state = 62;
;
 if (true) break;

case 62:
//C
this.state = 71;
;
 if (true) break;

case 64:
//C
this.state = 65;
 //BA.debugLineNum = 314;BA.debugLine="If iHrsOff < 12 Then";
if (true) break;

case 65:
//if
this.state = 70;
if (_ihrsoff<12) { 
this.state = 67;
}else {
this.state = 69;
}if (true) break;

case 67:
//C
this.state = 70;
 //BA.debugLineNum = 315;BA.debugLine="iHrOff = iHrsOff + 12";
parent._ihroff = (int) (_ihrsoff+12);
 //BA.debugLineNum = 316;BA.debugLine="mskTimeOff.Text = iHrOff & $\":\"$ & sMinOff";
parent.mostCurrent._msktimeoff.setText((Object)(BA.NumberToString(parent._ihroff)+(":")+_sminoff));
 if (true) break;

case 69:
//C
this.state = 70;
 //BA.debugLineNum = 318;BA.debugLine="iHrOff = iHrsOff";
parent._ihroff = _ihrsoff;
 //BA.debugLineNum = 319;BA.debugLine="mskTimeOff.Text = iHrOff & $\":\"$ & sMinOff";
parent.mostCurrent._msktimeoff.setText((Object)(BA.NumberToString(parent._ihroff)+(":")+_sminoff));
 if (true) break;

case 70:
//C
this.state = 71;
;
 //BA.debugLineNum = 321;BA.debugLine="iHrOff = iHrsOff + 12";
parent._ihroff = (int) (_ihrsoff+12);
 //BA.debugLineNum = 322;BA.debugLine="mskTimeOff.Text = iHrOff & $\":\"$ & sMinOff";
parent.mostCurrent._msktimeoff.setText((Object)(BA.NumberToString(parent._ihroff)+(":")+_sminoff));
 if (true) break;

case 71:
//C
this.state = 72;
;
 if (true) break;

case 72:
//C
this.state = 73;
;
 //BA.debugLineNum = 326;BA.debugLine="LogColor($\"Time On: \"$ & sTimeOn, Colors.Yellow";
anywheresoftware.b4a.keywords.Common.LogImpl("783427441",("Time On: ")+parent.mostCurrent._stimeon,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 327;BA.debugLine="LogColor($\"Time Off: \"$ & sTimeOff,Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("783427442",("Time Off: ")+parent.mostCurrent._stimeoff,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 329;BA.debugLine="iPowerSource = RS.GetInt(\"PowerSourceID\")";
_ipowersource = _rs.GetInt("PowerSourceID");
 //BA.debugLineNum = 330;BA.debugLine="If iPowerSource = 1 Then";
if (true) break;

case 73:
//if
this.state = 78;
if (_ipowersource==1) { 
this.state = 75;
}else {
this.state = 77;
}if (true) break;

case 75:
//C
this.state = 78;
 //BA.debugLineNum = 331;BA.debugLine="optElectricity.Checked = True";
parent.mostCurrent._optelectricity.setChecked(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 332;BA.debugLine="optGenerator.Checked = False";
parent.mostCurrent._optgenerator.setChecked(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 77:
//C
this.state = 78;
 //BA.debugLineNum = 334;BA.debugLine="optElectricity.Checked = False";
parent.mostCurrent._optelectricity.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 335;BA.debugLine="optGenerator.Checked = True";
parent.mostCurrent._optgenerator.setChecked(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 78:
//C
this.state = 79;
;
 //BA.debugLineNum = 338;BA.debugLine="txtPSI.Text = RS.GetInt(\"DrainPSI\")";
parent.mostCurrent._txtpsi.setText(BA.ObjectToCharSequence(_rs.GetInt("DrainPSI")));
 //BA.debugLineNum = 339;BA.debugLine="txtDuration.Text = RS.GetInt(\"DrainTime\")";
parent.mostCurrent._txtduration.setText(BA.ObjectToCharSequence(_rs.GetInt("DrainTime")));
 //BA.debugLineNum = 340;BA.debugLine="txtDrainCum.Text = RS.GetInt(\"DrainCum\")";
parent.mostCurrent._txtdraincum.setText(BA.ObjectToCharSequence(_rs.GetInt("DrainCum")));
 //BA.debugLineNum = 342;BA.debugLine="If txtDrainCum.Text = Null Or txtDrainCum.Text";
if (true) break;

case 79:
//if
this.state = 84;
if (parent.mostCurrent._txtdraincum.getText()== null || (parent.mostCurrent._txtdraincum.getText()).equals(BA.NumberToString(0)) || parent.mostCurrent._txtduration.getText()== null || (parent.mostCurrent._txtduration.getText()).equals(BA.NumberToString(0))) { 
this.state = 81;
}else {
this.state = 83;
}if (true) break;

case 81:
//C
this.state = 84;
 //BA.debugLineNum = 343;BA.debugLine="chkDrain.Checked = False";
parent.mostCurrent._chkdrain.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 344;BA.debugLine="txtPSI.Text = \"\"";
parent.mostCurrent._txtpsi.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 345;BA.debugLine="txtDuration.Text = \"\"";
parent.mostCurrent._txtduration.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 346;BA.debugLine="txtDrainCum.Text = \"\"";
parent.mostCurrent._txtdraincum.setText(BA.ObjectToCharSequence(""));
 if (true) break;

case 83:
//C
this.state = 84;
 //BA.debugLineNum = 348;BA.debugLine="chkDrain.Checked = True";
parent.mostCurrent._chkdrain.setChecked(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 84:
//C
this.state = 87;
;
 //BA.debugLineNum = 350;BA.debugLine="txtOnRemarks.Text = RS.GetString(\"TimeOnRemarks";
parent.mostCurrent._txtonremarks.setText(BA.ObjectToCharSequence(_rs.GetString("TimeOnRemarks")));
 //BA.debugLineNum = 351;BA.debugLine="txtOffRemarks.Text = RS.GetString(\"TimeOffRemar";
parent.mostCurrent._txtoffremarks.setText(BA.ObjectToCharSequence(_rs.GetString("TimeOffRemarks")));
 if (true) break;

case 86:
//C
this.state = 87;
 //BA.debugLineNum = 353;BA.debugLine="snack.Initialize(\"\", Activity,$\"Error Due to\"$";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("Error Due to")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 354;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 355;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 356;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 357;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("783427472",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 87:
//C
this.state = 90;
;
 if (true) break;

case 89:
//C
this.state = 90;
this.catchState = 0;
 //BA.debugLineNum = 360;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 361;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 362;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 363;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 364;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("783427479",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 90:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 366;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 25;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 26;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 27;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private xmlIcon As XmlLayoutBuilder";
mostCurrent._xmlicon = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 31;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private CD, CDtxtBox, cdFixedText As ColorDrawabl";
mostCurrent._cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdtxtbox = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdfixedtext = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 34;BA.debugLine="Private vibration As B4Avibrate";
mostCurrent._vibration = new com.johan.Vibrate.Vibrate();
 //BA.debugLineNum = 35;BA.debugLine="Private vibratePattern() As Long";
_vibratepattern = new long[(int) (0)];
;
 //BA.debugLineNum = 37;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private csAns As CSBuilder";
mostCurrent._csans = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 42;BA.debugLine="Private mskTimeOn As MaskedEditText";
mostCurrent._msktimeon = new flm.b4a.maskededittext.MaskedEditTextWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private mskTimeOff As MaskedEditText";
mostCurrent._msktimeoff = new flm.b4a.maskededittext.MaskedEditTextWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private optElectricity As B4XView";
mostCurrent._optelectricity = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private optGenerator As B4XView";
mostCurrent._optgenerator = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private chkDrain As B4XView";
mostCurrent._chkdrain = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private txtDuration As EditText";
mostCurrent._txtduration = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private txtPSI As EditText";
mostCurrent._txtpsi = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private txtDrainCum As EditText";
mostCurrent._txtdraincum = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private txtOnRemarks As EditText";
mostCurrent._txtonremarks = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private txtOffRemarks As EditText";
mostCurrent._txtoffremarks = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private btnSave As ACButton";
mostCurrent._btnsave = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private sTimeOn As String";
mostCurrent._stimeon = "";
 //BA.debugLineNum = 59;BA.debugLine="Private sTimeOff As String";
mostCurrent._stimeoff = "";
 //BA.debugLineNum = 60;BA.debugLine="Private iHrOn, iHrOff As Int";
_ihron = 0;
_ihroff = 0;
 //BA.debugLineNum = 62;BA.debugLine="Private TotDrainHrs, TotDrainCum As Double";
_totdrainhrs = 0;
_totdraincum = 0;
 //BA.debugLineNum = 64;BA.debugLine="Private PowerSourceID As Int";
_powersourceid = 0;
 //BA.debugLineNum = 65;BA.debugLine="Private TotOpHrs As Double";
_totophrs = 0;
 //BA.debugLineNum = 67;BA.debugLine="Private PumpPowerStatus As Int";
_pumppowerstatus = 0;
 //BA.debugLineNum = 69;BA.debugLine="Private imeKeyboard As IME";
mostCurrent._imekeyboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 70;BA.debugLine="Private Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public static boolean  _insertnewpumptime() throws Exception{
boolean _bretval = false;
String _sdatetime = "";
long _ldate = 0L;
String _sremarks = "";
String _slocation = "";
 //BA.debugLineNum = 463;BA.debugLine="Private Sub InsertNewPumpTime() As Boolean";
 //BA.debugLineNum = 464;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 466;BA.debugLine="Dim sDateTime As String";
_sdatetime = "";
 //BA.debugLineNum = 467;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 468;BA.debugLine="Dim sRemarks, sLocation As String";
_sremarks = "";
_slocation = "";
 //BA.debugLineNum = 470;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 471;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 472;BA.debugLine="sDateTime = DateTime.Date(lDate)";
_sdatetime = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 474;BA.debugLine="sRemarks = txtOnRemarks.Text";
_sremarks = mostCurrent._txtonremarks.getText();
 //BA.debugLineNum = 476;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 478;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("783689487",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 480;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("783689489",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 481;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("783689490",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 483;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 484;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("783689493",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 486;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 487;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeaderID";
anywheresoftware.b4a.keywords.Common.LogImpl("783689496",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 489;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 490;BA.debugLine="Try";
try { //BA.debugLineNum = 491;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO OnOffDe";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO OnOffDetails VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._tranheaderid /*int*/ ),(Object)(mostCurrent._stimeon),(Object)(("")),(Object)(("0")),(Object)(_powersourceid),(Object)(_totdrainhrs),(Object)(_totdraincum),(Object)(_sremarks),(Object)(("")),(Object)(("0")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_sdatetime),(Object)(_slocation),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(("")),(Object)(("0")),(Object)((""))}));
 //BA.debugLineNum = 494;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 495;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e23) {
			processBA.setLastException(e23); //BA.debugLineNum = 497;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("783689506",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 498;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 500;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 501;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 502;BA.debugLine="End Sub";
return false;
}
public static boolean  _isvalidentries() throws Exception{
 //BA.debugLineNum = 378;BA.debugLine="Private Sub IsValidEntries() As Boolean";
 //BA.debugLineNum = 379;BA.debugLine="LogColor(mskTimeOn.Text, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("783558401",mostCurrent._msktimeon.getText(),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 380;BA.debugLine="Try";
try { //BA.debugLineNum = 381;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(mskTimeOn.";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._msktimeon.getText()))<=0 || (mostCurrent._msktimeon.getText()).equals("__:__")) { 
 //BA.debugLineNum = 382;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Pump Time On cannot";
_requiredmsgbox(("ERROR"),("Pump Time On cannot be blank!"));
 //BA.debugLineNum = 383;BA.debugLine="mskTimeOn.RequestFocus";
mostCurrent._msktimeon.RequestFocus();
 //BA.debugLineNum = 384;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else if(mostCurrent._validation._istime /*boolean*/ (mostCurrent.activityBA,mostCurrent._msktimeon.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 387;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid Pump Time O";
_requiredmsgbox(("ERROR"),("Invalid Pump Time On!"));
 //BA.debugLineNum = 388;BA.debugLine="mskTimeOn.RequestFocus";
mostCurrent._msktimeon.RequestFocus();
 //BA.debugLineNum = 389;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 392;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(mskTimeOff";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._msktimeoff.getText()))<=0 || (mostCurrent._msktimeoff.getText()).equals("__:__")) { 
 //BA.debugLineNum = 393;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Pump Time Off canno";
_requiredmsgbox(("ERROR"),("Pump Time Off cannot be blank!"));
 //BA.debugLineNum = 394;BA.debugLine="mskTimeOff.RequestFocus";
mostCurrent._msktimeoff.RequestFocus();
 //BA.debugLineNum = 395;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else if(mostCurrent._validation._istime /*boolean*/ (mostCurrent.activityBA,mostCurrent._msktimeoff.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 398;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid Pump Time O";
_requiredmsgbox(("ERROR"),("Invalid Pump Time Of!"));
 //BA.debugLineNum = 399;BA.debugLine="mskTimeOff.RequestFocus";
mostCurrent._msktimeoff.RequestFocus();
 //BA.debugLineNum = 400;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 403;BA.debugLine="If optElectricity.Checked = False And optGenerat";
if (mostCurrent._optelectricity.getChecked()==anywheresoftware.b4a.keywords.Common.False && mostCurrent._optgenerator.getChecked()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 404;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"No Power Source sel";
_requiredmsgbox(("ERROR"),("No Power Source selected!"));
 //BA.debugLineNum = 405;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 408;BA.debugLine="If chkDrain.Checked = True Then";
if (mostCurrent._chkdrain.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 409;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtDurati";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtduration.getText()))<=0) { 
 //BA.debugLineNum = 410;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Drain Time in minu";
_requiredmsgbox(("ERROR"),("Drain Time in minutes cannot be blank!"));
 //BA.debugLineNum = 411;BA.debugLine="txtDuration.RequestFocus";
mostCurrent._txtduration.RequestFocus();
 //BA.debugLineNum = 412;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 415;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtPSI.Te";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtpsi.getText()))<=0) { 
 //BA.debugLineNum = 416;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Pump Pressure in P";
_requiredmsgbox(("ERROR"),("Pump Pressure in PSI cannot be blank!"));
 //BA.debugLineNum = 417;BA.debugLine="txtPSI.RequestFocus";
mostCurrent._txtpsi.RequestFocus();
 //BA.debugLineNum = 418;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 421;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtDrainC";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtdraincum.getText()))<=0) { 
 //BA.debugLineNum = 422;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Drain Production i";
_requiredmsgbox(("ERROR"),("Drain Production in CuM cannot be blank!"));
 //BA.debugLineNum = 423;BA.debugLine="txtDrainCum.RequestFocus";
mostCurrent._txtdraincum.RequestFocus();
 //BA.debugLineNum = 424;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 };
 } 
       catch (Exception e43) {
			processBA.setLastException(e43); //BA.debugLineNum = 428;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("783558450",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 429;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 431;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 432;BA.debugLine="End Sub";
return false;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 20;BA.debugLine="Private Font As Typeface = Typeface.LoadFromAsset";
_font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont.ttf")));
 //BA.debugLineNum = 21;BA.debugLine="Private FontBold As Typeface = Typeface.LoadFromA";
_fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont_bold.ttf")));
 //BA.debugLineNum = 22;BA.debugLine="Private InpTyp As SLInpTypeConst";
_inptyp = new bwsi.PumpOperations.slinptypeconst();
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _pumpon_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 928;BA.debugLine="Private Sub PumpOn_OnNegativeClicked (View As View";
 //BA.debugLineNum = 930;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 931;BA.debugLine="End Sub";
return "";
}
public static String  _pumpon_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 933;BA.debugLine="Private Sub PumpOn_OnPositiveClicked (View As View";
 //BA.debugLineNum = 936;BA.debugLine="Alert.Initialize.Dismiss2";
mostCurrent._alert.Initialize().Dismiss2();
 //BA.debugLineNum = 937;BA.debugLine="GlobalVar.blnNewTime = False";
mostCurrent._globalvar._blnnewtime /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 938;BA.debugLine="LogColor($\"Edit Time Record\"$, Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogImpl("784803589",("Edit Time Record"),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 939;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeaderID";
anywheresoftware.b4a.keywords.Common.LogImpl("784803590",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 940;BA.debugLine="LogColor($\"Detail ID: \"$ & GlobalVar.TimeDetailID";
anywheresoftware.b4a.keywords.Common.LogImpl("784803591",("Detail ID: ")+BA.NumberToString(mostCurrent._globalvar._timedetailid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 942;BA.debugLine="If DBaseFunctions.IsPumpTimeOverlappedEdit(DateTi";
if (mostCurrent._dbasefunctions._ispumptimeoverlappededit /*boolean*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(mostCurrent._stimeon),anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(mostCurrent._stimeoff),mostCurrent._globalvar._tranheaderid /*int*/ ,mostCurrent._globalvar._timedetailid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 943;BA.debugLine="RequiredMsgBox($\"E R R O R\"$,$\"Unable to Add New";
_requiredmsgbox(("E R R O R"),("Unable to Add New Pump Time record due to it will Overlap existing records"));
 //BA.debugLineNum = 944;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 947;BA.debugLine="If Not(UpdatePumpTime(GlobalVar.TranHeaderID, Glo";
if (anywheresoftware.b4a.keywords.Common.Not(_updatepumptime(mostCurrent._globalvar._tranheaderid /*int*/ ,mostCurrent._globalvar._timedetailid /*int*/ ))) { 
if (true) return "";};
 //BA.debugLineNum = 948;BA.debugLine="If Not(EditTranHeader(GlobalVar.TranHeaderID)) Th";
if (anywheresoftware.b4a.keywords.Common.Not(_edittranheader(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
if (true) return "";};
 //BA.debugLineNum = 949;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 //BA.debugLineNum = 950;BA.debugLine="End Sub";
return "";
}
public static String  _reqmsg_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 872;BA.debugLine="Private Sub ReqMsg_OnBindView (View As View, ViewT";
 //BA.debugLineNum = 873;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 874;BA.debugLine="Alert.Initialize";
mostCurrent._alert.Initialize();
 //BA.debugLineNum = 875;BA.debugLine="If ViewType = Alert.VIEW_TITLE Then ' Title";
if (_viewtype==mostCurrent._alert.VIEW_TITLE) { 
 //BA.debugLineNum = 876;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 877;BA.debugLine="lbl.TextSize = 30";
_lbl.setTextSize((float) (30));
 //BA.debugLineNum = 878;BA.debugLine="lbl.SetTextColorAnimated(2000,Colors.Magenta)";
_lbl.SetTextColorAnimated((int) (2000),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 881;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 885;BA.debugLine="CS.Initialize.Typeface(Typeface.MATERIALICONS).S";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe88e)))+"  "));
 //BA.debugLineNum = 886;BA.debugLine="CS.Typeface(Font).Size(24).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(_font.getObject())).Size((int) (24)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 888;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 890;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsg_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 867;BA.debugLine="Private Sub RequiredMsg_OnPositiveClicked (View As";
 //BA.debugLineNum = 868;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 869;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 870;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsgbox(String _stitle,String _smsg) throws Exception{
 //BA.debugLineNum = 842;BA.debugLine="Private Sub RequiredMsgBox(sTitle As String, sMsg";
 //BA.debugLineNum = 843;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 844;BA.debugLine="Alert.Initialize.Dismiss2";
mostCurrent._alert.Initialize().Dismiss2();
 //BA.debugLineNum = 846;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._redcolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessage(_smsg).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessageTypeface((android.graphics.Typeface)(_font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"RequiredMsg").SetOnViewBinder(mostCurrent.activityBA,"ReqMsg");
 //BA.debugLineNum = 861;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 862;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 863;BA.debugLine="End Sub";
return "";
}
public static boolean  _savetransheader() throws Exception{
boolean _bretval = false;
long _lngdatetime = 0L;
String _saddedat = "";
 //BA.debugLineNum = 434;BA.debugLine="Private Sub SaveTransHeader() As Boolean";
 //BA.debugLineNum = 435;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 436;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 437;BA.debugLine="Dim sAddedAt As String";
_saddedat = "";
 //BA.debugLineNum = 439;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 440;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 441;BA.debugLine="sAddedAt= DateTime.Date(lngDateTime)";
_saddedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 443;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 444;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 445;BA.debugLine="Try";
try { //BA.debugLineNum = 447;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO TranHea";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO TranHeader VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._branchid /*int*/ ),(Object)(mostCurrent._globalvar._pumphouseid /*int*/ ),(Object)(mostCurrent._globalvar._trandate /*String*/ ),(Object)(("0")),(Object)(("0")),(Object)(_totdraincum),(Object)(_totdrainhrs),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(("0")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_saddedat),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null}));
 //BA.debugLineNum = 453;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 454;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e14) {
			processBA.setLastException(e14); //BA.debugLineNum = 456;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("783623958",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 457;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 459;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 460;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 461;BA.debugLine="End Sub";
return false;
}
public static String  _showsavesuccess() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
anywheresoftware.b4a.objects.CSBuilder _cscontent = null;
 //BA.debugLineNum = 952;BA.debugLine="Private Sub ShowSaveSuccess()";
 //BA.debugLineNum = 953;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 954;BA.debugLine="Dim csContent As CSBuilder";
_cscontent = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 956;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar.";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 957;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black)";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("Pump time has been successfully updated!"))).PopAll();
 //BA.debugLineNum = 959;BA.debugLine="MatDialogBuilder.Initialize(\"AddPumpTimeOnRecords";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"AddPumpTimeOnRecords");
 //BA.debugLineNum = 960;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 961;BA.debugLine="MatDialogBuilder.Content(csContent)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_cscontent.getObject()));
 //BA.debugLineNum = 962;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 963;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 964;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 965;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 966;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 967;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 189;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 190;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 185;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 186;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 187;BA.debugLine="End Sub";
return "";
}
public static String  _txtduration_enterpressed() throws Exception{
 //BA.debugLineNum = 829;BA.debugLine="Sub txtDuration_EnterPressed";
 //BA.debugLineNum = 830;BA.debugLine="txtPSI.RequestFocus";
mostCurrent._txtpsi.RequestFocus();
 //BA.debugLineNum = 831;BA.debugLine="End Sub";
return "";
}
public static String  _txtduration_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 833;BA.debugLine="Sub txtDuration_FocusChanged (HasFocus As Boolean)";
 //BA.debugLineNum = 834;BA.debugLine="If HasFocus = True Then txtDuration.SelectAll";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._txtduration.SelectAll();};
 //BA.debugLineNum = 835;BA.debugLine="End Sub";
return "";
}
public static String  _txtduration_textchanged(String _old,String _new) throws Exception{
int _losses = 0;
 //BA.debugLineNum = 817;BA.debugLine="Sub txtDuration_TextChanged (Old As String, New As";
 //BA.debugLineNum = 818;BA.debugLine="Dim Losses As Int";
_losses = 0;
 //BA.debugLineNum = 820;BA.debugLine="Losses = 0";
_losses = (int) (0);
 //BA.debugLineNum = 821;BA.debugLine="If GlobalVar.SF.Len(txtDuration.Text) <= 0 Or Glo";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtduration.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._pumpdrainpipesize /*String*/ )<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtpsi.getText())<=0) { 
 //BA.debugLineNum = 822;BA.debugLine="Losses = 0";
_losses = (int) (0);
 }else {
 //BA.debugLineNum = 824;BA.debugLine="Losses = DBaseFunctions.ComputeWaterLoss(GlobalV";
_losses = (int) (mostCurrent._dbasefunctions._computewaterloss /*double*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumpdrainpipetype /*String*/ ,mostCurrent._globalvar._pumpdrainpipesize /*String*/ ,(int)(Double.parseDouble(mostCurrent._txtpsi.getText())),(int)(Double.parseDouble(mostCurrent._txtduration.getText()))));
 };
 //BA.debugLineNum = 826;BA.debugLine="txtDrainCum.Text = Losses";
mostCurrent._txtdraincum.setText(BA.ObjectToCharSequence(_losses));
 //BA.debugLineNum = 827;BA.debugLine="End Sub";
return "";
}
public static String  _txtonremarks_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 837;BA.debugLine="Sub txtOnRemarks_FocusChanged (HasFocus As Boolean";
 //BA.debugLineNum = 838;BA.debugLine="If HasFocus = True Then txtOnRemarks.SelectAll";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._txtonremarks.SelectAll();};
 //BA.debugLineNum = 839;BA.debugLine="End Sub";
return "";
}
public static String  _txtpsi_enterpressed() throws Exception{
 //BA.debugLineNum = 808;BA.debugLine="Sub txtPSI_EnterPressed";
 //BA.debugLineNum = 809;BA.debugLine="txtOnRemarks.RequestFocus";
mostCurrent._txtonremarks.RequestFocus();
 //BA.debugLineNum = 810;BA.debugLine="End Sub";
return "";
}
public static String  _txtpsi_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 812;BA.debugLine="Sub txtPSI_FocusChanged (HasFocus As Boolean)";
 //BA.debugLineNum = 813;BA.debugLine="If HasFocus = True Then txtPSI.SelectAll";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._txtpsi.SelectAll();};
 //BA.debugLineNum = 814;BA.debugLine="End Sub";
return "";
}
public static String  _txtpsi_textchanged(String _old,String _new) throws Exception{
int _losses = 0;
 //BA.debugLineNum = 796;BA.debugLine="Sub txtPSI_TextChanged (Old As String, New As Stri";
 //BA.debugLineNum = 797;BA.debugLine="Dim Losses As Int";
_losses = 0;
 //BA.debugLineNum = 799;BA.debugLine="Losses = 0";
_losses = (int) (0);
 //BA.debugLineNum = 800;BA.debugLine="If GlobalVar.SF.Len(txtDuration.Text) <= 0 Or Glo";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtduration.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._pumpdrainpipesize /*String*/ )<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtpsi.getText())<=0) { 
 //BA.debugLineNum = 801;BA.debugLine="Losses = 0";
_losses = (int) (0);
 }else {
 //BA.debugLineNum = 803;BA.debugLine="Losses = DBaseFunctions.ComputeWaterLoss(GlobalV";
_losses = (int) (mostCurrent._dbasefunctions._computewaterloss /*double*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumpdrainpipetype /*String*/ ,mostCurrent._globalvar._pumpdrainpipesize /*String*/ ,(int)(Double.parseDouble(mostCurrent._txtpsi.getText())),(int)(Double.parseDouble(mostCurrent._txtduration.getText()))));
 };
 //BA.debugLineNum = 805;BA.debugLine="txtDrainCum.Text = Losses";
mostCurrent._txtdraincum.setText(BA.ObjectToCharSequence(_losses));
 //BA.debugLineNum = 806;BA.debugLine="End Sub";
return "";
}
public static boolean  _updatepumptime(int _itranheaderid,int _idetailid) throws Exception{
boolean _bretval = false;
String _sdatetime = "";
long _ldate = 0L;
int _ipsi = 0;
double _dduration = 0;
double _dcum = 0;
String _sonremarks = "";
String _soffremarks = "";
String _slocation = "";
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher2 = null;
String _sminon = "";
String _sminoff = "";
int _ihrson = 0;
int _iminson = 0;
int _ihrsoff = 0;
int _iminsoff = 0;
 //BA.debugLineNum = 504;BA.debugLine="Private Sub UpdatePumpTime(iTranHeaderID As Int, i";
 //BA.debugLineNum = 505;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 506;BA.debugLine="Dim sDateTime As String";
_sdatetime = "";
 //BA.debugLineNum = 507;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 508;BA.debugLine="Dim iPSI As Int";
_ipsi = 0;
 //BA.debugLineNum = 509;BA.debugLine="Dim dDuration, dCuM As Double";
_dduration = 0;
_dcum = 0;
 //BA.debugLineNum = 510;BA.debugLine="Dim sOnRemarks, sOffRemarks, sLocation As String";
_sonremarks = "";
_soffremarks = "";
_slocation = "";
 //BA.debugLineNum = 512;BA.debugLine="Dim Matcher1, Matcher2 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
_matcher2 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 513;BA.debugLine="Dim sMinOn, sMinOff As String";
_sminon = "";
_sminoff = "";
 //BA.debugLineNum = 515;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d)\", mskTime";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d)",mostCurrent._msktimeon.getText());
 //BA.debugLineNum = 517;BA.debugLine="If Matcher1.Find Then";
if (_matcher1.Find()) { 
 //BA.debugLineNum = 518;BA.debugLine="Dim iHrsOn, iMinsOn As Int";
_ihrson = 0;
_iminson = 0;
 //BA.debugLineNum = 520;BA.debugLine="iHrsOn = Matcher1.Group(1)";
_ihrson = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 521;BA.debugLine="iMinsOn = Matcher1.Group(2)";
_iminson = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 523;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMinsOn))";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_iminson)))==1) { 
 //BA.debugLineNum = 524;BA.debugLine="sMinOn = $\"0\"$ & iMinsOn";
_sminon = ("0")+BA.NumberToString(_iminson);
 }else {
 //BA.debugLineNum = 526;BA.debugLine="sMinOn = iMinsOn";
_sminon = BA.NumberToString(_iminson);
 };
 //BA.debugLineNum = 529;BA.debugLine="If iHrsOn = 0 Then";
if (_ihrson==0) { 
 //BA.debugLineNum = 530;BA.debugLine="iHrOn = 12";
_ihron = (int) (12);
 //BA.debugLineNum = 531;BA.debugLine="sTimeOn = iHrOn & \":\" & sMinOn & \" AM\"";
mostCurrent._stimeon = BA.NumberToString(_ihron)+":"+_sminon+" AM";
 }else if(_ihrson>0 && _ihrson<12) { 
 //BA.debugLineNum = 533;BA.debugLine="iHrOn = iHrsOn";
_ihron = _ihrson;
 //BA.debugLineNum = 534;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrOn)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_ihron)))==1) { 
 //BA.debugLineNum = 535;BA.debugLine="sTimeOn = $\"0\"$ & iHrOn & \":\" & sMinOn & \" AM\"";
mostCurrent._stimeon = ("0")+BA.NumberToString(_ihron)+":"+_sminon+" AM";
 }else {
 //BA.debugLineNum = 537;BA.debugLine="sTimeOn = iHrOn & \":\" & sMinOn & \" AM\"";
mostCurrent._stimeon = BA.NumberToString(_ihron)+":"+_sminon+" AM";
 };
 }else if(_ihrson==12) { 
 //BA.debugLineNum = 540;BA.debugLine="iHrOn = 12";
_ihron = (int) (12);
 //BA.debugLineNum = 541;BA.debugLine="sTimeOn = iHrOn & \":\" & sMinOn & \" PM\"";
mostCurrent._stimeon = BA.NumberToString(_ihron)+":"+_sminon+" PM";
 }else {
 //BA.debugLineNum = 543;BA.debugLine="iHrOn = iHrsOn - 12";
_ihron = (int) (_ihrson-12);
 //BA.debugLineNum = 544;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrOn)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_ihron)))==1) { 
 //BA.debugLineNum = 545;BA.debugLine="sTimeOn = $\"0\"$ & iHrOn & \":\" & sMinOn & \" PM\"";
mostCurrent._stimeon = ("0")+BA.NumberToString(_ihron)+":"+_sminon+" PM";
 }else {
 //BA.debugLineNum = 547;BA.debugLine="sTimeOn = iHrOn & \":\" & sMinOn & \" PM\"";
mostCurrent._stimeon = BA.NumberToString(_ihron)+":"+_sminon+" PM";
 };
 };
 };
 //BA.debugLineNum = 552;BA.debugLine="Matcher2 = Regex.Matcher(\"(\\d\\d):(\\d\\d)\", mskTime";
_matcher2 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d)",mostCurrent._msktimeoff.getText());
 //BA.debugLineNum = 554;BA.debugLine="If Matcher2.Find Then";
if (_matcher2.Find()) { 
 //BA.debugLineNum = 555;BA.debugLine="Dim iHrsOff, iMinsOff As Int";
_ihrsoff = 0;
_iminsoff = 0;
 //BA.debugLineNum = 557;BA.debugLine="iHrsOff = Matcher2.Group(1)";
_ihrsoff = (int)(Double.parseDouble(_matcher2.Group((int) (1))));
 //BA.debugLineNum = 558;BA.debugLine="iMinsOff = Matcher2.Group(2)";
_iminsoff = (int)(Double.parseDouble(_matcher2.Group((int) (2))));
 //BA.debugLineNum = 560;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMinsOff))";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_iminsoff)))==1) { 
 //BA.debugLineNum = 561;BA.debugLine="sMinOff = $\"0\"$ & iMinsOff";
_sminoff = ("0")+BA.NumberToString(_iminsoff);
 }else {
 //BA.debugLineNum = 563;BA.debugLine="sMinOff = iMinsOff";
_sminoff = BA.NumberToString(_iminsoff);
 };
 //BA.debugLineNum = 566;BA.debugLine="If iHrsOff= 0 Then";
if (_ihrsoff==0) { 
 //BA.debugLineNum = 567;BA.debugLine="iHrOff = 12";
_ihroff = (int) (12);
 //BA.debugLineNum = 568;BA.debugLine="sTimeOff = iHrOff & \":\" & sMinOff & \" AM\"";
mostCurrent._stimeoff = BA.NumberToString(_ihroff)+":"+_sminoff+" AM";
 }else if(_ihrsoff>0 && _ihrsoff<12) { 
 //BA.debugLineNum = 570;BA.debugLine="iHrOff = iHrsOff";
_ihroff = _ihrsoff;
 //BA.debugLineNum = 571;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrOff))";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_ihroff)))==1) { 
 //BA.debugLineNum = 572;BA.debugLine="sTimeOff = $\"0\"$ & iHrOff & \":\" & sMinOff & \"";
mostCurrent._stimeoff = ("0")+BA.NumberToString(_ihroff)+":"+_sminoff+" AM";
 }else {
 //BA.debugLineNum = 574;BA.debugLine="sTimeOff = iHrOff & \":\" & sMinOff & \" AM\"";
mostCurrent._stimeoff = BA.NumberToString(_ihroff)+":"+_sminoff+" AM";
 };
 }else if(_ihrsoff==12) { 
 //BA.debugLineNum = 577;BA.debugLine="iHrOff = 12";
_ihroff = (int) (12);
 //BA.debugLineNum = 578;BA.debugLine="sTimeOff = iHrOff & \":\" & sMinOff & \" PM\"";
mostCurrent._stimeoff = BA.NumberToString(_ihroff)+":"+_sminoff+" PM";
 }else {
 //BA.debugLineNum = 580;BA.debugLine="iHrOff = iHrsOff - 12";
_ihroff = (int) (_ihrsoff-12);
 //BA.debugLineNum = 581;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrOff))";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_ihroff)))==1) { 
 //BA.debugLineNum = 582;BA.debugLine="sTimeOff = $\"0\"$ & iHrOff & \":\" & sMinOff & \"";
mostCurrent._stimeoff = ("0")+BA.NumberToString(_ihroff)+":"+_sminoff+" PM";
 }else {
 //BA.debugLineNum = 584;BA.debugLine="sTimeOff = iHrOff & \":\" & sMinOff & \" PM\"";
mostCurrent._stimeoff = BA.NumberToString(_ihroff)+":"+_sminoff+" PM";
 };
 };
 };
 //BA.debugLineNum = 589;BA.debugLine="LogColor(sTimeOn,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("783755093",mostCurrent._stimeon,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 590;BA.debugLine="LogColor(sTimeOff,Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("783755094",mostCurrent._stimeoff,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 592;BA.debugLine="If optElectricity.Checked = True Then";
if (mostCurrent._optelectricity.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 593;BA.debugLine="PowerSourceID = 1";
_powersourceid = (int) (1);
 }else {
 //BA.debugLineNum = 595;BA.debugLine="PowerSourceID = 0";
_powersourceid = (int) (0);
 };
 //BA.debugLineNum = 598;BA.debugLine="If chkDrain.Checked = True Then";
if (mostCurrent._chkdrain.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 599;BA.debugLine="iPSI = txtPSI.Text";
_ipsi = (int)(Double.parseDouble(mostCurrent._txtpsi.getText()));
 //BA.debugLineNum = 600;BA.debugLine="dDuration = txtDuration.Text";
_dduration = (double)(Double.parseDouble(mostCurrent._txtduration.getText()));
 //BA.debugLineNum = 601;BA.debugLine="dCuM = txtDrainCum.Text";
_dcum = (double)(Double.parseDouble(mostCurrent._txtdraincum.getText()));
 }else {
 //BA.debugLineNum = 603;BA.debugLine="iPSI = 0";
_ipsi = (int) (0);
 //BA.debugLineNum = 604;BA.debugLine="dDuration = 0";
_dduration = 0;
 //BA.debugLineNum = 605;BA.debugLine="dCuM = 0";
_dcum = 0;
 };
 //BA.debugLineNum = 608;BA.debugLine="LogColor($\"Total Drain Duration: \"$ & TotDrainHrs";
anywheresoftware.b4a.keywords.Common.LogImpl("783755112",("Total Drain Duration: ")+BA.NumberToString(_totdrainhrs),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 609;BA.debugLine="LogColor($\"Total Drain CuM: \"$ & TotDrainCum, Col";
anywheresoftware.b4a.keywords.Common.LogImpl("783755113",("Total Drain CuM: ")+BA.NumberToString(_totdraincum),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 611;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 612;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 613;BA.debugLine="sDateTime = DateTime.Date(lDate)";
_sdatetime = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 614;BA.debugLine="sOnRemarks = txtOnRemarks.Text";
_sonremarks = mostCurrent._txtonremarks.getText();
 //BA.debugLineNum = 615;BA.debugLine="sOffRemarks = txtOffRemarks.Text";
_soffremarks = mostCurrent._txtoffremarks.getText();
 //BA.debugLineNum = 617;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 619;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("783755123",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 621;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("783755125",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 622;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("783755126",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 624;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 625;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("783755129",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 627;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 628;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeaderID";
anywheresoftware.b4a.keywords.Common.LogImpl("783755132",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 630;BA.debugLine="TotOpHrs = ComputeTotHrs(sTimeOn, sTimeOff)";
_totophrs = _computetothrs(mostCurrent._stimeon,mostCurrent._stimeoff);
 //BA.debugLineNum = 632;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 633;BA.debugLine="Try";
try { //BA.debugLineNum = 634;BA.debugLine="Starter.strCriteria = \"UPDATE OnOffDetails SET \"";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE OnOffDetails SET "+"PumpOnTime = ?, "+"PumpOffTime = ?, "+"TotOpHrs = ?, "+"PowerSourceID = ?, "+"DrainPSI = ?, "+"DrainTime = ?, "+"DrainCum = ?, "+"TimeOnRemarks = ?, "+"TimeOffRemarks = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ?, "+"ModifiedOn = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid)+" "+"AND DetailID = "+BA.NumberToString(_idetailid);
 //BA.debugLineNum = 650;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{mostCurrent._stimeon,mostCurrent._stimeoff,BA.NumberToString(_totophrs),BA.NumberToString(_powersourceid),BA.NumberToString(_ipsi),BA.NumberToString(_dduration),BA.NumberToString(_dcum),_sonremarks,_soffremarks,BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_sdatetime,_slocation}));
 //BA.debugLineNum = 651;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 652;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e112) {
			processBA.setLastException(e112); //BA.debugLineNum = 654;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("783755158",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 655;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 657;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 658;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 659;BA.debugLine="End Sub";
return false;
}
public static boolean  _updatetranheader(int _itranheaderid) throws Exception{
boolean _bretval = false;
float _gtotophrs = 0f;
double _gtotdrain = 0;
double _gtotduration = 0;
long _lngdatetime = 0L;
String _smodifiedat = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsheader = null;
 //BA.debugLineNum = 661;BA.debugLine="Private Sub UpdateTranHeader(iTranHeaderID As Int)";
 //BA.debugLineNum = 662;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 663;BA.debugLine="Dim GTotOPHrs As Float";
_gtotophrs = 0f;
 //BA.debugLineNum = 664;BA.debugLine="Dim GTotDrain, GTotDuration As Double";
_gtotdrain = 0;
_gtotduration = 0;
 //BA.debugLineNum = 666;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 667;BA.debugLine="Dim sModifiedAt As String";
_smodifiedat = "";
 //BA.debugLineNum = 669;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 670;BA.debugLine="DateTime.TimeFormat = \"hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm:ss a");
 //BA.debugLineNum = 671;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 672;BA.debugLine="sModifiedAt = DateTime.Date(lngDateTime) & $\" \"$";
_smodifiedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime)+(" ")+anywheresoftware.b4a.keywords.Common.DateTime.Time(_lngdatetime);
 //BA.debugLineNum = 674;BA.debugLine="Dim rsHeader As Cursor";
_rsheader = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 676;BA.debugLine="Starter.strCriteria = \"SELECT * FROM TranHeader W";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM TranHeader WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 677;BA.debugLine="rsHeader = Starter.DBCon.ExecQuery(Starter.strCri";
_rsheader = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 678;BA.debugLine="If rsHeader.RowCount > 0 Then";
if (_rsheader.getRowCount()>0) { 
 //BA.debugLineNum = 679;BA.debugLine="rsHeader.Position = 0";
_rsheader.setPosition((int) (0));
 //BA.debugLineNum = 680;BA.debugLine="GTotOPHrs = rsHeader.GetDouble(\"TotOpHrs\") + Tot";
_gtotophrs = (float) (_rsheader.GetDouble("TotOpHrs")+_totophrs);
 //BA.debugLineNum = 681;BA.debugLine="GTotDuration = rsHeader.GetInt(\"TotDrainHrs\") +";
_gtotduration = _rsheader.GetInt("TotDrainHrs")+_totdrainhrs;
 //BA.debugLineNum = 682;BA.debugLine="GTotDrain = rsHeader.GetInt(\"TotDrain\") + TotDra";
_gtotdrain = _rsheader.GetInt("TotDrain")+_totdraincum;
 }else {
 //BA.debugLineNum = 684;BA.debugLine="GTotOPHrs = TotOpHrs";
_gtotophrs = (float) (_totophrs);
 //BA.debugLineNum = 685;BA.debugLine="GTotDuration = TotDrainHrs";
_gtotduration = _totdrainhrs;
 //BA.debugLineNum = 686;BA.debugLine="GTotDrain = TotDrainCum";
_gtotdrain = _totdraincum;
 };
 //BA.debugLineNum = 688;BA.debugLine="rsHeader.Close";
_rsheader.Close();
 //BA.debugLineNum = 689;BA.debugLine="LogColor($\"Total Op Hrs: \"$ & GTotOPHrs, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("783820572",("Total Op Hrs: ")+BA.NumberToString(_gtotophrs),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 691;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 692;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 693;BA.debugLine="Try";
try { //BA.debugLineNum = 694;BA.debugLine="Starter.strCriteria = \"UPDATE TranHeader SET \" &";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE TranHeader SET "+"TotOpHrs = ?, "+"TotDrainHrs = ?, "+"TotDrain = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 702;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_gtotophrs),BA.NumberToString(_gtotduration),BA.NumberToString(_gtotdrain),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_smodifiedat}));
 //BA.debugLineNum = 703;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 704;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e33) {
			processBA.setLastException(e33); //BA.debugLineNum = 706;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("783820589",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 707;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 709;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 710;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 711;BA.debugLine="End Sub";
return false;
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
