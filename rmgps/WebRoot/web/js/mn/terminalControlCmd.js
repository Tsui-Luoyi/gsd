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
			case '101':
			    var ipaddress = document.getElementById('ipaddress').value;
			    var port = document.getElementById('port').value;
			    if(ipaddress=="" || port==""){
			       alert("请填写相关IP和端口！");
			    }else{
			       rstVar = '{"ipaddress":'+ipaddress+',"port":'+port+'}';
			    }
			    break;
			case '102':
			    var time1 = document.getElementById('uptimenormal').value;
			    var time2 = document.getElementById('uptimeurgent').value;
			    rstVar = '{"up_time_normal":'+time1+',"up_time_urgent":'+time2+'}';
			    break;
			case '103':
				var devtermstate = document.getElementById('devtermstate').value;
			    if(devtermstate == '0'){
			    	rstVar = '{"devterm_state":'+devtermstate+'}';
			    }else{
			    	var persisttime = document.getElementById('persist_time').value;
			    	if(persisttime == ''){
			    		alert("请填写终端状态紧急持续时间！");
			    	}else{
			    		rstVar = '{"devterm_state":'+devtermstate+',"duration":'+persisttime+'}';
			    	}
			    }
			    break;
			case '105':
				var week = document.getElementById('week').value;
				var hour = document.getElementById('hour').value;
				var minute = document.getElementById('minute').value;
				if(hour=="" || minute==""){
					alert("请填写小时和分钟！");
				}else{
					var patrn_hour = /^\d$|^1\d$|^2[0-3]$/;
					var patrn_minute = /^\d$|^[1-5]\d$/;
					if(hour.match(patrn_hour)){
						hour = parseInt(hour);
						if(minute.match(patrn_minute)){
							minute = parseInt(minute);
							rstVar = '{"week":'+week+',"hour":'+hour+',"minute":'+minute+'}';
						}else{
							alert("分钟范围0-59！");
						}
					}else{
						alert("小时范围0-23！");
					}
				}
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