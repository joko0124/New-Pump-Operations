package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_psidistrecords{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("toolbar").vw.setTop((int)((0d * scale)));
if ((anywheresoftware.b4a.keywords.LayoutBuilder.getScreenSize()>6.5d)) { 
;
views.get("toolbar").vw.setHeight((int)((64d * scale)));
;}else{ 
;
if ((BA.ObjectToBoolean( String.valueOf(anywheresoftware.b4a.keywords.LayoutBuilder.isPortrait())))) { 
;
views.get("toolbar").vw.setHeight((int)((50d * scale)));
;}else{ 
;
views.get("toolbar").vw.setHeight((int)((42d * scale)));
;};
;};
views.get("pnlmain").vw.setTop((int)((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight())));
views.get("pnlmain").vw.setHeight((int)((100d / 100 * height) - ((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight()))));
views.get("pnlmain").vw.setLeft((int)(0d));
views.get("pnlmain").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("pnlpump").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlpump").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlpump").vw.setTop((int)((1d / 100 * height)));
views.get("pnlpump").vw.setHeight((int)((23d / 100 * height) - ((1d / 100 * height))));
views.get("lblpumptitle").vw.setLeft((int)(0d));
views.get("lblpumptitle").vw.setWidth((int)((views.get("pnlpump").vw.getWidth()) - (0d)));
views.get("lblpumptitle").vw.setTop((int)(0d));
views.get("lblcode").vw.setTop((int)((views.get("lblpumptitle").vw.getTop() + views.get("lblpumptitle").vw.getHeight())+(2d * scale)));
views.get("lblcode").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcode").vw.setWidth((int)((views.get("pnlpump").vw.getWidth())-(5d * scale) - ((2d / 100 * width))));
views.get("lbl6").vw.setTop((int)((views.get("lblcode").vw.getTop() + views.get("lblcode").vw.getHeight())+(2d * scale)));
views.get("lbl6").vw.setLeft((int)((views.get("lblcode").vw.getLeft())));
views.get("pnlpsipoint").vw.setTop((int)((views.get("lbl6").vw.getTop())));
views.get("pnlpsipoint").vw.setLeft((int)((views.get("lbl6").vw.getLeft() + views.get("lbl6").vw.getWidth())+(5d * scale)));
views.get("pnlpsipoint").vw.setWidth((int)((views.get("pnlpump").vw.getWidth())-(2d / 100 * width) - ((views.get("lbl6").vw.getLeft() + views.get("lbl6").vw.getWidth())+(5d * scale))));
views.get("cbopsipoint").vw.setLeft((int)((1.5d / 100 * width)));
views.get("cbopsipoint").vw.setWidth((int)((views.get("pnlpsipoint").vw.getWidth())-(1.5d / 100 * width) - ((1.5d / 100 * width))));
views.get("cbopsipoint").vw.setTop((int)((0.25d / 100 * height)));
views.get("cbopsipoint").vw.setHeight((int)((views.get("pnlpsipoint").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("pnllocation").vw.setLeft((int)((views.get("lbl6").vw.getLeft())));
views.get("pnllocation").vw.setWidth((int)((views.get("pnlpsipoint").vw.getLeft() + views.get("pnlpsipoint").vw.getWidth()) - ((views.get("lbl6").vw.getLeft()))));
views.get("pnllocation").vw.setTop((int)((views.get("pnlpsipoint").vw.getTop() + views.get("pnlpsipoint").vw.getHeight())+(5d * scale)));
views.get("txtlocation").vw.setLeft((int)((1d / 100 * width)));
views.get("txtlocation").vw.setWidth((int)((views.get("pnllocation").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtlocation").vw.setTop((int)((0.25d / 100 * height)));
views.get("txtlocation").vw.setHeight((int)((views.get("pnllocation").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("pnlreadingtime").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlreadingtime").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlreadingtime").vw.setTop((int)((views.get("pnlpump").vw.getTop() + views.get("pnlpump").vw.getHeight())+(5d * scale)));
views.get("pnlreadingtime").vw.setHeight((int)((43d / 100 * height) - ((views.get("pnlpump").vw.getTop() + views.get("pnlpump").vw.getHeight())+(5d * scale))));
views.get("lblrdgtimetitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblrdgtimetitle").vw.setWidth((int)((views.get("pnlreadingtime").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblrdgtimetitle").vw.setTop((int)(0d));
views.get("chkdefaulttimeread").vw.setLeft((int)((2d / 100 * width)));
views.get("chkdefaulttimeread").vw.setWidth((int)((views.get("pnlreadingtime").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("chkdefaulttimeread").vw.setTop((int)((views.get("lblrdgtimetitle").vw.getTop() + views.get("lblrdgtimetitle").vw.getHeight())+(5d * scale)));
views.get("label1").vw.setLeft((int)((3d / 100 * width)));
views.get("label1").vw.setTop((int)((views.get("chkdefaulttimeread").vw.getTop() + views.get("chkdefaulttimeread").vw.getHeight())+(5d * scale)));
views.get("pnlrdgtimeanchor").vw.setTop((int)((views.get("chkdefaulttimeread").vw.getTop() + views.get("chkdefaulttimeread").vw.getHeight())+(5d * scale)));
views.get("pnlrdgtimeanchor").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight()/2) - (views.get("pnlrdgtimeanchor").vw.getHeight() / 2)));
views.get("pnlrdgtimeanchor").vw.setLeft((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale)));
views.get("pnlrdgtimeanchor").vw.setWidth((int)((views.get("pnlreadingtime").vw.getWidth())-(50d / 100 * width) - ((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale))));
views.get("msktimeread").vw.setTop((int)((0.5d / 100 * height)));
views.get("msktimeread").vw.setHeight((int)((views.get("pnlrdgtimeanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("msktimeread").vw.setLeft((int)((1d / 100 * width)));
views.get("msktimeread").vw.setWidth((int)((views.get("pnlrdgtimeanchor").vw.getWidth())-(19d * scale) - ((1d / 100 * width))));
views.get("pnlstyle5").vw.setTop((int)((views.get("chkdefaulttimeread").vw.getTop() + views.get("chkdefaulttimeread").vw.getHeight())+(5d * scale)));
views.get("pnlstyle5").vw.setLeft((int)((views.get("pnlrdgtimeanchor").vw.getLeft() + views.get("pnlrdgtimeanchor").vw.getWidth())-(18d * scale)));
views.get("pnlstyle5").vw.setWidth((int)((views.get("pnlreadingtime").vw.getWidth())-(2d / 100 * width) - ((views.get("pnlrdgtimeanchor").vw.getLeft() + views.get("pnlrdgtimeanchor").vw.getWidth())-(18d * scale))));
views.get("label16").vw.setLeft((int)((1d / 100 * width)));
views.get("label16").vw.setWidth((int)((views.get("pnlstyle5").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlcover2").vw.setLeft((int)((views.get("pnlstyle5").vw.getLeft())));
views.get("pnlcover2").vw.setTop((int)((views.get("chkdefaulttimeread").vw.getTop() + views.get("chkdefaulttimeread").vw.getHeight())+(5d * scale)));
views.get("pnlreading").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlreading").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlreading").vw.setTop((int)((views.get("pnlreadingtime").vw.getTop() + views.get("pnlreadingtime").vw.getHeight())+(5d * scale)));
views.get("pnlreading").vw.setHeight((int)((62d / 100 * height) - ((views.get("pnlreadingtime").vw.getTop() + views.get("pnlreadingtime").vw.getHeight())+(5d * scale))));
views.get("lblreadingtitle").vw.setTop((int)(0d));
views.get("lblreadingtitle").vw.setLeft((int)(0d));
views.get("lblreadingtitle").vw.setWidth((int)((views.get("pnlreading").vw.getWidth()) - (0d)));
views.get("pnlanchorpsi").vw.setTop((int)((views.get("lblreadingtitle").vw.getTop() + views.get("lblreadingtitle").vw.getHeight())+(5d * scale)));
views.get("pnlanchorpsi").vw.setHeight((int)((views.get("pnlreading").vw.getHeight())-(5d * scale) - ((views.get("lblreadingtitle").vw.getTop() + views.get("lblreadingtitle").vw.getHeight())+(5d * scale))));
views.get("pnlanchorpsi").vw.setLeft((int)((1d / 100 * width)));
views.get("pnlanchorpsi").vw.setWidth((int)((views.get("pnlreading").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtpsirdg").vw.setLeft((int)((1d / 100 * width)));
views.get("txtpsirdg").vw.setWidth((int)((views.get("pnlanchorpsi").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtpsirdg").vw.setTop((int)((1d / 100 * height)));
views.get("txtpsirdg").vw.setHeight((int)((views.get("pnlanchorpsi").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("pnlremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlremarks").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlremarks").vw.setTop((int)((views.get("pnlreading").vw.getTop() + views.get("pnlreading").vw.getHeight())+(5d * scale)));
views.get("pnlremarks").vw.setHeight((int)((85d / 100 * height) - ((views.get("pnlreading").vw.getTop() + views.get("pnlreading").vw.getHeight())+(5d * scale))));
views.get("lblremarkstitle").vw.setLeft((int)(0d));
views.get("lblremarkstitle").vw.setWidth((int)((views.get("pnlremarks").vw.getWidth()) - (0d)));
views.get("lblremarkstitle").vw.setTop((int)(0d));
views.get("pnlremarksanchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlremarksanchor").vw.setWidth((int)((views.get("pnlremarks").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlremarksanchor").vw.setTop((int)((views.get("lblremarkstitle").vw.getTop() + views.get("lblremarkstitle").vw.getHeight())+(5d * scale)));
views.get("pnlremarksanchor").vw.setHeight((int)((views.get("pnlremarks").vw.getHeight())-(2d / 100 * height) - ((views.get("lblremarkstitle").vw.getTop() + views.get("lblremarkstitle").vw.getHeight())+(5d * scale))));
views.get("txtremarks").vw.setLeft((int)((1d / 100 * width)));
views.get("txtremarks").vw.setWidth((int)((views.get("pnlremarksanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtremarks").vw.setTop((int)((1d / 100 * height)));
views.get("txtremarks").vw.setHeight((int)((views.get("pnlremarksanchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("btnsave").vw.setLeft((int)((2d / 100 * width)));
views.get("btnsave").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("btnsave").vw.setTop((int)((views.get("pnlremarks").vw.getTop() + views.get("pnlremarks").vw.getHeight())+(10d * scale)));
views.get("btnsave").vw.setHeight((int)((90d / 100 * height) - ((views.get("pnlremarks").vw.getTop() + views.get("pnlremarks").vw.getHeight())+(10d * scale))));

}
}