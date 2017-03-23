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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link href="web/css/style.css" rel="stylesheet" type="text/css" />
	<script src="web/js/datepicker/WdatePicker.js"  charset="GB2312"></script>
  </head>
  
  <body>
    <form name="form1" action="mnCmdHistoryAction.action" method="post">
    	<input type="hidden" name="vno" value="${requestScope.vno }">
    	<div class="Search">
			<li>
				命令名称：
				<input name="cmd_name" id="cmd_name" value="${requestScope.cmd_name }">
				<!-- 
				<select name="cmd_name" id="cmd_name">
					<c:choose>
						<c:when test="${requestScope.cmd_name=='1'}">
							<option value="0">选择</option>
							<option value="1" selected="selected">ip和port</option>
							<option value="2">上传时间间隔</option>
							<option value="3">终端状态</option>
							<option value="4">终端工作参数</option>
						</c:when>
						<c:when test="${requestScope.cmd_name=='2'}">
							<option value="0">选择</option>
							<option value="1">ip和port</option>
							<option value="2" selected="selected">上传时间间隔</option>
							<option value="3">终端状态</option>
							<option value="4">终端工作参数</option>
						</c:when>
						<c:when test="${requestScope.cmd_name=='3'}">
							<option value="0">选择</option>
							<option value="1">ip和port</option>
							<option value="2">上传时间间隔</option>
							<option value="3" selected="selected">终端状态</option>
							<option value="4">终端工作参数</option>
						</c:when>
						<c:when test="${requestScope.cmd_name=='4'}">
							<option value="0">选择</option>
							<option value="1">ip和port</option>
							<option value="2">上传时间间隔</option>
							<option value="3">终端状态</option>
							<option value="4" selected="selected">终端工作参数</option>
						</c:when>
						<c:otherwise>
							<option value="0" selected="selected">选择</option>
							<option value="1">ip和port</option>
							<option value="2">上传时间间隔</option>
							<option value="3">终端状态</option>
							<option value="4">终端工作参数</option>
						</c:otherwise>
				    </c:choose>
				 </select>
				 -->
			</li>
			<li>
				类型：
				<select name="cmd_type" id="cmd_type">
					<c:choose>
						<c:when test="${requestScope.cmd_type=='1'}">
							<option value="0">选择</option>
							<option value="1" selected="selected">采集</option>
							<option value="2">设置</option>
						</c:when>
						<c:when test="${requestScope.cmd_type=='2'}">
							<option value="0">选择</option>
							<option value="1">采集</option>
							<option value="2" selected="selected">设置</option>
						</c:when>
						<c:otherwise>
							<option value="0" selected="selected">选择</option>
							<option value="1">采集</option>
							<option value="2">设置</option>
						</c:otherwise>
				    </c:choose>
				</select>
			</li>
			<li>开始时间：<input name="start_time" id="start_time" type="text" size="15" value="${requestScope.start_time }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></li>
			<li>结束时间：<input name="end_time" id="end_time" type="text" size="15" value="${requestScope.end_time }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></li>
			<li style="margin-top: -15px;"><a href="javascript:document.form1.submit();">搜 索</a></li>
			<li style="margin-top: -15px;"><a href="javascript:emptyQuery();">重 置</a></li>
		</div>
	</form>
		<div class="Content">
			<table>
			  <tr>
			    <th>命令名称</th>
			    <th>类型</th>
			    <th>发送时间</th>
			    <th>接收时间</th>
			    <th>结果</th>
			  </tr>
			  <c:forEach var="ch" items="${requestScope.cmdHistoryList }" varStatus="status">
				<tr>
					<td>${ch.cmd_name }</td>
					<td align="center">
						<c:if test="${ch.cmd_type == '1'}">采集</c:if>
						<c:if test="${ch.cmd_type == '2'}">设置</c:if>
					</td>
					<td align="center">${fn:substring(ch.start_time,0,19) }</td>
					<td align="center">${fn:substring(ch.end_time,0,19) }</td>
					<td align="center">${ch.receive_result }</td>
				</tr>
			  </c:forEach>
			</table>
			<div class="tablediv">
				<jsp:include page="../include/pagination2.jsp"></jsp:include>
			</div>
		</div>
  </body>
  <script type="text/javascript">
  		function emptyQuery(){
  			document.getElementById('cmd_name').value = '';
  			document.getElementById('cmd_type').options[0].selected= 'selected';
  			document.getElementById('start_time').value = '';
  			document.getElementById('end_time').value = '';
  		}
  </script>
</html>
