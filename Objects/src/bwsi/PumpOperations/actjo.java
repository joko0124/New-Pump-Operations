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

public class actjo extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static actjo mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.actjo");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actjo).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.actjo");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.actjo", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actjo) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actjo) Resume **");
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
		return actjo.class;
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
            BA.LogInfo("** Activity (actjo) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (actjo) Pause event (activity is not paused). **");
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
            actjo mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (actjo) Resume **");
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
public b4a.example3.customlistview _clvjos = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblappno = null;
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
public anywheresoftware.b4a.objects.IME _imekeyboard = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblreftitle = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlstatus = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcount = null;
public static int _selectedposition = 0;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fontbold = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlsasdetails = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnsasedit = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnsasok = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbllsasappno = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblsasacctclass = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblsascontype = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblsascustaddress = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblsascustname = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblsasfindings = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblsasjono = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblsasdateaccomplished = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblsasdatesstart = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cddialogedit = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cddialogcancel = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cddialogok = null;
public com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
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
public static class _jorecords{
public boolean IsInitialized;
public int JOID;
public String JONum;
public String JOCatCode;
public int JOStatus;
public int RefID;
public String RefNo;
public String CustName;
public String CustAdd;
public String AcctClass;
public String AcctSubClass;
public void Initialize() {
IsInitialized = true;
JOID = 0;
JONum = "";
JOCatCode = "";
JOStatus = 0;
RefID = 0;
RefNo = "";
CustName = "";
CustAdd = "";
AcctClass = "";
AcctSubClass = "";
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
 //BA.debugLineNum = 86;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 87;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 88;BA.debugLine="Activity.LoadLayout(\"JOList\")";
mostCurrent._activity.LoadLayout("JOList",mostCurrent.activityBA);
 //BA.debugLineNum = 90;BA.debugLine="GlobalVar.SelectedJODesc = DBaseFunctions.GetJODe";
mostCurrent._globalvar._selectedjodesc /*String*/  = mostCurrent._dbasefunctions._getjodesc /*String*/ (mostCurrent.activityBA,mostCurrent._globalvar._selectedjocatcode /*String*/ );
 //BA.debugLineNum = 92;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Append";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(mostCurrent._globalvar._selectedjodesc /*String*/ +(" - Job Order(s)"))).PopAll();
 //BA.debugLineNum = 93;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append($";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(("List of Job Orders"))).PopAll();
 //BA.debugLineNum = 95;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 96;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 97;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 99;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 100;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 101;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 102;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 103;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 104;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 106;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 107;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 109;BA.debugLine="cdSearch.Initialize(Colors.Transparent, 0)";
mostCurrent._cdsearch.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 110;BA.debugLine="txtSearch.Background = cdSearch";
mostCurrent._txtsearch.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cdsearch.getObject()));
 //BA.debugLineNum = 112;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 113;BA.debugLine="FillJOList(GlobalVar.SelectedJOCatCode)";
_filljolist(mostCurrent._globalvar._selectedjocatcode /*String*/ );
 //BA.debugLineNum = 114;BA.debugLine="HideDialogs";
_hidedialogs();
 };
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
de.amberhome.objects.appcompat.ACMenuItemWrapper _item = null;
 //BA.debugLineNum = 139;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 140;BA.debugLine="Dim Item As ACMenuItem";
_item = new de.amberhome.objects.appcompat.ACMenuItemWrapper();
 //BA.debugLineNum = 142;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 143;BA.debugLine="Menu.Add2(1, 1, \"Filter by\",xmlIcon.GetDrawable(\"";
_menu.Add2((int) (1),(int) (1),BA.ObjectToCharSequence("Filter by"),mostCurrent._xmlicon.GetDrawable("baseline_filter_alt_white_24dp")).setShowAsAction(_item.SHOW_AS_ACTION_IF_ROOM);
 //BA.debugLineNum = 145;BA.debugLine="CreateSubMenus";
_createsubmenus();
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 119;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 120;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 121;BA.debugLine="ToolBar_NavigationItemClick";
_toolbar_navigationitemclick();
 //BA.debugLineNum = 122;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 124;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 133;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 134;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 128;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 129;BA.debugLine="FillJOList(GlobalVar.SelectedJOCatCode)";
_filljolist(mostCurrent._globalvar._selectedjocatcode /*String*/ );
 //BA.debugLineNum = 130;BA.debugLine="HideDialogs";
_hidedialogs();
 //BA.debugLineNum = 131;BA.debugLine="End Sub";
return "";
}
public static String  _btnedit_click() throws Exception{
 //BA.debugLineNum = 617;BA.debugLine="Sub btnEdit_Click";
 //BA.debugLineNum = 619;BA.debugLine="End Sub";
return "";
}
public static String  _btnsasok_click() throws Exception{
 //BA.debugLineNum = 613;BA.debugLine="Sub btnSASOK_Click";
 //BA.debugLineNum = 614;BA.debugLine="HideDialogs";
_hidedialogs();
 //BA.debugLineNum = 615;BA.debugLine="End Sub";
return "";
}
public static String  _clvjos_itemclick(int _index,Object _value) throws Exception{
bwsi.PumpOperations.actjo._jorecords _jorec = null;
 //BA.debugLineNum = 295;BA.debugLine="Sub clvJOs_ItemClick (Index As Int, Value As Objec";
 //BA.debugLineNum = 296;BA.debugLine="Dim JORec As JORecords = Value";
_jorec = (bwsi.PumpOperations.actjo._jorecords)(_value);
 //BA.debugLineNum = 297;BA.debugLine="LogColor(Value, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("730081026",BA.ObjectToString(_value),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 298;BA.debugLine="LogColor(JORec.JOID, Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogImpl("730081027",BA.NumberToString(_jorec.JOID /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 299;BA.debugLine="LogColor(JORec.JONum, Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("730081028",_jorec.JONum /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 300;BA.debugLine="LogColor(JORec.JOStatus, Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogImpl("730081029",BA.NumberToString(_jorec.JOStatus /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 302;BA.debugLine="GlobalVar.SelectedJOID = JORec.JOID";
mostCurrent._globalvar._selectedjoid /*int*/  = _jorec.JOID /*int*/ ;
 //BA.debugLineNum = 303;BA.debugLine="Select JORec.JOStatus";
switch (BA.switchObjectToInt(_jorec.JOStatus /*int*/ ,(int) (1),(int) (2),(int) (3),(int) (4),(int) (5))) {
case 0: {
 //BA.debugLineNum = 305;BA.debugLine="vibration.vibrateOnce(2000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (2000));
 //BA.debugLineNum = 306;BA.debugLine="ConfirmStartJO(JORec.JOID)";
_confirmstartjo(_jorec.JOID /*int*/ );
 break; }
case 1: {
 //BA.debugLineNum = 308;BA.debugLine="Select JORec.JOCatCode";
switch (BA.switchObjectToInt(_jorec.JOCatCode /*String*/ ,"SAS","NC","DC-CR","DC-DA","RC","CM")) {
case 0: {
 //BA.debugLineNum = 310;BA.debugLine="StartActivity(actSASJOFindings)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actsasjofindings.getObject()));
 break; }
case 1: {
 //BA.debugLineNum = 312;BA.debugLine="StartActivity(actNCJOFindings)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actncjofindings.getObject()));
 break; }
case 2: {
 //BA.debugLineNum = 314;BA.debugLine="StartActivity(actDCCRJOFindings)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actdccrjofindings.getObject()));
 break; }
case 3: {
 //BA.debugLineNum = 316;BA.debugLine="StartActivity(actDCDAJOFindings)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actdcdajofindings.getObject()));
 break; }
case 4: {
 //BA.debugLineNum = 318;BA.debugLine="StartActivity(actRCJOFindings)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actrcjofindings.getObject()));
 break; }
case 5: {
 //BA.debugLineNum = 320;BA.debugLine="StartActivity(actCMJOFindings)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcmjofindings.getObject()));
 break; }
}
;
 break; }
case 2: {
 //BA.debugLineNum = 323;BA.debugLine="Select JORec.JOCatCode";
switch (BA.switchObjectToInt(_jorec.JOCatCode /*String*/ ,"SAS","NC","DC-CR","DC-DA","RC","CM")) {
case 0: {
 //BA.debugLineNum = 325;BA.debugLine="StartActivity(actJOAccomplishedSAS)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actjoaccomplishedsas.getObject()));
 break; }
case 1: {
 //BA.debugLineNum = 327;BA.debugLine="StartActivity(actNCJOFindings)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actncjofindings.getObject()));
 break; }
case 2: {
 //BA.debugLineNum = 329;BA.debugLine="StartActivity(actDCCRJOFindings)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actdccrjofindings.getObject()));
 break; }
case 3: {
 //BA.debugLineNum = 331;BA.debugLine="StartActivity(actDCDAJOFindings)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actdcdajofindings.getObject()));
 break; }
case 4: {
 //BA.debugLineNum = 333;BA.debugLine="StartActivity(actRCJOFindings)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actrcjofindings.getObject()));
 break; }
case 5: {
 //BA.debugLineNum = 335;BA.debugLine="StartActivity(actCMJOFindings)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcmjofindings.getObject()));
 break; }
default: {
 //BA.debugLineNum = 337;BA.debugLine="ShowSASJODetails(JORec.JOID)";
_showsasjodetails(_jorec.JOID /*int*/ );
 break; }
}
;
 break; }
case 3: {
 break; }
case 4: {
 break; }
}
;
 //BA.debugLineNum = 343;BA.debugLine="End Sub";
return "";
}
public static String  _clvjos_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
int _extrasize = 0;
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
bwsi.PumpOperations.actjo._jorecords _jorec = null;
 //BA.debugLineNum = 345;BA.debugLine="Sub clvJOs_VisibleRangeChanged (FirstIndex As Int,";
 //BA.debugLineNum = 346;BA.debugLine="Dim ExtraSize As Int = 15 'List size";
_extrasize = (int) (15);
 //BA.debugLineNum = 347;BA.debugLine="clvJOs.Refresh";
mostCurrent._clvjos._refresh();
 //BA.debugLineNum = 349;BA.debugLine="For i = Max(0, FirstIndex - ExtraSize) To Min(Las";
{
final int step3 = 1;
final int limit3 = (int) (anywheresoftware.b4a.keywords.Common.Min(_lastindex+_extrasize,mostCurrent._clvjos._getsize()-1));
_i = (int) (anywheresoftware.b4a.keywords.Common.Max(0,_firstindex-_extrasize)) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 350;BA.debugLine="Dim Pnl As B4XView = clvJOs.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._clvjos._getpanel(_i);
 //BA.debugLineNum = 351;BA.debugLine="If i > FirstIndex - ExtraSize And i < LastIndex";
if (_i>_firstindex-_extrasize && _i<_lastindex+_extrasize) { 
 //BA.debugLineNum = 352;BA.debugLine="If Pnl.NumberOfViews = 0 Then 'Add each item/la";
if (_pnl.getNumberOfViews()==0) { 
 //BA.debugLineNum = 353;BA.debugLine="Dim JORec As JORecords = clvJOs.GetValue(i)";
_jorec = (bwsi.PumpOperations.actjo._jorecords)(mostCurrent._clvjos._getvalue(_i));
 //BA.debugLineNum = 354;BA.debugLine="Pnl.LoadLayout(\"JOInfo\")";
_pnl.LoadLayout("JOInfo",mostCurrent.activityBA);
 //BA.debugLineNum = 355;BA.debugLine="If GlobalVar.SelectedJOCatCode = \"SAS\" Or Glob";
if ((mostCurrent._globalvar._selectedjocatcode /*String*/ ).equals("SAS") || (mostCurrent._globalvar._selectedjocatcode /*String*/ ).equals("NC")) { 
 //BA.debugLineNum = 356;BA.debugLine="lblRefTitle.Text = $\"Application No.: \"$";
mostCurrent._lblreftitle.setText(BA.ObjectToCharSequence(("Application No.: ")));
 }else {
 //BA.debugLineNum = 358;BA.debugLine="lblRefTitle.Text = $\"Account No.: \"$";
mostCurrent._lblreftitle.setText(BA.ObjectToCharSequence(("Account No.: ")));
 };
 //BA.debugLineNum = 361;BA.debugLine="lblJONum.Text = JORec.JONum";
mostCurrent._lbljonum.setText(BA.ObjectToCharSequence(_jorec.JONum /*String*/ ));
 //BA.debugLineNum = 362;BA.debugLine="lblAppNo.Text = JORec.RefNo";
mostCurrent._lblappno.setText(BA.ObjectToCharSequence(_jorec.RefNo /*String*/ ));
 //BA.debugLineNum = 363;BA.debugLine="lblCustName.Text = JORec.CustName";
mostCurrent._lblcustname.setText(BA.ObjectToCharSequence(_jorec.CustName /*String*/ ));
 //BA.debugLineNum = 364;BA.debugLine="lblCustAddress.Text = JORec.CustAdd";
mostCurrent._lblcustaddress.setText(BA.ObjectToCharSequence(_jorec.CustAdd /*String*/ ));
 //BA.debugLineNum = 365;BA.debugLine="lblCustAddress.Ellipsize = \"END\"";
mostCurrent._lblcustaddress.setEllipsize("END");
 //BA.debugLineNum = 367;BA.debugLine="If JORec.JOStatus = 1 Then";
if (_jorec.JOStatus /*int*/ ==1) { 
 //BA.debugLineNum = 368;BA.debugLine="lblStatus.Color = GlobalVar.GrayColor";
mostCurrent._lblstatus.setColor((int) (mostCurrent._globalvar._graycolor /*double*/ ));
 //BA.debugLineNum = 369;BA.debugLine="lblStatus.Text = \"PENDING\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("PENDING"));
 }else if(_jorec.JOStatus /*int*/ ==2) { 
 //BA.debugLineNum = 371;BA.debugLine="lblStatus.Color = GlobalVar.YellowColor";
mostCurrent._lblstatus.setColor((int) (mostCurrent._globalvar._yellowcolor /*double*/ ));
 //BA.debugLineNum = 372;BA.debugLine="lblStatus.Text = \"ON GOING\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("ON GOING"));
 }else if(_jorec.JOStatus /*int*/ ==3) { 
 //BA.debugLineNum = 374;BA.debugLine="lblStatus.Color = GlobalVar.GreenColor";
mostCurrent._lblstatus.setColor((int) (mostCurrent._globalvar._greencolor /*double*/ ));
 //BA.debugLineNum = 375;BA.debugLine="lblStatus.Text = \"ACCOMPLISHED\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("ACCOMPLISHED"));
 }else if(_jorec.JOStatus /*int*/ ==4) { 
 //BA.debugLineNum = 377;BA.debugLine="lblStatus.Color= GlobalVar.RedColor";
mostCurrent._lblstatus.setColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 378;BA.debugLine="lblStatus.Text = \"CANCELLED\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("CANCELLED"));
 }else if(_jorec.JOStatus /*int*/ ==5) { 
 //BA.debugLineNum = 380;BA.debugLine="lblStatus.Color= GlobalVar.BlueColor";
mostCurrent._lblstatus.setColor((int) (mostCurrent._globalvar._bluecolor /*double*/ ));
 //BA.debugLineNum = 381;BA.debugLine="lblStatus.Text = \"UPLOADED\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("UPLOADED"));
 };
 };
 }else {
 //BA.debugLineNum = 385;BA.debugLine="If Pnl.NumberOfViews > 0 Then";
if (_pnl.getNumberOfViews()>0) { 
 //BA.debugLineNum = 386;BA.debugLine="Pnl.RemoveAllViews 'Remove none visable item/l";
_pnl.RemoveAllViews();
 };
 };
 }
};
 //BA.debugLineNum = 390;BA.debugLine="End Sub";
return "";
}
public static String  _confirmstartjo(int _ijoid) throws Exception{
 //BA.debugLineNum = 621;BA.debugLine="Private Sub ConfirmStartJO(iJOID As Int)";
 //BA.debugLineNum = 622;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 624;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
mostCurrent._alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(mostCurrent._alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle("START JO").SetTitleColor((int) (mostCurrent._globalvar._bluecolor /*double*/ )).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetMessage(("You are about to Start this JO,")+anywheresoftware.b4a.keywords.Common.CRLF+("JO Date & Time Started will be today.")+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+("Do you want to START this JO Now?")).SetPositiveText("Confirm").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetNegativeText("Cancel").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"JO_Started").SetOnNegativeClicked(mostCurrent.activityBA,"JO_Started").SetOnViewBinder(mostCurrent.activityBA,"JOFontSizeBinder");
 //BA.debugLineNum = 642;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
mostCurrent._alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 643;BA.debugLine="Alert.Build.Show";
mostCurrent._alert.Build().Show();
 //BA.debugLineNum = 645;BA.debugLine="End Sub";
return "";
}
public static String  _createsubmenus() throws Exception{
anywheresoftware.b4a.objects.CSBuilder _csall = null;
anywheresoftware.b4a.objects.CSBuilder _cspending = null;
anywheresoftware.b4a.objects.CSBuilder _csaccomp = null;
anywheresoftware.b4a.objects.CSBuilder _csongoing = null;
anywheresoftware.b4a.objects.CSBuilder _cscan = null;
 //BA.debugLineNum = 148;BA.debugLine="Private Sub CreateSubMenus";
 //BA.debugLineNum = 149;BA.debugLine="Dim csAll, csPending, csAccomp, csOnGoing, csCan";
_csall = new anywheresoftware.b4a.objects.CSBuilder();
_cspending = new anywheresoftware.b4a.objects.CSBuilder();
_csaccomp = new anywheresoftware.b4a.objects.CSBuilder();
_csongoing = new anywheresoftware.b4a.objects.CSBuilder();
_cscan = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 151;BA.debugLine="csAll.Initialize.Color(Colors.White).Append($\"All";
_csall.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(("All"))).PopAll();
 //BA.debugLineNum = 152;BA.debugLine="csPending.Initialize.Color(Colors.White).Append($";
_cspending.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(("Pending"))).PopAll();
 //BA.debugLineNum = 153;BA.debugLine="csOnGoing.Initialize.Color(Colors.White).Append($";
_csongoing.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(("On-Going"))).PopAll();
 //BA.debugLineNum = 154;BA.debugLine="csAccomp.Initialize.Color(Colors.White).Append($\"";
_csaccomp.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(("Accomplished"))).PopAll();
 //BA.debugLineNum = 155;BA.debugLine="csCan.Initialize.Color(Colors.White).Append($\"Can";
_cscan.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(("Cancelled"))).PopAll();
 //BA.debugLineNum = 157;BA.debugLine="PopSubMenu.Initialize(\"FilterBy\", ToolBar.GetView";
mostCurrent._popsubmenu.Initialize(mostCurrent.activityBA,"FilterBy",(android.view.View)(mostCurrent._toolbar.GetView((int) (3)).getObject()));
 //BA.debugLineNum = 158;BA.debugLine="PopSubMenu.AddMenuItem(0,csAll,xmlIcon.GetDrawabl";
mostCurrent._popsubmenu.AddMenuItem((int) (0),BA.ObjectToCharSequence(_csall.getObject()),mostCurrent._xmlicon.GetDrawable("ic_select_all_white_24dp"));
 //BA.debugLineNum = 159;BA.debugLine="PopSubMenu.AddMenuItem(1,csPending,xmlIcon.GetDra";
mostCurrent._popsubmenu.AddMenuItem((int) (1),BA.ObjectToCharSequence(_cspending.getObject()),mostCurrent._xmlicon.GetDrawable("baseline_pending_actions_white_24dp"));
 //BA.debugLineNum = 160;BA.debugLine="PopSubMenu.AddMenuItem(2,csOnGoing,xmlIcon.GetDra";
mostCurrent._popsubmenu.AddMenuItem((int) (2),BA.ObjectToCharSequence(_csongoing.getObject()),mostCurrent._xmlicon.GetDrawable("baseline_engineering_white_24dp"));
 //BA.debugLineNum = 161;BA.debugLine="PopSubMenu.AddMenuItem(3,csAccomp,xmlIcon.GetDraw";
mostCurrent._popsubmenu.AddMenuItem((int) (3),BA.ObjectToCharSequence(_csaccomp.getObject()),mostCurrent._xmlicon.GetDrawable("baseline_assignment_turned_in_white_24dp"));
 //BA.debugLineNum = 162;BA.debugLine="PopSubMenu.AddMenuItem(4,csCan,xmlIcon.GetDrawabl";
mostCurrent._popsubmenu.AddMenuItem((int) (4),BA.ObjectToCharSequence(_cscan.getObject()),mostCurrent._xmlicon.GetDrawable("baseline_cancel_presentation_white_24dp"));
 //BA.debugLineNum = 163;BA.debugLine="End Sub";
return "";
}
public static String  _dispinfomsg(String _smsg,String _stitle) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _infomsg = null;
 //BA.debugLineNum = 701;BA.debugLine="Private Sub DispInfoMsg(sMsg As String, sTitle As";
 //BA.debugLineNum = 703;BA.debugLine="Dim InfoMsg As AX_CustomAlertDialog";
_infomsg = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 705;BA.debugLine="InfoMsg.Initialize.Create _ 			.SetDialogStyleNam";
_infomsg.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_infomsg.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(_stitle).SetMessage(_smsg).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"MessageBox").SetOnViewBinder(mostCurrent.activityBA,"FontSizeBinder");
 //BA.debugLineNum = 719;BA.debugLine="InfoMsg.SetDialogBackground(MyFunctions.myCD)";
_infomsg.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 720;BA.debugLine="InfoMsg.Build.Show";
_infomsg.Build().Show();
 //BA.debugLineNum = 721;BA.debugLine="End Sub";
return "";
}
public static void  _filljolist(String _sselectedjocat) throws Exception{
ResumableSub_FillJOList rsub = new ResumableSub_FillJOList(null,_sselectedjocat);
rsub.resume(processBA, null);
}
public static class ResumableSub_FillJOList extends BA.ResumableSub {
public ResumableSub_FillJOList(bwsi.PumpOperations.actjo parent,String _sselectedjocat) {
this.parent = parent;
this._sselectedjocat = _sselectedjocat;
}
bwsi.PumpOperations.actjo parent;
String _sselectedjocat;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actjo._jorecords _jorec = null;
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
 //BA.debugLineNum = 186;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 188;BA.debugLine="Try";
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
 //BA.debugLineNum = 189;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblJOs \" &";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblJOs "+"WHERE JOCatCode = '"+_sselectedjocat+"' "+"ORDER BY JOCreatedAt ASC";
 //BA.debugLineNum = 192;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("729949959",parent.mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 194;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 195;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 17;
return;
case 17:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 196;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 197;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 198;BA.debugLine="clvJOs.Clear";
parent.mostCurrent._clvjos._clear();
 //BA.debugLineNum = 199;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 200;BA.debugLine="Dim JORec As JORecords";
_jorec = new bwsi.PumpOperations.actjo._jorecords();
 //BA.debugLineNum = 201;BA.debugLine="JORec.Initialize";
_jorec.Initialize();
 //BA.debugLineNum = 202;BA.debugLine="JORec.JOID = RS.GetInt(\"JOID\")";
_jorec.JOID /*int*/  = _rs.GetInt("JOID");
 //BA.debugLineNum = 203;BA.debugLine="JORec.JONum = GlobalVar.SF.Upper(RS.GetString(";
_jorec.JONum /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JONo"));
 //BA.debugLineNum = 204;BA.debugLine="JORec.JOCatCode = GlobalVar.SF.Upper(RS.GetStr";
_jorec.JOCatCode /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JOCatCode"));
 //BA.debugLineNum = 205;BA.debugLine="JORec.RefID= RS.GetInt(\"RefID\")";
_jorec.RefID /*int*/  = _rs.GetInt("RefID");
 //BA.debugLineNum = 206;BA.debugLine="JORec.RefNo= GlobalVar.SF.Upper(RS.GetString(\"";
_jorec.RefNo /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("RefNo"));
 //BA.debugLineNum = 207;BA.debugLine="JORec.CustName = GlobalVar.SF.Upper(RS.GetStri";
_jorec.CustName /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustName"));
 //BA.debugLineNum = 208;BA.debugLine="JORec.CustAdd = GlobalVar.SF.Upper(RS.GetStrin";
_jorec.CustAdd /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustAddress"));
 //BA.debugLineNum = 209;BA.debugLine="JORec.AcctClass = GlobalVar.SF.Upper(RS.GetStr";
_jorec.AcctClass /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctClass"));
 //BA.debugLineNum = 210;BA.debugLine="JORec.AcctSubClass = GlobalVar.SF.Upper(RS.Get";
_jorec.AcctSubClass /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctSubClass"));
 //BA.debugLineNum = 211;BA.debugLine="JORec.JOStatus = RS.GetInt(\"JOStatus\")";
_jorec.JOStatus /*int*/  = _rs.GetInt("JOStatus");
 //BA.debugLineNum = 212;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 213;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0, clvJOs.AsVi";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),parent.mostCurrent._clvjos._asview().getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)));
 //BA.debugLineNum = 214;BA.debugLine="clvJOs.Add(Pnl, JORec)";
parent.mostCurrent._clvjos._add(_pnl,(Object)(_jorec));
 if (true) break;

case 10:
//C
this.state = 13;
;
 //BA.debugLineNum = 216;BA.debugLine="lblCount.Text = RS.RowCount & $\" Record(s) Foun";
parent.mostCurrent._lblcount.setText(BA.ObjectToCharSequence(BA.NumberToString(_rs.getRowCount())+(" Record(s) Found")));
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 218;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 219;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 220;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 221;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 222;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("729949989",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 13:
//C
this.state = 16;
;
 //BA.debugLineNum = 225;BA.debugLine="Log($\"List of Job Order Records = ${NumberFormat";
anywheresoftware.b4a.keywords.Common.LogImpl("729949992",("List of Job Order Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvjos._getsize()))+" Flow Meter Reading Records"),0);
 if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
 //BA.debugLineNum = 227;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("729949994",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 230;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 177;BA.debugLine="Sub FilterBy_ItemClicked (Item As ACMenuItem)";
 //BA.debugLineNum = 178;BA.debugLine="Log(\"Popupmenu Item clicked: \" & Item.Id & \" - \"";
anywheresoftware.b4a.keywords.Common.LogImpl("729884417","Popupmenu Item clicked: "+BA.NumberToString(_item.getId())+" - "+_item.getTitle(),0);
 //BA.debugLineNum = 179;BA.debugLine="FilterJoList(GlobalVar.SelectedJOCatCode, Item.Id";
_filterjolist(mostCurrent._globalvar._selectedjocatcode /*String*/ ,_item.getId());
 //BA.debugLineNum = 180;BA.debugLine="End Sub";
return "";
}
public static void  _filterjolist(String _sselectedjocat,int _istatus) throws Exception{
ResumableSub_FilterJoList rsub = new ResumableSub_FilterJoList(null,_sselectedjocat,_istatus);
rsub.resume(processBA, null);
}
public static class ResumableSub_FilterJoList extends BA.ResumableSub {
public ResumableSub_FilterJoList(bwsi.PumpOperations.actjo parent,String _sselectedjocat,int _istatus) {
this.parent = parent;
this._sselectedjocat = _sselectedjocat;
this._istatus = _istatus;
}
bwsi.PumpOperations.actjo parent;
String _sselectedjocat;
int _istatus;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actjo._jorecords _jorec = null;
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
 //BA.debugLineNum = 233;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 235;BA.debugLine="clvJOs.Clear";
parent.mostCurrent._clvjos._clear();
 //BA.debugLineNum = 236;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 25;
this.catchState = 24;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 24;
 //BA.debugLineNum = 238;BA.debugLine="If iStatus = 0 Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_istatus==0) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 239;BA.debugLine="clvJOs.Clear";
parent.mostCurrent._clvjos._clear();
 //BA.debugLineNum = 240;BA.debugLine="clvJOs.Refresh";
parent.mostCurrent._clvjos._refresh();
 //BA.debugLineNum = 241;BA.debugLine="FillJOList(sSelectedJOCat)";
_filljolist(_sselectedjocat);
 //BA.debugLineNum = 242;BA.debugLine="Return";
if (true) return ;
 if (true) break;
;
 //BA.debugLineNum = 244;BA.debugLine="Try";

case 7:
//try
this.state = 12;
this.catchState = 11;
this.state = 9;
if (true) break;

case 9:
//C
this.state = 12;
this.catchState = 11;
 //BA.debugLineNum = 245;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblJOs \"";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblJOs "+"WHERE JOCatCode = '"+_sselectedjocat+"' "+"AND JOStatus = "+BA.NumberToString(_istatus)+" "+"ORDER BY JOCreatedAt ASC";
 if (true) break;

case 11:
//C
this.state = 12;
this.catchState = 24;
 //BA.debugLineNum = 250;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("730015506",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 12:
//C
this.state = 13;
this.catchState = 24;
;
 //BA.debugLineNum = 254;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("730015510",parent.mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 255;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 256;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 26;
return;
case 26:
//C
this.state = 13;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 257;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 258;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 259;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 260;BA.debugLine="Dim JORec As JORecords";
_jorec = new bwsi.PumpOperations.actjo._jorecords();
 //BA.debugLineNum = 261;BA.debugLine="JORec.Initialize";
_jorec.Initialize();
 //BA.debugLineNum = 262;BA.debugLine="JORec.JOID = RS.GetInt(\"JOID\")";
_jorec.JOID /*int*/  = _rs.GetInt("JOID");
 //BA.debugLineNum = 263;BA.debugLine="JORec.JONum = GlobalVar.SF.Upper(RS.GetString(";
_jorec.JONum /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JONo"));
 //BA.debugLineNum = 264;BA.debugLine="JORec.JOCatCode = GlobalVar.SF.Upper(RS.GetStr";
_jorec.JOCatCode /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JOCatCode"));
 //BA.debugLineNum = 265;BA.debugLine="JORec.RefID= RS.GetInt(\"RefID\")";
_jorec.RefID /*int*/  = _rs.GetInt("RefID");
 //BA.debugLineNum = 266;BA.debugLine="JORec.RefNo= GlobalVar.SF.Upper(RS.GetString(\"";
_jorec.RefNo /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("RefNo"));
 //BA.debugLineNum = 267;BA.debugLine="JORec.CustName = GlobalVar.SF.Upper(RS.GetStri";
_jorec.CustName /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustName"));
 //BA.debugLineNum = 268;BA.debugLine="JORec.CustAdd = GlobalVar.SF.Upper(RS.GetStrin";
_jorec.CustAdd /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustAddress"));
 //BA.debugLineNum = 269;BA.debugLine="JORec.AcctClass = GlobalVar.SF.Upper(RS.GetStr";
_jorec.AcctClass /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctClass"));
 //BA.debugLineNum = 270;BA.debugLine="JORec.AcctSubClass = GlobalVar.SF.Upper(RS.Get";
_jorec.AcctSubClass /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctSubClass"));
 //BA.debugLineNum = 271;BA.debugLine="JORec.JOStatus = RS.GetInt(\"JOStatus\")";
_jorec.JOStatus /*int*/  = _rs.GetInt("JOStatus");
 //BA.debugLineNum = 274;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 275;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0, clvJOs.AsVi";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),parent.mostCurrent._clvjos._asview().getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)));
 //BA.debugLineNum = 276;BA.debugLine="clvJOs.Add(Pnl, JORec)";
parent.mostCurrent._clvjos._add(_pnl,(Object)(_jorec));
 if (true) break;

case 19:
//C
this.state = 22;
;
 //BA.debugLineNum = 278;BA.debugLine="lblCount.Text = RS.RowCount & $\" Record(s) Foun";
parent.mostCurrent._lblcount.setText(BA.ObjectToCharSequence(BA.NumberToString(_rs.getRowCount())+(" Record(s) Found")));
 if (true) break;

case 21:
//C
this.state = 22;
 //BA.debugLineNum = 280;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 281;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 282;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 283;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 284;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("730015540",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 22:
//C
this.state = 25;
;
 //BA.debugLineNum = 287;BA.debugLine="Log($\"List of Job Order Records = ${NumberFormat";
anywheresoftware.b4a.keywords.Common.LogImpl("730015543",("List of Job Order Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvjos._getsize()))+" SAS JO Records"),0);
 if (true) break;

case 24:
//C
this.state = 25;
this.catchState = 0;
 //BA.debugLineNum = 289;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("730015545",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 25:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 291;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 23;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 25;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private xmlIcon As XmlLayoutBuilder";
mostCurrent._xmlicon = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 27;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private CD As ColorDrawable";
mostCurrent._cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 29;BA.debugLine="Private vibration As B4Avibrate";
mostCurrent._vibration = new com.johan.Vibrate.Vibrate();
 //BA.debugLineNum = 30;BA.debugLine="Private vibratePattern() As Long";
_vibratepattern = new long[(int) (0)];
;
 //BA.debugLineNum = 32;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private csAns As CSBuilder";
mostCurrent._csans = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 35;BA.debugLine="Private clvJOs As CustomListView";
mostCurrent._clvjos = new b4a.example3.customlistview();
 //BA.debugLineNum = 36;BA.debugLine="Private lblAppNo As Label";
mostCurrent._lblappno = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private lblCustAddress As Label";
mostCurrent._lblcustaddress = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private lblCustName As Label";
mostCurrent._lblcustname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private lblStatus As Label";
mostCurrent._lblstatus = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private lblJONum As Label";
mostCurrent._lbljonum = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private pnlJOInfo As Panel";
mostCurrent._pnljoinfo = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Dim cdSearch As ColorDrawable";
mostCurrent._cdsearch = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 45;BA.debugLine="Private txtSearch As EditText";
mostCurrent._txtsearch = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private PopSubMenu As ACPopupMenu";
mostCurrent._popsubmenu = new de.amberhome.objects.appcompat.ACPopupMenuWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Type JORecords (JOID As Int, JONum As String, JOC";
;
 //BA.debugLineNum = 50;BA.debugLine="Private JOCounts As Int";
_jocounts = 0;
 //BA.debugLineNum = 51;BA.debugLine="Private Limit As Int = 2000";
_limit = (int) (2000);
 //BA.debugLineNum = 52;BA.debugLine="Private IMEKeyboard As IME";
mostCurrent._imekeyboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 54;BA.debugLine="Private lblRefTitle As Label";
mostCurrent._lblreftitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private pnlStatus As Panel";
mostCurrent._pnlstatus = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private lblCount As Label";
mostCurrent._lblcount = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private SelectedPosition As Int = -1 'SelectorDia";
_selectedposition = (int) (-1);
 //BA.debugLineNum = 59;BA.debugLine="Private Font As Typeface = Typeface.LoadFromAsset";
mostCurrent._font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont.ttf")));
 //BA.debugLineNum = 60;BA.debugLine="Private FontBold As Typeface = Typeface.LoadFromA";
mostCurrent._fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont_bold.ttf")));
 //BA.debugLineNum = 64;BA.debugLine="Private pnlSASDetails As Panel";
mostCurrent._pnlsasdetails = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 65;BA.debugLine="Private btnSASEdit As ACButton";
mostCurrent._btnsasedit = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 66;BA.debugLine="Private btnSASOK As ACButton";
mostCurrent._btnsasok = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Private lbllSASAppNo As Label";
mostCurrent._lbllsasappno = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 68;BA.debugLine="Private lblSASAcctClass As Label";
mostCurrent._lblsasacctclass = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 69;BA.debugLine="Private lblSASConType As Label";
mostCurrent._lblsascontype = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 70;BA.debugLine="Private lblSASCustAddress As Label";
mostCurrent._lblsascustaddress = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 71;BA.debugLine="Private lblSASCustName As Label";
mostCurrent._lblsascustname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 72;BA.debugLine="Private lblSASFindings As Label";
mostCurrent._lblsasfindings = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 73;BA.debugLine="Private lblSASJONo As Label";
mostCurrent._lblsasjono = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 75;BA.debugLine="Private lblSASDateAccomplished As Label";
mostCurrent._lblsasdateaccomplished = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 76;BA.debugLine="Private lblSASDatesStart As Label";
mostCurrent._lblsasdatesstart = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 79;BA.debugLine="Dim cdDialogEdit, cdDialogCancel, cdDialogOk As C";
mostCurrent._cddialogedit = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cddialogcancel = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
mostCurrent._cddialogok = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 80;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 82;BA.debugLine="End Sub";
return "";
}
public static String  _hidedialogs() throws Exception{
 //BA.debugLineNum = 758;BA.debugLine="Private Sub HideDialogs";
 //BA.debugLineNum = 759;BA.debugLine="pnlSASDetails.Visible = False";
mostCurrent._pnlsasdetails.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 760;BA.debugLine="End Sub";
return "";
}
public static String  _jo_started_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 648;BA.debugLine="Private Sub JO_Started_OnNegativeClicked (View As";
 //BA.debugLineNum = 650;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 651;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 652;BA.debugLine="End Sub";
return "";
}
public static String  _jo_started_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 654;BA.debugLine="Private Sub JO_Started_OnPositiveClicked (View As";
 //BA.debugLineNum = 656;BA.debugLine="vibration.vibrateCancel";
mostCurrent._vibration.vibrateCancel(processBA);
 //BA.debugLineNum = 657;BA.debugLine="LogColor(GlobalVar.SelectedJOID, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("730867459",BA.NumberToString(mostCurrent._globalvar._selectedjoid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 659;BA.debugLine="If StartJO(GlobalVar.SelectedJOID) = True Then";
if (_startjo(mostCurrent._globalvar._selectedjoid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 660;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 661;BA.debugLine="FillJOList(GlobalVar.SelectedJOCatCode)";
_filljolist(mostCurrent._globalvar._selectedjocatcode /*String*/ );
 //BA.debugLineNum = 662;BA.debugLine="DispInfoMsg($\"Selected JO Started...\"$, $\"JO UPD";
_dispinfomsg(("Selected JO Started..."),("JO UPDATED"));
 }else {
 //BA.debugLineNum = 664;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 667;BA.debugLine="End Sub";
return "";
}
public static String  _jofontsizebinder_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 669;BA.debugLine="Private Sub JOFontSizeBinder_OnBindView (View As V";
 //BA.debugLineNum = 670;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
mostCurrent._alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 671;BA.debugLine="Alert.Initialize";
mostCurrent._alert.Initialize();
 //BA.debugLineNum = 672;BA.debugLine="If ViewType = Alert.VIEW_TITLE Then ' Title";
if (_viewtype==mostCurrent._alert.VIEW_TITLE) { 
 //BA.debugLineNum = 673;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 678;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 682;BA.debugLine="CS.Initialize.Typeface(Typeface.DEFAULT_BOLD).Ty";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color(anywheresoftware.b4a.keywords.Common.Colors.Red).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe066)))+" "));
 //BA.debugLineNum = 683;BA.debugLine="CS.Typeface(Font).Size(22).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(mostCurrent._font.getObject())).Size((int) (22)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 685;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 687;BA.debugLine="If ViewType = Alert.VIEW_MESSAGE Then";
if (_viewtype==mostCurrent._alert.VIEW_MESSAGE) { 
 //BA.debugLineNum = 688;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 689;BA.debugLine="lbl.TextSize = 16";
_lbl.setTextSize((float) (16));
 //BA.debugLineNum = 690;BA.debugLine="lbl.TextColor = Colors.Gray";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 };
 //BA.debugLineNum = 693;BA.debugLine="If ViewType = Alert.VIEW_NEGATIVE Or ViewType = A";
if (_viewtype==mostCurrent._alert.VIEW_NEGATIVE || _viewtype==mostCurrent._alert.VIEW_POSITIVE) { 
 //BA.debugLineNum = 694;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 695;BA.debugLine="lbl.TextSize = 18";
_lbl.setTextSize((float) (18));
 };
 //BA.debugLineNum = 698;BA.debugLine="End Sub";
return "";
}
public static String  _messagebox_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
 //BA.debugLineNum = 723;BA.debugLine="Private Sub MessageBox_OnPositiveClicked (View As";
 //BA.debugLineNum = 724;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
mostCurrent._alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 725;BA.debugLine="End Sub";
return "";
}
public static String  _pnlsasdetails_click() throws Exception{
 //BA.debugLineNum = 609;BA.debugLine="Sub pnlSASDetails_Click";
 //BA.debugLineNum = 611;BA.debugLine="End Sub";
return "";
}
public static String  _pnlsasdetails_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 605;BA.debugLine="Sub pnlSASDetails_Touch (Action As Int, X As Float";
 //BA.debugLineNum = 607;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static void  _showjodetails(int _iid,String _scatcode) throws Exception{
ResumableSub_ShowJODetails rsub = new ResumableSub_ShowJODetails(null,_iid,_scatcode);
rsub.resume(processBA, null);
}
public static class ResumableSub_ShowJODetails extends BA.ResumableSub {
public ResumableSub_ShowJODetails(bwsi.PumpOperations.actjo parent,int _iid,String _scatcode) {
this.parent = parent;
this._iid = _iid;
this._scatcode = _scatcode;
}
bwsi.PumpOperations.actjo parent;
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
 //BA.debugLineNum = 448;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 449;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 450;BA.debugLine="Dim sJONo, sJOCatCode, sJODesc, sRefNo, sCustName";
_sjono = "";
_sjocatcode = "";
_sjodesc = "";
_srefno = "";
_scustname = "";
_scustadd = "";
_sacctclass = "";
_scontype = "";
 //BA.debugLineNum = 451;BA.debugLine="Dim sDateCreated, sDateStart, sDateFinished As St";
_sdatecreated = "";
_sdatestart = "";
_sdatefinished = "";
 //BA.debugLineNum = 454;BA.debugLine="sJONo = \"\"";
_sjono = "";
 //BA.debugLineNum = 455;BA.debugLine="sJODesc = \"\"";
_sjodesc = "";
 //BA.debugLineNum = 456;BA.debugLine="sRefNo = \"\"";
_srefno = "";
 //BA.debugLineNum = 457;BA.debugLine="sCustName = \"\"";
_scustname = "";
 //BA.debugLineNum = 458;BA.debugLine="sCustAdd = \"\"";
_scustadd = "";
 //BA.debugLineNum = 459;BA.debugLine="sAcctClass = \"\"";
_sacctclass = "";
 //BA.debugLineNum = 460;BA.debugLine="sDateCreated = \"\"";
_sdatecreated = "";
 //BA.debugLineNum = 461;BA.debugLine="sDateStart = \"\"";
_sdatestart = "";
 //BA.debugLineNum = 462;BA.debugLine="sDateFinished = \"\"";
_sdatefinished = "";
 //BA.debugLineNum = 463;BA.debugLine="sConType = \"\"";
_scontype = "";
 //BA.debugLineNum = 465;BA.debugLine="GlobalVar.TranHeaderID = DBaseFunctions.GetHeader";
parent.mostCurrent._globalvar._tranheaderid /*int*/  = parent.mostCurrent._dbasefunctions._getheaderid /*int*/ (mostCurrent.activityBA,parent.mostCurrent._globalvar._pumphouseid /*int*/ ,parent.mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 466;BA.debugLine="Try";
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
 //BA.debugLineNum = 467;BA.debugLine="Starter.strCriteria = \"SELECT JO.JONo, JO.JoDesc";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT JO.JONo, JO.JoDesc, "+"JO.RefNo AS App_AcctNo, JO.CustName, JO.CustAddress, "+"JO.AcctClass, JO.AcctSubClass, JO.ConType, "+"JO.JOCreatedAt, JO.DateStarted, JO.DateFinished "+"FROM tblJOs AS JO "+"WHERE JO.JOID = "+BA.NumberToString(_iid);
 //BA.debugLineNum = 474;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 475;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 24;
return;
case 24:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 477;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 478;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 479;BA.debugLine="sJONo = RS.GetString(\"JONo\")";
_sjono = _rs.GetString("JONo");
 //BA.debugLineNum = 480;BA.debugLine="sJODesc = RS.GetString(\"JoDesc\")";
_sjodesc = _rs.GetString("JoDesc");
 //BA.debugLineNum = 481;BA.debugLine="sRefNo = RS.GetString(\"App_AcctNo\")";
_srefno = _rs.GetString("App_AcctNo");
 //BA.debugLineNum = 482;BA.debugLine="sCustName = RS.GetString(\"CustName\")";
_scustname = _rs.GetString("CustName");
 //BA.debugLineNum = 483;BA.debugLine="sCustAdd = RS.GetString(\"CustAddress\")";
_scustadd = _rs.GetString("CustAddress");
 //BA.debugLineNum = 484;BA.debugLine="sAcctClass = RS.GetString(\"AcctClass\") & \"-\" &";
_sacctclass = _rs.GetString("AcctClass")+"-"+_rs.GetString("AcctSubClass");
 //BA.debugLineNum = 485;BA.debugLine="sConType = RS.GetString(\"ConType\")";
_scontype = _rs.GetString("ConType");
 //BA.debugLineNum = 486;BA.debugLine="sDateCreated = RS.GetString(\"JOCreatedAt\")";
_sdatecreated = _rs.GetString("JOCreatedAt");
 //BA.debugLineNum = 487;BA.debugLine="sDateStart = RS.GetString(\"DateStarted\")";
_sdatestart = _rs.GetString("DateStarted");
 //BA.debugLineNum = 488;BA.debugLine="sDateFinished = RS.GetString(\"DateFinished\")";
_sdatefinished = _rs.GetString("DateFinished");
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 490;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 491;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 492;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 493;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 494;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("730343215",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
 //BA.debugLineNum = 498;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("730343219",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;
;
 //BA.debugLineNum = 501;BA.debugLine="If sCatCode = \"SAS\" Then";

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
 //BA.debugLineNum = 502;BA.debugLine="sJOCatCode = \"Application No. :\"";
_sjocatcode = "Application No. :";
 if (true) break;

case 16:
//C
this.state = 17;
 //BA.debugLineNum = 504;BA.debugLine="sJOCatCode = \"Account No. :\"";
_sjocatcode = "Account No. :";
 if (true) break;

case 17:
//C
this.state = 18;
;
 //BA.debugLineNum = 507;BA.debugLine="csTitle.Initialize.Size(18).Bold.Color(GlobalVar.";
_cstitle.Initialize().Size((int) (18)).Bold().Color((int) (parent.mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(_scatcode+(" ")+("JO DETAILS"))).PopAll();
 //BA.debugLineNum = 509;BA.debugLine="If sCatCode = \"SAS\" Then";
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
 //BA.debugLineNum = 510;BA.debugLine="MatDialogBuilder.Initialize(\"JODetails\")";
parent.mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"JODetails");
 //BA.debugLineNum = 511;BA.debugLine="MatDialogBuilder.PositiveText(\"OK\").PositiveColo";
parent.mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence("OK")).PositiveColor((int) (parent.mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 512;BA.debugLine="MatDialogBuilder.NeutralText(\"EDIT\").NeutralColo";
parent.mostCurrent._matdialogbuilder.NeutralText(BA.ObjectToCharSequence("EDIT")).NeutralColor((int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 513;BA.debugLine="MatDialogBuilder.Title(csTitle)";
parent.mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 514;BA.debugLine="MatDialogBuilder.Content($\"  JO No. : ${sJONo}";
parent.mostCurrent._matdialogbuilder.Content(BA.ObjectToCharSequence(("  JO No. : "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sjono))+"\n"+"		"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sjodesc))+"\n"+"		Application No. : "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_srefno))+"\n"+"		Customer Name : "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_scustname))+"\n"+"		Address : "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_scustadd))+"\n"+"		F I N D I N G S\n"+"		\n"+"		Account Class: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_sacctclass))+"\n"+"		Connection Type: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_scontype))+"")));
 //BA.debugLineNum = 523;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LI";
parent.mostCurrent._matdialogbuilder.Theme(parent.mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 524;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
parent.mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 525;BA.debugLine="MatDialogBuilder.Cancelable(True)";
parent.mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 526;BA.debugLine="MatDialogBuilder.Show";
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
 //BA.debugLineNum = 529;BA.debugLine="End Sub";
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
public static void  _showsasjodetails(int _iid) throws Exception{
ResumableSub_ShowSASJODetails rsub = new ResumableSub_ShowSASJODetails(null,_iid);
rsub.resume(processBA, null);
}
public static class ResumableSub_ShowSASJODetails extends BA.ResumableSub {
public ResumableSub_ShowSASJODetails(bwsi.PumpOperations.actjo parent,int _iid) {
this.parent = parent;
this._iid = _iid;
}
bwsi.PumpOperations.actjo parent;
int _iid;
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
 //BA.debugLineNum = 533;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 534;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 535;BA.debugLine="Dim sJONo, sJOCatCode, sJODesc, sRefNo, sCustName";
_sjono = "";
_sjocatcode = "";
_sjodesc = "";
_srefno = "";
_scustname = "";
_scustadd = "";
_sacctclass = "";
_scontype = "";
 //BA.debugLineNum = 536;BA.debugLine="Dim sDateCreated, sDateStart, sDateFinished As St";
_sdatecreated = "";
_sdatestart = "";
_sdatefinished = "";
 //BA.debugLineNum = 539;BA.debugLine="sJONo = \"\"";
_sjono = "";
 //BA.debugLineNum = 540;BA.debugLine="sJODesc = \"\"";
_sjodesc = "";
 //BA.debugLineNum = 541;BA.debugLine="sRefNo = \"\"";
_srefno = "";
 //BA.debugLineNum = 542;BA.debugLine="sCustName = \"\"";
_scustname = "";
 //BA.debugLineNum = 543;BA.debugLine="sCustAdd = \"\"";
_scustadd = "";
 //BA.debugLineNum = 544;BA.debugLine="sAcctClass = \"\"";
_sacctclass = "";
 //BA.debugLineNum = 545;BA.debugLine="sDateCreated = \"\"";
_sdatecreated = "";
 //BA.debugLineNum = 546;BA.debugLine="sDateStart = \"\"";
_sdatestart = "";
 //BA.debugLineNum = 547;BA.debugLine="sDateFinished = \"\"";
_sdatefinished = "";
 //BA.debugLineNum = 548;BA.debugLine="sConType = \"\"";
_scontype = "";
 //BA.debugLineNum = 550;BA.debugLine="Try";
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
 //BA.debugLineNum = 551;BA.debugLine="Starter.strCriteria = \"SELECT JO.JONo, JO.JoDesc";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT JO.JONo, JO.JoDesc, "+"JO.RefNo AS App_AcctNo, JO.CustName, JO.CustAddress, "+"JO.AcctClass, JO.AcctSubClass, JO.ConType, "+"JO.JOCreatedAt, JO.DateStarted, JO.DateFinished "+"FROM tblJOs AS JO "+"WHERE JO.JOID = "+BA.NumberToString(_iid);
 //BA.debugLineNum = 558;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 559;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 13;
return;
case 13:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 561;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 562;BA.debugLine="RS.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 563;BA.debugLine="sJONo = RS.GetString(\"JONo\")";
_sjono = _rs.GetString("JONo");
 //BA.debugLineNum = 564;BA.debugLine="sJODesc = RS.GetString(\"JoDesc\")";
_sjodesc = _rs.GetString("JoDesc");
 //BA.debugLineNum = 565;BA.debugLine="sRefNo = RS.GetString(\"App_AcctNo\")";
_srefno = _rs.GetString("App_AcctNo");
 //BA.debugLineNum = 566;BA.debugLine="sCustName = RS.GetString(\"CustName\")";
_scustname = _rs.GetString("CustName");
 //BA.debugLineNum = 567;BA.debugLine="sCustAdd = RS.GetString(\"CustAddress\")";
_scustadd = _rs.GetString("CustAddress");
 //BA.debugLineNum = 568;BA.debugLine="sAcctClass = RS.GetString(\"AcctClass\") & \"-\" &";
_sacctclass = _rs.GetString("AcctClass")+"-"+_rs.GetString("AcctSubClass");
 //BA.debugLineNum = 569;BA.debugLine="sConType = RS.GetString(\"ConType\")";
_scontype = _rs.GetString("ConType");
 //BA.debugLineNum = 570;BA.debugLine="sDateCreated = RS.GetString(\"JOCreatedAt\")";
_sdatecreated = _rs.GetString("JOCreatedAt");
 //BA.debugLineNum = 571;BA.debugLine="sDateStart = RS.GetString(\"DateStarted\")";
_sdatestart = _rs.GetString("DateStarted");
 //BA.debugLineNum = 572;BA.debugLine="sDateFinished = RS.GetString(\"DateFinished\")";
_sdatefinished = _rs.GetString("DateFinished");
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 574;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 575;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 576;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 577;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 578;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("730408751",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 9:
//C
this.state = 12;
;
 //BA.debugLineNum = 580;BA.debugLine="pnlSASDetails.Visible = True";
parent.mostCurrent._pnlsasdetails.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 581;BA.debugLine="lblSASJONo.Text = sJONo";
parent.mostCurrent._lblsasjono.setText(BA.ObjectToCharSequence(_sjono));
 //BA.debugLineNum = 582;BA.debugLine="lbllSASAppNo.Text = sRefNo";
parent.mostCurrent._lbllsasappno.setText(BA.ObjectToCharSequence(_srefno));
 //BA.debugLineNum = 583;BA.debugLine="lblSASCustName.Text = sCustName";
parent.mostCurrent._lblsascustname.setText(BA.ObjectToCharSequence(_scustname));
 //BA.debugLineNum = 584;BA.debugLine="lblSASCustAddress.Text = sCustAdd";
parent.mostCurrent._lblsascustaddress.setText(BA.ObjectToCharSequence(_scustadd));
 //BA.debugLineNum = 586;BA.debugLine="lblSASAcctClass.Text = sAcctClass";
parent.mostCurrent._lblsasacctclass.setText(BA.ObjectToCharSequence(_sacctclass));
 //BA.debugLineNum = 587;BA.debugLine="lblSASConType.Text = sConType";
parent.mostCurrent._lblsascontype.setText(BA.ObjectToCharSequence(_scontype));
 //BA.debugLineNum = 588;BA.debugLine="lblSASDatesStart.Text = sDateStart";
parent.mostCurrent._lblsasdatesstart.setText(BA.ObjectToCharSequence(_sdatestart));
 //BA.debugLineNum = 589;BA.debugLine="lblSASDateAccomplished.Text = sDateFinished";
parent.mostCurrent._lblsasdateaccomplished.setText(BA.ObjectToCharSequence(_sdatefinished));
 //BA.debugLineNum = 591;BA.debugLine="cdDialogEdit.Initialize2(GlobalVar.YellowColor, 1";
parent.mostCurrent._cddialogedit.Initialize2((int) (parent.mostCurrent._globalvar._yellowcolor /*double*/ ),(int) (10),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 592;BA.debugLine="btnSASEdit.Background = cdDialogEdit";
parent.mostCurrent._btnsasedit.setBackground((android.graphics.drawable.Drawable)(parent.mostCurrent._cddialogedit.getObject()));
 //BA.debugLineNum = 594;BA.debugLine="btnSASEdit.Text = Chr(0xE8AD) & $\" Print JO\"$";
parent.mostCurrent._btnsasedit.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe8ad)))+(" Print JO")));
 //BA.debugLineNum = 596;BA.debugLine="cdDialogOk.Initialize2(GlobalVar.GreenColor, 10,";
parent.mostCurrent._cddialogok.Initialize2((int) (parent.mostCurrent._globalvar._greencolor /*double*/ ),(int) (10),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 597;BA.debugLine="btnSASOK.Background = cdDialogOk";
parent.mostCurrent._btnsasok.setBackground((android.graphics.drawable.Drawable)(parent.mostCurrent._cddialogok.getObject()));
 //BA.debugLineNum = 598;BA.debugLine="btnSASOK.Text = Chr(0xE5CA) & $\" Ok\"$";
parent.mostCurrent._btnsasok.setText(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe5ca)))+(" Ok")));
 if (true) break;

case 11:
//C
this.state = 12;
this.catchState = 0;
 //BA.debugLineNum = 600;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("730408773",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 12:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 602;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 727;BA.debugLine="Private Sub StartJO (iJOID As Int) As Boolean";
 //BA.debugLineNum = 728;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 729;BA.debugLine="Dim lngDateTime As Long";
_lngdatetime = 0L;
 //BA.debugLineNum = 730;BA.debugLine="Dim sDateStart As String";
_sdatestart = "";
 //BA.debugLineNum = 732;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 733;BA.debugLine="lngDateTime = DateTime.Now";
_lngdatetime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 734;BA.debugLine="DateTime.TimeFormat = \"hh:mm:ss a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm:ss a");
 //BA.debugLineNum = 735;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 736;BA.debugLine="sDateStart = DateTime.Date(lngDateTime) & $\" \"$ &";
_sdatestart = anywheresoftware.b4a.keywords.Common.DateTime.Date(_lngdatetime)+(" ")+anywheresoftware.b4a.keywords.Common.DateTime.Time(_lngdatetime);
 //BA.debugLineNum = 738;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 739;BA.debugLine="Try";
try { //BA.debugLineNum = 740;BA.debugLine="Starter.strCriteria = \"UPDATE tblJOs \" & _";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE tblJOs "+"SET JOStatus = ?, DateStarted = ? "+"WHERE JOID = "+BA.NumberToString(_ijoid);
 //BA.debugLineNum = 744;BA.debugLine="LogColor(Starter.strCriteria,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("731129617",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 745;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"2",_sdatestart}));
 //BA.debugLineNum = 746;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 //BA.debugLineNum = 748;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e17) {
			processBA.setLastException(e17); //BA.debugLineNum = 750;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 751;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("731129624",anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),0);
 };
 //BA.debugLineNum = 753;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 754;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 755;BA.debugLine="End Sub";
return false;
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
 //BA.debugLineNum = 170;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)'Ico";
 //BA.debugLineNum = 171;BA.debugLine="Select Item.Id";
switch (BA.switchObjectToInt(_item.getId(),(int) (1))) {
case 0: {
 //BA.debugLineNum = 173;BA.debugLine="PopSubMenu.Show";
mostCurrent._popsubmenu.Show();
 break; }
}
;
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 165;BA.debugLine="Sub ToolBar_NavigationItemClick 'Toolbar Arrow";
 //BA.debugLineNum = 166;BA.debugLine="IMEKeyboard.HideKeyboard";
mostCurrent._imekeyboard.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 167;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return "";
}
public static String  _txtsearch_enterpressed() throws Exception{
 //BA.debugLineNum = 443;BA.debugLine="Sub txtSearch_EnterPressed";
 //BA.debugLineNum = 445;BA.debugLine="End Sub";
return "";
}
public static void  _txtsearch_textchanged(String _old,String _new) throws Exception{
ResumableSub_txtSearch_TextChanged rsub = new ResumableSub_txtSearch_TextChanged(null,_old,_new);
rsub.resume(processBA, null);
}
public static class ResumableSub_txtSearch_TextChanged extends BA.ResumableSub {
public ResumableSub_txtSearch_TextChanged(bwsi.PumpOperations.actjo parent,String _old,String _new) {
this.parent = parent;
this._old = _old;
this._new = _new;
}
bwsi.PumpOperations.actjo parent;
String _old;
String _new;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.actjo._jorecords _jorec = null;
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
 //BA.debugLineNum = 393;BA.debugLine="If New.Length = 1 Or txtSearch.Text.Length = 2 Th";
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
 //BA.debugLineNum = 394;BA.debugLine="clvJOs.Clear";
parent.mostCurrent._clvjos._clear();
 //BA.debugLineNum = 395;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (0));
this.state = 23;
return;
case 23:
//C
this.state = 7;
;
 //BA.debugLineNum = 397;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 398;BA.debugLine="If New.Length = 0  Then";
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
 //BA.debugLineNum = 399;BA.debugLine="JOCounts = Starter.DBCon.ExecQuerySingleResult(\"";
parent._jocounts = (int)(Double.parseDouble(parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(*) FROM `tblJOs`;")));
 //BA.debugLineNum = 400;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblJOs \" &";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblJOs "+"WHERE JOCatCode = '"+parent.mostCurrent._globalvar._selectedjocatcode /*String*/ +"' "+"ORDER BY JOCreatedAt ASC";
 //BA.debugLineNum = 403;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("730212107",parent.mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 405;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 408;BA.debugLine="JOCounts = 0";
parent._jocounts = (int) (0);
 //BA.debugLineNum = 409;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",("SELECT * FROM `tblJOs` WHERE `CustName` Like '%"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_new))+"%' ORDER BY `JONo` ASC LIMIT 500;"),(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 if (true) break;

case 12:
//C
this.state = 13;
;
 //BA.debugLineNum = 412;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succes";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 24;
return;
case 24:
//C
this.state = 13;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 413;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 414;BA.debugLine="clvJOs.Clear";
parent.mostCurrent._clvjos._clear();
 //BA.debugLineNum = 415;BA.debugLine="clvJOs.Refresh";
parent.mostCurrent._clvjos._refresh();
 //BA.debugLineNum = 416;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 417;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 418;BA.debugLine="Dim JORec As JORecords";
_jorec = new bwsi.PumpOperations.actjo._jorecords();
 //BA.debugLineNum = 419;BA.debugLine="JORec.Initialize";
_jorec.Initialize();
 //BA.debugLineNum = 420;BA.debugLine="JORec.JOID = RS.GetInt(\"JOID\")";
_jorec.JOID /*int*/  = _rs.GetInt("JOID");
 //BA.debugLineNum = 421;BA.debugLine="JORec.JONum = GlobalVar.SF.Upper(RS.GetString(\"";
_jorec.JONum /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JONo"));
 //BA.debugLineNum = 422;BA.debugLine="JORec.JOCatCode = GlobalVar.SF.Upper(RS.GetStri";
_jorec.JOCatCode /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("JOCatCode"));
 //BA.debugLineNum = 423;BA.debugLine="JORec.RefID= RS.GetInt(\"RefID\")";
_jorec.RefID /*int*/  = _rs.GetInt("RefID");
 //BA.debugLineNum = 424;BA.debugLine="JORec.RefNo= GlobalVar.SF.Upper(RS.GetString(\"R";
_jorec.RefNo /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("RefNo"));
 //BA.debugLineNum = 425;BA.debugLine="JORec.CustName = GlobalVar.SF.Upper(RS.GetStrin";
_jorec.CustName /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustName"));
 //BA.debugLineNum = 426;BA.debugLine="JORec.CustAdd = GlobalVar.SF.Upper(RS.GetString";
_jorec.CustAdd /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("CustAddress"));
 //BA.debugLineNum = 427;BA.debugLine="JORec.AcctClass = GlobalVar.SF.Upper(RS.GetStri";
_jorec.AcctClass /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctClass"));
 //BA.debugLineNum = 428;BA.debugLine="JORec.AcctSubClass = GlobalVar.SF.Upper(RS.GetS";
_jorec.AcctSubClass /*String*/  = parent.mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(_rs.GetString("AcctSubClass"));
 //BA.debugLineNum = 429;BA.debugLine="JORec.JOStatus = RS.GetInt(\"JOStatus\")";
_jorec.JOStatus /*int*/  = _rs.GetInt("JOStatus");
 //BA.debugLineNum = 430;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 431;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0, clvJOs.AsVie";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),parent.mostCurrent._clvjos._asview().getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)));
 //BA.debugLineNum = 432;BA.debugLine="clvJOs.Add(Pnl, JORec)";
parent.mostCurrent._clvjos._add(_pnl,(Object)(_jorec));
 if (true) break;

case 19:
//C
this.state = 22;
;
 //BA.debugLineNum = 434;BA.debugLine="RS.Close";
_rs.Close();
 //BA.debugLineNum = 436;BA.debugLine="Log($\"List population time = ${NumberFormat2((Da";
anywheresoftware.b4a.keywords.Common.LogImpl("730212140",("List population time = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (1),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvjos._getsize()))+" airport names"),0);
 if (true) break;

case 21:
//C
this.state = 22;
 //BA.debugLineNum = 438;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("730212142",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 22:
//C
this.state = -1;
;
 //BA.debugLineNum = 441;BA.debugLine="End Sub";
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
