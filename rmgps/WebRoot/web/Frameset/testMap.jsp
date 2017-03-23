<%@ page import="java.util.*,com.jsd.web.login.vo.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:v="urn:schemeas-microsoft-com:vml" xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Content-Type" content="text/css; charset=UTF-8" />
	<meta http-equiv="Content-Type" content="text/javascript; charset=UTF-8" />
	<meta http-equiv="x-ua-compatible" content="ie=7" />
	<title>地图示例</title>
	<style type="text/css">
		<!--
			v\: * { Behavior: url(#default#VML); }
		-->
	</style>
	<script type="text/javascript" language="javascript" src="http://f.smartearth.cn:9000/SE_JSAPI?uid=gstarworld"></script>
	<script type="text/javascript" language="javascript">
		function loadMap(){
			map=new SE.Map("mapPanel");
			map.centerAndZoom(new SE.LngLat(113.72243,34.7595),16);
			// 添加标准控件，骨头棒
			map.addControl(new SE.MapControl());
			// 添加 卫图, 矢量 和 融合控件
			var switchControl=new SE.MapTypeControl();
			map.addControl(switchControl);
			map.removeMapType(SE.Traffic_MAP);
			switchControl.setRight(5);
			// 比例尺
			var scale = new SE.ScaleControl();
			scale.setLeft(20);
			scale.setBottom(30);
			map.addControl(scale);
			// 鼠标滚动
			map.handleMouseScroll(true);
			// 键盘事件
			map.handleKeyboard();
			
			// 创建位置Marker标记
			var marker=new SE.Marker(new SE.LngLat(113.72243,34.7595));
			map.addOverLay(marker);
            var infoWin=marker.openInfoWinHtml("欢迎使用地图API");  
            infoWin.setTitle("地图API"); 
		}
	</script>
</head>
<body onload="loadMap()">
<!-- 地图区域存放面板容器 -->
<div id="mapPanel" style="overflow:hidden; width:500px; height:500px; z-index:0;"></div>
</body>
</html>