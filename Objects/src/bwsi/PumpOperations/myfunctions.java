package bwsi.PumpOperations;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class myfunctions {
private static myfunctions mostCurrent = new myfunctions();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public b4a.example.dateutils _dateutils = null;
public bwsi.PumpOperations.main _main = null;
public bwsi.PumpOperations.globalvar _globalvar = null;
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
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _fontbit(anywheresoftware.b4a.BA _ba,String _icon,float _font_size,int _color,boolean _awesome) throws Exception{
anywheresoftware.b4a.keywords.constants.TypefaceWrapper _typ = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvs = null;
double _h = 0;
 //BA.debugLineNum = 68;BA.debugLine="Public Sub FontBit (icon As String, font_size As F";
 //BA.debugLineNum = 70;BA.debugLine="If color = 0 Then color = Colors.White";
if (_color==0) { 
_color = anywheresoftware.b4a.keywords.Common.Colors.White;};
 //BA.debugLineNum = 71;BA.debugLine="Dim typ As Typeface = Typeface.MATERIALICONS";
_typ = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_typ = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS()));
 //BA.debugLineNum = 72;BA.debugLine="If awesome Then typ = Typeface.FONTAWESOME";
if (_awesome) { 
_typ = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()));};
 //BA.debugLineNum = 73;BA.debugLine="Dim bmp As Bitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 74;BA.debugLine="bmp.InitializeMutable(32dip, 32dip)";
_bmp.InitializeMutable(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (32)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (32)));
 //BA.debugLineNum = 75;BA.debugLine="Dim cvs As Canvas";
_cvs = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 76;BA.debugLine="cvs.Initialize2(bmp)";
_cvs.Initialize2((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 77;BA.debugLine="Dim h As Double";
_h = 0;
 //BA.debugLineNum = 78;BA.debugLine="If Not(awesome) Then";
if (anywheresoftware.b4a.keywords.Common.Not(_awesome)) { 
 //BA.debugLineNum = 79;BA.debugLine="h = cvs.MeasureStringHeight(icon, typ, font_size";
_h = _cvs.MeasureStringHeight(_icon,(android.graphics.Typeface)(_typ.getObject()),_font_size)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
 }else {
 //BA.debugLineNum = 81;BA.debugLine="h = cvs.MeasureStringHeight(icon, typ, font_size";
_h = _cvs.MeasureStringHeight(_icon,(android.graphics.Typeface)(_typ.getObject()),_font_size);
 };
 //BA.debugLineNum = 83;BA.debugLine="cvs.DrawText(icon, bmp.Width / 2, bmp.Height / 2";
_cvs.DrawText(_ba,_icon,(float) (_bmp.getWidth()/(double)2),(float) (_bmp.getHeight()/(double)2+_h/(double)2),(android.graphics.Typeface)(_typ.getObject()),_font_size,_color,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 //BA.debugLineNum = 84;BA.debugLine="Return bmp";
if (true) return _bmp;
 //BA.debugLineNum = 85;BA.debugLine="End Sub";
return null;
}
public static boolean  _isthereuserdata(anywheresoftware.b4a.BA _ba) throws Exception{
boolean _blnretval = false;
 //BA.debugLineNum = 151;BA.debugLine="Public Sub IsThereUserData() As Boolean";
 //BA.debugLineNum = 152;BA.debugLine="Dim blnRetVal As Boolean";
_blnretval = false;
 //BA.debugLineNum = 153;BA.debugLine="blnRetVal = False";
_blnretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 155;BA.debugLine="Try";
try { //BA.debugLineNum = 156;BA.debugLine="blnRetVal = True";
_blnretval = anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e6) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e6); //BA.debugLineNum = 158;BA.debugLine="blnRetVal = False";
_blnretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 159;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("73211272",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 161;BA.debugLine="Return blnRetVal";
if (true) return _blnretval;
 //BA.debugLineNum = 162;BA.debugLine="End Sub";
return false;
}
public static anywheresoftware.b4a.objects.drawable.ColorDrawable  _mycd(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.drawable.ColorDrawable _mcd = null;
 //BA.debugLineNum = 140;BA.debugLine="Public Sub myCD As ColorDrawable";
 //BA.debugLineNum = 141;BA.debugLine="Dim mCD As ColorDrawable";
_mcd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 142;BA.debugLine="mCD.Initialize(Colors.RGB(240,240,240),0)";
_mcd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (240),(int) (240),(int) (240)),(int) (0));
 //BA.debugLineNum = 143;BA.debugLine="Return mCD";
if (true) return _mcd;
 //BA.debugLineNum = 144;BA.debugLine="End Sub";
return null;
}
public static String  _mytoastmsg(anywheresoftware.b4a.BA _ba,bwsi.PumpOperations.bctoast _mytoast,String _smessage) throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Public Sub MyToastMsg(MyToast As BCToast,  sMessag";
 //BA.debugLineNum = 106;BA.debugLine="MyToast.DurationMs = 1600";
_mytoast._durationms /*int*/  = (int) (1600);
 //BA.debugLineNum = 107;BA.debugLine="MyToast.Show(sMessage)";
_mytoast._show /*void*/ (_smessage);
 //BA.debugLineNum = 108;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="End Sub";
return "";
}
public static String  _retrieveusersettings(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 164;BA.debugLine="Public Sub RetrieveUserSettings";
 //BA.debugLineNum = 166;BA.debugLine="End Sub";
return "";
}
public static String  _saveusersettings(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 147;BA.debugLine="Public Sub SaveUserSettings";
 //BA.debugLineNum = 149;BA.debugLine="End Sub";
return "";
}
public static String  _setbutton(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _v,float _rx_topleft,float _ry_topleft,float _rx_topright,float _ry_topright,float _rx_bottomright,float _ry_bottomright,float _rx_bottomleft,float _ry_bottomleft) throws Exception{
anywheresoftware.b4a.objects.drawable.GradientDrawable _gradbutton = null;
int[] _clrs = null;
anywheresoftware.b4j.object.JavaObject _jo = null;
 //BA.debugLineNum = 110;BA.debugLine="Public Sub SetButton(v As View, Rx_TopLeft As Floa";
 //BA.debugLineNum = 111;BA.debugLine="Dim GradButton As GradientDrawable";
_gradbutton = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
 //BA.debugLineNum = 112;BA.debugLine="Dim Clrs(2) As Int";
_clrs = new int[(int) (2)];
;
 //BA.debugLineNum = 113;BA.debugLine="Clrs(0) = GlobalVar.GreenColor";
_clrs[(int) (0)] = (int) (mostCurrent._globalvar._greencolor /*double*/ );
 //BA.debugLineNum = 114;BA.debugLine="Clrs(1) = GlobalVar.GreenColor2";
_clrs[(int) (1)] = (int) (mostCurrent._globalvar._greencolor2 /*double*/ );
 //BA.debugLineNum = 115;BA.debugLine="If Not(GradButton.IsInitialized) Then GradButton.";
if (anywheresoftware.b4a.keywords.Common.Not(_gradbutton.IsInitialized())) { 
_gradbutton.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"TR_BL"),_clrs);};
 //BA.debugLineNum = 116;BA.debugLine="v.Background = GradButton";
_v.setBackground((android.graphics.drawable.Drawable)(_gradbutton.getObject()));
 //BA.debugLineNum = 118;BA.debugLine="Dim jo As JavaObject = v.Background";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_v.getBackground()));
 //BA.debugLineNum = 119;BA.debugLine="If v.Background Is ColorDrawable Or v.Background";
if (_v.getBackground() instanceof android.graphics.drawable.Drawable || _v.getBackground() instanceof android.graphics.drawable.GradientDrawable) { 
 //BA.debugLineNum = 120;BA.debugLine="jo.RunMethod(\"setCornerRadii\", Array As Object(A";
_jo.RunMethod("setCornerRadii",new Object[]{(Object)(new float[]{_rx_topleft,_ry_topleft,_rx_topright,_ry_topright,_rx_bottomright,_ry_bottomright,_rx_bottomleft,_ry_bottomleft})});
 };
 //BA.debugLineNum = 122;BA.debugLine="End Sub";
return "";
}
public static String  _setcancelbutton(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _v,float _rx_topleft,float _ry_topleft,float _rx_topright,float _ry_topright,float _rx_bottomright,float _ry_bottomright,float _rx_bottomleft,float _ry_bottomleft) throws Exception{
anywheresoftware.b4a.objects.drawable.GradientDrawable _gradbutton = null;
int[] _clrs = null;
anywheresoftware.b4j.object.JavaObject _jo = null;
 //BA.debugLineNum = 124;BA.debugLine="Public Sub SetCancelButton(v As View, Rx_TopLeft A";
 //BA.debugLineNum = 125;BA.debugLine="Dim GradButton As GradientDrawable";
_gradbutton = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
 //BA.debugLineNum = 126;BA.debugLine="Dim Clrs(2) As Int";
_clrs = new int[(int) (2)];
;
 //BA.debugLineNum = 127;BA.debugLine="Clrs(0) = GlobalVar.RedColor";
_clrs[(int) (0)] = (int) (mostCurrent._globalvar._redcolor /*double*/ );
 //BA.debugLineNum = 128;BA.debugLine="Clrs(1) = GlobalVar.NegColor";
_clrs[(int) (1)] = (int) (mostCurrent._globalvar._negcolor /*double*/ );
 //BA.debugLineNum = 129;BA.debugLine="If Not(GradButton.IsInitialized) Then GradButton.";
if (anywheresoftware.b4a.keywords.Common.Not(_gradbutton.IsInitialized())) { 
_gradbutton.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"TR_BL"),_clrs);};
 //BA.debugLineNum = 130;BA.debugLine="v.Background = GradButton";
_v.setBackground((android.graphics.drawable.Drawable)(_gradbutton.getObject()));
 //BA.debugLineNum = 132;BA.debugLine="Dim jo As JavaObject = v.Background";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_v.getBackground()));
 //BA.debugLineNum = 133;BA.debugLine="If v.Background Is ColorDrawable Or v.Background";
if (_v.getBackground() instanceof android.graphics.drawable.Drawable || _v.getBackground() instanceof android.graphics.drawable.GradientDrawable) { 
 //BA.debugLineNum = 134;BA.debugLine="jo.RunMethod(\"setCornerRadii\", Array As Object(A";
_jo.RunMethod("setCornerRadii",new Object[]{(Object)(new float[]{_rx_topleft,_ry_topleft,_rx_topright,_ry_topright,_rx_bottomright,_ry_bottomright,_rx_bottomleft,_ry_bottomleft})});
 };
 //BA.debugLineNum = 136;BA.debugLine="End Sub";
return "";
}
public static String  _setsnackbarbackground(anywheresoftware.b4a.BA _ba,de.amberhome.objects.SnackbarWrapper _psnack,int _pcolor) throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
 //BA.debugLineNum = 6;BA.debugLine="Public Sub SetSnackBarBackground(pSnack As DSSnack";
 //BA.debugLineNum = 7;BA.debugLine="Dim v As View";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
 //BA.debugLineNum = 8;BA.debugLine="v = pSnack.View";
_v = (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_psnack.getView()));
 //BA.debugLineNum = 9;BA.debugLine="v.Color = pColor";
_v.setColor(_pcolor);
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _setsnackbartextcolor(anywheresoftware.b4a.BA _ba,de.amberhome.objects.SnackbarWrapper _psnack,int _pcolor) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.collections.List _labellist = null;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.collections.List _labelviews = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
 //BA.debugLineNum = 12;BA.debugLine="Public Sub SetSnackBarTextColor(pSnack As DSSnackb";
 //BA.debugLineNum = 13;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Dim labelList As List";
_labellist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 15;BA.debugLine="labelList.Initialize";
_labellist.Initialize();
 //BA.debugLineNum = 18;BA.debugLine="p = pSnack.View";
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_psnack.getView()));
 //BA.debugLineNum = 36;BA.debugLine="For Each v As View In p.GetAllViewsRecursive";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
{
final anywheresoftware.b4a.BA.IterableList group5 = _p.GetAllViewsRecursive();
final int groupLen5 = group5.getSize()
;int index5 = 0;
;
for (; index5 < groupLen5;index5++){
_v = (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(group5.Get(index5)));
 //BA.debugLineNum = 37;BA.debugLine="If v Is Label Then";
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 38;BA.debugLine="labelList.Add(v)";
_labellist.Add((Object)(_v.getObject()));
 };
 }
};
 //BA.debugLineNum = 43;BA.debugLine="Dim labelViews As List";
_labelviews = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 44;BA.debugLine="labelViews = labelList";
_labelviews = _labellist;
 //BA.debugLineNum = 47;BA.debugLine="LogColor(labelViews.Size,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("72687011",BA.NumberToString(_labelviews.getSize()),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 49;BA.debugLine="For Each lbl As Label In labelViews";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
{
final anywheresoftware.b4a.BA.IterableList group13 = _labelviews;
final int groupLen13 = group13.getSize()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(group13.Get(index13)));
 //BA.debugLineNum = 50;BA.debugLine="Log(lbl.Tag)";
anywheresoftware.b4a.keywords.Common.LogImpl("72687014",BA.ObjectToString(_lbl.getTag()),0);
 //BA.debugLineNum = 51;BA.debugLine="If lbl.Text = \"\" Then";
if ((_lbl.getText()).equals("")) { 
 //BA.debugLineNum = 52;BA.debugLine="Log(\"Action Button\")";
anywheresoftware.b4a.keywords.Common.LogImpl("72687016","Action Button",0);
 //BA.debugLineNum = 53;BA.debugLine="lbl.TextColor = Colors.LightGray";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 //BA.debugLineNum = 54;BA.debugLine="lbl.Typeface = Typeface.DEFAULT_BOLD";
_lbl.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 55;BA.debugLine="lbl.TextSize = 20";
_lbl.setTextSize((float) (20));
 }else {
 //BA.debugLineNum = 58;BA.debugLine="lbl.TextColor = pColor";
_lbl.setTextColor(_pcolor);
 };
 }
};
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return "";
}
public static String  _showcustomtoast(anywheresoftware.b4a.BA _ba,Object _text,boolean _longduration,int _backgroundcolor,int _height) throws Exception{
anywheresoftware.b4j.object.JavaObject _ctxt = null;
int _duration = 0;
anywheresoftware.b4j.object.JavaObject _toast = null;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
 //BA.debugLineNum = 87;BA.debugLine="Public Sub ShowCustomToast(Text As Object, LongDur";
 //BA.debugLineNum = 88;BA.debugLine="Dim ctxt As JavaObject";
_ctxt = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 89;BA.debugLine="ctxt.InitializeContext";
_ctxt.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA));
 //BA.debugLineNum = 90;BA.debugLine="Dim duration As Int";
_duration = 0;
 //BA.debugLineNum = 91;BA.debugLine="If LongDuration Then duration = 1 Else duration =";
if (_longduration) { 
_duration = (int) (1);}
else {
_duration = (int) (0);};
 //BA.debugLineNum = 92;BA.debugLine="Dim toast As JavaObject";
_toast = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 93;BA.debugLine="toast = toast.InitializeStatic(\"android.widget.To";
_toast = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_toast.InitializeStatic("android.widget.Toast").RunMethod("makeText",new Object[]{(Object)(_ctxt.getObject()),_text,(Object)(_duration)})));
 //BA.debugLineNum = 94;BA.debugLine="Dim v As View = toast.RunMethod(\"getView\", Null)";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
_v = (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_toast.RunMethod("getView",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
 //BA.debugLineNum = 95;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 96;BA.debugLine="cd.Initialize(BackgroundColor, 20dip)";
_cd.Initialize(_backgroundcolor,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 97;BA.debugLine="v.Background = cd";
_v.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 99;BA.debugLine="toast.RunMethod(\"setGravity\", Array( _ 	       Bi";
_toast.RunMethod("setGravity",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL,anywheresoftware.b4a.keywords.Common.Gravity.BOTTOM)),(Object)(0),(Object)(0)});
 //BA.debugLineNum = 101;BA.debugLine="toast.RunMethod(\"show\", Null)";
_toast.RunMethod("show",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
}
