<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'loginHistoryInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link href="web/css/style.css" rel="stylesheet" type="text/css" />
	<script src="web/js/datepicker/WdatePicker.js"  charset="GB2312"></script>
	<script type="text/javascript">
  		function emptyQuery(){
  			document.getElementById('start_time').value = '';
  			document.getElementById('end_time').value = '';
  		}
  	</script>
  </head>
  
  <body>
	<form name="form1" action="mnLoginHistoryAction.action" method="post">
		<input type="hidden" name="vno" value="${requestScope.vno }">
		<div class="Search">
			<li>开始时间：<input name="start_time" id="start_time" type="text" size="15" value="${requestScope.start_time }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></li>
			<li>结束时间：<input name="end_time" id="end_time" type="text" size="15" value="${requestScope.end_time }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></li>
			<li style="margin-top: -15px;"><a href="javascript:document.form1.submit();">搜 索</a></li>
			<li style="margin-top: -15px;"><a href="javascript:emptyQuery();">重 置</a></li>
		</div>
	</form>
	<div class="Content">
		<table>
		    <tr>
		      <th>接收时间</th>
		      <th>定位状态</th>
		      <th>位置信息</th>
		      <th>信号强度</th>
		    </tr>
		    <c:forEach var="lh" items="${requestScope.loginHistoryList }" varStatus="u">
				<tr>
					<td align="center">${fn:substring(lh.revtime,0,19) }</td>
					<td align="center">
						<c:if test="${lh.locate_flag == 'A'}">卫星定位</c:if>
						<c:if test="${lh.locate_flag == 'S'}">基站定位</c:if>
						<c:if test="${lh.locate_flag == 'V'}">LBS数据库正在更新</c:if>
					</td>
					<td><input value="${lh.addr}" style="width:230px;border:0px;background-color:transparent;"></input></td>
					<td align="center">${lh.gsmstrong }</td>
				</tr>
			</c:forEach>
		</table>
		<div class="tablediv">
			<jsp:include page="../include/pagination2.jsp"></jsp:include>
		</div>
	</div>
  </body>
</html>
