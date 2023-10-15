package bwsi.PumpOperations;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class validation {
private static validation mostCurrent = new validation();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
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
public bwsi.PumpOperations.mainscreen _mainscreen = null;
public bwsi.PumpOperations.miscfunctions _miscfunctions = null;
public bwsi.PumpOperations.myfunctions _myfunctions = null;
public bwsi.PumpOperations.myscale _myscale = null;
public bwsi.PumpOperations.starter _starter = null;
public bwsi.PumpOperations.httputils2service _httputils2service = null;
public bwsi.PumpOperations.b4xcollections _b4xcollections = null;
public static String  _arraytype(anywheresoftware.b4a.BA _ba,Object _var) throws Exception{
String _res = "";
String _vartype = "";
String _secondchar = "";
 //BA.debugLineNum = 95;BA.debugLine="Public Sub ArrayType(Var As Object) As String";
 //BA.debugLineNum = 96;BA.debugLine="Dim Res As String";
_res = "";
 //BA.debugLineNum = 98;BA.debugLine="Dim VarType As String = GetType(Var)";
_vartype = anywheresoftware.b4a.keywords.Common.GetType(_var);
 //BA.debugLineNum = 100;BA.debugLine="If VarType.StartsWith(\"[\") Then";
if (_vartype.startsWith("[")) { 
 //BA.debugLineNum = 102;BA.debugLine="Dim SecondChar As String = VarType.SubString2(1,";
_secondchar = _vartype.substring((int) (1),(int) (2));
 //BA.debugLineNum = 103;BA.debugLine="Select Case SecondChar";
switch (BA.switchObjectToInt(_secondchar,"B","S","I","J","F","D","C","L")) {
case 0: {
 //BA.debugLineNum = 105;BA.debugLine="Res = \"Byte\"";
_res = "Byte";
 break; }
case 1: {
 //BA.debugLineNum = 107;BA.debugLine="Res = \"Short\"";
_res = "Short";
 break; }
case 2: {
 //BA.debugLineNum = 109;BA.debugLine="Res = \"Int\"";
_res = "Int";
 break; }
case 3: {
 //BA.debugLineNum = 111;BA.debugLine="Res = \"Long\"";
_res = "Long";
 break; }
case 4: {
 //BA.debugLineNum = 113;BA.debugLine="Res = \"Float\"";
_res = "Float";
 break; }
case 5: {
 //BA.debugLineNum = 115;BA.debugLine="Res = \"Double\"";
_res = "Double";
 break; }
case 6: {
 //BA.debugLineNum = 117;BA.debugLine="Res = \"Char\"";
_res = "Char";
 break; }
case 7: {
 //BA.debugLineNum = 119;BA.debugLine="If VarType.Contains(\"String\") Then";
if (_vartype.contains("String")) { 
 //BA.debugLineNum = 120;BA.debugLine="Res = \"String\"";
_res = "String";
 }else {
 //BA.debugLineNum = 122;BA.debugLine="Res = \"Object\"";
_res = "Object";
 };
 break; }
default: {
 //BA.debugLineNum = 125;BA.debugLine="Res = \"\"";
_res = "";
 break; }
}
;
 };
 //BA.debugLineNum = 130;BA.debugLine="Return Res";
if (true) return _res;
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static boolean  _donotmatch(anywheresoftware.b4a.BA _ba,String _valuetocheck,String _valuetocheck2) throws Exception{
 //BA.debugLineNum = 80;BA.debugLine="Sub DoNotMatch(ValueToCheck As String, ValueToChec";
 //BA.debugLineNum = 81;BA.debugLine="ValueToCheck = ValueToCheck.Trim";
_valuetocheck = _valuetocheck.trim();
 //BA.debugLineNum = 82;BA.debugLine="ValueToCheck2 = ValueToCheck2.Trim";
_valuetocheck2 = _valuetocheck2.trim();
 //BA.debugLineNum = 83;BA.debugLine="If ValueToCheck <> ValueToCheck2 Then";
if ((_valuetocheck).equals(_valuetocheck2) == false) { 
 //BA.debugLineNum = 84;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 86;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 88;BA.debugLine="End Sub";
return false;
}
public static boolean  _isactivity(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _v) throws Exception{
 //BA.debugLineNum = 232;BA.debugLine="Sub IsActivity(v As View) As Boolean";
 //BA.debugLineNum = 233;BA.debugLine="Try";
try { //BA.debugLineNum = 234;BA.debugLine="v.Left = 10dip";
_v.setLeft(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 235;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 } 
       catch (Exception e5) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e5); //BA.debugLineNum = 237;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 239;BA.debugLine="End Sub";
return false;
}
public static boolean  _isarray(anywheresoftware.b4a.BA _ba,Object _var) throws Exception{
String _vartype = "";
 //BA.debugLineNum = 90;BA.debugLine="Public Sub IsArray(Var As Object) As Boolean";
 //BA.debugLineNum = 91;BA.debugLine="Dim VarType As String = GetType(Var)";
_vartype = anywheresoftware.b4a.keywords.Common.GetType(_var);
 //BA.debugLineNum = 92;BA.debugLine="Return VarType.StartsWith(\"[\")";
if (true) return _vartype.startsWith("[");
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return false;
}
public static boolean  _isblank(anywheresoftware.b4a.BA _ba,String _valuetocheck) throws Exception{
 //BA.debugLineNum = 71;BA.debugLine="Sub IsBlank(ValueToCheck As String) As Boolean";
 //BA.debugLineNum = 72;BA.debugLine="ValueToCheck = ValueToCheck.Trim";
_valuetocheck = _valuetocheck.trim();
 //BA.debugLineNum = 73;BA.debugLine="If ValueToCheck.Length = 0 Then";
if (_valuetocheck.length()==0) { 
 //BA.debugLineNum = 74;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 76;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 78;BA.debugLine="End Sub";
return false;
}
public static boolean  _isdate(anywheresoftware.b4a.BA _ba,String _date) throws Exception{
 //BA.debugLineNum = 146;BA.debugLine="Public Sub IsDate(Date As String) As Boolean";
 //BA.debugLineNum = 147;BA.debugLine="Return Regex.IsMatch(\"\\d{4}-\\d{1,2}-\\d{1,2}\",Date";
if (true) return anywheresoftware.b4a.keywords.Common.Regex.IsMatch("\\d{4}-\\d{1,2}-\\d{1,2}",_date);
 //BA.debugLineNum = 148;BA.debugLine="End Sub";
return false;
}
public static boolean  _isdatetime(anywheresoftware.b4a.BA _ba,String _sdatetime) throws Exception{
 //BA.debugLineNum = 150;BA.debugLine="Public Sub IsDateTime(sDateTime As String) As Bool";
 //BA.debugLineNum = 151;BA.debugLine="Return Regex.IsMatch(\"\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,";
if (true) return anywheresoftware.b4a.keywords.Common.Regex.IsMatch("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}",_sdatetime);
 //BA.debugLineNum = 152;BA.debugLine="End Sub";
return false;
}
public static boolean  _isemail(anywheresoftware.b4a.BA _ba,String _emailaddress) throws Exception{
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matchemail = null;
 //BA.debugLineNum = 9;BA.debugLine="Public Sub IsEmail(EmailAddress As String) As Bool";
 //BA.debugLineNum = 11;BA.debugLine="Dim MatchEmail As Matcher = Regex.Matcher(\"^(?i)[";
_matchemail = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
_matchemail = anywheresoftware.b4a.keywords.Common.Regex.Matcher("^(?i)[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])$",_emailaddress);
 //BA.debugLineNum = 13;BA.debugLine="If MatchEmail.Find = True Then";
if (_matchemail.Find()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 14;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 16;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return false;
}
public static boolean  _islist(anywheresoftware.b4a.BA _ba,Object _data) throws Exception{
 //BA.debugLineNum = 221;BA.debugLine="Public Sub IsList(Data As Object) As Boolean";
 //BA.debugLineNum = 223;BA.debugLine="If GetType(Data) = \"java.util.ArrayList\" Then";
if ((anywheresoftware.b4a.keywords.Common.GetType(_data)).equals("java.util.ArrayList")) { 
 //BA.debugLineNum = 224;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 226;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 229;BA.debugLine="End Sub";
return false;
}
public static boolean  _islocation(anywheresoftware.b4a.BA _ba,String _location) throws Exception{
 //BA.debugLineNum = 134;BA.debugLine="Public Sub IsLocation(Location As String) As Boole";
 //BA.debugLineNum = 135;BA.debugLine="Return Regex.IsMatch(\"^-?\\d+(\\.\\d+)?+,-?\\d+(\\.\\d+";
if (true) return anywheresoftware.b4a.keywords.Common.Regex.IsMatch("^-?\\d+(\\.\\d+)?+,-?\\d+(\\.\\d+)?+$",_location);
 //BA.debugLineNum = 136;BA.debugLine="End Sub";
return false;
}
public static boolean  _ismap(anywheresoftware.b4a.BA _ba,Object _data) throws Exception{
 //BA.debugLineNum = 211;BA.debugLine="Public Sub IsMap(Data As Object) As Boolean";
 //BA.debugLineNum = 213;BA.debugLine="If GetType(Data) = \"anywheresoftware.b4a.objects.";
if ((anywheresoftware.b4a.keywords.Common.GetType(_data)).equals("anywheresoftware.b4a.objects.collections.Map$MyMap")) { 
 //BA.debugLineNum = 214;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 216;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 219;BA.debugLine="End Sub";
return false;
}
public static boolean  _ismobilenumber(anywheresoftware.b4a.BA _ba,String _input) throws Exception{
String _ph = "";
 //BA.debugLineNum = 44;BA.debugLine="Public Sub IsMobileNumber(Input As String) As Bool";
 //BA.debugLineNum = 46;BA.debugLine="Dim ph As String";
_ph = "";
 //BA.debugLineNum = 47;BA.debugLine="ph = Input";
_ph = _input;
 //BA.debugLineNum = 48;BA.debugLine="If ph.StartsWith(\"0\") Then";
if (_ph.startsWith("0")) { 
 //BA.debugLineNum = 49;BA.debugLine="ph = ph.SubString(1)";
_ph = _ph.substring((int) (1));
 };
 //BA.debugLineNum = 52;BA.debugLine="Try";
try { //BA.debugLineNum = 53;BA.debugLine="Return Regex.IsMatch(\"^\\d{10,16}$\",ph)";
if (true) return anywheresoftware.b4a.keywords.Common.Regex.IsMatch("^\\d{10,16}$",_ph);
 } 
       catch (Exception e9) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e9); //BA.debugLineNum = 55;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return false;
}
public static boolean  _isnationalid(anywheresoftware.b4a.BA _ba,String _id) throws Exception{
 //BA.debugLineNum = 138;BA.debugLine="Public Sub IsNationalID(ID As String) As Boolean";
 //BA.debugLineNum = 139;BA.debugLine="Return Regex.IsMatch(\"^\\d{9,14}$\",ID)";
if (true) return anywheresoftware.b4a.keywords.Common.Regex.IsMatch("^\\d{9,14}$",_id);
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return false;
}
public static boolean  _isnull(anywheresoftware.b4a.BA _ba,Object _data) throws Exception{
String _stype = "";
 //BA.debugLineNum = 194;BA.debugLine="Public Sub IsNull(Data As Object) As Boolean";
 //BA.debugLineNum = 196;BA.debugLine="Dim sType As String";
_stype = "";
 //BA.debugLineNum = 197;BA.debugLine="Try";
try { //BA.debugLineNum = 198;BA.debugLine="sType	=	GetType(Data)";
_stype = anywheresoftware.b4a.keywords.Common.GetType(_data);
 } 
       catch (Exception e5) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e5); //BA.debugLineNum = 200;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 203;BA.debugLine="If sType.ToLowerCase = \"null\" Then";
if ((_stype.toLowerCase()).equals("null")) { 
 //BA.debugLineNum = 204;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 207;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 209;BA.debugLine="End Sub";
return false;
}
public static boolean  _isnumbers(anywheresoftware.b4a.BA _ba,String _data) throws Exception{
 //BA.debugLineNum = 142;BA.debugLine="Public Sub IsNumbers(Data As String) As Boolean";
 //BA.debugLineNum = 143;BA.debugLine="Return IsNumber(Data)";
if (true) return anywheresoftware.b4a.keywords.Common.IsNumber(_data);
 //BA.debugLineNum = 144;BA.debugLine="End Sub";
return false;
}
public static boolean  _ispostalcode(anywheresoftware.b4a.BA _ba,String _code) throws Exception{
 //BA.debugLineNum = 32;BA.debugLine="Public Sub IsPostalCode(Code As String) As Boolean";
 //BA.debugLineNum = 34;BA.debugLine="Try";
try { //BA.debugLineNum = 35;BA.debugLine="Return Regex.IsMatch(\"^[0-9]{2,10}$\",Code)";
if (true) return anywheresoftware.b4a.keywords.Common.Regex.IsMatch("^[0-9]{2,10}$",_code);
 } 
       catch (Exception e4) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e4); //BA.debugLineNum = 37;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return false;
}
public static boolean  _issame(anywheresoftware.b4a.BA _ba,String _valuetocheck,String _valuetocheck2) throws Exception{
 //BA.debugLineNum = 61;BA.debugLine="Sub IsSame(ValueToCheck As String, ValueToCheck2 A";
 //BA.debugLineNum = 62;BA.debugLine="ValueToCheck = ValueToCheck.Trim";
_valuetocheck = _valuetocheck.trim();
 //BA.debugLineNum = 63;BA.debugLine="ValueToCheck2 = ValueToCheck2.Trim";
_valuetocheck2 = _valuetocheck2.trim();
 //BA.debugLineNum = 64;BA.debugLine="If ValueToCheck = ValueToCheck2 Then";
if ((_valuetocheck).equals(_valuetocheck2)) { 
 //BA.debugLineNum = 65;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 67;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return false;
}
public static boolean  _istime(anywheresoftware.b4a.BA _ba,String _stime) throws Exception{
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
int _ihrs = 0;
int _imins = 0;
 //BA.debugLineNum = 154;BA.debugLine="Public Sub IsTime(sTime As String) As Boolean";
 //BA.debugLineNum = 155;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 156;BA.debugLine="Matcher1 = Regex.Matcher(\"(\\d\\d):(\\d\\d)\",sTime)";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher("(\\d\\d):(\\d\\d)",_stime);
 //BA.debugLineNum = 158;BA.debugLine="If Matcher1.Find Then";
if (_matcher1.Find()) { 
 //BA.debugLineNum = 159;BA.debugLine="Dim iHrs, iMins As Int";
_ihrs = 0;
_imins = 0;
 //BA.debugLineNum = 160;BA.debugLine="iHrs = Matcher1.Group(1)";
_ihrs = (int)(Double.parseDouble(_matcher1.Group((int) (1))));
 //BA.debugLineNum = 161;BA.debugLine="iMins = Matcher1.Group(2)";
_imins = (int)(Double.parseDouble(_matcher1.Group((int) (2))));
 //BA.debugLineNum = 162;BA.debugLine="If iHrs < 0 Or iHrs > 23 Then Return False";
if (_ihrs<0 || _ihrs>23) { 
if (true) return anywheresoftware.b4a.keywords.Common.False;};
 //BA.debugLineNum = 163;BA.debugLine="If iMins < 0 Or iMins > 59	Then Return False";
if (_imins<0 || _imins>59) { 
if (true) return anywheresoftware.b4a.keywords.Common.False;};
 //BA.debugLineNum = 164;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 166;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
return false;
}
public static boolean  _isurl(anywheresoftware.b4a.BA _ba,String _url) throws Exception{
String _pattern = "";
 //BA.debugLineNum = 21;BA.debugLine="Sub IsUrl(Url As String) As Boolean";
 //BA.debugLineNum = 23;BA.debugLine="Dim pattern As String = \"http(s)?://([\\w+?\\.\\w+])";
_pattern = "http(s)?://([\\w+?\\.\\w+])+([a-zA-Z0-9\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)_\\-\\=\\+\\\\\\/\\?\\.\\:\\;\\'\\,]*)?";
 //BA.debugLineNum = 24;BA.debugLine="If Regex.IsMatch(pattern, Url) = True Then";
if (anywheresoftware.b4a.keywords.Common.Regex.IsMatch(_pattern,_url)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 25;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 27;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return false;
}
public static int  _parseint(anywheresoftware.b4a.BA _ba,String _str) throws Exception{
int _int2 = 0;
 //BA.debugLineNum = 241;BA.debugLine="Sub ParseInt(Str As String) As Int";
 //BA.debugLineNum = 242;BA.debugLine="Dim int2 As Int";
_int2 = 0;
 //BA.debugLineNum = 243;BA.debugLine="int2 = Str";
_int2 = (int)(Double.parseDouble(_str));
 //BA.debugLineNum = 244;BA.debugLine="Return int2";
if (true) return _int2;
 //BA.debugLineNum = 245;BA.debugLine="End Sub";
return 0;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
}
