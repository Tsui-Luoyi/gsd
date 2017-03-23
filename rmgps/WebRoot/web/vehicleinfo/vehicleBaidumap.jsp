<%@ page language="java"
	import="java.util.*,com.jsd.web.vehicle.vo.VehicleMapInfoVO"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String mapkey = (String)request.getSession().getAttribute("mapkey");
%>
<script type="text/javascript">
<!--
	<%
		List bandVehicleList = (List)request.getAttribute("vehicleInfoList");
		if(bandVehicleList == null || bandVehicleList.size() <= 0){
			String backmsg = "没有属于您的整车,请您核实后再操作|" + basePath + "web/mainFrame.jsp";
	%>
		document.location.href = "<%=basePath%>web/vehicleinfo/operateResult.jsp?resultFromjsp="+encodeURI(encodeURI("<%=backmsg%>"));
	<%}%>
//-->
</script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
		"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<base href="<%=basePath%>">
<title>北京金圣达电气科技有限公司</title>
<style type="text/css">
<!--
.main_huang {
	background-color: #F8EDC2;
	height: 25px;
	padding: 5px 3px 3px 30px;
	background-image: url(../images/xingX.png);
	font-size: 9pt;
	font-weight: bold;
	color: #242424;
	margin: -20px 10px 10px 10px;
	background-repeat: no-repeat;
	background-position: 6px 3px;
}
-->
</style>
<meta http-equiv="pragma" content="no-cache">
<link href="<%=basePath %>/web/css/mainFrame.css" rel="stylesheet"
	type="text/css">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%--<script src="http://maps.googleapis.com/maps/api/js?key=&sensor=false"
	type="text/javascript"></script>
--%>
<script type="text/javascript" src="http://ditu.google.cn/maps/api/js?sensor=false"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/web/js/remarker.js"></script>
</head>
<div style="display :block">
	<input id="lat" type="hidden" value="${requestScope.lat }" /> <input
		id="lng" type="hidden" value="${requestScope.lng }" />
</div>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=4d479f21ba6b7061741fab5a5a8bd6ba"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
<script type="text/javascript">
    var data_info =  '<%=request.getAttribute("jsonBaiduMutiInfo")%>';
   
	var markerClusterer=null;
	var map=null;
	data_info = $.parseJSON(data_info);
	var lat = parseFloat($("#lat").val());
	var lng = parseFloat($("#lng").val());
		
	$(function() {
	
		if(data_info.length==0){
			alert("没有历史记录");
		}
		//规定地图中心点
		var point = new BMap.Point(lng, lat);
		
		
	    map = new BMap.Map("allmap", {
			minZoom : 1,
			maxZoom : 18
		}); // 创建Map实例,设置地图允许的最小/大级别
		map.centerAndZoom(point, 5); // 初始化地图,设置中心点坐标和地图级别

		//map.setCurrentCity("北京"); // 设置地图显示的城市 此项是必须设置的
		//地图缩放功能
		/* setTimeout(function() {
			map.setZoom(12);
		}, 2000); //2秒后放大到12级 */
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
		//地图缩放控件   
		/* var marker = new BMap.Marker(point); // 创建标注
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		map.addOverlay(marker); // 将标注添加到地图中 */

		var overViewOpen = new BMap.OverviewMapControl({
			isOpen : true,
			anchor : BMAP_ANCHOR_BOTTOM_RIGHT
		});//右下角缩略地图
		map.addControl(overViewOpen);
		/**
		多点信息窗口控件
		 */
		/* var data_info = [[116.417854,39.921988,"地址：北京市东城区王府井大街88号乐天银泰百货八层"],
						 [116.406605,39.921585,"地址：北京市东城区东华门大街"],
						 [116.412222,39.912345,"地址：北京市东城区正义路甲5号"]
						];  */

		var opts = {
			width : 300, // 信息窗口宽度
			height : 120, // 信息窗口高度
			title : "信息窗口", // 信息窗口标题
			enableMessage : true
		//设置允许信息窗发送短息
		};
		//新建marker数组用来存放点
		var markers = [];
	
		for ( var i = 0; i < data_info.length; i++) {

			var marker = new BMap.Marker(new BMap.Point(data_info[i][0],
					data_info[i][1])); // 创建标注
			
			
			
			
			var content = data_info[i][2];
			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			//map.addOverlay(marker); // 将标注添加到地图中
			marker.addEventListener("click", openInfo.bind(null, content));
			markers.push(marker);
		}
		markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});
		//map.addOverlay(markerClusterer);
		function openInfo(content, e) {
			var p = e.target;
			var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
			var infoWindow = new BMap.InfoWindow(content, opts); // 创建信息窗口对象 
			map.openInfoWindow(infoWindow, point); //开启信息窗口
		}
		//调用聚合点的类
		
		/* //单纯画路径
		var spoi1 = new BMap.Point(116.380967,39.913285);    // 起点1
		
		var epoi  = new BMap.Point(116.424374,39.914668);    // 终点
		
		                                     // 搜索一条线路
			
		var driving2 = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});    //驾车实例
		driving2.search(spoi1, epoi);  */

		//车辆移动demo
		/* 
		var myP1 = new BMap.Point(116.380967,39.913285);    //起点
		var myP2 = new BMap.Point(116.424374,39.914668);    //终点
		var myIcon = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/Mario.png", new BMap.Size(32, 70), {    //小车图片
			//offset: new BMap.Size(0, -5),    //相当于CSS精灵
			imageOffset: new BMap.Size(0, 0)    //图片的偏移量。为了是图片底部中心对准坐标点。
		  });
		  
		  var routePolicy = [BMAP_DRIVING_POLICY_LEAST_TIME,BMAP_DRIVING_POLICY_LEAST_DISTANCE];
		  
		var driving2 = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true},policy: routePolicy});    //驾车实例
		driving2.search(myP1, myP2);    //显示一条公交线路

		window.run = function (){
			var driving = new BMap.DrivingRoute(map);    //驾车实例
			driving.search(myP1, myP2);
			driving.setSearchCompleteCallback(function(){
				var pts = driving.getResults().getPlan(0).getRoute(0).getPath();    //通过驾车实例，获得一系列点的数组
				var paths = pts.length;    //获得有几个点

				var carMk = new BMap.Marker(pts[0],{icon:myIcon});
				map.addOverlay(carMk);
				i=0;
				function resetMkPoint(i){
					carMk.setPosition(pts[i]);
					if(i < paths){
						setTimeout(function(){
							i++;
							resetMkPoint(i);
						},100);
					}
				}
				setTimeout(function(){
					resetMkPoint(5);
				},100);

			});
		}

		setTimeout(function(){
			run();
		},1);   */
		/**
		全景地图控件
		 */
		// 覆盖区域图层测试
		var stCtrl = new BMap.PanoramaControl(); //构造全景控件
		//map.addTileLayer(new BMap.PanoramaCoverageLayer());
		stCtrl.setOffset(new BMap.Size(15, 40));
		map.addControl(stCtrl);//添加全景控件
		
	});
</script>
<body>
	<div class="main_top2">&nbsp;</div>
	<div class="main_huang">首页 => 整机地图=>地图上整机所在位置</div>
	<div>
		<div id="allmap"
			style="margin-left:10px;margin-right:10px;WIDTH:1025px;HEIGHT:620px"></div>
		<div id="bound"></div>
	</div>
</body>
</html>
