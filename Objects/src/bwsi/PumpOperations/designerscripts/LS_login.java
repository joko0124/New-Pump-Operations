package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_login{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("pnlsignup").vw.setLeft((int)((50d / 100 * width) - (views.get("pnlsignup").vw.getWidth() / 2)));
views.get("panel2").vw.setWidth((int)((90d / 100 * width)));
views.get("panel2").vw.setLeft((int)((50d / 100 * width) - (views.get("panel2").vw.getWidth() / 2)));
views.get("logo").vw.setLeft((int)((45d / 100 * width) - (views.get("logo").vw.getWidth() / 2)));

}
}