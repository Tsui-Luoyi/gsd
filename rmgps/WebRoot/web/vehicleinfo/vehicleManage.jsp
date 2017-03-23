<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>vehicleManage</title>
<style type="text/css">
body{font-family:Verdana, Geneva, sans-serif;font-size:9pt;}
.dai_top{height:25px;border-bottom:1px solid #00a0e9;margin-bottom:5px;}
.dai_top b{font-size:14px;}
.se_top{height:30px;}
.dai_left{float:left;}
.dai_zhong{float:left;position:relative;}
.dai_zhong .jian_left{position:absolute;top:130px;left:10px;}
.dai_zhong .jian_right{position:absolute;top:270px;left:10px;}
.dai_zhong .jian_left img,.dai_zhong .jian_right img{border:0;}
.dai_right{float:left;position:relative;left:74px;}
.dai_button{border:0px;background-color:#00a0e9;height:22px;color:#FFF;margin-left:1px;}
.dai_input{width:128px;border:1px solid #CCC;height:18px;}
textarea{height:400px;width:291px;}
select{border:1px solid #CCC;height:22px;margin-right:1px;background-color:#F9F9F9;}
</style>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/web/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/web/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/web/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/web/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/web/css/default.css"> 
<script type="text/javascript"
	src="${pageContext.request.contextPath }/web/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/web/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/web/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/web/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>    
<link href="${pageContext.request.contextPath }/web/css/mainFrame.css" rel="stylesheet" type="text/css">
<!-- 
<script type="text/javascript" src="${pageContext.request.contextPath }/web/js/tools.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/web/js/vehicleManage.js"></script>
 -->
</head>

<script type="text/javascript">
	function chaxun(){
			$.ajax({
				   type: "POST",
				   url: "${pageContext.request.contextPath }/vehicleManageAction_queryAgent.action",
				   data: "agent_id="+$('#agent1').combobox('getValue'),
				   success: function(msg){
				  
					 $("#table_1").html("");
				     $.each(msg.subList,function(i,obj){
				    	 var trString = '<tr class="delFlag_1">'
				    	 	+ '<td style="width:15%"><input type="checkbox" name="checkbox1" value="'+obj.id+'"  onclick="xuanzhong1();"></td>'
				    	 	+ '<td style="width:15%">'+obj.vname+'</td>'
				    	 	+ '<td style="width:15%">'+obj.vno+'</td></tr>';
						 
				    	 $("#table_1").append(trString);
				     });
				     
				   	 $("#table_2").html("");
				     $.each(msg.totalList,function(i,obj){
				    	 var trString2 = '<tr class="delFlag_2">'
				    	 	+ '<td style="width:15%"><input type="checkbox" name="checkbox2" value="'+obj.id+'"  onclick="xuanzhong2();"></td>'
				    	 	+ '<td style="width:15%">'+obj.vname+'</td>'
				    	 	+ '<td style="width:15%">'+obj.vno+'</td></tr>';
				    	 	
				    	 $("#table_2").append(trString2);
				     });
				     
				   }
				});
	}
	  
	function manageSubmit1(){
		$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath }/vehicleManageAction_manageSubmit1.action",
			   data: "vname1="+$("#vname1").val()+"&vno1="+$("#vno1").val(),
			   success: function(msg){
				 $("#table_1").html("");
			     $.each(msg.subList,function(i,obj){
			    	 var trString = '<tr class="delFlag_1">'
			    	 	+ '<td style="width:15%"><input type="checkbox" name="checkbox1" value="'+obj.id+'"  onclick="xuanzhong1();"></td>'
			    	 	+ '<td style="width:15%">'+obj.vname+'</td>'
			    	 	+ '<td style="width:15%">'+obj.vno+'</td></tr>';
					 
			    	 $("#table_1").append(trString);
			     });
			   }
			});
	}
	function manageSubmit2(){
		$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath }/vehicleManageAction_manageSubmit2.action",
			   data: "vname2="+$("#vname2").val()+"&vno2="+$("#vno2").val(),
			   success: function(msg){
			     $("#table_2").html("");
			     $.each(msg.totalList,function(i,obj){
			    	 var trString2 = '<tr class="delFlag_2">'
			    	 	+ '<td style="width:15%"><input type="checkbox" name="checkbox2" value="'+obj.id+'"  onclick="xuanzhong2();"></td>'
			    	 	+ '<td style="width:15%">'+obj.vname+'</td>'
			    	 	+ '<td style="width:15%">'+obj.vno+'</td></tr>';
			    	 	
			    	 $("#table_2").append(trString2);
			     });
			   }
			});
	}
	function manageReset1(){
		$("#vname1").val('');
		$("#vno1").val('');
	}
	function manageReset2(){
		$("#vname2").val('');
		$("#vno2").val('');
	}
	/* 只提交代理商拥有的车辆 id   */
	function toRight(){//获取左边选中的复选框的值移至右边
		var ids1 = findSelections("checkbox1","id");//获取左边选中的复选框的值
		if(ids1 == null){//左边复选框没被选中，则取消提交
			alert("请选择代理商车辆！");
			return false;
		}
		
		$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath }/vehicleManageAction_toRight.action",
			   data: "ids1="+ids1,
			   success: function(msg){
				 $("#table_1").html("");
			     $.each(msg.subList,function(i,obj){
			    	 var trString = '<tr class="delFlag_1">'
			    	 	+ '<td style="width:15%"><input type="checkbox" name="checkbox1" value="'+obj.id+'"  onclick="xuanzhong1();"></td>'
			    	 	+ '<td style="width:15%">'+obj.vname+'</td>'
			    	 	+ '<td style="width:15%">'+obj.vno+'</td></tr>';
					 
			    	 $("#table_1").append(trString);
			     });
			     
			   	 $("#table_2").html("");
			     $.each(msg.totalList,function(i,obj){
			    	 var trString2 = '<tr class="delFlag_2">'
			    	 	+ '<td style="width:15%"><input type="checkbox" name="checkbox2" value="'+obj.id+'"  onclick="xuanzhong2();"></td>'
			    	 	+ '<td style="width:15%">'+obj.vname+'</td>'
			    	 	+ '<td style="width:15%">'+obj.vno+'</td></tr>';
			    	 	
			    	 $("#table_2").append(trString2);
			     });
			     
			   }
			});
	}
	/* 只提交代理商拥有的车辆 id   */
	function toLeft(){//获取右边选中的复选框的值移至左边
		var ids2 = findSelections("checkbox2","id");//获取右边选中的复选框的值
		if(ids2 == null){//右边复选框没被选中，则取消提交
			alert("请选择未代理商车辆！");
			return false;
		}
		
		$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath }/vehicleManageAction_toLeft.action",
			   data: "ids2="+ids2,
			   success: function(msg){
				 $("#table_1").html("");
			     $.each(msg.subList,function(i,obj){
			    	 var trString = '<tr class="delFlag_1">'
			    	 	+ '<td style="width:15%"><input type="checkbox" name="checkbox1" value="'+obj.id+'"  onclick="xuanzhong1();"></td>'
			    	 	+ '<td style="width:15%">'+obj.vname+'</td>'
			    	 	+ '<td style="width:15%">'+obj.vno+'</td></tr>';
					 
			    	 $("#table_1").append(trString);
			     });
			     
			   	 $("#table_2").html("");
			     $.each(msg.totalList,function(i,obj){
			    	 var trString2 = '<tr class="delFlag_2">'
			    	 	+ '<td style="width:15%"><input type="checkbox" name="checkbox2" value="'+obj.id+'"  onclick="xuanzhong2();"></td>'
			    	 	+ '<td style="width:15%">'+obj.vname+'</td>'
			    	 	+ '<td style="width:15%">'+obj.vno+'</td></tr>';
			    	 	
			    	 $("#table_2").append(trString2);
			     });
			     
			   }
			});
	}
	
	function findSelections(checkboxName, idName) {  //从列表中找出选中的id值列表
		var elementCheckbox = document.getElementsByName(checkboxName);  //通过name取出所有的checkbox
		var ids = null;  //定义id值的数组
		for(var i=0;i<elementCheckbox.length;i++){  //循环checkbox组
			if(elementCheckbox[i].checked) {  //如果被选中
				if(ids == null) {
					ids = new Array(0);
				}
				ids.push(elementCheckbox[i].value);  //加入选中的checkbox
			}
		}
		return ids;
	}

	function checkAll(cName) {//复选框全选
		var code_Values = document.all[cName];
		if(code_Values == undefined){
			//alert('无记录可选！');
			return false;
		}
		if (code_Values.length) {
			for ( var i = 0; i < code_Values.length; i++) {
				code_Values[i].checked = true;
			}
		} else {
			code_Values.checked = true;
		}
	}

	function uncheckAll(cName) {//复选框反选
		var code_Values = document.all[cName];
		if(code_Values == undefined){
			//alert('无记录可取消！');
			return false;
		}
		if (code_Values.length) {
			for ( var i = 0; i < code_Values.length; i++) {
				code_Values[i].checked = false;
			}
		} else {
			code_Values.checked = false;
		}
	}
</script>  
<body>
<div class="dai_top">
	<b>代理商</b>：
 	<select class="easyui-combobox" id="agent1" name="agent1" id="3"> 
		<option value="-1" selected="selected">---请选择---</option>
		<c:forEach var="a" items="${requestScope.agentList}">
			<option value="${a.agent_id }">${a.agent_name }</option>
		</c:forEach>
	</select>
	<button id="chaxun" href="javascript:void(0);" onclick="chaxun();">查询</button>
</div>

	<div>
		<span id="searchDiv1" style="float:left"></span>
		<span id="searchDiv2" style="float:right"></span>
  	</div>
  	<div>
    
    <div class="mainFrame" style="width:98%;float:left;margin-left: 10px;">
		&nbsp;&nbsp;&nbsp;
    	<input type="hidden" id="subList">
    	<input type="hidden" id = "totalList">
    	<font size="2">车辆名称</font><input id="vname1" name="vname1" type="text"/>&nbsp;&nbsp;&nbsp;<font size="2">终端号</font><input id="vno1" name="vno1" type="text"/> <input type="submit" value="搜索" onclick="manageSubmit1()"/><input type="button" value="重置" onclick="manageReset1()"/>
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<font size="2">车辆名称</font><input id="vname2" name="vname2" type="text"/>&nbsp;&nbsp;&nbsp;<font size="2">终端号</font><input id="vno2" name="vno2" type="text"/> <input type="submit" value="搜索" onclick="manageSubmit2()"/><input type="button" value="重置" onclick="manageReset2()"/>
			<div id="b" style="overflow-x: auto; overflow-y: auto; height: 390px; width:45%;float:left;border:1px solid #FAF0E6;">
				代理商车辆列表
				<table>
					<tr id="tr_title_1">
			    		<th style="width:10%">
			    			<a href="javascript:void(0);" onclick="checkAll('checkbox1');xuanzhong1();">全选/</a>
			   				<a href="javascript:void(0);" onclick="uncheckAll('checkbox1');xuanzhong1();">反选</a>
			   			</th>
			   			
			    		<th style="width:17%">
			    			<a href="javascript:void(0);" onclick="">车辆名称</a>
			    		</th> 
			    		<th style="width:17%">
			    			<a href="javascript:void(0);" onclick="">终端号</a>
			    		</th>
			    	</tr>
				</table>
				<table id="table_1" style="border: 0px;" id="345">
			    	<c:forEach var="v1" items="${requestScope.TotalList }" varStatus="status">
						<tr class="delFlag_1">
							<td><input type="checkbox" name="checkbox1" value="${v1.vid }" onclick="xuanzhong1();"></td>
							<td>${v1.vname }</td>
							<td>${v1.vno }</td>
						</tr>
					</c:forEach>
			    </table>
			</div>
			
			<div style="overflow-x: auto; overflow-y: auto;float:left;height: 390px;width:8%;margin-top: 90px;">
				<table align="center"  style="border: 0px;">
					<tr>
						<td style="text-align: center;background-color: white;border:0px;"><button id="button1" href="javascript:void(0);" onclick="toRight();">>>></button></td>
					</tr>
					<tr><td style="text-align: center;background-color: white;border:0px;"></td></tr>
					<tr>
						<td style="text-align: center;background-color: white;border:0px;"><button id="button2" href="javascript:void(0);" onclick="toLeft();" ><<<</button></td>
					</tr>
				</table>
			</div>
			
			<div style="overflow-x: auto; overflow-y: auto; height: 390px; width:46%;float:right;border:1px solid #FAF0E6;">
		    	未代理车辆列表<!-- span style="float:right;height: 20px"><input type="button" value="搜索" onclick="soushuo2()"></span-->
		    	<table>
		    		<tr id="tr_title_2">
			    		<th style="width:10%">
			    			<a href="javascript:void(0);" onclick="checkAll('checkbox2');xuanzhong2();">全选/</a>
			   				<a href="javascript:void(0);" onclick="uncheckAll('checkbox2');xuanzhong2();">反选</a>
			   			</th>
			    		<th style="width:17%">车辆名称</th>
			    		<th style="width:17%">终端号</th>
			    	</tr>
		    	</table>
 		    	<table id="table_2" style="border: 0px;" id="345">
			    	<c:forEach var="v2" items="${requestScope.TotalList }" varStatus="status">
						<tr class="delFlag_2">
							<td><input type="checkbox" name="checkbox2" value="${v2.vid }" onclick="xuanzhong2();"></td>
							<td>${v2.vname }</td>
							<td>${v2.vno }</td>
						</tr>
					</c:forEach>
			    </table>
 					
		    </div>
    </div>
 </div>
 
 
</body>
<script type="text/javascript"> 

</script> 
</html>
