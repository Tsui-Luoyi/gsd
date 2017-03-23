var windowHeight;
var windowWidth;
var popWidth;
var popHeight;
function popCenterWindow() {
	windowHeight = $(window).height();
	windowWidth = $(window).width();
	popHeight = $(".window").height();
	popWidth = $(".window").width();
	var popY = (windowHeight - popHeight) / 2;
	var popX = (windowWidth - popWidth) / 2;
	$("#center").css("top", popY).css("left", popX).slideToggle("slow");
	$(".title img").click(function() {
		$(this).parent().parent().hide("slow");
	});
}
$(document).ready(function(){
	$(".window").draggable();
});
