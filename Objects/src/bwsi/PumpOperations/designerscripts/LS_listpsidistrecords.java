package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_listpsidistrecords{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("lbldisttimeread").vw.setLeft((int)((2.5d / 100 * width)));
views.get("lbldisttimeread").vw.setWidth((int)((30d / 100 * width) - ((2.5d / 100 * width))));
views.get("lbllocation").vw.setLeft((int)((2.5d / 100 * width)));
views.get("lbllocation").vw.setWidth((int)((80d / 100 * width) - ((2.5d / 100 * width))));
views.get("lbllocation").vw.setTop((int)((views.get("lbldisttimeread").vw.getTop() + views.get("lbldisttimeread").vw.getHeight())-(5d * scale)));
views.get("lbldistpsirdg").vw.setLeft((int)((82d / 100 * width)));
views.get("lbldistpsirdg").vw.setWidth((int)((97d / 100 * width) - ((82d / 100 * width))));
views.get("lblstyle").vw.setLeft((int)((82d / 100 * width)));
views.get("lblstyle").vw.setWidth((int)((97d / 100 * width) - ((82d / 100 * width))));
views.get("lblstyle").vw.setTop((int)((views.get("lbldistpsirdg").vw.getTop() + views.get("lbldistpsirdg").vw.getHeight())-(7d * scale)));

}
}