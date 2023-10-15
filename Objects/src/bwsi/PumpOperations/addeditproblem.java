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

public class addeditproblem extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static addeditproblem mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.addeditproblem");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (addeditproblem).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.addeditproblem");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.addeditproblem", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (addeditproblem) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (addeditproblem) Resume **");
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
		return addeditproblem.class;
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
            BA.LogInfo("** Activity (addeditproblem) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (addeditproblem) Pause event (activity is not paused). **");
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
            addeditproblem mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (addeditproblem) Resume **");
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
public static String _stimestarted = "";
public static String _stimefinished = "";
public flm.b4a.maskededittext.MaskedEditTextWrapper _msktimefrom = null;
public flm.b4a.maskededittext.MaskedEditTextWrapper _msktimeto = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _cbopumparea = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _chkcritical = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtprobtitle = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtprobdetails = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnsaveupdate = null;
public anywheresoftware.b4a.objects.IME _kboard = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scvmain = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmain = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _chkwassolved = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtactiontaken = null;
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
public bwsi.PumpOperations.actnonoperational _actnonoperational = null;
public bwsi.PumpOperations.actproduction _actproduction = null;
public bwsi.PumpOperations.actrcjofindings _actrcjofindings = null;
public bwsi.PumpOperations.actrepmain _actrepmain = null;
public bwsi.PumpOperations.actsasjofindings _actsasjofindings = null;
public bwsi.PumpOperations.actwaterbalance _actwaterbalance = null;
public bwsi.PumpOperations.addeditchlorinerecord _addeditchlorinerecord = null;
public bwsi.PumpOperations.addeditfmrdg _addeditfmrdg = null;
public bwsi.PumpOperations.addeditnonoperational _addeditnonoperational = null;
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
 //BA.debugLineNum = 58;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 59;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 60;BA.debugLine="Activity.LoadLayout(\"ProblemEncMain\")";
mostCurrent._activity.LoadLayout("ProblemEncMain",mostCurrent.activityBA);
 //BA.debugLineNum = 61;BA.debugLine="scvMain.Panel.LoadLayout(\"ProblemRecords\")";
mostCurrent._scvmain.getPanel().LoadLayout("ProblemRecords",mostCurrent.activityBA);
 //BA.debugLineNum = 62;BA.debugLine="scvMain.Panel.Height = pnlMain.Height";
mostCurrent._scvmain.getPanel().setHeight(mostCurrent._pnlmain.getHeight());
 //BA.debugLineNum = 64;BA.debugLine="If GlobalVar.blnNewProblem = True Then";
if (mostCurrent._globalvar._blnnewproblem /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 65;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Appen";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("ADD NEW PROBLEM(S) ENCOUNTERED"))).PopAll();
 //BA.debugLineNum = 66;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append(";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 //BA.debugLineNum = 67;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\" SAVE\"$";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" SAVE")));
 //BA.debugLineNum = 68;BA.debugLine="FillPumpArea";
_fillpumparea();
 //BA.debugLineNum = 69;BA.debugLine="ClearUI";
_clearui();
 }else {
 //BA.debugLineNum = 71;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Appen";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("EDIT PROBLEM(S) ENCOUNTERED"))).PopAll();
 //BA.debugLineNum = 72;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append(";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 //BA.debugLineNum = 73;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\" UPDATE\"$";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" UPDATE")));
 //BA.debugLineNum = 74;BA.debugLine="FillPumpArea";
_fillpumparea();
 //BA.debugLineNum = 75;BA.debugLine="ClearUI";
_clearui();
 //BA.debugLineNum = 76;BA.debugLine="GetProblemDetails(GlobalVar.ProblemDetailID)";
_getproblemdetails(mostCurrent._globalvar._problemdetailid /*int*/ );
 };
 //BA.debugLineNum = 79;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 80;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 81;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 83;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 84;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 85;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 86;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 87;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 88;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 90;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 91;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 93;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 };
 //BA.debugLineNum = 95;BA.debugLine="kBoard.Initialize(\"\")";
mostCurrent._kboard.Initialize("");
 //BA.debugLineNum = 96;BA.debugLine="InpTyp.Initialize";
_inptyp._initialize /*String*/ (processBA);
 //BA.debugLineNum = 97;BA.debugLine="InpTyp.SetInputType(txtProbTitle,Array As Int(Inp";
_inptyp._setinputtype /*String*/ (mostCurrent._txtprobtitle,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_correct /*int*/ (),_inptyp._type_text_flag_cap_sentences /*int*/ ()});
 //BA.debugLineNum = 98;BA.debugLine="InpTyp.SetInputType(txtProbDetails,Array As Int(I";
_inptyp._setinputtype /*String*/ (mostCurrent._txtprobdetails,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_correct /*int*/ (),_inptyp._type_text_flag_cap_sentences /*int*/ ()});
 //BA.debugLineNum = 99;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 100;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 102;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 103;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 104;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 105;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 107;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 152;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 153;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 123;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 124;BA.debugLine="If Result Then";
if (_result) { 
 //BA.debugLineNum = 125;BA.debugLine="If Permission = Starter.RTP.PERMISSION_READ_EXTE";
if ((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 126;BA.debugLine="LogColor($\"Permission to Read External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("066650115",("Permission to Read External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 127;BA.debugLine="GlobalVar.ReadStoragePermission = True";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 129;BA.debugLine="LogColor($\"Permission to Write External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("066650118",("Permission to Write External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 130;BA.debugLine="GlobalVar.WriteStoragePermission = True";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION)) { 
 //BA.debugLineNum = 132;BA.debugLine="LogColor($\"Permission to Access Coarse Location";
anywheresoftware.b4a.keywords.Common.LogImpl("066650121",("Permission to Access Coarse Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 133;BA.debugLine="GlobalVar.CoarseLocPermission = True";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION)) { 
 //BA.debugLineNum = 135;BA.debugLine="LogColor($\"Permission to Access Fine Location G";
anywheresoftware.b4a.keywords.Common.LogImpl("066650124",("Permission to Access Fine Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 136;BA.debugLine="GlobalVar.FineLocPermission = True";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 138;BA.debugLine="Starter.StartFLP";
mostCurrent._starter._startflp /*void*/ ();
 }else {
 //BA.debugLineNum = 140;BA.debugLine="GlobalVar.ReadStoragePermission = False";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 141;BA.debugLine="GlobalVar.WriteStoragePermission = False";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 142;BA.debugLine="GlobalVar.CoarseLocPermission = False";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 143;BA.debugLine="GlobalVar.FineLocPermission = False";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 144;BA.debugLine="Result = False";
_result = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 146;BA.debugLine="Log (Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("066650135",_permission,0);
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 149;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 150;BA.debugLine="End Sub";
return "";
}
public static String  _addupdatechlorinerec_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 884;BA.debugLine="Private Sub AddUpdateChlorineRec_ButtonPressed(mDi";
 //BA.debugLineNum = 885;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE)) {
case 0: {
 //BA.debugLineNum = 887;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 890;BA.debugLine="End Sub";
return "";
}
public static String  _btnsaveupdate_click() throws Exception{
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher2 = null;
String _smin1 = "";
String _shr1 = "";
String _smin2 = "";
String _shr2 = "";
long _lngtimestarted = 0L;
long _lngtimefinished = 0L;
int _ihrs1 = 0;
int _imins1 = 0;
int _ihrs2 = 0;
int _imins2 = 0;
 //BA.debugLineNum = 199;BA.debugLine="Sub btnSaveUpdate_Click";
 //BA.debugLineNum = 200;BA.debugLine="Dim Matcher1, Matcher2 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
_matcher2 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 201;BA.debugLine="Dim sMin1, sHr1, sMin2, sHr2 As String";
_smin1 = "";
_shr1 = "";
_smin2 = "";
_shr2 = "";
 //BA.debugLineNum = 202;BA.debugLine="Dim lngTimeStarted, lngTimeFinished As Long";
_lngtimestarted = 0L;
_lngtimefinished = 0L;
 //BA.debugLineNum = 204;BA.debugLine="sTimeStarted = \"\"";
mostCurrent._stimestarted = "";
 //BA.debugLineNum = 206;BA.debugLine="If Not(IsValidEntries) Then Return 'Check Entries";
if (anywheresoftware.b4a.keywords.Common.Not(_isvalidentries())) { 
if (true) return "";};
 //BA.debugLineNum = 208;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d)\", mskTime";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d)",mostCurrent._msktimefrom.getText());
 //BA.debugLineNum = 210;BA.debugLine="If Matcher1.Find Then 'Split";
if (_matcher1.Find()) { 
 //BA.debugLineNum = 211;BA.debugLine="Dim iHrs1, iMins1 As Int";
_ihrs1 = 0;
_imins1 = 0;
 //BA.debugLineNum = 213;BA.debugLine="iHrs1 = Matcher1.Group(1)";
_ihrs1 = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 214;BA.debugLine="iMins1 = Matcher1.Group(2)";
_imins1 = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 216;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins1)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_imins1)))==1) { 
 //BA.debugLineNum = 217;BA.debugLine="sMin1 = $\"0\"$ & iMins1";
_smin1 = ("0")+BA.NumberToString(_imins1);
 }else {
 //BA.debugLineNum = 219;BA.debugLine="sMin1 = iMins1";
_smin1 = BA.NumberToString(_imins1);
 };
 //BA.debugLineNum = 222;BA.debugLine="If iHrs1 = 0 Then '12 AM";
if (_ihrs1==0) { 
 //BA.debugLineNum = 223;BA.debugLine="sHr1 = 12";
_shr1 = BA.NumberToString(12);
 //BA.debugLineNum = 224;BA.debugLine="sTimeStarted = sHr1 & \":\" & sMin1 & \" AM\"";
mostCurrent._stimestarted = _shr1+":"+_smin1+" AM";
 }else if(_ihrs1>0 && _ihrs1<12) { 
 //BA.debugLineNum = 226;BA.debugLine="sHr1 = iHrs1";
_shr1 = BA.NumberToString(_ihrs1);
 //BA.debugLineNum = 227;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr1)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_shr1))==1) { 
 //BA.debugLineNum = 228;BA.debugLine="sTimeStarted = $\"0\"$ & sHr1 & \":\" & sMin1 & \"";
mostCurrent._stimestarted = ("0")+_shr1+":"+_smin1+" AM";
 }else {
 //BA.debugLineNum = 230;BA.debugLine="sTimeStarted = sHr1 & \":\" & sMin1 & \" AM\"";
mostCurrent._stimestarted = _shr1+":"+_smin1+" AM";
 };
 }else if(_ihrs1==12) { 
 //BA.debugLineNum = 234;BA.debugLine="sHr1 = 12";
_shr1 = BA.NumberToString(12);
 //BA.debugLineNum = 235;BA.debugLine="sTimeStarted = sHr1 & \":\" & sMin1 & \" PM\"";
mostCurrent._stimestarted = _shr1+":"+_smin1+" PM";
 }else {
 //BA.debugLineNum = 237;BA.debugLine="sHr1 = iHrs1 - 12";
_shr1 = BA.NumberToString(_ihrs1-12);
 //BA.debugLineNum = 238;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr1)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_shr1))==1) { 
 //BA.debugLineNum = 239;BA.debugLine="sTimeStarted = $\"0\"$ & sHr1 & \":\" & sMin1 & \"";
mostCurrent._stimestarted = ("0")+_shr1+":"+_smin1+" PM";
 }else {
 //BA.debugLineNum = 241;BA.debugLine="sTimeStarted = sHr1 & \":\" & sMin1 & \" PM\"";
mostCurrent._stimestarted = _shr1+":"+_smin1+" PM";
 };
 };
 };
 //BA.debugLineNum = 246;BA.debugLine="LogColor($\"Start Time: \"$ & sTimeStarted,Colors.Y";
anywheresoftware.b4a.keywords.Common.LogImpl("067174447",("Start Time: ")+mostCurrent._stimestarted,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 248;BA.debugLine="Matcher2 = Regex.Matcher(\"(\\d\\d):(\\d\\d)\", mskTime";
_matcher2 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d)",mostCurrent._msktimeto.getText());
 //BA.debugLineNum = 250;BA.debugLine="If Matcher2.Find Then 'Split";
if (_matcher2.Find()) { 
 //BA.debugLineNum = 251;BA.debugLine="Dim iHrs2, iMins2 As Int";
_ihrs2 = 0;
_imins2 = 0;
 //BA.debugLineNum = 253;BA.debugLine="iHrs2 = Matcher2.Group(1)";
_ihrs2 = (int)(Double.parseDouble(_matcher2.Group((int) (1))));
 //BA.debugLineNum = 254;BA.debugLine="iMins2 = Matcher2.Group(2)";
_imins2 = (int)(Double.parseDouble(_matcher2.Group((int) (2))));
 //BA.debugLineNum = 256;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins2)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_imins2)))==1) { 
 //BA.debugLineNum = 257;BA.debugLine="sMin2 = $\"0\"$ & iMins2";
_smin2 = ("0")+BA.NumberToString(_imins2);
 }else {
 //BA.debugLineNum = 259;BA.debugLine="sMin2 = iMins2";
_smin2 = BA.NumberToString(_imins2);
 };
 //BA.debugLineNum = 262;BA.debugLine="If iHrs2 = 0 Then '12 AM";
if (_ihrs2==0) { 
 //BA.debugLineNum = 263;BA.debugLine="sHr2 = 12";
_shr2 = BA.NumberToString(12);
 //BA.debugLineNum = 264;BA.debugLine="sTimeFinished = sHr2 & \":\" & sMin2 & \" AM\"";
mostCurrent._stimefinished = _shr2+":"+_smin2+" AM";
 }else if(_ihrs2>0 && _ihrs2<12) { 
 //BA.debugLineNum = 266;BA.debugLine="sHr2 = iHrs2";
_shr2 = BA.NumberToString(_ihrs2);
 //BA.debugLineNum = 267;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr2)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_shr2))==1) { 
 //BA.debugLineNum = 268;BA.debugLine="sTimeFinished = $\"0\"$ & sHr2 & \":\" & sMin2 & \"";
mostCurrent._stimefinished = ("0")+_shr2+":"+_smin2+" AM";
 }else {
 //BA.debugLineNum = 270;BA.debugLine="sTimeFinished = sHr2 & \":\" & sMin2 & \" AM\"";
mostCurrent._stimefinished = _shr2+":"+_smin2+" AM";
 };
 }else if(_ihrs2==12) { 
 //BA.debugLineNum = 274;BA.debugLine="sHr2 = 12";
_shr2 = BA.NumberToString(12);
 //BA.debugLineNum = 275;BA.debugLine="sTimeFinished = sHr2 & \":\" & sMin2 & \" PM\"";
mostCurrent._stimefinished = _shr2+":"+_smin2+" PM";
 }else {
 //BA.debugLineNum = 277;BA.debugLine="sHr2 = iHrs2 - 12";
_shr2 = BA.NumberToString(_ihrs2-12);
 //BA.debugLineNum = 278;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr2)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_shr2))==1) { 
 //BA.debugLineNum = 279;BA.debugLine="sTimeFinished = $\"0\"$ & sHr2 & \":\" & sMin2 & \"";
mostCurrent._stimefinished = ("0")+_shr2+":"+_smin2+" PM";
 }else {
 //BA.debugLineNum = 281;BA.debugLine="sTimeFinished = sHr2 & \":\" & sMin2 & \" PM\"";
mostCurrent._stimefinished = _shr2+":"+_smin2+" PM";
 };
 };
 };
 //BA.debugLineNum = 286;BA.debugLine="LogColor($\"End Time: \"$ & sTimeFinished,Colors.Ye";
anywheresoftware.b4a.keywords.Common.LogImpl("067174487",("End Time: ")+mostCurrent._stimefinished,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 287;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 288;BA.debugLine="lngTimeStarted = DateTime.TimeParse(mskTimeFrom.T";
_lngtimestarted = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(mostCurrent._msktimefrom.getText());
 //BA.debugLineNum = 289;BA.debugLine="lngTimeFinished = DateTime.TimeParse(mskTimeTo.Te";
_lngtimefinished = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(mostCurrent._msktimeto.getText());
 //BA.debugLineNum = 291;BA.debugLine="LogColor($\"Time Start: \"$ & lngTimeStarted, Color";
anywheresoftware.b4a.keywords.Common.LogImpl("067174492",("Time Start: ")+BA.NumberToString(_lngtimestarted),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 292;BA.debugLine="LogColor($\"Time Finished: \"$ & lngTimeFinished, C";
anywheresoftware.b4a.keywords.Common.LogImpl("067174493",("Time Finished: ")+BA.NumberToString(_lngtimefinished),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 294;BA.debugLine="If lngTimeStarted > lngTimeFinished Then";
if (_lngtimestarted>_lngtimefinished) { 
 //BA.debugLineNum = 295;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Time End is earlier";
_requiredmsgbox(("ERROR"),("Time End is earlier than Time Started!"));
 //BA.debugLineNum = 296;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 299;BA.debugLine="ConfirmSaveUpdateProblem";
_confirmsaveupdateproblem();
 //BA.debugLineNum = 300;BA.debugLine="End Sub";
return "";
}
public static String  _cbopumparea_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 302;BA.debugLine="Sub cboPumpArea_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 303;BA.debugLine="LogColor($\"Selected \"$ & Position & \" - \" & Value";
anywheresoftware.b4a.keywords.Common.LogImpl("067239937",("Selected ")+BA.NumberToString(_position)+" - "+BA.ObjectToString(_value),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 304;BA.debugLine="End Sub";
return "";
}
public static String  _checkpermissions() throws Exception{
 //BA.debugLineNum = 111;BA.debugLine="Private Sub CheckPermissions";
 //BA.debugLineNum = 112;BA.debugLine="Log(\"Checking Permissions\")";
anywheresoftware.b4a.keywords.Common.LogImpl("066584577","Checking Permissions",0);
 //BA.debugLineNum = 114;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE);
 //BA.debugLineNum = 115;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE);
 //BA.debugLineNum = 116;BA.debugLine="Starter.RTP.GetAllSafeDirsExternal(\"\")";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .GetAllSafeDirsExternal("");
 //BA.debugLineNum = 118;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION);
 //BA.debugLineNum = 119;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 120;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}
public static String  _chkwassolved_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 306;BA.debugLine="Sub chkWasSolved_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 307;BA.debugLine="If Checked = True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 308;BA.debugLine="txtActionTaken.Enabled = True";
mostCurrent._txtactiontaken.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 309;BA.debugLine="txtActionTaken.RequestFocus";
mostCurrent._txtactiontaken.RequestFocus();
 }else {
 //BA.debugLineNum = 311;BA.debugLine="txtActionTaken.Enabled = False";
mostCurrent._txtactiontaken.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 312;BA.debugLine="txtActionTaken.Text = \"\"";
mostCurrent._txtactiontaken.setText(BA.ObjectToCharSequence(""));
 };
 //BA.debugLineNum = 314;BA.debugLine="End Sub";
return "";
}
public static String  _clearui() throws Exception{
 //BA.debugLineNum = 173;BA.debugLine="Private Sub ClearUI";
 //BA.debugLineNum = 174;BA.debugLine="mskTimeFrom.Text = \"__:__\"";
mostCurrent._msktimefrom.setText((Object)("__:__"));
 //BA.debugLineNum = 175;BA.debugLine="mskTimeTo.Text = \"__:__\"";
mostCurrent._msktimeto.setText((Object)("__:__"));
 //BA.debugLineNum = 176;BA.debugLine="chkCritical.Checked = False";
mostCurrent._chkcritical.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 178;BA.debugLine="txtProbTitle.Text = \"\"";
mostCurrent._txtprobtitle.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 179;BA.debugLine="txtProbDetails.Text = \"\"";
mostCurrent._txtprobdetails.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 180;BA.debugLine="chkWasSolved.Checked = False";
mostCurrent._chkwassolved.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 181;BA.debugLine="txtActionTaken.Text = \"\"";
mostCurrent._txtactiontaken.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 182;BA.debugLine="txtActionTaken.Enabled = False";
mostCurrent._txtactiontaken.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 184;BA.debugLine="CDtxtBox.Initialize(Colors.Transparent,0)";
mostCurrent._cdtxtbox.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 185;BA.debugLine="cboPumpArea.Background = CDtxtBox";
mostCurrent._cbopumparea.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 186;BA.debugLine="txtProbTitle.Background = CDtxtBox";
mostCurrent._txtprobtitle.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 187;BA.debugLine="txtProbDetails.Background = CDtxtBox";
mostCurrent._txtprobdetails.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 188;BA.debugLine="txtActionTaken.Background = CDtxtBox";
mostCurrent._txtactiontaken.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 189;BA.debugLine="mskTimeFrom.Background = CDtxtBox";
mostCurrent._msktimefrom.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 190;BA.debugLine="mskTimeTo.Background = CDtxtBox";
mostCurrent._msktimeto.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 192;BA.debugLine="MyFunctions.SetButton(btnSaveUpdate, 25, 25, 25,";
mostCurrent._myfunctions._setbutton /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btnsaveupdate.getObject())),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25));
 //BA.debugLineNum = 193;BA.debugLine="End Sub";
return "";
}
public static String  _confirmsaveupdateproblem() throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
String _stitle = "";
String _smsg = "";
 //BA.debugLineNum = 777;BA.debugLine="Private Sub ConfirmSaveUpdateProblem";
 //BA.debugLineNum = 778;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 779;BA.debugLine="Dim sTitle, sMsg As String";
_stitle = "";
_smsg = "";
 //BA.debugLineNum = 781;BA.debugLine="If GlobalVar.blnNewProblem = True Then";
if (mostCurrent._globalvar._blnnewproblem /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 782;BA.debugLine="sTitle = $\"SAVE NEW PROBLEM(S) ENCOUNTERED RECOR";
_stitle = ("SAVE NEW PROBLEM(S) ENCOUNTERED RECORD");
 //BA.debugLineNum = 783;BA.debugLine="sMsg = $\"Save New Problem Encountered Record?\"$";
_smsg = ("Save New Problem Encountered Record?");
 }else {
 //BA.debugLineNum = 785;BA.debugLine="sTitle = $\"UPDATE PROBLEM(S) ENCOUNTERED RECORD\"";
_stitle = ("UPDATE PROBLEM(S) ENCOUNTERED RECORD");
 //BA.debugLineNum = 786;BA.debugLine="sMsg = $\"Update Problem Encountered Record?\"$";
_smsg = ("Update Problem Encountered Record?");
 };
 //BA.debugLineNum = 789;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._bluecolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage(_smsg).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"SaveUpdateProblem").SetOnNegativeClicked(mostCurrent.activityBA,"SaveUpdateProblem");
 //BA.debugLineNum = 806;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 807;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 808;BA.debugLine="End Sub";
return "";
}
public static void  _fillpumparea() throws Exception{
ResumableSub_FillPumpArea rsub = new ResumableSub_FillPumpArea(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_FillPumpArea extends BA.ResumableSub {
public ResumableSub_FillPumpArea(bwsi.PumpOperations.addeditproblem parent) {
this.parent = parent;
}
bwsi.PumpOperations.addeditproblem parent;
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
 //BA.debugLineNum = 319;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 320;BA.debugLine="cboPumpArea.Clear";
parent.mostCurrent._cbopumparea.Clear();
 //BA.debugLineNum = 321;BA.debugLine="Try";
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
 //BA.debugLineNum = 322;BA.debugLine="Starter.strCriteria = \"SELECT PumpArea FROM Pump";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT PumpArea FROM PumpAreas";
 //BA.debugLineNum = 324;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 325;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 17;
return;
case 17:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 327;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 328;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 329;BA.debugLine="cboPumpArea.Add(RS.GetString(\"PumpArea\"))";
parent.mostCurrent._cbopumparea.Add(BA.ObjectToCharSequence(_rs.GetString("PumpArea")));
 if (true) break;

case 10:
//C
this.state = 13;
;
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 332;BA.debugLine="ToastMessageShow($\"Cannot get Pump Areas due to";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Cannot get Pump Areas due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 333;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("067371023",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 13:
//C
this.state = 16;
;
 if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
 //BA.debugLineNum = 337;BA.debugLine="ToastMessageShow($\"Cannot get Pump Areas due to";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Cannot get Pump Areas due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 338;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("067371028",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 340;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 761;BA.debugLine="Private Sub FontSizeBinder_OnBindView (View As Vie";
 //BA.debugLineNum = 762;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 763;BA.debugLine="Alert.Initialize";
_alert.Initialize();
 //BA.debugLineNum = 764;BA.debugLine="If ViewType = Alert.VIEW_TITLE Then ' Title";
if (_viewtype==_alert.VIEW_TITLE) { 
 //BA.debugLineNum = 765;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 766;BA.debugLine="lbl.TextSize = 30";
_lbl.setTextSize((float) (30));
 //BA.debugLineNum = 767;BA.debugLine="lbl.SetTextColorAnimated(2000,Colors.Magenta)";
_lbl.SetTextColorAnimated((int) (2000),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 769;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 770;BA.debugLine="CS.Initialize.Typeface(Typeface.MATERIALICONS).S";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe88e)))+"  "));
 //BA.debugLineNum = 771;BA.debugLine="CS.Typeface(Font).Size(24).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(mostCurrent._font.getObject())).Size((int) (24)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 773;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 775;BA.debugLine="End Sub";
return "";
}
public static void  _getproblemdetails(int _idetailid) throws Exception{
ResumableSub_GetProblemDetails rsub = new ResumableSub_GetProblemDetails(null,_idetailid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetProblemDetails extends BA.ResumableSub {
public ResumableSub_GetProblemDetails(bwsi.PumpOperations.addeditproblem parent,int _idetailid) {
this.parent = parent;
this._idetailid = _idetailid;
}
bwsi.PumpOperations.addeditproblem parent;
int _idetailid;
String _stimestart = "";
String _stimeend = "";
String _sarea = "";
String _sactiontake = "";
int _iarea = 0;
int _iiscritical = 0;
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher2 = null;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
int _ihrs = 0;
int _imins = 0;
String _ampm = "";
String _smin = "";
int _ihrs2 = 0;
int _imins2 = 0;
String _ampm2 = "";
String _smin2 = "";

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
 //BA.debugLineNum = 343;BA.debugLine="Dim sTimeStart, sTimeEnd As String";
_stimestart = "";
_stimeend = "";
 //BA.debugLineNum = 344;BA.debugLine="Dim sArea, sActionTake As String";
_sarea = "";
_sactiontake = "";
 //BA.debugLineNum = 345;BA.debugLine="Dim iArea, iIsCritical As Int";
_iarea = 0;
_iiscritical = 0;
 //BA.debugLineNum = 346;BA.debugLine="Dim Matcher1, Matcher2 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
_matcher2 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 348;BA.debugLine="Try";
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
 //BA.debugLineNum = 349;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 351;BA.debugLine="Starter.strCriteria = \"SELECT * FROM ProblemDeta";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM ProblemDetails "+"WHERE DetailID = "+BA.NumberToString(_idetailid);
 //BA.debugLineNum = 354;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 355;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 91;
return;
case 91:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 357;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 358;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 359;BA.debugLine="sTimeStart = RS.GetString(\"TimeStart\")";
_stimestart = _rs.GetString("TimeStart");
 //BA.debugLineNum = 360;BA.debugLine="sTimeEnd = RS.GetString(\"TimeFinished\")";
_stimeend = _rs.GetString("TimeFinished");
 //BA.debugLineNum = 361;BA.debugLine="iArea = RS.GetInt(\"AreaID\")";
_iarea = _rs.GetInt("AreaID");
 //BA.debugLineNum = 362;BA.debugLine="sArea = DBaseFunctions.GetCodeByID(\"PumpArea\",\"";
_sarea = parent.mostCurrent._dbasefunctions._getcodebyid /*String*/ (mostCurrent.activityBA,"PumpArea","PumpAreas","ID",_iarea);
 //BA.debugLineNum = 363;BA.debugLine="iIsCritical = RS.GetInt(\"IsCritical\")";
_iiscritical = _rs.GetInt("IsCritical");
 //BA.debugLineNum = 364;BA.debugLine="If iIsCritical = 1 Then";
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
 //BA.debugLineNum = 365;BA.debugLine="chkCritical.Checked = True";
parent.mostCurrent._chkcritical.setChecked(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 367;BA.debugLine="chkCritical.Checked = False";
parent.mostCurrent._chkcritical.setChecked(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 12:
//C
this.state = 13;
;
 //BA.debugLineNum = 369;BA.debugLine="cboPumpArea.SelectedIndex = cboPumpArea.IndexOf";
parent.mostCurrent._cbopumparea.setSelectedIndex(parent.mostCurrent._cbopumparea.IndexOf(BA.ObjectToCharSequence(_sarea)));
 //BA.debugLineNum = 370;BA.debugLine="txtProbTitle.Text = RS.GetString(\"ProblemTitle\"";
parent.mostCurrent._txtprobtitle.setText(BA.ObjectToCharSequence(_rs.GetString("ProblemTitle")));
 //BA.debugLineNum = 371;BA.debugLine="txtProbDetails.Text = RS.GetString(\"ProbDesc\")";
parent.mostCurrent._txtprobdetails.setText(BA.ObjectToCharSequence(_rs.GetString("ProbDesc")));
 //BA.debugLineNum = 372;BA.debugLine="sActionTake = RS.GetString(\"ActionTaken\")";
_sactiontake = _rs.GetString("ActionTaken");
 //BA.debugLineNum = 373;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(sActionTa";
if (true) break;

case 13:
//if
this.state = 18;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_sactiontake))<=0) { 
this.state = 15;
}else {
this.state = 17;
}if (true) break;

case 15:
//C
this.state = 18;
 //BA.debugLineNum = 374;BA.debugLine="chkWasSolved.Checked = False";
parent.mostCurrent._chkwassolved.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 375;BA.debugLine="txtActionTaken.Enabled = False";
parent.mostCurrent._txtactiontaken.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 377;BA.debugLine="chkWasSolved.Checked = True";
parent.mostCurrent._chkwassolved.setChecked(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 378;BA.debugLine="txtActionTaken.Text = sActionTake";
parent.mostCurrent._txtactiontaken.setText(BA.ObjectToCharSequence(_sactiontake));
 //BA.debugLineNum = 379;BA.debugLine="txtActionTaken.Enabled = True";
parent.mostCurrent._txtactiontaken.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 18:
//C
this.state = 19;
;
 //BA.debugLineNum = 383;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 384;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d) (\\S\\S)\"";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d) (\\S\\S)",_stimestart);
 //BA.debugLineNum = 385;BA.debugLine="If Matcher1.Find Then";
if (true) break;

case 19:
//if
this.state = 51;
if (_matcher1.Find()) { 
this.state = 21;
}if (true) break;

case 21:
//C
this.state = 22;
 //BA.debugLineNum = 386;BA.debugLine="Dim iHrs, iMins As Int";
_ihrs = 0;
_imins = 0;
 //BA.debugLineNum = 387;BA.debugLine="Dim AmPm As String";
_ampm = "";
 //BA.debugLineNum = 388;BA.debugLine="Dim sMin As String";
_smin = "";
 //BA.debugLineNum = 390;BA.debugLine="iHrs = Matcher1.Group(1)";
_ihrs = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 391;BA.debugLine="iMins = Matcher1.Group(2)";
_imins = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 392;BA.debugLine="AmPm = Matcher1.Group(3)";
_ampm = _matcher1.Group((int) (3));
 //BA.debugLineNum = 394;BA.debugLine="LogColor(AmPm,Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("067436596",_ampm,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 396;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins))";
if (true) break;

case 22:
//if
this.state = 27;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_imins)))==1) { 
this.state = 24;
}else {
this.state = 26;
}if (true) break;

case 24:
//C
this.state = 27;
 //BA.debugLineNum = 397;BA.debugLine="sMin = $\"0\"$ & iMins";
_smin = ("0")+BA.NumberToString(_imins);
 if (true) break;

case 26:
//C
this.state = 27;
 //BA.debugLineNum = 399;BA.debugLine="sMin = iMins";
_smin = BA.NumberToString(_imins);
 if (true) break;
;
 //BA.debugLineNum = 402;BA.debugLine="If AmPm = \"AM\" Then";

case 27:
//if
this.state = 50;
if ((_ampm).equals("AM")) { 
this.state = 29;
}else {
this.state = 43;
}if (true) break;

case 29:
//C
this.state = 30;
 //BA.debugLineNum = 403;BA.debugLine="If iHrs = 12 Then";
if (true) break;

case 30:
//if
this.state = 41;
if (_ihrs==12) { 
this.state = 32;
}else {
this.state = 34;
}if (true) break;

case 32:
//C
this.state = 41;
 //BA.debugLineNum = 404;BA.debugLine="mskTimeFrom.Text = $\"00:\"$ & sMin";
parent.mostCurrent._msktimefrom.setText((Object)(("00:")+_smin));
 if (true) break;

case 34:
//C
this.state = 35;
 //BA.debugLineNum = 406;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrs))";
if (true) break;

case 35:
//if
this.state = 40;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_ihrs)))==1) { 
this.state = 37;
}else {
this.state = 39;
}if (true) break;

case 37:
//C
this.state = 40;
 //BA.debugLineNum = 407;BA.debugLine="mskTimeFrom.Text = $\"0\"$ & iHrs & $\":\"$ & s";
parent.mostCurrent._msktimefrom.setText((Object)(("0")+BA.NumberToString(_ihrs)+(":")+_smin));
 if (true) break;

case 39:
//C
this.state = 40;
 //BA.debugLineNum = 409;BA.debugLine="mskTimeFrom.Text = iHrs & $\":\"$ & sMin";
parent.mostCurrent._msktimefrom.setText((Object)(BA.NumberToString(_ihrs)+(":")+_smin));
 if (true) break;

case 40:
//C
this.state = 41;
;
 if (true) break;

case 41:
//C
this.state = 50;
;
 if (true) break;

case 43:
//C
this.state = 44;
 //BA.debugLineNum = 413;BA.debugLine="If iHrs < 12 Then";
if (true) break;

case 44:
//if
this.state = 49;
if (_ihrs<12) { 
this.state = 46;
}else {
this.state = 48;
}if (true) break;

case 46:
//C
this.state = 49;
 //BA.debugLineNum = 414;BA.debugLine="mskTimeFrom.Text = (iHrs + 12) & $\":\"$ & sMi";
parent.mostCurrent._msktimefrom.setText((Object)(BA.NumberToString((_ihrs+12))+(":")+_smin));
 if (true) break;

case 48:
//C
this.state = 49;
 //BA.debugLineNum = 416;BA.debugLine="mskTimeFrom.Text = iHrs & $\":\"$ & sMin";
parent.mostCurrent._msktimefrom.setText((Object)(BA.NumberToString(_ihrs)+(":")+_smin));
 if (true) break;

case 49:
//C
this.state = 50;
;
 if (true) break;

case 50:
//C
this.state = 51;
;
 if (true) break;

case 51:
//C
this.state = 52;
;
 //BA.debugLineNum = 421;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 422;BA.debugLine="Matcher2 = Regex.Matcher(\"(\\d\\d):(\\d\\d) (\\S\\S)\"";
_matcher2 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d) (\\S\\S)",_stimeend);
 //BA.debugLineNum = 423;BA.debugLine="If Matcher2.Find Then";
if (true) break;

case 52:
//if
this.state = 84;
if (_matcher2.Find()) { 
this.state = 54;
}if (true) break;

case 54:
//C
this.state = 55;
 //BA.debugLineNum = 424;BA.debugLine="Dim iHrs2, iMins2 As Int";
_ihrs2 = 0;
_imins2 = 0;
 //BA.debugLineNum = 425;BA.debugLine="Dim AmPm2 As String";
_ampm2 = "";
 //BA.debugLineNum = 426;BA.debugLine="Dim sMin2 As String";
_smin2 = "";
 //BA.debugLineNum = 428;BA.debugLine="iHrs2 = Matcher2.Group(1)";
_ihrs2 = (int)(Double.parseDouble(_matcher2.Group((int) (1))));
 //BA.debugLineNum = 429;BA.debugLine="iMins2 = Matcher2.Group(2)";
_imins2 = (int)(Double.parseDouble(_matcher2.Group((int) (2))));
 //BA.debugLineNum = 430;BA.debugLine="AmPm2 = Matcher2.Group(3)";
_ampm2 = _matcher2.Group((int) (3));
 //BA.debugLineNum = 432;BA.debugLine="LogColor(AmPm2,Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("067436634",_ampm2,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 434;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins2))";
if (true) break;

case 55:
//if
this.state = 60;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_imins2)))==1) { 
this.state = 57;
}else {
this.state = 59;
}if (true) break;

case 57:
//C
this.state = 60;
 //BA.debugLineNum = 435;BA.debugLine="sMin2 = $\"0\"$ & iMins2";
_smin2 = ("0")+BA.NumberToString(_imins2);
 if (true) break;

case 59:
//C
this.state = 60;
 //BA.debugLineNum = 437;BA.debugLine="sMin2 = iMins2";
_smin2 = BA.NumberToString(_imins2);
 if (true) break;
;
 //BA.debugLineNum = 440;BA.debugLine="If AmPm2 = \"AM\" Then";

case 60:
//if
this.state = 83;
if ((_ampm2).equals("AM")) { 
this.state = 62;
}else {
this.state = 76;
}if (true) break;

case 62:
//C
this.state = 63;
 //BA.debugLineNum = 441;BA.debugLine="If iHrs2 = 12 Then";
if (true) break;

case 63:
//if
this.state = 74;
if (_ihrs2==12) { 
this.state = 65;
}else {
this.state = 67;
}if (true) break;

case 65:
//C
this.state = 74;
 //BA.debugLineNum = 442;BA.debugLine="mskTimeTo.Text = $\"00:\"$ & sMin2";
parent.mostCurrent._msktimeto.setText((Object)(("00:")+_smin2));
 if (true) break;

case 67:
//C
this.state = 68;
 //BA.debugLineNum = 444;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrs2)";
if (true) break;

case 68:
//if
this.state = 73;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_ihrs2)))==1) { 
this.state = 70;
}else {
this.state = 72;
}if (true) break;

case 70:
//C
this.state = 73;
 //BA.debugLineNum = 445;BA.debugLine="mskTimeTo.Text = $\"0\"$ & iHrs2 & $\":\"$ & sM";
parent.mostCurrent._msktimeto.setText((Object)(("0")+BA.NumberToString(_ihrs2)+(":")+_smin2));
 if (true) break;

case 72:
//C
this.state = 73;
 //BA.debugLineNum = 447;BA.debugLine="mskTimeTo.Text = iHrs2 & $\":\"$ & sMin2";
parent.mostCurrent._msktimeto.setText((Object)(BA.NumberToString(_ihrs2)+(":")+_smin2));
 if (true) break;

case 73:
//C
this.state = 74;
;
 if (true) break;

case 74:
//C
this.state = 83;
;
 if (true) break;

case 76:
//C
this.state = 77;
 //BA.debugLineNum = 451;BA.debugLine="If iHrs2 < 12 Then";
if (true) break;

case 77:
//if
this.state = 82;
if (_ihrs2<12) { 
this.state = 79;
}else {
this.state = 81;
}if (true) break;

case 79:
//C
this.state = 82;
 //BA.debugLineNum = 452;BA.debugLine="mskTimeTo.Text = (iHrs2 + 12) & $\":\"$ & sMin";
parent.mostCurrent._msktimeto.setText((Object)(BA.NumberToString((_ihrs2+12))+(":")+_smin2));
 if (true) break;

case 81:
//C
this.state = 82;
 //BA.debugLineNum = 454;BA.debugLine="mskTimeTo.Text = iHrs2 & $\":\"$ & sMin2";
parent.mostCurrent._msktimeto.setText((Object)(BA.NumberToString(_ihrs2)+(":")+_smin2));
 if (true) break;

case 82:
//C
this.state = 83;
;
 if (true) break;

case 83:
//C
this.state = 84;
;
 if (true) break;

case 84:
//C
this.state = 87;
;
 if (true) break;

case 86:
//C
this.state = 87;
 //BA.debugLineNum = 459;BA.debugLine="ToastMessageShow($\"Unable to fetch problem reco";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch problem record due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
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
 //BA.debugLineNum = 462;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("067436664",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 90:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 464;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 35;BA.debugLine="Private sTimeStarted As String";
mostCurrent._stimestarted = "";
 //BA.debugLineNum = 36;BA.debugLine="Private sTimeFinished As String";
mostCurrent._stimefinished = "";
 //BA.debugLineNum = 38;BA.debugLine="Private mskTimeFrom As MaskedEditText";
mostCurrent._msktimefrom = new flm.b4a.maskededittext.MaskedEditTextWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private mskTimeTo As MaskedEditText";
mostCurrent._msktimeto = new flm.b4a.maskededittext.MaskedEditTextWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private cboPumpArea As ACSpinner";
mostCurrent._cbopumparea = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private chkCritical As CheckBox";
mostCurrent._chkcritical = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private txtProbTitle As EditText";
mostCurrent._txtprobtitle = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private txtProbDetails As EditText";
mostCurrent._txtprobdetails = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private btnSaveUpdate As ACButton";
mostCurrent._btnsaveupdate = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private kBoard As IME";
mostCurrent._kboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 49;BA.debugLine="Private scvMain As ScrollView";
mostCurrent._scvmain = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private pnlMain As Panel";
mostCurrent._pnlmain = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private chkWasSolved As CheckBox";
mostCurrent._chkwassolved = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private txtActionTaken As EditText";
mostCurrent._txtactiontaken = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static boolean  _isvalidentries() throws Exception{
 //BA.debugLineNum = 466;BA.debugLine="Private Sub IsValidEntries () As Boolean";
 //BA.debugLineNum = 467;BA.debugLine="Try";
try { //BA.debugLineNum = 468;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(mskTimeFro";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._msktimefrom.getText()))<=0 || (mostCurrent._msktimefrom.getText()).equals("__:__")) { 
 //BA.debugLineNum = 469;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Time Started cannot";
_requiredmsgbox(("ERROR"),("Time Started cannot be blank!"));
 //BA.debugLineNum = 470;BA.debugLine="mskTimeFrom.RequestFocus";
mostCurrent._msktimefrom.RequestFocus();
 //BA.debugLineNum = 471;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else if(mostCurrent._validation._istime /*boolean*/ (mostCurrent.activityBA,mostCurrent._msktimefrom.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 474;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Time Started cannot";
_requiredmsgbox(("ERROR"),("Time Started cannot be blank!"));
 //BA.debugLineNum = 475;BA.debugLine="mskTimeFrom.RequestFocus";
mostCurrent._msktimefrom.RequestFocus();
 //BA.debugLineNum = 476;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 479;BA.debugLine="If DBaseFunctions.IsFuturisticTime(GlobalVar.Tra";
if (mostCurrent._dbasefunctions._isfuturistictime /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._msktimefrom.getText())==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 480;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, $\"Unable to Add/U";
_requiredmsgbox(("E R R O R"),("Unable to Add/Update New Problem Encountered record due to specified time is too soon."));
 //BA.debugLineNum = 481;BA.debugLine="mskTimeFrom.RequestFocus";
mostCurrent._msktimefrom.RequestFocus();
 //BA.debugLineNum = 482;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 485;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(mskTimeTo.";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._msktimeto.getText()))<=0 || (mostCurrent._msktimeto.getText()).equals("__:__")) { 
 //BA.debugLineNum = 486;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Time Finished canno";
_requiredmsgbox(("ERROR"),("Time Finished cannot be blank!"));
 //BA.debugLineNum = 487;BA.debugLine="mskTimeTo.RequestFocus";
mostCurrent._msktimeto.RequestFocus();
 //BA.debugLineNum = 488;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else if(mostCurrent._validation._istime /*boolean*/ (mostCurrent.activityBA,mostCurrent._msktimeto.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 491;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Time Finished canno";
_requiredmsgbox(("ERROR"),("Time Finished cannot be blank!"));
 //BA.debugLineNum = 492;BA.debugLine="mskTimeTo.RequestFocus";
mostCurrent._msktimeto.RequestFocus();
 //BA.debugLineNum = 493;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 496;BA.debugLine="If DBaseFunctions.IsFuturisticTime(GlobalVar.Tra";
if (mostCurrent._dbasefunctions._isfuturistictime /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._msktimeto.getText())==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 497;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, $\"Unable to Add/U";
_requiredmsgbox(("E R R O R"),("Unable to Add/Update New Problem Encountered record due to specified time is too soon."));
 //BA.debugLineNum = 498;BA.debugLine="mskTimeTo.RequestFocus";
mostCurrent._msktimeto.RequestFocus();
 //BA.debugLineNum = 499;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 502;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(cboPumpAre";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._cbopumparea.getSelectedItem()))<=0) { 
 //BA.debugLineNum = 503;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Pump Area cannot be";
_requiredmsgbox(("ERROR"),("Pump Area cannot be blank!"));
 //BA.debugLineNum = 504;BA.debugLine="cboPumpArea.RequestFocus";
mostCurrent._cbopumparea.RequestFocus();
 //BA.debugLineNum = 505;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 508;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtProbTit";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtprobtitle.getText()))<=0) { 
 //BA.debugLineNum = 509;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Problem Title canno";
_requiredmsgbox(("ERROR"),("Problem Title cannot be blank!"));
 //BA.debugLineNum = 510;BA.debugLine="txtProbTitle.RequestFocus";
mostCurrent._txtprobtitle.RequestFocus();
 //BA.debugLineNum = 511;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 514;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtProbDet";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtprobdetails.getText()))<=0) { 
 //BA.debugLineNum = 515;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Problem Details can";
_requiredmsgbox(("ERROR"),("Problem Details cannot be blank!"));
 //BA.debugLineNum = 516;BA.debugLine="txtProbDetails.RequestFocus";
mostCurrent._txtprobdetails.RequestFocus();
 //BA.debugLineNum = 517;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 520;BA.debugLine="If chkWasSolved.Checked = True Then";
if (mostCurrent._chkwassolved.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 521;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtAction";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtactiontaken.getText()))<=0) { 
 //BA.debugLineNum = 522;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Problem Details ca";
_requiredmsgbox(("ERROR"),("Problem Details cannot be blank!"));
 //BA.debugLineNum = 523;BA.debugLine="txtActionTaken.RequestFocus";
mostCurrent._txtactiontaken.RequestFocus();
 //BA.debugLineNum = 524;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 };
 //BA.debugLineNum = 527;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e54) {
			processBA.setLastException(e54); //BA.debugLineNum = 529;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 530;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("067502144",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 532;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 756;BA.debugLine="Private Sub RequiredMsg_OnPositiveClicked (View As";
 //BA.debugLineNum = 757;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 758;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 759;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsgbox(String _stitle,String _smsg) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 732;BA.debugLine="Private Sub RequiredMsgBox(sTitle As String, sMsg";
 //BA.debugLineNum = 733;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 734;BA.debugLine="Alert.Initialize.Dismiss2";
_alert.Initialize().Dismiss2();
 //BA.debugLineNum = 736;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._redcolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage(_smsg).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"RequiredMsg").SetOnViewBinder(mostCurrent.activityBA,"FontSizeBinder");
 //BA.debugLineNum = 751;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 752;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 753;BA.debugLine="End Sub";
return "";
}
public static boolean  _saveproblemdetails() throws Exception{
boolean _bretval = false;
String _timestart = "";
String _timefinished = "";
int _iareaid = 0;
int _iscritical = 0;
int _wassolved = 0;
String _sproblemtitle = "";
String _sdesc = "";
String _saction = "";
String _slocation = "";
long _ldate = 0L;
String _sdatetimeadded = "";
 //BA.debugLineNum = 537;BA.debugLine="Private Sub SaveProblemDetails() As Boolean";
 //BA.debugLineNum = 538;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 539;BA.debugLine="Dim TimeStart, TimeFinished As String";
_timestart = "";
_timefinished = "";
 //BA.debugLineNum = 540;BA.debugLine="Dim iAreaID As Int";
_iareaid = 0;
 //BA.debugLineNum = 541;BA.debugLine="Dim IsCritical, WasSolved As Int";
_iscritical = 0;
_wassolved = 0;
 //BA.debugLineNum = 542;BA.debugLine="Dim sProblemTitle, sDesc, sAction, sLocation As S";
_sproblemtitle = "";
_sdesc = "";
_saction = "";
_slocation = "";
 //BA.debugLineNum = 544;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 545;BA.debugLine="Dim sDateTimeAdded As String";
_sdatetimeadded = "";
 //BA.debugLineNum = 547;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 548;BA.debugLine="iAreaID = DBaseFunctions.GetIDByCode(\"ID\",\"PumpAr";
_iareaid = mostCurrent._dbasefunctions._getidbycode /*int*/ (mostCurrent.activityBA,"ID","PumpAreas","PumpArea",mostCurrent._cbopumparea.getSelectedItem());
 //BA.debugLineNum = 549;BA.debugLine="TimeStart = sTimeStarted";
_timestart = mostCurrent._stimestarted;
 //BA.debugLineNum = 550;BA.debugLine="TimeFinished = sTimeFinished";
_timefinished = mostCurrent._stimefinished;
 //BA.debugLineNum = 552;BA.debugLine="If chkCritical.Checked = True Then";
if (mostCurrent._chkcritical.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 553;BA.debugLine="IsCritical = 1";
_iscritical = (int) (1);
 }else {
 //BA.debugLineNum = 555;BA.debugLine="IsCritical = 0";
_iscritical = (int) (0);
 };
 //BA.debugLineNum = 558;BA.debugLine="If chkWasSolved.Checked = True Then";
if (mostCurrent._chkwassolved.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 559;BA.debugLine="WasSolved = 1";
_wassolved = (int) (1);
 //BA.debugLineNum = 560;BA.debugLine="sAction = txtActionTaken.Text";
_saction = mostCurrent._txtactiontaken.getText();
 }else {
 //BA.debugLineNum = 562;BA.debugLine="WasSolved = 0";
_wassolved = (int) (0);
 //BA.debugLineNum = 563;BA.debugLine="sAction = \"\"";
_saction = "";
 };
 //BA.debugLineNum = 566;BA.debugLine="sProblemTitle = txtProbTitle.Text";
_sproblemtitle = mostCurrent._txtprobtitle.getText();
 //BA.debugLineNum = 567;BA.debugLine="sDesc = txtProbDetails.Text";
_sdesc = mostCurrent._txtprobdetails.getText();
 //BA.debugLineNum = 569;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 570;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 571;BA.debugLine="sDateTimeAdded = DateTime.Date(lDate)";
_sdatetimeadded = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 573;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 574;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("067567653",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 576;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("067567655",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 577;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("067567656",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 579;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 580;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("067567659",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 582;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 583;BA.debugLine="Try";
try { //BA.debugLineNum = 584;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO Problem";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO ProblemDetails VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._tranheaderid /*int*/ ),(Object)(_timestart),(Object)(_timefinished),(Object)(_iareaid),(Object)(_iscritical),(Object)(_sproblemtitle),(Object)(_sdesc),(Object)(_wassolved),(Object)(_saction),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_sdatetimeadded),(Object)(_slocation),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(("")),(Object)(("0")),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null}));
 //BA.debugLineNum = 586;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 587;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e41) {
			processBA.setLastException(e41); //BA.debugLineNum = 589;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("067567668",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 590;BA.debugLine="ToastMessageShow($\"Unable to save Problem Encoun";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to save Problem Encountered Record due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
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
public static boolean  _savetransheader() throws Exception{
boolean _bretval = false;
long _lngdatetime = 0L;
String _saddedat = "";
 //BA.debugLineNum = 672;BA.debugLine="Private Sub SaveTransHeader() As Boolean";
 //BA.debugLineNum = 673;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 674;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 675;BA.debugLine="Dim sAddedAt As String";
_saddedat = "";
 //BA.debugLineNum = 677;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 678;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 679;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 680;BA.debugLine="sAddedAt= DateTime.Date(lngDateTime)";
_saddedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 683;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 684;BA.debugLine="Try";
try { //BA.debugLineNum = 685;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO TranHea";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO TranHeader VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._branchid /*int*/ ),(Object)(mostCurrent._globalvar._pumphouseid /*int*/ ),(Object)(mostCurrent._globalvar._trandate /*String*/ ),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("1")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_saddedat),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(("0")),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null}));
 //BA.debugLineNum = 688;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 689;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e14) {
			processBA.setLastException(e14); //BA.debugLineNum = 691;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("067698707",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
public static String  _saveupdateproblem_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 811;BA.debugLine="Private Sub SaveUpdateProblem_OnNegativeClicked (V";
 //BA.debugLineNum = 812;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 813;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 814;BA.debugLine="End Sub";
return "";
}
public static String  _saveupdateproblem_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 816;BA.debugLine="Private Sub SaveUpdateProblem_OnPositiveClicked (V";
 //BA.debugLineNum = 817;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 818;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 819;BA.debugLine="If GlobalVar.blnNewProblem = True Then";
if (mostCurrent._globalvar._blnnewproblem /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 820;BA.debugLine="If DBaseFunctions.IsTransactionHeaderExist(Globa";
if (mostCurrent._dbasefunctions._istransactionheaderexist /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 821;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHead";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 822;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeader";
anywheresoftware.b4a.keywords.Common.LogImpl("068157446",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 824;BA.debugLine="If Not(SaveProblemDetails) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_saveproblemdetails())) { 
 //BA.debugLineNum = 825;BA.debugLine="ToastMessageShow($\"Unable to Save Problem Enco";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Problem Encountered Records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 826;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 829;BA.debugLine="If Not(UpdateTranHeader(GlobalVar.TranHeaderID)";
if (anywheresoftware.b4a.keywords.Common.Not(_updatetranheader(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
 //BA.debugLineNum = 830;BA.debugLine="ToastMessageShow($\"Unable to Save Problem Enco";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Problem Encountered Records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 831;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 834;BA.debugLine="If Not(SaveTransHeader) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_savetransheader())) { 
 //BA.debugLineNum = 835;BA.debugLine="ToastMessageShow($\"Unable to Save Problem Enco";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Problem Encountered Records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 836;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 838;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHead";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 840;BA.debugLine="If Not(SaveProblemDetails) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_saveproblemdetails())) { 
 //BA.debugLineNum = 841;BA.debugLine="ToastMessageShow($\"Unable to Save Problem Enco";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Problem Encountered Records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 842;BA.debugLine="Return";
if (true) return "";
 };
 };
 }else {
 //BA.debugLineNum = 847;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeade";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 848;BA.debugLine="If Not(UpdateProblemRecord(GlobalVar.ProblemDeta";
if (anywheresoftware.b4a.keywords.Common.Not(_updateproblemrecord(mostCurrent._globalvar._problemdetailid /*int*/ ,mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
 //BA.debugLineNum = 849;BA.debugLine="ToastMessageShow($\"Unable to Save Problem Encou";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Problem Encountered Records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 850;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 852;BA.debugLine="If Not(UpdateTranHeader(GlobalVar.TranHeaderID))";
if (anywheresoftware.b4a.keywords.Common.Not(_updatetranheader(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
 //BA.debugLineNum = 853;BA.debugLine="ToastMessageShow($\"Unable to Save Problem Encou";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Problem Encountered Records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 854;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 858;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 //BA.debugLineNum = 859;BA.debugLine="End Sub";
return "";
}
public static String  _scvmain_scrollchanged(int _position) throws Exception{
 //BA.debugLineNum = 195;BA.debugLine="Sub scvMain_ScrollChanged(Position As Int)";
 //BA.debugLineNum = 197;BA.debugLine="End Sub";
return "";
}
public static String  _showsavesuccess() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
anywheresoftware.b4a.objects.CSBuilder _cscontent = null;
 //BA.debugLineNum = 862;BA.debugLine="Private Sub ShowSaveSuccess()";
 //BA.debugLineNum = 863;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 864;BA.debugLine="Dim csContent As CSBuilder";
_cscontent = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 866;BA.debugLine="If GlobalVar.blnNewProblem = True Then";
if (mostCurrent._globalvar._blnnewproblem /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 867;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 868;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("New Problem Encountered Record has been successfully saved!"))).PopAll();
 }else {
 //BA.debugLineNum = 870;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 871;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("Problem Encountered Record has been successfully updated!"))).PopAll();
 };
 //BA.debugLineNum = 874;BA.debugLine="MatDialogBuilder.Initialize(\"AddUpdateChlorineRec";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"AddUpdateChlorineRec");
 //BA.debugLineNum = 875;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 876;BA.debugLine="MatDialogBuilder.Content(csContent)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_cscontent.getObject()));
 //BA.debugLineNum = 877;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 878;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 879;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 880;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 881;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 882;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 168;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 169;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 163;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arroww";
 //BA.debugLineNum = 164;BA.debugLine="kBoard.HideKeyboard";
mostCurrent._kboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 165;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 166;BA.debugLine="End Sub";
return "";
}
public static boolean  _updateproblemrecord(int _idetailid,int _itranheaderid) throws Exception{
boolean _bretval = false;
String _timestart = "";
String _timefinished = "";
int _iareaid = 0;
int _iscritical = 0;
int _wassolved = 0;
String _sproblemtitle = "";
String _sdesc = "";
String _saction = "";
String _slocation = "";
long _ldate = 0L;
String _sdatetimeadded = "";
 //BA.debugLineNum = 598;BA.debugLine="Private Sub UpdateProblemRecord(iDetailID As Int,";
 //BA.debugLineNum = 599;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 600;BA.debugLine="Dim TimeStart, TimeFinished As String";
_timestart = "";
_timefinished = "";
 //BA.debugLineNum = 601;BA.debugLine="Dim iAreaID As Int";
_iareaid = 0;
 //BA.debugLineNum = 602;BA.debugLine="Dim IsCritical, WasSolved As Int";
_iscritical = 0;
_wassolved = 0;
 //BA.debugLineNum = 603;BA.debugLine="Dim sProblemTitle, sDesc, sAction, sLocation As S";
_sproblemtitle = "";
_sdesc = "";
_saction = "";
_slocation = "";
 //BA.debugLineNum = 605;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 606;BA.debugLine="Dim sDateTimeAdded As String";
_sdatetimeadded = "";
 //BA.debugLineNum = 608;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 609;BA.debugLine="iAreaID = DBaseFunctions.GetIDByCode(\"ID\",\"PumpAr";
_iareaid = mostCurrent._dbasefunctions._getidbycode /*int*/ (mostCurrent.activityBA,"ID","PumpAreas","PumpArea",mostCurrent._cbopumparea.getSelectedItem());
 //BA.debugLineNum = 610;BA.debugLine="TimeStart = sTimeStarted";
_timestart = mostCurrent._stimestarted;
 //BA.debugLineNum = 611;BA.debugLine="TimeFinished = sTimeFinished";
_timefinished = mostCurrent._stimefinished;
 //BA.debugLineNum = 613;BA.debugLine="If chkCritical.Checked = True Then";
if (mostCurrent._chkcritical.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 614;BA.debugLine="IsCritical = 1";
_iscritical = (int) (1);
 }else {
 //BA.debugLineNum = 616;BA.debugLine="IsCritical = 0";
_iscritical = (int) (0);
 };
 //BA.debugLineNum = 619;BA.debugLine="If chkWasSolved.Checked = True Then";
if (mostCurrent._chkwassolved.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 620;BA.debugLine="WasSolved = 1";
_wassolved = (int) (1);
 //BA.debugLineNum = 621;BA.debugLine="sAction = txtActionTaken.Text";
_saction = mostCurrent._txtactiontaken.getText();
 }else {
 //BA.debugLineNum = 623;BA.debugLine="WasSolved = 0";
_wassolved = (int) (0);
 //BA.debugLineNum = 624;BA.debugLine="sAction = \"\"";
_saction = "";
 };
 //BA.debugLineNum = 626;BA.debugLine="sProblemTitle = txtProbTitle.Text";
_sproblemtitle = mostCurrent._txtprobtitle.getText();
 //BA.debugLineNum = 627;BA.debugLine="sDesc = txtProbDetails.Text";
_sdesc = mostCurrent._txtprobdetails.getText();
 //BA.debugLineNum = 629;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 630;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 631;BA.debugLine="sDateTimeAdded = DateTime.Date(lDate)";
_sdatetimeadded = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 633;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 634;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("067633188",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 636;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("067633190",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 637;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("067633191",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 639;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 640;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("067633194",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 642;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 643;BA.debugLine="Try";
try { //BA.debugLineNum = 644;BA.debugLine="Starter.strCriteria = \"UPDATE ProblemDetails SET";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE ProblemDetails SET "+"TimeStart = ?, "+"TimeFinished = ?, "+"AreaID = ?, "+"IsCritical = ?, "+"ProblemTitle = ?, "+"ProbDesc = ?, "+"WasSolved = ?, "+"ActionTaken = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ?, "+"ModifiedOn = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid)+" "+"AND DetailID = "+BA.NumberToString(_idetailid);
 //BA.debugLineNum = 658;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("067633212",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 660;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{_timestart,_timefinished,BA.NumberToString(_iareaid),BA.NumberToString(_iscritical),_sproblemtitle,_sdesc,BA.NumberToString(_wassolved),_saction,BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_sdatetimeadded,_slocation}));
 //BA.debugLineNum = 661;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 662;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e43) {
			processBA.setLastException(e43); //BA.debugLineNum = 664;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("067633218",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 665;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 667;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 668;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 669;BA.debugLine="End Sub";
return false;
}
public static boolean  _updatetranheader(int _itranheaderid) throws Exception{
boolean _bretval = false;
long _lngdatetime = 0L;
String _smodifiedat = "";
 //BA.debugLineNum = 699;BA.debugLine="Private Sub UpdateTranHeader(iTranHeaderID As Int)";
 //BA.debugLineNum = 700;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 701;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 702;BA.debugLine="Dim sModifiedAt As String";
_smodifiedat = "";
 //BA.debugLineNum = 704;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 706;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 707;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 708;BA.debugLine="sModifiedAt = DateTime.Date(lngDateTime)";
_smodifiedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 710;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 711;BA.debugLine="Try";
try { //BA.debugLineNum = 712;BA.debugLine="Starter.strCriteria = \"UPDATE TranHeader SET \" &";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE TranHeader SET "+"HasProblem = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 718;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{("1"),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_smodifiedat}));
 //BA.debugLineNum = 719;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 720;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e15) {
			processBA.setLastException(e15); //BA.debugLineNum = 722;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("067764247",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 723;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 725;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 726;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 727;BA.debugLine="End Sub";
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
