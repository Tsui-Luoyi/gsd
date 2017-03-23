/* 只提交代理商拥有的车辆vid   */
function toRight(){//获取左边选中的复选框的值移至右边
	var ids1 = findSelections("checkbox_template","id");//获取左边选中的复选框的值
	
	if(ids1 == null){//左边复选框没被选中，则取消提交
		alert("请选择代理商车辆！");
		return false;
	}
	
	document.form123.action = "resetVehiclesAction.action?ids="+ids1+"&resetFlag="+1;//提交表单
	document.form123.submit();
}

/* 只提交代理商拥有的车辆vid   */
function toLeft(){//获取右边选中的复选框的值移至左边
	var ids2 = findSelections("checkbox_template2","id");//获取右边选中的复选框的值
	
	if(ids2 == null){//右边复选框没被选中，则取消提交
		alert("请选择未代理商车辆！");
		return false;
	}
	
	var ids1 = findAllSelections("checkbox_template","id");//获取左边复选框的所有的值[包括未被选中和被选中的所有值]
	var ids = ids1 + "," + ids2
	
	document.form123.action = "resetVehiclesAction.action?ids="+ids+"&resetFlag="+2;//提交表单
	document.form123.submit();
}
