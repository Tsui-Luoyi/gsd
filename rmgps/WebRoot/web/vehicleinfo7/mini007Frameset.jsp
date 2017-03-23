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
    <title>迷你OO7</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <frameset rows="60,*" name="totalframe" id="totalframe" frameborder="no" border="0" framespacing="0">
	<frame src="web/vehicleinfo7/pageTop.jsp?vno=<%=URLEncoder.encode(vno,"UTF-8") %>" name="topFrame" id="topFrame" scrolling="No" noresize="noresize" title="topFrame" />
  	<frame src="testMnCurrentInfoAction_testFindBaiduCurrentInfo.action?vno=<%=URLEncoder.encode(vno,"UTF-8") %>&map=1" name="contentFrame" id="contentFrame" scrolling="yes" noresize="noresize" title="contentFrame" />
  </frameset>
</html>
