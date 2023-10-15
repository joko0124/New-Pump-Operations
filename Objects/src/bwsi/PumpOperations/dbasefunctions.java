package bwsi.PumpOperations;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class dbasefunctions {
private static dbasefunctions mostCurrent = new dbasefunctions();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.sql.SQL.CursorWrapper _rstemp = null;
public static adr.stringfunctions.stringfunctions _sf = null;
public b4a.example.dateutils _dateutils = null;
public bwsi.PumpOperations.main _main = null;
public bwsi.PumpOperations.actnewproduction _actnewproduction = null;
public bwsi.PumpOperations.actpumpoff _actpumpoff = null;
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
public bwsi.PumpOperations.validation _validation = null;
public bwsi.PumpOperations.httputils2service _httputils2service = null;
public bwsi.PumpOperations.b4xcollections _b4xcollections = null;
public static String  _cleartable(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _tablename) throws Exception{
 //BA.debugLineNum = 1374;BA.debugLine="Public Sub ClearTable(SQL As SQL, TableName As Str";
 //BA.debugLineNum = 1375;BA.debugLine="If TableExists(SQL, TableName) = False Then";
if (_tableexists(_ba,_sql,_tablename)==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 1376;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1378;BA.debugLine="SQL.ExecNonQuery(\"DELETE FROM \" & TableName)";
_sql.ExecNonQuery("DELETE FROM "+_tablename);
 //BA.debugLineNum = 1379;BA.debugLine="End Sub";
return "";
}
public static double  _computewaterloss(anywheresoftware.b4a.BA _ba,String _spipetype,String _spipesize,int _ipsi,int _iminute) throws Exception{
anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper _bwaterloss = null;
double _lwaterloss = 0;
double _constantvalue = 0;
 //BA.debugLineNum = 1221;BA.debugLine="Public Sub ComputeWaterLoss (sPipeType As String,";
 //BA.debugLineNum = 1222;BA.debugLine="Dim bWaterLoss As BigDecimal";
_bwaterloss = new anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper();
 //BA.debugLineNum = 1223;BA.debugLine="Dim lWaterLoss As Double";
_lwaterloss = 0;
 //BA.debugLineNum = 1224;BA.debugLine="Dim ConstantValue As Double";
_constantvalue = 0;
 //BA.debugLineNum = 1226;BA.debugLine="Try";
try { //BA.debugLineNum = 1227;BA.debugLine="If iPSI > 30 Then Return 0";
if (_ipsi>30) { 
if (true) return 0;};
 //BA.debugLineNum = 1228;BA.debugLine="Starter.strCriteria = \"SELECT WaterLoss FROM Lea";
mostCurrent._starter._strcriteria /*String*/  = "SELECT WaterLoss FROM LeakVolumeConstant WHERE PipeType='"+_spipetype+"' "+"AND PipeSize = '"+_spipesize+"' "+"AND PSI = "+BA.NumberToString(_ipsi);
 //BA.debugLineNum = 1232;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("09830411",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 1234;BA.debugLine="ConstantValue = Starter.DBCon.ExecQuerySingleRes";
_constantvalue = (double)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT WaterLoss FROM LeakVolumeConstant WHERE PipeType = '"+_spipetype+"' "+"AND PipeSize = '"+_spipesize+"' "+"AND PSI = "+BA.NumberToString(_ipsi))));
 //BA.debugLineNum = 1238;BA.debugLine="lWaterLoss = ConstantValue * iMinute";
_lwaterloss = _constantvalue*_iminute;
 } 
       catch (Exception e11) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e11); //BA.debugLineNum = 1240;BA.debugLine="lWaterLoss = 0";
_lwaterloss = 0;
 //BA.debugLineNum = 1241;BA.debugLine="ToastMessageShow($\"Unable to fetch Constant Wate";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Constant Water Loss due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1242;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09830421",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 1244;BA.debugLine="bWaterLoss.Initialize(lWaterLoss)";
_bwaterloss.Initialize(BA.NumberToString(_lwaterloss));
 //BA.debugLineNum = 1245;BA.debugLine="bWaterLoss = RoundBD(bWaterLoss,2)";
_bwaterloss = _roundbd(_ba,_bwaterloss,(int) (2));
 //BA.debugLineNum = 1247;BA.debugLine="Return bWaterLoss";
if (true) return (double)(BA.ObjectToNumber(_bwaterloss));
 //BA.debugLineNum = 1248;BA.debugLine="End Sub";
return 0;
}
public static double  _getavepsi(anywheresoftware.b4a.BA _ba,int _dtranheaderid) throws Exception{
double _davepsi = 0;
 //BA.debugLineNum = 1297;BA.debugLine="Public Sub GetAvePSI (dTranHeaderID As Int) As Dou";
 //BA.debugLineNum = 1298;BA.debugLine="Dim dAvePSI As Double";
_davepsi = 0;
 //BA.debugLineNum = 1300;BA.debugLine="Try";
try { //BA.debugLineNum = 1301;BA.debugLine="dAvePSI = 0";
_davepsi = 0;
 //BA.debugLineNum = 1303;BA.debugLine="Starter.strCriteria = \"SELECT avg(PSIReading) FR";
mostCurrent._starter._strcriteria /*String*/  = "SELECT avg(PSIReading) FROM PressureRdgDetails WHERE HeaderID = "+BA.NumberToString(_dtranheaderid)+" "+"GROUP BY HeaderID";
 //BA.debugLineNum = 1306;BA.debugLine="dAvePSI = Starter.DBCon.ExecQuerySingleResult(St";
_davepsi = (double)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 1307;BA.debugLine="LogColor(Starter.strCriteria, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("010092554",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 } 
       catch (Exception e8) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e8); //BA.debugLineNum = 1310;BA.debugLine="dAvePSI = 0";
_davepsi = 0;
 //BA.debugLineNum = 1311;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("010092558",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 1312;BA.debugLine="ToastMessageShow($\"Unable to fetch Minimum Press";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Minimum Pressure Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 1314;BA.debugLine="Return dAvePSI";
if (true) return _davepsi;
 //BA.debugLineNum = 1315;BA.debugLine="End Sub";
return 0;
}
public static String  _getcodebyid(anywheresoftware.b4a.BA _ba,String _sretfield,String _stablename,String _scodetocompare,int _icodecomparison) throws Exception{
String _sretval = "";
 //BA.debugLineNum = 311;BA.debugLine="Public Sub GetCodeByID (sRetField As String, sTabl";
 //BA.debugLineNum = 312;BA.debugLine="Dim sRetval As String";
_sretval = "";
 //BA.debugLineNum = 313;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 314;BA.debugLine="Try";
try { //BA.debugLineNum = 315;BA.debugLine="Starter.strCriteria = \"SELECT \" & sRetField & \"";
mostCurrent._starter._strcriteria /*String*/  = "SELECT "+_sretfield+" FROM "+_stablename+" WHERE "+_scodetocompare+" = "+BA.NumberToString(_icodecomparison);
 //BA.debugLineNum = 316;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("08192005",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 318;BA.debugLine="sRetval = Starter.DBCon.ExecQuerySingleResult(St";
_sretval = mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ );
 } 
       catch (Exception e8) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e8); //BA.debugLineNum = 320;BA.debugLine="ToastMessageShow($\"Unable to fetch Pump Last Rea";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Pump Last Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 321;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08192010",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 322;BA.debugLine="sRetval = \"\"";
_sretval = "";
 };
 //BA.debugLineNum = 324;BA.debugLine="Return sRetval";
if (true) return _sretval;
 //BA.debugLineNum = 325;BA.debugLine="End Sub";
return "";
}
public static int  _getdbversionno(anywheresoftware.b4a.BA _ba) throws Exception{
int _iretval = 0;
 //BA.debugLineNum = 32;BA.debugLine="Public Sub GetDBVersionNo () As Int";
 //BA.debugLineNum = 34;BA.debugLine="Dim iRetVal As Int";
_iretval = 0;
 //BA.debugLineNum = 36;BA.debugLine="Try";
try { //BA.debugLineNum = 37;BA.debugLine="Starter.strCriteria = \"SELECT DBVersionNo FROM t";
mostCurrent._starter._strcriteria /*String*/  = "SELECT DBVersionNo FROM tblSysParam";
 //BA.debugLineNum = 38;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("07274502",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 40;BA.debugLine="iRetVal = Starter.DBCon.ExecQuerySingleResult(St";
_iretval = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 } 
       catch (Exception e7) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e7); //BA.debugLineNum = 42;BA.debugLine="ToastMessageShow($\"Unable to fetch Database Veri";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Database Verion Number due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 43;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("07274507",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 44;BA.debugLine="iRetVal = 0";
_iretval = (int) (0);
 };
 //BA.debugLineNum = 46;BA.debugLine="Return iRetVal";
if (true) return _iretval;
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return 0;
}
public static String  _getdrainpipesize(anywheresoftware.b4a.BA _ba,int _ipumpid) throws Exception{
String _sretval = "";
 //BA.debugLineNum = 173;BA.debugLine="Public Sub GetDrainPipeSize (iPumpID As Int) As St";
 //BA.debugLineNum = 175;BA.debugLine="Dim sRetval As String";
_sretval = "";
 //BA.debugLineNum = 177;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 179;BA.debugLine="Try";
try { //BA.debugLineNum = 180;BA.debugLine="Starter.strCriteria = \"SELECT DrainPipeSize FROM";
mostCurrent._starter._strcriteria /*String*/  = "SELECT DrainPipeSize FROM tblPumpStation WHERE StationID = "+BA.NumberToString(_ipumpid);
 //BA.debugLineNum = 181;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("07798792",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 183;BA.debugLine="sRetval = Starter.DBCon.ExecQuerySingleResult(St";
_sretval = mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ );
 } 
       catch (Exception e8) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e8); //BA.debugLineNum = 185;BA.debugLine="ToastMessageShow($\"Unable to fetch Pump Drain Pi";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Pump Drain Pipe Size due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 186;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("07798797",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 187;BA.debugLine="sRetval = \"\"";
_sretval = "";
 };
 //BA.debugLineNum = 189;BA.debugLine="Return sRetval";
if (true) return _sretval;
 //BA.debugLineNum = 191;BA.debugLine="End Sub";
return "";
}
public static String  _getdrainpipetype(anywheresoftware.b4a.BA _ba,int _ipumpid) throws Exception{
String _sretval = "";
 //BA.debugLineNum = 153;BA.debugLine="Public Sub GetDrainPipeType (iPumpID As Int) As St";
 //BA.debugLineNum = 155;BA.debugLine="Dim sRetval As String";
_sretval = "";
 //BA.debugLineNum = 157;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 159;BA.debugLine="Try";
try { //BA.debugLineNum = 160;BA.debugLine="Starter.strCriteria = \"SELECT DrainPipeType FROM";
mostCurrent._starter._strcriteria /*String*/  = "SELECT DrainPipeType FROM tblPumpStation WHERE StationID = "+BA.NumberToString(_ipumpid);
 //BA.debugLineNum = 161;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("07733256",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 163;BA.debugLine="sRetval = Starter.DBCon.ExecQuerySingleResult(St";
_sretval = mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ );
 } 
       catch (Exception e8) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e8); //BA.debugLineNum = 165;BA.debugLine="ToastMessageShow($\"Unable to fetch Pump Drain Pi";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Pump Drain Pipe Type due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 166;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("07733261",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 167;BA.debugLine="sRetval = \"\"";
_sretval = "";
 };
 //BA.debugLineNum = 169;BA.debugLine="Return sRetval";
if (true) return _sretval;
 //BA.debugLineNum = 171;BA.debugLine="End Sub";
return "";
}
public static String  _getfirstrdgtime(anywheresoftware.b4a.BA _ba,int _itranheaderid) throws Exception{
String _rdgtime = "";
 //BA.debugLineNum = 951;BA.debugLine="Private Sub GetFirstRdgTime (iTranHeaderID As Int)";
 //BA.debugLineNum = 952;BA.debugLine="Dim RdgTime As String";
_rdgtime = "";
 //BA.debugLineNum = 953;BA.debugLine="Try";
try { //BA.debugLineNum = 954;BA.debugLine="Starter.strCriteria = \"SELECT MIN(CASE WHEN subs";
mostCurrent._starter._strcriteria /*String*/  = "SELECT MIN(CASE WHEN substr(RdgTime,1,2) = '12' And substr(RdgTime,7,2) = 'AM' Then '00' || substr(RdgTime,3,6) "+"ELSE RdgTime END) "+"FROM ProductionDetails "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 959;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("09306120",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 961;BA.debugLine="RdgTime = Starter.DBCon.ExecQuerySingleResult(St";
_rdgtime = mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ );
 //BA.debugLineNum = 962;BA.debugLine="LogColor($\"1st Rdg: \"$ & RdgTime, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("09306123",("1st Rdg: ")+_rdgtime,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 } 
       catch (Exception e8) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e8); //BA.debugLineNum = 964;BA.debugLine="RdgTime = \"\"";
_rdgtime = "";
 //BA.debugLineNum = 965;BA.debugLine="ToastMessageShow($\"Unable to fetch System Parame";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch System Parameters due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 966;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09306127",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 968;BA.debugLine="Return RdgTime";
if (true) return _rdgtime;
 //BA.debugLineNum = 969;BA.debugLine="End Sub";
return "";
}
public static int  _getheaderid(anywheresoftware.b4a.BA _ba,int _ipumpid,String _strandate) throws Exception{
int _iretval = 0;
 //BA.debugLineNum = 195;BA.debugLine="Public Sub GetHeaderID (iPumpID As Int, sTranDate";
 //BA.debugLineNum = 196;BA.debugLine="Dim iRetval As Int";
_iretval = 0;
 //BA.debugLineNum = 197;BA.debugLine="Try";
try { //BA.debugLineNum = 198;BA.debugLine="Starter.strCriteria = \"SELECT HeaderID FROM Tran";
mostCurrent._starter._strcriteria /*String*/  = "SELECT HeaderID FROM TranHeader "+"WHERE PumpID = ? "+"AND TranDate = ?";
 //BA.debugLineNum = 201;BA.debugLine="LogColor(Starter.strCriteria, Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("07864326",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 202;BA.debugLine="rsTemp = Starter.DBCon.ExecQuery2(Starter.strCri";
_rstemp = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery2(mostCurrent._starter._strcriteria /*String*/ ,new String[]{BA.NumberToString(_ipumpid),_strandate})));
 //BA.debugLineNum = 203;BA.debugLine="If rsTemp.RowCount > 0 Then";
if (_rstemp.getRowCount()>0) { 
 //BA.debugLineNum = 204;BA.debugLine="rsTemp.Position = 0";
_rstemp.setPosition((int) (0));
 //BA.debugLineNum = 205;BA.debugLine="iRetval = rsTemp.GetInt(\"HeaderID\")";
_iretval = _rstemp.GetInt("HeaderID");
 }else {
 //BA.debugLineNum = 207;BA.debugLine="iRetval = 0";
_iretval = (int) (0);
 };
 } 
       catch (Exception e13) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e13); //BA.debugLineNum = 210;BA.debugLine="ToastMessageShow($\"Unable to fetch Transaction H";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Transaction Header due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 211;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("07864336",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 212;BA.debugLine="iRetval = 0";
_iretval = (int) (0);
 };
 //BA.debugLineNum = 214;BA.debugLine="rsTemp.Close";
_rstemp.Close();
 //BA.debugLineNum = 215;BA.debugLine="Return iRetval";
if (true) return _iretval;
 //BA.debugLineNum = 216;BA.debugLine="End Sub";
return 0;
}
public static int  _getidbycode(anywheresoftware.b4a.BA _ba,String _sretfield,String _stablename,String _sfieldtocompare,String _scodecomparison) throws Exception{
int _iretval = 0;
 //BA.debugLineNum = 294;BA.debugLine="Public Sub GetIDByCode (sRetField As String, sTabl";
 //BA.debugLineNum = 295;BA.debugLine="Dim iRetval As Int";
_iretval = 0;
 //BA.debugLineNum = 296;BA.debugLine="iRetval = 0";
_iretval = (int) (0);
 //BA.debugLineNum = 297;BA.debugLine="Try";
try { //BA.debugLineNum = 298;BA.debugLine="Starter.strCriteria = \"SELECT \" & sRetField & \"";
mostCurrent._starter._strcriteria /*String*/  = "SELECT "+_sretfield+" FROM "+_stablename+" WHERE "+_sfieldtocompare+" = '"+_scodecomparison+"'";
 //BA.debugLineNum = 299;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("08126469",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 301;BA.debugLine="iRetval = Starter.DBCon.ExecQuerySingleResult(St";
_iretval = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 } 
       catch (Exception e8) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e8); //BA.debugLineNum = 303;BA.debugLine="ToastMessageShow($\"Unable to fetch ID due to \"$";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch ID due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 304;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08126474",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 305;BA.debugLine="iRetval = 0";
_iretval = (int) (0);
 };
 //BA.debugLineNum = 307;BA.debugLine="LogColor($\"Return ID: \"$ & iRetval, Colors.Yellow";
anywheresoftware.b4a.keywords.Common.LogImpl("08126477",("Return ID: ")+BA.NumberToString(_iretval),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 308;BA.debugLine="Return iRetval";
if (true) return _iretval;
 //BA.debugLineNum = 309;BA.debugLine="End Sub";
return 0;
}
public static String  _getjodesc(anywheresoftware.b4a.BA _ba,String _sjocat) throws Exception{
String _sretval = "";
 //BA.debugLineNum = 327;BA.debugLine="Public Sub GetJODesc (sJOCAt As String) As String";
 //BA.debugLineNum = 328;BA.debugLine="Dim sRetval As String";
_sretval = "";
 //BA.debugLineNum = 329;BA.debugLine="sRetval = \"\"";
_sretval = "";
 //BA.debugLineNum = 330;BA.debugLine="Try";
try { //BA.debugLineNum = 331;BA.debugLine="Starter.strCriteria = \"SELECT jo_desc FROM const";
mostCurrent._starter._strcriteria /*String*/  = "SELECT jo_desc FROM constant_jo_categories WHERE jo_code = '"+_sjocat+"'";
 //BA.debugLineNum = 332;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("08257541",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 334;BA.debugLine="sRetval = Starter.DBCon.ExecQuerySingleResult(St";
_sretval = mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ );
 } 
       catch (Exception e8) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e8); //BA.debugLineNum = 336;BA.debugLine="ToastMessageShow($\"Unable to fetch JO Category d";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch JO Category due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 337;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08257546",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 338;BA.debugLine="sRetval = \"\"";
_sretval = "";
 };
 //BA.debugLineNum = 340;BA.debugLine="Return sRetval";
if (true) return _sretval;
 //BA.debugLineNum = 341;BA.debugLine="End Sub";
return "";
}
public static double  _getlastfmreading(anywheresoftware.b4a.BA _ba,int _istationid) throws Exception{
double _dretval = 0;
 //BA.debugLineNum = 119;BA.debugLine="Public Sub GetLastFMReading(iStationID As Int) As";
 //BA.debugLineNum = 121;BA.debugLine="Dim dRetval As Double";
_dretval = 0;
 //BA.debugLineNum = 122;BA.debugLine="Try";
try { //BA.debugLineNum = 123;BA.debugLine="Starter.strCriteria = \"SELECT LastRdg FROM tblPu";
mostCurrent._starter._strcriteria /*String*/  = "SELECT LastRdg FROM tblPumpStation WHERE StationID = "+BA.NumberToString(_istationid);
 //BA.debugLineNum = 124;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("07602181",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 126;BA.debugLine="dRetval = Starter.DBCon.ExecQuerySingleResult(St";
_dretval = (double)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 } 
       catch (Exception e7) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e7); //BA.debugLineNum = 128;BA.debugLine="ToastMessageShow($\"Unable to fetch Pump Last Rea";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Pump Last Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 129;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("07602186",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 130;BA.debugLine="dRetval = 0";
_dretval = 0;
 };
 //BA.debugLineNum = 132;BA.debugLine="Return dRetval";
if (true) return _dretval;
 //BA.debugLineNum = 133;BA.debugLine="End Sub";
return 0;
}
public static double  _getlastfmreadingtransid(anywheresoftware.b4a.BA _ba,int _ireading,int _ibackflow) throws Exception{
double _dretval = 0;
 //BA.debugLineNum = 135;BA.debugLine="Public Sub GetLastFMReadingTransID(iReading As Int";
 //BA.debugLineNum = 137;BA.debugLine="Dim dRetval As Double";
_dretval = 0;
 //BA.debugLineNum = 138;BA.debugLine="Try";
try { //BA.debugLineNum = 139;BA.debugLine="Starter.strCriteria = \"SELECT DetailID FROM Prod";
mostCurrent._starter._strcriteria /*String*/  = "SELECT DetailID FROM ProductionDetails "+"WHERE PresRdg = "+BA.NumberToString(_ireading)+" "+"AND PresCum < "+BA.NumberToString(_ibackflow);
 //BA.debugLineNum = 142;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("07667719",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 144;BA.debugLine="dRetval = Starter.DBCon.ExecQuerySingleResult(St";
_dretval = (double)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 } 
       catch (Exception e7) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e7); //BA.debugLineNum = 146;BA.debugLine="ToastMessageShow($\"Unable to fetch Detail Last R";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Detail Last Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 147;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("07667724",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 148;BA.debugLine="dRetval = 0";
_dretval = 0;
 };
 //BA.debugLineNum = 150;BA.debugLine="Return dRetval";
if (true) return _dretval;
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return 0;
}
public static double  _getlastpsirdg(anywheresoftware.b4a.BA _ba,int _dtranheaderid) throws Exception{
double _dpsirdg = 0;
anywheresoftware.b4a.sql.SQL.CursorWrapper _rs = null;
 //BA.debugLineNum = 1317;BA.debugLine="Public Sub GetLastPSIRdg (dTranHeaderID As Int) As";
 //BA.debugLineNum = 1318;BA.debugLine="Dim dPSIRdg As Double";
_dpsirdg = 0;
 //BA.debugLineNum = 1319;BA.debugLine="Dim RS As Cursor";
_rs = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 1320;BA.debugLine="Try";
try { //BA.debugLineNum = 1321;BA.debugLine="dPSIRdg = 0";
_dpsirdg = 0;
 //BA.debugLineNum = 1323;BA.debugLine="Starter.strCriteria = \"SELECT MAX(DetailID), PSI";
mostCurrent._starter._strcriteria /*String*/  = "SELECT MAX(DetailID), PSIReading FROM PressureRdgDetails WHERE HeaderID = "+BA.NumberToString(_dtranheaderid)+" "+"GROUP BY HeaderID";
 //BA.debugLineNum = 1326;BA.debugLine="LogColor(Starter.strCriteria, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("010158089",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 1327;BA.debugLine="RS = Starter.DBCon.ExecQuery(Starter.strCriteria";
_rs = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 1328;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 1329;BA.debugLine="dPSIRdg = RS.GetInt(\"PSIReading\")";
_dpsirdg = _rs.GetInt("PSIReading");
 }else {
 //BA.debugLineNum = 1331;BA.debugLine="dPSIRdg = 0";
_dpsirdg = 0;
 };
 } 
       catch (Exception e14) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e14); //BA.debugLineNum = 1334;BA.debugLine="dPSIRdg = 0";
_dpsirdg = 0;
 //BA.debugLineNum = 1335;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("010158098",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 1336;BA.debugLine="ToastMessageShow($\"Unable to fetch Minimum Press";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Minimum Pressure Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 1339;BA.debugLine="Return dPSIRdg";
if (true) return _dpsirdg;
 //BA.debugLineNum = 1340;BA.debugLine="End Sub";
return 0;
}
public static String  _getlastrdgtime(anywheresoftware.b4a.BA _ba,int _itranheaderid) throws Exception{
String _rdgtime = "";
 //BA.debugLineNum = 971;BA.debugLine="Private Sub GetLastRdgTime (iTranHeaderID As Int)";
 //BA.debugLineNum = 972;BA.debugLine="Dim RdgTime As String";
_rdgtime = "";
 //BA.debugLineNum = 973;BA.debugLine="Try";
try { //BA.debugLineNum = 974;BA.debugLine="Starter.strCriteria = \"SELECT MAX(CASE WHEN subs";
mostCurrent._starter._strcriteria /*String*/  = "SELECT MAX(CASE WHEN substr(RdgTime,1,2) = '12' And substr(RdgTime,7,2) = 'AM' Then '00' || substr(RdgTime,3,6) "+"ELSE RdgTime END) "+"FROM ProductionDetails "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 979;BA.debugLine="LogColor(Starter.strCriteria, Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("09371656",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 981;BA.debugLine="RdgTime = Starter.DBCon.ExecQuerySingleResult(St";
_rdgtime = mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ );
 //BA.debugLineNum = 982;BA.debugLine="LogColor($\"Last Rdg: \"$ & RdgTime, Colors.Yellow";
anywheresoftware.b4a.keywords.Common.LogImpl("09371659",("Last Rdg: ")+_rdgtime,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 } 
       catch (Exception e8) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e8); //BA.debugLineNum = 984;BA.debugLine="RdgTime = \"\"";
_rdgtime = "";
 //BA.debugLineNum = 985;BA.debugLine="ToastMessageShow($\"Unable to fetch System Parame";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch System Parameters due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 986;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09371663",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 988;BA.debugLine="Return RdgTime";
if (true) return _rdgtime;
 //BA.debugLineNum = 989;BA.debugLine="End Sub";
return "";
}
public static int  _getlasttimeonid(anywheresoftware.b4a.BA _ba,String _strandate,int _ipumpid) throws Exception{
int _iretval = 0;
 //BA.debugLineNum = 343;BA.debugLine="Public Sub GetLastTimeOnID (sTrandate As String, i";
 //BA.debugLineNum = 344;BA.debugLine="Dim iRetval As Int";
_iretval = 0;
 //BA.debugLineNum = 345;BA.debugLine="iRetval = 0";
_iretval = (int) (0);
 //BA.debugLineNum = 346;BA.debugLine="Try";
try { //BA.debugLineNum = 347;BA.debugLine="Starter.strCriteria = \"SELECT Details.DetailID F";
mostCurrent._starter._strcriteria /*String*/  = "SELECT Details.DetailID FROM OnOffDetails AS Details "+"INNER JOIN TranHeader AS Header ON Details.HeaderID = Header.HeaderID "+"WHERE Header.PumpID = "+BA.NumberToString(_ipumpid)+" "+"AND Header.TranDate = '"+_strandate+"' "+"ORDER BY substr(Details.PumpOnTime,7,2) || (Case WHEN substr(Details.PumpOnTime,1,2) = '12' AND substr(Details.PumpOnTime,7,2) ='AM' THEN '00' ELSE substr(Details.PumpOnTime,1,2) END) || ' ' || substr(Details.PumpOnTime,4,2) ASC";
 //BA.debugLineNum = 352;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("08323081",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 354;BA.debugLine="iRetval = Starter.DBCon.ExecQuerySingleResult(St";
_iretval = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 } 
       catch (Exception e8) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e8); //BA.debugLineNum = 356;BA.debugLine="ToastMessageShow($\"Unable to fetch ID due to \"$";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch ID due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 357;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08323086",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 358;BA.debugLine="iRetval = 0";
_iretval = (int) (0);
 };
 //BA.debugLineNum = 360;BA.debugLine="LogColor($\"Return ID: \"$ & iRetval, Colors.Yellow";
anywheresoftware.b4a.keywords.Common.LogImpl("08323089",("Return ID: ")+BA.NumberToString(_iretval),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 361;BA.debugLine="Return iRetval";
if (true) return _iretval;
 //BA.debugLineNum = 362;BA.debugLine="End Sub";
return 0;
}
public static double  _getmaxpsi(anywheresoftware.b4a.BA _ba,int _dtranheaderid) throws Exception{
double _dmaxpsi = 0;
 //BA.debugLineNum = 1277;BA.debugLine="Public Sub GetMaxPSI (dTranHeaderID As Int) As Dou";
 //BA.debugLineNum = 1278;BA.debugLine="Dim dMaxPSI As Double";
_dmaxpsi = 0;
 //BA.debugLineNum = 1280;BA.debugLine="Try";
try { //BA.debugLineNum = 1281;BA.debugLine="dMaxPSI = 0";
_dmaxpsi = 0;
 //BA.debugLineNum = 1283;BA.debugLine="Starter.strCriteria = \"SELECT max(PSIReading) FR";
mostCurrent._starter._strcriteria /*String*/  = "SELECT max(PSIReading) FROM PressureRdgDetails WHERE HeaderID = "+BA.NumberToString(_dtranheaderid)+" "+"GROUP BY HeaderID";
 //BA.debugLineNum = 1286;BA.debugLine="dMaxPSI = Starter.DBCon.ExecQuerySingleResult(St";
_dmaxpsi = (double)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 1287;BA.debugLine="LogColor(Starter.strCriteria, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("010027018",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 } 
       catch (Exception e8) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e8); //BA.debugLineNum = 1290;BA.debugLine="dMaxPSI = 0";
_dmaxpsi = 0;
 //BA.debugLineNum = 1291;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("010027022",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 1292;BA.debugLine="ToastMessageShow($\"Unable to fetch Minimum Press";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Minimum Pressure Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 1294;BA.debugLine="Return dMaxPSI";
if (true) return _dmaxpsi;
 //BA.debugLineNum = 1295;BA.debugLine="End Sub";
return 0;
}
public static double  _getminpsi(anywheresoftware.b4a.BA _ba,int _dtranheaderid) throws Exception{
double _dminpsi = 0;
 //BA.debugLineNum = 1257;BA.debugLine="Public Sub GetMinPSI (dTranHeaderID As Int) As Dou";
 //BA.debugLineNum = 1258;BA.debugLine="Dim dMinPSI As Double";
_dminpsi = 0;
 //BA.debugLineNum = 1260;BA.debugLine="Try";
try { //BA.debugLineNum = 1261;BA.debugLine="dMinPSI = 0";
_dminpsi = 0;
 //BA.debugLineNum = 1263;BA.debugLine="Starter.strCriteria = \"SELECT min(PSIReading) FR";
mostCurrent._starter._strcriteria /*String*/  = "SELECT min(PSIReading) FROM PressureRdgDetails WHERE HeaderID = "+BA.NumberToString(_dtranheaderid)+" "+"GROUP BY HeaderID";
 //BA.debugLineNum = 1266;BA.debugLine="dMinPSI = Starter.DBCon.ExecQuerySingleResult(St";
_dminpsi = (double)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 1267;BA.debugLine="LogColor(Starter.strCriteria, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("09961482",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 } 
       catch (Exception e8) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e8); //BA.debugLineNum = 1270;BA.debugLine="dMinPSI = 0";
_dminpsi = 0;
 //BA.debugLineNum = 1271;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09961486",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 1272;BA.debugLine="ToastMessageShow($\"Unable to fetch Minimum Press";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Minimum Pressure Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 1274;BA.debugLine="Return dMinPSI";
if (true) return _dminpsi;
 //BA.debugLineNum = 1275;BA.debugLine="End Sub";
return 0;
}
public static String  _getparameters(anywheresoftware.b4a.BA _ba) throws Exception{
int _countrec = 0;
 //BA.debugLineNum = 7;BA.debugLine="Public Sub GetParameters";
 //BA.debugLineNum = 9;BA.debugLine="Dim CountRec As Int";
_countrec = 0;
 //BA.debugLineNum = 10;BA.debugLine="Try";
try { //BA.debugLineNum = 11;BA.debugLine="CountRec = Starter.DBCon.ExecQuerySingleResult(\"";
_countrec = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult("SELECT COUNT(ID) FROM tblSysParam")));
 //BA.debugLineNum = 12;BA.debugLine="If CountRec <=0 Then Return";
if (_countrec<=0) { 
if (true) return "";};
 //BA.debugLineNum = 14;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblSysParam";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblSysParam";
 //BA.debugLineNum = 15;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("07208968",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 16;BA.debugLine="rsTemp = Starter.DBCon.ExecQuery(Starter.strCrit";
_rstemp = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 18;BA.debugLine="If rsTemp.RowCount > 0 Then";
if (_rstemp.getRowCount()>0) { 
 //BA.debugLineNum = 19;BA.debugLine="rsTemp.Position = 0";
_rstemp.setPosition((int) (0));
 //BA.debugLineNum = 20;BA.debugLine="GlobalVar.RdgFrom = rsTemp.GetString(\"RdgDateFr";
mostCurrent._globalvar._rdgfrom /*String*/  = _rstemp.GetString("RdgDateFrom");
 //BA.debugLineNum = 21;BA.debugLine="GlobalVar.RdgTo = rsTemp.GetString(\"RdgDateTo\")";
mostCurrent._globalvar._rdgto /*String*/  = _rstemp.GetString("RdgDateTo");
 //BA.debugLineNum = 22;BA.debugLine="GlobalVar.BranchID = rsTemp.GetInt(\"BranchID\")";
mostCurrent._globalvar._branchid /*int*/  = _rstemp.GetInt("BranchID");
 }else {
 //BA.debugLineNum = 24;BA.debugLine="ToastMessageShow($\"Unable to fetch System Param";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch System Parameters due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 };
 } 
       catch (Exception e17) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e17); //BA.debugLineNum = 27;BA.debugLine="ToastMessageShow($\"Unable to fetch System Parame";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch System Parameters due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 28;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("07208981",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return "";
}
public static String  _getplumberids(anywheresoftware.b4a.BA _ba,String _splumber) throws Exception{
String _sretval = "";
String _empcode = "";
String _empid = "";
anywheresoftware.b4a.objects.collections.List _empidlist = null;
String[] _arr = null;
int _i = 0;
int _j = 0;
 //BA.debugLineNum = 484;BA.debugLine="Public Sub GetPlumberIDs(sPlumber As String) As St";
 //BA.debugLineNum = 485;BA.debugLine="Dim sRetVal As String";
_sretval = "";
 //BA.debugLineNum = 486;BA.debugLine="Dim EmpCode As String";
_empcode = "";
 //BA.debugLineNum = 487;BA.debugLine="Dim EmpID As String";
_empid = "";
 //BA.debugLineNum = 488;BA.debugLine="Dim EmpIDList As List";
_empidlist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 490;BA.debugLine="EmpIDList.Initialize";
_empidlist.Initialize();
 //BA.debugLineNum = 491;BA.debugLine="EmpIDList.Clear";
_empidlist.Clear();
 //BA.debugLineNum = 493;BA.debugLine="Try";
try { //BA.debugLineNum = 494;BA.debugLine="Dim arr() As String";
_arr = new String[(int) (0)];
java.util.Arrays.fill(_arr,"");
 //BA.debugLineNum = 496;BA.debugLine="arr = Regex.Split(\",\", sPlumber)";
_arr = anywheresoftware.b4a.keywords.Common.Regex.Split(",",_splumber);
 //BA.debugLineNum = 497;BA.debugLine="LogColor(arr.Length, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("08716301",BA.NumberToString(_arr.length),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 498;BA.debugLine="For i = 0 To arr.Length - 1";
{
final int step11 = 1;
final int limit11 = (int) (_arr.length-1);
_i = (int) (0) ;
for (;_i <= limit11 ;_i = _i + step11 ) {
 //BA.debugLineNum = 499;BA.debugLine="Log(arr(i))";
anywheresoftware.b4a.keywords.Common.LogImpl("08716303",_arr[_i],0);
 //BA.debugLineNum = 500;BA.debugLine="EmpID = GetIDByCode(\"id\", \"tblPlumbers\",\"EmpNam";
_empid = BA.NumberToString(_getidbycode(_ba,"id","tblPlumbers","EmpName",mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_arr[_i])));
 //BA.debugLineNum = 501;BA.debugLine="EmpIDList.Add(EmpID)";
_empidlist.Add((Object)(_empid));
 }
};
 //BA.debugLineNum = 504;BA.debugLine="LogColor(EmpIDList.Size, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("08716308",BA.NumberToString(_empidlist.getSize()),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 506;BA.debugLine="For j = 0 To EmpIDList.Size - 1";
{
final int step17 = 1;
final int limit17 = (int) (_empidlist.getSize()-1);
_j = (int) (0) ;
for (;_j <= limit17 ;_j = _j + step17 ) {
 //BA.debugLineNum = 507;BA.debugLine="EmpCode =  EmpIDList.Get(j)";
_empcode = BA.ObjectToString(_empidlist.Get(_j));
 //BA.debugLineNum = 508;BA.debugLine="LogColor(EmpCode,Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("08716312",_empcode,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 509;BA.debugLine="If j = 0 Then";
if (_j==0) { 
 //BA.debugLineNum = 510;BA.debugLine="sRetVal = GlobalVar.SF.Trim(EmpCode)";
_sretval = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_empcode);
 }else {
 //BA.debugLineNum = 512;BA.debugLine="sRetVal = sRetVal & \",\" & EmpCode";
_sretval = _sretval+","+_empcode;
 };
 }
};
 //BA.debugLineNum = 516;BA.debugLine="Log (sRetVal)";
anywheresoftware.b4a.keywords.Common.LogImpl("08716320",_sretval,0);
 } 
       catch (Exception e28) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e28); //BA.debugLineNum = 519;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08716323",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 521;BA.debugLine="LogColor(sRetVal, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("08716325",_sretval,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 522;BA.debugLine="Return GlobalVar.SF.Trim(sRetVal)";
if (true) return mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_sretval);
 //BA.debugLineNum = 523;BA.debugLine="End Sub";
return "";
}
public static String  _getplumbernames(anywheresoftware.b4a.BA _ba,String _splumber) throws Exception{
String _sretval = "";
String _empcode = "";
String _empname = "";
anywheresoftware.b4a.objects.collections.List _empnamelist = null;
String[] _arr = null;
int _i = 0;
int _j = 0;
 //BA.debugLineNum = 525;BA.debugLine="Public Sub GetPlumberNames(sPlumber As String) As";
 //BA.debugLineNum = 526;BA.debugLine="Dim sRetVal As String";
_sretval = "";
 //BA.debugLineNum = 527;BA.debugLine="Dim EmpCode As String";
_empcode = "";
 //BA.debugLineNum = 528;BA.debugLine="Dim EmpName As String";
_empname = "";
 //BA.debugLineNum = 529;BA.debugLine="Dim EmpNameList As List";
_empnamelist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 531;BA.debugLine="EmpNameList.Initialize";
_empnamelist.Initialize();
 //BA.debugLineNum = 532;BA.debugLine="EmpNameList.Clear";
_empnamelist.Clear();
 //BA.debugLineNum = 534;BA.debugLine="Try";
try { //BA.debugLineNum = 535;BA.debugLine="Dim arr() As String";
_arr = new String[(int) (0)];
java.util.Arrays.fill(_arr,"");
 //BA.debugLineNum = 537;BA.debugLine="arr = Regex.Split(\",\", sPlumber)";
_arr = anywheresoftware.b4a.keywords.Common.Regex.Split(",",_splumber);
 //BA.debugLineNum = 538;BA.debugLine="LogColor(arr.Length, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("08781837",BA.NumberToString(_arr.length),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 539;BA.debugLine="For i = 0 To arr.Length - 1";
{
final int step11 = 1;
final int limit11 = (int) (_arr.length-1);
_i = (int) (0) ;
for (;_i <= limit11 ;_i = _i + step11 ) {
 //BA.debugLineNum = 540;BA.debugLine="Log(arr(i))";
anywheresoftware.b4a.keywords.Common.LogImpl("08781839",_arr[_i],0);
 //BA.debugLineNum = 541;BA.debugLine="EmpName = GetCodeByID(\"EmpName\", \"tblPlumbers\",";
_empname = _getcodebyid(_ba,"EmpName","tblPlumbers","id",(int)(Double.parseDouble(mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_arr[_i]))));
 //BA.debugLineNum = 542;BA.debugLine="EmpNameList.Add(EmpName)";
_empnamelist.Add((Object)(_empname));
 }
};
 //BA.debugLineNum = 545;BA.debugLine="LogColor(EmpNameList.Size, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("08781844",BA.NumberToString(_empnamelist.getSize()),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 547;BA.debugLine="For j = 0 To EmpNameList.Size - 1";
{
final int step17 = 1;
final int limit17 = (int) (_empnamelist.getSize()-1);
_j = (int) (0) ;
for (;_j <= limit17 ;_j = _j + step17 ) {
 //BA.debugLineNum = 548;BA.debugLine="EmpCode =  EmpNameList.Get(j)";
_empcode = BA.ObjectToString(_empnamelist.Get(_j));
 //BA.debugLineNum = 549;BA.debugLine="LogColor(EmpCode,Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("08781848",_empcode,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 550;BA.debugLine="If j = 0 Then";
if (_j==0) { 
 //BA.debugLineNum = 551;BA.debugLine="sRetVal = GlobalVar.SF.Trim(EmpCode)";
_sretval = mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_empcode);
 }else {
 //BA.debugLineNum = 553;BA.debugLine="sRetVal = sRetVal & \", \" & EmpCode";
_sretval = _sretval+", "+_empcode;
 };
 }
};
 //BA.debugLineNum = 557;BA.debugLine="Log (sRetVal)";
anywheresoftware.b4a.keywords.Common.LogImpl("08781856",_sretval,0);
 } 
       catch (Exception e28) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e28); //BA.debugLineNum = 560;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08781859",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 562;BA.debugLine="LogColor(sRetVal, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("08781861",_sretval,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 563;BA.debugLine="Return GlobalVar.SF.Trim(sRetVal)";
if (true) return mostCurrent._globalvar._sf /*adr.stringfunctions.stringfunctions*/ ._vvvvvvv4(_sretval);
 //BA.debugLineNum = 564;BA.debugLine="End Sub";
return "";
}
public static double  _getprevpsirdg(anywheresoftware.b4a.BA _ba) throws Exception{
double _dpsirdg = 0;
anywheresoftware.b4a.sql.SQL.CursorWrapper _rs = null;
 //BA.debugLineNum = 1342;BA.debugLine="Public Sub GetPrevPSIRdg As Double";
 //BA.debugLineNum = 1343;BA.debugLine="Dim dPSIRdg As Double";
_dpsirdg = 0;
 //BA.debugLineNum = 1344;BA.debugLine="Dim RS As Cursor";
_rs = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 1346;BA.debugLine="Try";
try { //BA.debugLineNum = 1347;BA.debugLine="dPSIRdg = 0";
_dpsirdg = 0;
 //BA.debugLineNum = 1349;BA.debugLine="Starter.strCriteria = \"SELECT PSIReading, max(De";
mostCurrent._starter._strcriteria /*String*/  = "SELECT PSIReading, max(DetailID) from PressureRdgDetails ORDER BY DetailID DESC LIMIT 1";
 //BA.debugLineNum = 1350;BA.debugLine="LogColor(Starter.strCriteria, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("010223624",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 1351;BA.debugLine="RS = Starter.DBCon.ExecQuery(Starter.strCriteria";
_rs = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 1352;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 1353;BA.debugLine="dPSIRdg = RS.GetInt(\"PSIReading\")";
_dpsirdg = _rs.GetInt("PSIReading");
 }else {
 //BA.debugLineNum = 1355;BA.debugLine="dPSIRdg = 0";
_dpsirdg = 0;
 };
 } 
       catch (Exception e14) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e14); //BA.debugLineNum = 1358;BA.debugLine="dPSIRdg = 0";
_dpsirdg = 0;
 //BA.debugLineNum = 1359;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("010223633",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 1360;BA.debugLine="ToastMessageShow($\"Unable to fetch Minimum Press";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Minimum Pressure Reading due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 1362;BA.debugLine="Return dPSIRdg";
if (true) return _dpsirdg;
 //BA.debugLineNum = 1363;BA.debugLine="End Sub";
return 0;
}
public static int  _getpumphouseid(anywheresoftware.b4a.BA _ba,String _scode) throws Exception{
int _iretval = 0;
 //BA.debugLineNum = 68;BA.debugLine="Public Sub GetPumpHouseID(sCode As String) As Int";
 //BA.debugLineNum = 70;BA.debugLine="Dim iRetVal As Int";
_iretval = 0;
 //BA.debugLineNum = 72;BA.debugLine="Try";
try { //BA.debugLineNum = 73;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblPumpStat";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblPumpStation WHERE PumpHouseCode = '"+_scode+"'";
 //BA.debugLineNum = 74;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("07405574",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 76;BA.debugLine="iRetVal = Starter.DBCon.ExecQuerySingleResult(St";
_iretval = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 } 
       catch (Exception e7) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e7); //BA.debugLineNum = 78;BA.debugLine="ToastMessageShow($\"Unable to fetch Pump StationI";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Pump StationID due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 79;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("07405579",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 80;BA.debugLine="iRetVal = 0";
_iretval = (int) (0);
 };
 //BA.debugLineNum = 82;BA.debugLine="Return iRetVal";
if (true) return _iretval;
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return 0;
}
public static int  _getpumppowerstatus(anywheresoftware.b4a.BA _ba,int _istationid) throws Exception{
int _iretval = 0;
 //BA.debugLineNum = 85;BA.debugLine="Public Sub GetPumpPowerStatus(iStationID As Int) A";
 //BA.debugLineNum = 87;BA.debugLine="Dim iRetval As Int";
_iretval = 0;
 //BA.debugLineNum = 88;BA.debugLine="Try";
try { //BA.debugLineNum = 89;BA.debugLine="Starter.strCriteria = \"SELECT OnOffStatus FROM t";
mostCurrent._starter._strcriteria /*String*/  = "SELECT OnOffStatus FROM tblPumpStation WHERE StationID = "+BA.NumberToString(_istationid);
 //BA.debugLineNum = 90;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("07471109",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 92;BA.debugLine="iRetval = Starter.DBCon.ExecQuerySingleResult(St";
_iretval = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 } 
       catch (Exception e7) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e7); //BA.debugLineNum = 94;BA.debugLine="ToastMessageShow($\"Unable to fetch Pump On/Off S";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Pump On/Off State due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 95;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("07471114",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 96;BA.debugLine="iRetval = 0";
_iretval = (int) (0);
 };
 //BA.debugLineNum = 98;BA.debugLine="Return iRetval";
if (true) return _iretval;
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return 0;
}
public static int  _getsystemmode(anywheresoftware.b4a.BA _ba,int _ibranchid) throws Exception{
int _iretval = 0;
 //BA.debugLineNum = 49;BA.debugLine="Public Sub GetSystemMode(iBranchID As Int) As Int";
 //BA.debugLineNum = 51;BA.debugLine="Dim iRetVal As Int";
_iretval = 0;
 //BA.debugLineNum = 53;BA.debugLine="Try";
try { //BA.debugLineNum = 54;BA.debugLine="Starter.strCriteria = \"SELECT SysMode FROM tblBr";
mostCurrent._starter._strcriteria /*String*/  = "SELECT SysMode FROM tblBranches WHERE BranchID = "+BA.NumberToString(_ibranchid);
 //BA.debugLineNum = 55;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("07340038",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 57;BA.debugLine="iRetVal = Starter.DBCon.ExecQuerySingleResult(St";
_iretval = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 } 
       catch (Exception e7) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e7); //BA.debugLineNum = 59;BA.debugLine="ToastMessageShow($\"Unable to fetch Branch System";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Branch System Mode due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 60;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("07340043",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 61;BA.debugLine="iRetVal = 0";
_iretval = (int) (0);
 };
 //BA.debugLineNum = 63;BA.debugLine="Return iRetVal";
if (true) return _iretval;
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return 0;
}
public static int  _getuserid(anywheresoftware.b4a.BA _ba,String _susername,String _suserpass) throws Exception{
int _iretval = 0;
 //BA.debugLineNum = 367;BA.debugLine="Public Sub GetUserID (sUserName As String, sUserPa";
 //BA.debugLineNum = 369;BA.debugLine="Dim iRetval As Int";
_iretval = 0;
 //BA.debugLineNum = 370;BA.debugLine="Try";
try { //BA.debugLineNum = 371;BA.debugLine="Starter.strCriteria = \"SELECT UserID FROM tblUse";
mostCurrent._starter._strcriteria /*String*/  = "SELECT UserID FROM tblUsers WHERE UserName = '"+_susername+"' "+"AND UserPassword = '"+_suserpass+"'";
 //BA.debugLineNum = 373;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("08388614",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 375;BA.debugLine="iRetval = Starter.DBCon.ExecQuerySingleResult(St";
_iretval = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 } 
       catch (Exception e7) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e7); //BA.debugLineNum = 377;BA.debugLine="ToastMessageShow($\"Unable to fetch User's ID due";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch User's ID due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 378;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08388619",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 379;BA.debugLine="iRetval = 0";
_iretval = (int) (0);
 };
 //BA.debugLineNum = 381;BA.debugLine="Return iRetval";
if (true) return _iretval;
 //BA.debugLineNum = 382;BA.debugLine="End Sub";
return 0;
}
public static boolean  _hasassignment(anywheresoftware.b4a.BA _ba,int _iuserid) throws Exception{
boolean _bretval = false;
int _iretval = 0;
 //BA.debugLineNum = 440;BA.debugLine="Public Sub HasAssignment(iUserID As Int) As Boolea";
 //BA.debugLineNum = 441;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 442;BA.debugLine="Dim iRetVal As Int";
_iretval = 0;
 //BA.debugLineNum = 443;BA.debugLine="Try";
try { //BA.debugLineNum = 444;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 445;BA.debugLine="Starter.strCriteria = \"SELECT Count(tblAssignedS";
mostCurrent._starter._strcriteria /*String*/  = "SELECT Count(tblAssignedStation.AssignedID) FROM tblAssignedStation WHERE tblAssignedStation.OpID = "+BA.NumberToString(_iuserid);
 //BA.debugLineNum = 446;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("08585222",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 448;BA.debugLine="iRetVal = Starter.DBCon.ExecQuerySingleResult(St";
_iretval = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 450;BA.debugLine="If iRetVal > 0 Then";
if (_iretval>0) { 
 //BA.debugLineNum = 451;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 };
 } 
       catch (Exception e12) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e12); //BA.debugLineNum = 454;BA.debugLine="ToastMessageShow($\"Unable to fetch User's ID due";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch User's ID due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 455;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08585231",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 456;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 458;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 459;BA.debugLine="End Sub";
return false;
}
public static int  _hoursbetween(anywheresoftware.b4a.BA _ba,String _startdate,String _starttime,String _enddate,String _endtime) throws Exception{
long _s = 0L;
long _e = 0L;
 //BA.debugLineNum = 1069;BA.debugLine="Sub HoursBetween(StartDate As String, StartTime As";
 //BA.debugLineNum = 1071;BA.debugLine="Dim s, e As Long";
_s = 0L;
_e = 0L;
 //BA.debugLineNum = 1072;BA.debugLine="s = ParseDateAndTime(StartDate, StartTime)";
_s = _parsedateandtime(_ba,_startdate,_starttime);
 //BA.debugLineNum = 1073;BA.debugLine="e = ParseDateAndTime(EndDate, EndTime)";
_e = _parsedateandtime(_ba,_enddate,_endtime);
 //BA.debugLineNum = 1074;BA.debugLine="Return (e - s) / DateTime.TicksPerMinute";
if (true) return (int) ((_e-_s)/(double)anywheresoftware.b4a.keywords.Common.DateTime.TicksPerMinute);
 //BA.debugLineNum = 1075;BA.debugLine="End Sub";
return 0;
}
public static boolean  _isfmrdgdetailheaderidexist(anywheresoftware.b4a.BA _ba,int _itranheaderid) throws Exception{
boolean _bretval = false;
 //BA.debugLineNum = 244;BA.debugLine="Public Sub IsFMRdgDetailHeaderIDExist (iTranHeader";
 //BA.debugLineNum = 245;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 247;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 248;BA.debugLine="Try";
try { //BA.debugLineNum = 249;BA.debugLine="Starter.strCriteria = \"SELECT * FROM ProductionD";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM ProductionDetails "+"WHERE HeaderID = "+BA.NumberToString(_itranheaderid);
 //BA.debugLineNum = 252;BA.debugLine="LogColor(Starter.strCriteria, Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("07995400",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 254;BA.debugLine="rsTemp = Starter.DBCon.ExecQuery(Starter.strCrit";
_rstemp = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 256;BA.debugLine="If rsTemp.RowCount > 0 Then";
if (_rstemp.getRowCount()>0) { 
 //BA.debugLineNum = 257;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 259;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e13) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e13); //BA.debugLineNum = 262;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 263;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("07995411",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 265;BA.debugLine="rsTemp.Close";
_rstemp.Close();
 //BA.debugLineNum = 266;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 267;BA.debugLine="End Sub";
return false;
}
public static boolean  _isfuturistictime(anywheresoftware.b4a.BA _ba,String _strandate,String _strantime) throws Exception{
boolean _bretval = false;
int _itotaltime = 0;
 //BA.debugLineNum = 1046;BA.debugLine="Public Sub IsFuturisticTime(sTrandate As String, s";
 //BA.debugLineNum = 1047;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 1048;BA.debugLine="Dim iTotalTime As Int";
_itotaltime = 0;
 //BA.debugLineNum = 1049;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1052;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 1053;BA.debugLine="DateTime.TimeFormat = \"HH:mm:ss\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm:ss");
 //BA.debugLineNum = 1054;BA.debugLine="iTotalTime = HoursBetween(DateTime.Date(DateTime.";
_itotaltime = _hoursbetween(_ba,anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),_strandate,_strantime+":00");
 //BA.debugLineNum = 1055;BA.debugLine="Log(iTotalTime)";
anywheresoftware.b4a.keywords.Common.LogImpl("09502729",BA.NumberToString(_itotaltime),0);
 //BA.debugLineNum = 1057;BA.debugLine="Try";
try { //BA.debugLineNum = 1058;BA.debugLine="If iTotalTime > 0 Then";
if (_itotaltime>0) { 
 //BA.debugLineNum = 1059;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 } 
       catch (Exception e13) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e13); //BA.debugLineNum = 1063;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1064;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09502738",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 1066;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 1067;BA.debugLine="End Sub";
return false;
}
public static boolean  _isgetbranchinfo(anywheresoftware.b4a.BA _ba,int _ibranchid) throws Exception{
boolean _bretval = false;
 //BA.debugLineNum = 413;BA.debugLine="Public Sub isGetBranchInfo (iBranchID As Int) As B";
 //BA.debugLineNum = 415;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 416;BA.debugLine="Try";
try { //BA.debugLineNum = 417;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblBranches";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblBranches WHERE BranchID = "+BA.NumberToString(_ibranchid);
 //BA.debugLineNum = 418;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("08519685",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 420;BA.debugLine="rsTemp = Starter.DBCon.ExecQuery(Starter.strCrit";
_rstemp = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 422;BA.debugLine="If rsTemp.RowCount > 0 Then";
if (_rstemp.getRowCount()>0) { 
 //BA.debugLineNum = 423;BA.debugLine="rsTemp.Position = 0";
_rstemp.setPosition((int) (0));
 //BA.debugLineNum = 424;BA.debugLine="GlobalVar.BranchCode = rsTemp.GetString(\"Branch";
mostCurrent._globalvar._branchcode /*String*/  = _rstemp.GetString("BranchCode");
 //BA.debugLineNum = 425;BA.debugLine="GlobalVar.BranchName = rsTemp.GetString(\"Branch";
mostCurrent._globalvar._branchname /*String*/  = _rstemp.GetString("BranchName");
 //BA.debugLineNum = 426;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 428;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e15) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e15); //BA.debugLineNum = 431;BA.debugLine="ToastMessageShow($\"Unable to fetch Branch Info d";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch Branch Info due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 432;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08519699",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 433;BA.debugLine="rsTemp.Close";
_rstemp.Close();
 //BA.debugLineNum = 434;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 436;BA.debugLine="rsTemp.Close";
_rstemp.Close();
 //BA.debugLineNum = 437;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 438;BA.debugLine="End Sub";
return false;
}
public static boolean  _isgetuserinfo(anywheresoftware.b4a.BA _ba,int _iuserid) throws Exception{
boolean _bretval = false;
 //BA.debugLineNum = 384;BA.debugLine="Public Sub isGetUserInfo (iUserID As Int) As Boole";
 //BA.debugLineNum = 386;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 387;BA.debugLine="Try";
try { //BA.debugLineNum = 388;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblUsers WH";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblUsers WHERE UserID = "+BA.NumberToString(_iuserid);
 //BA.debugLineNum = 389;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("08454149",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 391;BA.debugLine="rsTemp = Starter.DBCon.ExecQuery(Starter.strCrit";
_rstemp = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 393;BA.debugLine="If rsTemp.RowCount > 0 Then";
if (_rstemp.getRowCount()>0) { 
 //BA.debugLineNum = 394;BA.debugLine="rsTemp.Position = 0";
_rstemp.setPosition((int) (0));
 //BA.debugLineNum = 395;BA.debugLine="GlobalVar.UserName = rsTemp.GetString(\"UserName";
mostCurrent._globalvar._username /*String*/  = _rstemp.GetString("UserName");
 //BA.debugLineNum = 396;BA.debugLine="GlobalVar.UserPW = rsTemp.GetString(\"UserPasswo";
mostCurrent._globalvar._userpw /*String*/  = _rstemp.GetString("UserPassword");
 //BA.debugLineNum = 397;BA.debugLine="GlobalVar.EmpName = rsTemp.GetString(\"EmpName\")";
mostCurrent._globalvar._empname /*String*/  = _rstemp.GetString("EmpName");
 //BA.debugLineNum = 398;BA.debugLine="GlobalVar.UserAvatar = SF.Upper(SF.Left(rsTemp.";
mostCurrent._globalvar._useravatar /*String*/  = _sf._vvvvvvv5(_sf._vvv6(_rstemp.GetString("FirstName"),(long) (1))+_sf._vvv6(_rstemp.GetString("LastName"),(long) (1)));
 //BA.debugLineNum = 399;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 401;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e17) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e17); //BA.debugLineNum = 404;BA.debugLine="ToastMessageShow($\"Unable to fetch User's Info d";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch User's Info due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 405;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08454165",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 406;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 407;BA.debugLine="rsTemp.Close";
_rstemp.Close();
 };
 //BA.debugLineNum = 409;BA.debugLine="rsTemp.Close";
_rstemp.Close();
 //BA.debugLineNum = 410;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 411;BA.debugLine="End Sub";
return false;
}
public static boolean  _isgpmtransexist(anywheresoftware.b4a.BA _ba,int _ipumpid,String _strandate) throws Exception{
boolean _bretval = false;
 //BA.debugLineNum = 269;BA.debugLine="Public Sub IsGPMTransExist(iPumpID As Int, sTranda";
 //BA.debugLineNum = 270;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 272;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 273;BA.debugLine="Try";
try { //BA.debugLineNum = 274;BA.debugLine="Starter.strCriteria = \"SELECT * FROM tblGPMHisto";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM tblGPMHistory AS GPMHist "+"WHERE GPMHist.PumpID = "+BA.NumberToString(_ipumpid)+" "+"AND GPMHist.TranDate = '"+_strandate+"'";
 //BA.debugLineNum = 277;BA.debugLine="LogColor(Starter.strCriteria, Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("08060936",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 279;BA.debugLine="rsTemp = Starter.DBCon.ExecQuery(Starter.strCrit";
_rstemp = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 281;BA.debugLine="If rsTemp.RowCount > 0 Then";
if (_rstemp.getRowCount()>0) { 
 //BA.debugLineNum = 282;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 284;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e13) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e13); //BA.debugLineNum = 287;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 288;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08060947",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 290;BA.debugLine="rsTemp.Close";
_rstemp.Close();
 //BA.debugLineNum = 291;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 292;BA.debugLine="End Sub";
return false;
}
public static boolean  _ismultipos(anywheresoftware.b4a.BA _ba,int _iuserid) throws Exception{
boolean _bretval = false;
int _iretval = 0;
 //BA.debugLineNum = 461;BA.debugLine="Public Sub IsMultiPos(iUserID As Int) As Boolean";
 //BA.debugLineNum = 462;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 463;BA.debugLine="Dim iRetVal As Int";
_iretval = 0;
 //BA.debugLineNum = 464;BA.debugLine="Try";
try { //BA.debugLineNum = 465;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 466;BA.debugLine="Starter.strCriteria = \"SELECT IsMultiPos FROM tb";
mostCurrent._starter._strcriteria /*String*/  = "SELECT IsMultiPos FROM tblUsers WHERE UserID = "+BA.NumberToString(_iuserid);
 //BA.debugLineNum = 467;BA.debugLine="LogColor(Starter.strCriteria, Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogImpl("08650758",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 469;BA.debugLine="iRetVal = Starter.DBCon.ExecQuerySingleResult(St";
_iretval = (int)(Double.parseDouble(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuerySingleResult(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 471;BA.debugLine="If iRetVal = 1 Then";
if (_iretval==1) { 
 //BA.debugLineNum = 472;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 474;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e14) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e14); //BA.debugLineNum = 477;BA.debugLine="ToastMessageShow($\"Unable to fetch User's ID due";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(("Unable to fetch User's ID due to ")+anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage()),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 478;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08650769",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 479;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 481;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 482;BA.debugLine="End Sub";
return false;
}
public static boolean  _isnonoperationaltimeoverlapped(anywheresoftware.b4a.BA _ba,long _ltimestart,long _ltimeend,int _iheaderid,int _idetailid,boolean _beditmode) throws Exception{
boolean _istimeoverlapped = false;
boolean _bretval = false;
String _sstarttime = "";
String _sendtime = "";
long _lstarttime = 0L;
long _lendtime = 0L;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
 //BA.debugLineNum = 1088;BA.debugLine="Public Sub IsNonOperationalTimeOverlapped(lTimeSta";
 //BA.debugLineNum = 1089;BA.debugLine="Dim IsTimeOverlapped, bRetVal As Boolean";
_istimeoverlapped = false;
_bretval = false;
 //BA.debugLineNum = 1090;BA.debugLine="Dim sStartTime, sEndTime As String";
_sstarttime = "";
_sendtime = "";
 //BA.debugLineNum = 1091;BA.debugLine="Dim lStartTime, lEndTime As Long";
_lstarttime = 0L;
_lendtime = 0L;
 //BA.debugLineNum = 1093;BA.debugLine="Try";
try { //BA.debugLineNum = 1094;BA.debugLine="If bEditMode = True Then";
if (_beditmode==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1095;BA.debugLine="Starter.strCriteria = \"SELECT * FROM NonOpDetai";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM NonOpDetails "+"WHERE HeaderID = "+BA.NumberToString(_iheaderid)+" "+"AND DetailID <> "+BA.NumberToString(_idetailid)+" "+"ORDER BY substr(OffTime,7,2) || (Case WHEN substr(OffTime,1,2) = '12' AND substr(OffTime,7,2) ='AM' THEN '00' ELSE substr(OffTime,1,2) END) || ' ' || substr(OffTime,4,2) ASC";
 }else {
 //BA.debugLineNum = 1100;BA.debugLine="Starter.strCriteria = \"SELECT * FROM NonOpDetai";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM NonOpDetails "+"WHERE HeaderID = "+BA.NumberToString(_iheaderid)+" "+"ORDER BY substr(OffTime,7,2) || (Case WHEN substr(OffTime,1,2) = '12' AND substr(OffTime,7,2) ='AM' THEN '00' ELSE substr(OffTime,1,2) END) || ' ' || substr(OffTime,4,2) ASC";
 };
 //BA.debugLineNum = 1105;BA.debugLine="Dim RS As ResultSet = Starter.DBCon.ExecQuery(St";
_rs = new anywheresoftware.b4a.sql.SQL.ResultSetWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.ResultSetWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 1106;BA.debugLine="LogColor(Starter.strCriteria, Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699346",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1108;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1109;BA.debugLine="IsTimeOverlapped = False";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1111;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 1113;BA.debugLine="Do While RS.NextRow";
while (_rs.NextRow()) {
 //BA.debugLineNum = 1114;BA.debugLine="sStartTime = RS.GetString(\"OffTime\")";
_sstarttime = _rs.GetString("OffTime");
 //BA.debugLineNum = 1115;BA.debugLine="sEndTime = RS.GetString(\"OnTime\")";
_sendtime = _rs.GetString("OnTime");
 //BA.debugLineNum = 1117;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 1118;BA.debugLine="lStartTime = DateTime.TimeParse(sStartTime)";
_lstarttime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_sstarttime);
 //BA.debugLineNum = 1119;BA.debugLine="lEndTime = DateTime.TimeParse(sEndTime)";
_lendtime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_sendtime);
 //BA.debugLineNum = 1120;BA.debugLine="LogColor(sStartTime,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699360",_sstarttime,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 1121;BA.debugLine="LogColor(sEndTime,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699361",_sendtime,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 1123;BA.debugLine="LogColor($\"Input On: \"$ & lTimeStart,Colors.Re";
anywheresoftware.b4a.keywords.Common.LogImpl("09699363",("Input On: ")+BA.NumberToString(_ltimestart),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 1124;BA.debugLine="LogColor($\"Input Off: \"$ & lTimeEnd,Colors.Mag";
anywheresoftware.b4a.keywords.Common.LogImpl("09699364",("Input Off: ")+BA.NumberToString(_ltimeend),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 1125;BA.debugLine="LogColor($\"Data On: \"$ & RS.Position & $\" \"$ &";
anywheresoftware.b4a.keywords.Common.LogImpl("09699365",("Data On: ")+BA.NumberToString(_rs.getPosition())+(" ")+BA.NumberToString(_lstarttime),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 1126;BA.debugLine="LogColor($\"Data Off: \"$ & RS.Position & $\" \"$";
anywheresoftware.b4a.keywords.Common.LogImpl("09699366",("Data Off: ")+BA.NumberToString(_rs.getPosition())+(" ")+BA.NumberToString(_lendtime),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 1128;BA.debugLine="If lTimeStart = lStartTime Or lTimeEnd = lEndT";
if (_ltimestart==_lstarttime || _ltimeend==_lendtime || _ltimestart==_lendtime || _ltimeend==_lstarttime) { 
 //BA.debugLineNum = 1129;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1130;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699370",(""),0);
 //BA.debugLineNum = 1131;BA.debugLine="LogColor(\"1st Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699371","1st Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_ltimestart>_lstarttime && _ltimestart<_lendtime) { 
 //BA.debugLineNum = 1133;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1134;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699374",(""),0);
 //BA.debugLineNum = 1135;BA.debugLine="LogColor(\"2nd Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699375","2nd Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_ltimeend>_lstarttime && _ltimeend<_lendtime) { 
 //BA.debugLineNum = 1137;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1138;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699378",(""),0);
 //BA.debugLineNum = 1139;BA.debugLine="LogColor(\"3rd Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699379","3rd Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_lstarttime>_ltimestart && _lstarttime<_ltimeend) { 
 //BA.debugLineNum = 1141;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1142;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699382",(""),0);
 //BA.debugLineNum = 1143;BA.debugLine="LogColor(\"4th Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699383","4th Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_lendtime>_ltimestart && _lendtime<_ltimeend) { 
 //BA.debugLineNum = 1145;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1146;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699386",(""),0);
 //BA.debugLineNum = 1147;BA.debugLine="LogColor(\"5th Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699387","5th Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 1150;BA.debugLine="If IsTimeOverlapped = True Then";
if (_istimeoverlapped==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1151;BA.debugLine="Exit";
if (true) break;
 };
 }
;
 //BA.debugLineNum = 1154;BA.debugLine="If IsTimeOverlapped = True Then";
if (_istimeoverlapped==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1155;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 1157;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 }else {
 //BA.debugLineNum = 1160;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e61) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e61); //BA.debugLineNum = 1163;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09699403",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 1165;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 1166;BA.debugLine="End Sub";
return false;
}
public static boolean  _ispumptimeoverlapped(anywheresoftware.b4a.BA _ba,long _ltimeon,long _ltimeoff,int _iheaderid) throws Exception{
boolean _istimeoverlapped = false;
boolean _bretval = false;
String _spumptimeon = "";
String _spumptimeoff = "";
long _lpumptimeon = 0L;
long _lpumptimeoff = 0L;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
 //BA.debugLineNum = 570;BA.debugLine="Public Sub IsPumpTimeOverlapped(lTimeOn As Long, l";
 //BA.debugLineNum = 571;BA.debugLine="Dim IsTimeOverlapped, bRetVal As Boolean";
_istimeoverlapped = false;
_bretval = false;
 //BA.debugLineNum = 572;BA.debugLine="Dim sPumpTimeOn, sPumpTimeOff As String";
_spumptimeon = "";
_spumptimeoff = "";
 //BA.debugLineNum = 573;BA.debugLine="Dim lPumpTimeOn, lPumpTimeOff As Long";
_lpumptimeon = 0L;
_lpumptimeoff = 0L;
 //BA.debugLineNum = 575;BA.debugLine="Try";
try { //BA.debugLineNum = 576;BA.debugLine="Dim RS As ResultSet = Starter.DBCon.ExecQuery2(\"";
_rs = new anywheresoftware.b4a.sql.SQL.ResultSetWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.ResultSetWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery2("SELECT * FROM OnOffDetails WHERE HeaderID = ? "+"ORDER BY	DetailID ASC",new String[]{BA.NumberToString(_iheaderid)})));
 //BA.debugLineNum = 577;BA.debugLine="LogColor(\"SELECT * FROM OnOffDetails WHERE Heade";
anywheresoftware.b4a.keywords.Common.LogImpl("08847367","SELECT * FROM OnOffDetails WHERE HeaderID = "+BA.NumberToString(_iheaderid)+" ORDER BY DetailID ASC",anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 578;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 579;BA.debugLine="IsTimeOverlapped = False";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 581;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 583;BA.debugLine="Do While RS.NextRow";
while (_rs.NextRow()) {
 //BA.debugLineNum = 584;BA.debugLine="sPumpTimeOn = RS.GetString(\"PumpOnTime\")";
_spumptimeon = _rs.GetString("PumpOnTime");
 //BA.debugLineNum = 585;BA.debugLine="sPumpTimeOff = RS.GetString(\"PumpOffTime\")";
_spumptimeoff = _rs.GetString("PumpOffTime");
 //BA.debugLineNum = 586;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 587;BA.debugLine="lPumpTimeOn = DateTime.TimeParse(sPumpTimeOn)";
_lpumptimeon = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_spumptimeon);
 //BA.debugLineNum = 588;BA.debugLine="lPumpTimeOff = DateTime.TimeParse(sPumpTimeOff";
_lpumptimeoff = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_spumptimeoff);
 //BA.debugLineNum = 589;BA.debugLine="LogColor(sPumpTimeOn,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847379",_spumptimeon,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 590;BA.debugLine="LogColor(sPumpTimeOff,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847380",_spumptimeoff,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 592;BA.debugLine="LogColor($\"Input On: \"$ & lTimeOn,Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847382",("Input On: ")+BA.NumberToString(_ltimeon),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 593;BA.debugLine="LogColor($\"Input Off: \"$ & lTimeOff,Colors.Mag";
anywheresoftware.b4a.keywords.Common.LogImpl("08847383",("Input Off: ")+BA.NumberToString(_ltimeoff),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 594;BA.debugLine="LogColor($\"Data On: \"$ & RS.Position & $\" \"$ &";
anywheresoftware.b4a.keywords.Common.LogImpl("08847384",("Data On: ")+BA.NumberToString(_rs.getPosition())+(" ")+BA.NumberToString(_lpumptimeon),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 595;BA.debugLine="LogColor($\"Data Off: \"$ & RS.Position & $\" \"$";
anywheresoftware.b4a.keywords.Common.LogImpl("08847385",("Data Off: ")+BA.NumberToString(_rs.getPosition())+(" ")+BA.NumberToString(_lpumptimeoff),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 597;BA.debugLine="If lTimeOn = lPumpTimeOn Or lTimeOff = lPumpTi";
if (_ltimeon==_lpumptimeon || _ltimeoff==_lpumptimeoff || _ltimeon==_lpumptimeoff || _ltimeoff==_lpumptimeon) { 
 //BA.debugLineNum = 598;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 599;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847389",(""),0);
 //BA.debugLineNum = 600;BA.debugLine="LogColor(\"1st Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847390","1st Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_ltimeon>_lpumptimeon && _ltimeon<_lpumptimeoff) { 
 //BA.debugLineNum = 602;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 603;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847393",(""),0);
 //BA.debugLineNum = 604;BA.debugLine="LogColor(\"2nd Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847394","2nd Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_ltimeoff>_lpumptimeon && _ltimeoff<_lpumptimeoff) { 
 //BA.debugLineNum = 606;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 607;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847397",(""),0);
 //BA.debugLineNum = 608;BA.debugLine="LogColor(\"3rd Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847398","3rd Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_lpumptimeon>_ltimeon && _lpumptimeon<_ltimeoff) { 
 //BA.debugLineNum = 610;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 611;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847401",(""),0);
 //BA.debugLineNum = 612;BA.debugLine="LogColor(\"4th Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847402","4th Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_lpumptimeoff>_ltimeon && _lpumptimeoff<_ltimeoff) { 
 //BA.debugLineNum = 614;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 615;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847405",(""),0);
 //BA.debugLineNum = 616;BA.debugLine="LogColor(\"5th Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847406","5th Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 619;BA.debugLine="If IsTimeOverlapped = True Then";
if (_istimeoverlapped==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 620;BA.debugLine="Exit";
if (true) break;
 };
 }
;
 //BA.debugLineNum = 623;BA.debugLine="If IsTimeOverlapped = True Then";
if (_istimeoverlapped==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 624;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 626;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 }else {
 //BA.debugLineNum = 629;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e56) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e56); //BA.debugLineNum = 632;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08847422",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 634;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 635;BA.debugLine="End Sub";
return false;
}
public static boolean  _ispumptimeoverlappededit(anywheresoftware.b4a.BA _ba,long _ltimeon,long _ltimeoff,int _iheaderid,int _idetailid) throws Exception{
boolean _istimeoverlapped = false;
boolean _bretval = false;
String _spumptimeon = "";
String _spumptimeoff = "";
long _lpumptimeon = 0L;
long _lpumptimeoff = 0L;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
 //BA.debugLineNum = 637;BA.debugLine="Public Sub IsPumpTimeOverlappedEdit(lTimeOn As Lon";
 //BA.debugLineNum = 638;BA.debugLine="Dim IsTimeOverlapped, bRetVal As Boolean";
_istimeoverlapped = false;
_bretval = false;
 //BA.debugLineNum = 639;BA.debugLine="Dim sPumpTimeOn, sPumpTimeOff As String";
_spumptimeon = "";
_spumptimeoff = "";
 //BA.debugLineNum = 640;BA.debugLine="Dim lPumpTimeOn, lPumpTimeOff As Long";
_lpumptimeon = 0L;
_lpumptimeoff = 0L;
 //BA.debugLineNum = 642;BA.debugLine="Try";
try { //BA.debugLineNum = 643;BA.debugLine="Dim RS As ResultSet = Starter.DBCon.ExecQuery2(\"";
_rs = new anywheresoftware.b4a.sql.SQL.ResultSetWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.ResultSetWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery2("SELECT * FROM OnOffDetails WHERE HeaderID = ? AND DetailID <> ? "+"ORDER BY DetailID ASC",new String[]{BA.NumberToString(_iheaderid),BA.NumberToString(_idetailid)})));
 //BA.debugLineNum = 644;BA.debugLine="LogColor(\"SELECT * FROM OnOffDetails WHERE Heade";
anywheresoftware.b4a.keywords.Common.LogImpl("08912903","SELECT * FROM OnOffDetails WHERE HeaderID = "+BA.NumberToString(_iheaderid)+" ORDER BY DetailID ASC",anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 645;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 646;BA.debugLine="IsTimeOverlapped = False";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 648;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 650;BA.debugLine="Do While RS.NextRow";
while (_rs.NextRow()) {
 //BA.debugLineNum = 651;BA.debugLine="sPumpTimeOn = RS.GetString(\"PumpOnTime\")";
_spumptimeon = _rs.GetString("PumpOnTime");
 //BA.debugLineNum = 652;BA.debugLine="sPumpTimeOff = RS.GetString(\"PumpOffTime\")";
_spumptimeoff = _rs.GetString("PumpOffTime");
 //BA.debugLineNum = 653;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 654;BA.debugLine="lPumpTimeOn = DateTime.TimeParse(sPumpTimeOn)";
_lpumptimeon = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_spumptimeon);
 //BA.debugLineNum = 655;BA.debugLine="lPumpTimeOff = DateTime.TimeParse(sPumpTimeOff";
_lpumptimeoff = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_spumptimeoff);
 //BA.debugLineNum = 656;BA.debugLine="LogColor(sPumpTimeOn,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912915",_spumptimeon,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 657;BA.debugLine="LogColor(sPumpTimeOff,Colors.Yellow)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912916",_spumptimeoff,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 659;BA.debugLine="LogColor($\"Input On: \"$ & lTimeOn,Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912918",("Input On: ")+BA.NumberToString(_ltimeon),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 660;BA.debugLine="LogColor($\"Input Off: \"$ & lTimeOff,Colors.Mag";
anywheresoftware.b4a.keywords.Common.LogImpl("08912919",("Input Off: ")+BA.NumberToString(_ltimeoff),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 661;BA.debugLine="LogColor($\"Data On: \"$ & RS.Position & $\" \"$ &";
anywheresoftware.b4a.keywords.Common.LogImpl("08912920",("Data On: ")+BA.NumberToString(_rs.getPosition())+(" ")+BA.NumberToString(_lpumptimeon),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 662;BA.debugLine="LogColor($\"Data Off: \"$ & RS.Position & $\" \"$";
anywheresoftware.b4a.keywords.Common.LogImpl("08912921",("Data Off: ")+BA.NumberToString(_rs.getPosition())+(" ")+BA.NumberToString(_lpumptimeoff),anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 664;BA.debugLine="If lTimeOn = lPumpTimeOn Or lTimeOff = lPumpTi";
if (_ltimeon==_lpumptimeon || _ltimeoff==_lpumptimeoff || _ltimeon==_lpumptimeoff || _ltimeoff==_lpumptimeon) { 
 //BA.debugLineNum = 665;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 666;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912925",(""),0);
 //BA.debugLineNum = 667;BA.debugLine="LogColor(\"1st Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912926","1st Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_ltimeon>_lpumptimeon && _ltimeon<_lpumptimeoff) { 
 //BA.debugLineNum = 669;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 670;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912929",(""),0);
 //BA.debugLineNum = 671;BA.debugLine="LogColor(\"2nd Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912930","2nd Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_ltimeoff>_lpumptimeon && _ltimeoff<_lpumptimeoff) { 
 //BA.debugLineNum = 673;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 674;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912933",(""),0);
 //BA.debugLineNum = 675;BA.debugLine="LogColor(\"3rd Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912934","3rd Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_lpumptimeon>_ltimeon && _lpumptimeon<_ltimeoff) { 
 //BA.debugLineNum = 677;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 678;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912937",(""),0);
 //BA.debugLineNum = 679;BA.debugLine="LogColor(\"4th Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912938","4th Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_lpumptimeoff>_ltimeon && _lpumptimeoff<_ltimeoff) { 
 //BA.debugLineNum = 681;BA.debugLine="IsTimeOverlapped = True";
_istimeoverlapped = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 682;BA.debugLine="Log($\"\"$)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912941",(""),0);
 //BA.debugLineNum = 683;BA.debugLine="LogColor(\"5th Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912942","5th Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 686;BA.debugLine="If IsTimeOverlapped = True Then";
if (_istimeoverlapped==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 687;BA.debugLine="Exit";
if (true) break;
 };
 }
;
 //BA.debugLineNum = 690;BA.debugLine="If IsTimeOverlapped = True Then";
if (_istimeoverlapped==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 691;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 693;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 }else {
 //BA.debugLineNum = 696;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e56) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e56); //BA.debugLineNum = 699;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08912958",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 701;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 702;BA.debugLine="End Sub";
return false;
}
public static boolean  _isreadingtimeoverlapping(anywheresoftware.b4a.BA _ba,String _sreadtime,int _itranheaderid) throws Exception{
boolean _bretval = false;
long _lreadtime = 0L;
long _lrdgtime = 0L;
long _lpumpoff = 0L;
boolean _test1 = false;
boolean _test2 = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
 //BA.debugLineNum = 893;BA.debugLine="Public Sub IsReadingTimeOverlapping (sReadTime As";
 //BA.debugLineNum = 894;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 895;BA.debugLine="Dim lReadTime, lRdgTime, lPumpOff As Long";
_lreadtime = 0L;
_lrdgtime = 0L;
_lpumpoff = 0L;
 //BA.debugLineNum = 897;BA.debugLine="Dim Test1, Test2 As Boolean";
_test1 = false;
_test2 = false;
 //BA.debugLineNum = 900;BA.debugLine="Test1 = False";
_test1 = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 901;BA.debugLine="Test2 = False";
_test2 = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 903;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 906;BA.debugLine="LogColor($\"String Inputted Time On : \"$ & sReadTi";
anywheresoftware.b4a.keywords.Common.LogImpl("09240589",("String Inputted Time On : ")+_sreadtime,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 907;BA.debugLine="LogColor($\"Parsed Inputted Time On : \"$ & lReadTi";
anywheresoftware.b4a.keywords.Common.LogImpl("09240590",("Parsed Inputted Time On : ")+BA.NumberToString(_lreadtime),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 909;BA.debugLine="Try";
try { //BA.debugLineNum = 910;BA.debugLine="Dim RS As ResultSet = Starter.DBCon.ExecQuery2(\"";
_rs = new anywheresoftware.b4a.sql.SQL.ResultSetWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.ResultSetWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery2("SELECT * FROM ProductionDetails WHERE Time('"+_sreadtime+"') > TIME(RdgTime) AND Time('"+_sreadtime+"') < TIME(RdgTime) AND HeaderID = ? "+" ORDER BY DetailID ASC",new String[]{BA.NumberToString(_itranheaderid)})));
 //BA.debugLineNum = 912;BA.debugLine="LogColor(\"SELECT * FROM ProductionDetails WHERE";
anywheresoftware.b4a.keywords.Common.LogImpl("09240595","SELECT * FROM ProductionDetails WHERE Time('"+_sreadtime+"') > TIME(RdgTime) AND Time('"+_sreadtime+"') < TIME(RdgTime) AND HeaderID = ? "+" ORDER BY DetailID ASC",anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 914;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 916;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 917;BA.debugLine="Do While RS.NextRow";
while (_rs.NextRow()) {
 //BA.debugLineNum = 918;BA.debugLine="sReadTime = RS.GetString(\"RdgTime\")";
_sreadtime = _rs.GetString("RdgTime");
 //BA.debugLineNum = 919;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 921;BA.debugLine="lRdgTime = DateTime.TimeParse(sReadTime)";
_lrdgtime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_sreadtime);
 //BA.debugLineNum = 923;BA.debugLine="If lReadTime = lRdgTime Then";
if (_lreadtime==_lrdgtime) { 
 //BA.debugLineNum = 924;BA.debugLine="Return True 'Overlapped 1st Test";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 927;BA.debugLine="If lReadTime < lRdgTime Then";
if (_lreadtime<_lrdgtime) { 
 //BA.debugLineNum = 928;BA.debugLine="Test1 = True 'Overlapped 1st Test";
_test1 = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 929;BA.debugLine="Exit";
if (true) break;
 };
 //BA.debugLineNum = 932;BA.debugLine="If lReadTime > lRdgTime Then";
if (_lreadtime>_lrdgtime) { 
 //BA.debugLineNum = 933;BA.debugLine="Test2 = True 'Overlapped 2nd Test";
_test2 = anywheresoftware.b4a.keywords.Common.True;
 };
 }
;
 //BA.debugLineNum = 937;BA.debugLine="If Test1 = True Then";
if (_test1==anywheresoftware.b4a.keywords.Common.True) { 
 };
 }else {
 //BA.debugLineNum = 942;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e35) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e35); //BA.debugLineNum = 945;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 946;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09240629",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 948;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 949;BA.debugLine="End Sub";
return false;
}
public static boolean  _isreadtimeoverlapse(anywheresoftware.b4a.BA _ba,String _sreadtime,int _itranheaderid) throws Exception{
boolean _bretval = false;
String _firstrdgtime = "";
String _lastrdgtime = "";
long _lfirstrdgtime = 0L;
long _llastrdgtime = 0L;
long _lreadtime = 0L;
 //BA.debugLineNum = 991;BA.debugLine="Public Sub IsReadTimeOverlapse(sReadTime As String";
 //BA.debugLineNum = 992;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 993;BA.debugLine="Dim FirstRdgTime, LastRdgTime As String";
_firstrdgtime = "";
_lastrdgtime = "";
 //BA.debugLineNum = 994;BA.debugLine="Dim lFirstRdgTime, lLastRdgTime As Long";
_lfirstrdgtime = 0L;
_llastrdgtime = 0L;
 //BA.debugLineNum = 995;BA.debugLine="Dim lReadTime As Long";
_lreadtime = 0L;
 //BA.debugLineNum = 997;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 998;BA.debugLine="FirstRdgTime = 0";
_firstrdgtime = BA.NumberToString(0);
 //BA.debugLineNum = 999;BA.debugLine="LastRdgTime = 0";
_lastrdgtime = BA.NumberToString(0);
 //BA.debugLineNum = 1001;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 1002;BA.debugLine="LogColor(DateTime.TimeParse(sReadTime),Colors.Yel";
anywheresoftware.b4a.keywords.Common.LogImpl("09437195",BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_sreadtime)),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 1004;BA.debugLine="lReadTime = DateTime.TimeParse(sReadTime)";
_lreadtime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_sreadtime);
 //BA.debugLineNum = 1006;BA.debugLine="Try";
try { //BA.debugLineNum = 1007;BA.debugLine="FirstRdgTime = GetFirstRdgTime(iTranHeaderID)";
_firstrdgtime = _getfirstrdgtime(_ba,_itranheaderid);
 //BA.debugLineNum = 1008;BA.debugLine="LogColor(FirstRdgTime, Colors.Cyan)";
anywheresoftware.b4a.keywords.Common.LogImpl("09437201",_firstrdgtime,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 } 
       catch (Exception e15) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e15); //BA.debugLineNum = 1010;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09437203",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 1011;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 1014;BA.debugLine="Try";
try { //BA.debugLineNum = 1015;BA.debugLine="LastRdgTime = GetLastRdgTime(iTranHeaderID)";
_lastrdgtime = _getlastrdgtime(_ba,_itranheaderid);
 //BA.debugLineNum = 1016;BA.debugLine="LogColor(LastRdgTime, Colors.Magenta)";
anywheresoftware.b4a.keywords.Common.LogImpl("09437209",_lastrdgtime,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 } 
       catch (Exception e22) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e22); //BA.debugLineNum = 1018;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09437211",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 1019;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 1022;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 1023;BA.debugLine="LogColor(DateTime.TimeParse(FirstRdgTime),Colors.";
anywheresoftware.b4a.keywords.Common.LogImpl("09437216",BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_firstrdgtime)),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 1025;BA.debugLine="lFirstRdgTime = DateTime.TimeParse(FirstRdgTime)";
_lfirstrdgtime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_firstrdgtime);
 //BA.debugLineNum = 1027;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 1028;BA.debugLine="LogColor(DateTime.TimeParse(LastRdgTime),Colors.Y";
anywheresoftware.b4a.keywords.Common.LogImpl("09437221",BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_lastrdgtime)),anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 1030;BA.debugLine="lLastRdgTime = DateTime.TimeParse(LastRdgTime)";
_llastrdgtime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_lastrdgtime);
 //BA.debugLineNum = 1032;BA.debugLine="Try";
try { //BA.debugLineNum = 1033;BA.debugLine="If lReadTime >= lFirstRdgTime And lReadTime <= l";
if (_lreadtime>=_lfirstrdgtime && _lreadtime<=_llastrdgtime) { 
 //BA.debugLineNum = 1034;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 1036;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e38) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e38); //BA.debugLineNum = 1040;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09437233",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 //BA.debugLineNum = 1041;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 1043;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 1044;BA.debugLine="End Sub";
return false;
}
public static boolean  _istimeoffoverlapping(anywheresoftware.b4a.BA _ba,String _stimeoff,int _itranheaderid,int _idetailedid) throws Exception{
boolean _bretval = false;
String _spumpon = "";
String _spumpoff = "";
long _ltimeoff = 0L;
long _lpumpon = 0L;
long _lpumpoff = 0L;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
 //BA.debugLineNum = 750;BA.debugLine="Public Sub IsTimeOffOverlapping (sTimeOff As Strin";
 //BA.debugLineNum = 751;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 752;BA.debugLine="Dim sPumpOn, sPumpOff As String";
_spumpon = "";
_spumpoff = "";
 //BA.debugLineNum = 753;BA.debugLine="Dim lTimeOff, lPumpOn, lPumpOff As Long";
_ltimeoff = 0L;
_lpumpon = 0L;
_lpumpoff = 0L;
 //BA.debugLineNum = 755;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 756;BA.debugLine="lTimeOff = DateTime.TimeParse(sTimeOff)";
_ltimeoff = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_stimeoff);
 //BA.debugLineNum = 757;BA.debugLine="LogColor($\"String Inputted Time Off : \"$ & sTimeO";
anywheresoftware.b4a.keywords.Common.LogImpl("09043975",("String Inputted Time Off : ")+_stimeoff,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 758;BA.debugLine="LogColor($\"Parsed Inputted Time Off : \"$ & lTimeO";
anywheresoftware.b4a.keywords.Common.LogImpl("09043976",("Parsed Inputted Time Off : ")+BA.NumberToString(_ltimeoff),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 760;BA.debugLine="Try";
try { //BA.debugLineNum = 761;BA.debugLine="Dim RS As ResultSet = Starter.DBCon.ExecQuery2(\"";
_rs = new anywheresoftware.b4a.sql.SQL.ResultSetWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.ResultSetWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery2("SELECT * FROM OnOffDetails WHERE HeaderID = ? AND DetailID <> ? "+" ORDER BY DetailID ASC",new String[]{BA.NumberToString(_itranheaderid),BA.NumberToString(_idetailedid)})));
 //BA.debugLineNum = 763;BA.debugLine="LogColor(\"SELECT * FROM OnOffDetails WHERE Heade";
anywheresoftware.b4a.keywords.Common.LogImpl("09043981","SELECT * FROM OnOffDetails WHERE HeaderID = "+BA.NumberToString(_itranheaderid)+" ORDER BY DetailID ASC",anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 765;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 767;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 768;BA.debugLine="Do While RS.NextRow";
while (_rs.NextRow()) {
 //BA.debugLineNum = 769;BA.debugLine="sPumpOn = RS.GetString(\"PumpOnTime\")";
_spumpon = _rs.GetString("PumpOnTime");
 //BA.debugLineNum = 770;BA.debugLine="sPumpOff = RS.GetString(\"PumpOffTime\")";
_spumpoff = _rs.GetString("PumpOffTime");
 //BA.debugLineNum = 771;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 773;BA.debugLine="lPumpOn = DateTime.TimeParse(sPumpOn)";
_lpumpon = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_spumpon);
 //BA.debugLineNum = 774;BA.debugLine="lPumpOff = DateTime.TimeParse(sPumpOff)";
_lpumpoff = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_spumpoff);
 //BA.debugLineNum = 775;BA.debugLine="LogColor($\"Parsed Existing Time Off : \"$ & lPu";
anywheresoftware.b4a.keywords.Common.LogImpl("09043993",("Parsed Existing Time Off : ")+BA.NumberToString(_lpumpoff),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 777;BA.debugLine="If lTimeOff = lPumpOn Or lTimeOff = lPumpOff T";
if (_ltimeoff==_lpumpon || _ltimeoff==_lpumpoff) { 
 //BA.debugLineNum = 778;BA.debugLine="bRetVal = True 'Overlapped 1st Test";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 779;BA.debugLine="Exit";
if (true) break;
 }else if(_ltimeoff>_lpumpon && _ltimeoff<_lpumpoff) { 
 //BA.debugLineNum = 781;BA.debugLine="bRetVal = True 'Overlapped 2nd Test";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 782;BA.debugLine="Exit";
if (true) break;
 };
 }
;
 }else {
 //BA.debugLineNum = 787;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e32) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e32); //BA.debugLineNum = 790;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 791;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09044009",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 793;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 794;BA.debugLine="End Sub";
return false;
}
public static boolean  _istimeonearly(anywheresoftware.b4a.BA _ba,String _stimeon,int _itranheaderid,int _idetailedid) throws Exception{
boolean _bretval = false;
String _spumpon = "";
long _ltimeon = 0L;
long _lpumpon = 0L;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
 //BA.debugLineNum = 853;BA.debugLine="Public Sub IsTimeOnEarly (sTimeOn As String, iTran";
 //BA.debugLineNum = 854;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 855;BA.debugLine="Dim sPumpOn As String";
_spumpon = "";
 //BA.debugLineNum = 856;BA.debugLine="Dim lTimeOn, lPumpOn As Long";
_ltimeon = 0L;
_lpumpon = 0L;
 //BA.debugLineNum = 858;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 859;BA.debugLine="lTimeOn = DateTime.TimeParse(sTimeOn)";
_ltimeon = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_stimeon);
 //BA.debugLineNum = 860;BA.debugLine="LogColor($\"String Inputted Time On : \"$ & sTimeOn";
anywheresoftware.b4a.keywords.Common.LogImpl("09175047",("String Inputted Time On : ")+_stimeon,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 861;BA.debugLine="LogColor($\"Parsed Inputted Time On : \"$ & lTimeOn";
anywheresoftware.b4a.keywords.Common.LogImpl("09175048",("Parsed Inputted Time On : ")+BA.NumberToString(_ltimeon),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 863;BA.debugLine="Try";
try { //BA.debugLineNum = 864;BA.debugLine="Dim RS As ResultSet = Starter.DBCon.ExecQuery2(\"";
_rs = new anywheresoftware.b4a.sql.SQL.ResultSetWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.ResultSetWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery2("SELECT * FROM OnOffDetails WHERE HeaderID = ? AND DetailID <> ? "+" ORDER BY PumpOnTime, DetailID ASC",new String[]{BA.NumberToString(_itranheaderid),BA.NumberToString(_idetailedid)})));
 //BA.debugLineNum = 866;BA.debugLine="LogColor(\"SELECT * FROM OnOffDetails WHERE Heade";
anywheresoftware.b4a.keywords.Common.LogImpl("09175053","SELECT * FROM OnOffDetails WHERE HeaderID = "+BA.NumberToString(_itranheaderid)+" ORDER BY DetailID ASC",anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 868;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 870;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 871;BA.debugLine="Do While RS.NextRow";
while (_rs.NextRow()) {
 //BA.debugLineNum = 872;BA.debugLine="sPumpOn = RS.GetString(\"PumpOnTime\")";
_spumpon = _rs.GetString("PumpOnTime");
 //BA.debugLineNum = 873;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 875;BA.debugLine="lPumpOn = DateTime.TimeParse(sPumpOn)";
_lpumpon = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_spumpon);
 //BA.debugLineNum = 877;BA.debugLine="If lTimeOn < lPumpOn Then";
if (_ltimeon<_lpumpon) { 
 //BA.debugLineNum = 878;BA.debugLine="bRetVal = True 'Force Turn Off";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 879;BA.debugLine="Exit";
if (true) break;
 };
 }
;
 }else {
 //BA.debugLineNum = 884;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e26) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e26); //BA.debugLineNum = 887;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 888;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09175075",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 890;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 891;BA.debugLine="End Sub";
return false;
}
public static boolean  _istimeonoffoverlapping(anywheresoftware.b4a.BA _ba,String _stimeon,String _stimeoff,int _itranheaderid,int _idetailedid) throws Exception{
boolean _bretval = false;
String _spumpon = "";
String _spumpoff = "";
long _ltimeon = 0L;
long _ltimeoff = 0L;
long _lpumpon = 0L;
long _lpumpoff = 0L;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
 //BA.debugLineNum = 796;BA.debugLine="Public Sub IsTimeOnOffOverlapping (sTimeOn As Stri";
 //BA.debugLineNum = 797;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 798;BA.debugLine="Dim sPumpOn, sPumpOff As String";
_spumpon = "";
_spumpoff = "";
 //BA.debugLineNum = 799;BA.debugLine="Dim lTimeOn, lTimeOff, lPumpOn, lPumpOff As Long";
_ltimeon = 0L;
_ltimeoff = 0L;
_lpumpon = 0L;
_lpumpoff = 0L;
 //BA.debugLineNum = 801;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 802;BA.debugLine="lTimeOn = DateTime.TimeParse(sTimeOn)";
_ltimeon = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_stimeon);
 //BA.debugLineNum = 803;BA.debugLine="lTimeOff = DateTime.TimeParse(sTimeOff)";
_ltimeoff = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_stimeoff);
 //BA.debugLineNum = 804;BA.debugLine="LogColor($\"String Inputted Time On : \"$ & sTimeOn";
anywheresoftware.b4a.keywords.Common.LogImpl("09109512",("String Inputted Time On : ")+_stimeon,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 805;BA.debugLine="LogColor($\"Parsed Inputted Time On : \"$ & lTimeOn";
anywheresoftware.b4a.keywords.Common.LogImpl("09109513",("Parsed Inputted Time On : ")+BA.NumberToString(_ltimeon),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 806;BA.debugLine="LogColor($\"String Inputted Time Off : \"$ & sTimeO";
anywheresoftware.b4a.keywords.Common.LogImpl("09109514",("String Inputted Time Off : ")+_stimeoff,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 807;BA.debugLine="LogColor($\"Parsed Inputted Time Off : \"$ & lTimeO";
anywheresoftware.b4a.keywords.Common.LogImpl("09109515",("Parsed Inputted Time Off : ")+BA.NumberToString(_ltimeoff),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 809;BA.debugLine="Try";
try { //BA.debugLineNum = 810;BA.debugLine="Dim RS As ResultSet = Starter.DBCon.ExecQuery2(\"";
_rs = new anywheresoftware.b4a.sql.SQL.ResultSetWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.ResultSetWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery2("SELECT * FROM OnOffDetails WHERE HeaderID = ? AND DetailID <> ? "+" ORDER BY DetailID ASC",new String[]{BA.NumberToString(_itranheaderid),BA.NumberToString(_idetailedid)})));
 //BA.debugLineNum = 812;BA.debugLine="LogColor(\"SELECT * FROM OnOffDetails WHERE Heade";
anywheresoftware.b4a.keywords.Common.LogImpl("09109520","SELECT * FROM OnOffDetails WHERE HeaderID = "+BA.NumberToString(_itranheaderid)+" ORDER BY DetailID ASC",anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 814;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 816;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 817;BA.debugLine="Do While RS.NextRow";
while (_rs.NextRow()) {
 //BA.debugLineNum = 818;BA.debugLine="sPumpOn = RS.GetString(\"PumpOnTime\")";
_spumpon = _rs.GetString("PumpOnTime");
 //BA.debugLineNum = 819;BA.debugLine="sPumpOff = RS.GetString(\"PumpOffTime\")";
_spumpoff = _rs.GetString("PumpOffTime");
 //BA.debugLineNum = 820;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 822;BA.debugLine="lPumpOn = DateTime.TimeParse(sPumpOn)";
_lpumpon = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_spumpon);
 //BA.debugLineNum = 823;BA.debugLine="lPumpOff = DateTime.TimeParse(sPumpOff)";
_lpumpoff = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_spumpoff);
 //BA.debugLineNum = 825;BA.debugLine="If lTimeOn = lPumpOn Or lTimeOff = lPumpOff Or";
if (_ltimeon==_lpumpon || _ltimeoff==_lpumpoff || _ltimeon==_lpumpoff || _ltimeoff==_lpumpon) { 
 //BA.debugLineNum = 826;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 827;BA.debugLine="LogColor(\"1st Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("09109535","1st Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_ltimeon>_lpumpon && _ltimeon<_lpumpoff) { 
 //BA.debugLineNum = 829;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 830;BA.debugLine="LogColor(\"2nd Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("09109538","2nd Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_ltimeoff>_lpumpon && _ltimeoff<_lpumpoff) { 
 //BA.debugLineNum = 832;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 833;BA.debugLine="LogColor(\"3rd Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("09109541","3rd Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_lpumpon>_ltimeon && _lpumpon<_ltimeoff) { 
 //BA.debugLineNum = 835;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 836;BA.debugLine="LogColor(\"4th Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("09109544","4th Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 }else if(_lpumpoff>_ltimeon && _lpumpoff<_ltimeoff) { 
 //BA.debugLineNum = 838;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 839;BA.debugLine="LogColor(\"5th Test\",Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("09109547","5th Test",anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 }
;
 }else {
 //BA.debugLineNum = 844;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e43) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e43); //BA.debugLineNum = 847;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 848;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09109556",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 850;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 851;BA.debugLine="End Sub";
return false;
}
public static boolean  _istimeonoverlapping(anywheresoftware.b4a.BA _ba,String _stimeon,int _itranheaderid) throws Exception{
boolean _bretval = false;
String _spumpon = "";
String _spumpoff = "";
long _ltimeon = 0L;
long _lpumpon = 0L;
long _lpumpoff = 0L;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
 //BA.debugLineNum = 704;BA.debugLine="Public Sub IsTimeOnOverlapping (sTimeOn As String,";
 //BA.debugLineNum = 705;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 706;BA.debugLine="Dim sPumpOn, sPumpOff As String";
_spumpon = "";
_spumpoff = "";
 //BA.debugLineNum = 707;BA.debugLine="Dim lTimeOn, lPumpOn, lPumpOff As Long";
_ltimeon = 0L;
_lpumpon = 0L;
_lpumpoff = 0L;
 //BA.debugLineNum = 709;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 710;BA.debugLine="lTimeOn = DateTime.TimeParse(sTimeOn)";
_ltimeon = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_stimeon);
 //BA.debugLineNum = 712;BA.debugLine="LogColor($\"String Inputted Time On : \"$ & sTimeOn";
anywheresoftware.b4a.keywords.Common.LogImpl("08978440",("String Inputted Time On : ")+_stimeon,anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 713;BA.debugLine="LogColor($\"Parsed Inputted Time On : \"$ & lTimeOn";
anywheresoftware.b4a.keywords.Common.LogImpl("08978441",("Parsed Inputted Time On : ")+BA.NumberToString(_ltimeon),anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 715;BA.debugLine="Try";
try { //BA.debugLineNum = 716;BA.debugLine="Dim RS As ResultSet = Starter.DBCon.ExecQuery2(\"";
_rs = new anywheresoftware.b4a.sql.SQL.ResultSetWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.ResultSetWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery2("SELECT * FROM OnOffDetails WHERE HeaderID = ? "+" ORDER BY DetailID ASC",new String[]{BA.NumberToString(_itranheaderid)})));
 //BA.debugLineNum = 718;BA.debugLine="LogColor(\"SELECT * FROM OnOffDetails WHERE Heade";
anywheresoftware.b4a.keywords.Common.LogImpl("08978446","SELECT * FROM OnOffDetails WHERE HeaderID = "+BA.NumberToString(_itranheaderid)+" ORDER BY DetailID ASC",anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 720;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 722;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 723;BA.debugLine="Do While RS.NextRow";
while (_rs.NextRow()) {
 //BA.debugLineNum = 724;BA.debugLine="sPumpOn = RS.GetString(\"PumpOnTime\")";
_spumpon = _rs.GetString("PumpOnTime");
 //BA.debugLineNum = 725;BA.debugLine="sPumpOff = RS.GetString(\"PumpOffTime\")";
_spumpoff = _rs.GetString("PumpOffTime");
 //BA.debugLineNum = 726;BA.debugLine="DateTime.TimeFormat = \"hh:mm a\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("hh:mm a");
 //BA.debugLineNum = 728;BA.debugLine="lPumpOn = DateTime.TimeParse(sPumpOn)";
_lpumpon = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_spumpon);
 //BA.debugLineNum = 729;BA.debugLine="lPumpOff = DateTime.TimeParse(sPumpOff)";
_lpumpoff = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_spumpoff);
 //BA.debugLineNum = 731;BA.debugLine="If lTimeOn = lPumpOn Or lTimeOn = lPumpOff The";
if (_ltimeon==_lpumpon || _ltimeon==_lpumpoff) { 
 //BA.debugLineNum = 732;BA.debugLine="bRetVal = True 'Overlapped 1st Test";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 733;BA.debugLine="Exit";
if (true) break;
 }else if(_ltimeon>_lpumpon && _ltimeon<_lpumpoff) { 
 //BA.debugLineNum = 735;BA.debugLine="bRetVal = True 'Overlapped 2nd Test";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 736;BA.debugLine="Exit";
if (true) break;
 };
 }
;
 }else {
 //BA.debugLineNum = 741;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e31) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e31); //BA.debugLineNum = 744;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 745;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("08978473",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 747;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 748;BA.debugLine="End Sub";
return false;
}
public static boolean  _istransactionheaderexist(anywheresoftware.b4a.BA _ba,int _ipumpid,String _strandate) throws Exception{
boolean _bretval = false;
 //BA.debugLineNum = 218;BA.debugLine="Public Sub IsTransactionHeaderExist (iPumpID As In";
 //BA.debugLineNum = 219;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 221;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 222;BA.debugLine="Try";
try { //BA.debugLineNum = 223;BA.debugLine="Starter.strCriteria = \"SELECT * FROM TranHeader";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM TranHeader "+"WHERE PumpID = "+BA.NumberToString(_ipumpid)+" "+"AND TranDate = '"+_strandate+"'";
 //BA.debugLineNum = 227;BA.debugLine="LogColor(Starter.strCriteria, Colors.White)";
anywheresoftware.b4a.keywords.Common.LogImpl("07929865",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 229;BA.debugLine="rsTemp = Starter.DBCon.ExecQuery(Starter.strCrit";
_rstemp = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 231;BA.debugLine="If rsTemp.RowCount > 0 Then";
if (_rstemp.getRowCount()>0) { 
 //BA.debugLineNum = 232;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 234;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e13) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e13); //BA.debugLineNum = 237;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 238;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("07929876",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 240;BA.debugLine="rsTemp.Close";
_rstemp.Close();
 //BA.debugLineNum = 241;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 242;BA.debugLine="End Sub";
return false;
}
public static long  _parsedateandtime(anywheresoftware.b4a.BA _ba,String _d,String _t) throws Exception{
long _dd = 0L;
long _tt = 0L;
long _total = 0L;
 //BA.debugLineNum = 1077;BA.debugLine="Sub ParseDateAndTime(d As String, t As String) As";
 //BA.debugLineNum = 1079;BA.debugLine="Dim dd = DateTime.DateParse(d), tt = DateTime.Tim";
_dd = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(_d);
_tt = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_t);
 //BA.debugLineNum = 1080;BA.debugLine="tt = (tt + DateTime.TimeZoneOffset * DateTime.Tic";
_tt = (long) ((_tt+anywheresoftware.b4a.keywords.Common.DateTime.getTimeZoneOffset()*anywheresoftware.b4a.keywords.Common.DateTime.TicksPerHour)%anywheresoftware.b4a.keywords.Common.DateTime.TicksPerDay);
 //BA.debugLineNum = 1081;BA.debugLine="Dim total As Long";
_total = 0L;
 //BA.debugLineNum = 1082;BA.debugLine="total = dd + tt + _       (DateTime.GetTimeZoneOf";
_total = (long) (_dd+_tt+(anywheresoftware.b4a.keywords.Common.DateTime.GetTimeZoneOffsetAt(_dd)-anywheresoftware.b4a.keywords.Common.DateTime.GetTimeZoneOffsetAt((long) (_dd+_tt)))*anywheresoftware.b4a.keywords.Common.DateTime.TicksPerHour);
 //BA.debugLineNum = 1085;BA.debugLine="Return total";
if (true) return _total;
 //BA.debugLineNum = 1086;BA.debugLine="End Sub";
return 0L;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Private rsTemp As Cursor";
_rstemp = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 3;BA.debugLine="Private SF As StringFunctions";
_sf = new adr.stringfunctions.stringfunctions();
 //BA.debugLineNum = 4;BA.debugLine="End Sub";
return "";
}
public static String  _resettablesequence(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _stablename) throws Exception{
 //BA.debugLineNum = 1381;BA.debugLine="Public Sub ResetTableSequence(SQL As SQL, sTableNa";
 //BA.debugLineNum = 1382;BA.debugLine="SQL.BeginTransaction";
_sql.BeginTransaction();
 //BA.debugLineNum = 1383;BA.debugLine="Try";
try { //BA.debugLineNum = 1385;BA.debugLine="Starter.strCriteria = \"UPDATE sqlite_sequence \"";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE sqlite_sequence "+"SET seq = ? "+"WHERE name = '"+_stablename+"'";
 //BA.debugLineNum = 1388;BA.debugLine="SQL.ExecNonQuery2(Starter.strCriteria, Array As";
_sql.ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{("0")}));
 } 
       catch (Exception e6) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e6); //BA.debugLineNum = 1390;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("010420233",anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage(),0);
 };
 //BA.debugLineNum = 1392;BA.debugLine="SQL.TransactionSuccessful";
_sql.TransactionSuccessful();
 //BA.debugLineNum = 1394;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper  _roundbd(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.agraham.bignumbers.BigDecimalWrapper _bd,int _dp) throws Exception{
 //BA.debugLineNum = 1250;BA.debugLine="Sub RoundBD(BD As BigDecimal, DP As Int) As BigDec";
 //BA.debugLineNum = 1251;BA.debugLine="BD.Round(BD.Precision - BD.Scale + DP, BD.ROUND_H";
_bd.Round((int) (_bd.Precision()-_bd.Scale()+_dp),_bd.ROUND_HALF_UP);
 //BA.debugLineNum = 1252;BA.debugLine="Return BD";
if (true) return _bd;
 //BA.debugLineNum = 1253;BA.debugLine="End Sub";
return null;
}
public static boolean  _tableexists(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.sql.SQL _sql,String _tablename) throws Exception{
int _count = 0;
 //BA.debugLineNum = 1368;BA.debugLine="Public Sub TableExists(SQL As SQL, TableName As St";
 //BA.debugLineNum = 1369;BA.debugLine="Dim count As Int = SQL.ExecQuerySingleResult2(\"SE";
_count = (int)(Double.parseDouble(_sql.ExecQuerySingleResult2("SELECT count(name) FROM sqlite_master WHERE type='table' AND name=? COLLATE NOCASE",new String[]{_tablename})));
 //BA.debugLineNum = 1370;BA.debugLine="Return count > 0";
if (true) return _count>0;
 //BA.debugLineNum = 1371;BA.debugLine="End Sub";
return false;
}
public static boolean  _timeoverlapping(anywheresoftware.b4a.BA _ba,String _timestart,String _timeend,int _iheaderid) throws Exception{
String _sinputtimestart = "";
String _sinputtimeend = "";
String _sstarttime = "";
String _sendtime = "";
long _lstarttime = 0L;
long _lendtime = 0L;
long _linputstart = 0L;
long _linputend = 0L;
boolean _bretval = false;
anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
 //BA.debugLineNum = 1168;BA.debugLine="Public Sub TimeOverlapping (TimeStart As String, T";
 //BA.debugLineNum = 1169;BA.debugLine="Dim sInputTimeStart, sInputTimeEnd, sStartTime, s";
_sinputtimestart = "";
_sinputtimeend = "";
_sstarttime = "";
_sendtime = "";
 //BA.debugLineNum = 1170;BA.debugLine="Dim lStartTime, lEndTime As Long";
_lstarttime = 0L;
_lendtime = 0L;
 //BA.debugLineNum = 1171;BA.debugLine="Dim lInputStart, lInputEnd As Long";
_linputstart = 0L;
_linputend = 0L;
 //BA.debugLineNum = 1173;BA.debugLine="Dim bRetVal As Boolean";
_bretval = false;
 //BA.debugLineNum = 1175;BA.debugLine="sInputTimeStart = TimeStart & \":00\"";
_sinputtimestart = _timestart+":00";
 //BA.debugLineNum = 1176;BA.debugLine="sInputTimeEnd = TimeEnd & \":00\"";
_sinputtimeend = _timeend+":00";
 //BA.debugLineNum = 1178;BA.debugLine="DateTime.TimeFormat = \"HH:mm:ss\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm:ss");
 //BA.debugLineNum = 1179;BA.debugLine="lInputStart = DateTime.TimeParse(sInputTimeStart)";
_linputstart = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_sinputtimestart);
 //BA.debugLineNum = 1180;BA.debugLine="lInputEnd = DateTime.TimeParse(sInputTimeEnd)";
_linputend = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_sinputtimeend);
 //BA.debugLineNum = 1182;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1183;BA.debugLine="Try";
try { //BA.debugLineNum = 1184;BA.debugLine="Starter.strCriteria = \"SELECT * FROM NonOpDetail";
mostCurrent._starter._strcriteria /*String*/  = "SELECT * FROM NonOpDetails "+"WHERE HeaderID = "+BA.NumberToString(_iheaderid)+" "+"ORDER BY substr(OffTime,7,2) || (CASE WHEN substr(OffTime,1,2) = '12' AND substr(OffTime,7,2) ='AM' THEN '00' ELSE substr(OffTime,1,2) END) || ' ' || substr(OffTime,4,2) ASC";
 //BA.debugLineNum = 1188;BA.debugLine="Dim RS As ResultSet = Starter.DBCon.ExecQuery(St";
_rs = new anywheresoftware.b4a.sql.SQL.ResultSetWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.ResultSetWrapper(), (android.database.Cursor)(mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecQuery(mostCurrent._starter._strcriteria /*String*/ )));
 //BA.debugLineNum = 1190;BA.debugLine="LogColor(Starter.strCriteria, Colors.ARGB(255,25";
anywheresoftware.b4a.keywords.Common.LogImpl("09764886",mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (255),(int) (120),(int) (0)));
 //BA.debugLineNum = 1192;BA.debugLine="If RS.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 1193;BA.debugLine="Do While RS.NextRow";
while (_rs.NextRow()) {
 //BA.debugLineNum = 1194;BA.debugLine="sStartTime = RS.GetString(\"OffTime\") & \":00\"";
_sstarttime = _rs.GetString("OffTime")+":00";
 //BA.debugLineNum = 1195;BA.debugLine="sEndTime = RS.GetString(\"OnTime\") & \":00\"";
_sendtime = _rs.GetString("OnTime")+":00";
 //BA.debugLineNum = 1196;BA.debugLine="LogColor($\"Off Time: \"$ & sStartTime, Colors.M";
anywheresoftware.b4a.keywords.Common.LogImpl("09764892",("Off Time: ")+_sstarttime,anywheresoftware.b4a.keywords.Common.Colors.Magenta);
 //BA.debugLineNum = 1197;BA.debugLine="LogColor($\"On Time: \"$ & sEndTime, Colors.Cyan";
anywheresoftware.b4a.keywords.Common.LogImpl("09764893",("On Time: ")+_sendtime,anywheresoftware.b4a.keywords.Common.Colors.Cyan);
 //BA.debugLineNum = 1199;BA.debugLine="DateTime.TimeFormat = \"HH:mm:ss\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm:ss");
 //BA.debugLineNum = 1200;BA.debugLine="lStartTime = DateTime.TimeParse(sStartTime)";
_lstarttime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_sstarttime);
 //BA.debugLineNum = 1201;BA.debugLine="lEndTime = DateTime.TimeParse(sEndTime)";
_lendtime = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_sendtime);
 //BA.debugLineNum = 1203;BA.debugLine="If lInputStart > lStartTime And lInputStart <";
if (_linputstart>_lstarttime && _linputstart<_lendtime && _linputend>_lstarttime && _linputend<_lendtime) { 
 //BA.debugLineNum = 1204;BA.debugLine="bRetVal = True";
_bretval = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 1206;BA.debugLine="bRetVal = False";
_bretval = anywheresoftware.b4a.keywords.Common.False;
 };
 }
;
 }else {
 //BA.debugLineNum = 1211;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e34) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e34); //BA.debugLineNum = 1214;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("09764910",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 1216;BA.debugLine="Return bRetVal";
if (true) return _bretval;
 //BA.debugLineNum = 1217;BA.debugLine="End Sub";
return false;
}
public static String  _updatepumppowerstatus(anywheresoftware.b4a.BA _ba,int _ipumppowerstatus,int _ipumpid) throws Exception{
 //BA.debugLineNum = 101;BA.debugLine="Public Sub UpdatePumpPowerStatus(iPumpPowerStatus";
 //BA.debugLineNum = 103;BA.debugLine="Starter.DBCon.BeginTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .BeginTransaction();
 //BA.debugLineNum = 104;BA.debugLine="Try";
try { //BA.debugLineNum = 105;BA.debugLine="Starter.strCriteria = \"UPDATE tblPumpStation SET";
mostCurrent._starter._strcriteria /*String*/  = "UPDATE tblPumpStation SET "+"OnOffStatus = ? "+"WHERE StationID = "+BA.NumberToString(_ipumpid);
 //BA.debugLineNum = 109;BA.debugLine="Starter.DBCon.ExecNonQuery2(Starter.strCriteria,";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .ExecNonQuery2(mostCurrent._starter._strcriteria /*String*/ ,anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_ipumppowerstatus)}));
 //BA.debugLineNum = 110;BA.debugLine="Starter.DBCon.TransactionSuccessful";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .TransactionSuccessful();
 } 
       catch (Exception e7) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e7); //BA.debugLineNum = 112;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("07536651",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(_ba)),0);
 };
 //BA.debugLineNum = 114;BA.debugLine="Starter.DBCon.EndTransaction";
mostCurrent._starter._dbcon /*anywheresoftware.b4a.sql.SQL*/ .EndTransaction();
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return "";
}
}
