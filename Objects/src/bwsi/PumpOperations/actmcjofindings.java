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

public class actmcjofindings extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static actmcjofindings mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.actmcjofindings");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actmcjofindings).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.actmcjofindings");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.actmcjofindings", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actmcjofindings) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actmcjofindings) Resume **");
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
		return actmcjofindings.class;
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
            BA.LogInfo("** Activity (actmcjofindings) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (actmcjofindings) Pause event (activity is not paused). **");
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
            actmcjofindings mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (actmcjofindings) Resume **");
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
public anywheresoftware.b4a.object.XmlLayoutBuilder _xmlicon = null;
public de.amberhome.materialdialogs.MaterialDialogBuilderWrapper _matdialogbuilder = null;
public de.amberhome.objects.SnackbarWrapper _snack = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fontbold = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdremborder = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdwl = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scvmain = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmain = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbljonum = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbljocat = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblappnum = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcustname = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcustaddress = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblacctclass = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblmeterno = null;
public static int _imeterid = 0;
public anywheresoftware.b4a.objects.EditTextWrapper _txtprevrdg = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtcurrentrdg = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtmeterfindings = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtactiontaken = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _cbopipetype = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _cbopipesize = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtpsi = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtminutes = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtwaterloss = null;
public static double _waterloss = 0;
public anywheresoftware.b4a.objects.EditTextWrapper _txtremarks = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnsaveupdate = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlsignature = null;
public de.donmanfred.SignPad _signaturepad = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnconfirmsig = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlconfirmsig = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgsignature = null;
public de.donmanfred.MultiSelectSpinnerWrapper _spnplumbers = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtdatetimefinished = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtdatetimestarted = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btncancel = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnclear = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnok = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdcancel = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdclear = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdok = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdsig = null;
public Object _esig = null;
public static String _sigfoldername = "";
public static String _sigpicpath = "";
public static String _sigfilename = "";
public static String _rootdir = "";
public static boolean _hassign = false;
public anywheresoftware.b4a.objects.PanelWrapper _pnlfindings = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlwaterlossfindings = null;
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

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _accomplished_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 906;BA.debugLine="Private Sub Accomplished_OnNegativeClicked (View A";
 //BA.debugLineNum = 908;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 909;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 910;BA.debugLine="End Sub";
return "";
}
public static String  _accomplished_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 912;BA.debugLine="Private Sub Accomplished_OnPositiveClicked (View A";
 //BA.debugLineNum = 915;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 916;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 918;BA.debugLine="UpdateJO(GlobalVar.SelectedJOID)";
_updatejo(mostCurrent._globalvar._selectedjoid /*int*/ );
 //BA.debugLineNum = 919;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4a.object.XmlLayoutBuilder _xl = null;
 //BA.debugLineNum = 100;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 101;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 102;BA.debugLine="Activity.LoadLayout(\"JOFindingsMain\")";
mostCurrent._activity.LoadLayout("JOFindingsMain",mostCurrent.activityBA);
 //BA.debugLineNum = 104;BA.debugLine="scvMain.Panel.LoadLayout(\"MCJOFindings\")";
mostCurrent._scvmain.getPanel().LoadLayout("MCJOFindings",mostCurrent.activityBA);
 //BA.debugLineNum = 105;BA.debugLine="scvMain.Panel.Height = pnlMain.Height";
mostCurrent._scvmain.getPanel().setHeight(mostCurrent._pnlmain.getHeight());
 //BA.debugLineNum = 107;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append($";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Accomplishment Form"))).PopAll();
 //BA.debugLineNum = 108;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Append";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(mostCurrent._globalvar._selectedjodesc /*String*/ +(" JO"))).PopAll();
 //BA.debugLineNum = 110;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 111;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 112;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 114;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 115;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 116;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 117;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 118;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 119;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 121;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 122;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 124;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 125;BA.debugLine="HasSign = False";
_hassign = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 126;BA.debugLine="ClearUI";
_clearui();
 //BA.debugLineNum = 127;BA.debugLine="FillJORecord(GlobalVar.SelectedJOID)";
_filljorecord(mostCurrent._globalvar._selectedjoid /*int*/ );
 };
 //BA.debugLineNum = 143;BA.debugLine="InpTyp.Initialize";
_inptyp._initialize /*String*/ (processBA);
 //BA.debugLineNum = 144;BA.debugLine="InpTyp.SetInputType(txtRemarks,Array As Int(InpTy";
_inptyp._setinputtype /*String*/ (mostCurrent._txtremarks,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_complete /*int*/ (),_inptyp._type_text_flag_cap_words /*int*/ ()});
 //BA.debugLineNum = 146;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
de.amberhome.objects.appcompat.ACMenuItemWrapper _item = null;
 //BA.debugLineNum = 210;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 211;BA.debugLine="Dim Item As ACMenuItem";
_item = new de.amberhome.objects.appcompat.ACMenuItemWrapper();
 //BA.debugLineNum = 213;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 214;BA.debugLine="Menu.Add2(1, 1, \"Cancel JO\",xmlIcon.GetDrawable(\"";
_menu.Add2((int) (1),(int) (1),BA.ObjectToCharSequence("Cancel JO"),mostCurrent._xmlicon.GetDrawable("baseline_delete_forever_white_24dp")).setShowAsAction(_item.SHOW_AS_ACTION_WITH_TEXT);
 //BA.debugLineNum = 215;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 187;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 188;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 189;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 190;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 192;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 194;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 203;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 204;BA.debugLine="CallSubDelayed(Starter,\"StopFLP\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._starter.getObject()),"StopFLP");
 //BA.debugLineNum = 205;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 161;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 162;BA.debugLine="If Result Then";
if (_result) { 
 //BA.debugLineNum = 163;BA.debugLine="If Permission = Starter.RTP.PERMISSION_READ_EXTE";
if ((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 164;BA.debugLine="LogColor($\"Permission to Read External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("038338563",("Permission to Read External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 165;BA.debugLine="GlobalVar.ReadStoragePermission = True";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 167;BA.debugLine="LogColor($\"Permission to Write External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("038338566",("Permission to Write External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 168;BA.debugLine="GlobalVar.WriteStoragePermission = True";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION)) { 
 //BA.debugLineNum = 170;BA.debugLine="LogColor($\"Permission to Access Coarse Location";
anywheresoftware.b4a.keywords.Common.LogImpl("038338569",("Permission to Access Coarse Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 171;BA.debugLine="GlobalVar.CoarseLocPermission = True";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION)) { 
 //BA.debugLineNum = 173;BA.debugLine="LogColor($\"Permission to Access Fine Location G";
anywheresoftware.b4a.keywords.Common.LogImpl("038338572",("Permission to Access Fine Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 174;BA.debugLine="GlobalVar.FineLocPermission = True";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 176;BA.debugLine="Starter.StartFLP";
mostCurrent._starter._startflp /*void*/ ();
 }else {
 //BA.debugLineNum = 178;BA.debugLine="GlobalVar.ReadStoragePermission = False";
mostCurrent._globalvar._readstoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 179;BA.debugLine="GlobalVar.WriteStoragePermission = False";
mostCurrent._globalvar._writestoragepermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 180;BA.debugLine="GlobalVar.CoarseLocPermission = False";
mostCurrent._globalvar._coarselocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 181;BA.debugLine="GlobalVar.FineLocPermission = False";
mostCurrent._globalvar._finelocpermission /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 182;BA.debugLine="Result = False";
_result = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 184;BA.debugLine="Log (Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("038338583",_permission,0);
 //BA.debugLineNum = 185;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 196;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 197;BA.debugLine="HasSign = False";
_hassign = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 198;BA.debugLine="ClearUI";
_clearui();
 //BA.debugLineNum = 199;BA.debugLine="FillJORecord(GlobalVar.SelectedJOID)";
_filljorecord(mostCurrent._globalvar._selectedjoid /*int*/ );
 //BA.debugLineNum = 200;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 201;BA.debugLine="End Sub";
return "";
}
public static String  _btncancel_click() throws Exception{
 //BA.debugLineNum = 431;BA.debugLine="Sub btnCancel_Click";
 //BA.debugLineNum = 432;BA.debugLine="pnlSignature.Visible = False";
mostCurrent._pnlsignature.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 433;BA.debugLine="pnlConfirmSig.Visible = False";
mostCurrent._pnlconfirmsig.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 434;BA.debugLine="End Sub";
return "";
}
public static String  _btnclear_click() throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 406;BA.debugLine="Sub btnClear_Click";
 //BA.debugLineNum = 407;BA.debugLine="pnlSignature.Visible = True";
mostCurrent._pnlsignature.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 408;BA.debugLine="pnlConfirmSig.Visible = False";
mostCurrent._pnlconfirmsig.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 410;BA.debugLine="cdOK.Initialize2(GlobalVar.GreenColor, 20, 0, Col";
mostCurrent._cdok.Initialize2((int) (mostCurrent._globalvar._greencolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 411;BA.debugLine="btnConfirmSig.Background = cdOK";
mostCurrent._btnconfirmsig.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 412;BA.debugLine="btnConfirmSig.Text = Chr(0xE5C8) & $\" CONFIRM SIG";
mostCurrent._btnconfirmsig.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe5c8)))+(" CONFIRM SIGNATURE")));
 //BA.debugLineNum = 414;BA.debugLine="cdSig.Initialize2(0xFFD3D3D3,0,0,Colors.Transpare";
mostCurrent._cdsig.Initialize2((int) (0xffd3d3d3),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 415;BA.debugLine="SignaturePad.Background = cdSig";
mostCurrent._signaturepad.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdsig.getObject()));
 //BA.debugLineNum = 416;BA.debugLine="SignaturePad.clear";
mostCurrent._signaturepad.clear();
 //BA.debugLineNum = 417;BA.debugLine="SignaturePad.StrokeWidth = 15";
mostCurrent._signaturepad.setStrokeWidth((float) (15));
 //BA.debugLineNum = 418;BA.debugLine="SignaturePad.Visible = True";
mostCurrent._signaturepad.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 419;BA.debugLine="imgSignature.Bitmap = Null";
mostCurrent._imgsignature.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 420;BA.debugLine="SignaturePad.Capture(True)";
mostCurrent._signaturepad.Capture(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 421;BA.debugLine="scvMain.ScrollPosition = 0%x";
mostCurrent._scvmain.setScrollPosition(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (0),mostCurrent.activityBA));
 //BA.debugLineNum = 422;BA.debugLine="HasSign = False";
_hassign = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 423;BA.debugLine="scvMain.Enabled = False";
mostCurrent._scvmain.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 424;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 425;BA.debugLine="r.Target = scvMain";
_r.Target = (Object)(mostCurrent._scvmain.getObject());
 //BA.debugLineNum = 426;BA.debugLine="r.RunMethod2(\"setVerticalScrollBarEnabled\", False";
_r.RunMethod2("setVerticalScrollBarEnabled",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.False),"java.lang.boolean");
 //BA.debugLineNum = 427;BA.debugLine="r.RunMethod2(\"setOverScrollMode\", 2, \"java.lang.i";
_r.RunMethod2("setOverScrollMode",BA.NumberToString(2),"java.lang.int");
 //BA.debugLineNum = 428;BA.debugLine="r.SetOnTouchListener(\"scvTouch\")";
_r.SetOnTouchListener(processBA,"scvTouch");
 //BA.debugLineNum = 429;BA.debugLine="End Sub";
return "";
}
public static String  _btnconfirmsig_click() throws Exception{
long _lngdatetime = 0L;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 377;BA.debugLine="Sub btnConfirmSig_Click";
 //BA.debugLineNum = 378;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 379;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 381;BA.debugLine="If HasSign = False Then";
if (_hassign==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 382;BA.debugLine="RequiredMsgBox(Chr(0xE002) & $\" ERROR\"$,$\"Custom";
_requiredmsgbox(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe002)))+(" ERROR"),("Customer Signature is required."));
 //BA.debugLineNum = 383;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 386;BA.debugLine="Log(\"SignaturePad_onSigned(sign)\")";
anywheresoftware.b4a.keywords.Common.LogImpl("039190537","SignaturePad_onSigned(sign)",0);
 //BA.debugLineNum = 388;BA.debugLine="pnlConfirmSig.Visible = True";
mostCurrent._pnlconfirmsig.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 389;BA.debugLine="pnlSignature.Visible = False";
mostCurrent._pnlsignature.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 391;BA.debugLine="Dim bmp As Bitmap = eSig";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp = (anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(mostCurrent._esig));
 //BA.debugLineNum = 392;BA.debugLine="imgSignature.Bitmap = bmp";
mostCurrent._imgsignature.setBitmap((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 393;BA.debugLine="spnPlumbers.Items = Array As String(\"\")";
mostCurrent._spnplumbers.setItems(new String[]{""});
 //BA.debugLineNum = 394;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 395;BA.debugLine="txtDateTimeFinished.Text = DateTime.Date(lngDateT";
mostCurrent._txtdatetimefinished.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime)));
 //BA.debugLineNum = 396;BA.debugLine="scvMain.ScrollPosition = 0%x";
mostCurrent._scvmain.setScrollPosition(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (0),mostCurrent.activityBA));
 //BA.debugLineNum = 398;BA.debugLine="FillPlumbers";
_fillplumbers();
 //BA.debugLineNum = 400;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 401;BA.debugLine="r.Target = scvMain";
_r.Target = (Object)(mostCurrent._scvmain.getObject());
 //BA.debugLineNum = 402;BA.debugLine="r.RunMethod2(\"setVerticalScrollBarEnabled\", False";
_r.RunMethod2("setVerticalScrollBarEnabled",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.False),"java.lang.boolean");
 //BA.debugLineNum = 403;BA.debugLine="r.RunMethod2(\"setOverScrollMode\", 2, \"java.lang.i";
_r.RunMethod2("setOverScrollMode",BA.NumberToString(2),"java.lang.int");
 //BA.debugLineNum = 404;BA.debugLine="End Sub";
return "";
}
public static String  _btnok_click() throws Exception{
 //BA.debugLineNum = 444;BA.debugLine="Sub btnOK_Click";
 //BA.debugLineNum = 445;BA.debugLine="If spnPlumbers.SelectedItemsString = \"\" Then";
if ((mostCurrent._spnplumbers.getSelectedItemsString()).equals("")) { 
 //BA.debugLineNum = 446;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Unable to Accomplshe";
_requiredmsgbox(("ERROR"),("Unable to Accomplshed JO due to No selected plumber(s)."));
 //BA.debugLineNum = 447;BA.debugLine="spnPlumbers.RequestFocus";
mostCurrent._spnplumbers.RequestFocus();
 //BA.debugLineNum = 448;BA.debugLine="Log(\"No Selected\")";
anywheresoftware.b4a.keywords.Common.LogImpl("039518212","No Selected",0);
 //BA.debugLineNum = 449;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 452;BA.debugLine="If Not(MiscFunctions.IsValidDate(txtDateTimeStart";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._miscfunctions._isvaliddate /*boolean*/ (mostCurrent.activityBA,mostCurrent._txtdatetimestarted.getText())) || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtdatetimestarted.getText()))==0) { 
 //BA.debugLineNum = 453;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid date Started";
_requiredmsgbox(("ERROR"),("Invalid date Started!"));
 //BA.debugLineNum = 454;BA.debugLine="txtDateTimeStarted.RequestFocus";
mostCurrent._txtdatetimestarted.RequestFocus();
 //BA.debugLineNum = 455;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 458;BA.debugLine="If Not(MiscFunctions.IsValidDate(txtDateTimeFinis";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._miscfunctions._isvaliddate /*boolean*/ (mostCurrent.activityBA,mostCurrent._txtdatetimefinished.getText())) || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtdatetimefinished.getText()))==0) { 
 //BA.debugLineNum = 459;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid date finishe";
_requiredmsgbox(("ERROR"),("Invalid date finished!"));
 //BA.debugLineNum = 460;BA.debugLine="txtDateTimeFinished.RequestFocus";
mostCurrent._txtdatetimefinished.RequestFocus();
 //BA.debugLineNum = 461;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 464;BA.debugLine="scvMain.ScrollPosition = 0%x";
mostCurrent._scvmain.setScrollPosition(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (0),mostCurrent.activityBA));
 //BA.debugLineNum = 465;BA.debugLine="ConfirmJOAccomplishment";
_confirmjoaccomplishment();
 //BA.debugLineNum = 466;BA.debugLine="End Sub";
return "";
}
public static String  _btnsaveupdate_click() throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 338;BA.debugLine="Sub btnSaveUpdate_Click";
 //BA.debugLineNum = 339;BA.debugLine="Dim cdSig As ColorDrawable";
mostCurrent._cdsig = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 340;BA.debugLine="If Not(ValidateEntries) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_validateentries())) { 
 //BA.debugLineNum = 341;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 344;BA.debugLine="pnlSignature.Visible = True";
mostCurrent._pnlsignature.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 345;BA.debugLine="cdOK.Initialize2(GlobalVar.GreenColor, 20, 0, Col";
mostCurrent._cdok.Initialize2((int) (mostCurrent._globalvar._greencolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 346;BA.debugLine="btnConfirmSig.Background = cdOK";
mostCurrent._btnconfirmsig.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 347;BA.debugLine="btnConfirmSig.Text = Chr(0xE5C8) & $\" CONFIRM SIG";
mostCurrent._btnconfirmsig.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe5c8)))+(" CONFIRM SIGNATURE")));
 //BA.debugLineNum = 349;BA.debugLine="cdSig.Initialize2(0xFFD3D3D3,0,0,Colors.Transpare";
mostCurrent._cdsig.Initialize2((int) (0xffd3d3d3),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 350;BA.debugLine="SignaturePad.Background = cdSig";
mostCurrent._signaturepad.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdsig.getObject()));
 //BA.debugLineNum = 352;BA.debugLine="SignaturePad.clear";
mostCurrent._signaturepad.clear();
 //BA.debugLineNum = 353;BA.debugLine="SignaturePad.StrokeWidth = 15";
mostCurrent._signaturepad.setStrokeWidth((float) (15));
 //BA.debugLineNum = 354;BA.debugLine="SignaturePad.Visible = True";
mostCurrent._signaturepad.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 355;BA.debugLine="imgSignature.Bitmap = Null";
mostCurrent._imgsignature.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 356;BA.debugLine="SignaturePad.Capture(True)";
mostCurrent._signaturepad.Capture(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 357;BA.debugLine="scvMain.ScrollPosition = 0%x";
mostCurrent._scvmain.setScrollPosition(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (0),mostCurrent.activityBA));
 //BA.debugLineNum = 358;BA.debugLine="HasSign = False";
_hassign = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 359;BA.debugLine="scvMain.Enabled = False";
mostCurrent._scvmain.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 361;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 362;BA.debugLine="r.Target = scvMain";
_r.Target = (Object)(mostCurrent._scvmain.getObject());
 //BA.debugLineNum = 363;BA.debugLine="r.RunMethod2(\"setVerticalScrollBarEnabled\", False";
_r.RunMethod2("setVerticalScrollBarEnabled",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.False),"java.lang.boolean");
 //BA.debugLineNum = 364;BA.debugLine="r.RunMethod2(\"setOverScrollMode\", 2, \"java.lang.i";
_r.RunMethod2("setOverScrollMode",BA.NumberToString(2),"java.lang.int");
 //BA.debugLineNum = 365;BA.debugLine="r.SetOnTouchListener(\"scvTouch\")";
_r.SetOnTouchListener(processBA,"scvTouch");
 //BA.debugLineNum = 366;BA.debugLine="End Sub";
return "";
}
public static String  _cbopipesize_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 659;BA.debugLine="Sub cboPipeSize_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 660;BA.debugLine="If GlobalVar.SF.Len(cboPipeType.SelectedItem) <=";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbopipetype.getSelectedItem())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbopipesize.getSelectedItem())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtminutes.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtpsi.getText())<=0) { 
 //BA.debugLineNum = 661;BA.debugLine="WaterLoss = 0";
_waterloss = 0;
 }else {
 //BA.debugLineNum = 663;BA.debugLine="If txtPSI.Text <= 30 Then";
if ((double)(Double.parseDouble(mostCurrent._txtpsi.getText()))<=30) { 
 //BA.debugLineNum = 664;BA.debugLine="WaterLoss = DBaseFunctions.ComputeWaterLoss(cbo";
_waterloss = mostCurrent._dbasefunctions._computewaterloss /*double*/ (mostCurrent.activityBA,mostCurrent._cbopipetype.getSelectedItem(),mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvv2(mostCurrent._cbopipesize.getSelectedItem()),(int)(Double.parseDouble(mostCurrent._txtpsi.getText())),(int)(Double.parseDouble(mostCurrent._txtminutes.getText())));
 }else {
 //BA.debugLineNum = 666;BA.debugLine="WaterLoss = 0";
_waterloss = 0;
 };
 };
 //BA.debugLineNum = 669;BA.debugLine="txtWaterLoss.Text = WaterLoss";
mostCurrent._txtwaterloss.setText(BA.ObjectToCharSequence(_waterloss));
 //BA.debugLineNum = 670;BA.debugLine="End Sub";
return "";
}
public static String  _cbopipetype_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 672;BA.debugLine="Sub cboPipeType_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 673;BA.debugLine="If GlobalVar.SF.Len(cboPipeType.SelectedItem) <=";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbopipetype.getSelectedItem())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbopipesize.getSelectedItem())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtminutes.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtpsi.getText())<=0) { 
 //BA.debugLineNum = 674;BA.debugLine="WaterLoss = 0";
_waterloss = 0;
 }else {
 //BA.debugLineNum = 676;BA.debugLine="If txtPSI.Text <= 30 Then";
if ((double)(Double.parseDouble(mostCurrent._txtpsi.getText()))<=30) { 
 //BA.debugLineNum = 677;BA.debugLine="WaterLoss = DBaseFunctions.ComputeWaterLoss(cbo";
_waterloss = mostCurrent._dbasefunctions._computewaterloss /*double*/ (mostCurrent.activityBA,mostCurrent._cbopipetype.getSelectedItem(),mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvv2(mostCurrent._cbopipesize.getSelectedItem()),(int)(Double.parseDouble(mostCurrent._txtpsi.getText())),(int)(Double.parseDouble(mostCurrent._txtminutes.getText())));
 }else {
 //BA.debugLineNum = 679;BA.debugLine="WaterLoss = 0";
_waterloss = 0;
 };
 };
 //BA.debugLineNum = 682;BA.debugLine="txtWaterLoss.Text = WaterLoss";
mostCurrent._txtwaterloss.setText(BA.ObjectToCharSequence(_waterloss));
 //BA.debugLineNum = 683;BA.debugLine="End Sub";
return "";
}
public static String  _checkpermissions() throws Exception{
 //BA.debugLineNum = 149;BA.debugLine="Private Sub CheckPermissions";
 //BA.debugLineNum = 150;BA.debugLine="Log(\"Checking Permissions\")";
anywheresoftware.b4a.keywords.Common.LogImpl("038273025","Checking Permissions",0);
 //BA.debugLineNum = 152;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE);
 //BA.debugLineNum = 153;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE);
 //BA.debugLineNum = 154;BA.debugLine="Starter.RTP.GetAllSafeDirsExternal(\"\")";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .GetAllSafeDirsExternal("");
 //BA.debugLineNum = 156;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION);
 //BA.debugLineNum = 157;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 158;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 159;BA.debugLine="End Sub";
return "";
}
public static String  _clearui() throws Exception{
 //BA.debugLineNum = 235;BA.debugLine="Private Sub ClearUI";
 //BA.debugLineNum = 236;BA.debugLine="cdRemBorder.Initialize2(Colors.White, 0, 0, Color";
mostCurrent._cdremborder.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.White,(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 237;BA.debugLine="cdWL.Initialize(0xFF1A535C,0)";
mostCurrent._cdwl.Initialize((int) (0xff1a535c),(int) (0));
 //BA.debugLineNum = 239;BA.debugLine="txtPrevRdg.Background = cdWL";
mostCurrent._txtprevrdg.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdwl.getObject()));
 //BA.debugLineNum = 240;BA.debugLine="txtCurrentRdg.Background = cdRemBorder";
mostCurrent._txtcurrentrdg.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdremborder.getObject()));
 //BA.debugLineNum = 241;BA.debugLine="txtMeterFindings.Background = cdRemBorder";
mostCurrent._txtmeterfindings.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdremborder.getObject()));
 //BA.debugLineNum = 242;BA.debugLine="txtActionTaken.Background = cdRemBorder";
mostCurrent._txtactiontaken.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdremborder.getObject()));
 //BA.debugLineNum = 244;BA.debugLine="cboPipeType.Background = cdRemBorder";
mostCurrent._cbopipetype.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdremborder.getObject()));
 //BA.debugLineNum = 245;BA.debugLine="cboPipeSize.Background = cdRemBorder";
mostCurrent._cbopipesize.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdremborder.getObject()));
 //BA.debugLineNum = 246;BA.debugLine="txtPSI.Background = cdRemBorder";
mostCurrent._txtpsi.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdremborder.getObject()));
 //BA.debugLineNum = 247;BA.debugLine="txtMinutes.Background = cdRemBorder";
mostCurrent._txtminutes.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdremborder.getObject()));
 //BA.debugLineNum = 249;BA.debugLine="txtRemarks.Background = cdRemBorder";
mostCurrent._txtremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdremborder.getObject()));
 //BA.debugLineNum = 251;BA.debugLine="txtDateTimeStarted.Background = cdRemBorder";
mostCurrent._txtdatetimestarted.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdremborder.getObject()));
 //BA.debugLineNum = 252;BA.debugLine="txtDateTimeFinished.Background = cdRemBorder";
mostCurrent._txtdatetimefinished.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdremborder.getObject()));
 //BA.debugLineNum = 254;BA.debugLine="txtWaterLoss.Background = cdWL";
mostCurrent._txtwaterloss.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdwl.getObject()));
 //BA.debugLineNum = 256;BA.debugLine="CD.Initialize2(GlobalVar.GreenColor, 30, 0, Color";
mostCurrent._cd.Initialize2((int) (mostCurrent._globalvar._greencolor /*double*/ ),(int) (30),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 257;BA.debugLine="btnSaveUpdate.Background = CD";
mostCurrent._btnsaveupdate.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cd.getObject()));
 //BA.debugLineNum = 258;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\" ACCOMPLISH";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" ACCOMPLISH JO")));
 //BA.debugLineNum = 260;BA.debugLine="cdCancel.Initialize2(GlobalVar.RedColor, 20, 0, C";
mostCurrent._cdcancel.Initialize2((int) (mostCurrent._globalvar._redcolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 261;BA.debugLine="btnCancel.Background = cdCancel";
mostCurrent._btncancel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdcancel.getObject()));
 //BA.debugLineNum = 262;BA.debugLine="btnCancel.Text = $\"CANCEL\"$";
mostCurrent._btncancel.setText(BA.ObjectToCharSequence(("CANCEL")));
 //BA.debugLineNum = 264;BA.debugLine="cdClear.Initialize2(GlobalVar.YellowColor, 20, 0,";
mostCurrent._cdclear.Initialize2((int) (mostCurrent._globalvar._yellowcolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 265;BA.debugLine="btnClear.Background = cdClear";
mostCurrent._btnclear.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdclear.getObject()));
 //BA.debugLineNum = 266;BA.debugLine="btnClear.Text = $\"RETRY\"$";
mostCurrent._btnclear.setText(BA.ObjectToCharSequence(("RETRY")));
 //BA.debugLineNum = 268;BA.debugLine="cdOK.Initialize2(GlobalVar.GreenColor, 20, 0, Col";
mostCurrent._cdok.Initialize2((int) (mostCurrent._globalvar._greencolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 269;BA.debugLine="btnOk.Background = cdOK";
mostCurrent._btnok.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 270;BA.debugLine="btnOk.Text = $\"SAVE\"$";
mostCurrent._btnok.setText(BA.ObjectToCharSequence(("SAVE")));
 //BA.debugLineNum = 272;BA.debugLine="lblJONum.Text = \"\"";
mostCurrent._lbljonum.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 273;BA.debugLine="lblJOCat.Text = \"\"";
mostCurrent._lbljocat.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 274;BA.debugLine="lblAppNum.Text = \"\"";
mostCurrent._lblappnum.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 275;BA.debugLine="lblCustName.Text = \"\"";
mostCurrent._lblcustname.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 276;BA.debugLine="lblCustAddress.Text = \"\"";
mostCurrent._lblcustaddress.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 277;BA.debugLine="lblAcctClass.Text = \"\"";
mostCurrent._lblacctclass.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 278;BA.debugLine="lblMeterNo.Text = \"\"";
mostCurrent._lblmeterno.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 280;BA.debugLine="txtPrevRdg.Text = \"\"";
mostCurrent._txtprevrdg.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 281;BA.debugLine="txtCurrentRdg.Text = \"\"";
mostCurrent._txtcurrentrdg.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 282;BA.debugLine="txtMeterFindings.Text = \"\"";
mostCurrent._txtmeterfindings.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 283;BA.debugLine="txtActionTaken.Text = \"\"";
mostCurrent._txtactiontaken.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 285;BA.debugLine="txtMinutes.Text = \"\"";
mostCurrent._txtminutes.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 286;BA.debugLine="txtWaterLoss.Text = \"\"";
mostCurrent._txtwaterloss.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 287;BA.debugLine="txtRemarks.Text = \"\"";
mostCurrent._txtremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 289;BA.debugLine="txtDateTimeStarted.Text = \"\"";
mostCurrent._txtdatetimestarted.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 290;BA.debugLine="txtDateTimeFinished.Text = \"\"";
mostCurrent._txtdatetimefinished.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 291;BA.debugLine="WaterLoss = 0";
_waterloss = 0;
 //BA.debugLineNum = 293;BA.debugLine="pnlSignature.Visible = False";
mostCurrent._pnlsignature.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 294;BA.debugLine="pnlConfirmSig.Visible = False";
mostCurrent._pnlconfirmsig.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 297;BA.debugLine="FillPipeTypes";
_fillpipetypes();
 //BA.debugLineNum = 298;BA.debugLine="FillPipeSizes";
_fillpipesizes();
 //BA.debugLineNum = 299;BA.debugLine="End Sub";
return "";
}
public static String  _confirmjoaccomplishment() throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 881;BA.debugLine="Private Sub ConfirmJOAccomplishment";
 //BA.debugLineNum = 882;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 884;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle("JO Accomplishment").SetTitleColor((int) (mostCurrent._globalvar._bluecolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage("Do you want to Accomplish this JO Now?").SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"Accomplished").SetOnNegativeClicked(mostCurrent.activityBA,"Accomplished");
 //BA.debugLineNum = 901;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 902;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 903;BA.debugLine="End Sub";
return "";
}
public static String  _dispinfomsg(String _stitle,String _smsg) throws Exception{
 //BA.debugLineNum = 943;BA.debugLine="Private Sub DispInfoMsg(sTitle As String, sMsg As";
 //BA.debugLineNum = 944;BA.debugLine="MatDialogBuilder.Initialize(\"DispInformationMsg\")";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"DispInformationMsg");
 //BA.debugLineNum = 945;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 946;BA.debugLine="MatDialogBuilder.Title(sTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_stitle));
 //BA.debugLineNum = 947;BA.debugLine="MatDialogBuilder.Content(sMsg)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_smsg));
 //BA.debugLineNum = 948;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 949;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 950;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 951;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 952;BA.debugLine="End Sub";
return "";
}
public static String  _dispinformationmsg_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 954;BA.debugLine="Private Sub DispInformationMsg_ButtonPressed(mDial";
 //BA.debugLineNum = 955;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE)) {
case 0: {
 //BA.debugLineNum = 957;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 960;BA.debugLine="End Sub";
return "";
}
public static String  _filljorecord(int _ijoid) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rs = null;
 //BA.debugLineNum = 301;BA.debugLine="Private Sub FillJORecord (iJOID As Int)";
 //BA.debugLineNum = 302;BA.debugLine="Dim RS As Cursor";
_rs = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 303;BA.debugLine="Try";
try { //BA.debugLineNum = 304;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblJOs \" &";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblJOs "+"WHERE JOID = "+BA.NumberToString(_ijoid);
 //BA.debugLineNum = 306;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("038928389",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 308;BA.debugLine="RS = Starter.DBCon.ExecQuery(Starter.strCriteria";
_rs = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 310;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 311;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 312;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd HH:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss a");
 //BA.debugLineNum = 313;BA.debugLine="lblJONum.Text = RS.GetString(\"JONo\")";
mostCurrent._lbljonum.setText(BA.ObjectToCharSequence(_rs.GetString("JONo")));
 //BA.debugLineNum = 314;BA.debugLine="lblAppNum.Text = RS.GetString(\"RefNo\")";
mostCurrent._lblappnum.setText(BA.ObjectToCharSequence(_rs.GetString("RefNo")));
 //BA.debugLineNum = 315;BA.debugLine="lblJOCat.Text = GlobalVar.SF.Upper(RS.GetString";
mostCurrent._lbljocat.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JoDesc"))));
 //BA.debugLineNum = 316;BA.debugLine="lblCustName.Text = GlobalVar.SF.Upper(RS.GetStr";
mostCurrent._lblcustname.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustName"))));
 //BA.debugLineNum = 317;BA.debugLine="lblCustAddress.Text = GlobalVar.SF.Upper(RS.Get";
mostCurrent._lblcustaddress.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustAddress"))));
 //BA.debugLineNum = 318;BA.debugLine="lblAcctClass.Text = GlobalVar.SF.Upper(RS.GetSt";
mostCurrent._lblacctclass.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctClass"))+"-"+mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctSubClass"))));
 //BA.debugLineNum = 319;BA.debugLine="txtDateTimeStarted.Text = GlobalVar.SF.Upper(RS";
mostCurrent._txtdatetimestarted.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("DateStarted"))));
 //BA.debugLineNum = 320;BA.debugLine="iMeterID = RS.GetInt(\"MeterID\")";
_imeterid = _rs.GetInt("MeterID");
 //BA.debugLineNum = 321;BA.debugLine="lblMeterNo.Text = DBaseFunctions.GetCodeByID(\"M";
mostCurrent._lblmeterno.setText(BA.ObjectToCharSequence(mostCurrent._dbasefunctions._getcodebyid /*String*/ (mostCurrent.activityBA,"MeterNo","tblMeters","MeterID",_imeterid)));
 //BA.debugLineNum = 322;BA.debugLine="txtPrevRdg.Text = RS.GetString(\"PrevRdg\")";
mostCurrent._txtprevrdg.setText(BA.ObjectToCharSequence(_rs.GetString("PrevRdg")));
 }else {
 //BA.debugLineNum = 324;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 325;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 326;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 327;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 328;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("038928411",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 329;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e28) {
			processBA.setLastException(e28); //BA.debugLineNum = 332;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("038928415",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 334;BA.debugLine="RS.Close";
_rs.Close();
 //BA.debugLineNum = 335;BA.debugLine="End Sub";
return "";
}
public static String  _fillpipesizes() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rspipesize = null;
int _i = 0;
 //BA.debugLineNum = 488;BA.debugLine="Private Sub FillPipeSizes";
 //BA.debugLineNum = 489;BA.debugLine="Dim rsPipeSize As Cursor";
_rspipesize = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 490;BA.debugLine="cboPipeSize.Clear";
mostCurrent._cbopipesize.Clear();
 //BA.debugLineNum = 491;BA.debugLine="Try";
try { //BA.debugLineNum = 492;BA.debugLine="Starter.strCriteria = \"SELECT * FROM cons_pipe_s";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM cons_pipe_size";
 //BA.debugLineNum = 493;BA.debugLine="rsPipeSize = Starter.DBCon.ExecQuery(Starter.str";
_rspipesize = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 494;BA.debugLine="If rsPipeSize.RowCount > 0 Then";
if (_rspipesize.getRowCount()>0) { 
 //BA.debugLineNum = 495;BA.debugLine="For i = 0 To rsPipeSize.RowCount - 1";
{
final int step7 = 1;
final int limit7 = (int) (_rspipesize.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 496;BA.debugLine="rsPipeSize.Position = i";
_rspipesize.setPosition(_i);
 //BA.debugLineNum = 497;BA.debugLine="cboPipeSize.Add(GlobalVar.SF.Lower(rsPipeSize.";
mostCurrent._cbopipesize.Add(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvv2(_rspipesize.GetString("SizeDesc"))));
 }
};
 };
 } 
       catch (Exception e13) {
			processBA.setLastException(e13); //BA.debugLineNum = 501;BA.debugLine="ToastMessageShow(\"Unable to Load Pipe Type due t";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Unable to Load Pipe Type due to "+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 502;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("039649294",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 504;BA.debugLine="rsPipeSize.Close";
_rspipesize.Close();
 //BA.debugLineNum = 505;BA.debugLine="End Sub";
return "";
}
public static String  _fillpipetypes() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rspipetype = null;
int _i = 0;
 //BA.debugLineNum = 469;BA.debugLine="Private Sub FillPipeTypes";
 //BA.debugLineNum = 470;BA.debugLine="Dim rsPipeType As Cursor";
_rspipetype = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 471;BA.debugLine="cboPipeType.Clear";
mostCurrent._cbopipetype.Clear();
 //BA.debugLineNum = 472;BA.debugLine="Try";
try { //BA.debugLineNum = 473;BA.debugLine="Starter.strCriteria = \"SELECT * FROM cons_pipe_t";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM cons_pipe_type";
 //BA.debugLineNum = 474;BA.debugLine="rsPipeType = Starter.DBCon.ExecQuery(Starter.str";
_rspipetype = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 475;BA.debugLine="If rsPipeType.RowCount > 0 Then";
if (_rspipetype.getRowCount()>0) { 
 //BA.debugLineNum = 476;BA.debugLine="For i = 0 To rsPipeType.RowCount - 1";
{
final int step7 = 1;
final int limit7 = (int) (_rspipetype.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 477;BA.debugLine="rsPipeType.Position = i";
_rspipetype.setPosition(_i);
 //BA.debugLineNum = 478;BA.debugLine="cboPipeType.Add(GlobalVar.SF.Proper(rsPipeType";
mostCurrent._cbopipetype.Add(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvv5(_rspipetype.GetString("PipeDesc"))));
 }
};
 };
 } 
       catch (Exception e13) {
			processBA.setLastException(e13); //BA.debugLineNum = 482;BA.debugLine="ToastMessageShow(\"Unable to Load Pipe Type due t";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Unable to Load Pipe Type due to "+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 483;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("039583758",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 485;BA.debugLine="rsPipeType.Close";
_rspipetype.Close();
 //BA.debugLineNum = 486;BA.debugLine="End Sub";
return "";
}
public static String  _fillplumbers() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsplumbers = null;
String _empname = "";
String[] _empnamelist = null;
int _pcount = 0;
int _i = 0;
 //BA.debugLineNum = 507;BA.debugLine="Private Sub FillPlumbers";
 //BA.debugLineNum = 508;BA.debugLine="Dim rsPlumbers As Cursor";
_rsplumbers = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 510;BA.debugLine="Dim EmpName As String";
_empname = "";
 //BA.debugLineNum = 511;BA.debugLine="Dim EmpNameList() As String";
_empnamelist = new String[(int) (0)];
java.util.Arrays.fill(_empnamelist,"");
 //BA.debugLineNum = 512;BA.debugLine="Dim pCount As Int";
_pcount = 0;
 //BA.debugLineNum = 514;BA.debugLine="Try";
try { //BA.debugLineNum = 515;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblPlumbers";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblPlumbers "+"ORDER BY id ASC";
 //BA.debugLineNum = 518;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("039714827",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 520;BA.debugLine="rsPlumbers =  Starter.DBCon.ExecQuery (Starter.s";
_rsplumbers = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 521;BA.debugLine="If rsPlumbers.RowCount > 0 Then";
if (_rsplumbers.getRowCount()>0) { 
 //BA.debugLineNum = 522;BA.debugLine="pCount = rsPlumbers.RowCount";
_pcount = _rsplumbers.getRowCount();
 //BA.debugLineNum = 523;BA.debugLine="Dim EmpNameList(pCount) As String";
_empnamelist = new String[_pcount];
java.util.Arrays.fill(_empnamelist,"");
 //BA.debugLineNum = 525;BA.debugLine="For i = 0 To rsPlumbers.RowCount - 1";
{
final int step12 = 1;
final int limit12 = (int) (_rsplumbers.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit12 ;_i = _i + step12 ) {
 //BA.debugLineNum = 526;BA.debugLine="rsPlumbers.Position = i";
_rsplumbers.setPosition(_i);
 //BA.debugLineNum = 527;BA.debugLine="EmpName = rsPlumbers.GetString(\"EmpName\")";
_empname = _rsplumbers.GetString("EmpName");
 //BA.debugLineNum = 528;BA.debugLine="EmpNameList(i) = EmpName";
_empnamelist[_i] = _empname;
 }
};
 }else {
 //BA.debugLineNum = 531;BA.debugLine="snack.Initialize(\"\", Activity, \"No plumber(s) f";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),"No plumber(s) found!",mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 532;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Colors";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 533;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 534;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 535;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e25) {
			processBA.setLastException(e25); //BA.debugLineNum = 538;BA.debugLine="snack.Initialize(\"\", Activity, LastException,sna";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 539;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Colors.";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 540;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 541;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 542;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 545;BA.debugLine="spnPlumbers.Items = EmpNameList";
mostCurrent._spnplumbers.setItems(_empnamelist);
 //BA.debugLineNum = 546;BA.debugLine="End Sub";
return "";
}
public static String  _fontsizebinder_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 922;BA.debugLine="Private Sub FontSizeBinder_OnBindView (View As Vie";
 //BA.debugLineNum = 923;BA.debugLine="Dim alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 924;BA.debugLine="alert.Initialize";
_alert.Initialize();
 //BA.debugLineNum = 925;BA.debugLine="If ViewType = alert.VIEW_TITLE Then ' Title";
if (_viewtype==_alert.VIEW_TITLE) { 
 //BA.debugLineNum = 926;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 927;BA.debugLine="lbl.TextSize = 30";
_lbl.setTextSize((float) (30));
 //BA.debugLineNum = 928;BA.debugLine="lbl.SetTextColorAnimated(2000,Colors.Magenta)";
_lbl.SetTextColorAnimated((int) (2000),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 931;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 935;BA.debugLine="CS.Initialize.Typeface(Typeface.MATERIALICONS).S";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe88e)))+"  "));
 //BA.debugLineNum = 936;BA.debugLine="CS.Typeface(Font).Size(24).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(mostCurrent._font.getObject())).Size((int) (24)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 938;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 940;BA.debugLine="End Sub";
return "";
}
public static void  _getwatermeters(String _sbranchid) throws Exception{
ResumableSub_GetWaterMeters rsub = new ResumableSub_GetWaterMeters(null,_sbranchid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetWaterMeters extends BA.ResumableSub {
public ResumableSub_GetWaterMeters(bwsi.PumpOperations.actmcjofindings parent,String _sbranchid) {
this.parent = parent;
this._sbranchid = _sbranchid;
}
bwsi.PumpOperations.actmcjofindings parent;
String _sbranchid;
String _urlname = "";
String _retval = "";
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
 //BA.debugLineNum = 549;BA.debugLine="Dim URLName As String";
_urlname = "";
 //BA.debugLineNum = 550;BA.debugLine="Dim RetVal As String";
_retval = "";
 //BA.debugLineNum = 552;BA.debugLine="Dim j As HttpJob";
_j = new bwsi.PumpOperations.httpjob();
 //BA.debugLineNum = 554;BA.debugLine="j.Initialize(\"\",Me)";
_j._initialize /*String*/ (processBA,"",actmcjofindings.getObject());
 //BA.debugLineNum = 555;BA.debugLine="URLName = GlobalVar.BaseURL & $\"water-meters\"$";
_urlname = parent.mostCurrent._globalvar._baseurl /*String*/ +("water-meters");
 //BA.debugLineNum = 556;BA.debugLine="Log (URLName & $\"BranchID = \"$ & sBranchID)";
anywheresoftware.b4a.keywords.Common.LogImpl("039780360",_urlname+("BranchID = ")+_sbranchid,0);
 //BA.debugLineNum = 557;BA.debugLine="j.Download2(URLName, Array As String(\"BranchID\",";
_j._download2 /*String*/ (_urlname,new String[]{"BranchID",_sbranchid});
 //BA.debugLineNum = 559;BA.debugLine="ProgressDialogShow2($\"Downloading Water Meter Dat";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence(("Downloading Water Meter Data...")),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 560;BA.debugLine="Wait For (j) JobDone(j As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_j));
this.state = 11;
return;
case 11:
//C
this.state = 1;
_j = (bwsi.PumpOperations.httpjob) result[0];
;
 //BA.debugLineNum = 561;BA.debugLine="If j.Success Then";
if (true) break;

case 1:
//if
this.state = 10;
if (_j._success /*boolean*/ ) { 
this.state = 3;
}else {
this.state = 9;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 562;BA.debugLine="RetVal = j.GetString";
_retval = _j._getstring /*String*/ ();
 //BA.debugLineNum = 563;BA.debugLine="Log(RetVal)";
anywheresoftware.b4a.keywords.Common.LogImpl("039780367",_retval,0);
 //BA.debugLineNum = 564;BA.debugLine="If RetVal = \"[]\" Then";
if (true) break;

case 4:
//if
this.state = 7;
if ((_retval).equals("[]")) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 566;BA.debugLine="ToastMessageShow($\"No Water Meter found! ERROR:";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("No Water Meter found! ERROR: ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 567;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 568;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 7:
//C
this.state = 10;
;
 //BA.debugLineNum = 570;BA.debugLine="SaveWaterMeters(RetVal)";
_savewatermeters(_retval);
 if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 572;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("039780376",_j._errormessage /*String*/ ,0);
 //BA.debugLineNum = 573;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 575;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 577;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 579;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _jobdone(bwsi.PumpOperations.httpjob _j) throws Exception{
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 25;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 26;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private xmlIcon As XmlLayoutBuilder";
mostCurrent._xmlicon = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 29;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private Font As Typeface = Typeface.LoadFromAsset";
mostCurrent._font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont.ttf")));
 //BA.debugLineNum = 34;BA.debugLine="Private FontBold As Typeface = Typeface.LoadFromA";
mostCurrent._fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont_bold.ttf")));
 //BA.debugLineNum = 37;BA.debugLine="Private CD, cdRemBorder, cdWL As ColorDrawable";
mostCurrent._cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdremborder = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdwl = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 38;BA.debugLine="Private scvMain As ScrollView";
mostCurrent._scvmain = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private pnlMain As Panel";
mostCurrent._pnlmain = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private lblJONum As Label";
mostCurrent._lbljonum = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private lblJOCat As Label";
mostCurrent._lbljocat = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private lblAppNum As Label";
mostCurrent._lblappnum = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private lblCustName As Label";
mostCurrent._lblcustname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private lblCustAddress As Label";
mostCurrent._lblcustaddress = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private lblAcctClass As Label";
mostCurrent._lblacctclass = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private lblMeterNo As Label";
mostCurrent._lblmeterno = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private iMeterID As Int";
_imeterid = 0;
 //BA.debugLineNum = 51;BA.debugLine="Private txtPrevRdg As EditText";
mostCurrent._txtprevrdg = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private txtCurrentRdg As EditText";
mostCurrent._txtcurrentrdg = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private txtMeterFindings As EditText";
mostCurrent._txtmeterfindings = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private txtActionTaken As EditText";
mostCurrent._txtactiontaken = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private cboPipeType As ACSpinner";
mostCurrent._cbopipetype = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private cboPipeSize As ACSpinner";
mostCurrent._cbopipesize = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Private txtPSI As EditText";
mostCurrent._txtpsi = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private txtMinutes As EditText";
mostCurrent._txtminutes = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Private txtWaterLoss As EditText";
mostCurrent._txtwaterloss = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Private WaterLoss As Double";
_waterloss = 0;
 //BA.debugLineNum = 65;BA.debugLine="Private txtRemarks As EditText";
mostCurrent._txtremarks = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 68;BA.debugLine="Private btnSaveUpdate As ACButton";
mostCurrent._btnsaveupdate = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 71;BA.debugLine="Private pnlSignature As Panel";
mostCurrent._pnlsignature = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 72;BA.debugLine="Private SignaturePad As SignPad";
mostCurrent._signaturepad = new de.donmanfred.SignPad();
 //BA.debugLineNum = 73;BA.debugLine="Private btnConfirmSig As ACButton";
mostCurrent._btnconfirmsig = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 75;BA.debugLine="Private pnlConfirmSig As Panel";
mostCurrent._pnlconfirmsig = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 76;BA.debugLine="Private imgSignature As ImageView";
mostCurrent._imgsignature = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 77;BA.debugLine="Private spnPlumbers As MultiSelectSpinner";
mostCurrent._spnplumbers = new de.donmanfred.MultiSelectSpinnerWrapper();
 //BA.debugLineNum = 78;BA.debugLine="Private txtDateTimeFinished As EditText";
mostCurrent._txtdatetimefinished = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 79;BA.debugLine="Private txtDateTimeStarted As EditText";
mostCurrent._txtdatetimestarted = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 81;BA.debugLine="Private btnCancel As ACButton";
mostCurrent._btncancel = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 82;BA.debugLine="Private btnClear As ACButton";
mostCurrent._btnclear = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 83;BA.debugLine="Private btnOk As ACButton";
mostCurrent._btnok = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 85;BA.debugLine="Private cdCancel, cdClear, cdOK, cdSig As ColorDr";
mostCurrent._cdcancel = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdclear = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdok = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdsig = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 86;BA.debugLine="Private eSig As Object";
mostCurrent._esig = new Object();
 //BA.debugLineNum = 88;BA.debugLine="Private SigFolderName As String";
mostCurrent._sigfoldername = "";
 //BA.debugLineNum = 89;BA.debugLine="Private SigPicPath As String";
mostCurrent._sigpicpath = "";
 //BA.debugLineNum = 90;BA.debugLine="Private SigFilename As String";
mostCurrent._sigfilename = "";
 //BA.debugLineNum = 91;BA.debugLine="Private RootDir As String = File.DirRootExternal";
mostCurrent._rootdir = anywheresoftware.b4a.keywords.Common.File.getDirRootExternal();
 //BA.debugLineNum = 92;BA.debugLine="Private HasSign As Boolean";
_hassign = false;
 //BA.debugLineNum = 94;BA.debugLine="Private pnlFindings As Panel";
mostCurrent._pnlfindings = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 95;BA.debugLine="Private pnlWaterLossFindings As Panel";
mostCurrent._pnlwaterlossfindings = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public static String  _pnlconfirmsig_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 440;BA.debugLine="Sub pnlConfirmSig_Touch (Action As Int, X As Float";
 //BA.debugLineNum = 442;BA.debugLine="End Sub";
return "";
}
public static String  _pnlsignature_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 436;BA.debugLine="Sub pnlSignature_Touch (Action As Int, X As Float,";
 //BA.debugLineNum = 438;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 21;BA.debugLine="Dim InpTyp As SLInpTypeConst";
_inptyp = new bwsi.PumpOperations.slinptypeconst();
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsg_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 876;BA.debugLine="Private Sub RequiredMsg_OnPositiveClicked (View As";
 //BA.debugLineNum = 877;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 878;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 879;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsgbox(String _stitle,String _smsg) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 851;BA.debugLine="Private Sub RequiredMsgBox(sTitle As String, sMsg";
 //BA.debugLineNum = 852;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 853;BA.debugLine="Alert.Initialize.Dismiss2";
_alert.Initialize().Dismiss2();
 //BA.debugLineNum = 855;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._redcolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage(_smsg).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"RequiredMsg").SetOnViewBinder(mostCurrent.activityBA,"FontSizeBinder");
 //BA.debugLineNum = 870;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 871;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 872;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper  _roundbd(anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper _bd,int _dp) throws Exception{
 //BA.debugLineNum = 964;BA.debugLine="Sub RoundBD(BD As BigDecimal, DP As Int) As BigDec";
 //BA.debugLineNum = 965;BA.debugLine="BD.Round(BD.Precision - BD.Scale + DP, BD.ROUND_H";
_bd.Round((int) (_bd.Precision()-_bd.Scale()+_dp),_bd.ROUND_HALF_UP);
 //BA.debugLineNum = 966;BA.debugLine="Return BD";
if (true) return _bd;
 //BA.debugLineNum = 967;BA.debugLine="End Sub";
return null;
}
public static void  _savewatermeters(String _sdata) throws Exception{
ResumableSub_SaveWaterMeters rsub = new ResumableSub_SaveWaterMeters(null,_sdata);
rsub.resume(processBA, null);
}
public static class ResumableSub_SaveWaterMeters extends BA.ResumableSub {
public ResumableSub_SaveWaterMeters(bwsi.PumpOperations.actmcjofindings parent,String _sdata) {
this.parent = parent;
this._sdata = _sdata;
}
bwsi.PumpOperations.actmcjofindings parent;
String _sdata;
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.List _root = null;
anywheresoftware.b4a.objects.collections.Map _mp = null;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.BA.IterableList group6;
int index6;
int groupLen6;

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
 //BA.debugLineNum = 582;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 583;BA.debugLine="Dim root As List";
_root = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 585;BA.debugLine="parser.Initialize(sData)";
_parser.Initialize(_sdata);
 //BA.debugLineNum = 586;BA.debugLine="root = parser.NextArray";
_root = _parser.NextArray();
 //BA.debugLineNum = 588;BA.debugLine="Try";
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
 //BA.debugLineNum = 589;BA.debugLine="For Each MP As Map In root";
if (true) break;

case 4:
//for
this.state = 7;
_mp = new anywheresoftware.b4a.objects.collections.Map();
group6 = _root;
index6 = 0;
groupLen6 = group6.getSize();
this.state = 17;
if (true) break;

case 17:
//C
this.state = 7;
if (index6 < groupLen6) {
this.state = 6;
_mp = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(group6.Get(index6)));}
if (true) break;

case 18:
//C
this.state = 17;
index6++;
if (true) break;

case 6:
//C
this.state = 18;
 //BA.debugLineNum = 590;BA.debugLine="Starter.strCriteria=\"INSERT INTO tblMeters VALU";
parent.mostCurrent._starter._strcriteria /*String*/  = "INSERT INTO tblMeters VALUES (?, ?, ?, )";
 //BA.debugLineNum = 591;BA.debugLine="Starter.DBCon.AddNonQueryToBatch(Starter.strCri";
parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .AddNonQueryToBatch(parent.mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{_mp.Get((Object)("MeterID")),_mp.Get((Object)("MeterNo")),_mp.Get((Object)("BrandName"))}));
 if (true) break;
if (true) break;

case 7:
//C
this.state = 8;
;
 //BA.debugLineNum = 594;BA.debugLine="Dim SenderFilter As Object = Starter.DBCon.ExecN";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQueryBatch(processBA,"SQL");
 //BA.debugLineNum = 595;BA.debugLine="Wait For (SenderFilter) SQL_NonQueryComplete (Su";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_nonquerycomplete", processBA, this, _senderfilter);
this.state = 19;
return;
case 19:
//C
this.state = 8;
_success = (Boolean) result[0];
;
 //BA.debugLineNum = 597;BA.debugLine="If Success Then";
if (true) break;

case 8:
//if
this.state = 13;
if (_success) { 
this.state = 10;
}else {
this.state = 12;
}if (true) break;

case 10:
//C
this.state = 13;
 //BA.debugLineNum = 598;BA.debugLine="ToastMessageShow($\"Water Meters were successful";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Water Meters were successfully updated!")),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 600;BA.debugLine="ToastMessageShow($\"Unable to save water meter r";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to save water meter records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 601;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("039845908",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
 //BA.debugLineNum = 605;BA.debugLine="ToastMessageShow($\"Unable to save water meter re";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to save water meter records due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 606;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("039845913",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 608;BA.debugLine="End Sub";
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
public static void  _sql_nonquerycomplete(boolean _success) throws Exception{
}
public static String  _scvmain_scrollchanged(int _position) throws Exception{
 //BA.debugLineNum = 231;BA.debugLine="Sub scvMain_ScrollChanged(Position As Int)";
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}
public static boolean  _scvtouch(Object _viewtag,int _action,float _x,float _y,Object _motionevent) throws Exception{
 //BA.debugLineNum = 368;BA.debugLine="Sub scvTouch(viewtag As Object, action As Int, X A";
 //BA.debugLineNum = 369;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 370;BA.debugLine="End Sub";
return false;
}
public static String  _signaturepad_onsigned(Object _sign) throws Exception{
 //BA.debugLineNum = 372;BA.debugLine="Sub SignaturePad_onSigned(sign As Object)";
 //BA.debugLineNum = 373;BA.debugLine="eSig = sign";
mostCurrent._esig = _sign;
 //BA.debugLineNum = 374;BA.debugLine="HasSign = True";
_hassign = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 375;BA.debugLine="End Sub";
return "";
}
public static String  _spnplumbers_onitemselected(int _position,boolean _ischecked,String _item) throws Exception{
 //BA.debugLineNum = 746;BA.debugLine="Sub spnPlumbers_onItemSelected(position As Int, is";
 //BA.debugLineNum = 747;BA.debugLine="LogColor(item, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("040697857",_item,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 748;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 221;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 226;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 217;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 218;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 219;BA.debugLine="End Sub";
return "";
}
public static String  _txtinitrdg_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 690;BA.debugLine="Sub txtInitRdg_FocusChanged (HasFocus As Boolean)";
 //BA.debugLineNum = 691;BA.debugLine="If HasFocus = True Then scvMain.ScrollPosition =";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._scvmain.setScrollPosition(mostCurrent._pnlwaterlossfindings.getTop());};
 //BA.debugLineNum = 692;BA.debugLine="End Sub";
return "";
}
public static String  _txtlatestrdg_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 685;BA.debugLine="Sub txtLatestRdg_FocusChanged (HasFocus As Boolean";
 //BA.debugLineNum = 686;BA.debugLine="If HasFocus = True Then scvMain.ScrollPosition =";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._scvmain.setScrollPosition(mostCurrent._pnlfindings.getTop());};
 //BA.debugLineNum = 687;BA.debugLine="End Sub";
return "";
}
public static String  _txtminutes_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 732;BA.debugLine="Sub txtMinutes_FocusChanged (HasFocus As Boolean)";
 //BA.debugLineNum = 733;BA.debugLine="txtMinutes.SelectAll";
mostCurrent._txtminutes.SelectAll();
 //BA.debugLineNum = 734;BA.debugLine="End Sub";
return "";
}
public static String  _txtminutes_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 719;BA.debugLine="Sub txtMinutes_TextChanged (Old As String, New As";
 //BA.debugLineNum = 720;BA.debugLine="If GlobalVar.SF.Len(cboPipeType.SelectedItem) <=";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbopipetype.getSelectedItem())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbopipesize.getSelectedItem())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtminutes.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtpsi.getText())<=0) { 
 //BA.debugLineNum = 721;BA.debugLine="WaterLoss = 0";
_waterloss = 0;
 }else {
 //BA.debugLineNum = 723;BA.debugLine="If txtPSI.Text <= 30 Then";
if ((double)(Double.parseDouble(mostCurrent._txtpsi.getText()))<=30) { 
 //BA.debugLineNum = 724;BA.debugLine="WaterLoss = DBaseFunctions.ComputeWaterLoss(cbo";
_waterloss = mostCurrent._dbasefunctions._computewaterloss /*double*/ (mostCurrent.activityBA,mostCurrent._cbopipetype.getSelectedItem(),mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvv2(mostCurrent._cbopipesize.getSelectedItem()),(int)(Double.parseDouble(mostCurrent._txtpsi.getText())),(int)(Double.parseDouble(mostCurrent._txtminutes.getText())));
 }else {
 //BA.debugLineNum = 726;BA.debugLine="WaterLoss = 0";
_waterloss = 0;
 };
 };
 //BA.debugLineNum = 729;BA.debugLine="txtWaterLoss.Text = WaterLoss";
mostCurrent._txtwaterloss.setText(BA.ObjectToCharSequence(_waterloss));
 //BA.debugLineNum = 730;BA.debugLine="End Sub";
return "";
}
public static String  _txtnewmeterno_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 694;BA.debugLine="Sub txtNewMeterNo_FocusChanged (HasFocus As Boolea";
 //BA.debugLineNum = 695;BA.debugLine="If HasFocus = True Then scvMain.ScrollPosition =";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._scvmain.setScrollPosition(mostCurrent._pnlwaterlossfindings.getTop());};
 //BA.debugLineNum = 696;BA.debugLine="End Sub";
return "";
}
public static String  _txtpsi_enterpressed() throws Exception{
 //BA.debugLineNum = 715;BA.debugLine="Sub txtPSI_EnterPressed";
 //BA.debugLineNum = 716;BA.debugLine="txtMinutes.RequestFocus";
mostCurrent._txtminutes.RequestFocus();
 //BA.debugLineNum = 717;BA.debugLine="End Sub";
return "";
}
public static String  _txtpsi_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 711;BA.debugLine="Sub txtPSI_FocusChanged (HasFocus As Boolean)";
 //BA.debugLineNum = 712;BA.debugLine="txtPSI.SelectAll";
mostCurrent._txtpsi.SelectAll();
 //BA.debugLineNum = 713;BA.debugLine="End Sub";
return "";
}
public static String  _txtpsi_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 698;BA.debugLine="Sub txtPSI_TextChanged (Old As String, New As Stri";
 //BA.debugLineNum = 699;BA.debugLine="If GlobalVar.SF.Len(cboPipeType.SelectedItem) <=";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbopipetype.getSelectedItem())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbopipesize.getSelectedItem())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtminutes.getText())<=0 || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtpsi.getText())<=0) { 
 //BA.debugLineNum = 700;BA.debugLine="WaterLoss = 0";
_waterloss = 0;
 }else {
 //BA.debugLineNum = 702;BA.debugLine="If txtPSI.Text <= 30 Then";
if ((double)(Double.parseDouble(mostCurrent._txtpsi.getText()))<=30) { 
 //BA.debugLineNum = 703;BA.debugLine="WaterLoss = DBaseFunctions.ComputeWaterLoss(cbo";
_waterloss = mostCurrent._dbasefunctions._computewaterloss /*double*/ (mostCurrent.activityBA,mostCurrent._cbopipetype.getSelectedItem(),mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvv2(mostCurrent._cbopipesize.getSelectedItem()),(int)(Double.parseDouble(mostCurrent._txtpsi.getText())),(int)(Double.parseDouble(mostCurrent._txtminutes.getText())));
 }else {
 //BA.debugLineNum = 705;BA.debugLine="WaterLoss = 0";
_waterloss = 0;
 };
 };
 //BA.debugLineNum = 708;BA.debugLine="txtWaterLoss.Text = WaterLoss";
mostCurrent._txtwaterloss.setText(BA.ObjectToCharSequence(_waterloss));
 //BA.debugLineNum = 709;BA.debugLine="End Sub";
return "";
}
public static String  _txtremarks_focuschanged(boolean _hasfocus) throws Exception{
anywheresoftware.b4a.objects.EditTextWrapper _send = null;
 //BA.debugLineNum = 736;BA.debugLine="Sub txtRemarks_FocusChanged (HasFocus As Boolean)";
 //BA.debugLineNum = 737;BA.debugLine="Dim Send As EditText";
_send = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 739;BA.debugLine="If HasFocus Then";
if (_hasfocus) { 
 //BA.debugLineNum = 740;BA.debugLine="Send = Sender";
_send = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 741;BA.debugLine="scvMain.ScrollPosition = pnlWaterLossFindings.To";
mostCurrent._scvmain.setScrollPosition(mostCurrent._pnlwaterlossfindings.getTop());
 //BA.debugLineNum = 742;BA.debugLine="txtRemarks.SelectAll";
mostCurrent._txtremarks.SelectAll();
 };
 //BA.debugLineNum = 744;BA.debugLine="End Sub";
return "";
}
public static String  _updatejo(int _ijoid) throws Exception{
double _doldmeterid = 0;
double _dprevrdg = 0;
double _dfinalrdg = 0;
double _daddcons = 0;
double _dnewmeterid = 0;
double _dinitrdg = 0;
String _spipetype = "";
String _spipesize = "";
int _ipsi = 0;
int _iminutes = 0;
double _dwaterloss = 0;
long _lngdatetime = 0L;
String _sdateposted = "";
String _saccomplishedby = "";
String _sremarks = "";
String _slocation = "";
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
anywheresoftware.b4a.phone.Phone _phone = null;
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 753;BA.debugLine="Private Sub UpdateJO (iJOID As Int)";
 //BA.debugLineNum = 754;BA.debugLine="Dim dOldMeterID, dPrevRdg, dFinalRdg, dAddCons, d";
_doldmeterid = 0;
_dprevrdg = 0;
_dfinalrdg = 0;
_daddcons = 0;
_dnewmeterid = 0;
_dinitrdg = 0;
 //BA.debugLineNum = 757;BA.debugLine="Dim sPipeType, sPipeSize As String";
_spipetype = "";
_spipesize = "";
 //BA.debugLineNum = 758;BA.debugLine="Dim iPSI, iMinutes As Int";
_ipsi = 0;
_iminutes = 0;
 //BA.debugLineNum = 759;BA.debugLine="Dim dWaterLoss As Double";
_dwaterloss = 0;
 //BA.debugLineNum = 761;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 762;BA.debugLine="Dim sDatePosted As String";
_sdateposted = "";
 //BA.debugLineNum = 763;BA.debugLine="Dim sAccomplishedBy As String";
_saccomplishedby = "";
 //BA.debugLineNum = 764;BA.debugLine="Dim sRemarks, sLocation As String";
_sremarks = "";
_slocation = "";
 //BA.debugLineNum = 776;BA.debugLine="sPipeType = cboPipeType.SelectedItem";
_spipetype = mostCurrent._cbopipetype.getSelectedItem();
 //BA.debugLineNum = 777;BA.debugLine="sPipeSize = cboPipeSize.SelectedItem";
_spipesize = mostCurrent._cbopipesize.getSelectedItem();
 //BA.debugLineNum = 778;BA.debugLine="iPSI = txtPSI.Text";
_ipsi = (int)(Double.parseDouble(mostCurrent._txtpsi.getText()));
 //BA.debugLineNum = 779;BA.debugLine="iMinutes = txtMinutes.Text";
_iminutes = (int)(Double.parseDouble(mostCurrent._txtminutes.getText()));
 //BA.debugLineNum = 780;BA.debugLine="dWaterLoss = txtWaterLoss.Text";
_dwaterloss = (double)(Double.parseDouble(mostCurrent._txtwaterloss.getText()));
 //BA.debugLineNum = 783;BA.debugLine="sRemarks = txtRemarks.Text";
_sremarks = mostCurrent._txtremarks.getText();
 //BA.debugLineNum = 784;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 786;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("040763425",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 788;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("040763427",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 789;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("040763428",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 791;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 792;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("040763431",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 794;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 795;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 796;BA.debugLine="sDatePosted = DateTime.Date(lngDateTime)";
_sdateposted = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 797;BA.debugLine="sAccomplishedBy = DBaseFunctions.GetPlumberIDs(sp";
_saccomplishedby = mostCurrent._dbasefunctions._getplumberids /*String*/ (mostCurrent.activityBA,mostCurrent._spnplumbers.getSelectedItemsString());
 //BA.debugLineNum = 799;BA.debugLine="If GlobalVar.WriteStoragePermission = False Then";
if (mostCurrent._globalvar._writestoragepermission /*boolean*/ ==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 800;BA.debugLine="ToastMessageShow ($\"Unable to Save Signature Ima";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Signature Image due to permission to write was denied")),anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 802;BA.debugLine="SigPicPath = File.Combine(RootDir, \"DCIM\")";
mostCurrent._sigpicpath = anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._rootdir,"DCIM");
 //BA.debugLineNum = 803;BA.debugLine="SigFolderName = File.Combine(SigPicPath, \"BWSI-O";
mostCurrent._sigfoldername = anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._sigpicpath,"BWSI-OP");
 //BA.debugLineNum = 804;BA.debugLine="If File.Exists(SigFolderName, \"\") = False Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._sigfoldername,"")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 805;BA.debugLine="File.MakeDir(SigPicPath, \"BWSI-OP\")";
anywheresoftware.b4a.keywords.Common.File.MakeDir(mostCurrent._sigpicpath,"BWSI-OP");
 //BA.debugLineNum = 806;BA.debugLine="SigFolderName = File.Combine(SigPicPath, \"BWSI-";
mostCurrent._sigfoldername = anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._sigpicpath,"BWSI-OP");
 };
 };
 //BA.debugLineNum = 810;BA.debugLine="SigFilename = GlobalVar.SelectedJOCatCode & \" -\"&";
mostCurrent._sigfilename = mostCurrent._globalvar._selectedjocatcode /*String*/ +" -"+mostCurrent._lbljonum.getText()+"-"+mostCurrent._lblappnum.getText()+".jpg";
 //BA.debugLineNum = 811;BA.debugLine="Dim bmp As Bitmap = SignaturePad.TransparentSigna";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp = (anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(mostCurrent._signaturepad.getTransparentSignatureBitmap()));
 //BA.debugLineNum = 812;BA.debugLine="imgSignature.Bitmap = bmp";
mostCurrent._imgsignature.setBitmap((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 815;BA.debugLine="SignaturePad.saveBitmapToJPG(bmp,File.Combine(Sig";
mostCurrent._signaturepad.saveBitmapToJPG((android.graphics.Bitmap)(_bmp.getObject()),anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._sigfoldername,mostCurrent._sigfilename));
 //BA.debugLineNum = 816;BA.debugLine="Log(SigFilename & \" Saved\")";
anywheresoftware.b4a.keywords.Common.LogImpl("040763455",mostCurrent._sigfilename+" Saved",0);
 //BA.debugLineNum = 818;BA.debugLine="Dim Phone As Phone";
_phone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 819;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 820;BA.debugLine="i.Initialize(\"android.intent.action.MEDIA_SCANNER";
_i.Initialize("android.intent.action.MEDIA_SCANNER_SCAN_FILE","file://"+anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._sigfoldername,mostCurrent._sigfilename));
 //BA.debugLineNum = 822;BA.debugLine="Phone.SendBroadcastIntent(i)";
_phone.SendBroadcastIntent((android.content.Intent)(_i.getObject()));
 //BA.debugLineNum = 824;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 825;BA.debugLine="Try";
try { //BA.debugLineNum = 827;BA.debugLine="Starter.strCriteria = \"UPDATE tblJOs \" & _";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE tblJOs "+"SET JOStatus = ? , DateFinished = ?, AccomplishedBy = ?, SigFileName = ?, PostedAt = ?, PostedOn = ?, WasRead = ? "+"WHERE JOID = "+BA.NumberToString(_ijoid);
 //BA.debugLineNum = 830;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{("3"),mostCurrent._txtdatetimefinished.getText(),_saccomplishedby,mostCurrent._sigfoldername+"/"+mostCurrent._sigfilename,_sdateposted,_slocation,("1")}));
 //BA.debugLineNum = 833;BA.debugLine="Starter.strCriteria = \"INSERT INTO tblJOCMFindin";
mostCurrent._starter._strcriteria /*String*/  = "INSERT INTO tblJOCMFindings VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
 //BA.debugLineNum = 834;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._selectedjoid /*int*/ ),(Object)(_doldmeterid),(Object)(_dprevrdg),(Object)(_dfinalrdg),(Object)(_daddcons),(Object)(_dnewmeterid),(Object)(_dinitrdg),(Object)(_sremarks)}));
 //BA.debugLineNum = 837;BA.debugLine="Starter.strCriteria = \"INSERT INTO JOWaterLoss V";
mostCurrent._starter._strcriteria /*String*/  = "INSERT INTO JOWaterLoss VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?)";
 //BA.debugLineNum = 838;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._selectedjoid /*int*/ ),(Object)(("DC-CR")),(Object)(_spipetype),(Object)(_spipesize),(Object)(_ipsi),(Object)(_iminutes),(Object)(_dwaterloss)}));
 //BA.debugLineNum = 840;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 841;BA.debugLine="DispInfoMsg($\"JO ACCOMPLISHED\"$,$\"JO has been su";
_dispinfomsg(("JO ACCOMPLISHED"),("JO has been successfully accomplished."));
 } 
       catch (Exception e55) {
			processBA.setLastException(e55); //BA.debugLineNum = 843;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("040763482",anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),0);
 };
 //BA.debugLineNum = 845;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 846;BA.debugLine="End Sub";
return "";
}
public static boolean  _validateentries() throws Exception{
 //BA.debugLineNum = 612;BA.debugLine="Private Sub ValidateEntries () As Boolean";
 //BA.debugLineNum = 614;BA.debugLine="Try";
try { //BA.debugLineNum = 615;BA.debugLine="If GlobalVar.SF.Len(txtCurrentRdg.Text) <= 0 The";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtcurrentrdg.getText())<=0) { 
 //BA.debugLineNum = 616;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Unable to Accomplsh";
_requiredmsgbox(("ERROR"),("Unable to Accomplshed JO due to No specified Meter Number."));
 //BA.debugLineNum = 617;BA.debugLine="txtCurrentRdg.RequestFocus";
mostCurrent._txtcurrentrdg.RequestFocus();
 //BA.debugLineNum = 618;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 622;BA.debugLine="If GlobalVar.SF.Len(cboPipeType.SelectedItem) <=";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbopipetype.getSelectedItem())<=0) { 
 //BA.debugLineNum = 623;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Unable to Accomplsh";
_requiredmsgbox(("ERROR"),("Unable to Accomplshed JO due to Water Loss Computation is missing."));
 //BA.debugLineNum = 624;BA.debugLine="cboPipeType.RequestFocus";
mostCurrent._cbopipetype.RequestFocus();
 //BA.debugLineNum = 625;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 628;BA.debugLine="If GlobalVar.SF.Len(cboPipeSize.SelectedItem) <=";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbopipesize.getSelectedItem())<=0) { 
 //BA.debugLineNum = 629;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Unable to Accomplsh";
_requiredmsgbox(("ERROR"),("Unable to Accomplshed JO due to Water Loss Computation is missing."));
 //BA.debugLineNum = 630;BA.debugLine="cboPipeSize.RequestFocus";
mostCurrent._cbopipesize.RequestFocus();
 //BA.debugLineNum = 631;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 634;BA.debugLine="If GlobalVar.SF.Len(txtPSI.Text) <= 0 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtpsi.getText())<=0) { 
 //BA.debugLineNum = 635;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Unable to Accomplsh";
_requiredmsgbox(("ERROR"),("Unable to Accomplshed JO due to Water Loss Computation is missing."));
 //BA.debugLineNum = 636;BA.debugLine="txtPSI.RequestFocus";
mostCurrent._txtpsi.RequestFocus();
 //BA.debugLineNum = 637;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 640;BA.debugLine="If GlobalVar.SF.Len(txtMinutes.Text) <= 0 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtminutes.getText())<=0) { 
 //BA.debugLineNum = 641;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Unable to Accomplsh";
_requiredmsgbox(("ERROR"),("Unable to Accomplshed JO due to Water Loss Computation is missing."));
 //BA.debugLineNum = 642;BA.debugLine="txtMinutes.RequestFocus";
mostCurrent._txtminutes.RequestFocus();
 //BA.debugLineNum = 643;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 646;BA.debugLine="If GlobalVar.SF.Len(txtWaterLoss.Text) <= 0 Then";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._txtwaterloss.getText())<=0) { 
 //BA.debugLineNum = 647;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 650;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e32) {
			processBA.setLastException(e32); //BA.debugLineNum = 652;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("039911464",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 653;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 655;BA.debugLine="End Sub";
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
