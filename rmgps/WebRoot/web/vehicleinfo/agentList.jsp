<%@ page language="java" import="java.util.*,com.jsd.web.login.vo.*" pageEncoding="UTF-8"
		contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

UserVO userVO=(UserVO)request.getSession().getAttribute("USER");
int level2=userVO.getLevel();
String lockprivilege0=userVO.getLockprivilege();
String agentprivilege0=userVO.getAgentprivilege();
String vehicleprivilege0=userVO.getVehicleprivilege();
String terminalprivilege0=userVO.getTerminalprivilege();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <title>代理商列表管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/web/css/window.css">
    <link href="<%=basePath %>/web/css/mainFrame.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/web/js/jquery-ui-1.10.3.custom/development-bundle/themes/base/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/web/js/jquery-ui-1.10.3.custom/development-bundle/demos/demos.css"/>
	<script type="text/javascript" src="<%=basePath%>/web/js/tools.js"></script>
	<script type="text/javascript" src="<%=basePath%>/web/js/agentInfoManage.js"></script>
	<script type="text/javascript" src="<%=basePath%>/web/js/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/web/js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=basePath%>web/js/window.js"></script>
	<script type="text/javascript">
	function modifyAgent2(userId2){
		$("#agentdata").html('');
		$("#userdata").html('');
		$("#spa").html("");
		$("#spa1").html("");
		$("#spa2").html("");
		$("#spa3").html("");
		$("#spa4").html("");
	  	$("#fp").text("");
	  	$("#sp").text("");
	  	$("#fpw").val("");
	  	$("#upw").val("");
	  	$("#spw").val("");
		if(userId2=="000"){
			$("#agent_name").val("");
			$("#user_code").val("");
			$("#uname").val("");
			$("#user_password").val("");
			$("#user_id").val("");
			$("#first_agent_name").val("");
			$("#first_user_code").val("");
			$("#user_id").val("");
		  	$("#remark").val("");
		<%
		if(lockprivilege0.equals("1")||lockprivilege0=="1"){
		%>
			$("#spa4").html("<input type='checkbox' id='checkbox_template' name='checkbox_template' value='1'><font color='red' size='2'>允许解锁</font>");
			<%
			}
		if((agentprivilege0.equals("3")||agentprivilege0=="3")&&(level2 < 3)){
			%>
  			$("#spa").html("<input type='checkbox' id='checkbox_template' name='checkbox_template' value='3'><font color='red' size='2'>创建代理商</font><br/>");
			<%
			}
		if(vehicleprivilege0.equals("4")||vehicleprivilege0=="4"){
			%>
			$("#spa2").html("<input type='checkbox' id='checkbox_template' name='checkbox_template' value='4'><font color='red' size='2'>管理车辆</font>");
			<%
			}
		if(terminalprivilege0.equals("5")||terminalprivilege0=="5"){
			%>
			$("#spa3").html("<input type='checkbox' id='checkbox_template' name='checkbox_template' value='5'><font color='red' size='2'>终端续费</font>");
		<%
			}
			%>
		$("#spa5").html("");
		$("#tr1").hide();$("#tr2").hide();
		$("#tr4").after($("#tr3"));$("#tr3").after($("#tr6"));
		$("#btn2").show();$("#btn1").hide();
		popCenterWindow();
		}else{
			$.ajax({
				url:"getAgentUserByUserId.action",
				data:"user_id=" + userId2,
				dataType:"json",
				type:"POST",
				success:function(data){
					$("#agent_name").val(data[0].agentName);
					$("#user_code").val(data[0].userCode);
					$("#user_password").val(data[0].userPassword);
					$("#user_id").val(data[0].userId);
					$("#first_agent_name").val(data[0].agentName);
					$("#first_user_code").val(data[0].userCode);
					$("#user_id").val(data[0].userId);
					$("#remark").val(data[0].remark);
					$("#uname").val(data[0].userName);
					var levels2=data[0].levels;
					var lockPrivilege2=data[0].lockPrivilege;
					var agentPrivilege2=data[0].agentPrivilege;
					var vehiclePrivilege2=data[0].vehiclePrivilege;
					var terminalPrivilege2=data[0].terminalPrivilege;
					var blocks2=data[0].blocks;
  				<%
  				if(lockprivilege0.equals("1")||lockprivilege0=="1"){
  				%>
						if(lockPrivilege2 == '1'){
							$("#spa4").html("<input type='checkbox' id='checkbox_template' name='checkbox_template' value='1' checked><font color='red' size='2'>允许解锁</font>");
						}else{
							$("#spa4").html("<input type='checkbox' id='checkbox_template' name='checkbox_template' value='1'><font color='red' size='2'>允许解锁</font>");
						}
  				<%
  				}
					if((agentprivilege0.equals("3")||agentprivilege0=="3")&&(level2 < 3)){
    			%>
						if(levels2 <= 3){
	  						if(agentPrivilege2 == "3"){
	  							if(blocks2==0){
	  								$("#spa").html("<input type='checkbox' id='checkbox_template' name='checkbox_template' value='3' checked><font color='red' size='2'>创建代理商</font><br/>");
	  							}else{
	  								$("#spa").html("<input disabled type='checkbox' id='checkbox_template' name='checkbox_template' value='3' checked><font color='red' size='2'>拥有代理商</font><br/>");
	  							}
	  						}else{
	  							if(blocks2==0){
	  								$("#spa").html("<input type='checkbox' id='checkbox_template' name='checkbox_template' value='3'><font color='red' size='2'>创建代理商</font><br/>");
	  							}else{
	  								$("#spa").html("<input disabled type='checkbox' id='checkbox_template' name='checkbox_template' value='3' checked><font color='red' size='2'>拥有代理商</font><br/>");
	  							}
	  						}
						}else{
							$("#spa").html("");
						}
				<%
    			}
					if(vehicleprivilege0.equals("4")||vehicleprivilege0=="4"){
    			%>
						if(vehiclePrivilege2 == '4'){
							$("#spa2").html("<input type='checkbox' id='checkbox_template' name='checkbox_template' value='4' checked><font color='red' size='2'>管理车辆</font>");
						}else{
							$("#spa2").html("<input type='checkbox' id='checkbox_template' name='checkbox_template' value='4'><font color='red' size='2'>管理车辆</font>");
						}
				<%
    			}
					if(terminalprivilege0.equals("5")||terminalprivilege0=="5"){
    				%>
						if(terminalPrivilege2 == '5'){
						$("#spa3").html("<input type='checkbox' id='checkbox_template' name='checkbox_template' value='5' checked><font color='red' size='2'>续费管理</font>");
					}else{
						$("#spa3").html("<input type='checkbox' id='checkbox_template' name='checkbox_template' value='5'><font color='red' size='2'>续费管理</font>");
					}
					<%
    				}
    				%>
    				$("#spa5").html("<input type='button' onclick='JavaScript:deleteAgent2("+data[0].userId+");' value='删除'>");
    				$("#tr1").show();$("#tr2").show();
    				$("#tr5").after($("#tr3"));$("#tr3").after($("#tr6"));
    				$("#btn1").show();$("#btn2").hide();
					popCenterWindow();
				},
				error:function(){
					alert("请重新操作！");
				}
			});
		}
	}
	</script>
  </head>
  <body >
    <form name="form1" method="post"> 
	    <div class="main_top2">
	    	<div style="float:left;width:855px;">
		   		<input type="button" value="新增" onclick="modifyAgent2('000');">
		   		<input type="button" value="修改" onclick="modifyAgent();">
		   		<input type="button" value="删除" onclick="deleteAgent();">
	   		</div>
	   	</div>
		<div class="mainFrame">
		   	<table border="0" id="dv3" >
		   		<tr>
		   			<th>
		   				<a href="javascript:void(0);" onclick="checkAll('checkbox_template2');">全选/</a>
		   				<a href="javascript:void(0);" onclick="uncheckAll('checkbox_template2');">反选</a>
		   			</th>
		   			<th>代理商名称</th>
		   			<th>登录名</th>
		   			<th>备注</th>
		   		</tr>
		   			<%
		   			int i=0;
					%>
		   		<c:forEach var="a" items="${requestScope.agentList }" varStatus="status">
					<tr>
						<td><input type="checkbox" name="checkbox_template2" value="${a.user_id }"></td>
						<td>
						<img onclick="changeFolder2('js<%=i %>','js<%=i %>q')"
						<c:choose>
						<c:when test="${a.agents!=null }">
						src="${pageContext.request.contextPath }/web/js/dtree/img/nolines_plus.gif" style="cursor:pointer;" id="igjs<%=i %>q"
						</c:when>
						<c:otherwise>
						src="${pageContext.request.contextPath }/web/js/dtree/img/page.gif"
						</c:otherwise>
						</c:choose>
						/>	<span onclick="modifyAgent2(${a.user_id })" onmouseover="$(this).css({'color':'#0000ff','cursor':'pointer','font-weight':'bold'});"
								onmouseout="$(this).css({'color':'#000000','cursor':'auto','font-weight':'normal'})">${a.agent_name }</span></td>
						<td>${a.user_code }</td>
						<td>${a.remark }</td>
					</tr>
		   			<%
			   		int j2=0;
					%>
					<c:forEach var='g' items='${a.agents }' varStatus='status'>
					<tr id="js<%=i %>q<%=j2 %>" style="display:none;">
						<td><input type="checkbox" name="checkbox_template2" value="${g.user_id }" disabled></td>
						<td style="padding-left:30px;">
						<img onclick="changeFolder2('js<%=i %><%=j2 %>m','js<%=i %><%=j2 %>mw')"
						<c:choose>
						<c:when test="${g.agents!=null }">
						src="${pageContext.request.contextPath }/web/js/dtree/img/nolines_plus.gif" style="cursor:pointer;" id="igjs<%=i %><%=j2 %>mw"
						</c:when>
						<c:otherwise>
						src="${pageContext.request.contextPath }/web/js/dtree/img/page.gif"
						</c:otherwise>
						</c:choose>
						/>	${g.agent_name }</td>
						<td>${g.user_code }</td>
						<td>${g.remark }</td>
					</tr>
							<%
							int j3=0;
							%>
						<c:forEach var='g2' items='${g.agents }' >
						<tr id="js<%=i %><%=j2 %>mw<%=j3 %>" style="display:none;">
							<td><input type="checkbox" name="checkbox_template2" value="${g2.user_id }" disabled></td>
							<td style="padding-left:60px;">
							<img onclick="changeFolder2('js<%=i %><%=j2 %>m<%=j3 %>n','js<%=i %><%=j2 %>m<%=j3 %>nc')"
							<c:choose>
							<c:when test="${g2.agents!=null }">
							src="${pageContext.request.contextPath }/web/js/dtree/img/nolines_plus.gif" style="cursor:pointer;"  id="igjs<%=i %><%=j2 %>m<%=j3 %>nc"
							</c:when>
							<c:otherwise>
							src="${pageContext.request.contextPath }/web/js/dtree/img/page.gif"
							</c:otherwise>
							</c:choose>
							/>	${g2.agent_name }</td>
							<td>${g2.user_code }</td>
							<td>${g2.remark }</td>
						</tr>
							<%
							int j4=0;
							%>
							<c:forEach var='g3' items='${g2.agents }' >
							<tr id="js<%=i %><%=j2 %>m<%=j3 %>nc<%=j4 %>" style="display:none;">
								<td><input type="checkbox" name="checkbox_template2" value="${g3.user_id }" disabled></td>
								<td style="padding-left:90px;"><img id="ig4" src="${pageContext.request.contextPath }/web/js/dtree/img/page.gif"/>${g3.agent_name }</td>
								<td>${g3.user_code }</td>
								<td>${g3.remark }</td>
							</tr>
							<%
							j4++;
							%>
							</c:forEach>
							<%
							j3++;
							%>
						</c:forEach>
		   			<%
			   		j2++;
					%>
					</c:forEach>
					<%
					i=i+1;
					%>
				</c:forEach>
		   	</table>
		</div>
	</form>
   <div class="demo-description">
		<div class="FrameDOWN">
			<jsp:include page="../include/pagination2.jsp"></jsp:include>
		</div>
   <div id='dialog-modal'></div></div>
   <div class="window" id="center" > 
		<div id="title" class="title"><label style="padding-left:160px;padding-right:150px;">代理商资料管理</label>
			<img width="28px" height="28px" src="${pageContext.request.contextPath }/web/images/X2.png" />
		</div> 
		<div class="content">
		<script type="text/javascript" charset="utf-8">
		var sin;
		var sin2;
		var sin3;
		var sin4;
		function saveresult(signal){
			var agenterror = document.getElementById('agentdata').innerText;
			var usererror = document.getElementById('userdata').innerText;
			if(agenterror != '' || usererror != ''){
				return false;
			}
			var actionvalue = '';
			//验证非空输入
			if(!validateEmpty()) return false;
			//提交保存还是修改
			if($("#user_id").val()==""){//保存
				actionvalue = 'InsertAgentAction.action';
			}else{//修改
				actionvalue = 'ModifyAgentAction.action';
			}
			var ids2="";
			$("input[name='checkbox_template']:checkbox:checked").each(function(){
				ids2+=","+$(this).val();
			});
			if(signal=="mod"){
				$("#uname").blur();
				if(sin4==0){
					$.smt(actionvalue,ids2,$("#user_password").val());
				}else{
					alert("请重新输入！");
				}
			}else if(signal=="psw"){
				$("#fpw").blur();$("#upw").blur();$("#spw").blur();
				if(sin==0&&sin2==0&&sin3==0){
					$.smt(actionvalue,ids2,$("#upw").val());
				}else{
					return false;
				}
			}else{
				$("#upw").blur();$("#spw").blur();$("#uname").blur();
				if(sin==0&&sin3==0&&sin4==0){
					$.smt(actionvalue,ids2,$("#upw").val());
				}else{
					return false;
				}
			}
		}
		$.smt=function(actionvalue,ids2,datas){
			$.ajax({
				url:actionvalue,
				type:"POST",
				data:{
					"privileges":ids2,
					"agent_name":$("#agent_name").val(),
					"user_code":$("#user_code").val(),
					"userName":$("#uname").val(),
					"user_id":$("#user_id").val(),
					"user_password":datas,
					"first_agent_name":$("#first_agent_name").val(),
					"first_user_code":$("#first_user_code").val(),
					"remark":$("#remark").val()
				},
				success:function(data){
					alert(data);
					if(data=="添加成功！"||data=="修改成功！"){
						$(".title img").click(function() {
							$(this).parent().parent().hide("slow");
							window.setTimeout(function(){window.location.href="AgentManageAction.action";}, 200);
						});
					}else{
						$(".title img").click(function() {
							$(this).parent().parent().hide("slow");
						});
					}
				}
			});
		}
		function validateEmpty(){
			var agent_name = document.getElementById("agent_name").value;
			if(!validataImport(agent_name,false)){
				return false;
			}else{
				var user_code = document.getElementById("user_code").value;
				if(!validataImport(user_code,false)){
					return false;
				}else{
	 				return true;
				}
			}
		}
	 	function validataImport(e,flag){
	  		var regExp = new RegExp(" ","g")
	  		var str = e;//输入值与经过替换空格后的值是否相等，如相等则说明没有空格。--不允许输入空格
	  		e = e.replace(regExp,"");
	  		var patrn = /^([a-zA-Z0-9]){1,}$/;
	  		if(e == ""){//如果没有输入
	  			alert("不允许为空");
	  			return false;
	  		}else{//如果输入了
	  			if(str == e){//不允许输入空格
	  				if(flag){
	  					if(!e.match(patrn)){//验证是否合格--只验证密码
				  			alert("输入内容只能为数字、字母、数字+字母");
				  			return false;
				  		}else{
				  			return true;
				  		}
	  				}else{
	  					return true;
	  				}
	  			}else{
	  				alert("不允许输入空格");
	  				return false;
	  			}
	  		}
	  	}
	  	function validataImport2(e){//输入为空，或者输入值有空格则返回
	  		var regExp = new RegExp(" ","g")
	  		var str = e;//输入值与经过替换空格后的值是否相等，如相等则说明没有空格。
	  		e = e.replace(regExp,"");
	  		var patrn = /^([a-zA-Z0-9]){1,}$/;
	  		if(e == ""){//如果没有输入
	  			return false;
	  		}else{//如果输入了
	  			if(str != e){//输入空格
	  				return false;
	  			}else{
	  				return true;
	  			}
	  		}
	  	}
	  	function clearIt(flag){
		  	if(flag=="mod"){
			  	$("#agent_name").val("");
			  	$("#user_code").val("");
			  	$("#agent_name").val("");
			  	$("#remark").val("");
			  	$("#uname").val("");
		  	}else if(flag=="psw"){
			  	$("#fpw").val("");
			  	$("#upw").val("");
			  	$("#spw").val("");
		  	}else{
			  	$("#agent_name").val("");
			  	$("#user_code").val("");
			  	$("#agent_name").val("");
			  	$("#upw").val("");
			  	$("#spw").val("");
			  	$("#remark").val("");
			  	$("#uname").val("");
		  	}
	  	}
	  	var randomnum = 0;
	  	function checkRepeatAgent(){//检查同一组织结构代理商是否存在相同的代理商
	  		randomnum = randomnum+1;
	  		var first_agent_name = $("#first_agent_name").val();
	  		var agentname = $("#agent_name").val();
	  		if(!validataImport2(agentname)){
	  			$("#agentdata").html('');
				return false;
			}
			if(first_agent_name==""){
				$.checkAgentName(agentname,randomnum);
			}else{
		  		if(first_agent_name != agentname){//与页面初始化的原始值一样，则不校验
					$.checkAgentName(agentname,randomnum);
		  		}else{
		  			$("#agentdata").html('');
		  		}
			}
	  	}
	  	$.checkAgentName=function(agentname,randomnum){
	  		$("#agentdata").html('');
	  		$.ajax({
				url:"getAgentByAgentName.action",
				type:"POST",
				data:{
					"agent_name":agentname,
					"flushrandom":randomnum
				},
				success:function(data){
					$("#agentdata").html(data);
					if(data!=""){
						$("#agent_name").focus();
					}
				}
			});
	  	}
	  	function checkRepeatUser(){//检查是否存在相同的用户
	  		randomnum = randomnum+1;
	  		var first_user_code = $("#first_user_code").val();
	  		var usercode = $("#user_code").val();
	  		if(first_user_code==""){
	  			$.checkUserCode(usercode,randomnum);
	  		}else{
		  		if(first_user_code != usercode){//与页面初始化的原始值一样，则不校验
		  			$.checkUserCode(usercode,randomnum);
		  		}else{
		  			$("#userdata").html('');
		  		}
	  		}
	  		if(!validataImport2(usercode)){
	  			$("#userdata").html('');
				return false;
			}
	  	}
	  	$.checkUserCode=function(usercode,randomnum){
	  		$("#userdata").html('');
			$.ajax({
				url:"getUserByUserCode.action",
				type:"POST",
				data:{
					"user_code":usercode,
					"flushrandom":randomnum
				},
				success:function(data){
					$("#userdata").html(data);
					if(data!=""){
						$("#user_code").focus();
					}
				}
			});
	  	}
	  	$(document).ready(function(){
		  	$("#fpw").blur(function(){
			  	if($("#fpw").val()==$("#user_password").val()){
				  	$("#fp").text("");
				  	sin2 = 0;
			  	}else{
				  	$("#fp").text("密码错误！");
				  	sin2 = 1;
			  	}
			});
		});
	  	$(document).ready(function(){
		  	$("#upw").blur(function(){
			  	var upw=$("#upw").val();
			  	if(!validataImport(upw,true)){
			  		sin3 = 1;
	 			}else{
	 				sin3 = 0;
	 			}
			});
		});
	  	$(document).ready(function(){
		  	$("#spw").blur(function(){
			  	if($("#spw").val()==$("#upw").val()){
				  	$("#sp").text("");
				  	sin = 0;
			  	}else{
				  	$("#sp").text("密码不一致！");
				  	sin = 1;
			  	}
			});
		});
	  	$(document).ready(function(){
		  	$("#uname").blur(function(){
			  	if($("#uname").val()==""){
				  	$("#un").text("显示名称不能为空！");
				  	sin4 = 1;
			  	}else{
				  	$("#un").text("");
				  	sin4 = 0;
			  	}
			});
		});
		</script>
    <center>
    	<div align="left" style="margin-top:30px;" id="dv3">
    		<form name="form2" action="DeleteAgentAction.action" method="post">
    			<table>
    				<tr>
    					<td style="padding-left:9px;">代理商名称：</td>
    					<td>
    						<input name="agent_name" id="agent_name" onblur="checkRepeatAgent();">
    					</td>
    					<td><font color="red" size="2">*必填</font>
    							<label id="agentdata" style="font-size: 13px;"></label></td>
    				</tr>
    				<tr>
    					<td style="padding-left:9px;">登录名：</td>
    					<td>
    						<input name="user_code" id="user_code" onblur="checkRepeatUser();"/>
    					</td>
    					<td><font color="red" size="2">*必填</font>
    							<label id="userdata" style="font-size: 13px;"></label></td>
    				</tr>
    				<tr id="tr5">
    					<td>显示名称：</td>
    					<td>	<input name="uname" id="uname" />
    					</td>
    					<td><font color="red" size="2">*必填</font>
    							<label id="un" style="font-size: 13px;"></label></td>
    				</tr>
    				<tr id="tr1">
    					<td></td>
    					<td style="padding-bottom:20px;">
	    					<input type="button" value="保存" onclick="saveresult('mod');">
	    					<input type="button" value="清空" onclick="clearIt('mod');">
	    					<span id="spa5"></span>
    					</td>
    				</tr>
    				<tr id="tr2">
    					<td style="padding-left:9px;">输入旧密码：</td>
    					<td><input type="password" name="fpw" id="fpw" ></td>
    					<td><font color="red" size="2">*输入数字或字母或数字+字母</font>
    							<label id="fp" style="font-size: 13px;"></label></td>
    				</tr>
    				<tr>
    					<td style="padding-left:9px;">输入密码：</td>
    					<td><input type="password" id="upw" name="upw"></td>
    					<td><font color="red" size="2">*输入数字或字母或数字+字母</font></td>
    				</tr>
    				<tr id="tr4">
    					<td style="padding-left:9px;">确认密码：</td>
    					<td><input type="password" name="spw" id="spw" ></td>
    					<td><font color="red" size="2">*重新输入密码</font>
    							<label id="sp" style="font-size:13px;" ></label></td>
    				</tr>
    				<tr id="tr3">
    					<td style="padding-left:9px;">权限列表：</td>
    					<td>
    						<span id="spa4"></span><br/>
            				<span id="spa"></span>
    						<span id="spa2"></span><br>
    						<span id="spa3"></span>
    					</td>
    				</tr>
    				<tr id="tr6">
    					<td style="padding-left:9px;">备注：</td>
    					<td colspan="2"><textarea id="remark" name="remark" rows="5" cols="30"></textarea></td>
    				</tr>
    				<tr>
    					<td></td>
    					<td id="btn1">
	    					<input type="button" value="保存" onclick="saveresult('psw');">
	    					<input type="button" value="清空" onclick="clearIt('psw');">
    					</td>
    					<td id="btn2" >
	    					<input type="button" value="保存"onclick="saveresult('ins');">
	    					<input type="button" value="清空" onclick="clearIt('ins');">
    					</td>
    				</tr>
    			</table>
    			<input name="first_agent_name" id="first_agent_name" type="hidden">
    			<input name="first_user_code" id="first_user_code" type="hidden">
    			<input name="user_id" id="user_id" type="hidden">
    			<input name="user_password" id="user_password" type="hidden">
    		</form>
    	</div>
    </center>
		</div> 
   </div>
  </body>
</html>
