package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_jodetails{

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
views.get("pnlmain").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlmain").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("lbljodesc").vw.setTop((int)((1d / 100 * height)));
views.get("lbljodesc").vw.setLeft((int)((2d / 100 * width)));
views.get("lbljodesc").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblcaption1").vw.setTop((int)((views.get("lbljodesc").vw.getTop() + views.get("lbljodesc").vw.getHeight())));
views.get("lblcaption1").vw.setLeft((int)((2d / 100 * width)));
views.get("lbljono").vw.setTop((int)((views.get("lbljodesc").vw.getTop() + views.get("lbljodesc").vw.getHeight())));
views.get("lbljono").vw.setLeft((int)((views.get("lblcaption1").vw.getLeft() + views.get("lblcaption1").vw.getWidth())+(5d * scale)));
views.get("lbljono").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption1").vw.getLeft() + views.get("lblcaption1").vw.getWidth())+(5d * scale))));
views.get("lblappacctno").vw.setTop((int)((views.get("lblcaption1").vw.getTop() + views.get("lblcaption1").vw.getHeight())));
views.get("lblappacctno").vw.setLeft((int)((2d / 100 * width)));
views.get("lblrefno").vw.setTop((int)((views.get("lblappacctno").vw.getTop())));
views.get("lblrefno").vw.setLeft((int)((views.get("lblappacctno").vw.getLeft() + views.get("lblappacctno").vw.getWidth())+(5d * scale)));
views.get("lblrefno").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((views.get("lblappacctno").vw.getLeft() + views.get("lblappacctno").vw.getWidth())+(5d * scale))));
views.get("lblcaption2").vw.setTop((int)((views.get("lblappacctno").vw.getTop() + views.get("lblappacctno").vw.getHeight())));
views.get("lblcaption2").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcustname").vw.setTop((int)((views.get("lblcaption2").vw.getTop())));
views.get("lblcustname").vw.setLeft((int)((views.get("lblcaption2").vw.getLeft() + views.get("lblcaption2").vw.getWidth())+(5d * scale)));
views.get("lblcustname").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption2").vw.getLeft() + views.get("lblcaption2").vw.getWidth())+(5d * scale))));
views.get("lblcustaddress").vw.setTop((int)((views.get("lblcaption2").vw.getTop() + views.get("lblcaption2").vw.getHeight())));
views.get("lblcustaddress").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcustaddress").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblcaption3").vw.setTop((int)((views.get("lblcustaddress").vw.getTop() + views.get("lblcustaddress").vw.getHeight())));
views.get("lblcaption3").vw.setLeft((int)((2d / 100 * width)));
views.get("lblacctclass").vw.setTop((int)((views.get("lblcaption3").vw.getTop())));
views.get("lblacctclass").vw.setLeft((int)((views.get("lblcaption3").vw.getLeft() + views.get("lblcaption3").vw.getWidth())+(5d * scale)));
views.get("lblacctclass").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption3").vw.getLeft() + views.get("lblcaption3").vw.getWidth())+(5d * scale))));
views.get("lblcaption4").vw.setTop((int)((views.get("lblcaption3").vw.getTop() + views.get("lblcaption3").vw.getHeight())));
views.get("lblcaption4").vw.setLeft((int)((2d / 100 * width)));
views.get("lblmeterno").vw.setTop((int)((views.get("lblcaption4").vw.getTop())));
views.get("lblmeterno").vw.setLeft((int)((views.get("lblcaption4").vw.getLeft() + views.get("lblcaption4").vw.getWidth())+(5d * scale)));
views.get("lblmeterno").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption4").vw.getLeft() + views.get("lblcaption4").vw.getWidth())+(5d * scale))));
views.get("lblcaption5").vw.setTop((int)((views.get("lblcaption4").vw.getTop() + views.get("lblcaption4").vw.getHeight())));
views.get("lblcaption5").vw.setLeft((int)((2d / 100 * width)));
views.get("lblbookno").vw.setTop((int)((views.get("lblcaption5").vw.getTop())));
views.get("lblbookno").vw.setLeft((int)((views.get("lblcaption5").vw.getLeft() + views.get("lblcaption5").vw.getWidth())+(5d * scale)));
views.get("lblbookno").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(50d / 100 * width) - ((views.get("lblcaption5").vw.getLeft() + views.get("lblcaption5").vw.getWidth())+(5d * scale))));
views.get("lblcaption6").vw.setTop((int)((views.get("lblcaption4").vw.getTop() + views.get("lblcaption4").vw.getHeight())));
views.get("lblcaption6").vw.setLeft((int)((views.get("lblbookno").vw.getLeft() + views.get("lblbookno").vw.getWidth())+(5d * scale)));
views.get("lblcaption6").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(30d / 100 * width) - ((views.get("lblbookno").vw.getLeft() + views.get("lblbookno").vw.getWidth())+(5d * scale))));
views.get("lblseqno").vw.setTop((int)((views.get("lblcaption6").vw.getTop())));
views.get("lblseqno").vw.setLeft((int)((views.get("lblcaption6").vw.getLeft() + views.get("lblcaption6").vw.getWidth())+(5d * scale)));
views.get("lblseqno").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption6").vw.getLeft() + views.get("lblcaption6").vw.getWidth())+(5d * scale))));
views.get("lblcaption7").vw.setTop((int)((views.get("lblcaption5").vw.getTop() + views.get("lblcaption5").vw.getHeight())));
views.get("lblcaption7").vw.setLeft((int)((2d / 100 * width)));
views.get("lblnote").vw.setTop((int)((views.get("lblcaption7").vw.getTop())));
views.get("lblnote").vw.setLeft((int)((views.get("lblcaption7").vw.getLeft() + views.get("lblcaption7").vw.getWidth())+(5d * scale)));
views.get("lblnote").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption7").vw.getLeft() + views.get("lblcaption7").vw.getWidth())+(5d * scale))));
views.get("btncanceljo").vw.setTop((int)((83d / 100 * height)));
views.get("btncanceljo").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(2d / 100 * height) - ((83d / 100 * height))));
views.get("btnstartjo").vw.setTop((int)((83d / 100 * height)));
views.get("btnstartjo").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(2d / 100 * height) - ((83d / 100 * height))));
views.get("btnok").vw.setTop((int)((83d / 100 * height)));
views.get("btnok").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(2d / 100 * height) - ((83d / 100 * height))));
views.get("btncanceljo").vw.setLeft((int)((4d / 100 * width)));
views.get("btncanceljo").vw.setWidth((int)((34d / 100 * width) - ((4d / 100 * width))));
views.get("btnstartjo").vw.setLeft((int)((35d / 100 * width)));
views.get("btnstartjo").vw.setWidth((int)((65d / 100 * width) - ((35d / 100 * width))));
views.get("btnok").vw.setLeft((int)((66d / 100 * width)));
views.get("btnok").vw.setWidth((int)((96d / 100 * width) - ((66d / 100 * width))));

}
}