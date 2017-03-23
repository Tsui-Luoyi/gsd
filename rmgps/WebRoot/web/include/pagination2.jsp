<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
    function trim(str){ 
		return str.replace(/(^\s*)|(\s*$)/g, ""); 
	}
	function selectPage(){
		var pagesNum = document.getElementById("pagesNum").value;
		var value = trim(pagesNum); 
		if(value == ""){ 
		return; 
	    } 
		if(/^(\-?)(\d+)$/.test(value)){
			document.getElementById("form3").submit();
			return; 
		} 
		alert("请输入正确的页数"); 
		input.focus();
	} 
</script>
<div style="margin-top: 10px;border: none;"> 
	<table style="border: none;">
		<tr valign="top" style="background-color: white;border: none;">
			<td style="border: none;">
				<s:if test="pagination.totalPages != 0"> 
					<s:url action="web/vehicleinfo/%{#request.url}" id="first"> 
						<s:param name="pagination.currentPage" value="1"></s:param> 
						<s:param name="ifHit" value="1"></s:param>
					</s:url> 
					<s:url action="web/vehicleinfo/%{#request.url}" id="next"  > 
						<s:param name="ifHit" value="1"></s:param> 
						<s:param name="pagination.currentPage" value="pagination.currentPage+1">
					</s:param> 
					</s:url> 
					<s:url action="web/vehicleinfo/%{#request.url}" id="prior" > 
						<s:param name="pagination.currentPage" value="pagination.currentPage-1"></s:param>
						<s:param name="ifHit" value="1"></s:param> 
					</s:url> 
					<s:url action="web/vehicleinfo/%{#request.url}" id="last"> 
						<s:param name="pagination.currentPage" value="pagination.totalPages"></s:param> 
						<s:param name="ifHit" value="1"></s:param>
					</s:url> 
					<s:if test="pagination.currentPage == 1"> 
						<span>[首页/上页]</span> 
					</s:if>
					<s:else> 
						[<s:a href="%{first}">首页</s:a>/ 
						<s:a href="%{prior}">上页</s:a>] 
					</s:else>
					<s:property value="pagination.currentPage" />
					<s:if test="pagination.currentPage == pagination.totalPages || pagination.totalPages == 0"> 
						<span>[下页/尾页]</span> 
					</s:if> 
					<s:else>
						[<s:a href="%{next}">下页</s:a>/
				    	<s:a href="%{last}">尾页</s:a>]共<s:property value="pagination.totalPages"/>页&nbsp;
					</s:else>到第
					<s:form id="form3" name="form3" action="%{#request.url}" theme="simple" cssStyle="display:inline">
						<s:hidden name="pagination.totalPages" value="%{pagination.totalPages}"></s:hidden>
						<s:hidden id="ifHit" name="ifHit" value="1"></s:hidden>
						<input id="pagesNum" type="text" name="pagination.currentPage" size="2" value="<s:property value="pagination.currentPage" />" style="border-left: none;border-right: none;border-top: none;border-bottom-color: black;" onkeydown="javascript:if(window.event.keyCode==13)selectPage();"/>
					</s:form>
					页&nbsp;<a href="javascript:void(0);" onclick="selectPage()">确定</a>
				</s:if>
			</td>
		</tr>
	</table>
</div>