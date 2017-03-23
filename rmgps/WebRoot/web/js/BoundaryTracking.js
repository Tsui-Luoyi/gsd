/*思路：
 * 1.获取地图上可视区域，找出区域最大点和最小点的坐标位置
 * 2.获取地图中心点，解析中心点坐标位置
 * 3.将当前点坐标分别和地图可视区域最大点、最小点比较，判断当前坐标是否在该区域内
 * 4.如果在该区域内，不进行操作
 * 5.如果不在该区域内，则将地图中心点向当前点方向平移
 */

// 定义越界次数
var beyondCount = 0;
// 定义未越界次数
var unBeyondCount = 0;

function beyondBoundTracking(map, point) {
	if (map == null)
		return;
	// 获取地图上bounds
	var bounds = map.getBounds();
	var maxLng = bounds.getNorthEast().lng();
	var maxLat = bounds.getNorthEast().lat();
	var minLng = bounds.getSouthWest().lng();
	var minLat = bounds.getSouthWest().lat();

	// 获取地图中心点
	var mapCenter = map.getCenter();
	var mcLat = mapCenter.lat();
	var mcLng = mapCenter.lng();

	// 获取地图缩放级别
	var mapZoom = map.getZoom();
	// 当前坐标位置
	if (point == null)
		return;
	var curLat = point.lat();
	var curLng = point.lng();

	if (curLat > maxLat) {
		var deLat = curLat - maxLat;
		mcLat = mcLat + deLat + 0.002;
		mapCenter = new google.maps.LatLng(mcLat, mcLng);
		map.panTo(mapCenter);
		
		beyondCount++;
	}

	if (curLng > maxLng) {
		var deLng = curLng - maxLng;
		mcLng = mcLng + deLng + 0.002;
		mapCenter = new google.maps.LatLng(mcLat, mcLng);
		map.panTo(mapCenter);
		
		beyondCount++;
	}
	if (curLat < minLat) {
		var deLat = minLat - curLat;
		mcLat = mcLat - deLat - 0.002;
		mapCenter = new google.maps.LatLng(mcLat, mcLng);
		map.panTo(mapCenter);
		
		beyondCount++;
	}
	if (curLng < minLng) {
		var deLng = minLng - curLng;
		mcLng = mcLng - deLng - 0.002;
		mapCenter = new google.maps.LatLng(mcLat, mcLng);
		map.panTo(mapCenter);
		
		beyondCount++;
	}
	
	// 判断是否进行缩放
	if (mapZoom > 12 && beyondCount >=3){
		map.setZoom(mapZoom - 1);
		beyondCount = 0;
	}
	
	
	
	
	
	
	
	
}