
$(document).ready(function () { 
	var it1 = $('div[topnav=nav5]').attr('attr');
	if(it1 === 'undefined') return false;
	var heht1 = $('div[topnav=nav5]').find('#' + it1).height() + 500 + "px";
	$('div[topnav=nav5]').find('.submnu').height(heht1);
	$('div[topnav=nav5]').find('.submnu').animate({height:'show'},{
		queue:false,
		duration:500,
		easing:'easeOutBounce'
	});
	$('div[topnav=nav5]').stop().attr('class', 'imenu_on').siblings().attr('class', 'imenu');
	
},function(){
	$('div[topnav=nav5]').stop().find('.submnu').hide();
	$('div[topnav=nav5]').stop().attr('class', 'imenu');
});  
	

$(function(){
	//导航菜单
	$('div[topnav]').hover(function(){
		var it = $(this).attr('attr');
		if(it === 'undefined') return false;
		var heht = $(this).find('#' + it).height() + 500 + "px";
		
		$(this).find('.submnu').height(heht);
		
		$(this).stop().find('.submnu').animate({height:'show'},{
			queue:false,
			duration:500,
			easing:'easeOutBounce'
		});
		
		$(this).stop().attr('class', 'imenu_on').siblings().attr('class', 'imenu');
		
	},function(){
		$(this).stop().find('.submnu').hide();
		$(this).stop().attr('class', 'imenu');
	});  

});