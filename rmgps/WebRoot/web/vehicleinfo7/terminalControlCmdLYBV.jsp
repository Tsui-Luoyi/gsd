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
	<script type="text/javascript" src="web/js/mn/terminalControlCmdLYB.js"></script>
	<script type="text/javascript" src="web/js/jquery.js"></script>
	<script type="text/javascript" src="web/js/My97DatePicker/WdatePicker.js"></script>
  </head>
  <script type="text/javascript">
  $(function() {
	  $("#zhengchang").hide();
	  $("#alarm").hide();
	  var upinterval = $("#1").val();
	  var startminute =  $("#2").val();
	  var starthour =  $("#3").val();
	  var emergencyState =  $("#4").val();
	  var hour =  $("#5").val();
	  var minute =  $("#6").val();
	  var everydayUpNum =  $("#7").val();
	  var ipaddress =  $("#8").val();
	  var array = ipaddress.split("-");
	  /* 默认为工作状态 */
	  $("#devtermstate2").attr("checked",true);
	  $("#alarm").show();
	  
	  $("#upinterval option[id='upinterval_"+upinterval+"']").attr("selected",true);
	  if(starthour != "") {
	 	 $("#starthour").val(starthour+":"+startminute);
	  }else{
		  $("#starthour").val("00:00");
	  }
	  $("#emergencyState option[id='emergencyState_"+emergencyState+"']").attr("selected",true);
	  if(hour != "" && hour != "24") {
	  	$("#hour").val(hour+":"+minute);
	  }else{
		$("#hour").val("00:00");
	  }
	  $("#everydayUpNum option[id='everydayUpNum_"+everydayUpNum+"']").attr("selected",true);
	  $("#ipaddress0").val(array[0]);
	  $("#ipaddress1").val(array[1]);
	  $("#ipaddress2").val(array[2]);
	  $("#ipaddress3").val(array[3]);
	  if(emergencyState == "" || emergencyState == 0 || emergencyState == 3) {
			  $("#stop").hide();
	  }else if(emergencyState == 1){
		  	  $("#time").show();
			  $("#stop").show();
	  }else{
		  $("#time").hide();
		  $("#stop").show();
	  }
  });
  
  function change (flag) {
	  if(flag == 1) {
		  $("#zhengchang").show();
		  $("#alarm").hide();
	  }else if(flag == 0 || flag == 3) {
		  $("#alarm").show();
		  $("#zhengchang").hide();
	  }
  }
  
  function changeState (obj) {
	 var state = $(obj).val();
	 if(state == 0 || state == 3) {
		 $("#stop").hide();
		 $("#time").hide();
	 }else if(state == 1){
		 $("#time").show();
		 $("#stop").show();
	 }else{
		 $("#time").hide();
		 $("#stop").show();
	 }
  }
  </script>
  <body> 
  	<input type="hidden" value="${requestScope.vno }" id="vno">
    <div class="Content">
		<table cellpadding="8">
		  <tr>
			<td bgcolor="#C5EDFE">
				<div style="width:100%;float:center;" align="center">
					<input type="radio" onclick="change(1);" id="devtermstate1" name="devtermstate" value="1">参数设置
		        	<input type="radio" onclick="change(0);" id="devtermstate2" name="devtermstate" value="0">模式设置
		    	</div>
			</td>
		  </tr>
		  <tr>
		    <td bgcolor="#C5EDFE" id ="changeArea">
		    	<div style="width:100%;float:center;" id="zhengchang" >
		    		上传周期
		    		<select name="cycday" id="cycday">
		    			<option id="daydefault" value="1">默认</option>
		    			<option id="day_1" value="1">1天</option>
		    			<option id="day_2" value="2">2天</option>
		    			<option id="day_3" value="3">3天</option>
		    			<option id="day_4" value="4">4天</option>
		    			<option id="day_5" value="5">5天</option>
		    			<option id="day_6" value="6">6天</option>
		    			<option id="day_7" value="7">7天</option>
		    		</select>
		  			上传次数
			    	<select name="everydayUpNum" id="everydayUpNum">
						<option id="everydayUpNum_1" value="1">1次</option>
			    		<option id="everydayUpNum_2" value="2">2次</option>
			    		<option id="everydayUpNum_3" value="3">3次</option>
			    		<option id="everydayUpNum_4" value="4">4次</option>
					</select>
		    		预定上传时间：
		    		<input type="text" style="width:50px;" name="hour" id="hour" onclick="WdatePicker({dateFmt:'HH:mm'});"/>
	             	<span style="float: right">
						<input name="button5" type="button" value="采集" onclick="sendGatherCmd('34','采集','参数设置');"/>
						<input name="button5" type="button" value="设置" onclick="sendSettingCmd('304','设置','参数设置');"/>
					</span>
		    	</div>
		    	<!-- 模式选择 -->
				<div id="alarm" >
					模式选择:
					<select name="emergencyState" id="emergencyState" onchange="changeState(this);">
						<option id="emergencyState_0" value="0" >安全模式</option>
						<option id="emergencyState_1" value="1" >计划模式</option>
						<option id="emergencyState_2" value="2" >紧急模式</option>
						<option id="emergencyState_3" value="3" >关注模式</option>
					</select>
					<span id="stop">
						<span id="time">
				    	时间:
				    	<input type="text" style="width:50px;" name="starthour" id="starthour" onclick="WdatePicker({dateFmt:'HH:mm'});"/>
		             	</span>
						间隔:
				    	<select name="upinterval" id="upinterval">
							<option id="upinterval_10" value="10" >10</option>
							<option id="upinterval_30" value="30" >30</option>
							<option id="upinterval_60" value="60" >60</option>
						</select>
						次数:
					    <input name="residue" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " value="${requestScope.vo.residue }" id="residue" type="text" size="3"> (范围(000~255),0为永久)
				   	</span>
					<span style="float: right">
						<input name="button2" type="button" value="采集" onclick="sendGatherCmd('33','采集','模式选择');"/>
						<input name="button2" type="button" value="设置" onclick="sendSettingCmd('303','设置','模式选择');"/>
					</span>
				</div>
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
				  <td align="center">${cq.cmd_name }<input type="hidden" id="${cq.cmd_id}"></td>
				  <td align="center">
				  	<c:if test="${cq.cmd_type == '1'}">采集</c:if>
				  	<c:if test="${cq.cmd_type == '2'}">设置</c:if>
				  </td>
				  <td align="center">${fn:substring(cq.start_time,0,19) }</td>
				  <td align="center"><input value="取消发送" type="button" onclick="cancel_cmd('201_lyb','${cq.cmd_id}',this);"></td>
			  </tr>
		  </c:forEach>
		</table>
	</div>
	<input type="hidden" value="${requestScope.get.upinterval }" id="1">
	<input type="hidden" value="${requestScope.get.startminute }" id="2">
	<input type="hidden" value="${requestScope.get.starthour }" id="3">
	<input type="hidden" value="${requestScope.vo.emergencyState }" id="4">
	<input type="hidden" value="${requestScope.get.hour }" id="5">
	<input type="hidden" value="${requestScope.get.minute }" id="6">
	<input type="hidden" value="${requestScope.get.everydayUpNum }" id="7">
	<input type="hidden" value="${requestScope.get.ipaddress }" id="8">
  </body>
</html>