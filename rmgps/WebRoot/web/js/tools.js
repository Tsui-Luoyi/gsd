function findSelections(checkboxName, idName) {  //从列表中找出选中的id值列表
	var elementCheckbox = document.getElementsByName(checkboxName);  //通过name取出所有的checkbox
	var ids = null;  //定义id值的数组
	for(var i=0;i<elementCheckbox.length;i++){  //循环checkbox组
		if(elementCheckbox[i].checked) {  //如果被选中
			if(ids == null) {
				ids = new Array(0);
			}
			ids.push(elementCheckbox[i].value);  //加入选中的checkbox
		}
	}
	return ids;
}

function findNotSelections(checkboxName, idName) {  //从列表中找出未被选中的id值列表
	var elementCheckbox = document.getElementsByName(checkboxName);  //通过name取出所有的checkbox
	var ids = null;  //定义id值的数组
	for(var i=0;i<elementCheckbox.length;i++){  //循环checkbox组
		if(!elementCheckbox[i].checked) {  //如果未被选中
			if(ids == null) {
				ids = new Array(0);
			}
			ids.push(elementCheckbox[i].value);  //加入未被选中的checkbox
		}
	}
	return ids;
}


function findAllSelections(checkboxName, idName) {  //从列表中找出所有的id值列表
	var elementCheckbox = document.getElementsByName(checkboxName);  //通过name取出所有的checkbox
	var ids = null;  //定义id值的数组
	for(var i=0;i<elementCheckbox.length;i++){  //循环checkbox组
		if(ids == null) {
			ids = new Array(0);
		}
		ids.push(elementCheckbox[i].value);  //加入未被选中的checkbox
	}
	return ids;
}

function checkAll(cName) {//复选框全选
	var code_Values = document.all[cName];
	if(code_Values == undefined){
		//alert('无记录可选！');
		return false;
	}
	if (code_Values.length) {
		for ( var i = 0; i < code_Values.length; i++) {
			code_Values[i].checked = true;
		}
	} else {
		code_Values.checked = true;
	}
}

function uncheckAll(cName) {//复选框反选
	var code_Values = document.all[cName];
	if(code_Values == undefined){
		//alert('无记录可取消！');
		return false;
	}
	if (code_Values.length) {
		for ( var i = 0; i < code_Values.length; i++) {
			code_Values[i].checked = false;
		}
	} else {
		code_Values.checked = false;
	}
}

function checkAllAndEdit(cName) {//复选框全选并且被选中的行的部分列的文本可被编辑
	var code_Values = document.all[cName];
	if(code_Values == undefined){
		//alert('无记录可选！');
		return false;
	}
	if (code_Values.length) {
		for ( var i = 0; i < code_Values.length; i++) {
			code_Values[i].checked = true;
			
			var simnumNode = code_Values[i].parentNode.nextSibling.nextSibling.nextSibling.childNodes[0];
  			var lastrenewdateNode = code_Values[i].parentNode.nextSibling.nextSibling.nextSibling.nextSibling.childNodes[0];
  			var renewperiodNode = code_Values[i].parentNode.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.childNodes[0];
  			
  			simnumNode.readOnly = false;
  			simnumNode.style.border = "1px solid black";
  			simnumNode.style.backgroundColor = "#E0EEE0";
  			simnumNode.attachEvent("onkeydown",backspaceAble);
			
  			lastrenewdateNode.readOnly = false;
  			lastrenewdateNode.style.border = "1px solid black";
  			lastrenewdateNode.style.backgroundColor = "#E0EEE0";
  			lastrenewdateNode.attachEvent("onclick",addCalendar);//添加日历控件事件,且只允许从日历控件中选
  			
  			renewperiodNode.readOnly = false;
  			renewperiodNode.style.border = "1px solid black";
  			renewperiodNode.style.backgroundColor = "#E0EEE0";
  			renewperiodNode.attachEvent("onkeydown",backspaceAble);
		}
	} else {alert();
		code_Values.checked = true;
	}
}

function uncheckAllCloseEdit(cName) {//复选框反选并且未被选中的行的所有列的文本不可以被编辑
	var code_Values = document.all[cName];
	if(code_Values == undefined){
		//alert('无记录可取消！');
		return false;
	}
	if (code_Values.length) {
		for ( var i = 0; i < code_Values.length; i++) {
			code_Values[i].checked = false;
			
			var simnumNode = code_Values[i].parentNode.nextSibling.nextSibling.nextSibling.childNodes[0];
  			var lastrenewdateNode = code_Values[i].parentNode.nextSibling.nextSibling.nextSibling.nextSibling.childNodes[0];
  			var renewperiodNode = code_Values[i].parentNode.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.childNodes[0];
  			
  			simnumNode.value = simnumNode.nextSibling.nextSibling.value;
 			simnumNode.readOnly = true;
  			simnumNode.style.border = "0px";
  			simnumNode.style.backgroundColor = "transparent";
  			simnumNode.detachEvent("onkeydown",backspaceAble);
			
			lastrenewdateNode.value = lastrenewdateNode.nextSibling.nextSibling.value;
  			lastrenewdateNode.readOnly = true;
  			lastrenewdateNode.style.border = "0px";
  			lastrenewdateNode.style.backgroundColor = "transparent";
  			lastrenewdateNode.detachEvent("onclick",addCalendar);//添加日历控件事件,且只允许从日历控件中选
  			
  			renewperiodNode.value = renewperiodNode.nextSibling.nextSibling.value;
  			renewperiodNode.readOnly = true;
  			renewperiodNode.style.border = "0px";
  			renewperiodNode.style.backgroundColor = "transparent";
  			renewperiodNode.detachEvent("onkeydown",backspaceAble);
		}
	} else {
		code_Values.checked = false;
	}
}

function editTextStatus(obj){
	var simnumNode = obj.parentNode.nextSibling.nextSibling.nextSibling.childNodes[0];
	var lastrenewdateNode = obj.parentNode.nextSibling.nextSibling.nextSibling.nextSibling.childNodes[0];
	var renewperiodNode = obj.parentNode.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.childNodes[0];
	
	if(obj.checked){//选中
		simnumNode.readOnly = false;
		simnumNode.style.border = "1px solid black";
		simnumNode.style.backgroundColor = "#E0EEE0";
		//simnumNode.detachEvent("onkeydown",backspaceEnable);//先删除屏蔽退格键事件,然后通过js添加开启退格键事件--后测试发现是多余
		simnumNode.attachEvent("onkeydown",backspaceAble);

		lastrenewdateNode.readOnly = false;
		lastrenewdateNode.style.border = "1px solid black";
		lastrenewdateNode.style.backgroundColor = "#E0EEE0";
		lastrenewdateNode.attachEvent("onclick",addCalendar);//添加日历控件事件,且只允许从日历控件中选
		
		renewperiodNode.readOnly = false;
		renewperiodNode.style.border = "1px solid black";
		renewperiodNode.style.backgroundColor = "#E0EEE0";
		renewperiodNode.attachEvent("onkeydown",backspaceAble);
	}else{//取消选中
		simnumNode.value = simnumNode.nextSibling.nextSibling.value;
		simnumNode.readOnly = true;
		simnumNode.style.border = "0px";
		simnumNode.style.backgroundColor = "transparent";
		simnumNode.detachEvent("onkeydown",backspaceAble);

		lastrenewdateNode.value = lastrenewdateNode.nextSibling.nextSibling.value;
		lastrenewdateNode.readOnly = true;
		lastrenewdateNode.style.border = "0px";
		lastrenewdateNode.style.backgroundColor = "transparent";
		lastrenewdateNode.detachEvent("onclick",addCalendar);
		
		renewperiodNode.value = renewperiodNode.nextSibling.nextSibling.value;
		renewperiodNode.readOnly = true;
		renewperiodNode.style.border = "0px";
		renewperiodNode.style.backgroundColor = "transparent";
		renewperiodNode.detachEvent("onkeydown",backspaceAble);
	}
}

/* 批量填充被选中行的最后续费时间 */
function batchFillRenewDate(){
	var fillRenewDate = document.getElementById('fillRenewDate').value;
	
	if(fillRenewDate != null){
		fillRenewDate = fillRenewDate.replace(/(^[\s]*)|([\s]*$)/g, "");//删除字符串两端空格
		
		if(fillRenewDate.length == 0){
			alert("请输入最后续费时间！");
			return false;
		}
		
		if(fillRenewDate.length != 10){
			alert("您输入的日期有误，请重新输入！");
		}else{//开始进行填充
			fillSelectionsForRenewDate('checkbox_template', fillRenewDate);
		}
	}else{
		alert("您输入的日期有误，请重新输入！");
	}
}

/* 批量填充被选中行的续费周期 */
function batchFillRenewPeriod(){
	var fillRenewPeriod = document.getElementById('fillRenewPeriod').value;
	
	if(fillRenewPeriod != null){
		fillRenewPeriod = fillRenewPeriod.replace(/(^[\s]*)|([\s]*$)/g, "");//删除字符串两端空格
		
		if(fillRenewPeriod.length == 0){
			alert("请输入续费周期！");
			return false;
		}
		
		for(var i = 0;i < fillRenewPeriod.length;i++){//截取最后五位，必须为数字
			var regNum =/^\d$/;
			var flag = regNum.test(fillRenewPeriod.substr(i,1));
			if(!flag){
				alert("续费周期必须为数字！");
				return false;
			}
		}
		
		//开始进行填充
		fillSelectionsForRenewPeriod('checkbox_template', fillRenewPeriod);
	}else{
		alert("您输入的数字有误，请重新输入！");
	}
}

function fillSelectionsForRenewDate(checkboxName, renewDate) {  //从列表中找出选中的id值列表
	var elementCheckbox = document.getElementsByName(checkboxName);  //通过name取出所有的checkbox
	var selectFlag = false;
	
	for(var i=0;i<elementCheckbox.length;i++){  //循环checkbox组
		if(elementCheckbox[i].checked) {  //如果被选中
  			var lastrenewdateNode = elementCheckbox[i].parentNode.nextSibling.nextSibling.nextSibling.nextSibling.childNodes[0];
  			
  			lastrenewdateNode.value = renewDate;
  			selectFlag = true;
		}
	}
	
	if(!selectFlag){
		alert("请勾选您需要填充的记录行！");
	}
}

function fillSelectionsForRenewPeriod(checkboxName, renewPeriod) {  //从列表中找出选中的id值列表
	var elementCheckbox = document.getElementsByName(checkboxName);  //通过name取出所有的checkbox
	var selectFlag = false;
	
	for(var i=0;i<elementCheckbox.length;i++){  //循环checkbox组
		if(elementCheckbox[i].checked) {  //如果被选中
  			var renewperiodNode = elementCheckbox[i].parentNode.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.childNodes[0];
  			
  			renewperiodNode.value = renewPeriod;
  			selectFlag = true;
		}
	}
	
	if(!selectFlag){
		alert("请勾选您需要填充的记录行！");
	}
}

function saveEditedSelections(){//提交修改
	var editedData = getDataFromSelections('checkbox_template');
	
	if(!editedData){
		return false;
	}
	
	document.getElementById('alldata').value = editedData;
	document.f1.action = "modifyTerminalRenew.action";
	document.f1.submit();
}

function getDataFromSelections(checkboxName){
	var elementCheckbox = document.getElementsByName(checkboxName);  //通过name取出所有的checkbox
	var selectFlag = false;
	var editedData = "";
	
	for(var i=0;i<elementCheckbox.length;i++){  //循环checkbox组
		if(elementCheckbox[i].checked) {  //如果被选中
			var simnumNode = elementCheckbox[i].parentNode.nextSibling.nextSibling.nextSibling.childNodes[0];
  			var lastrenewdateNode = elementCheckbox[i].parentNode.nextSibling.nextSibling.nextSibling.nextSibling.childNodes[0];
  			var renewperiodNode = elementCheckbox[i].parentNode.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.childNodes[0];
  			
  			editedData = editedData + ";" + elementCheckbox[i].value + "," + 
  						simnumNode.value + "," + lastrenewdateNode.value + 
  						"," + renewperiodNode.value;

  			selectFlag = true;
		}
	}
	
	if(!selectFlag){
		alert("请至少选中一条记录，然后修改后再进行保存！");
		return false;
	}
	
	return editedData;
}

function backspaceEnable(){
	if(event.keyCode == 8){
		event.returnValue = false;
	}
}

function backspaceAble(){//激活退格事件
	if(event.keyCode == 8){
		event.returnValue = true;
	}
}

function addCalendar(){
	WdatePicker({dateFmt:'yyyy-MM-dd'});
}


