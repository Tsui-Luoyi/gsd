<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.jsd.web.login.vo.UserVO"%>
<%
   UserVO user = (UserVO)session.getAttribute("USER");
   String userName = user.getUserName();
   String userOrgname = user.getOrgName();
   String path = request.getContextPath();
   String copyNum = user.getCopyNum();
   String logoPath=user.getLogoPath();
   String logoClass = "topBG";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
<title>无标题文档</title>
<link href="../css/topFrame.css" rel="stylesheet" type="text/css">
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath }/web/images/5.ico"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/js/jquery.form.js"></script>
<script type="text/javascript" src="../js/inputBG.js"></script>
<script src="../js/prototype.js"></script>
</head>
<body>
	<div class="<%=logoClass %>">
	<div style="margin:0;padding:0;clear:both;position:absolute;top:0;left:20;">
		<table border="0" cellpadding="0" cellspacing="0" style="margin:0;padding:0;">
			<tr>
				<td id="imgTd"><img id="logo" alt="<%=userOrgname %>" src="${pageContext.request.contextPath}<%=logoPath %>" style="margin:0;padding:0;" width="56" height="56"/></td>
				<td valign="middle" style="padding-top:16px;padding-left:8px;" ><h2 style="color:#ffffff;"><%=userOrgname %></h2></td>
			</tr>
		</table>
	</div>
		<div class="top_an">
			<li><a href="<%=request.getContextPath()%>/logoutAction.action">退出系统</a></li>
			<li><a href="#">系统帮助</a></li>
			<li><a href="http://www.gstarworld.com" target="_blank">公司网站</a></li>
		</div>
		<div id="time_name"><%=userOrgname %>&nbsp;<font color="#CAE1FF">|</font>&nbsp;登录用户:<%=userName %>&nbsp;<font color="#CAE1FF">|</font>&nbsp;版本:<%=copyNum %></div>
	</div>
</body>
</html>
