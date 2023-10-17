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

public class actjoaccomplishedsas extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static actjoaccomplishedsas mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.actjoaccomplishedsas");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actjoaccomplishedsas).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.actjoaccomplishedsas");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.actjoaccomplishedsas", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actjoaccomplishedsas) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actjoaccomplishedsas) Resume **");
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
		return actjoaccomplishedsas.class;
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
            BA.LogInfo("** Activity (actjoaccomplishedsas) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (actjoaccomplishedsas) Pause event (activity is not paused). **");
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
            actjoaccomplishedsas mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (actjoaccomplishedsas) Resume **");
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
public anywheresoftware.b4a.objects.LabelWrapper _lbljocat = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbljono = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblref_no = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblrefno = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcustname = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcustaddress = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblacctclass = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcontype = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblremarks = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbldatesstart = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbldateaccomplished = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblaccomplishedby = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnok = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnprint = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdok = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cdprint = null;
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

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 47;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 48;BA.debugLine="Activity.LoadLayout(\"JOAccomplishedDetailsSAS\")";
mostCurrent._activity.LoadLayout("JOAccomplishedDetailsSAS",mostCurrent.activityBA);
 //BA.debugLineNum = 50;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 51;BA.debugLine="ClearUI";
_clearui();
 //BA.debugLineNum = 52;BA.debugLine="FillJORecord(GlobalVar.SelectedJOID)";
_filljorecord(mostCurrent._globalvar._selectedjoid /*int*/ );
 };
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 57;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 58;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 59;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 61;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 70;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 65;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 66;BA.debugLine="ClearUI";
_clearui();
 //BA.debugLineNum = 67;BA.debugLine="FillJORecord(GlobalVar.SelectedJOID)";
_filljorecord(mostCurrent._globalvar._selectedjoid /*int*/ );
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _btnedit_click() throws Exception{
 //BA.debugLineNum = 106;BA.debugLine="Sub btnEdit_Click";
 //BA.debugLineNum = 107;BA.debugLine="ToastMessageShow($\"Printing not yet ready...\"$, F";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Printing not yet ready...")),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 108;BA.debugLine="End Sub";
return "";
}
public static String  _btnok_click() throws Exception{
 //BA.debugLineNum = 102;BA.debugLine="Sub btnOk_Click";
 //BA.debugLineNum = 103;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 104;BA.debugLine="End Sub";
return "";
}
public static String  _clearui() throws Exception{
 //BA.debugLineNum = 76;BA.debugLine="Private Sub ClearUI";
 //BA.debugLineNum = 77;BA.debugLine="lblJOCat.Text = \"\"";
mostCurrent._lbljocat.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 78;BA.debugLine="lblJONo.Text = \"\"";
mostCurrent._lbljono.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 79;BA.debugLine="lblRef_No.Text = \"\"";
mostCurrent._lblref_no.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 80;BA.debugLine="lblRefNo.Text = \"\"";
mostCurrent._lblrefno.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 81;BA.debugLine="lblCustName.Text = \"\"";
mostCurrent._lblcustname.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 82;BA.debugLine="lblCustAddress.Text = \"\"";
mostCurrent._lblcustaddress.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 84;BA.debugLine="lblAcctClass.Text = \"\"";
mostCurrent._lblacctclass.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 85;BA.debugLine="lblConType.Text = \"\"";
mostCurrent._lblcontype.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 86;BA.debugLine="lblRemarks.Text = \"\"";
mostCurrent._lblremarks.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 88;BA.debugLine="lblDatesStart.Text = \"\"";
mostCurrent._lbldatesstart.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 89;BA.debugLine="lblDateAccomplished.Text = \"\"";
mostCurrent._lbldateaccomplished.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 90;BA.debugLine="lblAccomplishedBy.Text = \"\"";
mostCurrent._lblaccomplishedby.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 92;BA.debugLine="cdOk.Initialize(GlobalVar.GreenColor, 15)";
mostCurrent._cdok.Initialize((int) (mostCurrent._globalvar._greencolor /*double*/ ),(int) (15));
 //BA.debugLineNum = 93;BA.debugLine="cdPrint.Initialize(GlobalVar.YellowColor, 15)";
mostCurrent._cdprint.Initialize((int) (mostCurrent._globalvar._yellowcolor /*double*/ ),(int) (15));
 //BA.debugLineNum = 95;BA.debugLine="btnOk.Background = cdOk";
mostCurrent._btnok.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdok.getObject()));
 //BA.debugLineNum = 96;BA.debugLine="btnPrint.Background = cdPrint";
mostCurrent._btnprint.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdprint.getObject()));
 //BA.debugLineNum = 98;BA.debugLine="btnPrint.Text = Chr(0xE8AD) & \" PRINT\"";
mostCurrent._btnprint.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe8ad)))+" PRINT"));
 //BA.debugLineNum = 99;BA.debugLine="btnOk.Text = Chr(0xE8CE) & \" OK\"";
mostCurrent._btnok.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe8ce)))+" OK"));
 //BA.debugLineNum = 100;BA.debugLine="End Sub";
return "";
}
public static String  _filljorecord(int _ijoid) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsjosasdetails = null;
String _splumbersid = "";
 //BA.debugLineNum = 113;BA.debugLine="Private Sub FillJORecord (iJOID As Int)";
 //BA.debugLineNum = 114;BA.debugLine="Dim RSJOSASDetails As Cursor";
_rsjosasdetails = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 115;BA.debugLine="Dim sPlumbersID As String";
_splumbersid = "";
 //BA.debugLineNum = 116;BA.debugLine="Try";
try { //BA.debugLineNum = 117;BA.debugLine="Starter.strCriteria = \"SELECT JOs.JOCatCode, JOs";
mostCurrent._starter._strcriteria /*String*/  = "SELECT JOs.JOCatCode, JOs.JoDesc, JOs.JONo, JOs.RefNo, JOs.CustName, JOs.CustAddress, "+"Findings.AcctClass || ' - ' || Findings.AcctSubClass As AcctClassification, Findings.ConType, Findings.Remarks, "+"JOs.DateStarted, JOs.DateFinished, JOs.AccomplishedBy, constant_con_types.ConTypeDesc "+"FROM tblJOs As JOs "+"INNER JOIN tblJOSASFindings AS Findings ON JOs.JOID = Findings.JOID "+"INNER JOIN constant_con_types ON Findings.ConType = constant_con_types.id "+"WHERE Findings.JOID = "+BA.NumberToString(_ijoid);
 //BA.debugLineNum = 124;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("164028683",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 126;BA.debugLine="RSJOSASDetails = Starter.DBCon.ExecQuery(Starter";
_rsjosasdetails = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 128;BA.debugLine="If RSJOSASDetails.RowCount > 0 Then";
if (_rsjosasdetails.getRowCount()>0) { 
 //BA.debugLineNum = 129;BA.debugLine="RSJOSASDetails.Position = 0";
_rsjosasdetails.setPosition((int) (0));
 //BA.debugLineNum = 130;BA.debugLine="lblJOCat.Text = GlobalVar.SF.Upper(RSJOSASDetai";
mostCurrent._lbljocat.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rsjosasdetails.GetString("JoDesc"))));
 //BA.debugLineNum = 131;BA.debugLine="lblJONo.Text = GlobalVar.SF.Upper(RSJOSASDetail";
mostCurrent._lbljono.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rsjosasdetails.GetString("JONo"))));
 //BA.debugLineNum = 132;BA.debugLine="lblRef_No.Text = $\"Application No. :\"$";
mostCurrent._lblref_no.setText(BA.ObjectToCharSequence(("Application No. :")));
 //BA.debugLineNum = 133;BA.debugLine="lblRefNo.Text = GlobalVar.SF.Upper(RSJOSASDetai";
mostCurrent._lblrefno.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rsjosasdetails.GetString("RefNo"))));
 //BA.debugLineNum = 134;BA.debugLine="lblCustName.Text = GlobalVar.SF.Upper(RSJOSASDe";
mostCurrent._lblcustname.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rsjosasdetails.GetString("CustName"))));
 //BA.debugLineNum = 135;BA.debugLine="lblCustAddress.Text = GlobalVar.SF.Upper(RSJOSA";
mostCurrent._lblcustaddress.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rsjosasdetails.GetString("CustAddress"))));
 //BA.debugLineNum = 137;BA.debugLine="lblAcctClass.Text = GlobalVar.SF.Upper(RSJOSASD";
mostCurrent._lblacctclass.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rsjosasdetails.GetString("AcctClassification"))));
 //BA.debugLineNum = 138;BA.debugLine="lblConType.Text = GlobalVar.SF.Upper(RSJOSASDet";
mostCurrent._lblcontype.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rsjosasdetails.GetString("ConTypeDesc"))));
 //BA.debugLineNum = 139;BA.debugLine="lblRemarks.Text = GlobalVar.SF.Upper(RSJOSASDet";
mostCurrent._lblremarks.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rsjosasdetails.GetString("Remarks"))));
 //BA.debugLineNum = 141;BA.debugLine="lblDatesStart.Text = GlobalVar.SF.Upper(RSJOSAS";
mostCurrent._lbldatesstart.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rsjosasdetails.GetString("DateStarted"))));
 //BA.debugLineNum = 142;BA.debugLine="lblDateAccomplished.Text = GlobalVar.SF.Upper(R";
mostCurrent._lbldateaccomplished.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rsjosasdetails.GetString("DateFinished"))));
 //BA.debugLineNum = 143;BA.debugLine="sPlumbersID = RSJOSASDetails.GetString(\"Accompl";
_splumbersid = _rsjosasdetails.GetString("AccomplishedBy");
 //BA.debugLineNum = 144;BA.debugLine="lblAccomplishedBy.Text = GlobalVar.SF.Upper(DBa";
mostCurrent._lblaccomplishedby.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(mostCurrent._dbasefunctions._getplumbernames /*String*/ (mostCurrent.activityBA,_splumbersid))));
 }else {
 //BA.debugLineNum = 146;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("164028705",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 147;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e27) {
			processBA.setLastException(e27); //BA.debugLineNum = 151;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("164028710",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 153;BA.debugLine="RSJOSASDetails.Close";
_rsjosasdetails.Close();
 //BA.debugLineNum = 154;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Private lblJOCat As Label";
mostCurrent._lbljocat = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private lblJONo As Label";
mostCurrent._lbljono = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private lblRef_No As Label";
mostCurrent._lblref_no = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private lblRefNo As Label";
mostCurrent._lblrefno = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private lblCustName As Label";
mostCurrent._lblcustname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private lblCustAddress As Label";
mostCurrent._lblcustaddress = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private lblAcctClass As Label";
mostCurrent._lblacctclass = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private lblConType As Label";
mostCurrent._lblcontype = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private lblRemarks As Label";
mostCurrent._lblremarks = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private lblDatesStart As Label";
mostCurrent._lbldatesstart = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private lblDateAccomplished As Label";
mostCurrent._lbldateaccomplished = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private lblAccomplishedBy As Label";
mostCurrent._lblaccomplishedby = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private btnOk As ACButton";
mostCurrent._btnok = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private btnPrint As ACButton";
mostCurrent._btnprint = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private cdOk, cdPrint As ColorDrawable";
mostCurrent._cdok = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cdprint = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
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

public boolean _onCreateOptionsMenu(android.view.Menu menu) {
	if (processBA.subExists("activity_createmenu")) {
		processBA.raiseEvent2(null, true, "activity_createmenu", false, new de.amberhome.objects.appcompat.ACMenuWrapper(menu));
		return true;
	}
	else
		return false;
}
}
