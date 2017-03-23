<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String mapkey = (String)request.getSession().getAttribute("mapkey");
String vno = java.net.URLDecoder.decode(request.getParameter("vno"),"UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'vehiclemapDetailInfo.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<%--<script src="http://maps.googleapis.com/maps/api/js?key=&sensor=false"
	type="text/javascript"></script>
--%>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=4d479f21ba6b7061741fab5a5a8bd6ba"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
	<script src="web/js/datepicker/WdatePicker.js" charset="GB2312"></script>
	<link href="web/css/mainFrame.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="web/js/mn/vehiclemapDetailInfo.js"></script>
	<link href="web/css/style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
  <div class="Classification1">
  	<li class="huang"><a href="web/vehicleinfo7/vehicleBaidumapDetailInfo.jsp?vno=<%=URLEncoder.encode(vno,"UTF-8") %>" onclick="javascript:cssChange(this);" target="contentFrame">百度地图</a></li>
	</div>
	
    <div id="map" style="width:100%;height:55%;"></div></br>
    <div>
    <li><font color="red" size="3" face="黑体">尊敬的用户您好，为了方便您查询历史轨迹，本次更新我们加入新的操作方式，欢迎您尝试使用新功能，希望能给您带来更好的操作体验，具体使用方法如下。</font></li>
	<li><font color="black" size="3" face="黑体">使用说明：</font></li>
	<li><font color="black" size="3" face="黑体">1.在下方的日期窗口输入您想要查询的时间段。并点击查询按钮开始查询。</font></li>
	<li><font color="black" size="3" face="黑体">2.当页面跳转完成后即会在地图上显示您所查询的位置标点。</font></li>
	<li><font color="black" size="3" face="黑体">3.点击任意红色标点，都可以显示该点对应的详细信息。</font></li>
	<li><font color="black" size="3" face="黑体">4.手动点击键盘的左右按钮（"←","→"）可以操作历史回放功能。</font></li>
	</div></br>
    <form name="form1" action="mnTrackHistoryAction_findTrackBaiduHistoryInfo.action" method="post">
    	<input type="hidden" name="vno" value="<%=java.net.URLEncoder.encode(vno,"UTF-8") %>">
    	<div style="background-color:#fff;position:absolute;bottom:0px;width:490px;">
			<div class="guiji" style="width:480px;">
				<div style="float:left;"><img src="web/images/guiji_left.jpg"/></div>
				<div class="shijian">
					开始时间：<input name="start_time" id="start_time" readonly="readonly" size="18" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
					结束时间：<input name="end_time" id="end_time" readonly="readonly" size="18" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
				</div>
				<input style="margin-top: 7px;" type="button" value="查找" onclick="check_submit();">
			</div>
			<div style="float:left;"><img src="web/images/guiji_right.jpg"></div>
		</div>
    </form>
  </body>
  <script type="text/javascript">
 	/* // 定位地点
   	var point = new google.maps.LatLng(39.9626,116.4514);
  	load(point); */
  	// 百度地图API功能
	var map = new BMap.Map("map");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);
		//地图缩放功能
		//地图缩放控件
		var top_left_control = new BMap.ScaleControl({
			anchor : BMAP_ANCHOR_TOP_LEFT
		});// 左上角，添加比例尺
		var top_left_navigation = new BMap.NavigationControl(); //左上角，添加默认缩放平移控件

		map.addControl(top_left_control);
		map.addControl(top_left_navigation);

		//地图类型控件
		var mapType1 = new BMap.MapTypeControl({
			mapTypes : [ BMAP_NORMAL_MAP, BMAP_HYBRID_MAP ]
		});
		map.addControl(mapType1);
  </script>
</html>
