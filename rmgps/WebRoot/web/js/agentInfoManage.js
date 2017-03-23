function modifyAgent() {
	var ids = findSelections("checkbox_template2", "id"); //取得多选框的选择项
	if (ids == null) { //如果ids为空
		alert("请选择一条记录!");
		return;
	}
	if (ids.length > 1) { //如果ids有2条以上的纪录
		alert("只能选择一条记录!");
		return;
	}
	modifyAgent2(ids);
}
function dels(ids) {
	if(confirm("确定要删除吗？")){
		$.ajax( {
			url : "DeleteAgentAction.action",
			data : "user_id=" + ids,
			dataType : "json",
			type : "POST",
			success : function(data) {
				if (data == 0) {
					document.location.href = "AgentManageAction.action";
				} else if (data == 2) {
					alert("拥有下级代理商，无法删除！");
				} else if(data == 3){
					alert("代理商下有设备，无法删除！！");
				}else {
					alert("请重新选择！");
				}
			},
			error : function() {
				alert("请重新操作！");
			}
		});
	}
}
function deleteAgent2(userId2) {
	dels(userId2);
}
function deleteAgent() {
	var ids = findSelections("checkbox_template2", "id"); //取得多选框的选择项
	if (ids == null) { //如果ids为空
		alert("请选择一条记录!");
		return;
	} else {
		dels(ids);
	}
}
function changeFolder(jvo) {
	if ($('tr[id^=' + jvo + ']').css("display") == "block") {
		$('tr[id^=' + jvo + ']').hide();
	} else {
		$('tr[id^=' + jvo + ']').show();
	}
}
function changeFolder2(jvo, jvs) {
	if ($('tr[id^=' + jvs + ']').css("display") == "none") {
		$('img[id^=ig' + jvs + ']').attr("src","web/js/dtree/img/nolines_minus.gif");
		$('tr[id^=' + jvs + ']').show();
		$('tr[id^=' + jvs + ']').css("display", "block");
	} else if ($('tr[id^=' + jvo + ']').css("display") == "block") {
		$('img[id^=ig' + jvo + ']').attr("src","web/js/dtree/img/nolines_plus.gif");
		$('tr[id^=' + jvo + ']').hide();
		$('tr[id^=' + jvo + ']').css("display", "none");
	} else {
	}
}