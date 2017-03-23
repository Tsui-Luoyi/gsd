<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"+"web/";
//response.setStatus(200);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'error.jsp' starting page</title>
    <meta http-equiv="refresh" content="5;url=<%=basePath%>mainFrame.jsp"> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<center>
  		<div style="margin-top: 200px;">
	  		<label>
	  			<font color="#ff7700">系统出现异常，请您通知我们一下，谢谢！</font>
	  		</label><br />
	  		<label>5秒后，系统自动跳转到首页！</label>
	  	</div>
  	</center>
  </body>
</html>
