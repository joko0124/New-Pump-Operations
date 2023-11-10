package bwsi.PumpOperations;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class clvswipe extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "bwsi.PumpOperations.clvswipe");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", bwsi.PumpOperations.clvswipe.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public b4a.example3.customlistview _mclv = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _base = null;
public float _touchxstart = 0f;
public float _touchystart = 0f;
public boolean _handlingswipe = false;
public anywheresoftware.b4a.objects.B4XViewWrapper _actionspanel = null;
public int _lastswipeditem = 0;
public anywheresoftware.b4a.objects.collections.Map _actioncolors = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public anywheresoftware.b4a.objects.B4XCanvas _cvs = null;
public Object _mcallback = null;
public String _meventname = "";
public anywheresoftware.b4a.objects.B4XViewWrapper _mpulltorefreshpanel = null;
public boolean _pulltorefreshswipe = false;
public boolean _waitingforrefreshtocomplete = false;
public boolean _mscrollingdisabled = false;
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
public static class _swipeitem{
public boolean IsInitialized;
public Object Value;
public anywheresoftware.b4a.objects.collections.List Actions;
public boolean IsSwiped;
public int MaxSwipe;
public boolean Open;
public void Initialize() {
IsInitialized = true;
Value = new Object();
Actions = new anywheresoftware.b4a.objects.collections.List();
IsSwiped = false;
MaxSwipe = 0;
Open = false;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public boolean  _changeoffset(int _index,int _dx,boolean _complete) throws Exception{
b4a.example3.customlistview._clvitem _item = null;
bwsi.PumpOperations.clvswipe._swipeitem _m = null;
int _newleft = 0;
 //BA.debugLineNum = 55;BA.debugLine="Private Sub ChangeOffset(index As Int, dx As Int,";
 //BA.debugLineNum = 56;BA.debugLine="If index < 0 Or index >= mCLV.Size Then Return Fa";
if (_index<0 || _index>=_mclv._getsize()) { 
if (true) return __c.False;};
 //BA.debugLineNum = 57;BA.debugLine="Dim item As CLVItem = mCLV.GetRawListItem(index)";
_item = _mclv._getrawlistitem(_index);
 //BA.debugLineNum = 58;BA.debugLine="If Not(item.Value Is SwipeItem) Then Return False";
if (__c.Not(_item.Value instanceof bwsi.PumpOperations.clvswipe._swipeitem)) { 
if (true) return __c.False;};
 //BA.debugLineNum = 59;BA.debugLine="Dim m As SwipeItem = item.Value";
_m = (bwsi.PumpOperations.clvswipe._swipeitem)(_item.Value);
 //BA.debugLineNum = 60;BA.debugLine="If m.IsSwiped = False And complete = True Then Re";
if (_m.IsSwiped /*boolean*/ ==__c.False && _complete==__c.True) { 
if (true) return __c.False;};
 //BA.debugLineNum = 61;BA.debugLine="If m.IsSwiped = False Then";
if (_m.IsSwiped /*boolean*/ ==__c.False) { 
 //BA.debugLineNum = 62;BA.debugLine="If m.Actions.IsInitialized And m.Actions.Size >";
if (_m.Actions /*anywheresoftware.b4a.objects.collections.List*/ .IsInitialized() && _m.Actions /*anywheresoftware.b4a.objects.collections.List*/ .getSize()>0) { 
 //BA.debugLineNum = 63;BA.debugLine="m.IsSwiped = True";
_m.IsSwiped /*boolean*/  = __c.True;
 //BA.debugLineNum = 64;BA.debugLine="If ActionsPanel.Parent.IsInitialized Then Actio";
if (_actionspanel.getParent().IsInitialized()) { 
_actionspanel.RemoveViewFromParent();};
 //BA.debugLineNum = 65;BA.debugLine="item.Panel.Parent.AddView(ActionsPanel, 0, item";
_item.Panel.getParent().AddView((android.view.View)(_actionspanel.getObject()),(int) (0),_item.Panel.getTop(),_item.Panel.getWidth(),_item.Panel.getHeight());
 //BA.debugLineNum = 66;BA.debugLine="ActionsPanel.SendToBack";
_actionspanel.SendToBack();
 //BA.debugLineNum = 67;BA.debugLine="m.MaxSwipe = CreateActionButtons(m.Actions)";
_m.MaxSwipe /*int*/  = _createactionbuttons(_m.Actions /*anywheresoftware.b4a.objects.collections.List*/ );
 //BA.debugLineNum = 68;BA.debugLine="LastSwipedItem = index";
_lastswipeditem = _index;
 }else {
 //BA.debugLineNum = 70;BA.debugLine="Return False";
if (true) return __c.False;
 };
 };
 //BA.debugLineNum = 73;BA.debugLine="Dim NewLeft As Int = Max(Min(item.Panel.Left + dx";
_newleft = (int) (__c.Max(__c.Min(_item.Panel.getLeft()+_dx,0),-_m.MaxSwipe /*int*/ ));
 //BA.debugLineNum = 74;BA.debugLine="If complete Then";
if (_complete) { 
 //BA.debugLineNum = 75;BA.debugLine="If (m.Open = False And NewLeft >= -40dip) Or (m.";
if ((_m.Open /*boolean*/ ==__c.False && _newleft>=-__c.DipToCurrent((int) (40))) || (_m.Open /*boolean*/ ==__c.True && _newleft>=-_m.MaxSwipe /*int*/ +__c.DipToCurrent((int) (10)))) { 
 //BA.debugLineNum = 76;BA.debugLine="item.Panel.SetLayoutAnimated(200, 0, item.Panel";
_item.Panel.SetLayoutAnimated((int) (200),(int) (0),_item.Panel.getTop(),_item.Panel.getWidth(),_item.Panel.getHeight());
 //BA.debugLineNum = 77;BA.debugLine="m.IsSwiped = False";
_m.IsSwiped /*boolean*/  = __c.False;
 //BA.debugLineNum = 78;BA.debugLine="m.Open = False";
_m.Open /*boolean*/  = __c.False;
 //BA.debugLineNum = 79;BA.debugLine="ActionsPanel.RemoveViewFromParent";
_actionspanel.RemoveViewFromParent();
 }else {
 //BA.debugLineNum = 81;BA.debugLine="item.Panel.SetLayoutAnimated(200, -m.MaxSwipe,";
_item.Panel.SetLayoutAnimated((int) (200),(int) (-_m.MaxSwipe /*int*/ ),_item.Panel.getTop(),_item.Panel.getWidth(),_item.Panel.getHeight());
 //BA.debugLineNum = 82;BA.debugLine="m.Open = True";
_m.Open /*boolean*/  = __c.True;
 };
 }else {
 //BA.debugLineNum = 86;BA.debugLine="item.Panel.Left = NewLeft";
_item.Panel.setLeft(_newleft);
 };
 //BA.debugLineNum = 88;BA.debugLine="Return True";
if (true) return __c.True;
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return false;
}
public String  _changeyoffset(int _dy,boolean _complete) throws Exception{
int _newtop = 0;
 //BA.debugLineNum = 91;BA.debugLine="Private Sub ChangeYOffset(dy As Int, complete As B";
 //BA.debugLineNum = 92;BA.debugLine="If WaitingForRefreshToComplete Then Return";
if (_waitingforrefreshtocomplete) { 
if (true) return "";};
 //BA.debugLineNum = 93;BA.debugLine="Dim NewTop As Int = Min(Max(mCLV.AsView.Top + dy,";
_newtop = (int) (__c.Min(__c.Max(_mclv._asview().getTop()+_dy,0),_mpulltorefreshpanel.getHeight()));
 //BA.debugLineNum = 94;BA.debugLine="mCLV.AsView.Top = NewTop";
_mclv._asview().setTop(_newtop);
 //BA.debugLineNum = 95;BA.debugLine="If NewTop = mPullToRefreshPanel.Height Then";
if (_newtop==_mpulltorefreshpanel.getHeight()) { 
 //BA.debugLineNum = 96;BA.debugLine="RaiseRefreshEvent";
_raiserefreshevent();
 };
 //BA.debugLineNum = 98;BA.debugLine="If complete Then";
if (_complete) { 
 //BA.debugLineNum = 99;BA.debugLine="mCLV.AsView.SetLayoutAnimated(200, 0, 0, mCLV.As";
_mclv._asview().SetLayoutAnimated((int) (200),(int) (0),(int) (0),_mclv._asview().getWidth(),_mclv._asview().getHeight());
 };
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Private mCLV As CustomListView";
_mclv = new b4a.example3.customlistview();
 //BA.debugLineNum = 5;BA.debugLine="Type SwipeItem (Value As Object, Actions As List,";
;
 //BA.debugLineNum = 6;BA.debugLine="Public Base As B4XView";
_base = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private TouchXStart, TouchYStart As Float";
_touchxstart = 0f;
_touchystart = 0f;
 //BA.debugLineNum = 8;BA.debugLine="Private HandlingSwipe As Boolean";
_handlingswipe = false;
 //BA.debugLineNum = 9;BA.debugLine="Private ActionsPanel As B4XView";
_actionspanel = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Private LastSwipedItem As Int = -1";
_lastswipeditem = (int) (-1);
 //BA.debugLineNum = 11;BA.debugLine="Public ActionColors As Map";
_actioncolors = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 12;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 13;BA.debugLine="Private cvs As B4XCanvas";
_cvs = new anywheresoftware.b4a.objects.B4XCanvas();
 //BA.debugLineNum = 14;BA.debugLine="Private mCallback As Object";
_mcallback = new Object();
 //BA.debugLineNum = 15;BA.debugLine="Private mEventName As String";
_meventname = "";
 //BA.debugLineNum = 16;BA.debugLine="Private mPullToRefreshPanel As B4XView";
_mpulltorefreshpanel = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private PullToRefreshSwipe As Boolean 'ignore";
_pulltorefreshswipe = false;
 //BA.debugLineNum = 18;BA.debugLine="Private WaitingForRefreshToComplete As Boolean";
_waitingforrefreshtocomplete = false;
 //BA.debugLineNum = 20;BA.debugLine="Private mScrollingDisabled As Boolean";
_mscrollingdisabled = false;
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public String  _closelastswiped() throws Exception{
 //BA.debugLineNum = 170;BA.debugLine="Public Sub CloseLastSwiped";
 //BA.debugLineNum = 171;BA.debugLine="ChangeOffset(LastSwipedItem, 10000dip, True)";
_changeoffset(_lastswipeditem,__c.DipToCurrent((int) (10000)),__c.True);
 //BA.debugLineNum = 172;BA.debugLine="LastSwipedItem = -1";
_lastswipeditem = (int) (-1);
 //BA.debugLineNum = 173;BA.debugLine="End Sub";
return "";
}
public int  _createactionbuttons(anywheresoftware.b4a.objects.collections.List _actions) throws Exception{
int _lastx = 0;
String _action = "";
anywheresoftware.b4a.objects.LabelWrapper _l = null;
anywheresoftware.b4a.objects.B4XViewWrapper _xlbl = null;
int _width = 0;
 //BA.debugLineNum = 144;BA.debugLine="Private Sub CreateActionButtons (actions As List)";
 //BA.debugLineNum = 145;BA.debugLine="ActionsPanel.RemoveAllViews";
_actionspanel.RemoveAllViews();
 //BA.debugLineNum = 146;BA.debugLine="Dim LastX As Int = 0";
_lastx = (int) (0);
 //BA.debugLineNum = 147;BA.debugLine="For Each action As String In actions";
{
final anywheresoftware.b4a.BA.IterableList group3 = _actions;
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_action = BA.ObjectToString(group3.Get(index3));
 //BA.debugLineNum = 148;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 149;BA.debugLine="l.Initialize(\"lbl\")";
_l.Initialize(ba,"lbl");
 //BA.debugLineNum = 150;BA.debugLine="Dim xlbl As B4XView = l";
_xlbl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_xlbl = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_l.getObject()));
 //BA.debugLineNum = 151;BA.debugLine="xlbl.Text = action";
_xlbl.setText(BA.ObjectToCharSequence(_action));
 //BA.debugLineNum = 152;BA.debugLine="xlbl.Color = ActionColors.GetDefault(action, xui";
_xlbl.setColor((int)(BA.ObjectToNumber(_actioncolors.GetDefault((Object)(_action),(Object)(_xui.Color_White)))));
 //BA.debugLineNum = 153;BA.debugLine="xlbl.SetTextAlignment(\"CENTER\", \"CENTER\")";
_xlbl.SetTextAlignment("CENTER","CENTER");
 //BA.debugLineNum = 154;BA.debugLine="xlbl.Font = xui.CreateDefaultBoldFont(20)";
_xlbl.setFont(_xui.CreateDefaultBoldFont((float) (20)));
 //BA.debugLineNum = 155;BA.debugLine="xlbl.TextColor = xui.Color_Black";
_xlbl.setTextColor(_xui.Color_Black);
 //BA.debugLineNum = 156;BA.debugLine="Dim width As Int = Max(40dip, cvs.MeasureText(ac";
_width = (int) (__c.Max(__c.DipToCurrent((int) (40)),_cvs.MeasureText(_action,_xlbl.getFont()).getWidth()+__c.DipToCurrent((int) (20))));
 //BA.debugLineNum = 157;BA.debugLine="ActionsPanel.AddView(xlbl, ActionsPanel.Width -";
_actionspanel.AddView((android.view.View)(_xlbl.getObject()),(int) (_actionspanel.getWidth()-_width-_lastx),(int) (0),_width,_actionspanel.getHeight());
 //BA.debugLineNum = 158;BA.debugLine="LastX = LastX + width";
_lastx = (int) (_lastx+_width);
 }
};
 //BA.debugLineNum = 160;BA.debugLine="Return LastX";
if (true) return _lastx;
 //BA.debugLineNum = 161;BA.debugLine="End Sub";
return 0;
}
public bwsi.PumpOperations.clvswipe._swipeitem  _createitemvalue(Object _value,anywheresoftware.b4a.objects.collections.List _actions) throws Exception{
bwsi.PumpOperations.clvswipe._swipeitem _m = null;
 //BA.debugLineNum = 47;BA.debugLine="Public Sub CreateItemValue(Value As Object, Action";
 //BA.debugLineNum = 48;BA.debugLine="Dim m As SwipeItem";
_m = new bwsi.PumpOperations.clvswipe._swipeitem();
 //BA.debugLineNum = 49;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 50;BA.debugLine="m.Value = Value";
_m.Value /*Object*/  = _value;
 //BA.debugLineNum = 51;BA.debugLine="m.Actions = Actions";
_m.Actions /*anywheresoftware.b4a.objects.collections.List*/  = _actions;
 //BA.debugLineNum = 52;BA.debugLine="Return m";
if (true) return _m;
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.B4XViewWrapper  _getpulltorefreshpanel() throws Exception{
 //BA.debugLineNum = 193;BA.debugLine="Public Sub getPullToRefreshPanel As B4XView";
 //BA.debugLineNum = 194;BA.debugLine="Return mPullToRefreshPanel";
if (true) return _mpulltorefreshpanel;
 //BA.debugLineNum = 195;BA.debugLine="End Sub";
return null;
}
public boolean  _getscrollingenabled() throws Exception{
 //BA.debugLineNum = 201;BA.debugLine="Public Sub getScrollingEnabled As Boolean";
 //BA.debugLineNum = 202;BA.debugLine="Return Not(mScrollingDisabled)";
if (true) return __c.Not(_mscrollingdisabled);
 //BA.debugLineNum = 203;BA.debugLine="End Sub";
return false;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,b4a.example3.customlistview _clv,Object _callback,String _eventname) throws Exception{
innerInitialize(_ba);
anywheresoftware.b4a.objects.TouchPanelCreator _creator = null;
anywheresoftware.b4a.objects.B4XViewWrapper _p = null;
 //BA.debugLineNum = 24;BA.debugLine="Public Sub Initialize (clv As CustomListView, Call";
 //BA.debugLineNum = 26;BA.debugLine="Dim creator As TouchPanelCreator";
_creator = new anywheresoftware.b4a.objects.TouchPanelCreator();
 //BA.debugLineNum = 27;BA.debugLine="Base = creator.CreateTouchPanel(\"TouchPanel\")";
_base = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_creator.CreateTouchPanel(ba,"TouchPanel").getObject()));
 //BA.debugLineNum = 34;BA.debugLine="ActionsPanel = xui.CreatePanel(\"\")";
_actionspanel = _xui.CreatePanel(ba,"");
 //BA.debugLineNum = 35;BA.debugLine="Dim p As B4XView = xui.CreatePanel(\"\")";
_p = new anywheresoftware.b4a.objects.B4XViewWrapper();
_p = _xui.CreatePanel(ba,"");
 //BA.debugLineNum = 36;BA.debugLine="p.SetLayoutAnimated(0, 0, 0, 1dip, 1dip)";
_p.SetLayoutAnimated((int) (0),(int) (0),(int) (0),__c.DipToCurrent((int) (1)),__c.DipToCurrent((int) (1)));
 //BA.debugLineNum = 37;BA.debugLine="cvs.Initialize(p)";
_cvs.Initialize(_p);
 //BA.debugLineNum = 38;BA.debugLine="mCLV = clv";
_mclv = _clv;
 //BA.debugLineNum = 39;BA.debugLine="mCLV.AsView.Parent.AddView(Base, mCLV.AsView.Left";
_mclv._asview().getParent().AddView((android.view.View)(_base.getObject()),_mclv._asview().getLeft(),_mclv._asview().getTop(),_mclv._asview().getWidth(),_mclv._asview().getHeight());
 //BA.debugLineNum = 40;BA.debugLine="mCLV.AsView.RemoveViewFromParent";
_mclv._asview().RemoveViewFromParent();
 //BA.debugLineNum = 41;BA.debugLine="Base.AddView(mCLV.AsView, 0, 0, Base.Width, Base.";
_base.AddView((android.view.View)(_mclv._asview().getObject()),(int) (0),(int) (0),_base.getWidth(),_base.getHeight());
 //BA.debugLineNum = 42;BA.debugLine="mCallback = Callback";
_mcallback = _callback;
 //BA.debugLineNum = 43;BA.debugLine="mEventName = EventName";
_meventname = _eventname;
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public String  _lbl_click() throws Exception{
anywheresoftware.b4a.objects.B4XViewWrapper _lbl = null;
int _index = 0;
 //BA.debugLineNum = 163;BA.debugLine="Private Sub Lbl_Click";
 //BA.debugLineNum = 164;BA.debugLine="Dim lbl As B4XView = Sender";
_lbl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_lbl = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(__c.Sender(ba)));
 //BA.debugLineNum = 165;BA.debugLine="Dim index As Int = LastSwipedItem";
_index = _lastswipeditem;
 //BA.debugLineNum = 166;BA.debugLine="CloseLastSwiped";
_closelastswiped();
 //BA.debugLineNum = 167;BA.debugLine="CallSub3(mCallback, mEventName & \"_ActionClicked\"";
__c.CallSubNew3(ba,_mcallback,_meventname+"_ActionClicked",(Object)(_index),(Object)(_lbl.getText()));
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return "";
}
public String  _raiserefreshevent() throws Exception{
 //BA.debugLineNum = 103;BA.debugLine="Private Sub RaiseRefreshEvent";
 //BA.debugLineNum = 104;BA.debugLine="WaitingForRefreshToComplete = True";
_waitingforrefreshtocomplete = __c.True;
 //BA.debugLineNum = 105;BA.debugLine="HandlingSwipe = False";
_handlingswipe = __c.False;
 //BA.debugLineNum = 106;BA.debugLine="CallSub(mCallback, mEventName & \"_RefreshRequeste";
__c.CallSubNew(ba,_mcallback,_meventname+"_RefreshRequested");
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}
public String  _refreshcompleted() throws Exception{
 //BA.debugLineNum = 133;BA.debugLine="Public Sub RefreshCompleted";
 //BA.debugLineNum = 134;BA.debugLine="If WaitingForRefreshToComplete = False Then Retur";
if (_waitingforrefreshtocomplete==__c.False) { 
if (true) return "";};
 //BA.debugLineNum = 135;BA.debugLine="WaitingForRefreshToComplete = False";
_waitingforrefreshtocomplete = __c.False;
 //BA.debugLineNum = 136;BA.debugLine="mPullToRefreshPanel.Visible = False";
_mpulltorefreshpanel.setVisible(__c.False);
 //BA.debugLineNum = 137;BA.debugLine="mCLV.AsView.SetLayoutAnimated(200, 0, 0, mCLV.AsV";
_mclv._asview().SetLayoutAnimated((int) (200),(int) (0),(int) (0),_mclv._asview().getWidth(),_mclv._asview().getHeight());
 //BA.debugLineNum = 142;BA.debugLine="End Sub";
return "";
}
public String  _resize(int _width,int _height) throws Exception{
 //BA.debugLineNum = 175;BA.debugLine="Public Sub Resize(Width As Int, Height As Int)";
 //BA.debugLineNum = 176;BA.debugLine="CloseLastSwiped";
_closelastswiped();
 //BA.debugLineNum = 177;BA.debugLine="ActionsPanel.Width = Width";
_actionspanel.setWidth(_width);
 //BA.debugLineNum = 178;BA.debugLine="Base.SetLayoutAnimated(0, Base.Left, Base.Top, Wi";
_base.SetLayoutAnimated((int) (0),_base.getLeft(),_base.getTop(),_width,_height);
 //BA.debugLineNum = 179;BA.debugLine="If mPullToRefreshPanel.IsInitialized Then";
if (_mpulltorefreshpanel.IsInitialized()) { 
 //BA.debugLineNum = 180;BA.debugLine="mPullToRefreshPanel.SetLayoutAnimated(0, 0, 0, W";
_mpulltorefreshpanel.SetLayoutAnimated((int) (0),(int) (0),(int) (0),_width,_mpulltorefreshpanel.getHeight());
 };
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public String  _scrollchanged(int _offset) throws Exception{
 //BA.debugLineNum = 109;BA.debugLine="Public Sub ScrollChanged (offset As Int)";
 //BA.debugLineNum = 131;BA.debugLine="End Sub";
return "";
}
public String  _setpulltorefreshpanel(anywheresoftware.b4a.objects.B4XViewWrapper _pnl) throws Exception{
 //BA.debugLineNum = 184;BA.debugLine="Public Sub setPullToRefreshPanel(pnl As B4XView)";
 //BA.debugLineNum = 185;BA.debugLine="If pnl.Parent.IsInitialized Then pnl.RemoveViewFr";
if (_pnl.getParent().IsInitialized()) { 
_pnl.RemoveViewFromParent();};
 //BA.debugLineNum = 186;BA.debugLine="If mPullToRefreshPanel.IsInitialized Then mPullTo";
if (_mpulltorefreshpanel.IsInitialized()) { 
_mpulltorefreshpanel.RemoveViewFromParent();};
 //BA.debugLineNum = 187;BA.debugLine="mPullToRefreshPanel = pnl";
_mpulltorefreshpanel = _pnl;
 //BA.debugLineNum = 188;BA.debugLine="Base.AddView(mPullToRefreshPanel, 0, 0, Base.Widt";
_base.AddView((android.view.View)(_mpulltorefreshpanel.getObject()),(int) (0),(int) (0),_base.getWidth(),_mpulltorefreshpanel.getHeight());
 //BA.debugLineNum = 189;BA.debugLine="mPullToRefreshPanel.SendToBack";
_mpulltorefreshpanel.SendToBack();
 //BA.debugLineNum = 190;BA.debugLine="mPullToRefreshPanel.Visible = False";
_mpulltorefreshpanel.setVisible(__c.False);
 //BA.debugLineNum = 191;BA.debugLine="End Sub";
return "";
}
public String  _setscrollingenabled(boolean _b) throws Exception{
 //BA.debugLineNum = 205;BA.debugLine="Public Sub setScrollingEnabled (b As Boolean)";
 //BA.debugLineNum = 206;BA.debugLine="mScrollingDisabled = Not(b)";
_mscrollingdisabled = __c.Not(_b);
 //BA.debugLineNum = 207;BA.debugLine="End Sub";
return "";
}
public boolean  _touchpanel_onintercepttouchevent(int _action,float _x,float _y,Object _motionevent) throws Exception{
float _dx = 0f;
float _dy = 0f;
int _newswipeitem = 0;
 //BA.debugLineNum = 209;BA.debugLine="Private Sub TouchPanel_OnInterceptTouchEvent (Acti";
 //BA.debugLineNum = 210;BA.debugLine="If mScrollingDisabled Or HandlingSwipe Or Waiting";
if (_mscrollingdisabled || _handlingswipe || _waitingforrefreshtocomplete) { 
if (true) return __c.True;};
 //BA.debugLineNum = 211;BA.debugLine="Select Action";
switch (BA.switchObjectToInt(_action,_base.TOUCH_ACTION_DOWN,_base.TOUCH_ACTION_MOVE)) {
case 0: {
 //BA.debugLineNum = 213;BA.debugLine="TouchXStart = X";
_touchxstart = _x;
 //BA.debugLineNum = 214;BA.debugLine="TouchYStart = Y";
_touchystart = _y;
 //BA.debugLineNum = 215;BA.debugLine="HandlingSwipe = False";
_handlingswipe = __c.False;
 break; }
case 1: {
 //BA.debugLineNum = 217;BA.debugLine="Dim dx As Float = Abs(x - TouchXStart)";
_dx = (float) (__c.Abs(_x-_touchxstart));
 //BA.debugLineNum = 218;BA.debugLine="Dim dy As Float = Abs(y - TouchYStart)";
_dy = (float) (__c.Abs(_y-_touchystart));
 //BA.debugLineNum = 219;BA.debugLine="If mPullToRefreshPanel.IsInitialized And mCLV.s";
if (_mpulltorefreshpanel.IsInitialized() && _mclv._sv.getScrollViewOffsetY()==0 && _y-_touchystart>__c.DipToCurrent((int) (3))) { 
 //BA.debugLineNum = 220;BA.debugLine="HandlingSwipe = True";
_handlingswipe = __c.True;
 //BA.debugLineNum = 221;BA.debugLine="PullToRefreshSwipe = True";
_pulltorefreshswipe = __c.True;
 //BA.debugLineNum = 222;BA.debugLine="mPullToRefreshPanel.Visible = True";
_mpulltorefreshpanel.setVisible(__c.True);
 //BA.debugLineNum = 223;BA.debugLine="CloseLastSwiped";
_closelastswiped();
 }else if(_dy<__c.DipToCurrent((int) (20)) && _dx>__c.DipToCurrent((int) (10)) && _mclv._getsize()>0) { 
 //BA.debugLineNum = 225;BA.debugLine="If HandlingSwipe = False Then";
if (_handlingswipe==__c.False) { 
 //BA.debugLineNum = 226;BA.debugLine="Dim NewSwipeItem As Int = mCLV.FindIndexFromO";
_newswipeitem = _mclv._findindexfromoffset((int) (_touchystart+_mclv._sv.getScrollViewOffsetY()));
 //BA.debugLineNum = 227;BA.debugLine="If NewSwipeItem <> LastSwipedItem Then CloseL";
if (_newswipeitem!=_lastswipeditem) { 
_closelastswiped();};
 //BA.debugLineNum = 228;BA.debugLine="LastSwipedItem = NewSwipeItem";
_lastswipeditem = _newswipeitem;
 };
 //BA.debugLineNum = 230;BA.debugLine="HandlingSwipe = True";
_handlingswipe = __c.True;
 //BA.debugLineNum = 231;BA.debugLine="PullToRefreshSwipe = False";
_pulltorefreshswipe = __c.False;
 };
 break; }
}
;
 //BA.debugLineNum = 234;BA.debugLine="Return HandlingSwipe";
if (true) return _handlingswipe;
 //BA.debugLineNum = 235;BA.debugLine="End Sub";
return false;
}
public boolean  _touchpanel_ontouchevent(int _action,float _x,float _y,Object _motionevent) throws Exception{
float _dy = 0f;
float _dx = 0f;
 //BA.debugLineNum = 237;BA.debugLine="Private Sub TouchPanel_OnTouchEvent (Action As Int";
 //BA.debugLineNum = 238;BA.debugLine="If mScrollingDisabled Or HandlingSwipe = False Or";
if (_mscrollingdisabled || _handlingswipe==__c.False || _waitingforrefreshtocomplete) { 
if (true) return __c.True;};
 //BA.debugLineNum = 239;BA.debugLine="If PullToRefreshSwipe Then";
if (_pulltorefreshswipe) { 
 //BA.debugLineNum = 240;BA.debugLine="Select Action";
switch (BA.switchObjectToInt(_action,_base.TOUCH_ACTION_MOVE,_base.TOUCH_ACTION_UP)) {
case 0: {
 //BA.debugLineNum = 242;BA.debugLine="Dim dy As Float = y - TouchYStart";
_dy = (float) (_y-_touchystart);
 //BA.debugLineNum = 243;BA.debugLine="TouchYStart = Y";
_touchystart = _y;
 //BA.debugLineNum = 244;BA.debugLine="ChangeYOffset(dy, False)";
_changeyoffset((int) (_dy),__c.False);
 break; }
case 1: {
 //BA.debugLineNum = 246;BA.debugLine="ChangeYOffset(dy, True)";
_changeyoffset((int) (_dy),__c.True);
 //BA.debugLineNum = 247;BA.debugLine="HandlingSwipe = False";
_handlingswipe = __c.False;
 break; }
}
;
 }else {
 //BA.debugLineNum = 250;BA.debugLine="Select Action";
switch (BA.switchObjectToInt(_action,_base.TOUCH_ACTION_MOVE,_base.TOUCH_ACTION_UP)) {
case 0: {
 //BA.debugLineNum = 252;BA.debugLine="Dim dx As Float = x - TouchXStart";
_dx = (float) (_x-_touchxstart);
 //BA.debugLineNum = 253;BA.debugLine="TouchXStart = X";
_touchxstart = _x;
 //BA.debugLineNum = 254;BA.debugLine="HandlingSwipe = ChangeOffset(LastSwipedItem, d";
_handlingswipe = _changeoffset(_lastswipeditem,(int) (_dx),__c.False);
 break; }
case 1: {
 //BA.debugLineNum = 256;BA.debugLine="ChangeOffset(LastSwipedItem, 0, True)";
_changeoffset(_lastswipeditem,(int) (0),__c.True);
 //BA.debugLineNum = 257;BA.debugLine="HandlingSwipe = False";
_handlingswipe = __c.False;
 break; }
}
;
 };
 //BA.debugLineNum = 260;BA.debugLine="Return True";
if (true) return __c.True;
 //BA.debugLineNum = 261;BA.debugLine="End Sub";
return false;
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
