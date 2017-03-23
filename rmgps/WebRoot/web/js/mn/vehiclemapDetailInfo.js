function check_submit() {
	
	var st = document.getElementById('start_time').value;
	var et = document.getElementById('end_time').value;

	if (st == '' || et == '') {
		alert('开始时间与结束时间必须填写');
		return false;
	}
	document.form1.submit();
}

function load(point) {
	
	var myOptions = {
		center : point,
		zoom : 8,
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
	var map = new google.maps.Map(document.getElementById("map"), myOptions);
}