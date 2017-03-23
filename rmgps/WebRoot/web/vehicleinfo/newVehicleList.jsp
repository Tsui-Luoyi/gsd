<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<script type="text/javascript">
	var count = 0;
	var str;
	// 工具栏
	var toolbar = [ {
		id : 'button-delete',
		text : '刷新',
		iconCls : 'icon-reload',
		handler : doReload
	}/*,{
		id : 'warning',	
		text : '轮询',
		iconCls : 'icon-ok',
		handler : beginQuery,
		disabled:true
	},{
		id : 'warning2',	
		text : '停止轮询',
		iconCls : 'icon-no',
		handler : endQuery,
		disabled:true
	}*/];
	//定义冻结列
	var frozenColumns = [ [ {
		field : 'id',
		checkbox : true,
		rowspan : 2,
		hidden:true
	},{
		field : 'vname',
		title : '车辆编号',
		width : 190,
		rowspan : 2,
		align : 'center',
		sortable:true,
		formatter : function(value, rowdata, rowindex) {
	//		alert(value+":"+row.id+":"+row.vno+":"+index);
			var d = "<a href='#' onclick='look("+rowdata.id+")'>"+value+"</a>";
	//		var d = "<a href='javascript:look("+rowdata.id+","+rowdata.vno+");'>"+value+"</a>";
			return d;
		}
	}, {
		field : 'vtype_name',
		title : '型号',
		width : 70,
		rowspan : 2,
		align : 'center'
	},{
		field : 'agent_name',
		title : '代理商名称',
		width : 190,
		rowspan : 2,
		align : 'center',
		sortable:true
	},{
		field : 'time',
		title : '最新数据时间',
		width : 130,
		rowspan : 2,
		align : 'center',
		sortable:true
	},{
		field : 'dno',
		title : '终端编号',
		width : 130,
		rowspan : 2,
		align : 'center',
		resizable:true,
		sortable:true
	},{
		field : 'emergency_state',
		title : '紧急',
	//	width : 140,
		rowspan : 2,
		align : 'center',
		sortable:true,
		formatter:function(value,row,index){
			if(row.emergency_state==1){
	//			alert(row.emergency_state);
				return "<a href='#'>"+"<img src='${pageContext.request.contextPath}/web/picture/2.png'/>"+"</a>";
			}else if(row.emergency_state==0){
				return "-";
			}
		}
	}
	] ];
	
	// 定义标题栏
	var columns = [ [ {
		field : 'addr',
		title : '地理位置',
		width : 400,
		rowspan : 2,
		//align : 'center',
		sortable:true
	}] ];

	function showUnreadNews(){
	$(function(){
		// 初始化 datagrid
		// 创建grid
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			width:1150,
			height:520,
			sortName:'time',
			sortOrder:'desc',
			remoteSort:true,				//定义是否从服务器给数据排序。
			fit : false,
//			fitColumns:true,
			border : false,
			rownumbers : true,
			striped : true,			//是否显示斑马线效果
			singleSelect:true,		//如果为true，则只允许选择一行。
			pageList: [17,50,100],
			pagination : true,		//如果为true，则在数据表格控件底部显示分页工具栏
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/VehicleAction_queryVehicleList2.action",
			idField : 'id', 
			frozenColumns : frozenColumns,
			columns : columns,
			onLoadSuccess:load		//加载成功后调用的函数
		});
		
			$("body").css({visibility:"visible"});
			if(count!=0){
				$("#warning").linkbutton("disable"); 
			}			
	});
	}
	
	$(document).ready(showUnreadNews());
	//重新加载列表(刷新)
	function doReload() {
		$('#grid').datagrid('reload');
	}
	//开始轮询
	function beginQuery(){
		$("#warning").linkbutton("disable"); 
		timeID = setInterval('showUnreadNews()',1000);
		count = 1;
	}
	//停止轮询
	function endQuery(){
		$("#warning").linkbutton("enable"); 
		clearInterval(timeID);
	}
	//加载成功后调用的函数
	function load(data){//data为返回的所有数据
		str=data;		//把str定义成全局变量
//		alert(str);
	}
	//
	function look(target) {
		$.each(str.rows,function(index,obj){//easyui的遍历
			if(obj.id==target){
				vno = obj.vno;
				vname = obj.vname;
				vmid = obj.vtype_id;
//				alert(vno+","+vmid+","+vname);
	//跳转路径
	location.href = '${pageContext.request.contextPath }/VehicleOfProtocal.action?vmid='+vmid+'&vno='+vno+'&vname='+vname;
			}
		});
	}
</script>	
<script type="text/javascript"> 
	//查询
	function doSearch(){  
		
//		alert($('#agent_id_list').combobox('getValue'));
	    $('#grid').datagrid('load',{  
	    	vname_list: $('#vname_list').val(),  
	    	dno_list  : $('#dno_list').val(),  
//	    	agent_id_list: $('#agent_id_list').val(), 
			agent_id_list: $('#agent_id_list').combobox('getValue'),
	    	offcount: $('#offcount').val()
	    });  
	} 	
	//重置功能
	function doClean(){  
	    $('#vname_list').val('');
	  	$('#dno_list').val('');
	  	$("#agent_id_list").combobox("select","");

	  //document.getElementById("agent_id_list").options[0].selected= 'selected';
	   	document.getElementById("offcount").options[0].selected= 'selected';
	} 
/*
	//根据 车辆编号vname_list查询的联想功能
	$(function(){
		var urlStr ="${pageContext.request.contextPath}/VehicleAction_queryVehicleList3.action;
	    $('#vname_list').combobox({  
//	        url:"${pageContext.request.contextPath}/web/admin.json",  
	        valueField:'vname',  
	        textField:'vname',  
	        onChange:function (newValue, oldValue){  
	            if(newValue !=null){  
//	                var urlStr ="${pageContext.request.contextPath}/VehicleAction_queryVehicleList3.action?vname_list="+newValue;  
//					var urlStr ="${pageContext.request.contextPath}/web/admin.json";
					$("#vname_list").combobox("reload",urlStr);
	            }  
	        }
	    });
	});
	
	//根据 终端编号dno_list查询的联想功能
	$(function(){  
	    $('#dno_list').combobox({  
//	        url:"${pageContext.request.contextPath}/web/admin.json",  
	        valueField:'dno',  
	        textField:'dno',  
	        onChange:function (newValue, oldValue){  
	            if(newValue !=null){  
	                var urlStr ="${pageContext.request.contextPath}/VehicleAction_queryVehicleList3.action?dno_list="+newValue;  
//					var urlStr ="${pageContext.request.contextPath}/web/admin.json";
					$("#dno_list").combobox("reload",urlStr);
	            }  
	        }
	    }); 
	});  
*/	
	//excel下载事件
	$(function() {
		$("#dayin").click(function(){	
				$("#form2").submit();
			});
	});
	$(function() {

		window.parent.leftFrame.document.getElementById("zhengji1").style.display="block"; 
		window.parent.leftFrame.document.getElementById("zhengji2").style.display="none"; 		
		window.parent.leftFrame.document.getElementById("jinji1").style.display="block"; 
		window.parent.leftFrame.document.getElementById("jinji2").style.display="none";
	});
</script> 	
</head>
<body>
<div id="tb" style="padding:3px">  
    <span>车辆编号:</span>  
<!--    <input id="vname_list" style="line-height:20px;border:1px solid #ccc">   -->
   <input id="vname_list" name="vname_list" style="width:200px;" 
     	data-options="required:false"/>
     
    
    <span>终端编号:</span>  
<!--     <input id="dno_list" style="line-height:20px;border:1px solid #ccc"> -->
     <input id="dno_list" name="dno_list" style="width:200px;" 
     	data-options="required:false"/>
    

    <span>代理商名称:</span> 
    <!-- <select class="easyui-combobox" id="agent_id_list" name="agentn" id="3"> --> 
	<select class="easyui-combobox" id="agent_id_list" name="agentn" id="3">
		<option value="-1" selected="selected">全部</option>
		<c:forEach var="a" items="${requestScope.agentList}">
			<option value="${a.agent_id }">${a.agent_name }</option>
		</c:forEach>
	</select>
	
	<span>统计:</span>  
	<select id="offcount" name="offcount" >
		<option value="0" selected="selected">正常</option>
		<option value="1">离线1天</option>
		<option value="2">离线2天</option>
		<option value="3">离线3天以上</option>			
		<option value="-1">重新上线</option>
	<select>
	
    <a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">查询</a>  
    <a href="#" class="easyui-linkbutton" plain="true" onclick="doClean()">重置</a> 
    <a href="#" id="dayin" class="easyui-linkbutton" plain="true">excel下载</a> 
</div> 	
	
    <div>
    	<table id="grid"  style="width:10px;height:10px"></table>
	</div>
</body>
<form id="form2" name="form2" target="testIframeName" method="post" action="VehiclePrintAction_printVehicleList2.action" style="display:none;">
</form>
</html>