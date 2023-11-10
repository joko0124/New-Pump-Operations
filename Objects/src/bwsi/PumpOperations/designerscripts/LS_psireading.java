package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_psireading{

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
views.get("pnlreadingtime").vw.setTop((int)((5d / 100 * height)));
views.get("pnlreadingtime").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(67d / 100 * height) - ((5d / 100 * height))));
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
views.get("pnlrdg").vw.setTop((int)((views.get("pnlreadingtime").vw.getTop() + views.get("pnlreadingtime").vw.getHeight())+(15d * scale)));
views.get("pnlrdg").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(44d / 100 * height) - ((views.get("pnlreadingtime").vw.getTop() + views.get("pnlreadingtime").vw.getHeight())+(15d * scale))));
views.get("pnlrdg").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlrdg").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblpsirdgtitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblpsirdgtitle").vw.setWidth((int)((views.get("pnlrdg").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblrdgtimetitle").vw.setTop((int)(0d));
views.get("pnlpsirdganchor").vw.setTop((int)((views.get("lblpsirdgtitle").vw.getTop() + views.get("lblpsirdgtitle").vw.getHeight())+(5d * scale)));
views.get("pnlpsirdganchor").vw.setHeight((int)((views.get("pnlrdg").vw.getHeight())-(1d / 100 * height) - ((views.get("lblpsirdgtitle").vw.getTop() + views.get("lblpsirdgtitle").vw.getHeight())+(5d * scale))));
views.get("pnlpsirdganchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlpsirdganchor").vw.setWidth((int)((views.get("pnlrdg").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtpsirdg").vw.setLeft((int)((1d / 100 * width)));
views.get("txtpsirdg").vw.setWidth((int)((views.get("pnlpsirdganchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtpsirdg").vw.setTop((int)((1d / 100 * height)));
views.get("txtpsirdg").vw.setHeight((int)((views.get("pnlpsirdganchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("pnlpsirdgremarks").vw.setTop((int)((views.get("pnlrdg").vw.getTop() + views.get("pnlrdg").vw.getHeight())+(10d * scale)));
views.get("pnlpsirdgremarks").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(23d / 100 * height) - ((views.get("pnlrdg").vw.getTop() + views.get("pnlrdg").vw.getHeight())+(10d * scale))));
views.get("pnlpsirdgremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlpsirdgremarks").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblpsirdgremarkstitle").vw.setTop((int)((0d * scale)));
views.get("lblpsirdgremarkstitle").vw.setLeft((int)((0d * scale)));
views.get("lblpsirdgremarkstitle").vw.setLeft((int)((0d * scale)));
views.get("lblpsirdgremarkstitle").vw.setWidth((int)((97d / 100 * width) - ((0d * scale))));
views.get("pnlremarksanchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlremarksanchor").vw.setWidth((int)((views.get("pnlpsirdgremarks").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlremarksanchor").vw.setTop((int)((views.get("lblpsirdgremarkstitle").vw.getTop() + views.get("lblpsirdgremarkstitle").vw.getHeight())+(5d * scale)));
views.get("pnlremarksanchor").vw.setHeight((int)((views.get("pnlpsirdgremarks").vw.getHeight())-(1.5d / 100 * height) - ((views.get("lblpsirdgremarkstitle").vw.getTop() + views.get("lblpsirdgremarkstitle").vw.getHeight())+(5d * scale))));
views.get("txtpsirdgremarks").vw.setLeft((int)((3d / 100 * width)));
views.get("txtpsirdgremarks").vw.setWidth((int)((views.get("pnlremarksanchor").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
views.get("txtpsirdgremarks").vw.setTop((int)((1d / 100 * height)));
views.get("txtpsirdgremarks").vw.setHeight((int)((views.get("pnlremarksanchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("btncancel").vw.setLeft((int)((2d / 100 * width)));
views.get("btncancel").vw.setWidth((int)((47d / 100 * width) - ((2d / 100 * width))));
views.get("btncancel").vw.setTop((int)((views.get("pnlpsirdgremarks").vw.getTop() + views.get("pnlpsirdgremarks").vw.getHeight())+(10d * scale)));
views.get("btncancel").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(14d / 100 * height) - ((views.get("pnlpsirdgremarks").vw.getTop() + views.get("pnlpsirdgremarks").vw.getHeight())+(10d * scale))));
views.get("btnsaveupdate").vw.setLeft((int)((53d / 100 * width)));
views.get("btnsaveupdate").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((53d / 100 * width))));
views.get("btnsaveupdate").vw.setTop((int)((views.get("pnlpsirdgremarks").vw.getTop() + views.get("pnlpsirdgremarks").vw.getHeight())+(10d * scale)));
views.get("btnsaveupdate").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(14d / 100 * height) - ((views.get("pnlpsirdgremarks").vw.getTop() + views.get("pnlpsirdgremarks").vw.getHeight())+(10d * scale))));

}
}