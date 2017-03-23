<%@page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.jsd.web.login.vo.UserVO"%>
<%
   UserVO user = (UserVO)session.getAttribute("USER");
   String userName = user.getUserName();
   String userId = user.getUserCode();
   int loginCount = user.getLoginCount();
   String lastLoginDate = user.getLastLoginDate();
   String lastLoginIP = user.getLoginIP();
   String userRoleName = user.getRoleName();
   String orgname = user.getOrgName();
   String otherFlag = (String)session.getAttribute("otherFlag");
   String otherUserCode = (String)session.getAttribute("otherUserCode");
   String otherUserName = (String)session.getAttribute("otherUserName");
   String otherPassword = (String)session.getAttribute("otherPassword");
   
%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="css/home_mainFrame.css" rel="stylesheet" type="text/css">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/web/images/3.ico"/>
</head>
<script type="text/javascript" src="js/tanchu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/jquery-1.8.3.js"></script>


<body> 
<div>
	<div class="home_mainFrame">
		<div class="home_mainFrame_top">
			<li class="left">欢迎 <%=orgname %> <span class="xinghaoBull"></span><span class="xinghaoCSS"><%=userName %></span>登录本系统</li>
			<li class="right"><a href="#"><img src="images/Help.png" alt="帮助" border="0"></a></li>
		</div>
		<div class="home_mainFrame_center">
			本系统于2010年7月20日上线，欢迎大家使用！</div>
		<div class="home_mainFrame_down">
			<li>用户ID：<span class="xinghaoCSS"><%=userId %></span></li>
			<li>这是您今天第<a href="html/X_09/09_01.html" target="mainFrame"><%=loginCount %></a> 次登陆本系统</li>
			<li>上次登录时间为：<span class="xinghaoCSS"><%=lastLoginDate %> </span></li>
			<li>上次登录IP为：<span class="xinghaoCSS"><%=lastLoginIP %></span></li>
			<!-- <li>您有<a href="#">2</a> 封站内信需要处理，请及时查收！</li> -->
			<li id="lianxi">如有疑问请联系系统管理员<a href="mailto:service@gstarworld.com">service@gstarworld.com</a></li>
			<!--
			<li style="font-family:'宋体'; color: red">系统通知</li>
			<li style="font-family:'宋体'; color: red">尊敬的用户您好</li>
			-->
		</div>
	</div>
</div>
</body>
<script type="text/javascript" charset="utf-8">
	var otherFlag = '<%=otherFlag %>';
	var isFirst = "true";
	if ('y'== otherFlag){
		var f1 = '<%=session.getAttribute("isFirst")%>';
		var f2 = '<%=userName %>';
		var f3 = '<%=session.getAttribute("oldUserName")%>';
		if(("null" != f1) && (f2 == f3)){
			isFirst = "false";
		}else{
			isFirst = "true";
		}
		
		var otherUserCode = '<%=otherUserCode %>';
		var otherUserName = '<%=otherUserName %>';
		var otherPassword = '<%=otherPassword %>';
		var url = "http://www.gpsonline.cn/view/GPSMain.aspx?param0=0&param1="+otherUserCode+"&param2="+otherUserName+"&param3="+otherPassword+"&param4=false";
		
		if ("true"==isFirst){
			window.open(url);
			// 设置非首次访问
			<% 
				session.setAttribute("isFirst", "false");
				session.setAttribute("oldUserName", userName);
			%>
		} 
		
		// 为当前网页增加跳转链接
		var text = "<li>有线产品管理平台，请点击<a href='javascript:void(0);' onclick='enterOther();'>&nbsp;有线平台 </a>&nbsp;进入";
		$("#lianxi").after(text);
	}
	
	function enterOther(){
		window.open(url);
	}
</script>
</html>