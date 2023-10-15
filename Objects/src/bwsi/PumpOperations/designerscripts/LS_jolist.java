package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_jolist{

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
views.get("pnlsearch").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlsearch").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlsearch").vw.setTop((int)((1d / 100 * height)));
views.get("pnlsearch").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(85d / 100 * height) - ((1d / 100 * height))));
views.get("lblsearchicon").vw.setLeft((int)((1d / 100 * width)));
views.get("lblsearchicon").vw.setTop((int)((0.5d / 100 * height)));
views.get("lblsearchicon").vw.setHeight((int)((views.get("pnlsearch").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("txtsearch").vw.setLeft((int)((views.get("lblsearchicon").vw.getLeft() + views.get("lblsearchicon").vw.getWidth())));
views.get("txtsearch").vw.setWidth((int)((views.get("pnlsearch").vw.getWidth())-(1d / 100 * width) - ((views.get("lblsearchicon").vw.getLeft() + views.get("lblsearchicon").vw.getWidth()))));
views.get("txtsearch").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtsearch").vw.setHeight((int)((views.get("pnlsearch").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("pnllist").vw.setTop((int)((views.get("pnlsearch").vw.getTop() + views.get("pnlsearch").vw.getHeight())+(5d * scale)));
views.get("pnllist").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(8d / 100 * height) - ((views.get("pnlsearch").vw.getTop() + views.get("pnlsearch").vw.getHeight())+(5d * scale))));
views.get("pnllist").vw.setLeft((int)((2d / 100 * width)));
views.get("pnllist").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("clvjos").vw.setLeft((int)((1d / 100 * width)));
views.get("clvjos").vw.setWidth((int)((views.get("pnllist").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("clvjos").vw.setTop((int)((5d * scale)));
views.get("clvjos").vw.setHeight((int)((views.get("pnllist").vw.getHeight())-(5d * scale) - ((5d * scale))));
views.get("pnlstatus").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlstatus").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlstatus").vw.setTop((int)((views.get("pnllist").vw.getTop() + views.get("pnllist").vw.getHeight())+(12d * scale)));
views.get("pnlstatus").vw.setHeight((int)((views.get("pnlmain").vw.getTop() + views.get("pnlmain").vw.getHeight())-(0.25d / 100 * height) - ((views.get("pnllist").vw.getTop() + views.get("pnllist").vw.getHeight())+(12d * scale))));
views.get("lblcount").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcount").vw.setWidth((int)((98d / 100 * width) - ((2d / 100 * width))));
views.get("lblcount").vw.setLeft((int)((50d / 100 * width) - (views.get("lblcount").vw.getWidth() / 2)));
views.get("lblcount").vw.setTop((int)((0.5d / 100 * height)));
views.get("lblcount").vw.setHeight((int)((views.get("pnlstatus").vw.getHeight())-(8d / 100 * height) - ((0.5d / 100 * height))));
views.get("pnlsasdetails").vw.setTop((int)((0d / 100 * width)));
views.get("pnlsasdetails").vw.setHeight((int)((100d / 100 * height) - ((0d / 100 * width))));
views.get("pnlsasdetails").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlsasdetails").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlsasmsgbox").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlsasmsgbox").vw.setWidth((int)((views.get("pnlsasdetails").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlsasmsgbox").vw.setTop((int)((20d / 100 * height)));
views.get("pnlsasmsgbox").vw.setHeight((int)((views.get("pnlsasdetails").vw.getHeight())-(25d / 100 * height) - ((20d / 100 * height))));
views.get("lblsasmsgboxtitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblsasmsgboxtitle").vw.setWidth((int)((views.get("pnlsasmsgbox").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblsasmsgboxtitle").vw.setTop((int)((0d / 100 * height)));
views.get("lblsasinfoicon").vw.setTop((int)((0d / 100 * height)));
views.get("lblsasinfoicon").vw.setLeft((int)((0d / 100 * width)));
views.get("label1").vw.setLeft((int)((1d / 100 * width)));
views.get("label1").vw.setTop((int)((views.get("lblsasmsgboxtitle").vw.getTop() + views.get("lblsasmsgboxtitle").vw.getHeight())+(5d * scale)));
views.get("lblsasjono").vw.setLeft((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(5d * scale)));
views.get("lblsasjono").vw.setWidth((int)((views.get("pnlsasmsgbox").vw.getWidth())-(1d / 100 * width) - ((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(5d * scale))));
views.get("lblsasjono").vw.setTop((int)((views.get("label1").vw.getTop())));
views.get("label2").vw.setLeft((int)((1d / 100 * width)));
views.get("label2").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())+(2d * scale)));
views.get("lbllsasappno").vw.setLeft((int)((views.get("label2").vw.getLeft() + views.get("label2").vw.getWidth())+(5d * scale)));
views.get("lbllsasappno").vw.setWidth((int)((views.get("pnlsasmsgbox").vw.getWidth())-(1d / 100 * width) - ((views.get("label2").vw.getLeft() + views.get("label2").vw.getWidth())+(5d * scale))));
views.get("lbllsasappno").vw.setTop((int)((views.get("label2").vw.getTop())));
views.get("label3").vw.setLeft((int)((1d / 100 * width)));
views.get("label3").vw.setTop((int)((views.get("label2").vw.getTop() + views.get("label2").vw.getHeight())+(2d * scale)));
views.get("lblsascustname").vw.setLeft((int)((views.get("label3").vw.getLeft() + views.get("label3").vw.getWidth())+(5d * scale)));
views.get("lblsascustname").vw.setWidth((int)((views.get("pnlsasmsgbox").vw.getWidth())-(1d / 100 * width) - ((views.get("label3").vw.getLeft() + views.get("label3").vw.getWidth())+(5d * scale))));
views.get("lblsascustname").vw.setTop((int)((views.get("label3").vw.getTop())));
views.get("lblsascustaddress").vw.setLeft((int)((2d / 100 * width)));
views.get("lblsascustaddress").vw.setWidth((int)((views.get("pnlsasmsgbox").vw.getWidth())-(1d / 100 * width) - ((2d / 100 * width))));
views.get("lblsascustaddress").vw.setTop((int)((views.get("label3").vw.getTop() + views.get("label3").vw.getHeight())+(2d * scale)));
views.get("lblsasfindings").vw.setLeft((int)((0d / 100 * width)));
views.get("lblsasfindings").vw.setWidth((int)((views.get("pnlsasmsgbox").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblsasfindings").vw.setTop((int)((views.get("lblsascustaddress").vw.getTop() + views.get("lblsascustaddress").vw.getHeight())+(5d * scale)));
views.get("label4").vw.setLeft((int)((1d / 100 * width)));
views.get("label4").vw.setTop((int)((views.get("lblsasfindings").vw.getTop() + views.get("lblsasfindings").vw.getHeight())+(10d * scale)));
views.get("lblsasacctclass").vw.setLeft((int)((views.get("label4").vw.getLeft() + views.get("label4").vw.getWidth())+(5d * scale)));
views.get("lblsasacctclass").vw.setWidth((int)((views.get("pnlsasmsgbox").vw.getWidth())-(1d / 100 * width) - ((views.get("label4").vw.getLeft() + views.get("label4").vw.getWidth())+(5d * scale))));
views.get("lblsasacctclass").vw.setTop((int)((views.get("label4").vw.getTop())));
views.get("label5").vw.setLeft((int)((1d / 100 * width)));
views.get("label5").vw.setTop((int)((views.get("label4").vw.getTop() + views.get("label4").vw.getHeight())+(2d * scale)));
views.get("lblsascontype").vw.setLeft((int)((views.get("label5").vw.getLeft() + views.get("label5").vw.getWidth())+(5d * scale)));
views.get("lblsascontype").vw.setWidth((int)((views.get("pnlsasmsgbox").vw.getWidth())-(1d / 100 * width) - ((views.get("label5").vw.getLeft() + views.get("label5").vw.getWidth())+(5d * scale))));
views.get("lblsascontype").vw.setTop((int)((views.get("label5").vw.getTop())));
views.get("label6").vw.setLeft((int)((1d / 100 * width)));
views.get("label6").vw.setTop((int)((views.get("label5").vw.getTop() + views.get("label5").vw.getHeight())+(2d * scale)));
views.get("lblsasdatesstart").vw.setLeft((int)((views.get("label6").vw.getLeft() + views.get("label6").vw.getWidth())+(5d * scale)));
views.get("lblsasdatesstart").vw.setWidth((int)((views.get("pnlsasmsgbox").vw.getWidth())-(1d / 100 * width) - ((views.get("label6").vw.getLeft() + views.get("label6").vw.getWidth())+(5d * scale))));
views.get("lblsasdatesstart").vw.setTop((int)((views.get("label6").vw.getTop())));
views.get("label7").vw.setLeft((int)((1d / 100 * width)));
views.get("label7").vw.setTop((int)((views.get("label6").vw.getTop() + views.get("label6").vw.getHeight())+(2d * scale)));
views.get("lblsasdateaccomplished").vw.setLeft((int)((views.get("label7").vw.getLeft() + views.get("label7").vw.getWidth())+(5d * scale)));
views.get("lblsasdateaccomplished").vw.setWidth((int)((views.get("pnlsasmsgbox").vw.getWidth())-(1d / 100 * width) - ((views.get("label7").vw.getLeft() + views.get("label7").vw.getWidth())+(5d * scale))));
views.get("lblsasdateaccomplished").vw.setTop((int)((views.get("label7").vw.getTop())));
views.get("btnsasedit").vw.setLeft((int)((3d / 100 * width)));
views.get("btnsasedit").vw.setWidth((int)((views.get("pnlsasmsgbox").vw.getWidth())-(67d / 100 * width) - ((3d / 100 * width))));
views.get("btnsasedit").vw.setTop((int)((views.get("lblsasdateaccomplished").vw.getTop() + views.get("lblsasdateaccomplished").vw.getHeight())+(10d * scale)));
views.get("btnsasedit").vw.setHeight((int)((views.get("pnlsasmsgbox").vw.getHeight())-(1d / 100 * height) - ((views.get("lblsasdateaccomplished").vw.getTop() + views.get("lblsasdateaccomplished").vw.getHeight())+(10d * scale))));
views.get("btnsasok").vw.setLeft((int)((67d / 100 * width)));
views.get("btnsasok").vw.setWidth((int)((views.get("pnlsasmsgbox").vw.getWidth())-(3d / 100 * width) - ((67d / 100 * width))));
views.get("btnsasok").vw.setTop((int)((views.get("lblsasdateaccomplished").vw.getTop() + views.get("lblsasdateaccomplished").vw.getHeight())+(10d * scale)));
views.get("btnsasok").vw.setHeight((int)((views.get("pnlsasmsgbox").vw.getHeight())-(1d / 100 * height) - ((views.get("lblsasdateaccomplished").vw.getTop() + views.get("lblsasdateaccomplished").vw.getHeight())+(10d * scale))));

}
}