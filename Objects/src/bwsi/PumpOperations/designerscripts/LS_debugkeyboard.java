package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_debugkeyboard{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("pnlrdg").vw.setTop((int)((15d / 100 * height)+(10d * scale)));
views.get("pnlrdg").vw.setHeight((int)((44d / 100 * height) - ((15d / 100 * height)+(10d * scale))));
views.get("pnlrdg").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlrdg").vw.setWidth((int)((98d / 100 * width) - ((2d / 100 * width))));
views.get("lblfmrdgtitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblfmrdgtitle").vw.setWidth((int)((views.get("pnlrdg").vw.getWidth()) - ((0d / 100 * width))));
views.get("pnlfmrdganchor").vw.setTop((int)((views.get("lblfmrdgtitle").vw.getTop() + views.get("lblfmrdgtitle").vw.getHeight())+(5d * scale)));
views.get("pnlfmrdganchor").vw.setHeight((int)((views.get("pnlrdg").vw.getHeight())-(1d / 100 * height) - ((views.get("lblfmrdgtitle").vw.getTop() + views.get("lblfmrdgtitle").vw.getHeight())+(5d * scale))));
views.get("pnlfmrdganchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlfmrdganchor").vw.setWidth((int)((views.get("pnlrdg").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtfmrdg").vw.setLeft((int)((1d / 100 * width)));
views.get("txtfmrdg").vw.setWidth((int)((views.get("pnlfmrdganchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtfmrdg").vw.setTop((int)((1d / 100 * height)));
views.get("txtfmrdg").vw.setHeight((int)((views.get("pnlfmrdganchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));

}
}