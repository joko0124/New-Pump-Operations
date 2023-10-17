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

public class actproduction extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static actproduction mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.actproduction");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actproduction).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.actproduction");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.actproduction", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actproduction) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actproduction) Resume **");
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
		return actproduction.class;
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
            BA.LogInfo("** Activity (actproduction) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (actproduction) Pause event (activity is not paused). **");
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
            actproduction mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (actproduction) Resume **");
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

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 149;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 179;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 };
 //BA.debugLineNum = 198;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
 //BA.debugLineNum = 218;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 224;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 200;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 201;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 202;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 203;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 205;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 207;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 212;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 213;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 209;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 210;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddchlorine_click() throws Exception{
 //BA.debugLineNum = 1583;BA.debugLine="Sub btnAddChlorine_Click";
 //BA.debugLineNum = 1586;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddconcerns_click() throws Exception{
 //BA.debugLineNum = 1808;BA.debugLine="Sub btnAddConcerns_Click";
 //BA.debugLineNum = 1809;BA.debugLine="GlobalVar.blnNewProblem = True";
mostCurrent._globalvar._blnnewproblem /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1810;BA.debugLine="StartActivity(AddEditProblem)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._addeditproblem.getObject()));
 //BA.debugLineNum = 1811;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddfm_click() throws Exception{
 //BA.debugLineNum = 668;BA.debugLine="Sub btnAddFM_Click";
 //BA.debugLineNum = 698;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddpsi_click() throws Exception{
 //BA.debugLineNum = 1153;BA.debugLine="Sub btnAddPSI_Click";
 //BA.debugLineNum = 1180;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddtime_click() throws Exception{
 //BA.debugLineNum = 445;BA.debugLine="Sub btnAddTime_Click";
 //BA.debugLineNum = 452;BA.debugLine="End Sub";
return "";
}
public static String  _btneditprob_click() throws Exception{
 //BA.debugLineNum = 1692;BA.debugLine="Sub btnEditProb_Click";
 //BA.debugLineNum = 1696;BA.debugLine="End Sub";
return "";
}
public static String  _btnfmrdgcancel_click() throws Exception{
 //BA.debugLineNum = 759;BA.debugLine="Sub btnFMRdgCancel_Click";
 //BA.debugLineNum = 762;BA.debugLine="End Sub";
return "";
}
public static String  _btnprobencok_click() throws Exception{
 //BA.debugLineNum = 1688;BA.debugLine="Sub btnProbEncOK_Click";
 //BA.debugLineNum = 1690;BA.debugLine="End Sub";
return "";
}
public static String  _btnpsirdgcancel_click() throws Exception{
 //BA.debugLineNum = 1335;BA.debugLine="Sub btnPSIRdgCancel_Click";
 //BA.debugLineNum = 1338;BA.debugLine="End Sub";
return "";
}
public static String  _btnsaveupdatefmrdg_click() throws Exception{
 //BA.debugLineNum = 704;BA.debugLine="Sub btnSaveUpdateFMRdg_Click";
 //BA.debugLineNum = 757;BA.debugLine="End Sub";
return "";
}
public static String  _btnsaveupdatepsirdg_click() throws Exception{
 //BA.debugLineNum = 1182;BA.debugLine="Sub btnSaveUpdatePSIRdg_Click";
 //BA.debugLineNum = 1210;BA.debugLine="End Sub";
return "";
}
public static String  _btnsolved_click() throws Exception{
 //BA.debugLineNum = 1684;BA.debugLine="Sub btnSolved_Click";
 //BA.debugLineNum = 1686;BA.debugLine="End Sub";
return "";
}
public static String  _clvchlorine_itemclick(int _index,Object _value) throws Exception{
 //BA.debugLineNum = 1513;BA.debugLine="Sub clvChlorine_ItemClick (Index As Int, Value As";
 //BA.debugLineNum = 1518;BA.debugLine="End Sub";
return "";
}
public static String  _clvchlorine_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
 //BA.debugLineNum = 1488;BA.debugLine="Sub clvChlorine_VisibleRangeChanged (FirstIndex As";
 //BA.debugLineNum = 1511;BA.debugLine="End Sub";
return "";
}
public static String  _clvconcerns_itemclick(int _index,Object _value) throws Exception{
bwsi.PumpOperations.actnewproduction._concernsrecords _rec = null;
 //BA.debugLineNum = 1669;BA.debugLine="Sub clvConcerns_ItemClick (Index As Int, Value As";
 //BA.debugLineNum = 1670;BA.debugLine="Dim Rec As ConcernsRecords = Value";
_rec = (bwsi.PumpOperations.actnewproduction._concernsrecords)(_value);
 //BA.debugLineNum = 1671;BA.debugLine="Log(Rec.ID)";
anywheresoftware.b4a.keywords.Common.LogImpl("183689474",BA.NumberToString(_rec.ID /*int*/ ),0);
 //BA.debugLineNum = 1672;BA.debugLine="ShowProblemRecDetails(Rec.ID)";
_showproblemrecdetails(_rec.ID /*int*/ );
 //BA.debugLineNum = 1673;BA.debugLine="GlobalVar.ProblemDetailID = Rec.ID";
mostCurrent._globalvar._problemdetailid /*int*/  = _rec.ID /*int*/ ;
 //BA.debugLineNum = 1674;BA.debugLine="End Sub";
return "";
}
public static String  _clvconcerns_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
 //BA.debugLineNum = 1646;BA.debugLine="Sub clvConcerns_VisibleRangeChanged (FirstIndex As";
 //BA.debugLineNum = 1667;BA.debugLine="End Sub";
return "";
}
public static String  _clvfm_itemclick(int _index,Object _value) throws Exception{
 //BA.debugLineNum = 542;BA.debugLine="Sub clvFM_ItemClick (Index As Int, Value As Object";
 //BA.debugLineNum = 547;BA.debugLine="End Sub";
return "";
}
public static String  _clvfm_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
 //BA.debugLineNum = 515;BA.debugLine="Sub clvFM_VisibleRangeChanged (FirstIndex As Int,";
 //BA.debugLineNum = 540;BA.debugLine="End Sub";
return "";
}
public static String  _clvpsi_itemclick(int _index,Object _value) throws Exception{
 //BA.debugLineNum = 1066;BA.debugLine="Sub clvPSI_ItemClick (Index As Int, Value As Objec";
 //BA.debugLineNum = 1071;BA.debugLine="End Sub";
return "";
}
public static String  _clvpsi_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
 //BA.debugLineNum = 1043;BA.debugLine="Sub clvPSI_VisibleRangeChanged (FirstIndex As Int,";
 //BA.debugLineNum = 1064;BA.debugLine="End Sub";
return "";
}
public static String  _clvtime_itemclick(int _index,Object _value) throws Exception{
 //BA.debugLineNum = 347;BA.debugLine="Sub clvTime_ItemClick (Index As Int, Value As Obje";
 //BA.debugLineNum = 361;BA.debugLine="End Sub";
return "";
}
public static String  _clvtime_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
 //BA.debugLineNum = 315;BA.debugLine="Sub clvTime_VisibleRangeChanged (FirstIndex As Int";
 //BA.debugLineNum = 345;BA.debugLine="End Sub";
return "";
}
public static String  _confirmpumpoff(int _ipumpid) throws Exception{
 //BA.debugLineNum = 1814;BA.debugLine="Private Sub ConfirmPumpOff(iPumpID As Int)";
 //BA.debugLineNum = 1838;BA.debugLine="End Sub";
return "";
}
public static String  _editchlorinetime_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 1572;BA.debugLine="Private Sub EditChlorineTime_ButtonPressed(mDialog";
 //BA.debugLineNum = 1581;BA.debugLine="End Sub";
return "";
}
public static String  _editfmrdg_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 615;BA.debugLine="Private Sub EditFMRdg_ButtonPressed(mDialog As Mat";
 //BA.debugLineNum = 640;BA.debugLine="End Sub";
return "";
}
public static String  _editpsirdg_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 1129;BA.debugLine="Private Sub EditPSIRdg_ButtonPressed(mDialog As Ma";
 //BA.debugLineNum = 1151;BA.debugLine="End Sub";
return "";
}
public static String  _edittime_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 434;BA.debugLine="Private Sub EditTime_ButtonPressed(mDialog As Mate";
 //BA.debugLineNum = 443;BA.debugLine="End Sub";
return "";
}
public static String  _getfmrdgrec(String _strandate,int _ipumpid) throws Exception{
 //BA.debugLineNum = 466;BA.debugLine="Sub GetFMRdgRec(sTrandate As String, iPumpID As In";
 //BA.debugLineNum = 513;BA.debugLine="End Sub";
return "";
}
public static String  _getfmreading(int _idetailid) throws Exception{
 //BA.debugLineNum = 642;BA.debugLine="Private Sub GetFMReading(iDetailID As Int) As Stri";
 //BA.debugLineNum = 653;BA.debugLine="End Sub";
return "";
}
public static String  _getfmreadingremarks(int _idetailid) throws Exception{
 //BA.debugLineNum = 655;BA.debugLine="Private Sub GetFMReadingRemarks(iDetailID As Int)";
 //BA.debugLineNum = 666;BA.debugLine="End Sub";
return "";
}
public static int  _getpreviousrdg(int _idetailid) throws Exception{
 //BA.debugLineNum = 970;BA.debugLine="Private Sub GetPreviousRdg(iDetailID As Int) As In";
 //BA.debugLineNum = 983;BA.debugLine="End Sub";
return 0;
}
public static String  _getproblemsrec(String _strandate,int _ipumpid) throws Exception{
 //BA.debugLineNum = 1600;BA.debugLine="Sub GetProblemsRec(sTrandate As String, iPumpID As";
 //BA.debugLineNum = 1644;BA.debugLine="End Sub";
return "";
}
public static String  _getpsirdgrec(String _strandate,int _ipumpid) throws Exception{
 //BA.debugLineNum = 997;BA.debugLine="Sub GetPSIRdgRec(sTrandate As String, iPumpID As I";
 //BA.debugLineNum = 1041;BA.debugLine="End Sub";
return "";
}
public static String  _getpsireading(int _idetailid) throws Exception{
String _sretval = "";
 //BA.debugLineNum = 1340;BA.debugLine="Private Sub GetPSIReading(iDetailID As Int) As Str";
 //BA.debugLineNum = 1341;BA.debugLine="Dim sRetval As String";
_sretval = "";
 //BA.debugLineNum = 1342;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 1343;BA.debugLine="Try";
try { //BA.debugLineNum = 1344;BA.debugLine="sRetval = Starter.DBCon.ExecQuerySingleResult(\"S";
_sretval = mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT PSIReading FROM PressureRdgDetails WHERE DetailID = "+BA.NumberToString(_idetailid));
 //BA.debugLineNum = 1345;BA.debugLine="LogColor(sRetval, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("182903045",_sretval,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 1347;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 1348;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("182903048",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 1350;BA.debugLine="Return sRetval";
if (true) return _sretval;
 //BA.debugLineNum = 1351;BA.debugLine="End Sub";
return "";
}
public static String  _getpsireadingremarks(int _idetailid) throws Exception{
String _sretval = "";
 //BA.debugLineNum = 1353;BA.debugLine="Private Sub GetPSIReadingRemarks(iDetailID As Int)";
 //BA.debugLineNum = 1354;BA.debugLine="Dim sRetval As String";
_sretval = "";
 //BA.debugLineNum = 1355;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 1356;BA.debugLine="Try";
try { //BA.debugLineNum = 1357;BA.debugLine="sRetval = Starter.DBCon.ExecQuerySingleResult(\"S";
_sretval = mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT Remarks FROM PressureRdgDetails WHERE DetailID = "+BA.NumberToString(_idetailid));
 //BA.debugLineNum = 1358;BA.debugLine="LogColor(sRetval, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("182968581",_sretval,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 1360;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 1361;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("182968584",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 1363;BA.debugLine="Return sRetval";
if (true) return _sretval;
 //BA.debugLineNum = 1364;BA.debugLine="End Sub";
return "";
}
public static String  _getpumptimerec(String _strandate,int _ipumpid) throws Exception{
 //BA.debugLineNum = 245;BA.debugLine="Sub GetPumpTimeRec(sTrandate As String, iPumpID As";
 //BA.debugLineNum = 313;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 145;BA.debugLine="End Sub";
return "";
}
public static boolean  _islastfmreading(int _idetailsid,int _iheaderid) throws Exception{
 //BA.debugLineNum = 948;BA.debugLine="Private Sub isLastFMReading(iDetailsID As Int, iHe";
 //BA.debugLineNum = 968;BA.debugLine="End Sub";
return false;
}
public static boolean  _islastpsireading(int _idetailsid,int _iheaderid) throws Exception{
boolean _bretval = false;
int _idcheck = 0;
 //BA.debugLineNum = 1404;BA.debugLine="Private Sub isLastPSIReading(iDetailsID As Int, iH";
 //BA.debugLineNum = 1405;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 1406;BA.debugLine="Dim idCheck As Int";
_idcheck = 0;
 //BA.debugLineNum = 1407;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1409;BA.debugLine="Try";
try { //BA.debugLineNum = 1410;BA.debugLine="idCheck = Starter.DBCon.ExecQuerySingleResult(\"S";
_idcheck = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT Max(DetailID) FROM PressureRdgDetails WHERE HeaderID = "+BA.NumberToString(_iheaderid)+" "+"GROUP BY HeaderID")));
 //BA.debugLineNum = 1412;BA.debugLine="LogColor($\"Selected ID: \"$ & iDetailsID & $\" - L";
anywheresoftware.b4a.keywords.Common.LogImpl("183099656",("Selected ID: ")+BA.NumberToString(_idetailsid)+(" - Last ID: ")+BA.NumberToString(_idcheck),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 1414;BA.debugLine="If iDetailsID = idCheck Then";
if (_idetailsid==_idcheck) { 
 //BA.debugLineNum = 1415;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 1417;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e13) {
			processBA.setLastException(e13); //BA.debugLineNum = 1420;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1421;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("183099665",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 1423;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 1424;BA.debugLine="End Sub";
return false;
}
public static String  _pnladdeditfmrdg_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 700;BA.debugLine="Sub pnlAddEditFMRdg_Touch (Action As Int, X As Flo";
 //BA.debugLineNum = 702;BA.debugLine="End Sub";
return "";
}
public static String  _pnladdeditpsirdg_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1676;BA.debugLine="Sub pnlAddEditPSIRdg_Touch (Action As Int, X As Fl";
 //BA.debugLineNum = 1678;BA.debugLine="End Sub";
return "";
}
public static String  _pnlprobencdetails_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1680;BA.debugLine="Sub pnlProbEncDetails_Touch (Action As Int, X As F";
 //BA.debugLineNum = 1682;BA.debugLine="End Sub";
return "";
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
public static boolean  _savefmrdg() throws Exception{
 //BA.debugLineNum = 764;BA.debugLine="Private Sub SaveFMRdg() As Boolean";
 //BA.debugLineNum = 802;BA.debugLine="End Sub";
return false;
}
public static String  _savefmrdgsuccess_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 936;BA.debugLine="Private Sub SaveFMRdgSuccess_ButtonPressed(mDialog";
 //BA.debugLineNum = 946;BA.debugLine="End Sub";
return "";
}
public static boolean  _savepsirdg() throws Exception{
 //BA.debugLineNum = 1212;BA.debugLine="Private Sub SavePSIRdg() As Boolean";
 //BA.debugLineNum = 1246;BA.debugLine="End Sub";
return false;
}
public static String  _savepsirdgsuccess_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 1325;BA.debugLine="Private Sub SavePSIRdgSuccess_ButtonPressed(mDialo";
 //BA.debugLineNum = 1333;BA.debugLine="End Sub";
return "";
}
public static String  _showchlorinatorrecdetails(int _iid) throws Exception{
 //BA.debugLineNum = 1520;BA.debugLine="Sub ShowChlorinatorRecDetails (iID As Int)";
 //BA.debugLineNum = 1570;BA.debugLine="End Sub";
return "";
}
public static String  _showfmrdgrecdetails(int _iid) throws Exception{
 //BA.debugLineNum = 549;BA.debugLine="Sub ShowFMRdgRecDetails (iID As Int)";
 //BA.debugLineNum = 613;BA.debugLine="End Sub";
return "";
}
public static String  _showproblemrecdetails(int _iid) throws Exception{
 //BA.debugLineNum = 1698;BA.debugLine="Sub ShowProblemRecDetails (iID As Int)";
 //BA.debugLineNum = 1806;BA.debugLine="End Sub";
return "";
}
public static String  _showpsirdgrecdetails(int _iid) throws Exception{
 //BA.debugLineNum = 1073;BA.debugLine="Sub ShowPSIRdgRecDetails (iID As Int)";
 //BA.debugLineNum = 1127;BA.debugLine="End Sub";
return "";
}
public static String  _showpumptimerecdetails(int _iid) throws Exception{
 //BA.debugLineNum = 363;BA.debugLine="Sub ShowPumpTimeRecDetails (iID As Int)";
 //BA.debugLineNum = 432;BA.debugLine="End Sub";
return "";
}
public static String  _showsavepsisuccess() throws Exception{
 //BA.debugLineNum = 1303;BA.debugLine="Private Sub ShowSavePSISuccess()";
 //BA.debugLineNum = 1323;BA.debugLine="End Sub";
return "";
}
public static String  _tabmenu_tab1click() throws Exception{
 //BA.debugLineNum = 235;BA.debugLine="Sub TabMenu_Tab1Click";
 //BA.debugLineNum = 243;BA.debugLine="End Sub";
return "";
}
public static String  _tabmenu_tab2click() throws Exception{
 //BA.debugLineNum = 456;BA.debugLine="Sub TabMenu_Tab2Click";
 //BA.debugLineNum = 464;BA.debugLine="End Sub";
return "";
}
public static String  _tabmenu_tab3click() throws Exception{
 //BA.debugLineNum = 987;BA.debugLine="Sub TabMenu_Tab3Click";
 //BA.debugLineNum = 995;BA.debugLine="End Sub";
return "";
}
public static String  _tabmenu_tab4click() throws Exception{
 //BA.debugLineNum = 1430;BA.debugLine="Sub TabMenu_Tab4Click";
 //BA.debugLineNum = 1486;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 229;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 230;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 226;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 227;BA.debugLine="End Sub";
return "";
}
public static boolean  _updatefmrdg(int _idetailid) throws Exception{
 //BA.debugLineNum = 850;BA.debugLine="Private Sub UpdateFMRdg(iDetailID As Int) As Boole";
 //BA.debugLineNum = 891;BA.debugLine="End Sub";
return false;
}
public static boolean  _updatelastfmrdg(int _ipumphouseid) throws Exception{
 //BA.debugLineNum = 893;BA.debugLine="Private Sub UpdateLastFMRdg(iPumpHouseID As Int) A";
 //BA.debugLineNum = 934;BA.debugLine="End Sub";
return false;
}
public static boolean  _updatepsirdg(int _idetailid) throws Exception{
 //BA.debugLineNum = 1366;BA.debugLine="Private Sub UpdatePSIRdg(iDetailID As Int) As Bool";
 //BA.debugLineNum = 1402;BA.debugLine="End Sub";
return false;
}
public static boolean  _updatetranheaderfm(int _itranheaderid) throws Exception{
 //BA.debugLineNum = 804;BA.debugLine="Private Sub UpdateTranHeaderFM(iTranHeaderID As In";
 //BA.debugLineNum = 848;BA.debugLine="End Sub";
return false;
}
public static boolean  _updatetranheaderpsi(int _itranheaderid) throws Exception{
 //BA.debugLineNum = 1248;BA.debugLine="Private Sub UpdateTranHeaderPSI(iTranHeaderID As I";
 //BA.debugLineNum = 1301;BA.debugLine="End Sub";
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
