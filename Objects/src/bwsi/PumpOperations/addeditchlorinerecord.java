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

public class addeditchlorinerecord extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static addeditchlorinerecord mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.addeditchlorinerecord");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (addeditchlorinerecord).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.addeditchlorinerecord");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.addeditchlorinerecord", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (addeditchlorinerecord) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (addeditchlorinerecord) Resume **");
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
		return addeditchlorinerecord.class;
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
            BA.LogInfo("** Activity (addeditchlorinerecord) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (addeditchlorinerecord) Pause event (activity is not paused). **");
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
            addeditchlorinerecord mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (addeditchlorinerecord) Resume **");
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
public de.amberhome.materialdialogs.MaterialDialogBuilderWrapper _matdialogbuilder = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdtxtbox = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fontbold = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnsaveupdate = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _cbotype = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblbrand = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcode = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblmodel = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblserial = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblspm = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblunit = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtremarks = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtspmpercent = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtspmrate = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtvolume = null;
public static int _spmrate = 0;
public anywheresoftware.b4a.objects.IME _kboard = null;
public static String _schlotype = "";
public static String _suom = "";
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _chkdefaulttimereplenish = null;
public static String _sreptime = "";
public flm.b4a.maskededittext.MaskedEditTextWrapper _msktimereplenish = null;
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
 //BA.debugLineNum = 61;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 62;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 63;BA.debugLine="Activity.LoadLayout(\"ChlorinatorRecords\")";
mostCurrent._activity.LoadLayout("ChlorinatorRecords",mostCurrent.activityBA);
 //BA.debugLineNum = 65;BA.debugLine="GetChlorinatorData(GlobalVar.PumpHouseID)";
_getchlorinatordata(mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 67;BA.debugLine="InpTyp.Initialize";
_inptyp._initialize /*String*/ (processBA);
 //BA.debugLineNum = 68;BA.debugLine="InpTyp.SetInputType(txtRemarks,Array As Int(InpTy";
_inptyp._setinputtype /*String*/ (mostCurrent._txtremarks,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_correct /*int*/ (),_inptyp._type_text_flag_cap_sentences /*int*/ ()});
 //BA.debugLineNum = 70;BA.debugLine="If GlobalVar.blnNewChlorine = True Then";
if (mostCurrent._globalvar._blnnewchlorine /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 71;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Appen";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("ADD NEW CHLORINE RECORD"))).PopAll();
 //BA.debugLineNum = 72;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append(";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 //BA.debugLineNum = 73;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\" SAVE\"$";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" SAVE")));
 //BA.debugLineNum = 74;BA.debugLine="FillChlorineType";
_fillchlorinetype();
 //BA.debugLineNum = 75;BA.debugLine="ClearUI";
_clearui();
 }else {
 //BA.debugLineNum = 77;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Appen";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("EDIT CHLORINE RECORD"))).PopAll();
 //BA.debugLineNum = 78;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append(";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 //BA.debugLineNum = 79;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\" UPDATE\"$";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" UPDATE")));
 //BA.debugLineNum = 80;BA.debugLine="FillChlorineType";
_fillchlorinetype();
 //BA.debugLineNum = 81;BA.debugLine="ClearUI";
_clearui();
 //BA.debugLineNum = 82;BA.debugLine="GetChlorineDetails(GlobalVar.ChlorineDetailID)";
_getchlorinedetails(mostCurrent._globalvar._chlorinedetailid /*int*/ );
 };
 //BA.debugLineNum = 85;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 86;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 87;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 89;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 90;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 91;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 92;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 93;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 94;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 96;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 97;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 99;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 };
 //BA.debugLineNum = 101;BA.debugLine="kBoard.Initialize(\"\")";
mostCurrent._kboard.Initialize("");
 //BA.debugLineNum = 102;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 103;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
 //BA.debugLineNum = 161;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 163;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 105;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 106;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 107;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 108;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 110;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 112;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 155;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 126;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 127;BA.debugLine="If Result Then";
if (_result) { 
 //BA.debugLineNum = 128;BA.debugLine="If Permission = Starter.RTP.PERMISSION_READ_EXTE";
if ((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 129;BA.debugLine="LogColor($\"Permission to Read External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("761014019",("Permission to Read External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 130;BA.debugLine="GlobalVar.ReadStoragePermission = True";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 132;BA.debugLine="LogColor($\"Permission to Write External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("761014022",("Permission to Write External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 133;BA.debugLine="GlobalVar.WriteStoragePermission = True";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION)) { 
 //BA.debugLineNum = 135;BA.debugLine="LogColor($\"Permission to Access Coarse Location";
anywheresoftware.b4a.keywords.Common.LogImpl("761014025",("Permission to Access Coarse Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 136;BA.debugLine="GlobalVar.CoarseLocPermission = True";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION)) { 
 //BA.debugLineNum = 138;BA.debugLine="LogColor($\"Permission to Access Fine Location G";
anywheresoftware.b4a.keywords.Common.LogImpl("761014028",("Permission to Access Fine Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 139;BA.debugLine="GlobalVar.FineLocPermission = True";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 141;BA.debugLine="Starter.StartFLP";
mostCurrent._starter._startflp /*void*/ ();
 }else {
 //BA.debugLineNum = 143;BA.debugLine="GlobalVar.ReadStoragePermission = False";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 144;BA.debugLine="GlobalVar.WriteStoragePermission = False";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 145;BA.debugLine="GlobalVar.CoarseLocPermission = False";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 146;BA.debugLine="GlobalVar.FineLocPermission = False";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 147;BA.debugLine="Result = False";
_result = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 149;BA.debugLine="Log (Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("761014039",_permission,0);
 //BA.debugLineNum = 150;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 152;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 153;BA.debugLine="End Sub";
return "";
}
public static String  _addupdatechlorinerec_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 818;BA.debugLine="Private Sub AddUpdateChlorineRec_ButtonPressed(mDi";
 //BA.debugLineNum = 819;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE)) {
case 0: {
 //BA.debugLineNum = 821;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 824;BA.debugLine="End Sub";
return "";
}
public static String  _btnsaveupdate_click() throws Exception{
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
String _smin = "";
String _shr = "";
int _ihrs = 0;
int _imins = 0;
 //BA.debugLineNum = 200;BA.debugLine="Sub btnSaveUpdate_Click";
 //BA.debugLineNum = 201;BA.debugLine="If Not(IsValidEntries) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_isvalidentries())) { 
if (true) return "";};
 //BA.debugLineNum = 203;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 204;BA.debugLine="Dim sMin, sHr As String";
_smin = "";
_shr = "";
 //BA.debugLineNum = 205;BA.debugLine="sRepTime = \"\"";
mostCurrent._sreptime = "";
 //BA.debugLineNum = 207;BA.debugLine="If Not(IsValidEntries) Then Return 'Check Entries";
if (anywheresoftware.b4a.keywords.Common.Not(_isvalidentries())) { 
if (true) return "";};
 //BA.debugLineNum = 209;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d)\", mskTime";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d)",mostCurrent._msktimereplenish.getText());
 //BA.debugLineNum = 211;BA.debugLine="If Matcher1.Find Then 'Split";
if (_matcher1.Find()) { 
 //BA.debugLineNum = 212;BA.debugLine="Dim iHrs, iMins As Int";
_ihrs = 0;
_imins = 0;
 //BA.debugLineNum = 214;BA.debugLine="iHrs = Matcher1.Group(1)";
_ihrs = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 215;BA.debugLine="iMins = Matcher1.Group(2)";
_imins = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 217;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_imins)))==1) { 
 //BA.debugLineNum = 218;BA.debugLine="sMin = $\"0\"$ & iMins";
_smin = ("0")+BA.NumberToString(_imins);
 }else {
 //BA.debugLineNum = 220;BA.debugLine="sMin = iMins";
_smin = BA.NumberToString(_imins);
 };
 //BA.debugLineNum = 223;BA.debugLine="If iHrs = 0 Then '12 AM";
if (_ihrs==0) { 
 //BA.debugLineNum = 224;BA.debugLine="sHr = 12";
_shr = BA.NumberToString(12);
 //BA.debugLineNum = 225;BA.debugLine="sRepTime = sHr & \":\" & sMin & \" AM\"";
mostCurrent._sreptime = _shr+":"+_smin+" AM";
 }else if(_ihrs>0 && _ihrs<12) { 
 //BA.debugLineNum = 227;BA.debugLine="sHr = iHrs";
_shr = BA.NumberToString(_ihrs);
 //BA.debugLineNum = 228;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr)) = 1";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_shr))==1) { 
 //BA.debugLineNum = 229;BA.debugLine="sRepTime = $\"0\"$ & sHr & \":\" & sMin & \" AM\"";
mostCurrent._sreptime = ("0")+_shr+":"+_smin+" AM";
 }else {
 //BA.debugLineNum = 231;BA.debugLine="sRepTime = sHr & \":\" & sMin & \" AM\"";
mostCurrent._sreptime = _shr+":"+_smin+" AM";
 };
 }else if(_ihrs==12) { 
 //BA.debugLineNum = 235;BA.debugLine="sHr = 12";
_shr = BA.NumberToString(12);
 //BA.debugLineNum = 236;BA.debugLine="sRepTime = sHr & \":\" & sMin & \" PM\"";
mostCurrent._sreptime = _shr+":"+_smin+" PM";
 }else {
 //BA.debugLineNum = 238;BA.debugLine="sHr = iHrs - 12";
_shr = BA.NumberToString(_ihrs-12);
 //BA.debugLineNum = 239;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr)) = 1";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_shr))==1) { 
 //BA.debugLineNum = 240;BA.debugLine="sRepTime = $\"0\"$ & sHr & \":\" & sMin & \" PM\"";
mostCurrent._sreptime = ("0")+_shr+":"+_smin+" PM";
 }else {
 //BA.debugLineNum = 242;BA.debugLine="sRepTime = sHr & \":\" & sMin & \" PM\"";
mostCurrent._sreptime = _shr+":"+_smin+" PM";
 };
 };
 };
 //BA.debugLineNum = 247;BA.debugLine="LogColor($\"Reading Time: \"$ & sRepTime,Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("761472815",("Reading Time: ")+mostCurrent._sreptime,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 249;BA.debugLine="ConfirmSaveUpdateChlorine";
_confirmsaveupdatechlorine();
 //BA.debugLineNum = 251;BA.debugLine="End Sub";
return "";
}
public static String  _cbotype_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 286;BA.debugLine="Sub cboType_ItemClick (Position As Int, Value As O";
 //BA.debugLineNum = 287;BA.debugLine="LogColor($\"Selected \"$ & Position & \" - \" & Value";
anywheresoftware.b4a.keywords.Common.LogImpl("761603841",("Selected ")+BA.NumberToString(_position)+" - "+BA.ObjectToString(_value),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 288;BA.debugLine="sUOM = GetUOM(Value)";
mostCurrent._suom = _getuom(BA.ObjectToString(_value));
 //BA.debugLineNum = 289;BA.debugLine="lblUnit.Text = sUOM";
mostCurrent._lblunit.setText(BA.ObjectToCharSequence(mostCurrent._suom));
 //BA.debugLineNum = 290;BA.debugLine="End Sub";
return "";
}
public static String  _checkpermissions() throws Exception{
 //BA.debugLineNum = 114;BA.debugLine="Private Sub CheckPermissions";
 //BA.debugLineNum = 115;BA.debugLine="Log(\"Checking Permissions\")";
anywheresoftware.b4a.keywords.Common.LogImpl("760948481","Checking Permissions",0);
 //BA.debugLineNum = 117;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE);
 //BA.debugLineNum = 118;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE);
 //BA.debugLineNum = 119;BA.debugLine="Starter.RTP.GetAllSafeDirsExternal(\"\")";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .GetAllSafeDirsExternal("");
 //BA.debugLineNum = 121;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION);
 //BA.debugLineNum = 122;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 123;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 124;BA.debugLine="End Sub";
return "";
}
public static String  _chkdefaulttimereplenish_checkedchange(boolean _checked) throws Exception{
String _shour = "";
String _smin = "";
long _lhour = 0L;
long _lmin = 0L;
 //BA.debugLineNum = 253;BA.debugLine="Sub chkDefaultTimeReplenish_CheckedChange(Checked";
 //BA.debugLineNum = 254;BA.debugLine="Dim sHour As String";
_shour = "";
 //BA.debugLineNum = 255;BA.debugLine="Dim sMin As String";
_smin = "";
 //BA.debugLineNum = 256;BA.debugLine="Dim lHour, lMin As Long";
_lhour = 0L;
_lmin = 0L;
 //BA.debugLineNum = 258;BA.debugLine="If Checked = True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 259;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 260;BA.debugLine="lHour = DateTime.GetHour(DateTime.Now)";
_lhour = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 261;BA.debugLine="lMin = DateTime.GetMinute(DateTime.Now)";
_lmin = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 263;BA.debugLine="If GlobalVar.SF.Len(lHour) = 1 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_lhour))==1) { 
 //BA.debugLineNum = 264;BA.debugLine="sHour = $\"0\"$ & lHour";
_shour = ("0")+BA.NumberToString(_lhour);
 }else {
 //BA.debugLineNum = 266;BA.debugLine="sHour = lHour";
_shour = BA.NumberToString(_lhour);
 };
 //BA.debugLineNum = 269;BA.debugLine="If GlobalVar.SF.Len(lMin) = 1 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_lmin))==1) { 
 //BA.debugLineNum = 270;BA.debugLine="sMin = $\"0\"$ & lMin";
_smin = ("0")+BA.NumberToString(_lmin);
 }else {
 //BA.debugLineNum = 272;BA.debugLine="sMin = lMin";
_smin = BA.NumberToString(_lmin);
 };
 //BA.debugLineNum = 275;BA.debugLine="mskTimeReplenish.Text = sHour & \":\" & sMin";
mostCurrent._msktimereplenish.setText((Object)(_shour+":"+_smin));
 //BA.debugLineNum = 276;BA.debugLine="mskTimeReplenish.Enabled = False";
mostCurrent._msktimereplenish.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 278;BA.debugLine="mskTimeReplenish.Enabled = True";
mostCurrent._msktimereplenish.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 279;BA.debugLine="mskTimeReplenish.Text = \"__:__\"";
mostCurrent._msktimereplenish.setText((Object)("__:__"));
 //BA.debugLineNum = 280;BA.debugLine="mskTimeReplenish.RequestFocus";
mostCurrent._msktimereplenish.RequestFocus();
 //BA.debugLineNum = 281;BA.debugLine="mskTimeReplenish.SelectionStart = 0";
mostCurrent._msktimereplenish.setSelectionStart((int) (0));
 //BA.debugLineNum = 282;BA.debugLine="kBoard.ShowKeyboard(mskTimeReplenish)";
mostCurrent._kboard.ShowKeyboard((android.view.View)(mostCurrent._msktimereplenish.getObject()));
 };
 //BA.debugLineNum = 284;BA.debugLine="End Sub";
return "";
}
public static String  _clearui() throws Exception{
 //BA.debugLineNum = 176;BA.debugLine="Private Sub ClearUI";
 //BA.debugLineNum = 177;BA.debugLine="lblCode.Text = \"\"";
mostCurrent._lblcode.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 178;BA.debugLine="lblBrand.Text = \"\"";
mostCurrent._lblbrand.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 179;BA.debugLine="lblModel.Text = \"\"";
mostCurrent._lblmodel.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 180;BA.debugLine="lblSerial.Text = \"\"";
mostCurrent._lblserial.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 181;BA.debugLine="lblSPM.Text = \"\"";
mostCurrent._lblspm.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 182;BA.debugLine="chkDefaultTimeReplenish.Checked = False";
mostCurrent._chkdefaulttimereplenish.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 183;BA.debugLine="mskTimeReplenish.Text = \"__:__\"";
mostCurrent._msktimereplenish.setText((Object)("__:__"));
 //BA.debugLineNum = 185;BA.debugLine="txtVolume.Text = \"\"";
mostCurrent._txtvolume.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 186;BA.debugLine="txtSPMRate.Text = \"\"";
mostCurrent._txtspmrate.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 187;BA.debugLine="txtSPMPercent.Text = 0";
mostCurrent._txtspmpercent.setText(BA.ObjectToCharSequence(0));
 //BA.debugLineNum = 189;BA.debugLine="CDtxtBox.Initialize(Colors.Transparent,0)";
mostCurrent._cdtxtbox.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 190;BA.debugLine="cboType.Background = CDtxtBox";
mostCurrent._cbotype.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 191;BA.debugLine="txtVolume.Background = CDtxtBox";
mostCurrent._txtvolume.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 192;BA.debugLine="txtSPMRate.Background = CDtxtBox";
mostCurrent._txtspmrate.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 193;BA.debugLine="txtSPMPercent.Background = CDtxtBox";
mostCurrent._txtspmpercent.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 194;BA.debugLine="txtRemarks.Background = CDtxtBox";
mostCurrent._txtremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 195;BA.debugLine="mskTimeReplenish.Background = CDtxtBox";
mostCurrent._msktimereplenish.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 197;BA.debugLine="MyFunctions.SetButton(btnSaveUpdate, 25, 25, 25,";
mostCurrent._myfunctions._setbutton /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btnsaveupdate.getObject())),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25));
 //BA.debugLineNum = 198;BA.debugLine="End Sub";
return "";
}
public static String  _confirmsaveupdatechlorine() throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
String _stitle = "";
String _smsg = "";
 //BA.debugLineNum = 711;BA.debugLine="Private Sub ConfirmSaveUpdateChlorine";
 //BA.debugLineNum = 712;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 713;BA.debugLine="Dim sTitle, sMsg As String";
_stitle = "";
_smsg = "";
 //BA.debugLineNum = 715;BA.debugLine="If GlobalVar.blnNewChlorine = True Then";
if (mostCurrent._globalvar._blnnewchlorine /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 716;BA.debugLine="sTitle = $\"SAVE NEW CHLORINE RECORDS\"$";
_stitle = ("SAVE NEW CHLORINE RECORDS");
 //BA.debugLineNum = 717;BA.debugLine="sMsg = $\"Save New Chlorine Record?\"$";
_smsg = ("Save New Chlorine Record?");
 }else {
 //BA.debugLineNum = 719;BA.debugLine="sTitle = $\"UPDATE CHLORINE RECORDS\"$";
_stitle = ("UPDATE CHLORINE RECORDS");
 //BA.debugLineNum = 720;BA.debugLine="sMsg = $\"Update Chlorine Record?\"$";
_smsg = ("Update Chlorine Record?");
 };
 //BA.debugLineNum = 723;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._bluecolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage(_smsg).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"SaveUpdateChlorine").SetOnNegativeClicked(mostCurrent.activityBA,"SaveUpdateChlorine");
 //BA.debugLineNum = 740;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 741;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 742;BA.debugLine="End Sub";
return "";
}
public static void  _fillchlorinetype() throws Exception{
ResumableSub_FillChlorineType rsub = new ResumableSub_FillChlorineType(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_FillChlorineType extends BA.ResumableSub {
public ResumableSub_FillChlorineType(bwsi.PumpOperations.addeditchlorinerecord parent) {
this.parent = parent;
}
bwsi.PumpOperations.addeditchlorinerecord parent;
Object _senderfilter = null;
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
 //BA.debugLineNum = 341;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 342;BA.debugLine="cboType.Clear";
parent.mostCurrent._cbotype.Clear();
 //BA.debugLineNum = 343;BA.debugLine="Try";
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
 //BA.debugLineNum = 344;BA.debugLine="Starter.strCriteria = \"SELECT Chlorine.ChlorineT";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Chlorine.ChlorineType FROM cons_chlorine AS Chlorine";
 //BA.debugLineNum = 346;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 347;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 17;
return;
case 17:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 349;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 350;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 351;BA.debugLine="cboType.Add(GlobalVar.SF.Upper(RS.GetString(\"C";
parent.mostCurrent._cbotype.Add(BA.ObjectToCharSequence(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("ChlorineType"))));
 if (true) break;

case 10:
//C
this.state = 13;
;
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 354;BA.debugLine="ToastMessageShow($\"Cannot get Chlorine Type due";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Cannot get Chlorine Type due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 355;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("761800463",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 13:
//C
this.state = 16;
;
 //BA.debugLineNum = 357;BA.debugLine="lblUnit.Text = GetUOM(cboType.SelectedItem)";
parent.mostCurrent._lblunit.setText(BA.ObjectToCharSequence(_getuom(parent.mostCurrent._cbotype.getSelectedItem())));
 if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
 //BA.debugLineNum = 360;BA.debugLine="ToastMessageShow($\"Cannot get Chlorine Type due";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Cannot get Chlorine Type due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 361;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("761800469",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 363;BA.debugLine="End Sub";
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
public static String  _fontsizebinder_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 695;BA.debugLine="Private Sub FontSizeBinder_OnBindView (View As Vie";
 //BA.debugLineNum = 696;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 697;BA.debugLine="Alert.Initialize";
_alert.Initialize();
 //BA.debugLineNum = 698;BA.debugLine="If ViewType = Alert.VIEW_TITLE Then ' Title";
if (_viewtype==_alert.VIEW_TITLE) { 
 //BA.debugLineNum = 699;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 700;BA.debugLine="lbl.TextSize = 30";
_lbl.setTextSize((float) (30));
 //BA.debugLineNum = 701;BA.debugLine="lbl.SetTextColorAnimated(2000,Colors.Magenta)";
_lbl.SetTextColorAnimated((int) (2000),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 703;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 704;BA.debugLine="CS.Initialize.Typeface(Typeface.MATERIALICONS).S";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe88e)))+"  "));
 //BA.debugLineNum = 705;BA.debugLine="CS.Typeface(Font).Size(24).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(mostCurrent._font.getObject())).Size((int) (24)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 707;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 709;BA.debugLine="End Sub";
return "";
}
public static void  _getchlorinatordata(int _ipumpid) throws Exception{
ResumableSub_GetChlorinatorData rsub = new ResumableSub_GetChlorinatorData(null,_ipumpid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetChlorinatorData extends BA.ResumableSub {
public ResumableSub_GetChlorinatorData(bwsi.PumpOperations.addeditchlorinerecord parent,int _ipumpid) {
this.parent = parent;
this._ipumpid = _ipumpid;
}
bwsi.PumpOperations.addeditchlorinerecord parent;
int _ipumpid;
Object _senderfilter = null;
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
 //BA.debugLineNum = 304;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 305;BA.debugLine="Try";
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
 //BA.debugLineNum = 306;BA.debugLine="Starter.strCriteria = \"SELECT Chlorinator.code A";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Chlorinator.code AS ChloCode, Chlorinator.brand_name AS BrandName, "+"Chlorinator.model_no AS ModelNo, Chlorinator.serial_no AS SerialNo, Chlorinator.stroke_per_minute AS SPM "+"FROM tblChlorinator AS Chlorinator "+"INNER JOIN tblPumpStation AS Station ON Chlorinator.id = Station.ChlorinatorID "+"WHERE Station.StationID = 1 = "+BA.NumberToString(_ipumpid);
 //BA.debugLineNum = 312;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 313;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 13;
return;
case 13:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 315;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 316;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 317;BA.debugLine="lblCode.Text = GlobalVar.SF.Upper(RS.GetString(";
parent.mostCurrent._lblcode.setText(BA.ObjectToCharSequence(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("ChloCode"))));
 //BA.debugLineNum = 318;BA.debugLine="lblBrand.Text = GlobalVar.SF.Upper(RS.GetString";
parent.mostCurrent._lblbrand.setText(BA.ObjectToCharSequence(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("BrandName"))));
 //BA.debugLineNum = 319;BA.debugLine="lblModel.Text = GlobalVar.SF.Upper(RS.GetString";
parent.mostCurrent._lblmodel.setText(BA.ObjectToCharSequence(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("ModelNo"))));
 //BA.debugLineNum = 320;BA.debugLine="lblSerial.Text = GlobalVar.SF.Upper(RS.GetStrin";
parent.mostCurrent._lblserial.setText(BA.ObjectToCharSequence(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("SerialNo"))));
 //BA.debugLineNum = 321;BA.debugLine="SPMRate = RS.GetString(\"SPM\")";
parent._spmrate = (int)(Double.parseDouble(_rs.GetString("SPM")));
 //BA.debugLineNum = 322;BA.debugLine="lblSPM.Text = SPMRate & \" SPM\"";
parent.mostCurrent._lblspm.setText(BA.ObjectToCharSequence(BA.NumberToString(parent._spmrate)+" SPM"));
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 324;BA.debugLine="lblCode.Text = \"\"";
parent.mostCurrent._lblcode.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 325;BA.debugLine="lblBrand.Text = \"\"";
parent.mostCurrent._lblbrand.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 326;BA.debugLine="lblModel.Text = \"\"";
parent.mostCurrent._lblmodel.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 327;BA.debugLine="lblSerial.Text = \"\"";
parent.mostCurrent._lblserial.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 328;BA.debugLine="lblSPM.Text = 0";
parent.mostCurrent._lblspm.setText(BA.ObjectToCharSequence(0));
 //BA.debugLineNum = 329;BA.debugLine="SPMRate = 0";
parent._spmrate = (int) (0);
 //BA.debugLineNum = 330;BA.debugLine="ToastMessageShow($\"Cannot get Chlorinator Data";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Cannot get Chlorinator Data due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 331;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("761734940",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
 //BA.debugLineNum = 335;BA.debugLine="ToastMessageShow($\"Cannot get Chlorinator Data d";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Cannot get Chlorinator Data due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 336;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("761734945",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 12:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 338;BA.debugLine="End Sub";
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
public static void  _getchlorinedetails(int _idetailid) throws Exception{
ResumableSub_GetChlorineDetails rsub = new ResumableSub_GetChlorineDetails(null,_idetailid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetChlorineDetails extends BA.ResumableSub {
public ResumableSub_GetChlorineDetails(bwsi.PumpOperations.addeditchlorinerecord parent,int _idetailid) {
this.parent = parent;
this._idetailid = _idetailid;
}
bwsi.PumpOperations.addeditchlorinerecord parent;
int _idetailid;
String _stimerep = "";
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
int _ihrs = 0;
int _imins = 0;
String _ampm = "";
String _smin = "";

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
 //BA.debugLineNum = 379;BA.debugLine="Dim sTimeRep As String";
_stimerep = "";
 //BA.debugLineNum = 380;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 382;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 45;
this.catchState = 44;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 44;
 //BA.debugLineNum = 383;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 385;BA.debugLine="Starter.strCriteria = \"SELECT TimeReplenished, C";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT TimeReplenished, ChlorineType, Volume, SPMRate, Remarks "+"FROM ChlorineDetails "+"WHERE DetailID = "+BA.NumberToString(_idetailid);
 //BA.debugLineNum = 389;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 390;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 46;
return;
case 46:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 392;BA.debugLine="If Success Then";
if (true) break;

case 4:
//if
this.state = 42;
if (_success) { 
this.state = 6;
}else {
this.state = 41;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 393;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 394;BA.debugLine="sTimeRep = RS.GetString(\"TimeReplenished\")";
_stimerep = _rs.GetString("TimeReplenished");
 //BA.debugLineNum = 395;BA.debugLine="sChloType = RS.GetString(\"ChlorineType\")";
parent.mostCurrent._schlotype = _rs.GetString("ChlorineType");
 //BA.debugLineNum = 396;BA.debugLine="cboType.SelectedIndex = cboType.IndexOf(sChloTy";
parent.mostCurrent._cbotype.setSelectedIndex(parent.mostCurrent._cbotype.IndexOf(BA.ObjectToCharSequence(parent.mostCurrent._schlotype)));
 //BA.debugLineNum = 397;BA.debugLine="txtVolume.Text = RS.GetInt(\"Volume\")";
parent.mostCurrent._txtvolume.setText(BA.ObjectToCharSequence(_rs.GetInt("Volume")));
 //BA.debugLineNum = 398;BA.debugLine="txtSPMRate.Text = RS.GetInt(\"SPMRate\")";
parent.mostCurrent._txtspmrate.setText(BA.ObjectToCharSequence(_rs.GetInt("SPMRate")));
 //BA.debugLineNum = 399;BA.debugLine="txtRemarks.Text = RS.GetString(\"Remarks\")";
parent.mostCurrent._txtremarks.setText(BA.ObjectToCharSequence(_rs.GetString("Remarks")));
 //BA.debugLineNum = 400;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 401;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d) (\\S\\S)\"";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d) (\\S\\S)",_stimerep);
 //BA.debugLineNum = 402;BA.debugLine="If Matcher1.Find Then";
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
 //BA.debugLineNum = 403;BA.debugLine="Dim iHrs, iMins As Int";
_ihrs = 0;
_imins = 0;
 //BA.debugLineNum = 404;BA.debugLine="Dim AmPm As String";
_ampm = "";
 //BA.debugLineNum = 405;BA.debugLine="Dim sMin As String";
_smin = "";
 //BA.debugLineNum = 407;BA.debugLine="iHrs = Matcher1.Group(1)";
_ihrs = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 408;BA.debugLine="iMins = Matcher1.Group(2)";
_imins = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 409;BA.debugLine="AmPm = Matcher1.Group(3)";
_ampm = _matcher1.Group((int) (3));
 //BA.debugLineNum = 411;BA.debugLine="LogColor(AmPm,Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("761931553",_ampm,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 413;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins))";
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
 //BA.debugLineNum = 414;BA.debugLine="sMin = $\"0\"$ & iMins";
_smin = ("0")+BA.NumberToString(_imins);
 if (true) break;

case 14:
//C
this.state = 15;
 //BA.debugLineNum = 416;BA.debugLine="sMin = iMins";
_smin = BA.NumberToString(_imins);
 if (true) break;
;
 //BA.debugLineNum = 419;BA.debugLine="If AmPm = \"AM\" Then";

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
 //BA.debugLineNum = 420;BA.debugLine="If iHrs = 12 Then";
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
 //BA.debugLineNum = 421;BA.debugLine="mskTimeReplenish.Text = $\"00:\"$ & sMin";
parent.mostCurrent._msktimereplenish.setText((Object)(("00:")+_smin));
 if (true) break;

case 22:
//C
this.state = 23;
 //BA.debugLineNum = 423;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrs))";
if (true) break;

case 23:
//if
this.state = 28;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_ihrs)))==1) { 
this.state = 25;
}else {
this.state = 27;
}if (true) break;

case 25:
//C
this.state = 28;
 //BA.debugLineNum = 424;BA.debugLine="mskTimeReplenish.Text = $\"0\"$ & iHrs & $\":\"";
parent.mostCurrent._msktimereplenish.setText((Object)(("0")+BA.NumberToString(_ihrs)+(":")+_smin));
 if (true) break;

case 27:
//C
this.state = 28;
 //BA.debugLineNum = 426;BA.debugLine="mskTimeReplenish.Text = iHrs & $\":\"$ & sMin";
parent.mostCurrent._msktimereplenish.setText((Object)(BA.NumberToString(_ihrs)+(":")+_smin));
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
 //BA.debugLineNum = 430;BA.debugLine="If iHrs < 12 Then";
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
 //BA.debugLineNum = 431;BA.debugLine="mskTimeReplenish.Text = (iHrs + 12) & $\":\"$";
parent.mostCurrent._msktimereplenish.setText((Object)(BA.NumberToString((_ihrs+12))+(":")+_smin));
 if (true) break;

case 36:
//C
this.state = 37;
 //BA.debugLineNum = 433;BA.debugLine="mskTimeReplenish.Text = iHrs & $\":\"$ & sMin";
parent.mostCurrent._msktimereplenish.setText((Object)(BA.NumberToString(_ihrs)+(":")+_smin));
 if (true) break;

case 37:
//C
this.state = 38;
;
 if (true) break;

case 38:
//C
this.state = 39;
;
 if (true) break;

case 39:
//C
this.state = 42;
;
 if (true) break;

case 41:
//C
this.state = 42;
 //BA.debugLineNum = 438;BA.debugLine="sChloType = \"\"";
parent.mostCurrent._schlotype = "";
 //BA.debugLineNum = 439;BA.debugLine="cboType.SelectedIndex = 0";
parent.mostCurrent._cbotype.setSelectedIndex((int) (0));
 //BA.debugLineNum = 440;BA.debugLine="txtVolume.Text = \"0\"";
parent.mostCurrent._txtvolume.setText(BA.ObjectToCharSequence("0"));
 //BA.debugLineNum = 441;BA.debugLine="txtSPMRate.Text = \"0\"";
parent.mostCurrent._txtspmrate.setText(BA.ObjectToCharSequence("0"));
 //BA.debugLineNum = 442;BA.debugLine="txtRemarks.Text = \"\"";
parent.mostCurrent._txtremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 443;BA.debugLine="ToastMessageShow($\"Unable to fetch chlorine rec";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch chlorine record due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 42:
//C
this.state = 45;
;
 if (true) break;

case 44:
//C
this.state = 45;
this.catchState = 0;
 //BA.debugLineNum = 446;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("761931588",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 45:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 448;BA.debugLine="End Sub";
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
public static String  _getuom(String _svalue) throws Exception{
String _sretval = "";
 //BA.debugLineNum = 365;BA.debugLine="Private Sub GetUOM (sValue As String) As String";
 //BA.debugLineNum = 366;BA.debugLine="Dim sRetval As String";
_sretval = "";
 //BA.debugLineNum = 367;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 368;BA.debugLine="Try";
try { //BA.debugLineNum = 369;BA.debugLine="Starter.strCriteria = \"SELECT UOM FROM cons_chlo";
mostCurrent._starter._strcriteria /*String*/  = "SELECT UOM FROM cons_chlorine WHERE UPPER(ChlorineType) = '"+_svalue+"'";
 //BA.debugLineNum = 370;BA.debugLine="sRetval = Starter.DBCon.ExecQuerySingleResult(St";
_sretval = mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ );
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 372;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 373;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("761865992",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 375;BA.debugLine="Return sRetval";
if (true) return _sretval;
 //BA.debugLineNum = 376;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 25;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 26;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private CDtxtBox As ColorDrawable";
mostCurrent._cdtxtbox = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 32;BA.debugLine="Private Font As Typeface = Typeface.LoadFromAsset";
mostCurrent._font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont.ttf")));
 //BA.debugLineNum = 33;BA.debugLine="Private FontBold As Typeface = Typeface.LoadFromA";
mostCurrent._fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont_bold.ttf")));
 //BA.debugLineNum = 35;BA.debugLine="Private btnSaveUpdate As ACButton";
mostCurrent._btnsaveupdate = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private cboType As ACSpinner";
mostCurrent._cbotype = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private lblBrand As Label";
mostCurrent._lblbrand = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private lblCode As Label";
mostCurrent._lblcode = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private lblModel As Label";
mostCurrent._lblmodel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private lblSerial As Label";
mostCurrent._lblserial = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private lblSPM As Label";
mostCurrent._lblspm = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private lblUnit As Label";
mostCurrent._lblunit = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private txtRemarks As EditText";
mostCurrent._txtremarks = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private txtSPMPercent As EditText";
mostCurrent._txtspmpercent = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private txtSPMRate As EditText";
mostCurrent._txtspmrate = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private txtVolume As EditText";
mostCurrent._txtvolume = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private SPMRate = 0 As Int";
_spmrate = (int) (0);
 //BA.debugLineNum = 50;BA.debugLine="Private kBoard As IME";
mostCurrent._kboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 51;BA.debugLine="Private sChloType As String";
mostCurrent._schlotype = "";
 //BA.debugLineNum = 52;BA.debugLine="Private sUOM As String";
mostCurrent._suom = "";
 //BA.debugLineNum = 53;BA.debugLine="Private chkDefaultTimeReplenish As CheckBox";
mostCurrent._chkdefaulttimereplenish = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private sRepTime As String";
mostCurrent._sreptime = "";
 //BA.debugLineNum = 56;BA.debugLine="Private mskTimeReplenish As MaskedEditText";
mostCurrent._msktimereplenish = new flm.b4a.maskededittext.MaskedEditTextWrapper();
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public static boolean  _isvalidentries() throws Exception{
 //BA.debugLineNum = 450;BA.debugLine="Private Sub IsValidEntries () As Boolean";
 //BA.debugLineNum = 451;BA.debugLine="Try";
try { //BA.debugLineNum = 452;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(mskTimeRep";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._msktimereplenish.getText()))<=0 || (mostCurrent._msktimereplenish.getText()).equals("__:__")) { 
 //BA.debugLineNum = 453;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Replenish Time cann";
_requiredmsgbox(("ERROR"),("Replenish Time cannot be blank!"));
 //BA.debugLineNum = 454;BA.debugLine="mskTimeReplenish.RequestFocus";
mostCurrent._msktimereplenish.RequestFocus();
 //BA.debugLineNum = 455;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else if(mostCurrent._validation._istime /*boolean*/ (mostCurrent.activityBA,mostCurrent._msktimereplenish.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 458;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid Replenish T";
_requiredmsgbox(("ERROR"),("Invalid Replenish Time!"));
 //BA.debugLineNum = 459;BA.debugLine="mskTimeReplenish.RequestFocus";
mostCurrent._msktimereplenish.RequestFocus();
 //BA.debugLineNum = 460;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 463;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(cboType.Se";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._cbotype.getSelectedItem()))<=0) { 
 //BA.debugLineNum = 464;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Chlorine Type canno";
_requiredmsgbox(("ERROR"),("Chlorine Type cannot be blank!"));
 //BA.debugLineNum = 465;BA.debugLine="cboType.RequestFocus";
mostCurrent._cbotype.RequestFocus();
 //BA.debugLineNum = 466;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 469;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtVolume.";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtvolume.getText()))<=0) { 
 //BA.debugLineNum = 470;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Chlorine Volume can";
_requiredmsgbox(("ERROR"),("Chlorine Volume cannot be blank!"));
 //BA.debugLineNum = 471;BA.debugLine="txtVolume.RequestFocus";
mostCurrent._txtvolume.RequestFocus();
 //BA.debugLineNum = 472;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 475;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtSPMRate";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtspmrate.getText()))<=0) { 
 //BA.debugLineNum = 476;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Stroke Per Minute c";
_requiredmsgbox(("ERROR"),("Stroke Per Minute cannot be blank!"));
 //BA.debugLineNum = 477;BA.debugLine="txtSPMRate.RequestFocus";
mostCurrent._txtspmrate.RequestFocus();
 //BA.debugLineNum = 478;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 480;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e28) {
			processBA.setLastException(e28); //BA.debugLineNum = 482;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 483;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("761997089",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 485;BA.debugLine="End Sub";
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
public static String  _requiredmsg_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 690;BA.debugLine="Private Sub RequiredMsg_OnPositiveClicked (View As";
 //BA.debugLineNum = 691;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 692;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 693;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsgbox(String _stitle,String _smsg) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 666;BA.debugLine="Private Sub RequiredMsgBox(sTitle As String, sMsg";
 //BA.debugLineNum = 667;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 668;BA.debugLine="Alert.Initialize.Dismiss2";
_alert.Initialize().Dismiss2();
 //BA.debugLineNum = 670;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._redcolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage(_smsg).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"RequiredMsg").SetOnViewBinder(mostCurrent.activityBA,"FontSizeBinder");
 //BA.debugLineNum = 685;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 686;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 687;BA.debugLine="End Sub";
return "";
}
public static boolean  _savechlorinedetails() throws Exception{
boolean _bretval = false;
String _timeread = "";
int _ivol = 0;
int _istroke = 0;
long _lpercentage = 0L;
String _schlorinetype = "";
String _sremarks = "";
String _slocation = "";
long _ldate = 0L;
String _sdatetimeadded = "";
 //BA.debugLineNum = 490;BA.debugLine="Private Sub SaveChlorineDetails() As Boolean";
 //BA.debugLineNum = 491;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 492;BA.debugLine="Dim TimeRead As String";
_timeread = "";
 //BA.debugLineNum = 493;BA.debugLine="Dim iVol, iStroke As Int";
_ivol = 0;
_istroke = 0;
 //BA.debugLineNum = 494;BA.debugLine="Dim lPercentage As Long";
_lpercentage = 0L;
 //BA.debugLineNum = 495;BA.debugLine="Dim sChlorineType, sRemarks, sLocation As String";
_schlorinetype = "";
_sremarks = "";
_slocation = "";
 //BA.debugLineNum = 497;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 498;BA.debugLine="Dim sDateTimeAdded As String";
_sdatetimeadded = "";
 //BA.debugLineNum = 500;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 501;BA.debugLine="sChlorineType = cboType.SelectedItem";
_schlorinetype = mostCurrent._cbotype.getSelectedItem();
 //BA.debugLineNum = 502;BA.debugLine="TimeRead = sRepTime";
_timeread = mostCurrent._sreptime;
 //BA.debugLineNum = 503;BA.debugLine="iVol = GlobalVar.SF.Val(txtVolume.Text)";
_ivol = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtvolume.getText()));
 //BA.debugLineNum = 504;BA.debugLine="sUOM = lblUnit.Text";
mostCurrent._suom = mostCurrent._lblunit.getText();
 //BA.debugLineNum = 505;BA.debugLine="iStroke = GlobalVar.SF.Val(txtSPMRate.Text)";
_istroke = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtspmrate.getText()));
 //BA.debugLineNum = 506;BA.debugLine="lPercentage = GlobalVar.SF.Val(txtSPMPercent.Text";
_lpercentage = (long) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtspmpercent.getText()));
 //BA.debugLineNum = 507;BA.debugLine="sRemarks = txtRemarks.Text";
_sremarks = mostCurrent._txtremarks.getText();
 //BA.debugLineNum = 509;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 510;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 511;BA.debugLine="sDateTimeAdded = DateTime.Date(lDate)";
_sdatetimeadded = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 513;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 514;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("762062616",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 516;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("762062618",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 517;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("762062619",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 519;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 520;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("762062622",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 522;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 523;BA.debugLine="Try";
try { //BA.debugLineNum = 524;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO Chlorin";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO ChlorineDetails VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._tranheaderid /*int*/ ),(Object)(_timeread),(Object)(_schlorinetype),(Object)(_ivol),(Object)(mostCurrent._suom),(Object)(_istroke),(Object)(_lpercentage),(Object)(_sremarks),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_sdatetimeadded),(Object)(_slocation),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(("")),(Object)(("0")),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null}));
 //BA.debugLineNum = 526;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 527;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e31) {
			processBA.setLastException(e31); //BA.debugLineNum = 529;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("762062631",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 530;BA.debugLine="ToastMessageShow($\"Unable to save Chlorine Recor";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to save Chlorine Records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 531;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 533;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 534;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 535;BA.debugLine="End Sub";
return false;
}
public static boolean  _savetransheader() throws Exception{
boolean _bretval = false;
long _lngdatetime = 0L;
String _saddedat = "";
 //BA.debugLineNum = 598;BA.debugLine="Private Sub SaveTransHeader() As Boolean";
 //BA.debugLineNum = 599;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 600;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 601;BA.debugLine="Dim sAddedAt As String";
_saddedat = "";
 //BA.debugLineNum = 603;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 604;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 605;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 606;BA.debugLine="sAddedAt= DateTime.Date(lngDateTime)";
_saddedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 609;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 610;BA.debugLine="Try";
try { //BA.debugLineNum = 612;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO TranHea";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO TranHeader VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._branchid /*int*/ ),(Object)(mostCurrent._globalvar._pumphouseid /*int*/ ),(Object)(mostCurrent._globalvar._trandate /*String*/ ),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("1")),(Object)(("0")),(Object)(("0")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_saddedat),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(("0")),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null}));
 //BA.debugLineNum = 615;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 616;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e14) {
			processBA.setLastException(e14); //BA.debugLineNum = 618;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("762193684",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 619;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 621;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 622;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 623;BA.debugLine="End Sub";
return false;
}
public static String  _saveupdatechlorine_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 745;BA.debugLine="Private Sub SaveUpdateChlorine_OnNegativeClicked (";
 //BA.debugLineNum = 746;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 747;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 748;BA.debugLine="End Sub";
return "";
}
public static String  _saveupdatechlorine_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 750;BA.debugLine="Private Sub SaveUpdateChlorine_OnPositiveClicked (";
 //BA.debugLineNum = 751;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 752;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 753;BA.debugLine="If GlobalVar.blnNewChlorine = True Then";
if (mostCurrent._globalvar._blnnewchlorine /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 754;BA.debugLine="If DBaseFunctions.IsTransactionHeaderExist(Globa";
if (mostCurrent._dbasefunctions._istransactionheaderexist /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 755;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHead";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 756;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeader";
anywheresoftware.b4a.keywords.Common.LogImpl("762652422",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 758;BA.debugLine="If Not(SaveChlorineDetails) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_savechlorinedetails())) { 
 //BA.debugLineNum = 759;BA.debugLine="ToastMessageShow($\"Unable to Save Chlorinator";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Chlorinator Records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 760;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 763;BA.debugLine="If Not(UpdateTranHeader(GlobalVar.TranHeaderID)";
if (anywheresoftware.b4a.keywords.Common.Not(_updatetranheader(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
 //BA.debugLineNum = 764;BA.debugLine="ToastMessageShow($\"Unable to Save Chlorinator";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Chlorinator Records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 765;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 768;BA.debugLine="If Not(SaveTransHeader) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_savetransheader())) { 
 //BA.debugLineNum = 769;BA.debugLine="ToastMessageShow($\"Unable to Save Chlorinator";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Chlorinator Records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 770;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 772;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHead";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 774;BA.debugLine="If Not(SaveChlorineDetails) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_savechlorinedetails())) { 
 //BA.debugLineNum = 775;BA.debugLine="ToastMessageShow($\"Unable to Save Chlorinator";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Chlorinator Records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 776;BA.debugLine="Return";
if (true) return "";
 };
 };
 }else {
 //BA.debugLineNum = 781;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeade";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 782;BA.debugLine="If Not(UpdateChlorineRecord(GlobalVar.ChlorineDe";
if (anywheresoftware.b4a.keywords.Common.Not(_updatechlorinerecord(mostCurrent._globalvar._chlorinedetailid /*int*/ ,mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
 //BA.debugLineNum = 783;BA.debugLine="ToastMessageShow($\"Unable to Save Chlorinator R";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Chlorinator Records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 784;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 786;BA.debugLine="If Not(UpdateTranHeader(GlobalVar.TranHeaderID))";
if (anywheresoftware.b4a.keywords.Common.Not(_updatetranheader(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
 //BA.debugLineNum = 787;BA.debugLine="ToastMessageShow($\"Unable to Save Chlorinator R";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Chlorinator Records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 788;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 792;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 //BA.debugLineNum = 793;BA.debugLine="End Sub";
return "";
}
public static String  _showsavesuccess() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
anywheresoftware.b4a.objects.CSBuilder _cscontent = null;
 //BA.debugLineNum = 796;BA.debugLine="Private Sub ShowSaveSuccess()";
 //BA.debugLineNum = 797;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 798;BA.debugLine="Dim csContent As CSBuilder";
_cscontent = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 800;BA.debugLine="If GlobalVar.blnNewChlorine = True Then";
if (mostCurrent._globalvar._blnnewchlorine /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 801;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 802;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("New Chlorinator Record has been successfully saved!"))).PopAll();
 }else {
 //BA.debugLineNum = 804;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 805;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("Chlorinator Record has been successfully updated!"))).PopAll();
 };
 //BA.debugLineNum = 808;BA.debugLine="MatDialogBuilder.Initialize(\"AddUpdateChlorineRec";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"AddUpdateChlorineRec");
 //BA.debugLineNum = 809;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 810;BA.debugLine="MatDialogBuilder.Content(csContent)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_cscontent.getObject()));
 //BA.debugLineNum = 811;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 812;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 813;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 814;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 815;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 816;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 171;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 166;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arroww";
 //BA.debugLineNum = 167;BA.debugLine="kBoard.HideKeyboard";
mostCurrent._kboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 168;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 169;BA.debugLine="End Sub";
return "";
}
public static String  _txtspmrate_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 292;BA.debugLine="Sub txtSPMRate_TextChanged (Old As String, New As";
 //BA.debugLineNum = 293;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtSPMRate.";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtspmrate.getText()))<=0 || _spmrate==0) { 
 //BA.debugLineNum = 294;BA.debugLine="txtSPMPercent.Text = 0";
mostCurrent._txtspmpercent.setText(BA.ObjectToCharSequence(0));
 }else {
 //BA.debugLineNum = 296;BA.debugLine="txtSPMPercent.Text = Round2(((GlobalVar.SF.Val(t";
mostCurrent._txtspmpercent.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Round2(((mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtspmrate.getText())/(double)_spmrate)*100),(int) (2))));
 };
 //BA.debugLineNum = 298;BA.debugLine="End Sub";
return "";
}
public static boolean  _updatechlorinerecord(int _idetailid,int _itranheaderid) throws Exception{
boolean _bretval = false;
String _timeread = "";
int _ivol = 0;
int _istroke = 0;
long _lpercentage = 0L;
String _schlorinetype = "";
String _sremarks = "";
String _slocation = "";
long _ldate = 0L;
String _sdatetimeadded = "";
 //BA.debugLineNum = 538;BA.debugLine="Private Sub UpdateChlorineRecord(iDetailID As Int,";
 //BA.debugLineNum = 539;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 540;BA.debugLine="Dim TimeRead As String";
_timeread = "";
 //BA.debugLineNum = 541;BA.debugLine="Dim iVol, iStroke As Int";
_ivol = 0;
_istroke = 0;
 //BA.debugLineNum = 542;BA.debugLine="Dim lPercentage As Long";
_lpercentage = 0L;
 //BA.debugLineNum = 543;BA.debugLine="Dim sChlorineType, sRemarks, sLocation As String";
_schlorinetype = "";
_sremarks = "";
_slocation = "";
 //BA.debugLineNum = 545;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 546;BA.debugLine="Dim sDateTimeAdded As String";
_sdatetimeadded = "";
 //BA.debugLineNum = 548;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 549;BA.debugLine="sChlorineType = cboType.SelectedItem";
_schlorinetype = mostCurrent._cbotype.getSelectedItem();
 //BA.debugLineNum = 550;BA.debugLine="TimeRead = sRepTime";
_timeread = mostCurrent._sreptime;
 //BA.debugLineNum = 551;BA.debugLine="iVol = GlobalVar.SF.Val(txtVolume.Text)";
_ivol = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtvolume.getText()));
 //BA.debugLineNum = 552;BA.debugLine="sUOM = lblUnit.Text";
mostCurrent._suom = mostCurrent._lblunit.getText();
 //BA.debugLineNum = 553;BA.debugLine="iStroke = GlobalVar.SF.Val(txtSPMRate.Text)";
_istroke = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtspmrate.getText()));
 //BA.debugLineNum = 554;BA.debugLine="lPercentage = GlobalVar.SF.Val(txtSPMPercent.Text";
_lpercentage = (long) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtspmpercent.getText()));
 //BA.debugLineNum = 555;BA.debugLine="sRemarks = txtRemarks.Text";
_sremarks = mostCurrent._txtremarks.getText();
 //BA.debugLineNum = 557;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 558;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 559;BA.debugLine="sDateTimeAdded = DateTime.Date(lDate)";
_sdatetimeadded = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 561;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 562;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("762128152",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 564;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("762128154",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 565;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("762128155",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 567;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 568;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("762128158",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 570;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 571;BA.debugLine="Try";
try { //BA.debugLineNum = 572;BA.debugLine="Starter.strCriteria = \"UPDATE ChlorineDetails SE";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE ChlorineDetails SET "+"ChlorineType = ?, "+"Volume = ?, "+"UoM = ?, "+"SPMRate = ?, "+"SPMPercent = ?, "+"Remarks = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ?, "+"ModifiedOn = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid)+" "+"AND DetailID = "+BA.NumberToString(_idetailid);
 //BA.debugLineNum = 584;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("762128174",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 586;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{_schlorinetype,BA.NumberToString(_ivol),mostCurrent._suom,BA.NumberToString(_istroke),BA.NumberToString(_lpercentage),_sremarks,BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_sdatetimeadded,_slocation}));
 //BA.debugLineNum = 587;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 588;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e33) {
			processBA.setLastException(e33); //BA.debugLineNum = 590;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("762128180",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 591;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 593;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 594;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 595;BA.debugLine="End Sub";
return false;
}
public static boolean  _updatetranheader(int _itranheaderid) throws Exception{
boolean _bretval = false;
double _gminpsi = 0;
double _gmaxpsi = 0;
double _gavepsi = 0;
long _lngdatetime = 0L;
String _smodifiedat = "";
 //BA.debugLineNum = 626;BA.debugLine="Private Sub UpdateTranHeader(iTranHeaderID As Int)";
 //BA.debugLineNum = 627;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 628;BA.debugLine="Dim GMinPSI, GMaxPSI, GAvePSI As Double";
_gminpsi = 0;
_gmaxpsi = 0;
_gavepsi = 0;
 //BA.debugLineNum = 629;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 630;BA.debugLine="Dim sModifiedAt As String";
_smodifiedat = "";
 //BA.debugLineNum = 632;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 634;BA.debugLine="GMinPSI = DBaseFunctions.GetMinPSI(iTranHeaderID)";
_gminpsi = mostCurrent._dbasefunctions._getminpsi /*double*/ (mostCurrent.activityBA,_itranheaderid);
 //BA.debugLineNum = 635;BA.debugLine="GMaxPSI = DBaseFunctions.GetMaxPSI(iTranHeaderID)";
_gmaxpsi = mostCurrent._dbasefunctions._getmaxpsi /*double*/ (mostCurrent.activityBA,_itranheaderid);
 //BA.debugLineNum = 636;BA.debugLine="GAvePSI = DBaseFunctions.GetAvePSI(iTranHeaderID)";
_gavepsi = mostCurrent._dbasefunctions._getavepsi /*double*/ (mostCurrent.activityBA,_itranheaderid);
 //BA.debugLineNum = 638;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 639;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 640;BA.debugLine="sModifiedAt = DateTime.Date(lngDateTime)";
_smodifiedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 642;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 643;BA.debugLine="Try";
try { //BA.debugLineNum = 644;BA.debugLine="Starter.strCriteria = \"UPDATE TranHeader SET \" &";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE TranHeader SET "+"MinPSI = ?, "+"MaxPSI = ?, "+"AvePSI = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 652;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_gminpsi),BA.NumberToString(_gmaxpsi),BA.NumberToString(_gavepsi),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_smodifiedat}));
 //BA.debugLineNum = 653;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 654;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e19) {
			processBA.setLastException(e19); //BA.debugLineNum = 656;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("762259230",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 657;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 659;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 660;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 661;BA.debugLine="End Sub";
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
