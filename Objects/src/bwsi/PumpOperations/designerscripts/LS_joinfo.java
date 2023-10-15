package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_joinfo{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("pnljoinfo").vw.setTop((int)((1d / 100 * height)));
views.get("pnljoinfo").vw.setHeight((int)((99d / 100 * height) - ((1d / 100 * height))));
views.get("pnljoinfo").vw.setLeft((int)((1d / 100 * width)));
views.get("pnljoinfo").vw.setWidth((int)((99d / 100 * width) - ((1d / 100 * width))));
views.get("lbljonum").vw.setTop((int)((3d / 100 * height)));
views.get("lbljonum").vw.setHeight((int)((views.get("pnljoinfo").vw.getHeight())-(73d / 100 * height) - ((3d / 100 * height))));
views.get("lbljonum").vw.setLeft((int)((5d / 100 * width)));
views.get("lbljonum").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(5d / 100 * width) - ((5d / 100 * width))));
views.get("lblreftitle").vw.setTop((int)((views.get("lbljonum").vw.getTop() + views.get("lbljonum").vw.getHeight())+(2d * scale)));
views.get("lblreftitle").vw.setLeft((int)((views.get("lbljonum").vw.getLeft())));
views.get("lblappno").vw.setTop((int)((views.get("lbljonum").vw.getTop() + views.get("lbljonum").vw.getHeight())+(2d * scale)));
views.get("lblappno").vw.setLeft((int)((views.get("lblreftitle").vw.getLeft() + views.get("lblreftitle").vw.getWidth())+(3d * scale)));
views.get("lblappno").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(1d / 100 * width) - ((views.get("lblreftitle").vw.getLeft() + views.get("lblreftitle").vw.getWidth())+(3d * scale))));
views.get("label2").vw.setTop((int)((views.get("lblreftitle").vw.getTop() + views.get("lblreftitle").vw.getHeight())+(1d * scale)));
views.get("label2").vw.setLeft((int)((views.get("lbljonum").vw.getLeft())));
views.get("lblcustname").vw.setTop((int)((views.get("lblreftitle").vw.getTop() + views.get("lblreftitle").vw.getHeight())+(1d * scale)));
views.get("lblcustname").vw.setLeft((int)((views.get("label2").vw.getLeft() + views.get("label2").vw.getWidth())+(3d * scale)));
views.get("lblcustname").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(1d / 100 * width) - ((views.get("label2").vw.getLeft() + views.get("label2").vw.getWidth())+(3d * scale))));
views.get("lblcustaddress").vw.setTop((int)((views.get("lblcustname").vw.getTop() + views.get("lblcustname").vw.getHeight())+(2d * scale)));
views.get("lblcustaddress").vw.setLeft((int)((views.get("label2").vw.getLeft())+(10d * scale)));
views.get("lblcustaddress").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(1d / 100 * width) - ((views.get("label2").vw.getLeft())+(10d * scale))));
views.get("label3").vw.setTop((int)((views.get("lblcustaddress").vw.getTop() + views.get("lblcustaddress").vw.getHeight())+(1d * scale)));
views.get("label3").vw.setLeft((int)((views.get("lbljonum").vw.getLeft())));
views.get("lblstatus").vw.setTop((int)((views.get("lblcustaddress").vw.getTop() + views.get("lblcustaddress").vw.getHeight())+(1d * scale)));
views.get("lblstatus").vw.setLeft((int)((views.get("label3").vw.getLeft() + views.get("label3").vw.getWidth())+(3d * scale)));
views.get("lblstatus").vw.setWidth((int)((views.get("pnljoinfo").vw.getWidth())-(5d / 100 * width) - ((views.get("label3").vw.getLeft() + views.get("label3").vw.getWidth())+(3d * scale))));

}
}