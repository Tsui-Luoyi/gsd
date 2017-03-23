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
		field : 'vno',
		title : '车辆编号',
		width : 180,
		rowspan : 2,
		align : 'center',
		sortable:true,
	}, {
		field : 'user_name',
		title : '用户',
		width : 180,
		rowspan : 2,
		align : 'center'
	},{
		field : 'cmd_name',
		title : '命令名称',
		width : 180,
		rowspan : 2,
		align : 'center',
		sortable:true
	},{
		field : 'sendtime',
		title : '发送时间',
		width : 180,
		rowspan : 2,
		align : 'center',
		sortable:true
	},{
		field : 'receivetime',
		title : '接收时间',
		width : 180,
		rowspan : 2,
		align : 'center',
		resizable:true,
		sortable:true
	}
	] ];
	
	// 定义标题栏
	var columns = [ [ {
		field : 'receive_result',
		title : '操作结果',
		width : 180,
		rowspan : 2,
		align : 'center',
		sortable:true,
	}] ];

	function showUnreadNews(){
	$(function(){
		// 初始化 datagrid
		// 创建grid
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			width:1150,
			height:520,
			sortName:'sendtime',
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
			url : "${pageContext.request.contextPath}/vehicleMnAction_queryEmergentList.action",
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
	    $('#grid').datagrid('load',{  
	    	vno_list: $('#vno_list').val(),
	    	user_id_list: $('#user_id_list').combobox('getValue')
	    });  
	} 	
	//重置功能
	function doClean(){  
	    $('#vno_list').val('');
	    $('#user_id_list').combobox('clear');
	} 
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
   <input id="vno_list" style="line-height:20px;border:1px solid #ccc">
<!--    <select id="vno_list" name="vno_list" class="easyui-combobox" style="width:200px;" 
     	data-options="required:false">
     </select>
    -->   
    <span>代理商名称:</span>  
	<select class="easyui-combobox" id="user_id_list" name="agentn" id="3">
		<option value="-1" selected="selected">全部</option>
		<c:forEach var="a" items="${requestScope.agentList}">
			<option value="${a.user_id }">${a.agent_name }</option>
		</c:forEach>
	</select>
	
    <a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">查询</a>  
    <a href="#" class="easyui-linkbutton" plain="true" onclick="doClean()">重置</a> 
</div> 	
    <div>
    	<table id="grid"  style="width:10px;height:10px"></table>
	</div>
</body>
</html>