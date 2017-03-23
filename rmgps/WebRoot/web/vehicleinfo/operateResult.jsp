<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String backmsgFromjsp = request.getParameter("resultFromjsp");//从jsp页面跳转过来
String backmsgFromAction = (String)request.getAttribute("operateresult");//从Servlet跳转过来
String backmsg = "";
String msgshow = "";
String forwordHref = "";

if(backmsgFromjsp == null || "".equals(backmsgFromjsp)){
	backmsg = backmsgFromAction;
}else{
	backmsg = URLDecoder.decode(backmsgFromjsp,"UTF-8");
}

if(backmsg == null || "".equals(backmsg.trim()) || backmsg.split("\\|").length != 2){
	msgshow = "操作返回信息异常";
	forwordHref = basePath + "error.jsp";
}else{
	msgshow = backmsg.split("\\|")[0];
	forwordHref = backmsg.split("\\|")[1];
}
%>
<script type="text/javascript">
<!--
	alert("<%=msgshow %>");
	document.location.href = "<%=forwordHref %>";
//-->
</script>
