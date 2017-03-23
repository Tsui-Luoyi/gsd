<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
 %>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>无标题文档</title>
		<link rel="stylesheet" type="text/css" href="web/css/vehicleRecord.css"/>
	</head>
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/web/css/formZj.css">
	<script type="text/javascript" src="web/js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="web/js/slide.js"></script>
	<script src="web/js/datepicker/WdatePicker.js" charset="GB2312" defer="defer"></script>
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


	<body>
<script type="text/javascript">

	function onSubmit(){
	
	$.ajax({
				cache : true,
				type : "POST",
				url : '${pageContext.request.contextPath}/modifyEngineNoAction007.action',
				data : $('#zhengjiform').serialize(),// 你的formid
				
				beforeSend: function(){
				
				// 进行表单数据校验
					var vname = $("#vname").val();
					
					if(vname == ""){
						$.messager.alert("信息","整机别名不能为空","warning");
						return false;
					}
				},
				success : function(msg){ 
				
				if(msg.msg=="SUCCESS"){
				$.messager.alert("信息","修改成功","warning");
				}
				if(msg.msg=="FAIL"){
				$.messager.alert("信息","修改失败请重新输入","warning");
				}
				}
				
			});
			}
			
		
			function onover(){
		
			
			$.ajax({
				
				type : "POST",
				url : '${pageContext.request.contextPath}/VehicleRecordActionD007.action',
				data : $('#zhengjiform').serialize(),// 你的formid
				
				beforeSend: function(){
				
				
				},
				success : function(data){ 
			
		
			
				$("#zhongduaninfo").attr("onmouseover","属性值");
				$("#dno").val(data.dno);
				$("#manufacturer").val(data.manufacturer);
				$("#simnumber").val(data.simnumber);
				}
				
			});
			}
			function onOther(){
			
			$.ajax({
				
				type : "POST",
				url : '${pageContext.request.contextPath}/VehicleRecordActionO007.action',
				data : $('#zhengjiform').serialize(),// 你的formid
				
				beforeSend: function(){
				
				
				},
				success : function(data){ 
				
		
			
				$("#otherinfo").attr("onmouseover","属性值");
				$("#mName").val(data.mName);
				
				}
				
			});
			}
</script>

<div class="imenu_bg"> 

	<div class="imenu" topnav="nav5" attr="gyyz"><a href="javascript:void(0)">整机信息</a>
		<div class="submnu" id="zhengji" ><center>
<br />
 <h2><img src="web/picture/header.png" alt="Account information" /></h2>
 <form id="zhengjiform" name="zhengjiform" method="post"  action="login" namespace="/" theme="simple">
 <input type="text" id="vno" name="vno" value="${requestScope.vno }" style="display:none"></input>
    <ul>
        <li class="first">
            <h3>整机类型</h3>
            <p>
                <input type="text" id="zhengjileixin" name="zhengjileixin" value="MINI007"/>
                </p>
        </li>
        <li>
            <h3>整机名称</h3>
            <p>
                <input type="text" id="vname" name="vname" value="${requestScope.vname }"></input></p>
        </li>
         
         <li>

            <h3>发动机号</h3>
            <p>
                <input type="text" id="engineNo" name="engineNo" value="${requestScope.engineNo }" ></input></p>
        </li>
        <li class="last">
            <a id="signup1" href="javascript:void(0)" onclick="onSubmit()" ><img src="web/picture/button.png" alt="Fake signup button" style="vertical-align:middle;" /></a><img src="web/picture/clickhere.png" alt="" style="vertical-align:middle;" />

        </li>
    </ul>
    </form>	
    </center></div>
	</div>

	<div class="imenu"  topnav="navzx" attr="zx" id="zhongduaninfo" onmouseover="onover()">
		<a href="javascript:void(0)">终端信息</a>
		<div class="submnu"><center>
<br />
 <h2><img src="web/picture/header1.png" alt="Account information" /></h2> 
 <form id="zhongduanform" name="zhongduanform" method="post"  action="login" namespace="/" theme="simple">
    <ul>
        <li class="first">
            <h3>终端编号</h3>
            <p>
                <input type="text" id="dno" name="dno"></input>
                </p>
        </li>
         <li>

            <h3>终端生产厂商</h3>
            <p>
                <input type="text" id="manufacturer" name="manufacturer"  ></input></p>
        </li>
         <li>

            <h3>手机卡号</h3>
            <p>
                <input type="text" id="simnumber" name="simnumber" ></input></p>
        </li>
    </ul>
    </form>	
    </center></div>
	</div>
    
    <div class="imenu"  topnav="navpf" attr="pf" id="otherinfo" onmouseover="onOther()"><a href="javascript:void(0)">其他信息</a>
		<div class="submnu"><center>
<br />
<h2><img src="web/picture/header2.png" alt="Account information" /></h2> 
 <form id="otherform" name="otherform" method="post"  action="login" namespace="/" theme="simple">
    <ul>
        <li class="first">
            <h3>整机生产商</h3>
            <p>
                <input type="text" id="mName" name="mName" />
                </p>
        </li>
       
    
    </ul>
    </form>	
    </center></div>
	</div>


</div>

</body>
</html>