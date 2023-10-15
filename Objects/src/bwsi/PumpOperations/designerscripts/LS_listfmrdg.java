package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_listfmrdg{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("lblrdgtime").vw.setLeft((int)((0d / 100 * width)));
views.get("lblrdgtime").vw.setWidth((int)((30d / 100 * width) - ((0d / 100 * width))));
views.get("lblprescum").vw.setLeft((int)((30d / 100 * width)));
views.get("lblprescum").vw.setWidth((int)((47d / 100 * width) - ((30d / 100 * width))));
views.get("lblprevcum").vw.setLeft((int)((50d / 100 * width)));
views.get("lblprevcum").vw.setWidth((int)((67d / 100 * width) - ((50d / 100 * width))));
views.get("lbltotprod").vw.setLeft((int)((70d / 100 * width)));
views.get("lbltotprod").vw.setWidth((int)((97d / 100 * width) - ((70d / 100 * width))));

}
}