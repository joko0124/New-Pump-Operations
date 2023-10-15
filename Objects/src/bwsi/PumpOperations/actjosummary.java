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

public class actjosummary extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static actjosummary mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.actjosummary");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actjosummary).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.actjosummary");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.actjosummary", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actjosummary) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actjosummary) Resume **");
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
		return actjosummary.class;
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
            BA.LogInfo("** Activity (actjosummary) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (actjosummary) Pause event (activity is not paused). **");
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
            actjosummary mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (actjosummary) Resume **");
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
public b4a.example3.customlistview _clvjos = null;
public anywheresoftware.b4a.objects.IME _imekeyboard = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdsearch = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtsearch = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcount = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbljocatdesc = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblpending = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblongoing = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblaccomplished = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcancelled = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltotals = null;
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
public static class _joreason{
public boolean IsInitialized;
public String JOCatCode;
public String JODesc;
public int TotalPending;
public int TotalOnGoing;
public int TotalAccomplished;
public int TotalCancelled;
public int JOTotals;
public void Initialize() {
IsInitialized = true;
JOCatCode = "";
JODesc = "";
TotalPending = 0;
TotalOnGoing = 0;
TotalAccomplished = 0;
TotalCancelled = 0;
JOTotals = 0;
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
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 47;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 48;BA.debugLine="Activity.LoadLayout(\"JOSummaryList\")";
mostCurrent._activity.LoadLayout("JOSummaryList",mostCurrent.activityBA);
 //BA.debugLineNum = 50;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Append";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("Job Order Summary"))).PopAll();
 //BA.debugLineNum = 51;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append($";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("List of Assigned Job Orders"))).PopAll();
 //BA.debugLineNum = 53;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 54;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 55;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 57;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 58;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 59;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 60;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 61;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 62;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 64;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 65;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 67;BA.debugLine="cdSearch.Initialize(Colors.Transparent, 0)";
mostCurrent._cdsearch.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 68;BA.debugLine="txtSearch.Background = cdSearch";
mostCurrent._txtsearch.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdsearch.getObject()));
 //BA.debugLineNum = 70;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 71;BA.debugLine="imeKeyboard.Initialize(\"\")";
mostCurrent._imekeyboard.Initialize("");
 //BA.debugLineNum = 72;BA.debugLine="FillJOList(GlobalVar.UserID, GlobalVar.BranchID)";
_filljolist(mostCurrent._globalvar._userid /*int*/ ,mostCurrent._globalvar._branchid /*int*/ );
 };
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 77;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 78;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 79;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 80;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 82;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 93;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 86;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 87;BA.debugLine="txtSearch.Text = \"\"";
mostCurrent._txtsearch.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 88;BA.debugLine="FillJOList(GlobalVar.UserID, GlobalVar.BranchID)";
_filljolist(mostCurrent._globalvar._userid /*int*/ ,mostCurrent._globalvar._branchid /*int*/ );
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
public static String  _clvjos_itemclick(int _index,Object _value) throws Exception{
bwsi.PumpOperations.actjosummary._joreason _jocount = null;
 //BA.debugLineNum = 173;BA.debugLine="Sub clvJOs_ItemClick (Index As Int, Value As Objec";
 //BA.debugLineNum = 174;BA.debugLine="Dim JOCount As JOReason = Value";
_jocount = (bwsi.PumpOperations.actjosummary._joreason)(_value);
 //BA.debugLineNum = 176;BA.debugLine="LogColor(Value, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("036896771",BA.ObjectToString(_value),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 177;BA.debugLine="LogColor(JOCount.JOCatCode, Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogImpl("036896772",_jocount.JOCatCode /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 178;BA.debugLine="LogColor(JOCount.JODesc, Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("036896773",_jocount.JODesc /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 179;BA.debugLine="LogColor(JOCount.JOTotals, Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogImpl("036896774",BA.NumberToString(_jocount.JOTotals /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 181;BA.debugLine="GlobalVar.SelectedJOCatCode = JOCount.JOCatCode";
mostCurrent._globalvar._selectedjocatcode /*String*/  = _jocount.JOCatCode /*String*/ ;
 //BA.debugLineNum = 182;BA.debugLine="GlobalVar.SelectedJODesc = GlobalVar.SF.Proper(JO";
mostCurrent._globalvar._selectedjodesc /*String*/  = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvv5(_jocount.JODesc /*String*/ );
 //BA.debugLineNum = 184;BA.debugLine="Select Case GlobalVar.SelectedJOCatCode";
switch (BA.switchObjectToInt(mostCurrent._globalvar._selectedjocatcode /*String*/ ,"IC","MC","RM","SL","SV")) {
case 0: 
case 1: 
case 2: 
case 3: 
case 4: {
 //BA.debugLineNum = 186;BA.debugLine="StartActivity(actJOWithReasons)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actjowithreasons.getObject()));
 break; }
default: {
 //BA.debugLineNum = 188;BA.debugLine="StartActivity(actJO)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actjo.getObject()));
 break; }
}
;
 //BA.debugLineNum = 190;BA.debugLine="End Sub";
return "";
}
public static String  _clvjos_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
int _extrasize = 0;
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
bwsi.PumpOperations.actjosummary._joreason _jorec = null;
 //BA.debugLineNum = 192;BA.debugLine="Sub clvJOs_VisibleRangeChanged (FirstIndex As Int,";
 //BA.debugLineNum = 193;BA.debugLine="Dim ExtraSize As Int = 15 'List size";
_extrasize = (int) (15);
 //BA.debugLineNum = 194;BA.debugLine="clvJOs.Refresh";
mostCurrent._clvjos._refresh();
 //BA.debugLineNum = 196;BA.debugLine="For i = Max(0, FirstIndex - ExtraSize) To Min(Las";
{
final int step3 = 1;
final int limit3 = (int) (anywheresoftware.b4a.keywords.Common.Min(_lastindex+_extrasize,mostCurrent._clvjos._getsize()-1));
_i = (int) (anywheresoftware.b4a.keywords.Common.Max(0,_firstindex-_extrasize)) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 197;BA.debugLine="Dim Pnl As B4XView = clvJOs.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._clvjos._getpanel(_i);
 //BA.debugLineNum = 198;BA.debugLine="If i > FirstIndex - ExtraSize And i < LastIndex";
if (_i>_firstindex-_extrasize && _i<_lastindex+_extrasize) { 
 //BA.debugLineNum = 199;BA.debugLine="If Pnl.NumberOfViews = 0 Then 'Add each item/la";
if (_pnl.getNumberOfViews()==0) { 
 //BA.debugLineNum = 200;BA.debugLine="Dim JORec As JOReason = clvJOs.GetValue(i)";
_jorec = (bwsi.PumpOperations.actjosummary._joreason)(mostCurrent._clvjos._getvalue(_i));
 //BA.debugLineNum = 201;BA.debugLine="Pnl.LoadLayout(\"JOCategories\")";
_pnl.LoadLayout("JOCategories",mostCurrent.activityBA);
 //BA.debugLineNum = 203;BA.debugLine="lblJOCatDesc.Text = JORec.JODesc";
mostCurrent._lbljocatdesc.setText(BA.ObjectToCharSequence(_jorec.JODesc /*String*/ ));
 //BA.debugLineNum = 204;BA.debugLine="lblPending.Text = JORec.TotalPending";
mostCurrent._lblpending.setText(BA.ObjectToCharSequence(_jorec.TotalPending /*int*/ ));
 //BA.debugLineNum = 205;BA.debugLine="lblOnGoing.Text = JORec.TotalOnGoing";
mostCurrent._lblongoing.setText(BA.ObjectToCharSequence(_jorec.TotalOnGoing /*int*/ ));
 //BA.debugLineNum = 206;BA.debugLine="lblAccomplished.Text = JORec.TotalAccomplished";
mostCurrent._lblaccomplished.setText(BA.ObjectToCharSequence(_jorec.TotalAccomplished /*int*/ ));
 //BA.debugLineNum = 207;BA.debugLine="lblCancelled.Text = JORec.TotalCancelled";
mostCurrent._lblcancelled.setText(BA.ObjectToCharSequence(_jorec.TotalCancelled /*int*/ ));
 //BA.debugLineNum = 208;BA.debugLine="lblTotals.Text = JORec.JOTotals";
mostCurrent._lbltotals.setText(BA.ObjectToCharSequence(_jorec.JOTotals /*int*/ ));
 //BA.debugLineNum = 210;BA.debugLine="If JORec.TotalPending > 0 Then";
if (_jorec.TotalPending /*int*/ >0) { 
 //BA.debugLineNum = 211;BA.debugLine="lblPending.TextColor = GlobalVar.RedColor";
mostCurrent._lblpending.setTextColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 }else {
 //BA.debugLineNum = 213;BA.debugLine="lblPending.TextColor = GlobalVar.BlueColor";
mostCurrent._lblpending.setTextColor((int) (mostCurrent._globalvar._bluecolor /*double*/ ));
 };
 //BA.debugLineNum = 216;BA.debugLine="If JORec.TotalOnGoing > 0 Then";
if (_jorec.TotalOnGoing /*int*/ >0) { 
 //BA.debugLineNum = 217;BA.debugLine="lblOnGoing.TextColor = GlobalVar.RedColor";
mostCurrent._lblongoing.setTextColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 }else {
 //BA.debugLineNum = 219;BA.debugLine="lblOnGoing.TextColor = GlobalVar.BlueColor";
mostCurrent._lblongoing.setTextColor((int) (mostCurrent._globalvar._bluecolor /*double*/ ));
 };
 //BA.debugLineNum = 222;BA.debugLine="If JORec.TotalAccomplished <= 0 Then";
if (_jorec.TotalAccomplished /*int*/ <=0) { 
 //BA.debugLineNum = 223;BA.debugLine="lblAccomplished.TextColor = GlobalVar.RedColo";
mostCurrent._lblaccomplished.setTextColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 }else {
 //BA.debugLineNum = 225;BA.debugLine="lblAccomplished.TextColor = GlobalVar.BlueCol";
mostCurrent._lblaccomplished.setTextColor((int) (mostCurrent._globalvar._bluecolor /*double*/ ));
 };
 //BA.debugLineNum = 228;BA.debugLine="If JORec.TotalCancelled > 0 Then";
if (_jorec.TotalCancelled /*int*/ >0) { 
 //BA.debugLineNum = 229;BA.debugLine="lblCancelled.TextColor = GlobalVar.RedColor";
mostCurrent._lblcancelled.setTextColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 }else {
 //BA.debugLineNum = 231;BA.debugLine="lblCancelled.TextColor = GlobalVar.BlueColor";
mostCurrent._lblcancelled.setTextColor((int) (mostCurrent._globalvar._bluecolor /*double*/ ));
 };
 //BA.debugLineNum = 234;BA.debugLine="If JORec.JOTotals > 0 Then";
if (_jorec.JOTotals /*int*/ >0) { 
 //BA.debugLineNum = 235;BA.debugLine="lblTotals.TextColor = GlobalVar.RedColor";
mostCurrent._lbltotals.setTextColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 }else {
 //BA.debugLineNum = 237;BA.debugLine="lblTotals.TextColor = GlobalVar.BlueColor";
mostCurrent._lbltotals.setTextColor((int) (mostCurrent._globalvar._bluecolor /*double*/ ));
 };
 };
 }else {
 //BA.debugLineNum = 242;BA.debugLine="If Pnl.NumberOfViews > 0 Then";
if (_pnl.getNumberOfViews()>0) { 
 //BA.debugLineNum = 243;BA.debugLine="Pnl.RemoveAllViews 'Remove none visable item/l";
_pnl.RemoveAllViews();
 };
 };
 }
};
 //BA.debugLineNum = 247;BA.debugLine="End Sub";
return "";
}
public static void  _filljolist(int _iuserid,int _ibranchid) throws Exception{
ResumableSub_FillJOList rsub = new ResumableSub_FillJOList(null,_iuserid,_ibranchid);
rsub.resume(processBA, null);
}
public static class ResumableSub_FillJOList extends BA.ResumableSub {
public ResumableSub_FillJOList(bwsi.PumpOperations.actjosummary parent,int _iuserid,int _ibranchid) {
this.parent = parent;
this._iuserid = _iuserid;
this._ibranchid = _ibranchid;
}
bwsi.PumpOperations.actjosummary parent;
int _iuserid;
int _ibranchid;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actjosummary._joreason _jocount = null;
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
 //BA.debugLineNum = 119;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 121;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 21;
this.catchState = 20;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 20;
 //BA.debugLineNum = 122;BA.debugLine="Starter.strCriteria = \"SELECT sum(CASE WHEN JOs.";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT sum(CASE WHEN JOs.JOStatus = 1 THEN 1 ELSE 0 END) AS PendingJOs, "+"sum(CASE WHEN JOs.JOStatus = 2 THEN 1 ELSE 0 END) AS OnGoingJOs, "+"sum(CASE WHEN JOs.JOStatus = 3 THEN 1 ELSE 0 END) AS AccomplishedJOs, "+"sum(CASE WHEN JOs.JOStatus = 4 THEN 1 ELSE 0 END) AS CancelledJOs, "+"count(JOs.JOCatCode) AS TotalJOs, JOs.JOCatCode, JOCat.jo_desc "+"FROM tblJOs AS JOs "+"INNER JOIN constant_jo_categories AS JOCat ON JOs.JOCatCode = JOCat.jo_code "+"WHERE JOs.JOAssignedTo = "+BA.NumberToString(_iuserid)+" "+"AND JOs.BranchID = "+BA.NumberToString(_ibranchid)+" "+"GROUP BY JOs.JOCatCode "+"ORDER BY JOCat.id ASC";
 //BA.debugLineNum = 133;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("036831247",parent.mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 135;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 136;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 22;
return;
case 22:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 137;BA.debugLine="If Success Then";
if (true) break;

case 4:
//if
this.state = 18;
if (_success) { 
this.state = 6;
}else {
this.state = 17;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 138;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 139;BA.debugLine="clvJOs.Clear";
parent.mostCurrent._clvjos._clear();
 //BA.debugLineNum = 140;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 141;BA.debugLine="Dim JOCount As JOReason";
_jocount = new bwsi.PumpOperations.actjosummary._joreason();
 //BA.debugLineNum = 142;BA.debugLine="JOCount.Initialize";
_jocount.Initialize();
 //BA.debugLineNum = 143;BA.debugLine="JOCount.TotalPending = RS.GetInt(\"PendingJOs\")";
_jocount.TotalPending /*int*/  = _rs.GetInt("PendingJOs");
 //BA.debugLineNum = 144;BA.debugLine="JOCount.TotalOnGoing = RS.GetInt(\"OnGoingJOs\")";
_jocount.TotalOnGoing /*int*/  = _rs.GetInt("OnGoingJOs");
 //BA.debugLineNum = 145;BA.debugLine="JOCount.TotalAccomplished = RS.GetInt(\"Accompl";
_jocount.TotalAccomplished /*int*/  = _rs.GetInt("AccomplishedJOs");
 //BA.debugLineNum = 146;BA.debugLine="JOCount.TotalCancelled = RS.GetInt(\"CancelledJ";
_jocount.TotalCancelled /*int*/  = _rs.GetInt("CancelledJOs");
 //BA.debugLineNum = 147;BA.debugLine="JOCount.JOTotals = RS.GetInt(\"TotalJOs\")";
_jocount.JOTotals /*int*/  = _rs.GetInt("TotalJOs");
 //BA.debugLineNum = 148;BA.debugLine="JOCount.JOCatCode = GlobalVar.SF.Upper(RS.GetS";
_jocount.JOCatCode /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JOCatCode"));
 //BA.debugLineNum = 149;BA.debugLine="JOCount.JODesc = RS.GetString(\"jo_desc\")";
_jocount.JODesc /*String*/  = _rs.GetString("jo_desc");
 //BA.debugLineNum = 150;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 151;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0, clvJOs.AsVi";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),parent.mostCurrent._clvjos._asview().getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
 //BA.debugLineNum = 152;BA.debugLine="clvJOs.Add(Pnl, JOCount)";
parent.mostCurrent._clvjos._add(_pnl,(Object)(_jocount));
 if (true) break;
;
 //BA.debugLineNum = 154;BA.debugLine="If JOCount.JOTotals > 1 Then";

case 10:
//if
this.state = 15;
if (_jocount.JOTotals /*int*/ >1) { 
this.state = 12;
}else {
this.state = 14;
}if (true) break;

case 12:
//C
this.state = 15;
 //BA.debugLineNum = 155;BA.debugLine="lblCount.Text = RS.RowCount & $\" Job Order Cat";
parent.mostCurrent._lblcount.setText(BA.ObjectToCharSequence(BA.NumberToString(_rs.getRowCount())+(" Job Order Categories Found")));
 if (true) break;

case 14:
//C
this.state = 15;
 //BA.debugLineNum = 157;BA.debugLine="lblCount.Text = RS.RowCount & $\" Job Order Cat";
parent.mostCurrent._lblcount.setText(BA.ObjectToCharSequence(BA.NumberToString(_rs.getRowCount())+(" Job Order Category Found")));
 if (true) break;

case 15:
//C
this.state = 18;
;
 if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 160;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("036831274",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 18:
//C
this.state = 21;
;
 //BA.debugLineNum = 163;BA.debugLine="Log($\"List of Job Order Records = ${NumberFormat";
anywheresoftware.b4a.keywords.Common.LogImpl("036831277",("List of Job Order Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvjos._getsize()))+" Job Order Records"),0);
 if (true) break;

case 20:
//C
this.state = 21;
this.catchState = 0;
 //BA.debugLineNum = 165;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("036831279",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 21:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 23;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 25;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private clvJOs As CustomListView";
mostCurrent._clvjos = new b4a.example3.customlistview();
 //BA.debugLineNum = 28;BA.debugLine="Private imeKeyboard As IME";
mostCurrent._imekeyboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 30;BA.debugLine="Dim cdSearch As ColorDrawable";
mostCurrent._cdsearch = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 31;BA.debugLine="Private txtSearch As EditText";
mostCurrent._txtsearch = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Type JOReason (JOCatCode As String, JODesc As Str";
;
 //BA.debugLineNum = 34;BA.debugLine="Private lblCount As Label";
mostCurrent._lblcount = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private lblJOCatDesc As Label";
mostCurrent._lbljocatdesc = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private lblPending As Label";
mostCurrent._lblpending = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private lblOnGoing As Label";
mostCurrent._lblongoing = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private lblAccomplished As Label";
mostCurrent._lblaccomplished = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private lblCancelled As Label";
mostCurrent._lblcancelled = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private lblTotals As Label";
mostCurrent._lbltotals = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 107;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 108;BA.debugLine="imeKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 109;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _txtsearch_enterpressed() throws Exception{
 //BA.debugLineNum = 316;BA.debugLine="Sub txtSearch_EnterPressed";
 //BA.debugLineNum = 318;BA.debugLine="End Sub";
return "";
}
public static void  _txtsearch_textchanged(String _old,String _new) throws Exception{
ResumableSub_txtSearch_TextChanged rsub = new ResumableSub_txtSearch_TextChanged(null,_old,_new);
rsub.resume(processBA, null);
}
public static class ResumableSub_txtSearch_TextChanged extends BA.ResumableSub {
public ResumableSub_txtSearch_TextChanged(bwsi.PumpOperations.actjosummary parent,String _old,String _new) {
this.parent = parent;
this._old = _old;
this._new = _new;
}
bwsi.PumpOperations.actjosummary parent;
String _old;
String _new;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actjosummary._joreason _jocount = null;
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
 //BA.debugLineNum = 250;BA.debugLine="If New.Length = 1 Or txtSearch.Text.Length = 2 Th";
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
 //BA.debugLineNum = 251;BA.debugLine="clvJOs.Clear";
parent.mostCurrent._clvjos._clear();
 //BA.debugLineNum = 252;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (0));
this.state = 28;
return;
case 28:
//C
this.state = 7;
;
 //BA.debugLineNum = 254;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 255;BA.debugLine="If New.Length = 0  Then";
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
 //BA.debugLineNum = 256;BA.debugLine="Starter.strCriteria = \"SELECT sum(CASE WHEN JOs.";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT sum(CASE WHEN JOs.JOStatus = 1 THEN 1 ELSE 0 END) AS PendingJOs, "+"sum(CASE WHEN JOs.JOStatus = 2 THEN 1 ELSE 0 END) AS OnGoingJOs, "+"sum(CASE WHEN JOs.JOStatus = 3 THEN 1 ELSE 0 END) AS AccomplishedJOs, "+"sum(CASE WHEN JOs.JOStatus = 4 THEN 1 ELSE 0 END) AS CancelledJOs, "+"count(JOs.JOCatCode) AS TotalJOs, JOs.JOCatCode, JOCat.jo_desc "+"FROM tblJOs AS JOs "+"INNER JOIN constant_jo_categories AS JOCat ON JOs.JOCatCode = JOCat.jo_code "+"WHERE JOs.JOAssignedTo = "+BA.NumberToString(parent.mostCurrent._globalvar._userid /*int*/ )+" "+"AND JOs.BranchID = "+BA.NumberToString(parent.mostCurrent._globalvar._branchid /*int*/ )+" "+"GROUP BY JOs.JOCatCode "+"ORDER BY JOCat.id ASC LIMIT 100";
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 268;BA.debugLine="Starter.strCriteria = \"SELECT sum(CASE WHEN JOs.";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT sum(CASE WHEN JOs.JOStatus = 1 THEN 1 ELSE 0 END) AS PendingJOs, "+"sum(CASE WHEN JOs.JOStatus = 2 THEN 1 ELSE 0 END) AS OnGoingJOs, "+"sum(CASE WHEN JOs.JOStatus = 3 THEN 1 ELSE 0 END) AS AccomplishedJOs, "+"sum(CASE WHEN JOs.JOStatus = 4 THEN 1 ELSE 0 END) AS CancelledJOs, "+"count(JOs.JOCatCode) AS TotalJOs, JOs.JOCatCode, JOCat.jo_desc "+"FROM tblJOs AS JOs "+"INNER JOIN constant_jo_categories AS JOCat ON JOs.JOCatCode = JOCat.jo_code "+"WHERE JOs.JOAssignedTo = "+BA.NumberToString(parent.mostCurrent._globalvar._userid /*int*/ )+" "+"AND JOs.BranchID = "+BA.NumberToString(parent.mostCurrent._globalvar._branchid /*int*/ )+" "+"AND JOCat.jo_desc LIKE '%"+_new+"%' "+"GROUP BY JOs.JOCatCode "+"ORDER BY JOCat.id ASC LIMIT 100";
 if (true) break;

case 12:
//C
this.state = 13;
;
 //BA.debugLineNum = 282;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("037027873",parent.mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 283;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL\"";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 285;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succes";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 29;
return;
case 29:
//C
this.state = 13;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 286;BA.debugLine="If Success Then";
if (true) break;

case 13:
//if
this.state = 27;
if (_success) { 
this.state = 15;
}else {
this.state = 26;
}if (true) break;

case 15:
//C
this.state = 16;
 //BA.debugLineNum = 287;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 288;BA.debugLine="clvJOs.Clear";
parent.mostCurrent._clvjos._clear();
 //BA.debugLineNum = 289;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 290;BA.debugLine="Dim JOCount As JOReason";
_jocount = new bwsi.PumpOperations.actjosummary._joreason();
 //BA.debugLineNum = 291;BA.debugLine="JOCount.Initialize";
_jocount.Initialize();
 //BA.debugLineNum = 292;BA.debugLine="JOCount.TotalPending = RS.GetInt(\"PendingJOs\")";
_jocount.TotalPending /*int*/  = _rs.GetInt("PendingJOs");
 //BA.debugLineNum = 293;BA.debugLine="JOCount.TotalOnGoing = RS.GetInt(\"OnGoingJOs\")";
_jocount.TotalOnGoing /*int*/  = _rs.GetInt("OnGoingJOs");
 //BA.debugLineNum = 294;BA.debugLine="JOCount.TotalAccomplished = RS.GetInt(\"Accompli";
_jocount.TotalAccomplished /*int*/  = _rs.GetInt("AccomplishedJOs");
 //BA.debugLineNum = 295;BA.debugLine="JOCount.TotalCancelled = RS.GetInt(\"CancelledJO";
_jocount.TotalCancelled /*int*/  = _rs.GetInt("CancelledJOs");
 //BA.debugLineNum = 296;BA.debugLine="JOCount.JOTotals = RS.GetInt(\"TotalJOs\")";
_jocount.JOTotals /*int*/  = _rs.GetInt("TotalJOs");
 //BA.debugLineNum = 297;BA.debugLine="JOCount.JOCatCode = GlobalVar.SF.Upper(RS.GetSt";
_jocount.JOCatCode /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JOCatCode"));
 //BA.debugLineNum = 298;BA.debugLine="JOCount.JODesc = GlobalVar.SF.Upper(RS.GetStrin";
_jocount.JODesc /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("jo_desc"));
 //BA.debugLineNum = 299;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 300;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0, clvJOs.AsVie";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),parent.mostCurrent._clvjos._asview().getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
 //BA.debugLineNum = 301;BA.debugLine="clvJOs.Add(Pnl, JOCount)";
parent.mostCurrent._clvjos._add(_pnl,(Object)(_jocount));
 if (true) break;
;
 //BA.debugLineNum = 303;BA.debugLine="If JOCount.JOTotals > 1 Then";

case 19:
//if
this.state = 24;
if (_jocount.JOTotals /*int*/ >1) { 
this.state = 21;
}else {
this.state = 23;
}if (true) break;

case 21:
//C
this.state = 24;
 //BA.debugLineNum = 304;BA.debugLine="lblCount.Text = RS.RowCount & $\" Job Order Cate";
parent.mostCurrent._lblcount.setText(BA.ObjectToCharSequence(BA.NumberToString(_rs.getRowCount())+(" Job Order Categories Found")));
 if (true) break;

case 23:
//C
this.state = 24;
 //BA.debugLineNum = 306;BA.debugLine="lblCount.Text = RS.RowCount & $\" Job Order Categ";
parent.mostCurrent._lblcount.setText(BA.ObjectToCharSequence(BA.NumberToString(_rs.getRowCount())+(" Job Order Category Found")));
 if (true) break;

case 24:
//C
this.state = 27;
;
 if (true) break;

case 26:
//C
this.state = 27;
 //BA.debugLineNum = 309;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("037027900",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 27:
//C
this.state = -1;
;
 //BA.debugLineNum = 312;BA.debugLine="Log($\"List of Job Order Records = ${NumberFormat2";
anywheresoftware.b4a.keywords.Common.LogImpl("037027903",("List of Job Order Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvjos._getsize()))+" Job Order Records"),0);
 //BA.debugLineNum = 314;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
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
