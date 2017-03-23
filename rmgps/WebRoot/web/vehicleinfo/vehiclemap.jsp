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
<script type="text/javascript">
   		    var Cpoints = [];
	  		function initialize(){
		  		var myOptions={
				  	center:new google.maps.LatLng(34.1254,109.0283),
				  	zoom:5,
				  	mapTypeId:google.maps.MapTypeId.ROADMAP,
				  	scaleControl:true,
				  	scaleControlOptions:{
				  		position:google.maps.ControlPosition.BOTTOM_RIGHT
		  			},
				  	overviewMapControl:true,
				  	overviewMapControlOptions:{
				  		opened:true
		  			},
				  	mapTypeControl:true,
		  			mapTypeControlOptions:{
			  			style:google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
			  			position:google.maps.ControlPosition.TOP_LEFT
		  			}
				};
	  			var map = new google.maps.Map(document.getElementById("map"),myOptions);
				var shape = {
						coord: [0, 0, 0,20, 20,20,20,0],
						type: 'poly'
				};
	  			<%
	  				List list = (List)request.getAttribute("vehicleInfoList");
	  				
	  				for(int i = 0;i < list.size();i++){
		  				VehicleMapInfoVO vo = ((VehicleMapInfoVO)list.get(i));
		  				String vno = vo.getVno();
		  				String longitude = vo.getLongitude();
		  				String latitude = vo.getLatitude();
		  				String vmid = vo.getVmid();
		  				String vname = vo.getVname();
		  				
		  				if(vno != null && !"".equals(vno) && longitude != null && !"".equals(longitude) && !"0.0000".equals(longitude) && latitude != null && !"".equals(latitude) && !"0.0000".equals(latitude) && vmid != null && !"".equals(vmid)){
	  			%>
				  			var lng = <%=longitude %>;
						    var lat = <%=latitude %>;
						    var p=new google.maps.LatLng(lat,lng);
			        		Cpoints.push(p);
							var marker = new google.maps.Marker({
								icon:"${pageContext.request.contextPath }/web/images/mapCenter-2.png",
								position:p,
								shape:shape
							});
							marker.setMap(map);
							//--------------------鼠标响应事件
							google.maps.event.addListener(marker, "click", function(event){
								window.location.href = 'VehicleOfProtocal.action?vmid='+'<%=URLEncoder.encode(vmid,"UTF-8") %>'+'&vno='+'<%=URLEncoder.encode(vno,"UTF-8") %>'+'&vname='+'<%=URLEncoder.encode(vname,"UTF-8") %>';
							});
							google.maps.event.addListener(marker, 'mouseover', function() {
							    var newNode = document.createElement("div");
							    newNode.setAttribute("id", "offsetmark");
							    newNode.style.position = "absolute";
								newNode.style.left = event.clientX+document.body.scrollLeft + "px";
								newNode.style.top = event.clientY+document.body.scrollTop-35 + "px";
								newNode.style.color = "red";
							    newNode.innerHTML = '<%=vname%>';
							    document.body.appendChild(newNode);
							});
							google.maps.event.addListener(marker, "mouseout", function(event){ 
							    var divs = document.getElementById("offsetmark");
								if(divs != null){
									document.body.removeChild(divs);
								}
							});
							//--------------------鼠标响应事件结束
	  			<%
	  					}
	  				}
	  			%>
	var bermudaTriangle = new google.maps.Polygon({
			paths : Cpoints,
			strokeColor : "#ffffff",
			strokeWeight : 0,
			strokeOpacity : 0.1,
			fillColor : "#ffffff",
			fillOpacity : 0.0
		});
		bermudaTriangle.setMap(map);

		// -------初始化地图标注--此处引用外部/remarker.js文件中的函数---------
		initRemarker(map);

		// -------添加地图标注功能---------	  	
		addRemarker(map);

	}
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
<body>
	<div class="main_top2">&nbsp;</div>
	<div class="main_huang">首页 => 整机地图=>地图上整机所在位置</div>
	<div>
		<div id="map"
			style="margin-left:10px;margin-right:10px;WIDTH:1025px;HEIGHT:620px"></div>
		<div id="bound"></div>
	</div>
</body>
</html>
