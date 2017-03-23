<%@ page import="java.util.*,com.jsd.web.login.vo.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
 String base = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+base+"/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
<link href="../css/leftFrame.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/leftXL.js"></script>
	<script type="text/javascript" src="<%=basePath%>/web/js/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
	<script type="text/javascript">
		function isChange(obj){
			if(obj.className == 'left_XZ'){
				obj.style.backgroundImage= "url(../images/left_an_B.gif)";
				obj.style.color = '#155361';
			}
		}
		function backChange(obj){
			if(obj.className == 'left_XZ'){
				obj.style.backgroundImage= "url(../images/left_an_A.gif)";
				obj.style.color = '#EAF9F2';
			}
		}
		
		function disflush(){
		
		document.getElementById("zhengji1").style.display="none"; 
		document.getElementById("zhengji2").style.display="block";
	
		}
		function disflush2(){
		
		document.getElementById("jinji1").style.display="none"; 
		document.getElementById("jinji2").style.display="block";
	
		}
	</script>
</head>
<body>
	<div class="left_BG">
		<div class="left_XZ" style="height:30px;" id="dv5" onclick="showdv('5');" onmouseover="isChange(this);" onmouseout="backChange(this);">整机实时监控</div>
		<div class="left_2J" id="dvShow5">
		   	<c:forEach var="a" items="${sessionScope.USER.privileges }" varStatus="status">
		   		<c:if test="${a==1}">
		   			<p><a id="zhengji1" href="VehicleAction_home.action" target="mainFrame" onClick="disflush()">整机列表</a>
		   			<a id="zhengji2" href="#" style="display: none;">努力加载中</a></p>
		   		</c:if>
			</c:forEach>
			<p><a id="jinji1" href="vehicleMnAction_home.action" target="mainFrame" onClick="disflush2()">紧急列表</a>
			<a id="jinji2" href="#" style="display: none;">努力加载中</a></p>
			
		   	<c:forEach var="a" items="${sessionScope.USER.privileges }" varStatus="status">
		   		<c:if test="${a==2 }">
		   			<p><a href="VehicleMapAction_queryVehicleListBaiduMap.action" target="mainFrame">整机地图</a></p>
		   		</c:if>
			</c:forEach>
		</div>
		<div class="left_XZ" style="height:30px;" id="dv6" onclick="showdv('6');" onmouseover="isChange(this);" onmouseout="backChange(this);">系统管理</div>
		<div class="left_2J" id="dvShow6">
			<p><a href="${pageContext.request.contextPath}/web/vehicleinfo/upload.jsp?userId=${sessionScope.USER.userId }" target="mainFrame">logo上传</a></p>
			<p><a href="../vehicleinfo/agentInfo.jsp" target="mainFrame">资料修改</a></p>
		   	<c:forEach var="a" items="${sessionScope.USER.privileges }" varStatus="status">
		   		<c:if test="${a==3 }">
		   			<p><a href='AgentManageAction.action' target='mainFrame'>代理商管理</a></p>
		   		</c:if>
		   		<c:if test="${a==4 }">
		   			<p><a href='vehicleManageAction_home.action' target='mainFrame'>车辆管理</a></p>
		   		</c:if>
		   		<c:if test="${a==5 }">
		   			<p><a href='terminalRenew.action' target='mainFrame'>终端续费管理</a></p>
		   		</c:if>
			</c:forEach>
		</div>
	</div>
</body>
</html>
