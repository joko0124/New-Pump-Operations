package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_listpumptimerecords{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("lbltimeon").vw.setLeft((int)((2.5d / 100 * width)));
views.get("lbltimeon").vw.setWidth((int)((32.5d / 100 * width) - ((2.5d / 100 * width))));
views.get("lbltimeoff").vw.setLeft((int)((32.5d / 100 * width)));
views.get("lbltimeoff").vw.setWidth((int)((65d / 100 * width) - ((32.5d / 100 * width))));
views.get("lblophrs").vw.setLeft((int)((65d / 100 * width)));
views.get("lblophrs").vw.setWidth((int)((97d / 100 * width) - ((65d / 100 * width))));

}
}