package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_newpumptimerecords2{

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
views.get("pnltime").vw.setTop((int)((1d / 100 * height)));
views.get("pnltime").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(71d / 100 * height) - ((1d / 100 * height))));
views.get("pnltime").vw.setLeft((int)((2d / 100 * width)));
views.get("pnltime").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblontitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblontitle").vw.setWidth((int)((views.get("pnltime").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblontitle").vw.setTop((int)(0d));
views.get("chkdefaulttimeon").vw.setLeft((int)((2d / 100 * width)));
views.get("chkdefaulttimeon").vw.setWidth((int)((views.get("pnltime").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("chkdefaulttimeon").vw.setTop((int)((views.get("lblontitle").vw.getTop() + views.get("lblontitle").vw.getHeight())+(5d * scale)));
views.get("label1").vw.setLeft((int)((2d / 100 * width)));
views.get("label1").vw.setTop((int)((views.get("chkdefaulttimeon").vw.getTop() + views.get("chkdefaulttimeon").vw.getHeight())+(5d * scale)));
views.get("pnltimeonanchor").vw.setTop((int)((views.get("chkdefaulttimeon").vw.getTop() + views.get("chkdefaulttimeon").vw.getHeight())+(5d * scale)));
views.get("pnltimeonanchor").vw.setLeft((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale)));
views.get("pnltimeonanchor").vw.setWidth((int)((views.get("pnltime").vw.getWidth())-(50d / 100 * width) - ((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale))));
views.get("msktimeon").vw.setTop((int)((0.25d / 100 * height)));
views.get("msktimeon").vw.setHeight((int)((views.get("pnltimeonanchor").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("msktimeon").vw.setLeft((int)((1d / 100 * width)));
views.get("msktimeon").vw.setWidth((int)((views.get("pnltimeonanchor").vw.getWidth())-(19d * scale) - ((1d / 100 * width))));
views.get("pnlstyle1").vw.setTop((int)((views.get("chkdefaulttimeon").vw.getTop() + views.get("chkdefaulttimeon").vw.getHeight())+(5d * scale)));
views.get("pnlstyle1").vw.setLeft((int)((views.get("pnltimeonanchor").vw.getLeft() + views.get("pnltimeonanchor").vw.getWidth())-(18d * scale)));
views.get("pnlstyle1").vw.setWidth((int)((views.get("pnltime").vw.getWidth())-(2d / 100 * width) - ((views.get("pnltimeonanchor").vw.getLeft() + views.get("pnltimeonanchor").vw.getWidth())-(18d * scale))));
views.get("label3").vw.setLeft((int)((1d / 100 * width)));
views.get("label3").vw.setWidth((int)((views.get("pnlstyle1").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlcover1").vw.setLeft((int)((views.get("pnlstyle1").vw.getLeft())));
views.get("pnlcover1").vw.setTop((int)((views.get("chkdefaulttimeon").vw.getTop() + views.get("chkdefaulttimeon").vw.getHeight())+(5d * scale)));
views.get("pnlsource").vw.setTop((int)((views.get("pnltime").vw.getTop() + views.get("pnltime").vw.getHeight())+(10d * scale)));
views.get("pnlsource").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(58d / 100 * height) - ((views.get("pnltime").vw.getTop() + views.get("pnltime").vw.getHeight())+(10d * scale))));
views.get("pnlsource").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlsource").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblsourcetitle").vw.setTop((int)((0d * scale)));
views.get("lblsourcetitle").vw.setLeft((int)((0d * scale)));
views.get("lblsourcetitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblsourcetitle").vw.setWidth((int)((views.get("pnlmain").vw.getWidth()) - ((0d / 100 * width))));
views.get("optelectricity").vw.setLeft((int)((5d / 100 * width)));
views.get("optelectricity").vw.setTop((int)((views.get("lblsourcetitle").vw.getTop() + views.get("lblsourcetitle").vw.getHeight())+(5d * scale)));
views.get("optelectricity").vw.setHeight((int)((views.get("pnlsource").vw.getHeight())-(5d * scale) - ((views.get("lblsourcetitle").vw.getTop() + views.get("lblsourcetitle").vw.getHeight())+(5d * scale))));
views.get("optgenerator").vw.setLeft((int)((views.get("optelectricity").vw.getLeft() + views.get("optelectricity").vw.getWidth())+(10d * scale)));
views.get("optgenerator").vw.setTop((int)((views.get("lblsourcetitle").vw.getTop() + views.get("lblsourcetitle").vw.getHeight())+(5d * scale)));
views.get("optgenerator").vw.setHeight((int)((views.get("pnlsource").vw.getHeight())-(5d * scale) - ((views.get("lblsourcetitle").vw.getTop() + views.get("lblsourcetitle").vw.getHeight())+(5d * scale))));
views.get("pnldrain").vw.setTop((int)((views.get("pnlsource").vw.getTop() + views.get("pnlsource").vw.getHeight())+(10d * scale)));
views.get("pnldrain").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(35d / 100 * height) - ((views.get("pnlsource").vw.getTop() + views.get("pnlsource").vw.getHeight())+(10d * scale))));
views.get("pnldrain").vw.setLeft((int)((2d / 100 * width)));
views.get("pnldrain").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lbldraintitle").vw.setTop((int)((0d * scale)));
views.get("lbldraintitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lbldraintitle").vw.setWidth((int)((views.get("pnldrain").vw.getWidth()) - ((0d / 100 * width))));
views.get("chkdrain").vw.setTop((int)((views.get("lbldraintitle").vw.getTop() + views.get("lbldraintitle").vw.getHeight())+(8d * scale)));
views.get("chkdrain").vw.setLeft((int)((2d / 100 * width)));
views.get("chkdrain").vw.setWidth((int)((views.get("pnldrain").vw.getWidth())-(55d / 100 * width) - ((2d / 100 * width))));
views.get("lbldrain1").vw.setLeft((int)((views.get("chkdrain").vw.getLeft() + views.get("chkdrain").vw.getWidth())+(5d * scale)));
views.get("lbldrain1").vw.setTop((int)((views.get("chkdrain").vw.getTop() + views.get("chkdrain").vw.getHeight()/2) - (views.get("lbldrain1").vw.getHeight() / 2)));
views.get("pnldurationanchor").vw.setTop((int)((views.get("lbldrain1").vw.getTop() + views.get("lbldrain1").vw.getHeight()/2) - (views.get("pnldurationanchor").vw.getHeight() / 2)));
views.get("pnldurationanchor").vw.setLeft((int)((views.get("lbldrain1").vw.getLeft() + views.get("lbldrain1").vw.getWidth())+(3d * scale)));
views.get("pnldurationanchor").vw.setWidth((int)((views.get("pnldrain").vw.getWidth())-(10d / 100 * width) - ((views.get("lbldrain1").vw.getLeft() + views.get("lbldrain1").vw.getWidth())+(3d * scale))));
views.get("txtduration").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtduration").vw.setHeight((int)((views.get("pnldurationanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("txtduration").vw.setLeft((int)((1d / 100 * width)));
views.get("txtduration").vw.setWidth((int)((views.get("pnldurationanchor").vw.getWidth())-(6d / 100 * width) - ((1d / 100 * width))));
views.get("pnlstyle3").vw.setTop((int)((views.get("pnldurationanchor").vw.getTop())));
views.get("pnlstyle3").vw.setLeft((int)((views.get("pnldurationanchor").vw.getLeft() + views.get("pnldurationanchor").vw.getWidth())-(24d * scale)));
views.get("pnlstyle3").vw.setWidth((int)((views.get("pnldrain").vw.getWidth())-(2d / 100 * width) - ((views.get("pnldurationanchor").vw.getLeft() + views.get("pnldurationanchor").vw.getWidth())-(24d * scale))));
views.get("lbldrain2").vw.setLeft((int)((1d / 100 * width)));
views.get("lbldrain2").vw.setWidth((int)((views.get("pnlstyle3").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("lbldrain2").vw.setTop((int)(0d));
views.get("lbldrain2").vw.setHeight((int)((views.get("pnlstyle3").vw.getHeight()) - (0d)));
views.get("pnlcover3").vw.setLeft((int)((views.get("pnlstyle3").vw.getLeft())-(2d * scale)));
views.get("pnlcover3").vw.setTop((int)((views.get("pnlstyle3").vw.getTop())));
views.get("lbldrain3").vw.setTop((int)((views.get("lbldrain1").vw.getTop() + views.get("lbldrain1").vw.getHeight())+(5d * scale)));
views.get("lbldrain3").vw.setLeft((int)((views.get("chkdrain").vw.getLeft())));
views.get("pnlpsianchor").vw.setLeft((int)((views.get("lbldrain3").vw.getLeft() + views.get("lbldrain3").vw.getWidth())+(5d * scale)));
views.get("pnlpsianchor").vw.setWidth((int)((views.get("pnldrain").vw.getWidth())-(58d / 100 * width) - ((views.get("lbldrain3").vw.getLeft() + views.get("lbldrain3").vw.getWidth())+(5d * scale))));
views.get("pnlpsianchor").vw.setTop((int)((views.get("lbldrain3").vw.getTop() + views.get("lbldrain3").vw.getHeight()/2) - (views.get("pnlpsianchor").vw.getHeight() / 2)));
views.get("pnlstyle5").vw.setTop((int)((views.get("pnlpsianchor").vw.getTop())));
views.get("pnlstyle5").vw.setLeft((int)((views.get("pnlpsianchor").vw.getLeft() + views.get("pnlpsianchor").vw.getWidth())-(18d * scale)));
views.get("pnlstyle5").vw.setWidth((int)((views.get("pnldrain").vw.getWidth())-(50d / 100 * width) - ((views.get("pnlpsianchor").vw.getLeft() + views.get("pnlpsianchor").vw.getWidth())-(18d * scale))));
views.get("pnlcover5").vw.setLeft((int)((views.get("pnlstyle5").vw.getLeft())-(2d * scale)));
views.get("pnlcover5").vw.setTop((int)((views.get("pnlstyle5").vw.getTop())));
views.get("lbldrain5").vw.setLeft((int)((views.get("pnlstyle5").vw.getLeft() + views.get("pnlstyle5").vw.getWidth())+(10d * scale)));
views.get("lbldrain5").vw.setTop((int)((views.get("lbldrain1").vw.getTop() + views.get("lbldrain1").vw.getHeight())+(5d * scale)));
views.get("pnlvolanchor").vw.setTop((int)((views.get("lbldrain5").vw.getTop() + views.get("lbldrain5").vw.getHeight()/2) - (views.get("pnlvolanchor").vw.getHeight() / 2)));
views.get("pnlvolanchor").vw.setLeft((int)((views.get("lbldrain5").vw.getLeft() + views.get("lbldrain5").vw.getWidth())+(5d * scale)));
views.get("pnlvolanchor").vw.setWidth((int)((views.get("pnldrain").vw.getWidth())-(2d / 100 * width) - ((views.get("lbldrain5").vw.getLeft() + views.get("lbldrain5").vw.getWidth())+(5d * scale))));
views.get("txtdraincum").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtdraincum").vw.setHeight((int)((views.get("pnlvolanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("txtdraincum").vw.setLeft((int)((1d / 100 * width)));
views.get("txtdraincum").vw.setWidth((int)((views.get("pnlvolanchor").vw.getWidth())-(16d / 100 * width) - ((1d / 100 * width))));
views.get("lbldrain6").vw.setLeft((int)((views.get("txtdraincum").vw.getWidth())+(5d * scale)));
views.get("lbldrain6").vw.setWidth((int)((views.get("pnlvolanchor").vw.getWidth())-(1d / 100 * width) - ((views.get("txtdraincum").vw.getWidth())+(5d * scale))));
views.get("pnlremarks").vw.setTop((int)((views.get("pnldrain").vw.getTop() + views.get("pnldrain").vw.getHeight())+(10d * scale)));
views.get("pnlremarks").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(12d / 100 * height) - ((views.get("pnldrain").vw.getTop() + views.get("pnldrain").vw.getHeight())+(10d * scale))));
views.get("pnlremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlremarks").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblremarksontitle").vw.setTop((int)((0d * scale)));
views.get("lblremarksontitle").vw.setLeft((int)((0d * scale)));
views.get("lblremarksontitle").vw.setLeft((int)((0d * scale)));
views.get("lblremarksontitle").vw.setWidth((int)((97d / 100 * width) - ((0d * scale))));
views.get("pnlremarksanchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlremarksanchor").vw.setWidth((int)((views.get("pnlremarks").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlremarksanchor").vw.setTop((int)((views.get("lblremarksontitle").vw.getTop() + views.get("lblremarksontitle").vw.getHeight())+(5d * scale)));
views.get("pnlremarksanchor").vw.setHeight((int)((views.get("pnlremarks").vw.getHeight())-(1d / 100 * height) - ((views.get("lblremarksontitle").vw.getTop() + views.get("lblremarksontitle").vw.getHeight())+(5d * scale))));
views.get("txtonremarks").vw.setLeft((int)((3d / 100 * width)));
views.get("txtonremarks").vw.setWidth((int)((views.get("pnlremarksanchor").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
views.get("txtonremarks").vw.setTop((int)((1d / 100 * height)));
views.get("txtonremarks").vw.setHeight((int)((views.get("pnlremarksanchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("btnsave").vw.setLeft((int)((2d / 100 * width)));
views.get("btnsave").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("btnsave").vw.setTop((int)((82d / 100 * height)));
views.get("btnsave").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(1d / 100 * height) - ((82d / 100 * height))));

}
}