<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="fenye_div1">
	<div id="spanFirst" class="fy fygg"></div>
	<div id="spanPre" class="fy1 fygg"></div>
	<div id="spanPageNum" style="width:40;"></div>
	<div id="spanTotalPage" style="width:40;"></div>
	<div id="spanNext" class="fy1 fygg"></div><div id="spanLast" class="fy fygg"></div>
</div>
<div class="fenye_div2">
	<div id="forward_d" class="fy fygg">到第</div>
	<div  id="forward_s" class="fy fygg"><input id="forward_num" class="forward_num"/></div>
	<div  id="forward_ye" class="fy fygg">页</div>
	<div  id="forward_ok" class="fy fygg" onclick="forward_to_num();">确定</div>
</div>
<script type="text/javascript" src="web/js/commonjs/pagejs.js"></script>
<link rel="stylesheet" type="text/css" href="web/css/pagejs.css">