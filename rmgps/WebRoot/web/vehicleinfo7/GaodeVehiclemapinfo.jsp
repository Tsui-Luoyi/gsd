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

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link href="web/css/style.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
      html, body { height: 100%; margin: 0; padding: 0; }
      #map { width:100%;height: 80%; }
    </style>
</head>
<body>
	<div id="map"></div>
	<div class="msgdiv" style="20%;">
		<li>定位方式：${requestScope.vo.locate_flag == 'S'?'基站定位':'GPS定位' }</li>
		<li>位置：${requestScope.vo.addr }</li>
		<li>GPS接收时间：${fn:substring(requestScope.vo.revtime,0,19) }</li>
		<li>信号强度：${requestScope.vo.gsmstrong }</li>
		<li>卫星个数：${requestScope.vo.satellitenum }</li>
		<li>纬度：${requestScope.vo.latitude }</li>
		<li>经度：${requestScope.vo.longitude }</li>
	</div>
	<div style="display :block">
		<!-- 高德地图支持从百度经纬度进行纠偏 -->
		<input id="lat" type="hidden" value="${requestScope.vo.b_Latitude }" />
		<input id="lng" type="hidden" value="${requestScope.vo.b_Longitude }" />
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
	<script type="text/javascript">
		var map = null;
		var lat = parseFloat($("#lat").val());
		var lng = parseFloat($("#lng").val());
		
		function initialize() {
			var lnglat = new AMap.LngLat(lng,lat);
			AMap.convertFrom(lnglat,'baidu',function(status,result){
				if(status != 'complete')
					return;
				if(result.info != 'ok')
					return;
				lnglat = result.locations[0];
				
				map = new AMap.Map('map',{
					resizeEnable: true,
					zoom: 11,
					center: lnglat
				});
				
				AMap.plugin(['AMap.ToolBar','AMap.Scale','AMap.MapType','AMap.OverView'],function(){
					map.addControl(new AMap.ToolBar({locate:false}));
					map.addControl(new AMap.Scale());
					map.addControl(new AMap.MapType({showRoad:true}));
					map.addControl(new AMap.OverView({isOpen:true}));
				});
			
				new AMap.Marker({
					position: lnglat,
					map:map
				});	
			});
		}
	</script>
	<script
		src="http://webapi.amap.com/maps?v=1.3&key=d3ba5a2d57426deb48072817c2d7c4ce&callback=initialize">
	</script>
</body>
</html>
