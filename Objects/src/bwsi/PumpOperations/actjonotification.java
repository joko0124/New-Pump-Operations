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

public class actjonotification extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static actjonotification mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.actjonotification");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actjonotification).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.actjonotification");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.actjonotification", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actjonotification) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actjonotification) Resume **");
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
		return actjonotification.class;
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
            BA.LogInfo("** Activity (actjonotification) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (actjonotification) Pause event (activity is not paused). **");
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
            actjonotification mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (actjonotification) Resume **");
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
public de.amberhome.objects.appcompat.ACActionBar _actionbarbutton = null;
public de.amberhome.objects.appcompat.ACToolbarDarkWrapper _toolbar = null;
public anywheresoftware.b4a.object.XmlLayoutBuilder _xmlicon = null;
public de.amberhome.materialdialogs.MaterialDialogBuilderWrapper _matdialogbuilder = null;
public de.amberhome.objects.SnackbarWrapper _snack = null;
public anywheresoftware.b4a.objects.CSBuilder _csans = null;
public b4a.example3.customlistview _clvjos = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnljoinfo = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdsearch = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtsearch = null;
public de.amberhome.objects.appcompat.ACPopupMenuWrapper _popsubmenu = null;
public static int _jocounts = 0;
public static int _limit = 0;
public anywheresoftware.b4a.objects.IME _imekeyboard = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlstatus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcount = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fontbold = null;
public com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcustomer = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbldate = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbljonum = null;
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
public static class _jos{
public boolean IsInitialized;
public int JOID;
public String JONum;
public String JOCategory;
public String JOCatCode;
public int JOStatus;
public int RefID;
public String RefNo;
public String CustName;
public String CustAdd;
public String AcctClass;
public String AcctSubClass;
public int iWasRead;
public String sJODate;
public void Initialize() {
IsInitialized = true;
JOID = 0;
JONum = "";
JOCategory = "";
JOCatCode = "";
JOStatus = 0;
RefID = 0;
RefNo = "";
CustName = "";
CustAdd = "";
AcctClass = "";
AcctSubClass = "";
iWasRead = 0;
sJODate = "";
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
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4a.object.XmlLayoutBuilder _xl = null;
 //BA.debugLineNum = 63;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 64;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 65;BA.debugLine="Activity.LoadLayout(\"JONotification\")";
mostCurrent._activity.LoadLayout("JONotification",mostCurrent.activityBA);
 //BA.debugLineNum = 66;BA.debugLine="GlobalVar.SelectedJODesc = DBaseFunctions.GetJODe";
mostCurrent._globalvar._selectedjodesc /*String*/  = mostCurrent._dbasefunctions._getjodesc /*String*/ (mostCurrent.activityBA,mostCurrent._globalvar._selectedjocatcode /*String*/ );
 //BA.debugLineNum = 68;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Append";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("Job Order Notifications"))).PopAll();
 //BA.debugLineNum = 69;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append($";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("List of Job Orders"))).PopAll();
 //BA.debugLineNum = 71;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 72;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 73;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 75;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 76;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
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
 //BA.debugLineNum = 85;BA.debugLine="cdSearch.Initialize(Colors.Transparent, 0)";
mostCurrent._cdsearch.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 86;BA.debugLine="txtSearch.Background = cdSearch";
mostCurrent._txtsearch.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdsearch.getObject()));
 //BA.debugLineNum = 88;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 89;BA.debugLine="FillJOList";
_filljolist();
 };
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
de.amberhome.objects.appcompat.ACMenuItemWrapper _item = null;
 //BA.debugLineNum = 115;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 116;BA.debugLine="Dim Item As ACMenuItem";
_item = new de.amberhome.objects.appcompat.ACMenuItemWrapper();
 //BA.debugLineNum = 118;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 119;BA.debugLine="Menu.Add2(1, 1, \"Filter by\",xmlIcon.GetDrawable(\"";
_menu.Add2((int) (1),(int) (1),BA.ObjectToCharSequence("Filter by"),mostCurrent._xmlicon.GetDrawable("baseline_filter_alt_white_24dp")).setShowAsAction(_item.SHOW_AS_ACTION_IF_ROOM);
 //BA.debugLineNum = 120;BA.debugLine="CreateSubMenus";
_createsubmenus();
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 95;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 96;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 97;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 99;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 109;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 103;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 104;BA.debugLine="FillJOList";
_filljolist();
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return "";
}
public static String  _clvjos_itemclick(int _index,Object _value) throws Exception{
bwsi.PumpOperations.actjonotification._jos _joinfo = null;
 //BA.debugLineNum = 411;BA.debugLine="Sub clvJOs_ItemClick (Index As Int, Value As Objec";
 //BA.debugLineNum = 412;BA.debugLine="Dim JOInfo As JOs = Value";
_joinfo = (bwsi.PumpOperations.actjonotification._jos)(_value);
 //BA.debugLineNum = 413;BA.debugLine="LogColor(Value, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("833816578",BA.ObjectToString(_value),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 414;BA.debugLine="LogColor(JOInfo.JOID, Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogImpl("833816579",BA.NumberToString(_joinfo.JOID /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 415;BA.debugLine="LogColor(JOInfo.JONum, Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("833816580",_joinfo.JONum /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 416;BA.debugLine="LogColor(JOInfo.JOStatus, Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogImpl("833816581",BA.NumberToString(_joinfo.JOStatus /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 417;BA.debugLine="UpdateToReadJO(JOInfo.JOID)";
_updatetoreadjo(_joinfo.JOID /*int*/ );
 //BA.debugLineNum = 418;BA.debugLine="FillJOList";
_filljolist();
 //BA.debugLineNum = 419;BA.debugLine="End Sub";
return "";
}
public static String  _clvjos_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
int _extrasize = 0;
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
bwsi.PumpOperations.actjonotification._jos _joinfo = null;
 //BA.debugLineNum = 377;BA.debugLine="Sub clvJOs_VisibleRangeChanged (FirstIndex As Int,";
 //BA.debugLineNum = 378;BA.debugLine="Dim ExtraSize As Int = 15 'List size";
_extrasize = (int) (15);
 //BA.debugLineNum = 379;BA.debugLine="clvJOs.Refresh";
mostCurrent._clvjos._refresh();
 //BA.debugLineNum = 381;BA.debugLine="For i = Max(0, FirstIndex - ExtraSize) To Min(Las";
{
final int step3 = 1;
final int limit3 = (int) (anywheresoftware.b4a.keywords.Common.Min(_lastindex+_extrasize,mostCurrent._clvjos._getsize()-1));
_i = (int) (anywheresoftware.b4a.keywords.Common.Max(0,_firstindex-_extrasize)) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 382;BA.debugLine="Dim Pnl As B4XView = clvJOs.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._clvjos._getpanel(_i);
 //BA.debugLineNum = 383;BA.debugLine="If i > FirstIndex - ExtraSize And i < LastIndex";
if (_i>_firstindex-_extrasize && _i<_lastindex+_extrasize) { 
 //BA.debugLineNum = 384;BA.debugLine="If Pnl.NumberOfViews = 0 Then 'Add each item/la";
if (_pnl.getNumberOfViews()==0) { 
 //BA.debugLineNum = 385;BA.debugLine="Dim JOInfo As JOs = clvJOs.GetValue(i)";
_joinfo = (bwsi.PumpOperations.actjonotification._jos)(mostCurrent._clvjos._getvalue(_i));
 //BA.debugLineNum = 386;BA.debugLine="Pnl.LoadLayout(\"JONotifInfo\")";
_pnl.LoadLayout("JONotifInfo",mostCurrent.activityBA);
 //BA.debugLineNum = 387;BA.debugLine="lblJONum.Text = JOInfo.JOCategory & \" (\" & JOI";
mostCurrent._lbljonum.setText(BA.ObjectToCharSequence(_joinfo.JOCategory /*String*/ +" ("+_joinfo.JONum /*String*/ +")"));
 //BA.debugLineNum = 388;BA.debugLine="lblCustomer.Text = JOInfo.CustName & \" / \" & J";
mostCurrent._lblcustomer.setText(BA.ObjectToCharSequence(_joinfo.CustName /*String*/ +" / "+_joinfo.CustAdd /*String*/ ));
 //BA.debugLineNum = 389;BA.debugLine="lblDate.Text = JOInfo.sJODate";
mostCurrent._lbldate.setText(BA.ObjectToCharSequence(_joinfo.sJODate /*String*/ ));
 //BA.debugLineNum = 391;BA.debugLine="lblJONum.Ellipsize = \"END\"";
mostCurrent._lbljonum.setEllipsize("END");
 //BA.debugLineNum = 392;BA.debugLine="lblCustomer.Ellipsize = \"END\"";
mostCurrent._lblcustomer.setEllipsize("END");
 //BA.debugLineNum = 393;BA.debugLine="If JOInfo.iWasRead = 0 Then";
if (_joinfo.iWasRead /*int*/ ==0) { 
 //BA.debugLineNum = 394;BA.debugLine="lblJONum.TextColor = 0xFF17A2B8";
mostCurrent._lbljonum.setTextColor((int) (0xff17a2b8));
 //BA.debugLineNum = 395;BA.debugLine="lblJONum.Typeface = Typeface.LoadFromAssets(\"";
mostCurrent._lbljonum.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("sourcesanspro-bold.ttf"));
 //BA.debugLineNum = 396;BA.debugLine="lblDate.Typeface = Typeface.LoadFromAssets(\"s";
mostCurrent._lbldate.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("sourcesanspro-bold.ttf"));
 }else {
 //BA.debugLineNum = 398;BA.debugLine="lblJONum.TextColor = Colors.DarkGray";
mostCurrent._lbljonum.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
 //BA.debugLineNum = 399;BA.debugLine="lblJONum.Typeface = Typeface.LoadFromAssets(\"";
mostCurrent._lbljonum.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("sourcesanspro-regular.ttf"));
 //BA.debugLineNum = 400;BA.debugLine="lblDate.Typeface = Typeface.LoadFromAssets(\"s";
mostCurrent._lbldate.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("sourcesanspro-regular.ttf"));
 };
 };
 }else {
 //BA.debugLineNum = 404;BA.debugLine="If Pnl.NumberOfViews > 0 Then";
if (_pnl.getNumberOfViews()>0) { 
 //BA.debugLineNum = 405;BA.debugLine="Pnl.RemoveAllViews 'Remove none visable item/l";
_pnl.RemoveAllViews();
 };
 };
 }
};
 //BA.debugLineNum = 409;BA.debugLine="End Sub";
return "";
}
public static String  _confirmstartjo(int _ijoid) throws Exception{
 //BA.debugLineNum = 567;BA.debugLine="Private Sub ConfirmStartJO(iJOID As Int)";
 //BA.debugLineNum = 568;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 570;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle("START JO").SetTitleColor((int) (mostCurrent._globalvar._bluecolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage(("You are about to Start this JO,")+anywheresoftware.b4a.keywords.Common.CRLF+("JO Date & Time Started will be today.")+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+("Do you want to START this JO Now?")).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"JO_Started").SetOnNegativeClicked(mostCurrent.activityBA,"JO_Started").SetOnViewBinder(mostCurrent.activityBA,"JOFontSizeBinder");
 //BA.debugLineNum = 589;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 590;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 592;BA.debugLine="End Sub";
return "";
}
public static String  _createsubmenus() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _csall = null;
anywheresoftware.b4a.objects.CSBuilder _csunread = null;
anywheresoftware.b4a.objects.CSBuilder _csread = null;
 //BA.debugLineNum = 123;BA.debugLine="Private Sub CreateSubMenus";
 //BA.debugLineNum = 124;BA.debugLine="Dim csAll, csUnread, csRead As CSBuilder";
_csall = new anywheresoftware.b4a.objects.CSBuilder();
_csunread = new anywheresoftware.b4a.objects.CSBuilder();
_csread = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 126;BA.debugLine="csAll.Initialize.Color(Colors.White).Append($\"All";
_csall.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(("All"))).PopAll();
 //BA.debugLineNum = 127;BA.debugLine="csUnread.Initialize.Color(Colors.White).Append($\"";
_csunread.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(("Unread"))).PopAll();
 //BA.debugLineNum = 128;BA.debugLine="csRead.Initialize.Color(Colors.White).Append($\"Re";
_csread.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(("Read"))).PopAll();
 //BA.debugLineNum = 130;BA.debugLine="PopSubMenu.Initialize(\"FilterBy\", ToolBar.GetView";
mostCurrent._popsubmenu.Initialize(mostCurrent.activityBA,"FilterBy",(android.view.View)(mostCurrent._toolbar.GetView((int) (3)).getObject()));
 //BA.debugLineNum = 131;BA.debugLine="PopSubMenu.AddMenuItem(0,csAll,xmlIcon.GetDrawabl";
mostCurrent._popsubmenu.AddMenuItem((int) (0),BA.ObjectToCharSequence(_csall.getObject()),mostCurrent._xmlicon.GetDrawable("ic_select_all_white_24dp"));
 //BA.debugLineNum = 132;BA.debugLine="PopSubMenu.AddMenuItem(1,csUnread,xmlIcon.GetDraw";
mostCurrent._popsubmenu.AddMenuItem((int) (1),BA.ObjectToCharSequence(_csunread.getObject()),mostCurrent._xmlicon.GetDrawable("baseline_mark_email_unread_white_24dp"));
 //BA.debugLineNum = 133;BA.debugLine="PopSubMenu.AddMenuItem(2,csRead,xmlIcon.GetDrawab";
mostCurrent._popsubmenu.AddMenuItem((int) (2),BA.ObjectToCharSequence(_csread.getObject()),mostCurrent._xmlicon.GetDrawable("baseline_mark_email_read_white_24dp"));
 //BA.debugLineNum = 134;BA.debugLine="End Sub";
return "";
}
public static int  _daysbetween(String _startdate,String _starttime,String _enddate,String _endtime) throws Exception{
long _s = 0L;
long _e = 0L;
 //BA.debugLineNum = 750;BA.debugLine="Sub DaysBetween(StartDate As String, StartTime As";
 //BA.debugLineNum = 751;BA.debugLine="Dim s, e As Long";
_s = 0L;
_e = 0L;
 //BA.debugLineNum = 752;BA.debugLine="s = ParseDateAndTime(StartDate, StartTime)";
_s = _parsedateandtime(_startdate,_starttime);
 //BA.debugLineNum = 753;BA.debugLine="e = ParseDateAndTime(EndDate, EndTime)";
_e = _parsedateandtime(_enddate,_endtime);
 //BA.debugLineNum = 754;BA.debugLine="Return (e - s) / DateTime.TicksPerDay";
if (true) return (int) ((_e-_s)/(double)anywheresoftware.b4a.keywords.Common.DateTime.TicksPerDay);
 //BA.debugLineNum = 755;BA.debugLine="End Sub";
return 0;
}
public static String  _dispinfomsg(String _smsg,String _stitle) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _infomsg = null;
 //BA.debugLineNum = 647;BA.debugLine="Private Sub DispInfoMsg(sMsg As String, sTitle As";
 //BA.debugLineNum = 649;BA.debugLine="Dim InfoMsg As AX_CustomAlertDialog";
_infomsg = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 651;BA.debugLine="InfoMsg.Initialize.Create _ 			.SetDialogStyleNam";
_infomsg.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_infomsg.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetMessage(_smsg).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"MessageBox").SetOnViewBinder(mostCurrent.activityBA,"FontSizeBinder");
 //BA.debugLineNum = 665;BA.debugLine="InfoMsg.SetDialogBackground(MyFunctions.myCD)";
_infomsg.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 666;BA.debugLine="InfoMsg.Build.Show";
_infomsg.Build().Show();
 //BA.debugLineNum = 667;BA.debugLine="End Sub";
return "";
}
public static void  _filljolist() throws Exception{
ResumableSub_FillJOList rsub = new ResumableSub_FillJOList(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_FillJOList extends BA.ResumableSub {
public ResumableSub_FillJOList(bwsi.PumpOperations.actjonotification parent) {
this.parent = parent;
}
bwsi.PumpOperations.actjonotification parent;
Object _senderfilter = null;
String _sdatejo = "";
String _stimejo = "";
int _ihour = 0;
int _imin = 0;
String _ampm = "";
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actjonotification._jos _joinfo = null;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;

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
 //BA.debugLineNum = 157;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 158;BA.debugLine="Dim sDateJO, sTimeJO As String";
_sdatejo = "";
_stimejo = "";
 //BA.debugLineNum = 159;BA.debugLine="Dim iHour, iMin As Int";
_ihour = 0;
_imin = 0;
 //BA.debugLineNum = 160;BA.debugLine="Dim amPm As String";
_ampm = "";
 //BA.debugLineNum = 161;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 162;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 64;
this.catchState = 63;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 63;
 //BA.debugLineNum = 163;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblJOs \" &";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblJOs "+"WHERE WasUploaded = '"+BA.NumberToString(0)+"' "+"ORDER BY substr (JOAssignedAt,1,4) || ' ' || substr(JOAssignedAt,6,2) || ' ' || substr(JOAssignedAt,9,2) || ' ' || substr(JOAssignedAt,18,2) || ' ' || (Case WHEN substr(JOAssignedAt,12,2) = '12' AND substr(JOAssignedAt,18,2) = 'AM' THEN '00' ELSE substr(JOAssignedAt,12,2) END) || ' ' || substr(JOAssignedAt,15,2) DESC";
 //BA.debugLineNum = 166;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("833619978",parent.mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 168;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 169;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 65;
return;
case 65:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 170;BA.debugLine="If Success Then";
if (true) break;

case 4:
//if
this.state = 61;
if (_success) { 
this.state = 6;
}else {
this.state = 60;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 171;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 172;BA.debugLine="clvJOs.Clear";
parent.mostCurrent._clvjos._clear();
 //BA.debugLineNum = 173;BA.debugLine="Do While RS.NextRow";
if (true) break;

case 7:
//do while
this.state = 58;
while (_rs.NextRow()) {
this.state = 9;
if (true) break;
}
if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 174;BA.debugLine="Dim JOInfo As JOs";
_joinfo = new bwsi.PumpOperations.actjonotification._jos();
 //BA.debugLineNum = 175;BA.debugLine="JOInfo.Initialize";
_joinfo.Initialize();
 //BA.debugLineNum = 176;BA.debugLine="JOInfo.JOID = RS.GetInt(\"JOID\")";
_joinfo.JOID /*int*/  = _rs.GetInt("JOID");
 //BA.debugLineNum = 177;BA.debugLine="JOInfo.JONum = GlobalVar.SF.Upper(RS.GetString";
_joinfo.JONum /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JONo"));
 //BA.debugLineNum = 178;BA.debugLine="JOInfo.JOCatCode = GlobalVar.SF.Upper(RS.GetSt";
_joinfo.JOCatCode /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JOCatCode"));
 //BA.debugLineNum = 179;BA.debugLine="JOInfo.JOCategory = GlobalVar.SF.Upper(RS.GetS";
_joinfo.JOCategory /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JoDesc"));
 //BA.debugLineNum = 180;BA.debugLine="JOInfo.RefID= RS.GetInt(\"RefID\")";
_joinfo.RefID /*int*/  = _rs.GetInt("RefID");
 //BA.debugLineNum = 181;BA.debugLine="JOInfo.RefNo= GlobalVar.SF.Upper(RS.GetString(";
_joinfo.RefNo /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("RefNo"));
 //BA.debugLineNum = 182;BA.debugLine="JOInfo.CustName = GlobalVar.SF.Upper(RS.GetStr";
_joinfo.CustName /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustName"));
 //BA.debugLineNum = 183;BA.debugLine="JOInfo.CustAdd = GlobalVar.SF.Upper(RS.GetStri";
_joinfo.CustAdd /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustAddress"));
 //BA.debugLineNum = 184;BA.debugLine="JOInfo.AcctClass = GlobalVar.SF.Upper(RS.GetSt";
_joinfo.AcctClass /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctClass"));
 //BA.debugLineNum = 185;BA.debugLine="JOInfo.AcctSubClass = GlobalVar.SF.Upper(RS.Ge";
_joinfo.AcctSubClass /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctSubClass"));
 //BA.debugLineNum = 186;BA.debugLine="JOInfo.JOStatus = RS.GetInt(\"JOStatus\")";
_joinfo.JOStatus /*int*/  = _rs.GetInt("JOStatus");
 //BA.debugLineNum = 187;BA.debugLine="JOInfo.iWasRead = RS.GetInt(\"WasRead\")";
_joinfo.iWasRead /*int*/  = _rs.GetInt("WasRead");
 //BA.debugLineNum = 188;BA.debugLine="sDateJO = GlobalVar.SF.Left(RS.GetString(\"JOAs";
_sdatejo = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv6(_rs.GetString("JOAssignedAt"),(long) (10));
 //BA.debugLineNum = 189;BA.debugLine="iHour = GlobalVar.SF.Val(GlobalVar.SF.Mid(RS.G";
_ihour = (int) (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvv5(_rs.GetString("JOAssignedAt"),(int) (12),(int) (2))));
 //BA.debugLineNum = 190;BA.debugLine="iMin = GlobalVar.SF.Val(GlobalVar.SF.Mid(RS.Ge";
_imin = (int) (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvv5(_rs.GetString("JOAssignedAt"),(int) (15),(int) (2))));
 //BA.debugLineNum = 191;BA.debugLine="amPm =  GlobalVar.SF.Right(RS.GetString(\"JOAss";
_ampm = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvv7(_rs.GetString("JOAssignedAt"),(long) (2));
 //BA.debugLineNum = 192;BA.debugLine="Log(sDateJO)";
anywheresoftware.b4a.keywords.Common.LogImpl("833620004",_sdatejo,0);
 //BA.debugLineNum = 193;BA.debugLine="If amPm = \"PM\" And iHour <> 12 Then";
if (true) break;

case 10:
//if
this.state = 13;
if ((_ampm).equals("PM") && _ihour!=12) { 
this.state = 12;
}if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 194;BA.debugLine="iHour = iHour + 12";
_ihour = (int) (_ihour+12);
 if (true) break;
;
 //BA.debugLineNum = 197;BA.debugLine="If amPm = \"AM\" And iHour = 12 Then";

case 13:
//if
this.state = 16;
if ((_ampm).equals("AM") && _ihour==12) { 
this.state = 15;
}if (true) break;

case 15:
//C
this.state = 16;
 //BA.debugLineNum = 198;BA.debugLine="iHour = 0";
_ihour = (int) (0);
 if (true) break;
;
 //BA.debugLineNum = 201;BA.debugLine="If GlobalVar.SF.Len(iHour) = 1 Then";

case 16:
//if
this.state = 33;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_ihour))==1) { 
this.state = 18;
}else {
this.state = 26;
}if (true) break;

case 18:
//C
this.state = 19;
 //BA.debugLineNum = 202;BA.debugLine="If GlobalVar.SF.Len(iMin) = 1 Then";
if (true) break;

case 19:
//if
this.state = 24;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_imin))==1) { 
this.state = 21;
}else {
this.state = 23;
}if (true) break;

case 21:
//C
this.state = 24;
 //BA.debugLineNum = 203;BA.debugLine="sTimeJO = \"0\" & iHour & \":0\" & iMin & \":00\"";
_stimejo = "0"+BA.NumberToString(_ihour)+":0"+BA.NumberToString(_imin)+":00";
 if (true) break;

case 23:
//C
this.state = 24;
 //BA.debugLineNum = 205;BA.debugLine="sTimeJO = \"0\" & iHour & \":\" & iMin & \":00\"";
_stimejo = "0"+BA.NumberToString(_ihour)+":"+BA.NumberToString(_imin)+":00";
 if (true) break;

case 24:
//C
this.state = 33;
;
 if (true) break;

case 26:
//C
this.state = 27;
 //BA.debugLineNum = 208;BA.debugLine="If GlobalVar.SF.Len(iMin) = 1 Then";
if (true) break;

case 27:
//if
this.state = 32;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_imin))==1) { 
this.state = 29;
}else {
this.state = 31;
}if (true) break;

case 29:
//C
this.state = 32;
 //BA.debugLineNum = 209;BA.debugLine="sTimeJO = iHour & \":0\" & iMin & \":00\"";
_stimejo = BA.NumberToString(_ihour)+":0"+BA.NumberToString(_imin)+":00";
 if (true) break;

case 31:
//C
this.state = 32;
 //BA.debugLineNum = 211;BA.debugLine="sTimeJO = iHour & \":\" & iMin & \":00\"";
_stimejo = BA.NumberToString(_ihour)+":"+BA.NumberToString(_imin)+":00";
 if (true) break;

case 32:
//C
this.state = 33;
;
 if (true) break;

case 33:
//C
this.state = 34;
;
 //BA.debugLineNum = 214;BA.debugLine="Log(sTimeJO)";
anywheresoftware.b4a.keywords.Common.LogImpl("833620026",_stimejo,0);
 //BA.debugLineNum = 215;BA.debugLine="DateTime.TimeFormat = \"HH:mm:ss\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm:ss");
 //BA.debugLineNum = 216;BA.debugLine="LogColor(JOInfo.CustName, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("833620028",_joinfo.CustName /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 217;BA.debugLine="If DaysBetween(sDateJO, sTimeJO, DateTime.Date";
if (true) break;

case 34:
//if
this.state = 57;
if (_daysbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))>1) { 
this.state = 36;
}else if(_daysbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))==1) { 
this.state = 38;
}else if(_daysbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))<=0) { 
this.state = 40;
}if (true) break;

case 36:
//C
this.state = 57;
 //BA.debugLineNum = 218;BA.debugLine="JOInfo.sJODate = DaysBetween(sDateJO, sTimeJO";
_joinfo.sJODate /*String*/  = BA.NumberToString(_daysbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())))+(" days ago");
 if (true) break;

case 38:
//C
this.state = 57;
 //BA.debugLineNum = 220;BA.debugLine="JOInfo.sJODate = DaysBetween(sDateJO, sTimeJO";
_joinfo.sJODate /*String*/  = BA.NumberToString(_daysbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())))+(" day ago");
 if (true) break;

case 40:
//C
this.state = 41;
 //BA.debugLineNum = 222;BA.debugLine="LogColor (HoursBetween(sDateJO, sTimeJO, Date";
anywheresoftware.b4a.keywords.Common.LogImpl("833620034",BA.NumberToString(_hoursbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 223;BA.debugLine="If HoursBetween(sDateJO, sTimeJO, DateTime.Da";
if (true) break;

case 41:
//if
this.state = 56;
if (_hoursbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))>1) { 
this.state = 43;
}else if(_hoursbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))==1) { 
this.state = 45;
}else if(_hoursbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))<=0) { 
this.state = 47;
}if (true) break;

case 43:
//C
this.state = 56;
 //BA.debugLineNum = 224;BA.debugLine="JOInfo.sJODate = HoursBetween(sDateJO, sTime";
_joinfo.sJODate /*String*/  = BA.NumberToString(_hoursbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())))+(" hours ago");
 if (true) break;

case 45:
//C
this.state = 56;
 //BA.debugLineNum = 226;BA.debugLine="JOInfo.sJODate = HoursBetween(sDateJO, sTime";
_joinfo.sJODate /*String*/  = BA.NumberToString(_hoursbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())))+(" hour ago");
 if (true) break;

case 47:
//C
this.state = 48;
 //BA.debugLineNum = 228;BA.debugLine="If MinBetween(sDateJO, sTimeJO, DateTime.Dat";
if (true) break;

case 48:
//if
this.state = 55;
if (_minbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))>1) { 
this.state = 50;
}else if(_minbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))==1) { 
this.state = 52;
}else if(_minbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))<=0) { 
this.state = 54;
}if (true) break;

case 50:
//C
this.state = 55;
 //BA.debugLineNum = 229;BA.debugLine="JOInfo.sJODate = MinBetween(sDateJO, sTimeJ";
_joinfo.sJODate /*String*/  = BA.NumberToString(_minbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())))+(" mins. ago");
 if (true) break;

case 52:
//C
this.state = 55;
 //BA.debugLineNum = 231;BA.debugLine="JOInfo.sJODate = MinBetween(sDateJO, sTimeJ";
_joinfo.sJODate /*String*/  = BA.NumberToString(_minbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())))+(" min. ago");
 if (true) break;

case 54:
//C
this.state = 55;
 //BA.debugLineNum = 233;BA.debugLine="JOInfo.sJODate = \"Just Now\"";
_joinfo.sJODate /*String*/  = "Just Now";
 if (true) break;

case 55:
//C
this.state = 56;
;
 if (true) break;

case 56:
//C
this.state = 57;
;
 if (true) break;

case 57:
//C
this.state = 7;
;
 //BA.debugLineNum = 237;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 238;BA.debugLine="Pnl.SetLayoutAnimated(0, 5dip, 0, clvJOs.AsVie";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (0),parent.mostCurrent._clvjos._asview().getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
 //BA.debugLineNum = 239;BA.debugLine="clvJOs.Add(Pnl, JOInfo)";
parent.mostCurrent._clvjos._add(_pnl,(Object)(_joinfo));
 if (true) break;

case 58:
//C
this.state = 61;
;
 //BA.debugLineNum = 241;BA.debugLine="lblCount.Text = RS.RowCount & $\" Record(s) Foun";
parent.mostCurrent._lblcount.setText(BA.ObjectToCharSequence(BA.NumberToString(_rs.getRowCount())+(" Record(s) Found")));
 if (true) break;

case 60:
//C
this.state = 61;
 //BA.debugLineNum = 243;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 244;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 245;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 246;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 247;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("833620059",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 61:
//C
this.state = 64;
;
 //BA.debugLineNum = 250;BA.debugLine="Log($\"List of Job Order Records = ${NumberFormat";
anywheresoftware.b4a.keywords.Common.LogImpl("833620062",("List of Job Order Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvjos._getsize()))+" Job Order Records"),0);
 if (true) break;

case 63:
//C
this.state = 64;
this.catchState = 0;
 //BA.debugLineNum = 252;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("833620064",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 64:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 255;BA.debugLine="End Sub";
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
public static String  _filterby_itemclicked(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 148;BA.debugLine="Sub FilterBy_ItemClicked (Item As ACMenuItem)";
 //BA.debugLineNum = 149;BA.debugLine="Log(\"Popupmenu Item clicked: \" & Item.Id & \" - \"";
anywheresoftware.b4a.keywords.Common.LogImpl("833554433","Popupmenu Item clicked: "+BA.NumberToString(_item.getId())+" - "+_item.getTitle(),0);
 //BA.debugLineNum = 150;BA.debugLine="FilterJoList(Item.Id)";
_filterjolist(_item.getId());
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return "";
}
public static void  _filterjolist(int _istatus) throws Exception{
ResumableSub_FilterJoList rsub = new ResumableSub_FilterJoList(null,_istatus);
rsub.resume(processBA, null);
}
public static class ResumableSub_FilterJoList extends BA.ResumableSub {
public ResumableSub_FilterJoList(bwsi.PumpOperations.actjonotification parent,int _istatus) {
this.parent = parent;
this._istatus = _istatus;
}
bwsi.PumpOperations.actjonotification parent;
int _istatus;
Object _senderfilter = null;
String _sdatejo = "";
String _stimejo = "";
int _ihour = 0;
int _imin = 0;
String _ampm = "";
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actjonotification._jos _joinfo = null;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;

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
 //BA.debugLineNum = 258;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 259;BA.debugLine="Dim sDateJO, sTimeJO As String";
_sdatejo = "";
_stimejo = "";
 //BA.debugLineNum = 260;BA.debugLine="Dim iHour, iMin As Int";
_ihour = 0;
_imin = 0;
 //BA.debugLineNum = 261;BA.debugLine="Dim amPm As String";
_ampm = "";
 //BA.debugLineNum = 262;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 264;BA.debugLine="clvJOs.Clear";
parent.mostCurrent._clvjos._clear();
 //BA.debugLineNum = 265;BA.debugLine="If iStatus = 0 Then";
if (true) break;

case 1:
//if
this.state = 10;
if (_istatus==0) { 
this.state = 3;
}else if(_istatus==1) { 
this.state = 5;
}else if(_istatus==2) { 
this.state = 7;
}else {
this.state = 9;
}if (true) break;

case 3:
//C
this.state = 10;
 //BA.debugLineNum = 266;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblJOs \" &";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblJOs "+"WHERE WasUploaded = '"+BA.NumberToString(0)+"' "+"ORDER BY JOAssignedAt DESC";
 if (true) break;

case 5:
//C
this.state = 10;
 //BA.debugLineNum = 270;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblJOs \" &";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblJOs "+"WHERE WasUploaded = '"+BA.NumberToString(0)+"' "+"AND WasRead = '"+BA.NumberToString(0)+"' "+"ORDER BY JOAssignedAt DESC";
 if (true) break;

case 7:
//C
this.state = 10;
 //BA.debugLineNum = 276;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblJOs \" &";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblJOs "+"WHERE WasUploaded = '"+BA.NumberToString(0)+"' "+"AND WasRead = '"+BA.NumberToString(1)+"' "+"ORDER BY JOAssignedAt DESC";
 if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 281;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 10:
//C
this.state = 11;
;
 //BA.debugLineNum = 283;BA.debugLine="LogColor(Starter.strCriteria, Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("833685530",parent.mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 285;BA.debugLine="Try";
if (true) break;

case 11:
//try
this.state = 74;
this.catchState = 73;
this.state = 13;
if (true) break;

case 13:
//C
this.state = 14;
this.catchState = 73;
 //BA.debugLineNum = 286;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 287;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 75;
return;
case 75:
//C
this.state = 14;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 288;BA.debugLine="If Success Then";
if (true) break;

case 14:
//if
this.state = 71;
if (_success) { 
this.state = 16;
}else {
this.state = 70;
}if (true) break;

case 16:
//C
this.state = 17;
 //BA.debugLineNum = 289;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 290;BA.debugLine="clvJOs.Clear";
parent.mostCurrent._clvjos._clear();
 //BA.debugLineNum = 291;BA.debugLine="Do While RS.NextRow";
if (true) break;

case 17:
//do while
this.state = 68;
while (_rs.NextRow()) {
this.state = 19;
if (true) break;
}
if (true) break;

case 19:
//C
this.state = 20;
 //BA.debugLineNum = 292;BA.debugLine="Dim JOInfo As JOs";
_joinfo = new bwsi.PumpOperations.actjonotification._jos();
 //BA.debugLineNum = 293;BA.debugLine="JOInfo.Initialize";
_joinfo.Initialize();
 //BA.debugLineNum = 294;BA.debugLine="JOInfo.JOID = RS.GetInt(\"JOID\")";
_joinfo.JOID /*int*/  = _rs.GetInt("JOID");
 //BA.debugLineNum = 295;BA.debugLine="JOInfo.JONum = GlobalVar.SF.Upper(RS.GetString";
_joinfo.JONum /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JONo"));
 //BA.debugLineNum = 296;BA.debugLine="JOInfo.JOCatCode = GlobalVar.SF.Upper(RS.GetSt";
_joinfo.JOCatCode /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JOCatCode"));
 //BA.debugLineNum = 297;BA.debugLine="JOInfo.JOCategory = GlobalVar.SF.Upper(RS.GetS";
_joinfo.JOCategory /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JoDesc"));
 //BA.debugLineNum = 298;BA.debugLine="JOInfo.RefID= RS.GetInt(\"RefID\")";
_joinfo.RefID /*int*/  = _rs.GetInt("RefID");
 //BA.debugLineNum = 299;BA.debugLine="JOInfo.RefNo= GlobalVar.SF.Upper(RS.GetString(";
_joinfo.RefNo /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("RefNo"));
 //BA.debugLineNum = 300;BA.debugLine="JOInfo.CustName = GlobalVar.SF.Upper(RS.GetStr";
_joinfo.CustName /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustName"));
 //BA.debugLineNum = 301;BA.debugLine="JOInfo.CustAdd = GlobalVar.SF.Upper(RS.GetStri";
_joinfo.CustAdd /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustAddress"));
 //BA.debugLineNum = 302;BA.debugLine="JOInfo.AcctClass = GlobalVar.SF.Upper(RS.GetSt";
_joinfo.AcctClass /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctClass"));
 //BA.debugLineNum = 303;BA.debugLine="JOInfo.AcctSubClass = GlobalVar.SF.Upper(RS.Ge";
_joinfo.AcctSubClass /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctSubClass"));
 //BA.debugLineNum = 304;BA.debugLine="JOInfo.JOStatus = RS.GetInt(\"JOStatus\")";
_joinfo.JOStatus /*int*/  = _rs.GetInt("JOStatus");
 //BA.debugLineNum = 305;BA.debugLine="JOInfo.iWasRead = RS.GetInt(\"WasRead\")";
_joinfo.iWasRead /*int*/  = _rs.GetInt("WasRead");
 //BA.debugLineNum = 306;BA.debugLine="sDateJO = GlobalVar.SF.Left(RS.GetString(\"JOAs";
_sdatejo = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv6(_rs.GetString("JOAssignedAt"),(long) (10));
 //BA.debugLineNum = 307;BA.debugLine="iHour = GlobalVar.SF.Val(GlobalVar.SF.Mid(RS.G";
_ihour = (int) (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvv5(_rs.GetString("JOAssignedAt"),(int) (12),(int) (2))));
 //BA.debugLineNum = 308;BA.debugLine="iMin = GlobalVar.SF.Val(GlobalVar.SF.Mid(RS.Ge";
_imin = (int) (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv6(parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvv5(_rs.GetString("JOAssignedAt"),(int) (15),(int) (2))));
 //BA.debugLineNum = 309;BA.debugLine="amPm =  GlobalVar.SF.Right(RS.GetString(\"JOAss";
_ampm = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvv7(_rs.GetString("JOAssignedAt"),(long) (2));
 //BA.debugLineNum = 310;BA.debugLine="Log(sDateJO)";
anywheresoftware.b4a.keywords.Common.LogImpl("833685557",_sdatejo,0);
 //BA.debugLineNum = 311;BA.debugLine="Log(JOInfo.JOID & \", \" & JOInfo.CustName)";
anywheresoftware.b4a.keywords.Common.LogImpl("833685558",BA.NumberToString(_joinfo.JOID /*int*/ )+", "+_joinfo.CustName /*String*/ ,0);
 //BA.debugLineNum = 312;BA.debugLine="If amPm = \"PM\" And iHour <> 12 Then";
if (true) break;

case 20:
//if
this.state = 23;
if ((_ampm).equals("PM") && _ihour!=12) { 
this.state = 22;
}if (true) break;

case 22:
//C
this.state = 23;
 //BA.debugLineNum = 313;BA.debugLine="iHour = iHour + 12";
_ihour = (int) (_ihour+12);
 if (true) break;
;
 //BA.debugLineNum = 316;BA.debugLine="If amPm = \"AM\" And iHour = 12 Then";

case 23:
//if
this.state = 26;
if ((_ampm).equals("AM") && _ihour==12) { 
this.state = 25;
}if (true) break;

case 25:
//C
this.state = 26;
 //BA.debugLineNum = 317;BA.debugLine="iHour = 0";
_ihour = (int) (0);
 if (true) break;
;
 //BA.debugLineNum = 320;BA.debugLine="If GlobalVar.SF.Len(iHour) = 1 Then";

case 26:
//if
this.state = 43;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_ihour))==1) { 
this.state = 28;
}else {
this.state = 36;
}if (true) break;

case 28:
//C
this.state = 29;
 //BA.debugLineNum = 321;BA.debugLine="If GlobalVar.SF.Len(iMin) = 1 Then";
if (true) break;

case 29:
//if
this.state = 34;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_imin))==1) { 
this.state = 31;
}else {
this.state = 33;
}if (true) break;

case 31:
//C
this.state = 34;
 //BA.debugLineNum = 322;BA.debugLine="sTimeJO = \"0\" & iHour & \":0\" & iMin & \":00\"";
_stimejo = "0"+BA.NumberToString(_ihour)+":0"+BA.NumberToString(_imin)+":00";
 if (true) break;

case 33:
//C
this.state = 34;
 //BA.debugLineNum = 324;BA.debugLine="sTimeJO = \"0\" & iHour & \":\" & iMin & \":00\"";
_stimejo = "0"+BA.NumberToString(_ihour)+":"+BA.NumberToString(_imin)+":00";
 if (true) break;

case 34:
//C
this.state = 43;
;
 if (true) break;

case 36:
//C
this.state = 37;
 //BA.debugLineNum = 327;BA.debugLine="If GlobalVar.SF.Len(iMin) = 1 Then";
if (true) break;

case 37:
//if
this.state = 42;
if (parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvv7(BA.NumberToString(_imin))==1) { 
this.state = 39;
}else {
this.state = 41;
}if (true) break;

case 39:
//C
this.state = 42;
 //BA.debugLineNum = 328;BA.debugLine="sTimeJO = iHour & \":0\" & iMin & \":00\"";
_stimejo = BA.NumberToString(_ihour)+":0"+BA.NumberToString(_imin)+":00";
 if (true) break;

case 41:
//C
this.state = 42;
 //BA.debugLineNum = 330;BA.debugLine="sTimeJO = iHour & \":\" & iMin & \":00\"";
_stimejo = BA.NumberToString(_ihour)+":"+BA.NumberToString(_imin)+":00";
 if (true) break;

case 42:
//C
this.state = 43;
;
 if (true) break;

case 43:
//C
this.state = 44;
;
 //BA.debugLineNum = 333;BA.debugLine="Log(sTimeJO)";
anywheresoftware.b4a.keywords.Common.LogImpl("833685580",_stimejo,0);
 //BA.debugLineNum = 334;BA.debugLine="DateTime.TimeFormat = \"HH:mm:ss\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm:ss");
 //BA.debugLineNum = 335;BA.debugLine="If DaysBetween(sDateJO, sTimeJO, DateTime.Date";
if (true) break;

case 44:
//if
this.state = 67;
if (_daysbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))>1) { 
this.state = 46;
}else if(_daysbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))==1) { 
this.state = 48;
}else if(_daysbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))<=0) { 
this.state = 50;
}if (true) break;

case 46:
//C
this.state = 67;
 //BA.debugLineNum = 336;BA.debugLine="JOInfo.sJODate = DaysBetween(sDateJO, sTimeJO";
_joinfo.sJODate /*String*/  = BA.NumberToString(_daysbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())))+(" days ago");
 if (true) break;

case 48:
//C
this.state = 67;
 //BA.debugLineNum = 338;BA.debugLine="JOInfo.sJODate = DaysBetween(sDateJO, sTimeJO";
_joinfo.sJODate /*String*/  = BA.NumberToString(_daysbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())))+(" day ago");
 if (true) break;

case 50:
//C
this.state = 51;
 //BA.debugLineNum = 340;BA.debugLine="LogColor (HoursBetween(sDateJO, sTimeJO, Date";
anywheresoftware.b4a.keywords.Common.LogImpl("833685587",BA.NumberToString(_hoursbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 341;BA.debugLine="If HoursBetween(sDateJO, sTimeJO, DateTime.Da";
if (true) break;

case 51:
//if
this.state = 66;
if (_hoursbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))>1) { 
this.state = 53;
}else if(_hoursbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))==1) { 
this.state = 55;
}else if(_hoursbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))<=0) { 
this.state = 57;
}if (true) break;

case 53:
//C
this.state = 66;
 //BA.debugLineNum = 342;BA.debugLine="JOInfo.sJODate = HoursBetween(sDateJO, sTime";
_joinfo.sJODate /*String*/  = BA.NumberToString(_hoursbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())))+(" hours ago");
 if (true) break;

case 55:
//C
this.state = 66;
 //BA.debugLineNum = 344;BA.debugLine="JOInfo.sJODate = HoursBetween(sDateJO, sTime";
_joinfo.sJODate /*String*/  = BA.NumberToString(_hoursbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())))+(" hour ago");
 if (true) break;

case 57:
//C
this.state = 58;
 //BA.debugLineNum = 346;BA.debugLine="If MinBetween(sDateJO, sTimeJO, DateTime.Dat";
if (true) break;

case 58:
//if
this.state = 65;
if (_minbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))>1) { 
this.state = 60;
}else if(_minbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))==1) { 
this.state = 62;
}else if(_minbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))<=0) { 
this.state = 64;
}if (true) break;

case 60:
//C
this.state = 65;
 //BA.debugLineNum = 347;BA.debugLine="JOInfo.sJODate = MinBetween(sDateJO, sTimeJ";
_joinfo.sJODate /*String*/  = BA.NumberToString(_minbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())))+(" mins. ago");
 if (true) break;

case 62:
//C
this.state = 65;
 //BA.debugLineNum = 349;BA.debugLine="JOInfo.sJODate = MinBetween(sDateJO, sTimeJ";
_joinfo.sJODate /*String*/  = BA.NumberToString(_minbetween(_sdatejo,_stimejo,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow())))+(" min. ago");
 if (true) break;

case 64:
//C
this.state = 65;
 //BA.debugLineNum = 351;BA.debugLine="JOInfo.sJODate = \"Just Now\"";
_joinfo.sJODate /*String*/  = "Just Now";
 if (true) break;

case 65:
//C
this.state = 66;
;
 if (true) break;

case 66:
//C
this.state = 67;
;
 if (true) break;

case 67:
//C
this.state = 17;
;
 //BA.debugLineNum = 355;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 356;BA.debugLine="Pnl.SetLayoutAnimated(0, 5dip, 0, clvJOs.AsVie";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (0),parent.mostCurrent._clvjos._asview().getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
 //BA.debugLineNum = 357;BA.debugLine="clvJOs.Add(Pnl, JOInfo)";
parent.mostCurrent._clvjos._add(_pnl,(Object)(_joinfo));
 if (true) break;

case 68:
//C
this.state = 71;
;
 //BA.debugLineNum = 359;BA.debugLine="lblCount.Text = RS.RowCount & $\" Record(s) Foun";
parent.mostCurrent._lblcount.setText(BA.ObjectToCharSequence(BA.NumberToString(_rs.getRowCount())+(" Record(s) Found")));
 if (true) break;

case 70:
//C
this.state = 71;
 //BA.debugLineNum = 361;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 362;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 363;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 364;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 365;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("833685612",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 71:
//C
this.state = 74;
;
 //BA.debugLineNum = 368;BA.debugLine="Log($\"List of Job Order Records = ${NumberFormat";
anywheresoftware.b4a.keywords.Common.LogImpl("833685615",("List of Job Order Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvjos._getsize()))+" Job Order Records"),0);
 if (true) break;

case 73:
//C
this.state = 74;
this.catchState = 0;
 //BA.debugLineNum = 370;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("833685617",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 74:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 373;BA.debugLine="End Sub";
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
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper  _fontfromfile(String _dir,String _filename) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 703;BA.debugLine="Sub FontFromFile(Dir As String, FileName As String";
 //BA.debugLineNum = 705;BA.debugLine="Dim R As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 706;BA.debugLine="Return R.RunStaticMethod(\"android.graphics.Typefa";
if (true) return (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(_r.RunStaticMethod("android.graphics.Typeface","createFromFile",(Object[])(new String[]{anywheresoftware.b4a.keywords.Common.File.Combine(_dir,_filename)}),new String[]{"java.lang.String"})));
 //BA.debugLineNum = 707;BA.debugLine="End Sub";
return null;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 25;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private xmlIcon As XmlLayoutBuilder";
mostCurrent._xmlicon = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 27;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private csAns As CSBuilder";
mostCurrent._csans = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 32;BA.debugLine="Private clvJOs As CustomListView";
mostCurrent._clvjos = new b4a.example3.customlistview();
 //BA.debugLineNum = 33;BA.debugLine="Private pnlJOInfo As Panel";
mostCurrent._pnljoinfo = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Dim cdSearch As ColorDrawable";
mostCurrent._cdsearch = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 36;BA.debugLine="Private txtSearch As EditText";
mostCurrent._txtsearch = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private PopSubMenu As ACPopupMenu";
mostCurrent._popsubmenu = new de.amberhome.objects.appcompat.ACPopupMenuWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Type JOs (JOID As Int, JONum As String, JOCategor";
;
 //BA.debugLineNum = 41;BA.debugLine="Private JOCounts As Int";
_jocounts = 0;
 //BA.debugLineNum = 42;BA.debugLine="Private Limit As Int = 2000";
_limit = (int) (2000);
 //BA.debugLineNum = 43;BA.debugLine="Private IMEKeyboard As IME";
mostCurrent._imekeyboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 45;BA.debugLine="Private pnlStatus As Panel";
mostCurrent._pnlstatus = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private lblCount As Label";
mostCurrent._lblcount = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private Font As Typeface = Typeface.LoadFromAsset";
mostCurrent._font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont.ttf")));
 //BA.debugLineNum = 49;BA.debugLine="Private FontBold As Typeface = Typeface.LoadFromA";
mostCurrent._fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont_bold.ttf")));
 //BA.debugLineNum = 53;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 55;BA.debugLine="Private lblCustomer As Label";
mostCurrent._lblcustomer = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private lblDate As Label";
mostCurrent._lbldate = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private lblJONum As Label";
mostCurrent._lbljonum = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public static int  _hoursbetween(String _startdate,String _starttime,String _enddate,String _endtime) throws Exception{
long _s = 0L;
long _e = 0L;
 //BA.debugLineNum = 757;BA.debugLine="Sub HoursBetween(StartDate As String, StartTime As";
 //BA.debugLineNum = 758;BA.debugLine="Dim s, e As Long";
_s = 0L;
_e = 0L;
 //BA.debugLineNum = 759;BA.debugLine="s = ParseDateAndTime(StartDate, StartTime)";
_s = _parsedateandtime(_startdate,_starttime);
 //BA.debugLineNum = 760;BA.debugLine="e = ParseDateAndTime(EndDate, EndTime)";
_e = _parsedateandtime(_enddate,_endtime);
 //BA.debugLineNum = 761;BA.debugLine="Return (e - s) / DateTime.TicksPerHour";
if (true) return (int) ((_e-_s)/(double)anywheresoftware.b4a.keywords.Common.DateTime.TicksPerHour);
 //BA.debugLineNum = 762;BA.debugLine="End Sub";
return 0;
}
public static String  _jo_started_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 595;BA.debugLine="Private Sub JO_Started_OnNegativeClicked (View As";
 //BA.debugLineNum = 597;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 598;BA.debugLine="End Sub";
return "";
}
public static String  _jo_started_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 600;BA.debugLine="Private Sub JO_Started_OnPositiveClicked (View As";
 //BA.debugLineNum = 603;BA.debugLine="LogColor(GlobalVar.SelectedJOID, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("834209795",BA.NumberToString(mostCurrent._globalvar._selectedjoid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 605;BA.debugLine="If StartJO(GlobalVar.SelectedJOID) = True Then";
if (_startjo(mostCurrent._globalvar._selectedjoid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 606;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 607;BA.debugLine="FillJOList";
_filljolist();
 //BA.debugLineNum = 608;BA.debugLine="DispInfoMsg($\"Selected JO Started...\"$, $\"JO UPD";
_dispinfomsg(("Selected JO Started..."),("JO UPDATED"));
 }else {
 //BA.debugLineNum = 610;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 613;BA.debugLine="End Sub";
return "";
}
public static String  _jofontsizebinder_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 615;BA.debugLine="Private Sub JOFontSizeBinder_OnBindView (View As V";
 //BA.debugLineNum = 616;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 617;BA.debugLine="Alert.Initialize";
mostCurrent._alert.Initialize();
 //BA.debugLineNum = 618;BA.debugLine="If ViewType = Alert.VIEW_TITLE Then ' Title";
if (_viewtype==mostCurrent._alert.VIEW_TITLE) { 
 //BA.debugLineNum = 619;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 624;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 628;BA.debugLine="CS.Initialize.Typeface(Typeface.DEFAULT_BOLD).Ty";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe066)))+" "));
 //BA.debugLineNum = 629;BA.debugLine="CS.Typeface(Font).Size(22).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(mostCurrent._font.getObject())).Size((int) (22)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 631;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 633;BA.debugLine="If ViewType = Alert.VIEW_MESSAGE Then";
if (_viewtype==mostCurrent._alert.VIEW_MESSAGE) { 
 //BA.debugLineNum = 634;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 635;BA.debugLine="lbl.TextSize = 16";
_lbl.setTextSize((float) (16));
 //BA.debugLineNum = 636;BA.debugLine="lbl.TextColor = Colors.Gray";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 };
 //BA.debugLineNum = 639;BA.debugLine="If ViewType = Alert.VIEW_NEGATIVE Or ViewType = A";
if (_viewtype==mostCurrent._alert.VIEW_NEGATIVE || _viewtype==mostCurrent._alert.VIEW_POSITIVE) { 
 //BA.debugLineNum = 640;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 641;BA.debugLine="lbl.TextSize = 18";
_lbl.setTextSize((float) (18));
 };
 //BA.debugLineNum = 644;BA.debugLine="End Sub";
return "";
}
public static String  _messagebox_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 669;BA.debugLine="Private Sub MessageBox_OnPositiveClicked (View As";
 //BA.debugLineNum = 670;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 671;BA.debugLine="End Sub";
return "";
}
public static int  _minbetween(String _startdate,String _starttime,String _enddate,String _endtime) throws Exception{
long _s = 0L;
long _e = 0L;
 //BA.debugLineNum = 764;BA.debugLine="Sub MinBetween(StartDate As String, StartTime As S";
 //BA.debugLineNum = 765;BA.debugLine="Dim s, e As Long";
_s = 0L;
_e = 0L;
 //BA.debugLineNum = 766;BA.debugLine="s = ParseDateAndTime(StartDate, StartTime)";
_s = _parsedateandtime(_startdate,_starttime);
 //BA.debugLineNum = 767;BA.debugLine="e = ParseDateAndTime(EndDate, EndTime)";
_e = _parsedateandtime(_enddate,_endtime);
 //BA.debugLineNum = 768;BA.debugLine="Return (e - s) / DateTime.TicksPerMinute";
if (true) return (int) ((_e-_s)/(double)anywheresoftware.b4a.keywords.Common.DateTime.TicksPerMinute);
 //BA.debugLineNum = 769;BA.debugLine="End Sub";
return 0;
}
public static long  _parsedateandtime(String _d,String _t) throws Exception{
long _dd = 0L;
long _tt = 0L;
long _total = 0L;
 //BA.debugLineNum = 778;BA.debugLine="Sub ParseDateAndTime(d As String, t As String) As";
 //BA.debugLineNum = 779;BA.debugLine="Dim dd = DateTime.DateParse(d), tt = DateTime.Tim";
_dd = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(_d);
_tt = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_t);
 //BA.debugLineNum = 780;BA.debugLine="tt = (tt + DateTime.TimeZoneOffset * DateTime.Tic";
_tt = (long) ((_tt+anywheresoftware.b4a.keywords.Common.DateTime.getTimeZoneOffset()*anywheresoftware.b4a.keywords.Common.DateTime.TicksPerHour)%anywheresoftware.b4a.keywords.Common.DateTime.TicksPerDay);
 //BA.debugLineNum = 781;BA.debugLine="Dim total As Long";
_total = 0L;
 //BA.debugLineNum = 782;BA.debugLine="total = dd + tt + _       (DateTime.GetTimeZoneOf";
_total = (long) (_dd+_tt+(anywheresoftware.b4a.keywords.Common.DateTime.GetTimeZoneOffsetAt(_dd)-anywheresoftware.b4a.keywords.Common.DateTime.GetTimeZoneOffsetAt((long) (_dd+_tt)))*anywheresoftware.b4a.keywords.Common.DateTime.TicksPerHour);
 //BA.debugLineNum = 785;BA.debugLine="Return total";
if (true) return _total;
 //BA.debugLineNum = 786;BA.debugLine="End Sub";
return 0L;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static int  _secbetween(String _startdate,String _starttime,String _enddate,String _endtime) throws Exception{
long _s = 0L;
long _e = 0L;
 //BA.debugLineNum = 771;BA.debugLine="Sub SecBetween(StartDate As String, StartTime As S";
 //BA.debugLineNum = 772;BA.debugLine="Dim s, e As Long";
_s = 0L;
_e = 0L;
 //BA.debugLineNum = 773;BA.debugLine="s = ParseDateAndTime(StartDate, StartTime)";
_s = _parsedateandtime(_startdate,_starttime);
 //BA.debugLineNum = 774;BA.debugLine="e = ParseDateAndTime(EndDate, EndTime)";
_e = _parsedateandtime(_enddate,_endtime);
 //BA.debugLineNum = 775;BA.debugLine="Return (e - s) / DateTime.TicksPerSecond";
if (true) return (int) ((_e-_s)/(double)anywheresoftware.b4a.keywords.Common.DateTime.TicksPerSecond);
 //BA.debugLineNum = 776;BA.debugLine="End Sub";
return 0;
}
public static void  _showjodetails(int _iid,String _scatcode) throws Exception{
ResumableSub_ShowJODetails rsub = new ResumableSub_ShowJODetails(null,_iid,_scatcode);
rsub.resume(processBA, null);
}
public static class ResumableSub_ShowJODetails extends BA.ResumableSub {
public ResumableSub_ShowJODetails(bwsi.PumpOperations.actjonotification parent,int _iid,String _scatcode) {
this.parent = parent;
this._iid = _iid;
this._scatcode = _scatcode;
}
bwsi.PumpOperations.actjonotification parent;
int _iid;
String _scatcode;
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
Object _senderfilter = null;
String _sjono = "";
String _sjocatcode = "";
String _sjodesc = "";
String _srefno = "";
String _scustname = "";
String _scustadd = "";
String _sacctclass = "";
String _scontype = "";
String _sdatecreated = "";
String _sdatestart = "";
String _sdatefinished = "";
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
 //BA.debugLineNum = 482;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 483;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 484;BA.debugLine="Dim sJONo, sJOCatCode, sJODesc, sRefNo, sCustName";
_sjono = "";
_sjocatcode = "";
_sjodesc = "";
_srefno = "";
_scustname = "";
_scustadd = "";
_sacctclass = "";
_scontype = "";
 //BA.debugLineNum = 485;BA.debugLine="Dim sDateCreated, sDateStart, sDateFinished As St";
_sdatecreated = "";
_sdatestart = "";
_sdatefinished = "";
 //BA.debugLineNum = 488;BA.debugLine="sJONo = \"\"";
_sjono = "";
 //BA.debugLineNum = 489;BA.debugLine="sJODesc = \"\"";
_sjodesc = "";
 //BA.debugLineNum = 490;BA.debugLine="sRefNo = \"\"";
_srefno = "";
 //BA.debugLineNum = 491;BA.debugLine="sCustName = \"\"";
_scustname = "";
 //BA.debugLineNum = 492;BA.debugLine="sCustAdd = \"\"";
_scustadd = "";
 //BA.debugLineNum = 493;BA.debugLine="sAcctClass = \"\"";
_sacctclass = "";
 //BA.debugLineNum = 494;BA.debugLine="sDateCreated = \"\"";
_sdatecreated = "";
 //BA.debugLineNum = 495;BA.debugLine="sDateStart = \"\"";
_sdatestart = "";
 //BA.debugLineNum = 496;BA.debugLine="sDateFinished = \"\"";
_sdatefinished = "";
 //BA.debugLineNum = 497;BA.debugLine="sConType = \"\"";
_scontype = "";
 //BA.debugLineNum = 499;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
parent.mostCurrent._globalvar._tranheaderid /*int*/  = parent.mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,parent.mostCurrent._globalvar._pumphouseid /*int*/ ,parent.mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 500;BA.debugLine="Try";
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
 //BA.debugLineNum = 501;BA.debugLine="Starter.strCriteria = \"SELECT JO.JONo, JO.JoDesc";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT JO.JONo, JO.JoDesc, "+"JO.RefNo AS App_AcctNo, JO.CustName, JO.CustAddress, "+"JO.AcctClass, JO.AcctSubClass, JO.ConType, "+"JO.JOAssignedAt, JO.DateStarted, JO.DateFinished "+"FROM tblJOs AS JO "+"WHERE JO.JOID = "+BA.NumberToString(_iid);
 //BA.debugLineNum = 508;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 509;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 24;
return;
case 24:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 511;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 512;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 513;BA.debugLine="sJONo = RS.GetString(\"JONo\")";
_sjono = _rs.GetString("JONo");
 //BA.debugLineNum = 514;BA.debugLine="sJODesc = RS.GetString(\"JoDesc\")";
_sjodesc = _rs.GetString("JoDesc");
 //BA.debugLineNum = 515;BA.debugLine="sRefNo = RS.GetString(\"App_AcctNo\")";
_srefno = _rs.GetString("App_AcctNo");
 //BA.debugLineNum = 516;BA.debugLine="sCustName = RS.GetString(\"CustName\")";
_scustname = _rs.GetString("CustName");
 //BA.debugLineNum = 517;BA.debugLine="sCustAdd = RS.GetString(\"CustAddress\")";
_scustadd = _rs.GetString("CustAddress");
 //BA.debugLineNum = 518;BA.debugLine="sAcctClass = RS.GetString(\"AcctClass\") & \"-\" &";
_sacctclass = _rs.GetString("AcctClass")+"-"+_rs.GetString("AcctSubClass");
 //BA.debugLineNum = 519;BA.debugLine="sConType = RS.GetString(\"ConType\")";
_scontype = _rs.GetString("ConType");
 //BA.debugLineNum = 520;BA.debugLine="sDateCreated = RS.GetString(\"JOAssignedAt\")";
_sdatecreated = _rs.GetString("JOAssignedAt");
 //BA.debugLineNum = 521;BA.debugLine="sDateStart = RS.GetString(\"DateStarted\")";
_sdatestart = _rs.GetString("DateStarted");
 //BA.debugLineNum = 522;BA.debugLine="sDateFinished = RS.GetString(\"DateFinished\")";
_sdatefinished = _rs.GetString("DateFinished");
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 524;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 525;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 526;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 527;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 528;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("834013231",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
 //BA.debugLineNum = 532;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("834013235",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;
;
 //BA.debugLineNum = 535;BA.debugLine="If sCatCode = \"SAS\" Then";

case 12:
//if
this.state = 17;
this.catchState = 0;
if ((_scatcode).equals("SAS")) { 
this.state = 14;
}else {
this.state = 16;
}if (true) break;

case 14:
//C
this.state = 17;
 //BA.debugLineNum = 536;BA.debugLine="sJOCatCode = \"Application No. :\"";
_sjocatcode = "Application No. :";
 if (true) break;

case 16:
//C
this.state = 17;
 //BA.debugLineNum = 538;BA.debugLine="sJOCatCode = \"Account No. :\"";
_sjocatcode = "Account No. :";
 if (true) break;

case 17:
//C
this.state = 18;
;
 //BA.debugLineNum = 541;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar.";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (parent.mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(_scatcode+(" ")+("JO DETAILS"))).PopAll();
 //BA.debugLineNum = 543;BA.debugLine="If sCatCode = \"SAS\" Then";
if (true) break;

case 18:
//if
this.state = 23;
if ((_scatcode).equals("SAS")) { 
this.state = 20;
}else {
this.state = 22;
}if (true) break;

case 20:
//C
this.state = 23;
 //BA.debugLineNum = 544;BA.debugLine="MatDialogBuilder.Initialize(\"JODetails\")";
parent.mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"JODetails");
 //BA.debugLineNum = 545;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColo";
parent.mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (parent.mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 546;BA.debugLine="MatDialogBuilder.NeutralText(\"EDIT\").NeutralColo";
parent.mostCurrent._matdialogbuilder.NeutralText(BA.ObjectToCharSequence("EDIT")).NeutralColor((int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 547;BA.debugLine="MatDialogBuilder.Title(csTitle)";
parent.mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 548;BA.debugLine="MatDialogBuilder.Content($\"  JO No. : ${sJONo}";
parent.mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(("  JO No. : "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sjono))+"\n"+"		"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sjodesc))+"\n"+"		Application No. : "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_srefno))+"\n"+"		Customer Name : "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_scustname))+"\n"+"		Address : "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_scustadd))+"\n"+"		F I N D I N G S\n"+"		\n"+"		Account Class: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sacctclass))+"\n"+"		Connection Type: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_scontype))+"")));
 //BA.debugLineNum = 557;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LI";
parent.mostCurrent._matdialogbuilder.Theme(parent.mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 558;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
parent.mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 559;BA.debugLine="MatDialogBuilder.Cancelable(True)";
parent.mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 560;BA.debugLine="MatDialogBuilder.Show";
parent.mostCurrent._matdialogbuilder.Show();
 if (true) break;

case 22:
//C
this.state = 23;
 if (true) break;

case 23:
//C
this.state = -1;
;
 //BA.debugLineNum = 563;BA.debugLine="End Sub";
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
public static boolean  _startjo(int _ijoid) throws Exception{
boolean _bretval = false;
long _lngdatetime = 0L;
String _sdatestart = "";
 //BA.debugLineNum = 673;BA.debugLine="Private Sub StartJO (iJOID As Int) As Boolean";
 //BA.debugLineNum = 674;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 675;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 676;BA.debugLine="Dim sDateStart As String";
_sdatestart = "";
 //BA.debugLineNum = 678;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 679;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 680;BA.debugLine="DateTime.TimeFormat = \"hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm:ss a");
 //BA.debugLineNum = 681;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 682;BA.debugLine="sDateStart = DateTime.Date(lngDateTime) & $\" \"$ &";
_sdatestart = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime)+(" ")+anywheresoftware.b4a.keywords.Common.DateTime.Time(_lngdatetime);
 //BA.debugLineNum = 684;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 685;BA.debugLine="Try";
try { //BA.debugLineNum = 686;BA.debugLine="Starter.strCriteria = \"UPDATE tblJOs \" & _";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE tblJOs "+"SET JOStatus = ?, DateStarted = ? "+"WHERE JOID = "+BA.NumberToString(_ijoid);
 //BA.debugLineNum = 690;BA.debugLine="LogColor(Starter.strCriteria,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("834471953",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 691;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"2",_sdatestart}));
 //BA.debugLineNum = 692;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 694;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e17) {
			processBA.setLastException(e17); //BA.debugLineNum = 696;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 697;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("834471960",anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),0);
 };
 //BA.debugLineNum = 699;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 700;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 701;BA.debugLine="End Sub";
return false;
}
public static String  _statusdate(String _sjodate,String _sjotime) throws Exception{
String _sretval = "";
int _istatus = 0;
boolean _isday = false;
boolean _ishour = false;
boolean _ismin = false;
boolean _issec = false;
 //BA.debugLineNum = 732;BA.debugLine="Private Sub StatusDate (sJODate As String, sJOTime";
 //BA.debugLineNum = 733;BA.debugLine="Dim sRetVal As String";
_sretval = "";
 //BA.debugLineNum = 734;BA.debugLine="Dim iStatus As Int";
_istatus = 0;
 //BA.debugLineNum = 735;BA.debugLine="Dim isDay, isHour, isMin, isSec As Boolean";
_isday = false;
_ishour = false;
_ismin = false;
_issec = false;
 //BA.debugLineNum = 737;BA.debugLine="sRetVal = \"\"";
_sretval = "";
 //BA.debugLineNum = 738;BA.debugLine="iStatus = SecBetween(DateTime.Date(sJODate), Date";
_istatus = _secbetween(anywheresoftware.b4a.keywords.Common.DateTime.Date((long)(Double.parseDouble(_sjodate))),anywheresoftware.b4a.keywords.Common.DateTime.Time((long)(Double.parseDouble(_sjotime))),anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 740;BA.debugLine="If iStatus < 60 And iStatus > 1 Then";
if (_istatus<60 && _istatus>1) { 
 //BA.debugLineNum = 741;BA.debugLine="sRetVal = iStatus & \" seconds ago\"";
_sretval = BA.NumberToString(_istatus)+" seconds ago";
 }else if(_istatus<60 && _istatus==1) { 
 //BA.debugLineNum = 743;BA.debugLine="sRetVal = iStatus & \" second ago\"";
_sretval = BA.NumberToString(_istatus)+" second ago";
 }else if(_istatus>60 && _istatus<3600) { 
 //BA.debugLineNum = 745;BA.debugLine="sRetVal = (iStatus/3600) & \" minutes ago\"";
_sretval = BA.NumberToString((_istatus/(double)3600))+" minutes ago";
 };
 //BA.debugLineNum = 748;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 141;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 142;BA.debugLine="Select Item.Id";
switch (BA.switchObjectToInt(_item.getId(),(int) (1))) {
case 0: {
 //BA.debugLineNum = 144;BA.debugLine="PopSubMenu.Show";
mostCurrent._popsubmenu.Show();
 break; }
}
;
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 136;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 137;BA.debugLine="IMEKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 138;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 139;BA.debugLine="End Sub";
return "";
}
public static String  _txtsearch_enterpressed() throws Exception{
 //BA.debugLineNum = 477;BA.debugLine="Sub txtSearch_EnterPressed";
 //BA.debugLineNum = 479;BA.debugLine="End Sub";
return "";
}
public static void  _txtsearch_textchanged(String _old,String _new) throws Exception{
ResumableSub_txtSearch_TextChanged rsub = new ResumableSub_txtSearch_TextChanged(null,_old,_new);
rsub.resume(processBA, null);
}
public static class ResumableSub_txtSearch_TextChanged extends BA.ResumableSub {
public ResumableSub_txtSearch_TextChanged(bwsi.PumpOperations.actjonotification parent,String _old,String _new) {
this.parent = parent;
this._old = _old;
this._new = _new;
}
bwsi.PumpOperations.actjonotification parent;
String _old;
String _new;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actjonotification._jos _joinfo = null;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 422;BA.debugLine="If New.Length = 1 Or txtSearch.Text.Length = 2 Th";
if (true) break;

case 1:
//if
this.state = 6;
if (_new.length()==1 || parent.mostCurrent._txtsearch.getText().length()==2) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
if (true) return ;
if (true) break;

case 6:
//C
this.state = 7;
;
 //BA.debugLineNum = 423;BA.debugLine="clvJOs.Clear";
parent.mostCurrent._clvjos._clear();
 //BA.debugLineNum = 424;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (0));
this.state = 23;
return;
case 23:
//C
this.state = 7;
;
 //BA.debugLineNum = 426;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 427;BA.debugLine="If New.Length = 0  Then";
if (true) break;

case 7:
//if
this.state = 12;
if (_new.length()==0) { 
this.state = 9;
}else {
this.state = 11;
}if (true) break;

case 9:
//C
this.state = 12;
 //BA.debugLineNum = 428;BA.debugLine="JOCounts = Starter.DBCon.ExecQuerySingleResult(\"";
parent._jocounts = (int)(Double.parseDouble(parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM `tblJOs`;")));
 //BA.debugLineNum = 429;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblJOs \" &";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblJOs "+"WHERE WasUploaded = '"+BA.NumberToString(0)+"' "+"ORDER BY JOAssignedAt DESC";
 //BA.debugLineNum = 432;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("833882123",parent.mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 434;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 436;BA.debugLine="JOCounts = 0";
parent._jocounts = (int) (0);
 //BA.debugLineNum = 437;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",("SELECT * FROM `tblJOs` WHERE `JoDesc` Like '%"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_new))+"%' ORDER BY JOAssignedAt DESC LIMIT 500;"),(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 if (true) break;

case 12:
//C
this.state = 13;
;
 //BA.debugLineNum = 440;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succes";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 24;
return;
case 24:
//C
this.state = 13;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 441;BA.debugLine="If Success Then";
if (true) break;

case 13:
//if
this.state = 22;
if (_success) { 
this.state = 15;
}else {
this.state = 21;
}if (true) break;

case 15:
//C
this.state = 16;
 //BA.debugLineNum = 442;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 443;BA.debugLine="clvJOs.Clear";
parent.mostCurrent._clvjos._clear();
 //BA.debugLineNum = 444;BA.debugLine="Do While RS.NextRow";
if (true) break;

case 16:
//do while
this.state = 19;
while (_rs.NextRow()) {
this.state = 18;
if (true) break;
}
if (true) break;

case 18:
//C
this.state = 16;
 //BA.debugLineNum = 445;BA.debugLine="Dim JOInfo As JOs";
_joinfo = new bwsi.PumpOperations.actjonotification._jos();
 //BA.debugLineNum = 446;BA.debugLine="JOInfo.Initialize";
_joinfo.Initialize();
 //BA.debugLineNum = 447;BA.debugLine="JOInfo.JOID = RS.GetInt(\"JOID\")";
_joinfo.JOID /*int*/  = _rs.GetInt("JOID");
 //BA.debugLineNum = 448;BA.debugLine="JOInfo.JONum = GlobalVar.SF.Upper(RS.GetString(";
_joinfo.JONum /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JONo"));
 //BA.debugLineNum = 449;BA.debugLine="JOInfo.JOCatCode = GlobalVar.SF.Upper(RS.GetStr";
_joinfo.JOCatCode /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JOCatCode"));
 //BA.debugLineNum = 450;BA.debugLine="JOInfo.JOCategory = GlobalVar.SF.Upper(RS.GetSt";
_joinfo.JOCategory /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JoDesc"));
 //BA.debugLineNum = 451;BA.debugLine="JOInfo.RefID= RS.GetInt(\"RefID\")";
_joinfo.RefID /*int*/  = _rs.GetInt("RefID");
 //BA.debugLineNum = 452;BA.debugLine="JOInfo.RefNo= GlobalVar.SF.Upper(RS.GetString(\"";
_joinfo.RefNo /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("RefNo"));
 //BA.debugLineNum = 453;BA.debugLine="JOInfo.CustName = GlobalVar.SF.Upper(RS.GetStri";
_joinfo.CustName /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustName"));
 //BA.debugLineNum = 454;BA.debugLine="JOInfo.CustAdd = GlobalVar.SF.Upper(RS.GetStrin";
_joinfo.CustAdd /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustAddress"));
 //BA.debugLineNum = 455;BA.debugLine="JOInfo.AcctClass = GlobalVar.SF.Upper(RS.GetStr";
_joinfo.AcctClass /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctClass"));
 //BA.debugLineNum = 456;BA.debugLine="JOInfo.AcctSubClass = GlobalVar.SF.Upper(RS.Get";
_joinfo.AcctSubClass /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctSubClass"));
 //BA.debugLineNum = 457;BA.debugLine="JOInfo.JOStatus = RS.GetInt(\"JOStatus\")";
_joinfo.JOStatus /*int*/  = _rs.GetInt("JOStatus");
 //BA.debugLineNum = 458;BA.debugLine="JOInfo.iWasRead = RS.GetInt(\"WasRead\")";
_joinfo.iWasRead /*int*/  = _rs.GetInt("WasRead");
 //BA.debugLineNum = 459;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 460;BA.debugLine="Pnl.SetLayoutAnimated(0, 5dip, 0, clvJOs.AsView";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (0),parent.mostCurrent._clvjos._asview().getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
 //BA.debugLineNum = 461;BA.debugLine="clvJOs.Add(Pnl, JOInfo)";
parent.mostCurrent._clvjos._add(_pnl,(Object)(_joinfo));
 if (true) break;

case 19:
//C
this.state = 22;
;
 //BA.debugLineNum = 463;BA.debugLine="RS.Close";
_rs.Close();
 //BA.debugLineNum = 464;BA.debugLine="lblCount.Text = RS.RowCount & $\" Record(s) Found";
parent.mostCurrent._lblcount.setText(BA.ObjectToCharSequence(BA.NumberToString(_rs.getRowCount())+(" Record(s) Found")));
 if (true) break;

case 21:
//C
this.state = 22;
 //BA.debugLineNum = 466;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 467;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 468;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 469;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 470;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("833882161",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 22:
//C
this.state = -1;
;
 //BA.debugLineNum = 473;BA.debugLine="Log($\"List of Job Order Records = ${NumberFormat2";
anywheresoftware.b4a.keywords.Common.LogImpl("833882164",("List of Job Order Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvjos._getsize()))+" Job Order Records"),0);
 //BA.debugLineNum = 475;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static boolean  _updatetoreadjo(int _ijoid) throws Exception{
boolean _bretval = false;
 //BA.debugLineNum = 709;BA.debugLine="Private Sub UpdateToReadJO (iJOID As Int) As Boole";
 //BA.debugLineNum = 710;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 712;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 713;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 714;BA.debugLine="Try";
try { //BA.debugLineNum = 715;BA.debugLine="Starter.strCriteria = \"UPDATE tblJOs \" & _";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE tblJOs "+"SET WasRead = ? "+"WHERE JOID = "+BA.NumberToString(_ijoid);
 //BA.debugLineNum = 719;BA.debugLine="LogColor(Starter.strCriteria,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("834603018",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 720;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"1"}));
 //BA.debugLineNum = 721;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 723;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e11) {
			processBA.setLastException(e11); //BA.debugLineNum = 725;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 726;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("834603025",anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),0);
 };
 //BA.debugLineNum = 728;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 729;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 730;BA.debugLine="End Sub";
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
