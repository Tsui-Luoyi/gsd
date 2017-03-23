<%@ page language="java" import="java.util.*,javax.servlet.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String vno = URLDecoder.decode(request.getParameter("vno"),"UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<div style="display :block">
	<input id="addr" type="hidden" value="${requestScope.vo.addr }" />
	<input id="b_lat" type="hidden" value="${requestScope.vo.b_Latitude }" /> 
	<input id="b_lng" type="hidden" value="${requestScope.vo.b_Longitude }" />
</div>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="web/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
<script type="text/javascript"	src="http://api.map.baidu.com/api?v=2.0&ak=4d479f21ba6b7061741fab5a5a8bd6ba"></script>

<script type="text/javascript">
	var Cpoints = [];
	var addr = ($("#addr").val());
	var b_lat = parseFloat($("#b_lat").val());
	var b_lng = parseFloat($("#b_lng").val());

	$(function() {

		var point = new BMap.Point(b_lng, b_lat);
		var map = new BMap.Map("map"); 	// 创建Map实例
		map.centerAndZoom(point, 12); 	// 初始化地图,设置中心点坐标和地图级别
		map.setCurrentCity("北京"); 	// 仅当设置城市信息时，MapTypeControl的切换功能才能可用

		//地图缩放功能
		map.enableScrollWheelZoom(true);
		//缩放平移控件
		var top_left_navigation = new BMap.NavigationControl();
		map.addControl(top_left_navigation);
		
		//添加比例尺
		var top_left_control = new BMap.ScaleControl();
		map.addControl(top_left_control);
		
		//地图类型控件
		var mapType1 = new BMap.MapTypeControl({
			mapTypes : [ BMAP_NORMAL_MAP, BMAP_HYBRID_MAP ]
		});
		map.addControl(mapType1);

		//地图缩放控件   
		var marker = new BMap.Marker(point); // 创建标注
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		map.addOverlay(marker); // 将标注添加到地图中

		//右下角缩略地图
		var overViewOpen = new BMap.OverviewMapControl({
			isOpen : true,
			anchor : BMAP_ANCHOR_BOTTOM_RIGHT
		});
		map.addControl(overViewOpen);

		//全景地图控件
		var stCtrl = new BMap.PanoramaControl(); //构造全景控件
		stCtrl.setOffset(new BMap.Size(15, 40));
		map.addControl(stCtrl);//添加全景控件
	});
</script>
</head>
<body>
	<div id="map"
		style="margin-top:0px;margin-left:0px;width:100%;height:80%;"></div>
	<div class="msgdiv" style="20%;">
		<li>定位方式：${requestScope.vo.locate_flag == 'S'?'基站定位':'GPS定位' }</li>
		<li>位置：${requestScope.vo.addr }</li>
		<li>GPS接收时间：${fn:substring(requestScope.vo.revtime,0,19) }</li>
		<li>信号强度：${requestScope.vo.gsmstrong }</li>
		<li>卫星个数：${requestScope.vo.satellitenum }</li>
		<li>纬度：${requestScope.vo.b_Latitude }</li>
		<li>经度：${requestScope.vo.b_Longitude }</li>
	</div>
</body>
</html>
