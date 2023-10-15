package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_jorecord{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("pnljo").vw.setLeft((int)((1d / 100 * width)));
views.get("pnljo").vw.setWidth((int)((99d / 100 * width) - ((1d / 100 * width))));
views.get("pnljo").vw.setTop((int)(0d));
views.get("pnljo").vw.setHeight((int)((19d / 100 * height) - (0d)));
views.get("lbljono").vw.setLeft((int)((2d / 100 * width)));
views.get("lbljono").vw.setWidth((int)((views.get("pnljo").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lbljono").vw.setTop((int)((5d * scale)));
views.get("lbljono").vw.setHeight((int)((views.get("lbljono").vw.getHeight()) - ((5d * scale))));
views.get("lbljotype").vw.setLeft((int)((2d / 100 * width)));
views.get("lbljotype").vw.setWidth((int)((views.get("pnljo").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lbljotype").vw.setTop((int)((views.get("lbljono").vw.getTop() + views.get("lbljono").vw.getHeight())-(5d * scale)));
views.get("lblacctno").vw.setLeft((int)((2d / 100 * width)));
views.get("lblacctno").vw.setWidth((int)((views.get("pnljo").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblcustname").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcustname").vw.setWidth((int)((94d / 100 * width) - ((2d / 100 * width))));
views.get("lblcustaddress").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcustaddress").vw.setWidth((int)((views.get("pnljo").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));

}
}