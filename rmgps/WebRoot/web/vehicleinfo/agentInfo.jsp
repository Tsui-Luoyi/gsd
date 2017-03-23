<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
		import="java.util.*,com.jsd.web.login.vo.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserVO userVO=(UserVO)session.getAttribute("USER");
int userId=userVO.getUserId();
String privilegeValue=userVO.getPrivilegeValue();
String userName = userVO.getUserName();
String userCode=userVO.getUserCode();
String userOrgname = userVO.getOrgName();
String userPassword=userVO.getUserPassword();
String remark=userVO.getRemark();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>北京金圣达电气科技有限公司</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/web/js/jquery-ui-1.10.3.custom/development-bundle/demos/demos.css"/>
	<script type="text/javascript" src="<%=basePath%>/web/js/agentInfoManage.js"></script>
	<script type="text/javascript" src="<%=basePath%>/web/js/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/js/jquery.form.js"></script>
	<script type="text/javascript" charset="utf-8">
		var sin;
		var sin2;
		var sin3;
		var sin4;
		function saveresult(){
			var agenterror = document.getElementById('agentdata').innerText;
			var usererror = document.getElementById('userdata').innerText;
			if(agenterror != '' || usererror != ''){
				return false;
			}
			//验证非空输入
			if(!validateEmpty()) return false;
			$("#uname").blur();
			if(sin4==0){
				$.ajax({
					url:"ModifyAgentAction.action",
					type:"POST",
					data:{
						"privileges":$("#pv").val(),
						"agent_name":$("#agent_name").val(),
						"user_code":$("#user_code").val(),
						"userName":$("#uname").val(),
						"user_id":$("#uid").val(),
						"user_password":$("#user_password").val(),
						"first_agent_name":$("#aname").val(),
						"first_user_code":$("#uco").val(),
						"remark":$("#remark").val()
					},
					success:function(data){
						alert(data);
						if(data=="修改成功！"){
							window.parent.topFrame.document.location.reload();
						}
					},
					error:function(){
						alert("请重新输入！");
					}
				});
			}else{
				alert("请重新输入！");
			}
		}
	    function rstSubmit(){
	    	$("#fpw").blur();$("#upw").blur();$("#spw").blur();
		    if(sin==0&&sin2==0&&sin3==0){
			    $.ajax({
				   		url:"modifyPwdAction.action",
				   		type:"POST",
				   		data:"newtuserpwd="+$("#upw").val(),
				   		success:function(data){
				   			if(data==0){
					   			alert("密码修改成功！");
					   			$("#user_password").val($("#upw").val());
				   			}else{
					   			alert("密码修改失败！");
				   			}
				   		},
				   		error:function(){
					   		alert("系统错误！");
					   	}
				});
			}
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
			  	$("#user_name").val("");
			  	$("input[name='checkbox_template']:checkbox").attr("checked",false);
			  	$("#remark").val("");
			  	$("#uname").val("");
		  	}else{
			  	$("#fpw").val("");
			  	$("#upw").val("");
			  	$("#spw").val("");
		  	}
	  	}
	  	var randomnum = 0;
	  	function checkRepeatAgent(){//检查同一组织结构代理商是否存在相同的代理商
	  		randomnum = randomnum+1;
	  		var aname = $("#aname").val();
	  		var fname = $("#agent_name").val();
	  		
			if(fname==""){
				$("#agentdata").html('公司名称不能为空！');
			}else{
				if(!validataImport2(fname)){
		  			$("#agentdata").html('请正确填写！');
					return false;
				}else{
		  			$("#agentdata").html('');
			  		if(fname != aname){//与页面初始化的原始值一样，则不校验
				  		$.ajax({
							url:"getAgentByAgentName.action",
							type:"POST",
							data:{
								"agent_name":fname,
								"flushrandom":randomnum
							},
							success:function(data){
								$("#agentdata").html(data);
								if(data!=""){
									$("#agent_name").focus();
								}else{
									$("#aname").val(fname);
								}
							}
						});
			  		}
				}
			}
	  	}
	  	function checkRepeatUser(){//检查是否存在相同的用户
	  		randomnum = randomnum+1;
	  		var uco = $("#uco").val();
	  		var fuco = $("#user_code").val();
	  		if(fuco==""){
	  			$("#userdata").html('登录名不能为空！');
	  		}else{
	  			$("#userdata").html('');
		  		if(fuco != uco){//与页面初始化的原始值一样，则不校验
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
							}else{
								$("#uco").val(usercode);
							}
						}
					});
		  		}
	  		}
	  		if(!validataImport2(uco)){
	  			$("#userdata").html('');
				return false;
			}
	  	}
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
		</script>
</head>
<body>
   <div class="demo-description">
    	<form name="form2" method="post">
    		<table>
   				<tr><td colspan="3" style="font-size:22px;padding-left:85px;padding-bottom:20px;">资料设置</td></tr>
    			<tr>
    				<td>公司名称：</td>
    				<td>
    					<input name="agent_name" id="agent_name" value="<%=userOrgname %>" onblur="checkRepeatAgent();" size="30">
    				</td>
    				<td><font color="red" size="2">*必填</font>
    						<label id="agentdata" style="font-size: 13px;"></label></td>
    			</tr>
    			<tr>
    				<td>登录名：</td>
    				<td>
    					<input name="user_code" id="user_code" value="<%=userCode %>" onblur="checkRepeatUser();"/>
    				</td>
    				<td><font color="red" size="2">*必填</font>
    						<label id="userdata" style="font-size: 13px;"></label></td>
    			</tr>
    			<tr>
    				<td>显示名称：</td>
    				<td>	<input name="uname" id="uname" value="<%=userName %>"/>
    				</td>
    				<td><font color="red" size="2">*必填</font>
    						<label id="un" style="font-size: 13px;"></label></td>
    			</tr>
    			<tr>
    				<td></td>
    				<td style="padding-bottom:20px;">
	    				<input type="button" value="保存" onclick="saveresult();">
	    				<input type="button" value="清空" onclick="clearIt('mod');">
	    				<span id="spa5"></span>
    				</td>
    			</tr>
    			<tr>
    				<td>输入旧密码：</td>
    				<td><input type="password" name="fpw" id="fpw" ></td>
    				<td><font color="red" size="2">*输入数字或字母或数字+字母</font>
    						<label id="fp" style="font-size: 13px;"></label></td>
    			</tr>
    			<tr>
    				<td>输入密码：</td>
    				<td><input type="password" id="upw" name="upw"></td>
    				<td><font color="red" size="2">*输入数字或字母或数字+字母</font></td>
    			</tr>
    			<tr>
    				<td>确认密码：</td>
    				<td><input type="password" name="spw" id="spw" ></td>
    				<td><font color="red" size="2">*重新输入密码</font>
    						<label id="sp" style="font-size:13px;" ></label></td>
    			</tr>
    			<tr>
    				<td></td>
    				<td>
	    				<input type="button" value="保存" onclick="rstSubmit();">
	    				<input type="button" value="清空" onclick="clearIt('psw');">
    				</td>
    			</tr>
    		</table>
    		<input name="aname" id="aname" type="hidden" value="<%=userOrgname %>">
    		<input name="uco" id="uco" type="hidden" value="<%=userCode %>">
    		<input name="user_password" id="user_password" type="hidden" value="<%=userPassword %>">
    		<input name="uid" id="uid" type="hidden" value="<%=userId %>">
    		<input name="pv" id="pv" type="hidden" value="<%=privilegeValue %>">
    		<input name="remark" id="remark" type="hidden" value="<%=remark %>">
    	</form>
   </div>
</body>
</html>
