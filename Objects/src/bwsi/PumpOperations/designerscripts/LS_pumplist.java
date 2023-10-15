package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_pumplist{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("lblpumpcode").vw.setTop((int)(0d));
views.get("lblpumpcode").vw.setLeft((int)((2d / 100 * width)));
views.get("lblpumpcode").vw.setWidth((int)((98d / 100 * width) - ((2d / 100 * width))));
views.get("lblpumploc").vw.setLeft((int)((2d / 100 * width)));
views.get("lblpumploc").vw.setWidth((int)((98d / 100 * width) - ((2d / 100 * width))));
views.get("lblpumploc").vw.setTop((int)((views.get("lblpumpcode").vw.getTop() + views.get("lblpumpcode").vw.getHeight())-(10d * scale)));

}
}