package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_nonoperational{

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
views.get("pnltime").vw.setWidth((int)((100d / 100 * width)));
views.get("pnltime").vw.setLeft((int)(0d));
views.get("pnltime").vw.setTop((int)((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight())));
views.get("pnltime").vw.setHeight((int)((100d / 100 * height) - ((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight()))));
views.get("label1").vw.setLeft((int)(0d));
views.get("label1").vw.setWidth((int)((32.5d / 100 * width) - (0d)));
views.get("label2").vw.setLeft((int)((32.5d / 100 * width)));
views.get("label2").vw.setWidth((int)((65d / 100 * width) - ((32.5d / 100 * width))));
views.get("label3").vw.setLeft((int)((65d / 100 * width)));
views.get("label3").vw.setWidth((int)((100d / 100 * width) - ((65d / 100 * width))));
views.get("pnlstatus").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlstatus").vw.setWidth((int)((views.get("pnltime").vw.getWidth()) - ((0d / 100 * width))));
views.get("pnlstatus").vw.setTop((int)((87d / 100 * height)));
views.get("pnlstatus").vw.setHeight((int)((views.get("pnltime").vw.getHeight()) - ((87d / 100 * height))));
views.get("lblstatus").vw.setLeft((int)((2d / 100 * width)));
views.get("lblstatus").vw.setWidth((int)((views.get("pnlstatus").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblstatus").vw.setTop((int)((1d / 100 * height)));
views.get("lblstatus").vw.setHeight((int)((views.get("pnlstatus").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("clvtime").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())));
views.get("clvtime").vw.setHeight((int)((views.get("pnlstatus").vw.getTop())+(2d * scale) - ((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight()))));
views.get("clvtime").vw.setLeft((int)((0d / 100 * width)));
views.get("clvtime").vw.setWidth((int)((views.get("pnltime").vw.getWidth()) - ((0d / 100 * width))));
views.get("btnaddtime").vw.setLeft((int)((views.get("pnltime").vw.getLeft() + views.get("pnltime").vw.getWidth())-(views.get("btnaddtime").vw.getWidth())-(30d * scale)));
views.get("btnaddtime").vw.setTop((int)((views.get("clvtime").vw.getTop() + views.get("clvtime").vw.getHeight())-(views.get("btnaddtime").vw.getHeight())-(40d * scale)));

}
}