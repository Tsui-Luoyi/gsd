
var mapInfowindow;
var markerInfowindow;

/**
 * 初始化地图时，加载标注
 */ 
function initRemarker(map){
	$.ajax({
		type : "POST",
		url : "VehicleQueryRemarkerAction.action",
		success : function(data) {
			$.each(data,function(remarkerlist,objs){
				$.each(objs,function(i,obj){ // obj就是标志remarkerVO对象
					var olatlng = new google.maps.LatLng(obj.remarkerLat,obj.remarkerLng);
					var oremarker = new google.maps.Marker({
						zIndex:Number(obj.remarkerId),
						title:obj.remarkerTitle,
						position:olatlng,
						map:map
					});

					// 给oremarker添加右键点击事件
					google.maps.event.addListener(oremarker, 'click', function(event) {
						// 关闭已经存在的其他窗口
						if (mapInfowindow != null){
							mapInfowindow.close();
						}
						if (markerInfowindow != null){
							markerInfowindow.close();
						}
						
						// 取值上来的时候，让marker的zIndex的值等于数据库中取上来marker对象的id，这样就能保证唯一了。
						var markerContentString = getRemarkerContentStr(obj.remarkerTitle,obj.remarkerInfo);
						markerInfowindow = new google.maps.InfoWindow({
							content : markerContentString
						});
				
						var currentLatLng = event.latLng;
						markerInfowindow.setPosition(currentLatLng);
						markerInfowindow.open(map);
				
						var markerdelete = document.getElementById('markerDelbt');
						markerdelete.onclick = function delRemarker() {
							// 获取当前marker的索引，作为要删除的标注的id
							var remarkerId = oremarker.getZIndex();
							$.ajax({
								type : "GET",
								url : "VehicleRemoveRemarkerAction.action?remarkerId="
										+ remarkerId,
								success : function(msg) {
									markerInfowindow.close();
									oremarker.setMap(null);
								}
							});
						} // delRemarker end
					});
				});
			});
		}
	});
}

// 添加标注
function addRemarker(map){
		// 定义弹出输入页面的窗体
		var mapContentString =  getMapContentStr();
		mapInfowindow = new google.maps.InfoWindow({
			content : mapContentString
		});
		
		google.maps.event.addListener(map, 'rightclick',
				function(event) {
					// 关闭已经存在的其他窗口
					if (mapInfowindow != null){
						mapInfowindow.close();
					}
					if (markerInfowindow != null){
						markerInfowindow.close();
					}
	
					// 获取当前点击处的坐标
					var currentLatLng = event.latLng;
					mapInfowindow.setPosition(currentLatLng);
					mapInfowindow.open(map);
	
					// 获取地图按钮，添加点击事件
					var mapAddbt = document.getElementById('mapAddbt');
					mapAddbt.onclick = function addRemarker() {
						
						var remarkerTitleStr = document.getElementById('mapRemarkTitle').value;
						var remarkerInfoStr = document.getElementById('mapRemarkInfo').value;
						var time = new Date().getTime();    
						var jsondata = {
							"remarkerId": time,
							"remarkerLat" : currentLatLng.lat(),
							"remarkerLng" : currentLatLng.lng(),
							"remarkerInfo" : remarkerInfoStr,
							"remarkerTitle" : remarkerTitleStr
						}
						
						$.ajax({
							type : "POST",
							url : "VehicleAddRemarkerAction.action",
							data : jsondata,
							success : function(msg) {
							var	remarker = new google.maps.Marker({
									zIndex : time,
									title : remarkerTitleStr,
									position : currentLatLng,
									map : map
								});
								
								google.maps.event.addListener(remarker, 'click', function(event) {
									if (mapInfowindow != null){
										mapInfowindow.close();
									}
									if (markerInfowindow != null){
										markerInfowindow.close();
									}
									
									// 取值上来的时候，让marker的zIndex的值等于数据库中取上来marker对象的id，这样就能保证唯一了。
									var markerContentString = getRemarkerContentStr(remarkerTitleStr,remarkerInfoStr);
									markerInfowindow = new google.maps.InfoWindow({
										content : markerContentString
									});
							
									var currentLatLng = event.latLng;
									markerInfowindow.setPosition(currentLatLng);
									markerInfowindow.open(map);
							
									var markerdelete = document.getElementById('markerDelbt');
									markerdelete.onclick = function delRemarker() {
										// 获取当前marker的索引，作为要删除的标注的id
										var remarkerId = remarker.getZIndex();
										$.ajax({
											type : "GET",
											url : "VehicleRemoveRemarkerAction.action?remarkerId="
													+ remarkerId,
											success : function(msg) {
												markerInfowindow.close();
												remarker.setMap(null);
											}
										});
									} // delRemarker end
								});
							}
						});
						if (mapInfowindow != null){
							mapInfowindow.close();
						}
					} // addRemarker end
			});  // 添加标注结束
}
	
function getMapContentStr(){
	var mapContentStr = '<div id="mapInfowindow">'

		+ '<h3>添加备注</h3>'
		+ '标题'
		+ '<div id="mapBodyTitle">'
		+ '<input id="mapRemarkTitle" name="mapRemarkTitle" type="text"></input>'
		+ '</div>' 
		+ '内容'
		+ '<div id="mapBodyContent">'
		+ '<textarea rows="2" cols="30" id="mapRemarkInfo" name="mapRemarkInfo"></textarea>'
		+ '</div>' 
		+ '<div id="mapButton" style="align:center;">'
		+ '<button type="button" id="mapAddbt">添加</button>' 
		+ '</div>'
		+ '</div>';
	return mapContentStr;
}	

function getRemarkerContentStr(remarkerTitle,remarkerInfo){
	var markerContentString = '<div id="markerInfowindow">'

		+ '<h3>备注信息</h3>'
		+ '<hr/>'
		+ '<div id="mapBodyTitle">'
		+ '<h4>'
		+ remarkerTitle
		+'</h4>'
		+ '</div>' 
		+ '<div id="markerBodyContent">'
		+ '<textarea rows="2" cols="30" readonly="readonly" style="overflow:auto;border:0" id="markerRemarkInfo" name="markerRemarkInfo" readonly="true">'
		+ remarkerInfo
		+ '</textarea>'
		+ '</div>' + '<div id="markerButton">'
		+ '<button type="button" id="markerDelbt">删除</button>'
		+ '</div>' + '</div>';
	return markerContentString;
}


















