package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_chlorinatorrecords{

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
views.get("pnlmain").vw.setLeft((int)(0d));
views.get("pnlmain").vw.setTop((int)((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight())+(5d * scale)));
views.get("pnlmain").vw.setWidth((int)((100d / 100 * width)));
views.get("pnlmain").vw.setHeight((int)((100d / 100 * height)-(views.get("toolbar").vw.getHeight())-(5d * scale)));
views.get("pnlchlorinator").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlchlorinator").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("pnlchlorinator").vw.setTop((int)((0d / 100 * height)));
views.get("pnlchlorinator").vw.setHeight((int)((17d / 100 * height) - ((0d / 100 * height))));
views.get("lblchlorinatortitle").vw.setLeft((int)(0d));
views.get("lblchlorinatortitle").vw.setWidth((int)((views.get("pnlchlorinator").vw.getWidth()) - (0d)));
views.get("lblchlorinatortitle").vw.setTop((int)(0d));
views.get("lbl1").vw.setTop((int)((views.get("lblchlorinatortitle").vw.getTop() + views.get("lblchlorinatortitle").vw.getHeight())+(3d * scale)));
views.get("lbl1").vw.setLeft((int)((2d / 100 * width)));
views.get("lblcode").vw.setTop((int)((views.get("lblchlorinatortitle").vw.getTop() + views.get("lblchlorinatortitle").vw.getHeight())+(3d * scale)));
views.get("lblcode").vw.setLeft((int)((views.get("lbl1").vw.getLeft() + views.get("lbl1").vw.getWidth())+(10d * scale)));
views.get("lblcode").vw.setWidth((int)((views.get("pnlchlorinator").vw.getWidth())-(48d / 100 * width) - ((views.get("lbl1").vw.getLeft() + views.get("lbl1").vw.getWidth())+(10d * scale))));
views.get("lbl2").vw.setTop((int)((views.get("lbl1").vw.getTop())));
views.get("lbl2").vw.setLeft((int)((views.get("lblcode").vw.getLeft() + views.get("lblcode").vw.getWidth())+(3d * scale)));
views.get("lblbrand").vw.setTop((int)((views.get("lbl2").vw.getTop())));
views.get("lblbrand").vw.setLeft((int)((views.get("lbl2").vw.getLeft() + views.get("lbl2").vw.getWidth())+(10d * scale)));
views.get("lblbrand").vw.setWidth((int)((views.get("pnlchlorinator").vw.getWidth())-(2d / 100 * width) - ((views.get("lbl2").vw.getLeft() + views.get("lbl2").vw.getWidth())+(10d * scale))));
views.get("lbl3").vw.setTop((int)((views.get("lbl1").vw.getTop() + views.get("lbl1").vw.getHeight())+(3d * scale)));
views.get("lbl3").vw.setLeft((int)((2d / 100 * width)));
views.get("lblmodel").vw.setTop((int)((views.get("lbl3").vw.getTop())));
views.get("lblmodel").vw.setLeft((int)((views.get("lbl3").vw.getLeft() + views.get("lbl3").vw.getWidth())+(10d * scale)));
views.get("lblmodel").vw.setWidth((int)((views.get("pnlchlorinator").vw.getWidth())-(48d / 100 * width) - ((views.get("lbl3").vw.getLeft() + views.get("lbl3").vw.getWidth())+(10d * scale))));
views.get("lbl4").vw.setTop((int)((views.get("lbl3").vw.getTop())));
views.get("lbl4").vw.setLeft((int)((views.get("lblmodel").vw.getLeft() + views.get("lblmodel").vw.getWidth())+(3d * scale)));
views.get("lblserial").vw.setTop((int)((views.get("lbl4").vw.getTop())));
views.get("lblserial").vw.setLeft((int)((views.get("lbl4").vw.getLeft() + views.get("lbl4").vw.getWidth())+(10d * scale)));
views.get("lblserial").vw.setWidth((int)((views.get("pnlchlorinator").vw.getWidth())-(2d / 100 * width) - ((views.get("lbl4").vw.getLeft() + views.get("lbl4").vw.getWidth())+(10d * scale))));
views.get("lbl5").vw.setTop((int)((views.get("lbl4").vw.getTop() + views.get("lbl4").vw.getHeight())+(3d * scale)));
views.get("lbl5").vw.setLeft((int)((2d / 100 * width)));
views.get("lblspm").vw.setTop((int)((views.get("lblserial").vw.getTop() + views.get("lblserial").vw.getHeight())+(3d * scale)));
views.get("lblspm").vw.setLeft((int)((views.get("lbl5").vw.getLeft() + views.get("lbl5").vw.getWidth())+(10d * scale)));
views.get("lblspm").vw.setWidth((int)((views.get("pnlchlorinator").vw.getWidth())-(48d / 100 * width) - ((views.get("lbl5").vw.getLeft() + views.get("lbl5").vw.getWidth())+(10d * scale))));
views.get("pnlreplenishtime").vw.setTop((int)((views.get("pnlchlorinator").vw.getTop() + views.get("pnlchlorinator").vw.getHeight())+(5d * scale)));
views.get("pnlreplenishtime").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(55d / 100 * height) - ((views.get("pnlchlorinator").vw.getTop() + views.get("pnlchlorinator").vw.getHeight())+(5d * scale))));
views.get("pnlreplenishtime").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlreplenishtime").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lblrdgtimetitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblrdgtimetitle").vw.setWidth((int)((views.get("pnlreplenishtime").vw.getWidth()) - ((0d / 100 * width))));
views.get("lblrdgtimetitle").vw.setTop((int)(0d));
views.get("chkdefaulttimereplenish").vw.setLeft((int)((2d / 100 * width)));
views.get("chkdefaulttimereplenish").vw.setWidth((int)((views.get("pnlreplenishtime").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("chkdefaulttimereplenish").vw.setTop((int)((views.get("lblrdgtimetitle").vw.getTop() + views.get("lblrdgtimetitle").vw.getHeight())+(5d * scale)));
views.get("label1").vw.setLeft((int)((3d / 100 * width)));
views.get("label1").vw.setTop((int)((views.get("chkdefaulttimereplenish").vw.getTop() + views.get("chkdefaulttimereplenish").vw.getHeight())+(5d * scale)));
views.get("pnlrdgtimeanchor").vw.setTop((int)((views.get("chkdefaulttimereplenish").vw.getTop() + views.get("chkdefaulttimereplenish").vw.getHeight())+(5d * scale)));
views.get("pnlrdgtimeanchor").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight()/2) - (views.get("pnlrdgtimeanchor").vw.getHeight() / 2)));
views.get("pnlrdgtimeanchor").vw.setLeft((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale)));
views.get("pnlrdgtimeanchor").vw.setWidth((int)((views.get("pnlreplenishtime").vw.getWidth())-(40d / 100 * width) - ((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale))));
views.get("msktimereplenish").vw.setTop((int)((0.5d / 100 * height)));
views.get("msktimereplenish").vw.setHeight((int)((views.get("pnlrdgtimeanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("msktimereplenish").vw.setLeft((int)((1d / 100 * width)));
views.get("msktimereplenish").vw.setWidth((int)((views.get("pnlrdgtimeanchor").vw.getWidth())-(19d * scale) - ((1d / 100 * width))));
views.get("pnlstyle5").vw.setTop((int)((views.get("chkdefaulttimereplenish").vw.getTop() + views.get("chkdefaulttimereplenish").vw.getHeight())+(5d * scale)));
views.get("pnlstyle5").vw.setLeft((int)((views.get("pnlrdgtimeanchor").vw.getLeft() + views.get("pnlrdgtimeanchor").vw.getWidth())-(18d * scale)));
views.get("pnlstyle5").vw.setWidth((int)((views.get("pnlreplenishtime").vw.getWidth())-(2d / 100 * width) - ((views.get("pnlrdgtimeanchor").vw.getLeft() + views.get("pnlrdgtimeanchor").vw.getWidth())-(18d * scale))));
views.get("label16").vw.setLeft((int)((1d / 100 * width)));
views.get("label16").vw.setWidth((int)((views.get("pnlstyle5").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlcover2").vw.setLeft((int)((views.get("pnlstyle5").vw.getLeft())));
//BA.debugLineNum = 78;BA.debugLine="pnlCover2.Top = chkDefaultTimeReplenish.Bottom + 5dip"[ChlorinatorRecords/General script]
views.get("pnlcover2").vw.setTop((int)((views.get("chkdefaulttimereplenish").vw.getTop() + views.get("chkdefaulttimereplenish").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 81;BA.debugLine="pnlChlorine.SetLeftAndRight(2%x, pnlMain.Width - 2%x)"[ChlorinatorRecords/General script]
views.get("pnlchlorine").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlchlorine").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 82;BA.debugLine="pnlChlorine.SetTopAndBottom(pnlReplenishTime.Bottom + 10dip, pnlMain.Height - 27.5%y)"[ChlorinatorRecords/General script]
views.get("pnlchlorine").vw.setTop((int)((views.get("pnlreplenishtime").vw.getTop() + views.get("pnlreplenishtime").vw.getHeight())+(10d * scale)));
views.get("pnlchlorine").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(27.5d / 100 * height) - ((views.get("pnlreplenishtime").vw.getTop() + views.get("pnlreplenishtime").vw.getHeight())+(10d * scale))));
//BA.debugLineNum = 84;BA.debugLine="lblChlorineTitle.SetLeftAndRight(0, pnlChlorine.Width)"[ChlorinatorRecords/General script]
views.get("lblchlorinetitle").vw.setLeft((int)(0d));
views.get("lblchlorinetitle").vw.setWidth((int)((views.get("pnlchlorine").vw.getWidth()) - (0d)));
//BA.debugLineNum = 85;BA.debugLine="lbl6.Top = lblChlorineTitle.Bottom + 5dip"[ChlorinatorRecords/General script]
views.get("lbl6").vw.setTop((int)((views.get("lblchlorinetitle").vw.getTop() + views.get("lblchlorinetitle").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 86;BA.debugLine="pnlTypeAnchor.Top = lblChlorineTitle.Bottom + 5dip"[ChlorinatorRecords/General script]
views.get("pnltypeanchor").vw.setTop((int)((views.get("lblchlorinetitle").vw.getTop() + views.get("lblchlorinetitle").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 87;BA.debugLine="pnlTypeAnchor.SetLeftAndRight(lbl6.Right + 5dip, pnlChlorine.Width - 2%x)"[ChlorinatorRecords/General script]
views.get("pnltypeanchor").vw.setLeft((int)((views.get("lbl6").vw.getLeft() + views.get("lbl6").vw.getWidth())+(5d * scale)));
views.get("pnltypeanchor").vw.setWidth((int)((views.get("pnlchlorine").vw.getWidth())-(2d / 100 * width) - ((views.get("lbl6").vw.getLeft() + views.get("lbl6").vw.getWidth())+(5d * scale))));
//BA.debugLineNum = 88;BA.debugLine="cboType.SetLeftAndRight(1.5%x, pnlTypeAnchor.Width - 1.5%x)"[ChlorinatorRecords/General script]
views.get("cbotype").vw.setLeft((int)((1.5d / 100 * width)));
views.get("cbotype").vw.setWidth((int)((views.get("pnltypeanchor").vw.getWidth())-(1.5d / 100 * width) - ((1.5d / 100 * width))));
//BA.debugLineNum = 89;BA.debugLine="cboType.SetTopAndBottom(0.25%y, pnlTypeAnchor.Height - 0.25%y)"[ChlorinatorRecords/General script]
views.get("cbotype").vw.setTop((int)((0.25d / 100 * height)));
views.get("cbotype").vw.setHeight((int)((views.get("pnltypeanchor").vw.getHeight())-(0.25d / 100 * height) - ((0.25d / 100 * height))));
//BA.debugLineNum = 91;BA.debugLine="lbl7.Top = lbl6.Bottom + 5dip"[ChlorinatorRecords/General script]
views.get("lbl7").vw.setTop((int)((views.get("lbl6").vw.getTop() + views.get("lbl6").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 92;BA.debugLine="pnlVolAnchor.Top = pnlTypeAnchor.Bottom + 5dip"[ChlorinatorRecords/General script]
views.get("pnlvolanchor").vw.setTop((int)((views.get("pnltypeanchor").vw.getTop() + views.get("pnltypeanchor").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 93;BA.debugLine="pnlVolAnchor.SetLeftAndRight(lbl7.Right + 5dip, pnlChlorine.Width - 40%x)"[ChlorinatorRecords/General script]
views.get("pnlvolanchor").vw.setLeft((int)((views.get("lbl7").vw.getLeft() + views.get("lbl7").vw.getWidth())+(5d * scale)));
views.get("pnlvolanchor").vw.setWidth((int)((views.get("pnlchlorine").vw.getWidth())-(40d / 100 * width) - ((views.get("lbl7").vw.getLeft() + views.get("lbl7").vw.getWidth())+(5d * scale))));
//BA.debugLineNum = 94;BA.debugLine="txtVolume.SetLeftAndRight(1%x, pnlVolAnchor.Width - 15%x)"[ChlorinatorRecords/General script]
views.get("txtvolume").vw.setLeft((int)((1d / 100 * width)));
views.get("txtvolume").vw.setWidth((int)((views.get("pnlvolanchor").vw.getWidth())-(15d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 95;BA.debugLine="txtVolume.SetTopAndBottom(0.15%y, pnlVolAnchor.Height - 0.15%y)"[ChlorinatorRecords/General script]
views.get("txtvolume").vw.setTop((int)((0.15d / 100 * height)));
views.get("txtvolume").vw.setHeight((int)((views.get("pnlvolanchor").vw.getHeight())-(0.15d / 100 * height) - ((0.15d / 100 * height))));
//BA.debugLineNum = 96;BA.debugLine="Panel1.SetTopAndBottom(1dip, pnlVolAnchor.Height-1dip)"[ChlorinatorRecords/General script]
views.get("panel1").vw.setTop((int)((1d * scale)));
views.get("panel1").vw.setHeight((int)((views.get("pnlvolanchor").vw.getHeight())-(1d * scale) - ((1d * scale))));
//BA.debugLineNum = 97;BA.debugLine="Panel1.SetLeftAndRight(txtVolume.Right, pnlVolAnchor.Width - 11%x)"[ChlorinatorRecords/General script]
views.get("panel1").vw.setLeft((int)((views.get("txtvolume").vw.getLeft() + views.get("txtvolume").vw.getWidth())));
views.get("panel1").vw.setWidth((int)((views.get("pnlvolanchor").vw.getWidth())-(11d / 100 * width) - ((views.get("txtvolume").vw.getLeft() + views.get("txtvolume").vw.getWidth()))));
//BA.debugLineNum = 99;BA.debugLine="lblUnit.SetTopAndBottom(0, pnlVolAnchor.Height)"[ChlorinatorRecords/General script]
views.get("lblunit").vw.setTop((int)(0d));
views.get("lblunit").vw.setHeight((int)((views.get("pnlvolanchor").vw.getHeight()) - (0d)));
//BA.debugLineNum = 100;BA.debugLine="lblUnit.SetLeftAndRight(Panel1.Right - 5dip, pnlVolAnchor.Width)"[ChlorinatorRecords/General script]
views.get("lblunit").vw.setLeft((int)((views.get("panel1").vw.getLeft() + views.get("panel1").vw.getWidth())-(5d * scale)));
views.get("lblunit").vw.setWidth((int)((views.get("pnlvolanchor").vw.getWidth()) - ((views.get("panel1").vw.getLeft() + views.get("panel1").vw.getWidth())-(5d * scale))));
//BA.debugLineNum = 102;BA.debugLine="lbl8.Top = lbl7.Bottom + 5dip"[ChlorinatorRecords/General script]
views.get("lbl8").vw.setTop((int)((views.get("lbl7").vw.getTop() + views.get("lbl7").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 103;BA.debugLine="lbl8.Left = lbl6.Left"[ChlorinatorRecords/General script]
views.get("lbl8").vw.setLeft((int)((views.get("lbl6").vw.getLeft())));
//BA.debugLineNum = 104;BA.debugLine="pnlSPMRateAnchor.Top = pnlVolAnchor.Bottom + 5dip"[ChlorinatorRecords/General script]
views.get("pnlspmrateanchor").vw.setTop((int)((views.get("pnlvolanchor").vw.getTop() + views.get("pnlvolanchor").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 105;BA.debugLine="pnlSPMRateAnchor.SetLeftAndRight(lbl8.Right + 5dip, pnlChlorine.Width - 35%x)"[ChlorinatorRecords/General script]
views.get("pnlspmrateanchor").vw.setLeft((int)((views.get("lbl8").vw.getLeft() + views.get("lbl8").vw.getWidth())+(5d * scale)));
views.get("pnlspmrateanchor").vw.setWidth((int)((views.get("pnlchlorine").vw.getWidth())-(35d / 100 * width) - ((views.get("lbl8").vw.getLeft() + views.get("lbl8").vw.getWidth())+(5d * scale))));
//BA.debugLineNum = 106;BA.debugLine="txtSPMRate.SetLeftAndRight(1%x, pnlSPMRateAnchor.Width - 15%x)"[ChlorinatorRecords/General script]
views.get("txtspmrate").vw.setLeft((int)((1d / 100 * width)));
views.get("txtspmrate").vw.setWidth((int)((views.get("pnlspmrateanchor").vw.getWidth())-(15d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 107;BA.debugLine="txtSPMRate.SetTopAndBottom(0.15%y, pnlSPMRateAnchor.Height - 0.15%y)"[ChlorinatorRecords/General script]
views.get("txtspmrate").vw.setTop((int)((0.15d / 100 * height)));
views.get("txtspmrate").vw.setHeight((int)((views.get("pnlspmrateanchor").vw.getHeight())-(0.15d / 100 * height) - ((0.15d / 100 * height))));
//BA.debugLineNum = 109;BA.debugLine="lblSPMRate.SetTopAndBottom(0, pnlSPMRateAnchor.Height)"[ChlorinatorRecords/General script]
views.get("lblspmrate").vw.setTop((int)(0d));
views.get("lblspmrate").vw.setHeight((int)((views.get("pnlspmrateanchor").vw.getHeight()) - (0d)));
//BA.debugLineNum = 110;BA.debugLine="lblSPMRate.SetLeftAndRight(txtSPMRate.Right + 10dip, pnlSPMRateAnchor.Width)"[ChlorinatorRecords/General script]
views.get("lblspmrate").vw.setLeft((int)((views.get("txtspmrate").vw.getLeft() + views.get("txtspmrate").vw.getWidth())+(10d * scale)));
views.get("lblspmrate").vw.setWidth((int)((views.get("pnlspmrateanchor").vw.getWidth()) - ((views.get("txtspmrate").vw.getLeft() + views.get("txtspmrate").vw.getWidth())+(10d * scale))));
//BA.debugLineNum = 111;BA.debugLine="Panel2.SetTopAndBottom(1dip, pnlSPMRateAnchor.Height-1dip)"[ChlorinatorRecords/General script]
views.get("panel2").vw.setTop((int)((1d * scale)));
views.get("panel2").vw.setHeight((int)((views.get("pnlspmrateanchor").vw.getHeight())-(1d * scale) - ((1d * scale))));
//BA.debugLineNum = 112;BA.debugLine="Panel2.SetLeftAndRight(txtSPMRate.Right + 5dip, pnlSPMRateAnchor.Width - 11%x)"[ChlorinatorRecords/General script]
views.get("panel2").vw.setLeft((int)((views.get("txtspmrate").vw.getLeft() + views.get("txtspmrate").vw.getWidth())+(5d * scale)));
views.get("panel2").vw.setWidth((int)((views.get("pnlspmrateanchor").vw.getWidth())-(11d / 100 * width) - ((views.get("txtspmrate").vw.getLeft() + views.get("txtspmrate").vw.getWidth())+(5d * scale))));
//BA.debugLineNum = 114;BA.debugLine="pnlSPMPercentAnchor.Top = pnlVolAnchor.Bottom + 5dip"[ChlorinatorRecords/General script]
views.get("pnlspmpercentanchor").vw.setTop((int)((views.get("pnlvolanchor").vw.getTop() + views.get("pnlvolanchor").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 115;BA.debugLine="pnlSPMPercentAnchor.SetLeftAndRight(pnlSPMRateAnchor.Right + 5dip, pnlChlorine.Width - 2%x)"[ChlorinatorRecords/General script]
views.get("pnlspmpercentanchor").vw.setLeft((int)((views.get("pnlspmrateanchor").vw.getLeft() + views.get("pnlspmrateanchor").vw.getWidth())+(5d * scale)));
views.get("pnlspmpercentanchor").vw.setWidth((int)((views.get("pnlchlorine").vw.getWidth())-(2d / 100 * width) - ((views.get("pnlspmrateanchor").vw.getLeft() + views.get("pnlspmrateanchor").vw.getWidth())+(5d * scale))));
//BA.debugLineNum = 116;BA.debugLine="txtSPMPercent.SetLeftAndRight(1%x, pnlSPMPercentAnchor.Width - 15%x)"[ChlorinatorRecords/General script]
views.get("txtspmpercent").vw.setLeft((int)((1d / 100 * width)));
views.get("txtspmpercent").vw.setWidth((int)((views.get("pnlspmpercentanchor").vw.getWidth())-(15d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 117;BA.debugLine="txtSPMPercent.SetTopAndBottom(0.15%y, pnlSPMPercentAnchor.Height - 0.15%y)"[ChlorinatorRecords/General script]
views.get("txtspmpercent").vw.setTop((int)((0.15d / 100 * height)));
views.get("txtspmpercent").vw.setHeight((int)((views.get("pnlspmpercentanchor").vw.getHeight())-(0.15d / 100 * height) - ((0.15d / 100 * height))));
//BA.debugLineNum = 118;BA.debugLine="lblSPMPercent.SetTopAndBottom(0, pnlSPMPercentAnchor.Height)"[ChlorinatorRecords/General script]
views.get("lblspmpercent").vw.setTop((int)(0d));
views.get("lblspmpercent").vw.setHeight((int)((views.get("pnlspmpercentanchor").vw.getHeight()) - (0d)));
//BA.debugLineNum = 119;BA.debugLine="lblSPMPercent.SetLeftAndRight(txtSPMPercent.Right + 18dip, pnlSPMPercentAnchor.Width)"[ChlorinatorRecords/General script]
views.get("lblspmpercent").vw.setLeft((int)((views.get("txtspmpercent").vw.getLeft() + views.get("txtspmpercent").vw.getWidth())+(18d * scale)));
views.get("lblspmpercent").vw.setWidth((int)((views.get("pnlspmpercentanchor").vw.getWidth()) - ((views.get("txtspmpercent").vw.getLeft() + views.get("txtspmpercent").vw.getWidth())+(18d * scale))));
//BA.debugLineNum = 120;BA.debugLine="Panel3.SetTopAndBottom(1dip, pnlSPMPercentAnchor.Height-1dip)"[ChlorinatorRecords/General script]
views.get("panel3").vw.setTop((int)((1d * scale)));
views.get("panel3").vw.setHeight((int)((views.get("pnlspmpercentanchor").vw.getHeight())-(1d * scale) - ((1d * scale))));
//BA.debugLineNum = 121;BA.debugLine="Panel3.SetLeftAndRight(txtSPMPercent.Right + 5dip, pnlSPMPercentAnchor.Width - 8%x)"[ChlorinatorRecords/General script]
views.get("panel3").vw.setLeft((int)((views.get("txtspmpercent").vw.getLeft() + views.get("txtspmpercent").vw.getWidth())+(5d * scale)));
views.get("panel3").vw.setWidth((int)((views.get("pnlspmpercentanchor").vw.getWidth())-(8d / 100 * width) - ((views.get("txtspmpercent").vw.getLeft() + views.get("txtspmpercent").vw.getWidth())+(5d * scale))));
//BA.debugLineNum = 124;BA.debugLine="pnlRemarks.SetLeftAndRight(2%x, pnlMain.Width - 2%x)"[ChlorinatorRecords/General script]
views.get("pnlremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlremarks").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 125;BA.debugLine="pnlRemarks.SetTopAndBottom(pnlChlorine.Bottom + 10dip, pnlMain.Height - 12%y)"[ChlorinatorRecords/General script]
views.get("pnlremarks").vw.setTop((int)((views.get("pnlchlorine").vw.getTop() + views.get("pnlchlorine").vw.getHeight())+(10d * scale)));
views.get("pnlremarks").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(12d / 100 * height) - ((views.get("pnlchlorine").vw.getTop() + views.get("pnlchlorine").vw.getHeight())+(10d * scale))));
//BA.debugLineNum = 126;BA.debugLine="lblRemarksTitle.SetLeftAndRight(0, pnlRemarks.Width)"[ChlorinatorRecords/General script]
views.get("lblremarkstitle").vw.setLeft((int)(0d));
views.get("lblremarkstitle").vw.setWidth((int)((views.get("pnlremarks").vw.getWidth()) - (0d)));
//BA.debugLineNum = 127;BA.debugLine="lblRemarksTitle.Top=0"[ChlorinatorRecords/General script]
views.get("lblremarkstitle").vw.setTop((int)(0d));
//BA.debugLineNum = 128;BA.debugLine="Panel4.SetLeftAndRight(2%x, pnlRemarks.Width - 2%x)"[ChlorinatorRecords/General script]
views.get("panel4").vw.setLeft((int)((2d / 100 * width)));
views.get("panel4").vw.setWidth((int)((views.get("pnlremarks").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 129;BA.debugLine="Panel4.SetTopAndBottom(lblRemarksTitle.Bottom + 5dip, pnlRemarks.Height - 2%y)"[ChlorinatorRecords/General script]
views.get("panel4").vw.setTop((int)((views.get("lblremarkstitle").vw.getTop() + views.get("lblremarkstitle").vw.getHeight())+(5d * scale)));
views.get("panel4").vw.setHeight((int)((views.get("pnlremarks").vw.getHeight())-(2d / 100 * height) - ((views.get("lblremarkstitle").vw.getTop() + views.get("lblremarkstitle").vw.getHeight())+(5d * scale))));
//BA.debugLineNum = 130;BA.debugLine="txtRemarks.SetLeftAndRight(2%x, Panel4.Width - 2%x)"[ChlorinatorRecords/General script]
views.get("txtremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("txtremarks").vw.setWidth((int)((views.get("panel4").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 131;BA.debugLine="txtRemarks.SetTopAndBottom(0.5%y, Panel4.Height - 0.5%y)"[ChlorinatorRecords/General script]
views.get("txtremarks").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtremarks").vw.setHeight((int)((views.get("panel4").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
//BA.debugLineNum = 133;BA.debugLine="btnSaveUpdate.SetLeftAndRight(2%x, pnlMain.Width - 2%x)"[ChlorinatorRecords/General script]
views.get("btnsaveupdate").vw.setLeft((int)((2d / 100 * width)));
views.get("btnsaveupdate").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 134;BA.debugLine="btnSaveUpdate.SetTopAndBottom(pnlRemarks.Bottom + 10dip, pnlMain.Height - 1%y)"[ChlorinatorRecords/General script]
views.get("btnsaveupdate").vw.setTop((int)((views.get("pnlremarks").vw.getTop() + views.get("pnlremarks").vw.getHeight())+(10d * scale)));
views.get("btnsaveupdate").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(1d / 100 * height) - ((views.get("pnlremarks").vw.getTop() + views.get("pnlremarks").vw.getHeight())+(10d * scale))));

}
}