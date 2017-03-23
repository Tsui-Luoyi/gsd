var DIV_COUNT = 12;
var PostBlogUrl = "";

function ControlDiv(index) {

	for ( var i = 1; i <= DIV_COUNT; i++) {
		var e = document.getElementById("dv" + i);
		var ev = document.getElementById("dvShow" + i);
		if (e == null || ev == null)
			continue;
		if (i == index) {
			if (e.className == "left2_on") {
				e.style.backgroundImage = "url(../images/left_an_A.gif)";
				e.style.color = '#EAF9F2';
				e.className = "left_XZ";
			} else {
				e.style.backgroundImage = "url(../images/left_an_C.gif)";
				e.style.color = '#EAF9F2';
				e.className = "left2_on";
			}
		}
	}

}
function showdv(index) {

	if (document.getElementById("dvShow" + index).style.display == "none") {
		document.getElementById("dvShow" + index).style.display = "block";
		document.getElementById("dv" + index).className = "left_XZ";
	} else {
		document.getElementById("dvShow" + index).style.display = "none";
		document.getElementById("dv" + index).className = "left2_on";
	}

	ControlDiv(index);

}
function SpaceClass(div) {
	document.getElementById("divset" + div).className = "left2_break_1";

}
