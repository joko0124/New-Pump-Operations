package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_listpsirecords{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("lblpsirdgtime").vw.setLeft((int)(0d));
views.get("lblpsirdgtime").vw.setWidth((int)((60d / 100 * width) - (0d)));
views.get("lblpsirdg").vw.setLeft((int)((60d / 100 * width)));
views.get("lblpsirdg").vw.setWidth((int)((97d / 100 * width) - ((60d / 100 * width))));

}
}