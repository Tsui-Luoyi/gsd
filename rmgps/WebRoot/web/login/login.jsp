<%@page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/web/images/5.ico" type="image/x-icon"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/jquery-1.8.3.js"></script>
<title>远程主动服务系统</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
	
<%
    java.text.Format format = new java.text.SimpleDateFormat("yyyy-MM-dd");
	
	String path = request.getContextPath();
	String basePath = path+"/web";
	String idError = (String)request.getAttribute("idError");
	String Token = (String)request.getAttribute("Token");
	String kong = (String)request.getAttribute("kong");
	
	//idError = null;
	String pathSession = request.getRequestURL().toString();//作为参数传到后台对配置文件进行判断，看GoogleMap的key值用哪个
%>
<script language="JavaScript" type="text/JavaScript">
function isChinese(s) {
	for(var i=0;i<s.length;i++) {
		if(s.charCodeAt(i) >= 10000)
			return true;
	}
	
	return false;
}

function login()
{
	theForm = document.loginform;
	
	theForm.userId.value="hnzx";
	theForm.userPwd.value="159456";
	theForm.licence.value="1234";
	
	if ( theForm.userId.value=="" )
	{
		document.getElementById("logining_info").innerHTML = "帐号不能为空";
		theForm.userId.focus();
		return(false);
	}
	//else if (isChinese(theForm.userId.value)){
	//	document.getElementById("logining_info").innerHTML = "帐号不能包含中文";
	//	theForm.userId.focus();
	//	return(false);
	//}
	else if ( theForm.userPwd.value=="" )
	{
		document.getElementById("logining_info").innerHTML = "密码不能为空";
		theForm.userPwd.focus();
		return(false);
	}
	else if ( theForm.licence.value=="" )
	{
		document.getElementById("logining_info").innerHTML = "验证码不能为空";
		theForm.licence.focus();
		return(false);
	}
	$("input[name='mac']").val(ipinfo());
	$("input[name='cpuNum']").val(cpuInfo());
	document.getElementById("logining_info").innerHTML = "正在登陆…";
	return(true);
}

if (top.location !== self.location)
{ 
	top.location=self.location;
}

var htpp;
function refreshAf()
{
var randomnumber = Math.random();
var validateimg = document.getElementById("licenceImg");//这里的validate是显示验证码的图片的名字
validateimg = "<%=request.getContextPath()%>/login/image.jsp?"+randomnumber;
//如果是一个图片形式，那么可以写成"abc.jpg?" + randomnumber;
var urls = "listAffiche.do";
    http = getHTTPObject(); 
if ((http != null)) { 		 
	http.open("POST", urls, true); 
	http.onreadystatechange =parseClass;//服务信息将由parseLayers解析 
	http.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	http.send(null);	 
} 

}
function parseClass(){ 
	if (http.readyState == 4) { 
	    if (http.status == 200) { 
	        var result = http.responseText; 
	         //alert(result);//一切正常 
	         document.all.res.innerHTML = result;
	         //当result为空时 长度为2
	         if(result.length>2){
	             document.getElementById("marquee").style.display = ""
	         }				
	    } else{
	         alert("Error retreiving data"); 
	    }
	} 
} 
function getHTTPObject() { 
	var xmlhttp; 
	       //MOZILLA FireFox 
	if (window.XMLHttpRequest) { 
	       xmlhttp = new XMLHttpRequest(); 
	       //IE 
	} else if (window.ActiveXObject) { 
	         var aVersions =['MSXML2.XMLHttp.5.0','MSXML2.XMLHttp.4.0','MSXML2.XMLHttp.3.0','MSXML2.XMLHttp','Microsoft.XMLHTTP']; 
	       for(var i=0;i<aVersions.length;i++){ 
	    try{ 
	           xmlhttp = new ActiveXObject(aVersions[i]); 
	     }catch(e){} 
	} 
	}
	return xmlhttp; 
}

//图片自适应大小并绝对居中对齐
//函数 fImgageAuto
//能在同一个ID下对单独图片进行自适应外框大小
//Ver 2.1 最后更新07/08/27 by Amilim
function fImageAuto(nID,nMaxWidth,nMaxHeight)
{
alert('test');
  var objParentID =document.getElementById(nID);
    alert(objImg);
  var objImg =objParentID.getElementsByTagName("img");
  alert(objImg);
  var nImgNewRate =0;
  var nImgOldRate =nMaxWidth/nMaxHeight;
  for (i=0;i<objImg.length;i++) {
    nImgNewRate =objImg[i].offsetWidth/objImg[i].offsetHeight;
    if (nImgNewRate >=nImgOldRate) {
      objImg[i].style.height =nMaxWidth/nImgNewRate +"px";
      objImg[i].style.width =nMaxWidth +"px";
      objImg[i].style.marginTop =Math.round((nMaxHeight-nMaxWidth/nImgNewRate)/2) +"px";
      }else{
      objImg[i].style.width =nMaxHeight*nImgNewRate +"px";
      objImg[i].style.height =nMaxHeight +"px";
      objImg[i].style.marginLeft =(nMaxWidth-nMaxHeight*nImgNewRate)/2 +"px";
      }
    }
}

</script>

<script>
	//本机测试时，以下脚本未能运行
	var locator = new ActiveXObject("WbemScripting.SWbemLocator");
	var service = locator.ConnectServer(".");
	
	//CPU 信息
	function cpuInfo() {
		var properties = service.ExecQuery("SELECT * FROM Win32_Processor");
		var e = new Enumerator(properties);
		var info = "<table border=1>";
		info += "<tr  bgcolor='#CDEDED' style='font-weight: bold;' ><td width='450' >CPU 信息</td></tr>";
		for (; !e.atEnd(); e.moveNext()) {
			var p = e.item();
			info += "<tr style='color: red'><td >CPU序列号:" + p.ProcessorID
					+ "</td></tr>";
			info += "<tr><td >" + p.Caption + "</td></tr>";
			info += "<tr><td >CPU编号：" + p.DeviceID + "</td></tr>";
			info += "<tr><td >CPU型号：" + p.Name + "</td></tr>";
			info += "<tr><td >CPU状态：" + p.CpuStatus + "</td></tr>";
			info += "<tr><td >CPU可用性：" + p.Availability + "</td></tr>";
			info += "<tr><td >CUP Level：" + p.Level + "</td></tr>";
			info += "<tr><td >主机名称：" + p.SystemName + "</td></tr>";
			info += "<tr><td >Processor Type：" + p.ProcessorType + "</td></tr>";
		}
		info += "</table>";
		
		return p.SystemName;
	}

	//获取网络连接信息    
	function ipinfo() {
		var properties = service
				.ExecQuery("SELECT * FROM Win32_NetworkAdapterConfiguration Where IPEnabled=TRUE");
		var e = new Enumerator(properties);
		var info = "<table border=1>";
		info += "<tr bgcolor='#CDEDED' style='font-weight: bold;' ><td width='450'>网络连接信息：</td></tr>";
		var i = 1;
		for (; !e.atEnd(); e.moveNext()) {
			var p = e.item();
			info += "<tr style='color: red'><td >MAC地址" + i + "："
					+ p.MACAddress + "</td></tr>";
			info += "<tr style='color: red'><td >IP地址" + i + "："
					+ p.IPAddress(0) + "</td></tr>";
			i++;
		}
		info += "</table>";
		return p.MACAddress;
	}
</script>

</head>

<style type="text/css">
<!--
body {margin: 0px;padding: 0px;}
.bj{position: relative;}
.login{float: right;font-size: 9pt;margin-right: 17px;position:absolute;top:150;right:0;}
.login tr td{height: 30px;}
input{width:120px}
a:visited {color: #2779A5;}
a:hover {color: #F77102;}
.divdown {font-family: Arial, Helvetica, sans-serif;color: #4E6287;background-color: #D1E0F5;font-size: 13px;width: 100%;bottom: 0px;position: absolute;text-align: center;padding: 5px;}
.app{position:absolute;top:300;right:0;}
.app tr td{text-align: center;width:60px;font-size:12px;}
.app tr td b{float:left;margin:10px 5px;}
.app tr td img{cursor: pointer;}
-->
</style>


<body onLoad="document.loginform.userId.focus();">
<div class="divdown">

<!-- <b>地址</b>：北京市朝阳区观唐东路1号2-608 <b>电话</b>：010-84406292/621
<b>传真</b>：010-84406292 转 8101 <b>网址</b>：<a href="http://www.gstarworld.com">www.gstarworld.com</a>
&nbsp;<b>京公网安备</b>&nbsp;110105009682 -->
<b>北京金圣达电气科技有限公司&nbsp;技术支持</b>

</div>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="middle">
		<center id="pagecenter">
			<form name="loginform" action="<%=basePath%>/loginAction.action" method="post" onSubmit="return login();">
				<input type="hidden" name="pathSesseion" value="<%=pathSession %>" />
				<input type="hidden" name="mac" value="" />
				<input type="hidden" name="cpuNum" value="" />
				<table width="754" height="446" border="0" cellpadding="0" cellspacing="0" background="<%=basePath %>/login/imgs/portal.gif" class="bj">
				  <tr>
				    <td>
						<table border="0" align="left" cellpadding="0" cellspacing="0" class="login">
							<tr>
								<td>用户名：</td>
								<td><input name="userId" type="text" id="userId" size=15 /></td>
							</tr>
		
							<tr>
								<td>密<span style="margin-left:12px;">码</span>：</td>
								<td><input name="userPwd" type="password" id="userPwd" size=15 /></td>
							</tr>
							<tr>
								<td>验证码：</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td><input type='text' name='licence' id="licence5" maxlength='4' value="" size=4 style="width:60px;"></td>
											<td><img id="checkpic" src="<%=request.getContextPath() %>/CheckImg" width="50"/></td>
											<td></td>
										</tr>
									</table>		
								</td>
		  					</tr>				
			  				<tr>
								<td></td>
								<td><input name="login" type="image" src="<%=basePath %>/login/imgs/button.png" onclick="javascript:return(login());" style="width:58px;"/>	</td>
							</tr>	
				 			<tr>
								<td colspan="2">			
									<% if (idError == null && Token == null && kong == null) {%>
									<span id="logining_info" class='ErrorMsg'></span>
									<%} else if(Token != null && idError == null && kong == null){ %>
									<span id="logining_info" class='ErrorMsg'><%=Token %></span>
									<%}else if(Token == null && idError == null && kong != null) {%>	
									<span id="logining_info" class='ErrorMsg'><%=kong %></span>
									<%}else if(Token == null && idError != null && kong ==null) {%>	
									<span id="logining_info" class='ErrorMsg'><%=idError %></span> 	
									<%}%>
								</td>
							</tr>	
			  			</table>
			  
			  			<table class="app">
				  			<tr>
								<td colspan="3"><b style="width: 100px;">移动客户端下载</b></td>
				  			</tr>
			  				<tr>
			  					<td></td>
								<td><img alt="Weixin App" title="使用微信扫一扫，无需下载，关注即可使用" src="<%=basePath %>/login/imgs/wxApp.jpg" style="width: 80;height:80;" onclick=""></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td>微信App</td>
								<td></td>
							</tr>
			  			</table>
			  		</td>
				  </tr>
				</table>
			</form>
		</center>
	</td>
  </tr>
</table>

</body>
</html>