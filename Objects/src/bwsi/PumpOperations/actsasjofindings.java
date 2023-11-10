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

public class actsasjofindings extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static actsasjofindings mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.actsasjofindings");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actsasjofindings).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.actsasjofindings");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.actsasjofindings", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actsasjofindings) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actsasjofindings) Resume **");
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
		return actsasjofindings.class;
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
            BA.LogInfo("** Activity (actsasjofindings) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (actsasjofindings) Pause event (activity is not paused). **");
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
            actsasjofindings mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (actsasjofindings) Resume **");
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
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
public com.johan.Vibrate.Vibrate _vibration = null;
public static long[] _vibratepattern = null;
public de.amberhome.objects.SnackbarWrapper _snack = null;
public anywheresoftware.b4a.objects.CSBuilder _csans = null;
public b4a.example3.customlistview _clvjos = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblappnum = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcustaddress = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcustname = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstatus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbljonum = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnljoinfo = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdsearch = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtsearch = null;
public de.amberhome.objects.appcompat.ACPopupMenuWrapper _popsubmenu = null;
public static int _jocounts = 0;
public static int _limit = 0;
public anywheresoftware.b4a.objects.EditTextWrapper _txtremarks = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbljocat = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _cboacctclass = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _cbocontype = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _cbosubclass = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnsaveupdate = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlsignature = null;
public de.donmanfred.SignPad _signaturepad = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgsignature = null;
public static String _sigfoldername = "";
public static String _sigpicpath = "";
public static String _sigfilename = "";
public static String _rootdir = "";
public static boolean _hassign = false;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fontbold = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlconfirmsig = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btncancel = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnclear = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnok = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdconfirm = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdcancel = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdclear = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdok = null;
public Object _esig = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnconfirmsig = null;
public de.donmanfred.MultiSelectSpinnerWrapper _spnplumbers = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtdatetimestarted = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtdatetimefinished = null;
public static boolean _readstoragepermission = false;
public static boolean _writestoragepermission = false;
public static boolean _coarselocpermission = false;
public static boolean _finelocpermission = false;
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
public static class _josasdetails{
public boolean IsInitialized;
public int ID;
public String Num;
public String CatCode;
public int Status;
public int JOID;
public String AppNo;
public String CustName;
public String CustAdd;
public void Initialize() {
IsInitialized = true;
ID = 0;
Num = "";
CatCode = "";
Status = 0;
JOID = 0;
AppNo = "";
CustName = "";
CustAdd = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _accomplished_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 627;BA.debugLine="Private Sub Accomplished_OnNegativeClicked (View A";
 //BA.debugLineNum = 629;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 630;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 631;BA.debugLine="End Sub";
return "";
}
public static String  _accomplished_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 633;BA.debugLine="Private Sub Accomplished_OnPositiveClicked (View A";
 //BA.debugLineNum = 636;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 637;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 640;BA.debugLine="UpdateJO(GlobalVar.SelectedJOID)";
_updatejo(mostCurrent._globalvar._selectedjoid /*int*/ );
 //BA.debugLineNum = 641;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4a.object.XmlLayoutBuilder _xl = null;
 //BA.debugLineNum = 98;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 99;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 100;BA.debugLine="Activity.LoadLayout(\"SASJOFindings\")";
mostCurrent._activity.LoadLayout("SASJOFindings",mostCurrent.activityBA);
 //BA.debugLineNum = 102;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Append";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("Service Application Survey"))).PopAll();
 //BA.debugLineNum = 103;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append($";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Accomplishment Form"))).PopAll();
 //BA.debugLineNum = 105;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 106;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 107;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 109;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 110;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 111;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 112;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 113;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 114;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 116;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 117;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 119;BA.debugLine="cdSearch.Initialize(Colors.Transparent, 0)";
mostCurrent._cdsearch.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 120;BA.debugLine="txtRemarks.Background = cdSearch";
mostCurrent._txtremarks.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdsearch.getObject()));
 //BA.debugLineNum = 121;BA.debugLine="txtDateTimeStarted.Background = cdSearch";
mostCurrent._txtdatetimestarted.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdsearch.getObject()));
 //BA.debugLineNum = 122;BA.debugLine="txtDateTimeFinished.Background = cdSearch";
mostCurrent._txtdatetimefinished.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdsearch.getObject()));
 //BA.debugLineNum = 124;BA.debugLine="InpTyp.Initialize";
_inptyp._initialize /*String*/ (processBA);
 //BA.debugLineNum = 125;BA.debugLine="InpTyp.SetInputType(txtRemarks,Array As Int(InpTy";
_inptyp._setinputtype /*String*/ (mostCurrent._txtremarks,new int[]{_inptyp._type_class_text /*int*/ (),_inptyp._type_text_flag_auto_complete /*int*/ (),_inptyp._type_text_flag_cap_words /*int*/ ()});
 //BA.debugLineNum = 127;BA.debugLine="CD.Initialize2(GlobalVar.GreenColor, 30, 0, Color";
mostCurrent._cd.Initialize2((int) (mostCurrent._globalvar._greencolor /*double*/ ),(int) (30),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 128;BA.debugLine="btnSaveUpdate.Background = CD";
mostCurrent._btnsaveupdate.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cd.getObject()));
 //BA.debugLineNum = 129;BA.debugLine="btnSaveUpdate.Text = Chr(0xE161) & $\" ACCOMPLISH";
mostCurrent._btnsaveupdate.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe161)))+(" ACCOMPLISH JO")));
 //BA.debugLineNum = 131;BA.debugLine="cdCancel.Initialize2(GlobalVar.RedColor, 20, 0, C";
mostCurrent._cdcancel.Initialize2((int) (mostCurrent._globalvar._redcolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 132;BA.debugLine="btnCancel.Background = cdCancel";
mostCurrent._btncancel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdcancel.getObject()));
 //BA.debugLineNum = 133;BA.debugLine="btnCancel.Text = $\"CANCEL\"$";
mostCurrent._btncancel.setText(BA.ObjectToCharSequence(("CANCEL")));
 //BA.debugLineNum = 135;BA.debugLine="cdClear.Initialize2(GlobalVar.YellowColor, 20, 0,";
mostCurrent._cdclear.Initialize2((int) (mostCurrent._globalvar._yellowcolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 136;BA.debugLine="btnClear.Background = cdClear";
mostCurrent._btnclear.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdclear.getObject()));
 //BA.debugLineNum = 137;BA.debugLine="btnClear.Text = $\"RETRY\"$";
mostCurrent._btnclear.setText(BA.ObjectToCharSequence(("RETRY")));
 //BA.debugLineNum = 139;BA.debugLine="cdOK.Initialize2(GlobalVar.GreenColor, 20, 0, Col";
mostCurrent._cdok.Initialize2((int) (mostCurrent._globalvar._greencolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 140;BA.debugLine="btnOk.Background = cdOK";
mostCurrent._btnok.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 141;BA.debugLine="btnOk.Text = $\"SAVE\"$";
mostCurrent._btnok.setText(BA.ObjectToCharSequence(("SAVE")));
 //BA.debugLineNum = 143;BA.debugLine="cdConfirm.Initialize2(GlobalVar.GreenColor, 20, 0";
mostCurrent._cdconfirm.Initialize2((int) (mostCurrent._globalvar._greencolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 144;BA.debugLine="btnConfirmSig.Background = cdConfirm";
mostCurrent._btnconfirmsig.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdconfirm.getObject()));
 //BA.debugLineNum = 146;BA.debugLine="btnConfirmSig.Text = Chr(0xE5C8) & $\" CONFIRM SIG";
mostCurrent._btnconfirmsig.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe5c8)))+(" CONFIRM SIGNATURE")));
 //BA.debugLineNum = 148;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 149;BA.debugLine="HasSign = False";
_hassign = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 152;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 153;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
de.amberhome.objects.appcompat.ACMenuItemWrapper _item = null;
 //BA.debugLineNum = 219;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 220;BA.debugLine="Dim Item As ACMenuItem";
_item = new de.amberhome.objects.appcompat.ACMenuItemWrapper();
 //BA.debugLineNum = 221;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 222;BA.debugLine="Menu.Add2(1, 1, \"Cancel JO\",xmlIcon.GetDrawable(\"";
_menu.Add2((int) (1),(int) (1),BA.ObjectToCharSequence("Cancel JO"),mostCurrent._xmlicon.GetDrawable("baseline_delete_forever_white_24dp")).setShowAsAction(_item.SHOW_AS_ACTION_WITH_TEXT);
 //BA.debugLineNum = 223;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 193;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 194;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 195;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 196;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 198;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 200;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 211;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 212;BA.debugLine="CallSubDelayed(Starter,\"StopFLP\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._starter.getObject()),"StopFLP");
 //BA.debugLineNum = 213;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 167;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 168;BA.debugLine="If Result Then";
if (_result) { 
 //BA.debugLineNum = 169;BA.debugLine="If Permission = Starter.RTP.PERMISSION_READ_EXTE";
if ((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 170;BA.debugLine="LogColor($\"Permission to Read External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("857671683",("Permission to Read External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 171;BA.debugLine="ReadStoragePermission = True";
_readstoragepermission = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE)) { 
 //BA.debugLineNum = 173;BA.debugLine="LogColor($\"Permission to Write External Storage";
anywheresoftware.b4a.keywords.Common.LogImpl("857671686",("Permission to Write External Storage GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 174;BA.debugLine="WriteStoragePermission = True";
_writestoragepermission = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION)) { 
 //BA.debugLineNum = 176;BA.debugLine="LogColor($\"Permission to Access Coarse Location";
anywheresoftware.b4a.keywords.Common.LogImpl("857671689",("Permission to Access Coarse Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 177;BA.debugLine="CoarseLocPermission = True";
_coarselocpermission = anywheresoftware.b4a.keywords.Common.True;
 }else if((_permission).equals(mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION)) { 
 //BA.debugLineNum = 179;BA.debugLine="LogColor($\"Permission to Access Fine Location G";
anywheresoftware.b4a.keywords.Common.LogImpl("857671692",("Permission to Access Fine Location GRANTED"),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 180;BA.debugLine="FineLocPermission = True";
_finelocpermission = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 182;BA.debugLine="Starter.StartFLP";
mostCurrent._starter._startflp /*void*/ ();
 }else {
 //BA.debugLineNum = 184;BA.debugLine="ReadStoragePermission = False";
_readstoragepermission = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 185;BA.debugLine="WriteStoragePermission = False";
_writestoragepermission = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 186;BA.debugLine="CoarseLocPermission = False";
_coarselocpermission = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 187;BA.debugLine="FineLocPermission = False";
_finelocpermission = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 188;BA.debugLine="Result = False";
_result = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 190;BA.debugLine="Log (Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("857671703",_permission,0);
 //BA.debugLineNum = 191;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 202;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 203;BA.debugLine="ClearUI";
_clearui();
 //BA.debugLineNum = 204;BA.debugLine="FillJORecord(GlobalVar.SelectedJOID)";
_filljorecord(mostCurrent._globalvar._selectedjoid /*int*/ );
 //BA.debugLineNum = 205;BA.debugLine="FillCombos";
_fillcombos();
 //BA.debugLineNum = 206;BA.debugLine="HasSign = False";
_hassign = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 207;BA.debugLine="FillJORecord(GlobalVar.SelectedJOID)";
_filljorecord(mostCurrent._globalvar._selectedjoid /*int*/ );
 //BA.debugLineNum = 208;BA.debugLine="CheckPermissions";
_checkpermissions();
 //BA.debugLineNum = 209;BA.debugLine="End Sub";
return "";
}
public static String  _addplumbers() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsplumbers = null;
String _empname = "";
String[] _empnamelist = null;
int _pcount = 0;
int _i = 0;
 //BA.debugLineNum = 527;BA.debugLine="Private Sub AddPlumbers";
 //BA.debugLineNum = 528;BA.debugLine="Dim rsPlumbers As Cursor";
_rsplumbers = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 530;BA.debugLine="Dim EmpName As String";
_empname = "";
 //BA.debugLineNum = 531;BA.debugLine="Dim EmpNameList() As String";
_empnamelist = new String[(int) (0)];
java.util.Arrays.fill(_empnamelist,"");
 //BA.debugLineNum = 532;BA.debugLine="Dim pCount As Int";
_pcount = 0;
 //BA.debugLineNum = 534;BA.debugLine="Try";
try { //BA.debugLineNum = 535;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblPlumbers";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblPlumbers "+"ORDER BY id ASC";
 //BA.debugLineNum = 538;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("859244555",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 540;BA.debugLine="rsPlumbers =  Starter.DBCon.ExecQuery (Starter.s";
_rsplumbers = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 541;BA.debugLine="If rsPlumbers.RowCount > 0 Then";
if (_rsplumbers.getRowCount()>0) { 
 //BA.debugLineNum = 542;BA.debugLine="pCount = rsPlumbers.RowCount";
_pcount = _rsplumbers.getRowCount();
 //BA.debugLineNum = 543;BA.debugLine="Dim EmpNameList(pCount) As String";
_empnamelist = new String[_pcount];
java.util.Arrays.fill(_empnamelist,"");
 //BA.debugLineNum = 545;BA.debugLine="For i = 0 To rsPlumbers.RowCount - 1";
{
final int step12 = 1;
final int limit12 = (int) (_rsplumbers.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit12 ;_i = _i + step12 ) {
 //BA.debugLineNum = 546;BA.debugLine="rsPlumbers.Position = i";
_rsplumbers.setPosition(_i);
 //BA.debugLineNum = 547;BA.debugLine="EmpName = rsPlumbers.GetString(\"EmpName\")";
_empname = _rsplumbers.GetString("EmpName");
 //BA.debugLineNum = 548;BA.debugLine="EmpNameList(i) = EmpName";
_empnamelist[_i] = _empname;
 }
};
 }else {
 //BA.debugLineNum = 551;BA.debugLine="snack.Initialize(\"\", Activity, \"No plumber(s) f";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),"No plumber(s) found!",mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 552;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Colors";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 553;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 554;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 555;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e25) {
			processBA.setLastException(e25); //BA.debugLineNum = 558;BA.debugLine="snack.Initialize(\"\", Activity, LastException,sna";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 559;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Colors.";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 560;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 561;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 562;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 565;BA.debugLine="spnPlumbers.Items = EmpNameList";
mostCurrent._spnplumbers.setItems(_empnamelist);
 //BA.debugLineNum = 566;BA.debugLine="End Sub";
return "";
}
public static String  _btncancel_click() throws Exception{
 //BA.debugLineNum = 480;BA.debugLine="Sub btnCancel_Click";
 //BA.debugLineNum = 481;BA.debugLine="pnlSignature.Visible = False";
mostCurrent._pnlsignature.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 482;BA.debugLine="pnlConfirmSig.Visible = False";
mostCurrent._pnlconfirmsig.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 483;BA.debugLine="End Sub";
return "";
}
public static String  _btnclear_click() throws Exception{
 //BA.debugLineNum = 467;BA.debugLine="Sub btnClear_Click";
 //BA.debugLineNum = 468;BA.debugLine="pnlSignature.Visible = True";
mostCurrent._pnlsignature.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 469;BA.debugLine="pnlConfirmSig.Visible = False";
mostCurrent._pnlconfirmsig.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 470;BA.debugLine="SignaturePad.clear";
mostCurrent._signaturepad.clear();
 //BA.debugLineNum = 471;BA.debugLine="HasSign = False";
_hassign = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 472;BA.debugLine="SignaturePad.Visible = True";
mostCurrent._signaturepad.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 473;BA.debugLine="SignaturePad.StrokeWidth = 15";
mostCurrent._signaturepad.setStrokeWidth((float) (15));
 //BA.debugLineNum = 474;BA.debugLine="imgSignature.Bitmap = Null";
mostCurrent._imgsignature.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 475;BA.debugLine="SignaturePad.Capture(True)";
mostCurrent._signaturepad.Capture(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 477;BA.debugLine="LogColor(spnPlumbers.SelectedItemsString, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("858785802",mostCurrent._spnplumbers.getSelectedItemsString(),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 478;BA.debugLine="End Sub";
return "";
}
public static String  _btnconfirmsig_click() throws Exception{
long _lngdatetime = 0L;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
 //BA.debugLineNum = 499;BA.debugLine="Sub btnConfirmSig_Click";
 //BA.debugLineNum = 501;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 502;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 504;BA.debugLine="If HasSign = False Then";
if (_hassign==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 505;BA.debugLine="RequiredMsgBox(Chr(0xE002) & $\" ERROR\"$,$\"Custom";
_requiredmsgbox(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe002)))+(" ERROR"),("Customer Signature is required."));
 //BA.debugLineNum = 506;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 509;BA.debugLine="Log(\"SignaturePad_onSigned(sign)\")";
anywheresoftware.b4a.keywords.Common.LogImpl("859113482","SignaturePad_onSigned(sign)",0);
 //BA.debugLineNum = 511;BA.debugLine="pnlConfirmSig.Visible = True";
mostCurrent._pnlconfirmsig.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 512;BA.debugLine="pnlSignature.Visible = False";
mostCurrent._pnlsignature.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 514;BA.debugLine="Dim bmp As Bitmap = eSig";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp = (anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(mostCurrent._esig));
 //BA.debugLineNum = 515;BA.debugLine="imgSignature.Bitmap = bmp";
mostCurrent._imgsignature.setBitmap((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 516;BA.debugLine="spnPlumbers.Items = Array As String(\"\")";
mostCurrent._spnplumbers.setItems(new String[]{""});
 //BA.debugLineNum = 517;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 518;BA.debugLine="txtDateTimeFinished.Text = DateTime.Date(lngDateT";
mostCurrent._txtdatetimefinished.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime)));
 //BA.debugLineNum = 520;BA.debugLine="AddPlumbers";
_addplumbers();
 //BA.debugLineNum = 521;BA.debugLine="End Sub";
return "";
}
public static String  _btnok_click() throws Exception{
 //BA.debugLineNum = 445;BA.debugLine="Sub btnOk_Click";
 //BA.debugLineNum = 446;BA.debugLine="If spnPlumbers.SelectedItemsString = \"\" Then";
if ((mostCurrent._spnplumbers.getSelectedItemsString()).equals("")) { 
 //BA.debugLineNum = 447;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Unable to Accomplshe";
_requiredmsgbox(("ERROR"),("Unable to Accomplshed JO due to No selected plumber(s)."));
 //BA.debugLineNum = 448;BA.debugLine="spnPlumbers.RequestFocus";
mostCurrent._spnplumbers.RequestFocus();
 //BA.debugLineNum = 449;BA.debugLine="Log(\"No Selected\")";
anywheresoftware.b4a.keywords.Common.LogImpl("858720260","No Selected",0);
 //BA.debugLineNum = 450;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 453;BA.debugLine="If Not(MiscFunctions.IsValidDate(txtDateTimeStart";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._miscfunctions._isvaliddate /*boolean*/ (mostCurrent.activityBA,mostCurrent._txtdatetimestarted.getText())) || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtdatetimestarted.getText()))==0) { 
 //BA.debugLineNum = 454;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid date Started";
_requiredmsgbox(("ERROR"),("Invalid date Started!"));
 //BA.debugLineNum = 455;BA.debugLine="txtDateTimeStarted.RequestFocus";
mostCurrent._txtdatetimestarted.RequestFocus();
 //BA.debugLineNum = 456;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 459;BA.debugLine="If Not(MiscFunctions.IsValidDate(txtDateTimeFinis";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._miscfunctions._isvaliddate /*boolean*/ (mostCurrent.activityBA,mostCurrent._txtdatetimefinished.getText())) || mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(mostCurrent._txtdatetimefinished.getText()))==0) { 
 //BA.debugLineNum = 460;BA.debugLine="RequiredMsgBox($\"ERROR\"$, $\"Invalid date finishe";
_requiredmsgbox(("ERROR"),("Invalid date finished!"));
 //BA.debugLineNum = 461;BA.debugLine="txtDateTimeFinished.RequestFocus";
mostCurrent._txtdatetimefinished.RequestFocus();
 //BA.debugLineNum = 462;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 464;BA.debugLine="ConfirmJOAccomplishment";
_confirmjoaccomplishment();
 //BA.debugLineNum = 465;BA.debugLine="End Sub";
return "";
}
public static String  _btnsaveupdate_click() throws Exception{
anywheresoftware.b4a.objects.drawable.ColorDrawable _cdsig = null;
 //BA.debugLineNum = 320;BA.debugLine="Sub btnSaveUpdate_Click";
 //BA.debugLineNum = 321;BA.debugLine="Dim cdSig As ColorDrawable";
_cdsig = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 322;BA.debugLine="If Not(ValidateEntries) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_validateentries())) { 
 //BA.debugLineNum = 323;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 326;BA.debugLine="pnlSignature.Visible = True";
mostCurrent._pnlsignature.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 327;BA.debugLine="cdSig.Initialize2(0xFFD3D3D3,0,0,Colors.Transpare";
_cdsig.Initialize2((int) (0xffd3d3d3),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 329;BA.debugLine="SignaturePad.Background = cdSig";
mostCurrent._signaturepad.setBackground((android.graphics.drawable.Drawable)(_cdsig.getObject()));
 //BA.debugLineNum = 330;BA.debugLine="SignaturePad.clear";
mostCurrent._signaturepad.clear();
 //BA.debugLineNum = 331;BA.debugLine="SignaturePad.StrokeWidth = 15";
mostCurrent._signaturepad.setStrokeWidth((float) (15));
 //BA.debugLineNum = 332;BA.debugLine="SignaturePad.Visible = True";
mostCurrent._signaturepad.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 333;BA.debugLine="imgSignature.Bitmap = Null";
mostCurrent._imgsignature.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 334;BA.debugLine="SignaturePad.Capture(True)";
mostCurrent._signaturepad.Capture(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 335;BA.debugLine="HasSign = False";
_hassign = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 336;BA.debugLine="End Sub";
return "";
}
public static String  _cboacctclass_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 291;BA.debugLine="Sub cboAcctClass_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 293;BA.debugLine="End Sub";
return "";
}
public static String  _cbocontype_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 299;BA.debugLine="Sub cboConType_ItemClick (Position As Int, Value A";
 //BA.debugLineNum = 301;BA.debugLine="End Sub";
return "";
}
public static String  _cbosubclass_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 295;BA.debugLine="Sub cboSubClass_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 297;BA.debugLine="End Sub";
return "";
}
public static String  _checkpermissions() throws Exception{
 //BA.debugLineNum = 155;BA.debugLine="Private Sub CheckPermissions";
 //BA.debugLineNum = 156;BA.debugLine="Log(\"Checking Permissions\")";
anywheresoftware.b4a.keywords.Common.LogImpl("857606145","Checking Permissions",0);
 //BA.debugLineNum = 158;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_READ_EXTERNAL_STORAGE);
 //BA.debugLineNum = 159;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_WRITE_EXTERNAL_STORAGE);
 //BA.debugLineNum = 160;BA.debugLine="Starter.RTP.GetAllSafeDirsExternal(\"\")";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .GetAllSafeDirsExternal("");
 //BA.debugLineNum = 162;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_COARSE_LOCATION);
 //BA.debugLineNum = 163;BA.debugLine="Starter.RTP.CheckAndRequest(Starter.RTP.PERMISSIO";
mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .CheckAndRequest(processBA,mostCurrent._starter._rtp /*anywheresoftware.b4a.objects.RuntimePermissions*/ .PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 164;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 165;BA.debugLine="End Sub";
return "";
}
public static String  _clearui() throws Exception{
 //BA.debugLineNum = 303;BA.debugLine="Private Sub ClearUI";
 //BA.debugLineNum = 304;BA.debugLine="If txtRemarks.IsInitialized = False Then";
if (mostCurrent._txtremarks.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 305;BA.debugLine="txtRemarks.Initialize(\"txtRemarks\")";
mostCurrent._txtremarks.Initialize(mostCurrent.activityBA,"txtRemarks");
 };
 //BA.debugLineNum = 307;BA.debugLine="lblJONum.Text = \"\"";
mostCurrent._lbljonum.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 308;BA.debugLine="lblJOCat.Text = \"\"";
mostCurrent._lbljocat.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 309;BA.debugLine="lblCustName.Text = \"\"";
mostCurrent._lblcustname.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 310;BA.debugLine="lblCustAddress.Text = \"\"";
mostCurrent._lblcustaddress.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 311;BA.debugLine="lblAppNum.Text = \"\"";
mostCurrent._lblappnum.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 312;BA.debugLine="txtRemarks.Text = \"\"";
mostCurrent._txtremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 313;BA.debugLine="cboAcctClass.Clear";
mostCurrent._cboacctclass.Clear();
 //BA.debugLineNum = 314;BA.debugLine="cboSubClass.Clear";
mostCurrent._cbosubclass.Clear();
 //BA.debugLineNum = 315;BA.debugLine="cboConType.Clear";
mostCurrent._cbocontype.Clear();
 //BA.debugLineNum = 316;BA.debugLine="pnlSignature.Visible = False";
mostCurrent._pnlsignature.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 317;BA.debugLine="pnlConfirmSig.Visible = False";
mostCurrent._pnlconfirmsig.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 318;BA.debugLine="End Sub";
return "";
}
public static String  _confirmjoaccomplishment() throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 601;BA.debugLine="Private Sub ConfirmJOAccomplishment";
 //BA.debugLineNum = 602;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 604;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle("JO Accomplishment").SetTitleColor((int) (mostCurrent._globalvar._bluecolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage("Do you want to Accomplish this JO Now?").SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"Accomplished").SetOnNegativeClicked(mostCurrent.activityBA,"Accomplished");
 //BA.debugLineNum = 622;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 623;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 624;BA.debugLine="End Sub";
return "";
}
public static String  _dispinfomsg(String _stitle,String _smsg) throws Exception{
 //BA.debugLineNum = 664;BA.debugLine="Private Sub DispInfoMsg(sTitle As String, sMsg As";
 //BA.debugLineNum = 665;BA.debugLine="MatDialogBuilder.Initialize(\"DispInformationMsg\")";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"DispInformationMsg");
 //BA.debugLineNum = 666;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 667;BA.debugLine="MatDialogBuilder.Title(sTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_stitle));
 //BA.debugLineNum = 668;BA.debugLine="MatDialogBuilder.Content(sMsg)";
mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(_smsg));
 //BA.debugLineNum = 669;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 670;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 671;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 672;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 673;BA.debugLine="End Sub";
return "";
}
public static String  _dispinformationmsg_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 675;BA.debugLine="Private Sub DispInformationMsg_ButtonPressed(mDial";
 //BA.debugLineNum = 676;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE)) {
case 0: {
 //BA.debugLineNum = 678;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 681;BA.debugLine="End Sub";
return "";
}
public static String  _fillcombos() throws Exception{
 //BA.debugLineNum = 270;BA.debugLine="Private Sub FillCombos";
 //BA.debugLineNum = 271;BA.debugLine="cboAcctClass.Clear";
mostCurrent._cboacctclass.Clear();
 //BA.debugLineNum = 272;BA.debugLine="cboAcctClass.Add($\"RES\"$)";
mostCurrent._cboacctclass.Add(BA.ObjectToCharSequence(("RES")));
 //BA.debugLineNum = 273;BA.debugLine="cboAcctClass.Add($\"COM\"$)";
mostCurrent._cboacctclass.Add(BA.ObjectToCharSequence(("COM")));
 //BA.debugLineNum = 274;BA.debugLine="cboAcctClass.Add($\"IND\"$)";
mostCurrent._cboacctclass.Add(BA.ObjectToCharSequence(("IND")));
 //BA.debugLineNum = 275;BA.debugLine="cboAcctClass.Add($\"GOV\"$)";
mostCurrent._cboacctclass.Add(BA.ObjectToCharSequence(("GOV")));
 //BA.debugLineNum = 276;BA.debugLine="cboAcctClass.Add($\"REL\"$)";
mostCurrent._cboacctclass.Add(BA.ObjectToCharSequence(("REL")));
 //BA.debugLineNum = 278;BA.debugLine="cboSubClass.Clear";
mostCurrent._cbosubclass.Clear();
 //BA.debugLineNum = 279;BA.debugLine="cboSubClass.Add($\"A\"$)";
mostCurrent._cbosubclass.Add(BA.ObjectToCharSequence(("A")));
 //BA.debugLineNum = 280;BA.debugLine="cboSubClass.Add($\"B\"$)";
mostCurrent._cbosubclass.Add(BA.ObjectToCharSequence(("B")));
 //BA.debugLineNum = 281;BA.debugLine="cboSubClass.Add($\"C\"$)";
mostCurrent._cbosubclass.Add(BA.ObjectToCharSequence(("C")));
 //BA.debugLineNum = 283;BA.debugLine="cboConType.Clear";
mostCurrent._cbocontype.Clear();
 //BA.debugLineNum = 284;BA.debugLine="cboConType.Add($\"Plug Connection\"$)";
mostCurrent._cbocontype.Add(BA.ObjectToCharSequence(("Plug Connection")));
 //BA.debugLineNum = 285;BA.debugLine="cboConType.Add($\"Tapping Connection\"$)";
mostCurrent._cbocontype.Add(BA.ObjectToCharSequence(("Tapping Connection")));
 //BA.debugLineNum = 286;BA.debugLine="cboConType.Add($\"Across Soil\"$)";
mostCurrent._cbocontype.Add(BA.ObjectToCharSequence(("Across Soil")));
 //BA.debugLineNum = 287;BA.debugLine="cboConType.Add($\"Across Pavement\"$)";
mostCurrent._cbocontype.Add(BA.ObjectToCharSequence(("Across Pavement")));
 //BA.debugLineNum = 288;BA.debugLine="End Sub";
return "";
}
public static String  _filljorecord(int _ijoid) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsjosasdetails = null;
 //BA.debugLineNum = 237;BA.debugLine="Private Sub FillJORecord (iJOID As Int)";
 //BA.debugLineNum = 238;BA.debugLine="Dim RSJOSASDetails As Cursor";
_rsjosasdetails = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 240;BA.debugLine="Try";
try { //BA.debugLineNum = 241;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblJOs \" &";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblJOs "+"WHERE JOID = "+BA.NumberToString(_ijoid);
 //BA.debugLineNum = 243;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("858130438",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 245;BA.debugLine="RSJOSASDetails = Starter.DBCon.ExecQuery(Starter";
_rsjosasdetails = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 247;BA.debugLine="If RSJOSASDetails.RowCount > 0 Then";
if (_rsjosasdetails.getRowCount()>0) { 
 //BA.debugLineNum = 248;BA.debugLine="RSJOSASDetails.Position = 0";
_rsjosasdetails.setPosition((int) (0));
 //BA.debugLineNum = 249;BA.debugLine="lblJONum.Text = RSJOSASDetails.GetString(\"JONo\"";
mostCurrent._lbljonum.setText(BA.ObjectToCharSequence(_rsjosasdetails.GetString("JONo")));
 //BA.debugLineNum = 250;BA.debugLine="lblAppNum.Text = RSJOSASDetails.GetString(\"RefN";
mostCurrent._lblappnum.setText(BA.ObjectToCharSequence(_rsjosasdetails.GetString("RefNo")));
 //BA.debugLineNum = 251;BA.debugLine="lblJOCat.Text = GlobalVar.SF.Upper(RSJOSASDetai";
mostCurrent._lbljocat.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rsjosasdetails.GetString("JoDesc"))));
 //BA.debugLineNum = 252;BA.debugLine="lblCustName.Text = GlobalVar.SF.Upper(RSJOSASDe";
mostCurrent._lblcustname.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rsjosasdetails.GetString("CustName"))));
 //BA.debugLineNum = 253;BA.debugLine="lblCustAddress.Text = GlobalVar.SF.Upper(RSJOSA";
mostCurrent._lblcustaddress.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rsjosasdetails.GetString("CustAddress"))));
 //BA.debugLineNum = 254;BA.debugLine="txtDateTimeStarted.Text = RSJOSASDetails.GetStr";
mostCurrent._txtdatetimestarted.setText(BA.ObjectToCharSequence(_rsjosasdetails.GetString("DateStarted")));
 }else {
 //BA.debugLineNum = 256;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 257;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 258;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 259;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 260;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("858130455",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 261;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e23) {
			processBA.setLastException(e23); //BA.debugLineNum = 265;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("858130460",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 267;BA.debugLine="RSJOSASDetails.Close";
_rsjosasdetails.Close();
 //BA.debugLineNum = 268;BA.debugLine="End Sub";
return "";
}
public static String  _fontsizebinder_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 643;BA.debugLine="Private Sub FontSizeBinder_OnBindView (View As Vie";
 //BA.debugLineNum = 644;BA.debugLine="Dim alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 645;BA.debugLine="alert.Initialize";
_alert.Initialize();
 //BA.debugLineNum = 646;BA.debugLine="If ViewType = alert.VIEW_TITLE Then ' Title";
if (_viewtype==_alert.VIEW_TITLE) { 
 //BA.debugLineNum = 647;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 648;BA.debugLine="lbl.TextSize = 30";
_lbl.setTextSize((float) (30));
 //BA.debugLineNum = 649;BA.debugLine="lbl.SetTextColorAnimated(2000,Colors.Magenta)";
_lbl.SetTextColorAnimated((int) (2000),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 652;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 656;BA.debugLine="CS.Initialize.Typeface(Typeface.MATERIALICONS).S";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe88e)))+"  "));
 //BA.debugLineNum = 657;BA.debugLine="CS.Typeface(Font).Size(24).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(mostCurrent._font.getObject())).Size((int) (24)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 659;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 661;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 25;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 26;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private xmlIcon As XmlLayoutBuilder";
mostCurrent._xmlicon = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 28;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private CD As ColorDrawable";
mostCurrent._cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 30;BA.debugLine="Private vibration As B4Avibrate";
mostCurrent._vibration = new com.johan.Vibrate.Vibrate();
 //BA.debugLineNum = 31;BA.debugLine="Private vibratePattern() As Long";
_vibratepattern = new long[(int) (0)];
;
 //BA.debugLineNum = 33;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private csAns As CSBuilder";
mostCurrent._csans = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 36;BA.debugLine="Private clvJOs As CustomListView";
mostCurrent._clvjos = new b4a.example3.customlistview();
 //BA.debugLineNum = 37;BA.debugLine="Private lblAppNum As Label";
mostCurrent._lblappnum = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private lblCustAddress As Label";
mostCurrent._lblcustaddress = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private lblCustName As Label";
mostCurrent._lblcustname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private lblStatus As Label";
mostCurrent._lblstatus = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private lblJONum As Label";
mostCurrent._lbljonum = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private pnlJOInfo As Panel";
mostCurrent._pnljoinfo = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Dim cdSearch As ColorDrawable";
mostCurrent._cdsearch = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 45;BA.debugLine="Private txtSearch As EditText";
mostCurrent._txtsearch = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private PopSubMenu As ACPopupMenu";
mostCurrent._popsubmenu = new de.amberhome.objects.appcompat.ACPopupMenuWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private JOCounts As Int";
_jocounts = 0;
 //BA.debugLineNum = 48;BA.debugLine="Private Limit As Int = 2000";
_limit = (int) (2000);
 //BA.debugLineNum = 50;BA.debugLine="Private txtRemarks As EditText";
mostCurrent._txtremarks = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Type JOSASDetails(ID As Int, Num As String, CatCo";
;
 //BA.debugLineNum = 55;BA.debugLine="Private lblJOCat As Label";
mostCurrent._lbljocat = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private cboAcctClass As ACSpinner";
mostCurrent._cboacctclass = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private cboConType As ACSpinner";
mostCurrent._cbocontype = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private cboSubClass As ACSpinner";
mostCurrent._cbosubclass = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Private btnSaveUpdate As ACButton";
mostCurrent._btnsaveupdate = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private pnlSignature As Panel";
mostCurrent._pnlsignature = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Private SignaturePad As SignPad";
mostCurrent._signaturepad = new de.donmanfred.SignPad();
 //BA.debugLineNum = 62;BA.debugLine="Private imgSignature As ImageView";
mostCurrent._imgsignature = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Private SigFolderName As String";
mostCurrent._sigfoldername = "";
 //BA.debugLineNum = 65;BA.debugLine="Private SigPicPath As String";
mostCurrent._sigpicpath = "";
 //BA.debugLineNum = 66;BA.debugLine="Private SigFilename As String";
mostCurrent._sigfilename = "";
 //BA.debugLineNum = 67;BA.debugLine="Private RootDir As String = File.DirRootExternal";
mostCurrent._rootdir = anywheresoftware.b4a.keywords.Common.File.getDirRootExternal();
 //BA.debugLineNum = 68;BA.debugLine="Private HasSign As Boolean";
_hassign = false;
 //BA.debugLineNum = 70;BA.debugLine="Private Font As Typeface = Typeface.LoadFromAsset";
mostCurrent._font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont.ttf")));
 //BA.debugLineNum = 71;BA.debugLine="Private FontBold As Typeface = Typeface.LoadFromA";
mostCurrent._fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont_bold.ttf")));
 //BA.debugLineNum = 73;BA.debugLine="Private pnlConfirmSig As Panel";
mostCurrent._pnlconfirmsig = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 74;BA.debugLine="Private btnCancel As ACButton";
mostCurrent._btncancel = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 75;BA.debugLine="Private btnClear As ACButton";
mostCurrent._btnclear = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 76;BA.debugLine="Private btnOk As ACButton";
mostCurrent._btnok = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 78;BA.debugLine="Private cdConfirm, cdCancel, cdClear, cdOK As Col";
mostCurrent._cdconfirm = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdcancel = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdclear = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdok = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 79;BA.debugLine="Private eSig As Object";
mostCurrent._esig = new Object();
 //BA.debugLineNum = 80;BA.debugLine="Private btnConfirmSig As ACButton";
mostCurrent._btnconfirmsig = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 81;BA.debugLine="Private spnPlumbers As MultiSelectSpinner";
mostCurrent._spnplumbers = new de.donmanfred.MultiSelectSpinnerWrapper();
 //BA.debugLineNum = 82;BA.debugLine="Private txtDateTimeStarted As EditText";
mostCurrent._txtdatetimestarted = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 83;BA.debugLine="Private txtDateTimeFinished As EditText";
mostCurrent._txtdatetimefinished = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 90;BA.debugLine="Private ReadStoragePermission As Boolean";
_readstoragepermission = false;
 //BA.debugLineNum = 91;BA.debugLine="Private WriteStoragePermission As Boolean";
_writestoragepermission = false;
 //BA.debugLineNum = 92;BA.debugLine="Private CoarseLocPermission As Boolean";
_coarselocpermission = false;
 //BA.debugLineNum = 93;BA.debugLine="Private FineLocPermission As Boolean";
_finelocpermission = false;
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return "";
}
public static String  _pnlconfirmsig_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 495;BA.debugLine="Sub pnlConfirmSig_Touch (Action As Int, X As Float";
 //BA.debugLineNum = 497;BA.debugLine="End Sub";
return "";
}
public static String  _pnlsignature_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 491;BA.debugLine="Sub pnlSignature_Touch (Action As Int, X As Float,";
 //BA.debugLineNum = 493;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 596;BA.debugLine="Private Sub RequiredMsg_OnPositiveClicked (View As";
 //BA.debugLineNum = 597;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 598;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 599;BA.debugLine="End Sub";
return "";
}
public static String  _requiredmsgbox(String _stitle,String _smsg) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 571;BA.debugLine="Private Sub RequiredMsgBox(sTitle As String, sMsg";
 //BA.debugLineNum = 572;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 573;BA.debugLine="Alert.Initialize.Dismiss2";
_alert.Initialize().Dismiss2();
 //BA.debugLineNum = 575;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetTitleColor((int) (mostCurrent._globalvar._redcolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage(_smsg).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"RequiredMsg").SetOnViewBinder(mostCurrent.activityBA,"FontSizeBinder");
 //BA.debugLineNum = 590;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 591;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 592;BA.debugLine="End Sub";
return "";
}
public static String  _signaturepad_onsigned(Object _sign) throws Exception{
 //BA.debugLineNum = 485;BA.debugLine="Sub SignaturePad_onSigned(sign As Object)";
 //BA.debugLineNum = 487;BA.debugLine="eSig = sign";
mostCurrent._esig = _sign;
 //BA.debugLineNum = 488;BA.debugLine="HasSign = True";
_hassign = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 489;BA.debugLine="End Sub";
return "";
}
public static String  _spnplumbers_onitemselected(int _position,boolean _ischecked,String _item) throws Exception{
 //BA.debugLineNum = 523;BA.debugLine="Sub spnPlumbers_onItemSelected(position As Int, is";
 //BA.debugLineNum = 525;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 229;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)";
 //BA.debugLineNum = 231;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 225;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 226;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 227;BA.debugLine="End Sub";
return "";
}
public static String  _updatejo(int _ijoid) throws Exception{
String _sremarks = "";
String _sacctclass = "";
String _ssubclass = "";
String _scontype = "";
int _icontype = 0;
long _lngdatetime = 0L;
String _sdateposted = "";
String _saccomplishedby = "";
String _slocation = "";
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
anywheresoftware.b4a.phone.Phone _phone = null;
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 360;BA.debugLine="Private Sub UpdateJO (iJOID As Int)";
 //BA.debugLineNum = 361;BA.debugLine="Dim sRemarks As String";
_sremarks = "";
 //BA.debugLineNum = 362;BA.debugLine="Dim sAcctClass As String";
_sacctclass = "";
 //BA.debugLineNum = 363;BA.debugLine="Dim sSubClass As String";
_ssubclass = "";
 //BA.debugLineNum = 364;BA.debugLine="Dim sConType As String";
_scontype = "";
 //BA.debugLineNum = 365;BA.debugLine="Dim iConType As Int";
_icontype = 0;
 //BA.debugLineNum = 367;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 368;BA.debugLine="Dim sDatePosted As String";
_sdateposted = "";
 //BA.debugLineNum = 369;BA.debugLine="Dim sAccomplishedBy As String";
_saccomplishedby = "";
 //BA.debugLineNum = 370;BA.debugLine="Dim sLocation As String";
_slocation = "";
 //BA.debugLineNum = 372;BA.debugLine="sAcctClass = cboAcctClass.SelectedItem";
_sacctclass = mostCurrent._cboacctclass.getSelectedItem();
 //BA.debugLineNum = 373;BA.debugLine="sSubClass = cboSubClass.SelectedItem";
_ssubclass = mostCurrent._cbosubclass.getSelectedItem();
 //BA.debugLineNum = 374;BA.debugLine="sConType = cboConType.SelectedItem";
_scontype = mostCurrent._cbocontype.getSelectedItem();
 //BA.debugLineNum = 375;BA.debugLine="iConType = DBaseFunctions.GetIDByCode(\"id\", \"cons";
_icontype = mostCurrent._dbasefunctions._getidbycode /*int*/ (mostCurrent.activityBA,"id","constant_con_types","ConTypeDesc",_scontype);
 //BA.debugLineNum = 377;BA.debugLine="sRemarks = txtRemarks.Text";
_sremarks = mostCurrent._txtremarks.getText();
 //BA.debugLineNum = 378;BA.debugLine="Starter.FLP.Connect";
mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .Connect();
 //BA.debugLineNum = 380;BA.debugLine="Log($\"FLP is COnnected? \"$ & Starter.FLP.IsConnec";
anywheresoftware.b4a.keywords.Common.LogImpl("858654740",("FLP is COnnected? ")+BA.ObjectToString(mostCurrent._starter._flp /*uk.co.martinpearman.b4a.fusedlocationprovider.FusedLocationProviderWrapper*/ .IsConnected()),0);
 //BA.debugLineNum = 382;BA.debugLine="LogColor($\"Latitude: \"$ & GlobalVar.Lat, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("858654742",("Latitude: ")+mostCurrent._globalvar._lat /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 383;BA.debugLine="LogColor($\"Longitude: \"$ & GlobalVar.Lon, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("858654743",("Longitude: ")+mostCurrent._globalvar._lon /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 385;BA.debugLine="sLocation = GlobalVar.Lat & \",\" & GlobalVar.Lon";
_slocation = mostCurrent._globalvar._lat /*String*/ +","+mostCurrent._globalvar._lon /*String*/ ;
 //BA.debugLineNum = 386;BA.debugLine="LogColor($\"Location is \"$ & sLocation, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("858654746",("Location is ")+_slocation,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 388;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 389;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd hh:mm:ss a");
 //BA.debugLineNum = 390;BA.debugLine="sDatePosted = DateTime.Date(lngDateTime)";
_sdateposted = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime);
 //BA.debugLineNum = 391;BA.debugLine="sAccomplishedBy = DBaseFunctions.GetPlumberIDs(sp";
_saccomplishedby = mostCurrent._dbasefunctions._getplumberids /*String*/ (mostCurrent.activityBA,mostCurrent._spnplumbers.getSelectedItemsString());
 //BA.debugLineNum = 393;BA.debugLine="If WriteStoragePermission = False Then";
if (_writestoragepermission==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 394;BA.debugLine="ToastMessageShow ($\"Unable to Save Signature Ima";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to Save Signature Image due to permission to write was denied")),anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 396;BA.debugLine="SigPicPath = File.Combine(RootDir, \"DCIM\")";
mostCurrent._sigpicpath = anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._rootdir,"DCIM");
 //BA.debugLineNum = 397;BA.debugLine="SigFolderName = File.Combine(SigPicPath, \"BWSI-O";
mostCurrent._sigfoldername = anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._sigpicpath,"BWSI-OP");
 //BA.debugLineNum = 398;BA.debugLine="If File.Exists(SigFolderName, \"\") = False Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._sigfoldername,"")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 399;BA.debugLine="File.MakeDir(SigPicPath, \"BWSI-OP\")";
anywheresoftware.b4a.keywords.Common.File.MakeDir(mostCurrent._sigpicpath,"BWSI-OP");
 //BA.debugLineNum = 400;BA.debugLine="SigFolderName = File.Combine(SigPicPath, \"BWSI-";
mostCurrent._sigfoldername = anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._sigpicpath,"BWSI-OP");
 };
 };
 //BA.debugLineNum = 404;BA.debugLine="SigFilename = GlobalVar.SelectedJOCatCode & \" -\"&";
mostCurrent._sigfilename = mostCurrent._globalvar._selectedjocatcode /*String*/ +" -"+mostCurrent._lbljonum.getText()+"-"+mostCurrent._lblappnum.getText()+".jpg";
 //BA.debugLineNum = 405;BA.debugLine="Dim bmp As Bitmap = SignaturePad.TransparentSigna";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp = (anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(mostCurrent._signaturepad.getTransparentSignatureBitmap()));
 //BA.debugLineNum = 406;BA.debugLine="imgSignature.Bitmap = bmp";
mostCurrent._imgsignature.setBitmap((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 409;BA.debugLine="SignaturePad.saveBitmapToJPG(bmp,File.Combine(Sig";
mostCurrent._signaturepad.saveBitmapToJPG((android.graphics.Bitmap)(_bmp.getObject()),anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._sigfoldername,mostCurrent._sigfilename));
 //BA.debugLineNum = 410;BA.debugLine="Log(SigFilename & \" Saved\")";
anywheresoftware.b4a.keywords.Common.LogImpl("858654770",mostCurrent._sigfilename+" Saved",0);
 //BA.debugLineNum = 412;BA.debugLine="Dim Phone As Phone";
_phone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 413;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 414;BA.debugLine="i.Initialize(\"android.intent.action.MEDIA_SCANNER";
_i.Initialize("android.intent.action.MEDIA_SCANNER_SCAN_FILE","file://"+anywheresoftware.b4a.keywords.Common.File.Combine(mostCurrent._sigfoldername,mostCurrent._sigfilename));
 //BA.debugLineNum = 416;BA.debugLine="Phone.SendBroadcastIntent(i)";
_phone.SendBroadcastIntent((android.content.Intent)(_i.getObject()));
 //BA.debugLineNum = 418;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 419;BA.debugLine="Try";
try { //BA.debugLineNum = 421;BA.debugLine="Starter.strCriteria = \"UPDATE tblJOs \" & _";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE tblJOs "+"SET AcctClass = ?, AcctSubClass = ?, ConType = ?, JOStatus = ? , DateFinished = ?, AccomplishedBy = ?, SigFileName = ?, PostedAt = ?, PostedOn = ? "+"WHERE JOID = "+BA.NumberToString(_ijoid);
 //BA.debugLineNum = 424;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{_sacctclass,_ssubclass,_scontype,BA.NumberToString(3),mostCurrent._txtdatetimefinished.getText(),_saccomplishedby,mostCurrent._sigfoldername+"/"+mostCurrent._sigfilename,_sdateposted,_slocation}));
 //BA.debugLineNum = 427;BA.debugLine="Starter.strCriteria = \"INSERT INTO tblJOSASFindi";
mostCurrent._starter._strcriteria /*String*/  = "INSERT INTO tblJOSASFindings VALUES (?, ?, ?, ?, ?)";
 //BA.debugLineNum = 428;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._selectedjoid /*int*/ ),(Object)(_sacctclass),(Object)(_ssubclass),(Object)(_icontype),(Object)(_sremarks)}));
 //BA.debugLineNum = 431;BA.debugLine="Starter.strCriteria = \"INSERT INTO JOWaterLoss V";
mostCurrent._starter._strcriteria /*String*/  = "INSERT INTO JOWaterLoss VALUES ("+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Null)+", ?, ?, ?, ?, ?, ?, ?)";
 //BA.debugLineNum = 432;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(mostCurrent._globalvar._selectedjoid /*int*/ ),(Object)(("SAS")),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null,(Object)(("0")),(Object)(("0")),(Object)(("0"))}));
 //BA.debugLineNum = 434;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 435;BA.debugLine="DispInfoMsg($\"JO ACCOMPLISHED\"$,$\"JO has been su";
_dispinfomsg(("JO ACCOMPLISHED"),("JO has been successfully accomplished."));
 } 
       catch (Exception e55) {
			processBA.setLastException(e55); //BA.debugLineNum = 438;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("858654798",anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),0);
 };
 //BA.debugLineNum = 440;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 441;BA.debugLine="End Sub";
return "";
}
public static boolean  _validateentries() throws Exception{
boolean _bretval = false;
 //BA.debugLineNum = 338;BA.debugLine="Private Sub ValidateEntries () As Boolean";
 //BA.debugLineNum = 339;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 340;BA.debugLine="bRetVal =False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 342;BA.debugLine="Try";
try { //BA.debugLineNum = 343;BA.debugLine="If GlobalVar.SF.Len(cboAcctClass.SelectedItem) <";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cboacctclass.getSelectedItem())<=0) { 
 //BA.debugLineNum = 344;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 346;BA.debugLine="If GlobalVar.SF.Len(cboSubClass.SelectedItem) <=";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbosubclass.getSelectedItem())<=0) { 
 //BA.debugLineNum = 347;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 349;BA.debugLine="If GlobalVar.SF.Len(cboConType.SelectedItem) <=";
if (mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(mostCurrent._cbocontype.getSelectedItem())<=0) { 
 //BA.debugLineNum = 350;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 352;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e15) {
			processBA.setLastException(e15); //BA.debugLineNum = 354;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("858589200",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 355;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 357;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 358;BA.debugLine="End Sub";
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
