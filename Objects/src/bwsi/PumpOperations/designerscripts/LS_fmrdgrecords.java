package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_fmrdgrecords{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("lbltitle").vw.setLeft((int)(0d));
views.get("lbltitle").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("pnlrdg").vw.setLeft((int)((10d * scale)));
views.get("pnlrdg").vw.setWidth((int)((98d / 100 * width) - ((10d * scale))));
views.get("pnlrdg").vw.setTop((int)((views.get("lbltitle").vw.getTop() + views.get("lbltitle").vw.getHeight())+(10d * scale)));
views.get("pnlrdg").vw.setHeight((int)((18d / 100 * height) - ((views.get("lbltitle").vw.getTop() + views.get("lbltitle").vw.getHeight())+(10d * scale))));
views.get("pnlanchorfm").vw.setTop((int)((5d * scale)));
views.get("pnlanchorfm").vw.setHeight((int)((11d / 100 * height) - ((5d * scale))));
views.get("pnlanchorfm").vw.setLeft((int)((10d * scale)));
views.get("pnlanchorfm").vw.setWidth((int)((93d / 100 * width) - ((10d * scale))));
views.get("txtfmrdg").vw.setLeft((int)((7d * scale)));
views.get("txtfmrdg").vw.setWidth((int)((88d / 100 * width) - ((7d * scale))));
views.get("txtfmrdg").vw.setTop((int)((5d * scale)));
views.get("txtfmrdg").vw.setHeight((int)((9.5d / 100 * height) - ((5d * scale))));
views.get("pnlremarks").vw.setTop((int)((views.get("pnlrdg").vw.getTop() + views.get("pnlrdg").vw.getHeight())+(10d * scale)));
views.get("pnlremarks").vw.setHeight((int)((36d / 100 * height) - ((views.get("pnlrdg").vw.getTop() + views.get("pnlrdg").vw.getHeight())+(10d * scale))));
views.get("pnlremarks").vw.setLeft((int)((10d * scale)));
views.get("pnlremarks").vw.setWidth((int)((98d / 100 * width) - ((10d * scale))));
views.get("lblremarksonofftitle").vw.setTop((int)((0d * scale)));
views.get("lblremarksonofftitle").vw.setLeft((int)((0d * scale)));
views.get("lblremarksonofftitle").vw.setLeft((int)((0d * scale)));
views.get("lblremarksonofftitle").vw.setWidth((int)((97d / 100 * width) - ((0d * scale))));
views.get("panel5").vw.setLeft((int)((5d * scale)));
views.get("panel5").vw.setWidth((int)((94d / 100 * width) - ((5d * scale))));
views.get("panel5").vw.setTop((int)((views.get("lblremarksonofftitle").vw.getTop() + views.get("lblremarksonofftitle").vw.getHeight())+(5d * scale)));
views.get("panel5").vw.setHeight((int)((15d / 100 * height) - ((views.get("lblremarksonofftitle").vw.getTop() + views.get("lblremarksonofftitle").vw.getHeight())+(5d * scale))));
views.get("txtonoffremarks").vw.setLeft((int)((5d * scale)));
views.get("txtonoffremarks").vw.setWidth((int)((91d / 100 * width) - ((5d * scale))));
views.get("txtonoffremarks").vw.setTop((int)((5d * scale)));
views.get("txtonoffremarks").vw.setHeight((int)((9d / 100 * height) - ((5d * scale))));
views.get("btnsaveupdatefmrdg").vw.setTop((int)((views.get("pnlremarks").vw.getTop() + views.get("pnlremarks").vw.getHeight())+(5d * scale)));
views.get("btnsaveupdatefmrdg").vw.setHeight((int)((44d / 100 * height) - ((views.get("pnlremarks").vw.getTop() + views.get("pnlremarks").vw.getHeight())+(5d * scale))));
views.get("btnsaveupdatefmrdg").vw.setLeft((int)((10d * scale)));
views.get("btnsaveupdatefmrdg").vw.setWidth((int)((98d / 100 * width) - ((10d * scale))));

}
}