<%@ page import="java.util.*,com.jsd.web.login.vo.*"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:v="urn:schemeas-microsoft-com:vml"
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Type" content="text/css; charset=UTF-8" />
<meta http-equiv="Content-Type" content="text/javascript; charset=UTF-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />

<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=4d479f21ba6b7061741fab5a5a8bd6ba"></script>
<title>地图展示</title>

</head>
<body>
	<!-- 地图区域存放面板容器 -->
	<div id="allmap" style="width:800px; height:400px;"></div>
</body>
</html>
<script type="text/javascript" language="javascript">
	
		//规定地图中心点
		var point = new BMap.Point(0,0);
		var map = new BMap.Map("allmap", {
			minZoom : 8,
			maxZoom : 16
		}); // 创建Map实例,设置地图允许的最小/大级别
		map.centerAndZoom(point, 12); // 初始化地图,设置中心点坐标和地图级别

		map.setCurrentCity("北京"); // 设置地图显示的城市 此项是必须设置的
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
		var marker = new BMap.Marker(point); // 创建标注
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		map.addOverlay(marker); // 将标注添加到地图中
		
		var overViewOpen = new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT});//右下角缩略地图
		map.addControl(overViewOpen);  
		
	
	
	/* //单纯画路径
	var spoi1 = new BMap.Point(116.380967,39.913285);    // 起点1
	
	var epoi  = new BMap.Point(116.424374,39.914668);    // 终点
	
	                                     // 搜索一条线路
		
	var driving2 = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});    //驾车实例
	driving2.search(spoi1, epoi);  */
	
	//车辆移动demo
	
	/* var myP1 = new BMap.Point(116.380967,39.913285);    //起点
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
	多起点多终点
	*/
	/* var bounds = null;
	var linesPoints = null;
	var spoi1 = new BMap.Point(0,0);    // 起点1
	var spoi2 = new BMap.Point(116.380967,39.953285);    // 起点2
	var epoi  = new BMap.Point(116.424374,39.914668);    // 终点
	var myIcon = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/Mario.png", new BMap.Size(32, 70), {imageOffset: new BMap.Size(0, 0)});
	function initLine(){
		bounds = new Array();
		linesPoints = new Array();
		map.clearOverlays();                                                    // 清空覆盖物
		var driving3 = new BMap.DrivingRoute(map,{onSearchComplete:drawLine});  // 驾车实例,并设置回调
			driving3.search(spoi1, spoi2);                                       // 搜索一条线路
		driving3 = new BMap.DrivingRoute(map,{onSearchComplete:drawLine});  // 驾车实例,并设置回调
			driving3.search(spoi2, epoi);                                       // 搜索一条线路
	}
	function run(){
		for(var m = 0;m < linesPoints.length; m++){
			var pts = linesPoints[m];
			var len = pts.length;
			var carMk = new BMap.Marker(pts[0],{icon:myIcon});
			map.addOverlay(carMk);
			resetMkPoint(1,len,pts,carMk)
		}
		
		function resetMkPoint(i,len,pts,carMk){
			carMk.setPosition(pts[i]);
			if(i < len){
				setTimeout(function(){
					i++;
					resetMkPoint(i,len,pts,carMk);
				},100);
			}
		}
		
	}
	function drawLine(results){
		var opacity = 0.45;
		var planObj = results.getPlan(0);
		var b = new Array();
		var addMarkerFun = function(point,imgType,index,title){
			var url;
			var width;
			var height
			var myIcon;
			// imgType:1的场合，为起点和终点的图；2的场合为车的图形
			if(imgType == 1){
				url = "http://developer.baidu.com/map/jsdemo/img/dest_markers.png";
				width = 42;
				height = 34;
				myIcon = new BMap.Icon(url,new BMap.Size(width, height),{offset: new BMap.Size(14, 32),imageOffset: new BMap.Size(0, 0 - index * height)});
			}else{
				url = "http://developer.baidu.com/map/jsdemo/img/trans_icons.png";
				width = 22;
				height = 25;
				var d = 25;
				var cha = 0;
				var jia = 0
				if(index == 2){
					d = 21;
					cha = 5;
					jia = 1;
				}
				myIcon = new BMap.Icon(url,new BMap.Size(width, d),{offset: new BMap.Size(10, (11 + jia)),imageOffset: new BMap.Size(0, 0 - index * height - cha)});
			}
			
			var marker = new BMap.Marker(point, {icon: myIcon});
			if(title != null && title != ""){
				marker.setTitle(title);
			}
			// 起点和终点放在最上面
			if(imgType == 1){
				marker.setTop(true);
			}
			map.addOverlay(marker);
		}
		var addPoints = function(points){
			for(var i = 0; i < points.length; i++){
				bounds.push(points[i]);
				b.push(points[i]);
			}
		}	
		// 绘制驾车步行线路
		for (var i = 0; i < planObj.getNumRoutes(); i ++){
			var route = planObj.getRoute(i);
			if (route.getDistance(false) <= 0){continue;}
			addPoints(route.getPath());
			// 驾车线路
			if(route.getRouteType() == BMAP_ROUTE_TYPE_DRIVING){
				map.addOverlay(new BMap.Polyline(route.getPath(), {strokeColor: "#0030ff",strokeOpacity:opacity,strokeWeight:6,enableMassClear:true}));
			}else{
			// 步行线路有可能为0
				map.addOverlay(new BMap.Polyline(route.getPath(), {strokeColor: "#30a208",strokeOpacity:0.75,strokeWeight:4,enableMassClear:true}));
			}
		}	
		map.setViewport(bounds);	
		// 终点
		addMarkerFun(results.getEnd().point,1,1);
		// 开始点
		addMarkerFun(results.getStart().point,1,0);
		linesPoints[linesPoints.length] = b;
	}
	initLine();
	setTimeout(function(){
		run();
	},1500); */
	
	/**
	全景地图控件
	*/
	// 覆盖区域图层测试
	var stCtrl = new BMap.PanoramaControl(); //构造全景控件
	//map.addTileLayer(new BMap.PanoramaCoverageLayer());
	stCtrl.setOffset(new BMap.Size(15, 40));
	map.addControl(stCtrl);//添加全景控件
	
	
	
</script>