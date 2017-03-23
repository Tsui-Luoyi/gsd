<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String vno = URLDecoder.decode(request.getParameter("vno"),"UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link href="web/css/style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
  	<div class="Navigation">
    	<li style="list-style: none;"><img src="web/images/xingX.png" /> => <%=vno %> => <b>工作信息</b></li>
	</div>
	<div class="Classification">
		<li class="huang"><a id="mapshow" href="testMnCurrentInfoAction_testFindBaiduCurrentInfo.action?vno=<%=URLEncoder.encode(vno,"UTF-8") %>&map=1" onclick="javascript:cssChange(this);" target="contentFrame">最新数据</a></li>
		<li><a href="mnCmdAction.action?vno=<%=URLEncoder.encode(vno,"UTF-8") %>" onclick="javascript:cssChange(this);" target="contentFrame">采集设置</a></li>
		<li><a href="mnCmdHistoryAction.action?vno=<%=URLEncoder.encode(vno,"UTF-8") %>" onclick="javascript:cssChange(this);" target="contentFrame">命令日志</a></li>
		<li><a href="web/vehicleinfo7/vehicleBaidumapDetailInfo.jsp?vno=<%=URLEncoder.encode(vno,"UTF-8") %>" onclick="javascript:cssChange(this);" target="contentFrame">历史轨迹</a></li>
		<li><a href="mnLoginHistoryAction.action?vno=<%=URLEncoder.encode(vno,"UTF-8") %>" onclick="javascript:cssChange(this);" target="contentFrame">登录信息</a></li>
		<li><a href="VehicleRecordAction007.action?vno=<%=URLEncoder.encode(vno,"UTF-8") %>" onclick="javascript:cssChange(this);" target="contentFrame">整机档案</a></li>
		<li style="float:right"><select onchange=mapselect(this) >
			<option value="testMnCurrentInfoAction_testFindBaiduCurrentInfo.action?vno=<%=URLEncoder.encode(vno,"UTF-8") %>&map=1" selected >百度地图</option>
			<option value="testMnCurrentInfoAction_testFindBaiduCurrentInfo.action?vno=<%=URLEncoder.encode(vno,"UTF-8") %>&map=2">谷歌地图</option>
			<option value="testMnCurrentInfoAction_testFindBaiduCurrentInfo.action?vno=<%=URLEncoder.encode(vno,"UTF-8") %>&map=3">高德地图</option>
		</select> </li>
	</div>
  </body>
  <script type="text/javascript">
  	function cssChange(obj){
		for(var i=0;i<obj.parentNode.parentNode.childNodes.length;i++){
			if(obj.parentNode.parentNode.childNodes[i].className != undefined){
				obj.parentNode.parentNode.childNodes[i].className = "";
			}
		}
		obj.parentNode.className = "huang";
	}
  	
  	function mapselect(obj) {
  		var mapshowurl = obj.options[obj.selectedIndex].value;
  		var ms = document.getElementById("mapshow");
  		ms.href = mapshowurl;
  		ms.click();
  	}
	
  </script>
</html>
