package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_production{

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
views.get("pnltime").vw.setWidth((int)((100d / 100 * width)));
views.get("pnltime").vw.setLeft((int)(0d));
views.get("pnltime").vw.setTop((int)((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight())));
views.get("pnltime").vw.setHeight((int)((views.get("tabmenu").vw.getTop()) - ((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight()))));
views.get("label1").vw.setLeft((int)(0d));
views.get("label1").vw.setWidth((int)((32.5d / 100 * width) - (0d)));
views.get("label2").vw.setLeft((int)((32.5d / 100 * width)));
views.get("label2").vw.setWidth((int)((65d / 100 * width) - ((32.5d / 100 * width))));
views.get("label3").vw.setLeft((int)((65d / 100 * width)));
views.get("label3").vw.setWidth((int)((100d / 100 * width) - ((65d / 100 * width))));
views.get("clvtime").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())));
views.get("clvtime").vw.setHeight((int)((views.get("pnltime").vw.getHeight())-(views.get("label1").vw.getHeight())-(5d * scale)));
views.get("clvtime").vw.setLeft((int)(0d));
views.get("clvtime").vw.setWidth((int)((views.get("pnltime").vw.getWidth())));
views.get("btnaddtime").vw.setLeft((int)((views.get("pnltime").vw.getLeft() + views.get("pnltime").vw.getWidth())-(views.get("btnaddtime").vw.getWidth())-(20d * scale)));
views.get("btnaddtime").vw.setTop((int)((views.get("clvtime").vw.getTop() + views.get("clvtime").vw.getHeight())-(views.get("btnaddtime").vw.getHeight())-(20d * scale)));
views.get("pnlfmrdg").vw.setWidth((int)((100d / 100 * width)));
views.get("pnlfmrdg").vw.setLeft((int)(0d));
views.get("pnlfmrdg").vw.setTop((int)((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight())));
views.get("pnlfmrdg").vw.setHeight((int)((views.get("tabmenu").vw.getTop()) - ((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight()))));
views.get("label4").vw.setLeft((int)(0d));
views.get("label4").vw.setWidth((int)((30d / 100 * width) - (0d)));
views.get("label5").vw.setLeft((int)((30d / 100 * width)));
views.get("label5").vw.setWidth((int)((50d / 100 * width) - ((30d / 100 * width))));
views.get("label6").vw.setLeft((int)((50d / 100 * width)));
views.get("label6").vw.setWidth((int)((70d / 100 * width) - ((50d / 100 * width))));
views.get("label7").vw.setLeft((int)((70d / 100 * width)));
views.get("label7").vw.setWidth((int)((100d / 100 * width) - ((70d / 100 * width))));
views.get("clvfm").vw.setTop((int)((views.get("label4").vw.getTop() + views.get("label4").vw.getHeight())));
views.get("clvfm").vw.setHeight((int)((views.get("pnlfmrdg").vw.getHeight())-(views.get("label4").vw.getHeight())-(5d * scale)));
views.get("clvfm").vw.setLeft((int)(0d));
views.get("clvfm").vw.setWidth((int)((views.get("pnlfmrdg").vw.getWidth())));
views.get("btnaddfm").vw.setLeft((int)((views.get("pnlfmrdg").vw.getLeft() + views.get("pnlfmrdg").vw.getWidth())-(views.get("btnaddfm").vw.getWidth())-(20d * scale)));
views.get("btnaddfm").vw.setTop((int)((views.get("clvfm").vw.getTop() + views.get("clvfm").vw.getHeight())-(views.get("btnaddfm").vw.getHeight())-(20d * scale)));
views.get("pnlpsirdg").vw.setWidth((int)((100d / 100 * width)));
views.get("pnlpsirdg").vw.setLeft((int)(0d));
views.get("pnlpsirdg").vw.setTop((int)((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight())));
views.get("pnlpsirdg").vw.setHeight((int)((views.get("tabmenu").vw.getTop()) - ((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight()))));
views.get("label8").vw.setLeft((int)(0d));
views.get("label8").vw.setWidth((int)((60d / 100 * width) - (0d)));
views.get("label9").vw.setLeft((int)((60d / 100 * width)));
views.get("label9").vw.setWidth((int)((100d / 100 * width) - ((60d / 100 * width))));
views.get("clvpsi").vw.setTop((int)((views.get("label8").vw.getTop() + views.get("label8").vw.getHeight())));
views.get("clvpsi").vw.setHeight((int)((views.get("pnlpsirdg").vw.getHeight())-(views.get("label8").vw.getHeight())-(5d * scale)));
views.get("clvpsi").vw.setLeft((int)(0d));
views.get("clvpsi").vw.setWidth((int)((views.get("pnlpsirdg").vw.getWidth())));
views.get("btnaddpsi").vw.setLeft((int)((views.get("pnlpsirdg").vw.getLeft() + views.get("pnlpsirdg").vw.getWidth())-(views.get("btnaddpsi").vw.getWidth())-(20d * scale)));
views.get("btnaddpsi").vw.setTop((int)((views.get("clvpsi").vw.getTop() + views.get("clvpsi").vw.getHeight())-(views.get("btnaddpsi").vw.getHeight())-(20d * scale)));
views.get("pnlchlorinator").vw.setWidth((int)((100d / 100 * width)));
views.get("pnlchlorinator").vw.setLeft((int)(0d));
views.get("pnlchlorinator").vw.setTop((int)((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight())));
views.get("pnlchlorinator").vw.setHeight((int)((views.get("tabmenu").vw.getTop()) - ((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight()))));
views.get("label10").vw.setLeft((int)(0d));
views.get("label10").vw.setWidth((int)((35d / 100 * width) - (0d)));
views.get("label11").vw.setLeft((int)((35d / 100 * width)));
views.get("label11").vw.setWidth((int)((83d / 100 * width) - ((35d / 100 * width))));
views.get("label12").vw.setLeft((int)((83d / 100 * width)));
views.get("label12").vw.setWidth((int)((99d / 100 * width) - ((83d / 100 * width))));
views.get("clvchlorine").vw.setTop((int)((views.get("label10").vw.getTop() + views.get("label10").vw.getHeight())));
views.get("clvchlorine").vw.setHeight((int)((views.get("pnlchlorinator").vw.getHeight())-(views.get("label10").vw.getHeight())-(5d * scale)));
views.get("clvchlorine").vw.setLeft((int)(0d));
views.get("clvchlorine").vw.setWidth((int)((views.get("pnltime").vw.getWidth())));
views.get("btnaddchlorine").vw.setLeft((int)((views.get("pnlchlorinator").vw.getLeft() + views.get("pnlchlorinator").vw.getWidth())-(views.get("btnaddchlorine").vw.getWidth())-(20d * scale)));
views.get("btnaddchlorine").vw.setTop((int)((views.get("clvchlorine").vw.getTop() + views.get("clvchlorine").vw.getHeight())-(views.get("btnaddchlorine").vw.getHeight())-(20d * scale)));
views.get("pnlconcerns").vw.setWidth((int)((100d / 100 * width)));
views.get("pnlconcerns").vw.setLeft((int)(0d));
views.get("pnlconcerns").vw.setTop((int)((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight())));
views.get("pnlconcerns").vw.setHeight((int)((views.get("tabmenu").vw.getTop()) - ((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight()))));
views.get("label13").vw.setLeft((int)(0d));
views.get("label13").vw.setWidth((int)((28d / 100 * width) - (0d)));
views.get("label14").vw.setLeft((int)((28d / 100 * width)));
views.get("label14").vw.setWidth((int)((100d / 100 * width) - ((28d / 100 * width))));
views.get("clvconcerns").vw.setTop((int)((views.get("label13").vw.getTop() + views.get("label13").vw.getHeight())));
views.get("clvconcerns").vw.setHeight((int)((views.get("pnlconcerns").vw.getHeight())-(5d * scale)-(views.get("label13").vw.getHeight())-(5d * scale)));
views.get("clvconcerns").vw.setLeft((int)(0d));
views.get("clvconcerns").vw.setWidth((int)((views.get("pnlconcerns").vw.getWidth())));
views.get("btnaddconcerns").vw.setLeft((int)((views.get("pnlconcerns").vw.getLeft() + views.get("pnlconcerns").vw.getWidth())-(views.get("btnaddconcerns").vw.getWidth())-(20d * scale)));
views.get("btnaddconcerns").vw.setTop((int)((views.get("clvconcerns").vw.getTop() + views.get("clvconcerns").vw.getHeight())-(views.get("btnaddconcerns").vw.getHeight())-(15d * scale)));
views.get("pnladdeditfmrdg").vw.setWidth((int)((100d / 100 * width)));
views.get("pnladdeditfmrdg").vw.setHeight((int)((100d / 100 * height)));
views.get("pnladdeditfmrdg").vw.setLeft((int)(0d));
views.get("pnladdeditfmrdg").vw.setTop((int)(0d));
views.get("pnlfmrdgholder").vw.setHeight((int)((57d / 100 * height)));
views.get("pnlfmrdgholder").vw.setTop((int)(((views.get("pnladdeditfmrdg").vw.getHeight())/2d)-(50d * scale) - (views.get("pnlfmrdgholder").vw.getHeight() / 2)));
views.get("pnlfmrdgholder").vw.setLeft((int)((5d / 100 * width)));
views.get("pnlfmrdgholder").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("lbltitle").vw.setLeft((int)((0d * scale)));
views.get("lbltitle").vw.setWidth((int)((views.get("pnlfmrdgholder").vw.getWidth()) - ((0d * scale))));
views.get("pnlrdg").vw.setTop((int)((views.get("lbltitle").vw.getTop() + views.get("lbltitle").vw.getHeight())+(5d * scale)));
views.get("pnlrdg").vw.setHeight((int)((28d / 100 * height) - ((views.get("lbltitle").vw.getTop() + views.get("lbltitle").vw.getHeight())+(5d * scale))));
views.get("pnlrdg").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlrdg").vw.setWidth((int)((views.get("pnlfmrdgholder").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("chktimefmrdg").vw.setTop((int)((1d / 100 * height)));
views.get("chktimefmrdg").vw.setLeft((int)((2d / 100 * width)));
views.get("chktimefmrdg").vw.setWidth((int)((views.get("pnlrdg").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlanchorfm").vw.setTop((int)((views.get("chktimefmrdg").vw.getTop() + views.get("chktimefmrdg").vw.getHeight())+(5d * scale)));
views.get("pnlanchorfm").vw.setHeight((int)((views.get("pnlrdg").vw.getHeight())-(1d / 100 * height) - ((views.get("chktimefmrdg").vw.getTop() + views.get("chktimefmrdg").vw.getHeight())+(5d * scale))));
views.get("pnlanchorfm").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlanchorfm").vw.setWidth((int)((views.get("pnlrdg").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtfmrdg").vw.setTop((int)((1d / 100 * height)));
views.get("txtfmrdg").vw.setHeight((int)((views.get("pnlanchorfm").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("txtfmrdg").vw.setLeft((int)((1d / 100 * width)));
views.get("txtfmrdg").vw.setWidth((int)((views.get("pnlanchorfm").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlfmrdgremarks").vw.setTop((int)((views.get("pnlrdg").vw.getTop() + views.get("pnlrdg").vw.getHeight())+(5d * scale)));
views.get("pnlfmrdgremarks").vw.setHeight((int)((45d / 100 * height) - ((views.get("pnlrdg").vw.getTop() + views.get("pnlrdg").vw.getHeight())+(5d * scale))));
views.get("pnlfmrdgremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlfmrdgremarks").vw.setWidth((int)((views.get("pnlfmrdgholder").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblfmrdgremarks").vw.setLeft((int)((0d * scale)));
views.get("lblfmrdgremarks").vw.setWidth((int)((views.get("pnlfmrdgremarks").vw.getWidth()) - ((0d * scale))));
views.get("lblfmrdgremarks").vw.setTop((int)(0d));
views.get("pnlanchorfmrdgremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlanchorfmrdgremarks").vw.setWidth((int)((views.get("pnlfmrdgremarks").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlanchorfmrdgremarks").vw.setTop((int)((views.get("lblfmrdgremarks").vw.getTop() + views.get("lblfmrdgremarks").vw.getHeight())+(5d * scale)));
views.get("pnlanchorfmrdgremarks").vw.setHeight((int)((views.get("pnlfmrdgremarks").vw.getHeight())-(1d / 100 * height) - ((views.get("lblfmrdgremarks").vw.getTop() + views.get("lblfmrdgremarks").vw.getHeight())+(5d * scale))));
views.get("txtfmremarks").vw.setLeft((int)((1d / 100 * width)));
views.get("txtfmremarks").vw.setWidth((int)((views.get("pnlanchorfmrdgremarks").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtfmremarks").vw.setTop((int)((1d / 100 * height)));
views.get("txtfmremarks").vw.setHeight((int)((views.get("pnlanchorfmrdgremarks").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("btnsaveupdatefmrdg").vw.setTop((int)((views.get("pnlfmrdgremarks").vw.getTop() + views.get("pnlfmrdgremarks").vw.getHeight())+(10d * scale)));
views.get("btnsaveupdatefmrdg").vw.setHeight((int)((views.get("pnlfmrdgholder").vw.getHeight())-(1d / 100 * height) - ((views.get("pnlfmrdgremarks").vw.getTop() + views.get("pnlfmrdgremarks").vw.getHeight())+(10d * scale))));
views.get("btnsaveupdatefmrdg").vw.setLeft((int)((2d / 100 * width)));
views.get("btnsaveupdatefmrdg").vw.setWidth((int)((views.get("pnlfmrdgholder").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnladdeditpsirdg").vw.setWidth((int)((100d / 100 * width)));
views.get("pnladdeditpsirdg").vw.setHeight((int)((100d / 100 * height)));
views.get("pnladdeditpsirdg").vw.setLeft((int)(0d));
views.get("pnladdeditpsirdg").vw.setTop((int)(0d));
views.get("pnlpsirdgholder").vw.setHeight((int)((52d / 100 * height)));
views.get("pnlpsirdgholder").vw.setTop((int)(((views.get("pnladdeditfmrdg").vw.getHeight())/2d)-(50d * scale) - (views.get("pnlpsirdgholder").vw.getHeight() / 2)));
views.get("pnlpsirdgholder").vw.setLeft((int)((5d / 100 * width)));
views.get("pnlpsirdgholder").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("lbltitlepsi").vw.setLeft((int)((0d * scale)));
views.get("lbltitlepsi").vw.setWidth((int)((views.get("pnlfmrdgholder").vw.getWidth()) - ((0d * scale))));
views.get("pnlrdgpsi").vw.setTop((int)((views.get("lbltitle").vw.getTop() + views.get("lbltitle").vw.getHeight())+(5d * scale)));
views.get("pnlrdgpsi").vw.setHeight((int)((23d / 100 * height) - ((views.get("lbltitle").vw.getTop() + views.get("lbltitle").vw.getHeight())+(5d * scale))));
views.get("pnlrdgpsi").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlrdgpsi").vw.setWidth((int)((views.get("pnlfmrdgholder").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlanchorpsi").vw.setTop((int)((1d / 100 * height)));
views.get("pnlanchorpsi").vw.setHeight((int)((views.get("pnlrdg").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("pnlanchorpsi").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlanchorpsi").vw.setWidth((int)((views.get("pnlrdg").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txtpsirdg").vw.setTop((int)((1d / 100 * height)));
views.get("txtpsirdg").vw.setHeight((int)((views.get("pnlanchorfm").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("txtpsirdg").vw.setLeft((int)((1d / 100 * width)));
views.get("txtpsirdg").vw.setWidth((int)((views.get("pnlanchorfm").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlpsirdgremarks").vw.setTop((int)((views.get("pnlrdg").vw.getTop() + views.get("pnlrdg").vw.getHeight())+(5d * scale)));
views.get("pnlpsirdgremarks").vw.setHeight((int)((39d / 100 * height) - ((views.get("pnlrdg").vw.getTop() + views.get("pnlrdg").vw.getHeight())+(5d * scale))));
views.get("pnlpsirdgremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlpsirdgremarks").vw.setWidth((int)((views.get("pnlfmrdgholder").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblpsirdgremarks").vw.setLeft((int)((0d * scale)));
views.get("lblpsirdgremarks").vw.setWidth((int)((views.get("pnlfmrdgremarks").vw.getWidth()) - ((0d * scale))));
views.get("lblpsirdgremarks").vw.setTop((int)(0d));
views.get("pnlanchorpsirdgremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlanchorpsirdgremarks").vw.setWidth((int)((views.get("pnlfmrdgremarks").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlanchorpsirdgremarks").vw.setTop((int)((views.get("lblfmrdgremarks").vw.getTop() + views.get("lblfmrdgremarks").vw.getHeight())+(5d * scale)));
views.get("pnlanchorpsirdgremarks").vw.setHeight((int)((views.get("pnlfmrdgremarks").vw.getHeight())-(1d / 100 * height) - ((views.get("lblfmrdgremarks").vw.getTop() + views.get("lblfmrdgremarks").vw.getHeight())+(5d * scale))));
views.get("txtpsiremarks").vw.setLeft((int)((1d / 100 * width)));
views.get("txtpsiremarks").vw.setWidth((int)((views.get("pnlanchorfmrdgremarks").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("txtpsiremarks").vw.setTop((int)((1d / 100 * height)));
views.get("txtpsiremarks").vw.setHeight((int)((views.get("pnlanchorfmrdgremarks").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("btnsaveupdatepsirdg").vw.setTop((int)((views.get("pnlfmrdgremarks").vw.getTop() + views.get("pnlfmrdgremarks").vw.getHeight())+(10d * scale)));
views.get("btnsaveupdatepsirdg").vw.setHeight((int)((51d / 100 * height) - ((views.get("pnlfmrdgremarks").vw.getTop() + views.get("pnlfmrdgremarks").vw.getHeight())+(10d * scale))));
views.get("btnsaveupdatepsirdg").vw.setLeft((int)((2d / 100 * width)));
views.get("btnsaveupdatepsirdg").vw.setWidth((int)((views.get("pnlfmrdgholder").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlprobencdetails").vw.setWidth((int)((100d / 100 * width)));
views.get("pnlprobencdetails").vw.setHeight((int)((100d / 100 * height)));
views.get("pnlprobencdetails").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlprobencdetails").vw.setTop((int)((0d / 100 * height)));
views.get("pnlprobencmsgbox").vw.setLeft((int)((3d / 100 * width)));
views.get("pnlprobencmsgbox").vw.setWidth((int)((views.get("pnlprobencdetails").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
views.get("probdetailsicon").vw.setTop((int)((0d / 100 * height)));
views.get("probdetailsicon").vw.setLeft((int)((1.5d / 100 * width)));
views.get("lblprobenctitle").vw.setTop((int)((0d / 100 * height)));
views.get("lblprobenctitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblprobenctitle").vw.setWidth((int)((views.get("pnlprobencmsgbox").vw.getWidth()) - ((0d / 100 * width))));
views.get("lbl1").vw.setTop((int)((views.get("lblprobenctitle").vw.getTop() + views.get("lblprobenctitle").vw.getHeight())+(5d * scale)));
views.get("lbl1").vw.setLeft((int)((2d / 100 * width)));
views.get("lblpumpcode").vw.setTop((int)((views.get("lblprobenctitle").vw.getTop() + views.get("lblprobenctitle").vw.getHeight())+(5d * scale)));
views.get("lblpumpcode").vw.setLeft((int)((views.get("lbl1").vw.getLeft() + views.get("lbl1").vw.getWidth())+(2d * scale)));
views.get("lblpumpcode").vw.setWidth((int)((views.get("pnlprobencmsgbox").vw.getWidth())-(2d / 100 * width) - ((views.get("lbl1").vw.getLeft() + views.get("lbl1").vw.getWidth())+(2d * scale))));
views.get("lbl2").vw.setTop((int)((views.get("lbl1").vw.getTop() + views.get("lbl1").vw.getHeight())));
views.get("lbl2").vw.setLeft((int)((2d / 100 * width)));
views.get("lbltrandate").vw.setTop((int)((views.get("lblpumpcode").vw.getTop() + views.get("lblpumpcode").vw.getHeight())));
views.get("lbltrandate").vw.setLeft((int)((views.get("lbl2").vw.getLeft() + views.get("lbl2").vw.getWidth())+(2d * scale)));
views.get("lbltrandate").vw.setWidth((int)((views.get("pnlprobencmsgbox").vw.getWidth())-(2d / 100 * width) - ((views.get("lbl2").vw.getLeft() + views.get("lbl2").vw.getWidth())+(2d * scale))));
views.get("lbl3").vw.setTop((int)((views.get("lbl2").vw.getTop() + views.get("lbl2").vw.getHeight())));
views.get("lbl3").vw.setLeft((int)((2d / 100 * width)));
views.get("lbltimeenc").vw.setTop((int)((views.get("lbltrandate").vw.getTop() + views.get("lbltrandate").vw.getHeight())));
views.get("lbltimeenc").vw.setLeft((int)((views.get("lbl3").vw.getLeft() + views.get("lbl3").vw.getWidth())+(2d * scale)));
views.get("lbltimeenc").vw.setWidth((int)((views.get("pnlprobencmsgbox").vw.getWidth())-(2d / 100 * width) - ((views.get("lbl3").vw.getLeft() + views.get("lbl3").vw.getWidth())+(2d * scale))));
views.get("lbl4").vw.setTop((int)((views.get("lbl3").vw.getTop() + views.get("lbl3").vw.getHeight())));
views.get("lbl4").vw.setLeft((int)((2d / 100 * width)));
views.get("lblpumparea").vw.setTop((int)((views.get("lbltimeenc").vw.getTop() + views.get("lbltimeenc").vw.getHeight())));
views.get("lblpumparea").vw.setLeft((int)((views.get("lbl4").vw.getLeft() + views.get("lbl4").vw.getWidth())+(2d * scale)));
views.get("lblpumparea").vw.setWidth((int)((views.get("pnlprobencmsgbox").vw.getWidth())-(2d / 100 * width) - ((views.get("lbl4").vw.getLeft() + views.get("lbl4").vw.getWidth())+(2d * scale))));
views.get("chkcritical").vw.setTop((int)((views.get("lbl4").vw.getTop() + views.get("lbl4").vw.getHeight())));
views.get("chkcritical").vw.setLeft((int)((2d / 100 * width)));
views.get("lbl5").vw.setTop((int)((views.get("chkcritical").vw.getTop() + views.get("chkcritical").vw.getHeight())+(10d * scale)));
views.get("lbl5").vw.setLeft((int)((2d / 100 * width)));
views.get("lblprobtitle").vw.setTop((int)((views.get("lbl5").vw.getTop() + views.get("lbl5").vw.getHeight())));
views.get("lblprobtitle").vw.setLeft((int)((2d / 100 * width)));
views.get("lblprobtitle").vw.setWidth((int)((views.get("pnlprobencmsgbox").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblprobdesc").vw.setTop((int)((views.get("lblprobtitle").vw.getTop() + views.get("lblprobtitle").vw.getHeight())));
views.get("lblprobdesc").vw.setLeft((int)((5d / 100 * width)));
views.get("lblprobdesc").vw.setWidth((int)((views.get("pnlprobencmsgbox").vw.getWidth())-(2d / 100 * width) - ((5d / 100 * width))));
views.get("pnlprobsolved").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlprobsolved").vw.setWidth((int)((views.get("pnlprobencmsgbox").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlprobsolved").vw.setTop((int)((views.get("lblprobdesc").vw.getTop() + views.get("lblprobdesc").vw.getHeight())));
views.get("lbl6").vw.setTop((int)(0d));
views.get("lbl6").vw.setLeft((int)((2d / 100 * width)));
views.get("lblfindings").vw.setTop((int)((views.get("lbl6").vw.getTop() + views.get("lbl6").vw.getHeight())));
views.get("lblfindings").vw.setLeft((int)((5d / 100 * width)));
views.get("lblfindings").vw.setWidth((int)((views.get("pnlprobsolved").vw.getWidth())-(2d / 100 * width) - ((5d / 100 * width))));
views.get("lbl7").vw.setTop((int)((views.get("lblfindings").vw.getTop() + views.get("lblfindings").vw.getHeight())));
views.get("lbl7").vw.setLeft((int)((2d / 100 * width)));
views.get("lblactiontaken").vw.setTop((int)((views.get("lbl7").vw.getTop() + views.get("lbl7").vw.getHeight())));
views.get("lblactiontaken").vw.setLeft((int)((5d / 100 * width)));
views.get("lblactiontaken").vw.setWidth((int)((views.get("pnlprobsolved").vw.getWidth())-(2d / 100 * width) - ((5d / 100 * width))));
views.get("lbl8").vw.setTop((int)((views.get("lblactiontaken").vw.getTop() + views.get("lblactiontaken").vw.getHeight())));
views.get("lbl8").vw.setLeft((int)((2d / 100 * width)));
views.get("lblremarks").vw.setTop((int)((views.get("lbl8").vw.getTop() + views.get("lbl8").vw.getHeight())));
views.get("lblremarks").vw.setLeft((int)((5d / 100 * width)));
views.get("lblremarks").vw.setWidth((int)((views.get("pnlprobsolved").vw.getWidth())-(2d / 100 * width) - ((5d / 100 * width))));
views.get("btnsolved").vw.setTop((int)((views.get("pnlprobsolved").vw.getTop() + views.get("pnlprobsolved").vw.getHeight())+(5d * scale)));
views.get("btnsolved").vw.setHeight((int)((views.get("pnlprobencmsgbox").vw.getHeight())-(1d / 100 * height) - ((views.get("pnlprobsolved").vw.getTop() + views.get("pnlprobsolved").vw.getHeight())+(5d * scale))));
views.get("btnsolved").vw.setLeft((int)((2d / 100 * width)));
views.get("btnsolved").vw.setWidth((int)((views.get("pnlprobencmsgbox").vw.getWidth())-(55d / 100 * width) - ((2d / 100 * width))));
views.get("btneditprob").vw.setTop((int)((views.get("pnlprobsolved").vw.getTop() + views.get("pnlprobsolved").vw.getHeight())+(5d * scale)));
views.get("btneditprob").vw.setHeight((int)((views.get("pnlprobencmsgbox").vw.getHeight())-(1d / 100 * height) - ((views.get("pnlprobsolved").vw.getTop() + views.get("pnlprobsolved").vw.getHeight())+(5d * scale))));
views.get("btneditprob").vw.setLeft((int)((50d / 100 * width)));
views.get("btneditprob").vw.setWidth((int)((views.get("pnlprobencmsgbox").vw.getWidth())-(25d / 100 * width) - ((50d / 100 * width))));
views.get("btnprobencok").vw.setTop((int)((views.get("pnlprobsolved").vw.getTop() + views.get("pnlprobsolved").vw.getHeight())+(5d * scale)));
views.get("btnprobencok").vw.setHeight((int)((views.get("pnlprobencmsgbox").vw.getHeight())-(1d / 100 * height) - ((views.get("pnlprobsolved").vw.getTop() + views.get("pnlprobsolved").vw.getHeight())+(5d * scale))));
views.get("btnprobencok").vw.setLeft((int)((73d / 100 * width)));
views.get("btnprobencok").vw.setWidth((int)((views.get("pnlprobencmsgbox").vw.getWidth())-(2d / 100 * width) - ((73d / 100 * width))));
views.get("pnlpumpoff").vw.setTop((int)(0d));
views.get("pnlpumpoff").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("pnlpumpoff").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlpumpoff").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlpumpoffbackground").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlpumpoffbackground").vw.setWidth((int)((views.get("pnlpumpoff").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlpumpoffbackground").vw.setTop((int)((15d / 100 * height)));
views.get("pnlpumpoffbackground").vw.setHeight((int)((views.get("pnlpumpoff").vw.getHeight())-(35d / 100 * height) - ((15d / 100 * height))));
views.get("pnltimeoff").vw.setTop((int)((1d / 100 * height)));
views.get("pnltimeoff").vw.setHeight((int)((views.get("pnlpumpoffbackground").vw.getHeight())-(28d / 100 * height) - ((1d / 100 * height))));
views.get("pnltimeoff").vw.setLeft((int)((2d / 100 * width)));
views.get("pnltimeoff").vw.setWidth((int)((views.get("pnlpumpoffbackground").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblofftitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblofftitle").vw.setWidth((int)((views.get("pnlpumpoffbackground").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblofftitle").vw.setTop((int)(0d));
views.get("chkdefaulttimeoff").vw.setLeft((int)((2d / 100 * width)));
views.get("chkdefaulttimeoff").vw.setWidth((int)((views.get("pnltimeoff").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("chkdefaulttimeoff").vw.setTop((int)((views.get("lblofftitle").vw.getTop() + views.get("lblofftitle").vw.getHeight())+(5d * scale)));
views.get("label15").vw.setLeft((int)((3d / 100 * width)));
views.get("label15").vw.setTop((int)((views.get("chkdefaulttimeoff").vw.getTop() + views.get("chkdefaulttimeoff").vw.getHeight())+(5d * scale)));
views.get("pnltimeoffanchor").vw.setTop((int)((views.get("chkdefaulttimeoff").vw.getTop() + views.get("chkdefaulttimeoff").vw.getHeight())+(5d * scale)));
views.get("pnltimeoffanchor").vw.setTop((int)((views.get("label15").vw.getTop() + views.get("label15").vw.getHeight()/2) - (views.get("pnltimeoffanchor").vw.getHeight() / 2)));
views.get("pnltimeoffanchor").vw.setLeft((int)((views.get("label15").vw.getLeft() + views.get("label15").vw.getWidth())+(3d * scale)));
views.get("pnltimeoffanchor").vw.setWidth((int)((views.get("pnltimeoff").vw.getWidth())-(54d / 100 * width) - ((views.get("label15").vw.getLeft() + views.get("label15").vw.getWidth())+(3d * scale))));
views.get("msktimeoff").vw.setTop((int)((0.5d / 100 * height)));
views.get("msktimeoff").vw.setHeight((int)((views.get("pnltimeoffanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("msktimeoff").vw.setLeft((int)((1d / 100 * width)));
views.get("msktimeoff").vw.setWidth((int)((views.get("pnltimeoffanchor").vw.getWidth())-(19d * scale) - ((1d / 100 * width))));
views.get("pnlstyle5").vw.setTop((int)((views.get("chkdefaulttimeoff").vw.getTop() + views.get("chkdefaulttimeoff").vw.getHeight())+(5d * scale)));
views.get("pnlstyle5").vw.setLeft((int)((views.get("pnltimeoffanchor").vw.getLeft() + views.get("pnltimeoffanchor").vw.getWidth())-(18d * scale)));
views.get("pnlstyle5").vw.setWidth((int)((views.get("pnltimeoff").vw.getWidth())-(2d / 100 * width) - ((views.get("pnltimeoffanchor").vw.getLeft() + views.get("pnltimeoffanchor").vw.getWidth())-(18d * scale))));
//BA.debugLineNum = 252;BA.debugLine="Label16.SetLeftAndRight(1%x, pnlStyle5.Width-1%x)"[Production/General script]
views.get("label16").vw.setLeft((int)((1d / 100 * width)));
views.get("label16").vw.setWidth((int)((views.get("pnlstyle5").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 254;BA.debugLine="pnlCover2.Left = pnlStyle5.Left"[Production/General script]
views.get("pnlcover2").vw.setLeft((int)((views.get("pnlstyle5").vw.getLeft())));
//BA.debugLineNum = 255;BA.debugLine="pnlCover2.Top = chkDefaultTimeOff.Bottom + 5dip"[Production/General script]
views.get("pnlcover2").vw.setTop((int)((views.get("chkdefaulttimeoff").vw.getTop() + views.get("chkdefaulttimeoff").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 257;BA.debugLine="pnlOffRemarks.SetTopAndBottom(pnlTimeOff.Bottom + 10dip, pnlPumpOffBackground.Height - 10%y)"[Production/General script]
views.get("pnloffremarks").vw.setTop((int)((views.get("pnltimeoff").vw.getTop() + views.get("pnltimeoff").vw.getHeight())+(10d * scale)));
views.get("pnloffremarks").vw.setHeight((int)((views.get("pnlpumpoffbackground").vw.getHeight())-(10d / 100 * height) - ((views.get("pnltimeoff").vw.getTop() + views.get("pnltimeoff").vw.getHeight())+(10d * scale))));
//BA.debugLineNum = 258;BA.debugLine="pnlOffRemarks.SetLeftAndRight(2%x, pnlPumpOffBackground.Width - 2%x)"[Production/General script]
views.get("pnloffremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("pnloffremarks").vw.setWidth((int)((views.get("pnlpumpoffbackground").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 260;BA.debugLine="lblRemarksOffTitle.Top = 0dip"[Production/General script]
views.get("lblremarksofftitle").vw.setTop((int)((0d * scale)));
//BA.debugLineNum = 261;BA.debugLine="lblRemarksOffTitle.Left = 0dip"[Production/General script]
views.get("lblremarksofftitle").vw.setLeft((int)((0d * scale)));
//BA.debugLineNum = 262;BA.debugLine="lblRemarksOffTitle.SetLeftAndRight (0dip, 97%x)"[Production/General script]
views.get("lblremarksofftitle").vw.setLeft((int)((0d * scale)));
views.get("lblremarksofftitle").vw.setWidth((int)((97d / 100 * width) - ((0d * scale))));
//BA.debugLineNum = 264;BA.debugLine="pnlRemarksOffAnchor.SetLeftAndRight (2%x, pnlOffRemarks.Width - 2%x)"[Production/General script]
views.get("pnlremarksoffanchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlremarksoffanchor").vw.setWidth((int)((views.get("pnloffremarks").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 265;BA.debugLine="pnlRemarksOffAnchor.SetTopAndBottom(lblRemarksOffTitle.Bottom + 5dip, pnlOffRemarks.Height - 1.5%y)"[Production/General script]
views.get("pnlremarksoffanchor").vw.setTop((int)((views.get("lblremarksofftitle").vw.getTop() + views.get("lblremarksofftitle").vw.getHeight())+(5d * scale)));
views.get("pnlremarksoffanchor").vw.setHeight((int)((views.get("pnloffremarks").vw.getHeight())-(1.5d / 100 * height) - ((views.get("lblremarksofftitle").vw.getTop() + views.get("lblremarksofftitle").vw.getHeight())+(5d * scale))));
//BA.debugLineNum = 267;BA.debugLine="txtOffRemarks.SetLeftAndRight(3%x, pnlRemarksOffAnchor.Width - 3%x)"[Production/General script]
views.get("txtoffremarks").vw.setLeft((int)((3d / 100 * width)));
views.get("txtoffremarks").vw.setWidth((int)((views.get("pnlremarksoffanchor").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
//BA.debugLineNum = 268;BA.debugLine="txtOffRemarks.SetTopAndBottom(1%y, pnlRemarksOffAnchor.Height - 1%y)"[Production/General script]
views.get("txtoffremarks").vw.setTop((int)((1d / 100 * height)));
views.get("txtoffremarks").vw.setHeight((int)((views.get("pnlremarksoffanchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
//BA.debugLineNum = 270;BA.debugLine="btnPumpOffCancel.SetLeftAndRight(3%x, 46%x)"[Production/General script]
views.get("btnpumpoffcancel").vw.setLeft((int)((3d / 100 * width)));
views.get("btnpumpoffcancel").vw.setWidth((int)((46d / 100 * width) - ((3d / 100 * width))));
//BA.debugLineNum = 271;BA.debugLine="btnPumpOffCancel.SetTopAndBottom(pnlOffRemarks.Bottom + 10dip, pnlPumpOffBackground.Height - 1%y)"[Production/General script]
views.get("btnpumpoffcancel").vw.setTop((int)((views.get("pnloffremarks").vw.getTop() + views.get("pnloffremarks").vw.getHeight())+(10d * scale)));
views.get("btnpumpoffcancel").vw.setHeight((int)((views.get("pnlpumpoffbackground").vw.getHeight())-(1d / 100 * height) - ((views.get("pnloffremarks").vw.getTop() + views.get("pnloffremarks").vw.getHeight())+(10d * scale))));
//BA.debugLineNum = 273;BA.debugLine="btnPumpOffSave.SetLeftAndRight(52%x, pnlPumpOffBackground.Width - 3%x)"[Production/General script]
views.get("btnpumpoffsave").vw.setLeft((int)((52d / 100 * width)));
views.get("btnpumpoffsave").vw.setWidth((int)((views.get("pnlpumpoffbackground").vw.getWidth())-(3d / 100 * width) - ((52d / 100 * width))));
//BA.debugLineNum = 274;BA.debugLine="btnPumpOffSave.SetTopAndBottom(pnlOffRemarks.Bottom + 10dip, pnlPumpOffBackground.Height - 1%y)"[Production/General script]
views.get("btnpumpoffsave").vw.setTop((int)((views.get("pnloffremarks").vw.getTop() + views.get("pnloffremarks").vw.getHeight())+(10d * scale)));
views.get("btnpumpoffsave").vw.setHeight((int)((views.get("pnlpumpoffbackground").vw.getHeight())-(1d / 100 * height) - ((views.get("pnloffremarks").vw.getTop() + views.get("pnloffremarks").vw.getHeight())+(10d * scale))));

}
}