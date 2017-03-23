<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>My JSP 'vehiclemapDetailInfo.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%--<script src="http://maps.googleapis.com/maps/api/js?key=&sensor=false"
	type="text/javascript"></script>
--%>
<script type="text/javascript"
	src="http://ditu.google.cn/maps/api/js?sensor=false"></script>
<script src="web/js/datepicker/WdatePicker.js" charset="GB2312"></script>
<link href="web/css/mainFrame.css" rel="stylesheet" type="text/css" />
<link href="web/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="web/js/mn/vehiclemapDetailInfo.js"></script>
</head>

<body>
	<div class="Classification1">
	<li><a href="web/vehicleinfo7/vehicleBaidumapDetailInfo.jsp?vno=<%=URLEncoder.encode(vno,"UTF-8") %>" onclick="javascript:cssChange(this);" target="contentFrame">百度地图体验版</a></li>
  	<li class="huang"><a href="web/vehicleinfo7/vehiclemapDetailInfo.jsp?vno=<%=URLEncoder.encode(vno,"UTF-8") %>" onclick="javascript:cssChange(this);" target="contentFrame">谷歌地图</a></li>
	
	</div>
	<div id="map" style="width:100%;height:77%;"></div>
	<br>
	<form name="form1"
		action="mnTrackHistoryAction_findTrackHistoryInfo.action"
		method="post">
		<input type="hidden" name="vno"
			value="<%=java.net.URLEncoder.encode(vno, "UTF-8")%>">
		<div
			style="background-color:#fff;position:absolute;bottom:0px;width:490px;">
			<div class="guiji" style="width:480px;">
				<div style="float:left;">
					<img src="web/images/guiji_left.jpg" />
				</div>
				<div class="shijian">
					开始时间：<input name="start_time" id="start_time" readonly="readonly"
						size="18" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					结束时间：<input name="end_time" id="end_time" readonly="readonly"
						size="18" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				</div>
				<input style="margin-top: 7px;" type="button" value="查找"
					onclick="check_submit();">
			</div>
			<div style="float:left;">
				<img src="web/images/guiji_right.jpg">
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	// 定位地点
	var point = new google.maps.LatLng(39.9626, 116.4514);
	load(point);
</script>
</html>
