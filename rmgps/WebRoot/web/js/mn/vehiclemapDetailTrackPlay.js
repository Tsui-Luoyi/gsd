function fcx_swapImgRestore() { // v3.0
	var i, x, a = document.fcx_sr;
	for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
		x.src = x.oSrc;
}

function fcx_findObj(n, d) { // v4.01
	var p, i, x;
	if (!d)
		d = document;
	if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
		d = parent.frames[n.substring(p + 1)].document;
		n = n.substring(0, p);
	}
	if (!(x = d[n]) && d.all)
		x = d.all[n];
	for (i = 0; !x && i < d.forms.length; i++)
		x = d.forms[i][n];
	for (i = 0; !x && d.layers && i < d.layers.length; i++)
		x = fcx_findObj(n, d.layers[i].document);
	if (!x && d.getElementById)
		x = d.getElementById(n);
	return x;
}

function fcx_swapImage() { // v3.0
	var i, j = 0, x, a = fcx_swapImage.arguments;
	document.fcx_sr = new Array;
	for (i = 0; i < (a.length - 2); i += 3)
		if ((x = fcx_findObj(a[i])) != null) {
			document.fcx_sr[j++] = x;
			if (!x.oSrc)
				x.oSrc = x.src;
			x.src = a[i + 2];
		}
}

// ---------------------------------------初始化-----------------------------------
if (track == null)
	var track = {};
if (track.history == null)
	track.history = {};
if (defaultCenter == null)
	var defaultCenter = new google.maps.LatLng(34.1254, 109.0283); // 缺省地图中心点

track.history._showgpshandler = null;// GPS相关信息显示函数句柄
track.history._gpsHistoryList = null;// 定位信息原始数据
track.history._gpslist = [];// 保存定位信息对象的队列
track.history._vno = "";// 车辆编号
track.history._map = null;// GoogleMap对象句柄
track.history._marker = null;// 定位点标记
track.history._polyline = null;// 轨迹对象
track.history._contrl = {};// 轨迹控制器
track.history._contrl.stimehandler = null;// 开始时间控件的句柄
track.history._contrl.etimehandler = null;// 结束时间空间的句柄
track.history._contrl.state = 1; // 当前控制器状态 0-stop 1-play 2-pause 3-forword
// 4-back
track.history._contrl.timedirty = false; // 时间编辑控件的脏标志
track.history._contrl.stime = ""; // 开始查询时间
track.history._contrl.etime = ""; // 结束查询时间
track.history._contrl.pos = 0; // 当前的轨迹点索引
track.history._contrl.endpos = 0; // 最后轨迹点的索引
track.history._contrl.gpscount = 0; // gps坐标点的数量
track.history._contrl.timer = null; // 轨迹播放定时器
track.history._contrl.timeout = 1500; // 轨迹播放定时器倒计时时间
track.history._info = {};// 当前定位信息
track.history._info.gps = {}; // GPS定位信息
track.history._info.gps.longitude = "";// 经度
track.history._info.gps.latitude = "";// 纬度
track.history._info.gps.revtime = "";// 接收时间
track.history._info.gps.satellitenum = "";// 卫星个数
track.history._info.gps.gsmstrong = "";// 信号强度
track.history._info.gps.addr = "";// 地址
track.history._info.gps.locateFlag = "";	//定位方式

track.history.setVno = function(vno) {// 车辆编号赋值
	track.history._vno = vno;
};
track.history.setShowGPSHandler = function(handler) {// GPS信息显示函数句柄赋值
	track.history._showgpshandler = handler;
};
track.history.setStartTimeHandler = function(handler) {// 开始时间句柄赋值
	track.history._contrl.stimehandler = handler;
};
track.history.setEndTimeHandler = function(handler) {// 结束时间句柄赋值
	track.history._contrl.etimehandler = handler;
};
track.history.setTimeDirty = function(flag) {// 设置时间编辑控件脏读状态
	if (flag == null)
		track.history._contrl.timedirty = false;
	else
		track.history._contrl.timedirty = flag;
};
track.history.setSTime = function(time) {// 设置原始查询开始时间
	track.history._contrl.stime = time;
	track.history._contrl.stimehandler.value = time;
};
track.history.setETime = function(time) {// 设置原始查询结束时间
	track.history._contrl.etime = time;
	track.history._contrl.etimehandler.value = time;
};
track.history.setGpsHistoryList = function(obj) {// 设置定位信息原始数据
	track.history._gpsHistoryList = obj;
};
// ---------------------------------------初始化-结束----------------------------------

function showGPSInfo() {

	var gpsinfo = track.history._info.gps;
	var revtime = gpsinfo.revtime;
	document.getElementById('revtime').innerText = revtime.substring(0, 19);// 数据接收时间

	var jw = gpsinfo.addr;
	//改成直接赋予地址
	showLocation(jw);

	document.getElementById('gsmstrong').innerText = gpsinfo.gsmstrong;// 信号强度
	document.getElementById('locateFlag').innerText = gpsinfo.locateFlag;// 定位方式
	
//	document.getElementById('satellitenum').innerText = gpsinfo.satellitenum;// 卫星个数
}

function initPage(vno, start_time, end_time, st_obj, et_obj, gpsHistoryList) {
	track.history.setVno(vno);
	track.history.setShowGPSHandler(showGPSInfo);
	track.history.setStartTimeHandler(st_obj);
	track.history.setEndTimeHandler(et_obj);
	track.history.setSTime(start_time);
	track.history.setETime(end_time);
	track.history.setGpsHistoryList(gpsHistoryList);
	track.history.initMapObject();
}

track.history.initMapObject = function() {// 初始化地图对象

	if (track.history._map != null)
		return;
	var myOptions = {
		center : defaultCenter,
		zoom : 15,
		mapTypeId : google.maps.MapTypeId.ROADMAP,
		scaleControl : true,
		overviewMapControl : false,
		streetViewControl : false,
		mapTypeControl : true,
		mapTypeControlOptions : {
			style : google.maps.MapTypeControlStyle.DEFAULT,
			position : google.maps.ControlPosition.TOP_RIGHT
		},
		zoomControl : true
	};

	track.history._map = new google.maps.Map(document.getElementById("map"),
			myOptions);
	try {
		track.history._contrl.gpscount = track.history._gpsHistoryList.length;
		if (track.history._contrl.gpscount < 0)
			track.history._contrl.gpscount = 0;
		if (track.history._contrl.gpscount > 9999)
			track.history._contrl.gpscount = 9999;
	} catch (ex) {
		track.history._contrl.gpscount = 0;
	}

	if (track.history._contrl.gpscount == 0) {
		track.history._contrl.pause();
		alert("查询结果为空，无轨迹显示...");
		return;
	}

	if (!track.history._info.callback()) {// 第一个点的经纬度无效
		track.history._contrl.pause();
		return;
	}

	track.history._showMapMarker();
	track.history._showgpshandler();
	track.history._showruninfohandler();
	track.history._contrl.play();
	return;

	// track.history._loadErrorHandler();
	// return;
};
var waitTime = 0;
track.history._loadErrorHandler = function() {// 如果加载时出现错误，重新加载
	if (this._map == null) {
		return;
	}
	if (this._polyline != null) {
		this._polyline.setMap(null); // 从地图上移除polyline--------------
	}
	if (this._marker != null) {
		this._marker.setMap(null);// 从地图上移除marker---------
	}

	try {
		var point = new google.maps.LatLng(this._info.gps.latitude,
				this._info.gps.longitude);
		this._map.setCenter(point, 15);
		this._marker = new google.maps.Marker({
			icon : {
				url : "web/images/mapCenter.png",
				size : new google.maps.Size(50, 50),
				anchor : new google.maps.Point(25, 25)
			},
			position : point,
			map : this._map
		});
		this._polyline = new google.maps.Polyline({
			path : [ point ],
			geodesic : true,
			strokeColor : '#FF0000',
			strokeOpacity : 1.0,
			strokeWeight : 5
		});
		this._polyline.setMap(this._map);

	} catch (ex) {
		alert("地图定位点显示出现异常,Error:" + ex.name + ", " + ex.message);
	}

	document.getElementById('proBar').style.display = '';
	window.setTimeout("track.history.pauseIt()", 3000);
}

track.history.pauseIt = function() {
	waitTime++;
	if (waitTime == 3) {// 第三次执行给与alert-这个弹框一定程度上会帮助异常消失
		alert('您的网路不给力，请稍候...');
	}
	if (waitTime > 3) {// 第四次执行，提示用户刷新进行重新加载
		if (confirm("网络异常，是否刷新进行重新加载？")) {
			track.history._contrl.stimehandler.disabled = "";
			track.history._contrl.etimehandler.disabled = "";
			document.form1.submit();
			return false;
		} else {
			window.onerror = null;// 关闭异常捕获
			return false;
		}
	}
	// this._map.addOverlay(this._polyline);//----------偶然问题语句
	this._polyline.setMap(this._map);
	this._showMapMarker();
	document.getElementById('proBar').style.display = 'none';
	this._showgpshandler();
	this._contrl.play();
}

track.history._showMapMarker = function() {// 地图定位点显示-------------
	if (this._map == null)
		return;
	if (this._marker != null)
		return;
	try {
		var point = new google.maps.LatLng(this._info.gps.latitude,
				this._info.gps.longitude);
		this._map.setCenter(point);
		this._marker = new google.maps.Marker({
			icon : {
				url : "../images/mapCenter.png",
				size : new google.maps.Size(50, 50),
				anchor : new google.maps.Point(25, 25)
			},
			position : point,
			map : this._map
		});
		this._polyline = new google.maps.Polyline({
			path : [ point ],
			geodesic : true,
			strokeColor : '#FF0000',
			strokeOpacity : 1.0,
			strokeWeight : 5
		});
		this._polyline.setMap(this._map);
		this._contrl.pos = 1;

	} catch (ex) {

		alert("地图定位点显示出现异常,Error:" + ex.name + ", " + ex.message);
	}

	return;
};

// 插入新的轨迹点
track.history._insertnewvertex = function(point) {
	var path = this._polyline.getPath();
	path.push(point);
	this._marker.setPosition(point);
	// 判断当前轨迹点是否在地图可视区域，如果不在，则移动地图
	beyondBoundTracking(track.history._map,point);
	return;
};

// 移动到旧的轨迹点
track.history._movetovertex = function(pos) {
	if (pos <= 0 || pos > this._contrl.endpos)
		return false;
	var path = this._polyline.getPath();
	var point = path.getAt(pos - 1);
	if (point == null)
		return false;
	this._marker.setPosition(point);
	
	// 判断当前轨迹点是否在地图可视区域，如果不在，则移动地图
	beyondBoundTracking(track.history._map,point);
	return true;
};

// 定时器处理函数
track.history._contrl.timercallback = function() {
	track.history._contrl.timer = null;
	if (track.history._contrl.state != 1)
		return;

	if (track.history._contrl.pos == track.history._contrl.gpscount) {
		track.history._contrl.pause();
		alert("轨迹播放完毕...");
		return;
	}

	// 显示下一个轨迹点
	track.history._contrl.show(track.history._contrl.pos + 1);
	track.history._contrl.timer = window.setTimeout(
			track.history._contrl.timercallback, track.history._contrl.timeout);
	return;
};

track.history._contrl.show = function(pos) {// 显示指定的轨迹点
	if (pos < 1) {
		this.pos = 1;
		alert("已到达起点，无法继续后退...");
	} else if (pos > this.gpscount) {
		this.pos = this.endpos;
		alert("已到达终点，无法继续前进...")
	} else
		this.pos = pos;

	var retcb = false;
	if (this.pos > this.endpos)
		retcb = track.history._info.callback();
	if (retcb) {
		track.history._showgpshandler();
		// 插入新的polyline
		var point = new google.maps.LatLng(track.history._info.gps.latitude,
				track.history._info.gps.longitude);
		track.history._insertnewvertex(point);

		return;
	}

	if (!track.history._info.gps.getdatabypos(this.pos)) {// 提取GPS定位数据
		return;
	}
	track.history._showgpshandler();
	track.history._movetovertex(this.pos); // 移动到已存在的轨迹点
	return;
};

track.history._contrl.stimeChange = function() { // 开始时间发生改变
	if (this.stimehandler == null) {
		this.timedirty = false;
		return;
	}
	startTime = this.stimehandler.value;
	if (startTime == "") {
		alert("请输入开始时间...")
		this.timedirty = false;
		return;
	}

	this.timedirty = true;
}

track.history._contrl.etimeChange = function() { // 结束时间发生改变
	if (this.etimehandler == null) {
		this.timedirty = false;
		return;
	}
	endTime = this.etimehandler.value;
	if (endTime == "") {
		alert("请输入结束时间...")
		this.timedirty = false;
		return;
	}

	this.timedirty = true;
}

track.history._contrl.play = function() {// 播放
	this.state = 1;
	startTime = this.stimehandler.value;
	endTime = this.etimehandler.value;
	if (startTime != "" && endTime != "") {
		if (this.timedirty) {
			// 如果正在查询后台数据则按钮的功能失效！
			document.getElementById('proBar').style.display = '';
			document.getElementById("pause").onclick = '#';
			document.getElementById("pause").alt = '请稍等....';
			document.getElementById("back").onclick = '#';
			document.getElementById("back").alt = '请稍等....';
			document.getElementById("forward").onclick = '#';
			document.getElementById("forward").alt = '请稍等....';
			document.getElementById("stop").onclick = '#';
			document.getElementById("stop").alt = '请稍等....';
			document.getElementById("pause").style.display = 'inline-block';
			document.getElementById("play").style.display = 'none';
			form1.submit();
			this.stimehandler.disabled = "true";
			this.etimehandler.disabled = "true";
			return;
		}
	} else {// 再次播放时，如果开始时间或结束时间是空的，那么重新给其赋上一次的值
		this.stimehandler.value = this.stime;
		this.etimehandler.value = this.etime;
	}
	this.timedirty = false;// 还原为初始值
	if (this.pos == 0) {
		alert("无轨迹进行显示...");
		return;
	}

	if (this.pos == this.gpscount) {
		alert("轨迹播放完毕，重新播放请先按停止键...")
		return;
	}

	document.getElementById("pause").style.display = 'inline-block';
	document.getElementById("play").style.display = 'none';
	this.stimehandler.disabled = "true";
	this.etimehandler.disabled = "true";
	if (this.timer == null)
		this.timer = window.setTimeout(this.timercallback, 2000);
	return;
};

track.history._contrl.pause = function() {// 暂停
	this.state = 2;
	document.getElementById("pause").style.display = 'none';
	document.getElementById("play").style.display = 'inline-block';
	this.stimehandler.disabled = "";
	this.etimehandler.disabled = "";
	if (this.timer != null) {
		clearTimeout(this.timer);
		this.timer = null;
	}
	return;

};

track.history._contrl.stop = function() {// 停止
	this.pause();
	if (this.pos == 0)
		return;
	// 显示第一个轨迹点
	this.show(1);
	return;
};

track.history._contrl.forward = function() {// 前进
	this.pause();
	if (this.pos == 0)
		return;
	// 显示下一个轨迹点
	this.show(this.pos + 1);
	return;
};

track.history._contrl.back = function() {// 后退
	this.pause();
	if (this.pos == 0)
		return;
	// 显示上一个轨迹点
	this.show(this.pos - 1);
	return;
};

track.history._info.callback = function() { // 回调函数,从原始数据List更新信息
	if (track.history._contrl.gpscount == 0)
		return false;
	if (track.history._contrl.endpos == track.history._contrl.gpscount) {
		track.history._contrl.pos = track.history._contrl.endpos;
		return false;
	}

	var obj = track.history._gpsHistoryList[track.history._contrl.endpos];

	if (!track.history._info.gps.callback(obj)) {
		track.history._gpsHistoryList.shift(track.history._contrl.endpos, 1);
		track.history._contrl.gpscount = track.history._contrl.gpscount - 1;
		return false;
	}
	track.history._contrl.endpos++;
	track.history._contrl.pos = track.history._contrl.endpos;
	return true;
};

track.history._info.gps.getdatabypos = function(pos) { // 根据索引获取缓存数据
	var index = pos - 1;
	// ===================================================================================================================================
	if (index < 0 || index >= track.history._contrl.endpos)
		return false;
	var obj = track.history._gpslist[index];
	if (obj == null)
		return false;
	var i = 0;
	for (elements in obj) {
		this[elements] = obj[elements];
		i++;
	}
	if (i == 0)
		return false;

	return true;
};

track.history._info.gps.clone = function() {
	var newObj = new Object();
	for (elements in this) {
		newObj[elements] = this[elements];
	}
	return newObj;
}

track.history._info.gps.callback = function(obj) { // 回调函数
	if (obj == null) {
		return false;
	}
	var jsonContent = obj;
	track.history._info.gps.longitude = jsonContent.longitude;
	track.history._info.gps.latitude = jsonContent.latitude;
	track.history._info.gps.revtime = jsonContent.login_time;
	track.history._info.gps.gsmstrong = jsonContent.gsm_strong;
	track.history._info.gps.addr = jsonContent.addr;// 地址
	track.history._info.gps.locateFlag  = jsonContent.locate_flag;
	var obj = this.clone();
	track.history._gpslist.push(obj);
	return true;
};