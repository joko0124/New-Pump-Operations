package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_listgpmresults{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("lbltestdate").vw.setLeft((int)((2d / 100 * width)));
views.get("lbltestdate").vw.setWidth((int)((25d / 100 * width) - ((2d / 100 * width))));
views.get("lblgpmres").vw.setLeft((int)((28d / 100 * width)));
views.get("lblgpmres").vw.setWidth((int)((50d / 100 * width) - ((28d / 100 * width))));
views.get("lblwaterquality").vw.setLeft((int)((53d / 100 * width)));
views.get("lblwaterquality").vw.setWidth((int)((98d / 100 * width) - ((53d / 100 * width))));

}
}