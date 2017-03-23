<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>终端续费</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<link href="<%=basePath%>web/css/mainFrame.css" rel="stylesheet" type="text/css">
	<style>
		.mainFrame table th{cursor: pointer;}
		.ts2{width:120px;float: left;padding-top: 5px;}
	</style>
	<script src="<%=basePath%>web/js/datepicker/WdatePicker.js"  charset="GB2312"></script>
	<script type="text/javascript" src="<%=basePath%>web/js/tools.js"></script>
  </head>
  
  <body>
    <form name="f1" action="terminalRenew.action" method="post">
    	<!-- 查询条件 -->
    	<div class="main_top2" >
    		<div style="float:left;width:800px;">
    			<li style="margin-top: 10px;">整机名称:</li>
	    		<li><input id="vname" name="vname" value="${requestScope.vname }" /></li>
	    		<li style="margin-top: 10px;">终端编号:</li>
	    		<li><input id="dno" name="dno" value="${requestScope.dno }" /></li>
	    		<li style="margin-top: 10px;">SIM号码:</li>
	    		<li><input style="width:100px;" id="simnum" name="simnum" value="${requestScope.simnum }" /></li>
    		</div>
    		<div style="float:left;width:800px;">
    			<li style="margin-top: 10px;">即将到期的终端:</li>
	    		<li><input style="width:100px;" id="daytime" name="daytime" onkeydown="backspaceEnable();""/>天</li>
	    		<li style="margin-top: 10px;">续费周期:</li>
	    		<li><input style="width:50px;" id="renewperiod" name="renewperiod" value="${requestScope.renewperiod }" />月</li>
	    		<li class="ts">
					<a href="javascript:void(0);" onclick="document.f1.submit();">查询</a> 
				</li>
				<li class="ts">
					<a href="javascript:void(0);" onclick="clearIt();">清空</a> 
				</li>
    		</div>
    		<hr width="1010px;" align="center">
    		<div style="float:left;width:800px;">
    			<li style="margin-top: 10px;">批量填充被选中行的最后续费时间:</li>
	    		<li><input id="fillRenewDate" style="width:100px;" readonly="readonly" onkeydown="backspaceEnable();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></li>
	    		<li class="ts">
					<a style="width:90px;" href="javascript:void(0);" onclick="batchFillRenewDate();">开始批量填充</a> 
				</li>
	    	</div>
	    	
	    	<div style="float:left;width:800px;">
	    		<li style="margin-top: 10px;">批量填充被选中行的续费周期:</li>
	    		<li><input id="fillRenewPeriod" style="width:50px;" />月</li>
	    		<li class="ts">
					<a style="width:90px;" href="javascript:void(0);" onclick="batchFillRenewPeriod();">开始批量填充</a> 
				</li>
				<li class="ts2">
					<font color="#278137">&nbsp;&nbsp;&nbsp;*请输入数字</font>
				</li>
	    	</div>
    	</div>
    	
    	<div style="width:1040px;margin-left: 10px;margin-top: 30px;">
    		<label style="float:left;">终端续费信息列表</label>
    		<!-- 提交修改到服务器的保存按钮，一次性提交，不异步分开一个个提交 -->
    		<input type="button" style="float: right;" value="提交修改" onclick="saveEditedSelections();" />
    	</div>
    	
    	<!-- 数据展示 排序 批量-修改SIM、续费时间、周期-->
    	<div class="mainFrame" style="width:1040px;margin-left: 10px;">
    		<table id="listtable" class="sortable">
    			<thead>
	    			<tr>
	    				<th class="sorttable_nosort" style="width:7%;">
	    					<a href="javascript:void(0);" onclick="checkAllAndEdit('checkbox_template');">全选/</a>
			   				<a href="javascript:void(0);" onclick="uncheckAllCloseEdit('checkbox_template');">反选</a>
	    				</th>
	    				<th>整机名称</th>
	    				<th>终端编号</th>
	    				<th>SIM号码</th>
	    				<th class="sorttable_sorted">最后续费时间<span id="sorttable_sortfwdind">&nbsp<font face="webdings">6</font></span></th>
	    				<th>续费周期</th>
	    			</tr>
    			<thead>
    			<!-- 备注：tbody的id值必须为listbody在sorttable.js中被写死了 -->
    			<tbody id="listbody">
					<c:forEach var="t" items="${requestScope.terminalRenewInfoList }" varStatus="u">
					  	<tr
					  		<c:choose>
				  				<c:when test="${u.index % 2 == 0}"> class="tdBG01"</c:when>
				  				<c:otherwise>class="tdBG02"</c:otherwise>
				  			</c:choose>
					  	>
					  		<td style="text-align: center;">
					  			<input type="checkbox" name="checkbox_template" onclick="editTextStatus(this);" value="${t.dno }" />
					  		</td>
					  		<td>${t.vname}</td>
					  		<td>${t.dno}</td>
					  		<td style="text-align: center;">
					  			<input value="${t.simnum }" onkeydown="backspaceEnable();" readonly="readonly" style="width:100px;border:0px;background-color:transparent;" />
					  			<input type="hidden" value="${t.simnum }" />
					  		</td>
					  		<td style="text-align: center;">
					  			<input value="${t.lastrenewdate }" onkeydown="backspaceEnable();" readonly="readonly" style="width:100px;border:0px;background-color:transparent;" />
					  			<input type="hidden" value="${t.lastrenewdate }" />
					  		</td>
					  		<td style="text-align: center;">
					  			<input value="${t.renewperiod }"   onkeydown="backspaceEnable();" readonly="readonly" style="width:50px;border:0px;background-color:transparent;" />
					  			<input type="hidden" value="${t.renewperiod }" />
					  		</td>
					  	</tr>
				  	</c:forEach>
    			</tbody>
    		</table>
    		<!--  分页  -->
    		<span id="totalNum"></span>&nbsp;
			<span id="spanPageNum"></span>&nbsp;
			<span id="spanFirst"></span>&nbsp;
			<span id="spanPre"></span>&nbsp;
			<span id="spanNext"></span>&nbsp;
			<span id="spanLast"></span>&nbsp;
			<span id="spanTotalPage"></span>
    	</div>
    
		<!-- 隐藏域，存提交到服务器的数据，组与组之间';'分割，一组之内','分割 -->
		<input type="hidden" id="alldata" name="alldata" value="${requestScope.alldata }"/>
    </form>
  </body>
  <script type="text/javascript" src="<%=basePath%>web/js/pageOfJavaScript.js"></script>
  <script type="text/javascript" src="<%=basePath%>web/js/sorttable.js"></script>
  <script type="text/javascript">
  		function clearIt(){
  			document.getElementById('vname').value = "";
  			document.getElementById('dno').value = "";
  			document.getElementById('simnum').value = "";
  			document.getElementById('lastrenewdate').value = "";
  			document.getElementById('renewperiod').value = "";
  		}
  </script>
</html>
