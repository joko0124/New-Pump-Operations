package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_jonotifinfo{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("pnljoinfo").vw.setTop((int)((1d / 100 * height)));
views.get("pnljoinfo").vw.setHeight((int)((99d / 100 * height) - ((1d / 100 * height))));
views.get("pnljoinfo").vw.setLeft((int)((1d / 100 * width)));
views.get("pnljoinfo").vw.setWidth((int)((99d / 100 * width) - ((1d / 100 * width))));
views.get("lbljonum").vw.setTop((int)((10d * scale)));
views.get("lbljonum").vw.setLeft((int)((1d / 100 * width)));
views.get("lbljonum").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("lblcustomer").vw.setTop((int)((views.get("lbljonum").vw.getTop() + views.get("lbljonum").vw.getHeight())-(2d * scale)));
views.get("lblcustomer").vw.setLeft((int)((1d / 100 * width)));
views.get("lblcustomer").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(22d / 100 * width) - ((1d / 100 * width))));
views.get("lbldate").vw.setLeft((int)((views.get("lblcustomer").vw.getLeft() + views.get("lblcustomer").vw.getWidth())+(3d * scale)));
views.get("lbldate").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(1d / 100 * width) - ((views.get("lblcustomer").vw.getLeft() + views.get("lblcustomer").vw.getWidth())+(3d * scale))));
views.get("lbldate").vw.setTop((int)((views.get("lblcustomer").vw.getTop())));

}
}