package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_mainscreen{

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
views.get("pnluserinfo").vw.setLeft((int)((2d / 100 * width)));
views.get("pnluserinfo").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnluserinfo").vw.setTop((int)((1d / 100 * height)));
views.get("pnluserinfo").vw.setHeight((int)((14.5d / 100 * height) - ((1d / 100 * height))));
views.get("pnlstyle").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlstyle").vw.setTop((int)((1.5d / 100 * height)));
views.get("lblavatar").vw.setLeft((int)((views.get("pnlstyle").vw.getWidth())/2d - (views.get("lblavatar").vw.getWidth() / 2)));
views.get("lblavatar").vw.setTop((int)((views.get("pnlstyle").vw.getHeight())/2d - (views.get("lblavatar").vw.getHeight() / 2)));
views.get("lblempname").vw.setLeft((int)((views.get("pnlstyle").vw.getLeft() + views.get("pnlstyle").vw.getWidth())+(5d * scale)));
views.get("lblempname").vw.setWidth((int)((views.get("pnluserinfo").vw.getWidth())-(3d / 100 * width) - ((views.get("pnlstyle").vw.getLeft() + views.get("pnlstyle").vw.getWidth())+(5d * scale))));
views.get("lblempname").vw.setTop((int)((1.5d / 100 * height)));
views.get("lblbranchname").vw.setLeft((int)((views.get("pnlstyle").vw.getLeft() + views.get("pnlstyle").vw.getWidth())+(5d * scale)));
views.get("lblbranchname").vw.setWidth((int)((views.get("pnluserinfo").vw.getWidth())-(3d / 100 * width) - ((views.get("pnlstyle").vw.getLeft() + views.get("pnlstyle").vw.getWidth())+(5d * scale))));
views.get("lblbranchname").vw.setTop((int)((views.get("lblempname").vw.getTop() + views.get("lblempname").vw.getHeight())));
views.get("label1").vw.setTop((int)((views.get("lblbranchname").vw.getTop() + views.get("lblbranchname").vw.getHeight())-(2d * scale)));
views.get("label1").vw.setLeft((int)((0d / 100 * width)));
views.get("label1").vw.setWidth((int)((views.get("pnluserinfo").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblreadingperiod").vw.setLeft((int)((3d / 100 * width)));
views.get("lblreadingperiod").vw.setWidth((int)((views.get("pnluserinfo").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
views.get("lblreadingperiod").vw.setTop((int)((views.get("lblbranchname").vw.getTop() + views.get("lblbranchname").vw.getHeight())+(12d * scale)));
views.get("pnlprod").vw.setLeft((int)((6d / 100 * width)));
views.get("pnlprod").vw.setWidth((int)((48d / 100 * width) - ((6d / 100 * width))));
views.get("pnlprod").vw.setTop((int)((16d / 100 * height)));
views.get("pnlprod").vw.setHeight((int)((38d / 100 * height) - ((16d / 100 * height))));
views.get("img1").vw.setLeft((int)((views.get("pnlprod").vw.getWidth())/2d - (views.get("img1").vw.getWidth() / 2)));
views.get("img1").vw.setTop((int)((8.5d / 100 * height) - (views.get("img1").vw.getHeight() / 2)));
views.get("lbl1").vw.setLeft((int)((2d / 100 * width)));
views.get("lbl1").vw.setWidth((int)((views.get("pnlprod").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lbl1").vw.setTop((int)((views.get("img1").vw.getTop() + views.get("img1").vw.getHeight())+(15d * scale)));
views.get("pnljo").vw.setLeft((int)((52d / 100 * width)));
views.get("pnljo").vw.setWidth((int)((94d / 100 * width) - ((52d / 100 * width))));
views.get("pnljo").vw.setTop((int)((16d / 100 * height)));
views.get("pnljo").vw.setHeight((int)((38d / 100 * height) - ((16d / 100 * height))));
views.get("img4").vw.setLeft((int)((views.get("pnljo").vw.getWidth())/2d - (views.get("img4").vw.getWidth() / 2)));
views.get("img4").vw.setTop((int)((8.5d / 100 * height) - (views.get("img4").vw.getHeight() / 2)));
views.get("lbl4").vw.setLeft((int)((2d / 100 * width)));
views.get("lbl4").vw.setWidth((int)((views.get("pnljo").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lbl4").vw.setTop((int)((views.get("img4").vw.getTop() + views.get("img4").vw.getHeight())+(5d * scale)));
views.get("pnlrepair").vw.setLeft((int)((6d / 100 * width)));
views.get("pnlrepair").vw.setWidth((int)((48d / 100 * width) - ((6d / 100 * width))));
views.get("pnlrepair").vw.setTop((int)((40d / 100 * height)));
views.get("pnlrepair").vw.setHeight((int)((62d / 100 * height) - ((40d / 100 * height))));
views.get("img2").vw.setLeft((int)((views.get("pnlrepair").vw.getWidth())/2d - (views.get("img2").vw.getWidth() / 2)));
views.get("img2").vw.setTop((int)((8.5d / 100 * height) - (views.get("img2").vw.getHeight() / 2)));
views.get("lbl2").vw.setLeft((int)((2d / 100 * width)));
views.get("lbl2").vw.setWidth((int)((views.get("pnlrepair").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lbl2").vw.setTop((int)((views.get("img2").vw.getTop() + views.get("img2").vw.getHeight())+(5d * scale)));
views.get("pnlgpm").vw.setLeft((int)((52d / 100 * width)));
views.get("pnlgpm").vw.setWidth((int)((94d / 100 * width) - ((52d / 100 * width))));
views.get("pnlgpm").vw.setTop((int)((40d / 100 * height)));
views.get("pnlgpm").vw.setHeight((int)((62d / 100 * height) - ((40d / 100 * height))));
views.get("img6").vw.setLeft((int)((views.get("pnlgpm").vw.getWidth())/2d - (views.get("img6").vw.getWidth() / 2)));
views.get("img6").vw.setTop((int)((8.5d / 100 * height) - (views.get("img6").vw.getHeight() / 2)));
views.get("lbl6").vw.setLeft((int)((2d / 100 * width)));
views.get("lbl6").vw.setWidth((int)((views.get("pnlgpm").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lbl6").vw.setTop((int)((views.get("img6").vw.getTop() + views.get("img6").vw.getHeight())+(15d * scale)));
views.get("pnlnonop").vw.setLeft((int)((6d / 100 * width)));
views.get("pnlnonop").vw.setWidth((int)((48d / 100 * width) - ((6d / 100 * width))));
views.get("pnlnonop").vw.setTop((int)((64d / 100 * height)));
views.get("pnlnonop").vw.setHeight((int)((86d / 100 * height) - ((64d / 100 * height))));
views.get("img3").vw.setLeft((int)((views.get("pnlnonop").vw.getWidth())/2d - (views.get("img3").vw.getWidth() / 2)));
views.get("img3").vw.setTop((int)((8.5d / 100 * height) - (views.get("img3").vw.getHeight() / 2)));
views.get("lbl3").vw.setLeft((int)((2d / 100 * width)));
views.get("lbl3").vw.setWidth((int)((views.get("pnlnonop").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lbl3").vw.setTop((int)((views.get("img3").vw.getTop() + views.get("img3").vw.getHeight())+(15d * scale)));
views.get("pnlcpm").vw.setLeft((int)((52d / 100 * width)));
views.get("pnlcpm").vw.setWidth((int)((94d / 100 * width) - ((52d / 100 * width))));
views.get("pnlcpm").vw.setTop((int)((64d / 100 * height)));
views.get("pnlcpm").vw.setHeight((int)((86d / 100 * height) - ((64d / 100 * height))));
views.get("img5").vw.setLeft((int)((views.get("pnlcpm").vw.getWidth())/2d - (views.get("img5").vw.getWidth() / 2)));
views.get("img5").vw.setTop((int)((8.5d / 100 * height) - (views.get("img5").vw.getHeight() / 2)));
views.get("lbl5").vw.setLeft((int)((2d / 100 * width)));
views.get("lbl5").vw.setWidth((int)((views.get("pnlcpm").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lbl5").vw.setTop((int)((views.get("img5").vw.getTop() + views.get("img5").vw.getHeight())+(5d * scale)));
views.get("pnlstatus").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlstatus").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlstatus").vw.setTop((int)((views.get("pnlnonop").vw.getTop() + views.get("pnlnonop").vw.getHeight())+(10d * scale)));
views.get("pnlstatus").vw.setHeight((int)((views.get("pnlmain").vw.getTop() + views.get("pnlmain").vw.getHeight())-(0.25d / 100 * height) - ((views.get("pnlnonop").vw.getTop() + views.get("pnlnonop").vw.getHeight())+(10d * scale))));
views.get("lbltrandate").vw.setLeft((int)((2d / 100 * width)));
views.get("lbltrandate").vw.setWidth((int)((views.get("pnlstatus").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lbltrandate").vw.setTop((int)((0.5d / 100 * height)));
views.get("lbltrandate").vw.setHeight((int)((views.get("pnlstatus").vw.getHeight())-(8d / 100 * height) - ((0.5d / 100 * height))));
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
views.get("clvpumplist").vw.setHeight((int)((views.get("pnlanchor").vw.getHeight())-(10d / 100 * height) - ((views.get("lblmessage").vw.getTop() + views.get("lblmessage").vw.getHeight())+(10d * scale))));
views.get("clvpumplist").vw.setLeft((int)((4d / 100 * width)));
views.get("clvpumplist").vw.setWidth((int)((views.get("pnlanchor").vw.getWidth())-(4d / 100 * width) - ((4d / 100 * width))));
views.get("btncancel").vw.setLeft((int)((views.get("clvpumplist").vw.getLeft())));
views.get("btncancel").vw.setWidth((int)((views.get("pnlanchor").vw.getWidth())-(60d / 100 * width) - ((views.get("clvpumplist").vw.getLeft()))));
views.get("btncancel").vw.setTop((int)((views.get("clvpumplist").vw.getTop() + views.get("clvpumplist").vw.getHeight())+(15d * scale)));
views.get("btncancel").vw.setHeight((int)((views.get("pnlanchor").vw.getHeight())-(2d / 100 * height) - ((views.get("clvpumplist").vw.getTop() + views.get("clvpumplist").vw.getHeight())+(15d * scale))));

}
}