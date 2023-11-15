package bwsi.PumpOperations;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class b4xbitset extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "bwsi.PumpOperations.b4xbitset");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", bwsi.PumpOperations.b4xbitset.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public int[] _data = null;
public int _msize = 0;
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
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 5;BA.debugLine="Private data() As Int";
_data = new int[(int) (0)];
;
 //BA.debugLineNum = 7;BA.debugLine="Private mSize As Int";
_msize = 0;
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public String  _clear() throws Exception{
 //BA.debugLineNum = 53;BA.debugLine="Public Sub Clear";
 //BA.debugLineNum = 57;BA.debugLine="Dim data(Bit.ShiftRight(mSize, 5) + 1) As Int";
_data = new int[(int) (__c.Bit.ShiftRight(_msize,(int) (5))+1)];
;
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public boolean  _get(int _index) throws Exception{
int _dindex = 0;
int _offset = 0;
int _blockvalue = 0;
 //BA.debugLineNum = 38;BA.debugLine="Public Sub Get(Index As Int) As Boolean";
 //BA.debugLineNum = 39;BA.debugLine="Dim dindex As Int = Bit.ShiftRight(Index, 5)";
_dindex = __c.Bit.ShiftRight(_index,(int) (5));
 //BA.debugLineNum = 40;BA.debugLine="Dim offset As Int = Bit.And(0x0000001f, Index)";
_offset = __c.Bit.And((int) (0x0000001f),_index);
 //BA.debugLineNum = 44;BA.debugLine="Dim BlockValue As Int = data(dindex)";
_blockvalue = _data[_dindex];
 //BA.debugLineNum = 46;BA.debugLine="Return Bit.And(BlockValue, Bit.ShiftLeft(1, offse";
if (true) return __c.Bit.And(_blockvalue,__c.Bit.ShiftLeft((int) (1),_offset))!=0;
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return false;
}
public int  _getsize() throws Exception{
 //BA.debugLineNum = 49;BA.debugLine="Public Sub getSize As Int";
 //BA.debugLineNum = 50;BA.debugLine="Return mSize";
if (true) return _msize;
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return 0;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,int _size) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 10;BA.debugLine="Public Sub Initialize (Size As Int)";
 //BA.debugLineNum = 11;BA.debugLine="mSize = Size";
_msize = _size;
 //BA.debugLineNum = 12;BA.debugLine="Clear";
_clear();
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public String  _set(int _index,boolean _value) throws Exception{
int _dindex = 0;
int _offset = 0;
int _blockvalue = 0;
int _newblockvalue = 0;
 //BA.debugLineNum = 16;BA.debugLine="Public Sub Set(Index As Int, Value As Boolean)";
 //BA.debugLineNum = 17;BA.debugLine="Dim dindex As Int = Bit.ShiftRight(Index, 5)";
_dindex = __c.Bit.ShiftRight(_index,(int) (5));
 //BA.debugLineNum = 18;BA.debugLine="Dim offset As Int = Bit.And(0x0000001f, Index)";
_offset = __c.Bit.And((int) (0x0000001f),_index);
 //BA.debugLineNum = 22;BA.debugLine="Dim BlockValue As Int = data(dindex)";
_blockvalue = _data[_dindex];
 //BA.debugLineNum = 24;BA.debugLine="Dim NewBlockValue As Int";
_newblockvalue = 0;
 //BA.debugLineNum = 25;BA.debugLine="If Value Then";
if (_value) { 
 //BA.debugLineNum = 26;BA.debugLine="NewBlockValue = Bit.Or(BlockValue, Bit.ShiftLeft";
_newblockvalue = __c.Bit.Or(_blockvalue,__c.Bit.ShiftLeft((int) (1),_offset));
 }else {
 //BA.debugLineNum = 28;BA.debugLine="NewBlockValue = Bit.And(BlockValue, Bit.Not(Bit.";
_newblockvalue = __c.Bit.And(_blockvalue,__c.Bit.Not(__c.Bit.ShiftLeft((int) (1),_offset)));
 };
 //BA.debugLineNum = 33;BA.debugLine="data(dindex) = NewBlockValue";
_data[_dindex] = _newblockvalue;
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
