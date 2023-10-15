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

public class mainscreen extends androidx.appcompat.app.AppCompatActivity implements B4AActivity{
	public static mainscreen mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "bwsi.PumpOperations", "bwsi.PumpOperations.mainscreen");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (mainscreen).");
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
		activityBA = new BA(this, layout, processBA, "bwsi.PumpOperations", "bwsi.PumpOperations.mainscreen");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "bwsi.PumpOperations.mainscreen", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (mainscreen) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (mainscreen) Resume **");
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
		return mainscreen.class;
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
            BA.LogInfo("** Activity (mainscreen) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (mainscreen) Pause event (activity is not paused). **");
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
            mainscreen mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (mainscreen) Resume **");
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
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _notifbmp = null;
public de.amberhome.objects.appcompat.ACActionBar _actionbarbutton = null;
public de.amberhome.objects.appcompat.ACToolbarDarkWrapper _toolbar = null;
public anywheresoftware.b4a.object.XmlLayoutBuilder _xmlicon = null;
public de.amberhome.materialdialogs.MaterialDialogBuilderWrapper _matdialogbuilder = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
public com.johan.Vibrate.Vibrate _vibration = null;
public static long[] _vibratepattern = null;
public de.amberhome.objects.SnackbarWrapper _snack = null;
public anywheresoftware.b4a.objects.CSBuilder _csans = null;
public bwsi.PumpOperations.b4xdrawer _drawer = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lvmenus = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmenuheader = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbluserbranch = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbluserfullname = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img5 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img6 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnljo = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlnonop = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlprod = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlrepair = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlstatus = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlcpm = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlgpm = null;
public static int _selectedwbentry = 0;
public anywheresoftware.b4a.objects.LabelWrapper _lbltrandate = null;
public static long _thedate = 0L;
public static double _dblinitrdg = 0;
public static double _dbllastrdg = 0;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblempname = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblbranchname = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblreadingperiod = null;
public bwsi.PumpOperations.datedialogs _dtdialog = null;
public bwsi.PumpOperations.bctoast _mytoast = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlpumpselection = null;
public b4a.example3.customlistview _clvpumplist = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblpumploc = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lblpumpcode = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btnselect = null;
public de.amberhome.objects.appcompat.ACButtonWrapper _btncancel = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblavatar = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnluserstyle = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblmenuavatar = null;
public static int _jowithtype = 0;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fontbold = null;
public static int _ijounreadcount = 0;
public bwsi.PumpOperations.badger _badger1 = null;
public anywheresoftware.b4a.objects.Timer _notiftimer = null;
public bwsi.PumpOperations.keyvaluestore _kvs = null;
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
public bwsi.PumpOperations.addeditfmrdg _addeditfmrdg = null;
public bwsi.PumpOperations.addeditnonoperational _addeditnonoperational = null;
public bwsi.PumpOperations.addeditproblem _addeditproblem = null;
public bwsi.PumpOperations.addeditpsidistrecord _addeditpsidistrecord = null;
public bwsi.PumpOperations.addeditpsirdg _addeditpsirdg = null;
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
public static class _pumpassigned{
public boolean IsInitialized;
public int PumpID;
public String PumpCode;
public String PumpLoc;
public void Initialize() {
IsInitialized = true;
PumpID = 0;
PumpCode = "";
PumpLoc = "";
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
 //BA.debugLineNum = 96;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 97;BA.debugLine="MyScale.SetRate(0.5)";
mostCurrent._myscale._setrate /*String*/ (mostCurrent.activityBA,0.5);
 //BA.debugLineNum = 98;BA.debugLine="Drawer.Initialize(Me,\"MainMenu\",Activity, 82%x)";
mostCurrent._drawer._initialize /*String*/ (mostCurrent.activityBA,mainscreen.getObject(),"MainMenu",(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (82),mostCurrent.activityBA));
 //BA.debugLineNum = 99;BA.debugLine="Drawer.CenterPanel.LoadLayout(\"mainscreen\")";
mostCurrent._drawer._getcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().LoadLayout("mainscreen",mostCurrent.activityBA);
 //BA.debugLineNum = 101;BA.debugLine="GlobalVar.CSTitle.Initialize.Size(15).Bold.Append";
mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (15)).Bold().Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Application.getLabelName())).PopAll();
 //BA.debugLineNum = 102;BA.debugLine="GlobalVar.CSSubTitle.Initialize.Size(12).Append(A";
mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .Initialize().Size((int) (12)).Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Application.getVersionName())).PopAll();
 //BA.debugLineNum = 104;BA.debugLine="ToolBar.InitMenuListener";
mostCurrent._toolbar.InitMenuListener();
 //BA.debugLineNum = 105;BA.debugLine="ToolBar.Title = GlobalVar.CSTitle";
mostCurrent._toolbar.setTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cstitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 106;BA.debugLine="ToolBar.SubTitle = GlobalVar.CSSubTitle";
mostCurrent._toolbar.setSubTitle(BA.ObjectToCharSequence(mostCurrent._globalvar._cssubtitle /*anywheresoftware.b4a.objects.CSBuilder*/ .getObject()));
 //BA.debugLineNum = 108;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 109;BA.debugLine="Dim xl As XmlLayoutBuilder";
_xl = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 110;BA.debugLine="jo = ToolBar";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._toolbar.getObject()));
 //BA.debugLineNum = 111;BA.debugLine="jo.RunMethod(\"setPopupTheme\", Array(xl.GetResourc";
_jo.RunMethod("setPopupTheme",new Object[]{(Object)(_xl.GetResourceId("style","ToolbarMenu"))});
 //BA.debugLineNum = 112;BA.debugLine="jo.RunMethod(\"setContentInsetStartWithNavigation\"";
_jo.RunMethod("setContentInsetStartWithNavigation",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)))});
 //BA.debugLineNum = 113;BA.debugLine="jo.RunMethod(\"setTitleMarginStart\", Array(0dip))";
_jo.RunMethod("setTitleMarginStart",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)))});
 //BA.debugLineNum = 115;BA.debugLine="ActionBarButton.Initialize";
mostCurrent._actionbarbutton.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 117;BA.debugLine="ActionBarButton.ShowUpIndicator = True";
mostCurrent._actionbarbutton.setShowUpIndicator(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 118;BA.debugLine="ActionBarButton.UpIndicatorBitmap = LoadBitmapSam";
mostCurrent._actionbarbutton.setUpIndicatorBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmapSample(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"hamburger.png",anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80))).getObject()));
 //BA.debugLineNum = 120;BA.debugLine="NotifBMP = LoadBitmap(File.DirAssets,\"notifBMP.pn";
_notifbmp = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"notifBMP.png");
 //BA.debugLineNum = 122;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 123;BA.debugLine="If GlobalVar.UserPosID = 5 Then 'Pump Operator";
if (mostCurrent._globalvar._userposid /*int*/ ==5) { 
 //BA.debugLineNum = 124;BA.debugLine="CreateMainMenu(1, 1, 1, 0, 0, 0, 0, 1)";
_createmainmenu((int) (1),(int) (1),(int) (1),(int) (0),(int) (0),(int) (0),(int) (0),(int) (1));
 }else if(mostCurrent._globalvar._userposid /*int*/ ==6) { 
 //BA.debugLineNum = 126;BA.debugLine="CreateMainMenu(0, 0, 0, 1, 0, 0, 0, 1)";
_createmainmenu((int) (0),(int) (0),(int) (0),(int) (1),(int) (0),(int) (0),(int) (0),(int) (1));
 }else {
 //BA.debugLineNum = 128;BA.debugLine="CreateMainMenu(0, 0, 0, 0, 0, 0, 1, 1)";
_createmainmenu((int) (0),(int) (0),(int) (0),(int) (0),(int) (0),(int) (0),(int) (1),(int) (1));
 };
 //BA.debugLineNum = 131;BA.debugLine="GlobalVar.TranDate = DateTime.Date(DateTime.Now)";
mostCurrent._globalvar._trandate /*String*/  = anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow());
 //BA.debugLineNum = 132;BA.debugLine="ShowWelcomeDialog";
_showwelcomedialog();
 //BA.debugLineNum = 133;BA.debugLine="GlobalVar.RepMainID = 0";
mostCurrent._globalvar._repmainid /*int*/  = (int) (0);
 //BA.debugLineNum = 134;BA.debugLine="GlobalVar.RepMainDesc = \"\"";
mostCurrent._globalvar._repmaindesc /*String*/  = "";
 //BA.debugLineNum = 135;BA.debugLine="lblAvatar.Text = GlobalVar.UserAvatar";
mostCurrent._lblavatar.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._useravatar /*String*/ ));
 //BA.debugLineNum = 137;BA.debugLine="lblEmpName.Text = GlobalVar.EmpName";
mostCurrent._lblempname.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._empname /*String*/ ));
 //BA.debugLineNum = 138;BA.debugLine="lblBranchName.Text = GlobalVar.UserPos & \" | \" &";
mostCurrent._lblbranchname.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._userpos /*String*/ +" | "+mostCurrent._globalvar._branchname /*String*/ ));
 //BA.debugLineNum = 139;BA.debugLine="lblReadingPeriod.Text = $\"PERIOD COVERED: \"$ & G";
mostCurrent._lblreadingperiod.setText(BA.ObjectToCharSequence(("PERIOD COVERED: ")+mostCurrent._globalvar._rdgfrom /*String*/ +" - "+mostCurrent._globalvar._rdgto /*String*/ ));
 //BA.debugLineNum = 140;BA.debugLine="GlobalVar.PumpDrainPipeType = \"\"";
mostCurrent._globalvar._pumpdrainpipetype /*String*/  = "";
 //BA.debugLineNum = 141;BA.debugLine="GlobalVar.PumpDrainPipeSize = \"\"";
mostCurrent._globalvar._pumpdrainpipesize /*String*/  = "";
 //BA.debugLineNum = 142;BA.debugLine="JOWithType = 0";
_jowithtype = (int) (0);
 //BA.debugLineNum = 143;BA.debugLine="KVS.Initialize(File.DirInternal, \"operations.dat";
mostCurrent._kvs._initialize /*String*/ (processBA,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"operations.dat");
 };
 //BA.debugLineNum = 145;BA.debugLine="badger1.Initialize";
mostCurrent._badger1._initialize /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 146;BA.debugLine="csAns.Initialize.Color(Colors.White).Bold.Append(";
mostCurrent._csans.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Bold().Append(BA.ObjectToCharSequence(("YES"))).PopAll();
 //BA.debugLineNum = 147;BA.debugLine="Drawer.LeftPanel.LoadLayout(\"MainMenu\")";
mostCurrent._drawer._getleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().LoadLayout("MainMenu",mostCurrent.activityBA);
 //BA.debugLineNum = 149;BA.debugLine="MyToast.Initialize(Activity)";
mostCurrent._mytoast._initialize /*String*/ (mostCurrent.activityBA,(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())));
 //BA.debugLineNum = 151;BA.debugLine="CD.Initialize2(GlobalVar.RedColor, 20, 0, Colors.";
mostCurrent._cd.Initialize2((int) (mostCurrent._globalvar._redcolor /*double*/ ),(int) (20),(int) (0),anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 152;BA.debugLine="btnCancel.Background = CD";
mostCurrent._btncancel.setBackground((android.graphics.drawable.Drawable)(mostCurrent._cd.getObject()));
 //BA.debugLineNum = 153;BA.debugLine="btnCancel.Text = $\"CANCEL\"$";
mostCurrent._btncancel.setText(BA.ObjectToCharSequence(("CANCEL")));
 //BA.debugLineNum = 154;BA.debugLine="NotifTimer.Initialize(\"Timer1\", 2000)";
mostCurrent._notiftimer.Initialize(processBA,"Timer1",(long) (2000));
 //BA.debugLineNum = 156;BA.debugLine="If GlobalVar.UserPosID = 6 Then";
if (mostCurrent._globalvar._userposid /*int*/ ==6) { 
 //BA.debugLineNum = 157;BA.debugLine="NotifTimer.Enabled = True";
mostCurrent._notiftimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 159;BA.debugLine="NotifTimer.Enabled = False";
mostCurrent._notiftimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 161;BA.debugLine="End Sub";
return "";
}
public static String  _activity_createmenu(de.amberhome.objects.appcompat.ACMenuWrapper _menu) throws Exception{
de.amberhome.objects.appcompat.ACMenuItemWrapper _iitem = null;
 //BA.debugLineNum = 224;BA.debugLine="Sub Activity_CreateMenu(Menu As ACMenu)";
 //BA.debugLineNum = 225;BA.debugLine="Dim iItem As ACMenuItem";
_iitem = new de.amberhome.objects.appcompat.ACMenuItemWrapper();
 //BA.debugLineNum = 226;BA.debugLine="Menu.Clear";
_menu.Clear();
 //BA.debugLineNum = 227;BA.debugLine="If GlobalVar.UserPosID = 5 Then 'Pump Operator";
if (mostCurrent._globalvar._userposid /*int*/ ==5) { 
 //BA.debugLineNum = 228;BA.debugLine="Menu.Add2(1, 1, \"Transaction Date\",xmlIcon.GetDr";
_menu.Add2((int) (1),(int) (1),BA.ObjectToCharSequence("Transaction Date"),mostCurrent._xmlicon.GetDrawable("ic_date_range_white_24dp")).setShowAsAction(_iitem.SHOW_AS_ACTION_IF_ROOM);
 //BA.debugLineNum = 229;BA.debugLine="Menu.Add2(2, 2, \"Pump Selection\",xmlIcon.GetDraw";
_menu.Add2((int) (2),(int) (2),BA.ObjectToCharSequence("Pump Selection"),mostCurrent._xmlicon.GetDrawable("sharp_house_white_24dp")).setShowAsAction(_iitem.SHOW_AS_ACTION_ALWAYS);
 }else if(mostCurrent._globalvar._userposid /*int*/ ==6) { 
 //BA.debugLineNum = 231;BA.debugLine="Menu.Add2(1, 1, \"Transaction Date\",xmlIcon.GetDr";
_menu.Add2((int) (1),(int) (1),BA.ObjectToCharSequence("Transaction Date"),mostCurrent._xmlicon.GetDrawable("ic_date_range_white_24dp")).setShowAsAction(_iitem.SHOW_AS_ACTION_IF_ROOM);
 //BA.debugLineNum = 232;BA.debugLine="Menu.Add2(3, 3, \"Notif\",xmlIcon.GetDrawable(\"bas";
_menu.Add2((int) (3),(int) (3),BA.ObjectToCharSequence("Notif"),mostCurrent._xmlicon.GetDrawable("baseline_notifications_white_24dp")).setShowAsAction(_iitem.SHOW_AS_ACTION_ALWAYS);
 //BA.debugLineNum = 233;BA.debugLine="Menu.Add2(4, 4, \"Logout\",xmlIcon.GetDrawable(\"ba";
_menu.Add2((int) (4),(int) (4),BA.ObjectToCharSequence("Logout"),mostCurrent._xmlicon.GetDrawable("baseline_logout_white_24")).setShowAsAction(_iitem.SHOW_AS_ACTION_ALWAYS);
 }else {
 };
 //BA.debugLineNum = 236;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 163;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 164;BA.debugLine="If KeyCode = 4 Then";
if (_keycode==4) { 
 //BA.debugLineNum = 165;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 166;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 167;BA.debugLine="snack.Initialize(\"LogOFF\", Activity, $\"Sure to L";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"LogOFF",(android.view.View)(mostCurrent._activity.getObject()),("Sure to Log Off now?"),mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 168;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 169;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 170;BA.debugLine="snack.SetAction(csAns)";
mostCurrent._snack.SetAction(BA.ObjectToString(mostCurrent._csans));
 //BA.debugLineNum = 171;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 172;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 174;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 176;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 219;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 220;BA.debugLine="NotifTimer.Enabled = False";
mostCurrent._notiftimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 221;BA.debugLine="If UserClosed Then ExitApplication";
if (_userclosed) { 
anywheresoftware.b4a.keywords.Common.ExitApplication();};
 //BA.debugLineNum = 222;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 238;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 239;BA.debugLine="Log (Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("080674817",_permission,0);
 //BA.debugLineNum = 240;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 178;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 179;BA.debugLine="If GlobalVar.UserPosID = 5 Then 'Pump Operator";
if (mostCurrent._globalvar._userposid /*int*/ ==5) { 
 //BA.debugLineNum = 180;BA.debugLine="CreateMainMenu(1, 1, 1, 0, 0, 0, 0, 1)";
_createmainmenu((int) (1),(int) (1),(int) (1),(int) (0),(int) (0),(int) (0),(int) (0),(int) (1));
 //BA.debugLineNum = 181;BA.debugLine="GlobalVar.PumpDrainPipeType = \"\"";
mostCurrent._globalvar._pumpdrainpipetype /*String*/  = "";
 //BA.debugLineNum = 182;BA.debugLine="GlobalVar.PumpDrainPipeSize = \"\"";
mostCurrent._globalvar._pumpdrainpipesize /*String*/  = "";
 }else if(mostCurrent._globalvar._userposid /*int*/ ==6) { 
 //BA.debugLineNum = 184;BA.debugLine="CreateMainMenu(0, 0, 0, 1, 0, 0, 0, 1)";
_createmainmenu((int) (0),(int) (0),(int) (0),(int) (1),(int) (0),(int) (0),(int) (0),(int) (1));
 //BA.debugLineNum = 187;BA.debugLine="GlobalVar.PumpDrainPipeType = \"\"";
mostCurrent._globalvar._pumpdrainpipetype /*String*/  = "";
 //BA.debugLineNum = 188;BA.debugLine="GlobalVar.PumpDrainPipeSize = \"\"";
mostCurrent._globalvar._pumpdrainpipesize /*String*/  = "";
 }else {
 //BA.debugLineNum = 190;BA.debugLine="CreateMainMenu(0, 0, 0, 0, 0, 0, 1, 1)";
_createmainmenu((int) (0),(int) (0),(int) (0),(int) (0),(int) (0),(int) (0),(int) (1),(int) (1));
 };
 //BA.debugLineNum = 193;BA.debugLine="lblAvatar.Text = GlobalVar.UserAvatar";
mostCurrent._lblavatar.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._useravatar /*String*/ ));
 //BA.debugLineNum = 194;BA.debugLine="lblEmpName.Text = GlobalVar.EmpName";
mostCurrent._lblempname.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._empname /*String*/ ));
 //BA.debugLineNum = 195;BA.debugLine="lblBranchName.Text = GlobalVar.UserPos & \" | \" &";
mostCurrent._lblbranchname.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._userpos /*String*/ +" | "+mostCurrent._globalvar._branchname /*String*/ ));
 //BA.debugLineNum = 196;BA.debugLine="lblReadingPeriod.Text = $\"PERIOD COVERED: \"$ & Gl";
mostCurrent._lblreadingperiod.setText(BA.ObjectToCharSequence(("PERIOD COVERED: ")+mostCurrent._globalvar._rdgfrom /*String*/ +" - "+mostCurrent._globalvar._rdgto /*String*/ ));
 //BA.debugLineNum = 198;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 201;BA.debugLine="lblTranDate.Text =$\"TRANSACTION DATE: \"$ & Global";
mostCurrent._lbltrandate.setText(BA.ObjectToCharSequence(("TRANSACTION DATE: ")+mostCurrent._globalvar._trandate /*String*/ ));
 //BA.debugLineNum = 203;BA.debugLine="GlobalVar.RepMainID = 0";
mostCurrent._globalvar._repmainid /*int*/  = (int) (0);
 //BA.debugLineNum = 204;BA.debugLine="GlobalVar.RepMainDesc = \"\"";
mostCurrent._globalvar._repmaindesc /*String*/  = "";
 //BA.debugLineNum = 205;BA.debugLine="JOWithType = 0";
_jowithtype = (int) (0);
 //BA.debugLineNum = 206;BA.debugLine="If GlobalVar.UserPosID = 6 Then";
if (mostCurrent._globalvar._userposid /*int*/ ==6) { 
 //BA.debugLineNum = 207;BA.debugLine="NotifTimer.Enabled = True";
mostCurrent._notiftimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 209;BA.debugLine="NotifTimer.Enabled = False";
mostCurrent._notiftimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 211;BA.debugLine="If KVS.IsInitialized = False Then";
if (mostCurrent._kvs.IsInitialized /*boolean*/ ()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 212;BA.debugLine="KVS.Initialize(File.DirInternal, \"operations.dat";
mostCurrent._kvs._initialize /*String*/ (processBA,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"operations.dat");
 };
 //BA.debugLineNum = 217;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _addbadgetoicon(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp,int _number) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvs = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _mbmp = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _target = null;
 //BA.debugLineNum = 537;BA.debugLine="Sub AddBadgeToIcon(bmp As Bitmap, Number As Int) A";
 //BA.debugLineNum = 538;BA.debugLine="Dim cvs As Canvas";
_cvs = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 539;BA.debugLine="Dim mbmp As Bitmap";
_mbmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 540;BA.debugLine="mbmp.InitializeMutable(32dip, 32dip)";
_mbmp.InitializeMutable(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (32)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (32)));
 //BA.debugLineNum = 541;BA.debugLine="cvs.Initialize2(mbmp)";
_cvs.Initialize2((android.graphics.Bitmap)(_mbmp.getObject()));
 //BA.debugLineNum = 542;BA.debugLine="Dim target As Rect";
_target = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 543;BA.debugLine="target.Initialize(0, 0, mbmp.Width, mbmp.Height)";
_target.Initialize((int) (0),(int) (0),_mbmp.getWidth(),_mbmp.getHeight());
 //BA.debugLineNum = 544;BA.debugLine="cvs.DrawBitmap(bmp, Null, target)";
_cvs.DrawBitmap((android.graphics.Bitmap)(_bmp.getObject()),(android.graphics.Rect)(anywheresoftware.b4a.keywords.Common.Null),(android.graphics.Rect)(_target.getObject()));
 //BA.debugLineNum = 546;BA.debugLine="If Number > 0 Then";
if (_number>0) { 
 //BA.debugLineNum = 547;BA.debugLine="cvs.DrawCircle(mbmp.Width - 8dip, 8dip, 8dip, Gl";
_cvs.DrawCircle((float) (_mbmp.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8))),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8))),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8))),(int) (mostCurrent._globalvar._redcolor /*double*/ ),anywheresoftware.b4a.keywords.Common.True,(float) (0));
 //BA.debugLineNum = 548;BA.debugLine="cvs.DrawText(Min(Number, 100), mbmp.Width - 8dip";
_cvs.DrawText(mostCurrent.activityBA,BA.NumberToString(anywheresoftware.b4a.keywords.Common.Min(_number,100)),(float) (_mbmp.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8))),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD,(float) (10),anywheresoftware.b4a.keywords.Common.Colors.White,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 };
 //BA.debugLineNum = 550;BA.debugLine="Return mbmp";
if (true) return _mbmp;
 //BA.debugLineNum = 551;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.BitmapDrawable  _bitmaptobitmapdrawable(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bitmap) throws Exception{
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
 //BA.debugLineNum = 560;BA.debugLine="Sub BitmapToBitmapDrawable (bitmap As Bitmap) As B";
 //BA.debugLineNum = 561;BA.debugLine="Dim bd As BitmapDrawable";
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 562;BA.debugLine="bd.Initialize(bitmap)";
_bd.Initialize((android.graphics.Bitmap)(_bitmap.getObject()));
 //BA.debugLineNum = 563;BA.debugLine="Return bd";
if (true) return _bd;
 //BA.debugLineNum = 564;BA.debugLine="End Sub";
return null;
}
public static String  _btncancel_click() throws Exception{
 //BA.debugLineNum = 1439;BA.debugLine="Sub btnCancel_Click";
 //BA.debugLineNum = 1440;BA.debugLine="pnlPumpSelection.Visible = False";
mostCurrent._pnlpumpselection.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1441;BA.debugLine="End Sub";
return "";
}
public static String  _clearuserdata() throws Exception{
 //BA.debugLineNum = 1519;BA.debugLine="Private Sub ClearUserData";
 //BA.debugLineNum = 1520;BA.debugLine="KVS.Remove(\"user_data\")";
mostCurrent._kvs._remove /*String*/ ("user_data");
 //BA.debugLineNum = 1521;BA.debugLine="End Sub";
return "";
}
public static String  _closebutton_click() throws Exception{
 //BA.debugLineNum = 529;BA.debugLine="Private Sub CloseButton_Click()";
 //BA.debugLineNum = 530;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 //BA.debugLineNum = 531;BA.debugLine="End Sub";
return "";
}
public static String  _clvpumplist_itemclick(int _index,Object _value) throws Exception{
bwsi.PumpOperations.mainscreen._pumpassigned _rec = null;
 //BA.debugLineNum = 1355;BA.debugLine="Sub clvPumpList_ItemClick (Index As Int, Value As";
 //BA.debugLineNum = 1356;BA.debugLine="Dim Rec As PumpAssigned = Value";
_rec = (bwsi.PumpOperations.mainscreen._pumpassigned)(_value);
 //BA.debugLineNum = 1357;BA.debugLine="Log(Rec.PumpID)";
anywheresoftware.b4a.keywords.Common.LogImpl("083230722",BA.NumberToString(_rec.PumpID /*int*/ ),0);
 //BA.debugLineNum = 1358;BA.debugLine="GlobalVar.PumpHouseID = Rec.PumpID";
mostCurrent._globalvar._pumphouseid /*int*/  = _rec.PumpID /*int*/ ;
 //BA.debugLineNum = 1359;BA.debugLine="GlobalVar.PumpHouseCode = Rec.PumpCode";
mostCurrent._globalvar._pumphousecode /*String*/  = _rec.PumpCode /*String*/ ;
 //BA.debugLineNum = 1361;BA.debugLine="GlobalVar.PumpDrainPipeType = DBaseFunctions.GetD";
mostCurrent._globalvar._pumpdrainpipetype /*String*/  = mostCurrent._dbasefunctions._getdrainpipetype /*String*/ (mostCurrent.activityBA,_rec.PumpID /*int*/ );
 //BA.debugLineNum = 1362;BA.debugLine="GlobalVar.PumpDrainPipeSize = DBaseFunctions.GetD";
mostCurrent._globalvar._pumpdrainpipesize /*String*/  = mostCurrent._dbasefunctions._getdrainpipesize /*String*/ (mostCurrent.activityBA,_rec.PumpID /*int*/ );
 //BA.debugLineNum = 1364;BA.debugLine="pnlPumpSelection.Visible = False";
mostCurrent._pnlpumpselection.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1365;BA.debugLine="MyToast.DefaultTextColor = Colors.White";
mostCurrent._mytoast._defaulttextcolor /*int*/  = anywheresoftware.b4a.keywords.Common.Colors.White;
 //BA.debugLineNum = 1366;BA.debugLine="MyToast.pnl.Color = GlobalVar.BlueColor";
mostCurrent._mytoast._pnl /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor((int) (mostCurrent._globalvar._bluecolor /*double*/ ));
 //BA.debugLineNum = 1367;BA.debugLine="MyFunctions.MyToastMsg(MyToast, $\"Selected Pump:";
mostCurrent._myfunctions._mytoastmsg /*String*/ (mostCurrent.activityBA,mostCurrent._mytoast,("Selected Pump: ")+mostCurrent._globalvar._pumphousecode /*String*/ );
 //BA.debugLineNum = 1368;BA.debugLine="End Sub";
return "";
}
public static String  _clvpumplist_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
int _extrasize = 0;
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
bwsi.PumpOperations.mainscreen._pumpassigned _pa = null;
 //BA.debugLineNum = 1370;BA.debugLine="Sub clvPumpList_VisibleRangeChanged (FirstIndex As";
 //BA.debugLineNum = 1371;BA.debugLine="Dim ExtraSize As Int = 15 'List size";
_extrasize = (int) (15);
 //BA.debugLineNum = 1372;BA.debugLine="For i = Max(0, FirstIndex - ExtraSize) To Min(Las";
{
final int step2 = 1;
final int limit2 = (int) (anywheresoftware.b4a.keywords.Common.Min(_lastindex+_extrasize,mostCurrent._clvpumplist._getsize()-1));
_i = (int) (anywheresoftware.b4a.keywords.Common.Max(0,_firstindex-_extrasize)) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 1373;BA.debugLine="Dim Pnl As B4XView = clvPumpList.GetPanel(i)";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = mostCurrent._clvpumplist._getpanel(_i);
 //BA.debugLineNum = 1374;BA.debugLine="If i > FirstIndex - ExtraSize And i < LastIndex";
if (_i>_firstindex-_extrasize && _i<_lastindex+_extrasize) { 
 //BA.debugLineNum = 1375;BA.debugLine="If Pnl.NumberOfViews = 0 Then 'Add each item/la";
if (_pnl.getNumberOfViews()==0) { 
 //BA.debugLineNum = 1376;BA.debugLine="Dim PA As PumpAssigned = clvPumpList.GetValue(";
_pa = (bwsi.PumpOperations.mainscreen._pumpassigned)(mostCurrent._clvpumplist._getvalue(_i));
 //BA.debugLineNum = 1377;BA.debugLine="Pnl.LoadLayout(\"PumpList\")";
_pnl.LoadLayout("PumpList",mostCurrent.activityBA);
 //BA.debugLineNum = 1378;BA.debugLine="lblPumpCode.TextColor = GlobalVar.BlueColor";
mostCurrent._lblpumpcode.setTextColor((int) (mostCurrent._globalvar._bluecolor /*double*/ ));
 //BA.debugLineNum = 1379;BA.debugLine="lblPumpLoc.TextColor = Colors.Gray";
mostCurrent._lblpumploc.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 1380;BA.debugLine="lblPumpCode.Text = $\"PUMP - \"$ & PA.PumpCode";
mostCurrent._lblpumpcode.setText(BA.ObjectToCharSequence(("PUMP - ")+_pa.PumpCode /*String*/ ));
 //BA.debugLineNum = 1381;BA.debugLine="lblPumpLoc.Text  = PA.PumpLoc";
mostCurrent._lblpumploc.setText(BA.ObjectToCharSequence(_pa.PumpLoc /*String*/ ));
 };
 }else {
 //BA.debugLineNum = 1384;BA.debugLine="If Pnl.NumberOfViews > 0 Then";
if (_pnl.getNumberOfViews()>0) { 
 //BA.debugLineNum = 1385;BA.debugLine="Pnl.RemoveAllViews 'Remove none visable item/l";
_pnl.RemoveAllViews();
 };
 };
 }
};
 //BA.debugLineNum = 1389;BA.debugLine="End Sub";
return "";
}
public static String  _createmainmenu(int _imod1,int _imod2,int _imod3,int _imod4,int _imod5,int _imod6,int _imod7,int _imod8) throws Exception{
com.maximussoft.circularimageview.CircularImageViewWrapper _civ = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _imgback = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cdpressed = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cdnormal = null;
anywheresoftware.b4a.objects.drawable.StateListDrawable _sld = null;
anywheresoftware.b4a.objects.CSBuilder _csmenu1 = null;
anywheresoftware.b4a.objects.CSBuilder _csmenu2 = null;
anywheresoftware.b4a.objects.CSBuilder _csmenu3 = null;
anywheresoftware.b4a.objects.CSBuilder _csmenu4 = null;
anywheresoftware.b4a.objects.CSBuilder _csmenu5 = null;
anywheresoftware.b4a.objects.CSBuilder _csmenu6 = null;
anywheresoftware.b4a.objects.CSBuilder _csmenu7 = null;
anywheresoftware.b4a.objects.CSBuilder _csmenu8 = null;
Object _sicon1 = null;
Object _sicon2 = null;
Object _sicon3 = null;
Object _sicon4 = null;
Object _sicon5 = null;
Object _sicon6 = null;
Object _sicon7 = null;
Object _sicon8 = null;
anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fmainmenu = null;
anywheresoftware.b4a.keywords.constants.TypefaceWrapper _fsecmenu = null;
anywheresoftware.b4j.object.JavaObject _lvo = null;
 //BA.debugLineNum = 295;BA.debugLine="Private Sub CreateMainMenu(iMod1 As Int, iMod2 As";
 //BA.debugLineNum = 296;BA.debugLine="Dim civ As CircularImageView";
_civ = new com.maximussoft.circularimageview.CircularImageViewWrapper();
 //BA.debugLineNum = 297;BA.debugLine="Dim imgBack As BitmapDrawable";
_imgback = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 298;BA.debugLine="Dim CDPressed,CDNormal As ColorDrawable";
_cdpressed = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
_cdnormal = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 299;BA.debugLine="Dim SLD As StateListDrawable";
_sld = new anywheresoftware.b4a.objects.drawable.StateListDrawable();
 //BA.debugLineNum = 300;BA.debugLine="Dim csMenu1, csMenu2, csMenu3, csMenu4, csMenu5,";
_csmenu1 = new anywheresoftware.b4a.objects.CSBuilder();
_csmenu2 = new anywheresoftware.b4a.objects.CSBuilder();
_csmenu3 = new anywheresoftware.b4a.objects.CSBuilder();
_csmenu4 = new anywheresoftware.b4a.objects.CSBuilder();
_csmenu5 = new anywheresoftware.b4a.objects.CSBuilder();
_csmenu6 = new anywheresoftware.b4a.objects.CSBuilder();
_csmenu7 = new anywheresoftware.b4a.objects.CSBuilder();
_csmenu8 = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 301;BA.debugLine="Dim sIcon1, sIcon2, sIcon3, sIcon4, sIcon5, sIcon";
_sicon1 = new Object();
_sicon2 = new Object();
_sicon3 = new Object();
_sicon4 = new Object();
_sicon5 = new Object();
_sicon6 = new Object();
_sicon7 = new Object();
_sicon8 = new Object();
 //BA.debugLineNum = 303;BA.debugLine="Dim fMainMenu, fSecMenu As Typeface";
_fmainmenu = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_fsecmenu = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
 //BA.debugLineNum = 308;BA.debugLine="If pnlMenuHeader.IsInitialized = False Then pnlMe";
if (mostCurrent._pnlmenuheader.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._pnlmenuheader.Initialize(mostCurrent.activityBA,"");};
 //BA.debugLineNum = 309;BA.debugLine="LogColor(GlobalVar.UserAvatar, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("080871438",mostCurrent._globalvar._useravatar /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 320;BA.debugLine="CDNormal.Initialize(Colors.White,0) 'Normal Color";
_cdnormal.Initialize(anywheresoftware.b4a.keywords.Common.Colors.White,(int) (0));
 //BA.debugLineNum = 321;BA.debugLine="CDPressed.Initialize(0xFFD3D3D3,0)  'Pressed Colo";
_cdpressed.Initialize((int) (0xffd3d3d3),(int) (0));
 //BA.debugLineNum = 323;BA.debugLine="SLD.Initialize";
_sld.Initialize();
 //BA.debugLineNum = 324;BA.debugLine="SLD.AddState(SLD.State_Pressed, CDNormal)";
_sld.AddState(_sld.State_Pressed,(android.graphics.drawable.Drawable)(_cdnormal.getObject()));
 //BA.debugLineNum = 325;BA.debugLine="SLD.AddState(-SLD.State_Pressed, CDPressed)";
_sld.AddState((int) (-_sld.State_Pressed),(android.graphics.drawable.Drawable)(_cdpressed.getObject()));
 //BA.debugLineNum = 327;BA.debugLine="If lblMenuAvatar.IsInitialized = False Then lblMe";
if (mostCurrent._lblmenuavatar.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._lblmenuavatar.Initialize(mostCurrent.activityBA,"");};
 //BA.debugLineNum = 328;BA.debugLine="If lblUserFullName.IsInitialized = False Then lbl";
if (mostCurrent._lbluserfullname.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._lbluserfullname.Initialize(mostCurrent.activityBA,"");};
 //BA.debugLineNum = 329;BA.debugLine="If lblUserBranch.IsInitialized = False Then lblUs";
if (mostCurrent._lbluserbranch.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._lbluserbranch.Initialize(mostCurrent.activityBA,"");};
 //BA.debugLineNum = 330;BA.debugLine="If lvMenus.IsInitialized = False Then lvMenus.Ini";
if (mostCurrent._lvmenus.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._lvmenus.Initialize(mostCurrent.activityBA,"lvMenus");};
 //BA.debugLineNum = 332;BA.debugLine="Dim LVO As JavaObject = lvMenus";
_lvo = new anywheresoftware.b4j.object.JavaObject();
_lvo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._lvmenus.getObject()));
 //BA.debugLineNum = 333;BA.debugLine="LVO.RunMethod(\"setSelector\",Array As Object(SLD))";
_lvo.RunMethod("setSelector",new Object[]{(Object)(_sld.getObject())});
 //BA.debugLineNum = 334;BA.debugLine="lvMenus.FastScrollEnabled=True";
mostCurrent._lvmenus.setFastScrollEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 337;BA.debugLine="lblMenuAvatar.Text = GlobalVar.UserAvatar";
mostCurrent._lblmenuavatar.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._useravatar /*String*/ ));
 //BA.debugLineNum = 338;BA.debugLine="lblUserFullName.Text = GlobalVar.SF.Upper(GlobalV";
mostCurrent._lbluserfullname.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(mostCurrent._globalvar._empname /*String*/ )));
 //BA.debugLineNum = 339;BA.debugLine="lblUserBranch.Text = GlobalVar.BranchName";
mostCurrent._lbluserbranch.setText(BA.ObjectToCharSequence(mostCurrent._globalvar._branchname /*String*/ ));
 //BA.debugLineNum = 341;BA.debugLine="fMainMenu = Typeface.LoadFromAssets(\"Roboto-Bold.";
_fmainmenu = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("Roboto-Bold.ttf")));
 //BA.debugLineNum = 342;BA.debugLine="fSecMenu = Typeface.LoadFromAssets(\"roboto-regula";
_fsecmenu = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("roboto-regular.ttf")));
 //BA.debugLineNum = 345;BA.debugLine="If iMod1 = 1 Then";
if (_imod1==1) { 
 //BA.debugLineNum = 346;BA.debugLine="csMenu1.Initialize.Color(GlobalVar.BlueColor).Ap";
_csmenu1.Initialize().Color((int) (mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("Pump Production"))).PopAll();
 //BA.debugLineNum = 347;BA.debugLine="sIcon1 = MyFunctions.FontBit(Chr(0xF043),17,Glob";
_sicon1 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf043))),(float) (17),(int) (mostCurrent._globalvar._bluecolor /*double*/ ),anywheresoftware.b4a.keywords.Common.True).getObject());
 }else {
 //BA.debugLineNum = 349;BA.debugLine="csMenu1.Initialize.Color(Colors.LightGray).Appen";
_csmenu1.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.LightGray).Append(BA.ObjectToCharSequence(("Pump Production"))).PopAll();
 //BA.debugLineNum = 350;BA.debugLine="sIcon1 = MyFunctions.FontBit(Chr(0xF043),17,Colo";
_sicon1 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf043))),(float) (17),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.True).getObject());
 };
 //BA.debugLineNum = 353;BA.debugLine="If iMod2 = 1 Then";
if (_imod2==1) { 
 //BA.debugLineNum = 354;BA.debugLine="csMenu2.Initialize.Color(GlobalVar.BlueColor).Ap";
_csmenu2.Initialize().Color((int) (mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("Repair and Maintenance"))).PopAll();
 //BA.debugLineNum = 355;BA.debugLine="sIcon2 = MyFunctions.FontBit(Chr(0xF0AD),17,Glob";
_sicon2 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf0ad))),(float) (17),(int) (mostCurrent._globalvar._bluecolor /*double*/ ),anywheresoftware.b4a.keywords.Common.True).getObject());
 }else {
 //BA.debugLineNum = 357;BA.debugLine="csMenu2.Initialize.Color(Colors.LightGray).Appen";
_csmenu2.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.LightGray).Append(BA.ObjectToCharSequence(("Repair and Maintenance"))).PopAll();
 //BA.debugLineNum = 358;BA.debugLine="sIcon2 = MyFunctions.FontBit(Chr(0xF0AD),17,Colo";
_sicon2 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf0ad))),(float) (17),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.True).getObject());
 };
 //BA.debugLineNum = 361;BA.debugLine="If iMod3 = 1 Then";
if (_imod3==1) { 
 //BA.debugLineNum = 362;BA.debugLine="csMenu3.Initialize.Color(GlobalVar.BlueColor).Ap";
_csmenu3.Initialize().Color((int) (mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("Pump Non-operational"))).PopAll();
 //BA.debugLineNum = 363;BA.debugLine="sIcon3 = MyFunctions.FontBit(Chr(0xE0C4),17,Glob";
_sicon3 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe0c4))),(float) (17),(int) (mostCurrent._globalvar._bluecolor /*double*/ ),anywheresoftware.b4a.keywords.Common.False).getObject());
 }else {
 //BA.debugLineNum = 365;BA.debugLine="csMenu3.Initialize.Color(Colors.LightGray).Appen";
_csmenu3.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.LightGray).Append(BA.ObjectToCharSequence(("Pump Non-operational"))).PopAll();
 //BA.debugLineNum = 366;BA.debugLine="sIcon3 = MyFunctions.FontBit(Chr(0xE0C4),17,Colo";
_sicon3 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe0c4))),(float) (17),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.False).getObject());
 };
 //BA.debugLineNum = 369;BA.debugLine="If iMod4 = 1 Then";
if (_imod4==1) { 
 //BA.debugLineNum = 370;BA.debugLine="csMenu4.Initialize.Color(GlobalVar.BlueColor).Ap";
_csmenu4.Initialize().Color((int) (mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("Job Orders"))).PopAll();
 //BA.debugLineNum = 371;BA.debugLine="sIcon4 = MyFunctions.FontBit(Chr(0xF022),17,Glob";
_sicon4 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf022))),(float) (17),(int) (mostCurrent._globalvar._bluecolor /*double*/ ),anywheresoftware.b4a.keywords.Common.True).getObject());
 }else {
 //BA.debugLineNum = 373;BA.debugLine="csMenu4.Initialize.Color(Colors.LightGray).Appen";
_csmenu4.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.LightGray).Append(BA.ObjectToCharSequence(("Job Orders"))).PopAll();
 //BA.debugLineNum = 374;BA.debugLine="sIcon4 = MyFunctions.FontBit(Chr(0xF022),17,Colo";
_sicon4 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf022))),(float) (17),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.True).getObject());
 };
 //BA.debugLineNum = 377;BA.debugLine="If iMod5 = 1 Then";
if (_imod5==1) { 
 //BA.debugLineNum = 378;BA.debugLine="csMenu5.Initialize.Color(GlobalVar.BlueColor).Ap";
_csmenu5.Initialize().Color((int) (mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("Water Balance Entry"))).PopAll();
 //BA.debugLineNum = 379;BA.debugLine="sIcon5 = MyFunctions.FontBit(Chr(0xF201),17,Glob";
_sicon5 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf201))),(float) (17),(int) (mostCurrent._globalvar._bluecolor /*double*/ ),anywheresoftware.b4a.keywords.Common.True).getObject());
 }else {
 //BA.debugLineNum = 381;BA.debugLine="csMenu5.Initialize.Color(Colors.LightGray).Appen";
_csmenu5.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.LightGray).Append(BA.ObjectToCharSequence(("Water Balance Entry"))).PopAll();
 //BA.debugLineNum = 382;BA.debugLine="sIcon5 = MyFunctions.FontBit(Chr(0xF201),17,Colo";
_sicon5 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf201))),(float) (17),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.True).getObject());
 };
 //BA.debugLineNum = 385;BA.debugLine="If iMod6 = 1 Then";
if (_imod6==1) { 
 //BA.debugLineNum = 386;BA.debugLine="csMenu6.Initialize.Color(GlobalVar.BlueColor).Ap";
_csmenu6.Initialize().Color((int) (mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("Data Syncing"))).PopAll();
 //BA.debugLineNum = 387;BA.debugLine="sIcon6 = MyFunctions.FontBit(Chr(0xE8D5),17,Glob";
_sicon6 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe8d5))),(float) (17),(int) (mostCurrent._globalvar._bluecolor /*double*/ ),anywheresoftware.b4a.keywords.Common.False).getObject());
 }else {
 //BA.debugLineNum = 389;BA.debugLine="csMenu6.Initialize.Color(Colors.LightGray).Appen";
_csmenu6.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.LightGray).Append(BA.ObjectToCharSequence(("Data Syncing"))).PopAll();
 //BA.debugLineNum = 390;BA.debugLine="sIcon6 = MyFunctions.FontBit(Chr(0xE8D5),17,Colo";
_sicon6 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe8d5))),(float) (17),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.False).getObject());
 };
 //BA.debugLineNum = 393;BA.debugLine="If iMod7 = 1 Then";
if (_imod7==1) { 
 //BA.debugLineNum = 394;BA.debugLine="csMenu7.Initialize.Color(GlobalVar.BlueColor).Ap";
_csmenu7.Initialize().Color((int) (mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("Reading Settings"))).PopAll();
 //BA.debugLineNum = 395;BA.debugLine="sIcon7 = MyFunctions.FontBit(Chr(0xF073),17,Glob";
_sicon7 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf073))),(float) (17),(int) (mostCurrent._globalvar._bluecolor /*double*/ ),anywheresoftware.b4a.keywords.Common.True).getObject());
 }else {
 //BA.debugLineNum = 397;BA.debugLine="csMenu7.Initialize.Color(Colors.LightGray).Appen";
_csmenu7.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.LightGray).Append(BA.ObjectToCharSequence(("Reading Settings"))).PopAll();
 //BA.debugLineNum = 398;BA.debugLine="sIcon7 = MyFunctions.FontBit(Chr(0xF073),17,Colo";
_sicon7 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf073))),(float) (17),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.True).getObject());
 };
 //BA.debugLineNum = 401;BA.debugLine="If iMod8 = 1 Then";
if (_imod8==1) { 
 //BA.debugLineNum = 402;BA.debugLine="csMenu8.Initialize.Color(GlobalVar.BlueColor).Ap";
_csmenu8.Initialize().Color((int) (mostCurrent._globalvar._bluecolor /*double*/ )).Append(BA.ObjectToCharSequence(("User Settings"))).PopAll();
 //BA.debugLineNum = 403;BA.debugLine="sIcon8 = MyFunctions.FontBit(Chr(0xE851),17,Glob";
_sicon8 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe851))),(float) (17),(int) (mostCurrent._globalvar._bluecolor /*double*/ ),anywheresoftware.b4a.keywords.Common.False).getObject());
 }else {
 //BA.debugLineNum = 405;BA.debugLine="csMenu8.Initialize.Color(Colors.LightGray).Appen";
_csmenu8.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.LightGray).Append(BA.ObjectToCharSequence(("User Settings"))).PopAll();
 //BA.debugLineNum = 406;BA.debugLine="sIcon8 = MyFunctions.FontBit(Chr(0xE851),17,Colo";
_sicon8 = (Object)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe851))),(float) (17),anywheresoftware.b4a.keywords.Common.Colors.LightGray,anywheresoftware.b4a.keywords.Common.False).getObject());
 };
 //BA.debugLineNum = 408;BA.debugLine="lvMenus.Clear";
mostCurrent._lvmenus.Clear();
 //BA.debugLineNum = 411;BA.debugLine="lvMenus.AddTwoLinesAndBitmap2(csMenu1, $\"Input Ti";
mostCurrent._lvmenus.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_csmenu1.getObject()),BA.ObjectToCharSequence(("Input Time On/Off, Flow Meter and PSI Reading")),(android.graphics.Bitmap)(_sicon1),(Object)(1));
 //BA.debugLineNum = 412;BA.debugLine="lvMenus.AddTwoLinesAndBitmap2(csMenu2, $\"Allow to";
mostCurrent._lvmenus.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_csmenu2.getObject()),BA.ObjectToCharSequence(("Allow to Add Repair & Maintenance Entry")),(android.graphics.Bitmap)(_sicon2),(Object)(2));
 //BA.debugLineNum = 413;BA.debugLine="lvMenus.AddTwoLinesAndBitmap2(csMenu3, $\"Specify";
mostCurrent._lvmenus.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_csmenu3.getObject()),BA.ObjectToCharSequence(("Specify Date, Time & Reason for Pump non operational")),(android.graphics.Bitmap)(_sicon3),(Object)(3));
 //BA.debugLineNum = 414;BA.debugLine="lvMenus.AddTwoLinesAndBitmap2(csMenu4, $\"Allow to";
mostCurrent._lvmenus.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_csmenu4.getObject()),BA.ObjectToCharSequence(("Allow to post JO findings")),(android.graphics.Bitmap)(_sicon4),(Object)(4));
 //BA.debugLineNum = 415;BA.debugLine="lvMenus.AddTwoLinesAndBitmap2(csMenu5, $\"Allow to";
mostCurrent._lvmenus.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_csmenu5.getObject()),BA.ObjectToCharSequence(("Allow to Input Water Balance Entries")),(android.graphics.Bitmap)(_sicon5),(Object)(5));
 //BA.debugLineNum = 416;BA.debugLine="lvMenus.AddTwoLinesAndBitmap2(csMenu6, $\"Download";
mostCurrent._lvmenus.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_csmenu6.getObject()),BA.ObjectToCharSequence(("Download/Upload Data from/to Database Server")),(android.graphics.Bitmap)(_sicon6),(Object)(6));
 //BA.debugLineNum = 417;BA.debugLine="lvMenus.AddTwoLinesAndBitmap2(csMenu7, $\"Allow to";
mostCurrent._lvmenus.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_csmenu7.getObject()),BA.ObjectToCharSequence(("Allow to Set Current Reading Date")),(android.graphics.Bitmap)(_sicon7),(Object)(7));
 //BA.debugLineNum = 418;BA.debugLine="lvMenus.AddTwoLinesAndBitmap2(csMenu8, $\"Change U";
mostCurrent._lvmenus.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_csmenu8.getObject()),BA.ObjectToCharSequence(("Change User Name and/or User Password")),(android.graphics.Bitmap)(_sicon8),(Object)(8));
 //BA.debugLineNum = 419;BA.debugLine="lvMenus.AddTwoLinesAndBitmap2($\"Log Out \"$ & Glob";
mostCurrent._lvmenus.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(("Log Out ")+mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv5(mostCurrent._globalvar._username /*String*/ )),BA.ObjectToCharSequence(("Log-out Session")),(android.graphics.Bitmap)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf08b))),(float) (17),(int) (mostCurrent._globalvar._bluecolor /*double*/ ),anywheresoftware.b4a.keywords.Common.True).getObject()),(Object)(9));
 //BA.debugLineNum = 420;BA.debugLine="lvMenus.AddTwoLinesAndBitmap2($\"Close App\"$,$\"Clo";
mostCurrent._lvmenus.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(("Close App")),BA.ObjectToCharSequence(("Close Pump Operation App")),(android.graphics.Bitmap)(mostCurrent._myfunctions._fontbit /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ (mostCurrent.activityBA,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xf2d4))),(float) (17),(int) (mostCurrent._globalvar._bluecolor /*double*/ ),anywheresoftware.b4a.keywords.Common.True).getObject()),(Object)(10));
 //BA.debugLineNum = 422;BA.debugLine="lvMenus.TwoLinesAndBitmap.Label.TextColor = Globa";
mostCurrent._lvmenus.getTwoLinesAndBitmap().Label.setTextColor((int) (mostCurrent._globalvar._bluecolor /*double*/ ));
 //BA.debugLineNum = 423;BA.debugLine="lvMenus.TwoLinesAndBitmap.Label.TextSize = 13";
mostCurrent._lvmenus.getTwoLinesAndBitmap().Label.setTextSize((float) (13));
 //BA.debugLineNum = 424;BA.debugLine="lvMenus.TwoLinesAndBitmap.SecondLabel.TextSize =";
mostCurrent._lvmenus.getTwoLinesAndBitmap().SecondLabel.setTextSize((float) (8));
 //BA.debugLineNum = 425;BA.debugLine="lvMenus.TwoLinesAndBitmap.SecondLabel.TextColor =";
mostCurrent._lvmenus.getTwoLinesAndBitmap().SecondLabel.setTextColor((int) (0xff808080));
 //BA.debugLineNum = 426;BA.debugLine="lvMenus.TwoLinesAndBitmap.SecondLabel.Typeface =f";
mostCurrent._lvmenus.getTwoLinesAndBitmap().SecondLabel.setTypeface((android.graphics.Typeface)(_fsecmenu.getObject()));
 //BA.debugLineNum = 427;BA.debugLine="lvMenus.TwoLinesAndBitmap.Label.Typeface = fMainM";
mostCurrent._lvmenus.getTwoLinesAndBitmap().Label.setTypeface((android.graphics.Typeface)(_fmainmenu.getObject()));
 //BA.debugLineNum = 428;BA.debugLine="lvMenus.TwoLinesAndBitmap.ItemHeight = 50dip";
mostCurrent._lvmenus.getTwoLinesAndBitmap().setItemHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 429;BA.debugLine="End Sub";
return "";
}
public static String  _fontsizebinder_onbindview(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,int _viewtype) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 663;BA.debugLine="Private Sub FontSizeBinder_OnBindView (View As Vie";
 //BA.debugLineNum = 664;BA.debugLine="Dim alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 665;BA.debugLine="alert.Initialize";
_alert.Initialize();
 //BA.debugLineNum = 666;BA.debugLine="If ViewType = alert.VIEW_TITLE Then ' Title";
if (_viewtype==_alert.VIEW_TITLE) { 
 //BA.debugLineNum = 667;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 672;BA.debugLine="Dim CS As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 676;BA.debugLine="CS.Initialize.Typeface(Typeface.DEFAULT_BOLD).Ty";
_cs.Initialize().Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()).Size((int) (26)).Color((int) (mostCurrent._globalvar._pricolor /*double*/ )).Append(BA.ObjectToCharSequence(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (0xe88e)))+"  "));
 //BA.debugLineNum = 677;BA.debugLine="CS.Typeface(Font).Size(22).Append(lbl.Text).Pop";
_cs.Typeface((android.graphics.Typeface)(mostCurrent._font.getObject())).Size((int) (22)).Append(BA.ObjectToCharSequence(_lbl.getText())).Pop();
 //BA.debugLineNum = 679;BA.debugLine="lbl.Text = CS.PopAll";
_lbl.setText(BA.ObjectToCharSequence(_cs.PopAll().getObject()));
 };
 //BA.debugLineNum = 681;BA.debugLine="If ViewType = alert.VIEW_MESSAGE Then";
if (_viewtype==_alert.VIEW_MESSAGE) { 
 //BA.debugLineNum = 682;BA.debugLine="Dim lbl As Label = View";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_view.getObject()));
 //BA.debugLineNum = 683;BA.debugLine="lbl.TextSize = 17";
_lbl.setTextSize((float) (17));
 //BA.debugLineNum = 684;BA.debugLine="lbl.TextColor = Colors.Gray";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 };
 //BA.debugLineNum = 686;BA.debugLine="End Sub";
return "";
}
public static de.amberhome.objects.appcompat.ACMenuItemWrapper  _getmenuitem(String _title) throws Exception{
int _i = 0;
de.amberhome.objects.appcompat.ACMenuItemWrapper _m = null;
 //BA.debugLineNum = 566;BA.debugLine="Sub GetMenuItem(Title As String) As ACMenuItem";
 //BA.debugLineNum = 567;BA.debugLine="For i = 0 To ToolBar.Menu.Size - 1";
{
final int step1 = 1;
final int limit1 = (int) (mostCurrent._toolbar.getMenu().Size()-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 568;BA.debugLine="Dim m As ACMenuItem = ToolBar.Menu.GetItem(i)";
_m = new de.amberhome.objects.appcompat.ACMenuItemWrapper();
_m = mostCurrent._toolbar.getMenu().GetItem(_i);
 //BA.debugLineNum = 569;BA.debugLine="If m.Title = Title Then";
if ((_m.getTitle()).equals(_title)) { 
 //BA.debugLineNum = 570;BA.debugLine="Return m";
if (true) return _m;
 };
 }
};
 //BA.debugLineNum = 573;BA.debugLine="Return Null";
if (true) return (de.amberhome.objects.appcompat.ACMenuItemWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new de.amberhome.objects.appcompat.ACMenuItemWrapper(), (android.view.MenuItem)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 574;BA.debugLine="End Sub";
return null;
}
public static String  _getpumpinfo(int _ipumpstationid) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rspumpinfo = null;
 //BA.debugLineNum = 1333;BA.debugLine="Private Sub GetPumpInfo (iPumpStationID As Int)";
 //BA.debugLineNum = 1334;BA.debugLine="Dim rsPumpInfo As Cursor";
_rspumpinfo = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 1335;BA.debugLine="Try";
try { //BA.debugLineNum = 1336;BA.debugLine="Starter.strCriteria = \"SELECT Station.PumpHouseC";
mostCurrent._starter._strcriteria /*String*/  = "SELECT Station.PumpHouseCode, Station.PumpLocation, "+"Station.PumpHouseID, Station.FMID, Station.FMNo, Station.LastRdg "+"FROM tblPumpStation AS Station "+"WHERE Station.StationID = "+BA.NumberToString(_ipumpstationid);
 //BA.debugLineNum = 1340;BA.debugLine="rsPumpInfo = Starter.DBCon.ExecQuery(Starter.str";
_rspumpinfo = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 1342;BA.debugLine="If rsPumpInfo.RowCount > 0 Then";
if (_rspumpinfo.getRowCount()>0) { 
 //BA.debugLineNum = 1343;BA.debugLine="rsPumpInfo.Position = 0";
_rspumpinfo.setPosition((int) (0));
 };
 } 
       catch (Exception e9) {
			processBA.setLastException(e9); //BA.debugLineNum = 1347;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("083099662",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 1349;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 25;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 26;BA.debugLine="Dim ActionBarButton As ACActionBar";
mostCurrent._actionbarbutton = new de.amberhome.objects.appcompat.ACActionBar();
 //BA.debugLineNum = 27;BA.debugLine="Private ToolBar As ACToolBarDark";
mostCurrent._toolbar = new de.amberhome.objects.appcompat.ACToolbarDarkWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private xmlIcon As XmlLayoutBuilder";
mostCurrent._xmlicon = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 29;BA.debugLine="Private MatDialogBuilder As MaterialDialogBuilder";
mostCurrent._matdialogbuilder = new de.amberhome.materialdialogs.MaterialDialogBuilderWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private CD As ColorDrawable";
mostCurrent._cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 31;BA.debugLine="Private vibration As B4Avibrate";
mostCurrent._vibration = new com.johan.Vibrate.Vibrate();
 //BA.debugLineNum = 32;BA.debugLine="Private vibratePattern() As Long";
_vibratepattern = new long[(int) (0)];
;
 //BA.debugLineNum = 33;BA.debugLine="Private snack As DSSnackbar";
mostCurrent._snack = new de.amberhome.objects.SnackbarWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private csAns As CSBuilder";
mostCurrent._csans = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 36;BA.debugLine="Private Drawer As B4XDrawer";
mostCurrent._drawer = new bwsi.PumpOperations.b4xdrawer();
 //BA.debugLineNum = 37;BA.debugLine="Private lvMenus As ListView";
mostCurrent._lvmenus = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private pnlMenuHeader As Panel";
mostCurrent._pnlmenuheader = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private lblUserBranch As Label";
mostCurrent._lbluserbranch = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private lblUserFullName As Label";
mostCurrent._lbluserfullname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private img1 As ImageView";
mostCurrent._img1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private img2 As ImageView";
mostCurrent._img2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private img3 As ImageView";
mostCurrent._img3 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private img4 As ImageView";
mostCurrent._img4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private img5 As ImageView";
mostCurrent._img5 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private img6 As ImageView";
mostCurrent._img6 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private pnlJO As Panel";
mostCurrent._pnljo = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private pnlNonOp As Panel";
mostCurrent._pnlnonop = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private pnlProd As Panel";
mostCurrent._pnlprod = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private pnlRepair As Panel";
mostCurrent._pnlrepair = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private pnlStatus As Panel";
mostCurrent._pnlstatus = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private pnlCPM As Panel";
mostCurrent._pnlcpm = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private pnlGPM As Panel";
mostCurrent._pnlgpm = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private SelectedWBEntry As Int";
_selectedwbentry = 0;
 //BA.debugLineNum = 57;BA.debugLine="Private lblTranDate As Label";
mostCurrent._lbltrandate = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Dim theDate As Long";
_thedate = 0L;
 //BA.debugLineNum = 61;BA.debugLine="Dim dblInitRdg, dblLastRdg As Double";
_dblinitrdg = 0;
_dbllastrdg = 0;
 //BA.debugLineNum = 63;BA.debugLine="Private lblEmpName As B4XView";
mostCurrent._lblempname = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Private lblBranchName As B4XView";
mostCurrent._lblbranchname = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 65;BA.debugLine="Private lblReadingPeriod As B4XView";
mostCurrent._lblreadingperiod = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Private DtDialog As DateDialogs";
mostCurrent._dtdialog = new bwsi.PumpOperations.datedialogs();
 //BA.debugLineNum = 68;BA.debugLine="Private MyToast As BCToast";
mostCurrent._mytoast = new bwsi.PumpOperations.bctoast();
 //BA.debugLineNum = 70;BA.debugLine="Private pnlPumpSelection As Panel";
mostCurrent._pnlpumpselection = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 71;BA.debugLine="Private clvPumpList As CustomListView";
mostCurrent._clvpumplist = new b4a.example3.customlistview();
 //BA.debugLineNum = 72;BA.debugLine="Private lblPumpLoc As B4XView";
mostCurrent._lblpumploc = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 73;BA.debugLine="Private lblPumpCode As B4XView";
mostCurrent._lblpumpcode = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 75;BA.debugLine="Type PumpAssigned (PumpID As Int, PumpCode As Str";
;
 //BA.debugLineNum = 77;BA.debugLine="Private btnSelect As ACButton";
mostCurrent._btnselect = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 78;BA.debugLine="Private btnCancel As ACButton";
mostCurrent._btncancel = new de.amberhome.objects.appcompat.ACButtonWrapper();
 //BA.debugLineNum = 80;BA.debugLine="Private lblAvatar As Label";
mostCurrent._lblavatar = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 81;BA.debugLine="Private pnlUserStyle As Panel";
mostCurrent._pnluserstyle = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 82;BA.debugLine="Private lblMenuAvatar As Label";
mostCurrent._lblmenuavatar = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 83;BA.debugLine="Private JOWithType As Int";
_jowithtype = 0;
 //BA.debugLineNum = 85;BA.debugLine="Private Font As Typeface = Typeface.LoadFromAsset";
mostCurrent._font = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._font = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont.ttf")));
 //BA.debugLineNum = 86;BA.debugLine="Private FontBold As Typeface = Typeface.LoadFromA";
mostCurrent._fontbold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._fontbold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("myfont_bold.ttf")));
 //BA.debugLineNum = 87;BA.debugLine="Private iJOUnreadCount As Int";
_ijounreadcount = 0;
 //BA.debugLineNum = 89;BA.debugLine="Private badger1 As Badger";
mostCurrent._badger1 = new bwsi.PumpOperations.badger();
 //BA.debugLineNum = 90;BA.debugLine="Private NotifTimer As Timer";
mostCurrent._notiftimer = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 91;BA.debugLine="Private KVS As KeyValueStore";
mostCurrent._kvs = new bwsi.PumpOperations.keyvaluestore();
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _jocat_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 1098;BA.debugLine="Private Sub JOCat_ButtonPressed (mDialog As Materi";
 //BA.debugLineNum = 1099;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE)) {
case 0: {
 //BA.debugLineNum = 1101;BA.debugLine="LogColor(GlobalVar.SelectedJOCat, Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogImpl("082640899",BA.NumberToString(mostCurrent._globalvar._selectedjocat /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 1102;BA.debugLine="Select Case GlobalVar.SelectedJOCat";
switch (BA.switchObjectToInt(mostCurrent._globalvar._selectedjocat /*int*/ ,(int) (0),(int) (1),(int) (2),(int) (3),(int) (4),(int) (5),(int) (6),(int) (7),(int) (8),(int) (9),(int) (10),(int) (11),(int) (12))) {
case 0: {
 //BA.debugLineNum = 1104;BA.debugLine="GlobalVar.SelectedJOCatCode = \"SAS\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "SAS";
 //BA.debugLineNum = 1105;BA.debugLine="JOWithType = 0";
_jowithtype = (int) (0);
 break; }
case 1: {
 //BA.debugLineNum = 1108;BA.debugLine="GlobalVar.SelectedJOCatCode = \"NC\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "NC";
 //BA.debugLineNum = 1109;BA.debugLine="JOWithType = 0";
_jowithtype = (int) (0);
 break; }
case 2: {
 //BA.debugLineNum = 1112;BA.debugLine="GlobalVar.SelectedJOCatCode = \"DC-CR\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "DC-CR";
 //BA.debugLineNum = 1113;BA.debugLine="JOWithType = 0";
_jowithtype = (int) (0);
 break; }
case 3: {
 //BA.debugLineNum = 1116;BA.debugLine="GlobalVar.SelectedJOCatCode = \"DC-DA\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "DC-DA";
 //BA.debugLineNum = 1117;BA.debugLine="JOWithType = 0";
_jowithtype = (int) (0);
 break; }
case 4: {
 //BA.debugLineNum = 1120;BA.debugLine="GlobalVar.SelectedJOCatCode = \"RC\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "RC";
 //BA.debugLineNum = 1121;BA.debugLine="JOWithType = 0";
_jowithtype = (int) (0);
 break; }
case 5: {
 //BA.debugLineNum = 1124;BA.debugLine="GlobalVar.SelectedJOCatCode = \"CM\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "CM";
 //BA.debugLineNum = 1125;BA.debugLine="JOWithType = 0";
_jowithtype = (int) (0);
 break; }
case 6: {
 //BA.debugLineNum = 1128;BA.debugLine="GlobalVar.SelectedJOCatCode = \"MC\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "MC";
 //BA.debugLineNum = 1129;BA.debugLine="JOWithType = 1";
_jowithtype = (int) (1);
 break; }
case 7: {
 //BA.debugLineNum = 1132;BA.debugLine="GlobalVar.SelectedJOCatCode = \"RM\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "RM";
 //BA.debugLineNum = 1133;BA.debugLine="JOWithType = 1";
_jowithtype = (int) (1);
 break; }
case 8: {
 //BA.debugLineNum = 1136;BA.debugLine="GlobalVar.SelectedJOCatCode = \"IC\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "IC";
 //BA.debugLineNum = 1137;BA.debugLine="JOWithType = 1";
_jowithtype = (int) (1);
 break; }
case 9: {
 //BA.debugLineNum = 1140;BA.debugLine="GlobalVar.SelectedJOCatCode = \"SL\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "SL";
 //BA.debugLineNum = 1141;BA.debugLine="JOWithType = 1";
_jowithtype = (int) (1);
 break; }
case 10: {
 //BA.debugLineNum = 1144;BA.debugLine="GlobalVar.SelectedJOCatCode = \"SV\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "SV";
 //BA.debugLineNum = 1145;BA.debugLine="JOWithType = 1";
_jowithtype = (int) (1);
 break; }
case 11: {
 //BA.debugLineNum = 1148;BA.debugLine="GlobalVar.SelectedJOCatCode = \"OT\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "OT";
 //BA.debugLineNum = 1149;BA.debugLine="JOWithType = 0";
_jowithtype = (int) (0);
 break; }
case 12: {
 //BA.debugLineNum = 1152;BA.debugLine="GlobalVar.SelectedJOCatCode = \"CAC\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "CAC";
 //BA.debugLineNum = 1153;BA.debugLine="JOWithType = 0";
_jowithtype = (int) (0);
 break; }
}
;
 //BA.debugLineNum = 1157;BA.debugLine="If JOWithType = 0 Then";
if (_jowithtype==0) { 
 //BA.debugLineNum = 1158;BA.debugLine="StartActivity(actJO)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actjo.getObject()));
 //BA.debugLineNum = 1159;BA.debugLine="mDialog.Dismiss";
_mdialog.Dismiss();
 }else {
 //BA.debugLineNum = 1161;BA.debugLine="mDialog.Dismiss";
_mdialog.Dismiss();
 //BA.debugLineNum = 1162;BA.debugLine="ShowJOReasons(GlobalVar.SelectedJOCatCode)";
_showjoreasons(mostCurrent._globalvar._selectedjocatcode /*String*/ );
 };
 //BA.debugLineNum = 1165;BA.debugLine="LogColor(GlobalVar.SelectedJOCatCode, Colors.Ye";
anywheresoftware.b4a.keywords.Common.LogImpl("082640963",mostCurrent._globalvar._selectedjocatcode /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 1168;BA.debugLine="End Sub";
return "";
}
public static String  _jocat_ondismiss(de.amberhome.materialdialogs.MaterialDialogWrapper _dialog) throws Exception{
 //BA.debugLineNum = 1088;BA.debugLine="Private Sub JOCat_OnDismiss (Dialog As MaterialDia";
 //BA.debugLineNum = 1089;BA.debugLine="Log(\"Dialog dismissed\")";
anywheresoftware.b4a.keywords.Common.LogImpl("082509825","Dialog dismissed",0);
 //BA.debugLineNum = 1090;BA.debugLine="End Sub";
return "";
}
public static String  _jocat_singlechoiceitemselected(de.amberhome.materialdialogs.MaterialDialogWrapper _dialog,int _position,String _text) throws Exception{
 //BA.debugLineNum = 1092;BA.debugLine="Private Sub JOCat_SingleChoiceItemSelected (Dialog";
 //BA.debugLineNum = 1093;BA.debugLine="GlobalVar.SelectedJOCat = Position";
mostCurrent._globalvar._selectedjocat /*int*/  = _position;
 //BA.debugLineNum = 1094;BA.debugLine="GlobalVar.SelectedJOCatCode = Text";
mostCurrent._globalvar._selectedjocatcode /*String*/  = _text;
 //BA.debugLineNum = 1095;BA.debugLine="LogColor($\"JO Category: \"$ & Position & CRLF & $\"";
anywheresoftware.b4a.keywords.Common.LogImpl("082575363",("JO Category: ")+BA.NumberToString(_position)+anywheresoftware.b4a.keywords.Common.CRLF+("JO Cat Code: ")+_text,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 1096;BA.debugLine="End Sub";
return "";
}
public static String  _joreason_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 1235;BA.debugLine="Private Sub JOReason_ButtonPressed (mDialog As Mat";
 //BA.debugLineNum = 1236;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE)) {
case 0: {
 //BA.debugLineNum = 1238;BA.debugLine="LogColor(GlobalVar.SelectedJOCat, Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogImpl("082903043",BA.NumberToString(mostCurrent._globalvar._selectedjocat /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 1240;BA.debugLine="Select Case GlobalVar.SelectedJOCat";
switch (BA.switchObjectToInt(mostCurrent._globalvar._selectedjocat /*int*/ ,(int) (0),(int) (1),(int) (2),(int) (3),(int) (4),(int) (5),(int) (6),(int) (7),(int) (8),(int) (9),(int) (10),(int) (11),(int) (12))) {
case 0: {
 //BA.debugLineNum = 1242;BA.debugLine="GlobalVar.SelectedJOCatCode = \"SAS\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "SAS";
 //BA.debugLineNum = 1243;BA.debugLine="StartActivity(actJO)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actjo.getObject()));
 //BA.debugLineNum = 1244;BA.debugLine="LogColor(GlobalVar.SelectedJOCatCode, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("082903049",mostCurrent._globalvar._selectedjocatcode /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 break; }
case 1: {
 //BA.debugLineNum = 1247;BA.debugLine="GlobalVar.SelectedJOCatCode = \"NC\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "NC";
 //BA.debugLineNum = 1248;BA.debugLine="StartActivity(actJO)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actjo.getObject()));
 //BA.debugLineNum = 1249;BA.debugLine="LogColor(GlobalVar.SelectedJOCatCode, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("082903054",mostCurrent._globalvar._selectedjocatcode /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 break; }
case 2: {
 //BA.debugLineNum = 1252;BA.debugLine="GlobalVar.SelectedJOCatCode = \"DC-CR\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "DC-CR";
 //BA.debugLineNum = 1253;BA.debugLine="StartActivity(actJO)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actjo.getObject()));
 //BA.debugLineNum = 1254;BA.debugLine="LogColor(GlobalVar.SelectedJOCatCode, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("082903059",mostCurrent._globalvar._selectedjocatcode /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 break; }
case 3: {
 //BA.debugLineNum = 1257;BA.debugLine="GlobalVar.SelectedJOCatCode = \"DC-DA\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "DC-DA";
 //BA.debugLineNum = 1258;BA.debugLine="StartActivity(actJO)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actjo.getObject()));
 //BA.debugLineNum = 1259;BA.debugLine="LogColor(GlobalVar.SelectedJOCatCode, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("082903064",mostCurrent._globalvar._selectedjocatcode /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 break; }
case 4: {
 //BA.debugLineNum = 1262;BA.debugLine="GlobalVar.SelectedJOCatCode = \"RC\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "RC";
 //BA.debugLineNum = 1263;BA.debugLine="StartActivity(actJO)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actjo.getObject()));
 //BA.debugLineNum = 1264;BA.debugLine="LogColor(GlobalVar.SelectedJOCatCode, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("082903069",mostCurrent._globalvar._selectedjocatcode /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 break; }
case 5: {
 //BA.debugLineNum = 1267;BA.debugLine="GlobalVar.SelectedJOCatCode = \"CM\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "CM";
 //BA.debugLineNum = 1268;BA.debugLine="StartActivity(actJO)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actjo.getObject()));
 //BA.debugLineNum = 1269;BA.debugLine="LogColor(GlobalVar.SelectedJOCatCode, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("082903074",mostCurrent._globalvar._selectedjocatcode /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 break; }
case 6: {
 //BA.debugLineNum = 1272;BA.debugLine="GlobalVar.SelectedJOCatCode = \"MC\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "MC";
 //BA.debugLineNum = 1273;BA.debugLine="ShowJOReasons(\"MC\")";
_showjoreasons("MC");
 //BA.debugLineNum = 1274;BA.debugLine="LogColor(GlobalVar.SelectedJOCatCode, Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("082903079",mostCurrent._globalvar._selectedjocatcode /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 break; }
case 7: {
 //BA.debugLineNum = 1277;BA.debugLine="GlobalVar.SelectedJOCatCode = \"RM\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "RM";
 break; }
case 8: {
 //BA.debugLineNum = 1280;BA.debugLine="GlobalVar.SelectedJOCatCode = \"IC\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "IC";
 break; }
case 9: {
 //BA.debugLineNum = 1283;BA.debugLine="GlobalVar.SelectedJOCatCode = \"SL\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "SL";
 break; }
case 10: {
 //BA.debugLineNum = 1286;BA.debugLine="GlobalVar.SelectedJOCatCode = \"SV\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "SV";
 break; }
case 11: {
 //BA.debugLineNum = 1289;BA.debugLine="GlobalVar.SelectedJOCatCode = \"OT\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "OT";
 break; }
case 12: {
 //BA.debugLineNum = 1292;BA.debugLine="GlobalVar.SelectedJOCatCode = \"CAC\"";
mostCurrent._globalvar._selectedjocatcode /*String*/  = "CAC";
 break; }
}
;
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 1298;BA.debugLine="End Sub";
return "";
}
public static String  _joreason_ondismiss(de.amberhome.materialdialogs.MaterialDialogWrapper _dialog) throws Exception{
 //BA.debugLineNum = 1225;BA.debugLine="Private Sub JOReason_OnDismiss (Dialog As Material";
 //BA.debugLineNum = 1226;BA.debugLine="Log(\"Dialog dismissed\")";
anywheresoftware.b4a.keywords.Common.LogImpl("082771969","Dialog dismissed",0);
 //BA.debugLineNum = 1227;BA.debugLine="End Sub";
return "";
}
public static String  _joreason_singlechoiceitemselected(de.amberhome.materialdialogs.MaterialDialogWrapper _dialog,int _position,String _text) throws Exception{
 //BA.debugLineNum = 1229;BA.debugLine="Private Sub JOReason_SingleChoiceItemSelected (Dia";
 //BA.debugLineNum = 1230;BA.debugLine="GlobalVar.SelectedJOCat = Position";
mostCurrent._globalvar._selectedjocat /*int*/  = _position;
 //BA.debugLineNum = 1231;BA.debugLine="GlobalVar.SelectedJOCatCode = Text";
mostCurrent._globalvar._selectedjocatcode /*String*/  = _text;
 //BA.debugLineNum = 1232;BA.debugLine="LogColor($\"JO Category: \"$ & Position & CRLF & $\"";
anywheresoftware.b4a.keywords.Common.LogImpl("082837507",("JO Category: ")+BA.NumberToString(_position)+anywheresoftware.b4a.keywords.Common.CRLF+("JO Cat Code: ")+_text,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 1233;BA.debugLine="End Sub";
return "";
}
public static String  _logoff_click() throws Exception{
 //BA.debugLineNum = 523;BA.debugLine="Private Sub LogOFF_Click()";
 //BA.debugLineNum = 524;BA.debugLine="ClearUserData";
_clearuserdata();
 //BA.debugLineNum = 525;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 526;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 527;BA.debugLine="End Sub";
return "";
}
public static String  _lvmenus_itemclick(int _position,Object _value) throws Exception{
anywheresoftware.b4a.objects.drawable.ColorDrawable _cdback = null;
 //BA.debugLineNum = 431;BA.debugLine="Sub lvMenus_ItemClick (Position As Int, Value As O";
 //BA.debugLineNum = 432;BA.debugLine="LogColor(Value, Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogImpl("080936961",BA.ObjectToString(_value),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 433;BA.debugLine="Select Case Value";
switch (BA.switchObjectToInt(_value,(Object)(1),(Object)(2),(Object)(3),(Object)(4),(Object)(5),(Object)(6),(Object)(7),(Object)(8),(Object)(9),(Object)(10))) {
case 0: {
 break; }
case 1: {
 break; }
case 2: {
 break; }
case 3: {
 break; }
case 4: {
 break; }
case 5: {
 break; }
case 6: {
 break; }
case 7: {
 break; }
case 8: {
 //BA.debugLineNum = 501;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 502;BA.debugLine="snack.Initialize(\"LogOFF\", Activity, $\"Sure to";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"LogOFF",(android.view.View)(mostCurrent._activity.getObject()),("Sure to Log Off now?"),mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 503;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 504;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 505;BA.debugLine="snack.SetAction(csAns)";
mostCurrent._snack.SetAction(BA.ObjectToString(mostCurrent._csans));
 //BA.debugLineNum = 506;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 break; }
case 9: {
 //BA.debugLineNum = 509;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 510;BA.debugLine="snack.Initialize(\"CloseButton\", Activity, $\"Clo";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"CloseButton",(android.view.View)(mostCurrent._activity.getObject()),("Close ")+anywheresoftware.b4a.keywords.Common.Application.getLabelName()+("?"),mostCurrent._snack.DURATION_LONG);
 //BA.debugLineNum = 511;BA.debugLine="snack.SetAction(csAns)";
mostCurrent._snack.SetAction(BA.ObjectToString(mostCurrent._csans));
 //BA.debugLineNum = 512;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 513;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 514;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 break; }
}
;
 //BA.debugLineNum = 517;BA.debugLine="Dim CDBack As ColorDrawable";
_cdback = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 518;BA.debugLine="CDBack.Initialize(Colors.Transparent,0)";
_cdback.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 519;BA.debugLine="lvMenus.Background = CDBack";
mostCurrent._lvmenus.setBackground((android.graphics.drawable.Drawable)(_cdback.getObject()));
 //BA.debugLineNum = 520;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 521;BA.debugLine="End Sub";
return "";
}
public static boolean  _notifycountjo() throws Exception{
boolean _bretval = false;
 //BA.debugLineNum = 1469;BA.debugLine="Private Sub NotifyCountJO As Boolean";
 //BA.debugLineNum = 1470;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 1472;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1473;BA.debugLine="iJOUnreadCount = 0";
_ijounreadcount = (int) (0);
 //BA.debugLineNum = 1475;BA.debugLine="Try";
try { //BA.debugLineNum = 1476;BA.debugLine="iJOUnreadCount = Starter.DBCon.ExecQuerySingleRe";
_ijounreadcount = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(JOID) FROM tblJOs WHERE WasRead = 0 AND JOStatus = 1")));
 //BA.debugLineNum = 1477;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e8) {
			processBA.setLastException(e8); //BA.debugLineNum = 1479;BA.debugLine="iJOUnreadCount = 0";
_ijounreadcount = (int) (0);
 //BA.debugLineNum = 1480;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1481;BA.debugLine="ToastMessageShow($\"Unable to count JO due to \"$";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to count JO due to ")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1482;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("083689485",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 1484;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 1485;BA.debugLine="End Sub";
return false;
}
public static String  _pnlcpm_click() throws Exception{
 //BA.debugLineNum = 1303;BA.debugLine="Sub pnlCPM_Click";
 //BA.debugLineNum = 1304;BA.debugLine="If GlobalVar.UserPosID = 5 Then";
if (mostCurrent._globalvar._userposid /*int*/ ==5) { 
 //BA.debugLineNum = 1305;BA.debugLine="StartActivity(actCriticalPoint)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcriticalpoint.getObject()));
 }else {
 //BA.debugLineNum = 1308;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 1309;BA.debugLine="MyToast.DefaultTextColor = GlobalVar.RedColor";
mostCurrent._mytoast._defaulttextcolor /*int*/  = (int) (mostCurrent._globalvar._redcolor /*double*/ );
 //BA.debugLineNum = 1310;BA.debugLine="MyToast.pnl.Color = Colors.White";
mostCurrent._mytoast._pnl /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1311;BA.debugLine="MyToast.Show($\"You are Login as Plumber!\"$ & CRL";
mostCurrent._mytoast._show /*void*/ (("You are Login as Plumber!")+anywheresoftware.b4a.keywords.Common.CRLF+("Login your Pump Operator Account first..."));
 };
 //BA.debugLineNum = 1313;BA.debugLine="End Sub";
return "";
}
public static String  _pnlgpm_click() throws Exception{
 //BA.debugLineNum = 1319;BA.debugLine="Sub pnlGPM_Click";
 //BA.debugLineNum = 1320;BA.debugLine="If GlobalVar.UserPosID = 5 Then";
if (mostCurrent._globalvar._userposid /*int*/ ==5) { 
 //BA.debugLineNum = 1321;BA.debugLine="GlobalVar.blnNewGPM = True";
mostCurrent._globalvar._blnnewgpm /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1322;BA.debugLine="StartActivity(actGPMCalc)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actgpmcalc.getObject()));
 }else {
 //BA.debugLineNum = 1324;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 1325;BA.debugLine="MyToast.DefaultTextColor = GlobalVar.RedColor";
mostCurrent._mytoast._defaulttextcolor /*int*/  = (int) (mostCurrent._globalvar._redcolor /*double*/ );
 //BA.debugLineNum = 1326;BA.debugLine="MyToast.pnl.Color = Colors.White";
mostCurrent._mytoast._pnl /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1327;BA.debugLine="MyToast.Show($\"You are Login as Plumber!\"$ & CRL";
mostCurrent._mytoast._show /*void*/ (("You are Login as Plumber!")+anywheresoftware.b4a.keywords.Common.CRLF+("Login your Pump Operator Account first..."));
 };
 //BA.debugLineNum = 1329;BA.debugLine="End Sub";
return "";
}
public static String  _pnljo_click() throws Exception{
 //BA.debugLineNum = 1022;BA.debugLine="Sub pnlJO_Click";
 //BA.debugLineNum = 1023;BA.debugLine="If GlobalVar.UserPosID = 6 Then";
if (mostCurrent._globalvar._userposid /*int*/ ==6) { 
 //BA.debugLineNum = 1025;BA.debugLine="StartActivity(actJOSummary)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actjosummary.getObject()));
 }else {
 //BA.debugLineNum = 1027;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 1028;BA.debugLine="MyToast.DefaultTextColor = GlobalVar.RedColor";
mostCurrent._mytoast._defaulttextcolor /*int*/  = (int) (mostCurrent._globalvar._redcolor /*double*/ );
 //BA.debugLineNum = 1029;BA.debugLine="MyToast.pnl.Color = Colors.White";
mostCurrent._mytoast._pnl /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1030;BA.debugLine="MyToast.Show($\"You are Login as Pump Operator!\"$";
mostCurrent._mytoast._show /*void*/ (("You are Login as Pump Operator!")+anywheresoftware.b4a.keywords.Common.CRLF+("Login your Plumber Account first..."));
 };
 //BA.debugLineNum = 1032;BA.debugLine="End Sub";
return "";
}
public static String  _pnlnonop_click() throws Exception{
 //BA.debugLineNum = 1010;BA.debugLine="Sub pnlNonOp_Click";
 //BA.debugLineNum = 1011;BA.debugLine="If GlobalVar.UserPosID = 5 Then";
if (mostCurrent._globalvar._userposid /*int*/ ==5) { 
 //BA.debugLineNum = 1012;BA.debugLine="StartActivity(actNonOperational)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actnonoperational.getObject()));
 }else {
 //BA.debugLineNum = 1014;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 1015;BA.debugLine="MyToast.DefaultTextColor = GlobalVar.RedColor";
mostCurrent._mytoast._defaulttextcolor /*int*/  = (int) (mostCurrent._globalvar._redcolor /*double*/ );
 //BA.debugLineNum = 1016;BA.debugLine="MyToast.pnl.Color = Colors.White";
mostCurrent._mytoast._pnl /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1017;BA.debugLine="MyToast.Show($\"You are Login as Plumber!\"$ & CRL";
mostCurrent._mytoast._show /*void*/ (("You are Login as Plumber!")+anywheresoftware.b4a.keywords.Common.CRLF+("Login your Pump Operator Account first..."));
 };
 //BA.debugLineNum = 1019;BA.debugLine="End Sub";
return "";
}
public static String  _pnlprod_click() throws Exception{
 //BA.debugLineNum = 919;BA.debugLine="Sub pnlProd_Click";
 //BA.debugLineNum = 921;BA.debugLine="If GlobalVar.UserPosID = 5 Then";
if (mostCurrent._globalvar._userposid /*int*/ ==5) { 
 //BA.debugLineNum = 922;BA.debugLine="StartActivity(actNewProduction)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actnewproduction.getObject()));
 }else {
 //BA.debugLineNum = 924;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 925;BA.debugLine="MyToast.DefaultTextColor = GlobalVar.RedColor";
mostCurrent._mytoast._defaulttextcolor /*int*/  = (int) (mostCurrent._globalvar._redcolor /*double*/ );
 //BA.debugLineNum = 926;BA.debugLine="MyToast.pnl.Color = Colors.White";
mostCurrent._mytoast._pnl /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 927;BA.debugLine="MyToast.Show($\"You are Login as Plumber!\"$ & CRL";
mostCurrent._mytoast._show /*void*/ (("You are Login as Plumber!")+anywheresoftware.b4a.keywords.Common.CRLF+("Login your Pump Operator Account first..."));
 };
 //BA.debugLineNum = 929;BA.debugLine="End Sub";
return "";
}
public static String  _pnlpumpselection_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1351;BA.debugLine="Sub pnlPumpSelection_Touch (Action As Int, X As Fl";
 //BA.debugLineNum = 1353;BA.debugLine="End Sub";
return "";
}
public static String  _pnlrepair_click() throws Exception{
 //BA.debugLineNum = 736;BA.debugLine="Sub pnlRepair_Click";
 //BA.debugLineNum = 737;BA.debugLine="If GlobalVar.UserPosID = 6 Then";
if (mostCurrent._globalvar._userposid /*int*/ ==6) { 
 //BA.debugLineNum = 738;BA.debugLine="ShowRMJO";
_showrmjo();
 }else {
 //BA.debugLineNum = 740;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 741;BA.debugLine="MyToast.DefaultTextColor = GlobalVar.RedColor";
mostCurrent._mytoast._defaulttextcolor /*int*/  = (int) (mostCurrent._globalvar._redcolor /*double*/ );
 //BA.debugLineNum = 742;BA.debugLine="MyToast.pnl.Color = Colors.White";
mostCurrent._mytoast._pnl /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 743;BA.debugLine="MyToast.Show($\"You are Login as Pump Operator!\"$";
mostCurrent._mytoast._show /*void*/ (("You are Login as Pump Operator!")+anywheresoftware.b4a.keywords.Common.CRLF+("Login your Plumber Account first..."));
 };
 //BA.debugLineNum = 745;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 22;BA.debugLine="Private NotifBMP As Bitmap";
_notifbmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _removetrandate() throws Exception{
 //BA.debugLineNum = 1523;BA.debugLine="Private Sub RemoveTranDate";
 //BA.debugLineNum = 1524;BA.debugLine="KVS.Remove(\"trans_date\")";
mostCurrent._kvs._remove /*String*/ ("trans_date");
 //BA.debugLineNum = 1525;BA.debugLine="End Sub";
return "";
}
public static String  _rmjo_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 808;BA.debugLine="Private Sub RMJO_ButtonPressed (mDialog As Materia";
 //BA.debugLineNum = 809;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE)) {
case 0: {
 //BA.debugLineNum = 811;BA.debugLine="LogColor(GlobalVar.SelectedJOReason, Colors.Red";
anywheresoftware.b4a.keywords.Common.LogImpl("081920003",mostCurrent._globalvar._selectedjoreason /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 813;BA.debugLine="Select Case GlobalVar.SelectedJOReason";
switch (BA.switchObjectToInt(mostCurrent._globalvar._selectedjoreason /*String*/ ,"Drain from Mainline","Pump Drain","Inter-Connection","Replace Gate Valve","Hydrotest","Leak Repair","Busted Mainline","Busted Pipe","Replace Chlorinator Diagphram","Replace Chlorinator","Replace Chlorinator Hose")) {
case 0: {
 break; }
case 1: {
 break; }
case 2: {
 break; }
case 3: {
 break; }
case 4: {
 break; }
case 5: {
 break; }
case 6: {
 break; }
case 7: {
 break; }
case 8: {
 break; }
case 9: {
 break; }
case 10: {
 break; }
}
;
 //BA.debugLineNum = 826;BA.debugLine="LogColor(GlobalVar.SelectedJOReason, Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("081920018",mostCurrent._globalvar._selectedjoreason /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 break; }
case 1: {
 break; }
}
;
 //BA.debugLineNum = 830;BA.debugLine="End Sub";
return "";
}
public static String  _rmjo_ondismiss(de.amberhome.materialdialogs.MaterialDialogWrapper _dialog) throws Exception{
 //BA.debugLineNum = 800;BA.debugLine="Private Sub RMJO_OnDismiss (Dialog As MaterialDial";
 //BA.debugLineNum = 801;BA.debugLine="Log(\"Dialog dismissed\")";
anywheresoftware.b4a.keywords.Common.LogImpl("081788929","Dialog dismissed",0);
 //BA.debugLineNum = 802;BA.debugLine="End Sub";
return "";
}
public static String  _rmjo_singlechoiceitemselected(de.amberhome.materialdialogs.MaterialDialogWrapper _dialog,int _position,String _text) throws Exception{
 //BA.debugLineNum = 804;BA.debugLine="Private Sub RMJO_SingleChoiceItemSelected (Dialog";
 //BA.debugLineNum = 805;BA.debugLine="GlobalVar.SelectedJOReason = Text";
mostCurrent._globalvar._selectedjoreason /*String*/  = _text;
 //BA.debugLineNum = 806;BA.debugLine="End Sub";
return "";
}
public static String  _savetrandate() throws Exception{
anywheresoftware.b4a.objects.collections.Map _transdate = null;
 //BA.debugLineNum = 1510;BA.debugLine="Private Sub SaveTranDate";
 //BA.debugLineNum = 1512;BA.debugLine="Dim TransDate As Map";
_transdate = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 1513;BA.debugLine="TransDate.Initialize";
_transdate.Initialize();
 //BA.debugLineNum = 1514;BA.debugLine="TransDate.Put(\"TransDate\", GlobalVar.TranDate)";
_transdate.Put((Object)("TransDate"),(Object)(mostCurrent._globalvar._trandate /*String*/ ));
 //BA.debugLineNum = 1516;BA.debugLine="KVS.Put(\"trans_date\",TransDate)";
mostCurrent._kvs._put /*String*/ ("trans_date",(Object)(_transdate.getObject()));
 //BA.debugLineNum = 1517;BA.debugLine="End Sub";
return "";
}
public static String  _saveuserdata() throws Exception{
anywheresoftware.b4a.objects.collections.Map _userdata = null;
 //BA.debugLineNum = 1488;BA.debugLine="Private Sub SaveUserData";
 //BA.debugLineNum = 1489;BA.debugLine="Dim UserData As Map";
_userdata = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 1490;BA.debugLine="UserData.Initialize";
_userdata.Initialize();
 //BA.debugLineNum = 1491;BA.debugLine="UserData.Put(\"UserID\", GlobalVar.UserID)";
_userdata.Put((Object)("UserID"),(Object)(mostCurrent._globalvar._userid /*int*/ ));
 //BA.debugLineNum = 1492;BA.debugLine="UserData.Put(\"UserName\", GlobalVar.UserName)";
_userdata.Put((Object)("UserName"),(Object)(mostCurrent._globalvar._username /*String*/ ));
 //BA.debugLineNum = 1494;BA.debugLine="UserData.Put(\"UserPassword\", GlobalVar.UserPW)";
_userdata.Put((Object)("UserPassword"),(Object)(mostCurrent._globalvar._userpw /*String*/ ));
 //BA.debugLineNum = 1495;BA.debugLine="UserData.Put(\"EmpName\", GlobalVar.EmpName)";
_userdata.Put((Object)("EmpName"),(Object)(mostCurrent._globalvar._empname /*String*/ ));
 //BA.debugLineNum = 1496;BA.debugLine="UserData.Put(\"UserAvatar\", GlobalVar.UserAvatar)";
_userdata.Put((Object)("UserAvatar"),(Object)(mostCurrent._globalvar._useravatar /*String*/ ));
 //BA.debugLineNum = 1498;BA.debugLine="UserData.Put(\"BranchID\",GlobalVar.BranchID)";
_userdata.Put((Object)("BranchID"),(Object)(mostCurrent._globalvar._branchid /*int*/ ));
 //BA.debugLineNum = 1499;BA.debugLine="UserData.Put(\"BranchCode\", GlobalVar.BranchCode)";
_userdata.Put((Object)("BranchCode"),(Object)(mostCurrent._globalvar._branchcode /*String*/ ));
 //BA.debugLineNum = 1500;BA.debugLine="UserData.Put(\"BranchName\", GlobalVar.BranchName)";
_userdata.Put((Object)("BranchName"),(Object)(mostCurrent._globalvar._branchname /*String*/ ));
 //BA.debugLineNum = 1502;BA.debugLine="UserData.Put(\"UserPosID\", GlobalVar.UserPosID)";
_userdata.Put((Object)("UserPosID"),(Object)(mostCurrent._globalvar._userposid /*int*/ ));
 //BA.debugLineNum = 1503;BA.debugLine="UserData.Put(\"UserPosition\", GlobalVar.UserPos)";
_userdata.Put((Object)("UserPosition"),(Object)(mostCurrent._globalvar._userpos /*String*/ ));
 //BA.debugLineNum = 1505;BA.debugLine="UserData.Put(\"SysMode\", GlobalVar.SysMode)";
_userdata.Put((Object)("SysMode"),(Object)(mostCurrent._globalvar._sysmode /*int*/ ));
 //BA.debugLineNum = 1507;BA.debugLine="KVS.Put(\"user_data\",UserData)";
mostCurrent._kvs._put /*String*/ ("user_data",(Object)(_userdata.getObject()));
 //BA.debugLineNum = 1508;BA.debugLine="End Sub";
return "";
}
public static String  _selectedpump_buttonpressed(de.amberhome.materialdialogs.MaterialDialogWrapper _mdialog,String _saction) throws Exception{
 //BA.debugLineNum = 999;BA.debugLine="Private Sub SelectedPump_ButtonPressed (mDialog As";
 //BA.debugLineNum = 1000;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,_mdialog.ACTION_POSITIVE,_mdialog.ACTION_NEGATIVE)) {
case 0: {
 //BA.debugLineNum = 1002;BA.debugLine="LogColor(GlobalVar.PumpHouseID, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("082247683",BA.NumberToString(mostCurrent._globalvar._pumphouseid /*int*/ ),anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 1003;BA.debugLine="StartActivity(actProduction)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actproduction.getObject()));
 break; }
case 1: {
 //BA.debugLineNum = 1005;BA.debugLine="Return";
if (true) return "";
 break; }
}
;
 //BA.debugLineNum = 1007;BA.debugLine="End Sub";
return "";
}
public static String  _selectedpump_ondismiss(de.amberhome.materialdialogs.MaterialDialogWrapper _dialog) throws Exception{
 //BA.debugLineNum = 985;BA.debugLine="Private Sub SelectedPump_OnDismiss (Dialog As Mate";
 //BA.debugLineNum = 986;BA.debugLine="Log(\"Dialog dismissed\")";
anywheresoftware.b4a.keywords.Common.LogImpl("082116609","Dialog dismissed",0);
 //BA.debugLineNum = 987;BA.debugLine="End Sub";
return "";
}
public static String  _selectedpump_singlechoiceitemselected(de.amberhome.materialdialogs.MaterialDialogWrapper _dialog,int _position,String _text) throws Exception{
 //BA.debugLineNum = 989;BA.debugLine="Private Sub SelectedPump_SingleChoiceItemSelected";
 //BA.debugLineNum = 990;BA.debugLine="snack.Initialize(\"\", Activity, $\"Pump Selected :";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),("Pump Selected : ")+_text,mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 991;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalVa";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._pricolor /*double*/ ));
 //BA.debugLineNum = 992;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.Wh";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 993;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 994;BA.debugLine="GlobalVar.PumpHouseCode = Text";
mostCurrent._globalvar._pumphousecode /*String*/  = _text;
 //BA.debugLineNum = 996;BA.debugLine="GlobalVar.PumpHouseID = DBaseFunctions.GetPumpHou";
mostCurrent._globalvar._pumphouseid /*int*/  = mostCurrent._dbasefunctions._getpumphouseid /*int*/ (mostCurrent.activityBA,mostCurrent._globalvar._pumphousecode /*String*/ );
 //BA.debugLineNum = 997;BA.debugLine="End Sub";
return "";
}
public static String  _setdashboardicons() throws Exception{
 //BA.debugLineNum = 1443;BA.debugLine="Private Sub SetDashboardIcons";
 //BA.debugLineNum = 1444;BA.debugLine="If GlobalVar.UserPosID = 5 Then";
if (mostCurrent._globalvar._userposid /*int*/ ==5) { 
 //BA.debugLineNum = 1445;BA.debugLine="pnlProd.Enabled = True";
mostCurrent._pnlprod.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1446;BA.debugLine="img1.Bitmap = LoadBitmap(File.DirAssets,\"product";
mostCurrent._img1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"production.png").getObject()));
 }else if(mostCurrent._globalvar._userposid /*int*/ ==6) { 
 //BA.debugLineNum = 1448;BA.debugLine="pnlProd.Enabled = False";
mostCurrent._pnlprod.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1449;BA.debugLine="pnlProd.Color = Colors.LightGray";
mostCurrent._pnlprod.setColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 //BA.debugLineNum = 1450;BA.debugLine="img1.Bitmap = LoadBitmap(File.DirAssets,\"prod-di";
mostCurrent._img1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"prod-disable.png").getObject()));
 }else {
 };
 //BA.debugLineNum = 1454;BA.debugLine="End Sub";
return "";
}
public static void  _showassignedpump(int _iuserid) throws Exception{
ResumableSub_ShowAssignedPump rsub = new ResumableSub_ShowAssignedPump(null,_iuserid);
rsub.resume(processBA, null);
}
public static class ResumableSub_ShowAssignedPump extends BA.ResumableSub {
public ResumableSub_ShowAssignedPump(bwsi.PumpOperations.mainscreen parent,int _iuserid) {
this.parent = parent;
this._iuserid = _iuserid;
}
bwsi.PumpOperations.mainscreen parent;
int _iuserid;
Object _senderfilter = null;
boolean _success = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
long _starttime = 0L;
bwsi.PumpOperations.mainscreen._pumpassigned _pa = null;
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
 //BA.debugLineNum = 1392;BA.debugLine="Dim SenderFilter As Object";
_senderfilter = new Object();
 //BA.debugLineNum = 1393;BA.debugLine="clvPumpList.Clear";
parent.mostCurrent._clvpumplist._clear();
 //BA.debugLineNum = 1394;BA.debugLine="Try";
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
 //BA.debugLineNum = 1395;BA.debugLine="Starter.strCriteria = \"SELECT Assignment.Assigne";
parent.mostCurrent._starter._strcriteria /*String*/  = "SELECT Assignment.AssignedID, "+"Assignment.StationID, Station.PumpHouseCode, Station.PumpLocation "+"FROM tblAssignedStation AS Assignment "+"INNER JOIN tblPumpStation AS Station ON Assignment.StationID = Station.StationID "+"WHERE Assignment.OpID = "+BA.NumberToString(_iuserid)+" "+"ORDER BY Station.PumpID ASC";
 //BA.debugLineNum = 1402;BA.debugLine="SenderFilter = Starter.DBCon.ExecQueryAsync(\"SQL";
_senderfilter = parent.mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQueryAsync(processBA,"SQL",parent.mostCurrent._starter._strcriteria /*String*/ ,(anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 1403;BA.debugLine="Wait For (SenderFilter) SQL_QueryComplete (Succe";
anywheresoftware.b4a.keywords.Common.WaitFor("sql_querycomplete", processBA, this, _senderfilter);
this.state = 17;
return;
case 17:
//C
this.state = 4;
_success = (Boolean) result[0];
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) result[1];
;
 //BA.debugLineNum = 1405;BA.debugLine="If Success Then";
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
 //BA.debugLineNum = 1406;BA.debugLine="Dim StartTime As Long = DateTime.Now";
_starttime = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 1407;BA.debugLine="Do While RS.NextRow";
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
 //BA.debugLineNum = 1408;BA.debugLine="Dim PA As PumpAssigned";
_pa = new bwsi.PumpOperations.mainscreen._pumpassigned();
 //BA.debugLineNum = 1409;BA.debugLine="PA.Initialize";
_pa.Initialize();
 //BA.debugLineNum = 1410;BA.debugLine="PA.PumpID = RS.GetInt(\"StationID\")";
_pa.PumpID /*int*/  = _rs.GetInt("StationID");
 //BA.debugLineNum = 1411;BA.debugLine="PA.PumpCode = RS.GetString(\"PumpHouseCode\")";
_pa.PumpCode /*String*/  = _rs.GetString("PumpHouseCode");
 //BA.debugLineNum = 1412;BA.debugLine="PA.PumpLoc = RS.GetString(\"PumpLocation\")";
_pa.PumpLoc /*String*/  = _rs.GetString("PumpLocation");
 //BA.debugLineNum = 1414;BA.debugLine="Dim Pnl As B4XView = xui.CreatePanel(\"\")";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 1415;BA.debugLine="Pnl.SetLayoutAnimated(0, 10dip, 0dip, clvPumpL";
_pnl.SetLayoutAnimated((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),(int) (parent.mostCurrent._clvpumplist._asview().getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 1416;BA.debugLine="clvPumpList.Add(Pnl, PA)";
parent.mostCurrent._clvpumplist._add(_pnl,(Object)(_pa));
 if (true) break;

case 10:
//C
this.state = 13;
;
 if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 1419;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcept";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 1420;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1421;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 1422;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 1423;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("083361824",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;

case 13:
//C
this.state = 16;
;
 //BA.debugLineNum = 1426;BA.debugLine="Log($\"List of Time Records = ${NumberFormat2((Da";
anywheresoftware.b4a.keywords.Common.LogImpl("083361827",("List of Time Records = "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.NumberFormat2((anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_starttime)/(double)1000,(int) (0),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False)))+" seconds to populate "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(parent.mostCurrent._clvpumplist._getsize()))+" Time Records"),0);
 if (true) break;

case 15:
//C
this.state = 16;
this.catchState = 0;
 //BA.debugLineNum = 1429;BA.debugLine="snack.Initialize(\"\", Activity,$\"\"$ & LastExcepti";
parent.mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(parent.mostCurrent._activity.getObject()),("")+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),(int) (5000));
 //BA.debugLineNum = 1430;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
parent.mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1431;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
parent.mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,parent.mostCurrent._snack,(int) (parent.mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 1432;BA.debugLine="snack.Show";
parent.mostCurrent._snack.Show();
 //BA.debugLineNum = 1433;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("083361834",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 16:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 1436;BA.debugLine="End Sub";
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
public static String  _showdate_onnegativeclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
long _ldate = 0L;
String _sdate = "";
 //BA.debugLineNum = 611;BA.debugLine="Private Sub ShowDate_OnNegativeClicked (View As Vi";
 //BA.debugLineNum = 612;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 613;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 615;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 616;BA.debugLine="Dim sDate As String";
_sdate = "";
 //BA.debugLineNum = 618;BA.debugLine="DtDialog.Initialize(Activity, DateTime.Now)";
mostCurrent._dtdialog._initialize /*String*/ (mostCurrent.activityBA,mostCurrent._activity,anywheresoftware.b4a.keywords.Common.DateTime.getNow());
 //BA.debugLineNum = 619;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 620;BA.debugLine="theDate = DtDialog.Show($\"Select New Transaction";
_thedate = (long) (mostCurrent._dtdialog._show /*int*/ (("Select New Transaction Date")));
 //BA.debugLineNum = 622;BA.debugLine="If theDate = DialogResponse.POSITIVE Then";
if (_thedate==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 623;BA.debugLine="sDate = DtDialog.DateSelected";
_sdate = BA.NumberToString(mostCurrent._dtdialog._dateselected /*long*/ );
 //BA.debugLineNum = 625;BA.debugLine="GlobalVar.TranDate = DateTime.Date(sDate)";
mostCurrent._globalvar._trandate /*String*/  = anywheresoftware.b4a.keywords.Common.DateTime.Date((long)(Double.parseDouble(_sdate)));
 }else {
 //BA.debugLineNum = 627;BA.debugLine="theDate = DateTime.DateParse(DateTime.Date(DateT";
_thedate = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 628;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 629;BA.debugLine="GlobalVar.TranDate = DateTime.Date(theDate)";
mostCurrent._globalvar._trandate /*String*/  = anywheresoftware.b4a.keywords.Common.DateTime.Date(_thedate);
 };
 //BA.debugLineNum = 631;BA.debugLine="SaveTranDate";
_savetrandate();
 //BA.debugLineNum = 632;BA.debugLine="lblTranDate.Text =$\"TRANSACTION DATE: \"$ & Global";
mostCurrent._lbltrandate.setText(BA.ObjectToCharSequence(("TRANSACTION DATE: ")+mostCurrent._globalvar._trandate /*String*/ ));
 //BA.debugLineNum = 633;BA.debugLine="snack.Initialize(\"\",Activity,$\"Selected Transacti";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),("Selected Transaction Date is ")+mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 634;BA.debugLine="MyFunctions.SetSnackBarBackground(snack,GlobalVar";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._greencolor /*double*/ ));
 //BA.debugLineNum = 635;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.Wh";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 636;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 638;BA.debugLine="End Sub";
return "";
}
public static String  _showdate_onpositiveclicked(anywheresoftware.b4a.objects.ConcreteViewWrapper _view,Object _dialog) throws Exception{
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 640;BA.debugLine="Private Sub ShowDate_OnPositiveClicked (View As Vi";
 //BA.debugLineNum = 641;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 642;BA.debugLine="Alert.Initialize.Dismiss(Dialog)";
_alert.Initialize().Dismiss((android.app.Dialog)(_dialog));
 //BA.debugLineNum = 643;BA.debugLine="theDate = DateTime.DateParse(DateTime.Date(DateTi";
_thedate = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 644;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 645;BA.debugLine="GlobalVar.TranDate = DateTime.Date(theDate)";
mostCurrent._globalvar._trandate /*String*/  = anywheresoftware.b4a.keywords.Common.DateTime.Date(_thedate);
 //BA.debugLineNum = 646;BA.debugLine="lblTranDate.Text =$\"TRANSACTION DATE: \"$ & Global";
mostCurrent._lbltrandate.setText(BA.ObjectToCharSequence(("TRANSACTION DATE: ")+mostCurrent._globalvar._trandate /*String*/ ));
 //BA.debugLineNum = 648;BA.debugLine="If GlobalVar.UserPosID = 5 Then";
if (mostCurrent._globalvar._userposid /*int*/ ==5) { 
 //BA.debugLineNum = 649;BA.debugLine="If DBaseFunctions.HasAssignment(GlobalVar.UserID";
if (mostCurrent._dbasefunctions._hasassignment /*boolean*/ (mostCurrent.activityBA,mostCurrent._globalvar._userid /*int*/ )==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 650;BA.debugLine="pnlPumpSelection.Visible = True";
mostCurrent._pnlpumpselection.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 651;BA.debugLine="ShowAssignedPump(GlobalVar.UserID)";
_showassignedpump(mostCurrent._globalvar._userid /*int*/ );
 }else {
 //BA.debugLineNum = 653;BA.debugLine="pnlPumpSelection.Visible = False";
mostCurrent._pnlpumpselection.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 654;BA.debugLine="MyToast.DefaultTextColor = Colors.White";
mostCurrent._mytoast._defaulttextcolor /*int*/  = anywheresoftware.b4a.keywords.Common.Colors.White;
 //BA.debugLineNum = 655;BA.debugLine="MyToast.pnl.Color = GlobalVar.RedColor";
mostCurrent._mytoast._pnl /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor((int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 656;BA.debugLine="MyFunctions.MyToastMsg(MyToast, $\"You don't hav";
mostCurrent._myfunctions._mytoastmsg /*String*/ (mostCurrent.activityBA,mostCurrent._mytoast,("You don't have any Pump Assignment!"));
 };
 };
 //BA.debugLineNum = 659;BA.debugLine="SaveUserData";
_saveuserdata();
 //BA.debugLineNum = 661;BA.debugLine="End Sub";
return "";
}
public static String  _showjocat() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsjocat = null;
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
String _jodesc = "";
String[] _jodesclist = null;
int _pcount = 0;
int _i = 0;
 //BA.debugLineNum = 1034;BA.debugLine="Private Sub ShowJOCat";
 //BA.debugLineNum = 1035;BA.debugLine="Dim rsJOCat As Cursor";
_rsjocat = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 1036;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 1037;BA.debugLine="Dim JODesc As String";
_jodesc = "";
 //BA.debugLineNum = 1038;BA.debugLine="Dim JODescList() As String";
_jodesclist = new String[(int) (0)];
java.util.Arrays.fill(_jodesclist,"");
 //BA.debugLineNum = 1039;BA.debugLine="Dim pCount As Int";
_pcount = 0;
 //BA.debugLineNum = 1041;BA.debugLine="Try";
try { //BA.debugLineNum = 1042;BA.debugLine="Starter.strCriteria = \"SELECT * FROM constant_jo";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM constant_jo_categories WHERE jo_code <> '"+"RM"+"' "+"AND jo_code <> '"+"CAC"+"' "+"ORDER BY id ASC";
 //BA.debugLineNum = 1046;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("082444300",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 1048;BA.debugLine="rsJOCat =  Starter.DBCon.ExecQuery (Starter.strC";
_rsjocat = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 1049;BA.debugLine="If rsJOCat.RowCount > 0 Then";
if (_rsjocat.getRowCount()>0) { 
 //BA.debugLineNum = 1050;BA.debugLine="pCount = rsJOCat.RowCount";
_pcount = _rsjocat.getRowCount();
 //BA.debugLineNum = 1051;BA.debugLine="Dim JODescList(pCount) As String";
_jodesclist = new String[_pcount];
java.util.Arrays.fill(_jodesclist,"");
 //BA.debugLineNum = 1053;BA.debugLine="For i = 0 To rsJOCat.RowCount - 1";
{
final int step13 = 1;
final int limit13 = (int) (_rsjocat.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit13 ;_i = _i + step13 ) {
 //BA.debugLineNum = 1054;BA.debugLine="rsJOCat.Position = i";
_rsjocat.setPosition(_i);
 //BA.debugLineNum = 1055;BA.debugLine="JODesc = rsJOCat.GetString(\"jo_desc\")";
_jodesc = _rsjocat.GetString("jo_desc");
 //BA.debugLineNum = 1056;BA.debugLine="JODescList(i) = JODesc";
_jodesclist[_i] = _jodesc;
 }
};
 }else {
 //BA.debugLineNum = 1059;BA.debugLine="snack.Initialize(\"\", Activity, \"No List of Job";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),"No List of Job Order found!",mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 1060;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 1061;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1062;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 1063;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e26) {
			processBA.setLastException(e26); //BA.debugLineNum = 1066;BA.debugLine="snack.Initialize(\"\", Activity, LastException,sna";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 1067;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 1068;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1069;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 1070;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1074;BA.debugLine="csTitle.Initialize.Color(GlobalVar.PriColor).Bold";
_cstitle.Initialize().Color((int) (mostCurrent._globalvar._pricolor /*double*/ )).Bold().Size((int) (16)).Append(BA.ObjectToCharSequence(("SELECT J.O. Category "))).PopAll();
 //BA.debugLineNum = 1075;BA.debugLine="MatDialogBuilder.Initialize(\"JOCat\")";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"JOCat");
 //BA.debugLineNum = 1076;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 1078;BA.debugLine="MatDialogBuilder.Items(JODescList)";
mostCurrent._matdialogbuilder.Items((java.lang.CharSequence[])(_jodesclist));
 //BA.debugLineNum = 1079;BA.debugLine="MatDialogBuilder.PositiveText($\"SELECT\"$).Positiv";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence(("SELECT"))).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 1080;BA.debugLine="MatDialogBuilder.NegativeText($\"CANCEL\"$).Negativ";
mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence(("CANCEL"))).NegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ ));
 //BA.debugLineNum = 1081;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1082;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1083;BA.debugLine="MatDialogBuilder.itemsCallbackSingleChoice(0)";
mostCurrent._matdialogbuilder.ItemsCallbackSingleChoice((int) (0));
 //BA.debugLineNum = 1084;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 1085;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 1086;BA.debugLine="End Sub";
return "";
}
public static String  _showjoreasons(String _sjocatcode) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsjocat = null;
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
String _joreason = "";
String[] _joreasonlist = null;
int _pcount = 0;
int _i = 0;
 //BA.debugLineNum = 1171;BA.debugLine="Private Sub ShowJOReasons (sJOCatCode As String)";
 //BA.debugLineNum = 1172;BA.debugLine="Dim rsJOCat As Cursor";
_rsjocat = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 1173;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 1174;BA.debugLine="Dim JOReason As String";
_joreason = "";
 //BA.debugLineNum = 1175;BA.debugLine="Dim JOReasonList() As String";
_joreasonlist = new String[(int) (0)];
java.util.Arrays.fill(_joreasonlist,"");
 //BA.debugLineNum = 1176;BA.debugLine="Dim pCount As Int";
_pcount = 0;
 //BA.debugLineNum = 1178;BA.debugLine="Try";
try { //BA.debugLineNum = 1179;BA.debugLine="Starter.strCriteria = \"SELECT * FROM constant_jo";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM constant_jo_reasons "+"WHERE cat_code = '"+_sjocatcode+"' "+"ORDER BY id ASC";
 //BA.debugLineNum = 1183;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("082706444",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 1185;BA.debugLine="rsJOCat =  Starter.DBCon.ExecQuery (Starter.strC";
_rsjocat = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 1186;BA.debugLine="If rsJOCat.RowCount > 0 Then";
if (_rsjocat.getRowCount()>0) { 
 //BA.debugLineNum = 1187;BA.debugLine="pCount = rsJOCat.RowCount";
_pcount = _rsjocat.getRowCount();
 //BA.debugLineNum = 1188;BA.debugLine="Dim JOReasonList(pCount) As String";
_joreasonlist = new String[_pcount];
java.util.Arrays.fill(_joreasonlist,"");
 //BA.debugLineNum = 1190;BA.debugLine="For i = 0 To rsJOCat.RowCount - 1";
{
final int step13 = 1;
final int limit13 = (int) (_rsjocat.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit13 ;_i = _i + step13 ) {
 //BA.debugLineNum = 1191;BA.debugLine="rsJOCat.Position = i";
_rsjocat.setPosition(_i);
 //BA.debugLineNum = 1192;BA.debugLine="JOReason = rsJOCat.GetString(\"reason_desc\")";
_joreason = _rsjocat.GetString("reason_desc");
 //BA.debugLineNum = 1193;BA.debugLine="JOReasonList(i) = JOReason";
_joreasonlist[_i] = _joreason;
 }
};
 }else {
 //BA.debugLineNum = 1196;BA.debugLine="snack.Initialize(\"\", Activity, \"No JO Reason fo";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),"No JO Reason found!",mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 1197;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 1198;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1199;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 1200;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e26) {
			processBA.setLastException(e26); //BA.debugLineNum = 1203;BA.debugLine="snack.Initialize(\"\", Activity, LastException,sna";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 1204;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 1205;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1206;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 1207;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1211;BA.debugLine="csTitle.Initialize.Color(GlobalVar.PriColor).Bold";
_cstitle.Initialize().Color((int) (mostCurrent._globalvar._pricolor /*double*/ )).Bold().Size((int) (16)).Append(BA.ObjectToCharSequence(("SELECT J.O. Sub Category "))).PopAll();
 //BA.debugLineNum = 1212;BA.debugLine="MatDialogBuilder.Initialize(\"JOReason\")";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"JOReason");
 //BA.debugLineNum = 1213;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 1215;BA.debugLine="MatDialogBuilder.Items(JOReasonList)";
mostCurrent._matdialogbuilder.Items((java.lang.CharSequence[])(_joreasonlist));
 //BA.debugLineNum = 1216;BA.debugLine="MatDialogBuilder.PositiveText($\"SELECT\"$).Positiv";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence(("SELECT"))).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 1217;BA.debugLine="MatDialogBuilder.NegativeText($\"CANCEL\"$).Negativ";
mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence(("CANCEL"))).NegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ ));
 //BA.debugLineNum = 1218;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1219;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1220;BA.debugLine="MatDialogBuilder.itemsCallbackSingleChoice(0)";
mostCurrent._matdialogbuilder.ItemsCallbackSingleChoice((int) (0));
 //BA.debugLineNum = 1221;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 1222;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 1223;BA.debugLine="End Sub";
return "";
}
public static String  _showpumplist(double _iuserid) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rspumps = null;
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
String _pumps = "";
String[] _pumplist = null;
int _pcount = 0;
int _i = 0;
 //BA.debugLineNum = 931;BA.debugLine="Private Sub ShowPumpList (iUserID As Double)";
 //BA.debugLineNum = 932;BA.debugLine="Dim rsPumps As Cursor";
_rspumps = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 933;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 934;BA.debugLine="Dim Pumps As String";
_pumps = "";
 //BA.debugLineNum = 935;BA.debugLine="Dim PumpList() As String";
_pumplist = new String[(int) (0)];
java.util.Arrays.fill(_pumplist,"");
 //BA.debugLineNum = 936;BA.debugLine="Dim pCount As Int";
_pcount = 0;
 //BA.debugLineNum = 938;BA.debugLine="Try";
try { //BA.debugLineNum = 939;BA.debugLine="Starter.strCriteria = \"SELECT Assignment.Station";
mostCurrent._starter._strcriteria /*String*/  = "SELECT Assignment.StationID, PumpStation.PumpHouseCode "+"FROM tblAssignedStation AS Assignment "+"INNER JOIN tblPumpStation AS PumpStation ON Assignment.StationID = PumpStation.StationID "+"WHERE Assignment.OpID = "+BA.NumberToString(_iuserid)+" "+"ORDER BY PumpStation.StationID ASC		";
 //BA.debugLineNum = 945;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("082051086",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 947;BA.debugLine="rsPumps =  Starter.DBCon.ExecQuery (Starter.strC";
_rspumps = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 948;BA.debugLine="If rsPumps.RowCount > 0 Then";
if (_rspumps.getRowCount()>0) { 
 //BA.debugLineNum = 949;BA.debugLine="pCount = rsPumps.RowCount";
_pcount = _rspumps.getRowCount();
 //BA.debugLineNum = 950;BA.debugLine="Dim PumpList(pCount) As String";
_pumplist = new String[_pcount];
java.util.Arrays.fill(_pumplist,"");
 //BA.debugLineNum = 952;BA.debugLine="For i = 0 To rsPumps.RowCount - 1";
{
final int step13 = 1;
final int limit13 = (int) (_rspumps.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit13 ;_i = _i + step13 ) {
 //BA.debugLineNum = 953;BA.debugLine="rsPumps.Position = i";
_rspumps.setPosition(_i);
 //BA.debugLineNum = 954;BA.debugLine="Pumps = rsPumps.GetString(\"PumpHouseCode\")";
_pumps = _rspumps.GetString("PumpHouseCode");
 //BA.debugLineNum = 955;BA.debugLine="PumpList(i) = Pumps";
_pumplist[_i] = _pumps;
 }
};
 }else {
 //BA.debugLineNum = 958;BA.debugLine="snack.Initialize(\"\", Activity, \"No Assigned Pum";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),"No Assigned Pump(s) found!",mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 959;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 960;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 961;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 962;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e26) {
			processBA.setLastException(e26); //BA.debugLineNum = 965;BA.debugLine="snack.Initialize(\"\", Activity, LastException,sna";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 966;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 967;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 968;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 969;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 972;BA.debugLine="csTitle.Initialize.Color(GlobalVar.PriColor).Bold";
_cstitle.Initialize().Color((int) (mostCurrent._globalvar._pricolor /*double*/ )).Bold().Size((int) (16)).Append(BA.ObjectToCharSequence(("SELECT A PUMP"))).PopAll();
 //BA.debugLineNum = 973;BA.debugLine="MatDialogBuilder.Initialize(\"SelectedPump\")";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"SelectedPump");
 //BA.debugLineNum = 974;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 975;BA.debugLine="MatDialogBuilder.Items(PumpList)";
mostCurrent._matdialogbuilder.Items((java.lang.CharSequence[])(_pumplist));
 //BA.debugLineNum = 976;BA.debugLine="MatDialogBuilder.PositiveText($\"SELECT\"$).Positiv";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence(("SELECT"))).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 977;BA.debugLine="MatDialogBuilder.NegativeText($\"CANCEL\"$).Negativ";
mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence(("CANCEL"))).NegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ ));
 //BA.debugLineNum = 978;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 979;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 980;BA.debugLine="MatDialogBuilder.itemsCallbackSingleChoice(0)";
mostCurrent._matdialogbuilder.ItemsCallbackSingleChoice((int) (0));
 //BA.debugLineNum = 981;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 982;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 983;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _showresolutiondialog(uk.co.martinpearman.b4a.fusedlocationprovider.LocationSettingsStatus _settingsstatus) throws Exception{
ResumableSub_ShowResolutionDialog rsub = new ResumableSub_ShowResolutionDialog(null,_settingsstatus);
rsub.resume(processBA, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_ShowResolutionDialog extends BA.ResumableSub {
public ResumableSub_ShowResolutionDialog(bwsi.PumpOperations.mainscreen parent,uk.co.martinpearman.b4a.fusedlocationprovider.LocationSettingsStatus _settingsstatus) {
this.parent = parent;
this._settingsstatus = _settingsstatus;
}
bwsi.PumpOperations.mainscreen parent;
uk.co.martinpearman.b4a.fusedlocationprovider.LocationSettingsStatus _settingsstatus;
boolean _locationsettingsupdated = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = -1;
 //BA.debugLineNum = 1457;BA.debugLine="SettingsStatus.StartResolutionDialog(\"srd\")";
_settingsstatus.StartResolutionDialog(mostCurrent.activityBA,"srd");
 //BA.debugLineNum = 1458;BA.debugLine="Wait For srd_ResolutionDialogDismissed(LocationSe";
anywheresoftware.b4a.keywords.Common.WaitFor("srd_resolutiondialogdismissed", processBA, this, null);
this.state = 1;
return;
case 1:
//C
this.state = -1;
_locationsettingsupdated = (Boolean) result[0];
;
 //BA.debugLineNum = 1459;BA.debugLine="Return LocationSettingsUpdated";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(_locationsettingsupdated));return;};
 //BA.debugLineNum = 1460;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _srd_resolutiondialogdismissed(boolean _locationsettingsupdated) throws Exception{
}
public static String  _showrmjo() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rsjocat = null;
anywheresoftware.b4a.objects.CSBuilder _cstitle = null;
String _joreason = "";
String[] _joreasonlist = null;
int _pcount = 0;
int _i = 0;
 //BA.debugLineNum = 747;BA.debugLine="Private Sub ShowRMJO";
 //BA.debugLineNum = 748;BA.debugLine="Dim rsJOCat As Cursor";
_rsjocat = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 749;BA.debugLine="Dim csTitle As CSBuilder";
_cstitle = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 750;BA.debugLine="Dim JOReason As String";
_joreason = "";
 //BA.debugLineNum = 751;BA.debugLine="Dim JOReasonList() As String";
_joreasonlist = new String[(int) (0)];
java.util.Arrays.fill(_joreasonlist,"");
 //BA.debugLineNum = 752;BA.debugLine="Dim pCount As Int";
_pcount = 0;
 //BA.debugLineNum = 754;BA.debugLine="Try";
try { //BA.debugLineNum = 755;BA.debugLine="Starter.strCriteria = \"SELECT * FROM constant_jo";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM constant_jo_reasons "+"WHERE cat_code = '"+"RM"+"' "+"ORDER BY id ASC";
 //BA.debugLineNum = 759;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("081723404",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 761;BA.debugLine="rsJOCat =  Starter.DBCon.ExecQuery (Starter.strC";
_rsjocat = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 762;BA.debugLine="If rsJOCat.RowCount > 0 Then";
if (_rsjocat.getRowCount()>0) { 
 //BA.debugLineNum = 763;BA.debugLine="pCount = rsJOCat.RowCount";
_pcount = _rsjocat.getRowCount();
 //BA.debugLineNum = 764;BA.debugLine="Dim JOReasonList(pCount) As String";
_joreasonlist = new String[_pcount];
java.util.Arrays.fill(_joreasonlist,"");
 //BA.debugLineNum = 766;BA.debugLine="For i = 0 To rsJOCat.RowCount - 1";
{
final int step13 = 1;
final int limit13 = (int) (_rsjocat.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit13 ;_i = _i + step13 ) {
 //BA.debugLineNum = 767;BA.debugLine="rsJOCat.Position = i";
_rsjocat.setPosition(_i);
 //BA.debugLineNum = 768;BA.debugLine="JOReason = rsJOCat.GetString(\"reason_desc\")";
_joreason = _rsjocat.GetString("reason_desc");
 //BA.debugLineNum = 769;BA.debugLine="JOReasonList(i) = JOReason";
_joreasonlist[_i] = _joreason;
 }
};
 }else {
 //BA.debugLineNum = 772;BA.debugLine="snack.Initialize(\"\", Activity, \"No JO Reason fo";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),"No JO Reason found!",mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 773;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 774;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 775;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 776;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e26) {
			processBA.setLastException(e26); //BA.debugLineNum = 779;BA.debugLine="snack.Initialize(\"\", Activity, LastException,sna";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 780;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, GlobalV";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 781;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.W";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 782;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 783;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 787;BA.debugLine="csTitle.Initialize.Color(GlobalVar.PriColor).Bold";
_cstitle.Initialize().Color((int) (mostCurrent._globalvar._pricolor /*double*/ )).Bold().Size((int) (16)).Append(BA.ObjectToCharSequence(("SELECT Repair and Maintenance Category "))).PopAll();
 //BA.debugLineNum = 788;BA.debugLine="MatDialogBuilder.Initialize(\"RMJO\")";
mostCurrent._matdialogbuilder.Initialize(mostCurrent.activityBA,"RMJO");
 //BA.debugLineNum = 789;BA.debugLine="MatDialogBuilder.Title(csTitle)";
mostCurrent._matdialogbuilder.Title(BA.ObjectToCharSequence(_cstitle.getObject()));
 //BA.debugLineNum = 790;BA.debugLine="MatDialogBuilder.Items(JOReasonList)";
mostCurrent._matdialogbuilder.Items((java.lang.CharSequence[])(_joreasonlist));
 //BA.debugLineNum = 791;BA.debugLine="MatDialogBuilder.PositiveText($\"SELECT\"$).Positiv";
mostCurrent._matdialogbuilder.PositiveText(BA.ObjectToCharSequence(("SELECT"))).PositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ ));
 //BA.debugLineNum = 792;BA.debugLine="MatDialogBuilder.NegativeText($\"CANCEL\"$).Negativ";
mostCurrent._matdialogbuilder.NegativeText(BA.ObjectToCharSequence(("CANCEL"))).NegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ ));
 //BA.debugLineNum = 793;BA.debugLine="MatDialogBuilder.Cancelable(False)";
mostCurrent._matdialogbuilder.Cancelable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 794;BA.debugLine="MatDialogBuilder.CanceledOnTouchOutside(False)";
mostCurrent._matdialogbuilder.CanceledOnTouchOutside(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 795;BA.debugLine="MatDialogBuilder.itemsCallbackSingleChoice(0)";
mostCurrent._matdialogbuilder.ItemsCallbackSingleChoice((int) (0));
 //BA.debugLineNum = 796;BA.debugLine="MatDialogBuilder.Theme(MatDialogBuilder.THEME_LIG";
mostCurrent._matdialogbuilder.Theme(mostCurrent._matdialogbuilder.THEME_LIGHT);
 //BA.debugLineNum = 797;BA.debugLine="MatDialogBuilder.Show";
mostCurrent._matdialogbuilder.Show();
 //BA.debugLineNum = 798;BA.debugLine="End Sub";
return "";
}
public static String  _showwelcomedialog() throws Exception{
String _strandate = "";
com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder _alert = null;
 //BA.debugLineNum = 579;BA.debugLine="Private Sub ShowWelcomeDialog()";
 //BA.debugLineNum = 580;BA.debugLine="Dim theDate As Long";
_thedate = 0L;
 //BA.debugLineNum = 581;BA.debugLine="Dim sTranDate As String";
_strandate = "";
 //BA.debugLineNum = 583;BA.debugLine="theDate = DateTime.DateParse(DateTime.Date(DateTi";
_thedate = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 584;BA.debugLine="DateTime.DateFormat = \"MMM. dd, yyyy\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("MMM. dd, yyyy");
 //BA.debugLineNum = 585;BA.debugLine="sTranDate = DateTime.Date(theDate)";
_strandate = anywheresoftware.b4a.keywords.Common.DateTime.Date(_thedate);
 //BA.debugLineNum = 587;BA.debugLine="Dim Alert As AX_CustomAlertDialog";
_alert = new com.aghajari.ax_customalertviewdialog.AX_CustomAlertDialogBuilder();
 //BA.debugLineNum = 589;BA.debugLine="Alert.Initialize.Create _ 			.SetDialogStyleName(";
_alert.Initialize().Create(mostCurrent.activityBA).SetDialogStyleName("MyDialogDisableStatus").SetStyle(_alert.getSTYLE_DIALOGUE()).SetCancelable(anywheresoftware.b4a.keywords.Common.False).SetTitle(("W E L C O M E")).SetMessage(("Welcome to BWSI's Operations App!")+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+("Current Date is ")+_strandate).SetPositiveText("OK").SetPositiveColor((int) (mostCurrent._globalvar._poscolor /*double*/ )).SetPositiveTypeface((android.graphics.Typeface)(mostCurrent._fontbold.getObject())).SetNegativeText("SET DATE").SetNegativeColor((int) (mostCurrent._globalvar._negcolor /*double*/ )).SetNegativeTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetTitleTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetMessageTypeface((android.graphics.Typeface)(mostCurrent._font.getObject())).SetOnPositiveClicked(mostCurrent.activityBA,"ShowDate").SetOnNegativeClicked(mostCurrent.activityBA,"ShowDate").SetOnViewBinder(mostCurrent.activityBA,"FontSizeBinder");
 //BA.debugLineNum = 607;BA.debugLine="Alert.SetDialogBackground(MyFunctions.myCD)";
_alert.SetDialogBackground((android.graphics.drawable.Drawable)(mostCurrent._myfunctions._mycd /*anywheresoftware.b4a.objects.drawable.ColorDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 608;BA.debugLine="Alert.Build.Show";
_alert.Build().Show();
 //BA.debugLineNum = 609;BA.debugLine="End Sub";
return "";
}
public static String  _timer1_tick() throws Exception{
 //BA.debugLineNum = 1463;BA.debugLine="Private Sub Timer1_Tick";
 //BA.debugLineNum = 1464;BA.debugLine="If NotifyCountJO = True Then";
if (_notifycountjo()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1465;BA.debugLine="UpdateBadge(\"Notif\", AddBadgeToIcon(NotifBMP, iJ";
_updatebadge("Notif",_addbadgetoicon(_notifbmp,_ijounreadcount));
 };
 //BA.debugLineNum = 1467;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_menuitemclick(de.amberhome.objects.appcompat.ACMenuItemWrapper _item) throws Exception{
long _ldate = 0L;
String _sdate = "";
 //BA.debugLineNum = 251;BA.debugLine="Sub ToolBar_MenuItemClick (Item As ACMenuItem)";
 //BA.debugLineNum = 252;BA.debugLine="Select Case Item.Id";
switch (BA.switchObjectToInt(_item.getId(),(int) (1),(int) (2),(int) (3),(int) (4))) {
case 0: {
 //BA.debugLineNum = 254;BA.debugLine="Dim lDate As Long";
_ldate = 0L;
 //BA.debugLineNum = 255;BA.debugLine="Dim sDate As String";
_sdate = "";
 //BA.debugLineNum = 257;BA.debugLine="lDate = DateTime.DateParse(GlobalVar.TranDate)";
_ldate = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(mostCurrent._globalvar._trandate /*String*/ );
 //BA.debugLineNum = 258;BA.debugLine="DtDialog.Initialize(Activity, lDate)";
mostCurrent._dtdialog._initialize /*String*/ (mostCurrent.activityBA,mostCurrent._activity,_ldate);
 //BA.debugLineNum = 259;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 260;BA.debugLine="theDate = DtDialog.Show($\"Select New Transactio";
_thedate = (long) (mostCurrent._dtdialog._show /*int*/ (("Select New Transaction Date")));
 //BA.debugLineNum = 262;BA.debugLine="If theDate = DialogResponse.POSITIVE Then";
if (_thedate==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 263;BA.debugLine="sDate = DtDialog.DateSelected";
_sdate = BA.NumberToString(mostCurrent._dtdialog._dateselected /*long*/ );
 //BA.debugLineNum = 265;BA.debugLine="RemoveTranDate";
_removetrandate();
 //BA.debugLineNum = 266;BA.debugLine="GlobalVar.TranDate = DateTime.Date(sDate)";
mostCurrent._globalvar._trandate /*String*/  = anywheresoftware.b4a.keywords.Common.DateTime.Date((long)(Double.parseDouble(_sdate)));
 //BA.debugLineNum = 267;BA.debugLine="snack.Initialize(\"\",Activity,$\"Selected Transa";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),("Selected Transaction Date is ")+mostCurrent._globalvar._trandate /*String*/ ,mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 268;BA.debugLine="MyFunctions.SetSnackBarBackground(snack,Global";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._greencolor /*double*/ ));
 //BA.debugLineNum = 269;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 270;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 //BA.debugLineNum = 271;BA.debugLine="SaveTranDate";
_savetrandate();
 }else {
 //BA.debugLineNum = 273;BA.debugLine="snack.Initialize(\"\",Activity,$\"Setting Transac";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"",(android.view.View)(mostCurrent._activity.getObject()),("Setting Transaction Date Cancelled"),mostCurrent._snack.DURATION_LONG);
 //BA.debugLineNum = 274;BA.debugLine="MyFunctions.SetSnackBarBackground(snack,Colors";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 275;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Global";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 276;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 };
 //BA.debugLineNum = 278;BA.debugLine="lblTranDate.Text =$\"TRANSACTION DATE: \"$ & Glob";
mostCurrent._lbltrandate.setText(BA.ObjectToCharSequence(("TRANSACTION DATE: ")+mostCurrent._globalvar._trandate /*String*/ ));
 break; }
case 1: {
 //BA.debugLineNum = 280;BA.debugLine="pnlPumpSelection.Visible = True";
mostCurrent._pnlpumpselection.setVisible(anywheresoftware.b4a.keywords.Common.True);
 break; }
case 2: {
 //BA.debugLineNum = 282;BA.debugLine="StartActivity(actJONotification)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actjonotification.getObject()));
 break; }
case 3: {
 //BA.debugLineNum = 284;BA.debugLine="vibration.vibrateOnce(1000)";
mostCurrent._vibration.vibrateOnce(processBA,(long) (1000));
 //BA.debugLineNum = 285;BA.debugLine="snack.Initialize(\"LogOFF\", Activity, $\"Sure to";
mostCurrent._snack.Initialize(mostCurrent.activityBA,"LogOFF",(android.view.View)(mostCurrent._activity.getObject()),("Sure to Log Off now?"),mostCurrent._snack.DURATION_SHORT);
 //BA.debugLineNum = 286;BA.debugLine="MyFunctions.SetSnackBarBackground(snack, Global";
mostCurrent._myfunctions._setsnackbarbackground /*String*/ (mostCurrent.activityBA,mostCurrent._snack,(int) (mostCurrent._globalvar._redcolor /*double*/ ));
 //BA.debugLineNum = 287;BA.debugLine="MyFunctions.SetSnackBarTextColor(snack, Colors.";
mostCurrent._myfunctions._setsnackbartextcolor /*String*/ (mostCurrent.activityBA,mostCurrent._snack,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 288;BA.debugLine="snack.SetAction(csAns)";
mostCurrent._snack.SetAction(BA.ObjectToString(mostCurrent._csans));
 //BA.debugLineNum = 289;BA.debugLine="snack.Show";
mostCurrent._snack.Show();
 break; }
}
;
 //BA.debugLineNum = 291;BA.debugLine="End Sub";
return "";
}
public static String  _toolbar_navigationitemclick() throws Exception{
 //BA.debugLineNum = 244;BA.debugLine="Sub ToolBar_NavigationItemClick";
 //BA.debugLineNum = 248;BA.debugLine="Drawer.LeftOpen = Not(Drawer.LeftOpen)";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._drawer._getleftopen /*boolean*/ ()));
 //BA.debugLineNum = 249;BA.debugLine="End Sub";
return "";
}
public static String  _updatebadge(String _menutitle,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _icon) throws Exception{
de.amberhome.objects.appcompat.ACMenuItemWrapper _m = null;
 //BA.debugLineNum = 553;BA.debugLine="Sub UpdateBadge(MenuTitle As String, Icon As Bitma";
 //BA.debugLineNum = 554;BA.debugLine="Dim m As ACMenuItem = GetMenuItem(MenuTitle)";
_m = new de.amberhome.objects.appcompat.ACMenuItemWrapper();
_m = _getmenuitem(_menutitle);
 //BA.debugLineNum = 555;BA.debugLine="If m.IsInitialized = False Then";
if (_m.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 };
 //BA.debugLineNum = 557;BA.debugLine="m.Icon = BitmapToBitmapDrawable(Icon)";
_m.setIcon((android.graphics.drawable.Drawable)(_bitmaptobitmapdrawable(_icon).getObject()));
 //BA.debugLineNum = 558;BA.debugLine="End Sub";
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
