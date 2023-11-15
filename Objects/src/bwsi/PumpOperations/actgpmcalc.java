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

public class actgpmcalc extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static actgpmcalc mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.actgpmcalc");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actgpmcalc).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.actgpmcalc");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.actgpmcalc", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actgpmcalc) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actgpmcalc) Resume **");
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
		return actgpmcalc.class;
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
            BA.LogInfo("** Activity (actgpmcalc) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (actgpmcalc) Pause event (activity is not paused). **");
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
            actgpmcalc mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (actgpmcalc) Resume **");
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
public static anywheresoftware.b4a.objects.Timer _timer1 = null;
public static long _starttime = 0L;
public de.amberhome.objects.appcompat.ACActionBar _actionbarbutton = null;
public anywheresoftware.b4a.object.XmlLayoutBuilder _xmlicon = null;
public de.amberhome.objects.appcompat.ACToolbarDarkWrapper _toolbar = null;
public de.amberhome.materialdialogs.MaterialDialogBuilderWrapper _matdialogbuilder = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdtotgpm = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdtxtbox = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdbutton = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdrem = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdgpm = null;
public com.johan.Vibrate.Vibrate _vibration = null;
public de.amberhome.objects.SnackbarWrapper _snack = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdreading = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnsaveupdate = null;
public bwsi.PumpOperations.bctoast _toastmsg = null;
public anywheresoftware.b4a.objects.IME _kboard = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtbucketsize = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _cbouom = null;
public mlstopwatch.MLStopWatch _sw1 = null;
public mlstopwatch.MLStopWatch _sw2 = null;
public mlstopwatch.MLStopWatch _sw3 = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdstart = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdstop = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdreset = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdpause = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtgpm = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtremarks = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtwaterquality = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnshowtimer1 = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnshowtimer2 = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnshowtimer3 = null;
public com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fontbold = null;
public static int _itimer = 0;
public static double _dtime1 = 0;
public static double _dtime2 = 0;
public static double _dtime3 = 0;
public static String _stime1 = "";
public static String _stime2 = "";
public static String _stime3 = "";
public anywheresoftware.b4a.objects.PanelWrapper _pnlmaintimer = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlstopwatch = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnreset = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnpause = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnstart = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnstop = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txttry1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txttry2 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txttry3 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _chkmanual = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnok = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btncancel = null;
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
 //BA.debugLineNum = 94;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 95;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 96;BA.debugLine="Activity.LoadLayout(\"NewGPMCalc\")";
mostCurrent._activity.LoadLayout("NewGPMCalc",mostCurrent.activityBA);
 //BA.debugLineNum = 98;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Append";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("GPM CALCULATOR"))).PopAll();
 //BA.debugLineNum = 99;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append($";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("PUMP: ")+mostCurrent._globalvar._pumphousecode /*String*/ )).PopAll();
 //BA.debugLineNum = 101;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 102;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 103;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 105;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 106;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 107;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 108;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 109;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 110;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 112;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 113;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 115;BA.debugLine="CDButton.Initialize2(GlobalVar.GreenColor, 30, 0,";
mostCurrent._cdbutton.Initialize2((int) (mostCurrent._globalvar._greencolor /*double*/ ),(int) (30),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 116;BA.debugLine="btnSaveUpdate.Background = CDButton";
mostCurrent._btnsaveupdate.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdbutton.getObject()));
 //BA.debugLineNum = 117;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\" SAVE\"$";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" SAVE")));
 //BA.debugLineNum = 119;BA.debugLine="InpTyp.Initialize";
_inptyp._initialize /*String*/ (processBA);
 //BA.debugLineNum = 120;BA.debugLine="kBoard.Initialize(\"\")";
mostCurrent._kboard.Initialize("");
 //BA.debugLineNum = 122;BA.debugLine="InpTyp.SetInputType(txtBucketSize,Array As Int(In";
_inptyp._setinputtype /*String*/ (mostCurrent._txtbucketsize,new int[]{_inptyp._type_class_number /*int*/ (),_inptyp._type_number_flag_decimal /*int*/ (),_inptyp._type_number_flag_signed /*int*/ ()});
 //BA.debugLineNum = 123;BA.debugLine="InpTyp.SetInputType(txtWaterQuality,Array As Int(";
_inptyp._setinputtype /*String*/ (mostCurrent._txtwaterquality,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_correct /*int*/ (),_inptyp._type_text_flag_cap_sentences /*int*/ ()});
 //BA.debugLineNum = 124;BA.debugLine="InpTyp.SetInputType(txtRemarks,Array As Int(InpTy";
_inptyp._setinputtype /*String*/ (mostCurrent._txtremarks,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_correct /*int*/ (),_inptyp._type_text_flag_cap_sentences /*int*/ ()});
 //BA.debugLineNum = 125;BA.debugLine="InpTyp.SetInputType(txtTry1,Array As Int(InpTyp.T";
_inptyp._setinputtype /*String*/ (mostCurrent._txttry1,new int[]{_inptyp._type_class_number /*int*/ (),_inptyp._type_number_flag_decimal /*int*/ (),_inptyp._type_number_flag_signed /*int*/ ()});
 //BA.debugLineNum = 126;BA.debugLine="InpTyp.SetInputType(txtTry2,Array As Int(InpTyp.T";
_inptyp._setinputtype /*String*/ (mostCurrent._txttry2,new int[]{_inptyp._type_class_number /*int*/ (),_inptyp._type_number_flag_decimal /*int*/ (),_inptyp._type_number_flag_signed /*int*/ ()});
 //BA.debugLineNum = 127;BA.debugLine="InpTyp.SetInputType(txtTry3,Array As Int(InpTyp.T";
_inptyp._setinputtype /*String*/ (mostCurrent._txttry3,new int[]{_inptyp._type_class_number /*int*/ (),_inptyp._type_number_flag_decimal /*int*/ (),_inptyp._type_number_flag_signed /*int*/ ()});
 //BA.debugLineNum = 129;BA.debugLine="CDtxtBox.Initialize(Colors.Transparent,0)";
mostCurrent._cdtxtbox.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 130;BA.debugLine="txtBucketSize.Background = CDtxtBox";
mostCurrent._txtbucketsize.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 131;BA.debugLine="txtTry1.Background = CDtxtBox";
mostCurrent._txttry1.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 132;BA.debugLine="txtTry2.Background = CDtxtBox";
mostCurrent._txttry2.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 133;BA.debugLine="txtTry3.Background = CDtxtBox";
mostCurrent._txttry3.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 134;BA.debugLine="txtTry1.Padding = Array As Int(1dip, 0dip, 10dip,";
mostCurrent._txttry1.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0))});
 //BA.debugLineNum = 135;BA.debugLine="txtTry2.Padding = Array As Int(1dip, 0dip, 10dip,";
mostCurrent._txttry2.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0))});
 //BA.debugLineNum = 136;BA.debugLine="txtTry3.Padding = Array As Int(1dip, 0dip, 10dip,";
mostCurrent._txttry3.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0))});
 //BA.debugLineNum = 138;BA.debugLine="cdGPM.Initialize2(Colors.Black, 0, 0, Colors.Tran";
mostCurrent._cdgpm.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 139;BA.debugLine="txtGPM.Background = cdGPM";
mostCurrent._txtgpm.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdgpm.getObject()));
 //BA.debugLineNum = 140;BA.debugLine="txtGPM.TextColor = 0xFFADFF2F";
mostCurrent._txtgpm.setTextColor((int) (0xffadff2f));
 //BA.debugLineNum = 141;BA.debugLine="txtGPM.Padding = Array As Int(0dip, 0dip, 0dip, -";
mostCurrent._txtgpm.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),(int) (-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)))});
 //BA.debugLineNum = 143;BA.debugLine="cdRem.Initialize2(Colors.Transparent, 0, 0, Color";
mostCurrent._cdrem.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 144;BA.debugLine="txtRemarks.Background = cdRem";
mostCurrent._txtremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdrem.getObject()));
 //BA.debugLineNum = 145;BA.debugLine="txtWaterQuality.Background = cdRem";
mostCurrent._txtwaterquality.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdrem.getObject()));
 //BA.debugLineNum = 147;BA.debugLine="If Not(GlobalVar.blnNewGPM) Then";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._globalvar._blnnewgpm /*boolean*/ )) { 
 //BA.debugLineNum = 148;BA.debugLine="FillUOM";
_filluom();
 //BA.debugLineNum = 149;BA.debugLine="FillRecords(GlobalVar.GPMId)";
_fillrecords(mostCurrent._globalvar._gpmid /*int*/ );
 }else {
 //BA.debugLineNum = 151;BA.debugLine="ClearDisplay";
_cleardisplay();
 //BA.debugLineNum = 152;BA.debugLine="FillUOM";
_filluom();
 };
 //BA.debugLineNum = 155;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 156;BA.debugLine="txtBucketSize.RequestFocus";
mostCurrent._txtbucketsize.RequestFocus();
 //BA.debugLineNum = 157;BA.debugLine="kBoard.ShowKeyboard(txtBucketSize)";
mostCurrent._kboard.ShowKeyboard((android.view.View)(mostCurrent._txtbucketsize.getObject()));
 };
 //BA.debugLineNum = 160;BA.debugLine="kBoard.ShowKeyboard(txtBucketSize)";
mostCurrent._kboard.ShowKeyboard((android.view.View)(mostCurrent._txtbucketsize.getObject()));
 //BA.debugLineNum = 161;BA.debugLine="MyFunctions.SetButton(btnSaveUpdate, 25, 25, 25,";
mostCurrent._myfunctions._setbutton /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btnsaveupdate.getObject())),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25));
 //BA.debugLineNum = 163;BA.debugLine="SetButtonColors";
_setbuttoncolors();
 //BA.debugLineNum = 164;BA.debugLine="InitObjects";
_initobjects();
 //BA.debugLineNum = 165;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
de.amberhome.objects.appcompat.ACMenuItemWrapper _item = null;
 //BA.debugLineNum = 187;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 188;BA.debugLine="Dim Item As ACMenuItem";
_item = new de.amberhome.objects.appcompat.ACMenuItemWrapper();
 //BA.debugLineNum = 189;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 191;BA.debugLine="Menu.Add2(1, 1, \"GPM History\",xmlIcon.GetDrawable";
_menu.Add2((int) (1),(int) (1),BA.ObjectToCharSequence("GPM History"),mostCurrent._xmlicon.GetDrawable("baseline_history_white_18dp")).setShowAsAction(_item.SHOW_AS_ACTION_IF_ROOM);
 //BA.debugLineNum = 192;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 167;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 168;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 169;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 170;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 172;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 174;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 181;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 176;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 177;BA.debugLine="FillUOM";
_filluom();
 //BA.debugLineNum = 178;BA.debugLine="iTimer = 0";
_itimer = (int) (0);
 //BA.debugLineNum = 179;BA.debugLine="End Sub";
return "";
}
public static String  _btncancel_click() throws Exception{
 //BA.debugLineNum = 885;BA.debugLine="Sub btnCancel_Click";
 //BA.debugLineNum = 886;BA.debugLine="If Not(SW1.IsInitialized) Then";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._sw1.IsInitialized())) { 
 //BA.debugLineNum = 887;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 889;BA.debugLine="SW1.Stop";
mostCurrent._sw1.Stop();
 //BA.debugLineNum = 890;BA.debugLine="pnlMainTimer.Visible = False";
mostCurrent._pnlmaintimer.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 891;BA.debugLine="End Sub";
return "";
}
public static String  _btnok_click() throws Exception{
 //BA.debugLineNum = 874;BA.debugLine="Sub btnOk_Click";
 //BA.debugLineNum = 875;BA.debugLine="Select Case iTimer";
switch (_itimer) {
case 1: {
 //BA.debugLineNum = 877;BA.debugLine="ConfirmStopTimer($\"CONFIRM TIME\"$, $\"Do you wan";
_confirmstoptimer(("CONFIRM TIME"),("Do you want to fetch this as your Trial 1 time?"),_itimer);
 break; }
case 2: {
 //BA.debugLineNum = 879;BA.debugLine="ConfirmStopTimer($\"CONFIRM TIME\"$, $\"Do you wan";
_confirmstoptimer(("CONFIRM TIME"),("Do you want to fetch this as your Trial 2 time?"),_itimer);
 break; }
case 3: {
 //BA.debugLineNum = 881;BA.debugLine="ConfirmStopTimer($\"CONFIRM TIME\"$, $\"Do you wan";
_confirmstoptimer(("CONFIRM TIME"),("Do you want to fetch this as your Trial 3 time?"),_itimer);
 break; }
}
;
 //BA.debugLineNum = 883;BA.debugLine="End Sub";
return "";
}
public static String  _btnpause_click() throws Exception{
 //BA.debugLineNum = 751;BA.debugLine="Sub btnPause_Click";
 //BA.debugLineNum = 752;BA.debugLine="Select Case btnPause.Text";
switch (BA.switchObjectToInt(mostCurrent._btnpause.getText(),"PAUSE","RESUME")) {
case 0: {
 //BA.debugLineNum = 754;BA.debugLine="If Not(SW1.IsInitialized) Then";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._sw1.IsInitialized())) { 
 //BA.debugLineNum = 755;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 757;BA.debugLine="SW1.Pause";
mostCurrent._sw1.Pause();
 //BA.debugLineNum = 758;BA.debugLine="btnStart.Enabled = False";
mostCurrent._btnstart.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 759;BA.debugLine="btnStop.Enabled = False";
mostCurrent._btnstop.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 760;BA.debugLine="btnPause.Enabled = True";
mostCurrent._btnpause.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 761;BA.debugLine="btnReset.Enabled = False";
mostCurrent._btnreset.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 762;BA.debugLine="btnPause.Text = \"RESUME\"";
mostCurrent._btnpause.setText(BA.ObjectToCharSequence("RESUME"));
 //BA.debugLineNum = 763;BA.debugLine="btnReset.Enabled = True";
mostCurrent._btnreset.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 764;BA.debugLine="btnCancel.Enabled = True";
mostCurrent._btncancel.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 1: {
 //BA.debugLineNum = 766;BA.debugLine="If Not(SW1.IsInitialized) Then";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._sw1.IsInitialized())) { 
 //BA.debugLineNum = 767;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 769;BA.debugLine="SW1.Resume";
mostCurrent._sw1.Resume();
 //BA.debugLineNum = 771;BA.debugLine="btnStart.Enabled = False";
mostCurrent._btnstart.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 772;BA.debugLine="btnStop.Enabled = True";
mostCurrent._btnstop.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 773;BA.debugLine="btnPause.Enabled = True";
mostCurrent._btnpause.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 774;BA.debugLine="btnReset.Enabled = False";
mostCurrent._btnreset.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 775;BA.debugLine="btnPause.Text = \"PAUSE\"";
mostCurrent._btnpause.setText(BA.ObjectToCharSequence("PAUSE"));
 //BA.debugLineNum = 776;BA.debugLine="btnReset.Enabled = False";
mostCurrent._btnreset.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 777;BA.debugLine="btnCancel.Enabled = True";
mostCurrent._btncancel.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
 //BA.debugLineNum = 779;BA.debugLine="End Sub";
return "";
}
public static String  _btnreset_click() throws Exception{
 //BA.debugLineNum = 710;BA.debugLine="Sub btnReset_Click";
 //BA.debugLineNum = 711;BA.debugLine="If Not(SW1.IsInitialized) Then";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._sw1.IsInitialized())) { 
 //BA.debugLineNum = 712;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 714;BA.debugLine="SW1.Reset";
mostCurrent._sw1.Reset();
 //BA.debugLineNum = 716;BA.debugLine="btnReset.Enabled = False";
mostCurrent._btnreset.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 717;BA.debugLine="btnPause.Enabled = False";
mostCurrent._btnpause.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 718;BA.debugLine="btnStop.Enabled = False";
mostCurrent._btnstop.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 719;BA.debugLine="btnStart.Enabled = True";
mostCurrent._btnstart.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 720;BA.debugLine="btnOk.Enabled = True";
mostCurrent._btnok.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 722;BA.debugLine="End Sub";
return "";
}
public static String  _btnsaveupdate_click() throws Exception{
 //BA.debugLineNum = 217;BA.debugLine="Sub btnSaveUpdate_Click";
 //BA.debugLineNum = 218;BA.debugLine="If Not(IsValidEntries) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_isvalidentries())) { 
if (true) return "";};
 //BA.debugLineNum = 220;BA.debugLine="If DBaseFunctions.IsGPMTransExist(GlobalVar.PumpH";
if (mostCurrent._dbasefunctions._isgpmtransexist /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 221;BA.debugLine="ShowGPMExist";
_showgpmexist();
 //BA.debugLineNum = 222;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 225;BA.debugLine="If Not(GlobalVar.blnNewGPM) Then";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._globalvar._blnnewgpm /*boolean*/ )) { 
 //BA.debugLineNum = 226;BA.debugLine="If Not(UpdateGPM(GlobalVar.GPMId, GlobalVar.Tran";
if (anywheresoftware.b4a.keywords.Common.Not(_updategpm(mostCurrent._globalvar._gpmid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ ))) { 
if (true) return "";};
 }else {
 //BA.debugLineNum = 228;BA.debugLine="If Not(SaveNewGPM) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_savenewgpm())) { 
if (true) return "";};
 };
 //BA.debugLineNum = 230;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 //BA.debugLineNum = 231;BA.debugLine="End Sub";
return "";
}
public static String  _btnshowtimer1_click() throws Exception{
 //BA.debugLineNum = 806;BA.debugLine="Sub btnShowTimer1_Click";
 //BA.debugLineNum = 807;BA.debugLine="iTimer = 1";
_itimer = (int) (1);
 //BA.debugLineNum = 808;BA.debugLine="ShowTimer";
_showtimer();
 //BA.debugLineNum = 809;BA.debugLine="End Sub";
return "";
}
public static String  _btnshowtimer2_click() throws Exception{
 //BA.debugLineNum = 811;BA.debugLine="Sub btnShowTimer2_Click";
 //BA.debugLineNum = 812;BA.debugLine="iTimer = 2";
_itimer = (int) (2);
 //BA.debugLineNum = 813;BA.debugLine="ShowTimer";
_showtimer();
 //BA.debugLineNum = 814;BA.debugLine="End Sub";
return "";
}
public static String  _btnshowtimer3_click() throws Exception{
 //BA.debugLineNum = 816;BA.debugLine="Sub btnShowTimer3_Click";
 //BA.debugLineNum = 817;BA.debugLine="iTimer = 3";
_itimer = (int) (3);
 //BA.debugLineNum = 818;BA.debugLine="ShowTimer";
_showtimer();
 //BA.debugLineNum = 819;BA.debugLine="End Sub";
return "";
}
public static String  _btnstart_click() throws Exception{
 //BA.debugLineNum = 738;BA.debugLine="Sub btnStart_Click";
 //BA.debugLineNum = 739;BA.debugLine="If Not(SW1.IsInitialized) Then";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._sw1.IsInitialized())) { 
 //BA.debugLineNum = 740;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 742;BA.debugLine="SW1.Start";
mostCurrent._sw1.Start();
 //BA.debugLineNum = 744;BA.debugLine="btnStart.Enabled = False";
mostCurrent._btnstart.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 745;BA.debugLine="btnStop.Enabled = True";
mostCurrent._btnstop.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 746;BA.debugLine="btnPause.Enabled = True";
mostCurrent._btnpause.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 747;BA.debugLine="btnReset.Enabled = False";
mostCurrent._btnreset.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 748;BA.debugLine="btnCancel.Enabled = False";
mostCurrent._btncancel.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 749;BA.debugLine="End Sub";
return "";
}
public static String  _btnstop_click() throws Exception{
 //BA.debugLineNum = 724;BA.debugLine="Sub btnStop_Click";
 //BA.debugLineNum = 725;BA.debugLine="If Not(SW1.IsInitialized) Then";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._sw1.IsInitialized())) { 
 //BA.debugLineNum = 726;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 728;BA.debugLine="SW1.Stop";
mostCurrent._sw1.Stop();
 //BA.debugLineNum = 730;BA.debugLine="btnReset.Enabled = True";
mostCurrent._btnreset.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 731;BA.debugLine="btnPause.Enabled = False";
mostCurrent._btnpause.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 732;BA.debugLine="btnStop.Enabled = False";
mostCurrent._btnstop.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 733;BA.debugLine="btnStart.Enabled = False";
mostCurrent._btnstart.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 734;BA.debugLine="btnOk.Enabled = True";
mostCurrent._btnok.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 735;BA.debugLine="btnCancel.Enabled = True";
mostCurrent._btncancel.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 736;BA.debugLine="End Sub";
return "";
}
public static String  _cbouom_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 209;BA.debugLine="Sub cboUOM_ItemClick (Position As Int, Value As Ob";
 //BA.debugLineNum = 210;BA.debugLine="If GlobalVar.SF.Len(txtBucketSize.Text) <= 0 Or G";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtbucketsize.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbouom.getSelectedItem())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry1.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry2.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry3.getText())<=0) { 
 //BA.debugLineNum = 211;BA.debugLine="txtGPM.Text = 0";
mostCurrent._txtgpm.setText(BA.ObjectToCharSequence(0));
 //BA.debugLineNum = 212;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 214;BA.debugLine="txtGPM.Text = ComputeGPM";
mostCurrent._txtgpm.setText(BA.ObjectToCharSequence(_computegpm()));
 //BA.debugLineNum = 215;BA.debugLine="End Sub";
return "";
}
public static String  _chkmanual_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 833;BA.debugLine="Sub chkManual_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 834;BA.debugLine="If Checked Then";
if (_checked) { 
 //BA.debugLineNum = 835;BA.debugLine="btnShowTimer1.Enabled = False";
mostCurrent._btnshowtimer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 836;BA.debugLine="btnShowTimer2.Enabled = False";
mostCurrent._btnshowtimer2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 837;BA.debugLine="btnShowTimer3.Enabled = False";
mostCurrent._btnshowtimer3.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 838;BA.debugLine="txtTry1.Text = \"\"";
mostCurrent._txttry1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 839;BA.debugLine="txtTry2.Text = \"\"";
mostCurrent._txttry2.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 840;BA.debugLine="txtTry3.Text = \"\"";
mostCurrent._txttry3.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 841;BA.debugLine="txtTry1.Enabled = True";
mostCurrent._txttry1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 842;BA.debugLine="txtTry2.Enabled = True";
mostCurrent._txttry2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 843;BA.debugLine="txtTry3.Enabled = True";
mostCurrent._txttry3.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 844;BA.debugLine="txtTry1.RequestFocus";
mostCurrent._txttry1.RequestFocus();
 }else {
 //BA.debugLineNum = 846;BA.debugLine="btnShowTimer1.Enabled = True";
mostCurrent._btnshowtimer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 847;BA.debugLine="txtTry1.Text = \"\"";
mostCurrent._txttry1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 848;BA.debugLine="txtTry2.Text = \"\"";
mostCurrent._txttry2.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 849;BA.debugLine="txtTry3.Text = \"\"";
mostCurrent._txttry3.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 850;BA.debugLine="txtTry1.Enabled = False";
mostCurrent._txttry1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 851;BA.debugLine="txtTry2.Enabled = False";
mostCurrent._txttry2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 852;BA.debugLine="txtTry3.Enabled = False";
mostCurrent._txttry3.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 854;BA.debugLine="End Sub";
return "";
}
public static String  _cleardisplay() throws Exception{
 //BA.debugLineNum = 375;BA.debugLine="Private Sub ClearDisplay";
 //BA.debugLineNum = 376;BA.debugLine="txtGPM.Text = \"\"";
mostCurrent._txtgpm.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 377;BA.debugLine="txtBucketSize.Text = \"\"";
mostCurrent._txtbucketsize.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 378;BA.debugLine="txtRemarks.Text = \"\"";
mostCurrent._txtremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 379;BA.debugLine="chkManual.Checked = False";
mostCurrent._chkmanual.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 380;BA.debugLine="txtTry1.Text = \"\"";
mostCurrent._txttry1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 381;BA.debugLine="txtTry2.Text = \"\"";
mostCurrent._txttry2.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 382;BA.debugLine="txtTry3.Text = \"\"";
mostCurrent._txttry3.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 383;BA.debugLine="txtWaterQuality.Text = \"\"";
mostCurrent._txtwaterquality.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 384;BA.debugLine="btnShowTimer1.Enabled = True";
mostCurrent._btnshowtimer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 385;BA.debugLine="btnShowTimer2.Enabled = False";
mostCurrent._btnshowtimer2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 386;BA.debugLine="btnShowTimer3.Enabled = False";
mostCurrent._btnshowtimer3.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 387;BA.debugLine="End Sub";
return "";
}
public static double  _computegpm() throws Exception{
double _dtry1 = 0;
double _dtry2 = 0;
double _dtry3 = 0;
double _avesec = 0;
anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper _totalgpm = null;
double _dretval = 0;
double _mass = 0;
double _hr = 0;
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher2 = null;
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher3 = null;
double _imins1 = 0;
double _isecs1 = 0;
double _imilsecs1 = 0;
double _imins2 = 0;
double _isecs2 = 0;
double _imilsecs2 = 0;
double _imins3 = 0;
double _isecs3 = 0;
double _imilsecs3 = 0;
 //BA.debugLineNum = 428;BA.debugLine="Private Sub ComputeGPM() As Double";
 //BA.debugLineNum = 429;BA.debugLine="Dim dTry1, dTry2, dTry3 As Double";
_dtry1 = 0;
_dtry2 = 0;
_dtry3 = 0;
 //BA.debugLineNum = 430;BA.debugLine="Dim aveSec As Double";
_avesec = 0;
 //BA.debugLineNum = 431;BA.debugLine="Dim TotalGPM As BigDecimal";
_totalgpm = new anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper();
 //BA.debugLineNum = 432;BA.debugLine="Dim dRetVal As Double";
_dretval = 0;
 //BA.debugLineNum = 433;BA.debugLine="Dim mass, hr As Double";
_mass = 0;
_hr = 0;
 //BA.debugLineNum = 435;BA.debugLine="Dim Matcher1, Matcher2, Matcher3 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
_matcher2 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
_matcher3 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 436;BA.debugLine="Dim iMins1, iSecs1, iMilSecs1 As Double";
_imins1 = 0;
_isecs1 = 0;
_imilsecs1 = 0;
 //BA.debugLineNum = 437;BA.debugLine="Dim iMins2, iSecs2, iMilSecs2 As Double";
_imins2 = 0;
_isecs2 = 0;
_imilsecs2 = 0;
 //BA.debugLineNum = 438;BA.debugLine="Dim iMins3, iSecs3, iMilSecs3 As Double";
_imins3 = 0;
_isecs3 = 0;
_imilsecs3 = 0;
 //BA.debugLineNum = 440;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtTry1.Tex";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txttry1.getText()))>0 && mostCurrent._chkmanual.getChecked()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 441;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d).(\\d\\d)\",";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d).(\\d\\d)",mostCurrent._txttry1.getText());
 //BA.debugLineNum = 442;BA.debugLine="If Matcher1.Find Then";
if (_matcher1.Find()) { 
 //BA.debugLineNum = 444;BA.debugLine="iMins1 = Matcher1.Group(1)";
_imins1 = (double)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 445;BA.debugLine="iSecs1 = Matcher1.Group(2)";
_isecs1 = (double)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 446;BA.debugLine="iMilSecs1 = Matcher1.Group(3)/100";
_imilsecs1 = (double)(Double.parseDouble(_matcher1.Group((int) (3))))/(double)100;
 //BA.debugLineNum = 448;BA.debugLine="LogColor($\"Minutes : \"$ & iMins1, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("721757972",("Minutes : ")+BA.NumberToString(_imins1),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 449;BA.debugLine="LogColor($\"Seconds : \"$ & iSecs1, Colors.Magent";
anywheresoftware.b4a.keywords.Common.LogImpl("721757973",("Seconds : ")+BA.NumberToString(_isecs1),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 450;BA.debugLine="LogColor($\"Milliseconds : \"$ & iMilSecs1, Color";
anywheresoftware.b4a.keywords.Common.LogImpl("721757974",("Milliseconds : ")+BA.NumberToString(_imilsecs1),anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 452;BA.debugLine="If iMins1 > 1 Then";
if (_imins1>1) { 
 //BA.debugLineNum = 453;BA.debugLine="dTry1 = (iMins1 * 60) + iSecs1 + iMilSecs1";
_dtry1 = (_imins1*60)+_isecs1+_imilsecs1;
 }else {
 //BA.debugLineNum = 455;BA.debugLine="dTry1 = iSecs1 + iMilSecs1";
_dtry1 = _isecs1+_imilsecs1;
 };
 };
 }else if(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txttry1.getText()))>0 && mostCurrent._chkmanual.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 459;BA.debugLine="dTry1 = GlobalVar.SF.Val(txtTry1.Text)";
_dtry1 = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txttry1.getText());
 };
 //BA.debugLineNum = 462;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtTry2.Tex";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txttry2.getText()))>0 && mostCurrent._chkmanual.getChecked()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 463;BA.debugLine="Matcher2 = Regex.Matcher(\"(\\d\\d):(\\d\\d).(\\d\\d)\",";
_matcher2 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d).(\\d\\d)",mostCurrent._txttry2.getText());
 //BA.debugLineNum = 464;BA.debugLine="If Matcher2.Find Then";
if (_matcher2.Find()) { 
 //BA.debugLineNum = 466;BA.debugLine="iMins2 = Matcher2.Group(1)";
_imins2 = (double)(Double.parseDouble(_matcher2.Group((int) (1))));
 //BA.debugLineNum = 467;BA.debugLine="iSecs2 = Matcher2.Group(2)";
_isecs2 = (double)(Double.parseDouble(_matcher2.Group((int) (2))));
 //BA.debugLineNum = 468;BA.debugLine="iMilSecs2 = Matcher2.Group(3)/100";
_imilsecs2 = (double)(Double.parseDouble(_matcher2.Group((int) (3))))/(double)100;
 //BA.debugLineNum = 470;BA.debugLine="LogColor($\"Minutes : \"$ & iMins2, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("721757994",("Minutes : ")+BA.NumberToString(_imins2),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 471;BA.debugLine="LogColor($\"Seconds : \"$ & iSecs2, Colors.Magent";
anywheresoftware.b4a.keywords.Common.LogImpl("721757995",("Seconds : ")+BA.NumberToString(_isecs2),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 472;BA.debugLine="LogColor($\"Milliseconds : \"$ & iMilSecs2, Color";
anywheresoftware.b4a.keywords.Common.LogImpl("721757996",("Milliseconds : ")+BA.NumberToString(_imilsecs2),anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 474;BA.debugLine="If iMins2 > 1 Then";
if (_imins2>1) { 
 //BA.debugLineNum = 475;BA.debugLine="dTry2 = (iMins2 * 60) + iSecs2 + iMilSecs2";
_dtry2 = (_imins2*60)+_isecs2+_imilsecs2;
 }else {
 //BA.debugLineNum = 477;BA.debugLine="dTry2 = iSecs2 + iMilSecs2";
_dtry2 = _isecs2+_imilsecs2;
 };
 };
 }else if(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txttry2.getText()))>0 && mostCurrent._chkmanual.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 481;BA.debugLine="dTry2 = GlobalVar.SF.Val(txtTry2.Text)";
_dtry2 = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txttry2.getText());
 };
 //BA.debugLineNum = 484;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtTry3.Tex";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txttry3.getText()))>0 && mostCurrent._chkmanual.getChecked()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 485;BA.debugLine="Matcher3 = Regex.Matcher(\"(\\d\\d):(\\d\\d).(\\d\\d)\",";
_matcher3 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d).(\\d\\d)",mostCurrent._txttry3.getText());
 //BA.debugLineNum = 486;BA.debugLine="If Matcher3.Find Then";
if (_matcher3.Find()) { 
 //BA.debugLineNum = 488;BA.debugLine="iMins3 = Matcher3.Group(1)";
_imins3 = (double)(Double.parseDouble(_matcher3.Group((int) (1))));
 //BA.debugLineNum = 489;BA.debugLine="iSecs3 = Matcher3.Group(2)";
_isecs3 = (double)(Double.parseDouble(_matcher3.Group((int) (2))));
 //BA.debugLineNum = 490;BA.debugLine="iMilSecs3 = Matcher3.Group(3)/100";
_imilsecs3 = (double)(Double.parseDouble(_matcher3.Group((int) (3))))/(double)100;
 //BA.debugLineNum = 492;BA.debugLine="LogColor($\"Minutes : \"$ & iMins3, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("721758016",("Minutes : ")+BA.NumberToString(_imins3),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 493;BA.debugLine="LogColor($\"Seconds : \"$ & iSecs3, Colors.Magent";
anywheresoftware.b4a.keywords.Common.LogImpl("721758017",("Seconds : ")+BA.NumberToString(_isecs3),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 494;BA.debugLine="LogColor($\"Milliseconds : \"$ & iMilSecs3, Color";
anywheresoftware.b4a.keywords.Common.LogImpl("721758018",("Milliseconds : ")+BA.NumberToString(_imilsecs3),anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 496;BA.debugLine="If iMins3 > 1 Then";
if (_imins3>1) { 
 //BA.debugLineNum = 497;BA.debugLine="dTry3 = (iMins3 * 60) + iSecs3 + iMilSecs3";
_dtry3 = (_imins3*60)+_isecs3+_imilsecs3;
 }else {
 //BA.debugLineNum = 499;BA.debugLine="dTry3 = iSecs3 + iMilSecs3";
_dtry3 = _isecs3+_imilsecs3;
 };
 };
 }else if(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txttry3.getText()))>0 && mostCurrent._chkmanual.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 503;BA.debugLine="dTry3 = GlobalVar.SF.Val(txtTry3.Text)";
_dtry3 = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txttry3.getText());
 };
 //BA.debugLineNum = 506;BA.debugLine="LogColor($\"Try 1: \"$ & dTry1 & $\" Try 2: \"$ & dTr";
anywheresoftware.b4a.keywords.Common.LogImpl("721758030",("Try 1: ")+BA.NumberToString(_dtry1)+(" Try 2: ")+BA.NumberToString(_dtry2)+(" Try 3: ")+BA.NumberToString(_dtry3),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 508;BA.debugLine="If cboUOM.SelectedItem = \"Liter (L)\" Then";
if ((mostCurrent._cbouom.getSelectedItem()).equals("Liter (L)")) { 
 //BA.debugLineNum = 509;BA.debugLine="mass = GlobalVar.SF.Val(txtBucketSize.Text) / 3.";
_mass = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtbucketsize.getText())/(double)3.785;
 }else {
 //BA.debugLineNum = 511;BA.debugLine="mass = GlobalVar.SF.Val(txtBucketSize.Text)";
_mass = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtbucketsize.getText());
 };
 //BA.debugLineNum = 514;BA.debugLine="aveSec = 0";
_avesec = 0;
 //BA.debugLineNum = 515;BA.debugLine="aveSec =  (dTry1 + dTry2 + dTry3) / 3";
_avesec = (_dtry1+_dtry2+_dtry3)/(double)3;
 //BA.debugLineNum = 517;BA.debugLine="hr = 60 / aveSec";
_hr = 60/(double)_avesec;
 //BA.debugLineNum = 519;BA.debugLine="dRetVal = mass * hr";
_dretval = _mass*_hr;
 //BA.debugLineNum = 521;BA.debugLine="TotalGPM.Initialize(dRetVal)";
_totalgpm.Initialize(BA.NumberToString(_dretval));
 //BA.debugLineNum = 522;BA.debugLine="TotalGPM = RoundBD(TotalGPM,2)";
_totalgpm = _roundbd(_totalgpm,(int) (2));
 //BA.debugLineNum = 524;BA.debugLine="LogColor($\"Average seconds: \"$ & aveSec, Colors.W";
anywheresoftware.b4a.keywords.Common.LogImpl("721758048",("Average seconds: ")+BA.NumberToString(_avesec),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 525;BA.debugLine="LogColor($\"Hour: \"$ & hr, Colors.Green)";
anywheresoftware.b4a.keywords.Common.LogImpl("721758049",("Hour: ")+BA.NumberToString(_hr),anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 526;BA.debugLine="LogColor($\"Mass: \"$ & mass, Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("721758050",("Mass: ")+BA.NumberToString(_mass),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 527;BA.debugLine="LogColor($\"Total GPM: \"$ & TotalGPM, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("721758051",("Total GPM: ")+BA.ObjectToString(_totalgpm),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 529;BA.debugLine="Return TotalGPM";
if (true) return (double)(BA.ObjectToNumber(_totalgpm));
 //BA.debugLineNum = 530;BA.debugLine="End Sub";
return 0;
}
public static String  _confirmstoptimer(String _stitle,String _smsg,int _isender) throws Exception{
 //BA.debugLineNum = 615;BA.debugLine="Private Sub ConfirmStopTimer(sTitle As String, sMs";
 //BA.debugLineNum = 616;BA.debugLine="iTimer = iSender";
_itimer = _isender;
 //BA.debugLineNum = 617;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._bluecolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage(_smsg).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"StopTimer").SetOnNegativeClicked(mostCurrent.activityBA,"StopTimer");
 //BA.debugLineNum = 634;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 635;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 636;BA.debugLine="End Sub";
return "";
}
public static String  _convertmillisecondstostring(long _t) throws Exception{
int _hours = 0;
int _minutes = 0;
int _seconds = 0;
int _msec = 0;
 //BA.debugLineNum = 565;BA.debugLine="Private Sub ConvertMillisecondsToString(t As Long)";
 //BA.debugLineNum = 566;BA.debugLine="Dim hours, minutes, seconds, msec As Int";
_hours = 0;
_minutes = 0;
_seconds = 0;
_msec = 0;
 //BA.debugLineNum = 567;BA.debugLine="hours = t / DateTime.TicksPerHour";
_hours = (int) (_t/(double)anywheresoftware.b4a.keywords.Common.DateTime.TicksPerHour);
 //BA.debugLineNum = 568;BA.debugLine="minutes = (t Mod DateTime.TicksPerHour) / DateTim";
_minutes = (int) ((_t%anywheresoftware.b4a.keywords.Common.DateTime.TicksPerHour)/(double)anywheresoftware.b4a.keywords.Common.DateTime.TicksPerMinute);
 //BA.debugLineNum = 569;BA.debugLine="seconds = (t Mod DateTime.TicksPerMinute) / DateT";
_seconds = (int) ((_t%anywheresoftware.b4a.keywords.Common.DateTime.TicksPerMinute)/(double)anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond);
 //BA.debugLineNum = 570;BA.debugLine="msec = DateTime.TicksPerSecond / 100";
_msec = (int) (anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond/(double)100);
 //BA.debugLineNum = 571;BA.debugLine="Return $\"$1.0{hours}:$2.0{minutes}:$2.0{seconds}.";
if (true) return (""+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("1.0",(Object)(_hours))+":"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("2.0",(Object)(_minutes))+":"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("2.0",(Object)(_seconds))+"."+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("2.0",(Object)(_msec))+"");
 //BA.debugLineNum = 572;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.StateListDrawable  _createbuttoncolor(int _focusedcolor,int _enabledcolor,int _disabledcolor,int _pressedcolor) throws Exception{
anywheresoftware.b4a.objects.drawable.StateListDrawable _retcolor = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _drwfocusedcolor = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _drwenabledcolor = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _drwdisabledcolor = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _drwpressedcolor = null;
 //BA.debugLineNum = 579;BA.debugLine="Public Sub CreateButtonColor(FocusedColor As Int,";
 //BA.debugLineNum = 581;BA.debugLine="Dim RetColor As StateListDrawable";
_retcolor = new anywheresoftware.b4a.objects.drawable.StateListDrawable();
 //BA.debugLineNum = 582;BA.debugLine="Dim drwFocusedColor, drwEnabledColor, drwDisabled";
_drwfocusedcolor = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
_drwenabledcolor = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
_drwdisabledcolor = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
_drwpressedcolor = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 589;BA.debugLine="RetColor.Initialize";
_retcolor.Initialize();
 //BA.debugLineNum = 591;BA.debugLine="drwFocusedColor.Initialize2(FocusedColor, 25, 0,";
_drwfocusedcolor.Initialize2(_focusedcolor,(int) (25),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 592;BA.debugLine="drwEnabledColor.Initialize2(EnabledColor, 25, 0,";
_drwenabledcolor.Initialize2(_enabledcolor,(int) (25),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 593;BA.debugLine="drwDisabledColor.Initialize2(DisabledColor, 25, 2";
_drwdisabledcolor.Initialize2(_disabledcolor,(int) (25),(int) (2),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 594;BA.debugLine="drwPressedColor.Initialize2(PressedColor, 25, 0,";
_drwpressedcolor.Initialize2(_pressedcolor,(int) (25),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 596;BA.debugLine="RetColor.AddState(RetColor.State_Focused, drwFocu";
_retcolor.AddState(_retcolor.State_Focused,(android.graphics.drawable.Drawable)(_drwfocusedcolor.getObject()));
 //BA.debugLineNum = 597;BA.debugLine="RetColor.AddState(RetColor.State_Pressed, drwPres";
_retcolor.AddState(_retcolor.State_Pressed,(android.graphics.drawable.Drawable)(_drwpressedcolor.getObject()));
 //BA.debugLineNum = 598;BA.debugLine="RetColor.AddState(RetColor.State_Enabled, drwEnab";
_retcolor.AddState(_retcolor.State_Enabled,(android.graphics.drawable.Drawable)(_drwenabledcolor.getObject()));
 //BA.debugLineNum = 599;BA.debugLine="RetColor.AddState(RetColor.State_Disabled, drwDis";
_retcolor.AddState(_retcolor.State_Disabled,(android.graphics.drawable.Drawable)(_drwdisabledcolor.getObject()));
 //BA.debugLineNum = 600;BA.debugLine="RetColor.AddCatchAllState(drwFocusedColor)";
_retcolor.AddCatchAllState((android.graphics.drawable.Drawable)(_drwfocusedcolor.getObject()));
 //BA.debugLineNum = 601;BA.debugLine="RetColor.AddCatchAllState(drwEnabledColor)";
_retcolor.AddCatchAllState((android.graphics.drawable.Drawable)(_drwenabledcolor.getObject()));
 //BA.debugLineNum = 602;BA.debugLine="RetColor.AddCatchAllState(drwDisabledColor)";
_retcolor.AddCatchAllState((android.graphics.drawable.Drawable)(_drwdisabledcolor.getObject()));
 //BA.debugLineNum = 603;BA.debugLine="RetColor.AddCatchAllState(drwPressedColor)";
_retcolor.AddCatchAllState((android.graphics.drawable.Drawable)(_drwpressedcolor.getObject()));
 //BA.debugLineNum = 604;BA.debugLine="Return RetColor";
if (true) return _retcolor;
 //BA.debugLineNum = 606;BA.debugLine="End Sub";
return null;
}
public static void  _fillrecords(int _igpmid) throws Exception{
ResumableSub_FillRecords rsub = new ResumableSub_FillRecords(null,_igpmid);
rsub.resume(processBA, null);
}
public static class ResumableSub_FillRecords extends BA.ResumableSub {
public ResumableSub_FillRecords(bwsi.PumpOperations.actgpmcalc parent,int _igpmid) {
this.parent = parent;
this._igpmid = _igpmid;
}
bwsi.PumpOperations.actgpmcalc parent;
int _igpmid;
Object _senderfilter = null;
String _suom = "";
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
 //BA.debugLineNum = 397;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 398;BA.debugLine="Dim sUOM As String";
_suom = "";
 //BA.debugLineNum = 399;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 10;
this.catchState = 9;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 9;
 //BA.debugLineNum = 401;BA.debugLine="Starter.strCriteria = \"SELECT BucketSize, UnitOf";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT BucketSize, UnitOfMeasurement, Trial1, Trial2, Trial3, WaterQuality, Remarks "+"FROM tblGPMHistory "+"WHERE GPMID = "+BA.NumberToString(_igpmid);
 //BA.debugLineNum = 405;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 406;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 407;BA.debugLine="If Success Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_success) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 408;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 409;BA.debugLine="txtBucketSize.Text = RS.GetInt(\"BucketSize\")";
parent.mostCurrent._txtbucketsize.setText(BA.ObjectToCharSequence(_rs.GetInt("BucketSize")));
 //BA.debugLineNum = 410;BA.debugLine="sUOM = RS.GetString(\"UnitOfMeasurement\")";
_suom = _rs.GetString("UnitOfMeasurement");
 //BA.debugLineNum = 411;BA.debugLine="txtTry1.Text = RS.GetString(\"Trial1\")";
parent.mostCurrent._txttry1.setText(BA.ObjectToCharSequence(_rs.GetString("Trial1")));
 //BA.debugLineNum = 412;BA.debugLine="txtTry2.Text = RS.GetString(\"Trial2\")";
parent.mostCurrent._txttry2.setText(BA.ObjectToCharSequence(_rs.GetString("Trial2")));
 //BA.debugLineNum = 413;BA.debugLine="txtTry3.Text = RS.GetString(\"Trial3\")";
parent.mostCurrent._txttry3.setText(BA.ObjectToCharSequence(_rs.GetString("Trial3")));
 //BA.debugLineNum = 414;BA.debugLine="txtWaterQuality.Text = RS.GetString(\"WaterQuali";
parent.mostCurrent._txtwaterquality.setText(BA.ObjectToCharSequence(_rs.GetString("WaterQuality")));
 //BA.debugLineNum = 415;BA.debugLine="txtRemarks.Text = RS.GetString(\"Remarks\")";
parent.mostCurrent._txtremarks.setText(BA.ObjectToCharSequence(_rs.GetString("Remarks")));
 //BA.debugLineNum = 416;BA.debugLine="cboUOM.SelectedIndex = cboUOM.IndexOf(sUOM)";
parent.mostCurrent._cbouom.setSelectedIndex(parent.mostCurrent._cbouom.IndexOf(BA.ObjectToCharSequence(_suom)));
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
 //BA.debugLineNum = 419;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 420;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 421;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 422;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 423;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("721692443",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 426;BA.debugLine="End Sub";
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
public static String  _filluom() throws Exception{
 //BA.debugLineNum = 389;BA.debugLine="Private Sub FillUOM";
 //BA.debugLineNum = 390;BA.debugLine="cboUOM.Clear";
mostCurrent._cbouom.Clear();
 //BA.debugLineNum = 391;BA.debugLine="cboUOM.Add(\"Liter (L)\")";
mostCurrent._cbouom.Add(BA.ObjectToCharSequence("Liter (L)"));
 //BA.debugLineNum = 392;BA.debugLine="cboUOM.Add(\"Milliliter (mL)\")";
mostCurrent._cbouom.Add(BA.ObjectToCharSequence("Milliliter (mL)"));
 //BA.debugLineNum = 393;BA.debugLine="cboUOM.Add(\"Gallon (gal)\")";
mostCurrent._cbouom.Add(BA.ObjectToCharSequence("Gallon (gal)"));
 //BA.debugLineNum = 394;BA.debugLine="End Sub";
return "";
}
public static String  _fontsizebinder_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _savealert = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 979;BA.debugLine="Private Sub FontSizeBinder_OnBindView (View As Vie";
 //BA.debugLineNum = 980;BA.debugLine="Dim SaveAlert As AX_CustomAlertDialog";
_savealert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 981;BA.debugLine="SaveAlert.Initialize";
_savealert.Initialize();
 //BA.debugLineNum = 982;BA.debugLine="If ViewType = Alert.VIEW_TITLE Then ' Title";
if (_viewtype==mostCurrent._alert.VIEW_TITLE) { 
 //BA.debugLineNum = 983;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 984;BA.debugLine="lbl.TextSize = 30";
_lbl.setTextSize((float) (30));
 //BA.debugLineNum = 985;BA.debugLine="lbl.SetTextColorAnimated(2000,Colors.Magenta)";
_lbl.SetTextColorAnimated((int) (2000),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 988;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 992;BA.debugLine="CS.Initialize.Typeface(Typeface.MATERIALICONS).S";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe88e)))+"  "));
 //BA.debugLineNum = 993;BA.debugLine="CS.Typeface(Font).Size(24).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(mostCurrent._font.getObject())).Size((int) (24)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 995;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 997;BA.debugLine="End Sub";
return "";
}
public static int  _getgpmid(String _strandate,int _ipumpid) throws Exception{
int _iretval = 0;
 //BA.debugLineNum = 297;BA.debugLine="Private Sub GetGPMID (sTranDate As String, iPumpID";
 //BA.debugLineNum = 298;BA.debugLine="Dim iRetval As Int";
_iretval = 0;
 //BA.debugLineNum = 299;BA.debugLine="Try";
try { //BA.debugLineNum = 300;BA.debugLine="Starter.strCriteria = \"SELECT GPMID FROM tblGPMH";
mostCurrent._starter._strcriteria /*String*/  = "SELECT GPMID FROM tblGPMHistory "+"WHERE PumpID = "+BA.NumberToString(_ipumpid)+" "+"AND TranDate = '"+_strandate+"'";
 //BA.debugLineNum = 303;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("721233670",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 305;BA.debugLine="iRetval = Starter.DBCon.ExecQuerySingleResult(St";
_iretval = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 307;BA.debugLine="ToastMessageShow($\"Unable to fetch GPM Record ID";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch GPM Record ID due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 308;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("721233675",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 309;BA.debugLine="iRetval = 0";
_iretval = (int) (0);
 };
 //BA.debugLineNum = 311;BA.debugLine="Return iRetval";
if (true) return _iretval;
 //BA.debugLineNum = 312;BA.debugLine="End Sub";
return 0;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 29;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 30;BA.debugLine="Private xmlIcon As XmlLayoutBuilder";
mostCurrent._xmlicon = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 32;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private CDTotGPM, CDtxtBox, CDButton, cdRem, cdGP";
mostCurrent._cdtotgpm = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdtxtbox = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdbutton = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdrem = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdgpm = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 37;BA.debugLine="Private vibration As B4Avibrate";
mostCurrent._vibration = new com.johan.Vibrate.Vibrate();
 //BA.debugLineNum = 38;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private cdReading As ColorDrawable";
mostCurrent._cdreading = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 41;BA.debugLine="Private btnSaveUpdate As ACButton";
mostCurrent._btnsaveupdate = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private ToastMsg As BCToast";
mostCurrent._toastmsg = new bwsi.PumpOperations.bctoast();
 //BA.debugLineNum = 45;BA.debugLine="Private kBoard As IME";
mostCurrent._kboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 47;BA.debugLine="Private txtBucketSize As EditText";
mostCurrent._txtbucketsize = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private cboUOM As ACSpinner";
mostCurrent._cbouom = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private SW1 As MLStopWatch";
mostCurrent._sw1 = new mlstopwatch.MLStopWatch();
 //BA.debugLineNum = 51;BA.debugLine="Private SW2 As MLStopWatch";
mostCurrent._sw2 = new mlstopwatch.MLStopWatch();
 //BA.debugLineNum = 52;BA.debugLine="Private SW3 As MLStopWatch";
mostCurrent._sw3 = new mlstopwatch.MLStopWatch();
 //BA.debugLineNum = 54;BA.debugLine="Private cdStart As ColorDrawable";
mostCurrent._cdstart = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 55;BA.debugLine="Private cdStop As ColorDrawable";
mostCurrent._cdstop = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 56;BA.debugLine="Private cdReset As ColorDrawable";
mostCurrent._cdreset = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 57;BA.debugLine="Private cdPause As ColorDrawable";
mostCurrent._cdpause = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 59;BA.debugLine="Private txtGPM As EditText";
mostCurrent._txtgpm = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Private txtRemarks As EditText";
mostCurrent._txtremarks = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Private txtWaterQuality As EditText";
mostCurrent._txtwaterquality = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 65;BA.debugLine="Private btnShowTimer1 As ACButton";
mostCurrent._btnshowtimer1 = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 66;BA.debugLine="Private btnShowTimer2 As ACButton";
mostCurrent._btnshowtimer2 = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Private btnShowTimer3 As ACButton";
mostCurrent._btnshowtimer3 = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 70;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 71;BA.debugLine="Private Font As Typeface = Typeface.LoadFromAsset";
mostCurrent._font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont.ttf")));
 //BA.debugLineNum = 72;BA.debugLine="Private FontBold As Typeface = Typeface.LoadFromA";
mostCurrent._fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont_bold.ttf")));
 //BA.debugLineNum = 74;BA.debugLine="Private iTimer As Int";
_itimer = 0;
 //BA.debugLineNum = 75;BA.debugLine="Private dTime1, dTime2, dTime3 As Double";
_dtime1 = 0;
_dtime2 = 0;
_dtime3 = 0;
 //BA.debugLineNum = 76;BA.debugLine="Private sTime1, sTime2, sTime3 As String";
mostCurrent._stime1 = "";
mostCurrent._stime2 = "";
mostCurrent._stime3 = "";
 //BA.debugLineNum = 77;BA.debugLine="Private pnlMainTimer As Panel";
mostCurrent._pnlmaintimer = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 78;BA.debugLine="Private pnlStopWatch As Panel";
mostCurrent._pnlstopwatch = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 79;BA.debugLine="Private btnReset As ACButton";
mostCurrent._btnreset = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 80;BA.debugLine="Private btnPause As ACButton";
mostCurrent._btnpause = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 81;BA.debugLine="Private btnStart As ACButton";
mostCurrent._btnstart = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 82;BA.debugLine="Private btnStop As ACButton";
mostCurrent._btnstop = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 83;BA.debugLine="Private txtTry1 As EditText";
mostCurrent._txttry1 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 84;BA.debugLine="Private txtTry2 As EditText";
mostCurrent._txttry2 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 85;BA.debugLine="Private txtTry3 As EditText";
mostCurrent._txttry3 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 86;BA.debugLine="Private chkManual As CheckBox";
mostCurrent._chkmanual = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 88;BA.debugLine="Private btnOk As ACButton";
mostCurrent._btnok = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 89;BA.debugLine="Private btnCancel As ACButton";
mostCurrent._btncancel = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public static String  _gpmexist_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 363;BA.debugLine="Private Sub GPMExist_ButtonPressed(mDialog As Mate";
 //BA.debugLineNum = 364;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE)) {
case 0: {
 //BA.debugLineNum = 366;BA.debugLine="GlobalVar.blnNewGPM = False";
mostCurrent._globalvar._blnnewgpm /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 367;BA.debugLine="GlobalVar.GPMId = GetGPMID(GlobalVar.TranDate,";
mostCurrent._globalvar._gpmid /*int*/  = _getgpmid(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 368;BA.debugLine="If Not(UpdateGPM(GlobalVar.GPMId, GlobalVar.Tra";
if (anywheresoftware.b4a.keywords.Common.Not(_updategpm(mostCurrent._globalvar._gpmid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ ))) { 
if (true) return "";};
 //BA.debugLineNum = 369;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 break; }
case 1: {
 //BA.debugLineNum = 371;BA.debugLine="Return";
if (true) return "";
 break; }
}
;
 //BA.debugLineNum = 373;BA.debugLine="End Sub";
return "";
}
public static String  _initobjects() throws Exception{
 //BA.debugLineNum = 856;BA.debugLine="Private Sub InitObjects";
 //BA.debugLineNum = 857;BA.debugLine="chkManual.Checked = False";
mostCurrent._chkmanual.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 858;BA.debugLine="txtGPM.Text = \"0\"";
mostCurrent._txtgpm.setText(BA.ObjectToCharSequence("0"));
 //BA.debugLineNum = 859;BA.debugLine="txtBucketSize.Text = \"\"";
mostCurrent._txtbucketsize.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 860;BA.debugLine="txtTry1.Text = \"\"";
mostCurrent._txttry1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 861;BA.debugLine="txtTry2.Text = \"\"";
mostCurrent._txttry2.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 862;BA.debugLine="txtTry3.Text = \"\"";
mostCurrent._txttry3.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 863;BA.debugLine="txtWaterQuality.Text = \"\"";
mostCurrent._txtwaterquality.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 864;BA.debugLine="txtRemarks.Text = \"\"";
mostCurrent._txtremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 865;BA.debugLine="txtTry1.Enabled = False";
mostCurrent._txttry1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 866;BA.debugLine="txtTry2.Enabled = False";
mostCurrent._txttry2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 867;BA.debugLine="txtTry3.Enabled = False";
mostCurrent._txttry3.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 868;BA.debugLine="btnShowTimer1.Enabled = True";
mostCurrent._btnshowtimer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 869;BA.debugLine="btnShowTimer2.Enabled = False";
mostCurrent._btnshowtimer2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 870;BA.debugLine="btnShowTimer3.Enabled = False";
mostCurrent._btnshowtimer3.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 871;BA.debugLine="pnlMainTimer.Visible = False";
mostCurrent._pnlmaintimer.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 872;BA.debugLine="End Sub";
return "";
}
public static boolean  _isvalidentries() throws Exception{
boolean _blnretval = false;
 //BA.debugLineNum = 919;BA.debugLine="Private Sub IsValidEntries() As Boolean";
 //BA.debugLineNum = 920;BA.debugLine="Dim blnRetVal As Boolean";
_blnretval = false;
 //BA.debugLineNum = 922;BA.debugLine="blnRetVal = False";
_blnretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 924;BA.debugLine="Try";
try { //BA.debugLineNum = 925;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtBucketS";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtbucketsize.getText()))<=0 || (mostCurrent._txtbucketsize.getText()).equals("")) { 
 //BA.debugLineNum = 926;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, \"Bucket size cann";
_requiredmsgbox(("E R R O R"),"Bucket size cannot be blank!");
 //BA.debugLineNum = 927;BA.debugLine="txtBucketSize.RequestFocus";
mostCurrent._txtbucketsize.RequestFocus();
 //BA.debugLineNum = 928;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 931;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtTry1.Te";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txttry1.getText()))==0 || (mostCurrent._txttry1.getText()).equals("")) { 
 //BA.debugLineNum = 932;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, \"First Try cannot";
_requiredmsgbox(("E R R O R"),"First Try cannot be blank!");
 //BA.debugLineNum = 933;BA.debugLine="If chkManual.Checked = True Then";
if (mostCurrent._chkmanual.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 934;BA.debugLine="txtTry1.Enabled = True";
mostCurrent._txttry1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 935;BA.debugLine="txtTry1.RequestFocus";
mostCurrent._txttry1.RequestFocus();
 }else {
 //BA.debugLineNum = 937;BA.debugLine="txtTry1.Enabled = False";
mostCurrent._txttry1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 938;BA.debugLine="btnShowTimer1.Enabled = True";
mostCurrent._btnshowtimer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 939;BA.debugLine="btnShowTimer1.RequestFocus";
mostCurrent._btnshowtimer1.RequestFocus();
 };
 //BA.debugLineNum = 941;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else if(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txttry2.getText()))==0 || (mostCurrent._txttry2.getText()).equals("")) { 
 //BA.debugLineNum = 943;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, \"Second Try canno";
_requiredmsgbox(("E R R O R"),"Second Try cannot be blank!");
 //BA.debugLineNum = 944;BA.debugLine="If chkManual.Checked = True Then";
if (mostCurrent._chkmanual.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 945;BA.debugLine="txtTry2.Enabled = True";
mostCurrent._txttry2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 946;BA.debugLine="txtTry2.RequestFocus";
mostCurrent._txttry2.RequestFocus();
 }else {
 //BA.debugLineNum = 948;BA.debugLine="txtTry2.Enabled = False";
mostCurrent._txttry2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 949;BA.debugLine="btnShowTimer2.Enabled = True";
mostCurrent._btnshowtimer2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 950;BA.debugLine="btnShowTimer1.RequestFocus";
mostCurrent._btnshowtimer1.RequestFocus();
 };
 //BA.debugLineNum = 952;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else if(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txttry3.getText()))==0 || (mostCurrent._txttry3.getText()).equals("")) { 
 //BA.debugLineNum = 954;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, \"Third Try cannot";
_requiredmsgbox(("E R R O R"),"Third Try cannot be blank!");
 //BA.debugLineNum = 955;BA.debugLine="If chkManual.Checked = True Then";
if (mostCurrent._chkmanual.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 956;BA.debugLine="txtTry3.Enabled = True";
mostCurrent._txttry3.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 957;BA.debugLine="txtTry3.RequestFocus";
mostCurrent._txttry3.RequestFocus();
 }else {
 //BA.debugLineNum = 959;BA.debugLine="txtTry3.Enabled = False";
mostCurrent._txttry3.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 960;BA.debugLine="btnShowTimer3.Enabled = True";
mostCurrent._btnshowtimer3.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 961;BA.debugLine="btnShowTimer3.RequestFocus";
mostCurrent._btnshowtimer3.RequestFocus();
 };
 //BA.debugLineNum = 963;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 966;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtWaterQu";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtwaterquality.getText()))<=0 || (mostCurrent._txtwaterquality.getText()).equals("")) { 
 //BA.debugLineNum = 967;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, \"Water Quality ca";
_requiredmsgbox(("E R R O R"),"Water Quality cannot be blank!");
 //BA.debugLineNum = 968;BA.debugLine="txtWaterQuality.RequestFocus";
mostCurrent._txtwaterquality.RequestFocus();
 //BA.debugLineNum = 969;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 971;BA.debugLine="blnRetVal = True";
_blnretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e50) {
			processBA.setLastException(e50); //BA.debugLineNum = 973;BA.debugLine="blnRetVal = False";
_blnretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 974;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("723593015",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 976;BA.debugLine="Return blnRetVal";
if (true) return _blnretval;
 //BA.debugLineNum = 977;BA.debugLine="End Sub";
return false;
}
public static String  _pnlmaintimer_click() throws Exception{
 //BA.debugLineNum = 999;BA.debugLine="Sub pnlMainTimer_Click";
 //BA.debugLineNum = 1001;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 21;BA.debugLine="Private InpTyp As SLInpTypeConst";
_inptyp = new bwsi.PumpOperations.slinptypeconst();
 //BA.debugLineNum = 23;BA.debugLine="Private Timer1 As Timer";
_timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 24;BA.debugLine="Private StartTime As Long";
_starttime = 0L;
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsg_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 915;BA.debugLine="Private Sub RequiredMsg_OnPositiveClicked (View As";
 //BA.debugLineNum = 916;BA.debugLine="Alert.Initialize.Dismiss2";
mostCurrent._alert.Initialize().Dismiss2();
 //BA.debugLineNum = 917;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsgbox(String _stitle,String _smsg) throws Exception{
 //BA.debugLineNum = 893;BA.debugLine="Private Sub RequiredMsgBox(sTitle As String, sMsg";
 //BA.debugLineNum = 894;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._redcolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage(_smsg).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"RequiredMsg").SetOnViewBinder(mostCurrent.activityBA,"FontSizeBinder");
 //BA.debugLineNum = 909;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 910;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 911;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper  _roundbd(anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper _bd,int _dp) throws Exception{
 //BA.debugLineNum = 532;BA.debugLine="Sub RoundBD(BD As BigDecimal, DP As Int) As BigDec";
 //BA.debugLineNum = 533;BA.debugLine="BD.Round(BD.Precision - BD.Scale + DP, BD.ROUND_H";
_bd.Round((int) (_bd.Precision()-_bd.Scale()+_dp),_bd.ROUND_HALF_UP);
 //BA.debugLineNum = 534;BA.debugLine="Return BD";
if (true) return _bd;
 //BA.debugLineNum = 535;BA.debugLine="End Sub";
return null;
}
public static boolean  _savenewgpm() throws Exception{
boolean _bretval = false;
String _sdatetime = "";
long _ldate = 0L;
 //BA.debugLineNum = 233;BA.debugLine="Private Sub SaveNewGPM() As Boolean";
 //BA.debugLineNum = 234;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 235;BA.debugLine="Dim sDateTime As String";
_sdatetime = "";
 //BA.debugLineNum = 236;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 238;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 239;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 240;BA.debugLine="sDateTime = DateTime.Date(lDate)";
_sdatetime = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 242;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 243;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 244;BA.debugLine="Try";
try { //BA.debugLineNum = 245;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO tblGPMH";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO tblGPMHistory VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._pumphouseid /*int*/ ),(Object)(mostCurrent._globalvar._trandate /*String*/ ),(Object)(mostCurrent._txtbucketsize.getText()),(Object)(mostCurrent._cbouom.getSelectedItem()),(Object)(mostCurrent._txttry1.getText()),(Object)(mostCurrent._txttry2.getText()),(Object)(mostCurrent._txttry3.getText()),(Object)(mostCurrent._txtgpm.getText()),(Object)(mostCurrent._txtwaterquality.getText()),(Object)(mostCurrent._txtremarks.getText()),(Object)(("0")),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_sdatetime),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null}));
 //BA.debugLineNum = 247;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 248;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e14) {
			processBA.setLastException(e14); //BA.debugLineNum = 250;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("721102609",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 251;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 253;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 254;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 255;BA.debugLine="End Sub";
return false;
}
public static String  _savesuccess_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 337;BA.debugLine="Private Sub SaveSuccess_ButtonPressed(mDialog As M";
 //BA.debugLineNum = 338;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEUTRAL)) {
case 0: {
 //BA.debugLineNum = 340;BA.debugLine="ClearDisplay";
_cleardisplay();
 break; }
case 1: {
 //BA.debugLineNum = 342;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
}
;
 //BA.debugLineNum = 344;BA.debugLine="End Sub";
return "";
}
public static String  _setbuttoncolors() throws Exception{
 //BA.debugLineNum = 608;BA.debugLine="Private Sub SetButtonColors()";
 //BA.debugLineNum = 610;BA.debugLine="btnShowTimer1.Background = CreateButtonColor(0xFF";
mostCurrent._btnshowtimer1.setBackground((android.graphics.drawable.Drawable)(_createbuttoncolor((int) (0xff28a745),(int) (0xff1976d2),(int) (0xff1e88e5),(int) (0xff28a745)).getObject()));
 //BA.debugLineNum = 611;BA.debugLine="btnShowTimer2.Background = CreateButtonColor(0xFF";
mostCurrent._btnshowtimer2.setBackground((android.graphics.drawable.Drawable)(_createbuttoncolor((int) (0xff28a745),(int) (0xff1976d2),(int) (0xff1e88e5),(int) (0xff28a745)).getObject()));
 //BA.debugLineNum = 612;BA.debugLine="btnShowTimer3.Background = CreateButtonColor(0xFF";
mostCurrent._btnshowtimer3.setBackground((android.graphics.drawable.Drawable)(_createbuttoncolor((int) (0xff28a745),(int) (0xff1976d2),(int) (0xff1e88e5),(int) (0xff28a745)).getObject()));
 //BA.debugLineNum = 613;BA.debugLine="End Sub";
return "";
}
public static String  _showgpmexist() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
anywheresoftware.b4a.objects.CSBuilder _cscontent = null;
 //BA.debugLineNum = 346;BA.debugLine="Private Sub ShowGPMExist()";
 //BA.debugLineNum = 347;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 348;BA.debugLine="Dim csContent As CSBuilder";
_cscontent = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 350;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar.";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("Confirm Save!"))).PopAll();
 //BA.debugLineNum = 351;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black)";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("GPM Result is already exist for the specified Pump and Transaction Date.")+anywheresoftware.b4a.keywords.Common.CRLF+("Do you want to overwrite existing GPM result?"))).PopAll();
 //BA.debugLineNum = 352;BA.debugLine="MatDialogBuilder.Initialize(\"GPMExist\")";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"GPMExist");
 //BA.debugLineNum = 353;BA.debugLine="MatDialogBuilder.PositiveText(\"YES\").PositiveColo";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("YES")).PositiveColor((int) (mostCurrent._globalvar._bluecolor /*double*/ ));
 //BA.debugLineNum = 354;BA.debugLine="MatDialogBuilder.NegativeText(\"NO\").NegativeColor";
mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence("NO")).NegativeColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 355;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 356;BA.debugLine="MatDialogBuilder.Content(csContent)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_cscontent.getObject()));
 //BA.debugLineNum = 357;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 358;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 359;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 360;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 361;BA.debugLine="End Sub";
return "";
}
public static String  _showsavesuccess() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
anywheresoftware.b4a.objects.CSBuilder _cscontent = null;
 //BA.debugLineNum = 314;BA.debugLine="Private Sub ShowSaveSuccess()";
 //BA.debugLineNum = 315;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 316;BA.debugLine="Dim csContent As CSBuilder";
_cscontent = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 318;BA.debugLine="If GlobalVar.blnNewGPM = True Then";
if (mostCurrent._globalvar._blnnewgpm /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 319;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 320;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("New GPM Result has been successfully saved!"))).PopAll();
 }else {
 //BA.debugLineNum = 322;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 323;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("GPM Result has been successfully updated!"))).PopAll();
 };
 //BA.debugLineNum = 326;BA.debugLine="MatDialogBuilder.Initialize(\"SaveSuccess\")";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"SaveSuccess");
 //BA.debugLineNum = 327;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 328;BA.debugLine="MatDialogBuilder.NeutralText($\"Close GPM Calculat";
mostCurrent._matdialogbuilder.NeutralText(BA.ObjectToCharSequence(("Close GPM Calculator?"))).NeutralColor((int) (mostCurrent._globalvar._neutralcolor /*double*/ ));
 //BA.debugLineNum = 329;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 330;BA.debugLine="MatDialogBuilder.Content(csContent)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_cscontent.getObject()));
 //BA.debugLineNum = 331;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 332;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 333;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 334;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 335;BA.debugLine="End Sub";
return "";
}
public static String  _showtimer() throws Exception{
 //BA.debugLineNum = 680;BA.debugLine="Sub ShowTimer";
 //BA.debugLineNum = 681;BA.debugLine="cdStart.Initialize2(GlobalVar.GreenColor2, 20, 0,";
mostCurrent._cdstart.Initialize2((int) (mostCurrent._globalvar._greencolor2 /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 682;BA.debugLine="cdPause.Initialize2(GlobalVar.YellowColor, 20, 0,";
mostCurrent._cdpause.Initialize2((int) (mostCurrent._globalvar._yellowcolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 683;BA.debugLine="cdStop.Initialize2(GlobalVar.RedColor, 20, 0, Col";
mostCurrent._cdstop.Initialize2((int) (mostCurrent._globalvar._redcolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 684;BA.debugLine="cdReset.Initialize2(GlobalVar.BlueColor, 25, 0, C";
mostCurrent._cdreset.Initialize2((int) (mostCurrent._globalvar._bluecolor /*double*/ ),(int) (25),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 686;BA.debugLine="pnlMainTimer.Visible = True";
mostCurrent._pnlmaintimer.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 687;BA.debugLine="SW1.Initialize(\"\")";
mostCurrent._sw1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 688;BA.debugLine="pnlStopWatch.AddView(SW1,1%x, 1%y, pnlStopWatch.W";
mostCurrent._pnlstopwatch.AddView((android.view.View)(mostCurrent._sw1.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1),mostCurrent.activityBA),(int) (mostCurrent._pnlstopwatch.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA)),(int) (mostCurrent._pnlstopwatch.getHeight()-anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1),mostCurrent.activityBA)));
 //BA.debugLineNum = 689;BA.debugLine="SW1.Color = Colors.Black";
mostCurrent._sw1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 690;BA.debugLine="SW1.TextColor = 0xFFADFF2F";
mostCurrent._sw1.setTextColor((int) (0xffadff2f));
 //BA.debugLineNum = 691;BA.debugLine="SW1.TextSize = 35";
mostCurrent._sw1.setTextSize((float) (35));
 //BA.debugLineNum = 692;BA.debugLine="SW1.Typeface = Typeface.DEFAULT_BOLD";
mostCurrent._sw1.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 693;BA.debugLine="SW1.Gravity = Gravity.CENTER";
mostCurrent._sw1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 695;BA.debugLine="btnStart.Background = cdStart";
mostCurrent._btnstart.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdstart.getObject()));
 //BA.debugLineNum = 696;BA.debugLine="btnStop.Background = cdStop";
mostCurrent._btnstop.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdstop.getObject()));
 //BA.debugLineNum = 697;BA.debugLine="btnPause.Background = cdPause";
mostCurrent._btnpause.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdpause.getObject()));
 //BA.debugLineNum = 698;BA.debugLine="btnReset.Background = cdReset";
mostCurrent._btnreset.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdreset.getObject()));
 //BA.debugLineNum = 699;BA.debugLine="MyFunctions.SetButton(btnOk, 20, 20, 20, 20, 20,";
mostCurrent._myfunctions._setbutton /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btnok.getObject())),(float) (20),(float) (20),(float) (20),(float) (20),(float) (20),(float) (20),(float) (20),(float) (20));
 //BA.debugLineNum = 700;BA.debugLine="MyFunctions.SetCancelButton(btnCancel, 20, 20, 20";
mostCurrent._myfunctions._setcancelbutton /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btncancel.getObject())),(float) (20),(float) (20),(float) (20),(float) (20),(float) (20),(float) (20),(float) (20),(float) (20));
 //BA.debugLineNum = 702;BA.debugLine="btnStart.Enabled = True";
mostCurrent._btnstart.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 703;BA.debugLine="btnStop.Enabled = False";
mostCurrent._btnstop.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 704;BA.debugLine="btnPause.Enabled = False";
mostCurrent._btnpause.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 705;BA.debugLine="btnOk.Enabled = False";
mostCurrent._btnok.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 706;BA.debugLine="btnCancel.Enabled = True";
mostCurrent._btncancel.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 707;BA.debugLine="btnReset.Enabled = False";
mostCurrent._btnreset.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 708;BA.debugLine="End Sub";
return "";
}
public static String  _stoptimer_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 639;BA.debugLine="Private Sub StopTimer_OnNegativeClicked (View As V";
 //BA.debugLineNum = 640;BA.debugLine="Alert.Initialize.Dismiss2";
mostCurrent._alert.Initialize().Dismiss2();
 //BA.debugLineNum = 641;BA.debugLine="End Sub";
return "";
}
public static String  _stoptimer_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
double _retval = 0;
anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper _bdtime = null;
String _smin = "";
 //BA.debugLineNum = 644;BA.debugLine="Private Sub StopTimer_OnPositiveClicked (View As V";
 //BA.debugLineNum = 645;BA.debugLine="Dim RetVal As Double";
_retval = 0;
 //BA.debugLineNum = 646;BA.debugLine="Dim bdTime As BigDecimal";
_bdtime = new anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper();
 //BA.debugLineNum = 647;BA.debugLine="Dim sMin As String";
_smin = "";
 //BA.debugLineNum = 649;BA.debugLine="Alert.Initialize.Dismiss2";
mostCurrent._alert.Initialize().Dismiss2();
 //BA.debugLineNum = 652;BA.debugLine="LogColor($\"Return Value is: \"$ & RetVal, Colors.Y";
anywheresoftware.b4a.keywords.Common.LogImpl("722282248",("Return Value is: ")+BA.NumberToString(_retval),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 654;BA.debugLine="bdTime.Initialize(RetVal)";
_bdtime.Initialize(BA.NumberToString(_retval));
 //BA.debugLineNum = 655;BA.debugLine="bdTime = RoundBD(bdTime,2)";
_bdtime = _roundbd(_bdtime,(int) (2));
 //BA.debugLineNum = 658;BA.debugLine="Select Case iTimer";
switch (_itimer) {
case 1: {
 //BA.debugLineNum = 660;BA.debugLine="txtTry1.Text = SW1.Text";
mostCurrent._txttry1.setText(BA.ObjectToCharSequence(mostCurrent._sw1.getText()));
 //BA.debugLineNum = 661;BA.debugLine="dTime1 =  bdTime";
_dtime1 = (double)(BA.ObjectToNumber(_bdtime));
 //BA.debugLineNum = 662;BA.debugLine="LogColor($\"1st Try: \"$ & dTime1, Colors.Magenta";
anywheresoftware.b4a.keywords.Common.LogImpl("722282258",("1st Try: ")+BA.NumberToString(_dtime1),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 663;BA.debugLine="btnShowTimer2.Enabled = True";
mostCurrent._btnshowtimer2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 2: {
 //BA.debugLineNum = 665;BA.debugLine="txtTry2.Text = SW1.Text";
mostCurrent._txttry2.setText(BA.ObjectToCharSequence(mostCurrent._sw1.getText()));
 //BA.debugLineNum = 666;BA.debugLine="dTime2 =  bdTime";
_dtime2 = (double)(BA.ObjectToNumber(_bdtime));
 //BA.debugLineNum = 667;BA.debugLine="LogColor($\"2nd Try: \"$ & dTime2, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("722282263",("2nd Try: ")+BA.NumberToString(_dtime2),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 668;BA.debugLine="btnShowTimer3.Enabled = True";
mostCurrent._btnshowtimer3.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 3: {
 //BA.debugLineNum = 670;BA.debugLine="txtTry3.Text = SW1.Text";
mostCurrent._txttry3.setText(BA.ObjectToCharSequence(mostCurrent._sw1.getText()));
 //BA.debugLineNum = 671;BA.debugLine="dTime3 =  bdTime";
_dtime3 = (double)(BA.ObjectToNumber(_bdtime));
 //BA.debugLineNum = 672;BA.debugLine="LogColor($\"3rd Try: \"$ & dTime3, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("722282268",("3rd Try: ")+BA.NumberToString(_dtime3),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 break; }
}
;
 //BA.debugLineNum = 675;BA.debugLine="pnlMainTimer.Visible = False";
mostCurrent._pnlmaintimer.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 677;BA.debugLine="End Sub";
return "";
}
public static String  _timer1_tick() throws Exception{
long _milliseconds = 0L;
 //BA.debugLineNum = 574;BA.debugLine="Private Sub Timer1_Tick";
 //BA.debugLineNum = 575;BA.debugLine="Dim milliseconds As Long = DateTime.Now - StartTi";
_milliseconds = (long) (anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime);
 //BA.debugLineNum = 577;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 199;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 200;BA.debugLine="Select Case Item.Id";
switch (BA.switchObjectToInt(_item.getId(),(int) (1))) {
case 0: {
 //BA.debugLineNum = 202;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 203;BA.debugLine="StartActivity(actGPMHistory)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actgpmhistory.getObject()));
 break; }
}
;
 //BA.debugLineNum = 205;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 194;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arroww";
 //BA.debugLineNum = 195;BA.debugLine="kBoard.HideKeyboard";
mostCurrent._kboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 196;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 197;BA.debugLine="End Sub";
return "";
}
public static String  _txtbucketsize_enterpressed() throws Exception{
 //BA.debugLineNum = 829;BA.debugLine="Sub txtBucketSize_EnterPressed";
 //BA.debugLineNum = 830;BA.debugLine="cboUOM.RequestFocus";
mostCurrent._cbouom.RequestFocus();
 //BA.debugLineNum = 831;BA.debugLine="End Sub";
return "";
}
public static String  _txtbucketsize_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 821;BA.debugLine="Sub txtBucketSize_TextChanged (Old As String, New";
 //BA.debugLineNum = 822;BA.debugLine="If GlobalVar.SF.Len(txtBucketSize.Text) <= 0 Or G";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtbucketsize.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbouom.getSelectedItem())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry1.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry2.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry3.getText())<=0) { 
 //BA.debugLineNum = 823;BA.debugLine="txtGPM.Text = 0";
mostCurrent._txtgpm.setText(BA.ObjectToCharSequence(0));
 //BA.debugLineNum = 824;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 826;BA.debugLine="txtGPM.Text = ComputeGPM";
mostCurrent._txtgpm.setText(BA.ObjectToCharSequence(_computegpm()));
 //BA.debugLineNum = 827;BA.debugLine="End Sub";
return "";
}
public static String  _txttry1_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 782;BA.debugLine="Sub txtTry1_TextChanged (Old As String, New As Str";
 //BA.debugLineNum = 783;BA.debugLine="If GlobalVar.SF.Len(txtBucketSize.Text) <= 0 Or G";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtbucketsize.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbouom.getSelectedItem())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry1.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry2.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry3.getText())<=0) { 
 //BA.debugLineNum = 784;BA.debugLine="txtGPM.Text = 0";
mostCurrent._txtgpm.setText(BA.ObjectToCharSequence(0));
 //BA.debugLineNum = 785;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 787;BA.debugLine="txtGPM.Text = ComputeGPM";
mostCurrent._txtgpm.setText(BA.ObjectToCharSequence(_computegpm()));
 //BA.debugLineNum = 788;BA.debugLine="End Sub";
return "";
}
public static String  _txttry2_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 790;BA.debugLine="Sub txtTry2_TextChanged (Old As String, New As Str";
 //BA.debugLineNum = 791;BA.debugLine="If GlobalVar.SF.Len(txtBucketSize.Text) <= 0 Or G";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtbucketsize.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbouom.getSelectedItem())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry1.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry2.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry3.getText())<=0) { 
 //BA.debugLineNum = 792;BA.debugLine="txtGPM.Text = 0";
mostCurrent._txtgpm.setText(BA.ObjectToCharSequence(0));
 //BA.debugLineNum = 793;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 795;BA.debugLine="txtGPM.Text = ComputeGPM";
mostCurrent._txtgpm.setText(BA.ObjectToCharSequence(_computegpm()));
 //BA.debugLineNum = 796;BA.debugLine="End Sub";
return "";
}
public static String  _txttry3_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 798;BA.debugLine="Sub txtTry3_TextChanged (Old As String, New As Str";
 //BA.debugLineNum = 799;BA.debugLine="If GlobalVar.SF.Len(txtBucketSize.Text) <= 0 Or G";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtbucketsize.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbouom.getSelectedItem())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry1.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry2.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txttry3.getText())<=0) { 
 //BA.debugLineNum = 800;BA.debugLine="txtGPM.Text = 0";
mostCurrent._txtgpm.setText(BA.ObjectToCharSequence(0));
 //BA.debugLineNum = 801;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 803;BA.debugLine="txtGPM.Text = ComputeGPM";
mostCurrent._txtgpm.setText(BA.ObjectToCharSequence(_computegpm()));
 //BA.debugLineNum = 804;BA.debugLine="End Sub";
return "";
}
public static boolean  _updategpm(int _igpmid,String _strandate,int _ipumpid) throws Exception{
boolean _bretval = false;
String _sdatetime = "";
long _ldate = 0L;
 //BA.debugLineNum = 257;BA.debugLine="Private Sub UpdateGPM(iGPMID As Int, sTranDate As";
 //BA.debugLineNum = 258;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 259;BA.debugLine="Dim sDateTime As String";
_sdatetime = "";
 //BA.debugLineNum = 260;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 262;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 263;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 264;BA.debugLine="sDateTime = DateTime.Date(lDate)";
_sdatetime = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 266;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 267;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 268;BA.debugLine="Try";
try { //BA.debugLineNum = 269;BA.debugLine="Starter.strCriteria = \"UPDATE tblGPMHistory SET";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE tblGPMHistory SET "+"BucketSize = ?, "+"UnitOfMeasurement = ?, "+"Trial1 = ?, "+"Trial2 = ?, "+"Trial3 = ?, "+"GPMResult = ?, "+"WaterQuality = ?, "+"Remarks = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ? "+"WHERE GPMID = "+BA.NumberToString(_igpmid)+" "+"AND TranDate = '"+_strandate+"' "+"AND PumpID = "+BA.NumberToString(_ipumpid);
 //BA.debugLineNum = 284;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{mostCurrent._txtbucketsize.getText(),mostCurrent._cbouom.getSelectedItem(),mostCurrent._txttry1.getText(),mostCurrent._txttry2.getText(),mostCurrent._txttry3.getText(),mostCurrent._txtgpm.getText(),mostCurrent._txtwaterquality.getText(),mostCurrent._txtremarks.getText(),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_sdatetime}));
 //BA.debugLineNum = 286;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 287;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e15) {
			processBA.setLastException(e15); //BA.debugLineNum = 289;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("721168160",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 290;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 292;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 293;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 294;BA.debugLine="End Sub";
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
