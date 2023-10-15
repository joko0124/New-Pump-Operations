package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_pumpofftime{

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
views.get("pnlpumpoff").vw.setTop((int)((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight())));
views.get("pnlpumpoff").vw.setHeight((int)((100d / 100 * height) - ((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight()))));
views.get("pnlpumpoff").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlpumpoff").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlpumpoffbackground").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlpumpoffbackground").vw.setWidth((int)((views.get("pnlpumpoff").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlpumpoffbackground").vw.setTop((int)((15d / 100 * height)));
views.get("pnlpumpoffbackground").vw.setHeight((int)((views.get("pnlpumpoff").vw.getHeight())-(23d / 100 * height) - ((15d / 100 * height))));
views.get("pnltimeoff").vw.setTop((int)((1d / 100 * height)));
views.get("pnltimeoff").vw.setHeight((int)((views.get("pnlpumpoffbackground").vw.getHeight())-(30d / 100 * height) - ((1d / 100 * height))));
views.get("pnltimeoff").vw.setLeft((int)((2d / 100 * width)));
views.get("pnltimeoff").vw.setWidth((int)((views.get("pnlpumpoffbackground").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblofftitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblofftitle").vw.setWidth((int)((views.get("pnlpumpoffbackground").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblofftitle").vw.setTop((int)(0d));
views.get("chkdefaulttimeoff").vw.setLeft((int)((2d / 100 * width)));
views.get("chkdefaulttimeoff").vw.setWidth((int)((views.get("pnltimeoff").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("chkdefaulttimeoff").vw.setTop((int)((views.get("lblofftitle").vw.getTop() + views.get("lblofftitle").vw.getHeight())+(5d * scale)));
views.get("label1").vw.setLeft((int)((3d / 100 * width)));
views.get("label1").vw.setTop((int)((views.get("chkdefaulttimeoff").vw.getTop() + views.get("chkdefaulttimeoff").vw.getHeight())+(5d * scale)));
views.get("pnltimeoffanchor").vw.setTop((int)((views.get("chkdefaulttimeoff").vw.getTop() + views.get("chkdefaulttimeoff").vw.getHeight())+(5d * scale)));
views.get("pnltimeoffanchor").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight()/2) - (views.get("pnltimeoffanchor").vw.getHeight() / 2)));
views.get("pnltimeoffanchor").vw.setLeft((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale)));
views.get("pnltimeoffanchor").vw.setWidth((int)((views.get("pnltimeoff").vw.getWidth())-(50d / 100 * width) - ((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale))));
views.get("msktimeoff").vw.setTop((int)((0.5d / 100 * height)));
views.get("msktimeoff").vw.setHeight((int)((views.get("pnltimeoffanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("msktimeoff").vw.setLeft((int)((1d / 100 * width)));
views.get("msktimeoff").vw.setWidth((int)((views.get("pnltimeoffanchor").vw.getWidth())-(19d * scale) - ((1d / 100 * width))));
views.get("pnlstyle5").vw.setTop((int)((views.get("chkdefaulttimeoff").vw.getTop() + views.get("chkdefaulttimeoff").vw.getHeight())+(5d * scale)));
views.get("pnlstyle5").vw.setLeft((int)((views.get("pnltimeoffanchor").vw.getLeft() + views.get("pnltimeoffanchor").vw.getWidth())-(18d * scale)));
views.get("pnlstyle5").vw.setWidth((int)((views.get("pnltimeoff").vw.getWidth())-(2d / 100 * width) - ((views.get("pnltimeoffanchor").vw.getLeft() + views.get("pnltimeoffanchor").vw.getWidth())-(18d * scale))));
views.get("label16").vw.setLeft((int)((1d / 100 * width)));
views.get("label16").vw.setWidth((int)((views.get("pnlstyle5").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlcover2").vw.setLeft((int)((views.get("pnlstyle5").vw.getLeft())));
views.get("pnlcover2").vw.setTop((int)((views.get("chkdefaulttimeoff").vw.getTop() + views.get("chkdefaulttimeoff").vw.getHeight())+(5d * scale)));
views.get("pnloffremarks").vw.setTop((int)((views.get("pnltimeoff").vw.getTop() + views.get("pnltimeoff").vw.getHeight())+(10d * scale)));
views.get("pnloffremarks").vw.setHeight((int)((views.get("pnlpumpoffbackground").vw.getHeight())-(10d / 100 * height) - ((views.get("pnltimeoff").vw.getTop() + views.get("pnltimeoff").vw.getHeight())+(10d * scale))));
views.get("pnloffremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("pnloffremarks").vw.setWidth((int)((views.get("pnlpumpoffbackground").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblremarksofftitle").vw.setTop((int)((0d * scale)));
views.get("lblremarksofftitle").vw.setLeft((int)((0d * scale)));
views.get("lblremarksofftitle").vw.setLeft((int)((0d * scale)));
views.get("lblremarksofftitle").vw.setWidth((int)((97d / 100 * width) - ((0d * scale))));
views.get("pnlremarksoffanchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlremarksoffanchor").vw.setWidth((int)((views.get("pnloffremarks").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlremarksoffanchor").vw.setTop((int)((views.get("lblremarksofftitle").vw.getTop() + views.get("lblremarksofftitle").vw.getHeight())+(5d * scale)));
views.get("pnlremarksoffanchor").vw.setHeight((int)((views.get("pnloffremarks").vw.getHeight())-(1.5d / 100 * height) - ((views.get("lblremarksofftitle").vw.getTop() + views.get("lblremarksofftitle").vw.getHeight())+(5d * scale))));
views.get("txtoffremarks").vw.setLeft((int)((3d / 100 * width)));
views.get("txtoffremarks").vw.setWidth((int)((views.get("pnlremarksoffanchor").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
views.get("txtoffremarks").vw.setTop((int)((1d / 100 * height)));
views.get("txtoffremarks").vw.setHeight((int)((views.get("pnlremarksoffanchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("btnpumpoffcancel").vw.setLeft((int)((3d / 100 * width)));
views.get("btnpumpoffcancel").vw.setWidth((int)((46d / 100 * width) - ((3d / 100 * width))));
views.get("btnpumpoffcancel").vw.setTop((int)((views.get("pnloffremarks").vw.getTop() + views.get("pnloffremarks").vw.getHeight())+(10d * scale)));
views.get("btnpumpoffcancel").vw.setHeight((int)((views.get("pnlpumpoffbackground").vw.getHeight())-(1d / 100 * height) - ((views.get("pnloffremarks").vw.getTop() + views.get("pnloffremarks").vw.getHeight())+(10d * scale))));
views.get("btnpumpoffsave").vw.setLeft((int)((52d / 100 * width)));
views.get("btnpumpoffsave").vw.setWidth((int)((views.get("pnlpumpoffbackground").vw.getWidth())-(3d / 100 * width) - ((52d / 100 * width))));
views.get("btnpumpoffsave").vw.setTop((int)((views.get("pnloffremarks").vw.getTop() + views.get("pnloffremarks").vw.getHeight())+(10d * scale)));
views.get("btnpumpoffsave").vw.setHeight((int)((views.get("pnlpumpoffbackground").vw.getHeight())-(1d / 100 * height) - ((views.get("pnloffremarks").vw.getTop() + views.get("pnloffremarks").vw.getHeight())+(10d * scale))));

}
}