package bwsi.PumpOperations;

import android.content.Context;
import android.app.NotificationManager;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class nb6 extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "bwsi.PumpOperations.nb6");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", bwsi.PumpOperations.nb6.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4j.object.JavaObject _channel = null;
public anywheresoftware.b4j.object.JavaObject _notificationbuilder = null;
public int _sdklevel = 0;
public anywheresoftware.b4j.object.JavaObject _ctxt = null;
public int _s_old = 0;
public int _s_builder = 0;
public int _s_channel = 0;
public int _supportlevel = 0;
public anywheresoftware.b4a.objects.NotificationWrapper _oldnotification = null;
public anywheresoftware.b4j.object.JavaObject _pendingintentstatic = null;
public anywheresoftware.b4j.object.JavaObject _notificationstatic = null;
public anywheresoftware.b4j.object.JavaObject _common = null;
public int _ndefaults = 0;
public anywheresoftware.b4j.object.JavaObject _notifchannelgroup = null;
public String _groupid = "";
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
public bwsi.PumpOperations.nb6  _addbuttonaction(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp,Object _title,Object _service,String _action) throws Exception{
Object _ac = null;
 //BA.debugLineNum = 93;BA.debugLine="Public Sub AddButtonAction (Bmp As Bitmap, Title A";
 //BA.debugLineNum = 94;BA.debugLine="If IsBuilder = False Then Return Me";
if (_isbuilder()==__c.False) { 
if (true) return (bwsi.PumpOperations.nb6)(this);};
 //BA.debugLineNum = 95;BA.debugLine="Dim ac As Object = CreateAction(Bmp, Title, Creat";
_ac = _createaction(_bmp,_title,_createreceiverpendingintent(_service,_action));
 //BA.debugLineNum = 96;BA.debugLine="NotificationBuilder.RunMethod(\"addAction\", Array(";
_notificationbuilder.RunMethod("addAction",new Object[]{_ac});
 //BA.debugLineNum = 97;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _autocancel(boolean _cancel) throws Exception{
 //BA.debugLineNum = 143;BA.debugLine="Public Sub AutoCancel (Cancel As Boolean) As NB6";
 //BA.debugLineNum = 144;BA.debugLine="If IsOld Then";
if (_isold()) { 
 //BA.debugLineNum = 145;BA.debugLine="OldNotification.AutoCancel = Cancel";
_oldnotification.setAutoCancel(_cancel);
 }else {
 //BA.debugLineNum = 147;BA.debugLine="NotificationBuilder.RunMethod(\"setAutoCancel\", A";
_notificationbuilder.RunMethod("setAutoCancel",new Object[]{(Object)(_cancel)});
 };
 //BA.debugLineNum = 149;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 150;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _badgeicontype(String _icontype) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 154;BA.debugLine="Public Sub BadgeIconType (IconType As String) As N";
 //BA.debugLineNum = 155;BA.debugLine="If SdkLevel >= 26 Then";
if (_sdklevel>=26) { 
 //BA.debugLineNum = 156;BA.debugLine="Dim m As Map = CreateMap(\"LARGE\": 2, \"NONE\": 0,";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = __c.createMap(new Object[] {(Object)("LARGE"),(Object)(2),(Object)("NONE"),(Object)(0),(Object)("SMALL"),(Object)(1)});
 //BA.debugLineNum = 157;BA.debugLine="NotificationBuilder.RunMethod(\"setBadgeIconType\"";
_notificationbuilder.RunMethod("setBadgeIconType",new Object[]{_m.Get((Object)(_icontype))});
 };
 //BA.debugLineNum = 159;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 160;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _bigpicturestyle(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _largeiconbmp,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _picture,Object _contenttitle,Object _summarytext) throws Exception{
 //BA.debugLineNum = 259;BA.debugLine="Public Sub BigPictureStyle(LargeIconBmp As Bitmap,";
 //BA.debugLineNum = 260;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 261;BA.debugLine="SetStyle(\"android.app.Notification$BigPictureSty";
_setstyle("android.app.Notification$BigPictureStyle",__c.createMap(new Object[] {(Object)("bigLargeIcon"),(Object)(_largeiconbmp.getObject()),(Object)("bigPicture"),(Object)(_picture.getObject()),(Object)("setBigContentTitle"),_contenttitle,(Object)("setSummaryText"),_summarytext}));
 };
 //BA.debugLineNum = 267;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 268;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _bigtextstyle(Object _contenttitle,Object _summarytext,Object _text) throws Exception{
 //BA.debugLineNum = 271;BA.debugLine="Public Sub BigTextStyle (ContentTitle As Object, S";
 //BA.debugLineNum = 272;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 273;BA.debugLine="SetStyle(\"android.app.Notification$BigTextStyle\"";
_setstyle("android.app.Notification$BigTextStyle",__c.createMap(new Object[] {(Object)("bigText"),_text,(Object)("setBigContentTitle"),_contenttitle,(Object)("setSummaryText"),_summarytext}));
 };
 //BA.debugLineNum = 276;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 277;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.NotificationWrapper  _build(Object _contenttitle,Object _contenttext,String _tag,Object _activity) throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _in = null;
Object _pendingintent = null;
anywheresoftware.b4j.object.JavaObject _manager = null;
 //BA.debugLineNum = 296;BA.debugLine="Public Sub Build (ContentTitle As Object, ContentT";
 //BA.debugLineNum = 297;BA.debugLine="If IsOld Then";
if (_isold()) { 
 //BA.debugLineNum = 298;BA.debugLine="OldNotification.SetInfo2(ContentTitle, ContentTe";
_oldnotification.SetInfo2New(ba,BA.ObjectToCharSequence(_contenttitle),BA.ObjectToCharSequence(_contenttext),BA.ObjectToCharSequence(_tag),_activity);
 //BA.debugLineNum = 299;BA.debugLine="Return OldNotification";
if (true) return _oldnotification;
 }else {
 //BA.debugLineNum = 301;BA.debugLine="If Not(IsChannel) And nDefaults <> 7 Then 'not a";
if (__c.Not(_ischannel()) && _ndefaults!=7) { 
 //BA.debugLineNum = 302;BA.debugLine="NotificationBuilder.RunMethod(\"setDefaults\", Ar";
_notificationbuilder.RunMethod("setDefaults",new Object[]{(Object)(_ndefaults)});
 };
 //BA.debugLineNum = 304;BA.debugLine="Dim in As Intent = CreateIntent(Activity, False)";
_in = new anywheresoftware.b4a.objects.IntentWrapper();
_in = _createintent(_activity,__c.False);
 //BA.debugLineNum = 305;BA.debugLine="in.Flags = Bit.Or(268435456, 131072) 'FLAG_ACTIV";
_in.setFlags(__c.Bit.Or((int) (268435456),(int) (131072)));
 //BA.debugLineNum = 306;BA.debugLine="in.PutExtra(\"Notification_Tag\", Tag)";
_in.PutExtra("Notification_Tag",(Object)(_tag));
 //BA.debugLineNum = 307;BA.debugLine="Dim PendingIntent As Object = PendingIntentStati";
_pendingintent = _pendingintentstatic.RunMethod("getActivity",new Object[]{(Object)(_ctxt.getObject()),(Object)(__c.Rnd((int) (0),(int) (0x7fffffff))),(Object)(_in.getObject()),(Object)(0)});
 //BA.debugLineNum = 308;BA.debugLine="NotificationBuilder.RunMethodJO(\"setContentTitle";
_notificationbuilder.RunMethodJO("setContentTitle",new Object[]{_contenttitle}).RunMethodJO("setContentText",new Object[]{_contenttext});
 //BA.debugLineNum = 309;BA.debugLine="NotificationBuilder.RunMethod(\"setContentIntent\"";
_notificationbuilder.RunMethod("setContentIntent",new Object[]{_pendingintent});
 //BA.debugLineNum = 311;BA.debugLine="If IsChannel Then";
if (_ischannel()) { 
 //BA.debugLineNum = 312;BA.debugLine="Dim manager As JavaObject = ctxt.RunMethod(\"get";
_manager = new anywheresoftware.b4j.object.JavaObject();
_manager = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_ctxt.RunMethod("getSystemService",new Object[]{(Object)("notification")})));
 //BA.debugLineNum = 313;BA.debugLine="manager.RunMethod(\"createNotificationChannel\",";
_manager.RunMethod("createNotificationChannel",new Object[]{(Object)(_channel.getObject())});
 //BA.debugLineNum = 316;BA.debugLine="If NotifChannelGroup.IsInitialized Then";
if (_notifchannelgroup.IsInitialized()) { 
 //BA.debugLineNum = 317;BA.debugLine="manager.RunMethod(\"createNotificationChannelGr";
_manager.RunMethod("createNotificationChannelGroup",new Object[]{(Object)(_notifchannelgroup.getObject())});
 //BA.debugLineNum = 318;BA.debugLine="Channel.RunMethod(\"setGroup\", Array(groupID))";
_channel.RunMethod("setGroup",new Object[]{(Object)(_groupid)});
 };
 };
 //BA.debugLineNum = 322;BA.debugLine="Return NotificationBuilder.RunMethod(\"build\", Nu";
if (true) return (anywheresoftware.b4a.objects.NotificationWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.NotificationWrapper(), (java.lang.Object)(_notificationbuilder.RunMethod("build",(Object[])(__c.Null))));
 };
 //BA.debugLineNum = 324;BA.debugLine="End Sub";
return null;
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private Channel As JavaObject";
_channel = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 4;BA.debugLine="Private NotificationBuilder As JavaObject";
_notificationbuilder = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 5;BA.debugLine="Private SdkLevel As Int";
_sdklevel = 0;
 //BA.debugLineNum = 6;BA.debugLine="Private ctxt As JavaObject";
_ctxt = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 7;BA.debugLine="Private const S_OLD = 0, S_BUILDER = 1, S_CHANNEL";
_s_old = (int) (0);
_s_builder = (int) (1);
_s_channel = (int) (2);
 //BA.debugLineNum = 8;BA.debugLine="Private SupportLevel As Int";
_supportlevel = 0;
 //BA.debugLineNum = 9;BA.debugLine="Private OldNotification As Notification";
_oldnotification = new anywheresoftware.b4a.objects.NotificationWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Private PendingIntentStatic As JavaObject";
_pendingintentstatic = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 11;BA.debugLine="Private NotificationStatic As JavaObject";
_notificationstatic = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 12;BA.debugLine="Private common As JavaObject";
_common = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 13;BA.debugLine="Private nDefaults As Int = 7 '1 (default sound) +";
_ndefaults = (int) (7);
 //BA.debugLineNum = 14;BA.debugLine="Private NotifChannelGroup As JavaObject";
_notifchannelgroup = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 15;BA.debugLine="Private groupID As String";
_groupid = "";
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public bwsi.PumpOperations.nb6  _color(int _clr) throws Exception{
 //BA.debugLineNum = 163;BA.debugLine="Public Sub Color (Clr As Int) As NB6";
 //BA.debugLineNum = 164;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 165;BA.debugLine="NotificationBuilder.RunMethod(\"setColor\", Array(";
_notificationbuilder.RunMethod("setColor",new Object[]{(Object)(_clr)});
 };
 //BA.debugLineNum = 167;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return null;
}
public Object  _createaction(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp,Object _title,Object _pendingintent) throws Exception{
anywheresoftware.b4j.object.JavaObject _builder = null;
 //BA.debugLineNum = 339;BA.debugLine="Private Sub CreateAction (Bmp As Bitmap, Title As";
 //BA.debugLineNum = 340;BA.debugLine="Dim builder As JavaObject";
_builder = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 341;BA.debugLine="builder.InitializeNewInstance(\"android.app.Notifi";
_builder.InitializeNewInstance("android.app.Notification$Action$Builder",new Object[]{_createiconfrombitmap(_bmp),_title,_pendingintent});
 //BA.debugLineNum = 342;BA.debugLine="Return builder.RunMethod(\"build\", Null)";
if (true) return _builder.RunMethod("build",(Object[])(__c.Null));
 //BA.debugLineNum = 343;BA.debugLine="End Sub";
return null;
}
public Object  _createiconfrombitmap(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp) throws Exception{
anywheresoftware.b4j.object.JavaObject _icon = null;
 //BA.debugLineNum = 345;BA.debugLine="Private Sub CreateIconFromBitmap(bmp As Bitmap) As";
 //BA.debugLineNum = 346;BA.debugLine="If bmp = Null Or bmp.IsInitialized = False Then R";
if (_bmp== null || _bmp.IsInitialized()==__c.False) { 
if (true) return (Object)(0);};
 //BA.debugLineNum = 347;BA.debugLine="Dim icon As JavaObject";
_icon = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 348;BA.debugLine="Return icon.InitializeStatic(\"android.graphics.dr";
if (true) return _icon.InitializeStatic("android.graphics.drawable.Icon").RunMethod("createWithBitmap",new Object[]{(Object)(_bmp.getObject())});
 //BA.debugLineNum = 349;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.IntentWrapper  _createintent(Object _target,boolean _receiver) throws Exception{
anywheresoftware.b4j.object.JavaObject _in = null;
 //BA.debugLineNum = 332;BA.debugLine="Private Sub CreateIntent (Target As Object, Receiv";
 //BA.debugLineNum = 333;BA.debugLine="Target = common.RunMethod(\"getComponentClass\", Ar";
_target = _common.RunMethod("getComponentClass",new Object[]{__c.Null,_target,(Object)(_receiver)});
 //BA.debugLineNum = 334;BA.debugLine="Dim in As JavaObject";
_in = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 335;BA.debugLine="in.InitializeNewInstance(\"android.content.Intent\"";
_in.InitializeNewInstance("android.content.Intent",new Object[]{(Object)(_ctxt.getObject()),_target});
 //BA.debugLineNum = 336;BA.debugLine="Return in";
if (true) return (anywheresoftware.b4a.objects.IntentWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.IntentWrapper(), (android.content.Intent)(_in.getObject()));
 //BA.debugLineNum = 337;BA.debugLine="End Sub";
return null;
}
public Object  _createreceiverpendingintent(Object _service,String _action) throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _in = null;
 //BA.debugLineNum = 326;BA.debugLine="Private Sub CreateReceiverPendingIntent (Service A";
 //BA.debugLineNum = 327;BA.debugLine="Dim in As Intent = CreateIntent(Service, True)";
_in = new anywheresoftware.b4a.objects.IntentWrapper();
_in = _createintent(_service,__c.True);
 //BA.debugLineNum = 328;BA.debugLine="in.Action = Action";
_in.setAction(_action);
 //BA.debugLineNum = 329;BA.debugLine="Return PendingIntentStatic.RunMethod(\"getBroadcas";
if (true) return _pendingintentstatic.RunMethod("getBroadcast",new Object[]{(Object)(_ctxt.getObject()),(Object)(1),(Object)(_in.getObject()),(Object)(0)});
 //BA.debugLineNum = 330;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _customlight(int _argb,int _onms,int _offms) throws Exception{
 //BA.debugLineNum = 408;BA.debugLine="Public Sub CustomLight(argb As Int, onMs As Int, o";
 //BA.debugLineNum = 409;BA.debugLine="If IsChannel Then";
if (_ischannel()) { 
 //BA.debugLineNum = 410;BA.debugLine="Channel.RunMethod(\"setLightColor\", Array(argb))";
_channel.RunMethod("setLightColor",new Object[]{(Object)(_argb)});
 }else {
 //BA.debugLineNum = 412;BA.debugLine="If Not(IsOld) Then";
if (__c.Not(_isold())) { 
 //BA.debugLineNum = 413;BA.debugLine="nDefaults = Bit.And(nDefaults, 1 + 2) 'clear bi";
_ndefaults = __c.Bit.And(_ndefaults,(int) (1+2));
 };
 //BA.debugLineNum = 415;BA.debugLine="NotificationBuilder.RunMethod(\"setLights\", Array";
_notificationbuilder.RunMethod("setLights",new Object[]{(Object)(_argb),(Object)(_onms),(Object)(_offms)});
 };
 //BA.debugLineNum = 417;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 418;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _customsound(Object _fileprovideruri) throws Exception{
 //BA.debugLineNum = 232;BA.debugLine="Public Sub CustomSound (FileProviderUri As Object)";
 //BA.debugLineNum = 233;BA.debugLine="If IsOld Then Return Me";
if (_isold()) { 
if (true) return (bwsi.PumpOperations.nb6)(this);};
 //BA.debugLineNum = 234;BA.debugLine="ctxt.RunMethod(\"grantUriPermission\", Array(\"com.a";
_ctxt.RunMethod("grantUriPermission",new Object[]{(Object)("com.android.systemui"),_fileprovideruri,(Object)(1)});
 //BA.debugLineNum = 235;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 236;BA.debugLine="NotificationBuilder.RunMethod(\"setSound\", Array(";
_notificationbuilder.RunMethod("setSound",new Object[]{_fileprovideruri,_notificationstatic.GetField("AUDIO_ATTRIBUTES_DEFAULT")});
 //BA.debugLineNum = 237;BA.debugLine="If IsChannel Then";
if (_ischannel()) { 
 //BA.debugLineNum = 238;BA.debugLine="Channel.RunMethod(\"setSound\", Array(FileProvide";
_channel.RunMethod("setSound",new Object[]{_fileprovideruri,_notificationstatic.GetField("AUDIO_ATTRIBUTES_DEFAULT")});
 };
 };
 //BA.debugLineNum = 241;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 242;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _deleteaction(Object _service,String _action) throws Exception{
 //BA.debugLineNum = 103;BA.debugLine="Public Sub DeleteAction (Service As Object, Action";
 //BA.debugLineNum = 104;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 105;BA.debugLine="NotificationBuilder.RunMethod(\"setDeleteIntent\",";
_notificationbuilder.RunMethod("setDeleteIntent",new Object[]{_createreceiverpendingintent(_service,_action)});
 };
 //BA.debugLineNum = 107;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 108;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _groupset(String _grpid) throws Exception{
 //BA.debugLineNum = 391;BA.debugLine="Public Sub GroupSet(grpID As String) As NB6";
 //BA.debugLineNum = 392;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 393;BA.debugLine="NotificationBuilder.RunMethod(\"setGroup\", Array(";
_notificationbuilder.RunMethod("setGroup",new Object[]{(Object)(_grpid)});
 };
 //BA.debugLineNum = 395;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 396;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _groupsummary(boolean _isgroupsummary) throws Exception{
 //BA.debugLineNum = 401;BA.debugLine="Public Sub GroupSummary(isGroupSummary As Boolean)";
 //BA.debugLineNum = 402;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 403;BA.debugLine="NotificationBuilder.RunMethod(\"setGroupSummary\",";
_notificationbuilder.RunMethod("setGroupSummary",new Object[]{(Object)(_isgroupsummary)});
 };
 //BA.debugLineNum = 405;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 406;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _inboxstyle(Object _summarytext,anywheresoftware.b4a.objects.collections.List _text) throws Exception{
anywheresoftware.b4j.object.JavaObject _style = null;
int _i = 0;
 //BA.debugLineNum = 364;BA.debugLine="Public Sub InboxStyle(SummaryText As Object, Text";
 //BA.debugLineNum = 365;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 367;BA.debugLine="Dim style As JavaObject";
_style = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 368;BA.debugLine="style.InitializeNewInstance(\"android.app.Notific";
_style.InitializeNewInstance("android.app.Notification$InboxStyle",(Object[])(__c.Null));
 //BA.debugLineNum = 369;BA.debugLine="style.RunMethod(\"setSummaryText\", Array(SummaryT";
_style.RunMethod("setSummaryText",new Object[]{_summarytext});
 //BA.debugLineNum = 370;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 371;BA.debugLine="For i = 0 To (Min(5, Text.size-1)) 'max. number";
{
final int step6 = 1;
final int limit6 = (int) ((__c.Min(5,_text.getSize()-1)));
_i = (int) (0) ;
for (;_i <= limit6 ;_i = _i + step6 ) {
 //BA.debugLineNum = 372;BA.debugLine="style.RunMethod(\"addLine\", Array(Text.Get(i)))";
_style.RunMethod("addLine",new Object[]{_text.Get(_i)});
 }
};
 //BA.debugLineNum = 374;BA.debugLine="NotificationBuilder.RunMethod(\"setStyle\", Array(";
_notificationbuilder.RunMethod("setStyle",new Object[]{(Object)(_style.getObject())});
 };
 //BA.debugLineNum = 376;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 377;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _initialize(anywheresoftware.b4a.BA _ba,String _channelid,Object _channelname,String _importancelevel,boolean _usebuildversionsdk) throws Exception{
innerInitialize(_ba);
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4a.objects.collections.Map _im = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _pm = null;
int _p = 0;
 //BA.debugLineNum = 41;BA.debugLine="Public Sub Initialize (ChannelId As String, Channe";
 //BA.debugLineNum = 42;BA.debugLine="ctxt.InitializeContext";
_ctxt.InitializeContext(ba);
 //BA.debugLineNum = 43;BA.debugLine="PendingIntentStatic.InitializeStatic(\"android.app";
_pendingintentstatic.InitializeStatic("android.app.PendingIntent");
 //BA.debugLineNum = 44;BA.debugLine="NotificationStatic.InitializeStatic(\"android.app.";
_notificationstatic.InitializeStatic("android.app.Notification");
 //BA.debugLineNum = 45;BA.debugLine="common.InitializeStatic(\"anywheresoftware.b4a.key";
_common.InitializeStatic("anywheresoftware.b4a.keywords.Common");
 //BA.debugLineNum = 46;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 47;BA.debugLine="If useBuildVersionSDK Then";
if (_usebuildversionsdk) { 
 //BA.debugLineNum = 48;BA.debugLine="SdkLevel = jo.InitializeStatic(\"android.os.Build";
_sdklevel = (int)(BA.ObjectToNumber(_jo.InitializeStatic("android.os.Build$VERSION").GetField("SDK_INT")));
 }else {
 //BA.debugLineNum = 50;BA.debugLine="jo = Me";
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(this));
 //BA.debugLineNum = 51;BA.debugLine="SdkLevel = jo.RunMethod(\"getTargetSDK\", Null)";
_sdklevel = (int)(BA.ObjectToNumber(_jo.RunMethod("getTargetSDK",(Object[])(__c.Null))));
 };
 //BA.debugLineNum = 53;BA.debugLine="If SdkLevel < 23 Then";
if (_sdklevel<23) { 
 //BA.debugLineNum = 54;BA.debugLine="Log(\"support sOld\")";
__c.LogImpl("788277005","support sOld",0);
 //BA.debugLineNum = 55;BA.debugLine="SupportLevel = S_OLD";
_supportlevel = _s_old;
 }else if(_sdklevel>=26) { 
 //BA.debugLineNum = 57;BA.debugLine="Log(\"support sChannel \" & ChannelId & \" \" & Chan";
__c.LogImpl("788277008","support sChannel "+_channelid+" "+BA.ObjectToString(_channelname),0);
 //BA.debugLineNum = 58;BA.debugLine="SupportLevel = S_CHANNEL";
_supportlevel = _s_channel;
 }else {
 //BA.debugLineNum = 60;BA.debugLine="Log(\"support sBuilder\")";
__c.LogImpl("788277011","support sBuilder",0);
 //BA.debugLineNum = 61;BA.debugLine="SupportLevel = S_BUILDER";
_supportlevel = _s_builder;
 };
 //BA.debugLineNum = 64;BA.debugLine="If IsOld Then";
if (_isold()) { 
 //BA.debugLineNum = 65;BA.debugLine="OldNotification.Initialize";
_oldnotification.Initialize();
 //BA.debugLineNum = 66;BA.debugLine="OldNotification.Icon = \"icon\"";
_oldnotification.setIcon("icon");
 }else if(_ischannel()) { 
 //BA.debugLineNum = 68;BA.debugLine="NotificationBuilder.InitializeNewInstance(\"andro";
_notificationbuilder.InitializeNewInstance("android.app.Notification$Builder",new Object[]{(Object)(_ctxt.getObject()),(Object)(_channelid)});
 //BA.debugLineNum = 69;BA.debugLine="Dim im As Map = CreateMap(\"MIN\": 1, \"LOW\": 2, \"D";
_im = new anywheresoftware.b4a.objects.collections.Map();
_im = __c.createMap(new Object[] {(Object)("MIN"),(Object)(1),(Object)("LOW"),(Object)(2),(Object)("DEFAULT"),(Object)(3),(Object)("HIGH"),(Object)(4)});
 //BA.debugLineNum = 70;BA.debugLine="Dim i As Int = im.Get(ImportanceLevel)";
_i = (int)(BA.ObjectToNumber(_im.Get((Object)(_importancelevel))));
 //BA.debugLineNum = 71;BA.debugLine="Channel.InitializeNewInstance(\"android.app.Notif";
_channel.InitializeNewInstance("android.app.NotificationChannel",new Object[]{(Object)(_channelid),_channelname,(Object)(_i)});
 }else {
 //BA.debugLineNum = 73;BA.debugLine="NotificationBuilder.InitializeNewInstance(\"andro";
_notificationbuilder.InitializeNewInstance("android.app.Notification$Builder",new Object[]{(Object)(_ctxt.getObject())});
 //BA.debugLineNum = 74;BA.debugLine="Dim pm As Map = CreateMap(\"MIN\": -2, \"LOW\": -1,";
_pm = new anywheresoftware.b4a.objects.collections.Map();
_pm = __c.createMap(new Object[] {(Object)("MIN"),(Object)(-2),(Object)("LOW"),(Object)(-1),(Object)("DEFAULT"),(Object)(0),(Object)("HIGH"),(Object)(1)});
 //BA.debugLineNum = 75;BA.debugLine="Dim p As Int = pm.Get(ImportanceLevel)";
_p = (int)(BA.ObjectToNumber(_pm.Get((Object)(_importancelevel))));
 //BA.debugLineNum = 76;BA.debugLine="NotificationBuilder.RunMethod(\"setPriority\", Arr";
_notificationbuilder.RunMethod("setPriority",new Object[]{(Object)(_p)});
 };
 //BA.debugLineNum = 79;BA.debugLine="If ImportanceLevel = \"DEFAULT\" Or ImportanceLevel";
if ((_importancelevel).equals("DEFAULT") || (_importancelevel).equals("HIGH")) { 
 //BA.debugLineNum = 80;BA.debugLine="SetDefaults(True, True, True)";
_setdefaults(__c.True,__c.True,__c.True);
 }else {
 //BA.debugLineNum = 82;BA.debugLine="SetDefaults(False, True, True)";
_setdefaults(__c.False,__c.True,__c.True);
 };
 //BA.debugLineNum = 84;BA.debugLine="nDefaults = 1+2+4 'all true sound, vibration, lig";
_ndefaults = (int) (1+2+4);
 //BA.debugLineNum = 85;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return null;
}
public boolean  _isbuilder() throws Exception{
 //BA.debugLineNum = 351;BA.debugLine="Private Sub IsBuilder As Boolean";
 //BA.debugLineNum = 352;BA.debugLine="Return SupportLevel >= S_BUILDER";
if (true) return _supportlevel>=_s_builder;
 //BA.debugLineNum = 353;BA.debugLine="End Sub";
return false;
}
public boolean  _ischannel() throws Exception{
 //BA.debugLineNum = 359;BA.debugLine="Private Sub IsChannel As Boolean";
 //BA.debugLineNum = 360;BA.debugLine="Return SupportLevel = S_CHANNEL";
if (true) return _supportlevel==_s_channel;
 //BA.debugLineNum = 361;BA.debugLine="End Sub";
return false;
}
public boolean  _isold() throws Exception{
 //BA.debugLineNum = 355;BA.debugLine="Private Sub IsOld As Boolean";
 //BA.debugLineNum = 356;BA.debugLine="Return SupportLevel = S_OLD";
if (true) return _supportlevel==_s_old;
 //BA.debugLineNum = 357;BA.debugLine="End Sub";
return false;
}
public bwsi.PumpOperations.nb6  _largeicon(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _icon) throws Exception{
 //BA.debugLineNum = 127;BA.debugLine="Public Sub LargeIcon (Icon As Bitmap) As NB6";
 //BA.debugLineNum = 128;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 129;BA.debugLine="NotificationBuilder.RunMethod(\"setLargeIcon\", Ar";
_notificationbuilder.RunMethod("setLargeIcon",new Object[]{_createiconfrombitmap(_icon)});
 };
 //BA.debugLineNum = 131;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return null;
}
public String  _notificationchannelgroup(String _grpid,String _grpname) throws Exception{
 //BA.debugLineNum = 382;BA.debugLine="Public Sub NotificationChannelGroup(grpID As Strin";
 //BA.debugLineNum = 383;BA.debugLine="If IsChannel Then";
if (_ischannel()) { 
 //BA.debugLineNum = 384;BA.debugLine="groupID = grpID";
_groupid = _grpid;
 //BA.debugLineNum = 385;BA.debugLine="NotifChannelGroup.InitializeNewInstance(\"android";
_notifchannelgroup.InitializeNewInstance("android.app.NotificationChannelGroup",new Object[]{(Object)(_grpid),(Object)(_grpname)});
 };
 //BA.debugLineNum = 387;BA.debugLine="End Sub";
return "";
}
public bwsi.PumpOperations.nb6  _number(int _num) throws Exception{
 //BA.debugLineNum = 171;BA.debugLine="Public Sub Number (Num As Int) As NB6";
 //BA.debugLineNum = 172;BA.debugLine="If IsOld Then";
if (_isold()) { 
 //BA.debugLineNum = 173;BA.debugLine="OldNotification.Number = Num";
_oldnotification.setNumber(_num);
 }else {
 //BA.debugLineNum = 175;BA.debugLine="NotificationBuilder.RunMethod(\"setNumber\", Array";
_notificationbuilder.RunMethod("setNumber",new Object[]{(Object)(_num)});
 };
 //BA.debugLineNum = 177;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 178;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _oldnotificationicon(String _resourcename) throws Exception{
 //BA.debugLineNum = 111;BA.debugLine="Public Sub OldNotificationIcon (ResourceName As St";
 //BA.debugLineNum = 112;BA.debugLine="If IsOld Then";
if (_isold()) { 
 //BA.debugLineNum = 113;BA.debugLine="OldNotification.Icon = ResourceName";
_oldnotification.setIcon(_resourcename);
 };
 //BA.debugLineNum = 115;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _onlyalertonce(boolean _once) throws Exception{
 //BA.debugLineNum = 135;BA.debugLine="Public Sub OnlyAlertOnce (Once As Boolean) As NB6";
 //BA.debugLineNum = 136;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 137;BA.debugLine="NotificationBuilder.RunMethod(\"setOnlyAlertOnce\"";
_notificationbuilder.RunMethod("setOnlyAlertOnce",new Object[]{(Object)(_once)});
 };
 //BA.debugLineNum = 139;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _progress(int _value,int _maxvalue,boolean _indeterminate) throws Exception{
 //BA.debugLineNum = 193;BA.debugLine="Public Sub Progress (Value As Int, MaxValue As Int";
 //BA.debugLineNum = 194;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 195;BA.debugLine="NotificationBuilder.RunMethod(\"setProgress\", Arr";
_notificationbuilder.RunMethod("setProgress",new Object[]{(Object)(_maxvalue),(Object)(_value),(Object)(_indeterminate)});
 };
 //BA.debugLineNum = 197;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 198;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _setdefaults(boolean _sound,boolean _light,boolean _vibrate) throws Exception{
int _currentdefaults = 0;
 //BA.debugLineNum = 210;BA.debugLine="Public Sub SetDefaults (Sound As Boolean, Light As";
 //BA.debugLineNum = 211;BA.debugLine="If IsOld Then";
if (_isold()) { 
 //BA.debugLineNum = 212;BA.debugLine="OldNotification.Sound = Sound";
_oldnotification.setSound(_sound);
 //BA.debugLineNum = 213;BA.debugLine="OldNotification.Light = Light";
_oldnotification.setLight(_light);
 //BA.debugLineNum = 214;BA.debugLine="OldNotification.Vibrate = Vibrate";
_oldnotification.setVibrate(_vibrate);
 }else {
 //BA.debugLineNum = 216;BA.debugLine="If IsChannel Then";
if (_ischannel()) { 
 //BA.debugLineNum = 217;BA.debugLine="Channel.RunMethod(\"enableLights\", Array(Light))";
_channel.RunMethod("enableLights",new Object[]{(Object)(_light)});
 //BA.debugLineNum = 218;BA.debugLine="Channel.RunMethod(\"enableVibration\", Array(Vibr";
_channel.RunMethod("enableVibration",new Object[]{(Object)(_vibrate)});
 }else {
 //BA.debugLineNum = 220;BA.debugLine="Dim CurrentDefaults As Int";
_currentdefaults = 0;
 //BA.debugLineNum = 221;BA.debugLine="If Sound Then CurrentDefaults = 1";
if (_sound) { 
_currentdefaults = (int) (1);};
 //BA.debugLineNum = 222;BA.debugLine="If Vibrate Then CurrentDefaults = Bit.Or(Curren";
if (_vibrate) { 
_currentdefaults = __c.Bit.Or(_currentdefaults,(int) (2));};
 //BA.debugLineNum = 223;BA.debugLine="If Light Then CurrentDefaults = Bit.Or(CurrentD";
if (_light) { 
_currentdefaults = __c.Bit.Or(_currentdefaults,(int) (4));};
 //BA.debugLineNum = 224;BA.debugLine="NotificationBuilder.RunMethod(\"setDefaults\", Ar";
_notificationbuilder.RunMethod("setDefaults",new Object[]{(Object)(_currentdefaults)});
 };
 };
 //BA.debugLineNum = 227;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 228;BA.debugLine="End Sub";
return null;
}
public String  _setstyle(String _stylename,anywheresoftware.b4a.objects.collections.Map _props) throws Exception{
anywheresoftware.b4j.object.JavaObject _style = null;
String _method = "";
Object _value = null;
 //BA.debugLineNum = 279;BA.debugLine="Private Sub SetStyle(StyleName As String, Props As";
 //BA.debugLineNum = 280;BA.debugLine="Dim style As JavaObject";
_style = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 281;BA.debugLine="style.InitializeNewInstance(StyleName, Null)";
_style.InitializeNewInstance(_stylename,(Object[])(__c.Null));
 //BA.debugLineNum = 282;BA.debugLine="For Each method As String In Props.Keys";
{
final anywheresoftware.b4a.BA.IterableList group3 = _props.Keys();
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_method = BA.ObjectToString(group3.Get(index3));
 //BA.debugLineNum = 283;BA.debugLine="Dim value As Object = Props.Get(method)";
_value = _props.Get((Object)(_method));
 //BA.debugLineNum = 284;BA.debugLine="If value <> Null Then";
if (_value!= null) { 
 //BA.debugLineNum = 285;BA.debugLine="style.RunMethod(method, Array(value))";
_style.RunMethod(_method,new Object[]{_value});
 };
 }
};
 //BA.debugLineNum = 288;BA.debugLine="NotificationBuilder.RunMethod(\"setStyle\", Array(s";
_notificationbuilder.RunMethod("setStyle",new Object[]{(Object)(_style.getObject())});
 //BA.debugLineNum = 289;BA.debugLine="End Sub";
return "";
}
public bwsi.PumpOperations.nb6  _showwhen(long _time) throws Exception{
 //BA.debugLineNum = 181;BA.debugLine="Public Sub ShowWhen (Time As Long) As NB6";
 //BA.debugLineNum = 182;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 183;BA.debugLine="NotificationBuilder.RunMethod(\"setShowWhen\", Arr";
_notificationbuilder.RunMethod("setShowWhen",new Object[]{(Object)(__c.True)});
 //BA.debugLineNum = 184;BA.debugLine="NotificationBuilder.RunMethod(\"setWhen\", Array(T";
_notificationbuilder.RunMethod("setWhen",new Object[]{(Object)(_time)});
 };
 //BA.debugLineNum = 186;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 187;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _smallicon(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _icon) throws Exception{
 //BA.debugLineNum = 119;BA.debugLine="Public Sub SmallIcon (Icon As Bitmap) As NB6";
 //BA.debugLineNum = 120;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 121;BA.debugLine="NotificationBuilder.RunMethod(\"setSmallIcon\", Ar";
_notificationbuilder.RunMethod("setSmallIcon",new Object[]{_createiconfrombitmap(_icon)});
 };
 //BA.debugLineNum = 123;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 124;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _subtext(Object _text) throws Exception{
 //BA.debugLineNum = 201;BA.debugLine="Public Sub SubText (Text As Object) As NB6";
 //BA.debugLineNum = 202;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 203;BA.debugLine="NotificationBuilder.RunMethod(\"setSubText\", Arra";
_notificationbuilder.RunMethod("setSubText",new Object[]{_text});
 };
 //BA.debugLineNum = 205;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
return null;
}
public bwsi.PumpOperations.nb6  _visibility(String _value) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
int _i = 0;
 //BA.debugLineNum = 249;BA.debugLine="Public Sub Visibility (Value As String) As NB6";
 //BA.debugLineNum = 250;BA.debugLine="If IsBuilder Then";
if (_isbuilder()) { 
 //BA.debugLineNum = 251;BA.debugLine="Dim m As Map = CreateMap(\"PUBLIC\": 1, \"SECRET\":";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = __c.createMap(new Object[] {(Object)("PUBLIC"),(Object)(1),(Object)("SECRET"),(Object)(-1),(Object)("PRIVATE"),(Object)(0)});
 //BA.debugLineNum = 252;BA.debugLine="Dim i As Int = m.Get(Value)";
_i = (int)(BA.ObjectToNumber(_m.Get((Object)(_value))));
 //BA.debugLineNum = 253;BA.debugLine="NotificationBuilder.RunMethod(\"setVisibility\", A";
_notificationbuilder.RunMethod("setVisibility",new Object[]{(Object)(_i)});
 };
 //BA.debugLineNum = 255;BA.debugLine="Return Me";
if (true) return (bwsi.PumpOperations.nb6)(this);
 //BA.debugLineNum = 256;BA.debugLine="End Sub";
return null;
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}

public int getTargetSDK(){
Context context = BA.applicationContext;
int targetSdkVersion = context.getApplicationInfo().targetSdkVersion;
return targetSdkVersion;
}
}
