package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_psirdgdistribution{

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
views.get("pnlmain").vw.setWidth((int)((100d / 100 * width)));
views.get("pnlmain").vw.setLeft((int)(0d));
views.get("pnlmain").vw.setHeight((int)((100d / 100 * height)-(views.get("toolbar").vw.getHeight())));
views.get("pnlmain").vw.setTop((int)((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight())));
views.get("label1").vw.setLeft((int)(0d));
views.get("label1").vw.setWidth((int)((80d / 100 * width) - (0d)));
views.get("label2").vw.setLeft((int)((80d / 100 * width)));
views.get("label2").vw.setWidth((int)((100d / 100 * width) - ((80d / 100 * width))));
views.get("clvlist").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())));
views.get("clvlist").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(views.get("label1").vw.getHeight())));
views.get("clvlist").vw.setLeft((int)(0d));
views.get("clvlist").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())));
views.get("btnaddpsidist").vw.setLeft((int)((views.get("pnlmain").vw.getLeft() + views.get("pnlmain").vw.getWidth())-(views.get("btnaddpsidist").vw.getWidth())-(20d * scale)));
views.get("btnaddpsidist").vw.setTop((int)((views.get("clvlist").vw.getTop() + views.get("clvlist").vw.getHeight())-(views.get("btnaddpsidist").vw.getHeight())-(50d * scale)));

}
}