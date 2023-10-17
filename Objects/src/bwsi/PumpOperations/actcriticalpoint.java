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

public class actcriticalpoint extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static actcriticalpoint mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.actcriticalpoint");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actcriticalpoint).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.actcriticalpoint");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.actcriticalpoint", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actcriticalpoint) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actcriticalpoint) Resume **");
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
		return actcriticalpoint.class;
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
            BA.LogInfo("** Activity (actcriticalpoint) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (actcriticalpoint) Pause event (activity is not paused). **");
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
            actcriticalpoint mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (actcriticalpoint) Resume **");
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
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
public com.johan.Vibrate.Vibrate _vibration = null;
public static long[] _vibratepattern = null;
public de.amberhome.objects.SnackbarWrapper _snack = null;
public anywheresoftware.b4a.objects.CSBuilder _csans = null;
public anywheresoftware.b4a.objects.IME _kboard = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _pnlpsidist = null;
public static long _thedate = 0L;
public b4a.example3.customlistview _clvlist = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lbldisttimeread = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lbllocation = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lbldistpsirdg = null;
public de.amberhome.objects.FloatingActionButtonWrapper _btnaddpsidist = null;
public bwsi.PumpOperations.bctoast _mytoast = null;
public de.amberhome.objects.appcompat.ACPopupMenuWrapper _popsubmenu = null;
public b4a.example.dateutils _dateutils = null;
public bwsi.PumpOperations.main _main = null;
public bwsi.PumpOperations.actnewproduction _actnewproduction = null;
public bwsi.PumpOperations.addedittimerecord _addedittimerecord = null;
public bwsi.PumpOperations.mainscreen _mainscreen = null;
public bwsi.PumpOperations.actcmjofindings _actcmjofindings = null;
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
public bwsi.PumpOperations.addeditpsidistrecord _addeditpsidistrecord = null;
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
public static class _pressuredistrecords{
public boolean IsInitialized;
public int ID;
public String sPSIPoint;
public String sPSIDistRdgTime;
public String sPSIDistLoc;
public int iPSIDistRdg;
public void Initialize() {
IsInitialized = true;
ID = 0;
sPSIPoint = "";
sPSIDistRdgTime = "";
sPSIDistLoc = "";
iPSIDistRdg = 0;
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
 //BA.debugLineNum = 52;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 53;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 54;BA.debugLine="Activity.LoadLayout(\"PSIRdgDistribution\")";
mostCurrent._activity.LoadLayout("PSIRdgDistribution",mostCurrent.activityBA);
 //BA.debugLineNum = 60;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Append";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(("PUMP - ")+mostCurrent._globalvar._pumphousecode /*String*/ )).PopAll();
 //BA.debugLineNum = 61;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append($";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("Transaction Date: ")+mostCurrent._globalvar._trandate /*String*/ )).PopAll();
 //BA.debugLineNum = 63;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 64;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 65;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 67;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 68;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 69;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 70;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 71;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 72;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 74;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 75;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 77;BA.debugLine="kboard.Initialize(\"KeyBoard\")";
mostCurrent._kboard.Initialize("KeyBoard");
 //BA.debugLineNum = 78;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 };
 //BA.debugLineNum = 81;BA.debugLine="MyToast.Initialize(Activity)";
mostCurrent._mytoast._initialize /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())));
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
de.amberhome.objects.appcompat.ACMenuItemWrapper _item = null;
 //BA.debugLineNum = 104;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 105;BA.debugLine="Dim Item As ACMenuItem";
_item = new de.amberhome.objects.appcompat.ACMenuItemWrapper();
 //BA.debugLineNum = 107;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 108;BA.debugLine="Menu.Add2(1, 1, \"Filter by\",xmlIcon.GetDrawable(\"";
_menu.Add2((int) (1),(int) (1),BA.ObjectToCharSequence("Filter by"),mostCurrent._xmlicon.GetDrawable("baseline_filter_alt_white_24dp")).setShowAsAction(_item.SHOW_AS_ACTION_IF_ROOM);
 //BA.debugLineNum = 109;BA.debugLine="CreateSubMenus";
_createsubmenus();
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 85;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 86;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 87;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 88;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 90;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 98;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 95;BA.debugLine="GetPSIDistRec(GlobalVar.TranDate, GlobalVar.PumpH";
_getpsidistrec(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddpsidist_click() throws Exception{
 //BA.debugLineNum = 328;BA.debugLine="Sub btnAddPSIDist_Click";
 //BA.debugLineNum = 329;BA.debugLine="GlobalVar.blnNewPSIDist = True";
mostCurrent._globalvar._blnnewpsidist /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 330;BA.debugLine="StartActivity(AddEditPSIDistRecord)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addeditpsidistrecord.getObject()));
 //BA.debugLineNum = 331;BA.debugLine="End Sub";
return "";
}
public static String  _clvlist_itemclick(int _index,Object _value) throws Exception{
bwsi.PumpOperations.actcriticalpoint._pressuredistrecords _rec = null;
 //BA.debugLineNum = 258;BA.debugLine="Sub clvList_ItemClick (Index As Int, Value As Obje";
 //BA.debugLineNum = 259;BA.debugLine="Dim Rec As PressureDistRecords = Value";
_rec = (bwsi.PumpOperations.actcriticalpoint._pressuredistrecords)(_value);
 //BA.debugLineNum = 260;BA.debugLine="Log(Rec.ID)";
anywheresoftware.b4a.keywords.Common.LogImpl("114090242",BA.NumberToString(_rec.ID /*int*/ ),0);
 //BA.debugLineNum = 261;BA.debugLine="ShowPSIDistRecDetails(Rec.ID)";
_showpsidistrecdetails(_rec.ID /*int*/ );
 //BA.debugLineNum = 262;BA.debugLine="GlobalVar.PSIDistDetailID = Rec.ID";
mostCurrent._globalvar._psidistdetailid /*int*/  = _rec.ID /*int*/ ;
 //BA.debugLineNum = 263;BA.debugLine="End Sub";
return "";
}
public static String  _clvlist_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
int _extrasize = 0;
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
bwsi.PumpOperations.actcriticalpoint._pressuredistrecords _pr = null;
 //BA.debugLineNum = 233;BA.debugLine="Sub clvList_VisibleRangeChanged (FirstIndex As Int";
 //BA.debugLineNum = 234;BA.debugLine="Dim ExtraSize As Int = 15 'List size";
_extrasize = (int) (15);
 //BA.debugLineNum = 235;BA.debugLine="For i = Max(0, FirstIndex - ExtraSize) To Min(Las";
{
final int step2 = 1;
final int limit2 = (int) (anywheresoftware.b4a.keywords.Common.Min(_lastindex+_extrasize,mostCurrent._clvlist._getsize()-1));
_i = (int) (anywheresoftware.b4a.keywords.Common.Max(0,_firstindex-_extrasize)) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 236;BA.debugLine="Dim Pnl As B4XView = clvList.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._clvlist._getpanel(_i);
 //BA.debugLineNum = 237;BA.debugLine="If i > FirstIndex - ExtraSize And i < LastIndex";
if (_i>_firstindex-_extrasize && _i<_lastindex+_extrasize) { 
 //BA.debugLineNum = 238;BA.debugLine="If Pnl.NumberOfViews = 0 Then 'Add each item/la";
if (_pnl.getNumberOfViews()==0) { 
 //BA.debugLineNum = 239;BA.debugLine="Dim PR As PressureDistRecords = clvList.GetVal";
_pr = (bwsi.PumpOperations.actcriticalpoint._pressuredistrecords)(mostCurrent._clvlist._getvalue(_i));
 //BA.debugLineNum = 240;BA.debugLine="Pnl.LoadLayout(\"ListPSIDistRecords\")";
_pnl.LoadLayout("ListPSIDistRecords",mostCurrent.activityBA);
 //BA.debugLineNum = 241;BA.debugLine="lblDistTimeRead.TextColor = GlobalVar.PriColor";
mostCurrent._lbldisttimeread.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 243;BA.debugLine="lblDistPSIRdg.TextColor = GlobalVar.PriColor";
mostCurrent._lbldistpsirdg.setTextColor((int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 245;BA.debugLine="lblDistTimeRead.Text = PR.sPSIDistRdgTime";
mostCurrent._lbldisttimeread.setText(BA.ObjectToCharSequence(_pr.sPSIDistRdgTime /*String*/ ));
 //BA.debugLineNum = 246;BA.debugLine="lblLocation.Text = PR.sPSIPoint & \" - \" & PR.s";
mostCurrent._lbllocation.setText(BA.ObjectToCharSequence(_pr.sPSIPoint /*String*/ +" - "+_pr.sPSIDistLoc /*String*/ ));
 //BA.debugLineNum = 247;BA.debugLine="lblDistPSIRdg.Text = PR.iPSIDistRdg";
mostCurrent._lbldistpsirdg.setText(BA.ObjectToCharSequence(_pr.iPSIDistRdg /*int*/ ));
 };
 }else {
 //BA.debugLineNum = 250;BA.debugLine="If Pnl.NumberOfViews > 0 Then";
if (_pnl.getNumberOfViews()>0) { 
 //BA.debugLineNum = 251;BA.debugLine="Pnl.RemoveAllViews 'Remove none visable item/l";
_pnl.RemoveAllViews();
 };
 };
 }
};
 //BA.debugLineNum = 256;BA.debugLine="End Sub";
return "";
}
public static String  _createsubmenus() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _csall = null;
anywheresoftware.b4a.objects.CSBuilder _cspoint = null;
anywheresoftware.b4a.sql.SQL.CursorWrapper _rspoint = null;
String _spointnum = "";
int _pcount = 0;
int _jid = 0;
int _i = 0;
 //BA.debugLineNum = 112;BA.debugLine="Private Sub CreateSubMenus";
 //BA.debugLineNum = 113;BA.debugLine="Dim csAll, csPoint As CSBuilder";
_csall = new anywheresoftware.b4a.objects.CSBuilder();
_cspoint = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 115;BA.debugLine="csAll.Initialize.Color(Colors.White).Append($\"All";
_csall.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(("All"))).PopAll();
 //BA.debugLineNum = 117;BA.debugLine="PopSubMenu.Initialize(\"FilterBy\", ToolBar.GetView";
mostCurrent._popsubmenu.Initialize(mostCurrent.activityBA,"FilterBy",(android.view.View)(mostCurrent._toolbar.GetView((int) (3)).getObject()));
 //BA.debugLineNum = 118;BA.debugLine="PopSubMenu.AddMenuItem(0,csAll,xmlIcon.GetDrawabl";
mostCurrent._popsubmenu.AddMenuItem((int) (0),BA.ObjectToCharSequence(_csall.getObject()),mostCurrent._xmlicon.GetDrawable("baseline_gps_fixed_white_24dp"));
 //BA.debugLineNum = 120;BA.debugLine="Dim rsPoint As Cursor";
_rspoint = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 121;BA.debugLine="Dim sPointNum As String";
_spointnum = "";
 //BA.debugLineNum = 122;BA.debugLine="Dim pCount As Int";
_pcount = 0;
 //BA.debugLineNum = 124;BA.debugLine="Try";
try { //BA.debugLineNum = 125;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblPressure";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblPressurePoint "+"WHERE PumpHouseID = "+BA.NumberToString(mostCurrent._globalvar._pumphouseid /*int*/ )+" "+"ORDER BY id ASC";
 //BA.debugLineNum = 129;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("113697041",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 131;BA.debugLine="rsPoint =  Starter.DBCon.ExecQuery (Starter.strC";
_rspoint = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 132;BA.debugLine="If rsPoint.RowCount > 0 Then";
if (_rspoint.getRowCount()>0) { 
 //BA.debugLineNum = 133;BA.debugLine="pCount = rsPoint.RowCount";
_pcount = _rspoint.getRowCount();
 //BA.debugLineNum = 134;BA.debugLine="Dim jID As Int";
_jid = 0;
 //BA.debugLineNum = 135;BA.debugLine="For i = 0 To rsPoint.RowCount - 1";
{
final int step15 = 1;
final int limit15 = (int) (_rspoint.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit15 ;_i = _i + step15 ) {
 //BA.debugLineNum = 136;BA.debugLine="rsPoint.Position = i";
_rspoint.setPosition(_i);
 //BA.debugLineNum = 137;BA.debugLine="sPointNum = rsPoint.GetString(\"PPointNo\")";
_spointnum = _rspoint.GetString("PPointNo");
 //BA.debugLineNum = 138;BA.debugLine="jID = i + 1";
_jid = (int) (_i+1);
 //BA.debugLineNum = 139;BA.debugLine="csPoint.Initialize.Color(Colors.White).Append(";
_cspoint.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(_spointnum)).PopAll();
 //BA.debugLineNum = 140;BA.debugLine="PopSubMenu.AddMenuItem(jID,csPoint,xmlIcon.Get";
mostCurrent._popsubmenu.AddMenuItem(_jid,BA.ObjectToCharSequence(_cspoint.getObject()),mostCurrent._xmlicon.GetDrawable("baseline_hub_white_24dp"));
 }
};
 }else {
 //BA.debugLineNum = 143;BA.debugLine="snack.Initialize(\"\", Activity, \"No List of Job";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),"No List of Job Order found!",mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 144;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 145;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 146;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 147;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e30) {
			processBA.setLastException(e30); //BA.debugLineNum = 150;BA.debugLine="snack.Initialize(\"\", Activity, LastException,sna";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 151;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 152;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 153;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 154;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return "";
}
public static String  _editpsidistrec_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 318;BA.debugLine="Private Sub EditPSIDistRec_ButtonPressed(mDialog A";
 //BA.debugLineNum = 319;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE,_mdialog.ACTION_NEUTRAL)) {
case 0: {
 break; }
case 1: {
 //BA.debugLineNum = 322;BA.debugLine="GlobalVar.blnNewPSIDist = False";
mostCurrent._globalvar._blnnewpsidist /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 323;BA.debugLine="StartActivity(AddEditPSIDistRecord)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addeditpsidistrecord.getObject()));
 break; }
case 2: {
 break; }
}
;
 //BA.debugLineNum = 326;BA.debugLine="End Sub";
return "";
}
public static String  _filterby_itemclicked(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 169;BA.debugLine="Sub FilterBy_ItemClicked (Item As ACMenuItem)";
 //BA.debugLineNum = 170;BA.debugLine="Log(\"Popupmenu Item clicked: \" & Item.Id & \" - \"";
anywheresoftware.b4a.keywords.Common.LogImpl("113893633","Popupmenu Item clicked: "+BA.NumberToString(_item.getId())+" - "+_item.getTitle(),0);
 //BA.debugLineNum = 171;BA.debugLine="Select Case Item.Id";
switch (BA.switchObjectToInt(_item.getId(),(int) (0))) {
case 0: {
 //BA.debugLineNum = 173;BA.debugLine="GetPSIDistRec(GlobalVar.TranDate, GlobalVar.Pum";
_getpsidistrec(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ );
 break; }
default: {
 //BA.debugLineNum = 175;BA.debugLine="FilterPSIDistRec(GlobalVar.TranDate, GlobalVar.";
_filterpsidistrec(mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._globalvar._pumphouseid /*int*/ ,_item.getTitle());
 break; }
}
;
 //BA.debugLineNum = 178;BA.debugLine="End Sub";
return "";
}
public static void  _filterpsidistrec(String _strandate,int _ipumpid,String _spointno) throws Exception{
ResumableSub_FilterPSIDistRec rsub = new ResumableSub_FilterPSIDistRec(null,_strandate,_ipumpid,_spointno);
rsub.resume(processBA, null);
}
public static class ResumableSub_FilterPSIDistRec extends BA.ResumableSub {
public ResumableSub_FilterPSIDistRec(bwsi.PumpOperations.actcriticalpoint parent,String _strandate,int _ipumpid,String _spointno) {
this.parent = parent;
this._strandate = _strandate;
this._ipumpid = _ipumpid;
this._spointno = _spointno;
}
bwsi.PumpOperations.actcriticalpoint parent;
String _strandate;
int _ipumpid;
String _spointno;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actcriticalpoint._pressuredistrecords _pr = null;
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
 //BA.debugLineNum = 334;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 335;BA.debugLine="clvList.Clear";
parent.mostCurrent._clvlist._clear();
 //BA.debugLineNum = 336;BA.debugLine="Try";
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
 //BA.debugLineNum = 337;BA.debugLine="Starter.strCriteria = \"SELECT DistPSIReading.Rdg";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT DistPSIReading.RdgID, DistPSIReading.PSIPointID, PressurePoint.PPointNo, PressurePoint.PLocation, DistPSIReading.RdgDate, "+"DistPSIReading.RdgTime, DistPSIReading.PSIReading, DistPSIReading.Remarks "+"FROM PressureDistReadings AS DistPSIReading "+"INNER JOIN tblPressurePoint AS PressurePoint ON DistPSIReading.PSIPointID = PressurePoint.ID "+"WHERE PressurePoint.PumpHouseID = "+BA.NumberToString(_ipumpid)+" "+"AND DistPSIReading.RdgDate =  '"+_strandate+"' "+"AND PressurePoint.PPointNo =  '"+_spointno+"' "+"AND DistPSIReading.AddedBy =  "+BA.NumberToString(parent.mostCurrent._globalvar._userid /*int*/ )+" "+"ORDER BY DistPSIReading.RdgID ASC, DistPSIReading.RdgTime ASC";
 //BA.debugLineNum = 347;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 348;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 17;
return;
case 17:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 350;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 351;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 352;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 353;BA.debugLine="Dim PR As PressureDistRecords";
_pr = new bwsi.PumpOperations.actcriticalpoint._pressuredistrecords();
 //BA.debugLineNum = 354;BA.debugLine="PR.Initialize";
_pr.Initialize();
 //BA.debugLineNum = 355;BA.debugLine="PR.ID = RS.GetInt(\"RdgID\")";
_pr.ID /*int*/  = _rs.GetInt("RdgID");
 //BA.debugLineNum = 356;BA.debugLine="PR.sPSIPoint = RS.GetString(\"PPointNo\")";
_pr.sPSIPoint /*String*/  = _rs.GetString("PPointNo");
 //BA.debugLineNum = 357;BA.debugLine="PR.sPSIDistRdgTime = RS.GetString(\"RdgTime\")";
_pr.sPSIDistRdgTime /*String*/  = _rs.GetString("RdgTime");
 //BA.debugLineNum = 358;BA.debugLine="PR.sPSIDistLoc = RS.GetString(\"PLocation\")";
_pr.sPSIDistLoc /*String*/  = _rs.GetString("PLocation");
 //BA.debugLineNum = 359;BA.debugLine="PR.iPSIDistRdg = RS.GetInt(\"PSIReading\")";
_pr.iPSIDistRdg /*int*/  = _rs.GetInt("PSIReading");
 //BA.debugLineNum = 361;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 362;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0, clvList.AsV";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (parent.mostCurrent._clvlist._asview().getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 363;BA.debugLine="clvList.Add(Pnl, PR)";
parent.mostCurrent._clvlist._add(_pnl,(Object)(_pr));
 if (true) break;

case 10:
//C
this.state = 13;
;
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 366;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 367;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 368;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 369;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 370;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("114352421",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 13:
//C
this.state = 16;
;
 //BA.debugLineNum = 373;BA.debugLine="Log($\"List of Time Records = ${NumberFormat2((Da";
anywheresoftware.b4a.keywords.Common.LogImpl("114352424",("List of Time Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvlist._getsize()))+" PSI Distribution Records."),0);
 if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
 //BA.debugLineNum = 376;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 377;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 378;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 379;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 380;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("114352431",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 382;BA.debugLine="End Sub";
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
public static void  _getpsidistrec(String _strandate,int _ipumpid) throws Exception{
ResumableSub_GetPSIDistRec rsub = new ResumableSub_GetPSIDistRec(null,_strandate,_ipumpid);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetPSIDistRec extends BA.ResumableSub {
public ResumableSub_GetPSIDistRec(bwsi.PumpOperations.actcriticalpoint parent,String _strandate,int _ipumpid) {
this.parent = parent;
this._strandate = _strandate;
this._ipumpid = _ipumpid;
}
bwsi.PumpOperations.actcriticalpoint parent;
String _strandate;
int _ipumpid;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actcriticalpoint._pressuredistrecords _pr = null;
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
 //BA.debugLineNum = 184;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 185;BA.debugLine="clvList.Clear";
parent.mostCurrent._clvlist._clear();
 //BA.debugLineNum = 186;BA.debugLine="Try";
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
 //BA.debugLineNum = 187;BA.debugLine="Starter.strCriteria = \"SELECT DistPSIReading.Rdg";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT DistPSIReading.RdgID, DistPSIReading.PSIPointID, PressurePoint.PPointNo, PressurePoint.PLocation, DistPSIReading.RdgDate, "+"DistPSIReading.RdgTime, DistPSIReading.PSIReading, DistPSIReading.Remarks "+"FROM PressureDistReadings AS DistPSIReading "+"INNER JOIN tblPressurePoint AS PressurePoint ON DistPSIReading.PSIPointID = PressurePoint.ID "+"WHERE PressurePoint.PumpHouseID = "+BA.NumberToString(_ipumpid)+" "+"AND DistPSIReading.RdgDate =  '"+_strandate+"' "+"AND DistPSIReading.AddedBy =  "+BA.NumberToString(parent.mostCurrent._globalvar._userid /*int*/ )+" "+"ORDER BY DistPSIReading.RdgID ASC, DistPSIReading.RdgTime ASC";
 //BA.debugLineNum = 196;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 197;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 17;
return;
case 17:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 199;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 200;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 201;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 202;BA.debugLine="Dim PR As PressureDistRecords";
_pr = new bwsi.PumpOperations.actcriticalpoint._pressuredistrecords();
 //BA.debugLineNum = 203;BA.debugLine="PR.Initialize";
_pr.Initialize();
 //BA.debugLineNum = 204;BA.debugLine="PR.ID = RS.GetInt(\"RdgID\")";
_pr.ID /*int*/  = _rs.GetInt("RdgID");
 //BA.debugLineNum = 205;BA.debugLine="PR.sPSIPoint = RS.GetString(\"PPointNo\")";
_pr.sPSIPoint /*String*/  = _rs.GetString("PPointNo");
 //BA.debugLineNum = 206;BA.debugLine="PR.sPSIDistRdgTime = RS.GetString(\"RdgTime\")";
_pr.sPSIDistRdgTime /*String*/  = _rs.GetString("RdgTime");
 //BA.debugLineNum = 207;BA.debugLine="PR.sPSIDistLoc = RS.GetString(\"PLocation\")";
_pr.sPSIDistLoc /*String*/  = _rs.GetString("PLocation");
 //BA.debugLineNum = 208;BA.debugLine="PR.iPSIDistRdg = RS.GetInt(\"PSIReading\")";
_pr.iPSIDistRdg /*int*/  = _rs.GetInt("PSIReading");
 //BA.debugLineNum = 210;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 211;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0, clvList.AsV";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (parent.mostCurrent._clvlist._asview().getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 212;BA.debugLine="clvList.Add(Pnl, PR)";
parent.mostCurrent._clvlist._add(_pnl,(Object)(_pr));
 if (true) break;

case 10:
//C
this.state = 13;
;
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 215;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 216;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 217;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 218;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 219;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("113959204",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 13:
//C
this.state = 16;
;
 //BA.debugLineNum = 222;BA.debugLine="Log($\"List of Time Records = ${NumberFormat2((Da";
anywheresoftware.b4a.keywords.Common.LogImpl("113959207",("List of Time Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvlist._getsize()))+" PSI Distribution Records."),0);
 if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
 //BA.debugLineNum = 225;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 226;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 227;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 228;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 229;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("113959214",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 231;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 22;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 23;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 24;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private xmlIcon As XmlLayoutBuilder";
mostCurrent._xmlicon = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 26;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private CD As ColorDrawable";
mostCurrent._cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 28;BA.debugLine="Private vibration As B4Avibrate";
mostCurrent._vibration = new com.johan.Vibrate.Vibrate();
 //BA.debugLineNum = 29;BA.debugLine="Private vibratePattern() As Long";
_vibratepattern = new long[(int) (0)];
;
 //BA.debugLineNum = 31;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private csAns As CSBuilder";
mostCurrent._csans = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 33;BA.debugLine="Dim kboard As IME";
mostCurrent._kboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 35;BA.debugLine="Private pnlPSIDist As B4XView";
mostCurrent._pnlpsidist = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Dim theDate As Long";
_thedate = 0L;
 //BA.debugLineNum = 39;BA.debugLine="Type PressureDistRecords (ID As Int, sPSIPoint As";
;
 //BA.debugLineNum = 40;BA.debugLine="Private clvList As CustomListView";
mostCurrent._clvlist = new b4a.example3.customlistview();
 //BA.debugLineNum = 41;BA.debugLine="Private lblDistTimeRead As B4XView";
mostCurrent._lbldisttimeread = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private lblLocation As B4XView";
mostCurrent._lbllocation = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private lblDistPSIRdg As B4XView";
mostCurrent._lbldistpsirdg = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private btnAddPSIDist As DSFloatingActionButton";
mostCurrent._btnaddpsidist = new de.amberhome.objects.FloatingActionButtonWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private MyToast As BCToast";
mostCurrent._mytoast = new bwsi.PumpOperations.bctoast();
 //BA.debugLineNum = 47;BA.debugLine="Private PopSubMenu As ACPopupMenu";
mostCurrent._popsubmenu = new de.amberhome.objects.appcompat.ACPopupMenuWrapper();
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static void  _showpsidistrecdetails(int _iid) throws Exception{
ResumableSub_ShowPSIDistRecDetails rsub = new ResumableSub_ShowPSIDistRecDetails(null,_iid);
rsub.resume(processBA, null);
}
public static class ResumableSub_ShowPSIDistRecDetails extends BA.ResumableSub {
public ResumableSub_ShowPSIDistRecDetails(bwsi.PumpOperations.actcriticalpoint parent,int _iid) {
this.parent = parent;
this._iid = _iid;
}
bwsi.PumpOperations.actcriticalpoint parent;
int _iid;
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
Object _senderfilter = null;
String _sdate = "";
String _spcode = "";
String _sdistributiontime = "";
String _slocation = "";
String _srem = "";
int _ipsidistrdg = 0;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 266;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 267;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 268;BA.debugLine="Dim sDate, sPCode, sDistributionTime, sLocation,";
_sdate = "";
_spcode = "";
_sdistributiontime = "";
_slocation = "";
_srem = "";
 //BA.debugLineNum = 269;BA.debugLine="Dim iPSIDistRdg As Int";
_ipsidistrdg = 0;
 //BA.debugLineNum = 271;BA.debugLine="Starter.strCriteria = \"SELECT PSIDistRdg.RdgDate";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT PSIDistRdg.RdgDate as TranDate, PumpStation.PumpHouseCode, "+"PSIDistRdg.RdgTime, PPoint.PPointNo, PPoint.PLocation, "+"PSIDistRdg.PSIReading, PSIDistRdg.Remarks "+"FROM PressureDistReadings AS PSIDistRdg "+"INNER JOIN tblPressurePoint AS PPoint ON PSIDistRdg.PSIPointID = PPoint.ID "+"INNER JOIN tblPumpStation AS PumpStation ON PPoint.PumpHouseID = PumpStation.StationID "+"WHERE PSIDistRdg.RdgID = "+BA.NumberToString(_iid);
 //BA.debugLineNum = 279;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL\"";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 280;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succes";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 7;
return;
case 7:
//C
this.state = 1;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 282;BA.debugLine="If Success Then";
if (true) break;

case 1:
//if
this.state = 6;
if (_success) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 283;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 284;BA.debugLine="sDate = RS.GetString(\"TranDate\")";
_sdate = _rs.GetString("TranDate");
 //BA.debugLineNum = 285;BA.debugLine="sPCode = RS.GetString(\"PumpHouseCode\")";
_spcode = _rs.GetString("PumpHouseCode");
 //BA.debugLineNum = 286;BA.debugLine="sDistributionTime = RS.GetString(\"RdgTime\")";
_sdistributiontime = _rs.GetString("RdgTime");
 //BA.debugLineNum = 287;BA.debugLine="sLocation = RS.GetString(\"PPointNo\") & \" - \" & R";
_slocation = _rs.GetString("PPointNo")+" - "+_rs.GetString("PLocation");
 //BA.debugLineNum = 288;BA.debugLine="iPSIDistRdg = RS.GetInt(\"PSIReading\")";
_ipsidistrdg = _rs.GetInt("PSIReading");
 //BA.debugLineNum = 289;BA.debugLine="sRem = RS.GetString(\"Remarks\")";
_srem = _rs.GetString("Remarks");
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 291;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 292;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 293;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 294;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 295;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("114155806",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 298;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar.";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (parent.mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("PRESSURE (Distribution) RECORD DETAILS"))).PopAll();
 //BA.debugLineNum = 300;BA.debugLine="MatDialogBuilder.Initialize(\"EditPSIDistRec\")";
parent.mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"EditPSIDistRec");
 //BA.debugLineNum = 301;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColor";
parent.mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (parent.mostCurrent._globalvar._bluecolor /*double*/ ));
 //BA.debugLineNum = 302;BA.debugLine="MatDialogBuilder.NegativeText(\"EDIT\").NegativeCol";
parent.mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence("EDIT")).NegativeColor((int) (parent.mostCurrent._globalvar._greencolor /*double*/ ));
 //BA.debugLineNum = 303;BA.debugLine="MatDialogBuilder.NeutralText(\"DELETE\").NeutralCol";
parent.mostCurrent._matdialogbuilder.NeutralText(BA.ObjectToCharSequence("DELETE")).NeutralColor((int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 304;BA.debugLine="MatDialogBuilder.Title(csTitle)";
parent.mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 305;BA.debugLine="MatDialogBuilder.Content($\"  Pump: ${sPCode} 	Tra";
parent.mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(("  Pump: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_spcode))+"\n"+"	Transaction Date: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sdate))+"\n"+"\n"+"	Time Read: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sdistributiontime))+"\n"+"	Location: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_slocation))+"\n"+"	Pressure Reading: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_ipsidistrdg))+" PSI\n"+"	Remarks: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_srem))+"")));
 //BA.debugLineNum = 312;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
parent.mostCurrent._matdialogbuilder.Theme(parent.mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 313;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
parent.mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 314;BA.debugLine="MatDialogBuilder.Cancelable(True)";
parent.mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 315;BA.debugLine="MatDialogBuilder.Show";
parent.mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 316;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 163;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 164;BA.debugLine="Select Item.Id";
switch (BA.switchObjectToInt(_item.getId(),(int) (1))) {
case 0: {
 //BA.debugLineNum = 166;BA.debugLine="PopSubMenu.Show";
mostCurrent._popsubmenu.Show();
 break; }
}
;
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 158;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 159;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 160;BA.debugLine="kboard.HideKeyboard";
mostCurrent._kboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 161;BA.debugLine="End Sub";
return "";
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
