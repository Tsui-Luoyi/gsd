	function showTime(obj){//终端状态紧急时显示持续时间，正常时隐藏持续时间
		if(obj.value == '1'){
			document.getElementById('persistTime').style.display = 'block';
		}else{
			document.getElementById('persistTime').style.display = 'none';
		}
	}
	
var randomnum = 0;
	
	function cancel_cmd(cmdid,cancel_cmdid,obj){//发送取消命令
		//发送取消命令
		randomnum = randomnum+1;
		var vnostr = document.getElementById('vno').value;
		var urlstr = "sendCancelCmdAction.action?vno="+vnostr+"&cmdid="+cmdid+"&cancel_cmdid="+cancel_cmdid+"&flushrandom="+randomnum;
		$.ajax({
			type:"get",
			url:urlstr,
			async :false,
			success:function backfunction(backdata){
				//不做处理
			}
		});
		//增加或更新table中命令发送队列信息--即删除改行
		obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode);
	}

	function sendSettingCmd(cmdid,cmd_type,cmd_name){//设置
		var jsonstr = setJsonTovar(cmdid);
		if(jsonstr == ''){
			return false;
		}
		randomnum = randomnum+1;
		var vnostr = document.getElementById('vno').value;
		var urlstr = "sendSettingCmdAction.action?vno="+vnostr+"&cmdid="+cmdid+"&jsonstr="+jsonstr+"&flushrandom="+randomnum;
		$.ajax({
			type:"get",
			url:urlstr,
			async :false,
			success:function backfunction(backdata){
				//不做处理
			}
		});
		//增加或更新table中命令发送队列信息
		insertORupdateRow(cmdid,cmd_type,cmd_name);
	}
	
	function insertORupdateRow(cmdid,cmd_type,cmd_name){
		var e = document.getElementById('cmdqueuetable');
		if(document.getElementById(cmdid) != undefined){//该命令已存在发送队列中，则更新其发送时间
			var obj = document.getElementById(cmdid);
			obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode);//删除自己
			var nextRow = e.insertRow(1);//增加新行到第一行位置
			var newCell = nextRow.insertCell();
			newCell.innerHTML = cmd_name + "<input type='hidden' id='"+cmdid+"'>";
			var newCell = nextRow.insertCell();
			newCell.align = "center";
			newCell.innerHTML = cmd_type;
			var newCell = nextRow.insertCell();
			newCell.align = "center";
			newCell.innerHTML = formatdate();
			var newCell = nextRow.insertCell();
			newCell.align = "center";
			newCell.innerHTML = "<input value='取消发送' type='button' onclick='cancel_cmd("+"201,"+cmdid+",this);'>";
		}else{
			var nextRow = e.insertRow(1);//增加新行到第一行位置
			var newCell = nextRow.insertCell();
			newCell.innerHTML = cmd_name + "<input type='hidden' id='"+cmdid+"'>";
			var newCell = nextRow.insertCell();
			newCell.align = "center";
			newCell.innerHTML = cmd_type;
			var newCell = nextRow.insertCell();
			newCell.align = "center";
			newCell.innerHTML = formatdate();
			var newCell = nextRow.insertCell();
			newCell.align = "center";
			newCell.innerHTML = "<input value='取消发送' type='button' onclick='cancel_cmd("+"201,"+cmdid+",this);'>";
		}
	}
	
	function formatdate(){
		var myDate = new Date();
		var cyear = myDate.getFullYear();
		var cmonth = myDate.getMonth()+1;//获取当前月份(0-11,0代表1月)
		cmonth = formatedate_2(cmonth);
		var cdate = myDate.getDate();
		cdate = formatedate_2(cdate);
		var chours = myDate.getHours();
		chours = formatedate_2(chours);
		var cminutes = myDate.getMinutes();
		cminutes = formatedate_2(cminutes);
		var cseconds = myDate.getSeconds();
		cseconds = formatedate_2(cseconds);
		var newdate = cyear+"-"+cmonth+"-"+cdate+" "+chours+":"+cminutes+":"+cseconds;
		return newdate;
	}
	
	function formatedate_2(obj){
		if((''+obj).length == 1){
			obj = '0'+obj;
		}
		return obj;
	}
	
	function sendGatherCmd(cmdid,cmd_type,cmd_name){//采集
		randomnum = randomnum+1;
		var vnostr = document.getElementById('vno').value;
		var urlstr = "sendGatherCmdAction.action?vno="+vnostr+"&cmdid="+cmdid+"&flushrandom="+randomnum;
		$.ajax({
			type:"get",
			url:urlstr,
			async :false,
			success:function backfunction(backdata){
				//不做处理
			}
		});
		//增加或更新table中命令发送队列信息
		insertORupdateRow(cmdid,cmd_type,cmd_name);
	}
	
	function setJsonTovar(cmdid){
      	var rstVar = "";
      	switch(cmdid){
			case '302':
			    var state = document.getElementById('devtermstate').value;
			       rstVar = '{"devterm_state":'+state+'}';
			    break;
			case '305':
			    var ipaddress0 = document.getElementById('ipaddress0').value;
			    var ipaddress1 = document.getElementById('ipaddress1').value;
			    var ipaddress2 = document.getElementById('ipaddress2').value;
			    var ipaddress3 = document.getElementById('ipaddress3').value;
			    var port = document.getElementById('port').value;
			   /* if(ipaddress0 == "" || ipaddress1 == "" || ipaddress2 == "" || ipaddress3 == "" || port == "") {
			    	return "";
			    } 
			    if(ipaddress0.length >4 || ipaddress1.length >4 || ipaddress2.length >4 || ipaddress3.length >4 || port >6) {
			    	alert("请输入正确的IP和端口号");
			    	return "";
			    }*/
			    rstVar = '{"ipaddress":'+ipaddress0+"-"+ipaddress1+"-"+ipaddress2+"-"+ipaddress3+",port:"+port+'}';
			    break;
			case '303':
				var upinterval = document.getElementById('upinterval').value;
				var emergencyState = document.getElementById('emergencyState').value;
				var time = document.getElementById('starthour').value;
				var starthour;
				var startminute;
				if(time == null || time == "") {
					 starthour = "00";
					 startminute = "00";
				}else{
					 starthour = time.split(":")[0];
					 startminute = time.split(":")[1];
				}
				
				var residue = document.getElementById('residue').value;
				if(residue <= 999 && residue != "" || emergencyState == 0 || emergencyState == 3) {
					rstVar = '{"upinterval":'+upinterval+",emergencyState:"+emergencyState+",starthour:"+starthour+",startminute:"+startminute+",residue:"+residue+'}';
				}else {
					document.getElementById('residue').value="";
					alert("请输入正确的剩余次数!");
				}
			    break;
			case '304':
				var cycDay = document.getElementById('cycday');
				if(cycDay == null)
					cycDay = 1;
				else
					cycDay=cycDay.value;
				var everydayUpNum = document.getElementById('everydayUpNum').value;
				var time= document.getElementById('hour').value;
				if(time == null || time == "") {
					alert("请选择上传时间.");
				}else {
					var hour = time.split(":")[0];
					var minute = time.split(":")[1];
					rstVar = '{"hour":'+hour+',"minute":'+minute+',"everydayUpNum":'+ everydayUpNum+ ',"cycDay":' + cycDay +'}';
      			}	
				break;
			case '306':
				var message = document.getElementById('messageNO').value;
				 if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(message))){
				        alert("不是完整的11位手机号或者正确的手机号前七位");
				    }
				rstVar = '{"message":'+message+'}';
				break;
		}
		return rstVar;
    }
	
/*	function normalOrAlarm(obj) {
		  var _val = $(obj).val();
		  if(_val == 1) {
			  $("#changeArea").empty();
			  var str = '上传间隔'
			    +'<select name="upinterval" id="upinterval">'
				+'<option value="10">10</option>'
	  			+'<option value="30">30</option>'
	  			+'<option value="60">60</option>'
				+'</select>'
				+'<input name="button2" type="button" value="采集" />'
				+'<input name="button2" type="button" value="设置" />';
			  $("#changeArea").prepend(str);
		  }
	  }*/