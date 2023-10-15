package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_addeditpumpnonoperational{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[AddEditPumpNonOperational/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 3;BA.debugLine="ToolBar.Top = 0dip"[AddEditPumpNonOperational/General script]
views.get("toolbar").vw.setTop((int)((0d * scale)));
//BA.debugLineNum = 5;BA.debugLine="If ActivitySize > 6.5 Then"[AddEditPumpNonOperational/General script]
if ((anywheresoftware.b4a.keywords.LayoutBuilder.getScreenSize()>6.5d)) { 
;
//BA.debugLineNum = 6;BA.debugLine="ToolBar.Height = 64dip"[AddEditPumpNonOperational/General script]
views.get("toolbar").vw.setHeight((int)((64d * scale)));
//BA.debugLineNum = 7;BA.debugLine="Else"[AddEditPumpNonOperational/General script]
;}else{ 
;
//BA.debugLineNum = 8;BA.debugLine="If Portrait Then"[AddEditPumpNonOperational/General script]
if ((BA.ObjectToBoolean( String.valueOf(anywheresoftware.b4a.keywords.LayoutBuilder.isPortrait())))) { 
;
//BA.debugLineNum = 9;BA.debugLine="ToolBar.Height = 50dip"[AddEditPumpNonOperational/General script]
views.get("toolbar").vw.setHeight((int)((50d * scale)));
//BA.debugLineNum = 10;BA.debugLine="Else"[AddEditPumpNonOperational/General script]
;}else{ 
;
//BA.debugLineNum = 11;BA.debugLine="ToolBar.Height = 42dip"[AddEditPumpNonOperational/General script]
views.get("toolbar").vw.setHeight((int)((42d * scale)));
//BA.debugLineNum = 12;BA.debugLine="End If"[AddEditPumpNonOperational/General script]
;};
//BA.debugLineNum = 13;BA.debugLine="End If"[AddEditPumpNonOperational/General script]
;};
//BA.debugLineNum = 16;BA.debugLine="pnlMain.SetTopAndBottom(ToolBar.Bottom, 100%y)"[AddEditPumpNonOperational/General script]
views.get("pnlmain").vw.setTop((int)((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight())));
views.get("pnlmain").vw.setHeight((int)((100d / 100 * height) - ((views.get("toolbar").vw.getTop() + views.get("toolbar").vw.getHeight()))));
//BA.debugLineNum = 17;BA.debugLine="pnlMain.SetLeftAndRight(0%x, 100%x)"[AddEditPumpNonOperational/General script]
views.get("pnlmain").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlmain").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
//BA.debugLineNum = 20;BA.debugLine="pnlTime.SetTopAndBottom(1%y, pnlMain.Height - 70%y)"[AddEditPumpNonOperational/General script]
views.get("pnltime").vw.setTop((int)((1d / 100 * height)));
views.get("pnltime").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(70d / 100 * height) - ((1d / 100 * height))));
//BA.debugLineNum = 21;BA.debugLine="pnlTime.SetLeftAndRight(2%x, pnlMain.Width - 2%x)"[AddEditPumpNonOperational/General script]
views.get("pnltime").vw.setLeft((int)((2d / 100 * width)));
views.get("pnltime").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 23;BA.debugLine="lblOnOffTitle.SetLeftAndRight(0%x, pnlTime.Width)"[AddEditPumpNonOperational/General script]
views.get("lblonofftitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblonofftitle").vw.setWidth((int)((views.get("pnltime").vw.getWidth()) - ((0d / 100 * width))));
//BA.debugLineNum = 24;BA.debugLine="lblOnOffTitle.Top = 0"[AddEditPumpNonOperational/General script]
views.get("lblonofftitle").vw.setTop((int)(0d));
//BA.debugLineNum = 26;BA.debugLine="Label1.Left = 2%x"[AddEditPumpNonOperational/General script]
views.get("label1").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 27;BA.debugLine="Label1.Top = lblOnOffTitle.Bottom + 10dip"[AddEditPumpNonOperational/General script]
views.get("label1").vw.setTop((int)((views.get("lblonofftitle").vw.getTop() + views.get("lblonofftitle").vw.getHeight())+(10d * scale)));
//BA.debugLineNum = 29;BA.debugLine="pnlTimeOnAnchor.Top = lblOnOffTitle.Bottom + 10dip"[AddEditPumpNonOperational/General script]
views.get("pnltimeonanchor").vw.setTop((int)((views.get("lblonofftitle").vw.getTop() + views.get("lblonofftitle").vw.getHeight())+(10d * scale)));
//BA.debugLineNum = 30;BA.debugLine="pnlTimeOnAnchor.SetLeftAndRight(Label1.Right + 3dip, pnlTime.Width - 45%x)"[AddEditPumpNonOperational/General script]
views.get("pnltimeonanchor").vw.setLeft((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale)));
views.get("pnltimeonanchor").vw.setWidth((int)((views.get("pnltime").vw.getWidth())-(45d / 100 * width) - ((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())+(3d * scale))));
//BA.debugLineNum = 32;BA.debugLine="mskTimeStart.SetTopAndBottom(0.5%y, pnlTimeOnAnchor.Height - 0.5%y)"[AddEditPumpNonOperational/General script]
views.get("msktimestart").vw.setTop((int)((0.5d / 100 * height)));
views.get("msktimestart").vw.setHeight((int)((views.get("pnltimeonanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
//BA.debugLineNum = 33;BA.debugLine="mskTimeStart.SetLeftAndRight(1%x, pnlTimeOnAnchor.Width - 5%x)"[AddEditPumpNonOperational/General script]
views.get("msktimestart").vw.setLeft((int)((1d / 100 * width)));
views.get("msktimestart").vw.setWidth((int)((views.get("pnltimeonanchor").vw.getWidth())-(5d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 35;BA.debugLine="pnlStyle1.Top = lblOnOffTitle.Bottom + 10dip"[AddEditPumpNonOperational/General script]
views.get("pnlstyle1").vw.setTop((int)((views.get("lblonofftitle").vw.getTop() + views.get("lblonofftitle").vw.getHeight())+(10d * scale)));
//BA.debugLineNum = 36;BA.debugLine="pnlStyle1.SetLeftAndRight(pnlTimeOnAnchor.Right - 18dip, pnlTime.Width - 2%x)"[AddEditPumpNonOperational/General script]
views.get("pnlstyle1").vw.setLeft((int)((views.get("pnltimeonanchor").vw.getLeft() + views.get("pnltimeonanchor").vw.getWidth())-(18d * scale)));
views.get("pnlstyle1").vw.setWidth((int)((views.get("pnltime").vw.getWidth())-(2d / 100 * width) - ((views.get("pnltimeonanchor").vw.getLeft() + views.get("pnltimeonanchor").vw.getWidth())-(18d * scale))));
//BA.debugLineNum = 38;BA.debugLine="Label3.SetLeftAndRight(1%x, pnlStyle1.Width-1%x)"[AddEditPumpNonOperational/General script]
views.get("label3").vw.setLeft((int)((1d / 100 * width)));
views.get("label3").vw.setWidth((int)((views.get("pnlstyle1").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 39;BA.debugLine="pnlCover1.Left = pnlStyle1.Left"[AddEditPumpNonOperational/General script]
views.get("pnlcover1").vw.setLeft((int)((views.get("pnlstyle1").vw.getLeft())));
//BA.debugLineNum = 40;BA.debugLine="pnlCover1.Top = lblOnOffTitle.Bottom + 10dip"[AddEditPumpNonOperational/General script]
views.get("pnlcover1").vw.setTop((int)((views.get("lblonofftitle").vw.getTop() + views.get("lblonofftitle").vw.getHeight())+(10d * scale)));
//BA.debugLineNum = 42;BA.debugLine="Label2.Left = 2%x"[AddEditPumpNonOperational/General script]
views.get("label2").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 43;BA.debugLine="Label2.Top = Label1.Bottom + 5dip"[AddEditPumpNonOperational/General script]
views.get("label2").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 45;BA.debugLine="pnlTimeOffAnchor.Top = Label1.Bottom + 5dip"[AddEditPumpNonOperational/General script]
views.get("pnltimeoffanchor").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 46;BA.debugLine="pnlTimeOffAnchor.SetLeftAndRight(Label2.Right + 3dip, pnlTime.Width - 45%x)"[AddEditPumpNonOperational/General script]
views.get("pnltimeoffanchor").vw.setLeft((int)((views.get("label2").vw.getLeft() + views.get("label2").vw.getWidth())+(3d * scale)));
views.get("pnltimeoffanchor").vw.setWidth((int)((views.get("pnltime").vw.getWidth())-(45d / 100 * width) - ((views.get("label2").vw.getLeft() + views.get("label2").vw.getWidth())+(3d * scale))));
//BA.debugLineNum = 48;BA.debugLine="mskTimeFinished.SetTopAndBottom(0.5%y, pnlTimeOffAnchor.Height - 0.5%y)"[AddEditPumpNonOperational/General script]
views.get("msktimefinished").vw.setTop((int)((0.5d / 100 * height)));
views.get("msktimefinished").vw.setHeight((int)((views.get("pnltimeoffanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
//BA.debugLineNum = 49;BA.debugLine="mskTimeFinished.SetLeftAndRight(1%x, pnlTimeOffAnchor.Width - 5%x)"[AddEditPumpNonOperational/General script]
views.get("msktimefinished").vw.setLeft((int)((1d / 100 * width)));
views.get("msktimefinished").vw.setWidth((int)((views.get("pnltimeoffanchor").vw.getWidth())-(5d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 52;BA.debugLine="pnlStyle2.Top = Label1.Bottom + 5dip"[AddEditPumpNonOperational/General script]
views.get("pnlstyle2").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 53;BA.debugLine="pnlStyle2.SetLeftAndRight(pnlTimeOffAnchor.Right - 18dip, pnlTime.Width - 2%x)"[AddEditPumpNonOperational/General script]
views.get("pnlstyle2").vw.setLeft((int)((views.get("pnltimeoffanchor").vw.getLeft() + views.get("pnltimeoffanchor").vw.getWidth())-(18d * scale)));
views.get("pnlstyle2").vw.setWidth((int)((views.get("pnltime").vw.getWidth())-(2d / 100 * width) - ((views.get("pnltimeoffanchor").vw.getLeft() + views.get("pnltimeoffanchor").vw.getWidth())-(18d * scale))));
//BA.debugLineNum = 55;BA.debugLine="Label4.SetLeftAndRight(1%x, pnlStyle2.Width-1%x)"[AddEditPumpNonOperational/General script]
views.get("label4").vw.setLeft((int)((1d / 100 * width)));
views.get("label4").vw.setWidth((int)((views.get("pnlstyle2").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
//BA.debugLineNum = 57;BA.debugLine="pnlCover2.Left = pnlStyle2.Left"[AddEditPumpNonOperational/General script]
views.get("pnlcover2").vw.setLeft((int)((views.get("pnlstyle2").vw.getLeft())));
//BA.debugLineNum = 58;BA.debugLine="pnlCover2.Top = Label1.Bottom + 5dip"[AddEditPumpNonOperational/General script]
views.get("pnlcover2").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 61;BA.debugLine="pnlReason.SetTopAndBottom(pnlTime.Bottom + 10dip, pnlMain.Height - 50%y)"[AddEditPumpNonOperational/General script]
views.get("pnlreason").vw.setTop((int)((views.get("pnltime").vw.getTop() + views.get("pnltime").vw.getHeight())+(10d * scale)));
views.get("pnlreason").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(50d / 100 * height) - ((views.get("pnltime").vw.getTop() + views.get("pnltime").vw.getHeight())+(10d * scale))));
//BA.debugLineNum = 62;BA.debugLine="pnlReason.SetLeftAndRight(2%x, pnlMain.Width - 2%x)"[AddEditPumpNonOperational/General script]
views.get("pnlreason").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlreason").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 64;BA.debugLine="lblReasonTitle.SetLeftAndRight(0%x, pnlReason.Width)"[AddEditPumpNonOperational/General script]
views.get("lblreasontitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblreasontitle").vw.setWidth((int)((views.get("pnlreason").vw.getWidth()) - ((0d / 100 * width))));
//BA.debugLineNum = 65;BA.debugLine="lblReasonTitle.Top = 0"[AddEditPumpNonOperational/General script]
views.get("lblreasontitle").vw.setTop((int)(0d));
//BA.debugLineNum = 67;BA.debugLine="pnlReasonAnchor.SetLeftAndRight(2%x, pnlReason.Width - 2%x)"[AddEditPumpNonOperational/General script]
views.get("pnlreasonanchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlreasonanchor").vw.setWidth((int)((views.get("pnlreason").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 68;BA.debugLine="pnlReasonAnchor.SetTopAndBottom(lblReasonTitle.Bottom + 5dip, pnlReason.Height - 1%y)"[AddEditPumpNonOperational/General script]
views.get("pnlreasonanchor").vw.setTop((int)((views.get("lblreasontitle").vw.getTop() + views.get("lblreasontitle").vw.getHeight())+(5d * scale)));
views.get("pnlreasonanchor").vw.setHeight((int)((views.get("pnlreason").vw.getHeight())-(1d / 100 * height) - ((views.get("lblreasontitle").vw.getTop() + views.get("lblreasontitle").vw.getHeight())+(5d * scale))));
//BA.debugLineNum = 69;BA.debugLine="txtRemarks.SetLeftAndRight(2%x, pnlReasonAnchor.Width - 2%x)"[AddEditPumpNonOperational/General script]
views.get("txtremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("txtremarks").vw.setWidth((int)((views.get("pnlreasonanchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 70;BA.debugLine="txtRemarks.SetTopAndBottom(1%y, pnlReasonAnchor.Height - 1%y)"[AddEditPumpNonOperational/General script]
views.get("txtremarks").vw.setTop((int)((1d / 100 * height)));
views.get("txtremarks").vw.setHeight((int)((views.get("pnlreasonanchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
//BA.debugLineNum = 73;BA.debugLine="pnlRemarks.SetTopAndBottom(pnlReason.Bottom + 10dip, pnlMain.Height - 28%y)"[AddEditPumpNonOperational/General script]
views.get("pnlremarks").vw.setTop((int)((views.get("pnlreason").vw.getTop() + views.get("pnlreason").vw.getHeight())+(10d * scale)));
views.get("pnlremarks").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(28d / 100 * height) - ((views.get("pnlreason").vw.getTop() + views.get("pnlreason").vw.getHeight())+(10d * scale))));
//BA.debugLineNum = 74;BA.debugLine="pnlRemarks.SetLeftAndRight(2%x, pnlMain.Width - 2%x)"[AddEditPumpNonOperational/General script]
views.get("pnlremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlremarks").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 76;BA.debugLine="lblRemarksTitle.SetLeftAndRight(0%x, pnlRemarks.Width)"[AddEditPumpNonOperational/General script]
views.get("lblremarkstitle").vw.setLeft((int)((0d / 100 * width)));
views.get("lblremarkstitle").vw.setWidth((int)((views.get("pnlremarks").vw.getWidth()) - ((0d / 100 * width))));
//BA.debugLineNum = 77;BA.debugLine="lblRemarksTitle.Top = 0"[AddEditPumpNonOperational/General script]
views.get("lblremarkstitle").vw.setTop((int)(0d));
//BA.debugLineNum = 79;BA.debugLine="pnlRemarksAnchor.SetLeftAndRight(2%x, pnlRemarks.Width - 2%x)"[AddEditPumpNonOperational/General script]
views.get("pnlremarksanchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlremarksanchor").vw.setWidth((int)((views.get("pnlremarks").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 80;BA.debugLine="pnlRemarksAnchor.SetTopAndBottom(lblRemarksTitle.Bottom + 5dip, pnlRemarks.Height - 1%y)"[AddEditPumpNonOperational/General script]
views.get("pnlremarksanchor").vw.setTop((int)((views.get("lblremarkstitle").vw.getTop() + views.get("lblremarkstitle").vw.getHeight())+(5d * scale)));
views.get("pnlremarksanchor").vw.setHeight((int)((views.get("pnlremarks").vw.getHeight())-(1d / 100 * height) - ((views.get("lblremarkstitle").vw.getTop() + views.get("lblremarkstitle").vw.getHeight())+(5d * scale))));
//BA.debugLineNum = 81;BA.debugLine="txtRemarks.SetLeftAndRight(2%x, pnlReasonAnchor.Width - 2%x)"[AddEditPumpNonOperational/General script]
views.get("txtremarks").vw.setLeft((int)((2d / 100 * width)));
views.get("txtremarks").vw.setWidth((int)((views.get("pnlreasonanchor").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 82;BA.debugLine="txtRemarks.SetTopAndBottom(1%y, pnlReasonAnchor.Height - 1%y)"[AddEditPumpNonOperational/General script]
views.get("txtremarks").vw.setTop((int)((1d / 100 * height)));
views.get("txtremarks").vw.setHeight((int)((views.get("pnlreasonanchor").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
//BA.debugLineNum = 84;BA.debugLine="btnSaveUpdate.SetLeftAndRight(2%x, pnlMain.Width - 2%x)"[AddEditPumpNonOperational/General script]
views.get("btnsaveupdate").vw.setLeft((int)((2d / 100 * width)));
views.get("btnsaveupdate").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 85;BA.debugLine="btnSaveUpdate.SetTopAndBottom(80%y, pnlMain.Height - 2%y)"[AddEditPumpNonOperational/General script]
views.get("btnsaveupdate").vw.setTop((int)((80d / 100 * height)));
views.get("btnsaveupdate").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(2d / 100 * height) - ((80d / 100 * height))));

}
}