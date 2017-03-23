<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String mapkey = (String) request.getSession()
			.getAttribute("mapkey");
	String vno = java.net.URLDecoder.decode(
			request.getParameter("vno"), "UTF-8");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=4d479f21ba6b7061741fab5a5a8bd6ba"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
<link href="web/css/mainFrame.css" rel="stylesheet" type="text/css" />
<link href="web/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
		window.onerror = function(sMessage, sUrl, sLine){
			//alert("页面出现错误\n"+sMessage+"\n"+sUrl+"\n"+sLine+"\n");
			track.history._loadErrorHandler();
			return true;
		}
	</script>

<script src="web/js/datepicker/WdatePicker.js" charset="GB2312"
	defer="defer"></script>
<%--<script src="http://maps.googleapis.com/maps/api/js?key=&sensor=false"
	type="text/javascript"></script>
--%>
<script type="text/javascript"
	src="http://ditu.google.cn/maps/api/js?sensor=false"></script>
 <script type="text/javascript" src="web/js/jw2name.js"></script>
<script type="text/javascript" src="web/js/BoundaryTracking.js"></script> 
<!-- <script type="text/javascript"
	src="web/js/mn/vehiclemapDetailTrackPlay.js"></script> -->
	<script type="text/javascript" src="web/js/mn/vehiclemapDetailInfo.js"></script>
</head>
<div style="display :block">
	<input id="lat" type="hidden" value="${requestScope.lat }" /> <input
		id="lng" type="hidden" value="${requestScope.lng }" />
</div>
<body style="width:100%;height:100%;">
	<div class="Classification1">
  	<li><a href="web/vehicleinfo7/vehiclemapDetailInfo.jsp?vno=<%=URLEncoder.encode(vno,"UTF-8") %>" onclick="javascript:cssChange(this);" target="contentFrame">谷歌地图</a></li>
	<li class="huang"><a href="web/vehicleinfo7/vehicleBaidumapDetailInfo.jsp?vno=<%=URLEncoder.encode(vno,"UTF-8") %>" onclick="javascript:cssChange(this);" target="contentFrame">百度地图体验版</a></li>
	</div>
	<div id="allmap" style="width:100%;height:55%;"></div></br>
    <div>
    <li><font color="red" size="3" face="黑体">尊敬的用户您好，为了方便您查询历史轨迹，本次更新我们加入新的操作方式，欢迎您尝试使用新功能，希望能给您带来更好的操作体验，具体使用方法如下。</font></li>
	<li><font color="black" size="3" face="黑体">使用说明：</font></li>
	<li><font color="black" size="3" face="黑体">1.在下方的日期窗口输入您想要查询的时间段。并点击查询按钮开始查询。</font></li>
	<li><font color="black" size="3" face="黑体">2.当页面跳转完成后即会在地图上显示您所查询的位置标点。</font></li>
	<li><font color="black" size="3" face="黑体">3.点击任意红色标点，都可以显示该点对应的详细信息。</font></li>
	<li><font color="black" size="3" face="黑体">4.手动点击键盘的左右按钮（"←","→"）可以操作历史回放功能。</font></li>
	</div></br>
	<%-- <form name="form1"
		action="mnTrackHistoryAction_findTrackHistoryInfo.action"
		method="post">
		<input type="hidden" name="vno" value="${requestScope.vno}">
		

		<div
			style="background-color:#fff;position:absolute;width:100%;margin-top: 10px;">
			<div class="guiji" style="width:550px;">
				<div style="float:left;">
					<img src="web/images/guiji_left.jpg" />
				</div>
				<div class="shijian">
					开始时间：<input name="start_time" id="start_time"
						value="${requestScope.start_time}"
						onchange="track.history._contrl.stimeChange();" 
						theme="simple" size="18"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 结束时间：<input
						name="end_time" id="end_time" value="${requestScope.end_time}"
						onchange="track.history._contrl.etimeChange();" 
						theme="simple" size="18"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				</div>
				<div style="float:left;">
					<a href="javascript:void(0);" onMouseOut="fcx_swapImgRestore();"
						onMouseOver="fcx_swapImage('back','','web/images/guiji_01_2.jpg',1);">
						<img id="back" src="web/images/guiji_01.jpg" alt="后退" border="0"
						onclick="track.history._contrl.back();" /> </a> <a
						href="javascript:void(0);" onMouseOut="fcx_swapImgRestore();"
						onMouseOver="fcx_swapImage('play','','web/images/guiji_02_2.jpg',1);">
						<img id="play" src="web/images/guiji_02.jpg" alt="播放" border="0"
						style="display: none" onclick="track.history._contrl.play();" />
					</a> <a href="javascript:void(0);" onMouseOut="fcx_swapImgRestore();"
						onMouseOver="fcx_swapImage('pause','','web/images/guiji_05_2.jpg',1);">
						<img id="pause" src="web/images/guiji_05.jpg" alt="暂停" border="0"
						onclick="track.history._contrl.pause();" /> </a> <a
						href="javascript:void(0);" onMouseOut="fcx_swapImgRestore();"
						onMouseOver="fcx_swapImage('forward','','web/images/guiji_03_2.jpg',1);">
						<img id="forward" src="web/images/guiji_03.jpg" alt="前进"
						border="0" onclick="track.history._contrl.forward();" /> </a> <a
						href="javascript:void(0);" onMouseOut="fcx_swapImgRestore();"
						onMouseOver="fcx_swapImage('stop','','web/images/guiji_04_2.jpg',1);">
						<img id="stop" src="web/images/guiji_04.jpg" alt="停止"
						onclick="track.history._contrl.stop();" border="0" /> </a>
				</div>
				<div style="float:right;">
					<img src="web/images/guiji_right.jpg">
				</div>
			</div>
			<div id='proBar' style="display: none;margin-top: 15px;">
				<img src="web/images/loading.gif" width="15" height="15">&nbsp;&nbsp;正在加载中，请稍后...
			</div>
		</div>
	</form> --%>
	<form name="form1" action="mnTrackHistoryAction_findTrackBaiduHistoryInfo.action" method="post">
	<input type="hidden" name="vno" value="${requestScope.vno}">
    	
    	<div style="background-color:#fff;position:absolute;bottom:0px;width:490px;">
			<div class="guiji" style="width:480px;">
				<div style="float:left;"><img src="web/images/guiji_left.jpg"/></div>
				<div class="shijian">
					开始时间：<input name="start_time" id="start_time" readonly="readonly" size="18" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${requestScope.start_time}"/>
					结束时间：<input name="end_time" id="end_time" readonly="readonly" size="18" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${requestScope.end_time}"/>
				</div>
				<input style="margin-top: 7px;" type="button" value="查找" onclick="check_submit();">
			</div>
			<div style="float:left;"><img src="web/images/guiji_right.jpg"></div>
		</div>
    </form>
</body>

<input type="hidden" name="gpsHistoryList"
	value="${requestScope.gpsHistoryList}">
<!-- <script type="text/javascript" defer="defer">
   
  	initPage("${requestScope.vno}","${requestScope.start_time}","${requestScope.end_time}",document.getElementById("start_time"),document.getElementById("end_time"),${requestScope.gpsHistoryList});
  </script> -->
<script type="text/javascript">
		var data_info =  '<%=request.getAttribute("jsonMutiInfo")%>';
	var markerClusterer=null;
	var map=null;
	var page2Right=0;
	var page2Left=0;
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
			minZoom : 5,
			maxZoom : 18
		}); // 创建Map实例,设置地图允许的最小/大级别
		map.centerAndZoom(point, 10); // 初始化地图,设置中心点坐标和地图级别

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
	function trickRun() {
		/**
		多点路径图
		 */
		 
		
		 
		 var bounds = null;
		var linesPoints = null;
		 
		
		 for(var i=0;i<data_info.length;i++){
			
			var spoi1 = new BMap.Point(data_info[i][0],data_info[i][1]); 
			var epoi  = new BMap.Point(data_info[i+1][0],data_info[i+1][1]);    // 终点
			var content = data_info[i][2];
			var myIcon = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/Mario.png", new BMap.Size(32, 70), {imageOffset: new BMap.Size(0, 0)})
			function initLine(){
			bounds = new Array();
			linesPoints = new Array();
			//map.clearOverlays();                                                    // 清空覆盖物
			var driving3 = new BMap.DrivingRoute(map,{onSearchComplete:drawLine});  // 驾车实例,并设置回调
				driving3.search(spoi1, epoi);                                       // 搜索一条线路
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
		},1500);
		}
	}
	
	$(document).keydown(function(event) {
		//判断当event.keyCode 为37时（即左方面键），执行函数to_left(); 
		//判断当event.keyCode 为39时（即右方面键），执行函数to_right();
		
		if (event.keyCode == 37) {
		
			if(page2Left<0){
				alert("已经没有更靠前的数据");
			}else{
				map.clearOverlays();
				//往左翻页
			var lmarker = new BMap.Marker(new BMap.Point(data_info[page2Left][0],
					data_info[page2Left][1])); // 创建标注
			var lpoint = new BMap.Point(data_info[page2Left][0],
					data_info[page2Left][1]);
			map.centerAndZoom(lpoint, 11); // 初始化地图,设置中心点坐标和地图级别 
			var lcontent = data_info[page2Left][2];
			page2Right= page2Left+1;
			page2Left--;
			
			lmarker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			map.addOverlay(lmarker); // 将标注添加到地图中
			
			var infoWindow = new BMap.InfoWindow(lcontent);  // 创建信息窗口对象
			map.openInfoWindow(infoWindow,lpoint); //开启信息窗口
			}
		
		} else if (event.keyCode == 39) {
			
			  
			if(page2Right<data_info.length){
		
			map.clearOverlays(); 
			  
			//往右翻页
			var pmarker = new BMap.Marker(new BMap.Point(data_info[page2Right][0],
					data_info[page2Right][1])); // 创建标注
			var ppoint = new BMap.Point(data_info[page2Right][0],
					data_info[page2Right][1]);
			
			map.centerAndZoom(ppoint, 11); // 初始化地图,设置中心点坐标和地图级别 
			
			
			var pcontent = data_info[page2Right][2];
			page2Left=(page2Right-1);
			page2Right++;
			
			pmarker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			map.addOverlay(pmarker); // 将标注添加到地图中
			
			var infoWindow = new BMap.InfoWindow(pcontent);  // 创建信息窗口对象
			map.openInfoWindow(infoWindow,ppoint); //开启信息窗口
			
			}else if(page2Right>=data_info.length){
				alert("已经到达最后一条数据");
			}
		}
		
	});
</script>


</html>
