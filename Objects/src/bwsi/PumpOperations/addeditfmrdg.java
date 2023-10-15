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
 //BA.debugLineNum = 143;BA.debugLine="InpTyp.Initialize";
_inptyp._initialize /*String*/ (processBA);
 //BA.debugLineNum = 144;BA.debugLine="InpTyp.SetInputType(txtFMRdgRemarks,Array As Int(";
_inptyp._setinputtype /*String*/ (mostCurrent._txtfmrdgremarks,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_correct /*int*/ (),_inptyp._type_text_flag_cap_sentences /*int*/ ()});
 //BA.debugLineNum = 147;BA.debugLine="imeKeyboard.Initialize(\"\")";
mostCurrent._imekeyboard.Initialize("");
 //BA.debugLineNum = 148;BA.debugLine="soundsAlarmChannel.Initialize(2)";
_soundsalarmchannel.Initialize((int) (2));
 //BA.debugLineNum = 149;BA.debugLine="ClearUI";
_clearui();
 //BA.debugLineNum = 150;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 152;BA.debugLine="If GlobalVar.blnNewFMRdg = True Then";
if (mostCurrent._globalvar._blnnewfmrdg /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 153;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\"  SAVE\"$";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+("  SAVE")));
 //BA.debugLineNum = 154;BA.debugLine="btnCancel.Text = Chr(0xE5C9) & $\"  CANCEL\"$";
mostCurrent._btncancel.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe5c9)))+("  CANCEL")));
 }else {
 //BA.debugLineNum = 156;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\" UPDATE\"$";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" UPDATE")));
 //BA.debugLineNum = 157;BA.debugLine="btnCancel.Text = Chr(0xE5C9) & $\"  CANCEL\"$";
mostCurrent._btncancel.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe5c9)))+("  CANCEL")));
 //BA.debugLineNum = 158;BA.debugLine="GetFMRdgRecord(GlobalVar.FMRdgDetailID)";
_getfmrdgrecord(mostCurrent._globalvar._fmrdgdetailid /*int*/ );
 };
 //BA.debugLineNum = 161;BA.debugLine="dLastFMRdg = DBaseFunctions.GetLastFMReading(Glob";
_dlastfmrdg = mostCurrent._dbasefunctions._getlastfmreading /*double*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 163;BA.debugLine="csAns.Initialize.Color(Colors.White).Bold.Append(";
mostCurrent._csans.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Bold().Append(BA.ObjectToCharSequence(("YES"))).PopAll();
 //BA.debugLineNum = 168;BA.debugLine="MyFunctions.SetButton(btnSaveUpdate, 25, 25, 25,";
mostCurrent._myfunctions._setbutton /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btnsaveupdate.getObject())),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25));
 //BA.debugLineNum = 169;BA.debugLine="MyFunctions.SetCancelButton(btnCancel, 25, 25, 25";
mostCurrent._myfunctions._setcancelbutton /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btncancel.getObject())),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25),(float) (25));
 //BA.debugLineNum = 170;BA.debugLine="sRdgTime = \"\"";
mostCurrent._srdgtime = "";
 //BA.debugLineNum = 172;BA.debugLine="cdCancel.Initialize2(GlobalVar.RedColor, 20, 0, C";
mostCurrent._cdcancel.Initialize2((int) (mostCurrent._globalvar._redcolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 173;BA.debugLine="cdOK.Initialize2(GlobalVar.GreenColor, 20, 0, Col";
mostCurrent._cdok.Initialize2((int) (mostCurrent._globalvar._greencolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 175;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 176;BA.debugLine="pnlKeyboard.Initialize(\"\")";
mostCurrent._pnlkeyboard.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 177;BA.debugLine="pnlKeyboard.BringToFront";
mostCurrent._pnlkeyboard.BringToFront();
 //BA.debugLineNum = 179;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
 //BA.debugLineNum = 246;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 248;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 249;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 219;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 220;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 221;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 222;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 224;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 226;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 240;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 241;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 193;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 194;BA.debugLine="If Result Then";
if (_result) { 
 //BA.debugLineNum = 195;BA.debugLine="If Permission = Starter.RTP.PERMISSION_READ_EXTE";
if ((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 196;BA.debugLine="LogColor($\"Permission to Read External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("060162051",("Permission to Read External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 197;BA.debugLine="GlobalVar.ReadStoragePermission = True";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 199;BA.debugLine="LogColor($\"Permission to Write External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("060162054",("Permission to Write External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 200;BA.debugLine="GlobalVar.WriteStoragePermission = True";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION)) { 
 //BA.debugLineNum = 202;BA.debugLine="LogColor($\"Permission to Access Coarse Location";
anywheresoftware.b4a.keywords.Common.LogImpl("060162057",("Permission to Access Coarse Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 203;BA.debugLine="GlobalVar.CoarseLocPermission = True";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION)) { 
 //BA.debugLineNum = 205;BA.debugLine="LogColor($\"Permission to Access Fine Location G";
anywheresoftware.b4a.keywords.Common.LogImpl("060162060",("Permission to Access Fine Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 206;BA.debugLine="GlobalVar.FineLocPermission = True";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 208;BA.debugLine="Starter.StartFLP";
mostCurrent._starter._startflp /*void*/ ();
 }else {
 //BA.debugLineNum = 210;BA.debugLine="GlobalVar.ReadStoragePermission = False";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 211;BA.debugLine="GlobalVar.WriteStoragePermission = False";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 212;BA.debugLine="GlobalVar.CoarseLocPermission = False";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 213;BA.debugLine="GlobalVar.FineLocPermission = False";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 214;BA.debugLine="Result = False";
_result = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 216;BA.debugLine="Log (Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("060162071",_permission,0);
 //BA.debugLineNum = 217;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 228;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 229;BA.debugLine="SoundID = soundsAlarmChannel.Load(File.DirAssets,";
_soundid = _soundsalarmchannel.Load(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"beep.wav");
 //BA.debugLineNum = 230;BA.debugLine="vibratePattern = Array As Long(500, 500, 300, 500";
_vibratepattern = new long[]{(long) (500),(long) (500),(long) (300),(long) (500)};
 //BA.debugLineNum = 231;BA.debugLine="If soundsAlarmChannel.IsInitialized = False Then";
if (_soundsalarmchannel.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_soundsalarmchannel.Initialize((int) (2));};
 //BA.debugLineNum = 232;BA.debugLine="txtFMRdg.InputType = Bit.Or(txtFMRdg.InputType,TY";
mostCurrent._txtfmrdg.setInputType(anywheresoftware.b4a.keywords.Common.Bit.Or(mostCurrent._txtfmrdg.getInputType(),_type_text_flag_no_suggestions));
 //BA.debugLineNum = 233;BA.debugLine="txtFMRdg.SingleLine = True";
mostCurrent._txtfmrdg.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 234;BA.debugLine="txtFMRdg.ForceDoneButton = True";
mostCurrent._txtfmrdg.setForceDoneButton(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 235;BA.debugLine="cKeyboard.Initialize(\"CKB\",\"keyboardview_trans\")";
mostCurrent._ckeyboard.Initialize(mostCurrent.activityBA,"CKB","keyboardview_trans");
 //BA.debugLineNum = 236;BA.debugLine="cKeyboard.RegisterEditText(txtFMRdg,\"txtFMRdg\",\"n";
mostCurrent._ckeyboard.RegisterEditText((android.widget.EditText)(mostCurrent._txtfmrdg.getObject()),"txtFMRdg","num",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 238;BA.debugLine="End Sub";
return "";
}
public static String  _addpumptimeonrecords_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 1453;BA.debugLine="Private Sub AddPumpTimeOnRecords_ButtonPressed(mDi";
 //BA.debugLineNum = 1454;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE)) {
case 0: {
 //BA.debugLineNum = 1456;BA.debugLine="imeKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 1457;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
}
;
 //BA.debugLineNum = 1459;BA.debugLine="End Sub";
return "";
}
public static String  _btncancel_click() throws Exception{
 //BA.debugLineNum = 609;BA.debugLine="Sub btnCancel_Click";
 //BA.debugLineNum = 610;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 611;BA.debugLine="End Sub";
return "";
}
public static String  _btnhbconfirmcancel_click() throws Exception{
 //BA.debugLineNum = 1230;BA.debugLine="Sub btnHBConfirmCancel_Click";
 //BA.debugLineNum = 1231;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1232;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1233;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1234;BA.debugLine="txtFMRdg.RequestFocus";
mostCurrent._txtfmrdg.RequestFocus();
 //BA.debugLineNum = 1235;BA.debugLine="End Sub";
return "";
}
public static String  _btnhbconfirmsave_click() throws Exception{
 //BA.debugLineNum = 1226;BA.debugLine="Sub btnHBConfirmSave_Click";
 //BA.debugLineNum = 1227;BA.debugLine="ConfirmSaveRdg";
_confirmsaverdg();
 //BA.debugLineNum = 1228;BA.debugLine="End Sub";
return "";
}
public static String  _btnhighcancel_click() throws Exception{
 //BA.debugLineNum = 1188;BA.debugLine="Sub btnHighCancel_Click";
 //BA.debugLineNum = 1189;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1190;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1191;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1192;BA.debugLine="txtFMRdg.RequestFocus";
mostCurrent._txtfmrdg.RequestFocus();
 //BA.debugLineNum = 1193;BA.debugLine="End Sub";
return "";
}
public static String  _btnhighok_click() throws Exception{
 //BA.debugLineNum = 1173;BA.debugLine="Sub btnHighOk_Click";
 //BA.debugLineNum = 1174;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1175;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1177;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtHighRema";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txthighremarks.getText()))<=0) { 
 //BA.debugLineNum = 1178;BA.debugLine="RequiredMsgBox($\"REMARKS REQUIRED\"$, $\"Remarks i";
_requiredmsgbox(("REMARKS REQUIRED"),("Remarks is required for this reading!"));
 //BA.debugLineNum = 1179;BA.debugLine="txtHighRemarks.RequestFocus";
mostCurrent._txthighremarks.RequestFocus();
 //BA.debugLineNum = 1180;BA.debugLine="imeKeyboard.ShowKeyboard(txtHighRemarks)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._txthighremarks.getObject()));
 //BA.debugLineNum = 1181;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 1183;BA.debugLine="txtFMRdgRemarks.Text = txtHighRemarks.Text";
mostCurrent._txtfmrdgremarks.setText(BA.ObjectToCharSequence(mostCurrent._txthighremarks.getText()));
 };
 //BA.debugLineNum = 1185;BA.debugLine="ShowHighConfirmationWarning";
_showhighconfirmationwarning();
 //BA.debugLineNum = 1186;BA.debugLine="End Sub";
return "";
}
public static String  _btnlowcancel_click() throws Exception{
 //BA.debugLineNum = 1147;BA.debugLine="Sub btnLowCancel_Click";
 //BA.debugLineNum = 1149;BA.debugLine="End Sub";
return "";
}
public static String  _btnlowok_click() throws Exception{
 //BA.debugLineNum = 1130;BA.debugLine="Sub btnLowOk_Click";
 //BA.debugLineNum = 1131;BA.debugLine="pnlLowProdMsg.Visible = False";
mostCurrent._pnllowprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1133;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1134;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1136;BA.debugLine="If GlobalVar.SF.Len(txtFMRdgRemarks.Text) <= 0 Th";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtfmrdgremarks.getText())<=0) { 
 //BA.debugLineNum = 1137;BA.debugLine="RequiredMsgBox($\"REMARKS REQUIRED\"$, $\"Remarks i";
_requiredmsgbox(("REMARKS REQUIRED"),("Remarks is required for this reading!"));
 //BA.debugLineNum = 1138;BA.debugLine="txtFMRdgRemarks.RequestFocus";
mostCurrent._txtfmrdgremarks.RequestFocus();
 //BA.debugLineNum = 1139;BA.debugLine="imeKeyboard.ShowKeyboard(txtFMRdgRemarks)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._txtfmrdgremarks.getObject()));
 //BA.debugLineNum = 1140;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 1142;BA.debugLine="SaveUpdateFMReading";
_saveupdatefmreading();
 };
 //BA.debugLineNum = 1145;BA.debugLine="End Sub";
return "";
}
public static String  _btnnegativecancel_click() throws Exception{
 //BA.debugLineNum = 1107;BA.debugLine="Sub btnNegativeCancel_Click";
 //BA.debugLineNum = 1108;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1109;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1110;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1111;BA.debugLine="End Sub";
return "";
}
public static String  _btnnegativeok_click() throws Exception{
 //BA.debugLineNum = 1089;BA.debugLine="Sub btnNegativeOk_Click";
 //BA.debugLineNum = 1090;BA.debugLine="If GlobalVar.blnNewFMRdg = True Then";
if (mostCurrent._globalvar._blnnewfmrdg /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1091;BA.debugLine="If GlobalVar.SF.Len(txtBackFlowCum.Text) <=0 The";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtbackflowcum.getText())<=0) { 
 //BA.debugLineNum = 1092;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, $\"Unable to save";
_requiredmsgbox(("E R R O R"),("Unable to save Reading due to Back flow in CuM is blank!"));
 //BA.debugLineNum = 1093;BA.debugLine="txtBackFlowCum.RequestFocus";
mostCurrent._txtbackflowcum.RequestFocus();
 //BA.debugLineNum = 1094;BA.debugLine="dBackFlow = 0";
_dbackflow = 0;
 //BA.debugLineNum = 1095;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1097;BA.debugLine="If GlobalVar.SF.Len(txtNegativeRemarks.Text) <=0";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtnegativeremarks.getText())<=0) { 
 //BA.debugLineNum = 1098;BA.debugLine="RequiredMsgBox($\"E R R O R\"$, $\"Unable to save";
_requiredmsgbox(("E R R O R"),("Unable to save Reading due to Reading Remarks is required!"));
 //BA.debugLineNum = 1099;BA.debugLine="txtNegativeRemarks.RequestFocus";
mostCurrent._txtnegativeremarks.RequestFocus();
 //BA.debugLineNum = 1100;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1102;BA.debugLine="ConfirmSaveNegativeRdg";
_confirmsavenegativerdg();
 }else {
 };
 //BA.debugLineNum = 1105;BA.debugLine="End Sub";
return "";
}
public static String  _btnsaveupdate_click() throws Exception{
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
String _smin = "";
String _shr = "";
int _ihrs = 0;
int _imins = 0;
 //BA.debugLineNum = 521;BA.debugLine="Sub btnSaveUpdate_Click";
 //BA.debugLineNum = 522;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 523;BA.debugLine="Dim sMin, sHr As String";
_smin = "";
_shr = "";
 //BA.debugLineNum = 524;BA.debugLine="sRdgTime = \"\"";
mostCurrent._srdgtime = "";
 //BA.debugLineNum = 526;BA.debugLine="If Not(IsValidEntries) Then Return 'Check Entries";
if (anywheresoftware.b4a.keywords.Common.Not(_isvalidentries())) { 
if (true) return "";};
 //BA.debugLineNum = 528;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d)\", mskTime";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d)",mostCurrent._msktimeread.getText());
 //BA.debugLineNum = 530;BA.debugLine="If Matcher1.Find Then 'Split";
if (_matcher1.Find()) { 
 //BA.debugLineNum = 531;BA.debugLine="Dim iHrs, iMins As Int";
_ihrs = 0;
_imins = 0;
 //BA.debugLineNum = 533;BA.debugLine="iHrs = Matcher1.Group(1)";
_ihrs = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 534;BA.debugLine="iMins = Matcher1.Group(2)";
_imins = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 536;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(iMins)) =";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(BA.NumberToString(_imins)))==1) { 
 //BA.debugLineNum = 537;BA.debugLine="sMin = $\"0\"$ & iMins";
_smin = ("0")+BA.NumberToString(_imins);
 }else {
 //BA.debugLineNum = 539;BA.debugLine="sMin = iMins";
_smin = BA.NumberToString(_imins);
 };
 //BA.debugLineNum = 542;BA.debugLine="If iHrs = 0 Then '12 AM";
if (_ihrs==0) { 
 //BA.debugLineNum = 543;BA.debugLine="sHr = 12";
_shr = BA.NumberToString(12);
 //BA.debugLineNum = 544;BA.debugLine="sRdgTime = sHr & \":\" & sMin & \" AM\"";
mostCurrent._srdgtime = _shr+":"+_smin+" AM";
 }else if(_ihrs>0 && _ihrs<12) { 
 //BA.debugLineNum = 546;BA.debugLine="sHr = iHrs";
_shr = BA.NumberToString(_ihrs);
 //BA.debugLineNum = 547;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr)) = 1";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_shr))==1) { 
 //BA.debugLineNum = 548;BA.debugLine="sRdgTime = $\"0\"$ & sHr & \":\" & sMin & \" AM\"";
mostCurrent._srdgtime = ("0")+_shr+":"+_smin+" AM";
 }else {
 //BA.debugLineNum = 550;BA.debugLine="sRdgTime = sHr & \":\" & sMin & \" AM\"";
mostCurrent._srdgtime = _shr+":"+_smin+" AM";
 };
 }else if(_ihrs==12) { 
 //BA.debugLineNum = 554;BA.debugLine="sHr = 12";
_shr = BA.NumberToString(12);
 //BA.debugLineNum = 555;BA.debugLine="sRdgTime = sHr & \":\" & sMin & \" PM\"";
mostCurrent._srdgtime = _shr+":"+_smin+" PM";
 }else {
 //BA.debugLineNum = 557;BA.debugLine="sHr = iHrs - 12";
_shr = BA.NumberToString(_ihrs-12);
 //BA.debugLineNum = 558;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(sHr)) = 1";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_shr))==1) { 
 //BA.debugLineNum = 559;BA.debugLine="sRdgTime = $\"0\"$ & sHr & \":\" & sMin & \" PM\"";
mostCurrent._srdgtime = ("0")+_shr+":"+_smin+" PM";
 }else {
 //BA.debugLineNum = 561;BA.debugLine="sRdgTime = sHr & \":\" & sMin & \" PM\"";
mostCurrent._srdgtime = _shr+":"+_smin+" PM";
 };
 };
 };
 //BA.debugLineNum = 566;BA.debugLine="LogColor($\"Reading Time: \"$ & sRdgTime,Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("061276205",("Reading Time: ")+mostCurrent._srdgtime,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 568;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 569;BA.debugLine="LogColor($\"Header ID: \"$ & GlobalVar.TranHeaderID";
anywheresoftware.b4a.keywords.Common.LogImpl("061276208",("Header ID: ")+BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 571;BA.debugLine="If DBaseFunctions.IsFMRdgDetailHeaderIDExist(Glob";
if (mostCurrent._dbasefunctions._isfmrdgdetailheaderidexist /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._tranheaderid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 572;BA.debugLine="If DBaseFunctions.IsReadTimeOverlapse(sRdgTime,";
if (mostCurrent._dbasefunctions._isreadtimeoverlapse /*boolean*/ (mostCurrent.activityBA,mostCurrent._srdgtime,mostCurrent._globalvar._tranheaderid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 573;BA.debugLine="RequiredMsgBox($\"TIME OVERLAPPING\"$, $\"Unable t";
_requiredmsgbox(("TIME OVERLAPPING"),("Unable to Save transaction for it will overlapped existing reading!"));
 //BA.debugLineNum = 574;BA.debugLine="chkDefaultTimeRead.Checked = False";
mostCurrent._chkdefaulttimeread.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 575;BA.debugLine="mskTimeRead.RequestFocus";
mostCurrent._msktimeread.RequestFocus();
 //BA.debugLineNum = 576;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 580;BA.debugLine="If GlobalVar.SF.Val(txtFMRdg.Text) < dLastFMRdg T";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())<_dlastfmrdg) { 
 //BA.debugLineNum = 582;BA.debugLine="isNegativeRdg = True";
_isnegativerdg = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 583;BA.debugLine="ShowNegativeWarning";
_shownegativewarning();
 //BA.debugLineNum = 584;BA.debugLine="Return";
if (true) return "";
 }else if(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())==_dlastfmrdg) { 
 //BA.debugLineNum = 587;BA.debugLine="isNegativeRdg = False";
_isnegativerdg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 588;BA.debugLine="ShowZeroWarning";
_showzerowarning();
 //BA.debugLineNum = 589;BA.debugLine="Return";
if (true) return "";
 }else if((mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())-_dlastfmrdg)>=200) { 
 //BA.debugLineNum = 592;BA.debugLine="isNegativeRdg = False";
_isnegativerdg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 593;BA.debugLine="sHighRdg = txtFMRdg.Text";
mostCurrent._shighrdg = mostCurrent._txtfmrdg.getText();
 //BA.debugLineNum = 594;BA.debugLine="ShowHighWarning";
_showhighwarning();
 }else if((mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())-_dlastfmrdg)<=50) { 
 //BA.debugLineNum = 598;BA.debugLine="isNegativeRdg = False";
_isnegativerdg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 599;BA.debugLine="ConfirmSaveRdg";
_confirmsaverdg();
 }else {
 //BA.debugLineNum = 601;BA.debugLine="isNegativeRdg = False";
_isnegativerdg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 602;BA.debugLine="ConfirmSaveRdg";
_confirmsaverdg();
 };
 //BA.debugLineNum = 606;BA.debugLine="End Sub";
return "";
}
public static String  _btnzerocancel_click() throws Exception{
 //BA.debugLineNum = 1047;BA.debugLine="Sub btnZeroCancel_Click";
 //BA.debugLineNum = 1048;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1049;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1050;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1051;BA.debugLine="txtFMRdg.RequestFocus";
mostCurrent._txtfmrdg.RequestFocus();
 //BA.debugLineNum = 1052;BA.debugLine="End Sub";
return "";
}
public static String  _btnzerook_click() throws Exception{
 //BA.debugLineNum = 1032;BA.debugLine="Sub btnZeroOk_Click";
 //BA.debugLineNum = 1033;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1034;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1036;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtZeroRema";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtzeroremarks.getText()))<=0) { 
 //BA.debugLineNum = 1037;BA.debugLine="RequiredMsgBox($\"REMARKS REQUIRED\"$, $\"Remarks i";
_requiredmsgbox(("REMARKS REQUIRED"),("Remarks is required for this reading!"));
 //BA.debugLineNum = 1038;BA.debugLine="txtZeroRemarks.RequestFocus";
mostCurrent._txtzeroremarks.RequestFocus();
 //BA.debugLineNum = 1039;BA.debugLine="imeKeyboard.ShowKeyboard(txtZeroRemarks)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._txtzeroremarks.getObject()));
 //BA.debugLineNum = 1040;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 1042;BA.debugLine="txtFMRdgRemarks.Text = txtZeroRemarks.Text";
mostCurrent._txtfmrdgremarks.setText(BA.ObjectToCharSequence(mostCurrent._txtzeroremarks.getText()));
 };
 //BA.debugLineNum = 1044;BA.debugLine="ConfirmSaveRdg";
_confirmsaverdg();
 //BA.debugLineNum = 1045;BA.debugLine="End Sub";
return "";
}
public static String  _checkpermissions() throws Exception{
 //BA.debugLineNum = 181;BA.debugLine="Private Sub CheckPermissions";
 //BA.debugLineNum = 182;BA.debugLine="Log(\"Checking Permissions\")";
anywheresoftware.b4a.keywords.Common.LogImpl("060096513","Checking Permissions",0);
 //BA.debugLineNum = 184;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE);
 //BA.debugLineNum = 185;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE);
 //BA.debugLineNum = 186;BA.debugLine="Starter.RTP.GetAllSafeDirsExternal(\"\")";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .GetAllSafeDirsExternal("");
 //BA.debugLineNum = 188;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION);
 //BA.debugLineNum = 189;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 190;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 191;BA.debugLine="End Sub";
return "";
}
public static String  _chkbackflow_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 1079;BA.debugLine="Sub chkBackFlow_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 1080;BA.debugLine="If Checked = True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1081;BA.debugLine="txtBackFlowCum.Enabled = True";
mostCurrent._txtbackflowcum.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1082;BA.debugLine="txtBackFlowCum.RequestFocus";
mostCurrent._txtbackflowcum.RequestFocus();
 }else {
 //BA.debugLineNum = 1084;BA.debugLine="txtBackFlowCum.Enabled = False";
mostCurrent._txtbackflowcum.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1085;BA.debugLine="txtBackFlowCum.Text = \"\"";
mostCurrent._txtbackflowcum.setText(BA.ObjectToCharSequence(""));
 };
 //BA.debugLineNum = 1087;BA.debugLine="End Sub";
return "";
}
public static String  _chkdefaulttimeread_checkedchange(boolean _checked) throws Exception{
String _shour = "";
String _smin = "";
long _lhour = 0L;
long _lmin = 0L;
 //BA.debugLineNum = 478;BA.debugLine="Sub chkDefaultTimeRead_CheckedChange(Checked As Bo";
 //BA.debugLineNum = 479;BA.debugLine="Dim sHour As String";
_shour = "";
 //BA.debugLineNum = 480;BA.debugLine="Dim sMin As String";
_smin = "";
 //BA.debugLineNum = 481;BA.debugLine="Dim lHour, lMin As Long";
_lhour = 0L;
_lmin = 0L;
 //BA.debugLineNum = 483;BA.debugLine="If Checked = True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 484;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 485;BA.debugLine="lHour = DateTime.GetHour(DateTime.Now)";
_lhour = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 486;BA.debugLine="lMin = DateTime.GetMinute(DateTime.Now)";
_lmin = (long) (anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 488;BA.debugLine="If GlobalVar.SF.Len(lHour) = 1 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_lhour))==1) { 
 //BA.debugLineNum = 489;BA.debugLine="sHour = $\"0\"$ & lHour";
_shour = ("0")+BA.NumberToString(_lhour);
 }else {
 //BA.debugLineNum = 491;BA.debugLine="sHour = lHour";
_shour = BA.NumberToString(_lhour);
 };
 //BA.debugLineNum = 494;BA.debugLine="If GlobalVar.SF.Len(lMin) = 1 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_lmin))==1) { 
 //BA.debugLineNum = 495;BA.debugLine="sMin = $\"0\"$ & lMin";
_smin = ("0")+BA.NumberToString(_lmin);
 }else {
 //BA.debugLineNum = 497;BA.debugLine="sMin = lMin";
_smin = BA.NumberToString(_lmin);
 };
 //BA.debugLineNum = 500;BA.debugLine="mskTimeRead.Text = sHour & \":\" & sMin";
mostCurrent._msktimeread.setText((Object)(_shour+":"+_smin));
 //BA.debugLineNum = 503;BA.debugLine="mskTimeRead.Enabled = False";
mostCurrent._msktimeread.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 505;BA.debugLine="mskTimeRead.Enabled = True";
mostCurrent._msktimeread.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 506;BA.debugLine="mskTimeRead.Text = \"__:__\"";
mostCurrent._msktimeread.setText((Object)("__:__"));
 //BA.debugLineNum = 507;BA.debugLine="mskTimeRead.RequestFocus";
mostCurrent._msktimeread.RequestFocus();
 //BA.debugLineNum = 508;BA.debugLine="imeKeyboard.ShowKeyboard(mskTimeRead)";
mostCurrent._imekeyboard.ShowKeyboard((android.view.View)(mostCurrent._msktimeread.getObject()));
 };
 //BA.debugLineNum = 510;BA.debugLine="End Sub";
return "";
}
public static String  _clearui() throws Exception{
 //BA.debugLineNum = 272;BA.debugLine="Private Sub ClearUI";
 //BA.debugLineNum = 273;BA.debugLine="Try";
try { //BA.debugLineNum = 274;BA.debugLine="CDtxtBox.Initialize(Colors.Transparent,0)";
mostCurrent._cdtxtbox.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 275;BA.debugLine="cdFixedText.Initialize2(Colors.Black,0,0,0)";
mostCurrent._cdfixedtext.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (0),(int) (0),(int) (0));
 //BA.debugLineNum = 277;BA.debugLine="txtFMRdg.Background = cdFixedText";
mostCurrent._txtfmrdg.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdfixedtext.getObject()));
 //BA.debugLineNum = 278;BA.debugLine="txtBackFlowCum.Background = CDtxtBox";
mostCurrent._txtbackflowcum.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 279;BA.debugLine="txtNegativeRemarks.Background = CDtxtBox";
mostCurrent._txtnegativeremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 280;BA.debugLine="mskTimeRead.Background = CDtxtBox";
mostCurrent._msktimeread.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 281;BA.debugLine="txtFMRdgRemarks.Background = CDtxtBox";
mostCurrent._txtfmrdgremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 282;BA.debugLine="txtZeroRemarks.Background = CDtxtBox";
mostCurrent._txtzeroremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 283;BA.debugLine="txtHighRemarks.Background = CDtxtBox";
mostCurrent._txthighremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdtxtbox.getObject()));
 //BA.debugLineNum = 285;BA.debugLine="chkDefaultTimeRead.Checked = False";
mostCurrent._chkdefaulttimeread.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 286;BA.debugLine="mskTimeRead.Text = \"__:__\"";
mostCurrent._msktimeread.setText((Object)("__:__"));
 //BA.debugLineNum = 287;BA.debugLine="txtFMRdg.Text = \"\"";
mostCurrent._txtfmrdg.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 288;BA.debugLine="txtFMRdgRemarks.Text = \"\"";
mostCurrent._txtfmrdgremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 290;BA.debugLine="isNegativeRdg = False";
_isnegativerdg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 291;BA.debugLine="sHighRdg = \"\"";
mostCurrent._shighrdg = "";
 } 
       catch (Exception e18) {
			processBA.setLastException(e18); //BA.debugLineNum = 293;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("060620821",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 295;BA.debugLine="End Sub";
return "";
}
public static String  _confirmsavenegativerdg() throws Exception{
 //BA.debugLineNum = 1274;BA.debugLine="Private Sub ConfirmSaveNegativeRdg";
 //BA.debugLineNum = 1275;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1277;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle("SAVE NEGATIVE READING").SetTitleColor((int) (mostCurrent._globalvar._bluecolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessage(("Continuing this may ALTER your previous reading due to back flow")+anywheresoftware.b4a.keywords.Common.CRLF+("Save reading anyway?")).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(_font.getObject())).SetMessageTypeface((android.graphics.Typeface)(_font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"SaveNegative").SetOnNegativeClicked(mostCurrent.activityBA,"SaveNegative");
 //BA.debugLineNum = 1294;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 1295;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 1296;BA.debugLine="End Sub";
return "";
}
public static String  _confirmsaverdg() throws Exception{
 //BA.debugLineNum = 1351;BA.debugLine="Private Sub ConfirmSaveRdg";
 //BA.debugLineNum = 1352;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1354;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle("SAVE NEW READING").SetTitleColor((int) (mostCurrent._globalvar._bluecolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessage(("Save New Pump Flow Meter Reading?")).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(_font.getObject())).SetMessageTypeface((android.graphics.Typeface)(_font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"NormalReading").SetOnNegativeClicked(mostCurrent.activityBA,"NormalReading");
 //BA.debugLineNum = 1371;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 1372;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 1373;BA.debugLine="End Sub";
return "";
}
public static String  _dispinfomsg(String _stitle,String _smsg) throws Exception{
 //BA.debugLineNum = 1412;BA.debugLine="Private Sub DispInfoMsg(sTitle As String, sMsg As";
 //BA.debugLineNum = 1413;BA.debugLine="MatDialogBuilder.Initialize(\"DispInformationMsg\")";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"DispInformationMsg");
 //BA.debugLineNum = 1414;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 1415;BA.debugLine="MatDialogBuilder.Title(sTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_stitle));
 //BA.debugLineNum = 1416;BA.debugLine="MatDialogBuilder.Content(sMsg)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_smsg));
 //BA.debugLineNum = 1417;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 1418;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1419;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1420;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 1421;BA.debugLine="End Sub";
return "";
}
public static String  _dispinformationmsg_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 1423;BA.debugLine="Private Sub DispInformationMsg_ButtonPressed(mDial";
 //BA.debugLineNum = 1424;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE)) {
case 0: {
 //BA.debugLineNum = 1426;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 1429;BA.debugLine="End Sub";
return "";
}
public static boolean  _edittranheader(int _itranheaderid) throws Exception{
 //BA.debugLineNum = 475;BA.debugLine="Private Sub EditTranHeader(iTranHeaderID As Int) A";
 //BA.debugLineNum = 476;BA.debugLine="End Sub";
return false;
}
public static String  _fontsizebinder_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 1391;BA.debugLine="Private Sub FontSizeBinder_OnBindView (View As Vie";
 //BA.debugLineNum = 1392;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1393;BA.debugLine="Alert.Initialize";
mostCurrent._alert.Initialize();
 //BA.debugLineNum = 1394;BA.debugLine="If ViewType = Alert.VIEW_TITLE Then ' Title";
if (_viewtype==mostCurrent._alert.VIEW_TITLE) { 
 //BA.debugLineNum = 1395;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 1396;BA.debugLine="lbl.TextSize = 30";
_lbl.setTextSize((float) (30));
 //BA.debugLineNum = 1397;BA.debugLine="lbl.SetTextColorAnimated(2000,Colors.Magenta)";
_lbl.SetTextColorAnimated((int) (2000),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 1400;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 1404;BA.debugLine="CS.Initialize.Typeface(Typeface.MATERIALICONS).S";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe88e)))+"  "));
 //BA.debugLineNum = 1405;BA.debugLine="CS.Typeface(Font).Size(24).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(_font.getObject())).Size((int) (24)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 1407;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 1409;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 310;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 312;BA.debugLine="Dim dPresentRdg As Int";
_dpresentrdg = 0;
 //BA.debugLineNum = 313;BA.debugLine="Dim sRdgTime, sRemarks As String";
parent.mostCurrent._srdgtime = "";
_sremarks = "";
 //BA.debugLineNum = 315;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
parent.mostCurrent._globalvar._tranheaderid /*int*/  = parent.mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,parent.mostCurrent._globalvar._pumphouseid /*int*/ ,parent.mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 316;BA.debugLine="Try";
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
 //BA.debugLineNum = 317;BA.debugLine="Starter.strCriteria = \"SELECT FMDetails.RdgTime,";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT FMDetails.RdgTime, FMDetails.PrevRdg, FMDetails.PresRdg, "+"FMDetails.PresCum, FMDetails.Remarks "+"FROM ProductionDetails AS FMDetails "+"WHERE FMDetails.DetailID = "+BA.NumberToString(_idetailedid);
 //BA.debugLineNum = 322;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 323;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 13;
return;
case 13:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 325;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 326;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 327;BA.debugLine="sRdgTime = RS.GetString(\"RdgTime\")";
parent.mostCurrent._srdgtime = _rs.GetString("RdgTime");
 //BA.debugLineNum = 328;BA.debugLine="dPresentRdg= RS.GetInt(\"PresRdg\")";
_dpresentrdg = _rs.GetInt("PresRdg");
 //BA.debugLineNum = 329;BA.debugLine="sRemarks = RS.GetString(\"Remarks\")";
_sremarks = _rs.GetString("Remarks");
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 331;BA.debugLine="snack.Initialize(\"\", Activity,$\"Unable to Fetch";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("Unable to Fetch Flow Meter Reading Details due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 332;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 333;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 334;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 335;BA.debugLine="Return";
if (true) return ;
 //BA.debugLineNum = 336;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("060751899",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
 //BA.debugLineNum = 340;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("060751903",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 12:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 343;BA.debugLine="chkDefaultTimeRead.Enabled = False";
parent.mostCurrent._chkdefaulttimeread.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 344;BA.debugLine="mskTimeRead.Text = sRdgTime";
parent.mostCurrent._msktimeread.setText((Object)(parent.mostCurrent._srdgtime));
 //BA.debugLineNum = 345;BA.debugLine="txtFMRdg.Text = dPresentRdg";
parent.mostCurrent._txtfmrdg.setText(BA.ObjectToCharSequence(_dpresentrdg));
 //BA.debugLineNum = 346;BA.debugLine="txtFMRdgRemarks.Text = sRemarks";
parent.mostCurrent._txtfmrdgremarks.setText(BA.ObjectToCharSequence(_sremarks));
 //BA.debugLineNum = 348;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 901;BA.debugLine="Private Sub GetLatestRdgID(iHeaderID As Int, sPres";
 //BA.debugLineNum = 902;BA.debugLine="Dim iRetVal As Int";
_iretval = 0;
 //BA.debugLineNum = 904;BA.debugLine="Try";
try { //BA.debugLineNum = 905;BA.debugLine="Starter.strCriteria = \"SELECT MAX(ProductionDeta";
mostCurrent._starter._strcriteria /*String*/  = "SELECT MAX(ProductionDetails.DetailID) FROM ProductionDetails "+"INNER JOIN TranHeader ON ProductionDetails.HeaderID = TranHeader.HeaderID "+"WHERE ProductionDetails.HeaderID = "+BA.NumberToString(_iheaderid)+" "+"AND PresRdg = '"+_spresrdg+"' "+"AND TranHeader.PumpID = "+BA.NumberToString(mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 911;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("061734922",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 913;BA.debugLine="iRetVal = Starter.DBCon.ExecQuerySingleResult(St";
_iretval = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 915;BA.debugLine="ToastMessageShow($\"Unable to fetch Pump Last Rea";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Pump Last Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 916;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("061734927",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 917;BA.debugLine="iRetVal = 0";
_iretval = (int) (0);
 };
 //BA.debugLineNum = 919;BA.debugLine="Return iRetVal";
if (true) return _iretval;
 //BA.debugLineNum = 920;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 297;BA.debugLine="Private Sub HidePanels";
 //BA.debugLineNum = 298;BA.debugLine="pnlZeroProdMsg.Visible = False";
mostCurrent._pnlzeroprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 299;BA.debugLine="pnlNegativeProdMsg.Visible = False";
mostCurrent._pnlnegativeprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 300;BA.debugLine="pnlLowProdMsg.Visible = False";
mostCurrent._pnllowprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 301;BA.debugLine="pnlHighProdMsg.Visible = False";
mostCurrent._pnlhighprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 302;BA.debugLine="pnlHighBillConfirmation.Visible = False";
mostCurrent._pnlhighbillconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 303;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 744;BA.debugLine="Private Sub InsertNegativeRdg(iTranHeaderID As Int";
 //BA.debugLineNum = 745;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 746;BA.debugLine="Dim iNewReading, iLastReading, iLastCuM As Int";
_inewreading = 0;
_ilastreading = 0;
_ilastcum = 0;
 //BA.debugLineNum = 747;BA.debugLine="Dim sRemarks, sLocation As String";
_sremarks = "";
_slocation = "";
 //BA.debugLineNum = 748;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 749;BA.debugLine="Dim sDateAdded As String";
_sdateadded = "";
 //BA.debugLineNum = 750;BA.debugLine="Dim dTotCum As Double";
_dtotcum = 0;
 //BA.debugLineNum = 751;BA.debugLine="Dim rsLast As Cursor";
_rslast = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 753;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 756;BA.debugLine="Dim iPrevDetailedID As Int";
_iprevdetailedid = 0;
 //BA.debugLineNum = 758;BA.debugLine="Starter.strCriteria = \"SELECT MAX(DetailID) FROM";
mostCurrent._starter._strcriteria /*String*/  = "SELECT MAX(DetailID) FROM ProductionDetails "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 761;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("061538321",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 763;BA.debugLine="iPrevDetailedID = Starter.DBCon.ExecQuerySingleRe";
_iprevdetailedid = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 765;BA.debugLine="If iPrevDetailedID = 0 Then 'Request to Net";
if (_iprevdetailedid==0) { 
 //BA.debugLineNum = 767;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 770;BA.debugLine="Starter.strCriteria = \"SELECT * FROM ProductionD";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM ProductionDetails WHERE DetailID = "+BA.NumberToString(_iprevdetailedid);
 //BA.debugLineNum = 771;BA.debugLine="LogColor(Starter.strCriteria, Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("061538331",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 773;BA.debugLine="rsLast = Starter.DBCon.ExecQuery(Starter.strCrit";
_rslast = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 774;BA.debugLine="If rsLast.RowCount > 0 Then";
if (_rslast.getRowCount()>0) { 
 //BA.debugLineNum = 775;BA.debugLine="rsLast.Position = 0";
_rslast.setPosition((int) (0));
 //BA.debugLineNum = 776;BA.debugLine="iLastReading = rsLast.GetInt(\"PresRdg\")";
_ilastreading = _rslast.GetInt("PresRdg");
 //BA.debugLineNum = 777;BA.debugLine="iLastCuM = rsLast.GetInt(\"PresCum\")";
_ilastcum = _rslast.GetInt("PresCum");
 }else {
 //BA.debugLineNum = 779;BA.debugLine="iLastReading = 0";
_ilastreading = (int) (0);
 //BA.debugLineNum = 780;BA.debugLine="iLastCuM = 0";
_ilastcum = (int) (0);
 };
 };
 //BA.debugLineNum = 784;BA.debugLine="iLastReading = iLastReading - GlobalVar.SF.Val(tx";
_ilastreading = (int) (_ilastreading-mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtbackflowcum.getText()));
 //BA.debugLineNum = 785;BA.debugLine="iLastCuM = iLastCuM - GlobalVar.SF.Val(txtBackFlo";
_ilastcum = (int) (_ilastcum-mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtbackflowcum.getText()));
 //BA.debugLineNum = 786;BA.debugLine="dBackFlow = GlobalVar.SF.Val(txtBackFlowCum.Text)";
_dbackflow = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtbackflowcum.getText());
 //BA.debugLineNum = 791;BA.debugLine="dTotCum = 0";
_dtotcum = 0;
 //BA.debugLineNum = 793;BA.debugLine="iNewReading = GlobalVar.SF.Val(txtFMRdg.Text)";
_inewreading = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText()));
 //BA.debugLineNum = 794;BA.debugLine="dTotCum = iNewReading - iLastReading";
_dtotcum = _inewreading-_ilastreading;
 //BA.debugLineNum = 796;BA.debugLine="sRemarks = txtFMRdgRemarks.Text";
_sremarks = mostCurrent._txtfmrdgremarks.getText();
 //BA.debugLineNum = 797;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 799;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("061538359",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 801;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("061538361",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 802;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("061538362",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 804;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 805;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("061538365",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 807;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 808;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 809;BA.debugLine="sDateAdded = DateTime.Date(lDate)";
_sdateadded = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 811;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 812;BA.debugLine="Try";
try { //BA.debugLineNum = 813;BA.debugLine="Starter.strCriteria = \"UPDATE ProductionDetails";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE ProductionDetails SET "+"PresRdg = ?, "+"PresCum = ?, "+"BackFlowCum = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ?, "+"ModifiedOn = ? "+"WHERE DetailID = "+BA.NumberToString(_iprevdetailedid);
 //BA.debugLineNum = 822;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_ilastreading),BA.NumberToString(_ilastcum),BA.NumberToString(_dbackflow),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_sdateadded,_slocation}));
 //BA.debugLineNum = 824;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO Product";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO ProductionDetails VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._tranheaderid /*int*/ ),(Object)(mostCurrent._srdgtime),(Object)(_ilastreading),(Object)(mostCurrent._txtfmrdg.getText()),(Object)(_dtotcum),(Object)(("0")),(Object)(_sremarks),(Object)(("0")),(Object)(("")),(Object)(("")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_sdateadded),(Object)(_slocation),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)((""))}));
 //BA.debugLineNum = 826;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 827;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e52) {
			processBA.setLastException(e52); //BA.debugLineNum = 829;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("061538389",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 830;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 832;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 833;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 834;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 613;BA.debugLine="Private Sub InsertNewFlowMeterReading() As Boolean";
 //BA.debugLineNum = 614;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 615;BA.debugLine="Dim sRemarks, sLocation As String";
_sremarks = "";
_slocation = "";
 //BA.debugLineNum = 616;BA.debugLine="Dim sDateAdded As String";
_sdateadded = "";
 //BA.debugLineNum = 617;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 618;BA.debugLine="Dim iBackFlowCum As Int";
_ibackflowcum = 0;
 //BA.debugLineNum = 619;BA.debugLine="Dim dTotCum As Double";
_dtotcum = 0;
 //BA.debugLineNum = 621;BA.debugLine="iBackFlowCum = 0";
_ibackflowcum = (int) (0);
 //BA.debugLineNum = 622;BA.debugLine="dTotCum = 0";
_dtotcum = 0;
 //BA.debugLineNum = 623;BA.debugLine="dTotCum = GlobalVar.SF.Val(txtFMRdg.Text) - dLast";
_dtotcum = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())-_dlastfmrdg;
 //BA.debugLineNum = 625;BA.debugLine="sRemarks = txtFMRdgRemarks.Text";
_sremarks = mostCurrent._txtfmrdgremarks.getText();
 //BA.debugLineNum = 626;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 628;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("061407247",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 630;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("061407249",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 631;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("061407250",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 633;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 634;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("061407253",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 636;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 637;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 638;BA.debugLine="sDateAdded = DateTime.Date(lDate)";
_sdateadded = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 641;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 642;BA.debugLine="Try";
try { //BA.debugLineNum = 643;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO Product";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO ProductionDetails VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._tranheaderid /*int*/ ),(Object)(mostCurrent._srdgtime),(Object)(_dlastfmrdg),(Object)(mostCurrent._txtfmrdg.getText()),(Object)(_dtotcum),(Object)(_ibackflowcum),(Object)(_sremarks),(Object)(("0")),(Object)(("")),(Object)(("")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_sdateadded),(Object)(_slocation),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)((""))}));
 //BA.debugLineNum = 645;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 646;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e26) {
			processBA.setLastException(e26); //BA.debugLineNum = 648;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("061407267",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 649;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 651;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 652;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 653;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 655;BA.debugLine="Private Sub InsertNewNegativeRdg() As Boolean";
 //BA.debugLineNum = 656;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 657;BA.debugLine="Dim iNewReading, iLastReading, iLastCuM As Int";
_inewreading = 0;
_ilastreading = 0;
_ilastcum = 0;
 //BA.debugLineNum = 658;BA.debugLine="Dim sRemarks, sLocation As String";
_sremarks = "";
_slocation = "";
 //BA.debugLineNum = 659;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 660;BA.debugLine="Dim sDateAdded As String";
_sdateadded = "";
 //BA.debugLineNum = 661;BA.debugLine="Dim dTotCum As Double";
_dtotcum = 0;
 //BA.debugLineNum = 663;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 666;BA.debugLine="Dim iPrevDetailedID As Int";
_iprevdetailedid = 0;
 //BA.debugLineNum = 668;BA.debugLine="iPrevDetailedID = DBaseFunctions.GetLastFMReading";
_iprevdetailedid = (int) (mostCurrent._dbasefunctions._getlastfmreadingtransid /*double*/ (mostCurrent.activityBA,(int) (_dlastfmrdg),(int) (_dbackflow)));
 //BA.debugLineNum = 670;BA.debugLine="If iPrevDetailedID = 0 Then 'Request to Net";
if (_iprevdetailedid==0) { 
 //BA.debugLineNum = 672;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 674;BA.debugLine="Starter.strCriteria = \"SELECT PresRdg FROM Produ";
mostCurrent._starter._strcriteria /*String*/  = "SELECT PresRdg FROM ProductionDetails WHERE DetailID = "+BA.NumberToString(_iprevdetailedid);
 //BA.debugLineNum = 675;BA.debugLine="LogColor(Starter.strCriteria, Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("061472788",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 677;BA.debugLine="iLastReading = Starter.DBCon.ExecQuerySingleResu";
_ilastreading = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 679;BA.debugLine="If iLastReading = 0 Then";
if (_ilastreading==0) { 
 //BA.debugLineNum = 680;BA.debugLine="iLastReading = iLastReading";
_ilastreading = _ilastreading;
 }else {
 //BA.debugLineNum = 682;BA.debugLine="iLastReading = iLastReading - GlobalVar.SF.Val(";
_ilastreading = (int) (_ilastreading-mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtbackflowcum.getText()));
 };
 //BA.debugLineNum = 685;BA.debugLine="Starter.strCriteria = \"SELECT PresCum FROM Produ";
mostCurrent._starter._strcriteria /*String*/  = "SELECT PresCum FROM ProductionDetails WHERE DetailID = "+BA.NumberToString(_iprevdetailedid);
 //BA.debugLineNum = 686;BA.debugLine="LogColor(Starter.strCriteria, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("061472799",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 688;BA.debugLine="iLastCuM = Starter.DBCon.ExecQuerySingleResult(S";
_ilastcum = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 690;BA.debugLine="If iLastCuM = 0 Then";
if (_ilastcum==0) { 
 //BA.debugLineNum = 691;BA.debugLine="iLastCuM = iLastCuM";
_ilastcum = _ilastcum;
 }else {
 //BA.debugLineNum = 693;BA.debugLine="iLastCuM = iLastCuM - dBackFlow";
_ilastcum = (int) (_ilastcum-_dbackflow);
 };
 };
 //BA.debugLineNum = 699;BA.debugLine="dTotCum = 0";
_dtotcum = 0;
 //BA.debugLineNum = 701;BA.debugLine="iNewReading = GlobalVar.SF.Val(txtFMRdg.Text)";
_inewreading = (int) (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText()));
 //BA.debugLineNum = 702;BA.debugLine="dTotCum = iNewReading - iLastReading";
_dtotcum = _inewreading-_ilastreading;
 //BA.debugLineNum = 704;BA.debugLine="sRemarks = txtFMRdgRemarks.Text";
_sremarks = mostCurrent._txtfmrdgremarks.getText();
 //BA.debugLineNum = 705;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 707;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("061472820",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 709;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("061472822",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 710;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("061472823",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 712;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 713;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("061472826",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 715;BA.debugLine="lDate = DateTime.Now";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 716;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 717;BA.debugLine="sDateAdded = DateTime.Date(lDate)";
_sdateadded = anywheresoftware.b4a.keywords.Common.DateTime.Date(_ldate);
 //BA.debugLineNum = 719;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 720;BA.debugLine="Try";
try { //BA.debugLineNum = 721;BA.debugLine="Starter.strCriteria = \"UPDATE ProductionDetails";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE ProductionDetails SET "+"PresRdg = ?, "+"PresCum = ?, "+"BackFlowCum = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ?, "+"ModifiedOn = ? "+"WHERE DetailID = "+BA.NumberToString(_iprevdetailedid);
 //BA.debugLineNum = 730;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_ilastreading),BA.NumberToString(_ilastcum),BA.NumberToString(_dbackflow),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_sdateadded,_slocation}));
 //BA.debugLineNum = 732;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO Product";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO ProductionDetails VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._tranheaderid /*int*/ ),(Object)(mostCurrent._srdgtime),(Object)(_dlastfmrdg),(Object)(mostCurrent._txtfmrdg.getText()),(Object)(_dtotcum),(Object)(_dbackflow),(Object)(_sremarks),(Object)(("0")),(Object)(("")),(Object)(("")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_sdateadded),(Object)(_slocation),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)((""))}));
 //BA.debugLineNum = 734;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 735;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e51) {
			processBA.setLastException(e51); //BA.debugLineNum = 737;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("061472850",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 738;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 740;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 741;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 742;BA.debugLine="End Sub";
return false;
}
public static boolean  _isvalidentries() throws Exception{
boolean _bretval = false;
 //BA.debugLineNum = 352;BA.debugLine="Private Sub IsValidEntries() As Boolean";
 //BA.debugLineNum = 353;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 354;BA.debugLine="LogColor(mskTimeRead.Text, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("060817410",mostCurrent._msktimeread.getText(),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 356;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 357;BA.debugLine="Try";
try { //BA.debugLineNum = 358;BA.debugLine="If chkDefaultTimeRead.Checked = True Then";
if (mostCurrent._chkdefaulttimeread.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 359;BA.debugLine="If Validation.IsTime(mskTimeRead.Text) = False";
if (mostCurrent._validation._istime /*boolean*/ (mostCurrent.activityBA,mostCurrent._msktimeread.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 360;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid Reading Ti";
_requiredmsgbox(("ERROR"),("Invalid Reading Time!"));
 //BA.debugLineNum = 361;BA.debugLine="mskTimeRead.RequestFocus";
mostCurrent._msktimeread.RequestFocus();
 //BA.debugLineNum = 362;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 }else {
 //BA.debugLineNum = 365;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(mskTimeRe";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._msktimeread.getText()))<=0 || (mostCurrent._msktimeread.getText()).equals("__:__")) { 
 //BA.debugLineNum = 366;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Reading Time canno";
_requiredmsgbox(("ERROR"),("Reading Time cannot be blank!"));
 //BA.debugLineNum = 367;BA.debugLine="mskTimeRead.RequestFocus";
mostCurrent._msktimeread.RequestFocus();
 //BA.debugLineNum = 368;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 }else if(mostCurrent._validation._istime /*boolean*/ (mostCurrent.activityBA,mostCurrent._msktimeread.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 371;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid Reading Ti";
_requiredmsgbox(("ERROR"),("Invalid Reading Time!"));
 //BA.debugLineNum = 372;BA.debugLine="mskTimeRead.RequestFocus";
mostCurrent._msktimeread.RequestFocus();
 //BA.debugLineNum = 373;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 };
 } 
       catch (Exception e23) {
			processBA.setLastException(e23); //BA.debugLineNum = 378;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("060817434",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 379;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 381;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 382;BA.debugLine="End Sub";
return false;
}
public static String  _msktimeread_enterpressed() throws Exception{
 //BA.debugLineNum = 517;BA.debugLine="Sub mskTimeRead_EnterPressed";
 //BA.debugLineNum = 519;BA.debugLine="End Sub";
return "";
}
public static String  _msktimeread_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 1465;BA.debugLine="Sub mskTimeRead_FocusChanged(HasFocus As Boolean)";
 //BA.debugLineNum = 1466;BA.debugLine="If HasFocus = True Then mskTimeRead.SelectAll";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._msktimeread.SelectAll();};
 //BA.debugLineNum = 1467;BA.debugLine="End Sub";
return "";
}
public static String  _normalreading_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 1376;BA.debugLine="Private Sub NormalReading_OnNegativeClicked (View";
 //BA.debugLineNum = 1378;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1379;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 1380;BA.debugLine="End Sub";
return "";
}
public static String  _normalreading_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 1382;BA.debugLine="Private Sub NormalReading_OnPositiveClicked (View";
 //BA.debugLineNum = 1385;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1386;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 1387;BA.debugLine="SaveUpdateFMReading";
_saveupdatefmreading();
 //BA.debugLineNum = 1388;BA.debugLine="End Sub";
return "";
}
public static String  _pnlhighbillconfirmation_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1197;BA.debugLine="Sub pnlHighBillConfirmation_Touch (Action As Int,";
 //BA.debugLineNum = 1199;BA.debugLine="End Sub";
return "";
}
public static String  _pnlhighprodmsg_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1153;BA.debugLine="Sub pnlHighProdMsg_Touch (Action As Int, X As Floa";
 //BA.debugLineNum = 1155;BA.debugLine="End Sub";
return "";
}
public static String  _pnllowprodmsg_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1116;BA.debugLine="Sub pnlLowProdMsg_Touch (Action As Int, X As Float";
 //BA.debugLineNum = 1118;BA.debugLine="End Sub";
return "";
}
public static String  _pnlnegativeprodmsg_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1057;BA.debugLine="Sub pnlNegativeProdMsg_Touch (Action As Int, X As";
 //BA.debugLineNum = 1059;BA.debugLine="End Sub";
return "";
}
public static String  _pnlzeroprodmsg_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1010;BA.debugLine="Sub pnlZeroProdMsg_Touch (Action As Int, X As Floa";
 //BA.debugLineNum = 1012;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 1269;BA.debugLine="Private Sub RequiredMsg_OnPositiveClicked (View As";
 //BA.debugLineNum = 1270;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1271;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 1272;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsgbox(String _stitle,String _smsg) throws Exception{
 //BA.debugLineNum = 1244;BA.debugLine="Private Sub RequiredMsgBox(sTitle As String, sMsg";
 //BA.debugLineNum = 1245;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1246;BA.debugLine="Alert.Initialize.Dismiss2";
mostCurrent._alert.Initialize().Dismiss2();
 //BA.debugLineNum = 1248;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._redcolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessage(_smsg).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(_fontbold.getObject())).SetMessageTypeface((android.graphics.Typeface)(_font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"RequiredMsg").SetOnViewBinder(mostCurrent.activityBA,"FontSizeBinder");
 //BA.debugLineNum = 1263;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 1264;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 1265;BA.debugLine="End Sub";
return "";
}
public static String  _savenegative_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 1299;BA.debugLine="Private Sub SaveNegative_OnNegativeClicked (View A";
 //BA.debugLineNum = 1301;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1302;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 1303;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1304;BA.debugLine="End Sub";
return "";
}
public static String  _savenegative_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 1306;BA.debugLine="Private Sub SaveNegative_OnPositiveClicked (View A";
 //BA.debugLineNum = 1309;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 1310;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 1312;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 1313;BA.debugLine="soundsAlarmChannel.Stop(SoundID)";
_soundsalarmchannel.Stop(_soundid);
 //BA.debugLineNum = 1314;BA.debugLine="pnlNegativeProdMsg.Visible = False";
mostCurrent._pnlnegativeprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1316;BA.debugLine="dBackFlow = GlobalVar.SF.Val(txtBackFlowCum.Text)";
_dbackflow = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtbackflowcum.getText());
 //BA.debugLineNum = 1317;BA.debugLine="dPreviousRdg = dLastFMRdg - dBackFlow";
_dpreviousrdg = _dlastfmrdg-_dbackflow;
 //BA.debugLineNum = 1319;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 1321;BA.debugLine="If GlobalVar.TranHeaderID = 0 Then 'New FM Readin";
if (mostCurrent._globalvar._tranheaderid /*int*/ ==0) { 
 //BA.debugLineNum = 1322;BA.debugLine="If Not(SaveTransHeader) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_savetransheader())) { 
 //BA.debugLineNum = 1323;BA.debugLine="ToastMessageShow($\"Unable to save negative read";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to save negative reading header due to ")+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA))),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1324;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1327;BA.debugLine="If Not(InsertNewNegativeRdg) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_insertnewnegativerdg())) { 
 //BA.debugLineNum = 1328;BA.debugLine="ToastMessageShow($\"Unable to save negative read";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to save negative reading details due to ")+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA))),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1329;BA.debugLine="dBackFlow = 0";
_dbackflow = 0;
 //BA.debugLineNum = 1330;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 1335;BA.debugLine="If Not(UpdateTranHeader(GlobalVar.TranHeaderID))";
if (anywheresoftware.b4a.keywords.Common.Not(_updatetranheader(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
 //BA.debugLineNum = 1336;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1340;BA.debugLine="If Not(InsertNegativeRdg(GlobalVar.TranHeaderID)";
if (anywheresoftware.b4a.keywords.Common.Not(_insertnegativerdg(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
 //BA.debugLineNum = 1341;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 1345;BA.debugLine="UpdateLastFMReadings(GlobalVar.PumpHouseID)";
_updatelastfmreadings(mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 1346;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 //BA.debugLineNum = 1348;BA.debugLine="End Sub";
return "";
}
public static String  _savetojson(int _irdgid) throws Exception{
String _sjson = "";
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _jsongen = null;
anywheresoftware.b4a.objects.collections.List _jsonlist = null;
anywheresoftware.b4a.objects.collections.Map _jsonmap = null;
anywheresoftware.b4a.sql.SQL.CursorWrapper _rs = null;
 //BA.debugLineNum = 922;BA.debugLine="Private Sub SavetoJSON(iRdgID As Int) As String";
 //BA.debugLineNum = 923;BA.debugLine="Dim sJSON As String";
_sjson = "";
 //BA.debugLineNum = 924;BA.debugLine="Dim JSONGen As JSONGenerator";
_jsongen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 925;BA.debugLine="Dim JSONList As List";
_jsonlist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 926;BA.debugLine="Dim JSONMap As Map";
_jsonmap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 927;BA.debugLine="Dim RS As Cursor";
_rs = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 930;BA.debugLine="Try";
try { //BA.debugLineNum = 932;BA.debugLine="Starter.strCriteria = \"SELECT * FROM ProductionD";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM ProductionDetails "+"WHERE DetailID = "+BA.NumberToString(_irdgid);
 //BA.debugLineNum = 934;BA.debugLine="LogColor(Starter.strCriteria, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("061800460",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 936;BA.debugLine="RS = Starter.DBCon.ExecQuery(Starter.strCriteria";
_rs = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 938;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 939;BA.debugLine="JSONList.Initialize";
_jsonlist.Initialize();
 //BA.debugLineNum = 940;BA.debugLine="JSONMap.Initialize";
_jsonmap.Initialize();
 //BA.debugLineNum = 941;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 943;BA.debugLine="JSONMap.Put(\"pump_id\", GlobalVar.PumpHouseID)";
_jsonmap.Put((Object)("pump_id"),(Object)(mostCurrent._globalvar._pumphouseid /*int*/ ));
 //BA.debugLineNum = 944;BA.debugLine="JSONMap.Put(\"transaction_date\", GlobalVar.TranD";
_jsonmap.Put((Object)("transaction_date"),(Object)(mostCurrent._globalvar._trandate /*String*/ ));
 //BA.debugLineNum = 945;BA.debugLine="JSONMap.Put(\"transaction_time\", mskTimeRead.Tex";
_jsonmap.Put((Object)("transaction_time"),(Object)(mostCurrent._msktimeread.getText()));
 //BA.debugLineNum = 946;BA.debugLine="JSONMap.Put(\"reading_previous\", RS.GetInt(\"Prev";
_jsonmap.Put((Object)("reading_previous"),(Object)(_rs.GetInt("PrevRdg")));
 //BA.debugLineNum = 947;BA.debugLine="JSONMap.Put(\"reading_present\", RS.GetInt(\"PresR";
_jsonmap.Put((Object)("reading_present"),(Object)(_rs.GetInt("PresRdg")));
 //BA.debugLineNum = 948;BA.debugLine="JSONMap.Put(\"production\", RS.GetInt(\"PresCum\"))";
_jsonmap.Put((Object)("production"),(Object)(_rs.GetInt("PresCum")));
 //BA.debugLineNum = 949;BA.debugLine="JSONMap.Put(\"remarks\", RS.GetString(\"Remarks\"))";
_jsonmap.Put((Object)("remarks"),(Object)(_rs.GetString("Remarks")));
 //BA.debugLineNum = 950;BA.debugLine="JSONMap.Put(\"pump_operator\", RS.GetInt(\"AddedBy";
_jsonmap.Put((Object)("pump_operator"),(Object)(_rs.GetInt("AddedBy")));
 //BA.debugLineNum = 951;BA.debugLine="JSONMap.Put(\"coordinates\", RS.GetString(\"AddedO";
_jsonmap.Put((Object)("coordinates"),(Object)(_rs.GetString("AddedOn")));
 //BA.debugLineNum = 952;BA.debugLine="JSONGen.Initialize(JSONMap)";
_jsongen.Initialize(_jsonmap);
 //BA.debugLineNum = 954;BA.debugLine="Log (JSONGen.ToString)";
anywheresoftware.b4a.keywords.Common.LogImpl("061800480",_jsongen.ToString(),0);
 //BA.debugLineNum = 955;BA.debugLine="sJSON = JSONGen.ToString";
_sjson = _jsongen.ToString();
 //BA.debugLineNum = 956;BA.debugLine="Log (sJSON)";
anywheresoftware.b4a.keywords.Common.LogImpl("061800482",_sjson,0);
 }else {
 //BA.debugLineNum = 959;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 960;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 961;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 962;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 963;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("061800489",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 } 
       catch (Exception e35) {
			processBA.setLastException(e35); //BA.debugLineNum = 969;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("061800495",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 971;BA.debugLine="Return sJSON";
if (true) return _sjson;
 //BA.debugLineNum = 972;BA.debugLine="End Sub";
return "";
}
public static boolean  _savetransheader() throws Exception{
boolean _bretval = false;
long _lngdatetime = 0L;
String _saddedat = "";
double _totprod = 0;
 //BA.debugLineNum = 384;BA.debugLine="Private Sub SaveTransHeader() As Boolean";
 //BA.debugLineNum = 385;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 386;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 387;BA.debugLine="Dim sAddedAt As String";
_saddedat = "";
 //BA.debugLineNum = 388;BA.debugLine="Dim TotProd As Double";
_totprod = 0;
 //BA.debugLineNum = 390;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 391;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 392;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 393;BA.debugLine="sAddedAt= DateTime.Date(lngDateTime)";
_saddedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 395;BA.debugLine="If isNegativeRdg = True Then";
if (_isnegativerdg==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 396;BA.debugLine="TotProd = 0";
_totprod = 0;
 }else {
 //BA.debugLineNum = 398;BA.debugLine="TotProd = GlobalVar.SF.Val(txtFMRdg.Text) -  dLa";
_totprod = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())-_dlastfmrdg;
 };
 //BA.debugLineNum = 401;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 402;BA.debugLine="Try";
try { //BA.debugLineNum = 404;BA.debugLine="Starter.DBCon.ExecNonQuery2(\"INSERT INTO TranHea";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2("INSERT INTO TranHeader VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._branchid /*int*/ ),(Object)(mostCurrent._globalvar._pumphouseid /*int*/ ),(Object)(mostCurrent._globalvar._trandate /*String*/ ),(Object)(("0")),(Object)(_totprod),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(("0")),(Object)(mostCurrent._globalvar._userid /*int*/ ),(Object)(_saddedat),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(("0")),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null}));
 //BA.debugLineNum = 407;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 408;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e20) {
			processBA.setLastException(e20); //BA.debugLineNum = 410;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("060882970",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 411;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 413;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 414;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 415;BA.debugLine="End Sub";
return false;
}
public static String  _saveupdatefmreading() throws Exception{
 //BA.debugLineNum = 852;BA.debugLine="Private Sub SaveUpdateFMReading";
 //BA.debugLineNum = 853;BA.debugLine="Select Case GlobalVar.blnNewFMRdg";
switch (BA.switchObjectToInt(mostCurrent._globalvar._blnnewfmrdg /*boolean*/ ,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False)) {
case 0: {
 //BA.debugLineNum = 856;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHead";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 857;BA.debugLine="LogColor(GlobalVar.TranHeaderID, Colors.Magenta";
anywheresoftware.b4a.keywords.Common.LogImpl("061669381",BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 859;BA.debugLine="If GlobalVar.TranHeaderID = 0 Then";
if (mostCurrent._globalvar._tranheaderid /*int*/ ==0) { 
 //BA.debugLineNum = 860;BA.debugLine="If Not(SaveTransHeader) Then Return";
if (anywheresoftware.b4a.keywords.Common.Not(_savetransheader())) { 
if (true) return "";};
 //BA.debugLineNum = 861;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHea";
mostCurrent._globalvar._tranheaderid /*int*/  = mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphouseid /*int*/ ,mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 862;BA.debugLine="LogColor(GlobalVar.TranHeaderID, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("061669386",BA.NumberToString(mostCurrent._globalvar._tranheaderid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 863;BA.debugLine="If Not(InsertNewFlowMeterReading) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_insertnewflowmeterreading())) { 
 //BA.debugLineNum = 864;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 865;BA.debugLine="snack.Initialize(\"\", Activity, $\"Unable to Ad";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),("Unable to Add New Flow Meter Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),mostCurrent._snack.DURATION_LONG);
 //BA.debugLineNum = 866;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Glob";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 867;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Color";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 868;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 869;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 872;BA.debugLine="If Not(InsertNewFlowMeterReading) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_insertnewflowmeterreading())) { 
 //BA.debugLineNum = 873;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 874;BA.debugLine="snack.Initialize(\"\", Activity, $\"Unable to Ad";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),("Unable to Add New Flow Meter Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),mostCurrent._snack.DURATION_LONG);
 //BA.debugLineNum = 875;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Glob";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 876;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Color";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 877;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 878;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 880;BA.debugLine="If Not(UpdateTranHeader(GlobalVar.TranHeaderID";
if (anywheresoftware.b4a.keywords.Common.Not(_updatetranheader(mostCurrent._globalvar._tranheaderid /*int*/ ))) { 
 //BA.debugLineNum = 881;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 884;BA.debugLine="UpdateLastFMReadings(GlobalVar.PumpHouseID)";
_updatelastfmreadings(mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 885;BA.debugLine="GlobalVar.FMRdgDetailID = GetLatestRdgID(Global";
mostCurrent._globalvar._fmrdgdetailid /*int*/  = _getlatestrdgid(mostCurrent._globalvar._tranheaderid /*int*/ ,mostCurrent._txtfmrdg.getText());
 //BA.debugLineNum = 886;BA.debugLine="If GlobalVar.FMRdgDetailID = 0 Then";
if (mostCurrent._globalvar._fmrdgdetailid /*int*/ ==0) { 
 }else {
 //BA.debugLineNum = 888;BA.debugLine="sUploadReading = SavetoJSON(GlobalVar.FMRdgDet";
mostCurrent._suploadreading = _savetojson(mostCurrent._globalvar._fmrdgdetailid /*int*/ );
 };
 //BA.debugLineNum = 890;BA.debugLine="If GlobalVar.SF.Len(sUploadReading) = 0 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._suploadreading)==0) { 
 }else {
 //BA.debugLineNum = 892;BA.debugLine="UploadReadingData(sUploadReading)";
_uploadreadingdata(mostCurrent._suploadreading);
 };
 //BA.debugLineNum = 894;BA.debugLine="ShowSaveSuccess";
_showsavesuccess();
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 898;BA.debugLine="End Sub";
return "";
}
public static String  _showhighconfirmationwarning() throws Exception{
 //BA.debugLineNum = 1201;BA.debugLine="Private Sub ShowHighConfirmationWarning";
 //BA.debugLineNum = 1202;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1203;BA.debugLine="pnlHighBillConfirmation.Visible = True";
mostCurrent._pnlhighbillconfirmation.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1204;BA.debugLine="btnHBConfirmCancel.Background = cdCancel";
mostCurrent._btnhbconfirmcancel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdcancel.getObject()));
 //BA.debugLineNum = 1205;BA.debugLine="btnHBConfirmSave.Background = cdOK";
mostCurrent._btnhbconfirmsave.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 1207;BA.debugLine="vibration.vibratePattern(vibratePattern, 0)";
mostCurrent._vibration.vibratePattern(processBA,_vibratepattern,(int) (0));
 //BA.debugLineNum = 1208;BA.debugLine="soundsAlarmChannel.Play(SoundID,1,1,1,0,1)";
_soundsalarmchannel.Play(_soundid,(float) (1),(float) (1),(int) (1),(int) (0),(float) (1));
 //BA.debugLineNum = 1209;BA.debugLine="txtPresRdgConfirm.Background = cdFixedText";
mostCurrent._txtpresrdgconfirm.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdfixedtext.getObject()));
 //BA.debugLineNum = 1210;BA.debugLine="txtPresRdgConfirm.Text = \"\"";
mostCurrent._txtpresrdgconfirm.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1211;BA.debugLine="btnHBConfirmSave.Enabled = False";
mostCurrent._btnhbconfirmsave.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1212;BA.debugLine="End Sub";
return "";
}
public static String  _showhighwarning() throws Exception{
 //BA.debugLineNum = 1157;BA.debugLine="Private Sub ShowHighWarning";
 //BA.debugLineNum = 1158;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1159;BA.debugLine="pnlHighProdMsg.Visible = True";
mostCurrent._pnlhighprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1160;BA.debugLine="btnHighCancel.Background = cdCancel";
mostCurrent._btnhighcancel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdcancel.getObject()));
 //BA.debugLineNum = 1161;BA.debugLine="btnHighOk.Background = cdOK";
mostCurrent._btnhighok.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 1163;BA.debugLine="vibration.vibratePattern(vibratePattern, 0)";
mostCurrent._vibration.vibratePattern(processBA,_vibratepattern,(int) (0));
 //BA.debugLineNum = 1164;BA.debugLine="soundsAlarmChannel.Play(SoundID,1,1,1,0,1)";
_soundsalarmchannel.Play(_soundid,(float) (1),(float) (1),(int) (1),(int) (0),(float) (1));
 //BA.debugLineNum = 1166;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtFMRdgRem";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtfmrdgremarks.getText()))<=0) { 
 //BA.debugLineNum = 1167;BA.debugLine="txtHighRemarks.Text = \"\"";
mostCurrent._txthighremarks.setText(BA.ObjectToCharSequence(""));
 }else {
 //BA.debugLineNum = 1169;BA.debugLine="txtHighRemarks.Text = txtFMRdgRemarks.Text";
mostCurrent._txthighremarks.setText(BA.ObjectToCharSequence(mostCurrent._txtfmrdgremarks.getText()));
 };
 //BA.debugLineNum = 1171;BA.debugLine="End Sub";
return "";
}
public static String  _showlowwarning() throws Exception{
 //BA.debugLineNum = 1120;BA.debugLine="Private Sub ShowLowWarning";
 //BA.debugLineNum = 1121;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1122;BA.debugLine="pnlLowProdMsg.Visible = True";
mostCurrent._pnllowprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1123;BA.debugLine="btnLowCancel.Background = cdCancel";
mostCurrent._btnlowcancel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdcancel.getObject()));
 //BA.debugLineNum = 1124;BA.debugLine="btnLowOk.Background = cdOK";
mostCurrent._btnlowok.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 1126;BA.debugLine="vibration.vibratePattern(vibratePattern, 0)";
mostCurrent._vibration.vibratePattern(processBA,_vibratepattern,(int) (0));
 //BA.debugLineNum = 1127;BA.debugLine="soundsAlarmChannel.Play(SoundID,1,1,1,0,1)";
_soundsalarmchannel.Play(_soundid,(float) (1),(float) (1),(int) (1),(int) (0),(float) (1));
 //BA.debugLineNum = 1128;BA.debugLine="End Sub";
return "";
}
public static String  _shownegativewarning() throws Exception{
 //BA.debugLineNum = 1061;BA.debugLine="Private Sub ShowNegativeWarning";
 //BA.debugLineNum = 1062;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1064;BA.debugLine="pnlNegativeProdMsg.Visible = True";
mostCurrent._pnlnegativeprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1066;BA.debugLine="chkBackFlow.Checked = False";
mostCurrent._chkbackflow.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1067;BA.debugLine="txtBackFlowCum.Text = \"\"";
mostCurrent._txtbackflowcum.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1068;BA.debugLine="txtBackFlowCum.Hint = (GlobalVar.SF.Val(txtFMRdg.";
mostCurrent._txtbackflowcum.setHint(BA.NumberToString((mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())-_dlastfmrdg))+" CuM Back flow");
 //BA.debugLineNum = 1070;BA.debugLine="txtNegativeRemarks.Text = \"\"";
mostCurrent._txtnegativeremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1072;BA.debugLine="btnNegativeCancel.Background = cdCancel";
mostCurrent._btnnegativecancel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdcancel.getObject()));
 //BA.debugLineNum = 1073;BA.debugLine="btnNegativeOk.Background = cdOK";
mostCurrent._btnnegativeok.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 1075;BA.debugLine="vibration.vibratePattern(vibratePattern, 0)";
mostCurrent._vibration.vibratePattern(processBA,_vibratepattern,(int) (0));
 //BA.debugLineNum = 1076;BA.debugLine="soundsAlarmChannel.Play(SoundID,1,1,1,0,1)";
_soundsalarmchannel.Play(_soundid,(float) (1),(float) (1),(int) (1),(int) (0),(float) (1));
 //BA.debugLineNum = 1077;BA.debugLine="End Sub";
return "";
}
public static String  _showsavesuccess() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
anywheresoftware.b4a.objects.CSBuilder _cscontent = null;
 //BA.debugLineNum = 1431;BA.debugLine="Private Sub ShowSaveSuccess()";
 //BA.debugLineNum = 1432;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 1433;BA.debugLine="Dim csContent As CSBuilder";
_cscontent = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 1435;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1436;BA.debugLine="If GlobalVar.blnNewFMRdg = True Then";
if (mostCurrent._globalvar._blnnewfmrdg /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1437;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 1438;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("New Pump Flow Meter Reading has been successfully saved!"))).PopAll();
 }else {
 //BA.debugLineNum = 1440;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (mostCurrent._globalvar._poscolor /*double*/ )).Append(BA.ObjectToCharSequence(("S U C C E S S!"))).PopAll();
 //BA.debugLineNum = 1441;BA.debugLine="csContent.Initialize.Size(14).Color(Colors.Black";
_cscontent.Initialize().Size((int) (14)).Color(anywheresoftware.b4a.keywords.Common.Colors.Black).Append(BA.ObjectToCharSequence(("Pump Flow Meter Reading has been successfully updated!"))).PopAll();
 };
 //BA.debugLineNum = 1443;BA.debugLine="MatDialogBuilder.Initialize(\"AddPumpTimeOnRecords";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"AddPumpTimeOnRecords");
 //BA.debugLineNum = 1444;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 1445;BA.debugLine="MatDialogBuilder.Content(csContent)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_cscontent.getObject()));
 //BA.debugLineNum = 1446;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 1447;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1448;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1449;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 1450;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 1451;BA.debugLine="End Sub";
return "";
}
public static String  _showzerowarning() throws Exception{
 //BA.debugLineNum = 1014;BA.debugLine="Private Sub ShowZeroWarning";
 //BA.debugLineNum = 1015;BA.debugLine="HidePanels";
_hidepanels();
 //BA.debugLineNum = 1016;BA.debugLine="pnlZeroProdMsg.Visible = True";
mostCurrent._pnlzeroprodmsg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1017;BA.debugLine="btnZeroCancel.Background = cdCancel";
mostCurrent._btnzerocancel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdcancel.getObject()));
 //BA.debugLineNum = 1018;BA.debugLine="btnZeroOk.Background = cdOK";
mostCurrent._btnzerook.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 1020;BA.debugLine="vibration.vibratePattern(vibratePattern, 0)";
mostCurrent._vibration.vibratePattern(processBA,_vibratepattern,(int) (0));
 //BA.debugLineNum = 1021;BA.debugLine="soundsAlarmChannel.Play(SoundID,1,1,1,0,1)";
_soundsalarmchannel.Play(_soundid,(float) (1),(float) (1),(int) (1),(int) (0),(float) (1));
 //BA.debugLineNum = 1023;BA.debugLine="If GlobalVar.SF.Len(GlobalVar.SF.Trim(txtFMRdgRem";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtfmrdgremarks.getText()))<=0) { 
 //BA.debugLineNum = 1024;BA.debugLine="lblZeroMsg.Text = $\"It seems that your Pump Flow";
mostCurrent._lblzeromsg.setText(BA.ObjectToCharSequence(("It seems that your Pump Flow Meter didn't move from your Previous Reading")+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+("Reading Remarks is required this time.")));
 //BA.debugLineNum = 1025;BA.debugLine="txtZeroRemarks.Text = \"\"";
mostCurrent._txtzeroremarks.setText(BA.ObjectToCharSequence(""));
 }else {
 //BA.debugLineNum = 1027;BA.debugLine="lblZeroMsg.Text = $\"It seems that your Pump Flow";
mostCurrent._lblzeromsg.setText(BA.ObjectToCharSequence(("It seems that your Pump Flow Meter didn't move from your Previous Reading")+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+("Do you wish to save this record anyway?")));
 //BA.debugLineNum = 1028;BA.debugLine="txtZeroRemarks.Text = txtFMRdgRemarks.Text";
mostCurrent._txtzeroremarks.setText(BA.ObjectToCharSequence(mostCurrent._txtfmrdgremarks.getText()));
 };
 //BA.debugLineNum = 1030;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 266;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 267;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 251;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 252;BA.debugLine="If pnlZeroProdMsg.Visible = True Then";
if (mostCurrent._pnlzeroprodmsg.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 253;BA.debugLine="btnZeroCancel_Click";
_btnzerocancel_click();
 //BA.debugLineNum = 254;BA.debugLine="imeKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 }else if(mostCurrent._pnlnegativeprodmsg.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 256;BA.debugLine="btnNegativeOk_Click";
_btnnegativeok_click();
 //BA.debugLineNum = 257;BA.debugLine="imeKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 }else if(mostCurrent._ckeyboard.IsSoftKeyboardVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 259;BA.debugLine="cKeyboard.HideKeyboard";
mostCurrent._ckeyboard.HideKeyboard();
 }else {
 //BA.debugLineNum = 261;BA.debugLine="imeKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 262;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 264;BA.debugLine="End Sub";
return "";
}
public static String  _txtfmrdg_enterpressed() throws Exception{
 //BA.debugLineNum = 512;BA.debugLine="Sub txtFMRdg_EnterPressed";
 //BA.debugLineNum = 513;BA.debugLine="cKeyboard.HideKeyboard";
mostCurrent._ckeyboard.HideKeyboard();
 //BA.debugLineNum = 514;BA.debugLine="txtFMRdgRemarks.RequestFocus";
mostCurrent._txtfmrdgremarks.RequestFocus();
 //BA.debugLineNum = 515;BA.debugLine="End Sub";
return "";
}
public static String  _txtfmrdg_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 1470;BA.debugLine="Sub txtFMRdg_FocusChanged (HasFocus As Boolean)";
 //BA.debugLineNum = 1471;BA.debugLine="If HasFocus = True Then";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1474;BA.debugLine="cKeyboard.ShowKeyboard(txtFMRdg)";
mostCurrent._ckeyboard.ShowKeyboard((android.widget.EditText)(mostCurrent._txtfmrdg.getObject()));
 }else {
 //BA.debugLineNum = 1476;BA.debugLine="pnlKeyboard.Visible = False";
mostCurrent._pnlkeyboard.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1477;BA.debugLine="cKeyboard.HideKeyboard";
mostCurrent._ckeyboard.HideKeyboard();
 };
 //BA.debugLineNum = 1479;BA.debugLine="End Sub";
return "";
}
public static String  _txtpresrdgconfirm_enterpressed() throws Exception{
 //BA.debugLineNum = 1214;BA.debugLine="Sub txtPresRdgConfirm_EnterPressed";
 //BA.debugLineNum = 1216;BA.debugLine="End Sub";
return "";
}
public static String  _txtpresrdgconfirm_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 1218;BA.debugLine="Sub txtPresRdgConfirm_TextChanged (Old As String,";
 //BA.debugLineNum = 1219;BA.debugLine="If txtFMRdg.Text = New Or sHighRdg = New Then";
if ((mostCurrent._txtfmrdg.getText()).equals(_new) || (mostCurrent._shighrdg).equals(_new)) { 
 //BA.debugLineNum = 1220;BA.debugLine="btnHBConfirmSave.Enabled = True";
mostCurrent._btnhbconfirmsave.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 1222;BA.debugLine="btnHBConfirmSave.Enabled = False";
mostCurrent._btnhbconfirmsave.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 1224;BA.debugLine="End Sub";
return "";
}
public static String  _updatelastfmreadings(int _ipumpid) throws Exception{
 //BA.debugLineNum = 836;BA.debugLine="Private Sub UpdateLastFMReadings(iPumpID As Int)";
 //BA.debugLineNum = 838;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 839;BA.debugLine="Try";
try { //BA.debugLineNum = 840;BA.debugLine="Starter.strCriteria = \"UPDATE tblPumpStation \" &";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE tblPumpStation "+"SET LastRdg = ? "+"WHERE StationID = "+BA.NumberToString(_ipumpid);
 //BA.debugLineNum = 844;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{mostCurrent._txtfmrdg.getText()}));
 //BA.debugLineNum = 845;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 847;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("061603851",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 849;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 850;BA.debugLine="End Sub";
return "";
}
public static boolean  _updatetranheader(int _itranheaderid) throws Exception{
boolean _bretval = false;
double _gtotprod = 0;
long _lngdatetime = 0L;
String _smodifiedat = "";
double _dtotcum = 0;
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsheader = null;
 //BA.debugLineNum = 417;BA.debugLine="Private Sub UpdateTranHeader(iTranHeaderID As Int)";
 //BA.debugLineNum = 418;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 419;BA.debugLine="Dim GTotProd As Double";
_gtotprod = 0;
 //BA.debugLineNum = 421;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 422;BA.debugLine="Dim sModifiedAt As String";
_smodifiedat = "";
 //BA.debugLineNum = 423;BA.debugLine="Dim dTotCum As Double";
_dtotcum = 0;
 //BA.debugLineNum = 426;BA.debugLine="dTotCum = 0";
_dtotcum = 0;
 //BA.debugLineNum = 427;BA.debugLine="If isNegativeRdg = True Then";
if (_isnegativerdg==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 428;BA.debugLine="dTotCum = dLastFMRdg - GlobalVar.SF.Val(txtFMRdg";
_dtotcum = _dlastfmrdg-mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText());
 }else {
 //BA.debugLineNum = 430;BA.debugLine="dTotCum = GlobalVar.SF.Val(txtFMRdg.Text) - dLas";
_dtotcum = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(mostCurrent._txtfmrdg.getText())-_dlastfmrdg;
 };
 //BA.debugLineNum = 433;BA.debugLine="LogColor($\"Total CuM: \"$ & dTotCum, Colors.Yellow";
anywheresoftware.b4a.keywords.Common.LogImpl("060948496",("Total CuM: ")+BA.NumberToString(_dtotcum),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 435;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 436;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 437;BA.debugLine="sModifiedAt = DateTime.Date(lngDateTime)";
_smodifiedat = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 439;BA.debugLine="Dim rsHeader As Cursor";
_rsheader = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 441;BA.debugLine="Starter.strCriteria = \"SELECT * FROM TranHeader W";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM TranHeader WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 442;BA.debugLine="rsHeader = Starter.DBCon.ExecQuery(Starter.strCri";
_rsheader = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 444;BA.debugLine="If rsHeader.RowCount > 0 Then";
if (_rsheader.getRowCount()>0) { 
 //BA.debugLineNum = 445;BA.debugLine="rsHeader.Position = 0";
_rsheader.setPosition((int) (0));
 //BA.debugLineNum = 446;BA.debugLine="GTotProd = rsHeader.GetDouble(\"TotProduction\") +";
_gtotprod = _rsheader.GetDouble("TotProduction")+_dtotcum;
 }else {
 //BA.debugLineNum = 448;BA.debugLine="GTotProd =  dTotCum";
_gtotprod = _dtotcum;
 };
 //BA.debugLineNum = 450;BA.debugLine="rsHeader.Close";
_rsheader.Close();
 //BA.debugLineNum = 452;BA.debugLine="LogColor($\"Total Production: \"$ & GTotProd, Color";
anywheresoftware.b4a.keywords.Common.LogImpl("060948515",("Total Production: ")+BA.NumberToString(_gtotprod),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 454;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 456;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 457;BA.debugLine="Try";
try { //BA.debugLineNum = 458;BA.debugLine="Starter.strCriteria = \"UPDATE TranHeader SET \" &";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE TranHeader SET "+"TotProduction = ?, "+"ModifiedBy = ?, "+"ModifiedAt = ? "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 464;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_gtotprod),BA.NumberToString(mostCurrent._globalvar._userid /*int*/ ),_smodifiedat}));
 //BA.debugLineNum = 465;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 466;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e35) {
			processBA.setLastException(e35); //BA.debugLineNum = 468;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("060948531",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 469;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 471;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 472;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 473;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 975;BA.debugLine="Dim retVal As String";
_retval = "";
 //BA.debugLineNum = 976;BA.debugLine="Dim jParser As JSONParser";
_jparser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 978;BA.debugLine="Dim j As HttpJob";
_j = new bwsi.PumpOperations.httpjob();
 //BA.debugLineNum = 979;BA.debugLine="j.Initialize(\"\", Me)";
_j._initialize /*String*/ (processBA,"",addeditfmrdg.getObject());
 //BA.debugLineNum = 981;BA.debugLine="j.PostString(GlobalVar.BaseURL & \"logs/production";
_j._poststring /*String*/ (parent.mostCurrent._globalvar._baseurl /*String*/ +"logs/production",_sdata);
 //BA.debugLineNum = 982;BA.debugLine="j.GetRequest.SetHeader(\"User-Agent\", \"Mozilla/5.0";
_j._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ ().SetHeader("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.92 Safari/537.36");
 //BA.debugLineNum = 983;BA.debugLine="j.GetRequest.SetContentType(\"plain/text\")";
_j._getrequest /*anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest*/ ().SetContentType("plain/text");
 //BA.debugLineNum = 984;BA.debugLine="Log(sData)";
anywheresoftware.b4a.keywords.Common.LogImpl("061865994",_sdata,0);
 //BA.debugLineNum = 986;BA.debugLine="Wait For (j) JobDone(j As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_j));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_j = (bwsi.PumpOperations.httpjob) result[0];
;
 //BA.debugLineNum = 987;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 988;BA.debugLine="retVal = j.GetString";
_retval = _j._getstring /*String*/ ();
 //BA.debugLineNum = 989;BA.debugLine="jParser.Initialize(retVal)";
_jparser.Initialize(_retval);
 //BA.debugLineNum = 990;BA.debugLine="Log(retVal)";
anywheresoftware.b4a.keywords.Common.LogImpl("061866000",_retval,0);
 //BA.debugLineNum = 991;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 992;BA.debugLine="j.Release";
_j._release /*String*/ ();
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 994;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 995;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("061866005",_j._errormessage /*String*/ ,0);
 //BA.debugLineNum = 997;BA.debugLine="jParser.Initialize(retVal)";
_jparser.Initialize(_retval);
 //BA.debugLineNum = 998;BA.debugLine="Log(retVal)";
anywheresoftware.b4a.keywords.Common.LogImpl("061866008",_retval,0);
 //BA.debugLineNum = 1000;BA.debugLine="ToastMessageShow(\"Unable to Upload Reading Data";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Unable to Upload Reading Data due to "+_j._errormessage /*String*/ ),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1001;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 1002;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("061866012",_j._errormessage /*String*/ ,0);
 //BA.debugLineNum = 1003;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 1005;BA.debugLine="End Sub";
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
