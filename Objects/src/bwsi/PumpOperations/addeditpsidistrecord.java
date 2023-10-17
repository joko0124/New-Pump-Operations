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

public class addeditpsidistrecord extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static addeditpsidistrecord mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.addeditpsidistrecord");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (addeditpsidistrecord).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.addeditpsidistrecord");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.addeditpsidistrecord", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (addeditpsidistrecord) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (addeditpsidistrecord) Resume **");
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
		return addeditpsidistrecord.class;
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
            BA.LogInfo("** Activity (addeditpsidistrecord) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (addeditpsidistrecord) Pause event (activity is not paused). **");
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
            addeditpsidistrecord mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (addeditpsidistrecord) Resume **");
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
public static int _type_text_flag_no_suggestions = 0;
public de.amberhome.objects.appcompat.ACActionBar _actionbarbutton = null;
public de.amberhome.objects.appcompat.ACToolbarDarkWrapper _toolbar = null;
public de.amberhome.materialdialogs.MaterialDialogBuilderWrapper _matdialogbuilder = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdtxtbox = null;
public com.johan.Vibrate.Vibrate _vibration = null;
public de.amberhome.objects.SnackbarWrapper _snack = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdreading = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fontbold = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcode = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _cbopsipoint = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtlocation = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _chkdefaulttimeread = null;
public flm.b4a.maskededittext.MaskedEditTextWrapper _msktimeread = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtpsirdg = null;
public static String _srdgtime = "";
public anywheresoftware.b4a.objects.EditTextWrapper _txtremarks = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnsaveupdate = null;
public bm.watscho.keyboard.CustomKeyboard _ckeyboard = null;
public anywheresoftware.b4a.objects.IME _imekeyboard = null;
public b4a.example.dateutils _dateutils = null;
public bwsi.PumpOperations.main _main = null;
public bwsi.PumpOperations.actnewproduction _actnewproduction = null;
public bwsi.PumpOperations.addedittimerecord _addedittimerecord = null;
public bwsi.PumpOperations.mainscreen _mainscreen = null;
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
public bwsi.PumpOperations.actpumpoff _actpumpoff = null;
public bwsi.PumpOperations.actrcjofindings _actrcjofindings = null;
public bwsi.PumpOperations.actrepmain _actrepmain = null;
public bwsi.PumpOperations.actsasjofindings _actsasjofindings = null;
public bwsi.PumpOperations.actwaterbalance _actwaterbalance = null;
public bwsi.PumpOperations.addeditchlorinerecord _addeditchlorinerecord = null;
public bwsi.PumpOperations.addeditfmrdg _addeditfmrdg = null;
public bwsi.PumpOperations.addeditnonoperational _addeditnonoperational = null;
public bwsi.PumpOperations.addeditproblem _addeditproblem = null;
public bwsi.PumpOperations.addeditpsirdg _addeditpsirdg = null;
public bwsi.PumpOperations.dbasefunctions _dbasefunctions = null;
public bwsi.PumpOperations.dbutils _dbutils = null;
public bwsi.PumpOperations.edittimerecord _edittimerecord = null;
public bwsi.PumpOperations.firebasemessaging _firebasemessaging = null;
public bwsi.PumpOperations.globalvar _globalvar = null;
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
 //BA.debugLineNum = 55;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 56;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 57;BA.debugLine="Activity.LoadLayout(\"NewPsiRdgDist\")";
mostCurrent._activity.LoadLayout("NewPsiRdgDist",mostCurrent.activityBA);
 //BA.debugLineNum = 59;BA.debugLine="lblCode.Text = GlobalVar.PumpHouseCode & $\" - \"$";
mostCurrent._lblcode.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._pumphousecode /*String*/ +(" - ")+_getpumplocation(mostCurrent._globalvar._pumphouseid /*int*/ )));
 //BA.debugLineNum = 60;BA.debugLine="imeKeyboard.Initialize(\"ime\")";
mostCurrent._imekeyboard.Initialize("ime");
 //BA.debugLineNum = 62;BA.debugLine="InpTyp.Initialize";
_inptyp._initialize /*String*/ (processBA);
 //BA.debugLineNum = 63;BA.debugLine="InpTyp.SetInputType(txtRemarks,Array As Int(InpTy";
_inptyp._setinputtype /*String*/ (mostCurrent._txtremarks,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_correct /*int*/ (),_inptyp._type_text_flag_cap_sentences /*int*/ ()});
 //BA.debugLineNum = 65;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Append";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("SERVICE LINE PRESSURE READING"))).PopAll();
 //BA.debugLineNum = 66;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append($";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 //BA.debugLineNum = 68;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 69;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 70;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 72;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 73;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 74;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 75;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 76;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 77;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 79;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 80;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 81;BA.debugLine="FillPressurePoint(GlobalVar.PumpHouseID)";
_fillpressurepoint(mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 83;BA.debugLine="If GlobalVar.blnNewPSIDist = True Then";
if (mostCurrent._globalvar._blnnewpsidist /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 84;BA.debugLine="ClearUI";
_clearui();
 //BA.debugLineNum = 85;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\" SAVE\"$";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" SAVE")));
 }else {
 //BA.debugLineNum = 87;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\" UPDATE\"$";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" UPDATE")));
 //BA.debugLineNum = 88;BA.debugLine="ClearUI";
_clearui();
 //BA.debugLineNum = 89;BA.debugLine="GetPSIDistRdgDetails(GlobalVar.PSIDistDetailID)";
_getpsidistrdgdetails(mostCurrent._globalvar._psidistdetailid /*int*/ );
 };
 //BA.debugLineNum = 92;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 93;BA.debugLine="FillPressurePoint(GlobalVar.PumpHouseID)";
_fillpressurepoint(mostCurrent._globalvar._pumphouseid /*int*/ );
 };
 //BA.debugLineNum = 95;BA.debugLine="txtPSIRdg.InputType = Bit.Or(txtPSIRdg.InputType,";
mostCurrent._txtpsirdg.setInputType(anywheresoftware.b4a.keywords.Common.Bit.Or(mostCurrent._txtpsirdg.getInputType(),_type_text_flag_no_suggestions));
 //BA.debugLineNum = 96;BA.debugLine="txtPSIRdg.SingleLine = True";
mostCurrent._txtpsirdg.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 97;BA.debugLine="txtPSIRdg.ForceDoneButton = True";
mostCurrent._txtpsirdg.setForceDoneButton(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 98;BA.debugLine="cKeyboard.Initialize(\"CKB\",\"keyboardview_trans\")";
mostCurrent._ckeyboard.Initialize(mostCurrent.activityBA,"CKB","keyboardview_trans");
 //BA.debugLineNum = 99;BA.debugLine="cKeyboard.RegisterEditText(txtPSIRdg,\"txtPSIRdg\",";
mostCurrent._ckeyboard.RegisterEditText((android.widget.EditText)(mostCurrent._txtpsirdg.getObject()),"txtPSIRdg","num",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 101;BA.debugLine="MyFunctions.SetButton(btnSaveUpdate, 25, 25, 25,";
mostCurrent._myfunctions._setbutton /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btnsaveupdate.getObject())),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25));
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
 //BA.debugLineNum = 143;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 144;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 145;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
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
 //BA.debugLineNum = 155;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 117;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 118;BA.debugLine="If Result Then";
if (_result) { 
 //BA.debugLineNum = 119;BA.debugLine="If Permission = Starter.RTP.PERMISSION_READ_EXTE";
if ((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 120;BA.debugLine="LogColor($\"Permission to Read External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("1103743491",("Permission to Read External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 121;BA.debugLine="GlobalVar.ReadStoragePermission = True";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 123;BA.debugLine="LogColor($\"Permission to Write External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("1103743494",("Permission to Write External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 124;BA.debugLine="GlobalVar.WriteStoragePermission = True";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION)) { 
 //BA.debugLineNum = 126;BA.debugLine="LogColor($\"Permission to Access Coarse Location";
anywheresoftware.b4a.keywords.Common.LogImpl("1103743497",("Permission to Access Coarse Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 127;BA.debugLine="GlobalVar.CoarseLocPermission = True";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION)) { 
 //BA.debugLineNum = 129;BA.debugLine="LogColor($\"Permission to Access Fine Location G";
anywheresoftware.b4a.keywords.Common.LogImpl("1103743500",("Permission to Access Fine Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 130;BA.debugLine="GlobalVar.FineLocPermission = True";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 132;BA.debugLine="Starter.StartFLP";
mostCurrent._starter._startflp /*void*/ ();
 }else {
 //BA.debugLineNum = 134;BA.debugLine="GlobalVar.ReadStoragePermission = False";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 135;BA.debugLine="GlobalVar.WriteStoragePermission = False";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 136;BA.debugLine="GlobalVar.CoarseLocPermission = False";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 137;BA.debugLine="GlobalVar.FineLocPermission = False";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 138;BA.debugLine="Result = False";
_result = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 140;BA.debugLine="Log (Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("1103743511",_permission,0);
 //BA.debugLineNum = 141;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 152;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 153;BA.debugLine="End Sub";
return "";
}
public static String  _addupdatepsireading_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 734;BA.debugLine="Private Sub AddUpdatePSIReading_ButtonPressed(mDia";
 //BA.debugLineNum = 735;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE)) {
case 0: {
 //BA.debugLineNum = 737;BA.debugLine="imeKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 738;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
}
;
 //BA.debugLineNum = 740;BA.debugLine="End Sub";
return "";
}
public static String  _btnsaveupdate_click() throws Exception{
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
String _smin = "";
String _shr = "";
int _ihrs = 0;
int _imins = 0;
 //BA.debugLineNum = 178;BA.debugLine="Sub btnSaveUpdate_Click";
 //BA.debugLineNum = 179;BA.debugLine="If Not(IsValidEntries) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_isvalidentries())) { 
if (true) return "";};
 //BA.debugLineNum = 181;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 182;BA.debugLine="Dim sMin, sHr As String";
_smin = "";
_shr = "";
 //BA.debugLineNum = 183;BA.debugLine="sRdgTime = \"\"";
mostCurrent._srdgtime = "";
 //BA.debugLineNum = 185;BA.debugLine="If Not(IsValidEntries) Then Return 'Check Entries";
if (anywheresoftware.b4a.keywords.Common.Not(_isvalidentries())) { 
if (true) return "";};
 //BA.debugLineNum = 187;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d)\", mskTime";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d)",mostCurrent._msktimeread.getText());
 //BA.debugLineNum = 189;BA.debugLine="If Matcher1.Find Then 'Split";
if (_matcher1.Find()) { 
 //BA.debugLineNum = 190;BA.debugLine="Dim iHrs, iMins As Int";
_ihrs = 0;
_imins = 0;
 //BA.debugLineNum = 192;BA.debugLine="iHrs = Matcher1.Group(1)";
_ihrs = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 193;BA.debugLine="iMins = Matcher1.Group(2)";
_imins = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 195;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_imins)))==1) { 
 //BA.debugLineNum = 196;BA.debugLine="sMin = $\"0\"$ & iMins";
_smin = ("0")+BA.NumberToString(_imins);
 }else {
 //BA.debugLineNum = 198;BA.debugLine="sMin = iMins";
_smin = BA.NumberToString(_imins);
 };
 //BA.debugLineNum = 201;BA.debugLine="If iHrs = 0 Then '12 AM";
if (_ihrs==0) { 
 //BA.debugLineNum = 202;BA.debugLine="sHr = 12";
_shr = BA.NumberToString(12);
 //BA.debugLineNum = 203;BA.debugLine="sRdgTime = sHr & \":\" & sMin & \" AM\"";
mostCurrent._srdgtime = _shr+":"+_smin+" AM";
 }else if(_ihrs>0 && _ihrs<12) { 
 //BA.debugLineNum = 205;BA.debugLine="sHr = iHrs";
_shr = BA.NumberToString(_ihrs);
 //BA.debugLineNum = 206;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr)) = 1";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_shr))==1) { 
 //BA.debugLineNum = 207;BA.debugLine="sRdgTime = $\"0\"$ & sHr & \":\" & sMin & \" AM\"";
mostCurrent._srdgtime = ("0")+_shr+":"+_smin+" AM";
 }else {
 //BA.debugLineNum = 209;BA.debugLine="sRdgTime = sHr & \":\" & sMin & \" AM\"";
mostCurrent._srdgtime = _shr+":"+_smin+" AM";
 };
 }else if(_ihrs==12) { 
 //BA.debugLineNum = 213;BA.debugLine="sHr = 12";
_shr = BA.NumberToString(12);
 //BA.debugLineNum = 214;BA.debugLine="sRdgTime = sHr & \":\" & sMin & \" PM\"";
mostCurrent._srdgtime = _shr+":"+_smin+" PM";
 }else {
 //BA.debugLineNum = 216;BA.debugLine="sHr = iHrs - 12";
_shr = BA.NumberToString(_ihrs-12);
 //BA.debugLineNum = 217;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr)) = 1";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_shr))==1) { 
 //BA.debugLineNum = 218;BA.debugLine="sRdgTime = $\"0\"$ & sHr & \":\" & sMin & \" PM\"";
mostCurrent._srdgtime = ("0")+_shr+":"+_smin+" PM";
 }else {
 //BA.debugLineNum = 220;BA.debugLine="sRdgTime = sHr & \":\" & sMin & \" PM\"";
mostCurrent._srdgtime = _shr+":"+_smin+" PM";
 };
 };
 };
 //BA.debugLineNum = 225;BA.debugLine="LogColor($\"Reading Time: \"$ & sRdgTime,Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("1104202287",("Reading Time: ")+mostCurrent._srdgtime,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 227;BA.debugLine="ConfirmSaveUpdateReading";
_confirmsaveupdatereading();
 //BA.debugLineNum = 228;BA.debugLine="End Sub";
return "";
}
public static String  _cbopsipoint_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 230;BA.debugLine="Sub cboPSIPoint_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 231;BA.debugLine="LogColor($\"Selected \"$ & Position & \" - \" & Value";
anywheresoftware.b4a.keywords.Common.LogImpl("1104267777",("Selected ")+BA.NumberToString(_position)+" - "+BA.ObjectToString(_value),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 232;BA.debugLine="txtLocation.Text = GetLocation(Value)";
mostCurrent._txtlocation.setText(BA.ObjectToCharSequence(_getlocation(BA.ObjectToString(_value))));
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}
public static String  _checkpermissions() throws Exception{
 //BA.debugLineNum = 105;BA.debugLine="Private Sub CheckPermissions";
 //BA.debugLineNum = 106;BA.debugLine="Log(\"Checking Permissions\")";
anywheresoftware.b4a.keywords.Common.LogImpl("1103677953","Checking Permissions",0);
 //BA.debugLineNum = 108;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE);
 //BA.debugLineNum = 109;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE);
 //BA.debugLineNum = 110;BA.debugLine="Starter.RTP.GetAllSafeDirsExternal(\"\")";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .GetAllSafeDirsExternal("");
 //BA.debugLineNum = 112;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION);
 //BA.debugLineNum = 113;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 114;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 115;BA.debugLine="End Sub";
return "";
}
public static String  _chkdefaulttimeread_checkedchange(boolean _checked) throws Exception{
String _shour = "";
String _smin = "";
long _lhour = 0L;
long _lmin = 0L;
 //BA.debugLineNum = 235;BA.debugLine="Sub chkDefaultTimeRead_CheckedChange(Checked As Bo";
 //BA.debugLineNum = 236;BA.debugLine="Dim sHour As String";
_shour = "";
 //BA.debugLineNum = 237;BA.debugLine="Dim sMin As String";
_smin = "";
 //BA.debugLineNum = 238;BA.debugLine="Dim lHour, lMin As Long";
_lhour = 0L;
_lmin = 0L;
 //BA.debugLineNum = 240;BA.debugLine="If Checked = True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 241;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 242;BA.debugLine="lHour = DateTime.GetHour(DateTime.Now)";
_lhour = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 243;BA.debugLine="lMin = DateTime.GetMinute(DateTime.Now)";
_lmin = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 245;BA.debugLine="If GlobalVar.SF.Len(lHour) = 1 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_lhour))==1) { 
 //BA.debugLineNum = 246;BA.debugLine="sHour = $\"0\"$ & lHour";
_shour = ("0")+BA.NumberToString(_lhour);
 }else {
 //BA.debugLineNum = 248;BA.debugLine="sHour = lHour";
_shour = BA.NumberToString(_lhour);
 };
 //BA.debugLineNum = 251;BA.debugLine="If GlobalVar.SF.Len(lMin) = 1 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_lmin))==1) { 
 //BA.debugLineNum = 252;BA.debugLine="sMin = $\"0\"$ & lMin";
_smin = ("0")+BA.NumberToString(_lmin);
 }else {
 //BA.debugLineNum = 254;BA.debugLine="sMin = lMin";
_smin = BA.NumberToString(_lmin);
 };
 //BA.debugLineNum = 257;BA.debugLine="mskTimeRead.Text = sHour & \":\" & sMin";
mostCurrent._msktimeread.setText((Object)(_shour+":"+_smin));
 //BA.debugLineNum = 258;BA.debugLine="mskTimeRead.Enabled = False";
mostCurrent._msktimeread.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 260;BA.debugLine="mskTimeRead.Enabled = True";
mostCurrent._msktimeread.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 261;BA.debugLine="mskTimeRead.Text = \"__:__\"";
mostCurrent._msktimeread.setText((Object)("__:__"));
 //BA.debugLineNum = 262;BA.debugLine="mskTimeRead.RequestFocus";
mostCurrent._msktimeread.RequestFocus();
 //BA.debugLineNum = 263;BA.debugLine="imeKeyboard.ShowKeyboard(mskTimeRead)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._msktimeread.getObject()));
 };
 //BA.debugLineNum = 266;BA.debugLine="End Sub";
return "";
}
public static String  _clearui() throws Exception{
 //BA.debugLineNum = 485;BA.debugLine="Private Sub ClearUI";
 //BA.debugLineNum = 486;BA.debugLine="cboPSIPoint.Clear";
mostCurrent._cbopsipoint.Clear();
 //BA.debugLineNum = 487;BA.debugLine="txtLocation.Text = \"\"";
mostCurrent._txtlocation.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 488;BA.debugLine="chkDefaultTimeRead.Checked = False";
mostCurrent._chkdefaulttimeread.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 489;BA.debugLine="mskTimeRead.Text = \"__:__\"";
mostCurrent._msktimeread.setText((Object)("__:__"));
 //BA.debugLineNum = 490;BA.debugLine="txtPSIRdg.Text = \"\"";
mostCurrent._txtpsirdg.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 491;BA.debugLine="txtRemarks.Text = \"\"";
mostCurrent._txtremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 492;BA.debugLine="CDtxtBox.Initialize(Colors.Transparent,0)";
mostCurrent._cdtxtbox.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 494;BA.debugLine="txtLocation.Background = CDtxtBox";
mostCurrent._txtlocation.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 495;BA.debugLine="mskTimeRead.Background = CDtxtBox";
mostCurrent._msktimeread.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 496;BA.debugLine="txtRemarks.Background = CDtxtBox";
mostCurrent._txtremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 497;BA.debugLine="cboPSIPoint.Background = CDtxtBox";
mostCurrent._cbopsipoint.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 499;BA.debugLine="cdReading.Initialize2(Colors.Black,0,0,0)";
mostCurrent._cdreading.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (0),(int) (0),(int) (0));
 //BA.debugLineNum = 500;BA.debugLine="txtPSIRdg.Background = cdReading";
mostCurrent._txtpsirdg.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdreading.getObject()));
 //BA.debugLineNum = 501;BA.debugLine="End Sub";
return "";
}
public static String  _confirmsaveupdatereading() throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
String _stitle = "";
String _smsg = "";
 //BA.debugLineNum = 642;BA.debugLine="Private Sub ConfirmSaveUpdateReading";
 //BA.debugLineNum = 643;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 644;BA.debugLine="Dim sTitle, sMsg As String";
_stitle = "";
_smsg = "";
 //BA.debugLineNum = 646;BA.debugLine="If GlobalVar.blnNewPSIDist = True Then";
if (mostCurrent._globalvar._blnnewpsidist /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 647;BA.debugLine="sTitle = $\"SAVE NEW PSI READING\"$";
_stitle = ("SAVE NEW PSI READING");
 //BA.debugLineNum = 648;BA.debugLine="sMsg = $\"Save New Pressure Reading?\"$";
_smsg = ("Save New Pressure Reading?");
 }else {
 //BA.debugLineNum = 650;BA.debugLine="sTitle = $\"UPDATE PSI READING\"$";
_stitle = ("UPDATE PSI READING");
 //BA.debugLineNum = 651;BA.debugLine="sMsg = $\"Update Pressure Reading?\"$";
_smsg = ("Update Pressure Reading?");
 };
 //BA.debugLineNum = 654;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._bluecolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage(_smsg).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"SavePSIReading").SetOnNegativeClicked(mostCurrent.activityBA,"SavePSIReading");
 //BA.debugLineNum = 671;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 672;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 673;BA.debugLine="End Sub";
return "";
}
public static void  _fillpressurepoint(int _ipumpid) throws Exception{
ResumableSub_FillPressurePoint rsub = new ResumableSub_FillPressurePoint(null,_ipumpid);
rsub.resume(processBA, null);
}
public static class ResumableSub_FillPressurePoint extends BA.ResumableSub {
public ResumableSub_FillPressurePoint(bwsi.PumpOperations.addeditpsidistrecord parent,int _ipumpid) {
this.parent = parent;
this._ipumpid = _ipumpid;
}
bwsi.PumpOperations.addeditpsidistrecord parent;
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
 //BA.debugLineNum = 518;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 519;BA.debugLine="cboPSIPoint.Clear";
parent.mostCurrent._cbopsipoint.Clear();
 //BA.debugLineNum = 520;BA.debugLine="Try";
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
 //BA.debugLineNum = 521;BA.debugLine="Starter.strCriteria = \"SELECT PPointNo FROM tblP";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT PPointNo FROM tblPressurePoint WHERE PumpHouseID = "+BA.NumberToString(_ipumpid);
 //BA.debugLineNum = 523;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 524;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 17;
return;
case 17:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 526;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 527;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 528;BA.debugLine="cboPSIPoint.Add(GlobalVar.SF.Upper(RS.GetStrin";
parent.mostCurrent._cbopsipoint.Add(BA.ObjectToCharSequence(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("PPointNo"))));
 if (true) break;

case 10:
//C
this.state = 13;
;
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 531;BA.debugLine="snack.Initialize(\"\", Activity, $\"Cannot get Pre";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("Cannot get Pressure Point due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 532;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 533;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 534;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 535;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1104923154",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 13:
//C
this.state = 16;
;
 //BA.debugLineNum = 537;BA.debugLine="txtLocation.Text = GetLocation(cboPSIPoint.Selec";
parent.mostCurrent._txtlocation.setText(BA.ObjectToCharSequence(_getlocation(parent.mostCurrent._cbopsipoint.getSelectedItem())));
 if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
 //BA.debugLineNum = 540;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 541;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 542;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 543;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 544;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1104923163",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 546;BA.debugLine="End Sub";
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
public static String  _getlocation(String _svalue) throws Exception{
String _sretval = "";
 //BA.debugLineNum = 548;BA.debugLine="Private Sub GetLocation (sValue As String) As Stri";
 //BA.debugLineNum = 549;BA.debugLine="Dim sRetval As String";
_sretval = "";
 //BA.debugLineNum = 550;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 551;BA.debugLine="Try";
try { //BA.debugLineNum = 552;BA.debugLine="Starter.strCriteria = \"SELECT PLocation FROM tbl";
mostCurrent._starter._strcriteria /*String*/  = "SELECT PLocation FROM tblPressurePoint WHERE UPPER(PPointNo) = '"+_svalue+"'";
 //BA.debugLineNum = 553;BA.debugLine="sRetval = Starter.DBCon.ExecQuerySingleResult(St";
_sretval = mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ );
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 555;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 556;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1104988680",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 558;BA.debugLine="Return sRetval";
if (true) return _sretval;
 //BA.debugLineNum = 559;BA.debugLine="End Sub";
return "";
}
public static int  _getpointid(String _svalue) throws Exception{
int _iretval = 0;
 //BA.debugLineNum = 561;BA.debugLine="Private Sub GetPointID (sValue As String) As Int";
 //BA.debugLineNum = 562;BA.debugLine="Dim iRetval As Int";
_iretval = 0;
 //BA.debugLineNum = 563;BA.debugLine="iRetval = 0";
_iretval = (int) (0);
 //BA.debugLineNum = 564;BA.debugLine="Try";
try { //BA.debugLineNum = 565;BA.debugLine="Starter.strCriteria = \"SELECT ID FROM tblPressur";
mostCurrent._starter._strcriteria /*String*/  = "SELECT ID FROM tblPressurePoint WHERE UPPER(PPointNo) = '"+_svalue+"'";
 //BA.debugLineNum = 566;BA.debugLine="iRetval = Starter.DBCon.ExecQuerySingleResult(St";
_iretval = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 568;BA.debugLine="iRetval = 0";
_iretval = (int) (0);
 //BA.debugLineNum = 569;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1105054216",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 571;BA.debugLine="Return iRetval";
if (true) return _iretval;
 //BA.debugLineNum = 572;BA.debugLine="End Sub";
return 0;
}
public static void  _getpsidistrdgdetails(int _irdgid) throws Exception{
ResumableSub_GetPSIDistRdgDetails rsub = new ResumableSub_GetPSIDistRdgDetails(null,_irdgid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetPSIDistRdgDetails extends BA.ResumableSub {
public ResumableSub_GetPSIDistRdgDetails(bwsi.PumpOperations.addeditpsidistrecord parent,int _irdgid) {
this.parent = parent;
this._irdgid = _irdgid;
}
bwsi.PumpOperations.addeditpsidistrecord parent;
int _irdgid;
String _spointno = "";
String _stimerdg = "";
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
 //BA.debugLineNum = 337;BA.debugLine="Dim sPointNo As String";
_spointno = "";
 //BA.debugLineNum = 338;BA.debugLine="Dim sTimeRdg As String";
_stimerdg = "";
 //BA.debugLineNum = 339;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 341;BA.debugLine="Try";
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
 //BA.debugLineNum = 342;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 344;BA.debugLine="Starter.strCriteria = \"SELECT PressurePointRdg.R";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT PressurePointRdg.RdgID, "+"PressurePoint.PumpHouseID, PressurePointRdg.PSIPointID, PressurePoint.PPointNo,PressurePoint.PLocation, "+"PressurePointRdg.RdgTime, PressurePointRdg.PSIReading, PressurePointRdg.Remarks "+"FROM PressureDistReadings AS PressurePointRdg "+"INNER JOIN tblPressurePoint AS PressurePoint ON PressurePointRdg.PSIPointID = PressurePoint.ID "+"WHERE PressurePointRdg.RdgID = "+BA.NumberToString(_irdgid);
 //BA.debugLineNum = 351;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 352;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 46;
return;
case 46:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 354;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 355;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 356;BA.debugLine="GlobalVar.PumpHouseID = RS.GetInt(\"PumpHouseID\"";
parent.mostCurrent._globalvar._pumphouseid /*int*/  = _rs.GetInt("PumpHouseID");
 //BA.debugLineNum = 357;BA.debugLine="sPointNo = RS.GetString(\"PPointNo\")";
_spointno = _rs.GetString("PPointNo");
 //BA.debugLineNum = 358;BA.debugLine="cboPSIPoint.SelectedIndex = cboPSIPoint.IndexOf";
parent.mostCurrent._cbopsipoint.setSelectedIndex(parent.mostCurrent._cbopsipoint.IndexOf(BA.ObjectToCharSequence(_spointno)));
 //BA.debugLineNum = 359;BA.debugLine="txtLocation.Text = RS.GetString(\"PLocation\")";
parent.mostCurrent._txtlocation.setText(BA.ObjectToCharSequence(_rs.GetString("PLocation")));
 //BA.debugLineNum = 360;BA.debugLine="chkDefaultTimeRead.Checked = False";
parent.mostCurrent._chkdefaulttimeread.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 361;BA.debugLine="sTimeRdg = RS.GetString(\"RdgTime\")";
_stimerdg = _rs.GetString("RdgTime");
 //BA.debugLineNum = 362;BA.debugLine="txtPSIRdg.Text = RS.GetString(\"PSIReading\")";
parent.mostCurrent._txtpsirdg.setText(BA.ObjectToCharSequence(_rs.GetString("PSIReading")));
 //BA.debugLineNum = 363;BA.debugLine="txtRemarks.Text = RS.GetString(\"Remarks\")";
parent.mostCurrent._txtremarks.setText(BA.ObjectToCharSequence(_rs.GetString("Remarks")));
 //BA.debugLineNum = 365;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 366;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d) (\\S\\S)\"";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d) (\\S\\S)",_stimerdg);
 //BA.debugLineNum = 367;BA.debugLine="If Matcher1.Find Then";
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
 //BA.debugLineNum = 368;BA.debugLine="Dim iHrs, iMins As Int";
_ihrs = 0;
_imins = 0;
 //BA.debugLineNum = 369;BA.debugLine="Dim AmPm As String";
_ampm = "";
 //BA.debugLineNum = 370;BA.debugLine="Dim sMin As String";
_smin = "";
 //BA.debugLineNum = 372;BA.debugLine="iHrs = Matcher1.Group(1)";
_ihrs = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 373;BA.debugLine="iMins = Matcher1.Group(2)";
_imins = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 374;BA.debugLine="AmPm = Matcher1.Group(3)";
_ampm = _matcher1.Group((int) (3));
 //BA.debugLineNum = 376;BA.debugLine="LogColor(AmPm,Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("1104661032",_ampm,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 378;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins))";
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
 //BA.debugLineNum = 379;BA.debugLine="sMin = $\"0\"$ & iMins";
_smin = ("0")+BA.NumberToString(_imins);
 if (true) break;

case 14:
//C
this.state = 15;
 //BA.debugLineNum = 381;BA.debugLine="sMin = iMins";
_smin = BA.NumberToString(_imins);
 if (true) break;
;
 //BA.debugLineNum = 384;BA.debugLine="If AmPm = \"AM\" Then";

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
 //BA.debugLineNum = 385;BA.debugLine="If iHrs = 12 Then";
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
 //BA.debugLineNum = 386;BA.debugLine="mskTimeRead.Text = $\"00:\"$ & sMin";
parent.mostCurrent._msktimeread.setText((Object)(("00:")+_smin));
 if (true) break;

case 22:
//C
this.state = 23;
 //BA.debugLineNum = 388;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iHrs))";
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
 //BA.debugLineNum = 389;BA.debugLine="mskTimeRead.Text = $\"0\"$ & iHrs & $\":\"$ & s";
parent.mostCurrent._msktimeread.setText((Object)(("0")+BA.NumberToString(_ihrs)+(":")+_smin));
 if (true) break;

case 27:
//C
this.state = 28;
 //BA.debugLineNum = 391;BA.debugLine="mskTimeRead.Text = iHrs & $\":\"$ & sMin";
parent.mostCurrent._msktimeread.setText((Object)(BA.NumberToString(_ihrs)+(":")+_smin));
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
 //BA.debugLineNum = 395;BA.debugLine="If iHrs < 12 Then";
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
 //BA.debugLineNum = 396;BA.debugLine="mskTimeRead.Text = (iHrs + 12) & $\":\"$ & sMi";
parent.mostCurrent._msktimeread.setText((Object)(BA.NumberToString((_ihrs+12))+(":")+_smin));
 if (true) break;

case 36:
//C
this.state = 37;
 //BA.debugLineNum = 398;BA.debugLine="mskTimeRead.Text = iHrs & $\":\"$ & sMin";
parent.mostCurrent._msktimeread.setText((Object)(BA.NumberToString(_ihrs)+(":")+_smin));
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
 //BA.debugLineNum = 405;BA.debugLine="snack.Initialize(\"\", Activity,$\"Cannot Return P";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("Cannot Return PSI Distribution Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 406;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 407;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 408;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 409;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1104661065",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 42:
//C
this.state = 45;
;
 //BA.debugLineNum = 412;BA.debugLine="Starter.strCriteria = \"\"";
parent.mostCurrent._starter._strcriteria /*String*/  = "";
 //BA.debugLineNum = 413;BA.debugLine="LogColor(Starter.strCriteria, Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("1104661069",parent.mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 if (true) break;

case 44:
//C
this.state = 45;
this.catchState = 0;
 //BA.debugLineNum = 416;BA.debugLine="snack.Initialize(\"\", Activity,$\"Cannot Return PS";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("Cannot Return PSI Distribution Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 417;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 418;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 419;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 420;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1104661076",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 45:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 422;BA.debugLine="End Sub";
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
public static String  _getpumplocation(int _ipumpid) throws Exception{
String _sretval = "";
 //BA.debugLineNum = 503;BA.debugLine="Private Sub GetPumpLocation (iPumpID As Int) As St";
 //BA.debugLineNum = 504;BA.debugLine="Dim sRetval As String";
_sretval = "";
 //BA.debugLineNum = 505;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 506;BA.debugLine="Try";
try { //BA.debugLineNum = 507;BA.debugLine="Starter.strCriteria = \"SELECT PumpLocation FROM";
mostCurrent._starter._strcriteria /*String*/  = "SELECT PumpLocation FROM tblPumpStation WHERE StationID = "+BA.NumberToString(_ipumpid);
 //BA.debugLineNum = 508;BA.debugLine="sRetval = Starter.DBCon.ExecQuerySingleResult(St";
_sretval = mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ );
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 510;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 511;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1104857608",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 513;BA.debugLine="Return sRetval";
if (true) return _sretval;
 //BA.debugLineNum = 514;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 25;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 26;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 27;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private CD, CDtxtBox As ColorDrawable";
mostCurrent._cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdtxtbox = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 32;BA.debugLine="Private vibration As B4Avibrate";
mostCurrent._vibration = new com.johan.Vibrate.Vibrate();
 //BA.debugLineNum = 33;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private cdReading As ColorDrawable";
mostCurrent._cdreading = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 35;BA.debugLine="Private Font As Typeface = Typeface.LoadFromAsset";
mostCurrent._font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont.ttf")));
 //BA.debugLineNum = 36;BA.debugLine="Private FontBold As Typeface = Typeface.LoadFromA";
mostCurrent._fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont_bold.ttf")));
 //BA.debugLineNum = 39;BA.debugLine="Private lblCode As Label";
mostCurrent._lblcode = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private cboPSIPoint As ACSpinner";
mostCurrent._cbopsipoint = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private txtLocation As EditText";
mostCurrent._txtlocation = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private chkDefaultTimeRead As CheckBox";
mostCurrent._chkdefaulttimeread = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private mskTimeRead As MaskedEditText";
mostCurrent._msktimeread = new flm.b4a.maskededittext.MaskedEditTextWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private txtPSIRdg As EditText";
mostCurrent._txtpsirdg = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private sRdgTime As String";
mostCurrent._srdgtime = "";
 //BA.debugLineNum = 46;BA.debugLine="Private txtRemarks As EditText";
mostCurrent._txtremarks = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private btnSaveUpdate As ACButton";
mostCurrent._btnsaveupdate = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private cKeyboard As CustomKeyboard";
mostCurrent._ckeyboard = new bm.watscho.keyboard.CustomKeyboard();
 //BA.debugLineNum = 50;BA.debugLine="Private imeKeyboard As IME";
mostCurrent._imekeyboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public static boolean  _isvalidentries() throws Exception{
 //BA.debugLineNum = 574;BA.debugLine="Private Sub IsValidEntries () As Boolean";
 //BA.debugLineNum = 575;BA.debugLine="Try";
try { //BA.debugLineNum = 576;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(cboPSIPoin";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._cbopsipoint.getSelectedItem()))<=0) { 
 //BA.debugLineNum = 577;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Pressure Point cann";
_requiredmsgbox(("ERROR"),("Pressure Point cannot be blank!"));
 //BA.debugLineNum = 578;BA.debugLine="cboPSIPoint.RequestFocus";
mostCurrent._cbopsipoint.RequestFocus();
 //BA.debugLineNum = 579;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 582;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(mskTimeRea";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._msktimeread.getText()))<=0 || (mostCurrent._msktimeread.getText()).equals("__:__")) { 
 //BA.debugLineNum = 583;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Reading Time cannot";
_requiredmsgbox(("ERROR"),("Reading Time cannot be blank!"));
 //BA.debugLineNum = 584;BA.debugLine="mskTimeRead.RequestFocus";
mostCurrent._msktimeread.RequestFocus();
 //BA.debugLineNum = 585;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else if(mostCurrent._validation._istime /*boolean*/ (mostCurrent.activityBA,mostCurrent._msktimeread.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 588;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid Reading Tim";
_requiredmsgbox(("ERROR"),("Invalid Reading Time!"));
 //BA.debugLineNum = 589;BA.debugLine="mskTimeRead.RequestFocus";
mostCurrent._msktimeread.RequestFocus();
 //BA.debugLineNum = 590;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 593;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtPSIRdg.";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtpsirdg.getText()))<=0) { 
 //BA.debugLineNum = 594;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Pressure Reading ca";
_requiredmsgbox(("ERROR"),("Pressure Reading cannot be blank!"));
 //BA.debugLineNum = 595;BA.debugLine="txtPSIRdg.RequestFocus";
mostCurrent._txtpsirdg.RequestFocus();
 //BA.debugLineNum = 596;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 599;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e23) {
			processBA.setLastException(e23); //BA.debugLineNum = 601;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 602;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1105119772",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 604;BA.debugLine="End Sub";
return false;
}
public static String  _msktimeread_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 268;BA.debugLine="Sub mskTimeRead_FocusChanged(HasFocus As Boolean)";
 //BA.debugLineNum = 270;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 21;BA.debugLine="Private InpTyp As SLInpTypeConst";
_inptyp = new bwsi.PumpOperations.slinptypeconst();
 //BA.debugLineNum = 22;BA.debugLine="Dim TYPE_TEXT_FLAG_NO_SUGGESTIONS  As Int = 0x800";
_type_text_flag_no_suggestions = (int) (0x80000);
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsg_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 635;BA.debugLine="Private Sub RequiredMsg_OnPositiveClicked (View As";
 //BA.debugLineNum = 636;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 637;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 638;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsgbox(String _stitle,String _smsg) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 610;BA.debugLine="Private Sub RequiredMsgBox(sTitle As String, sMsg";
 //BA.debugLineNum = 611;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 612;BA.debugLine="Alert.Initialize.Dismiss2";
_alert.Initialize().Dismiss2();
 //BA.debugLineNum = 614;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._redcolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage(_smsg).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"RequiredMsg").SetOnViewBinder(mostCurrent.activityBA,"FontSizeBinder");
 //BA.debugLineNum = 629;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 630;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 631;BA.debugLine="End Sub";
return "";
}
public static boolean  _savepsidistrdg() throws Exception{
boolean _bretval = false;
String _dateread = "";
String _timeread = "";
int _psipointid = 0;
String _sdatetime = "";
long _ldate = 0L;
int _ipsi = 0;
String _slocation = "";
String _sremarks = "";
 //BA.debugLineNum = 286;BA.debugLine="Private Sub SavePSIDistRdg() As Boolean";
 //BA.debugLineNum = 287;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 288;BA.debugLine="Dim DateRead, TimeRead As String";
_dateread = "";
_timeread = "";
 //BA.debugLineNum = 289;BA.debugLine="Dim PSIPointID As Int";
_psipointid = 0;
 //BA.debugLineNum = 290;BA.debugLine="Dim sDateTime As String";
_sdatetime = "";
 //BA.debugLineNum = 291;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 292;BA.debugLine="Dim iPSI As Int";
_ipsi = 0;
 //BA.debugLineNum = 293;BA.debugLine="Dim sLocation, sRemarks As String";
_slocation = "";
_sremarks = "";
 //BA.debugLineNum = 295;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 296;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 297;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 298;BA.debugLine="sDateTime = DateTime.Date(lDate)";
_sdatetime = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 301;BA.debugLine="TimeRead = sRdgTime";
_timeread = mostCurrent._srdgtime;
 //BA.debugLineNum = 302;BA.debugLine="DateRead = GlobalVar.TranDate";
_dateread = mostCurrent._globalvar._trandate /*String*/ ;
 //BA.debugLineNum = 305;BA.debugLine="PSIPointID = GetPointID(cboPSIPoint.SelectedItem)";
_psipointid = _getpointid(mostCurrent._cbopsipoint.getSelectedItem());
 //BA.debugLineNum = 306;BA.debugLine="iPSI = GlobalVar.SF.Val(txtPSIRdg.Text)";
_ipsi = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtpsirdg.getText()));
 //BA.debugLineNum = 307;BA.debugLine="sRemarks = txtRemarks.Text";
_sremarks = mostCurrent._txtremarks.getText();
 //BA.debugLineNum = 308;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 310;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("1104595480",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 312;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("1104595482",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 313;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("1104595483",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 315;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 316;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("1104595486",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 318;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 319;BA.debugLine="Try";
try { //BA.debugLineNum = 320;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO Pressur";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO PressureDistReadings VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._branchid /*int*/ ),(Object)(_psipointid),(Object)(_dateread),(Object)(_timeread),(Object)(_ipsi),(Object)(_sremarks),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_sdatetime),(Object)(_slocation),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(("")),(Object)(("0")),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null}));
 //BA.debugLineNum = 322;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 323;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e29) {
			processBA.setLastException(e29); //BA.debugLineNum = 325;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1104595495",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 326;BA.debugLine="ToastMessageShow($\"Unable to save Pressure from";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to save Pressure from Distribution Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 327;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 329;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 330;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 331;BA.debugLine="End Sub";
return false;
}
public static String  _savepsireading_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 676;BA.debugLine="Private Sub SavePSIReading_OnNegativeClicked (View";
 //BA.debugLineNum = 677;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 678;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 679;BA.debugLine="End Sub";
return "";
}
public static String  _savepsireading_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 681;BA.debugLine="Private Sub SavePSIReading_OnPositiveClicked (View";
 //BA.debugLineNum = 682;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 683;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 684;BA.debugLine="If GlobalVar.blnNewPSIDist = True Then";
if (mostCurrent._globalvar._blnnewpsidist /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 685;BA.debugLine="If Not(SavePSIDistRdg) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_savepsidistrdg())) { 
 //BA.debugLineNum = 686;BA.debugLine="RequiredMsgBox($\"ERRORS SAVING\"$,$\"Unable to Sa";
_requiredmsgbox(("ERRORS SAVING"),("Unable to Save Pressure Reading due to")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage());
 //BA.debugLineNum = 687;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 690;BA.debugLine="UpdatePSIDistRdg(GlobalVar.PSIDistDetailID)";
_updatepsidistrdg(mostCurrent._globalvar._psidistdetailid /*int*/ );
 };
 //BA.debugLineNum = 692;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 //BA.debugLineNum = 693;BA.debugLine="End Sub";
return "";
}
public static String  _showsavesuccess() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
anywheresoftware.b4a.objects.CSBuilder _cscontent = null;
 //BA.debugLineNum = 712;BA.debugLine="Private Sub ShowSaveSuccess()";
 //BA.debugLineNum = 713;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 714;BA.debugLine="Dim csContent As CSBuilder";
_cscontent = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 716;BA.debugLine="If GlobalVar.blnNewPSIDist = True Then";
if (mostCurrent._globalvar._blnnewpsidist /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 717;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 718;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("New Pressure Reading has been successfully saved!"))).PopAll();
 }else {
 //BA.debugLineNum = 720;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 721;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("Pressure Reading has been successfully updated!"))).PopAll();
 };
 //BA.debugLineNum = 724;BA.debugLine="MatDialogBuilder.Initialize(\"AddUpdatePSIReading\"";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"AddUpdatePSIReading");
 //BA.debugLineNum = 725;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 726;BA.debugLine="MatDialogBuilder.Content(csContent)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_cscontent.getObject()));
 //BA.debugLineNum = 727;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 728;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 729;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 730;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 731;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 732;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 174;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 166;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arroww";
 //BA.debugLineNum = 167;BA.debugLine="If cKeyboard.IsSoftKeyboardVisible = True Then";
if (mostCurrent._ckeyboard.IsSoftKeyboardVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 168;BA.debugLine="cKeyboard.HideKeyboard";
mostCurrent._ckeyboard.HideKeyboard();
 }else {
 //BA.debugLineNum = 170;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
return "";
}
public static String  _txtpsirdg_enterpressed() throws Exception{
 //BA.debugLineNum = 272;BA.debugLine="Sub txtPSIRdg_EnterPressed";
 //BA.debugLineNum = 273;BA.debugLine="txtRemarks.RequestFocus";
mostCurrent._txtremarks.RequestFocus();
 //BA.debugLineNum = 274;BA.debugLine="cKeyboard.HideKeyboard";
mostCurrent._ckeyboard.HideKeyboard();
 //BA.debugLineNum = 275;BA.debugLine="End Sub";
return "";
}
public static String  _txtpsirdg_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 277;BA.debugLine="Sub txtPSIRdg_FocusChanged (HasFocus As Boolean)";
 //BA.debugLineNum = 278;BA.debugLine="If HasFocus = True Then";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 279;BA.debugLine="cKeyboard.ShowKeyboard(txtPSIRdg)";
mostCurrent._ckeyboard.ShowKeyboard((android.widget.EditText)(mostCurrent._txtpsirdg.getObject()));
 }else {
 //BA.debugLineNum = 281;BA.debugLine="cKeyboard.HideKeyboard";
mostCurrent._ckeyboard.HideKeyboard();
 };
 //BA.debugLineNum = 283;BA.debugLine="End Sub";
return "";
}
public static boolean  _updatepsidistrdg(int _irdgid) throws Exception{
boolean _bretval = false;
String _dateread = "";
String _timeread = "";
int _psipointid = 0;
String _sdatetime = "";
long _ldate = 0L;
int _ipsi = 0;
String _slocation = "";
String _sremarks = "";
 //BA.debugLineNum = 424;BA.debugLine="Private Sub UpdatePSIDistRdg(iRdgID As Int) As Boo";
 //BA.debugLineNum = 425;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 426;BA.debugLine="Dim DateRead, TimeRead As String";
_dateread = "";
_timeread = "";
 //BA.debugLineNum = 427;BA.debugLine="Dim PSIPointID As Int";
_psipointid = 0;
 //BA.debugLineNum = 428;BA.debugLine="Dim sDateTime As String";
_sdatetime = "";
 //BA.debugLineNum = 429;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 430;BA.debugLine="Dim iPSI As Int";
_ipsi = 0;
 //BA.debugLineNum = 431;BA.debugLine="Dim sLocation, sRemarks As String";
_slocation = "";
_sremarks = "";
 //BA.debugLineNum = 433;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 434;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 435;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 436;BA.debugLine="sDateTime = DateTime.Date(lDate)";
_sdatetime = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 439;BA.debugLine="TimeRead = sRdgTime";
_timeread = mostCurrent._srdgtime;
 //BA.debugLineNum = 440;BA.debugLine="DateRead = GlobalVar.TranDate";
_dateread = mostCurrent._globalvar._trandate /*String*/ ;
 //BA.debugLineNum = 443;BA.debugLine="PSIPointID = GetPointID(cboPSIPoint.SelectedItem)";
_psipointid = _getpointid(mostCurrent._cbopsipoint.getSelectedItem());
 //BA.debugLineNum = 444;BA.debugLine="iPSI = GlobalVar.SF.Val(txtPSIRdg.Text)";
_ipsi = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtpsirdg.getText()));
 //BA.debugLineNum = 445;BA.debugLine="sRemarks = txtRemarks.Text";
_sremarks = mostCurrent._txtremarks.getText();
 //BA.debugLineNum = 446;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 448;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("1104726552",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 450;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("1104726554",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 451;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("1104726555",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 453;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 454;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("1104726558",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 456;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 457;BA.debugLine="Try";
try { //BA.debugLineNum = 458;BA.debugLine="Starter.strCriteria = \"UPDATE PressureDistReadin";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE PressureDistReadings SET "+"PSIPointID = ?, "+"RdgDate = ?, "+"RdgTime = ?, "+"PSIReading = ?, "+"Remarks = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ?, "+"ModifiedOn = ? "+"WHERE RdgID = "+BA.NumberToString(_irdgid);
 //BA.debugLineNum = 468;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("1104726572",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 470;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_psipointid),_dateread,_timeread,BA.NumberToString(_ipsi),_sremarks,BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_sdatetime,_slocation}));
 //BA.debugLineNum = 471;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 } 
       catch (Exception e30) {
			processBA.setLastException(e30); //BA.debugLineNum = 473;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1104726577",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 474;BA.debugLine="ToastMessageShow($\"Unable to Update Pressure Rea";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Update Pressure Reading upon Distribution due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 475;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 477;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 478;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 480;BA.debugLine="End Sub";
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
