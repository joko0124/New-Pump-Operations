package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_dcdajofindings{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("pnlmain").vw.setLeft((int)(0d));
views.get("pnlmain").vw.setTop((int)((0.5d / 100 * height)));
views.get("pnlmain").vw.setHeight((int)((185d / 100 * height) - ((0.5d / 100 * height))));
views.get("pnlmain").vw.setWidth((int)((100d / 100 * width)));
views.get("pnljodetails").vw.setTop((int)((1d / 100 * height)));
views.get("pnljodetails").vw.setHeight((int)((57d / 100 * height)));
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
views.get("lblcaption5").vw.setTop((int)((views.get("pnljodetstyle6").vw.getTop() + views.get("pnljodetstyle6").vw.getHeight())+(3d * scale)));
views.get("pnljodetstyle7").vw.setTop((int)((views.get("pnljodetstyle6").vw.getTop() + views.get("pnljodetstyle6").vw.getHeight())+(3d * scale)));
views.get("pnljodetstyle7").vw.setLeft((int)((views.get("lblcaption5").vw.getLeft() + views.get("lblcaption5").vw.getWidth())+(2d * scale)));
views.get("pnljodetstyle7").vw.setWidth((int)((views.get("pnljodetails").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption5").vw.getLeft() + views.get("lblcaption5").vw.getWidth())+(2d * scale))));
views.get("lblmeterno").vw.setLeft((int)((2d / 100 * width)));
views.get("lblmeterno").vw.setWidth((int)((views.get("pnljodetstyle7").vw.getWidth())-(1d / 100 * width) - ((2d / 100 * width))));
views.get("lblmeterno").vw.setTop((int)((0.25d / 100 * height)));
views.get("lblmeterno").vw.setHeight((int)((views.get("pnljodetstyle7").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("pnlfindings").vw.setTop((int)((views.get("pnljodetails").vw.getTop() + views.get("pnljodetails").vw.getHeight())+(5d * scale)));
views.get("pnlfindings").vw.setLeft((int)((1d / 100 * width)));
views.get("pnlfindings").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlfindings").vw.setHeight((int)((36d / 100 * height)));
views.get("lblfindingstitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblfindingstitle").vw.setWidth((int)((views.get("pnlfindings").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblcaption9").vw.setTop((int)((views.get("lblfindingstitle").vw.getTop() + views.get("lblfindingstitle").vw.getHeight())+(5d * scale)));
views.get("lblcaption9").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlactiontakenanchor").vw.setTop((int)((views.get("lblcaption9").vw.getTop())));
views.get("pnlactiontakenanchor").vw.setLeft((int)((views.get("lblcaption9").vw.getLeft() + views.get("lblcaption9").vw.getWidth())+(5d * scale)));
views.get("pnlactiontakenanchor").vw.setWidth((int)((views.get("pnlfindings").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption9").vw.getLeft() + views.get("lblcaption9").vw.getWidth())+(5d * scale))));
views.get("cboactiontaken").vw.setLeft((int)((1d / 100 * width)));
views.get("cboactiontaken").vw.setWidth((int)((views.get("pnlactiontakenanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("cboactiontaken").vw.setTop((int)((0.25d / 100 * height)));
views.get("cboactiontaken").vw.setHeight((int)((views.get("pnlactiontakenanchor").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("lblcaption8").vw.setLeft((int)((views.get("lblcaption6").vw.getLeft() + views.get("lblcaption6").vw.getWidth()) - (views.get("lblcaption8").vw.getWidth())));
views.get("lblcaption8").vw.setTop((int)((views.get("lblcaption9").vw.getTop() + views.get("lblcaption9").vw.getHeight())+(5d * scale)));
views.get("pnlmeterfindingsanchor").vw.setTop((int)((views.get("lblcaption8").vw.getTop())));
views.get("pnlmeterfindingsanchor").vw.setLeft((int)((views.get("lblcaption8").vw.getLeft() + views.get("lblcaption8").vw.getWidth())+(5d * scale)));
views.get("pnlmeterfindingsanchor").vw.setWidth((int)((views.get("pnlfindings").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption8").vw.getLeft() + views.get("lblcaption8").vw.getWidth())+(5d * scale))));
views.get("txtmeterfindings").vw.setLeft((int)((1d / 100 * width)));
views.get("txtmeterfindings").vw.setWidth((int)((views.get("pnlmeterfindingsanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtmeterfindings").vw.setTop((int)((0.25d / 100 * height)));
views.get("txtmeterfindings").vw.setHeight((int)((views.get("pnlmeterfindingsanchor").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("lblcaption6").vw.setLeft((int)((views.get("lblcaption9").vw.getLeft() + views.get("lblcaption9").vw.getWidth()) - (views.get("lblcaption6").vw.getWidth())));
views.get("lblcaption6").vw.setTop((int)((views.get("lblcaption8").vw.getTop() + views.get("lblcaption8").vw.getHeight())+(5d * scale)));
views.get("pnlprevrdganchor").vw.setTop((int)((views.get("lblcaption6").vw.getTop())));
views.get("pnlprevrdganchor").vw.setLeft((int)((views.get("lblcaption6").vw.getLeft() + views.get("lblcaption6").vw.getWidth())+(5d * scale)));
views.get("pnlprevrdganchor").vw.setWidth((int)((views.get("pnlfindings").vw.getWidth())-(45d / 100 * width) - ((views.get("lblcaption6").vw.getLeft() + views.get("lblcaption6").vw.getWidth())+(5d * scale))));
views.get("txtprevrdg").vw.setLeft((int)((1d / 100 * width)));
views.get("txtprevrdg").vw.setWidth((int)((views.get("pnlprevrdganchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtprevrdg").vw.setTop((int)((0.25d / 100 * height)));
views.get("txtprevrdg").vw.setHeight((int)((views.get("pnlprevrdganchor").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("lblcaption7").vw.setLeft((int)((views.get("pnlprevrdganchor").vw.getLeft() + views.get("pnlprevrdganchor").vw.getWidth())));
views.get("lblcaption7").vw.setTop((int)((views.get("lblcaption6").vw.getTop())));
views.get("pnlfinalrdganchor").vw.setTop((int)((views.get("lblcaption7").vw.getTop())));
views.get("pnlfinalrdganchor").vw.setLeft((int)((views.get("lblcaption7").vw.getLeft() + views.get("lblcaption7").vw.getWidth())+(5d * scale)));
views.get("pnlfinalrdganchor").vw.setWidth((int)((views.get("pnlfindings").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption7").vw.getLeft() + views.get("lblcaption7").vw.getWidth())+(5d * scale))));
views.get("txtfinalrdg").vw.setLeft((int)((1d / 100 * width)));
views.get("txtfinalrdg").vw.setWidth((int)((views.get("pnlfinalrdganchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtfinalrdg").vw.setTop((int)((0.25d / 100 * height)));
views.get("txtfinalrdg").vw.setHeight((int)((views.get("pnlfinalrdganchor").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("pnlwaterlossfindings").vw.setTop((int)((views.get("pnlfindings").vw.getTop() + views.get("pnlfindings").vw.getHeight())+(5d * scale)));
views.get("pnlwaterlossfindings").vw.setLeft((int)((1d / 100 * width)));
views.get("pnlwaterlossfindings").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlwaterlossfindings").vw.setHeight((int)((54d / 100 * height)));
views.get("lblwaterlossfindings").vw.setLeft((int)((0d / 100 * width)));
views.get("lblwaterlossfindings").vw.setWidth((int)((views.get("pnlwaterlossfindings").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblcaption10").vw.setTop((int)((views.get("lblwaterlossfindings").vw.getTop() + views.get("lblwaterlossfindings").vw.getHeight())+(5d * scale)));
views.get("lblcaption10").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlstartedanchor").vw.setTop((int)((views.get("lblcaption10").vw.getTop())));
views.get("pnlstartedanchor").vw.setLeft((int)((views.get("lblcaption10").vw.getLeft() + views.get("lblcaption10").vw.getWidth())+(5d * scale)));
views.get("pnlstartedanchor").vw.setWidth((int)((views.get("pnlwaterlossfindings").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption10").vw.getLeft() + views.get("lblcaption10").vw.getWidth())+(5d * scale))));
views.get("txtdatetimestarted").vw.setLeft((int)((1d / 100 * width)));
views.get("txtdatetimestarted").vw.setWidth((int)((views.get("pnlstartedanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtdatetimestarted").vw.setTop((int)((0.25d / 100 * height)));
views.get("txtdatetimestarted").vw.setHeight((int)((views.get("pnlstartedanchor").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("lblcaption11").vw.setTop((int)((views.get("lblcaption10").vw.getTop() + views.get("lblcaption10").vw.getHeight())+(5d * scale)));
views.get("lblcaption11").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlfinishedanchor").vw.setTop((int)((views.get("lblcaption11").vw.getTop())));
views.get("pnlfinishedanchor").vw.setLeft((int)((views.get("lblcaption10").vw.getLeft() + views.get("lblcaption10").vw.getWidth())+(5d * scale)));
views.get("pnlfinishedanchor").vw.setWidth((int)((views.get("pnlwaterlossfindings").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption10").vw.getLeft() + views.get("lblcaption10").vw.getWidth())+(5d * scale))));
views.get("txtdatetimefinished").vw.setLeft((int)((1d / 100 * width)));
views.get("txtdatetimefinished").vw.setWidth((int)((views.get("pnlfinishedanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtdatetimefinished").vw.setTop((int)((0.25d / 100 * height)));
views.get("txtdatetimefinished").vw.setHeight((int)((views.get("pnlfinishedanchor").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("lblcaption12").vw.setTop((int)((views.get("lblcaption11").vw.getTop() + views.get("lblcaption11").vw.getHeight())+(5d * scale)));
views.get("lblcaption12").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlpipetypeanchor").vw.setTop((int)((views.get("pnlfinishedanchor").vw.getTop() + views.get("pnlfinishedanchor").vw.getHeight())+(5d * scale)));
views.get("pnlpipetypeanchor").vw.setLeft((int)((views.get("lblcaption12").vw.getLeft() + views.get("lblcaption12").vw.getWidth())+(5d * scale)));
views.get("pnlpipetypeanchor").vw.setWidth((int)((views.get("pnlwaterlossfindings").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption12").vw.getLeft() + views.get("lblcaption12").vw.getWidth())+(5d * scale))));
views.get("cbopipetype").vw.setLeft((int)((1.75d / 100 * width)));
views.get("cbopipetype").vw.setWidth((int)((views.get("pnlpipetypeanchor").vw.getWidth())-(1.75d / 100 * width) - ((1.75d / 100 * width))));
views.get("cbopipetype").vw.setTop((int)((0.25d / 100 * height)));
views.get("cbopipetype").vw.setHeight((int)((views.get("pnlpipetypeanchor").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("lblcaption13").vw.setLeft((int)((views.get("lblcaption12").vw.getLeft() + views.get("lblcaption12").vw.getWidth()) - (views.get("lblcaption13").vw.getWidth())));
views.get("lblcaption13").vw.setTop((int)((views.get("lblcaption12").vw.getTop() + views.get("lblcaption12").vw.getHeight())+(5d * scale)));
views.get("pnlpipesizeanchor").vw.setTop((int)((views.get("lblcaption13").vw.getTop())));
views.get("pnlpipesizeanchor").vw.setLeft((int)((views.get("lblcaption13").vw.getLeft() + views.get("lblcaption13").vw.getWidth())+(5d * scale)));
views.get("pnlpipesizeanchor").vw.setWidth((int)((views.get("pnlwaterlossfindings").vw.getWidth())-(2d / 100 * width) - ((views.get("lblcaption13").vw.getLeft() + views.get("lblcaption13").vw.getWidth())+(5d * scale))));
views.get("cbopipesize").vw.setLeft((int)((1d / 100 * width)));
views.get("cbopipesize").vw.setWidth((int)((views.get("pnlpipesizeanchor").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("cbopipesize").vw.setTop((int)((0.25d / 100 * height)));
views.get("cbopipesize").vw.setHeight((int)((views.get("pnlpipesizeanchor").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("lblcaption14").vw.setLeft((int)((views.get("lblcaption13").vw.getLeft() + views.get("lblcaption13").vw.getWidth()) - (views.get("lblcaption14").vw.getWidth())));
views.get("lblcaption14").vw.setTop((int)((views.get("lblcaption13").vw.getTop() + views.get("lblcaption13").vw.getHeight())+(5d * scale)));
views.get("pnlpsianchor").vw.setTop((int)((views.get("lblcaption14").vw.getTop())));
views.get("pnlpsianchor").vw.setLeft((int)((views.get("lblcaption14").vw.getLeft() + views.get("lblcaption14").vw.getWidth())+(5d * scale)));
views.get("pnlpsianchor").vw.setWidth((int)((views.get("pnlwaterlossfindings").vw.getWidth())-(60d / 100 * width) - ((views.get("lblcaption14").vw.getLeft() + views.get("lblcaption14").vw.getWidth())+(5d * scale))));
views.get("txtpsi").vw.setLeft((int)((1.75d / 100 * width)));
views.get("txtpsi").vw.setWidth((int)((views.get("pnlpsianchor").vw.getWidth())-(1.75d / 100 * width) - ((1.75d / 100 * width))));
views.get("txtpsi").vw.setTop((int)((0.25d / 100 * height)));
views.get("txtpsi").vw.setHeight((int)((views.get("pnlpsianchor").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("lblcaption15").vw.setLeft((int)((views.get("pnlpsianchor").vw.getLeft() + views.get("pnlpsianchor").vw.getWidth())+(5d * scale)));
views.get("lblcaption15").vw.setTop((int)((views.get("lblcaption14").vw.getTop())));
views.get("pnlwaterlossanchor").vw.setTop((int)((views.get("lblcaption15").vw.getTop())));
views.get("pnlwaterlossanchor").vw.setLeft((int)((views.get("lblcaption15").vw.getLeft() + views.get("lblcaption15").vw.getWidth())+(5d * scale)));
views.get("pnlwaterlossanchor").vw.setWidth((int)((views.get("pnlwaterlossfindings").vw.getWidth())-(10d / 100 * width) - ((views.get("lblcaption15").vw.getLeft() + views.get("lblcaption15").vw.getWidth())+(5d * scale))));
views.get("txtwaterloss").vw.setLeft((int)((2d / 100 * width)));
views.get("txtwaterloss").vw.setWidth((int)((views.get("pnlwaterlossanchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtwaterloss").vw.setTop((int)((0.25d / 100 * height)));
views.get("txtwaterloss").vw.setHeight((int)((views.get("pnlwaterlossanchor").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
views.get("lblcum").vw.setLeft((int)((views.get("pnlwaterlossanchor").vw.getLeft() + views.get("pnlwaterlossanchor").vw.getWidth())+(5d * scale)));
views.get("lblcum").vw.setTop((int)((views.get("lblcaption15").vw.getTop())));
views.get("pnlremarks").vw.setTop((int)((views.get("pnlwaterlossfindings").vw.getTop() + views.get("pnlwaterlossfindings").vw.getHeight())+(5d * scale)));
views.get("pnlremarks").vw.setLeft((int)((1d / 100 * width)));
views.get("pnlremarks").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlremarks").vw.setHeight((int)((20d / 100 * height)));
views.get("lblremarkstitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblremarkstitle").vw.setWidth((int)((views.get("pnlremarks").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblremarkstitle").vw.setTop((int)((0d / 100 * height)));
views.get("pnlremarksanchor").vw.setLeft((int)((1d / 100 * width)));
views.get("pnlremarksanchor").vw.setWidth((int)((views.get("pnlremarks").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlremarksanchor").vw.setTop((int)((views.get("lblremarkstitle").vw.getTop() + views.get("lblremarkstitle").vw.getHeight())+(5d * scale)));
views.get("pnlremarksanchor").vw.setHeight((int)((views.get("pnlremarks").vw.getHeight())-(1d / 100 * height) - ((views.get("lblremarkstitle").vw.getTop() + views.get("lblremarkstitle").vw.getHeight())+(5d * scale))));
views.get("txtremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("txtremarks").vw.setWidth((int)((views.get("pnlremarksanchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtremarks").vw.setTop((int)((1d / 100 * height)));
views.get("txtremarks").vw.setHeight((int)((views.get("pnlremarksanchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("btnsaveupdate").vw.setTop((int)((views.get("pnlremarks").vw.getTop() + views.get("pnlremarks").vw.getHeight())+(10d * scale)));
views.get("btnsaveupdate").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(2d / 100 * height) - ((views.get("pnlremarks").vw.getTop() + views.get("pnlremarks").vw.getHeight())+(10d * scale))));
views.get("btnsaveupdate").vw.setLeft((int)((2d / 100 * width)));
views.get("btnsaveupdate").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlsignature").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlsignature").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlsignature").vw.setTop((int)((0d / 100 * height)));
views.get("pnlsignature").vw.setHeight((int)((views.get("pnlmain").vw.getTop() + views.get("pnlmain").vw.getHeight()) - ((0d / 100 * height))));
views.get("pnlmainsigbg").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlmainsigbg").vw.setWidth((int)((views.get("pnlsignature").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlmainsigbg").vw.setTop((int)((20d / 100 * height)));
views.get("pnlmainsigbg").vw.setHeight((int)((views.get("pnlsignature").vw.getHeight())-(100d / 100 * height) - ((20d / 100 * height))));
views.get("lblcustsignature").vw.setLeft((int)((0d / 100 * width)));
views.get("lblcustsignature").vw.setWidth((int)((views.get("pnlmainsigbg").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblcustsignature").vw.setTop((int)((0d / 100 * height)));
views.get("pnlsigbg").vw.setLeft((int)((3d / 100 * width)));
views.get("pnlsigbg").vw.setWidth((int)((views.get("pnlmainsigbg").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
views.get("pnlsigbg").vw.setTop((int)((views.get("lblcustsignature").vw.getTop() + views.get("lblcustsignature").vw.getHeight())+(5d * scale)));
views.get("pnlsigbg").vw.setHeight((int)((views.get("pnlmainsigbg").vw.getHeight())-(10d / 100 * height) - ((views.get("lblcustsignature").vw.getTop() + views.get("lblcustsignature").vw.getHeight())+(5d * scale))));
views.get("signaturepad").vw.setLeft((int)((1.5d / 100 * width)));
views.get("signaturepad").vw.setWidth((int)((views.get("pnlsigbg").vw.getWidth())-(1.5d / 100 * width) - ((1.5d / 100 * width))));
views.get("signaturepad").vw.setTop((int)((1.5d / 100 * height)));
views.get("signaturepad").vw.setHeight((int)((views.get("pnlsigbg").vw.getHeight())-(1.5d / 100 * height) - ((1.5d / 100 * height))));
views.get("btnconfirmsig").vw.setLeft((int)((views.get("pnlsigbg").vw.getLeft())));
views.get("btnconfirmsig").vw.setWidth((int)((views.get("pnlsigbg").vw.getLeft() + views.get("pnlsigbg").vw.getWidth()) - ((views.get("pnlsigbg").vw.getLeft()))));
views.get("btnconfirmsig").vw.setTop((int)((views.get("pnlsigbg").vw.getTop() + views.get("pnlsigbg").vw.getHeight())+(10d * scale)));
views.get("btnconfirmsig").vw.setHeight((int)((views.get("pnlmainsigbg").vw.getHeight())-(1d / 100 * height) - ((views.get("pnlsigbg").vw.getTop() + views.get("pnlsigbg").vw.getHeight())+(10d * scale))));
views.get("pnlconfirmsig").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlconfirmsig").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlconfirmsig").vw.setTop((int)((0d / 100 * height)));
views.get("pnlconfirmsig").vw.setHeight((int)((views.get("pnlmain").vw.getTop() + views.get("pnlmain").vw.getHeight()) - ((0d / 100 * height))));
views.get("pnlmainsigbg2").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlmainsigbg2").vw.setWidth((int)((views.get("pnlconfirmsig").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlmainsigbg2").vw.setTop((int)((20d / 100 * height)));
views.get("pnlmainsigbg2").vw.setHeight((int)((views.get("pnlconfirmsig").vw.getHeight())-(100d / 100 * height) - ((20d / 100 * height))));
views.get("lblconfirmsig").vw.setLeft((int)((0d / 100 * width)));
views.get("lblconfirmsig").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblconfirmsig").vw.setTop((int)((0d / 100 * height)));
views.get("pnlimagesig").vw.setLeft((int)((3d / 100 * width)));
views.get("pnlimagesig").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
views.get("pnlimagesig").vw.setTop((int)((views.get("lblconfirmsig").vw.getTop() + views.get("lblconfirmsig").vw.getHeight())+(5d * scale)));
views.get("pnlimagesig").vw.setHeight((int)((views.get("pnlmainsigbg2").vw.getHeight())-(10d / 100 * height) - ((views.get("lblconfirmsig").vw.getTop() + views.get("lblconfirmsig").vw.getHeight())+(5d * scale))));
views.get("imgsignature").vw.setLeft((int)((2d / 100 * width)));
views.get("imgsignature").vw.setWidth((int)((views.get("pnlimagesig").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("imgsignature").vw.setTop((int)((2d / 100 * height)));
views.get("imgsignature").vw.setHeight((int)((views.get("pnlimagesig").vw.getHeight())-(2d / 100 * height) - ((2d / 100 * height))));
views.get("btncancel").vw.setLeft((int)((3d / 100 * width)));
views.get("btncancel").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth())-(65d / 100 * width) - ((3d / 100 * width))));
views.get("btncancel").vw.setTop((int)((views.get("pnlimagesig").vw.getTop() + views.get("pnlimagesig").vw.getHeight())+(10d * scale)));
views.get("btncancel").vw.setHeight((int)((views.get("pnlmainsigbg").vw.getHeight())-(1d / 100 * height) - ((views.get("pnlimagesig").vw.getTop() + views.get("pnlimagesig").vw.getHeight())+(10d * scale))));
views.get("btnclear").vw.setLeft((int)((36d / 100 * width)));
views.get("btnclear").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth())-(32d / 100 * width) - ((36d / 100 * width))));
views.get("btnclear").vw.setTop((int)((views.get("pnlimagesig").vw.getTop() + views.get("pnlimagesig").vw.getHeight())+(10d * scale)));
views.get("btnclear").vw.setHeight((int)((views.get("pnlmainsigbg").vw.getHeight())-(1d / 100 * height) - ((views.get("pnlimagesig").vw.getTop() + views.get("pnlimagesig").vw.getHeight())+(10d * scale))));
views.get("btnok").vw.setLeft((int)((69d / 100 * width)));
views.get("btnok").vw.setWidth((int)((views.get("pnlmainsigbg2").vw.getWidth())-(2d / 100 * width) - ((69d / 100 * width))));
views.get("btnok").vw.setTop((int)((views.get("pnlimagesig").vw.getTop() + views.get("pnlimagesig").vw.getHeight())+(10d * scale)));
views.get("btnok").vw.setHeight((int)((views.get("pnlmainsigbg").vw.getHeight())-(1d / 100 * height) - ((views.get("pnlimagesig").vw.getTop() + views.get("pnlimagesig").vw.getHeight())+(10d * scale))));
views.get("pnlmessagebox").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlmessagebox").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlmessagebox").vw.setTop((int)((0d / 100 * height)));
views.get("pnlmessagebox").vw.setHeight((int)((views.get("pnlmain").vw.getTop() + views.get("pnlmain").vw.getHeight()) - ((0d / 100 * height))));
views.get("pnlmsgbox").vw.setLeft((int)((5d / 100 * width)));
views.get("pnlmsgbox").vw.setWidth((int)((views.get("pnlmessagebox").vw.getWidth())-(5d / 100 * width) - ((5d / 100 * width))));
views.get("pnlmsgbox").vw.setTop((int)((30d / 100 * height)));
views.get("pnlmsgbox").vw.setHeight((int)((views.get("pnlmessagebox").vw.getHeight())-(120d / 100 * height) - ((30d / 100 * height))));
views.get("lblmsgtitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblmsgtitle").vw.setWidth((int)((views.get("pnlmainsigbg").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblmsgtitle").vw.setTop((int)((0d / 100 * height)));
views.get("lblmsgicon").vw.setTop((int)((0d / 100 * height)));
views.get("lblmsgicon").vw.setLeft((int)((5d * scale)));
views.get("lblmsgcontent").vw.setTop((int)((views.get("lblmsgtitle").vw.getTop() + views.get("lblmsgtitle").vw.getHeight())+(10d * scale)));
views.get("lblmsgcontent").vw.setLeft((int)((10d * scale)));
views.get("lblmsgcontent").vw.setWidth((int)((views.get("pnlmsgbox").vw.getWidth())-(10d * scale) - ((10d * scale))));
views.get("btnmsgok").vw.setLeft((int)((10d * scale)));
views.get("btnmsgok").vw.setWidth((int)((views.get("pnlmsgbox").vw.getWidth())-(10d * scale) - ((10d * scale))));
views.get("btnmsgok").vw.setTop((int)((views.get("lblmsgcontent").vw.getTop() + views.get("lblmsgcontent").vw.getHeight())+(5d * scale)));
views.get("btnmsgok").vw.setHeight((int)((views.get("pnlmsgbox").vw.getHeight())-(10d * scale) - ((views.get("lblmsgcontent").vw.getTop() + views.get("lblmsgcontent").vw.getHeight())+(5d * scale))));

}
}