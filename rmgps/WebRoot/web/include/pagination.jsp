<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
    function trim(str){ 
		return str.replace(/(^\s*)|(\s*$)/g, ""); 
	}
	function selectPage(input){
		var value = trim(input.value); 
		if(value == ""){ 
		return; 
	  } 
		if(/\d+/.test(value)){
			input.form.submit(); 
			return; 
		} 
		alert("请输入正确的页数"); 
		input.focus();
	} 
</script>
<div> 
	<table>
		<tr>
			<td style="height: 20px;">
				<s:if test="pagination.totalPages != 0">
					<s:url action="%{#request.url}" id="first"> 
						<s:param name="pagination.currentPage" value="1"></s:param> 
					</s:url> 
					<s:url action="%{#request.url}" id="next"  > 
						<s:param name="pagination.currentPage" value="pagination.currentPage+1"> 
					</s:param> 
					</s:url> 
					<s:url action="%{#request.url}" id="prior" > 
						<s:param name="pagination.currentPage" value="pagination.currentPage-1"></s:param> 
					</s:url> 
					<s:url action="%{#request.url}" id="last"> 
						<s:param name="pagination.currentPage" value="pagination.totalPages"></s:param> 
					</s:url> 
					<s:if test="pagination.currentPage == 1"> 
						<span class="current">首页</span> 
						<span class="current">上一页</span> 
					</s:if>
					<s:else> 
						<s:a href="%{first}">首页</s:a> 
						<s:a href="%{prior}">上一页</s:a> 
					</s:else> 
					<s:if test="pagination.currentPage == pagination.totalPages || pagination.totalPages == 0"> 
						<span class="current">下一页</span> 
						<span class="current">末页</span>
					</s:if> 
					<s:else> 
						<s:a href="%{next}">下一页</s:a>&nbsp;&nbsp;
					  	<s:a href="%{last}">末页</s:a>
					</s:else>
			</td>
			<td>
				<span class="jumplabel"><input type="button" value="跳转到" style="padding-top: 3px;border: 1px solid #003366;background-color:#FFCC00;height: 21px;text-align:center;"/></span>
				<s:form action="%{#request.url}" theme="simple" cssStyle="display:inline">
					<s:hidden name="pagination.totalPages" value="%{pagination.totalPages}"></s:hidden>
					<input type="text" name="pagination.currentPage" size="2" onblur="selectPage(this)" value='<s:property value="pagination.currentPage" />'/>
					<span class="jumplabel">页</span>
				</s:form>
			</td>
			<td>
				<span class="jumplabel">共<s:property value="pagination.totalRowsCount" />条</span> 
				<span class="jumplabel">当前是第<s:property value="pagination.currentPage" />/<s:property value="pagination.totalPages"/>页</span>
				</s:if>
			</td>
		</tr>
	</table>
</div>