package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_dashboard{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("pnltoolbar").vw.setTop((int)((0d * scale)));
views.get("pnltoolbar").vw.setLeft((int)((0d / 100 * width)));
views.get("pnltoolbar").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
if ((anywheresoftware.b4a.keywords.LayoutBuilder.getScreenSize()>6.5d)) { 
;
views.get("pnltoolbar").vw.setHeight((int)((64d * scale)));
;}else{ 
;
if ((BA.ObjectToBoolean( String.valueOf(anywheresoftware.b4a.keywords.LayoutBuilder.isPortrait())))) { 
;
views.get("pnltoolbar").vw.setHeight((int)((50d * scale)));
;}else{ 
;
views.get("pnltoolbar").vw.setHeight((int)((42d * scale)));
;};
;};
views.get("lblexit").vw.setLeft((int)((views.get("pnltoolbar").vw.getWidth())-(2d / 100 * width) - (views.get("lblexit").vw.getWidth())));
views.get("lblexit").vw.setTop((int)((5d * scale)));
views.get("pnlmain").vw.setTop((int)((views.get("pnltoolbar").vw.getTop() + views.get("pnltoolbar").vw.getHeight())));
views.get("pnlmain").vw.setHeight((int)((100d / 100 * height) - ((views.get("pnltoolbar").vw.getTop() + views.get("pnltoolbar").vw.getHeight()))));
views.get("pnlmain").vw.setLeft((int)(0d));
views.get("pnlmain").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("lblempname").vw.setLeft((int)((2d / 100 * width)));
views.get("lblempname").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblempname").vw.setTop((int)((1d / 100 * height)));
views.get("lblbranchname").vw.setLeft((int)((2d / 100 * width)));
views.get("lblbranchname").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblbranchname").vw.setTop((int)((views.get("lblempname").vw.getTop() + views.get("lblempname").vw.getHeight())));
views.get("lblreadingperiod").vw.setLeft((int)((2d / 100 * width)));
views.get("lblreadingperiod").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblreadingperiod").vw.setTop((int)((views.get("lblbranchname").vw.getTop() + views.get("lblbranchname").vw.getHeight())));
views.get("pnlprod").vw.setTop((int)((views.get("pnltoolbar").vw.getTop() + views.get("pnltoolbar").vw.getHeight())+(10d * scale)));
views.get("lblempname").vw.setLeft((int)((5d / 100 * width)));
views.get("lblempname").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("lblempname").vw.setTop((int)((views.get("pnltoolbar").vw.getTop() + views.get("pnltoolbar").vw.getHeight())+(7d * scale)));
views.get("lblbranchname").vw.setLeft((int)((5d / 100 * width)));
views.get("lblbranchname").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("lblbranchname").vw.setTop((int)((views.get("lblempname").vw.getTop() + views.get("lblempname").vw.getHeight())));
views.get("pnlprod").vw.setLeft((int)((5d / 100 * width)));
views.get("pnlprod").vw.setWidth((int)((48d / 100 * width) - ((5d / 100 * width))));
views.get("pnlprod").vw.setTop((int)((19d / 100 * height)));
views.get("pnlprod").vw.setHeight((int)((42d / 100 * height) - ((19d / 100 * height))));
views.get("lbl1").vw.setLeft((int)((22d / 100 * width) - (views.get("lbl1").vw.getWidth() / 2)));
views.get("lbl1").vw.setTop((int)((20d / 100 * height) - (views.get("lbl1").vw.getHeight() / 2)));
views.get("img1").vw.setLeft((int)((22d / 100 * width) - (views.get("img1").vw.getWidth() / 2)));
views.get("img1").vw.setTop((int)((10d / 100 * height) - (views.get("img1").vw.getHeight() / 2)));
views.get("pnlnonop").vw.setLeft((int)((52d / 100 * width)));
views.get("pnlnonop").vw.setWidth((int)((95d / 100 * width) - ((52d / 100 * width))));
views.get("pnlnonop").vw.setTop((int)((19d / 100 * height)));
views.get("pnlnonop").vw.setHeight((int)((42d / 100 * height) - ((19d / 100 * height))));
views.get("lbl3").vw.setLeft((int)((22d / 100 * width) - (views.get("lbl3").vw.getWidth() / 2)));
views.get("lbl3").vw.setTop((int)((20d / 100 * height) - (views.get("lbl3").vw.getHeight() / 2)));
views.get("img3").vw.setLeft((int)((22d / 100 * width) - (views.get("img3").vw.getWidth() / 2)));
views.get("img3").vw.setTop((int)((10d / 100 * height) - (views.get("img3").vw.getHeight() / 2)));
views.get("pnlrepair").vw.setLeft((int)((5d / 100 * width)));
views.get("pnlrepair").vw.setWidth((int)((48d / 100 * width) - ((5d / 100 * width))));
views.get("pnlrepair").vw.setTop((int)((44d / 100 * height)));
views.get("pnlrepair").vw.setHeight((int)((67d / 100 * height) - ((44d / 100 * height))));
views.get("lbl2").vw.setLeft((int)((22d / 100 * width) - (views.get("lbl2").vw.getWidth() / 2)));
views.get("lbl2").vw.setTop((int)((20d / 100 * height) - (views.get("lbl2").vw.getHeight() / 2)));
views.get("img2").vw.setLeft((int)((22d / 100 * width) - (views.get("img2").vw.getWidth() / 2)));
views.get("img2").vw.setTop((int)((10d / 100 * height) - (views.get("img2").vw.getHeight() / 2)));
views.get("pnljo").vw.setLeft((int)((52d / 100 * width)));
views.get("pnljo").vw.setWidth((int)((95d / 100 * width) - ((52d / 100 * width))));
views.get("pnljo").vw.setTop((int)((44d / 100 * height)));
views.get("pnljo").vw.setHeight((int)((67d / 100 * height) - ((44d / 100 * height))));
views.get("lbl4").vw.setLeft((int)((22d / 100 * width) - (views.get("lbl4").vw.getWidth() / 2)));
views.get("lbl4").vw.setTop((int)((20d / 100 * height) - (views.get("lbl4").vw.getHeight() / 2)));
views.get("img4").vw.setLeft((int)((22d / 100 * width) - (views.get("img4").vw.getWidth() / 2)));
views.get("img4").vw.setTop((int)((10d / 100 * height) - (views.get("img4").vw.getHeight() / 2)));
views.get("pnlwb").vw.setLeft((int)((5d / 100 * width)));
views.get("pnlwb").vw.setWidth((int)((48d / 100 * width) - ((5d / 100 * width))));
views.get("pnlwb").vw.setTop((int)((69d / 100 * height)));
views.get("pnlwb").vw.setHeight((int)((94d / 100 * height) - ((69d / 100 * height))));
views.get("lbl5").vw.setLeft((int)((22d / 100 * width) - (views.get("lbl5").vw.getWidth() / 2)));
views.get("lbl5").vw.setTop((int)((20d / 100 * height) - (views.get("lbl5").vw.getHeight() / 2)));
views.get("img5").vw.setLeft((int)((22d / 100 * width) - (views.get("img5").vw.getWidth() / 2)));
views.get("img5").vw.setTop((int)((10d / 100 * height) - (views.get("img5").vw.getHeight() / 2)));
views.get("pnlgpm").vw.setLeft((int)((52d / 100 * width)));
views.get("pnlgpm").vw.setWidth((int)((95d / 100 * width) - ((52d / 100 * width))));
views.get("pnlgpm").vw.setTop((int)((69d / 100 * height)));
views.get("pnlgpm").vw.setHeight((int)((94d / 100 * height) - ((69d / 100 * height))));
views.get("lbl6").vw.setLeft((int)((22d / 100 * width) - (views.get("lbl6").vw.getWidth() / 2)));
views.get("lbl6").vw.setTop((int)((20d / 100 * height) - (views.get("lbl6").vw.getHeight() / 2)));
views.get("img6").vw.setLeft((int)((22d / 100 * width) - (views.get("img6").vw.getWidth() / 2)));
views.get("img6").vw.setTop((int)((10d / 100 * height) - (views.get("img6").vw.getHeight() / 2)));
views.get("pnlstatus").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlstatus").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlstatus").vw.setTop((int)((96d / 100 * height)));
views.get("pnlstatus").vw.setHeight((int)((100d / 100 * height) - ((96d / 100 * height))));
views.get("lblstatus").vw.setLeft((int)((1d / 100 * width)));
views.get("lblstatus").vw.setWidth((int)((99d / 100 * width) - ((1d / 100 * width))));
views.get("lblstatus").vw.setLeft((int)((50d / 100 * width) - (views.get("lblstatus").vw.getWidth() / 2)));
views.get("lblstatus").vw.setTop((int)(0-(5d * scale)));
views.get("pnlpumpselection").vw.setHeight((int)((100d / 100 * height)));
views.get("pnlpumpselection").vw.setWidth((int)((100d / 100 * width)));
views.get("pnlpumpselection").vw.setLeft((int)(0d));
views.get("pnlpumpselection").vw.setTop((int)(0d));
views.get("pnlanchor").vw.setHeight((int)((40d / 100 * height)));
views.get("pnlanchor").vw.setLeft((int)((5d / 100 * width)));
views.get("pnlanchor").vw.setWidth((int)((views.get("pnlpumpselection").vw.getWidth())-(5d / 100 * width) - ((5d / 100 * width))));
views.get("pnlanchor").vw.setTop((int)((views.get("pnlpumpselection").vw.getHeight())/2d-(5d / 100 * height) - (views.get("pnlanchor").vw.getHeight() / 2)));
views.get("lblpumpseltitle").vw.setTop((int)(0d));
views.get("lblpumpseltitle").vw.setLeft((int)(0d));
views.get("lblpumpseltitle").vw.setWidth((int)((views.get("pnlanchor").vw.getWidth()) - (0d)));
views.get("lblmessage").vw.setLeft((int)((5d / 100 * width)));
views.get("lblmessage").vw.setWidth((int)((views.get("pnlanchor").vw.getWidth())-(2d / 100 * width) - ((5d / 100 * width))));
views.get("lblmessage").vw.setTop((int)((views.get("lblpumpseltitle").vw.getTop() + views.get("lblpumpseltitle").vw.getHeight())+(10d * scale)));
views.get("clvpumplist").vw.setTop((int)((views.get("lblmessage").vw.getTop() + views.get("lblmessage").vw.getHeight())+(10d * scale)));
views.get("clvpumplist").vw.setHeight((int)((views.get("pnlanchor").vw.getHeight())-(10d * scale) - ((views.get("lblmessage").vw.getTop() + views.get("lblmessage").vw.getHeight())+(10d * scale))));
views.get("clvpumplist").vw.setLeft((int)((4d / 100 * width)));
views.get("clvpumplist").vw.setWidth((int)((views.get("pnlanchor").vw.getWidth())-(4d / 100 * width) - ((4d / 100 * width))));

}
}