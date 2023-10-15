package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_listchlorinatorrecords{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("lbltimerep").vw.setLeft((int)(0d));
views.get("lbltimerep").vw.setWidth((int)((29d / 100 * width) - (0d)));
views.get("lblchlorinetype").vw.setLeft((int)((29d / 100 * width)));
views.get("lblchlorinetype").vw.setWidth((int)((80d / 100 * width) - ((29d / 100 * width))));
views.get("lblvolume").vw.setLeft((int)((80d / 100 * width)));
views.get("lblvolume").vw.setWidth((int)((97d / 100 * width) - ((80d / 100 * width))));

}
}