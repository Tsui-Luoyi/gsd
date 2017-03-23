<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
		import="java.util.*,com.jsd.web.login.vo.UserVO"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String userId=String.valueOf(request.getParameter("userId"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=8"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <title>北京金圣达电气科技有限公司</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/js/tapmodo-Jcrop-1902fbc/css/jquery.Jcrop.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/js/uploadify-v3.1/uploadify.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/development-bundle/themes/base/jquery-ui.css" id="theme">
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/jquery-ui-1.10.3.custom/js/jquery.form.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/tapmodo-Jcrop-1902fbc/js/jquery.Jcrop.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/tapmodo-Jcrop-1902fbc/js/jquery.color.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/tapmodo-Jcrop-1902fbc/js/autoZoomLoadImage.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/uploadify-v3.1/jquery.uploadify-3.1.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#tab2").hide();
	});
	$(document).ready(function() {
		$('#cup').ajaxForm({
			dataType:"json",
			success:function(responseText){
				if(responseText!="系统繁忙，请稍后重试！"){
					$(window.parent.topFrame.document).find('#imgTd').empty();
					$(window.parent.topFrame.document).find('#imgTd').append("<img id='logo' width='56px' height='56px' style='margin:0;padding:0;' src='${pageContext.request.contextPath}"+responseText[0]+"' />");
					//$(window.parent.topFrame.document).find('#logo').attr("src","${pageContext.request.contextPath}"+responseText[0]);
					alert(responseText[1]);
				}else{
					alert(responseText);
				}
			}
		});
	});
	$(document).ready(function() {
		$('#uploadForm').ajaxForm({
			success:function(data){
				$("#output").html(data);
				$("#dvImg").empty();
				$("#preview").attr("src","${pageContext.request.contextPath}/upload/"+$("#uid").val()+"/"+data);
				$("#dvImg").append("<img id='img2' width='300px' height='300px' src='${pageContext.request.contextPath}/upload/"+$("#uid").val()+"/"+data+"' />");
				$("#img2").autoZoomLoadImage(true, 300, 300);
				$.imgJcrop();
				$("#tab2").show();
			}
		});
	});

	$(document).ready(function() {
		$("#portrait").uploadify( {
			'swf' : '${pageContext.request.contextPath}/web/js/uploadify-v3.1/uploadify.swf',
			'uploader' : 'uploadAction_upload.action',
			//显示待上传文件列表的div区域
			'queueID' : 'fileQueue',
			'auto' : false,
			'multi' : false,
			'buttonText' : '上传头像',
			'fileObjName' : 'image',
			'method' : 'post',
			//上传文件大小限制
			'fileSizeLimit' : '5MB',
			//调用后台action时传递的参数
			'formData' : {
				'uid' : $("#uid").val()
			},
			//有speed和percentage两种，一个显示速度，一个显示完成百分比 
			'displayData' : 'percentage',
			//允许的格式
			'fileTypeExts' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
			//如果配置了以下的'fileExt'属性，那么这个属性是必须的 
			'fileTypeDesc' : '支持格式:*.jpg;*.gif;*.jpeg;*.png;*.bmp',
			'onSelectError' : function(file, errorCode){ //file选择失败后触发
				alert("最多上传2个！");
			},
			'onFallback' : function(){ //flash报错触发
				alert("请您先安装flash控件");  
			},
			'onUploadSuccess' : function(file, data, response){ //上传成功后触发
				if("sizeError" == data){
					alert("文件大小不能超过5M");  
				} else if("typeError" == data){
					alert("不支持的文件类型");  
				}else{
					alert("上传成功！");
					$("#output").html(data);
					$("#dvImg").empty();
					$("#preview").attr("src","${pageContext.request.contextPath}/upload/"+$("#uid").val()+"/"+data);
					$("#dvImg").append("<img id='img2' width='300px' height='300px' src='${pageContext.request.contextPath}/upload/"+$("#uid").val()+"/"+data+"' />");
					$("#img2").autoZoomLoadImage(true, 300, 300);
					$.imgJcrop();
					$("#tab2").show();
				}
			}  
		});
	});
    </script>
	
</head>
<body>
	<p><font style="font-size:15px;">设置logo图片</font>
			<input type="hidden" id="uid" name="uid" value="<%=userId %>"/></p>
	<div id="output"></div>
	<div id="icondiv"></div>
	<table id="tab2">
		<tr>
			<td>
				原图：
				<div id="dvImg" 	style="width: 300px; height: 300px; overflow: hidden;">
					<img id="img2" width="300px" height="300px"/>
				</div>
				<input type="hidden" name="width0" id="width0" />
				<input type="hidden" name="width2" id="width2" />
			</td>
			<td>
				<form id="cup" action="uploadAction_cutPic.action" method="post">
					预览：
					<div 	style="width: 56px; height: 56px; overflow: hidden; border: 1px solid gray;">
						<img id="preview" width="56px" height="56px" />
					</div>
					点击
					<input type="hidden" name="image2.x" id="x" />
					<input type="hidden" name="image2.y" id="y" />
					<input type="hidden" name="image2.width" id="width" />
					<input type="hidden" name="image2.height" id="height" />
					<input type="submit" id="upl" value="确定" />
				</form>
			</td>
		</tr>
		<tr>
			<td colspan="2">若无法剪裁，请重新上传！</td>
		</tr>
	</table>
	<br/>
	<div id="fileQueue"></div>
	<input type="file" name="xxx" id="portrait"/>(最大5M)
	<p>
	<a href="javascript:jQuery('#portrait').uploadify('upload','*')">开始上传</a>
	<a href="javascript:jQuery('#portrait').uploadify('cancel')">取消所有上传</a>
	</p>
	<a href="javascript:history.back()"><img border="0" src="${pageContext.request.contextPath}/web/images/back.jpg"/></a>
	
</body>
</html>
