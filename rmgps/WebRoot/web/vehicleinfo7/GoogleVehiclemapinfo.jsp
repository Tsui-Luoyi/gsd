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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<style type="text/css">
      html, body { height: 100%; margin: 0; padding: 0; }
      #map { width:100%;height: 80%; }
    </style>
	<link href="web/css/style.css" rel="stylesheet" type="text/css" />
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
		<input id="lat" type="hidden" value="${requestScope.vo.latitude }" />
		<input id="lng" type="hidden" value="${requestScope.vo.longitude }" />
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
	<script type="text/javascript">
		var map = null;
		var lat = parseFloat($("#lat").val());
		var lng = parseFloat($("#lng").val());
		
		function initialize() {
			var latlng = new google.maps.LatLng(lat, lng);
			var myOptions = {
				center : latlng,
				zoom : 11,
				zoomControl : true,
				zoomControlOptions : {
					position : google.maps.ControlPosition.LEFT_TOP
				},
				
				streetViewControl : true,
				streetViewControlOptions : {
					position : google.maps.ControlPosition.TOP_LEFT
				},
				
				mapTypeId : google.maps.MapTypeId.ROADMAP,
				mapTypeControl : true,
				mapTypeControlOptions : {
					mapTypeIds : [google.maps.MapTypeId.ROADMAP,
					              google.maps.MapTypeId.HYBRID],
					style : google.maps.MapTypeControlStyle.DEFAULT,
					position : google.maps.ControlPosition.TOP_RIGHT
				},
				
				scaleControl : true,
				scaleControlOptions : {
					style : google.maps.ScaleControlStyle .DEFAULT
				}
			};
			
			map = new google.maps.Map(document.getElementById("map"), myOptions);
			
			var shape = {
				coord : [ 0, 0, 0, 20, 20, 20, 20, 0 ],
				type : 'poly'
			};
		
			var marker = new google.maps.Marker(
					{
						icon : "${pageContext.request.contextPath }/web/images/mapCenter.png",
						position : latlng,
						shape : shape
					});
			marker.setMap(map);
			
		}
	</script>
	<script async defer
		src="http://ditu.google.cn/maps/api/js?v=3&key=AIzaSyAWsh6FQFjU1EKSACo4msf2w_HcJ6k5BT4&callback=initialize">
	</script>
</body>
</html>
