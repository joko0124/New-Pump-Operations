package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_pumpfmreading{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("toolbar").vw.setTop((int)((0d * scale)));
if ((anywheresoftware.b4a.keywords.LayoutBuilder.getScreenSize()>6.5d)) { 
;
views.get("toolbar").vw.setHeight((int)((64d * scale)));
;}else{ 
;
if ((BA.ObjectToBoolean( String.valueOf(anywheresoftware.b4a.keywords.LayoutBuilder.isPortrait())))) { 
;
views.get("toolbar").vw.setHeight((int)((50d * scale)));
;}else{ 
;
views.get("toolbar").vw.setHeight((int)((42d * scale)));
;};
;};
views.get("pnlmain").vw.setTop((int)((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight())));
views.get("pnlmain").vw.setHeight((int)((100d / 100 * height) - ((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight()))));
views.get("pnlmain").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlmain").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlkeyboard").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlkeyboard").vw.setWidth((int)((views.get("pnlmain").vw.getWidth()) - ((0d / 100 * width))));
views.get("pnlkeyboard").vw.setTop((int)((59d / 100 * height)));
views.get("pnlkeyboard").vw.setHeight((int)((views.get("pnlmain").vw.getHeight()) - ((59d / 100 * height))));
views.get("pnlreadingtime").vw.setTop((int)((5d / 100 * height)));
views.get("pnlreadingtime").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(68d / 100 * height) - ((5d / 100 * height))));
views.get("pnlreadingtime").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlreadingtime").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblrdgtimetitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblrdgtimetitle").vw.setWidth((int)((views.get("pnlreadingtime").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblrdgtimetitle").vw.setTop((int)(0d));
views.get("chkdefaulttimeread").vw.setLeft((int)((2d / 100 * width)));
views.get("chkdefaulttimeread").vw.setWidth((int)((views.get("pnlreadingtime").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("chkdefaulttimeread").vw.setTop((int)((views.get("lblrdgtimetitle").vw.getTop() + views.get("lblrdgtimetitle").vw.getHeight())+(5d * scale)));
views.get("label1").vw.setLeft((int)((3d / 100 * width)));
views.get("label1").vw.setTop((int)((views.get("chkdefaulttimeread").vw.getTop() + views.get("chkdefaulttimeread").vw.getHeight())+(5d * scale)));
views.get("pnlrdgtimeanchor").vw.setTop((int)((views.get("chkdefaulttimeread").vw.getTop() + views.get("chkdefaulttimeread").vw.getHeight())+(5d * scale)));
views.get("pnlrdgtimeanchor").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight()/2) - (views.get("pnlrdgtimeanchor").vw.getHeight() / 2)));
views.get("pnlrdgtimeanchor").vw.setLeft((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale)));
views.get("pnlrdgtimeanchor").vw.setWidth((int)((views.get("pnlreadingtime").vw.getWidth())-(50d / 100 * width) - ((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale))));
views.get("msktimeread").vw.setTop((int)((0.5d / 100 * height)));
views.get("msktimeread").vw.setHeight((int)((views.get("pnlrdgtimeanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("msktimeread").vw.setLeft((int)((1d / 100 * width)));
views.get("msktimeread").vw.setWidth((int)((views.get("pnlrdgtimeanchor").vw.getWidth())-(19d * scale) - ((1d / 100 * width))));
views.get("pnlstyle5").vw.setTop((int)((views.get("chkdefaulttimeread").vw.getTop() + views.get("chkdefaulttimeread").vw.getHeight())+(5d * scale)));
views.get("pnlstyle5").vw.setLeft((int)((views.get("pnlrdgtimeanchor").vw.getLeft() + views.get("pnlrdgtimeanchor").vw.getWidth())-(18d * scale)));
views.get("pnlstyle5").vw.setWidth((int)((views.get("pnlreadingtime").vw.getWidth())-(2d / 100 * width) - ((views.get("pnlrdgtimeanchor").vw.getLeft() + views.get("pnlrdgtimeanchor").vw.getWidth())-(18d * scale))));
views.get("label16").vw.setLeft((int)((1d / 100 * width)));
views.get("label16").vw.setWidth((int)((views.get("pnlstyle5").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlcover2").vw.setLeft((int)((views.get("pnlstyle5").vw.getLeft())));
views.get("pnlcover2").vw.setTop((int)((views.get("chkdefaulttimeread").vw.getTop() + views.get("chkdefaulttimeread").vw.getHeight())+(5d * scale)));
views.get("pnlrdg").vw.setTop((int)((views.get("pnlreadingtime").vw.getTop() + views.get("pnlreadingtime").vw.getHeight())+(10d * scale)));
views.get("pnlrdg").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(45d / 100 * height) - ((views.get("pnlreadingtime").vw.getTop() + views.get("pnlreadingtime").vw.getHeight())+(10d * scale))));
views.get("pnlrdg").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlrdg").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblfmrdgtitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblfmrdgtitle").vw.setWidth((int)((views.get("pnlrdg").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblrdgtimetitle").vw.setTop((int)(0d));
views.get("pnlfmrdganchor").vw.setTop((int)((views.get("lblfmrdgtitle").vw.getTop() + views.get("lblfmrdgtitle").vw.getHeight())+(5d * scale)));
views.get("pnlfmrdganchor").vw.setHeight((int)((views.get("pnlrdg").vw.getHeight())-(1d / 100 * height) - ((views.get("lblfmrdgtitle").vw.getTop() + views.get("lblfmrdgtitle").vw.getHeight())+(5d * scale))));
views.get("pnlfmrdganchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlfmrdganchor").vw.setWidth((int)((views.get("pnlrdg").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtfmrdg").vw.setLeft((int)((1d / 100 * width)));
views.get("txtfmrdg").vw.setWidth((int)((views.get("pnlfmrdganchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtfmrdg").vw.setTop((int)((1d / 100 * height)));
views.get("txtfmrdg").vw.setHeight((int)((views.get("pnlfmrdganchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("pnlfmrdgremarks").vw.setTop((int)((views.get("pnlrdg").vw.getTop() + views.get("pnlrdg").vw.getHeight())+(10d * scale)));
views.get("pnlfmrdgremarks").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(20d / 100 * height) - ((views.get("pnlrdg").vw.getTop() + views.get("pnlrdg").vw.getHeight())+(10d * scale))));
views.get("pnlfmrdgremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlfmrdgremarks").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblfmrdgremarkstitle").vw.setTop((int)((0d * scale)));
views.get("lblfmrdgremarkstitle").vw.setLeft((int)((0d * scale)));
views.get("lblfmrdgremarkstitle").vw.setLeft((int)((0d * scale)));
views.get("lblfmrdgremarkstitle").vw.setWidth((int)((97d / 100 * width) - ((0d * scale))));
views.get("pnlremarksanchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlremarksanchor").vw.setWidth((int)((views.get("pnlfmrdgremarks").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlremarksanchor").vw.setTop((int)((views.get("lblfmrdgremarkstitle").vw.getTop() + views.get("lblfmrdgremarkstitle").vw.getHeight())+(5d * scale)));
views.get("pnlremarksanchor").vw.setHeight((int)((views.get("pnlfmrdgremarks").vw.getHeight())-(1.5d / 100 * height) - ((views.get("lblfmrdgremarkstitle").vw.getTop() + views.get("lblfmrdgremarkstitle").vw.getHeight())+(5d * scale))));
views.get("txtfmrdgremarks").vw.setLeft((int)((3d / 100 * width)));
views.get("txtfmrdgremarks").vw.setWidth((int)((views.get("pnlremarksanchor").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
views.get("txtfmrdgremarks").vw.setTop((int)((1d / 100 * height)));
views.get("txtfmrdgremarks").vw.setHeight((int)((views.get("pnlremarksanchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("btncancel").vw.setLeft((int)((2d / 100 * width)));
views.get("btncancel").vw.setWidth((int)((47d / 100 * width) - ((2d / 100 * width))));
views.get("btncancel").vw.setTop((int)((views.get("pnlfmrdgremarks").vw.getTop() + views.get("pnlfmrdgremarks").vw.getHeight())+(10d * scale)));
views.get("btncancel").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(10d / 100 * height) - ((views.get("pnlfmrdgremarks").vw.getTop() + views.get("pnlfmrdgremarks").vw.getHeight())+(10d * scale))));
views.get("btnsaveupdate").vw.setLeft((int)((53d / 100 * width)));
views.get("btnsaveupdate").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((53d / 100 * width))));
views.get("btnsaveupdate").vw.setTop((int)((views.get("pnlfmrdgremarks").vw.getTop() + views.get("pnlfmrdgremarks").vw.getHeight())+(10d * scale)));
views.get("btnsaveupdate").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(10d / 100 * height) - ((views.get("pnlfmrdgremarks").vw.getTop() + views.get("pnlfmrdgremarks").vw.getHeight())+(10d * scale))));
views.get("pnlzeroprodmsg").vw.setTop((int)((0d / 100 * height)));
views.get("pnlzeroprodmsg").vw.setHeight((int)((100d / 100 * height) - ((0d / 100 * height))));
views.get("pnlzeroprodmsg").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlzeroprodmsg").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlzeromsg").vw.setTop((int)((25d / 100 * height)));
views.get("pnlzeromsg").vw.setHeight((int)((70d / 100 * height) - ((25d / 100 * height))));
views.get("pnlzeromsg").vw.setLeft((int)((3d / 100 * width)));
views.get("pnlzeromsg").vw.setWidth((int)((97d / 100 * width) - ((3d / 100 * width))));
views.get("lblzeromsgtitle").vw.setTop((int)(0d));
views.get("lblzeroicon").vw.setLeft((int)((2d / 100 * width)));
views.get("lblzeromsgtitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblzeromsgtitle").vw.setWidth((int)((views.get("pnlzeromsg").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblzeroicon").vw.setTop((int)((views.get("lblzeromsgtitle").vw.getTop() + views.get("lblzeromsgtitle").vw.getHeight()/2) - (views.get("lblzeroicon").vw.getHeight() / 2)));
views.get("lblzerowarning").vw.setTop((int)((views.get("lblzeromsgtitle").vw.getTop() + views.get("lblzeromsgtitle").vw.getHeight())+(5d * scale)));
views.get("lblzerowarning").vw.setLeft((int)((2d / 100 * width)));
views.get("lblzerowarning").vw.setWidth((int)((views.get("pnlzeromsg").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblzeromsg").vw.setTop((int)((views.get("lblzerowarning").vw.getTop() + views.get("lblzerowarning").vw.getHeight())+(3d * scale)));
views.get("lblzeromsg").vw.setLeft((int)((3d / 100 * width)));
views.get("lblzeromsg").vw.setWidth((int)((views.get("pnlzeromsg").vw.getWidth())-(2d / 100 * width) - ((3d / 100 * width))));
views.get("pnlzeroremarksanchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlzeroremarksanchor").vw.setWidth((int)((views.get("pnlzeromsg").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlzeroremarksanchor").vw.setTop((int)((views.get("lblzeromsg").vw.getTop() + views.get("lblzeromsg").vw.getHeight())+(5d * scale)));
views.get("txtzeroremarks").vw.setLeft((int)((1d / 100 * width)));
views.get("txtzeroremarks").vw.setWidth((int)((views.get("pnlzeroremarksanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtzeroremarks").vw.setTop((int)((0.75d / 100 * height)));
views.get("txtzeroremarks").vw.setHeight((int)((views.get("pnlzeroremarksanchor").vw.getHeight())-(0.75d / 100 * height) - ((0.75d / 100 * height))));
views.get("btnzerocancel").vw.setTop((int)((views.get("pnlzeroremarksanchor").vw.getTop() + views.get("pnlzeroremarksanchor").vw.getHeight())+(10d * scale)));
views.get("btnzerocancel").vw.setHeight((int)((views.get("pnlzeromsg").vw.getHeight())-(2d / 100 * height) - ((views.get("pnlzeroremarksanchor").vw.getTop() + views.get("pnlzeroremarksanchor").vw.getHeight())+(10d * scale))));
views.get("btnzerook").vw.setTop((int)((views.get("pnlzeroremarksanchor").vw.getTop() + views.get("pnlzeroremarksanchor").vw.getHeight())+(10d * scale)));
views.get("btnzerook").vw.setHeight((int)((views.get("pnlzeromsg").vw.getHeight())-(2d / 100 * height) - ((views.get("pnlzeroremarksanchor").vw.getTop() + views.get("pnlzeroremarksanchor").vw.getHeight())+(10d * scale))));
views.get("btnzerocancel").vw.setLeft((int)((2d / 100 * width)));
views.get("btnzerook").vw.setLeft((int)((views.get("pnlzeromsg").vw.getWidth())-(2d / 100 * width) - (views.get("btnzerook").vw.getWidth())));
views.get("pnlnegativeprodmsg").vw.setTop((int)((0d / 100 * height)));
views.get("pnlnegativeprodmsg").vw.setHeight((int)((100d / 100 * height) - ((0d / 100 * height))));
views.get("pnlnegativeprodmsg").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlnegativeprodmsg").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlnegativemsg").vw.setTop((int)((17d / 100 * height)));
views.get("pnlnegativemsg").vw.setHeight((int)((77d / 100 * height) - ((17d / 100 * height))));
views.get("pnlnegativemsg").vw.setLeft((int)((3d / 100 * width)));
views.get("pnlnegativemsg").vw.setWidth((int)((97d / 100 * width) - ((3d / 100 * width))));
views.get("lblnegativemsgtitle").vw.setTop((int)(0d));
views.get("lblnegativeicon").vw.setLeft((int)((2d / 100 * width)));
views.get("lblnegativemsgtitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblnegativemsgtitle").vw.setWidth((int)((views.get("pnlnegativemsg").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblnegativeicon").vw.setTop((int)((views.get("lblnegativemsgtitle").vw.getTop() + views.get("lblnegativemsgtitle").vw.getHeight()/2) - (views.get("lblnegativeicon").vw.getHeight() / 2)));
views.get("lblnegativewarning").vw.setTop((int)((views.get("lblnegativemsgtitle").vw.getTop() + views.get("lblnegativemsgtitle").vw.getHeight())+(5d * scale)));
views.get("lblnegativewarning").vw.setLeft((int)((2d / 100 * width)));
views.get("lblnegativewarning").vw.setWidth((int)((views.get("pnlnegativemsg").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblnegativemsg").vw.setTop((int)((views.get("lblnegativewarning").vw.getTop() + views.get("lblnegativewarning").vw.getHeight())+(3d * scale)));
views.get("lblnegativemsg").vw.setLeft((int)((3d / 100 * width)));
views.get("lblnegativemsg").vw.setWidth((int)((views.get("pnlnegativemsg").vw.getWidth())-(2d / 100 * width) - ((3d / 100 * width))));
views.get("chkbackflow").vw.setLeft((int)((views.get("lblnegativemsg").vw.getLeft())));
views.get("chkbackflow").vw.setTop((int)((views.get("lblnegativemsg").vw.getTop() + views.get("lblnegativemsg").vw.getHeight())+(5d * scale)));
views.get("pnlbackflowanchor").vw.setLeft((int)((views.get("chkbackflow").vw.getLeft())));
views.get("pnlbackflowanchor").vw.setWidth((int)((views.get("pnlnegativemsg").vw.getWidth())-(40d / 100 * width) - ((views.get("chkbackflow").vw.getLeft()))));
views.get("pnlbackflowanchor").vw.setTop((int)((views.get("chkbackflow").vw.getTop() + views.get("chkbackflow").vw.getHeight())));
views.get("pnlbackflowanchor").vw.setHeight((int)((views.get("pnlnegativemsg").vw.getHeight())-(19d / 100 * height) - ((views.get("chkbackflow").vw.getTop() + views.get("chkbackflow").vw.getHeight()))));
views.get("txtbackflowcum").vw.setLeft((int)((1d / 100 * width)));
views.get("txtbackflowcum").vw.setWidth((int)((views.get("pnlbackflowanchor").vw.getWidth())-(3d / 100 * width) - ((1d / 100 * width))));
views.get("txtbackflowcum").vw.setTop((int)((0.75d / 100 * height)));
views.get("txtbackflowcum").vw.setHeight((int)((views.get("pnlbackflowanchor").vw.getHeight())-(0.75d / 100 * height) - ((0.75d / 100 * height))));
views.get("pnlnegativeremarksanchor").vw.setLeft((int)((views.get("pnlbackflowanchor").vw.getLeft())));
views.get("pnlnegativeremarksanchor").vw.setWidth((int)((views.get("pnlnegativemsg").vw.getWidth())-(2d / 100 * width) - ((views.get("pnlbackflowanchor").vw.getLeft()))));
views.get("pnlnegativeremarksanchor").vw.setTop((int)((views.get("pnlbackflowanchor").vw.getTop() + views.get("pnlbackflowanchor").vw.getHeight())+(5d * scale)));
views.get("pnlnegativeremarksanchor").vw.setHeight((int)((views.get("pnlnegativemsg").vw.getHeight())-(10d / 100 * height) - ((views.get("pnlbackflowanchor").vw.getTop() + views.get("pnlbackflowanchor").vw.getHeight())+(5d * scale))));
views.get("txtnegativeremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("txtnegativeremarks").vw.setWidth((int)((views.get("pnlnegativeremarksanchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtnegativeremarks").vw.setTop((int)((0.75d / 100 * height)));
views.get("txtnegativeremarks").vw.setHeight((int)((views.get("pnlnegativeremarksanchor").vw.getHeight())-(0.75d / 100 * height) - ((0.75d / 100 * height))));
views.get("btnnegativecancel").vw.setTop((int)((views.get("pnlnegativeremarksanchor").vw.getTop() + views.get("pnlnegativeremarksanchor").vw.getHeight())+(5d * scale)));
views.get("btnnegativecancel").vw.setHeight((int)((views.get("pnlnegativemsg").vw.getHeight())-(1d / 100 * height) - ((views.get("pnlnegativeremarksanchor").vw.getTop() + views.get("pnlnegativeremarksanchor").vw.getHeight())+(5d * scale))));
views.get("btnnegativecancel").vw.setLeft((int)((2d / 100 * width)));
views.get("btnnegativeok").vw.setTop((int)((views.get("pnlnegativeremarksanchor").vw.getTop() + views.get("pnlnegativeremarksanchor").vw.getHeight())+(5d * scale)));
views.get("btnnegativeok").vw.setHeight((int)((views.get("pnlnegativemsg").vw.getHeight())-(1d / 100 * height) - ((views.get("pnlnegativeremarksanchor").vw.getTop() + views.get("pnlnegativeremarksanchor").vw.getHeight())+(5d * scale))));
views.get("btnnegativeok").vw.setLeft((int)((views.get("pnlnegativemsg").vw.getWidth())-(2d / 100 * width) - (views.get("btnnegativeok").vw.getWidth())));
views.get("pnlhighprodmsg").vw.setTop((int)((0d / 100 * height)));
views.get("pnlhighprodmsg").vw.setHeight((int)((100d / 100 * height) - ((0d / 100 * height))));
views.get("pnlhighprodmsg").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlhighprodmsg").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlhighmsg").vw.setTop((int)((25d / 100 * height)));
views.get("pnlhighmsg").vw.setHeight((int)((70d / 100 * height) - ((25d / 100 * height))));
views.get("pnlhighmsg").vw.setLeft((int)((3d / 100 * width)));
views.get("pnlhighmsg").vw.setWidth((int)((97d / 100 * width) - ((3d / 100 * width))));
views.get("lblhighmsgtitle").vw.setTop((int)(0d));
views.get("lblhighicon").vw.setLeft((int)((2d / 100 * width)));
views.get("lblhighmsgtitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblhighmsgtitle").vw.setWidth((int)((views.get("pnlhighmsg").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblhighicon").vw.setTop((int)((views.get("lblhighmsgtitle").vw.getTop() + views.get("lblhighmsgtitle").vw.getHeight()/2) - (views.get("lblhighicon").vw.getHeight() / 2)));
views.get("lblhighwarning").vw.setTop((int)((views.get("lblhighmsgtitle").vw.getTop() + views.get("lblhighmsgtitle").vw.getHeight())+(5d * scale)));
views.get("lblhighwarning").vw.setLeft((int)((2d / 100 * width)));
views.get("lblhighwarning").vw.setWidth((int)((views.get("pnlhighmsg").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblhighmsg").vw.setTop((int)((views.get("lblhighwarning").vw.getTop() + views.get("lblhighwarning").vw.getHeight())+(3d * scale)));
views.get("lblhighmsg").vw.setLeft((int)((3d / 100 * width)));
views.get("lblhighmsg").vw.setWidth((int)((views.get("pnlhighmsg").vw.getWidth())-(2d / 100 * width) - ((3d / 100 * width))));
views.get("pnlhighremarksanchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlhighremarksanchor").vw.setWidth((int)((views.get("pnlzeromsg").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlhighremarksanchor").vw.setTop((int)((views.get("lblzeromsg").vw.getTop() + views.get("lblzeromsg").vw.getHeight())+(5d * scale)));
views.get("txthighremarks").vw.setLeft((int)((1d / 100 * width)));
views.get("txthighremarks").vw.setWidth((int)((views.get("pnlhighremarksanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txthighremarks").vw.setTop((int)((0.75d / 100 * height)));
views.get("txthighremarks").vw.setHeight((int)((views.get("pnlhighremarksanchor").vw.getHeight())-(0.75d / 100 * height) - ((0.75d / 100 * height))));
views.get("btnhighcancel").vw.setTop((int)((views.get("pnlhighremarksanchor").vw.getTop() + views.get("pnlhighremarksanchor").vw.getHeight())+(10d * scale)));
views.get("btnhighcancel").vw.setHeight((int)((views.get("pnlhighmsg").vw.getHeight())-(2d / 100 * height) - ((views.get("pnlhighremarksanchor").vw.getTop() + views.get("pnlhighremarksanchor").vw.getHeight())+(10d * scale))));
views.get("btnhighok").vw.setTop((int)((views.get("pnlhighremarksanchor").vw.getTop() + views.get("pnlhighremarksanchor").vw.getHeight())+(10d * scale)));
views.get("btnhighok").vw.setHeight((int)((views.get("pnlhighmsg").vw.getHeight())-(2d / 100 * height) - ((views.get("pnlhighremarksanchor").vw.getTop() + views.get("pnlhighremarksanchor").vw.getHeight())+(10d * scale))));
views.get("btnhighcancel").vw.setLeft((int)((2d / 100 * width)));
views.get("btnhighok").vw.setLeft((int)((views.get("pnlhighmsg").vw.getWidth())-(2d / 100 * width) - (views.get("btnhighok").vw.getWidth())));
views.get("pnllowprodmsg").vw.setTop((int)((0d / 100 * height)));
views.get("pnllowprodmsg").vw.setHeight((int)((100d / 100 * height) - ((0d / 100 * height))));
views.get("pnllowprodmsg").vw.setLeft((int)((0d / 100 * width)));
views.get("pnllowprodmsg").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnllowmsg").vw.setTop((int)((30d / 100 * height)));
views.get("pnllowmsg").vw.setHeight((int)((65d / 100 * height) - ((30d / 100 * height))));
views.get("pnllowmsg").vw.setLeft((int)((3d / 100 * width)));
views.get("pnllowmsg").vw.setWidth((int)((97d / 100 * width) - ((3d / 100 * width))));
views.get("lbllowmsgtitle").vw.setTop((int)(0d));
views.get("lbllowicon").vw.setLeft((int)((2d / 100 * width)));
views.get("lbllowmsgtitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lbllowmsgtitle").vw.setWidth((int)((views.get("pnllowmsg").vw.getWidth()) - ((0d / 100 * width))));
views.get("lbllowicon").vw.setTop((int)((views.get("lbllowmsgtitle").vw.getTop() + views.get("lbllowmsgtitle").vw.getHeight()/2) - (views.get("lbllowicon").vw.getHeight() / 2)));
views.get("lbllowwarning").vw.setTop((int)((views.get("lbllowmsgtitle").vw.getTop() + views.get("lbllowmsgtitle").vw.getHeight())+(5d * scale)));
views.get("lbllowwarning").vw.setLeft((int)((2d / 100 * width)));
views.get("lbllowwarning").vw.setWidth((int)((views.get("pnllowmsg").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lbllowmsg").vw.setTop((int)((views.get("lbllowwarning").vw.getTop() + views.get("lbllowwarning").vw.getHeight())+(3d * scale)));
views.get("lbllowmsg").vw.setLeft((int)((3d / 100 * width)));
views.get("lbllowmsg").vw.setWidth((int)((views.get("pnllowmsg").vw.getWidth())-(2d / 100 * width) - ((3d / 100 * width))));
views.get("btnlowcancel").vw.setTop((int)((views.get("lblzeromsg").vw.getTop() + views.get("lblzeromsg").vw.getHeight())+(10d * scale)));
views.get("btnlowcancel").vw.setHeight((int)((views.get("pnllowmsg").vw.getHeight())-(2d / 100 * height) - ((views.get("lblzeromsg").vw.getTop() + views.get("lblzeromsg").vw.getHeight())+(10d * scale))));
views.get("btnlowok").vw.setTop((int)((views.get("lblzeromsg").vw.getTop() + views.get("lblzeromsg").vw.getHeight())+(10d * scale)));
views.get("btnlowok").vw.setHeight((int)((views.get("pnllowmsg").vw.getHeight())-(2d / 100 * height) - ((views.get("lblzeromsg").vw.getTop() + views.get("lblzeromsg").vw.getHeight())+(10d * scale))));
views.get("btnlowcancel").vw.setLeft((int)((2d / 100 * width)));
views.get("btnlowok").vw.setLeft((int)((views.get("pnllowmsg").vw.getWidth())-(2d / 100 * width) - (views.get("btnlowok").vw.getWidth())));
views.get("pnlhighbillconfirmation").vw.setLeft((int)(0d));
views.get("pnlhighbillconfirmation").vw.setTop((int)(0d));
views.get("pnlhighbillconfirmation").vw.setWidth((int)((100d / 100 * width)));
views.get("pnlhighbillconfirmation").vw.setHeight((int)((100d / 100 * height)));
views.get("pnlhbconfirm").vw.setLeft((int)((3d / 100 * width)));
views.get("pnlhbconfirm").vw.setWidth((int)((views.get("pnlhighbillconfirmation").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
views.get("pnlhbconfirm").vw.setHeight((int)((40d / 100 * height)));
views.get("pnlhbconfirm").vw.setTop((int)((views.get("pnlhighbillconfirmation").vw.getHeight())/2d-(13d / 100 * height) - (views.get("pnlhbconfirm").vw.getHeight() / 2)));
views.get("lblhbconfirmicon").vw.setLeft((int)((1d / 100 * width)));
views.get("lblhbconfirmicon").vw.setTop((int)((0d / 100 * height)));
views.get("lblhbconfirmtitle").vw.setLeft((int)(0d));
views.get("lblhbconfirmtitle").vw.setWidth((int)((views.get("pnlhbconfirm").vw.getWidth()) - (0d)));
views.get("lblhbconfirmtitle").vw.setTop((int)((0d / 100 * height)));
views.get("lblhbconfirmmsgcontent").vw.setLeft((int)((5d / 100 * width)));
views.get("lblhbconfirmmsgcontent").vw.setWidth((int)((views.get("pnlhbconfirm").vw.getWidth())-(2d / 100 * width) - ((5d / 100 * width))));
views.get("lblhbconfirmmsgcontent").vw.setTop((int)((8d / 100 * height)));
views.get("pnlhbconfirmpresrdganchor").vw.setLeft((int)((3d / 100 * width)));
views.get("pnlhbconfirmpresrdganchor").vw.setWidth((int)((views.get("pnlhbconfirm").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
views.get("pnlhbconfirmpresrdganchor").vw.setTop((int)((views.get("lblhbconfirmmsgcontent").vw.getTop() + views.get("lblhbconfirmmsgcontent").vw.getHeight())+(10d * scale)));
views.get("pnlhbconfirmpresrdganchor").vw.setHeight((int)((views.get("pnlhbconfirm").vw.getHeight())-(13d / 100 * height) - ((views.get("lblhbconfirmmsgcontent").vw.getTop() + views.get("lblhbconfirmmsgcontent").vw.getHeight())+(10d * scale))));
views.get("txtpresrdgconfirm").vw.setLeft((int)((2d / 100 * width)));
views.get("txtpresrdgconfirm").vw.setWidth((int)((views.get("pnlhbconfirmpresrdganchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtpresrdgconfirm").vw.setTop((int)((0.25d / 100 * height)));
views.get("txtpresrdgconfirm").vw.setHeight((int)((views.get("pnlhbconfirmpresrdganchor").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("btnhbconfirmcancel").vw.setTop((int)((32.5d / 100 * height)));
views.get("btnhbconfirmcancel").vw.setHeight((int)((views.get("pnlhbconfirm").vw.getHeight())-(10d * scale) - ((32.5d / 100 * height))));
views.get("btnhbconfirmcancel").vw.setLeft((int)((3d / 100 * width)));
views.get("btnhbconfirmcancel").vw.setWidth((int)((views.get("pnlhbconfirm").vw.getWidth())-(71d / 100 * width) - ((3d / 100 * width))));
views.get("btnhbconfirmsave").vw.setTop((int)((32.5d / 100 * height)));
views.get("btnhbconfirmsave").vw.setHeight((int)((views.get("pnlhbconfirm").vw.getHeight())-(10d * scale) - ((32.5d / 100 * height))));
views.get("btnhbconfirmsave").vw.setLeft((int)((54d / 100 * width)));
views.get("btnhbconfirmsave").vw.setWidth((int)((views.get("pnlhbconfirm").vw.getWidth())-(3d / 100 * width) - ((54d / 100 * width))));

}
}