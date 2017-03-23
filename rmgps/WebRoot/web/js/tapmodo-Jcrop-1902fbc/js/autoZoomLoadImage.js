var x;
var y;
var x2;
var y2;
var width;
var height;
$(function(){
	jQuery.fn.autoZoomLoadImage = function(scaling, width, height, loadPic) {
	  if (loadPic == null) loadPic = "web/images/blue-loading.gif";
	  return this.each(function() {
	    var t = $(this);
	    var src = $(this).attr("src");
	    var img = new Image();
	    //alert("Loading")
	    img.src = src;
	    //自动缩放图片
	    var autoScaling = function() {
	      if (scaling) {
	        if (img.width > 0 && img.height > 0) {
	          if (img.width / img.height >= width / height) {
	            if (img.width > width) {
	              t.width(width);
	              t.height((img.height * width) / img.width);
	            }
	            else {
	              t.width(img.width);
	              t.height(img.height);
	            }
	          }
	          else {
	            if (img.height > height) {
	              t.height(height);
	              t.width((img.width * height) / img.height);
	            }
	            else {
	              t.width(img.width);
	              t.height(img.height);
	            }
	          }
	        }
	      }
	      $("#width0").val(t.width());
	      $("#width2").val(img.width);
	    }
	    //处理ff下会自动读取缓存图片
	    if (img.complete) {
	      autoScaling();
	      return;
	    }
	    $(this).attr("src", "");
	    var loading = $("<img alt=\"加载中\" title=\"图片加载中\" src=\"" + loadPic + "\" />");
	    t.hide();
	    t.after(loading);
	    $(img).load(function() {
	      autoScaling();
	      loading.remove();
	      t.attr("src", this.src);
	      t.show();
	    });
	  });
	}

	var jcrop_api, boundx, boundy;
	//使原图具有裁剪功能
	$.imgJcrop=function(){
		$('#img2').Jcrop({
			onChange: updatePreview,
			onSelect: updatePreview,
			aspectRatio: 1, //选中区域宽高比为1，即选中区域为正方形
			bgColor:"#ccc", //裁剪时背景颜色设为灰色
			bgOpacity:0.1 //透明度设为0.1
		},function(){
			var bounds = this.getBounds();
			boundx = bounds[0];
			boundy = bounds[1];
			jcrop_api = this;
		});
	}
	//裁剪过程中，每改变裁剪大小执行该函数
	function updatePreview(c){
		if (parseInt(c.w) > 0){
			$('#preview').css({
				width: Math.round(56/c.w * boundx) + 'px',//60 为预览div的宽和高
				height: Math.round(56/ c.h * boundy) + 'px',
				marginLeft: '-' + Math.round(56 / c.w * c.x) + 'px',
				marginTop: '-' + Math.round(56 / c.h * c.y) + 'px'
			});
			var width0=$("#width0").val();
			var width2=$("#width2").val();
			var width1=Math.round(c.w*width2/width0);
			var height1=Math.round(c.h*width2/width0);
			var x1=Math.round(c.x*width2/width0);
			var y1=Math.round(c.y*width2/width0);
			$('#width').val(width1);  //c.w 裁剪区域的宽
			$('#height').val(height1); //c.h 裁剪区域的高
			$('#x').val(x1);  //c.x 裁剪区域左上角顶点相对于图片左上角顶点的x坐标
			$('#y').val(y1);  //c.y 裁剪区域顶点的y坐标
		}
	};
});
