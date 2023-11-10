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

public class addedittimerecord extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static addedittimerecord mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.addedittimerecord");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (addedittimerecord).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.addedittimerecord");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.addedittimerecord", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (addedittimerecord) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (addedittimerecord) Resume **");
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
		return addedittimerecord.class;
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
            BA.LogInfo("** Activity (addedittimerecord) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (addedittimerecord) Pause event (activity is not paused). **");
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
            addedittimerecord mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (addedittimerecord) Resume **");
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
public bwsi.PumpOperations.wobblemenu _tabmenu = null;
public flm.b4a.maskededittext.MaskedEditTextWrapper _msktimeon = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _optelectricity = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _optgenerator = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _chkdrain = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtduration = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtpsi = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtdraincum = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtonremarks = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _chkdefaulttimeon = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnsave = null;
public static boolean _saveheadersuccess = false;
public static boolean _savedetailsuccess = false;
public static String _stimeon = "";
public static String _stimeoff = "";
public static int _ihron = 0;
public static int _ihroff = 0;
public static double _totdrainhrs = 0;
public static double _totdraincum = 0;
public static double _totpsi = 0;
public static int _powersourceid = 0;
public static float _totophrs = 0f;
public static int _pumppowerstatus = 0;
public anywheresoftware.b4a.objects.SpinnerWrapper _spinner1 = null;
public com.example.B4ADropDownView.B4ADropDownView _b4adropdownview1 = null;
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
 //BA.debugLineNum = 75;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 76;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 77;BA.debugLine="Activity.LoadLayout(\"NewPumpTimeRecords2\")";
mostCurrent._activity.LoadLayout("NewPumpTimeRecords2",mostCurrent.activityBA);
 //BA.debugLineNum = 79;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 80;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 82;BA.debugLine="If GlobalVar.blnNewTime = True Then";
if (mostCurrent._globalvar._blnnewtime /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 83;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Appen";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("ADD NEW PUMP TIME RECORD"))).PopAll();
 //BA.debugLineNum = 84;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append(";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 //BA.debugLineNum = 85;BA.debugLine="ClearDisplay";
_cleardisplay();
 //BA.debugLineNum = 86;BA.debugLine="btnSave.Text = Chr(0xE161) & $\" SAVE\"$";
mostCurrent._btnsave.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" SAVE")));
 }else {
 //BA.debugLineNum = 88;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Appen";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("EDIT PUMP TIME RECORD"))).PopAll();
 //BA.debugLineNum = 89;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append(";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 //BA.debugLineNum = 90;BA.debugLine="btnSave.Text = Chr(0xE161) & $\" UPDATE\"$";
mostCurrent._btnsave.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" UPDATE")));
 //BA.debugLineNum = 91;BA.debugLine="ClearDisplay";
_cleardisplay();
 //BA.debugLineNum = 92;BA.debugLine="GetTimeRecord(GlobalVar.TimeDetailID)";
_gettimerecord(mostCurrent._globalvar._timedetailid /*int*/ );
 };
 //BA.debugLineNum = 95;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 96;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 97;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 98;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 100;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 101;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 103;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 104;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 105;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 107;BA.debugLine="InpTyp.Initialize";
_inptyp._initialize /*String*/ (processBA);
 //BA.debugLineNum = 108;BA.debugLine="InpTyp.SetInputType(txtOnRemarks,Array As Int(Inp";
_inptyp._setinputtype /*String*/ (mostCurrent._txtonremarks,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_correct /*int*/ (),_inptyp._type_text_flag_cap_sentences /*int*/ ()});
 //BA.debugLineNum = 110;BA.debugLine="CDtxtBox.Initialize(Colors.Transparent,0)";
mostCurrent._cdtxtbox.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 111;BA.debugLine="cdFixedText.Initialize(GlobalVar.BlueColor,0)";
mostCurrent._cdfixedtext.Initialize((int) (mostCurrent._globalvar._bluecolor /*double*/ ),(int) (0));
 //BA.debugLineNum = 112;BA.debugLine="mskTimeOn.Background = CDtxtBox";
mostCurrent._msktimeon.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 113;BA.debugLine="txtDuration.Background = CDtxtBox";
mostCurrent._txtduration.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 114;BA.debugLine="txtDrainCum.Background = cdFixedText";
mostCurrent._txtdraincum.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdfixedtext.getObject()));
 //BA.debugLineNum = 115;BA.debugLine="txtOnRemarks.Background = CDtxtBox";
mostCurrent._txtonremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 116;BA.debugLine="txtPSI.Background = CDtxtBox";
mostCurrent._txtpsi.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 117;BA.debugLine="imeKeyboard.Initialize(\"\")";
mostCurrent._imekeyboard.Initialize("");
 //BA.debugLineNum = 119;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 120;BA.debugLine="PumpPowerStatus = DBaseFunctions.GetPumpPowerSta";
_pumppowerstatus = mostCurrent._dbasefunctions._getpumppowerstatus /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ );
 };
 //BA.debugLineNum = 123;BA.debugLine="MyFunctions.SetButton(btnSave, 25, 25, 25, 25, 25";
mostCurrent._myfunctions._setbutton /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btnsave.getObject())),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25));
 //BA.debugLineNum = 124;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 125;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
 //BA.debugLineNum = 185;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 187;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 165;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 166;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 167;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 168;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 170;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 179;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 180;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 139;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 140;BA.debugLine="If Result Then";
if (_result) { 
 //BA.debugLineNum = 141;BA.debugLine="If Permission = Starter.RTP.PERMISSION_READ_EXTE";
if ((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 142;BA.debugLine="LogColor($\"Permission to Read External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("875694083",("Permission to Read External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 143;BA.debugLine="GlobalVar.ReadStoragePermission = True";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 145;BA.debugLine="LogColor($\"Permission to Write External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("875694086",("Permission to Write External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 146;BA.debugLine="GlobalVar.WriteStoragePermission = True";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION)) { 
 //BA.debugLineNum = 148;BA.debugLine="LogColor($\"Permission to Access Coarse Location";
anywheresoftware.b4a.keywords.Common.LogImpl("875694089",("Permission to Access Coarse Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 149;BA.debugLine="GlobalVar.CoarseLocPermission = True";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION)) { 
 //BA.debugLineNum = 151;BA.debugLine="LogColor($\"Permission to Access Fine Location G";
anywheresoftware.b4a.keywords.Common.LogImpl("875694092",("Permission to Access Fine Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 152;BA.debugLine="GlobalVar.FineLocPermission = True";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 154;BA.debugLine="Starter.StartFLP";
mostCurrent._starter._startflp /*void*/ ();
 }else {
 //BA.debugLineNum = 156;BA.debugLine="GlobalVar.ReadStoragePermission = False";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 157;BA.debugLine="GlobalVar.WriteStoragePermission = False";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 158;BA.debugLine="GlobalVar.CoarseLocPermission = False";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 159;BA.debugLine="GlobalVar.FineLocPermission = False";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 160;BA.debugLine="Result = False";
_result = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 162;BA.debugLine="Log (Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("875694103",_permission,0);
 //BA.debugLineNum = 163;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 174;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 175;BA.debugLine="PumpPowerStatus = DBaseFunctions.GetPumpPowerStat";
_pumppowerstatus = mostCurrent._dbasefunctions._getpumppowerstatus /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 176;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 177;BA.debugLine="End Sub";
return "";
}
public static String  _addpumptimeonrecords_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 924;BA.debugLine="Private Sub AddPumpTimeOnRecords_ButtonPressed(mDi";
 //BA.debugLineNum = 925;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE)) {
case 0: {
 //BA.debugLineNum = 927;BA.debugLine="imeKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 928;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
}
;
 //BA.debugLineNum = 930;BA.debugLine="End Sub";
return "";
}
public static String  _btnsave_click() throws Exception{
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
String _smin = "";
int _ihrs = 0;
int _imins = 0;
 //BA.debugLineNum = 213;BA.debugLine="Sub btnSave_Click";
 //BA.debugLineNum = 214;BA.debugLine="If Not(IsValidEntries) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_isvalidentries())) { 
if (true) return "";};
 //BA.debugLineNum = 216;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 217;BA.debugLine="Dim sMin As String";
_smin = "";
 //BA.debugLineNum = 219;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d)\", mskTime";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d)",mostCurrent._msktimeon.getText());
 //BA.debugLineNum = 221;BA.debugLine="If Matcher1.Find Then";
if (_matcher1.Find()) { 
 //BA.debugLineNum = 222;BA.debugLine="Dim iHrs, iMins As Int";
_ihrs = 0;
_imins = 0;
 //BA.debugLineNum = 224;BA.debugLine="iHrs = Matcher1.Group(1)";
_ihrs = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 225;BA.debugLine="iMins = Matcher1.Group(2)";
_imins = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 227;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_imins)))==1) { 
 //BA.debugLineNum = 228;BA.debugLine="sMin = $\"0\"$ & iMins";
_smin = ("0")+BA.NumberToString(_imins);
 }else {
 //BA.debugLineNum = 230;BA.debugLine="sMin = iMins";
_smin = BA.NumberToString(_imins);
 };
 //BA.debugLineNum = 233;BA.debugLine="If iHrs = 0 Then";
if (_ihrs==0) { 
 //BA.debugLineNum = 234;BA.debugLine="iHrOn = 12";
_ihron = (int) (12);
 //BA.debugLineNum = 235;BA.debugLine="sTimeOn = iHrOn & \":\" & sMin & \" AM\"";
mostCurrent._stimeon = BA.NumberToString(_ihron)+":"+_smin+" AM";
 }else if(_ihrs>0 && _ihrs<12) { 
 //BA.debugLineNum = 237;BA.debugLine="iHrOn = iHrs";
_ihron = _ihrs;
 //BA.debugLineNum = 238;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrOn)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_ihron)))==1) { 
 //BA.debugLineNum = 239;BA.debugLine="sTimeOn = $\"0\"$ & iHrOn & \":\" & sMin & \" AM\"";
mostCurrent._stimeon = ("0")+BA.NumberToString(_ihron)+":"+_smin+" AM";
 }else {
 //BA.debugLineNum = 241;BA.debugLine="sTimeOn = iHrOn & \":\" & sMin & \" AM\"";
mostCurrent._stimeon = BA.NumberToString(_ihron)+":"+_smin+" AM";
 };
 }else if(_ihrs==12) { 
 //BA.debugLineNum = 244;BA.debugLine="iHrOn = 12";
_ihron = (int) (12);
 //BA.debugLineNum = 245;BA.debugLine="sTimeOn = iHrOn & \":\" & sMin & \" PM\"";
mostCurrent._stimeon = BA.NumberToString(_ihron)+":"+_smin+" PM";
 }else {
 //BA.debugLineNum = 247;BA.debugLine="iHrOn = iHrs - 12";
_ihron = (int) (_ihrs-12);
 //BA.debugLineNum = 248;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrOn)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_ihron)))==1) { 
 //BA.debugLineNum = 249;BA.debugLine="sTimeOn = $\"0\"$ & iHrOn & \":\" & sMin & \" PM\"";
mostCurrent._stimeon = ("0")+BA.NumberToString(_ihron)+":"+_smin+" PM";
 }else {
 //BA.debugLineNum = 251;BA.debugLine="sTimeOn = iHrOn & \":\" & sMin & \" PM\"";
mostCurrent._stimeon = BA.NumberToString(_ihron)+":"+_smin+" PM";
 };
 };
 };
 //BA.debugLineNum = 256;BA.debugLine="LogColor(sTimeOn,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("876218411",mostCurrent._stimeon,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 258;BA.debugLine="If optElectricity.Checked = True Then";
if (mostCurrent._optelectricity.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 259;BA.debugLine="PowerSourceID = 1";
_powersourceid = (int) (1);
 }else {
 //BA.debugLineNum = 261;BA.debugLine="PowerSourceID = 0";
_powersourceid = (int) (0);
 };
 //BA.debugLineNum = 264;BA.debugLine="If chkDrain.Checked = True Then";
if (mostCurrent._chkdrain.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 265;BA.debugLine="TotDrainHrs = txtDuration.Text";
_totdrainhrs = (double)(Double.parseDouble(mostCurrent._txtduration.getText()));
 //BA.debugLineNum = 266;BA.debugLine="TotDrainCum = txtDrainCum.Text";
_totdraincum = (double)(Double.parseDouble(mostCurrent._txtdraincum.getText()));
 }else {
 //BA.debugLineNum = 268;BA.debugLine="TotDrainHrs = 0";
_totdrainhrs = 0;
 //BA.debugLineNum = 269;BA.debugLine="TotDrainCum = 0";
_totdraincum = 0;
 };
 //BA.debugLineNum = 272;BA.debugLine="LogColor($\"Total Drain Duration: \"$ & TotDrainHrs";
anywheresoftware.b4a.keywords.Common.LogImpl("876218427",("Total Drain Duration: ")+BA.NumberToString(_totdrainhrs),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 273;BA.debugLine="LogColor($\"Total Drain CuM: \"$ & TotDrainCum, Col";
anywheresoftware.b4a.keywords.Common.LogImpl("876218428",("Total Drain CuM: ")+BA.NumberToString(_totdraincum),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 275;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 276;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeaderID";
anywheresoftware.b4a.keywords.Common.LogImpl("876218431",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 278;BA.debugLine="If GlobalVar.blnNewTime = True Then";
if (mostCurrent._globalvar._blnnewtime /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 280;BA.debugLine="If DBaseFunctions.IsFuturisticTime(GlobalVar.Tra";
if (mostCurrent._dbasefunctions._isfuturistictime /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._msktimeon.getText())==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 281;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, $\"Unable to Add N";
_requiredmsgbox(("E R R O R"),("Unable to Add New Pump Time record due to specified time is too soon."));
 //BA.debugLineNum = 282;BA.debugLine="mskTimeOn.RequestFocus";
mostCurrent._msktimeon.RequestFocus();
 //BA.debugLineNum = 283;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 286;BA.debugLine="If DBaseFunctions.IsTimeOnOverlapping(sTimeOn, G";
if (mostCurrent._dbasefunctions._istimeonoverlapping /*boolean*/ (mostCurrent.activityBA,mostCurrent._stimeon,mostCurrent._globalvar._tranheaderid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 287;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, $\"Unable to Add N";
_requiredmsgbox(("E R R O R"),("Unable to Add New Pump Time record due to it will Overlap existing records."));
 //BA.debugLineNum = 288;BA.debugLine="mskTimeOn.RequestFocus";
mostCurrent._msktimeon.RequestFocus();
 //BA.debugLineNum = 289;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 293;BA.debugLine="ConfirmSaveRecords(GlobalVar.blnNewTime)";
_confirmsaverecords(mostCurrent._globalvar._blnnewtime /*boolean*/ );
 //BA.debugLineNum = 295;BA.debugLine="End Sub";
return "";
}
public static String  _checkpermissions() throws Exception{
 //BA.debugLineNum = 127;BA.debugLine="Private Sub CheckPermissions";
 //BA.debugLineNum = 128;BA.debugLine="Log(\"Checking Permissions\")";
anywheresoftware.b4a.keywords.Common.LogImpl("875628545","Checking Permissions",0);
 //BA.debugLineNum = 130;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE);
 //BA.debugLineNum = 131;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE);
 //BA.debugLineNum = 132;BA.debugLine="Starter.RTP.GetAllSafeDirsExternal(\"\")";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .GetAllSafeDirsExternal("");
 //BA.debugLineNum = 134;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION);
 //BA.debugLineNum = 135;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 136;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 137;BA.debugLine="End Sub";
return "";
}
public static String  _chkdefaulttimeon_checkedchange(boolean _checked) throws Exception{
String _shour = "";
String _smin = "";
long _lhour = 0L;
long _lmin = 0L;
 //BA.debugLineNum = 698;BA.debugLine="Sub chkDefaultTimeOn_CheckedChange(Checked As Bool";
 //BA.debugLineNum = 699;BA.debugLine="Dim sHour As String";
_shour = "";
 //BA.debugLineNum = 700;BA.debugLine="Dim sMin As String";
_smin = "";
 //BA.debugLineNum = 701;BA.debugLine="Dim lHour, lMin As Long";
_lhour = 0L;
_lmin = 0L;
 //BA.debugLineNum = 703;BA.debugLine="If Checked = True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 704;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 705;BA.debugLine="lHour = DateTime.GetHour(DateTime.Now)";
_lhour = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 706;BA.debugLine="lMin = DateTime.GetMinute(DateTime.Now)";
_lmin = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 708;BA.debugLine="If GlobalVar.SF.Len(lHour) = 1 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_lhour))==1) { 
 //BA.debugLineNum = 709;BA.debugLine="sHour = $\"0\"$ & lHour";
_shour = ("0")+BA.NumberToString(_lhour);
 }else {
 //BA.debugLineNum = 711;BA.debugLine="sHour = lHour";
_shour = BA.NumberToString(_lhour);
 };
 //BA.debugLineNum = 714;BA.debugLine="If GlobalVar.SF.Len(lMin) = 1 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_lmin))==1) { 
 //BA.debugLineNum = 715;BA.debugLine="sMin = $\"0\"$ & lMin";
_smin = ("0")+BA.NumberToString(_lmin);
 }else {
 //BA.debugLineNum = 717;BA.debugLine="sMin = lMin";
_smin = BA.NumberToString(_lmin);
 };
 //BA.debugLineNum = 720;BA.debugLine="mskTimeOn.Text = sHour & \":\" & sMin";
mostCurrent._msktimeon.setText((Object)(_shour+":"+_smin));
 }else {
 //BA.debugLineNum = 722;BA.debugLine="mskTimeOn.Text = \"__:__\"";
mostCurrent._msktimeon.setText((Object)("__:__"));
 };
 //BA.debugLineNum = 724;BA.debugLine="End Sub";
return "";
}
public static String  _chkdrain_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 199;BA.debugLine="Sub chkDrain_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 200;BA.debugLine="If Checked =True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 201;BA.debugLine="txtDuration.Enabled = True";
mostCurrent._txtduration.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 202;BA.debugLine="txtDuration.RequestFocus";
mostCurrent._txtduration.RequestFocus();
 //BA.debugLineNum = 203;BA.debugLine="imeKeyboard.ShowKeyboard(txtDuration)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._txtduration.getObject()));
 //BA.debugLineNum = 204;BA.debugLine="txtPSI.Enabled = True";
mostCurrent._txtpsi.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 206;BA.debugLine="txtDuration.Enabled = False";
mostCurrent._txtduration.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 207;BA.debugLine="txtPSI.Enabled = False";
mostCurrent._txtpsi.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 208;BA.debugLine="txtDuration.Text = \"\"";
mostCurrent._txtduration.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 209;BA.debugLine="txtDrainCum.Text = \"\"";
mostCurrent._txtdraincum.setText(BA.ObjectToCharSequence(""));
 };
 //BA.debugLineNum = 211;BA.debugLine="End Sub";
return "";
}
public static String  _cleardisplay() throws Exception{
 //BA.debugLineNum = 432;BA.debugLine="Private Sub ClearDisplay";
 //BA.debugLineNum = 433;BA.debugLine="Try";
try { //BA.debugLineNum = 434;BA.debugLine="mskTimeOn.Text = \"__:__\"";
mostCurrent._msktimeon.setText((Object)("__:__"));
 //BA.debugLineNum = 435;BA.debugLine="optElectricity.Checked = True";
mostCurrent._optelectricity.setChecked(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 436;BA.debugLine="txtDuration.Text = \"\"";
mostCurrent._txtduration.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 437;BA.debugLine="txtDrainCum.Text = \"\"";
mostCurrent._txtdraincum.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 438;BA.debugLine="txtOnRemarks.Text = \"\"";
mostCurrent._txtonremarks.setText(BA.ObjectToCharSequence(""));
 } 
       catch (Exception e8) {
			processBA.setLastException(e8); //BA.debugLineNum = 440;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("876349448",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 442;BA.debugLine="End Sub";
return "";
}
public static String  _confirmsaverecords(boolean _baddedit) throws Exception{
String _stitle = "";
String _scontent = "";
 //BA.debugLineNum = 822;BA.debugLine="Private Sub ConfirmSaveRecords(bAddEdit As Boolean";
 //BA.debugLineNum = 824;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 825;BA.debugLine="Dim sTitle, sContent As String";
_stitle = "";
_scontent = "";
 //BA.debugLineNum = 827;BA.debugLine="Select bAddEdit";
switch (BA.switchObjectToInt(_baddedit,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False)) {
case 0: {
 //BA.debugLineNum = 829;BA.debugLine="sTitle = $\"CONFIRM SAVE?\"$";
_stitle = ("CONFIRM SAVE?");
 //BA.debugLineNum = 830;BA.debugLine="sContent = $\"Save the Pump Time On?\"$";
_scontent = ("Save the Pump Time On?");
 break; }
case 1: {
 //BA.debugLineNum = 832;BA.debugLine="sTitle = $\"CONFIRM UPDATE?\"$";
_stitle = ("CONFIRM UPDATE?");
 //BA.debugLineNum = 833;BA.debugLine="sContent = $\"Modified the Pump Time On?\"$";
_scontent = ("Modified the Pump Time On?");
 break; }
}
;
 //BA.debugLineNum = 836;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitleTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetMessageTypeface((android.graphics.Typeface)(_font.getObject())).SetMessage(_scontent).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"PumpOn").SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetOnNegativeClicked(mostCurrent.activityBA,"PumpOn");
 //BA.debugLineNum = 853;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 854;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 855;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 643;BA.debugLine="Private Sub EditTranHeader(iTranHeaderID As Int) A";
 //BA.debugLineNum = 644;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 645;BA.debugLine="Dim GTotOPHrs As Double";
_gtotophrs = 0;
 //BA.debugLineNum = 646;BA.debugLine="Dim GTotDrain, GTotDuration As Double";
_gtotdrain = 0;
_gtotduration = 0;
 //BA.debugLineNum = 648;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 649;BA.debugLine="Dim sModifiedAt As String";
_smodifiedat = "";
 //BA.debugLineNum = 651;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 652;BA.debugLine="DateTime.TimeFormat = \"hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm:ss a");
 //BA.debugLineNum = 653;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 654;BA.debugLine="sModifiedAt = DateTime.Date(lngDateTime) & $\" \"$";
_smodifiedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime)+(" ")+anywheresoftware.b4a.keywords.Common.DateTime.Time(_lngdatetime);
 //BA.debugLineNum = 656;BA.debugLine="Dim rsDetail As Cursor";
_rsdetail = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 658;BA.debugLine="Starter.strCriteria = \"SELECT sum(TotOpHrs) as GT";
mostCurrent._starter._strcriteria /*String*/  = "SELECT sum(TotOpHrs) as GTotOpHrs, sum(DrainTime) as GTotDrainTime, sum(DrainCum) as GTotDrain "+"FROM OnOffDetails WHERE HeaderID = "+BA.NumberToString(_itranheaderid)+" "+"GROUP BY HeaderID";
 //BA.debugLineNum = 662;BA.debugLine="rsDetail = Starter.DBCon.ExecQuery(Starter.strCri";
_rsdetail = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 664;BA.debugLine="If rsDetail.RowCount > 0 Then";
if (_rsdetail.getRowCount()>0) { 
 //BA.debugLineNum = 665;BA.debugLine="rsDetail.Position = 0";
_rsdetail.setPosition((int) (0));
 //BA.debugLineNum = 666;BA.debugLine="GTotOPHrs = rsDetail.GetDouble(\"GTotOpHrs\")";
_gtotophrs = _rsdetail.GetDouble("GTotOpHrs");
 //BA.debugLineNum = 667;BA.debugLine="GTotDuration = rsDetail.GetInt(\"GTotDrainTime\")";
_gtotduration = _rsdetail.GetInt("GTotDrainTime");
 //BA.debugLineNum = 668;BA.debugLine="GTotDrain = rsDetail.GetInt(\"GTotDrain\")";
_gtotdrain = _rsdetail.GetInt("GTotDrain");
 }else {
 //BA.debugLineNum = 670;BA.debugLine="GTotOPHrs = TotOpHrs";
_gtotophrs = _totophrs;
 //BA.debugLineNum = 671;BA.debugLine="GTotDuration = TotDrainHrs";
_gtotduration = _totdrainhrs;
 //BA.debugLineNum = 672;BA.debugLine="GTotDrain = TotDrainCum";
_gtotdrain = _totdraincum;
 };
 //BA.debugLineNum = 674;BA.debugLine="rsDetail.Close";
_rsdetail.Close();
 //BA.debugLineNum = 676;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 677;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 678;BA.debugLine="Try";
try { //BA.debugLineNum = 679;BA.debugLine="Starter.strCriteria = \"UPDATE TranHeader SET \" &";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE TranHeader SET "+"TotOpHrs = ?, "+"TotDrainHrs = ?, "+"TotDrain = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 687;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_gtotophrs),BA.NumberToString(_gtotduration),BA.NumberToString(_gtotdrain),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_smodifiedat}));
 //BA.debugLineNum = 688;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 689;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e32) {
			processBA.setLastException(e32); //BA.debugLineNum = 691;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("876742704",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 692;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 694;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 695;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 696;BA.debugLine="End Sub";
return false;
}
public static void  _gettimerecord(int _idetailedid) throws Exception{
ResumableSub_GetTimeRecord rsub = new ResumableSub_GetTimeRecord(null,_idetailedid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetTimeRecord extends BA.ResumableSub {
public ResumableSub_GetTimeRecord(bwsi.PumpOperations.addedittimerecord parent,int _idetailedid) {
this.parent = parent;
this._idetailedid = _idetailedid;
}
bwsi.PumpOperations.addedittimerecord parent;
int _idetailedid;
int _ipowersource = 0;
Object _senderfilter = null;
long _ltimeon = 0L;
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
int _ihrs = 0;
int _imins = 0;
String _ampm = "";
String _smin = "";
String _smin1 = "";

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
 //BA.debugLineNum = 298;BA.debugLine="Dim iPowerSource As Int";
_ipowersource = 0;
 //BA.debugLineNum = 299;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 300;BA.debugLine="Dim sTimeOn, sTimeOff As String";
parent.mostCurrent._stimeon = "";
parent.mostCurrent._stimeoff = "";
 //BA.debugLineNum = 301;BA.debugLine="Dim lTimeOn As Long";
_ltimeon = 0L;
 //BA.debugLineNum = 302;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 304;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 72;
this.catchState = 71;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 71;
 //BA.debugLineNum = 306;BA.debugLine="Starter.strCriteria = \"SELECT Header.TranDate, \"";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Header.TranDate, "+"Pump.PumpHouseCode, Details.PumpOnTime, Details.PumpOffTime, Details.TotOpHrs, "+"Details.PowerSourceID, Details.DrainTime, Details.DrainCum, Details.TimeOnRemarks "+"FROM OnOffDetails AS Details "+"INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID "+"INNER JOIN tblPumpStation AS Pump ON Pump.StationID = Header.PumpID "+"WHERE Details.DetailID = "+BA.NumberToString(_idetailedid);
 //BA.debugLineNum = 314;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 315;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 73;
return;
case 73:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 317;BA.debugLine="If Success Then";
if (true) break;

case 4:
//if
this.state = 69;
if (_success) { 
this.state = 6;
}else {
this.state = 68;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 318;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 319;BA.debugLine="sTimeOn = RS.GetString(\"PumpOnTime\")";
parent.mostCurrent._stimeon = _rs.GetString("PumpOnTime");
 //BA.debugLineNum = 320;BA.debugLine="sTimeOff = RS.GetString(\"PumpOffTime\")";
parent.mostCurrent._stimeoff = _rs.GetString("PumpOffTime");
 //BA.debugLineNum = 322;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 323;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d) (\\S\\S)\"";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d) (\\S\\S)",parent.mostCurrent._stimeon);
 //BA.debugLineNum = 324;BA.debugLine="If Matcher1.Find Then";
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
 //BA.debugLineNum = 325;BA.debugLine="Dim iHrs, iMins As Int";
_ihrs = 0;
_imins = 0;
 //BA.debugLineNum = 326;BA.debugLine="Dim AmPm As String";
_ampm = "";
 //BA.debugLineNum = 327;BA.debugLine="Dim sMin As String";
_smin = "";
 //BA.debugLineNum = 329;BA.debugLine="iHrs = Matcher1.Group(1)";
_ihrs = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 330;BA.debugLine="iMins = Matcher1.Group(2)";
_imins = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 331;BA.debugLine="AmPm = Matcher1.Group(3)";
_ampm = _matcher1.Group((int) (3));
 //BA.debugLineNum = 333;BA.debugLine="LogColor(AmPm,Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("876283940",_ampm,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 335;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins))";
if (true) break;

case 10:
//if
this.state = 15;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_imins)))==1) { 
this.state = 12;
}else {
this.state = 14;
}if (true) break;

case 12:
//C
this.state = 15;
 //BA.debugLineNum = 336;BA.debugLine="sMin = $\"0\"$ & iMins";
_smin = ("0")+BA.NumberToString(_imins);
 if (true) break;

case 14:
//C
this.state = 15;
 //BA.debugLineNum = 338;BA.debugLine="sMin = iMins";
_smin = BA.NumberToString(_imins);
 if (true) break;
;
 //BA.debugLineNum = 341;BA.debugLine="If AmPm = \"AM\" Then";

case 15:
//if
this.state = 38;
if ((_ampm).equals("AM")) { 
this.state = 17;
}else {
this.state = 31;
}if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 342;BA.debugLine="If iHrs = 12 Then";
if (true) break;

case 18:
//if
this.state = 29;
if (_ihrs==12) { 
this.state = 20;
}else {
this.state = 22;
}if (true) break;

case 20:
//C
this.state = 29;
 //BA.debugLineNum = 343;BA.debugLine="iHrOn = 0";
parent._ihron = (int) (0);
 //BA.debugLineNum = 344;BA.debugLine="mskTimeOn.Text = $\"0\"$ & iHrOn & $\":\"$ & sMi";
parent.mostCurrent._msktimeon.setText((Object)(("0")+BA.NumberToString(parent._ihron)+(":")+_smin));
 if (true) break;

case 22:
//C
this.state = 23;
 //BA.debugLineNum = 346;BA.debugLine="iHrOn = iHrs";
parent._ihron = _ihrs;
 //BA.debugLineNum = 347;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrOn)";
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
 //BA.debugLineNum = 348;BA.debugLine="mskTimeOn.Text = $\"0\"$ & iHrOn & $\":\"$ & sM";
parent.mostCurrent._msktimeon.setText((Object)(("0")+BA.NumberToString(parent._ihron)+(":")+_smin));
 if (true) break;

case 27:
//C
this.state = 28;
 //BA.debugLineNum = 350;BA.debugLine="mskTimeOn.Text = iHrOn & $\":\"$ & sMin";
parent.mostCurrent._msktimeon.setText((Object)(BA.NumberToString(parent._ihron)+(":")+_smin));
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
 //BA.debugLineNum = 354;BA.debugLine="If iHrs < 12 Then";
if (true) break;

case 32:
//if
this.state = 37;
if (_ihrs<12) { 
this.state = 34;
}else {
this.state = 36;
}if (true) break;

case 34:
//C
this.state = 37;
 //BA.debugLineNum = 355;BA.debugLine="iHrOn = iHrs + 12";
parent._ihron = (int) (_ihrs+12);
 //BA.debugLineNum = 356;BA.debugLine="mskTimeOn.Text = iHrOn & $\":\"$ & sMin";
parent.mostCurrent._msktimeon.setText((Object)(BA.NumberToString(parent._ihron)+(":")+_smin));
 if (true) break;

case 36:
//C
this.state = 37;
 //BA.debugLineNum = 358;BA.debugLine="iHrOn = iHrs";
parent._ihron = _ihrs;
 //BA.debugLineNum = 359;BA.debugLine="mskTimeOn.Text = iHrOn & $\":\"$ & sMin";
parent.mostCurrent._msktimeon.setText((Object)(BA.NumberToString(parent._ihron)+(":")+_smin));
 if (true) break;

case 37:
//C
this.state = 38;
;
 //BA.debugLineNum = 361;BA.debugLine="iHrOn = iHrs + 12";
parent._ihron = (int) (_ihrs+12);
 //BA.debugLineNum = 362;BA.debugLine="mskTimeOn.Text = iHrOn & $\":\"$ & sMin";
parent.mostCurrent._msktimeon.setText((Object)(BA.NumberToString(parent._ihron)+(":")+_smin));
 if (true) break;

case 38:
//C
this.state = 39;
;
 if (true) break;
;
 //BA.debugLineNum = 366;BA.debugLine="If sTimeOff = \"\" Or GlobalVar.SF.Len(sTimeOff)";

case 39:
//if
this.state = 54;
if ((parent.mostCurrent._stimeoff).equals("") || parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(parent.mostCurrent._stimeoff)<=0) { 
this.state = 41;
}else {
this.state = 43;
}if (true) break;

case 41:
//C
this.state = 54;
 if (true) break;

case 43:
//C
this.state = 44;
 //BA.debugLineNum = 369;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 370;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d) (\\S\\S)";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d) (\\S\\S)",parent.mostCurrent._stimeoff);
 //BA.debugLineNum = 371;BA.debugLine="If Matcher1.Find Then";
if (true) break;

case 44:
//if
this.state = 53;
if (_matcher1.Find()) { 
this.state = 46;
}if (true) break;

case 46:
//C
this.state = 47;
 //BA.debugLineNum = 372;BA.debugLine="Dim iHrs, iMins As Int";
_ihrs = 0;
_imins = 0;
 //BA.debugLineNum = 373;BA.debugLine="Dim AmPm As String";
_ampm = "";
 //BA.debugLineNum = 374;BA.debugLine="Dim sMin1 As String";
_smin1 = "";
 //BA.debugLineNum = 376;BA.debugLine="iHrs = Matcher1.Group(1)";
_ihrs = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 377;BA.debugLine="iMins = Matcher1.Group(2)";
_imins = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 378;BA.debugLine="AmPm = Matcher1.Group(3)";
_ampm = _matcher1.Group((int) (3));
 //BA.debugLineNum = 380;BA.debugLine="LogColor(AmPm,Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("876283987",_ampm,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 382;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins))";
if (true) break;

case 47:
//if
this.state = 52;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_imins)))==1) { 
this.state = 49;
}else {
this.state = 51;
}if (true) break;

case 49:
//C
this.state = 52;
 //BA.debugLineNum = 383;BA.debugLine="sMin1 = $\"0\"$ & iMins";
_smin1 = ("0")+BA.NumberToString(_imins);
 if (true) break;

case 51:
//C
this.state = 52;
 //BA.debugLineNum = 385;BA.debugLine="sMin1 = iMins";
_smin1 = BA.NumberToString(_imins);
 if (true) break;

case 52:
//C
this.state = 53;
;
 if (true) break;

case 53:
//C
this.state = 54;
;
 if (true) break;

case 54:
//C
this.state = 55;
;
 //BA.debugLineNum = 391;BA.debugLine="LogColor(sTimeOn,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("876283998",parent.mostCurrent._stimeon,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 392;BA.debugLine="LogColor(sTimeOff,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("876283999",parent.mostCurrent._stimeoff,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 396;BA.debugLine="iPowerSource = RS.GetInt(\"PowerSourceID\")";
_ipowersource = _rs.GetInt("PowerSourceID");
 //BA.debugLineNum = 397;BA.debugLine="If iPowerSource = 1 Then";
if (true) break;

case 55:
//if
this.state = 60;
if (_ipowersource==1) { 
this.state = 57;
}else {
this.state = 59;
}if (true) break;

case 57:
//C
this.state = 60;
 //BA.debugLineNum = 398;BA.debugLine="optElectricity.Checked = True";
parent.mostCurrent._optelectricity.setChecked(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 399;BA.debugLine="optGenerator.Checked = False";
parent.mostCurrent._optgenerator.setChecked(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 59:
//C
this.state = 60;
 //BA.debugLineNum = 401;BA.debugLine="optElectricity.Checked = False";
parent.mostCurrent._optelectricity.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 402;BA.debugLine="optGenerator.Checked = True";
parent.mostCurrent._optgenerator.setChecked(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 60:
//C
this.state = 61;
;
 //BA.debugLineNum = 405;BA.debugLine="txtDuration.Text = RS.GetInt(\"DrainTime\")";
parent.mostCurrent._txtduration.setText(BA.ObjectToCharSequence(_rs.GetInt("DrainTime")));
 //BA.debugLineNum = 406;BA.debugLine="txtDrainCum.Text = RS.GetInt(\"DrainCum\")";
parent.mostCurrent._txtdraincum.setText(BA.ObjectToCharSequence(_rs.GetInt("DrainCum")));
 //BA.debugLineNum = 408;BA.debugLine="If txtDrainCum.Text = Null Or txtDrainCum.Text";
if (true) break;

case 61:
//if
this.state = 66;
if (parent.mostCurrent._txtdraincum.getText()== null || (parent.mostCurrent._txtdraincum.getText()).equals(BA.NumberToString(0)) || parent.mostCurrent._txtduration.getText()== null || (parent.mostCurrent._txtduration.getText()).equals(BA.NumberToString(0))) { 
this.state = 63;
}else {
this.state = 65;
}if (true) break;

case 63:
//C
this.state = 66;
 //BA.debugLineNum = 409;BA.debugLine="chkDrain.Checked = False";
parent.mostCurrent._chkdrain.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 410;BA.debugLine="txtDuration.Text = \"\"";
parent.mostCurrent._txtduration.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 411;BA.debugLine="txtDrainCum.Text = \"\"";
parent.mostCurrent._txtdraincum.setText(BA.ObjectToCharSequence(""));
 if (true) break;

case 65:
//C
this.state = 66;
 //BA.debugLineNum = 413;BA.debugLine="chkDrain.Checked = True";
parent.mostCurrent._chkdrain.setChecked(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 66:
//C
this.state = 69;
;
 //BA.debugLineNum = 415;BA.debugLine="txtOnRemarks.Text = RS.GetString(\"TimeOnRemarks";
parent.mostCurrent._txtonremarks.setText(BA.ObjectToCharSequence(_rs.GetString("TimeOnRemarks")));
 if (true) break;

case 68:
//C
this.state = 69;
 //BA.debugLineNum = 417;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 418;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 419;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 420;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 421;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("876284028",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 69:
//C
this.state = 72;
;
 if (true) break;

case 71:
//C
this.state = 72;
this.catchState = 0;
 //BA.debugLineNum = 424;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 425;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 426;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 427;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 428;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("876284035",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 72:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 430;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 28;BA.debugLine="Private xmlIcon As XmlLayoutBuilder";
mostCurrent._xmlicon = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 29;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private CD, CDtxtBox, cdFixedText As ColorDrawabl";
mostCurrent._cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdtxtbox = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdfixedtext = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 31;BA.debugLine="Private vibration As B4Avibrate";
mostCurrent._vibration = new com.johan.Vibrate.Vibrate();
 //BA.debugLineNum = 32;BA.debugLine="Private vibratePattern() As Long";
_vibratepattern = new long[(int) (0)];
;
 //BA.debugLineNum = 34;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private csAns As CSBuilder";
mostCurrent._csans = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 37;BA.debugLine="Private TabMenu As WobbleMenu";
mostCurrent._tabmenu = new bwsi.PumpOperations.wobblemenu();
 //BA.debugLineNum = 40;BA.debugLine="Private mskTimeOn As MaskedEditText";
mostCurrent._msktimeon = new flm.b4a.maskededittext.MaskedEditTextWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private optElectricity As B4XView";
mostCurrent._optelectricity = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private optGenerator As B4XView";
mostCurrent._optgenerator = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private chkDrain As B4XView";
mostCurrent._chkdrain = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private txtDuration As EditText";
mostCurrent._txtduration = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private txtPSI As EditText";
mostCurrent._txtpsi = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private txtDrainCum As EditText";
mostCurrent._txtdraincum = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private txtOnRemarks As EditText";
mostCurrent._txtonremarks = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private chkDefaultTimeOn As CheckBox";
mostCurrent._chkdefaulttimeon = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private btnSave As ACButton";
mostCurrent._btnsave = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private SaveHeaderSuccess, SaveDetailSuccess As B";
_saveheadersuccess = false;
_savedetailsuccess = false;
 //BA.debugLineNum = 56;BA.debugLine="Private sTimeOn As String";
mostCurrent._stimeon = "";
 //BA.debugLineNum = 57;BA.debugLine="Private sTimeOff As String";
mostCurrent._stimeoff = "";
 //BA.debugLineNum = 58;BA.debugLine="Private iHrOn, iHrOff As Int";
_ihron = 0;
_ihroff = 0;
 //BA.debugLineNum = 59;BA.debugLine="Private TotDrainHrs, TotDrainCum, totPSI As Doubl";
_totdrainhrs = 0;
_totdraincum = 0;
_totpsi = 0;
 //BA.debugLineNum = 60;BA.debugLine="Private PowerSourceID As Int";
_powersourceid = 0;
 //BA.debugLineNum = 61;BA.debugLine="Private TotOpHrs As Float";
_totophrs = 0f;
 //BA.debugLineNum = 63;BA.debugLine="Private PumpPowerStatus As Int";
_pumppowerstatus = 0;
 //BA.debugLineNum = 65;BA.debugLine="Private Spinner1 As Spinner";
mostCurrent._spinner1 = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 66;BA.debugLine="Private B4ADropDownView1 As B4ADropDownView";
mostCurrent._b4adropdownview1 = new com.example.B4ADropDownView.B4ADropDownView();
 //BA.debugLineNum = 68;BA.debugLine="Private imeKeyboard As IME";
mostCurrent._imekeyboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 69;BA.debugLine="Private Alert As AX_CustomAlertDialog";
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
 //BA.debugLineNum = 514;BA.debugLine="Private Sub InsertNewPumpTime() As Boolean";
 //BA.debugLineNum = 515;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 517;BA.debugLine="Dim sDateTime As String";
_sdatetime = "";
 //BA.debugLineNum = 518;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 519;BA.debugLine="Dim sRemarks, sLocation As String";
_sremarks = "";
_slocation = "";
 //BA.debugLineNum = 521;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 522;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 523;BA.debugLine="sDateTime = DateTime.Date(lDate)";
_sdatetime = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 525;BA.debugLine="sRemarks = txtOnRemarks.Text";
_sremarks = mostCurrent._txtonremarks.getText();
 //BA.debugLineNum = 527;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 529;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("876546063",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 531;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("876546065",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 532;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("876546066",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 534;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 535;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("876546069",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 537;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 538;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeaderID";
anywheresoftware.b4a.keywords.Common.LogImpl("876546072",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 540;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 541;BA.debugLine="Try";
try { //BA.debugLineNum = 542;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO OnOffDe";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO OnOffDetails VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._tranheaderid /*int*/ ),(Object)(mostCurrent._stimeon),(Object)(("")),(Object)(("0")),(Object)(_powersourceid),(Object)(mostCurrent._txtpsi.getText()),(Object)(_totdrainhrs),(Object)(_totdraincum),(Object)(_sremarks),(Object)(("")),(Object)(("0")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_sdatetime),(Object)(_slocation),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(("")),(Object)(("0")),(Object)((""))}));
 //BA.debugLineNum = 545;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 546;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e23) {
			processBA.setLastException(e23); //BA.debugLineNum = 548;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("876546082",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 549;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 551;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 552;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 553;BA.debugLine="End Sub";
return false;
}
public static boolean  _isvalidentries() throws Exception{
 //BA.debugLineNum = 444;BA.debugLine="Private Sub IsValidEntries() As Boolean";
 //BA.debugLineNum = 445;BA.debugLine="LogColor(mskTimeOn.Text, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("876414977",mostCurrent._msktimeon.getText(),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 446;BA.debugLine="Try";
try { //BA.debugLineNum = 447;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(mskTimeOn.";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._msktimeon.getText()))<=0 || (mostCurrent._msktimeon.getText()).equals("__:__")) { 
 //BA.debugLineNum = 448;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Pump Time cannot be";
_requiredmsgbox(("ERROR"),("Pump Time cannot be blank!"));
 //BA.debugLineNum = 449;BA.debugLine="mskTimeOn.RequestFocus";
mostCurrent._msktimeon.RequestFocus();
 //BA.debugLineNum = 450;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else if(mostCurrent._validation._istime /*boolean*/ (mostCurrent.activityBA,mostCurrent._msktimeon.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 453;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid Pump Time O";
_requiredmsgbox(("ERROR"),("Invalid Pump Time On!"));
 //BA.debugLineNum = 454;BA.debugLine="mskTimeOn.RequestFocus";
mostCurrent._msktimeon.RequestFocus();
 //BA.debugLineNum = 455;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 458;BA.debugLine="If optElectricity.Checked = False And optGenerat";
if (mostCurrent._optelectricity.getChecked()==anywheresoftware.b4a.keywords.Common.False && mostCurrent._optgenerator.getChecked()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 459;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"No Power Source sel";
_requiredmsgbox(("ERROR"),("No Power Source selected!"));
 //BA.debugLineNum = 460;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 463;BA.debugLine="If chkDrain.Checked = True Then";
if (mostCurrent._chkdrain.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 464;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtDurati";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtduration.getText()))<=0) { 
 //BA.debugLineNum = 465;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Drain Time in minu";
_requiredmsgbox(("ERROR"),("Drain Time in minutes cannot be blank!"));
 //BA.debugLineNum = 466;BA.debugLine="txtDuration.RequestFocus";
mostCurrent._txtduration.RequestFocus();
 //BA.debugLineNum = 467;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 470;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtPSI.Te";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtpsi.getText()))<=0) { 
 //BA.debugLineNum = 471;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Pump Pressure in P";
_requiredmsgbox(("ERROR"),("Pump Pressure in PSI cannot be blank!"));
 //BA.debugLineNum = 472;BA.debugLine="txtPSI.RequestFocus";
mostCurrent._txtpsi.RequestFocus();
 //BA.debugLineNum = 473;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 476;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtDrainC";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtdraincum.getText()))<=0) { 
 //BA.debugLineNum = 477;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Drain Production i";
_requiredmsgbox(("ERROR"),("Drain Production in CuM cannot be blank!"));
 //BA.debugLineNum = 478;BA.debugLine="txtDrainCum.RequestFocus";
mostCurrent._txtdraincum.RequestFocus();
 //BA.debugLineNum = 479;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 };
 } 
       catch (Exception e34) {
			processBA.setLastException(e34); //BA.debugLineNum = 483;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("876415015",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 484;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 486;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 487;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 858;BA.debugLine="Private Sub PumpOn_OnNegativeClicked (View As View";
 //BA.debugLineNum = 860;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 861;BA.debugLine="End Sub";
return "";
}
public static String  _pumpon_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 863;BA.debugLine="Private Sub PumpOn_OnPositiveClicked (View As View";
 //BA.debugLineNum = 866;BA.debugLine="Alert.Initialize.Dismiss2";
mostCurrent._alert.Initialize().Dismiss2();
 //BA.debugLineNum = 867;BA.debugLine="Select GlobalVar.blnNewTime 'New or Edit";
switch (BA.switchObjectToInt(mostCurrent._globalvar._blnnewtime /*boolean*/ ,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False)) {
case 0: {
 //BA.debugLineNum = 869;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHead";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 870;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeader";
anywheresoftware.b4a.keywords.Common.LogImpl("877660167",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 872;BA.debugLine="If GlobalVar.TranHeaderID = 0 Or Not(DBaseFunct";
if (mostCurrent._globalvar._tranheaderid /*int*/ ==0 || anywheresoftware.b4a.keywords.Common.Not(mostCurrent._dbasefunctions._istransactionheaderexist /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ ))) { 
 //BA.debugLineNum = 873;BA.debugLine="If Not(SaveTransHeader) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_savetransheader())) { 
if (true) return "";};
 //BA.debugLineNum = 874;BA.debugLine="If Not(InsertNewPumpTime) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_insertnewpumptime())) { 
if (true) return "";};
 //BA.debugLineNum = 875;BA.debugLine="DBaseFunctions.UpdatePumpPowerStatus (1, Globa";
mostCurrent._dbasefunctions._updatepumppowerstatus /*String*/ (mostCurrent.activityBA,(int) (1),mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 876;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 }else {
 //BA.debugLineNum = 883;BA.debugLine="If Not(InsertNewPumpTime) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_insertnewpumptime())) { 
if (true) return "";};
 //BA.debugLineNum = 884;BA.debugLine="If Not(UpdateTranHeader(GlobalVar.TranHeaderID";
if (anywheresoftware.b4a.keywords.Common.Not(_updatetranheader(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
if (true) return "";};
 //BA.debugLineNum = 885;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 };
 break; }
case 1: {
 //BA.debugLineNum = 889;BA.debugLine="LogColor($\"Edit Time Record\"$, Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogImpl("877660186",("Edit Time Record"),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 890;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHead";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 891;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeader";
anywheresoftware.b4a.keywords.Common.LogImpl("877660188",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 892;BA.debugLine="If DBaseFunctions.IsPumpTimeOverlappedEdit(Date";
if (mostCurrent._dbasefunctions._ispumptimeoverlappededit /*boolean*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(mostCurrent._stimeon),anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(mostCurrent._stimeoff),mostCurrent._globalvar._tranheaderid /*int*/ ,mostCurrent._globalvar._timedetailid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 893;BA.debugLine="RequiredMsgBox($\"E R R O R\"$,$\"Unable to Edit";
_requiredmsgbox(("E R R O R"),("Unable to Edit Pump Time record due to it will Overlap existing records"));
 //BA.debugLineNum = 894;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 896;BA.debugLine="If Not(UpdatePumpTime(GlobalVar.TranHeaderID, G";
if (anywheresoftware.b4a.keywords.Common.Not(_updatepumptime(mostCurrent._globalvar._tranheaderid /*int*/ ,mostCurrent._globalvar._timedetailid /*int*/ ))) { 
if (true) return "";};
 //BA.debugLineNum = 897;BA.debugLine="If Not(EditTranHeader(GlobalVar.TranHeaderID))";
if (anywheresoftware.b4a.keywords.Common.Not(_edittranheader(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
if (true) return "";};
 //BA.debugLineNum = 898;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 break; }
}
;
 //BA.debugLineNum = 900;BA.debugLine="End Sub";
return "";
}
public static String  _reqmsg_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 802;BA.debugLine="Private Sub ReqMsg_OnBindView (View As View, ViewT";
 //BA.debugLineNum = 803;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 804;BA.debugLine="Alert.Initialize";
mostCurrent._alert.Initialize();
 //BA.debugLineNum = 805;BA.debugLine="If ViewType = Alert.VIEW_TITLE Then ' Title";
if (_viewtype==mostCurrent._alert.VIEW_TITLE) { 
 //BA.debugLineNum = 806;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 807;BA.debugLine="lbl.TextSize = 30";
_lbl.setTextSize((float) (30));
 //BA.debugLineNum = 808;BA.debugLine="lbl.SetTextColorAnimated(2000,Colors.Magenta)";
_lbl.SetTextColorAnimated((int) (2000),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 811;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 815;BA.debugLine="CS.Initialize.Typeface(Typeface.MATERIALICONS).S";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe88e)))+"  "));
 //BA.debugLineNum = 816;BA.debugLine="CS.Typeface(Font).Size(24).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(_font.getObject())).Size((int) (24)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 818;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 820;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsg_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 797;BA.debugLine="Private Sub RequiredMsg_OnPositiveClicked (View As";
 //BA.debugLineNum = 798;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 799;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 800;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsgbox(String _stitle,String _smsg) throws Exception{
 //BA.debugLineNum = 772;BA.debugLine="Private Sub RequiredMsgBox(sTitle As String, sMsg";
 //BA.debugLineNum = 773;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 774;BA.debugLine="Alert.Initialize.Dismiss2";
mostCurrent._alert.Initialize().Dismiss2();
 //BA.debugLineNum = 776;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._redcolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessage(_smsg).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessageTypeface((android.graphics.Typeface)(_font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"RequiredMsg").SetOnViewBinder(mostCurrent.activityBA,"ReqMsg");
 //BA.debugLineNum = 791;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 792;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 793;BA.debugLine="End Sub";
return "";
}
public static boolean  _savetransheader() throws Exception{
boolean _bretval = false;
long _lngdatetime = 0L;
String _saddedat = "";
 //BA.debugLineNum = 489;BA.debugLine="Private Sub SaveTransHeader() As Boolean";
 //BA.debugLineNum = 490;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 491;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 492;BA.debugLine="Dim sAddedAt As String";
_saddedat = "";
 //BA.debugLineNum = 494;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 495;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 496;BA.debugLine="sAddedAt= DateTime.Date(lngDateTime)";
_saddedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 498;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 499;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 500;BA.debugLine="Try";
try { //BA.debugLineNum = 502;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO TranHea";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO TranHeader VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._branchid /*int*/ ),(Object)(mostCurrent._globalvar._pumphouseid /*int*/ ),(Object)(mostCurrent._globalvar._trandate /*String*/ ),(Object)(("0")),(Object)(("0")),(Object)(_totdraincum),(Object)(_totdrainhrs),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_saddedat),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(("0")),(Object)(("")),(Object)((""))}));
 //BA.debugLineNum = 504;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 505;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e14) {
			processBA.setLastException(e14); //BA.debugLineNum = 507;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("876480530",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 508;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 510;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 511;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 512;BA.debugLine="End Sub";
return false;
}
public static String  _showsavesuccess() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
anywheresoftware.b4a.objects.CSBuilder _cscontent = null;
 //BA.debugLineNum = 902;BA.debugLine="Private Sub ShowSaveSuccess()";
 //BA.debugLineNum = 903;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 904;BA.debugLine="Dim csContent As CSBuilder";
_cscontent = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 906;BA.debugLine="If GlobalVar.blnNewTime = True Then";
if (mostCurrent._globalvar._blnnewtime /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 907;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 908;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("New pump time has been successfully saved!"))).PopAll();
 }else {
 //BA.debugLineNum = 910;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 911;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("Pump time has been successfully updated!"))).PopAll();
 };
 //BA.debugLineNum = 914;BA.debugLine="MatDialogBuilder.Initialize(\"AddPumpTimeOnRecords";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"AddPumpTimeOnRecords");
 //BA.debugLineNum = 915;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 916;BA.debugLine="MatDialogBuilder.Content(csContent)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_cscontent.getObject()));
 //BA.debugLineNum = 917;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 918;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 919;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 920;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 921;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 922;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 194;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 195;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 190;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 191;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 192;BA.debugLine="End Sub";
return "";
}
public static String  _txtduration_enterpressed() throws Exception{
 //BA.debugLineNum = 759;BA.debugLine="Sub txtDuration_EnterPressed";
 //BA.debugLineNum = 760;BA.debugLine="txtPSI.RequestFocus";
mostCurrent._txtpsi.RequestFocus();
 //BA.debugLineNum = 761;BA.debugLine="End Sub";
return "";
}
public static String  _txtduration_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 763;BA.debugLine="Sub txtDuration_FocusChanged (HasFocus As Boolean)";
 //BA.debugLineNum = 764;BA.debugLine="If HasFocus = True Then txtDuration.SelectAll";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._txtduration.SelectAll();};
 //BA.debugLineNum = 765;BA.debugLine="End Sub";
return "";
}
public static String  _txtduration_textchanged(String _old,String _new) throws Exception{
int _losses = 0;
 //BA.debugLineNum = 747;BA.debugLine="Sub txtDuration_TextChanged (Old As String, New As";
 //BA.debugLineNum = 748;BA.debugLine="Dim Losses As Int";
_losses = 0;
 //BA.debugLineNum = 750;BA.debugLine="Losses = 0";
_losses = (int) (0);
 //BA.debugLineNum = 751;BA.debugLine="If GlobalVar.SF.Len(txtDuration.Text) <= 0 Or Glo";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtduration.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._pumpdrainpipesize /*String*/ )<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtpsi.getText())<=0) { 
 //BA.debugLineNum = 752;BA.debugLine="Losses = 0";
_losses = (int) (0);
 }else {
 //BA.debugLineNum = 754;BA.debugLine="Losses = DBaseFunctions.ComputeWaterLoss(GlobalV";
_losses = (int) (mostCurrent._dbasefunctions._computewaterloss /*double*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumpdrainpipetype /*String*/ ,mostCurrent._globalvar._pumpdrainpipesize /*String*/ ,(int)(Double.parseDouble(mostCurrent._txtpsi.getText())),(int)(Double.parseDouble(mostCurrent._txtduration.getText()))));
 };
 //BA.debugLineNum = 756;BA.debugLine="txtDrainCum.Text = Losses";
mostCurrent._txtdraincum.setText(BA.ObjectToCharSequence(_losses));
 //BA.debugLineNum = 757;BA.debugLine="End Sub";
return "";
}
public static String  _txtonremarks_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 767;BA.debugLine="Sub txtOnRemarks_FocusChanged (HasFocus As Boolean";
 //BA.debugLineNum = 768;BA.debugLine="If HasFocus = True Then txtOnRemarks.SelectAll";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._txtonremarks.SelectAll();};
 //BA.debugLineNum = 769;BA.debugLine="End Sub";
return "";
}
public static String  _txtpsi_enterpressed() throws Exception{
 //BA.debugLineNum = 738;BA.debugLine="Sub txtPSI_EnterPressed";
 //BA.debugLineNum = 739;BA.debugLine="txtOnRemarks.RequestFocus";
mostCurrent._txtonremarks.RequestFocus();
 //BA.debugLineNum = 740;BA.debugLine="End Sub";
return "";
}
public static String  _txtpsi_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 742;BA.debugLine="Sub txtPSI_FocusChanged (HasFocus As Boolean)";
 //BA.debugLineNum = 743;BA.debugLine="If HasFocus = True Then txtPSI.SelectAll";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._txtpsi.SelectAll();};
 //BA.debugLineNum = 744;BA.debugLine="End Sub";
return "";
}
public static String  _txtpsi_textchanged(String _old,String _new) throws Exception{
int _losses = 0;
 //BA.debugLineNum = 726;BA.debugLine="Sub txtPSI_TextChanged (Old As String, New As Stri";
 //BA.debugLineNum = 727;BA.debugLine="Dim Losses As Int";
_losses = 0;
 //BA.debugLineNum = 729;BA.debugLine="Losses = 0";
_losses = (int) (0);
 //BA.debugLineNum = 730;BA.debugLine="If GlobalVar.SF.Len(txtDuration.Text) <= 0 Or Glo";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtduration.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._pumpdrainpipesize /*String*/ )<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtpsi.getText())<=0) { 
 //BA.debugLineNum = 731;BA.debugLine="Losses = 0";
_losses = (int) (0);
 }else {
 //BA.debugLineNum = 733;BA.debugLine="Losses = DBaseFunctions.ComputeWaterLoss(GlobalV";
_losses = (int) (mostCurrent._dbasefunctions._computewaterloss /*double*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumpdrainpipetype /*String*/ ,mostCurrent._globalvar._pumpdrainpipesize /*String*/ ,(int)(Double.parseDouble(mostCurrent._txtpsi.getText())),(int)(Double.parseDouble(mostCurrent._txtduration.getText()))));
 };
 //BA.debugLineNum = 735;BA.debugLine="txtDrainCum.Text = Losses";
mostCurrent._txtdraincum.setText(BA.ObjectToCharSequence(_losses));
 //BA.debugLineNum = 736;BA.debugLine="End Sub";
return "";
}
public static boolean  _updatepumptime(int _itranheaderid,int _idetailid) throws Exception{
boolean _bretval = false;
String _sdatetime = "";
long _ldate = 0L;
 //BA.debugLineNum = 555;BA.debugLine="Private Sub UpdatePumpTime(iTranHeaderID As Int, i";
 //BA.debugLineNum = 556;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 557;BA.debugLine="Dim sDateTime As String";
_sdatetime = "";
 //BA.debugLineNum = 558;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 560;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 561;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 562;BA.debugLine="sDateTime = DateTime.Date(lDate)";
_sdatetime = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 564;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 565;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeaderID";
anywheresoftware.b4a.keywords.Common.LogImpl("876611594",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 567;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 568;BA.debugLine="Try";
try { //BA.debugLineNum = 569;BA.debugLine="Starter.strCriteria = \"UPDATE OnOffDetails SET \"";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE OnOffDetails SET "+"PumpOnTime = ?, "+"PowerSourceID = ?, "+"DrainTime = ?, "+"DrainCum = ?, "+"TimeOnRemarks = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid)+" "+"AND DetailID = "+BA.NumberToString(_idetailid);
 //BA.debugLineNum = 580;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{mostCurrent._stimeon,BA.NumberToString(_totophrs),BA.NumberToString(_powersourceid),BA.NumberToString(_totdrainhrs),BA.NumberToString(_totdraincum),mostCurrent._txtonremarks.getText(),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_sdatetime}));
 //BA.debugLineNum = 581;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 582;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e16) {
			processBA.setLastException(e16); //BA.debugLineNum = 584;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("876611613",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 585;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 587;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 588;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 589;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 591;BA.debugLine="Private Sub UpdateTranHeader(iTranHeaderID As Int)";
 //BA.debugLineNum = 592;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 593;BA.debugLine="Dim GTotOPHrs As Float";
_gtotophrs = 0f;
 //BA.debugLineNum = 594;BA.debugLine="Dim GTotDrain, GTotDuration As Double";
_gtotdrain = 0;
_gtotduration = 0;
 //BA.debugLineNum = 596;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 597;BA.debugLine="Dim sModifiedAt As String";
_smodifiedat = "";
 //BA.debugLineNum = 599;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 600;BA.debugLine="DateTime.TimeFormat = \"hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm:ss a");
 //BA.debugLineNum = 601;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 602;BA.debugLine="sModifiedAt = DateTime.Date(lngDateTime) & $\" \"$";
_smodifiedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime)+(" ")+anywheresoftware.b4a.keywords.Common.DateTime.Time(_lngdatetime);
 //BA.debugLineNum = 604;BA.debugLine="Dim rsHeader As Cursor";
_rsheader = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 606;BA.debugLine="Starter.strCriteria = \"SELECT * FROM TranHeader W";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM TranHeader WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 607;BA.debugLine="rsHeader = Starter.DBCon.ExecQuery(Starter.strCri";
_rsheader = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 608;BA.debugLine="If rsHeader.RowCount > 0 Then";
if (_rsheader.getRowCount()>0) { 
 //BA.debugLineNum = 609;BA.debugLine="rsHeader.Position = 0";
_rsheader.setPosition((int) (0));
 //BA.debugLineNum = 610;BA.debugLine="GTotOPHrs = rsHeader.GetDouble(\"TotOpHrs\") + Tot";
_gtotophrs = (float) (_rsheader.GetDouble("TotOpHrs")+_totophrs);
 //BA.debugLineNum = 611;BA.debugLine="GTotDuration = rsHeader.GetInt(\"TotDrainHrs\") +";
_gtotduration = _rsheader.GetInt("TotDrainHrs")+_totdrainhrs;
 //BA.debugLineNum = 612;BA.debugLine="GTotDrain = rsHeader.GetInt(\"TotDrain\") + TotDra";
_gtotdrain = _rsheader.GetInt("TotDrain")+_totdraincum;
 }else {
 //BA.debugLineNum = 614;BA.debugLine="GTotOPHrs = TotOpHrs";
_gtotophrs = _totophrs;
 //BA.debugLineNum = 615;BA.debugLine="GTotDuration = TotDrainHrs";
_gtotduration = _totdrainhrs;
 //BA.debugLineNum = 616;BA.debugLine="GTotDrain = TotDrainCum";
_gtotdrain = _totdraincum;
 };
 //BA.debugLineNum = 618;BA.debugLine="rsHeader.Close";
_rsheader.Close();
 //BA.debugLineNum = 619;BA.debugLine="LogColor($\"Total Op Hrs: \"$ & GTotOPHrs, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("876677148",("Total Op Hrs: ")+BA.NumberToString(_gtotophrs),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 621;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 622;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 623;BA.debugLine="Try";
try { //BA.debugLineNum = 624;BA.debugLine="Starter.strCriteria = \"UPDATE TranHeader SET \" &";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE TranHeader SET "+"TotOpHrs = ?, "+"TotDrainHrs = ?, "+"TotDrain = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 632;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_gtotophrs),BA.NumberToString(_gtotduration),BA.NumberToString(_gtotdrain),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_smodifiedat}));
 //BA.debugLineNum = 633;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 634;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e33) {
			processBA.setLastException(e33); //BA.debugLineNum = 636;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("876677165",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 637;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 639;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 640;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 641;BA.debugLine="End Sub";
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
