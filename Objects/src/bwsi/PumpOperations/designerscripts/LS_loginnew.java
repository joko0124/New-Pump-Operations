package bwsi.PumpOperations.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_loginnew{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("pnlmain").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlmain").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlmain").vw.setTop((int)((0d / 100 * height)));
views.get("pnlmain").vw.setHeight((int)((100d / 100 * height) - ((0d / 100 * height))));
views.get("pnlheader").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlheader").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlheader").vw.setTop((int)((0d / 100 * height)));
views.get("pnlheader").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(65d / 100 * height) - ((0d / 100 * height))));
views.get("lblgradient").vw.setLeft((int)((0d / 100 * width)));
views.get("lblgradient").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("lblgradient").vw.setTop((int)((0d / 100 * height)));
views.get("lblgradient").vw.setHeight((int)((views.get("pnlheader").vw.getHeight()) - ((0d / 100 * height))));
views.get("pnllogin").vw.setLeft((int)((5d / 100 * width)));
views.get("pnllogin").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(5d / 100 * width) - ((5d / 100 * width))));
views.get("pnllogin").vw.setTop((int)((21d / 100 * height)));
views.get("pnllogin").vw.setHeight((int)((views.get("pnlmain").vw.getHeight())-(35d / 100 * height) - ((21d / 100 * height))));
views.get("logo").vw.setLeft((int)((20d / 100 * width)));
views.get("logo").vw.setWidth((int)((views.get("pnllogin").vw.getWidth())-(20d / 100 * width) - ((20d / 100 * width))));
views.get("logo").vw.setTop((int)((2d / 100 * height)));
views.get("pnlusernameanchor").vw.setLeft((int)((10d / 100 * width)));
views.get("pnlusernameanchor").vw.setWidth((int)((views.get("pnllogin").vw.getWidth())-(10d / 100 * width) - ((10d / 100 * width))));
views.get("pnlusernameanchor").vw.setTop((int)((views.get("logo").vw.getTop() + views.get("logo").vw.getHeight())+(20d * scale)));
views.get("pnlusernameanchor").vw.setHeight((int)((views.get("pnllogin").vw.getHeight())-(23d / 100 * height) - ((views.get("logo").vw.getTop() + views.get("logo").vw.getHeight())+(20d * scale))));
views.get("lblusernameicon").vw.setLeft((int)((3d / 100 * width)));
views.get("lblusernameicon").vw.setTop((int)((0.5d / 100 * height)));
views.get("lblusernameicon").vw.setHeight((int)((views.get("pnlusernameanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("txtusername").vw.setLeft((int)((views.get("lblusernameicon").vw.getLeft() + views.get("lblusernameicon").vw.getWidth())+(3d * scale)));
views.get("txtusername").vw.setWidth((int)((views.get("pnlusernameanchor").vw.getWidth())-(1d / 100 * width) - ((views.get("lblusernameicon").vw.getLeft() + views.get("lblusernameicon").vw.getWidth())+(3d * scale))));
views.get("txtusername").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtusername").vw.setHeight((int)((views.get("pnlusernameanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("pnlpasswordanchor").vw.setLeft((int)((10d / 100 * width)));
views.get("pnlpasswordanchor").vw.setWidth((int)((views.get("pnllogin").vw.getWidth())-(10d / 100 * width) - ((10d / 100 * width))));
views.get("pnlpasswordanchor").vw.setTop((int)((views.get("pnlusernameanchor").vw.getTop() + views.get("pnlusernameanchor").vw.getHeight())+(10d * scale)));
views.get("pnlpasswordanchor").vw.setHeight((int)((views.get("pnllogin").vw.getHeight())-(15d / 100 * height) - ((views.get("pnlusernameanchor").vw.getTop() + views.get("pnlusernameanchor").vw.getHeight())+(10d * scale))));
views.get("lblpasswordicon").vw.setLeft((int)((3d / 100 * width)));
views.get("lblpasswordicon").vw.setTop((int)((0.5d / 100 * height)));
views.get("lblpasswordicon").vw.setHeight((int)((views.get("pnlpasswordanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("txtpassword").vw.setLeft((int)((views.get("lblpasswordicon").vw.getLeft() + views.get("lblpasswordicon").vw.getWidth())+(3d * scale)));
views.get("txtpassword").vw.setWidth((int)((views.get("pnlpasswordanchor").vw.getWidth())-(1d / 100 * width) - ((views.get("lblpasswordicon").vw.getLeft() + views.get("lblpasswordicon").vw.getWidth())+(3d * scale))));
views.get("txtpassword").vw.setTop((int)((0.5d / 100 * height)));
views.get("txtpassword").vw.setHeight((int)((views.get("pnlpasswordanchor").vw.getHeight())-(0.5d / 100 * height) - ((0.5d / 100 * height))));
views.get("lblcheck").vw.setLeft((int)((12d / 100 * width)));
views.get("chkshowpass").vw.setLeft((int)((11d / 100 * width)));
views.get("chkshowpass").vw.setWidth((int)((views.get("pnllogin").vw.getWidth())-(12d / 100 * width) - ((11d / 100 * width))));
views.get("pnlsignup").vw.setLeft((int)((40d / 100 * width)));
views.get("pnlsignup").vw.setWidth((int)((views.get("pnlmain").vw.getWidth())-(40d / 100 * width) - ((40d / 100 * width))));
views.get("pnlsignup").vw.setTop((int)((59d / 100 * height)));
views.get("btnlogin").vw.setLeft((int)((1.75d / 100 * width)));
views.get("btnlogin").vw.setWidth((int)((views.get("pnlsignup").vw.getWidth())-(1.75d / 100 * width) - ((1.75d / 100 * width))));
views.get("btnlogin").vw.setTop((int)((2d / 100 * height)));
views.get("label3").vw.setLeft((int)((0d / 100 * width)));
views.get("label3").vw.setWidth((int)((views.get("btnlogin").vw.getWidth()) - ((0d / 100 * width))));
views.get("label3").vw.setTop((int)((0d / 100 * height)));
views.get("label3").vw.setHeight((int)((views.get("btnlogin").vw.getHeight()) - ((0d / 100 * height))));
views.get("lblprojname").vw.setLeft((int)((0d / 100 * width)));
views.get("lblprojname").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("lblprojname").vw.setTop((int)((views.get("pnlmain").vw.getTop() + views.get("pnlmain").vw.getHeight()) - (views.get("lblprojname").vw.getHeight())));
views.get("pnlbranchanchor").vw.setLeft((int)((2d / 100 * width)));
views.get("pnlbranchanchor").vw.setTop((int)((views.get("lblprojname").vw.getTop()) - (views.get("pnlbranchanchor").vw.getHeight())));
views.get("label2").vw.setLeft((int)((0d / 100 * width)));
views.get("label2").vw.setWidth((int)((views.get("pnlbranchanchor").vw.getWidth()) - ((0d / 100 * width))));
views.get("pnlbranchsettings").vw.setLeft((int)((views.get("pnlbranchanchor").vw.getWidth())/2d - (views.get("pnlbranchsettings").vw.getWidth() / 2)));
views.get("pnlipsettingsanchor").vw.setLeft((int)((98d / 100 * width) - (views.get("pnlipsettingsanchor").vw.getWidth())));
views.get("pnlipsettingsanchor").vw.setTop((int)((views.get("lblprojname").vw.getTop()) - (views.get("pnlipsettingsanchor").vw.getHeight())));
views.get("label5").vw.setLeft((int)((0d / 100 * width)));
views.get("label5").vw.setWidth((int)((views.get("pnlipsettingsanchor").vw.getWidth()) - ((0d / 100 * width))));
views.get("pnlipsettings").vw.setLeft((int)((views.get("pnlipsettingsanchor").vw.getWidth())/2d - (views.get("pnlipsettings").vw.getWidth() / 2)));
views.get("pnlbranchsearch").vw.setTop((int)((0d / 100 * height)));
views.get("pnlbranchsearch").vw.setHeight((int)((100d / 100 * height) - ((0d / 100 * height))));
views.get("pnlbranchsearch").vw.setLeft((int)((0d / 100 * width)));
views.get("pnlbranchsearch").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("pnlsearchbranches").vw.setLeft((int)((1d / 100 * width)));
views.get("pnlsearchbranches").vw.setWidth((int)((views.get("pnlbranchsearch").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("pnlsearchbranches").vw.setTop((int)((15d / 100 * height)));
views.get("pnlsearchbranches").vw.setHeight((int)((views.get("pnlbranchsearch").vw.getHeight())-(15d / 100 * height) - ((15d / 100 * height))));

}
}