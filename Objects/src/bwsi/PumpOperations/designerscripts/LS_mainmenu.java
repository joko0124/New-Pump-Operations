package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_mainmenu{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("pnlmenuheader").vw.setTop((int)((0d / 100 * height)));
views.get("pnlmenuheader").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlmenuheader").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
if ((anywheresoftware.b4a.keywords.LayoutBuilder.getScreenSize()>6.5d)) { 
;
views.get("pnlmenuheader").vw.setHeight((int)((64d * scale)));
;}else{ 
;
if ((BA.ObjectToBoolean( String.valueOf(anywheresoftware.b4a.keywords.LayoutBuilder.isPortrait())))) { 
;
views.get("pnlmenuheader").vw.setHeight((int)((50d * scale)));
;}else{ 
;
views.get("pnlmenuheader").vw.setHeight((int)((48d * scale)));
;};
;};
views.get("pnluserstyle").vw.setLeft((int)((2d / 100 * width)));
views.get("pnluserstyle").vw.setTop((int)((1d / 100 * height)));
views.get("lblmenuavatar").vw.setLeft((int)((views.get("pnluserstyle").vw.getWidth())/2d - (views.get("lblmenuavatar").vw.getWidth() / 2)));
views.get("lblmenuavatar").vw.setTop((int)((views.get("pnluserstyle").vw.getHeight())/2d - (views.get("lblmenuavatar").vw.getHeight() / 2)));
views.get("lbluserfullname").vw.setLeft((int)((views.get("pnluserstyle").vw.getLeft() + views.get("pnluserstyle").vw.getWidth())+(8d * scale)));
views.get("lbluserfullname").vw.setWidth((int)((views.get("pnlmenuheader").vw.getWidth())-(1d / 100 * width) - ((views.get("pnluserstyle").vw.getLeft() + views.get("pnluserstyle").vw.getWidth())+(8d * scale))));
views.get("lbluserbranch").vw.setLeft((int)((views.get("pnluserstyle").vw.getLeft() + views.get("pnluserstyle").vw.getWidth())+(8d * scale)));
views.get("lbluserbranch").vw.setWidth((int)((views.get("pnlmenuheader").vw.getWidth())-(1d / 100 * width) - ((views.get("pnluserstyle").vw.getLeft() + views.get("pnluserstyle").vw.getWidth())+(8d * scale))));
views.get("lbluserfullname").vw.setTop((int)((1.75d / 100 * height)));
views.get("lbluserbranch").vw.setTop((int)((views.get("lbluserfullname").vw.getTop() + views.get("lbluserfullname").vw.getHeight())));
views.get("lvmenus").vw.setTop((int)((views.get("pnlmenuheader").vw.getTop() + views.get("pnlmenuheader").vw.getHeight())+(10d * scale)));
views.get("lvmenus").vw.setHeight((int)((100d / 100 * height) - ((views.get("pnlmenuheader").vw.getTop() + views.get("pnlmenuheader").vw.getHeight())+(10d * scale))));

}
}