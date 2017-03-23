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
	<script type="text/javascript" src="web/js/mn/terminalControlCmd.js"></script>
	<script type="text/javascript" src="web/js/jquery.js"></script>
  </head>
  
  <body> 
  	<input type="hidden" value="${requestScope.vno }" id="vno">
    <div class="Content">
		<table cellpadding="8">
		  <tr>
		    <td bgcolor="#C5EDFE" width="49%">
		    	IP地址和端口号-
		    	<input name="ipaddress" id="ipaddress" type="text" size="15" value="${requestScope.vo.ipaddress }"/>
		        -<input name="port" id="port" type="text" size="3" value="${requestScope.vo.port }"/><br />
				<input type="button" value="采集" onclick="sendGatherCmd('1','采集','IP地址和端口号');" disabled/>
				<input type="button" value="设置" onclick="sendSettingCmd('101','设置','IP地址和端口号');" disabled/>
			</td>
		    <td bgcolor="#C5EDFE">
		    	上传时间间隔：正常-
		    	<select name="uptimenormal" id="uptimenormal">
					<c:choose>
						<c:when test="${requestScope.vo.up_time_normal=='2'}">
							<option value="0">一天</option>
				    		<option value="2" selected="selected">一星期</option>
						</c:when>
						<c:otherwise>
							<option value="0" selected="selected">一天</option>
				    		<option value="2">一星期</option>
						</c:otherwise>
				    </c:choose>
				</select>
				紧急-
				<select name="uptimeurgent" id="uptimeurgent">
					<c:choose>
						<c:when test="${requestScope.vo.up_time_urgent=='1'}">
							<option value="0">十分钟</option>
				    		<option value="1" selected="selected">半小时</option>
				    		<option value="2">一小时</option>
						</c:when>
						<c:when test="${requestScope.vo.up_time_urgent=='2'}">
							<option value="0">十分钟</option>
				    		<option value="1">半小时</option>
				    		<option value="2" selected="selected">一小时</option>
						</c:when>
						<c:otherwise>
							<option value="0" selected="selected">十分钟</option>
				    		<option value="1">半小时</option>
				    		<option value="2">一小时</option>
						</c:otherwise>
				    </c:choose>
				</select>
				<br />
				<input name="button2" type="button" value="采集" onclick="sendGatherCmd('2','采集','上传时间间隔');"/>
				<input name="button2" type="button" value="设置" onclick="sendSettingCmd('102','设置','上传时间间隔');"/>
			</td>
		  </tr>
		  <tr>
		  	<td bgcolor="#C5EDFE">
		    	<div style="width:100%;float:left;">
		    		<c:choose>
						<c:when test="${requestScope.vo.devterm_state=='1'}">
							<div style="float:left;">
					    		终端状态：
						        <select name="devtermstate" id="devtermstate" onchange="showTime(this);">
									<option value="0">正常</option>
						        	<option value="1" selected="selected">紧急</option>
						        </select>
					    	</div>
							<div id="persistTime">
								&nbsp;&nbsp;持续时间
								<input name="persist_time" id="persist_time" type="text" size="1" value="${requestScope.vo.duration }" />小时,范围0~36
							</div>
						</c:when>
						<c:otherwise>
							<div style="float:left;">
					    		终端状态：
						        <select name="devtermstate" id="devtermstate" onchange="showTime(this);">
						        	<option value="0" selected="selected">正常</option>
						        	<option value="1">紧急</option>
						        </select>
					    	</div>
							<div name="persistTime" id="persistTime" style="display: none;">
								&nbsp;&nbsp;持续时间
								<input name="persist_time" id="persist_time" type="text" size="1" />小时,范围0~36
							</div>
						</c:otherwise>
				    </c:choose>
		    	</div>
				<div style="width:100%;">
					<input name="button3" type="button" value="采集" onclick="sendGatherCmd('3','采集','终端状态');"/>
					<input name="button3" type="button" value="设置" onclick="sendSettingCmd('103','设置','终端状态');"/>
				</div>
			</td>
		  	<td bgcolor="#C5EDFE">
		  		<div style="width:100%;float:left;">
		    		预定上传时间：
				    <select name="week" id="week">
						<c:choose>
							<c:when test="${requestScope.vo.week=='2'}">
								<option value="1">星期一</option>
					    		<option value="2" selected="selected">星期二</option>
					    		<option value="3">星期三</option>
					    		<option value="4">星期四</option>
					    		<option value="5">星期五</option>
					    		<option value="6">星期六</option>
					    		<option value="7">星期日</option>
							</c:when>
							<c:when test="${requestScope.vo.week=='3'}">
								<option value="1">星期一</option>
					    		<option value="2">星期二</option>
					    		<option value="3" selected="selected">星期三</option>
					    		<option value="4">星期四</option>
					    		<option value="5">星期五</option>
					    		<option value="6">星期六</option>
					    		<option value="7">星期日</option>
							</c:when>
							<c:when test="${requestScope.vo.week=='4'}">
								<option value="1">星期一</option>
					    		<option value="2">星期二</option>
					    		<option value="3">星期三</option>
					    		<option value="4" selected="selected">星期四</option>
					    		<option value="5">星期五</option>
					    		<option value="6">星期六</option>
					    		<option value="7">星期日</option>
							</c:when>
							<c:when test="${requestScope.vo.week=='5'}">
								<option value="1">星期一</option>
					    		<option value="2">星期二</option>
					    		<option value="3">星期三</option>
					    		<option value="4">星期四</option>
					    		<option value="5" selected="selected">星期五</option>
					    		<option value="6">星期六</option>
					    		<option value="7">星期日</option>
							</c:when>
							<c:when test="${requestScope.vo.week=='6'}">
								<option value="1">星期一</option>
					    		<option value="2">星期二</option>
					    		<option value="3">星期三</option>
					    		<option value="4">星期四</option>
					    		<option value="5">星期五</option>
					    		<option value="6" selected="selected">星期六</option>
					    		<option value="7">星期日</option>
							</c:when>
							<c:when test="${requestScope.vo.week=='7'}">
								<option value="1">星期一</option>
					    		<option value="2">星期二</option>
					    		<option value="3">星期三</option>
					    		<option value="4">星期四</option>
					    		<option value="5">星期五</option>
					    		<option value="6">星期六</option>
					    		<option value="7" selected="selected">星期日</option>
							</c:when>
							<c:otherwise>
								<option value="1" selected="selected">星期一</option>
					    		<option value="2">星期二</option>
					    		<option value="3">星期三</option>
					    		<option value="4">星期四</option>
					    		<option value="5">星期五</option>
					    		<option value="6">星期六</option>
					    		<option value="7">星期日</option>
							</c:otherwise>
					    </c:choose>
					</select>
				    -<input name="hour" id="hour" size="1" value="${requestScope.vo.hour }">时
				    -<input name="minute" id="minute" size="1" value="${requestScope.vo.minute }">分
		    	</div>
				<div style="width:100%;">
					<input name="button5" type="button" value="采集" onclick="sendGatherCmd('5','采集','预定上传时间');"/>
					<input name="button5" type="button" value="设置" onclick="sendSettingCmd('105','设置','预定上传时间');"/>
					（范围：0-23时，0-59分）
				</div>
		  	</td>
		  </tr>
		  <tr>
		    <td colspan="3">
		    	<b>采集终端工作参数：
		        	<input name="button" type="button" value="采集" onclick="sendGatherCmd('4','采集','终端工作参数');"/>
		        </b>
		    </td>
		  </tr>
		  <tr>
		    <td colspan="3">
		    	成功登录次数：
		        <input name="textfield3" type="text" size="8" value="${requestScope.vo.success_login_num }" />
		      	失败登录次数： 
		      	<input name="textfield32" type="text" size="8" value="${requestScope.vo.fail_login_num }" />
		    </td>
		  </tr>
		  <tr>
		    <td colspan="3">
		    	成功定位次数：
		        <input name="textfield33" type="text" size="8" value="${requestScope.vo.success_locate_num }" />
				失败定位次数：
				<input name="textfield322" type="text" size="8" value="${requestScope.vo.fail_locate_num }" />
			</td>
		  </tr>
		</table>
	</div><br>
	<div class="msgdiv" style="overflow-x: auto; overflow-y: auto; height: 350px; width:100%;">
		<table id="cmdqueuetable">
		  <tr>
		    <th>命令名称</th>
		    <th>命令类型</th>
		    <th>发送时间</th>
		    <th>取消</th>
		  </tr>
		  <c:forEach var="cq" items="${requestScope.cmdQueueList }" varStatus="status">
			  <tr>
				  <td>${cq.cmd_name }<input type="hidden" id="${cq.cmd_id}"></td>
				  <td align="center">
				  	<c:if test="${cq.cmd_type == '1'}">采集</c:if>
				  	<c:if test="${cq.cmd_type == '2'}">设置</c:if>
				  </td>
				  <td align="center">${fn:substring(cq.start_time,0,19) }</td>
				  <td align="center"><input value="取消发送" type="button" onclick="cancel_cmd('201','${cq.cmd_id}',this);"></td>
			  </tr>
		  </c:forEach>
		</table>
	</div>
  </body>
</html>
