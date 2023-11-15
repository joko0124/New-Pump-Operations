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

public class addeditnonoperational extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static addeditnonoperational mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.addeditnonoperational");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (addeditnonoperational).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.addeditnonoperational");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.addeditnonoperational", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (addeditnonoperational) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (addeditnonoperational) Resume **");
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
		return addeditnonoperational.class;
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
            BA.LogInfo("** Activity (addeditnonoperational) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (addeditnonoperational) Pause event (activity is not paused). **");
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
            addeditnonoperational mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (addeditnonoperational) Resume **");
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
public com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
public de.amberhome.objects.SnackbarWrapper _snack = null;
public anywheresoftware.b4a.objects.CSBuilder _csans = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnsaveupdate = null;
public flm.b4a.maskededittext.MaskedEditTextWrapper _msktimestart = null;
public flm.b4a.maskededittext.MaskedEditTextWrapper _msktimefinished = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtreason = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtremarks = null;
public anywheresoftware.b4a.objects.IME _imekeyboard = null;
public static String _stimestart = "";
public static String _stimefinished = "";
public static int _ihrs1 = 0;
public static int _ihrs2 = 0;
public static int _imins1 = 0;
public static int _imins2 = 0;
public static String _ihrsstart = "";
public static String _ihrsfinished = "";
public static String _smin1 = "";
public static String _smin2 = "";
public static String _sampm1 = "";
public static String _sampm2 = "";
public static float _totnonophours = 0f;
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
 //BA.debugLineNum = 57;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 58;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 59;BA.debugLine="Activity.LoadLayout(\"AddEditPumpNonOperational\")";
mostCurrent._activity.LoadLayout("AddEditPumpNonOperational",mostCurrent.activityBA);
 //BA.debugLineNum = 61;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 62;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 64;BA.debugLine="If GlobalVar.blnNewNonOp = True Then";
if (mostCurrent._globalvar._blnnewnonop /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 65;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Appen";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("ADD NEW PUMP NON-OPERATIONAL RECORD"))).PopAll();
 //BA.debugLineNum = 66;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append(";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 //BA.debugLineNum = 67;BA.debugLine="ClearUI";
_clearui();
 //BA.debugLineNum = 68;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\" SAVE\"$";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" SAVE")));
 }else {
 //BA.debugLineNum = 70;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Appen";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("EDIT PUMP NON-OPERATIONAL RECORD"))).PopAll();
 //BA.debugLineNum = 71;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append(";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 //BA.debugLineNum = 72;BA.debugLine="ClearUI";
_clearui();
 //BA.debugLineNum = 73;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\" UPDATE\"$";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" UPDATE")));
 //BA.debugLineNum = 74;BA.debugLine="GetNonOpRecord(GlobalVar.NonOpDetailID)";
_getnonoprecord(mostCurrent._globalvar._nonopdetailid /*int*/ );
 };
 //BA.debugLineNum = 77;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 78;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 79;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 80;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 82;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 83;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 85;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 86;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 87;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 89;BA.debugLine="InpTyp.Initialize";
_inptyp._initialize /*String*/ (processBA);
 //BA.debugLineNum = 90;BA.debugLine="InpTyp.SetInputType(txtReason,Array As Int(InpTyp";
_inptyp._setinputtype /*String*/ (mostCurrent._txtreason,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_correct /*int*/ (),_inptyp._type_text_flag_cap_sentences /*int*/ ()});
 //BA.debugLineNum = 91;BA.debugLine="InpTyp.SetInputType(txtRemarks,Array As Int(InpTy";
_inptyp._setinputtype /*String*/ (mostCurrent._txtremarks,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_correct /*int*/ (),_inptyp._type_text_flag_cap_sentences /*int*/ ()});
 //BA.debugLineNum = 93;BA.debugLine="imeKeyboard.Initialize(\"\")";
mostCurrent._imekeyboard.Initialize("");
 //BA.debugLineNum = 95;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 };
 //BA.debugLineNum = 98;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
 //BA.debugLineNum = 158;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 160;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 161;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 139;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 140;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 141;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 142;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 144;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 152;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 153;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 113;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 114;BA.debugLine="If Result Then";
if (_result) { 
 //BA.debugLineNum = 115;BA.debugLine="If Permission = Starter.RTP.PERMISSION_READ_EXTE";
if ((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 116;BA.debugLine="LogColor($\"Permission to Read External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("767633155",("Permission to Read External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 117;BA.debugLine="GlobalVar.ReadStoragePermission = True";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 119;BA.debugLine="LogColor($\"Permission to Write External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("767633158",("Permission to Write External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 120;BA.debugLine="GlobalVar.WriteStoragePermission = True";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION)) { 
 //BA.debugLineNum = 122;BA.debugLine="LogColor($\"Permission to Access Coarse Location";
anywheresoftware.b4a.keywords.Common.LogImpl("767633161",("Permission to Access Coarse Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 123;BA.debugLine="GlobalVar.CoarseLocPermission = True";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION)) { 
 //BA.debugLineNum = 125;BA.debugLine="LogColor($\"Permission to Access Fine Location G";
anywheresoftware.b4a.keywords.Common.LogImpl("767633164",("Permission to Access Fine Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 126;BA.debugLine="GlobalVar.FineLocPermission = True";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 128;BA.debugLine="Starter.StartFLP";
mostCurrent._starter._startflp /*void*/ ();
 }else {
 //BA.debugLineNum = 130;BA.debugLine="GlobalVar.ReadStoragePermission = False";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 131;BA.debugLine="GlobalVar.WriteStoragePermission = False";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 132;BA.debugLine="GlobalVar.CoarseLocPermission = False";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 133;BA.debugLine="GlobalVar.FineLocPermission = False";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 134;BA.debugLine="Result = False";
_result = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 136;BA.debugLine="Log (Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("767633175",_permission,0);
 //BA.debugLineNum = 137;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 148;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 149;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 150;BA.debugLine="End Sub";
return "";
}
public static String  _btnsaveupdate_click() throws Exception{
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher2 = null;
 //BA.debugLineNum = 672;BA.debugLine="Sub btnSaveUpdate_Click";
 //BA.debugLineNum = 673;BA.debugLine="If Not(IsValidEntries) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_isvalidentries())) { 
if (true) return "";};
 //BA.debugLineNum = 675;BA.debugLine="Dim Matcher1, Matcher2 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
_matcher2 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 677;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d)\", mskTime";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d)",mostCurrent._msktimestart.getText());
 //BA.debugLineNum = 679;BA.debugLine="If Matcher1.Find Then";
if (_matcher1.Find()) { 
 //BA.debugLineNum = 680;BA.debugLine="iHrs1 = GlobalVar.SF.Val(Matcher1.Group(1))";
_ihrs1 = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 681;BA.debugLine="iMins1 = GlobalVar.SF.Val(Matcher1.Group(2))";
_imins1 = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 683;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins1)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_imins1)))==1) { 
 //BA.debugLineNum = 684;BA.debugLine="sMin1 = $\"0\"$ & iMins1";
mostCurrent._smin1 = ("0")+BA.NumberToString(_imins1);
 }else {
 //BA.debugLineNum = 686;BA.debugLine="sMin1 = iMins1";
mostCurrent._smin1 = BA.NumberToString(_imins1);
 };
 //BA.debugLineNum = 689;BA.debugLine="If iHrs1 > 12 Then";
if (_ihrs1>12) { 
 //BA.debugLineNum = 690;BA.debugLine="iHrsStart = iHrs1 - 12";
mostCurrent._ihrsstart = BA.NumberToString(_ihrs1-12);
 //BA.debugLineNum = 691;BA.debugLine="sAmPm1 = \"PM\"";
mostCurrent._sampm1 = "PM";
 }else if(_ihrs1==12) { 
 //BA.debugLineNum = 693;BA.debugLine="iHrsStart = iHrs1";
mostCurrent._ihrsstart = BA.NumberToString(_ihrs1);
 //BA.debugLineNum = 694;BA.debugLine="sAmPm1 = \"PM\"";
mostCurrent._sampm1 = "PM";
 }else if(_ihrs1<12) { 
 //BA.debugLineNum = 696;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrs1)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_ihrs1)))==1) { 
 //BA.debugLineNum = 697;BA.debugLine="iHrsStart = $\"0\"$ & iHrs1";
mostCurrent._ihrsstart = ("0")+BA.NumberToString(_ihrs1);
 }else {
 //BA.debugLineNum = 699;BA.debugLine="iHrsStart = iHrs1";
mostCurrent._ihrsstart = BA.NumberToString(_ihrs1);
 };
 //BA.debugLineNum = 701;BA.debugLine="sAmPm1 = \"AM\"";
mostCurrent._sampm1 = "AM";
 };
 //BA.debugLineNum = 704;BA.debugLine="sTimeStart = iHrsStart & $\":\"$ & sMin1 & $\" \"$ &";
mostCurrent._stimestart = mostCurrent._ihrsstart+(":")+mostCurrent._smin1+(" ")+mostCurrent._sampm1;
 //BA.debugLineNum = 705;BA.debugLine="LogColor($\"Start Time: \"$ & sTimeStart,Colors.Ye";
anywheresoftware.b4a.keywords.Common.LogImpl("769140513",("Start Time: ")+mostCurrent._stimestart,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 };
 //BA.debugLineNum = 710;BA.debugLine="Matcher2 = Regex.Matcher(\"(\\d\\d):(\\d\\d)\", mskTime";
_matcher2 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d)",mostCurrent._msktimefinished.getText());
 //BA.debugLineNum = 712;BA.debugLine="If Matcher2.Find Then";
if (_matcher2.Find()) { 
 //BA.debugLineNum = 713;BA.debugLine="iHrs2 = GlobalVar.SF.Val(Matcher2.Group(1))";
_ihrs2 = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(_matcher2.Group((int) (1))));
 //BA.debugLineNum = 714;BA.debugLine="iMins2 = GlobalVar.SF.Val(Matcher2.Group(2))";
_imins2 = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(_matcher2.Group((int) (2))));
 //BA.debugLineNum = 716;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins2)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_imins2)))==1) { 
 //BA.debugLineNum = 717;BA.debugLine="sMin2 = $\"0\"$ & iMins2";
mostCurrent._smin2 = ("0")+BA.NumberToString(_imins2);
 }else {
 //BA.debugLineNum = 719;BA.debugLine="sMin2 = iMins2";
mostCurrent._smin2 = BA.NumberToString(_imins2);
 };
 //BA.debugLineNum = 722;BA.debugLine="If iHrs2 > 12 Then";
if (_ihrs2>12) { 
 //BA.debugLineNum = 723;BA.debugLine="iHrsFinished = iHrs2 - 12";
mostCurrent._ihrsfinished = BA.NumberToString(_ihrs2-12);
 //BA.debugLineNum = 724;BA.debugLine="sAmPm2 = \"PM\"";
mostCurrent._sampm2 = "PM";
 }else if(_ihrs2==12) { 
 //BA.debugLineNum = 726;BA.debugLine="iHrsFinished = iHrs2";
mostCurrent._ihrsfinished = BA.NumberToString(_ihrs2);
 //BA.debugLineNum = 727;BA.debugLine="sAmPm2 = \"PM\"";
mostCurrent._sampm2 = "PM";
 }else if(_ihrs2<12) { 
 //BA.debugLineNum = 729;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrs2)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_ihrs2)))==1) { 
 //BA.debugLineNum = 730;BA.debugLine="iHrsFinished = $\"0\"$ & iHrs2";
mostCurrent._ihrsfinished = ("0")+BA.NumberToString(_ihrs2);
 }else {
 //BA.debugLineNum = 732;BA.debugLine="iHrsFinished = iHrs2";
mostCurrent._ihrsfinished = BA.NumberToString(_ihrs2);
 };
 //BA.debugLineNum = 734;BA.debugLine="sAmPm2 = \"AM\"";
mostCurrent._sampm2 = "AM";
 };
 //BA.debugLineNum = 737;BA.debugLine="sTimeFinished = iHrsFinished & $\":\"$ & sMin2 & $";
mostCurrent._stimefinished = mostCurrent._ihrsfinished+(":")+mostCurrent._smin2+(" ")+mostCurrent._sampm2;
 //BA.debugLineNum = 738;BA.debugLine="LogColor($\"Finished Time: \"$ & sTimeFinished,Col";
anywheresoftware.b4a.keywords.Common.LogImpl("769140546",("Finished Time: ")+mostCurrent._stimefinished,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 };
 //BA.debugLineNum = 741;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 742;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeaderID";
anywheresoftware.b4a.keywords.Common.LogImpl("769140550",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 744;BA.debugLine="If GlobalVar.blnNewNonOp = True Then";
if (mostCurrent._globalvar._blnnewnonop /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 745;BA.debugLine="If GlobalVar.TranHeaderID > 0 Then";
if (mostCurrent._globalvar._tranheaderid /*int*/ >0) { 
 //BA.debugLineNum = 746;BA.debugLine="If DBaseFunctions.IsNonOperationalTimeOverlappe";
if (mostCurrent._dbasefunctions._isnonoperationaltimeoverlapped /*boolean*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(mostCurrent._msktimestart.getText()+":00"),anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(mostCurrent._msktimefinished.getText()+":00"),mostCurrent._globalvar._tranheaderid /*int*/ ,(int) (0),mostCurrent._globalvar._blnnewnonop /*boolean*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 747;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, $\"Unable to Add";
_requiredmsgbox(("E R R O R"),("Unable to Add New Non-Operational record due to it will Overlap existing records."));
 //BA.debugLineNum = 748;BA.debugLine="mskTimeStart.RequestFocus";
mostCurrent._msktimestart.RequestFocus();
 //BA.debugLineNum = 749;BA.debugLine="Return";
if (true) return "";
 };
 };
 }else {
 //BA.debugLineNum = 753;BA.debugLine="If GlobalVar.TranHeaderID > 0 Then";
if (mostCurrent._globalvar._tranheaderid /*int*/ >0) { 
 //BA.debugLineNum = 754;BA.debugLine="If DBaseFunctions.IsNonOperationalTimeOverlappe";
if (mostCurrent._dbasefunctions._isnonoperationaltimeoverlapped /*boolean*/ (mostCurrent.activityBA,(long)(Double.parseDouble(mostCurrent._stimestart)),(long)(Double.parseDouble(mostCurrent._stimefinished)),mostCurrent._globalvar._tranheaderid /*int*/ ,mostCurrent._globalvar._nonopdetailid /*int*/ ,mostCurrent._globalvar._blnnewnonop /*boolean*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 755;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, $\"Unable to Edit";
_requiredmsgbox(("E R R O R"),("Unable to Edit Non-Operational record due to it will Overlap existing records."));
 //BA.debugLineNum = 756;BA.debugLine="mskTimeStart.RequestFocus";
mostCurrent._msktimestart.RequestFocus();
 //BA.debugLineNum = 757;BA.debugLine="Return";
if (true) return "";
 };
 };
 };
 //BA.debugLineNum = 762;BA.debugLine="ConfirmSaveRecords(GlobalVar.blnNewNonOp)";
_confirmsaverecords(mostCurrent._globalvar._blnnewnonop /*boolean*/ );
 //BA.debugLineNum = 763;BA.debugLine="End Sub";
return "";
}
public static String  _checkpermissions() throws Exception{
 //BA.debugLineNum = 101;BA.debugLine="Private Sub CheckPermissions";
 //BA.debugLineNum = 102;BA.debugLine="Log(\"Checking Permissions\")";
anywheresoftware.b4a.keywords.Common.LogImpl("767567617","Checking Permissions",0);
 //BA.debugLineNum = 104;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE);
 //BA.debugLineNum = 105;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE);
 //BA.debugLineNum = 106;BA.debugLine="Starter.RTP.GetAllSafeDirsExternal(\"\")";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .GetAllSafeDirsExternal("");
 //BA.debugLineNum = 108;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION);
 //BA.debugLineNum = 109;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 110;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return "";
}
public static String  _clearui() throws Exception{
 //BA.debugLineNum = 207;BA.debugLine="Private Sub ClearUI";
 //BA.debugLineNum = 208;BA.debugLine="mskTimeStart.Text = \"__:__\"";
mostCurrent._msktimestart.setText((Object)("__:__"));
 //BA.debugLineNum = 209;BA.debugLine="mskTimeFinished.Text = \"__:__\"";
mostCurrent._msktimefinished.setText((Object)("__:__"));
 //BA.debugLineNum = 210;BA.debugLine="txtReason.Text = \"\"";
mostCurrent._txtreason.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 211;BA.debugLine="txtRemarks.Text = \"\"";
mostCurrent._txtremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 213;BA.debugLine="cdFixedText.Initialize2(Colors.Transparent, 0, 0,";
mostCurrent._cdfixedtext.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 214;BA.debugLine="mskTimeStart.Background = cdFixedText";
mostCurrent._msktimestart.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdfixedtext.getObject()));
 //BA.debugLineNum = 215;BA.debugLine="mskTimeFinished.Background = cdFixedText";
mostCurrent._msktimefinished.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdfixedtext.getObject()));
 //BA.debugLineNum = 216;BA.debugLine="txtReason.Background = cdFixedText";
mostCurrent._txtreason.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdfixedtext.getObject()));
 //BA.debugLineNum = 217;BA.debugLine="txtRemarks.Background = cdFixedText";
mostCurrent._txtremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdfixedtext.getObject()));
 //BA.debugLineNum = 218;BA.debugLine="MyFunctions.SetButton(btnSaveUpdate, 25, 25, 25,";
mostCurrent._myfunctions._setbutton /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btnsaveupdate.getObject())),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25));
 //BA.debugLineNum = 219;BA.debugLine="End Sub";
return "";
}
public static float  _computetothrs(String _t1,String _t2) throws Exception{
float _dretval = 0f;
long _starttime = 0L;
long _endtime = 0L;
b4a.example.dateutils._period _p = null;
 //BA.debugLineNum = 765;BA.debugLine="Private Sub ComputeTotHrs(T1 As String, T2 As Stri";
 //BA.debugLineNum = 766;BA.debugLine="Dim dRetVal As Float";
_dretval = 0f;
 //BA.debugLineNum = 767;BA.debugLine="Dim StartTime, EndTime As Long";
_starttime = 0L;
_endtime = 0L;
 //BA.debugLineNum = 769;BA.debugLine="Try";
try { //BA.debugLineNum = 770;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 771;BA.debugLine="StartTime = DateTime.TimeParse(T1)";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_t1);
 //BA.debugLineNum = 772;BA.debugLine="EndTime = DateTime.TimeParse(T2)";
_endtime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_t2);
 //BA.debugLineNum = 774;BA.debugLine="Dim p As Period = DateUtils.PeriodBetween(StartT";
_p = mostCurrent._dateutils._periodbetween(mostCurrent.activityBA,_starttime,_endtime);
 //BA.debugLineNum = 776;BA.debugLine="dRetVal = p.Hours + (p.Minutes/60)";
_dretval = (float) (_p.Hours+(_p.Minutes/(double)60));
 } 
       catch (Exception e10) {
			processBA.setLastException(e10); //BA.debugLineNum = 778;BA.debugLine="dRetVal = 0";
_dretval = (float) (0);
 //BA.debugLineNum = 779;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("769206030",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 781;BA.debugLine="Return dRetVal";
if (true) return _dretval;
 //BA.debugLineNum = 782;BA.debugLine="End Sub";
return 0f;
}
public static String  _confirmsave_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 607;BA.debugLine="Private Sub ConfirmSave_OnNegativeClicked (View As";
 //BA.debugLineNum = 608;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 609;BA.debugLine="End Sub";
return "";
}
public static String  _confirmsave_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 611;BA.debugLine="Private Sub ConfirmSave_OnPositiveClicked (View As";
 //BA.debugLineNum = 612;BA.debugLine="Alert.Initialize.Dismiss2";
mostCurrent._alert.Initialize().Dismiss2();
 //BA.debugLineNum = 613;BA.debugLine="Select GlobalVar.blnNewNonOp 'New or Edit";
switch (BA.switchObjectToInt(mostCurrent._globalvar._blnnewnonop /*boolean*/ ,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False)) {
case 0: {
 //BA.debugLineNum = 615;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHead";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 616;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeader";
anywheresoftware.b4a.keywords.Common.LogImpl("768943877",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 618;BA.debugLine="If GlobalVar.TranHeaderID = 0 Or Not(DBaseFunct";
if (mostCurrent._globalvar._tranheaderid /*int*/ ==0 || anywheresoftware.b4a.keywords.Common.Not(mostCurrent._dbasefunctions._istransactionheaderexist /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ ))) { 
 //BA.debugLineNum = 619;BA.debugLine="If Not(SaveTransHeader) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_savetransheader())) { 
if (true) return "";};
 //BA.debugLineNum = 620;BA.debugLine="If Not(SaveNewNonOpRec) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_savenewnonoprec())) { 
if (true) return "";};
 }else {
 //BA.debugLineNum = 622;BA.debugLine="If Not(SaveNewNonOpRec) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_savenewnonoprec())) { 
if (true) return "";};
 //BA.debugLineNum = 623;BA.debugLine="If Not(UpdateTranHeader(GlobalVar.TranHeaderID";
if (anywheresoftware.b4a.keywords.Common.Not(_updatetranheader(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
if (true) return "";};
 };
 //BA.debugLineNum = 626;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 640;BA.debugLine="End Sub";
return "";
}
public static String  _confirmsaverecords(boolean _baddedit) throws Exception{
String _stitle = "";
String _scontent = "";
 //BA.debugLineNum = 572;BA.debugLine="Private Sub ConfirmSaveRecords(bAddEdit As Boolean";
 //BA.debugLineNum = 574;BA.debugLine="Dim sTitle, sContent As String";
_stitle = "";
_scontent = "";
 //BA.debugLineNum = 576;BA.debugLine="Select bAddEdit";
switch (BA.switchObjectToInt(_baddedit,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False)) {
case 0: {
 //BA.debugLineNum = 578;BA.debugLine="sTitle = $\"CONFIRM SAVE?\"$";
_stitle = ("CONFIRM SAVE?");
 //BA.debugLineNum = 579;BA.debugLine="sContent = $\"Save the Non-Operational Record?\"$";
_scontent = ("Save the Non-Operational Record?");
 break; }
case 1: {
 //BA.debugLineNum = 581;BA.debugLine="sTitle = $\"CONFIRM UPDATE?\"$";
_stitle = ("CONFIRM UPDATE?");
 //BA.debugLineNum = 582;BA.debugLine="sContent = $\"Modified the Non-Operational Recor";
_scontent = ("Modified the Non-Operational Record?");
 break; }
}
;
 //BA.debugLineNum = 585;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitleTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetMessageTypeface((android.graphics.Typeface)(_font.getObject())).SetMessage(_scontent).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"ConfirmSave").SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetOnNegativeClicked(mostCurrent.activityBA,"ConfirmSave");
 //BA.debugLineNum = 602;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 603;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 604;BA.debugLine="End Sub";
return "";
}
public static boolean  _edittranheader(int _itranheaderid) throws Exception{
 //BA.debugLineNum = 466;BA.debugLine="Private Sub EditTranHeader(iTranHeaderID As Int) A";
 //BA.debugLineNum = 519;BA.debugLine="End Sub";
return false;
}
public static void  _getnonoprecord(int _idetailedid) throws Exception{
ResumableSub_GetNonOpRecord rsub = new ResumableSub_GetNonOpRecord(null,_idetailedid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetNonOpRecord extends BA.ResumableSub {
public ResumableSub_GetNonOpRecord(bwsi.PumpOperations.addeditnonoperational parent,int _idetailedid) {
this.parent = parent;
this._idetailedid = _idetailedid;
}
bwsi.PumpOperations.addeditnonoperational parent;
int _idetailedid;
int _ipowersource = 0;
Object _senderfilter = null;
String _stimeon = "";
String _stimeoff = "";
long _ltimeon = 0L;
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
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
 //BA.debugLineNum = 173;BA.debugLine="Dim iPowerSource As Int";
_ipowersource = 0;
 //BA.debugLineNum = 174;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 175;BA.debugLine="Dim sTimeOn, sTimeOff As String";
_stimeon = "";
_stimeoff = "";
 //BA.debugLineNum = 176;BA.debugLine="Dim lTimeOn As Long";
_ltimeon = 0L;
 //BA.debugLineNum = 177;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 179;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 14;
this.catchState = 13;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 13;
 //BA.debugLineNum = 181;BA.debugLine="Starter.strCriteria = \"SELECT Header.TranDate, \"";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Header.TranDate, "+"Pump.PumpHouseCode, Details.PumpOnTime, Details.PumpOffTime, Details.TotOpHrs, "+"Details.PowerSourceID, Details.DrainTime, Details.DrainCum, Details.TimeOnRemarks "+"FROM OnOffDetails AS Details "+"INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID "+"INNER JOIN tblPumpStation AS Pump ON Pump.StationID = Header.PumpID "+"WHERE Details.DetailID = "+BA.NumberToString(_idetailedid);
 //BA.debugLineNum = 189;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 190;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 15;
return;
case 15:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 192;BA.debugLine="If Success Then";
if (true) break;

case 4:
//if
this.state = 11;
if (_success) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 193;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 194;BA.debugLine="sTimeOn = RS.GetString(\"PumpOnTime\")";
_stimeon = _rs.GetString("PumpOnTime");
 //BA.debugLineNum = 195;BA.debugLine="sTimeOff = RS.GetString(\"PumpOffTime\")";
_stimeoff = _rs.GetString("PumpOffTime");
 //BA.debugLineNum = 197;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 198;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d) (\\S\\S)\"";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d) (\\S\\S)",_stimeon);
 //BA.debugLineNum = 199;BA.debugLine="If Matcher1.Find Then";
if (true) break;

case 7:
//if
this.state = 10;
if (_matcher1.Find()) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 if (true) break;

case 10:
//C
this.state = 11;
;
 if (true) break;

case 11:
//C
this.state = 14;
;
 if (true) break;

case 13:
//C
this.state = 14;
this.catchState = 0;
 //BA.debugLineNum = 203;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("768091935",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 14:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 205;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 34;BA.debugLine="Private Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 36;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private csAns As CSBuilder";
mostCurrent._csans = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 39;BA.debugLine="Private btnSaveUpdate As ACButton";
mostCurrent._btnsaveupdate = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private mskTimeStart As MaskedEditText";
mostCurrent._msktimestart = new flm.b4a.maskededittext.MaskedEditTextWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private mskTimeFinished As MaskedEditText";
mostCurrent._msktimefinished = new flm.b4a.maskededittext.MaskedEditTextWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private txtReason As EditText";
mostCurrent._txtreason = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private txtRemarks As EditText";
mostCurrent._txtremarks = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private imeKeyboard As IME";
mostCurrent._imekeyboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 47;BA.debugLine="Private sTimeStart, sTimeFinished As String";
mostCurrent._stimestart = "";
mostCurrent._stimefinished = "";
 //BA.debugLineNum = 48;BA.debugLine="Private iHrs1, iHrs2, iMins1, iMins2 As Int";
_ihrs1 = 0;
_ihrs2 = 0;
_imins1 = 0;
_imins2 = 0;
 //BA.debugLineNum = 49;BA.debugLine="Private iHrsStart, iHrsFinished As String";
mostCurrent._ihrsstart = "";
mostCurrent._ihrsfinished = "";
 //BA.debugLineNum = 50;BA.debugLine="Private sMin1, sMin2 As String";
mostCurrent._smin1 = "";
mostCurrent._smin2 = "";
 //BA.debugLineNum = 51;BA.debugLine="Private sAmPm1, sAmPm2 As String";
mostCurrent._sampm1 = "";
mostCurrent._sampm2 = "";
 //BA.debugLineNum = 52;BA.debugLine="Private TotNonOpHours As Float";
_totnonophours = 0f;
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static boolean  _isvalidentries() throws Exception{
String _sstart = "";
String _send = "";
long _lstart = 0L;
long _lend = 0L;
 //BA.debugLineNum = 221;BA.debugLine="Private Sub IsValidEntries() As Boolean";
 //BA.debugLineNum = 222;BA.debugLine="Dim sStart, sEnd As String";
_sstart = "";
_send = "";
 //BA.debugLineNum = 223;BA.debugLine="Dim lStart, lEnd As Long";
_lstart = 0L;
_lend = 0L;
 //BA.debugLineNum = 225;BA.debugLine="sStart = mskTimeStart.Text & \":00\"";
_sstart = mostCurrent._msktimestart.getText()+":00";
 //BA.debugLineNum = 226;BA.debugLine="sEnd = mskTimeFinished.Text & \":00\"";
_send = mostCurrent._msktimefinished.getText()+":00";
 //BA.debugLineNum = 228;BA.debugLine="DateTime.TimeFormat = \"HH:mm:ss\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm:ss");
 //BA.debugLineNum = 229;BA.debugLine="lStart = DateTime.TimeParse(sStart)";
_lstart = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_sstart);
 //BA.debugLineNum = 230;BA.debugLine="lEnd = DateTime.TimeParse(sEnd)";
_lend = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_send);
 //BA.debugLineNum = 231;BA.debugLine="Log(lStart & \" \" & lEnd)";
anywheresoftware.b4a.keywords.Common.LogImpl("768222986",BA.NumberToString(_lstart)+" "+BA.NumberToString(_lend),0);
 //BA.debugLineNum = 233;BA.debugLine="LogColor(mskTimeFinished.Text, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("768222988",mostCurrent._msktimefinished.getText(),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 234;BA.debugLine="Try";
try { //BA.debugLineNum = 235;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(mskTimeSta";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._msktimestart.getText()))<=0 || (mostCurrent._msktimestart.getText()).equals("__:__")) { 
 //BA.debugLineNum = 236;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Time Start cannot b";
_requiredmsgbox(("ERROR"),("Time Start cannot be blank!"));
 //BA.debugLineNum = 237;BA.debugLine="mskTimeStart.SelectAll";
mostCurrent._msktimestart.SelectAll();
 //BA.debugLineNum = 238;BA.debugLine="mskTimeStart.RequestFocus";
mostCurrent._msktimestart.RequestFocus();
 //BA.debugLineNum = 239;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 242;BA.debugLine="If Validation.IsTime(mskTimeStart.Text) = False";
if (mostCurrent._validation._istime /*boolean*/ (mostCurrent.activityBA,mostCurrent._msktimestart.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 243;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid Time Start!";
_requiredmsgbox(("ERROR"),("Invalid Time Start!"));
 //BA.debugLineNum = 244;BA.debugLine="mskTimeStart.SelectAll";
mostCurrent._msktimestart.SelectAll();
 //BA.debugLineNum = 245;BA.debugLine="mskTimeStart.RequestFocus";
mostCurrent._msktimestart.RequestFocus();
 //BA.debugLineNum = 246;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 249;BA.debugLine="If DBaseFunctions.IsFuturisticTime(GlobalVar.Tra";
if (mostCurrent._dbasefunctions._isfuturistictime /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._msktimestart.getText())==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 250;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Time Start has not";
_requiredmsgbox(("ERROR"),("Time Start has not yet come!"));
 //BA.debugLineNum = 251;BA.debugLine="mskTimeStart.SelectAll";
mostCurrent._msktimestart.SelectAll();
 //BA.debugLineNum = 252;BA.debugLine="mskTimeStart.RequestFocus";
mostCurrent._msktimestart.RequestFocus();
 //BA.debugLineNum = 253;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 256;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(mskTimeFin";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._msktimefinished.getText()))<=0 || (mostCurrent._msktimefinished.getText()).equals("__:__")) { 
 //BA.debugLineNum = 257;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Time Finshed cannot";
_requiredmsgbox(("ERROR"),("Time Finshed cannot be blank!"));
 //BA.debugLineNum = 258;BA.debugLine="mskTimeFinished.SelectAll";
mostCurrent._msktimefinished.SelectAll();
 //BA.debugLineNum = 259;BA.debugLine="mskTimeFinished.RequestFocus";
mostCurrent._msktimefinished.RequestFocus();
 //BA.debugLineNum = 260;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 263;BA.debugLine="If Validation.IsTime(mskTimeFinished.Text) = Fal";
if (mostCurrent._validation._istime /*boolean*/ (mostCurrent.activityBA,mostCurrent._msktimefinished.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 264;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid Time Finish";
_requiredmsgbox(("ERROR"),("Invalid Time Finished!"));
 //BA.debugLineNum = 265;BA.debugLine="mskTimeFinished.SelectAll";
mostCurrent._msktimefinished.SelectAll();
 //BA.debugLineNum = 266;BA.debugLine="mskTimeFinished.RequestFocus";
mostCurrent._msktimefinished.RequestFocus();
 //BA.debugLineNum = 267;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 270;BA.debugLine="If DBaseFunctions.IsFuturisticTime(GlobalVar.Tra";
if (mostCurrent._dbasefunctions._isfuturistictime /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._msktimefinished.getText())==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 271;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Time Finished has n";
_requiredmsgbox(("ERROR"),("Time Finished has not yet come!"));
 //BA.debugLineNum = 272;BA.debugLine="mskTimeFinished.SelectAll";
mostCurrent._msktimefinished.SelectAll();
 //BA.debugLineNum = 273;BA.debugLine="mskTimeFinished.RequestFocus";
mostCurrent._msktimefinished.RequestFocus();
 //BA.debugLineNum = 274;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 277;BA.debugLine="If lStart > lEnd Then";
if (_lstart>_lend) { 
 //BA.debugLineNum = 278;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Time Finished is ea";
_requiredmsgbox(("ERROR"),("Time Finished is earlier than Time Started!"));
 //BA.debugLineNum = 279;BA.debugLine="mskTimeFinished.SelectAll";
mostCurrent._msktimefinished.SelectAll();
 //BA.debugLineNum = 280;BA.debugLine="mskTimeFinished.RequestFocus";
mostCurrent._msktimefinished.RequestFocus();
 //BA.debugLineNum = 281;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 284;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtReason.";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtreason.getText()))<=0) { 
 //BA.debugLineNum = 285;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Non Operational Rea";
_requiredmsgbox(("ERROR"),("Non Operational Reason cannot be blank!"));
 //BA.debugLineNum = 286;BA.debugLine="txtReason.RequestFocus";
mostCurrent._txtreason.RequestFocus();
 //BA.debugLineNum = 287;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e59) {
			processBA.setLastException(e59); //BA.debugLineNum = 291;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("768223046",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 292;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 294;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 295;BA.debugLine="End Sub";
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
public static String  _reqmsg_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 552;BA.debugLine="Private Sub ReqMsg_OnBindView (View As View, ViewT";
 //BA.debugLineNum = 553;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 554;BA.debugLine="Alert.Initialize";
mostCurrent._alert.Initialize();
 //BA.debugLineNum = 555;BA.debugLine="If ViewType = Alert.VIEW_TITLE Then ' Title";
if (_viewtype==mostCurrent._alert.VIEW_TITLE) { 
 //BA.debugLineNum = 556;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 557;BA.debugLine="lbl.TextSize = 30";
_lbl.setTextSize((float) (30));
 //BA.debugLineNum = 558;BA.debugLine="lbl.SetTextColorAnimated(2000,Colors.Magenta)";
_lbl.SetTextColorAnimated((int) (2000),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 561;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 565;BA.debugLine="CS.Initialize.Typeface(Typeface.MATERIALICONS).S";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe88e)))+"  "));
 //BA.debugLineNum = 566;BA.debugLine="CS.Typeface(Font).Size(24).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(_font.getObject())).Size((int) (24)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 568;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 570;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsg_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 547;BA.debugLine="Private Sub RequiredMsg_OnPositiveClicked (View As";
 //BA.debugLineNum = 548;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 549;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 550;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsgbox(String _stitle,String _smsg) throws Exception{
 //BA.debugLineNum = 522;BA.debugLine="Private Sub RequiredMsgBox(sTitle As String, sMsg";
 //BA.debugLineNum = 523;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 524;BA.debugLine="Alert.Initialize.Dismiss2";
mostCurrent._alert.Initialize().Dismiss2();
 //BA.debugLineNum = 526;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._redcolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessage(_smsg).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessageTypeface((android.graphics.Typeface)(_font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"RequiredMsg").SetOnViewBinder(mostCurrent.activityBA,"ReqMsg");
 //BA.debugLineNum = 541;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 542;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 543;BA.debugLine="End Sub";
return "";
}
public static boolean  _savenewnonoprec() throws Exception{
boolean _bretval = false;
String _sdatetimeadded = "";
long _ldate = 0L;
String _sreason = "";
String _sremarks = "";
String _slocation = "";
 //BA.debugLineNum = 325;BA.debugLine="Private Sub SaveNewNonOpRec() As Boolean";
 //BA.debugLineNum = 326;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 328;BA.debugLine="Dim sDateTimeAdded As String";
_sdatetimeadded = "";
 //BA.debugLineNum = 329;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 330;BA.debugLine="Dim sReason, sRemarks, sLocation As String";
_sreason = "";
_sremarks = "";
_slocation = "";
 //BA.debugLineNum = 332;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 333;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 334;BA.debugLine="sDateTimeAdded = DateTime.Date(lDate)";
_sdatetimeadded = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 336;BA.debugLine="TotNonOpHours = ComputeTotHrs(sTimeStart, sTimeFi";
_totnonophours = _computetothrs(mostCurrent._stimestart,mostCurrent._stimefinished);
 //BA.debugLineNum = 338;BA.debugLine="sReason = txtReason.Text";
_sreason = mostCurrent._txtreason.getText();
 //BA.debugLineNum = 339;BA.debugLine="sRemarks = txtRemarks.Text";
_sremarks = mostCurrent._txtremarks.getText();
 //BA.debugLineNum = 341;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 343;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("768354066",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 345;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("768354068",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 346;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("768354069",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 348;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 349;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("768354072",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 351;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 352;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeaderID";
anywheresoftware.b4a.keywords.Common.LogImpl("768354075",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 354;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 355;BA.debugLine="Try";
try { //BA.debugLineNum = 356;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO NonOpDe";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO NonOpDetails VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._tranheaderid /*int*/ ),(Object)(mostCurrent._stimestart),(Object)(mostCurrent._stimefinished),(Object)(_totnonophours),(Object)(_sreason),(Object)(_sremarks),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_sdatetimeadded),(Object)(_slocation),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(("")),(Object)(("0")),(Object)(("")),(Object)((""))}));
 //BA.debugLineNum = 358;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 359;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e25) {
			processBA.setLastException(e25); //BA.debugLineNum = 361;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("768354084",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 362;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 364;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 365;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 366;BA.debugLine="End Sub";
return false;
}
public static boolean  _savetransheader() throws Exception{
boolean _bretval = false;
long _lngdatetime = 0L;
String _saddedat = "";
float _ftotnonophrs = 0f;
 //BA.debugLineNum = 297;BA.debugLine="Private Sub SaveTransHeader() As Boolean";
 //BA.debugLineNum = 298;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 299;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 300;BA.debugLine="Dim sAddedAt As String";
_saddedat = "";
 //BA.debugLineNum = 301;BA.debugLine="Dim fTotNonOpHrs As Float";
_ftotnonophrs = 0f;
 //BA.debugLineNum = 303;BA.debugLine="fTotNonOpHrs = ComputeTotHrs(sTimeStart, sTimeFin";
_ftotnonophrs = _computetothrs(mostCurrent._stimestart,mostCurrent._stimefinished);
 //BA.debugLineNum = 305;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 306;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 307;BA.debugLine="sAddedAt= DateTime.Date(lngDateTime)";
_saddedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 309;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 310;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 311;BA.debugLine="Try";
try { //BA.debugLineNum = 313;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO TranHea";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO TranHeader VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._branchid /*int*/ ),(Object)(mostCurrent._globalvar._pumphouseid /*int*/ ),(Object)(mostCurrent._globalvar._trandate /*String*/ ),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(_ftotnonophrs),(Object)(("0")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_saddedat),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(("0")),(Object)(("")),(Object)((""))}));
 //BA.debugLineNum = 315;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 316;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e16) {
			processBA.setLastException(e16); //BA.debugLineNum = 318;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("768288533",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 319;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 321;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 322;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 323;BA.debugLine="End Sub";
return false;
}
public static String  _showsavesuccess() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
anywheresoftware.b4a.objects.CSBuilder _cscontent = null;
 //BA.debugLineNum = 642;BA.debugLine="Private Sub ShowSaveSuccess()";
 //BA.debugLineNum = 643;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 644;BA.debugLine="Dim csContent As CSBuilder";
_cscontent = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 646;BA.debugLine="If GlobalVar.blnNewNonOp = True Then";
if (mostCurrent._globalvar._blnnewnonop /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 647;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 648;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("New pump time has been successfully saved!"))).PopAll();
 }else {
 //BA.debugLineNum = 650;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 651;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("Pump time has been successfully updated!"))).PopAll();
 };
 //BA.debugLineNum = 654;BA.debugLine="MatDialogBuilder.Initialize(\"Success\")";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"Success");
 //BA.debugLineNum = 655;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 656;BA.debugLine="MatDialogBuilder.Content(csContent)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_cscontent.getObject()));
 //BA.debugLineNum = 657;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 658;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 659;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 660;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 661;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 662;BA.debugLine="End Sub";
return "";
}
public static String  _success_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 664;BA.debugLine="Private Sub Success_ButtonPressed(mDialog As Mater";
 //BA.debugLineNum = 665;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE)) {
case 0: {
 //BA.debugLineNum = 667;BA.debugLine="imeKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 668;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
}
;
 //BA.debugLineNum = 670;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 167;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 163;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 164;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 165;BA.debugLine="End Sub";
return "";
}
public static boolean  _updatenonoprecord(int _itranheaderid,int _idetailid) throws Exception{
boolean _bretval = false;
String _sdatetimemodified = "";
long _ldate = 0L;
String _sreason = "";
String _sremarks = "";
String _slocation = "";
 //BA.debugLineNum = 368;BA.debugLine="Private Sub UpdateNonOPRecord(iTranHeaderID As Int";
 //BA.debugLineNum = 369;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 371;BA.debugLine="Dim sDateTimeModified As String";
_sdatetimemodified = "";
 //BA.debugLineNum = 372;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 373;BA.debugLine="Dim sReason, sRemarks, sLocation As String";
_sreason = "";
_sremarks = "";
_slocation = "";
 //BA.debugLineNum = 375;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 376;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 377;BA.debugLine="sDateTimeModified = DateTime.Date(lDate)";
_sdatetimemodified = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 379;BA.debugLine="TotNonOpHours = ComputeTotHrs(sTimeStart, sTimeFi";
_totnonophours = _computetothrs(mostCurrent._stimestart,mostCurrent._stimefinished);
 //BA.debugLineNum = 381;BA.debugLine="sReason = txtReason.Text";
_sreason = mostCurrent._txtreason.getText();
 //BA.debugLineNum = 382;BA.debugLine="sRemarks = txtRemarks.Text";
_sremarks = mostCurrent._txtremarks.getText();
 //BA.debugLineNum = 384;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 386;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("768419602",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 388;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("768419604",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 389;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("768419605",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 391;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 392;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("768419608",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 394;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 395;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeaderID";
anywheresoftware.b4a.keywords.Common.LogImpl("768419611",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 397;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 398;BA.debugLine="Try";
try { //BA.debugLineNum = 399;BA.debugLine="Starter.strCriteria = \"UPDATE NonOpDetails SET \"";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE NonOpDetails SET "+"OffTime = ?, "+"OnTime = ?, "+"TotNonOpHrs = ?, "+"NonOpReason = ?, "+"Remarks = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ?, "+"ModifiedOn = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid)+" "+"AND DetailID = "+BA.NumberToString(_idetailid);
 //BA.debugLineNum = 411;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{mostCurrent._stimestart,mostCurrent._stimefinished,BA.NumberToString(_totnonophours),_sreason,_sremarks,BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_sdatetimemodified,_slocation}));
 //BA.debugLineNum = 412;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 413;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e26) {
			processBA.setLastException(e26); //BA.debugLineNum = 415;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("768419631",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 416;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 418;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 419;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 420;BA.debugLine="End Sub";
return false;
}
public static boolean  _updatetranheader(int _itranheaderid) throws Exception{
boolean _bretval = false;
float _gtotnonophrs = 0f;
long _lngdatetime = 0L;
String _smodifiedat = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsheader = null;
 //BA.debugLineNum = 422;BA.debugLine="Private Sub UpdateTranHeader(iTranHeaderID As Int)";
 //BA.debugLineNum = 423;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 424;BA.debugLine="Dim GTotNonOpHrs As Float";
_gtotnonophrs = 0f;
 //BA.debugLineNum = 426;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 427;BA.debugLine="Dim sModifiedAt As String";
_smodifiedat = "";
 //BA.debugLineNum = 429;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 430;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 431;BA.debugLine="sModifiedAt = DateTime.Date(lngDateTime)";
_smodifiedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 433;BA.debugLine="Dim rsHeader As Cursor";
_rsheader = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 435;BA.debugLine="Starter.strCriteria = \"SELECT * FROM TranHeader W";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM TranHeader WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 436;BA.debugLine="rsHeader = Starter.DBCon.ExecQuery(Starter.strCri";
_rsheader = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 437;BA.debugLine="If rsHeader.RowCount > 0 Then";
if (_rsheader.getRowCount()>0) { 
 //BA.debugLineNum = 438;BA.debugLine="rsHeader.Position = 0";
_rsheader.setPosition((int) (0));
 //BA.debugLineNum = 439;BA.debugLine="GTotNonOpHrs = rsHeader.GetDouble(\"TotNonOpHours";
_gtotnonophrs = (float) (_rsheader.GetDouble("TotNonOpHours")+_totnonophours);
 }else {
 //BA.debugLineNum = 441;BA.debugLine="GTotNonOpHrs = TotNonOpHours";
_gtotnonophrs = _totnonophours;
 };
 //BA.debugLineNum = 443;BA.debugLine="rsHeader.Close";
_rsheader.Close();
 //BA.debugLineNum = 444;BA.debugLine="LogColor($\"Total Op Hrs: \"$ & GTotNonOpHrs, Color";
anywheresoftware.b4a.keywords.Common.LogImpl("768485142",("Total Op Hrs: ")+BA.NumberToString(_gtotnonophrs),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 446;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 447;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 448;BA.debugLine="Try";
try { //BA.debugLineNum = 449;BA.debugLine="Starter.strCriteria = \"UPDATE TranHeader SET \" &";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE TranHeader SET "+"TotNonOpHours = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 455;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_gtotnonophrs),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_smodifiedat}));
 //BA.debugLineNum = 456;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 457;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e27) {
			processBA.setLastException(e27); //BA.debugLineNum = 459;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("768485157",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 460;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 462;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 463;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 464;BA.debugLine="End Sub";
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
