package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_cmjofindings{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("pnlmain").vw.setLeft((int)(0d));
views.get("pnlmain").vw.setTop((int)((0.5d / 100 * height)));
views.get("pnlmain").vw.setHeight((int)((220d / 100 * height) - ((0.5d / 100 * height))));
views.get("pnlmain").vw.setWidth((int)((100d / 100 * width)));
views.get("pnljodetails").vw.setTop((int)((1d / 100 * height)));
views.get("pnljodetails").vw.setHeight((int)((51d / 100 * height)));
views.get("pnljodetails").vw.setLeft((int)((1d / 100 * width)));
views.get("pnljodetails").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("lbljodetailstitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lbljodetailstitle").vw.setWidth((int)((views.get("pnljodetails").vw.getWidth()) - ((0d / 100 * width))));
views.get("lbljodetailstitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lbljodetailstitle").vw.setWidth((int)((views.get("pnljodetails").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblcaption1").vw.setTop((int)((views.get("lbljodetailstitle").vw.getTop() + views.get("lbljodetailstitle").vw.getHeight())+(5d * scale)));
views.get("pnljodetstyle1").vw.setTop((int)((views.get("lbljodetailstitle").vw.getTop() + views.get("lbljodetailstitle").vw.getHeight())+(5d * scale)));
views.get("pnljodetstyle1").vw.setLeft((int)((views.get("lblcaption1").vw.getLeft() + views.get("lblcaption1").vw.getWidth())+(2d * scale)));
views.get("pnljodetstyle1").vw.setWidth((int)((views.get("pnljodetails").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption1").vw.getLeft() + views.get("lblcaption1").vw.getWidth())+(2d * scale))));
views.get("lbljonum").vw.setLeft((int)((2d / 100 * width)));
views.get("lbljonum").vw.setWidth((int)((views.get("pnljodetstyle1").vw.getWidth())-(1d / 100 * width) - ((2d / 100 * width))));
views.get("lbljonum").vw.setTop((int)((0.25d / 100 * height)));
views.get("lbljonum").vw.setHeight((int)((views.get("pnljodetstyle1").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("pnljodetstyle2").vw.setTop((int)((views.get("pnljodetstyle1").vw.getTop() + views.get("pnljodetstyle1").vw.getHeight())+(1d * scale)));
views.get("pnljodetstyle2").vw.setLeft((int)((views.get("lblcaption1").vw.getLeft() + views.get("lblcaption1").vw.getWidth())+(2d * scale)));
views.get("pnljodetstyle2").vw.setWidth((int)((views.get("pnljodetails").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption1").vw.getLeft() + views.get("lblcaption1").vw.getWidth())+(2d * scale))));
views.get("lbljocat").vw.setLeft((int)((2d / 100 * width)));
views.get("lbljocat").vw.setWidth((int)((views.get("pnljodetstyle2").vw.getWidth())-(1d / 100 * width) - ((2d / 100 * width))));
views.get("lbljocat").vw.setTop((int)((0.25d / 100 * height)));
views.get("lbljocat").vw.setHeight((int)((views.get("pnljodetstyle2").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("lblcaption2").vw.setTop((int)((views.get("pnljodetstyle2").vw.getTop() + views.get("pnljodetstyle2").vw.getHeight())+(3d * scale)));
views.get("pnljodetstyle3").vw.setTop((int)((views.get("pnljodetstyle2").vw.getTop() + views.get("pnljodetstyle2").vw.getHeight())+(3d * scale)));
views.get("pnljodetstyle3").vw.setLeft((int)((views.get("lblcaption2").vw.getLeft() + views.get("lblcaption2").vw.getWidth())+(2d * scale)));
views.get("pnljodetstyle3").vw.setWidth((int)((views.get("pnljodetails").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption2").vw.getLeft() + views.get("lblcaption2").vw.getWidth())+(2d * scale))));
views.get("lblappnum").vw.setLeft((int)((2d / 100 * width)));
views.get("lblappnum").vw.setWidth((int)((views.get("pnljodetstyle3").vw.getWidth())-(1d / 100 * width) - ((2d / 100 * width))));
views.get("lblappnum").vw.setTop((int)((0.25d / 100 * height)));
views.get("lblappnum").vw.setHeight((int)((views.get("pnljodetstyle3").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("lblcaption3").vw.setTop((int)((views.get("pnljodetstyle3").vw.getTop() + views.get("pnljodetstyle3").vw.getHeight())+(3d * scale)));
views.get("pnljodetstyle4").vw.setTop((int)((views.get("pnljodetstyle3").vw.getTop() + views.get("pnljodetstyle3").vw.getHeight())+(3d * scale)));
views.get("pnljodetstyle4").vw.setLeft((int)((views.get("lblcaption3").vw.getLeft() + views.get("lblcaption3").vw.getWidth())+(2d * scale)));
views.get("pnljodetstyle4").vw.setWidth((int)((views.get("pnljodetails").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption3").vw.getLeft() + views.get("lblcaption3").vw.getWidth())+(2d * scale))));
views.get("lblcustname").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcustname").vw.setWidth((int)((views.get("pnljodetstyle4").vw.getWidth())-(1d / 100 * width) - ((2d / 100 * width))));
views.get("lblcustname").vw.setTop((int)((0.25d / 100 * height)));
views.get("lblcustname").vw.setHeight((int)((views.get("pnljodetstyle4").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("pnljodetstyle5").vw.setTop((int)((views.get("pnljodetstyle4").vw.getTop() + views.get("pnljodetstyle4").vw.getHeight())+(3d * scale)));
views.get("pnljodetstyle5").vw.setLeft((int)((views.get("lblcaption1").vw.getLeft())));
views.get("pnljodetstyle5").vw.setWidth((int)((views.get("pnljodetails").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption1").vw.getLeft()))));
views.get("lblcustaddress").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcustaddress").vw.setWidth((int)((views.get("pnljodetstyle5").vw.getWidth())-(1d / 100 * width) - ((2d / 100 * width))));
views.get("lblcustaddress").vw.setTop((int)((0.25d / 100 * height)));
views.get("lblcustaddress").vw.setHeight((int)((views.get("pnljodetstyle5").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("lblcaption4").vw.setTop((int)((views.get("pnljodetstyle5").vw.getTop() + views.get("pnljodetstyle5").vw.getHeight())+(3d * scale)));
views.get("pnljodetstyle6").vw.setTop((int)((views.get("pnljodetstyle5").vw.getTop() + views.get("pnljodetstyle5").vw.getHeight())+(3d * scale)));
views.get("pnljodetstyle6").vw.setLeft((int)((views.get("lblcaption4").vw.getLeft() + views.get("lblcaption4").vw.getWidth())+(2d * scale)));
views.get("pnljodetstyle6").vw.setWidth((int)((views.get("pnljodetails").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption4").vw.getLeft() + views.get("lblcaption4").vw.getWidth())+(2d * scale))));
views.get("lblacctclass").vw.setLeft((int)((2d / 100 * width)));
views.get("lblacctclass").vw.setWidth((int)((views.get("pnljodetstyle6").vw.getWidth())-(1d / 100 * width) - ((2d / 100 * width))));
views.get("lblacctclass").vw.setTop((int)((0.25d / 100 * height)));
views.get("lblacctclass").vw.setHeight((int)((views.get("pnljodetstyle6").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("pnlfindings").vw.setTop((int)((views.get("pnljodetails").vw.getTop() + views.get("pnljodetails").vw.getHeight())+(5d * scale)));
views.get("pnlfindings").vw.setLeft((int)((1d / 100 * width)));
views.get("pnlfindings").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlfindings").vw.setHeight((int)((68d / 100 * height)));
views.get("lblfindingstitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblfindingstitle").vw.setWidth((int)((views.get("pnlfindings").vw.getWidth()) - ((0d / 100 * width))));
views.get("label1").vw.setTop((int)((views.get("lblfindingstitle").vw.getTop() + views.get("lblfindingstitle").vw.getHeight())+(2d * scale)));
views.get("label1").vw.setLeft((int)((2d / 100 * width)));
views.get("pnloldmeter").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight()/2)));
views.get("pnloldmeter").vw.setHeight((int)((views.get("pnlfindings").vw.getHeight())-(27d / 100 * height) - ((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight()/2))));
views.get("pnloldmeter").vw.setLeft((int)((2d / 100 * width)));
views.get("pnloldmeter").vw.setWidth((int)((views.get("pnlfindings").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblcaption5").vw.setTop((int)((3d / 100 * height)));
views.get("lblcaption5").vw.setLeft((int)((3d / 100 * width)));
views.get("pnloldmeterbrandanchor").vw.setTop((int)((views.get("lblcaption5").vw.getTop())));
views.get("pnloldmeterbrandanchor").vw.setLeft((int)((views.get("lblcaption5").vw.getLeft() + views.get("lblcaption5").vw.getWidth())+(5d * scale)));
views.get("pnloldmeterbrandanchor").vw.setWidth((int)((views.get("pnloldmeter").vw.getWidth())-(52d / 100 * width) - ((views.get("lblcaption5").vw.getLeft() + views.get("lblcaption5").vw.getWidth())+(5d * scale))));
views.get("txtoldmeterbrand").vw.setLeft((int)((1d / 100 * width)));
views.get("txtoldmeterbrand").vw.setWidth((int)((views.get("pnloldmeterbrandanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtoldmeterbrand").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtoldmeterbrand").vw.setHeight((int)((views.get("pnloldmeterbrandanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("lblcaption6").vw.setLeft((int)((views.get("pnloldmeterbrandanchor").vw.getLeft() + views.get("pnloldmeterbrandanchor").vw.getWidth())+(10d * scale)));
views.get("lblcaption6").vw.setTop((int)((views.get("lblcaption5").vw.getTop())));
views.get("pnloldmeternoanchor").vw.setTop((int)((views.get("lblcaption6").vw.getTop())));
views.get("pnloldmeternoanchor").vw.setLeft((int)((views.get("lblcaption6").vw.getLeft() + views.get("lblcaption6").vw.getWidth())+(5d * scale)));
views.get("pnloldmeternoanchor").vw.setWidth((int)((views.get("pnloldmeter").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption6").vw.getLeft() + views.get("lblcaption6").vw.getWidth())+(5d * scale))));
views.get("txtoldmeterno").vw.setLeft((int)((2d / 100 * width)));
views.get("txtoldmeterno").vw.setWidth((int)((views.get("pnloldmeternoanchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtoldmeterno").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtoldmeterno").vw.setHeight((int)((views.get("pnloldmeternoanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("lblcaption7").vw.setLeft((int)((views.get("lblcaption5").vw.getLeft() + views.get("lblcaption5").vw.getWidth()) - (views.get("lblcaption7").vw.getWidth())));
views.get("lblcaption7").vw.setTop((int)((views.get("lblcaption5").vw.getTop() + views.get("lblcaption5").vw.getHeight())+(5d * scale)));
views.get("pnlprevrdganchor").vw.setTop((int)((views.get("lblcaption7").vw.getTop())));
views.get("pnlprevrdganchor").vw.setLeft((int)((views.get("lblcaption7").vw.getLeft() + views.get("lblcaption7").vw.getWidth())+(5d * scale)));
views.get("pnlprevrdganchor").vw.setWidth((int)((views.get("pnloldmeter").vw.getWidth())-(52d / 100 * width) - ((views.get("lblcaption7").vw.getLeft() + views.get("lblcaption7").vw.getWidth())+(5d * scale))));
views.get("txtprevrdg").vw.setLeft((int)((2d / 100 * width)));
views.get("txtprevrdg").vw.setWidth((int)((views.get("pnlprevrdganchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtprevrdg").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtprevrdg").vw.setHeight((int)((views.get("pnlprevrdganchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("lblcaption8").vw.setLeft((int)((views.get("lblcaption6").vw.getLeft() + views.get("lblcaption6").vw.getWidth()) - (views.get("lblcaption8").vw.getWidth())));
views.get("lblcaption8").vw.setTop((int)((views.get("lblcaption7").vw.getTop())));
views.get("pnllatestrdganchor").vw.setTop((int)((views.get("lblcaption8").vw.getTop())));
views.get("pnllatestrdganchor").vw.setLeft((int)((views.get("lblcaption8").vw.getLeft() + views.get("lblcaption8").vw.getWidth())+(5d * scale)));
views.get("pnllatestrdganchor").vw.setWidth((int)((views.get("pnloldmeter").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption8").vw.getLeft() + views.get("lblcaption8").vw.getWidth())+(5d * scale))));
views.get("txtlatestrdg").vw.setLeft((int)((2d / 100 * width)));
views.get("txtlatestrdg").vw.setWidth((int)((views.get("pnllatestrdganchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtlatestrdg").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtlatestrdg").vw.setHeight((int)((views.get("pnllatestrdganchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("lblcaption9").vw.setLeft((int)((views.get("lblcaption6").vw.getLeft() + views.get("lblcaption6").vw.getWidth()) - (views.get("lblcaption9").vw.getWidth())));
views.get("lblcaption9").vw.setTop((int)((views.get("lblcaption8").vw.getTop() + views.get("lblcaption8").vw.getHeight())+(5d * scale)));
views.get("pnladdconsanchor").vw.setTop((int)((views.get("lblcaption9").vw.getTop())));
views.get("pnladdconsanchor").vw.setLeft((int)((views.get("lblcaption9").vw.getLeft() + views.get("lblcaption9").vw.getWidth())+(5d * scale)));
views.get("pnladdconsanchor").vw.setWidth((int)((views.get("pnloldmeter").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption9").vw.getLeft() + views.get("lblcaption9").vw.getWidth())+(5d * scale))));
views.get("txtaddcons").vw.setLeft((int)((2d / 100 * width)));
views.get("txtaddcons").vw.setWidth((int)((views.get("pnladdconsanchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtaddcons").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtaddcons").vw.setHeight((int)((views.get("pnladdconsanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("label7").vw.setTop((int)((views.get("pnloldmeter").vw.getTop() + views.get("pnloldmeter").vw.getHeight())+(2d * scale)));
views.get("label7").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlnewmeter").vw.setLeft((int)((views.get("pnloldmeter").vw.getLeft())));
views.get("pnlnewmeter").vw.setWidth((int)((views.get("pnlfindings").vw.getWidth())-(2d / 100 * width) - ((views.get("pnloldmeter").vw.getLeft()))));
views.get("pnlnewmeter").vw.setTop((int)((views.get("label7").vw.getTop() + views.get("label7").vw.getHeight()/2)));
views.get("pnlnewmeter").vw.setHeight((int)((views.get("pnlfindings").vw.getHeight())-(2d / 100 * height) - ((views.get("label7").vw.getTop() + views.get("label7").vw.getHeight()/2))));
views.get("label2").vw.setTop((int)((3d / 100 * height)));
views.get("label2").vw.setLeft((int)((3d / 100 * width)));
views.get("pnlnewmeterbrandanchor").vw.setTop((int)((views.get("label2").vw.getTop())));
views.get("pnlnewmeterbrandanchor").vw.setLeft((int)((views.get("lblcaption5").vw.getLeft() + views.get("lblcaption5").vw.getWidth())+(5d * scale)));
views.get("pnlnewmeterbrandanchor").vw.setWidth((int)((views.get("pnlnewmeter").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption5").vw.getLeft() + views.get("lblcaption5").vw.getWidth())+(5d * scale))));
views.get("cbometerbrand").vw.setLeft((int)((1d / 100 * width)));
views.get("cbometerbrand").vw.setWidth((int)((views.get("pnlnewmeterbrandanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("cbometerbrand").vw.setTop((int)((0.5d / 100 * height)));
views.get("cbometerbrand").vw.setHeight((int)((views.get("pnlnewmeterbrandanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("label3").vw.setLeft((int)((views.get("label2").vw.getLeft() + views.get("label2").vw.getWidth()) - (views.get("label3").vw.getWidth())));
views.get("label3").vw.setTop((int)((views.get("label2").vw.getTop() + views.get("label2").vw.getHeight())+(5d * scale)));
views.get("pnlnewmeteranchor").vw.setTop((int)((views.get("label3").vw.getTop())));
views.get("pnlnewmeteranchor").vw.setLeft((int)((views.get("label3").vw.getLeft() + views.get("label3").vw.getWidth())+(5d * scale)));
views.get("pnlnewmeteranchor").vw.setWidth((int)((views.get("pnlnewmeter").vw.getWidth())-(52d / 100 * width) - ((views.get("label3").vw.getLeft() + views.get("label3").vw.getWidth())+(5d * scale))));
views.get("txtnewmeterno").vw.setLeft((int)((2d / 100 * width)));
views.get("txtnewmeterno").vw.setWidth((int)((views.get("pnlnewmeteranchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtnewmeterno").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtnewmeterno").vw.setHeight((int)((views.get("pnlnewmeteranchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("label4").vw.setLeft((int)((views.get("pnlnewmeteranchor").vw.getLeft() + views.get("pnlnewmeteranchor").vw.getWidth())+(10d * scale)));
views.get("label4").vw.setTop((int)((views.get("label3").vw.getTop())));
views.get("pnlinitrdganchor").vw.setTop((int)((views.get("label4").vw.getTop())));
views.get("pnlinitrdganchor").vw.setLeft((int)((views.get("label4").vw.getLeft() + views.get("label4").vw.getWidth())+(5d * scale)));
views.get("pnlinitrdganchor").vw.setWidth((int)((views.get("pnlnewmeter").vw.getWidth())-(2d / 100 * width) - ((views.get("label4").vw.getLeft() + views.get("label4").vw.getWidth())+(5d * scale))));
views.get("txtinitrdg").vw.setLeft((int)((2d / 100 * width)));
views.get("txtinitrdg").vw.setWidth((int)((views.get("pnlinitrdganchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtinitrdg").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtinitrdg").vw.setHeight((int)((views.get("pnlinitrdganchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("pnlwaterlossfindings").vw.setTop((int)((views.get("pnlfindings").vw.getTop() + views.get("pnlfindings").vw.getHeight())+(5d * scale)));
views.get("pnlwaterlossfindings").vw.setLeft((int)((1d / 100 * width)));
views.get("pnlwaterlossfindings").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlwaterlossfindings").vw.setHeight((int)((55d / 100 * height)));
views.get("lblwaterlossfindings").vw.setLeft((int)((0d / 100 * width)));
views.get("lblwaterlossfindings").vw.setWidth((int)((views.get("pnlwaterlossfindings").vw.getWidth()) - ((0d / 100 * width))));
//BA.debugLineNum = 132;BA.debugLine="lblPipeType.Top = lblWaterLossFindings.Bottom + 5dip"[CMJOFindings/General script]
views.get("lblpipetype").vw.setTop((int)((views.get("lblwaterlossfindings").vw.getTop() + views.get("lblwaterlossfindings").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 133;BA.debugLine="lblPipeType.Left = 2%x"[CMJOFindings/General script]
views.get("lblpipetype").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 134;BA.debugLine="pnlPipeTypeAnchor.Top = lblWaterLossFindings.Bottom + 5dip"[CMJOFindings/General script]
views.get("pnlpipetypeanchor").vw.setTop((int)((views.get("lblwaterlossfindings").vw.getTop() + views.get("lblwaterlossfindings").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 135;BA.debugLine="pnlPipeTypeAnchor.SetLeftAndRight(lblPipeType.Right + 5dip, pnlWaterLossFindings.Width - 2%x)"[CMJOFindings/General script]
views.get("pnlpipetypeanchor").vw.setLeft((int)((views.get("lblpipetype").vw.getLeft() + views.get("lblpipetype").vw.getWidth())+(5d * scale)));
views.get("pnlpipetypeanchor").vw.setWidth((int)((views.get("pnlwaterlossfindings").vw.getWidth())-(2d / 100 * width) - ((views.get("lblpipetype").vw.getLeft() + views.get("lblpipetype").vw.getWidth())+(5d * scale))));
//BA.debugLineNum = 136;BA.debugLine="cboPipeType.SetLeftAndRight(1.75%x, pnlPipeTypeAnchor.Width - 1.75%x)"[CMJOFindings/General script]
views.get("cbopipetype").vw.setLeft((int)((1.75d / 100 * width)));
views.get("cbopipetype").vw.setWidth((int)((views.get("pnlpipetypeanchor").vw.getWidth())-(1.75d / 100 * width) - ((1.75d / 100 * width))));
//BA.debugLineNum = 137;BA.debugLine="cboPipeType.SetTopAndBottom(0.5%y, pnlPipeTypeAnchor.Height - 0.5%y)"[CMJOFindings/General script]
views.get("cbopipetype").vw.setTop((int)((0.5d / 100 * height)));
views.get("cbopipetype").vw.setHeight((int)((views.get("pnlpipetypeanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
//BA.debugLineNum = 139;BA.debugLine="lblPipeSize.Right = lblPipeType.Right"[CMJOFindings/General script]
views.get("lblpipesize").vw.setLeft((int)((views.get("lblpipetype").vw.getLeft() + views.get("lblpipetype").vw.getWidth()) - (views.get("lblpipesize").vw.getWidth())));
//BA.debugLineNum = 140;BA.debugLine="lblPipeSize.Top = lblPipeType.Bottom + 5dip"[CMJOFindings/General script]
views.get("lblpipesize").vw.setTop((int)((views.get("lblpipetype").vw.getTop() + views.get("lblpipetype").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 141;BA.debugLine="pnlPipeSizeAnchor.Top = lblPipeSize.Top"[CMJOFindings/General script]
views.get("pnlpipesizeanchor").vw.setTop((int)((views.get("lblpipesize").vw.getTop())));
//BA.debugLineNum = 142;BA.debugLine="pnlPipeSizeAnchor.SetLeftAndRight(lblPipeSize.Right + 5dip, pnlWaterLossFindings.Width - 2%x)"[CMJOFindings/General script]
views.get("pnlpipesizeanchor").vw.setLeft((int)((views.get("lblpipesize").vw.getLeft() + views.get("lblpipesize").vw.getWidth())+(5d * scale)));
views.get("pnlpipesizeanchor").vw.setWidth((int)((views.get("pnlwaterlossfindings").vw.getWidth())-(2d / 100 * width) - ((views.get("lblpipesize").vw.getLeft() + views.get("lblpipesize").vw.getWidth())+(5d * scale))));
//BA.debugLineNum = 143;BA.debugLine="cboPipeSize.SetLeftAndRight(1%x, pnlPipeSizeAnchor.Width - 1%x)"[CMJOFindings/General script]
views.get("cbopipesize").vw.setLeft((int)((1d / 100 * width)));
views.get("cbopipesize").vw.setWidth((int)((views.get("pnlpipesizeanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 144;BA.debugLine="cboPipeSize.SetTopAndBottom(0.5%y, pnlPipeSizeAnchor.Height - 0.5%y)"[CMJOFindings/General script]
views.get("cbopipesize").vw.setTop((int)((0.5d / 100 * height)));
views.get("cbopipesize").vw.setHeight((int)((views.get("pnlpipesizeanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
//BA.debugLineNum = 146;BA.debugLine="lblPressure.Right = lblPipeSize.Right"[CMJOFindings/General script]
views.get("lblpressure").vw.setLeft((int)((views.get("lblpipesize").vw.getLeft() + views.get("lblpipesize").vw.getWidth()) - (views.get("lblpressure").vw.getWidth())));
//BA.debugLineNum = 147;BA.debugLine="lblPressure.Top = lblPipeSize.Bottom + 5dip"[CMJOFindings/General script]
views.get("lblpressure").vw.setTop((int)((views.get("lblpipesize").vw.getTop() + views.get("lblpipesize").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 148;BA.debugLine="pnlPSIAnchor.Top = lblPressure.Top"[CMJOFindings/General script]
views.get("pnlpsianchor").vw.setTop((int)((views.get("lblpressure").vw.getTop())));
//BA.debugLineNum = 149;BA.debugLine="pnlPSIAnchor.SetLeftAndRight(lblPressure.Right + 5dip, pnlWaterLossFindings.Width - 40%x)"[CMJOFindings/General script]
views.get("pnlpsianchor").vw.setLeft((int)((views.get("lblpressure").vw.getLeft() + views.get("lblpressure").vw.getWidth())+(5d * scale)));
views.get("pnlpsianchor").vw.setWidth((int)((views.get("pnlwaterlossfindings").vw.getWidth())-(40d / 100 * width) - ((views.get("lblpressure").vw.getLeft() + views.get("lblpressure").vw.getWidth())+(5d * scale))));
//BA.debugLineNum = 150;BA.debugLine="txtPSI.SetLeftAndRight(1.75%x, pnlPSIAnchor.Width - 1.75%x)"[CMJOFindings/General script]
views.get("txtpsi").vw.setLeft((int)((1.75d / 100 * width)));
views.get("txtpsi").vw.setWidth((int)((views.get("pnlpsianchor").vw.getWidth())-(1.75d / 100 * width) - ((1.75d / 100 * width))));
//BA.debugLineNum = 151;BA.debugLine="txtPSI.SetTopAndBottom(0.5%y, pnlPSIAnchor.Height - 0.5%y)"[CMJOFindings/General script]
views.get("txtpsi").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtpsi").vw.setHeight((int)((views.get("pnlpsianchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
//BA.debugLineNum = 153;BA.debugLine="lblMinutes.Left = 3%x"[CMJOFindings/General script]
views.get("lblminutes").vw.setLeft((int)((3d / 100 * width)));
//BA.debugLineNum = 154;BA.debugLine="lblMinutes.Top = lblPressure.Bottom + 5dip"[CMJOFindings/General script]
views.get("lblminutes").vw.setTop((int)((views.get("lblpressure").vw.getTop() + views.get("lblpressure").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 155;BA.debugLine="pnlMinutes.Top = lblMinutes.Top"[CMJOFindings/General script]
views.get("pnlminutes").vw.setTop((int)((views.get("lblminutes").vw.getTop())));
//BA.debugLineNum = 156;BA.debugLine="pnlMinutes.SetLeftAndRight(lblMinutes.Right + 5dip, pnlWaterLossFindings.Width - 13%x)"[CMJOFindings/General script]
views.get("pnlminutes").vw.setLeft((int)((views.get("lblminutes").vw.getLeft() + views.get("lblminutes").vw.getWidth())+(5d * scale)));
views.get("pnlminutes").vw.setWidth((int)((views.get("pnlwaterlossfindings").vw.getWidth())-(13d / 100 * width) - ((views.get("lblminutes").vw.getLeft() + views.get("lblminutes").vw.getWidth())+(5d * scale))));
//BA.debugLineNum = 157;BA.debugLine="txtMinutes.SetLeftAndRight(2%x, pnlMinutes.Width - 2%x)"[CMJOFindings/General script]
views.get("txtminutes").vw.setLeft((int)((2d / 100 * width)));
views.get("txtminutes").vw.setWidth((int)((views.get("pnlminutes").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 158;BA.debugLine="txtMinutes.SetTopAndBottom(0.5%y, pnlMinutes.Height - 0.5%y)"[CMJOFindings/General script]
views.get("txtminutes").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtminutes").vw.setHeight((int)((views.get("pnlminutes").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
//BA.debugLineNum = 159;BA.debugLine="lblMins.Left = pnlMinutes.Right + 2dip"[CMJOFindings/General script]
views.get("lblmins").vw.setLeft((int)((views.get("pnlminutes").vw.getLeft() + views.get("pnlminutes").vw.getWidth())+(2d * scale)));
//BA.debugLineNum = 160;BA.debugLine="lblMins.VerticalCenter = lblMinutes.VerticalCenter"[CMJOFindings/General script]
views.get("lblmins").vw.setTop((int)((views.get("lblminutes").vw.getTop() + views.get("lblminutes").vw.getHeight()/2) - (views.get("lblmins").vw.getHeight() / 2)));
//BA.debugLineNum = 162;BA.debugLine="lblWL.Right = lblMinutes.Right"[CMJOFindings/General script]
views.get("lblwl").vw.setLeft((int)((views.get("lblminutes").vw.getLeft() + views.get("lblminutes").vw.getWidth()) - (views.get("lblwl").vw.getWidth())));
//BA.debugLineNum = 163;BA.debugLine="lblWL.Top = lblMinutes.Bottom + 5dip"[CMJOFindings/General script]
views.get("lblwl").vw.setTop((int)((views.get("lblminutes").vw.getTop() + views.get("lblminutes").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 164;BA.debugLine="pnlWaterLossAnchor.Top = lblWL.Top"[CMJOFindings/General script]
views.get("pnlwaterlossanchor").vw.setTop((int)((views.get("lblwl").vw.getTop())));
//BA.debugLineNum = 165;BA.debugLine="pnlWaterLossAnchor.SetLeftAndRight(lblWL.Right + 5dip, pnlWaterLossFindings.Width - 13%x)"[CMJOFindings/General script]
views.get("pnlwaterlossanchor").vw.setLeft((int)((views.get("lblwl").vw.getLeft() + views.get("lblwl").vw.getWidth())+(5d * scale)));
views.get("pnlwaterlossanchor").vw.setWidth((int)((views.get("pnlwaterlossfindings").vw.getWidth())-(13d / 100 * width) - ((views.get("lblwl").vw.getLeft() + views.get("lblwl").vw.getWidth())+(5d * scale))));
//BA.debugLineNum = 166;BA.debugLine="txtWaterLoss.SetLeftAndRight(2%x, pnlWaterLossAnchor.Width - 2%x)"[CMJOFindings/General script]
views.get("txtwaterloss").vw.setLeft((int)((2d / 100 * width)));
views.get("txtwaterloss").vw.setWidth((int)((views.get("pnlwaterlossanchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 167;BA.debugLine="txtWaterLoss.SetTopAndBottom(0.5%y, pnlWaterLossAnchor.Height - 0.5%y)"[CMJOFindings/General script]
views.get("txtwaterloss").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtwaterloss").vw.setHeight((int)((views.get("pnlwaterlossanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
//BA.debugLineNum = 169;BA.debugLine="lblCum.Left = pnlWaterLossAnchor.Right + 2dip"[CMJOFindings/General script]
views.get("lblcum").vw.setLeft((int)((views.get("pnlwaterlossanchor").vw.getLeft() + views.get("pnlwaterlossanchor").vw.getWidth())+(2d * scale)));
//BA.debugLineNum = 170;BA.debugLine="lblCum.Top = lblWL.Top"[CMJOFindings/General script]
views.get("lblcum").vw.setTop((int)((views.get("lblwl").vw.getTop())));
//BA.debugLineNum = 173;BA.debugLine="pnlRemarks.Top = pnlWaterLossFindings.Bottom + 5dip"[CMJOFindings/General script]
views.get("pnlremarks").vw.setTop((int)((views.get("pnlwaterlossfindings").vw.getTop() + views.get("pnlwaterlossfindings").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 174;BA.debugLine="pnlRemarks.SetLeftAndRight(1%x, pnlMain.Width - 1%x)"[CMJOFindings/General script]
views.get("pnlremarks").vw.setLeft((int)((1d / 100 * width)));
views.get("pnlremarks").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 175;BA.debugLine="pnlRemarks.Height = 23%y"[CMJOFindings/General script]
views.get("pnlremarks").vw.setHeight((int)((23d / 100 * height)));
//BA.debugLineNum = 177;BA.debugLine="lblRemarksTitle.SetLeftAndRight(0%x, pnlRemarks.Width)"[CMJOFindings/General script]
views.get("lblremarkstitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblremarkstitle").vw.setWidth((int)((views.get("pnlremarks").vw.getWidth()) - ((0d / 100 * width))));
//BA.debugLineNum = 178;BA.debugLine="lblRemarksTitle.Top = 0%y"[CMJOFindings/General script]
views.get("lblremarkstitle").vw.setTop((int)((0d / 100 * height)));
//BA.debugLineNum = 180;BA.debugLine="pnlRemarksAnchor.SetLeftAndRight(1%x, pnlRemarks.Width - 1%X)"[CMJOFindings/General script]
views.get("pnlremarksanchor").vw.setLeft((int)((1d / 100 * width)));
views.get("pnlremarksanchor").vw.setWidth((int)((views.get("pnlremarks").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 181;BA.debugLine="pnlRemarksAnchor.SetTopAndBottom (lblRemarksTitle.Bottom + 5dip, pnlRemarks.Height - 1%y)"[CMJOFindings/General script]
views.get("pnlremarksanchor").vw.setTop((int)((views.get("lblremarkstitle").vw.getTop() + views.get("lblremarkstitle").vw.getHeight())+(5d * scale)));
views.get("pnlremarksanchor").vw.setHeight((int)((views.get("pnlremarks").vw.getHeight())-(1d / 100 * height) - ((views.get("lblremarkstitle").vw.getTop() + views.get("lblremarkstitle").vw.getHeight())+(5d * scale))));
//BA.debugLineNum = 182;BA.debugLine="txtRemarks.SetLeftAndRight(2%x, pnlRemarksAnchor.Width - 2%x)"[CMJOFindings/General script]
views.get("txtremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("txtremarks").vw.setWidth((int)((views.get("pnlremarksanchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 183;BA.debugLine="txtRemarks.SetTopAndBottom(1%y, pnlRemarksAnchor.Height - 1%y)"[CMJOFindings/General script]
views.get("txtremarks").vw.setTop((int)((1d / 100 * height)));
views.get("txtremarks").vw.setHeight((int)((views.get("pnlremarksanchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
//BA.debugLineNum = 185;BA.debugLine="btnSaveUpdate.SetTopAndBottom(pnlRemarks.Bottom + 10dip, pnlMain.Height - 2%y)"[CMJOFindings/General script]
views.get("btnsaveupdate").vw.setTop((int)((views.get("pnlremarks").vw.getTop() + views.get("pnlremarks").vw.getHeight())+(10d * scale)));
views.get("btnsaveupdate").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(2d / 100 * height) - ((views.get("pnlremarks").vw.getTop() + views.get("pnlremarks").vw.getHeight())+(10d * scale))));
//BA.debugLineNum = 186;BA.debugLine="btnSaveUpdate.SetLeftAndRight(2%x, pnlMain.Width - 2%x)"[CMJOFindings/General script]
views.get("btnsaveupdate").vw.setLeft((int)((2d / 100 * width)));
views.get("btnsaveupdate").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 190;BA.debugLine="pnlSignature.SetLeftAndRight (0%x,100%x)"[CMJOFindings/General script]
views.get("pnlsignature").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlsignature").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
//BA.debugLineNum = 192;BA.debugLine="pnlSignature.Top = -10%y"[CMJOFindings/General script]
views.get("pnlsignature").vw.setTop((int)(0-(10d / 100 * height)));
//BA.debugLineNum = 193;BA.debugLine="pnlSignature.Height = 130%y"[CMJOFindings/General script]
views.get("pnlsignature").vw.setHeight((int)((130d / 100 * height)));
//BA.debugLineNum = 195;BA.debugLine="pnlMainSigBG.SetLeftAndRight(2%x,pnlSignature.Width - 2%x)"[CMJOFindings/General script]
views.get("pnlmainsigbg").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlmainsigbg").vw.setWidth((int)((views.get("pnlsignature").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 196;BA.debugLine="pnlMainSigBG.SetTopAndBottom(20%y, pnlSignature.Height - 50%y)"[CMJOFindings/General script]
views.get("pnlmainsigbg").vw.setTop((int)((20d / 100 * height)));
views.get("pnlmainsigbg").vw.setHeight((int)((views.get("pnlsignature").vw.getHeight())-(50d / 100 * height) - ((20d / 100 * height))));
//BA.debugLineNum = 199;BA.debugLine="lblCustSignature.SetLeftAndRight (0%x, pnlMainSigBG.Width)"[CMJOFindings/General script]
views.get("lblcustsignature").vw.setLeft((int)((0d / 100 * width)));
views.get("lblcustsignature").vw.setWidth((int)((views.get("pnlmainsigbg").vw.getWidth()) - ((0d / 100 * width))));
//BA.debugLineNum = 200;BA.debugLine="lblCustSignature.Top = 0%y"[CMJOFindings/General script]
views.get("lblcustsignature").vw.setTop((int)((0d / 100 * height)));
//BA.debugLineNum = 203;BA.debugLine="pnlSigBG.SetLeftAndRight(3%x, pnlMainSigBG.Width - 3%x)"[CMJOFindings/General script]
views.get("pnlsigbg").vw.setLeft((int)((3d / 100 * width)));
views.get("pnlsigbg").vw.setWidth((int)((views.get("pnlmainsigbg").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
//BA.debugLineNum = 204;BA.debugLine="pnlSigBG.SetTopAndBottom(lblCustSignature.Bottom + 5dip, pnlMainSigBG.Height - 15%y)"[CMJOFindings/General script]
views.get("pnlsigbg").vw.setTop((int)((views.get("lblcustsignature").vw.getTop() + views.get("lblcustsignature").vw.getHeight())+(5d * scale)));
views.get("pnlsigbg").vw.setHeight((int)((views.get("pnlmainsigbg").vw.getHeight())-(15d / 100 * height) - ((views.get("lblcustsignature").vw.getTop() + views.get("lblcustsignature").vw.getHeight())+(5d * scale))));
//BA.debugLineNum = 206;BA.debugLine="btnConfirmSig.SetLeftAndRight(pnlSigBG.Left, pnlSigBG.Right)"[CMJOFindings/General script]
views.get("btnconfirmsig").vw.setLeft((int)((views.get("pnlsigbg").vw.getLeft())));
views.get("btnconfirmsig").vw.setWidth((int)((views.get("pnlsigbg").vw.getLeft() + views.get("pnlsigbg").vw.getWidth()) - ((views.get("pnlsigbg").vw.getLeft()))));
//BA.debugLineNum = 207;BA.debugLine="btnConfirmSig.SetTopAndBottom(pnlSigBG.Bottom + 10dip, pnlMainSigBG.Height - 2%y)"[CMJOFindings/General script]
views.get("btnconfirmsig").vw.setTop((int)((views.get("pnlsigbg").vw.getTop() + views.get("pnlsigbg").vw.getHeight())+(10d * scale)));
views.get("btnconfirmsig").vw.setHeight((int)((views.get("pnlmainsigbg").vw.getHeight())-(2d / 100 * height) - ((views.get("pnlsigbg").vw.getTop() + views.get("pnlsigbg").vw.getHeight())+(10d * scale))));
//BA.debugLineNum = 209;BA.debugLine="SignaturePad.SetLeftAndRight(1.5%x, pnlSigBG.Width - 1.5%x)"[CMJOFindings/General script]
views.get("signaturepad").vw.setLeft((int)((1.5d / 100 * width)));
views.get("signaturepad").vw.setWidth((int)((views.get("pnlsigbg").vw.getWidth())-(1.5d / 100 * width) - ((1.5d / 100 * width))));
//BA.debugLineNum = 210;BA.debugLine="SignaturePad.SetTopAndBottom(1.5%y, pnlSigBG.Height - 1.5%y)"[CMJOFindings/General script]
views.get("signaturepad").vw.setTop((int)((1.5d / 100 * height)));
views.get("signaturepad").vw.setHeight((int)((views.get("pnlsigbg").vw.getHeight())-(1.5d / 100 * height) - ((1.5d / 100 * height))));
//BA.debugLineNum = 214;BA.debugLine="pnlConfirmSig.SetLeftAndRight (0%x,100%x)"[CMJOFindings/General script]
views.get("pnlconfirmsig").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlconfirmsig").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
//BA.debugLineNum = 215;BA.debugLine="pnlConfirmSig.SetTopAndBottom(0%y, 120%y)"[CMJOFindings/General script]
views.get("pnlconfirmsig").vw.setTop((int)((0d / 100 * height)));
views.get("pnlconfirmsig").vw.setHeight((int)((120d / 100 * height) - ((0d / 100 * height))));
//BA.debugLineNum = 217;BA.debugLine="pnlMainSigBG2.SetLeftAndRight(1%x, pnlConfirmSig.Width - 1%x)"[CMJOFindings/General script]
views.get("pnlmainsigbg2").vw.setLeft((int)((1d / 100 * width)));
views.get("pnlmainsigbg2").vw.setWidth((int)((views.get("pnlconfirmsig").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 218;BA.debugLine="pnlMainSigBG2.SetTopAndBottom(10%y, pnlConfirmSig.Height - 15%y)"[CMJOFindings/General script]
views.get("pnlmainsigbg2").vw.setTop((int)((10d / 100 * height)));
views.get("pnlmainsigbg2").vw.setHeight((int)((views.get("pnlconfirmsig").vw.getHeight())-(15d / 100 * height) - ((10d / 100 * height))));
//BA.debugLineNum = 220;BA.debugLine="lblConfirmSig.SetLeftAndRight (0%x, pnlMainSigBG2.Width)"[CMJOFindings/General script]
views.get("lblconfirmsig").vw.setLeft((int)((0d / 100 * width)));
views.get("lblconfirmsig").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth()) - ((0d / 100 * width))));
//BA.debugLineNum = 221;BA.debugLine="lblConfirmSig.Top = 0%y"[CMJOFindings/General script]
views.get("lblconfirmsig").vw.setTop((int)((0d / 100 * height)));
//BA.debugLineNum = 223;BA.debugLine="pnlImageSig.SetLeftAndRight(3%x, pnlMainSigBG2.Width - 3%x)"[CMJOFindings/General script]
views.get("pnlimagesig").vw.setLeft((int)((3d / 100 * width)));
views.get("pnlimagesig").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
//BA.debugLineNum = 224;BA.debugLine="pnlImageSig.SetTopAndBottom(lblConfirmSig.Bottom + 5dip, pnlMainSigBG2.Height - 50%y)"[CMJOFindings/General script]
views.get("pnlimagesig").vw.setTop((int)((views.get("lblconfirmsig").vw.getTop() + views.get("lblconfirmsig").vw.getHeight())+(5d * scale)));
views.get("pnlimagesig").vw.setHeight((int)((views.get("pnlmainsigbg2").vw.getHeight())-(50d / 100 * height) - ((views.get("lblconfirmsig").vw.getTop() + views.get("lblconfirmsig").vw.getHeight())+(5d * scale))));
//BA.debugLineNum = 226;BA.debugLine="imgSignature.SetLeftAndRight(2%x, pnlImageSig.Width - 2%x)"[CMJOFindings/General script]
views.get("imgsignature").vw.setLeft((int)((2d / 100 * width)));
views.get("imgsignature").vw.setWidth((int)((views.get("pnlimagesig").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 227;BA.debugLine="imgSignature.SetTopAndBottom(2%y, pnlImageSig.Height - 2%y)"[CMJOFindings/General script]
views.get("imgsignature").vw.setTop((int)((2d / 100 * height)));
views.get("imgsignature").vw.setHeight((int)((views.get("pnlimagesig").vw.getHeight())-(2d / 100 * height) - ((2d / 100 * height))));
//BA.debugLineNum = 229;BA.debugLine="lblAccomplishedBy.SetLeftAndRight(2%x, pnlMainSigBG2.Width - 2%x)"[CMJOFindings/General script]
views.get("lblaccomplishedby").vw.setLeft((int)((2d / 100 * width)));
views.get("lblaccomplishedby").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 230;BA.debugLine="lblAccomplishedBy.Top = pnlImageSig.Bottom + 10dip"[CMJOFindings/General script]
views.get("lblaccomplishedby").vw.setTop((int)((views.get("pnlimagesig").vw.getTop() + views.get("pnlimagesig").vw.getHeight())+(10d * scale)));
//BA.debugLineNum = 232;BA.debugLine="pnlPlumberAnchor.SetLeftAndRight(2%x, pnlMainSigBG2.Width - 2%x)"[CMJOFindings/General script]
views.get("pnlplumberanchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlplumberanchor").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 233;BA.debugLine="pnlPlumberAnchor.Top = lblAccomplishedBy.Bottom + 2dip"[CMJOFindings/General script]
views.get("pnlplumberanchor").vw.setTop((int)((views.get("lblaccomplishedby").vw.getTop() + views.get("lblaccomplishedby").vw.getHeight())+(2d * scale)));
//BA.debugLineNum = 234;BA.debugLine="spnPlumbers.SetLeftAndRight(1%x, pnlPlumberAnchor.Width - 1%x)"[CMJOFindings/General script]
views.get("spnplumbers").vw.setLeft((int)((1d / 100 * width)));
views.get("spnplumbers").vw.setWidth((int)((views.get("pnlplumberanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 235;BA.debugLine="spnPlumbers.SetTopAndBottom(1%y, pnlPlumberAnchor.Height - 1%y)"[CMJOFindings/General script]
views.get("spnplumbers").vw.setTop((int)((1d / 100 * height)));
views.get("spnplumbers").vw.setHeight((int)((views.get("pnlplumberanchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
//BA.debugLineNum = 237;BA.debugLine="lblDateTimeStart.Top = pnlPlumberAnchor.Bottom + 5dip"[CMJOFindings/General script]
views.get("lbldatetimestart").vw.setTop((int)((views.get("pnlplumberanchor").vw.getTop() + views.get("pnlplumberanchor").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 238;BA.debugLine="lblDateTimeStart.Left = 2%x"[CMJOFindings/General script]
views.get("lbldatetimestart").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 239;BA.debugLine="pnlStartedAnchor.Top = lblDateTimeStart.Top"[CMJOFindings/General script]
views.get("pnlstartedanchor").vw.setTop((int)((views.get("lbldatetimestart").vw.getTop())));
//BA.debugLineNum = 240;BA.debugLine="pnlStartedAnchor.SetLeftAndRight(lblDateTimeStart.Right + 5dip, pnlMainSigBG2.Width - 2%x)"[CMJOFindings/General script]
views.get("pnlstartedanchor").vw.setLeft((int)((views.get("lbldatetimestart").vw.getLeft() + views.get("lbldatetimestart").vw.getWidth())+(5d * scale)));
views.get("pnlstartedanchor").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth())-(2d / 100 * width) - ((views.get("lbldatetimestart").vw.getLeft() + views.get("lbldatetimestart").vw.getWidth())+(5d * scale))));
//BA.debugLineNum = 241;BA.debugLine="txtDateTimeStarted.SetLeftAndRight(1%x, pnlStartedAnchor.Width - 1%x)"[CMJOFindings/General script]
views.get("txtdatetimestarted").vw.setLeft((int)((1d / 100 * width)));
views.get("txtdatetimestarted").vw.setWidth((int)((views.get("pnlstartedanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 242;BA.debugLine="txtDateTimeStarted.SetTopAndBottom(0.75%y, pnlStartedAnchor.Height - 0.75%y)"[CMJOFindings/General script]
views.get("txtdatetimestarted").vw.setTop((int)((0.75d / 100 * height)));
views.get("txtdatetimestarted").vw.setHeight((int)((views.get("pnlstartedanchor").vw.getHeight())-(0.75d / 100 * height) - ((0.75d / 100 * height))));
//BA.debugLineNum = 244;BA.debugLine="lblDateTimeFinished.Top = lblDateTimeStart.Bottom + 5dip"[CMJOFindings/General script]
views.get("lbldatetimefinished").vw.setTop((int)((views.get("lbldatetimestart").vw.getTop() + views.get("lbldatetimestart").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 245;BA.debugLine="lblDateTimeFinished.Left = 2%x"[CMJOFindings/General script]
views.get("lbldatetimefinished").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 246;BA.debugLine="pnlFinishedAnchor.Top = lblDateTimeFinished.Top"[CMJOFindings/General script]
views.get("pnlfinishedanchor").vw.setTop((int)((views.get("lbldatetimefinished").vw.getTop())));
//BA.debugLineNum = 247;BA.debugLine="pnlFinishedAnchor.SetLeftAndRight(lblDateTimeStart.Right + 5dip, pnlMainSigBG2.Width - 2%x)"[CMJOFindings/General script]
views.get("pnlfinishedanchor").vw.setLeft((int)((views.get("lbldatetimestart").vw.getLeft() + views.get("lbldatetimestart").vw.getWidth())+(5d * scale)));
views.get("pnlfinishedanchor").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth())-(2d / 100 * width) - ((views.get("lbldatetimestart").vw.getLeft() + views.get("lbldatetimestart").vw.getWidth())+(5d * scale))));
//BA.debugLineNum = 248;BA.debugLine="txtDateTimeFinished.SetLeftAndRight(1%x, pnlFinishedAnchor.Width - 1%x)"[CMJOFindings/General script]
views.get("txtdatetimefinished").vw.setLeft((int)((1d / 100 * width)));
views.get("txtdatetimefinished").vw.setWidth((int)((views.get("pnlfinishedanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 249;BA.debugLine="txtDateTimeFinished.SetTopAndBottom(0.75%y, pnlFinishedAnchor.Height - 0.75%y)"[CMJOFindings/General script]
views.get("txtdatetimefinished").vw.setTop((int)((0.75d / 100 * height)));
views.get("txtdatetimefinished").vw.setHeight((int)((views.get("pnlfinishedanchor").vw.getHeight())-(0.75d / 100 * height) - ((0.75d / 100 * height))));
//BA.debugLineNum = 251;BA.debugLine="btnCancel.SetLeftAndRight(3%x, pnlMainSigBG2.Width - 65%x)"[CMJOFindings/General script]
views.get("btncancel").vw.setLeft((int)((3d / 100 * width)));
views.get("btncancel").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth())-(65d / 100 * width) - ((3d / 100 * width))));
//BA.debugLineNum = 252;BA.debugLine="btnCancel.Top = pnlFinishedAnchor.Bottom + 10dip"[CMJOFindings/General script]
views.get("btncancel").vw.setTop((int)((views.get("pnlfinishedanchor").vw.getTop() + views.get("pnlfinishedanchor").vw.getHeight())+(10d * scale)));
//BA.debugLineNum = 254;BA.debugLine="btnClear.SetLeftAndRight(36%x, pnlMainSigBG2.Width - 32%x)"[CMJOFindings/General script]
views.get("btnclear").vw.setLeft((int)((36d / 100 * width)));
views.get("btnclear").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth())-(32d / 100 * width) - ((36d / 100 * width))));
//BA.debugLineNum = 255;BA.debugLine="btnClear.Top = pnlFinishedAnchor.Bottom + 10dip"[CMJOFindings/General script]
views.get("btnclear").vw.setTop((int)((views.get("pnlfinishedanchor").vw.getTop() + views.get("pnlfinishedanchor").vw.getHeight())+(10d * scale)));
//BA.debugLineNum = 257;BA.debugLine="btnOK.SetLeftAndRight(69%x, pnlMainSigBG2.Width - 2%x)"[CMJOFindings/General script]
views.get("btnok").vw.setLeft((int)((69d / 100 * width)));
views.get("btnok").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth())-(2d / 100 * width) - ((69d / 100 * width))));
//BA.debugLineNum = 258;BA.debugLine="btnOK.Top = pnlFinishedAnchor.Bottom + 10dip"[CMJOFindings/General script]
views.get("btnok").vw.setTop((int)((views.get("pnlfinishedanchor").vw.getTop() + views.get("pnlfinishedanchor").vw.getHeight())+(10d * scale)));

}
}