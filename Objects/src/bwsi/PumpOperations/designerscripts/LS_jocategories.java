package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_jocategories{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("pnljoinfo").vw.setTop((int)((1d / 100 * height)));
views.get("pnljoinfo").vw.setHeight((int)((99d / 100 * height) - ((1d / 100 * height))));
views.get("pnljoinfo").vw.setLeft((int)((1d / 100 * width)));
views.get("pnljoinfo").vw.setWidth((int)((99d / 100 * width) - ((1d / 100 * width))));
views.get("lbljocatdesc").vw.setTop((int)((5d / 100 * height)));
views.get("lbljocatdesc").vw.setHeight((int)((views.get("pnljoinfo").vw.getHeight())-(62d / 100 * height) - ((5d / 100 * height))));
views.get("lbljocatdesc").vw.setLeft((int)((2d / 100 * width)));
views.get("lbljocatdesc").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("label1").vw.setTop((int)((views.get("lbljocatdesc").vw.getTop() + views.get("lbljocatdesc").vw.getHeight())+(2d * scale)));
views.get("label1").vw.setLeft((int)((views.get("lbljocatdesc").vw.getLeft())));
views.get("lblpending").vw.setTop((int)((views.get("lbljocatdesc").vw.getTop() + views.get("lbljocatdesc").vw.getHeight())+(2d * scale)));
views.get("lblpending").vw.setLeft((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale)));
views.get("lblpending").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(46d / 100 * width) - ((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale))));
views.get("label2").vw.setTop((int)((views.get("label1").vw.getTop())));
views.get("label2").vw.setLeft((int)((views.get("lblpending").vw.getLeft() + views.get("lblpending").vw.getWidth())+(5d * scale)));
views.get("lblongoing").vw.setTop((int)((views.get("label2").vw.getTop())));
views.get("lblongoing").vw.setLeft((int)((views.get("label2").vw.getLeft() + views.get("label2").vw.getWidth())+(3d * scale)));
views.get("lblongoing").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(1d / 100 * width) - ((views.get("label2").vw.getLeft() + views.get("label2").vw.getWidth())+(3d * scale))));
views.get("label3").vw.setTop((int)((views.get("label2").vw.getTop() + views.get("label2").vw.getHeight())+(1d * scale)));
views.get("label3").vw.setLeft((int)((views.get("label1").vw.getLeft())));
views.get("lblaccomplished").vw.setTop((int)((views.get("label3").vw.getTop())));
views.get("lblaccomplished").vw.setLeft((int)((views.get("label3").vw.getLeft() + views.get("label3").vw.getWidth())+(3d * scale)));
views.get("lblaccomplished").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(46d / 100 * width) - ((views.get("label3").vw.getLeft() + views.get("label3").vw.getWidth())+(3d * scale))));
views.get("label4").vw.setTop((int)((views.get("label2").vw.getTop() + views.get("label2").vw.getHeight())+(1d * scale)));
views.get("label4").vw.setLeft((int)((views.get("label2").vw.getLeft())));
views.get("lblcancelled").vw.setTop((int)((views.get("label4").vw.getTop())));
views.get("lblcancelled").vw.setLeft((int)((views.get("label4").vw.getLeft() + views.get("label4").vw.getWidth())+(3d * scale)));
views.get("lblcancelled").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(1d / 100 * width) - ((views.get("label4").vw.getLeft() + views.get("label4").vw.getWidth())+(3d * scale))));
views.get("label5").vw.setTop((int)((views.get("label4").vw.getTop() + views.get("label4").vw.getHeight())+(1d * scale)));
views.get("label5").vw.setLeft((int)((views.get("label3").vw.getLeft())));
views.get("lbltotals").vw.setTop((int)((views.get("label5").vw.getTop())));
views.get("lbltotals").vw.setLeft((int)((views.get("label5").vw.getLeft() + views.get("label5").vw.getWidth())+(3d * scale)));
views.get("lbltotals").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(1d / 100 * width) - ((views.get("label5").vw.getLeft() + views.get("label5").vw.getWidth())+(3d * scale))));

}
}