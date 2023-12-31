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

public class addeditfmrdg extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static addeditfmrdg mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.addeditfmrdg");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (addeditfmrdg).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.addeditfmrdg");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.addeditfmrdg", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (addeditfmrdg) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (addeditfmrdg) Resume **");
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
		return addeditfmrdg.class;
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
            BA.LogInfo("** Activity (addeditfmrdg) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (addeditfmrdg) Pause event (activity is not paused). **");
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
            addeditfmrdg mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (addeditfmrdg) Resume **");
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
public static anywheresoftware.b4a.audio.SoundPoolWrapper _soundsalarmchannel = null;
public static anywheresoftware.b4a.obejcts.TTS _tts1 = null;
public static int _type_text_flag_no_suggestions = 0;
public de.amberhome.objects.appcompat.ACActionBar _actionbarbutton = null;
public de.amberhome.objects.appcompat.ACToolbarDarkWrapper _toolbar = null;
public anywheresoftware.b4a.object.XmlLayoutBuilder _xmlicon = null;
public de.amberhome.materialdialogs.MaterialDialogBuilderWrapper _matdialogbuilder = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdtxtbox = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdfixedtext = null;
public de.amberhome.objects.SnackbarWrapper _snack = null;
public anywheresoftware.b4a.objects.CSBuilder _csans = null;
public anywheresoftware.b4a.objects.IME _imekeyboard = null;
public com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdok = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdcancel = null;
public com.johan.Vibrate.Vibrate _vibration = null;
public static long[] _vibratepattern = null;
public static int _soundid = 0;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _chkdefaulttimeread = null;
public flm.b4a.maskededittext.MaskedEditTextWrapper _msktimeread = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtfmrdg = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtfmrdgremarks = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btncancel = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnsaveupdate = null;
public static double _dlastfmrdg = 0;
public static double _dpreviousrdg = 0;
public static double _dbackflow = 0;
public anywheresoftware.b4a.objects.PanelWrapper _pnlzeroprodmsg = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnzerook = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnzerocancel = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblzeromsg = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtzeroremarks = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlnegativeprodmsg = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnnegativeok = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblnegativemsg = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _chkbackflow = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtbackflowcum = null;
public static boolean _isnegativerdg = false;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnnegativecancel = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtnegativeremarks = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlhighprodmsg = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnhighcancel = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnhighok = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlhighbillconfirmation = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtpresrdgconfirm = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnhbconfirmcancel = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnhbconfirmsave = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txthighremarks = null;
public static String _shighrdg = "";
public anywheresoftware.b4a.objects.PanelWrapper _pnllowprodmsg = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnlowcancel = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnlowok = null;
public static String _srdgtime = "";
public bm.watscho.keyboard.CustomKeyboard _ckeyboard = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlkeyboard = null;
public static String _suploadreading = "";
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
 //BA.debugLineNum = 109;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 110;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 111;BA.debugLine="Activity.LoadLayout(\"PumpFMReading\")";
mostCurrent._activity.LoadLayout("PumpFMReading",mostCurrent.activityBA);
 //BA.debugLineNum = 113;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 114;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 115;BA.debugLine="jo.InitializeStatic(\"java.util.Locale\").RunMethod";
_jo.InitializeStatic("java.util.Locale").RunMethod("setDefault",new Object[]{_jo.GetField("US")});
 //BA.debugLineNum = 117;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 118;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 119;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 120;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 122;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 123;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 125;BA.debugLine="If GlobalVar.blnNewFMRdg = True Then";
if (mostCurrent._globalvar._blnnewfmrdg /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 126;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Appen";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("ADD NEW FLOW METER READING RECORD"))).PopAll();
 //BA.debugLineNum = 127;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append(";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 }else {
 //BA.debugLineNum = 129;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Appen";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("EDIT FLOW METER READING RECORD"))).PopAll();
 //BA.debugLineNum = 130;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append(";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 };
 //BA.debugLineNum = 133;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 134;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 135;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 137;BA.debugLine="txtFMRdg.InputType = Bit.Or(txtFMRdg.InputType,TY";
mostCurrent._txtfmrdg.setInputType(anywheresoftware.b4a.keywords.Common.Bit.Or(mostCurrent._txtfmrdg.getInputType(),_type_text_flag_no_suggestions));
 //BA.debugLineNum = 138;BA.debugLine="txtFMRdg.SingleLine = True";
mostCurrent._txtfmrdg.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 139;BA.debugLine="txtFMRdg.ForceDoneButton = True";
mostCurrent._txtfmrdg.setForceDoneButton(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 140;BA.debugLine="cKeyboard.Initialize(\"CKB\",\"keyboardview_trans\")";
mostCurrent._ckeyboard.Initialize(mostCurrent.activityBA,"CKB","keyboardview_trans");
 //BA.debugLineNum = 141;BA.debugLine="cKeyboard.RegisterEditText(txtFMRdg,\"txtFMRdg\",\"n";
mostCurrent._ckeyboard.RegisterEditText((android.widget.EditText)(mostCurrent._txtfmrdg.getObject()),"txtFMRdg","num",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 143;BA.debugLine="txtFMRdg.Padding = Array As Int (0dip, -5dip, 0di";
mostCurrent._txtfmrdg.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),(int) (-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),(int) (-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)))});
 //BA.debugLineNum = 145;BA.debugLine="InpTyp.Initialize";
_inptyp._initialize /*String*/ (processBA);
 //BA.debugLineNum = 146;BA.debugLine="InpTyp.SetInputType(txtFMRdgRemarks,Array As Int(";
_inptyp._setinputtype /*String*/ (mostCurrent._txtfmrdgremarks,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_correct /*int*/ (),_inptyp._type_text_flag_cap_sentences /*int*/ ()});
 //BA.debugLineNum = 149;BA.debugLine="imeKeyboard.Initialize(\"\")";
mostCurrent._imekeyboard.Initialize("");
 //BA.debugLineNum = 150;BA.debugLine="soundsAlarmChannel.Initialize(2)";
_soundsalarmchannel.Initialize((int) (2));
 //BA.debugLineNum = 151;BA.debugLine="ClearUI";
_clearui();
 //BA.debugLineNum = 152;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 154;BA.debugLine="If GlobalVar.blnNewFMRdg = True Then";
if (mostCurrent._globalvar._blnnewfmrdg /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 155;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\"  SAVE\"$";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+("  SAVE")));
 //BA.debugLineNum = 156;BA.debugLine="btnCancel.Text = Chr(0xE5C9) & $\"  CANCEL\"$";
mostCurrent._btncancel.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe5c9)))+("  CANCEL")));
 }else {
 //BA.debugLineNum = 158;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\" UPDATE\"$";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" UPDATE")));
 //BA.debugLineNum = 159;BA.debugLine="btnCancel.Text = Chr(0xE5C9) & $\"  CANCEL\"$";
mostCurrent._btncancel.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe5c9)))+("  CANCEL")));
 //BA.debugLineNum = 160;BA.debugLine="GetFMRdgRecord(GlobalVar.FMRdgDetailID)";
_getfmrdgrecord(mostCurrent._globalvar._fmrdgdetailid /*int*/ );
 };
 //BA.debugLineNum = 163;BA.debugLine="dLastFMRdg = DBaseFunctions.GetLastFMReading(Glob";
_dlastfmrdg = mostCurrent._dbasefunctions._getlastfmreading /*double*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 165;BA.debugLine="csAns.Initialize.Color(Colors.White).Bold.Append(";
mostCurrent._csans.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Bold().Append(BA.ObjectToCharSequence(("YES"))).PopAll();
 //BA.debugLineNum = 170;BA.debugLine="MyFunctions.SetButton(btnSaveUpdate, 25, 25, 25,";
mostCurrent._myfunctions._setbutton /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btnsaveupdate.getObject())),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25));
 //BA.debugLineNum = 171;BA.debugLine="MyFunctions.SetCancelButton(btnCancel, 25, 25, 25";
mostCurrent._myfunctions._setcancelbutton /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btncancel.getObject())),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25));
 //BA.debugLineNum = 172;BA.debugLine="sRdgTime = \"\"";
mostCurrent._srdgtime = "";
 //BA.debugLineNum = 174;BA.debugLine="cdCancel.Initialize2(GlobalVar.RedColor, 20, 0, C";
mostCurrent._cdcancel.Initialize2((int) (mostCurrent._globalvar._redcolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 175;BA.debugLine="cdOK.Initialize2(GlobalVar.GreenColor, 20, 0, Col";
mostCurrent._cdok.Initialize2((int) (mostCurrent._globalvar._greencolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 177;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 178;BA.debugLine="pnlKeyboard.Initialize(\"\")";
mostCurrent._pnlkeyboard.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 179;BA.debugLine="pnlKeyboard.BringToFront";
mostCurrent._pnlkeyboard.BringToFront();
 //BA.debugLineNum = 181;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
 //BA.debugLineNum = 248;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 250;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 251;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 221;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 222;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 223;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 224;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 226;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 228;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 242;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 243;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 195;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 196;BA.debugLine="If Result Then";
if (_result) { 
 //BA.debugLineNum = 197;BA.debugLine="If Permission = Starter.RTP.PERMISSION_READ_EXTE";
if ((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 198;BA.debugLine="LogColor($\"Permission to Read External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("763111171",("Permission to Read External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 199;BA.debugLine="GlobalVar.ReadStoragePermission = True";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 201;BA.debugLine="LogColor($\"Permission to Write External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("763111174",("Permission to Write External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 202;BA.debugLine="GlobalVar.WriteStoragePermission = True";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION)) { 
 //BA.debugLineNum = 204;BA.debugLine="LogColor($\"Permission to Access Coarse Location";
anywheresoftware.b4a.keywords.Common.LogImpl("763111177",("Permission to Access Coarse Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 205;BA.debugLine="GlobalVar.CoarseLocPermission = True";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION)) { 
 //BA.debugLineNum = 207;BA.debugLine="LogColor($\"Permission to Access Fine Location G";
anywheresoftware.b4a.keywords.Common.LogImpl("763111180",("Permission to Access Fine Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 208;BA.debugLine="GlobalVar.FineLocPermission = True";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 210;BA.debugLine="Starter.StartFLP";
mostCurrent._starter._startflp /*void*/ ();
 }else {
 //BA.debugLineNum = 212;BA.debugLine="GlobalVar.ReadStoragePermission = False";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 213;BA.debugLine="GlobalVar.WriteStoragePermission = False";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 214;BA.debugLine="GlobalVar.CoarseLocPermission = False";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 215;BA.debugLine="GlobalVar.FineLocPermission = False";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 216;BA.debugLine="Result = False";
_result = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 218;BA.debugLine="Log (Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("763111191",_permission,0);
 //BA.debugLineNum = 219;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 230;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 231;BA.debugLine="SoundID = soundsAlarmChannel.Load(File.DirAssets,";
_soundid = _soundsalarmchannel.Load(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"beep.wav");
 //BA.debugLineNum = 232;BA.debugLine="vibratePattern = Array As Long(500, 500, 300, 500";
_vibratepattern = new long[]{(long) (500),(long) (500),(long) (300),(long) (500)};
 //BA.debugLineNum = 233;BA.debugLine="If soundsAlarmChannel.IsInitialized = False Then";
if (_soundsalarmchannel.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_soundsalarmchannel.Initialize((int) (2));};
 //BA.debugLineNum = 234;BA.debugLine="txtFMRdg.InputType = Bit.Or(txtFMRdg.InputType,TY";
mostCurrent._txtfmrdg.setInputType(anywheresoftware.b4a.keywords.Common.Bit.Or(mostCurrent._txtfmrdg.getInputType(),_type_text_flag_no_suggestions));
 //BA.debugLineNum = 235;BA.debugLine="txtFMRdg.SingleLine = True";
mostCurrent._txtfmrdg.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 236;BA.debugLine="txtFMRdg.ForceDoneButton = True";
mostCurrent._txtfmrdg.setForceDoneButton(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 237;BA.debugLine="cKeyboard.Initialize(\"CKB\",\"keyboardview_trans\")";
mostCurrent._ckeyboard.Initialize(mostCurrent.activityBA,"CKB","keyboardview_trans");
 //BA.debugLineNum = 238;BA.debugLine="cKeyboard.RegisterEditText(txtFMRdg,\"txtFMRdg\",\"n";
mostCurrent._ckeyboard.RegisterEditText((android.widget.EditText)(mostCurrent._txtfmrdg.getObject()),"txtFMRdg","num",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 240;BA.debugLine="End Sub";
return "";
}
public static String  _addpumptimeonrecords_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 1456;BA.debugLine="Private Sub AddPumpTimeOnRecords_ButtonPressed(mDi";
 //BA.debugLineNum = 1457;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE)) {
case 0: {
 //BA.debugLineNum = 1459;BA.debugLine="imeKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 1460;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
}
;
 //BA.debugLineNum = 1462;BA.debugLine="End Sub";
return "";
}
public static String  _btncancel_click() throws Exception{
 //BA.debugLineNum = 612;BA.debugLine="Sub btnCancel_Click";
 //BA.debugLineNum = 613;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 614;BA.debugLine="End Sub";
return "";
}
public static String  _btnhbconfirmcancel_click() throws Exception{
 //BA.debugLineNum = 1233;BA.debugLine="Sub btnHBConfirmCancel_Click";
 //BA.debugLineNum = 1234;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1235;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1236;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1237;BA.debugLine="txtFMRdg.RequestFocus";
mostCurrent._txtfmrdg.RequestFocus();
 //BA.debugLineNum = 1238;BA.debugLine="End Sub";
return "";
}
public static String  _btnhbconfirmsave_click() throws Exception{
 //BA.debugLineNum = 1229;BA.debugLine="Sub btnHBConfirmSave_Click";
 //BA.debugLineNum = 1230;BA.debugLine="ConfirmSaveRdg";
_confirmsaverdg();
 //BA.debugLineNum = 1231;BA.debugLine="End Sub";
return "";
}
public static String  _btnhighcancel_click() throws Exception{
 //BA.debugLineNum = 1191;BA.debugLine="Sub btnHighCancel_Click";
 //BA.debugLineNum = 1192;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1193;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1194;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1195;BA.debugLine="txtFMRdg.RequestFocus";
mostCurrent._txtfmrdg.RequestFocus();
 //BA.debugLineNum = 1196;BA.debugLine="End Sub";
return "";
}
public static String  _btnhighok_click() throws Exception{
 //BA.debugLineNum = 1176;BA.debugLine="Sub btnHighOk_Click";
 //BA.debugLineNum = 1177;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1178;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1180;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtHighRema";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txthighremarks.getText()))<=0) { 
 //BA.debugLineNum = 1181;BA.debugLine="RequiredMsgBox($\"REMARKS REQUIRED\"$, $\"Remarks i";
_requiredmsgbox(("REMARKS REQUIRED"),("Remarks is required for this reading!"));
 //BA.debugLineNum = 1182;BA.debugLine="txtHighRemarks.RequestFocus";
mostCurrent._txthighremarks.RequestFocus();
 //BA.debugLineNum = 1183;BA.debugLine="imeKeyboard.ShowKeyboard(txtHighRemarks)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._txthighremarks.getObject()));
 //BA.debugLineNum = 1184;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 1186;BA.debugLine="txtFMRdgRemarks.Text = txtHighRemarks.Text";
mostCurrent._txtfmrdgremarks.setText(BA.ObjectToCharSequence(mostCurrent._txthighremarks.getText()));
 };
 //BA.debugLineNum = 1188;BA.debugLine="ShowHighConfirmationWarning";
_showhighconfirmationwarning();
 //BA.debugLineNum = 1189;BA.debugLine="End Sub";
return "";
}
public static String  _btnlowcancel_click() throws Exception{
 //BA.debugLineNum = 1150;BA.debugLine="Sub btnLowCancel_Click";
 //BA.debugLineNum = 1152;BA.debugLine="End Sub";
return "";
}
public static String  _btnlowok_click() throws Exception{
 //BA.debugLineNum = 1133;BA.debugLine="Sub btnLowOk_Click";
 //BA.debugLineNum = 1134;BA.debugLine="pnlLowProdMsg.Visible = False";
mostCurrent._pnllowprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1136;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1137;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1139;BA.debugLine="If GlobalVar.SF.Len(txtFMRdgRemarks.Text) <= 0 Th";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtfmrdgremarks.getText())<=0) { 
 //BA.debugLineNum = 1140;BA.debugLine="RequiredMsgBox($\"REMARKS REQUIRED\"$, $\"Remarks i";
_requiredmsgbox(("REMARKS REQUIRED"),("Remarks is required for this reading!"));
 //BA.debugLineNum = 1141;BA.debugLine="txtFMRdgRemarks.RequestFocus";
mostCurrent._txtfmrdgremarks.RequestFocus();
 //BA.debugLineNum = 1142;BA.debugLine="imeKeyboard.ShowKeyboard(txtFMRdgRemarks)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._txtfmrdgremarks.getObject()));
 //BA.debugLineNum = 1143;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 1145;BA.debugLine="SaveUpdateFMReading";
_saveupdatefmreading();
 };
 //BA.debugLineNum = 1148;BA.debugLine="End Sub";
return "";
}
public static String  _btnnegativecancel_click() throws Exception{
 //BA.debugLineNum = 1110;BA.debugLine="Sub btnNegativeCancel_Click";
 //BA.debugLineNum = 1111;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1112;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1113;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1114;BA.debugLine="End Sub";
return "";
}
public static String  _btnnegativeok_click() throws Exception{
 //BA.debugLineNum = 1092;BA.debugLine="Sub btnNegativeOk_Click";
 //BA.debugLineNum = 1093;BA.debugLine="If GlobalVar.blnNewFMRdg = True Then";
if (mostCurrent._globalvar._blnnewfmrdg /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1094;BA.debugLine="If GlobalVar.SF.Len(txtBackFlowCum.Text) <=0 The";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtbackflowcum.getText())<=0) { 
 //BA.debugLineNum = 1095;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, $\"Unable to save";
_requiredmsgbox(("E R R O R"),("Unable to save Reading due to Back flow in CuM is blank!"));
 //BA.debugLineNum = 1096;BA.debugLine="txtBackFlowCum.RequestFocus";
mostCurrent._txtbackflowcum.RequestFocus();
 //BA.debugLineNum = 1097;BA.debugLine="dBackFlow = 0";
_dbackflow = 0;
 //BA.debugLineNum = 1098;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1100;BA.debugLine="If GlobalVar.SF.Len(txtNegativeRemarks.Text) <=0";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtnegativeremarks.getText())<=0) { 
 //BA.debugLineNum = 1101;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, $\"Unable to save";
_requiredmsgbox(("E R R O R"),("Unable to save Reading due to Reading Remarks is required!"));
 //BA.debugLineNum = 1102;BA.debugLine="txtNegativeRemarks.RequestFocus";
mostCurrent._txtnegativeremarks.RequestFocus();
 //BA.debugLineNum = 1103;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1105;BA.debugLine="ConfirmSaveNegativeRdg";
_confirmsavenegativerdg();
 }else {
 };
 //BA.debugLineNum = 1108;BA.debugLine="End Sub";
return "";
}
public static String  _btnsaveupdate_click() throws Exception{
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
String _smin = "";
String _shr = "";
int _ihrs = 0;
int _imins = 0;
 //BA.debugLineNum = 524;BA.debugLine="Sub btnSaveUpdate_Click";
 //BA.debugLineNum = 525;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 526;BA.debugLine="Dim sMin, sHr As String";
_smin = "";
_shr = "";
 //BA.debugLineNum = 527;BA.debugLine="sRdgTime = \"\"";
mostCurrent._srdgtime = "";
 //BA.debugLineNum = 529;BA.debugLine="If Not(IsValidEntries) Then Return 'Check Entries";
if (anywheresoftware.b4a.keywords.Common.Not(_isvalidentries())) { 
if (true) return "";};
 //BA.debugLineNum = 531;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d)\", mskTime";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d)",mostCurrent._msktimeread.getText());
 //BA.debugLineNum = 533;BA.debugLine="If Matcher1.Find Then 'Split";
if (_matcher1.Find()) { 
 //BA.debugLineNum = 534;BA.debugLine="Dim iHrs, iMins As Int";
_ihrs = 0;
_imins = 0;
 //BA.debugLineNum = 536;BA.debugLine="iHrs = Matcher1.Group(1)";
_ihrs = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 537;BA.debugLine="iMins = Matcher1.Group(2)";
_imins = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 539;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_imins)))==1) { 
 //BA.debugLineNum = 540;BA.debugLine="sMin = $\"0\"$ & iMins";
_smin = ("0")+BA.NumberToString(_imins);
 }else {
 //BA.debugLineNum = 542;BA.debugLine="sMin = iMins";
_smin = BA.NumberToString(_imins);
 };
 //BA.debugLineNum = 545;BA.debugLine="If iHrs = 0 Then '12 AM";
if (_ihrs==0) { 
 //BA.debugLineNum = 546;BA.debugLine="sHr = 12";
_shr = BA.NumberToString(12);
 //BA.debugLineNum = 547;BA.debugLine="sRdgTime = sHr & \":\" & sMin & \" AM\"";
mostCurrent._srdgtime = _shr+":"+_smin+" AM";
 }else if(_ihrs>0 && _ihrs<12) { 
 //BA.debugLineNum = 549;BA.debugLine="sHr = iHrs";
_shr = BA.NumberToString(_ihrs);
 //BA.debugLineNum = 550;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr)) = 1";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_shr))==1) { 
 //BA.debugLineNum = 551;BA.debugLine="sRdgTime = $\"0\"$ & sHr & \":\" & sMin & \" AM\"";
mostCurrent._srdgtime = ("0")+_shr+":"+_smin+" AM";
 }else {
 //BA.debugLineNum = 553;BA.debugLine="sRdgTime = sHr & \":\" & sMin & \" AM\"";
mostCurrent._srdgtime = _shr+":"+_smin+" AM";
 };
 }else if(_ihrs==12) { 
 //BA.debugLineNum = 557;BA.debugLine="sHr = 12";
_shr = BA.NumberToString(12);
 //BA.debugLineNum = 558;BA.debugLine="sRdgTime = sHr & \":\" & sMin & \" PM\"";
mostCurrent._srdgtime = _shr+":"+_smin+" PM";
 }else {
 //BA.debugLineNum = 560;BA.debugLine="sHr = iHrs - 12";
_shr = BA.NumberToString(_ihrs-12);
 //BA.debugLineNum = 561;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr)) = 1";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_shr))==1) { 
 //BA.debugLineNum = 562;BA.debugLine="sRdgTime = $\"0\"$ & sHr & \":\" & sMin & \" PM\"";
mostCurrent._srdgtime = ("0")+_shr+":"+_smin+" PM";
 }else {
 //BA.debugLineNum = 564;BA.debugLine="sRdgTime = sHr & \":\" & sMin & \" PM\"";
mostCurrent._srdgtime = _shr+":"+_smin+" PM";
 };
 };
 };
 //BA.debugLineNum = 569;BA.debugLine="LogColor($\"Reading Time: \"$ & sRdgTime,Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("764225325",("Reading Time: ")+mostCurrent._srdgtime,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 571;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 572;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeaderID";
anywheresoftware.b4a.keywords.Common.LogImpl("764225328",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 574;BA.debugLine="If DBaseFunctions.IsFMRdgDetailHeaderIDExist(Glob";
if (mostCurrent._dbasefunctions._isfmrdgdetailheaderidexist /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._tranheaderid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 575;BA.debugLine="If DBaseFunctions.IsReadTimeOverlapse(sRdgTime,";
if (mostCurrent._dbasefunctions._isreadtimeoverlapse /*boolean*/ (mostCurrent.activityBA,mostCurrent._srdgtime,mostCurrent._globalvar._tranheaderid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 576;BA.debugLine="RequiredMsgBox($\"TIME OVERLAPPING\"$, $\"Unable t";
_requiredmsgbox(("TIME OVERLAPPING"),("Unable to Save transaction for it will overlapped existing reading!"));
 //BA.debugLineNum = 577;BA.debugLine="chkDefaultTimeRead.Checked = False";
mostCurrent._chkdefaulttimeread.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 578;BA.debugLine="mskTimeRead.RequestFocus";
mostCurrent._msktimeread.RequestFocus();
 //BA.debugLineNum = 579;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 583;BA.debugLine="If GlobalVar.SF.Val(txtFMRdg.Text) < dLastFMRdg T";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())<_dlastfmrdg) { 
 //BA.debugLineNum = 585;BA.debugLine="isNegativeRdg = True";
_isnegativerdg = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 586;BA.debugLine="ShowNegativeWarning";
_shownegativewarning();
 //BA.debugLineNum = 587;BA.debugLine="Return";
if (true) return "";
 }else if(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())==_dlastfmrdg) { 
 //BA.debugLineNum = 590;BA.debugLine="isNegativeRdg = False";
_isnegativerdg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 591;BA.debugLine="ShowZeroWarning";
_showzerowarning();
 //BA.debugLineNum = 592;BA.debugLine="Return";
if (true) return "";
 }else if((mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())-_dlastfmrdg)>=200) { 
 //BA.debugLineNum = 595;BA.debugLine="isNegativeRdg = False";
_isnegativerdg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 596;BA.debugLine="sHighRdg = txtFMRdg.Text";
mostCurrent._shighrdg = mostCurrent._txtfmrdg.getText();
 //BA.debugLineNum = 597;BA.debugLine="ShowHighWarning";
_showhighwarning();
 }else if((mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())-_dlastfmrdg)<=50) { 
 //BA.debugLineNum = 601;BA.debugLine="isNegativeRdg = False";
_isnegativerdg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 602;BA.debugLine="ConfirmSaveRdg";
_confirmsaverdg();
 }else {
 //BA.debugLineNum = 604;BA.debugLine="isNegativeRdg = False";
_isnegativerdg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 605;BA.debugLine="ConfirmSaveRdg";
_confirmsaverdg();
 };
 //BA.debugLineNum = 609;BA.debugLine="End Sub";
return "";
}
public static String  _btnzerocancel_click() throws Exception{
 //BA.debugLineNum = 1050;BA.debugLine="Sub btnZeroCancel_Click";
 //BA.debugLineNum = 1051;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1052;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1053;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1054;BA.debugLine="txtFMRdg.RequestFocus";
mostCurrent._txtfmrdg.RequestFocus();
 //BA.debugLineNum = 1055;BA.debugLine="End Sub";
return "";
}
public static String  _btnzerook_click() throws Exception{
 //BA.debugLineNum = 1035;BA.debugLine="Sub btnZeroOk_Click";
 //BA.debugLineNum = 1036;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1037;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1039;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtZeroRema";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtzeroremarks.getText()))<=0) { 
 //BA.debugLineNum = 1040;BA.debugLine="RequiredMsgBox($\"REMARKS REQUIRED\"$, $\"Remarks i";
_requiredmsgbox(("REMARKS REQUIRED"),("Remarks is required for this reading!"));
 //BA.debugLineNum = 1041;BA.debugLine="txtZeroRemarks.RequestFocus";
mostCurrent._txtzeroremarks.RequestFocus();
 //BA.debugLineNum = 1042;BA.debugLine="imeKeyboard.ShowKeyboard(txtZeroRemarks)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._txtzeroremarks.getObject()));
 //BA.debugLineNum = 1043;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 1045;BA.debugLine="txtFMRdgRemarks.Text = txtZeroRemarks.Text";
mostCurrent._txtfmrdgremarks.setText(BA.ObjectToCharSequence(mostCurrent._txtzeroremarks.getText()));
 };
 //BA.debugLineNum = 1047;BA.debugLine="ConfirmSaveRdg";
_confirmsaverdg();
 //BA.debugLineNum = 1048;BA.debugLine="End Sub";
return "";
}
public static String  _checkpermissions() throws Exception{
 //BA.debugLineNum = 183;BA.debugLine="Private Sub CheckPermissions";
 //BA.debugLineNum = 184;BA.debugLine="Log(\"Checking Permissions\")";
anywheresoftware.b4a.keywords.Common.LogImpl("763045633","Checking Permissions",0);
 //BA.debugLineNum = 186;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE);
 //BA.debugLineNum = 187;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE);
 //BA.debugLineNum = 188;BA.debugLine="Starter.RTP.GetAllSafeDirsExternal(\"\")";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .GetAllSafeDirsExternal("");
 //BA.debugLineNum = 190;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION);
 //BA.debugLineNum = 191;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 192;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 193;BA.debugLine="End Sub";
return "";
}
public static String  _chkbackflow_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 1082;BA.debugLine="Sub chkBackFlow_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 1083;BA.debugLine="If Checked = True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1084;BA.debugLine="txtBackFlowCum.Enabled = True";
mostCurrent._txtbackflowcum.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1085;BA.debugLine="txtBackFlowCum.RequestFocus";
mostCurrent._txtbackflowcum.RequestFocus();
 }else {
 //BA.debugLineNum = 1087;BA.debugLine="txtBackFlowCum.Enabled = False";
mostCurrent._txtbackflowcum.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1088;BA.debugLine="txtBackFlowCum.Text = \"\"";
mostCurrent._txtbackflowcum.setText(BA.ObjectToCharSequence(""));
 };
 //BA.debugLineNum = 1090;BA.debugLine="End Sub";
return "";
}
public static String  _chkdefaulttimeread_checkedchange(boolean _checked) throws Exception{
String _shour = "";
String _smin = "";
long _lhour = 0L;
long _lmin = 0L;
 //BA.debugLineNum = 480;BA.debugLine="Sub chkDefaultTimeRead_CheckedChange(Checked As Bo";
 //BA.debugLineNum = 481;BA.debugLine="Dim sHour As String";
_shour = "";
 //BA.debugLineNum = 482;BA.debugLine="Dim sMin As String";
_smin = "";
 //BA.debugLineNum = 483;BA.debugLine="Dim lHour, lMin As Long";
_lhour = 0L;
_lmin = 0L;
 //BA.debugLineNum = 485;BA.debugLine="If Checked = True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 486;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 487;BA.debugLine="lHour = DateTime.GetHour(DateTime.Now)";
_lhour = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 488;BA.debugLine="lMin = DateTime.GetMinute(DateTime.Now)";
_lmin = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 490;BA.debugLine="If GlobalVar.SF.Len(lHour) = 1 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_lhour))==1) { 
 //BA.debugLineNum = 491;BA.debugLine="sHour = $\"0\"$ & lHour";
_shour = ("0")+BA.NumberToString(_lhour);
 }else {
 //BA.debugLineNum = 493;BA.debugLine="sHour = lHour";
_shour = BA.NumberToString(_lhour);
 };
 //BA.debugLineNum = 496;BA.debugLine="If GlobalVar.SF.Len(lMin) = 1 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_lmin))==1) { 
 //BA.debugLineNum = 497;BA.debugLine="sMin = $\"0\"$ & lMin";
_smin = ("0")+BA.NumberToString(_lmin);
 }else {
 //BA.debugLineNum = 499;BA.debugLine="sMin = lMin";
_smin = BA.NumberToString(_lmin);
 };
 //BA.debugLineNum = 502;BA.debugLine="mskTimeRead.Text = sHour & \":\" & sMin";
mostCurrent._msktimeread.setText((Object)(_shour+":"+_smin));
 //BA.debugLineNum = 505;BA.debugLine="mskTimeRead.Enabled = False";
mostCurrent._msktimeread.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 507;BA.debugLine="mskTimeRead.Enabled = True";
mostCurrent._msktimeread.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 508;BA.debugLine="mskTimeRead.Text = \"__:__\"";
mostCurrent._msktimeread.setText((Object)("__:__"));
 //BA.debugLineNum = 509;BA.debugLine="mskTimeRead.RequestFocus";
mostCurrent._msktimeread.RequestFocus();
 //BA.debugLineNum = 510;BA.debugLine="imeKeyboard.ShowKeyboard(mskTimeRead)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._msktimeread.getObject()));
 };
 //BA.debugLineNum = 512;BA.debugLine="End Sub";
return "";
}
public static String  _clearui() throws Exception{
 //BA.debugLineNum = 274;BA.debugLine="Private Sub ClearUI";
 //BA.debugLineNum = 275;BA.debugLine="Try";
try { //BA.debugLineNum = 276;BA.debugLine="CDtxtBox.Initialize(Colors.Transparent,0)";
mostCurrent._cdtxtbox.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 277;BA.debugLine="cdFixedText.Initialize2(Colors.Black,0,0,0)";
mostCurrent._cdfixedtext.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (0),(int) (0),(int) (0));
 //BA.debugLineNum = 279;BA.debugLine="txtFMRdg.Background = cdFixedText";
mostCurrent._txtfmrdg.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdfixedtext.getObject()));
 //BA.debugLineNum = 280;BA.debugLine="txtBackFlowCum.Background = CDtxtBox";
mostCurrent._txtbackflowcum.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 281;BA.debugLine="txtNegativeRemarks.Background = CDtxtBox";
mostCurrent._txtnegativeremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 282;BA.debugLine="mskTimeRead.Background = CDtxtBox";
mostCurrent._msktimeread.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 283;BA.debugLine="txtFMRdgRemarks.Background = CDtxtBox";
mostCurrent._txtfmrdgremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 284;BA.debugLine="txtZeroRemarks.Background = CDtxtBox";
mostCurrent._txtzeroremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 285;BA.debugLine="txtHighRemarks.Background = CDtxtBox";
mostCurrent._txthighremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 287;BA.debugLine="chkDefaultTimeRead.Checked = False";
mostCurrent._chkdefaulttimeread.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 288;BA.debugLine="mskTimeRead.Text = \"__:__\"";
mostCurrent._msktimeread.setText((Object)("__:__"));
 //BA.debugLineNum = 289;BA.debugLine="txtFMRdg.Text = \"\"";
mostCurrent._txtfmrdg.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 290;BA.debugLine="txtFMRdgRemarks.Text = \"\"";
mostCurrent._txtfmrdgremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 292;BA.debugLine="isNegativeRdg = False";
_isnegativerdg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 293;BA.debugLine="sHighRdg = \"\"";
mostCurrent._shighrdg = "";
 } 
       catch (Exception e18) {
			processBA.setLastException(e18); //BA.debugLineNum = 295;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("763569941",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 297;BA.debugLine="End Sub";
return "";
}
public static String  _confirmsavenegativerdg() throws Exception{
 //BA.debugLineNum = 1277;BA.debugLine="Private Sub ConfirmSaveNegativeRdg";
 //BA.debugLineNum = 1278;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1280;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle("SAVE NEGATIVE READING").SetTitleColor((int) (mostCurrent._globalvar._bluecolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessage(("Continuing this may ALTER your previous reading due to back flow")+anywheresoftware.b4a.keywords.Common.CRLF+("Save reading anyway?")).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(_font.getObject())).SetMessageTypeface((android.graphics.Typeface)(_font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"SaveNegative").SetOnNegativeClicked(mostCurrent.activityBA,"SaveNegative");
 //BA.debugLineNum = 1297;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 1298;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 1299;BA.debugLine="End Sub";
return "";
}
public static String  _confirmsaverdg() throws Exception{
 //BA.debugLineNum = 1354;BA.debugLine="Private Sub ConfirmSaveRdg";
 //BA.debugLineNum = 1355;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1357;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle("SAVE NEW READING").SetTitleColor((int) (mostCurrent._globalvar._bluecolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessage(("Save New Pump Flow Meter Reading?")).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(_font.getObject())).SetMessageTypeface((android.graphics.Typeface)(_font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"NormalReading").SetOnNegativeClicked(mostCurrent.activityBA,"NormalReading");
 //BA.debugLineNum = 1374;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 1375;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 1376;BA.debugLine="End Sub";
return "";
}
public static String  _dispinfomsg(String _stitle,String _smsg) throws Exception{
 //BA.debugLineNum = 1415;BA.debugLine="Private Sub DispInfoMsg(sTitle As String, sMsg As";
 //BA.debugLineNum = 1416;BA.debugLine="MatDialogBuilder.Initialize(\"DispInformationMsg\")";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"DispInformationMsg");
 //BA.debugLineNum = 1417;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 1418;BA.debugLine="MatDialogBuilder.Title(sTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_stitle));
 //BA.debugLineNum = 1419;BA.debugLine="MatDialogBuilder.Content(sMsg)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_smsg));
 //BA.debugLineNum = 1420;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 1421;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1422;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1423;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 1424;BA.debugLine="End Sub";
return "";
}
public static String  _dispinformationmsg_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 1426;BA.debugLine="Private Sub DispInformationMsg_ButtonPressed(mDial";
 //BA.debugLineNum = 1427;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE)) {
case 0: {
 //BA.debugLineNum = 1429;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 1432;BA.debugLine="End Sub";
return "";
}
public static boolean  _edittranheader(int _itranheaderid) throws Exception{
 //BA.debugLineNum = 477;BA.debugLine="Private Sub EditTranHeader(iTranHeaderID As Int) A";
 //BA.debugLineNum = 478;BA.debugLine="End Sub";
return false;
}
public static String  _fontsizebinder_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 1394;BA.debugLine="Private Sub FontSizeBinder_OnBindView (View As Vie";
 //BA.debugLineNum = 1395;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1396;BA.debugLine="Alert.Initialize";
mostCurrent._alert.Initialize();
 //BA.debugLineNum = 1397;BA.debugLine="If ViewType = Alert.VIEW_TITLE Then ' Title";
if (_viewtype==mostCurrent._alert.VIEW_TITLE) { 
 //BA.debugLineNum = 1398;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 1399;BA.debugLine="lbl.TextSize = 30";
_lbl.setTextSize((float) (30));
 //BA.debugLineNum = 1400;BA.debugLine="lbl.SetTextColorAnimated(2000,Colors.Magenta)";
_lbl.SetTextColorAnimated((int) (2000),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 1403;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 1407;BA.debugLine="CS.Initialize.Typeface(Typeface.MATERIALICONS).S";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe88e)))+"  "));
 //BA.debugLineNum = 1408;BA.debugLine="CS.Typeface(Font).Size(24).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(_font.getObject())).Size((int) (24)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 1410;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 1412;BA.debugLine="End Sub";
return "";
}
public static void  _getfmrdgrecord(int _idetailedid) throws Exception{
ResumableSub_GetFMRdgRecord rsub = new ResumableSub_GetFMRdgRecord(null,_idetailedid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetFMRdgRecord extends BA.ResumableSub {
public ResumableSub_GetFMRdgRecord(bwsi.PumpOperations.addeditfmrdg parent,int _idetailedid) {
this.parent = parent;
this._idetailedid = _idetailedid;
}
bwsi.PumpOperations.addeditfmrdg parent;
int _idetailedid;
Object _senderfilter = null;
int _dpresentrdg = 0;
String _sremarks = "";
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
 //BA.debugLineNum = 312;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 314;BA.debugLine="Dim dPresentRdg As Int";
_dpresentrdg = 0;
 //BA.debugLineNum = 315;BA.debugLine="Dim sRdgTime, sRemarks As String";
parent.mostCurrent._srdgtime = "";
_sremarks = "";
 //BA.debugLineNum = 317;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
parent.mostCurrent._globalvar._tranheaderid /*int*/  = parent.mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,parent.mostCurrent._globalvar._pumphouseid /*int*/ ,parent.mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 318;BA.debugLine="Try";
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
 //BA.debugLineNum = 319;BA.debugLine="Starter.strCriteria = \"SELECT FMDetails.RdgTime,";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT FMDetails.RdgTime, FMDetails.PrevRdg, FMDetails.PresRdg, "+"FMDetails.PresCum, FMDetails.Remarks "+"FROM ProductionDetails AS FMDetails "+"WHERE FMDetails.DetailID = "+BA.NumberToString(_idetailedid);
 //BA.debugLineNum = 324;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 325;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 13;
return;
case 13:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 327;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 328;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 329;BA.debugLine="sRdgTime = RS.GetString(\"RdgTime\")";
parent.mostCurrent._srdgtime = _rs.GetString("RdgTime");
 //BA.debugLineNum = 330;BA.debugLine="dPresentRdg= RS.GetInt(\"PresRdg\")";
_dpresentrdg = _rs.GetInt("PresRdg");
 //BA.debugLineNum = 331;BA.debugLine="sRemarks = RS.GetString(\"Remarks\")";
_sremarks = _rs.GetString("Remarks");
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 333;BA.debugLine="snack.Initialize(\"\", Activity,$\"Unable to Fetch";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("Unable to Fetch Flow Meter Reading Details due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 334;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 335;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 336;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 337;BA.debugLine="Return";
if (true) return ;
 //BA.debugLineNum = 338;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("763701019",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
 //BA.debugLineNum = 342;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("763701023",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 12:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 345;BA.debugLine="chkDefaultTimeRead.Enabled = False";
parent.mostCurrent._chkdefaulttimeread.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 346;BA.debugLine="mskTimeRead.Text = sRdgTime";
parent.mostCurrent._msktimeread.setText((Object)(parent.mostCurrent._srdgtime));
 //BA.debugLineNum = 347;BA.debugLine="txtFMRdg.Text = dPresentRdg";
parent.mostCurrent._txtfmrdg.setText(BA.ObjectToCharSequence(_dpresentrdg));
 //BA.debugLineNum = 348;BA.debugLine="txtFMRdgRemarks.Text = sRemarks";
parent.mostCurrent._txtfmrdgremarks.setText(BA.ObjectToCharSequence(_sremarks));
 //BA.debugLineNum = 350;BA.debugLine="End Sub";
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
public static int  _getlatestrdgid(int _iheaderid,String _spresrdg) throws Exception{
int _iretval = 0;
 //BA.debugLineNum = 904;BA.debugLine="Private Sub GetLatestRdgID(iHeaderID As Int, sPres";
 //BA.debugLineNum = 905;BA.debugLine="Dim iRetVal As Int";
_iretval = 0;
 //BA.debugLineNum = 907;BA.debugLine="Try";
try { //BA.debugLineNum = 908;BA.debugLine="Starter.strCriteria = \"SELECT MAX(ProductionDeta";
mostCurrent._starter._strcriteria /*String*/  = "SELECT MAX(ProductionDetails.DetailID) FROM ProductionDetails "+"INNER JOIN TranHeader ON ProductionDetails.HeaderID = TranHeader.HeaderID "+"WHERE ProductionDetails.HeaderID = "+BA.NumberToString(_iheaderid)+" "+"AND PresRdg = '"+_spresrdg+"' "+"AND TranHeader.PumpID = "+BA.NumberToString(mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 914;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("764684042",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 916;BA.debugLine="iRetVal = Starter.DBCon.ExecQuerySingleResult(St";
_iretval = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 918;BA.debugLine="ToastMessageShow($\"Unable to fetch Pump Last Rea";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Pump Last Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 919;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("764684047",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 920;BA.debugLine="iRetVal = 0";
_iretval = (int) (0);
 };
 //BA.debugLineNum = 922;BA.debugLine="Return iRetVal";
if (true) return _iretval;
 //BA.debugLineNum = 923;BA.debugLine="End Sub";
return 0;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 30;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 31;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 32;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private xmlIcon As XmlLayoutBuilder";
mostCurrent._xmlicon = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 35;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private CDtxtBox, cdFixedText As ColorDrawable";
mostCurrent._cdtxtbox = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdfixedtext = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 39;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private csAns As CSBuilder";
mostCurrent._csans = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 42;BA.debugLine="Private imeKeyboard As IME";
mostCurrent._imekeyboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 43;BA.debugLine="Private Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 45;BA.debugLine="Private cdOK As ColorDrawable";
mostCurrent._cdok = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 46;BA.debugLine="Private cdCancel As ColorDrawable";
mostCurrent._cdcancel = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 48;BA.debugLine="Private vibration As B4Avibrate";
mostCurrent._vibration = new com.johan.Vibrate.Vibrate();
 //BA.debugLineNum = 49;BA.debugLine="Private vibratePattern() As Long";
_vibratepattern = new long[(int) (0)];
;
 //BA.debugLineNum = 50;BA.debugLine="Private SoundID As Int";
_soundid = 0;
 //BA.debugLineNum = 53;BA.debugLine="Private chkDefaultTimeRead As CheckBox";
mostCurrent._chkdefaulttimeread = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private mskTimeRead As MaskedEditText";
mostCurrent._msktimeread = new flm.b4a.maskededittext.MaskedEditTextWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private txtFMRdg As EditText";
mostCurrent._txtfmrdg = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private txtFMRdgRemarks As EditText";
mostCurrent._txtfmrdgremarks = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private btnCancel As ACButton";
mostCurrent._btncancel = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private btnSaveUpdate As ACButton";
mostCurrent._btnsaveupdate = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private dLastFMRdg As Double";
_dlastfmrdg = 0;
 //BA.debugLineNum = 61;BA.debugLine="Private dPreviousRdg As Double";
_dpreviousrdg = 0;
 //BA.debugLineNum = 62;BA.debugLine="Private dBackFlow As Double";
_dbackflow = 0;
 //BA.debugLineNum = 65;BA.debugLine="Private pnlZeroProdMsg As Panel";
mostCurrent._pnlzeroprodmsg = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 66;BA.debugLine="Private btnZeroOk As ACButton";
mostCurrent._btnzerook = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Private btnZeroCancel As ACButton";
mostCurrent._btnzerocancel = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 68;BA.debugLine="Private lblZeroMsg As Label";
mostCurrent._lblzeromsg = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 69;BA.debugLine="Private txtZeroRemarks As EditText";
mostCurrent._txtzeroremarks = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 72;BA.debugLine="Private pnlNegativeProdMsg As Panel";
mostCurrent._pnlnegativeprodmsg = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 73;BA.debugLine="Private btnNegativeOk As ACButton";
mostCurrent._btnnegativeok = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 74;BA.debugLine="Private lblNegativeMsg As Label";
mostCurrent._lblnegativemsg = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 75;BA.debugLine="Private chkBackFlow As CheckBox";
mostCurrent._chkbackflow = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 76;BA.debugLine="Private txtBackFlowCum As EditText";
mostCurrent._txtbackflowcum = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 77;BA.debugLine="Private isNegativeRdg As Boolean";
_isnegativerdg = false;
 //BA.debugLineNum = 78;BA.debugLine="Private btnNegativeCancel As ACButton";
mostCurrent._btnnegativecancel = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 79;BA.debugLine="Private txtNegativeRemarks As EditText";
mostCurrent._txtnegativeremarks = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 82;BA.debugLine="Private pnlHighProdMsg As Panel";
mostCurrent._pnlhighprodmsg = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 83;BA.debugLine="Private btnHighCancel As ACButton";
mostCurrent._btnhighcancel = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 84;BA.debugLine="Private btnHighOk As ACButton";
mostCurrent._btnhighok = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 87;BA.debugLine="Private pnlHighBillConfirmation As Panel";
mostCurrent._pnlhighbillconfirmation = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 88;BA.debugLine="Private txtPresRdgConfirm As EditText";
mostCurrent._txtpresrdgconfirm = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 89;BA.debugLine="Private btnHBConfirmCancel As ACButton";
mostCurrent._btnhbconfirmcancel = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 90;BA.debugLine="Private btnHBConfirmSave As ACButton";
mostCurrent._btnhbconfirmsave = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 91;BA.debugLine="Private txtHighRemarks As EditText";
mostCurrent._txthighremarks = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 92;BA.debugLine="Private sHighRdg As String";
mostCurrent._shighrdg = "";
 //BA.debugLineNum = 95;BA.debugLine="Private pnlLowProdMsg As Panel";
mostCurrent._pnllowprodmsg = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 96;BA.debugLine="Private btnLowCancel As ACButton";
mostCurrent._btnlowcancel = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 97;BA.debugLine="Private btnLowOk As ACButton";
mostCurrent._btnlowok = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 100;BA.debugLine="Private sRdgTime As String";
mostCurrent._srdgtime = "";
 //BA.debugLineNum = 101;BA.debugLine="Private cKeyboard As CustomKeyboard";
mostCurrent._ckeyboard = new bm.watscho.keyboard.CustomKeyboard();
 //BA.debugLineNum = 102;BA.debugLine="Private pnlKeyboard As Panel";
mostCurrent._pnlkeyboard = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 104;BA.debugLine="Private sUploadReading As String";
mostCurrent._suploadreading = "";
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return "";
}
public static String  _hidepanels() throws Exception{
 //BA.debugLineNum = 299;BA.debugLine="Private Sub HidePanels";
 //BA.debugLineNum = 300;BA.debugLine="pnlZeroProdMsg.Visible = False";
mostCurrent._pnlzeroprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 301;BA.debugLine="pnlNegativeProdMsg.Visible = False";
mostCurrent._pnlnegativeprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 302;BA.debugLine="pnlLowProdMsg.Visible = False";
mostCurrent._pnllowprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 303;BA.debugLine="pnlHighProdMsg.Visible = False";
mostCurrent._pnlhighprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 304;BA.debugLine="pnlHighBillConfirmation.Visible = False";
mostCurrent._pnlhighbillconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 305;BA.debugLine="End Sub";
return "";
}
public static boolean  _insertnegativerdg(int _itranheaderid) throws Exception{
boolean _bretval = false;
int _inewreading = 0;
int _ilastreading = 0;
int _ilastcum = 0;
String _sremarks = "";
String _slocation = "";
long _ldate = 0L;
String _sdateadded = "";
double _dtotcum = 0;
anywheresoftware.b4a.sql.SQL.CursorWrapper _rslast = null;
int _iprevdetailedid = 0;
 //BA.debugLineNum = 747;BA.debugLine="Private Sub InsertNegativeRdg(iTranHeaderID As Int";
 //BA.debugLineNum = 748;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 749;BA.debugLine="Dim iNewReading, iLastReading, iLastCuM As Int";
_inewreading = 0;
_ilastreading = 0;
_ilastcum = 0;
 //BA.debugLineNum = 750;BA.debugLine="Dim sRemarks, sLocation As String";
_sremarks = "";
_slocation = "";
 //BA.debugLineNum = 751;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 752;BA.debugLine="Dim sDateAdded As String";
_sdateadded = "";
 //BA.debugLineNum = 753;BA.debugLine="Dim dTotCum As Double";
_dtotcum = 0;
 //BA.debugLineNum = 754;BA.debugLine="Dim rsLast As Cursor";
_rslast = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 756;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 759;BA.debugLine="Dim iPrevDetailedID As Int";
_iprevdetailedid = 0;
 //BA.debugLineNum = 761;BA.debugLine="Starter.strCriteria = \"SELECT MAX(DetailID) FROM";
mostCurrent._starter._strcriteria /*String*/  = "SELECT MAX(DetailID) FROM ProductionDetails "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 764;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("764487441",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 766;BA.debugLine="iPrevDetailedID = Starter.DBCon.ExecQuerySingleRe";
_iprevdetailedid = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 768;BA.debugLine="If iPrevDetailedID = 0 Then 'Request to Net";
if (_iprevdetailedid==0) { 
 //BA.debugLineNum = 770;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 773;BA.debugLine="Starter.strCriteria = \"SELECT * FROM ProductionD";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM ProductionDetails WHERE DetailID = "+BA.NumberToString(_iprevdetailedid);
 //BA.debugLineNum = 774;BA.debugLine="LogColor(Starter.strCriteria, Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("764487451",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 776;BA.debugLine="rsLast = Starter.DBCon.ExecQuery(Starter.strCrit";
_rslast = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 777;BA.debugLine="If rsLast.RowCount > 0 Then";
if (_rslast.getRowCount()>0) { 
 //BA.debugLineNum = 778;BA.debugLine="rsLast.Position = 0";
_rslast.setPosition((int) (0));
 //BA.debugLineNum = 779;BA.debugLine="iLastReading = rsLast.GetInt(\"PresRdg\")";
_ilastreading = _rslast.GetInt("PresRdg");
 //BA.debugLineNum = 780;BA.debugLine="iLastCuM = rsLast.GetInt(\"PresCum\")";
_ilastcum = _rslast.GetInt("PresCum");
 }else {
 //BA.debugLineNum = 782;BA.debugLine="iLastReading = 0";
_ilastreading = (int) (0);
 //BA.debugLineNum = 783;BA.debugLine="iLastCuM = 0";
_ilastcum = (int) (0);
 };
 };
 //BA.debugLineNum = 787;BA.debugLine="iLastReading = iLastReading - GlobalVar.SF.Val(tx";
_ilastreading = (int) (_ilastreading-mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtbackflowcum.getText()));
 //BA.debugLineNum = 788;BA.debugLine="iLastCuM = iLastCuM - GlobalVar.SF.Val(txtBackFlo";
_ilastcum = (int) (_ilastcum-mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtbackflowcum.getText()));
 //BA.debugLineNum = 789;BA.debugLine="dBackFlow = GlobalVar.SF.Val(txtBackFlowCum.Text)";
_dbackflow = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtbackflowcum.getText());
 //BA.debugLineNum = 794;BA.debugLine="dTotCum = 0";
_dtotcum = 0;
 //BA.debugLineNum = 796;BA.debugLine="iNewReading = GlobalVar.SF.Val(txtFMRdg.Text)";
_inewreading = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText()));
 //BA.debugLineNum = 797;BA.debugLine="dTotCum = iNewReading - iLastReading";
_dtotcum = _inewreading-_ilastreading;
 //BA.debugLineNum = 799;BA.debugLine="sRemarks = txtFMRdgRemarks.Text";
_sremarks = mostCurrent._txtfmrdgremarks.getText();
 //BA.debugLineNum = 800;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 802;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("764487479",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 804;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("764487481",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 805;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("764487482",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 807;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 808;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("764487485",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 810;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 811;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 812;BA.debugLine="sDateAdded = DateTime.Date(lDate)";
_sdateadded = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 814;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 815;BA.debugLine="Try";
try { //BA.debugLineNum = 816;BA.debugLine="Starter.strCriteria = \"UPDATE ProductionDetails";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE ProductionDetails SET "+"PresRdg = ?, "+"PresCum = ?, "+"BackFlowCum = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ?, "+"ModifiedOn = ? "+"WHERE DetailID = "+BA.NumberToString(_iprevdetailedid);
 //BA.debugLineNum = 825;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_ilastreading),BA.NumberToString(_ilastcum),BA.NumberToString(_dbackflow),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_sdateadded,_slocation}));
 //BA.debugLineNum = 827;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO Product";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO ProductionDetails VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._tranheaderid /*int*/ ),(Object)(mostCurrent._srdgtime),(Object)(_ilastreading),(Object)(mostCurrent._txtfmrdg.getText()),(Object)(_dtotcum),(Object)(("0")),(Object)(_sremarks),(Object)(("0")),(Object)(("")),(Object)(("")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_sdateadded),(Object)(_slocation),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)((""))}));
 //BA.debugLineNum = 829;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 830;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e52) {
			processBA.setLastException(e52); //BA.debugLineNum = 832;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("764487509",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 833;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 835;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 836;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 837;BA.debugLine="End Sub";
return false;
}
public static boolean  _insertnewflowmeterreading() throws Exception{
boolean _bretval = false;
String _sremarks = "";
String _slocation = "";
String _sdateadded = "";
long _ldate = 0L;
int _ibackflowcum = 0;
double _dtotcum = 0;
 //BA.debugLineNum = 616;BA.debugLine="Private Sub InsertNewFlowMeterReading() As Boolean";
 //BA.debugLineNum = 617;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 618;BA.debugLine="Dim sRemarks, sLocation As String";
_sremarks = "";
_slocation = "";
 //BA.debugLineNum = 619;BA.debugLine="Dim sDateAdded As String";
_sdateadded = "";
 //BA.debugLineNum = 620;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 621;BA.debugLine="Dim iBackFlowCum As Int";
_ibackflowcum = 0;
 //BA.debugLineNum = 622;BA.debugLine="Dim dTotCum As Double";
_dtotcum = 0;
 //BA.debugLineNum = 624;BA.debugLine="iBackFlowCum = 0";
_ibackflowcum = (int) (0);
 //BA.debugLineNum = 625;BA.debugLine="dTotCum = 0";
_dtotcum = 0;
 //BA.debugLineNum = 626;BA.debugLine="dTotCum = GlobalVar.SF.Val(txtFMRdg.Text) - dLast";
_dtotcum = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())-_dlastfmrdg;
 //BA.debugLineNum = 628;BA.debugLine="sRemarks = txtFMRdgRemarks.Text";
_sremarks = mostCurrent._txtfmrdgremarks.getText();
 //BA.debugLineNum = 629;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 631;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("764356367",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 633;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("764356369",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 634;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("764356370",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 636;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 637;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("764356373",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 639;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 640;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 641;BA.debugLine="sDateAdded = DateTime.Date(lDate)";
_sdateadded = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 644;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 645;BA.debugLine="Try";
try { //BA.debugLineNum = 646;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO Product";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO ProductionDetails VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._tranheaderid /*int*/ ),(Object)(mostCurrent._srdgtime),(Object)(_dlastfmrdg),(Object)(mostCurrent._txtfmrdg.getText()),(Object)(_dtotcum),(Object)(_ibackflowcum),(Object)(_sremarks),(Object)(("0")),(Object)(("")),(Object)(("")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_sdateadded),(Object)(_slocation),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)((""))}));
 //BA.debugLineNum = 648;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 649;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e26) {
			processBA.setLastException(e26); //BA.debugLineNum = 651;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("764356387",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 652;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 654;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 655;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 656;BA.debugLine="End Sub";
return false;
}
public static boolean  _insertnewnegativerdg() throws Exception{
boolean _bretval = false;
int _inewreading = 0;
int _ilastreading = 0;
int _ilastcum = 0;
String _sremarks = "";
String _slocation = "";
long _ldate = 0L;
String _sdateadded = "";
double _dtotcum = 0;
int _iprevdetailedid = 0;
 //BA.debugLineNum = 658;BA.debugLine="Private Sub InsertNewNegativeRdg() As Boolean";
 //BA.debugLineNum = 659;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 660;BA.debugLine="Dim iNewReading, iLastReading, iLastCuM As Int";
_inewreading = 0;
_ilastreading = 0;
_ilastcum = 0;
 //BA.debugLineNum = 661;BA.debugLine="Dim sRemarks, sLocation As String";
_sremarks = "";
_slocation = "";
 //BA.debugLineNum = 662;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 663;BA.debugLine="Dim sDateAdded As String";
_sdateadded = "";
 //BA.debugLineNum = 664;BA.debugLine="Dim dTotCum As Double";
_dtotcum = 0;
 //BA.debugLineNum = 666;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 669;BA.debugLine="Dim iPrevDetailedID As Int";
_iprevdetailedid = 0;
 //BA.debugLineNum = 671;BA.debugLine="iPrevDetailedID = DBaseFunctions.GetLastFMReading";
_iprevdetailedid = (int) (mostCurrent._dbasefunctions._getlastfmreadingtransid /*double*/ (mostCurrent.activityBA,(int) (_dlastfmrdg),(int) (_dbackflow)));
 //BA.debugLineNum = 673;BA.debugLine="If iPrevDetailedID = 0 Then 'Request to Net";
if (_iprevdetailedid==0) { 
 //BA.debugLineNum = 675;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 677;BA.debugLine="Starter.strCriteria = \"SELECT PresRdg FROM Produ";
mostCurrent._starter._strcriteria /*String*/  = "SELECT PresRdg FROM ProductionDetails WHERE DetailID = "+BA.NumberToString(_iprevdetailedid);
 //BA.debugLineNum = 678;BA.debugLine="LogColor(Starter.strCriteria, Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("764421908",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 680;BA.debugLine="iLastReading = Starter.DBCon.ExecQuerySingleResu";
_ilastreading = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 682;BA.debugLine="If iLastReading = 0 Then";
if (_ilastreading==0) { 
 //BA.debugLineNum = 683;BA.debugLine="iLastReading = iLastReading";
_ilastreading = _ilastreading;
 }else {
 //BA.debugLineNum = 685;BA.debugLine="iLastReading = iLastReading - GlobalVar.SF.Val(";
_ilastreading = (int) (_ilastreading-mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtbackflowcum.getText()));
 };
 //BA.debugLineNum = 688;BA.debugLine="Starter.strCriteria = \"SELECT PresCum FROM Produ";
mostCurrent._starter._strcriteria /*String*/  = "SELECT PresCum FROM ProductionDetails WHERE DetailID = "+BA.NumberToString(_iprevdetailedid);
 //BA.debugLineNum = 689;BA.debugLine="LogColor(Starter.strCriteria, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("764421919",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 691;BA.debugLine="iLastCuM = Starter.DBCon.ExecQuerySingleResult(S";
_ilastcum = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 693;BA.debugLine="If iLastCuM = 0 Then";
if (_ilastcum==0) { 
 //BA.debugLineNum = 694;BA.debugLine="iLastCuM = iLastCuM";
_ilastcum = _ilastcum;
 }else {
 //BA.debugLineNum = 696;BA.debugLine="iLastCuM = iLastCuM - dBackFlow";
_ilastcum = (int) (_ilastcum-_dbackflow);
 };
 };
 //BA.debugLineNum = 702;BA.debugLine="dTotCum = 0";
_dtotcum = 0;
 //BA.debugLineNum = 704;BA.debugLine="iNewReading = GlobalVar.SF.Val(txtFMRdg.Text)";
_inewreading = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText()));
 //BA.debugLineNum = 705;BA.debugLine="dTotCum = iNewReading - iLastReading";
_dtotcum = _inewreading-_ilastreading;
 //BA.debugLineNum = 707;BA.debugLine="sRemarks = txtFMRdgRemarks.Text";
_sremarks = mostCurrent._txtfmrdgremarks.getText();
 //BA.debugLineNum = 708;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 710;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("764421940",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 712;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("764421942",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 713;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("764421943",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 715;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 716;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("764421946",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 718;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 719;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 720;BA.debugLine="sDateAdded = DateTime.Date(lDate)";
_sdateadded = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 722;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 723;BA.debugLine="Try";
try { //BA.debugLineNum = 724;BA.debugLine="Starter.strCriteria = \"UPDATE ProductionDetails";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE ProductionDetails SET "+"PresRdg = ?, "+"PresCum = ?, "+"BackFlowCum = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ?, "+"ModifiedOn = ? "+"WHERE DetailID = "+BA.NumberToString(_iprevdetailedid);
 //BA.debugLineNum = 733;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_ilastreading),BA.NumberToString(_ilastcum),BA.NumberToString(_dbackflow),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_sdateadded,_slocation}));
 //BA.debugLineNum = 735;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO Product";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO ProductionDetails VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._tranheaderid /*int*/ ),(Object)(mostCurrent._srdgtime),(Object)(_dlastfmrdg),(Object)(mostCurrent._txtfmrdg.getText()),(Object)(_dtotcum),(Object)(_dbackflow),(Object)(_sremarks),(Object)(("0")),(Object)(("")),(Object)(("")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_sdateadded),(Object)(_slocation),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)((""))}));
 //BA.debugLineNum = 737;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 738;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e51) {
			processBA.setLastException(e51); //BA.debugLineNum = 740;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("764421970",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 741;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 743;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 744;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 745;BA.debugLine="End Sub";
return false;
}
public static boolean  _isvalidentries() throws Exception{
boolean _bretval = false;
 //BA.debugLineNum = 354;BA.debugLine="Private Sub IsValidEntries() As Boolean";
 //BA.debugLineNum = 355;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 356;BA.debugLine="LogColor(mskTimeRead.Text, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("763766530",mostCurrent._msktimeread.getText(),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 358;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 359;BA.debugLine="Try";
try { //BA.debugLineNum = 360;BA.debugLine="If chkDefaultTimeRead.Checked = True Then";
if (mostCurrent._chkdefaulttimeread.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 361;BA.debugLine="If Validation.IsTime(mskTimeRead.Text) = False";
if (mostCurrent._validation._istime /*boolean*/ (mostCurrent.activityBA,mostCurrent._msktimeread.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 362;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid Reading Ti";
_requiredmsgbox(("ERROR"),("Invalid Reading Time!"));
 //BA.debugLineNum = 363;BA.debugLine="mskTimeRead.RequestFocus";
mostCurrent._msktimeread.RequestFocus();
 //BA.debugLineNum = 364;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 }else {
 //BA.debugLineNum = 367;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(mskTimeRe";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._msktimeread.getText()))<=0 || (mostCurrent._msktimeread.getText()).equals("__:__")) { 
 //BA.debugLineNum = 368;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Reading Time canno";
_requiredmsgbox(("ERROR"),("Reading Time cannot be blank!"));
 //BA.debugLineNum = 369;BA.debugLine="mskTimeRead.RequestFocus";
mostCurrent._msktimeread.RequestFocus();
 //BA.debugLineNum = 370;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 }else if(mostCurrent._validation._istime /*boolean*/ (mostCurrent.activityBA,mostCurrent._msktimeread.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 373;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid Reading Ti";
_requiredmsgbox(("ERROR"),("Invalid Reading Time!"));
 //BA.debugLineNum = 374;BA.debugLine="mskTimeRead.RequestFocus";
mostCurrent._msktimeread.RequestFocus();
 //BA.debugLineNum = 375;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 };
 } 
       catch (Exception e23) {
			processBA.setLastException(e23); //BA.debugLineNum = 380;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("763766554",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 381;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 383;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 384;BA.debugLine="End Sub";
return false;
}
public static String  _msktimeread_enterpressed() throws Exception{
 //BA.debugLineNum = 520;BA.debugLine="Sub mskTimeRead_EnterPressed";
 //BA.debugLineNum = 522;BA.debugLine="End Sub";
return "";
}
public static String  _msktimeread_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 1468;BA.debugLine="Sub mskTimeRead_FocusChanged(HasFocus As Boolean)";
 //BA.debugLineNum = 1469;BA.debugLine="If HasFocus = True Then mskTimeRead.SelectAll";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._msktimeread.SelectAll();};
 //BA.debugLineNum = 1470;BA.debugLine="End Sub";
return "";
}
public static String  _normalreading_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 1379;BA.debugLine="Private Sub NormalReading_OnNegativeClicked (View";
 //BA.debugLineNum = 1381;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1382;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 1383;BA.debugLine="End Sub";
return "";
}
public static String  _normalreading_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 1385;BA.debugLine="Private Sub NormalReading_OnPositiveClicked (View";
 //BA.debugLineNum = 1388;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1389;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 1390;BA.debugLine="SaveUpdateFMReading";
_saveupdatefmreading();
 //BA.debugLineNum = 1391;BA.debugLine="End Sub";
return "";
}
public static String  _pnlhighbillconfirmation_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1200;BA.debugLine="Sub pnlHighBillConfirmation_Touch (Action As Int,";
 //BA.debugLineNum = 1202;BA.debugLine="End Sub";
return "";
}
public static String  _pnlhighprodmsg_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1156;BA.debugLine="Sub pnlHighProdMsg_Touch (Action As Int, X As Floa";
 //BA.debugLineNum = 1158;BA.debugLine="End Sub";
return "";
}
public static String  _pnllowprodmsg_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1119;BA.debugLine="Sub pnlLowProdMsg_Touch (Action As Int, X As Float";
 //BA.debugLineNum = 1121;BA.debugLine="End Sub";
return "";
}
public static String  _pnlnegativeprodmsg_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1060;BA.debugLine="Sub pnlNegativeProdMsg_Touch (Action As Int, X As";
 //BA.debugLineNum = 1062;BA.debugLine="End Sub";
return "";
}
public static String  _pnlzeroprodmsg_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1013;BA.debugLine="Sub pnlZeroProdMsg_Touch (Action As Int, X As Floa";
 //BA.debugLineNum = 1015;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 21;BA.debugLine="Private Font As Typeface = Typeface.LoadFromAsset";
_font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont.ttf")));
 //BA.debugLineNum = 22;BA.debugLine="Private FontBold As Typeface = Typeface.LoadFromA";
_fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont_bold.ttf")));
 //BA.debugLineNum = 23;BA.debugLine="Private InpTyp As SLInpTypeConst";
_inptyp = new bwsi.PumpOperations.slinptypeconst();
 //BA.debugLineNum = 25;BA.debugLine="Private soundsAlarmChannel As SoundPool";
_soundsalarmchannel = new anywheresoftware.b4a.audio.SoundPoolWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private TTS1 As TTS";
_tts1 = new anywheresoftware.b4a.obejcts.TTS();
 //BA.debugLineNum = 27;BA.debugLine="Dim TYPE_TEXT_FLAG_NO_SUGGESTIONS  As Int = 0x800";
_type_text_flag_no_suggestions = (int) (0x80000);
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsg_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 1272;BA.debugLine="Private Sub RequiredMsg_OnPositiveClicked (View As";
 //BA.debugLineNum = 1273;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1274;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 1275;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsgbox(String _stitle,String _smsg) throws Exception{
 //BA.debugLineNum = 1247;BA.debugLine="Private Sub RequiredMsgBox(sTitle As String, sMsg";
 //BA.debugLineNum = 1248;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1249;BA.debugLine="Alert.Initialize.Dismiss2";
mostCurrent._alert.Initialize().Dismiss2();
 //BA.debugLineNum = 1251;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._redcolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessage(_smsg).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessageTypeface((android.graphics.Typeface)(_font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"RequiredMsg").SetOnViewBinder(mostCurrent.activityBA,"FontSizeBinder");
 //BA.debugLineNum = 1266;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 1267;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 1268;BA.debugLine="End Sub";
return "";
}
public static String  _savenegative_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 1302;BA.debugLine="Private Sub SaveNegative_OnNegativeClicked (View A";
 //BA.debugLineNum = 1304;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1305;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 1306;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1307;BA.debugLine="End Sub";
return "";
}
public static String  _savenegative_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 1309;BA.debugLine="Private Sub SaveNegative_OnPositiveClicked (View A";
 //BA.debugLineNum = 1312;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1313;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 1315;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1316;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1317;BA.debugLine="pnlNegativeProdMsg.Visible = False";
mostCurrent._pnlnegativeprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1319;BA.debugLine="dBackFlow = GlobalVar.SF.Val(txtBackFlowCum.Text)";
_dbackflow = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtbackflowcum.getText());
 //BA.debugLineNum = 1320;BA.debugLine="dPreviousRdg = dLastFMRdg - dBackFlow";
_dpreviousrdg = _dlastfmrdg-_dbackflow;
 //BA.debugLineNum = 1322;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 1324;BA.debugLine="If GlobalVar.TranHeaderID = 0 Then 'New FM Readin";
if (mostCurrent._globalvar._tranheaderid /*int*/ ==0) { 
 //BA.debugLineNum = 1325;BA.debugLine="If Not(SaveTransHeader) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_savetransheader())) { 
 //BA.debugLineNum = 1326;BA.debugLine="ToastMessageShow($\"Unable to save negative read";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to save negative reading header due to ")+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA))),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1327;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1330;BA.debugLine="If Not(InsertNewNegativeRdg) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_insertnewnegativerdg())) { 
 //BA.debugLineNum = 1331;BA.debugLine="ToastMessageShow($\"Unable to save negative read";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to save negative reading details due to ")+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA))),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1332;BA.debugLine="dBackFlow = 0";
_dbackflow = 0;
 //BA.debugLineNum = 1333;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 1338;BA.debugLine="If Not(UpdateTranHeader(GlobalVar.TranHeaderID))";
if (anywheresoftware.b4a.keywords.Common.Not(_updatetranheader(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
 //BA.debugLineNum = 1339;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1343;BA.debugLine="If Not(InsertNegativeRdg(GlobalVar.TranHeaderID)";
if (anywheresoftware.b4a.keywords.Common.Not(_insertnegativerdg(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
 //BA.debugLineNum = 1344;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 1348;BA.debugLine="UpdateLastFMReadings(GlobalVar.PumpHouseID)";
_updatelastfmreadings(mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 1349;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 //BA.debugLineNum = 1351;BA.debugLine="End Sub";
return "";
}
public static String  _savetojson(int _irdgid) throws Exception{
String _sjson = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _jsongen = null;
anywheresoftware.b4a.objects.collections.List _jsonlist = null;
anywheresoftware.b4a.objects.collections.Map _jsonmap = null;
anywheresoftware.b4a.sql.SQL.CursorWrapper _rs = null;
 //BA.debugLineNum = 925;BA.debugLine="Private Sub SavetoJSON(iRdgID As Int) As String";
 //BA.debugLineNum = 926;BA.debugLine="Dim sJSON As String";
_sjson = "";
 //BA.debugLineNum = 927;BA.debugLine="Dim JSONGen As JSONGenerator";
_jsongen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 928;BA.debugLine="Dim JSONList As List";
_jsonlist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 929;BA.debugLine="Dim JSONMap As Map";
_jsonmap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 930;BA.debugLine="Dim RS As Cursor";
_rs = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 933;BA.debugLine="Try";
try { //BA.debugLineNum = 935;BA.debugLine="Starter.strCriteria = \"SELECT * FROM ProductionD";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM ProductionDetails "+"WHERE DetailID = "+BA.NumberToString(_irdgid);
 //BA.debugLineNum = 937;BA.debugLine="LogColor(Starter.strCriteria, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("764749580",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 939;BA.debugLine="RS = Starter.DBCon.ExecQuery(Starter.strCriteria";
_rs = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 941;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 942;BA.debugLine="JSONList.Initialize";
_jsonlist.Initialize();
 //BA.debugLineNum = 943;BA.debugLine="JSONMap.Initialize";
_jsonmap.Initialize();
 //BA.debugLineNum = 944;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 946;BA.debugLine="JSONMap.Put(\"pump_id\", GlobalVar.PumpHouseID)";
_jsonmap.Put((Object)("pump_id"),(Object)(mostCurrent._globalvar._pumphouseid /*int*/ ));
 //BA.debugLineNum = 947;BA.debugLine="JSONMap.Put(\"transaction_date\", GlobalVar.TranD";
_jsonmap.Put((Object)("transaction_date"),(Object)(mostCurrent._globalvar._trandate /*String*/ ));
 //BA.debugLineNum = 948;BA.debugLine="JSONMap.Put(\"transaction_time\", mskTimeRead.Tex";
_jsonmap.Put((Object)("transaction_time"),(Object)(mostCurrent._msktimeread.getText()));
 //BA.debugLineNum = 949;BA.debugLine="JSONMap.Put(\"reading_previous\", RS.GetInt(\"Prev";
_jsonmap.Put((Object)("reading_previous"),(Object)(_rs.GetInt("PrevRdg")));
 //BA.debugLineNum = 950;BA.debugLine="JSONMap.Put(\"reading_present\", RS.GetInt(\"PresR";
_jsonmap.Put((Object)("reading_present"),(Object)(_rs.GetInt("PresRdg")));
 //BA.debugLineNum = 951;BA.debugLine="JSONMap.Put(\"production\", RS.GetInt(\"PresCum\"))";
_jsonmap.Put((Object)("production"),(Object)(_rs.GetInt("PresCum")));
 //BA.debugLineNum = 952;BA.debugLine="JSONMap.Put(\"remarks\", RS.GetString(\"Remarks\"))";
_jsonmap.Put((Object)("remarks"),(Object)(_rs.GetString("Remarks")));
 //BA.debugLineNum = 953;BA.debugLine="JSONMap.Put(\"pump_operator\", RS.GetInt(\"AddedBy";
_jsonmap.Put((Object)("pump_operator"),(Object)(_rs.GetInt("AddedBy")));
 //BA.debugLineNum = 954;BA.debugLine="JSONMap.Put(\"coordinates\", RS.GetString(\"AddedO";
_jsonmap.Put((Object)("coordinates"),(Object)(_rs.GetString("AddedOn")));
 //BA.debugLineNum = 955;BA.debugLine="JSONGen.Initialize(JSONMap)";
_jsongen.Initialize(_jsonmap);
 //BA.debugLineNum = 957;BA.debugLine="Log (JSONGen.ToString)";
anywheresoftware.b4a.keywords.Common.LogImpl("764749600",_jsongen.ToString(),0);
 //BA.debugLineNum = 958;BA.debugLine="sJSON = JSONGen.ToString";
_sjson = _jsongen.ToString();
 //BA.debugLineNum = 959;BA.debugLine="Log (sJSON)";
anywheresoftware.b4a.keywords.Common.LogImpl("764749602",_sjson,0);
 }else {
 //BA.debugLineNum = 962;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 963;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 964;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 965;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 966;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("764749609",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 } 
       catch (Exception e35) {
			processBA.setLastException(e35); //BA.debugLineNum = 972;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("764749615",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 974;BA.debugLine="Return sJSON";
if (true) return _sjson;
 //BA.debugLineNum = 975;BA.debugLine="End Sub";
return "";
}
public static boolean  _savetransheader() throws Exception{
boolean _bretval = false;
long _lngdatetime = 0L;
String _saddedat = "";
double _totprod = 0;
 //BA.debugLineNum = 386;BA.debugLine="Private Sub SaveTransHeader() As Boolean";
 //BA.debugLineNum = 387;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 388;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 389;BA.debugLine="Dim sAddedAt As String";
_saddedat = "";
 //BA.debugLineNum = 390;BA.debugLine="Dim TotProd As Double";
_totprod = 0;
 //BA.debugLineNum = 392;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 393;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 394;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 395;BA.debugLine="sAddedAt= DateTime.Date(lngDateTime)";
_saddedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 397;BA.debugLine="If isNegativeRdg = True Then";
if (_isnegativerdg==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 398;BA.debugLine="TotProd = 0";
_totprod = 0;
 }else {
 //BA.debugLineNum = 400;BA.debugLine="TotProd = GlobalVar.SF.Val(txtFMRdg.Text) -  dLa";
_totprod = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())-_dlastfmrdg;
 };
 //BA.debugLineNum = 403;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 404;BA.debugLine="Try";
try { //BA.debugLineNum = 406;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO TranHea";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO TranHeader VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._branchid /*int*/ ),(Object)(mostCurrent._globalvar._pumphouseid /*int*/ ),(Object)(mostCurrent._globalvar._trandate /*String*/ ),(Object)(("0")),(Object)(_totprod),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_saddedat),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(("0")),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null}));
 //BA.debugLineNum = 409;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 410;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e20) {
			processBA.setLastException(e20); //BA.debugLineNum = 412;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("763832090",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 413;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 415;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 416;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 417;BA.debugLine="End Sub";
return false;
}
public static String  _saveupdatefmreading() throws Exception{
 //BA.debugLineNum = 855;BA.debugLine="Private Sub SaveUpdateFMReading";
 //BA.debugLineNum = 856;BA.debugLine="Select Case GlobalVar.blnNewFMRdg";
switch (BA.switchObjectToInt(mostCurrent._globalvar._blnnewfmrdg /*boolean*/ ,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False)) {
case 0: {
 //BA.debugLineNum = 859;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHead";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 860;BA.debugLine="LogColor(GlobalVar.TranHeaderID, Colors.Magenta";
anywheresoftware.b4a.keywords.Common.LogImpl("764618501",BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 862;BA.debugLine="If GlobalVar.TranHeaderID = 0 Then";
if (mostCurrent._globalvar._tranheaderid /*int*/ ==0) { 
 //BA.debugLineNum = 863;BA.debugLine="If Not(SaveTransHeader) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_savetransheader())) { 
if (true) return "";};
 //BA.debugLineNum = 864;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHea";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 865;BA.debugLine="LogColor(GlobalVar.TranHeaderID, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("764618506",BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 866;BA.debugLine="If Not(InsertNewFlowMeterReading) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_insertnewflowmeterreading())) { 
 //BA.debugLineNum = 867;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 868;BA.debugLine="snack.Initialize(\"\", Activity, $\"Unable to Ad";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),("Unable to Add New Flow Meter Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),mostCurrent._snack.DURATION_LONG);
 //BA.debugLineNum = 869;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Glob";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 870;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Color";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 871;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 872;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 875;BA.debugLine="If Not(InsertNewFlowMeterReading) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_insertnewflowmeterreading())) { 
 //BA.debugLineNum = 876;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 877;BA.debugLine="snack.Initialize(\"\", Activity, $\"Unable to Ad";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),("Unable to Add New Flow Meter Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),mostCurrent._snack.DURATION_LONG);
 //BA.debugLineNum = 878;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Glob";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 879;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Color";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 880;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 881;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 883;BA.debugLine="If Not(UpdateTranHeader(GlobalVar.TranHeaderID";
if (anywheresoftware.b4a.keywords.Common.Not(_updatetranheader(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
 //BA.debugLineNum = 884;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 887;BA.debugLine="UpdateLastFMReadings(GlobalVar.PumpHouseID)";
_updatelastfmreadings(mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 888;BA.debugLine="GlobalVar.FMRdgDetailID = GetLatestRdgID(Global";
mostCurrent._globalvar._fmrdgdetailid /*int*/  = _getlatestrdgid(mostCurrent._globalvar._tranheaderid /*int*/ ,mostCurrent._txtfmrdg.getText());
 //BA.debugLineNum = 889;BA.debugLine="If GlobalVar.FMRdgDetailID = 0 Then";
if (mostCurrent._globalvar._fmrdgdetailid /*int*/ ==0) { 
 }else {
 //BA.debugLineNum = 891;BA.debugLine="sUploadReading = SavetoJSON(GlobalVar.FMRdgDet";
mostCurrent._suploadreading = _savetojson(mostCurrent._globalvar._fmrdgdetailid /*int*/ );
 };
 //BA.debugLineNum = 893;BA.debugLine="If GlobalVar.SF.Len(sUploadReading) = 0 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._suploadreading)==0) { 
 }else {
 //BA.debugLineNum = 895;BA.debugLine="UploadReadingData(sUploadReading)";
_uploadreadingdata(mostCurrent._suploadreading);
 };
 //BA.debugLineNum = 897;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 901;BA.debugLine="End Sub";
return "";
}
public static String  _showhighconfirmationwarning() throws Exception{
 //BA.debugLineNum = 1204;BA.debugLine="Private Sub ShowHighConfirmationWarning";
 //BA.debugLineNum = 1205;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1206;BA.debugLine="pnlHighBillConfirmation.Visible = True";
mostCurrent._pnlhighbillconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1207;BA.debugLine="btnHBConfirmCancel.Background = cdCancel";
mostCurrent._btnhbconfirmcancel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdcancel.getObject()));
 //BA.debugLineNum = 1208;BA.debugLine="btnHBConfirmSave.Background = cdOK";
mostCurrent._btnhbconfirmsave.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 1210;BA.debugLine="vibration.vibratePattern(vibratePattern, 0)";
mostCurrent._vibration.vibratePattern(processBA,_vibratepattern,(int) (0));
 //BA.debugLineNum = 1211;BA.debugLine="soundsAlarmChannel.Play(SoundID,1,1,1,0,1)";
_soundsalarmchannel.Play(_soundid,(float) (1),(float) (1),(int) (1),(int) (0),(float) (1));
 //BA.debugLineNum = 1212;BA.debugLine="txtPresRdgConfirm.Background = cdFixedText";
mostCurrent._txtpresrdgconfirm.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdfixedtext.getObject()));
 //BA.debugLineNum = 1213;BA.debugLine="txtPresRdgConfirm.Text = \"\"";
mostCurrent._txtpresrdgconfirm.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1214;BA.debugLine="btnHBConfirmSave.Enabled = False";
mostCurrent._btnhbconfirmsave.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1215;BA.debugLine="End Sub";
return "";
}
public static String  _showhighwarning() throws Exception{
 //BA.debugLineNum = 1160;BA.debugLine="Private Sub ShowHighWarning";
 //BA.debugLineNum = 1161;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1162;BA.debugLine="pnlHighProdMsg.Visible = True";
mostCurrent._pnlhighprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1163;BA.debugLine="btnHighCancel.Background = cdCancel";
mostCurrent._btnhighcancel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdcancel.getObject()));
 //BA.debugLineNum = 1164;BA.debugLine="btnHighOk.Background = cdOK";
mostCurrent._btnhighok.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 1166;BA.debugLine="vibration.vibratePattern(vibratePattern, 0)";
mostCurrent._vibration.vibratePattern(processBA,_vibratepattern,(int) (0));
 //BA.debugLineNum = 1167;BA.debugLine="soundsAlarmChannel.Play(SoundID,1,1,1,0,1)";
_soundsalarmchannel.Play(_soundid,(float) (1),(float) (1),(int) (1),(int) (0),(float) (1));
 //BA.debugLineNum = 1169;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtFMRdgRem";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtfmrdgremarks.getText()))<=0) { 
 //BA.debugLineNum = 1170;BA.debugLine="txtHighRemarks.Text = \"\"";
mostCurrent._txthighremarks.setText(BA.ObjectToCharSequence(""));
 }else {
 //BA.debugLineNum = 1172;BA.debugLine="txtHighRemarks.Text = txtFMRdgRemarks.Text";
mostCurrent._txthighremarks.setText(BA.ObjectToCharSequence(mostCurrent._txtfmrdgremarks.getText()));
 };
 //BA.debugLineNum = 1174;BA.debugLine="End Sub";
return "";
}
public static String  _showlowwarning() throws Exception{
 //BA.debugLineNum = 1123;BA.debugLine="Private Sub ShowLowWarning";
 //BA.debugLineNum = 1124;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1125;BA.debugLine="pnlLowProdMsg.Visible = True";
mostCurrent._pnllowprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1126;BA.debugLine="btnLowCancel.Background = cdCancel";
mostCurrent._btnlowcancel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdcancel.getObject()));
 //BA.debugLineNum = 1127;BA.debugLine="btnLowOk.Background = cdOK";
mostCurrent._btnlowok.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 1129;BA.debugLine="vibration.vibratePattern(vibratePattern, 0)";
mostCurrent._vibration.vibratePattern(processBA,_vibratepattern,(int) (0));
 //BA.debugLineNum = 1130;BA.debugLine="soundsAlarmChannel.Play(SoundID,1,1,1,0,1)";
_soundsalarmchannel.Play(_soundid,(float) (1),(float) (1),(int) (1),(int) (0),(float) (1));
 //BA.debugLineNum = 1131;BA.debugLine="End Sub";
return "";
}
public static String  _shownegativewarning() throws Exception{
 //BA.debugLineNum = 1064;BA.debugLine="Private Sub ShowNegativeWarning";
 //BA.debugLineNum = 1065;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1067;BA.debugLine="pnlNegativeProdMsg.Visible = True";
mostCurrent._pnlnegativeprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1069;BA.debugLine="chkBackFlow.Checked = False";
mostCurrent._chkbackflow.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1070;BA.debugLine="txtBackFlowCum.Text = \"\"";
mostCurrent._txtbackflowcum.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1071;BA.debugLine="txtBackFlowCum.Hint = (GlobalVar.SF.Val(txtFMRdg.";
mostCurrent._txtbackflowcum.setHint(BA.NumberToString((mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())-_dlastfmrdg))+" CuM Back flow");
 //BA.debugLineNum = 1073;BA.debugLine="txtNegativeRemarks.Text = \"\"";
mostCurrent._txtnegativeremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1075;BA.debugLine="btnNegativeCancel.Background = cdCancel";
mostCurrent._btnnegativecancel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdcancel.getObject()));
 //BA.debugLineNum = 1076;BA.debugLine="btnNegativeOk.Background = cdOK";
mostCurrent._btnnegativeok.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 1078;BA.debugLine="vibration.vibratePattern(vibratePattern, 0)";
mostCurrent._vibration.vibratePattern(processBA,_vibratepattern,(int) (0));
 //BA.debugLineNum = 1079;BA.debugLine="soundsAlarmChannel.Play(SoundID,1,1,1,0,1)";
_soundsalarmchannel.Play(_soundid,(float) (1),(float) (1),(int) (1),(int) (0),(float) (1));
 //BA.debugLineNum = 1080;BA.debugLine="End Sub";
return "";
}
public static String  _showsavesuccess() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
anywheresoftware.b4a.objects.CSBuilder _cscontent = null;
 //BA.debugLineNum = 1434;BA.debugLine="Private Sub ShowSaveSuccess()";
 //BA.debugLineNum = 1435;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 1436;BA.debugLine="Dim csContent As CSBuilder";
_cscontent = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 1438;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1439;BA.debugLine="If GlobalVar.blnNewFMRdg = True Then";
if (mostCurrent._globalvar._blnnewfmrdg /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1440;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 1441;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("New Pump Flow Meter Reading has been successfully saved!"))).PopAll();
 }else {
 //BA.debugLineNum = 1443;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 1444;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("Pump Flow Meter Reading has been successfully updated!"))).PopAll();
 };
 //BA.debugLineNum = 1446;BA.debugLine="MatDialogBuilder.Initialize(\"AddPumpTimeOnRecords";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"AddPumpTimeOnRecords");
 //BA.debugLineNum = 1447;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 1448;BA.debugLine="MatDialogBuilder.Content(csContent)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_cscontent.getObject()));
 //BA.debugLineNum = 1449;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 1450;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1451;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1452;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 1453;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 1454;BA.debugLine="End Sub";
return "";
}
public static String  _showzerowarning() throws Exception{
 //BA.debugLineNum = 1017;BA.debugLine="Private Sub ShowZeroWarning";
 //BA.debugLineNum = 1018;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1019;BA.debugLine="pnlZeroProdMsg.Visible = True";
mostCurrent._pnlzeroprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1020;BA.debugLine="btnZeroCancel.Background = cdCancel";
mostCurrent._btnzerocancel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdcancel.getObject()));
 //BA.debugLineNum = 1021;BA.debugLine="btnZeroOk.Background = cdOK";
mostCurrent._btnzerook.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 1023;BA.debugLine="vibration.vibratePattern(vibratePattern, 0)";
mostCurrent._vibration.vibratePattern(processBA,_vibratepattern,(int) (0));
 //BA.debugLineNum = 1024;BA.debugLine="soundsAlarmChannel.Play(SoundID,1,1,1,0,1)";
_soundsalarmchannel.Play(_soundid,(float) (1),(float) (1),(int) (1),(int) (0),(float) (1));
 //BA.debugLineNum = 1026;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtFMRdgRem";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtfmrdgremarks.getText()))<=0) { 
 //BA.debugLineNum = 1027;BA.debugLine="lblZeroMsg.Text = $\"It seems that your Pump Flow";
mostCurrent._lblzeromsg.setText(BA.ObjectToCharSequence(("It seems that your Pump Flow Meter didn't move from your Previous Reading")+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+("Reading Remarks is required this time.")));
 //BA.debugLineNum = 1028;BA.debugLine="txtZeroRemarks.Text = \"\"";
mostCurrent._txtzeroremarks.setText(BA.ObjectToCharSequence(""));
 }else {
 //BA.debugLineNum = 1030;BA.debugLine="lblZeroMsg.Text = $\"It seems that your Pump Flow";
mostCurrent._lblzeromsg.setText(BA.ObjectToCharSequence(("It seems that your Pump Flow Meter didn't move from your Previous Reading")+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+("Do you wish to save this record anyway?")));
 //BA.debugLineNum = 1031;BA.debugLine="txtZeroRemarks.Text = txtFMRdgRemarks.Text";
mostCurrent._txtzeroremarks.setText(BA.ObjectToCharSequence(mostCurrent._txtfmrdgremarks.getText()));
 };
 //BA.debugLineNum = 1033;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 268;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 269;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 253;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 254;BA.debugLine="If pnlZeroProdMsg.Visible = True Then";
if (mostCurrent._pnlzeroprodmsg.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 255;BA.debugLine="btnZeroCancel_Click";
_btnzerocancel_click();
 //BA.debugLineNum = 256;BA.debugLine="imeKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 }else if(mostCurrent._pnlnegativeprodmsg.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 258;BA.debugLine="btnNegativeOk_Click";
_btnnegativeok_click();
 //BA.debugLineNum = 259;BA.debugLine="imeKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 }else if(mostCurrent._ckeyboard.IsSoftKeyboardVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 261;BA.debugLine="cKeyboard.HideKeyboard";
mostCurrent._ckeyboard.HideKeyboard();
 }else {
 //BA.debugLineNum = 263;BA.debugLine="imeKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 264;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 266;BA.debugLine="End Sub";
return "";
}
public static String  _txtfmrdg_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 1473;BA.debugLine="Sub txtFMRdg_FocusChanged (HasFocus As Boolean)";
 //BA.debugLineNum = 1474;BA.debugLine="If HasFocus = True Then";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1477;BA.debugLine="cKeyboard.ShowKeyboard(txtFMRdg)";
mostCurrent._ckeyboard.ShowKeyboard((android.widget.EditText)(mostCurrent._txtfmrdg.getObject()));
 }else {
 //BA.debugLineNum = 1479;BA.debugLine="pnlKeyboard.Visible = False";
mostCurrent._pnlkeyboard.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1480;BA.debugLine="cKeyboard.HideKeyboard";
mostCurrent._ckeyboard.HideKeyboard();
 };
 //BA.debugLineNum = 1482;BA.debugLine="End Sub";
return "";
}
public static boolean  _txtfmrdg_handleaction() throws Exception{
 //BA.debugLineNum = 514;BA.debugLine="Sub txtFMRdg_HandleAction() As Boolean";
 //BA.debugLineNum = 515;BA.debugLine="cKeyboard.HideKeyboard";
mostCurrent._ckeyboard.HideKeyboard();
 //BA.debugLineNum = 516;BA.debugLine="txtFMRdgRemarks.RequestFocus";
mostCurrent._txtfmrdgremarks.RequestFocus();
 //BA.debugLineNum = 517;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 518;BA.debugLine="End Sub";
return false;
}
public static String  _txtpresrdgconfirm_enterpressed() throws Exception{
 //BA.debugLineNum = 1217;BA.debugLine="Sub txtPresRdgConfirm_EnterPressed";
 //BA.debugLineNum = 1219;BA.debugLine="End Sub";
return "";
}
public static String  _txtpresrdgconfirm_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 1221;BA.debugLine="Sub txtPresRdgConfirm_TextChanged (Old As String,";
 //BA.debugLineNum = 1222;BA.debugLine="If txtFMRdg.Text = New Or sHighRdg = New Then";
if ((mostCurrent._txtfmrdg.getText()).equals(_new) || (mostCurrent._shighrdg).equals(_new)) { 
 //BA.debugLineNum = 1223;BA.debugLine="btnHBConfirmSave.Enabled = True";
mostCurrent._btnhbconfirmsave.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 1225;BA.debugLine="btnHBConfirmSave.Enabled = False";
mostCurrent._btnhbconfirmsave.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 1227;BA.debugLine="End Sub";
return "";
}
public static String  _updatelastfmreadings(int _ipumpid) throws Exception{
 //BA.debugLineNum = 839;BA.debugLine="Private Sub UpdateLastFMReadings(iPumpID As Int)";
 //BA.debugLineNum = 841;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 842;BA.debugLine="Try";
try { //BA.debugLineNum = 843;BA.debugLine="Starter.strCriteria = \"UPDATE tblPumpStation \" &";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE tblPumpStation "+"SET LastRdg = ? "+"WHERE StationID = "+BA.NumberToString(_ipumpid);
 //BA.debugLineNum = 847;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{mostCurrent._txtfmrdg.getText()}));
 //BA.debugLineNum = 848;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 850;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("764552971",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 852;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 853;BA.debugLine="End Sub";
return "";
}
public static boolean  _updatetranheader(int _itranheaderid) throws Exception{
boolean _bretval = false;
double _gtotprod = 0;
long _lngdatetime = 0L;
String _smodifiedat = "";
double _dtotcum = 0;
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsheader = null;
 //BA.debugLineNum = 419;BA.debugLine="Private Sub UpdateTranHeader(iTranHeaderID As Int)";
 //BA.debugLineNum = 420;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 421;BA.debugLine="Dim GTotProd As Double";
_gtotprod = 0;
 //BA.debugLineNum = 423;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 424;BA.debugLine="Dim sModifiedAt As String";
_smodifiedat = "";
 //BA.debugLineNum = 425;BA.debugLine="Dim dTotCum As Double";
_dtotcum = 0;
 //BA.debugLineNum = 428;BA.debugLine="dTotCum = 0";
_dtotcum = 0;
 //BA.debugLineNum = 429;BA.debugLine="If isNegativeRdg = True Then";
if (_isnegativerdg==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 430;BA.debugLine="dTotCum = dLastFMRdg - GlobalVar.SF.Val(txtFMRdg";
_dtotcum = _dlastfmrdg-mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText());
 }else {
 //BA.debugLineNum = 432;BA.debugLine="dTotCum = GlobalVar.SF.Val(txtFMRdg.Text) - dLas";
_dtotcum = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())-_dlastfmrdg;
 };
 //BA.debugLineNum = 435;BA.debugLine="LogColor($\"Total CuM: \"$ & dTotCum, Colors.Yellow";
anywheresoftware.b4a.keywords.Common.LogImpl("763897616",("Total CuM: ")+BA.NumberToString(_dtotcum),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 437;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 438;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 439;BA.debugLine="sModifiedAt = DateTime.Date(lngDateTime)";
_smodifiedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 441;BA.debugLine="Dim rsHeader As Cursor";
_rsheader = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 443;BA.debugLine="Starter.strCriteria = \"SELECT * FROM TranHeader W";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM TranHeader WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 444;BA.debugLine="rsHeader = Starter.DBCon.ExecQuery(Starter.strCri";
_rsheader = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 446;BA.debugLine="If rsHeader.RowCount > 0 Then";
if (_rsheader.getRowCount()>0) { 
 //BA.debugLineNum = 447;BA.debugLine="rsHeader.Position = 0";
_rsheader.setPosition((int) (0));
 //BA.debugLineNum = 448;BA.debugLine="GTotProd = rsHeader.GetDouble(\"TotProduction\") +";
_gtotprod = _rsheader.GetDouble("TotProduction")+_dtotcum;
 }else {
 //BA.debugLineNum = 450;BA.debugLine="GTotProd =  dTotCum";
_gtotprod = _dtotcum;
 };
 //BA.debugLineNum = 452;BA.debugLine="rsHeader.Close";
_rsheader.Close();
 //BA.debugLineNum = 454;BA.debugLine="LogColor($\"Total Production: \"$ & GTotProd, Color";
anywheresoftware.b4a.keywords.Common.LogImpl("763897635",("Total Production: ")+BA.NumberToString(_gtotprod),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 456;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 458;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 459;BA.debugLine="Try";
try { //BA.debugLineNum = 460;BA.debugLine="Starter.strCriteria = \"UPDATE TranHeader SET \" &";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE TranHeader SET "+"TotProduction = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 466;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_gtotprod),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_smodifiedat}));
 //BA.debugLineNum = 467;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 468;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e35) {
			processBA.setLastException(e35); //BA.debugLineNum = 470;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("763897651",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 471;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 473;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 474;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 475;BA.debugLine="End Sub";
return false;
}
public static void  _uploadreadingdata(String _sdata) throws Exception{
ResumableSub_UploadReadingData rsub = new ResumableSub_UploadReadingData(null,_sdata);
rsub.resume(processBA, null);
}
public static class ResumableSub_UploadReadingData extends BA.ResumableSub {
public ResumableSub_UploadReadingData(bwsi.PumpOperations.addeditfmrdg parent,String _sdata) {
this.parent = parent;
this._sdata = _sdata;
}
bwsi.PumpOperations.addeditfmrdg parent;
String _sdata;
String _retval = "";
anywheresoftware.b4a.objects.collections.JSONParser _jparser = null;
bwsi.PumpOperations.httpjob _j = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 978;BA.debugLine="Dim retVal As String";
_retval = "";
 //BA.debugLineNum = 979;BA.debugLine="Dim jParser As JSONParser";
_jparser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 981;BA.debugLine="Dim j As HttpJob";
_j = new bwsi.PumpOperations.httpjob();
 //BA.debugLineNum = 982;BA.debugLine="j.Initialize(\"\", Me)";
_j._initialize /*String*/ (processBA,"",addeditfmrdg.getObject());
 //BA.debugLineNum = 984;BA.debugLine="j.PostString(GlobalVar.BaseURL & \"logs/production";
_j._poststring /*String*/ (parent.mostCurrent._globalvar._baseurl /*String*/ +"logs/production",_sdata);
 //BA.debugLineNum = 985;BA.debugLine="j.GetRequest.SetHeader(\"User-Agent\", \"Mozilla/5.0";
_j._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ ().SetHeader("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.92 Safari/537.36");
 //BA.debugLineNum = 986;BA.debugLine="j.GetRequest.SetContentType(\"plain/text\")";
_j._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ ().SetContentType("plain/text");
 //BA.debugLineNum = 987;BA.debugLine="Log(sData)";
anywheresoftware.b4a.keywords.Common.LogImpl("764815114",_sdata,0);
 //BA.debugLineNum = 989;BA.debugLine="Wait For (j) JobDone(j As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_j));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_j = (bwsi.PumpOperations.httpjob) result[0];
;
 //BA.debugLineNum = 990;BA.debugLine="If j.Success Then";
if (true) break;

case 1:
//if
this.state = 6;
if (_j._success /*boolean*/ ) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 991;BA.debugLine="retVal = j.GetString";
_retval = _j._getstring /*String*/ ();
 //BA.debugLineNum = 992;BA.debugLine="jParser.Initialize(retVal)";
_jparser.Initialize(_retval);
 //BA.debugLineNum = 993;BA.debugLine="Log(retVal)";
anywheresoftware.b4a.keywords.Common.LogImpl("764815120",_retval,0);
 //BA.debugLineNum = 994;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 995;BA.debugLine="j.Release";
_j._release /*String*/ ();
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 997;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 998;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("764815125",_j._errormessage /*String*/ ,0);
 //BA.debugLineNum = 1000;BA.debugLine="jParser.Initialize(retVal)";
_jparser.Initialize(_retval);
 //BA.debugLineNum = 1001;BA.debugLine="Log(retVal)";
anywheresoftware.b4a.keywords.Common.LogImpl("764815128",_retval,0);
 //BA.debugLineNum = 1003;BA.debugLine="ToastMessageShow(\"Unable to Upload Reading Data";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Unable to Upload Reading Data due to "+_j._errormessage /*String*/ ),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1004;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 1005;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("764815132",_j._errormessage /*String*/ ,0);
 //BA.debugLineNum = 1006;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 1008;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _jobdone(bwsi.PumpOperations.httpjob _j) throws Exception{
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
