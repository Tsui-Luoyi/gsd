//jquery.jQchart
//
//@charset utf-8
//(Public Domain)
//http://jsgt.org/lib/jquery/plugin/jqchart/nightly/nightly.htm

(function($) { 

  ////body�������쌝�� IE��body�o����canvas�Ӥ��ʤ� ʹ��?
  if($.browser.msie)document.body||document.write('<body>');
  //http://allabout.co.jp/internet/javascript/closeup/CU20060228A/index2.htm

$.fn.jQchart = {};
$.fn.jQchart= function(cfg){ 

	var that=this,its;
	
	//Adjustment for canvas on IE
	if($.browser.msie)$(window).load(function (){ini()})
	else ini();

	$.fn.jQchart.version= '0.02-beta-1.4';
	$.fn.jQchart.global_zIndex=1000;
	
	function ini(){
		switch( typeof cfg ){
			case 'string' : ajaxload(cfg)   ;break;
			case 'object' : drawChart(cfg) ;break;
		};
	}
	
	//XHR for case 'string' 
	function ajaxload(cfg){
		$.post(cfg, function(res){
			eval("var _option = "+res);
			drawChart(_option);
		});
	}
	
	//Arguments for case 'object' 
	function drawChart(_option){
	
		jQuery.extend( $.fn.jQchart,{
		
			draw : function (op){
				return this.wrtGraph(op);
			},
			
			//�����O��
			init :	function (op){ 
		
				if(!op)op={}
				if(!op.config)op.config={scaleY:{}};
				if(!op.config.scaleY)op.config.scaleY={};
				
				this.op=jQuery.extend({
					
					type		: op.config.type	|| 'line',
				
					//�����Х���ID��
					id	:	$(that).get(0).id||op.config.id || "canvasChart_"+(new Date()).getTime(),
					
					//�����Х��η��ȸߤ�
					width		: op.config.width	|| $("#"+that.get(0).id).attr('width')	||300,
					height		: op.config.height	|| $("#"+that.get(0).id).attr('height') ||150,
		
					//canvas property //���Ȥ�line���S�e��config�O�����ܤˤ���
					fillStyle	: op.config.fillStyle	|| "rgba(255,0,0,0.5)",
					strokeStyle	: op.config.strokeStyle	|| "rgba(180,180,180,0.5)",
					lineWidth	: op.config.lineWidth	||	1,
		
					//�ե���ȥ�����
					scaleFontSize	: op.config.scaleFontSize || op.fontSize ||	10,
					dataFontSize	: op.config.dataFontSize	|| op.fontSize ||10,
					
					//����`���I��Υѥǥ���
					paddingL	: op.config.paddingL	|| 40,
					paddingT	: op.config.paddingT	|| 30,
					paddingR	: op.config.paddingR	|| 10,
					paddingB	: op.config.paddingB	|| 30,
					
					//Title
					title		: op.config.title		|| '<a href="http://jsgt.org/mt/archives/01/001827.html">jquery.jQchart</a>',
					titleTop	: op.config.titleTop	|| 5,
					titleLeft	: op.config.titleLeft	|| 70,
					
	
					//YĿʢ
					dataYmin	: op.config.scaleY.min || 0,
					dataYmax	: op.config.scaleY.max || 1000,
					dataYgap	: op.config.scaleY.gap || 100,
					scaleYLeft	: op.config.scaleYLeft || 4,
					
					//X�S��٥�����
					labelX		: op.config.labelX || null,
	
					//����`���I��Υǩ`����ʾ
					labelDataShow	: op.config.labelDataShow || true,
					labelDataOffsetY: op.config.labelDataOffsetY || 10,
					labelDataOffsetX: op.config.labelDataOffsetX || 0,
					
					data		: op.data ||{}
					
				},op||{});
				
				this.op.grid	= {
						col:(this.op.labelX)?this.op.labelX.length+1: this.op.data[0].length+1||50,//����
						row:this.op.dataYmax/this.op.dataYgap
				};
				this.op.scaleXTop
					=this.op.scaleYBottom
					=this.op.height-this.op.paddingB;
				this.op.scaleXRight	=this.op.width-this.op.paddingR;
				this.op.chartWidth		=this.op.width -this.op.paddingL-this.op.paddingR;
				this.op.chartHeight		=this.op.height-this.op.paddingT-this.op.paddingB;
				this.op.scaleYTop		=this.op.paddingT;
				this.op.scaleXLeft		=this.op.paddingL;
				this.op.yGap			=this.op.dataYgap*this.op.chartHeight/this.op.dataYmax
				this.op.xGap			=this.op.chartWidth/this.op.grid.col
		
				this.resetBox(this.op.id);
				this.mkCanvas(this.op);
			},
			//�ꥻ�å�
			resetBox : function(id){
				$("#jQchart-title-T-"+id).remove();
				$("#jQchart-scale-Y-"+id).remove();
				$("#jQchart-scale-X-"+id).remove();
				$("#jQchart-data-D-"+id).remove();
			},
			//�����Х����å�
			mkCanvas : function (op){
			
				its=this;
			
				//for Opera Bug
				if(jQuery.browser.opera && eval(jQuery.browser.version,10)<9.5)
					$("#"+that.get(0).id).get(0).outerHTML=('<canvas id="'+this.op.id+'"></canvas>');
				//CanvasҪ��
				this.canvas = $("#"+that.get(0).id)
								.attr('width'	,this.op.width)
								.attr('height' ,this.op.height)
								.get(0)
			
				//�ᥤ��CanvasDIV����
				this.jQcanvasBox =this.mkBox4Canvas(this.canvas);
				this.canvasBox =$(this.jQcanvasBox).get(0);
					
				if (this.canvas.getContext){
		
					this.ctx=this.canvas.getContext('2d'); 
					
					//�����Х��ץ�ѥƥ��O��
					this.ctx.fillStyle   =this.op.fillStyle;
					this.ctx.strokeStyle =this.op.strokeStyle;
					this.ctx.lineWidth   =this.op.lineWidth;
	
					this.ctx.shadowBlur = 10;
					this.ctx.shadowOffsetX = 10;
	
	
	
					//XY�S�軭
					this.setXline(this.op);
					this.setYline(this.op);
					
	
					
					//��DIV����
					this.titleBox//Title
						=this.mkBoxElement('T',
							this.op.titleLeft,this.op.titleTop
						).appendTo(this.jQcanvasBox)
						.css('width',this.op.width-this.op.titleLeft)//fix for safari3 2007.12.4
						.get(0);
					this.scaleYBox//Y�S�����`��
						=this.mkBoxElement('Y',
							this.op.scaleYLeft,this.op.scaleYTop
						).appendTo(this.jQcanvasBox).get(0);
					this.scaleXBox//X�S�����`��
						=this.mkBoxElement('X',
							this.op.scaleXLeft,this.op.scaleXTop
					).appendTo(this.jQcanvasBox).get(0);
					
					this.dataBox  //����`���ϤΥǩ`��
						=this.mkBoxElement('D',
							this.op.paddingL+ 'px',this.op.paddingT+ 'px'
						).appendTo(this.jQcanvasBox)
						.get(0);
					//����`���ϤΥǩ`����ʾ/�Ǳ�ʾ
					if(this.op.labelDataShow){ $(this.dataBox).show() } else { $(this.dataBox).hide() }
	
	
					//���֥륯��å���λ�äΥɥ�å��ƄӤ����ܤˤʤ�ޤ�
					if(jQuery)if(jQuery.ui)if(jQuery.ui.draggable){
	
						var _cnt=0;_cnt++;if(_cnt>1)return;/*for fix unbind*/
					
						this.jQcanvasBox.unbind('dblclick');
						this.jQcanvasBox.dblclick(function(e){  
							
							if(!$(this).hasClass("ui-draggable")){
								draggingon(this);
							}
	
						})	.mousedown(function(){_cnt=0/*fix unbind*/})
							.click(function(e){
								if(e.target.id!=$(this).get(0).id) draggingoff(this);
							})
					}
				
					function draggingon(oj){
	
						//var jclen=$(".jQchart").length;
							
						$(oj).draggable({opacity:0.5 })
								.addClass("ui-draggable")
								.removeClass("ui-draggable-disabled")
								.css('border','2px dotted cyan')
								.css("zIndex",$.fn.jQchart.global_zIndex++)
								/*.append($('<div id="jQchart-close-'+oj.id+'" \
									style="font-size:0.9em;\
									width:300px;\
									color:#333;\
									padding:0px">[X]-close</div>').one('click',function(){
										$(_cbox).replaceWith('<canvas id="'+its.canvas.id+'"></canvas>')
									}))
								*/
								.append('<div id="jQchart-drgmsg-M-'+oj.id+'" class="jQchart-drgmsg-M">draggable-�ɥ�å��Ǥ��ޤ�</div>')
								
					}
					function draggingoff(oj){
						$(oj).draggableDisable()
							.css('border','0px')
							.removeClass("ui-draggable")
							.addClass("ui-draggable-disabled")
						$("#jQchart-drgmsg-M-"+oj.id).remove();
					}
			     //========dbug========
			     //this._debugShowPos4();
				}
			},
			
			mkBox4Canvas:function(canvas){
		
				if(!document.getElementById("jQchart-" +this.op.id))
					return $('<div></div>')
						.attr('id','jQchart-'+this.op.id)
						.attr('class','jQchart')
						.css({
									position : 'relative',
									margin	 :'0px',
									padding	 :'0px',
									width	 : this.op.width+'px',
									height	 : this.op.height+'px'
						})
						.insertBefore(canvas)
						.append(canvas)
						
				else return $("#jQchart-" +this.op.id)
			},
			mkBoxElement:function(type,x,y){
		
				var typeName='';
				switch(type){
					case 'T' : typeName='title';break;
					case 'Y' : typeName='scale';break;
					case 'X' : typeName='scale';break;
					case 'D' : typeName='data' ;break;
				};
		
				if($("'#jQchart-scale-" +type+'-'+this.op.id+"'" ).length==0)
					return $('<div></div>')
						.attr('id'	 ,'jQchart-'+typeName+'-'+type+'-'+this.op.id)
						.attr('class','jQchart-'+typeName+'-'+type)
						.css({
										position : this.op.position	||'absolute',
										left		: x 		 ||'10px',
										top			: y 		 ||'10px'
						})
						.appendTo(document.body)
		
			},
		
			//Title
			wrtTitle :	function(op){
				op.subclass = 'title' ;
					this.wrtText(0+ 'px',0+ 'px',
						this.op.title,
						op,
						"#jQchart-title-T-"+this.op.id
					);
			},
		
			//X�S��٥�
			wrtXscale	: function(op){
				op.subclass = 'labelX' ;
				op.color		= '#333'	 ;
				op.start = this.util.getBasePoint(this.op);
		
				var x=0+op.xGap//op.start.x ,
					y=10//op.start.y  ;
						
				for (var i = 0, currP=0; i < op.grid.col-1;i++) {
	
					this.wrtText(x+ 'px',y+ 'px',
						op.labelX[currP]+" ",
						op,
						"#jQchart-scale-X-"+this.op.id
					);
					x	+=this.op.xGap;
					currP ++;
					this.util.setNextX(op); 
				}
				
			},
		
			//Y�S��٥�
			wrtYscale	: function(op){
				op.subclass = 'labelY' ;
				op.color		= '#333'	 ;
				op.start = this.util.getBasePoint(this.op);
		
				var x=this.op.scaleYLeft ,
					y=this.op.chartHeight - this.op.scaleFontSize ;
						
				for (var i = 0, currP=0; i <= op.grid.row;i++) {
				
					currP = Math.round((currP*100))/100;//���
				
					this.wrtText(x+ 'px',y+ 'px',
						currP+" ",
						op,
						"#jQchart-scale-Y-"+this.op.id
					);
					y	-=this.op.yGap;
					currP +=this.op.dataYgap;
					this.util.setNextY(op); 
				}
				
			},
		
			//ˮƽ���S
			setXline : function(op){
				
				this.op.begin = this.util.getBasePoint(this.op);
				this.op.end	={ 
								x	: this.op.scaleXRight,
								y	: this.op.begin.y 
				}
				
				for (var i = 0; i <= this.op.grid.row; i++) { 
					this.drawLine(this.op);
					this.util.setNextY(this.op); 
				}
				
				this.op.setXline=true;
			},
		
			//��ֱ���S
			setYline : function(op){
				this.op.begin = this.util.getBasePoint(this.op);
				this.op.end	={ 
								x	: this.op.begin.x,
								y	: this.op.scaleYTop
				};
				 for (var i = 0; i <= this.op.grid.col ; i++) {
						this.drawLine(this.op);
						this.util.setNextX(this.op);
				}
		
				this.op.setYline=true;
		
			},
		
			//�S�軭
			drawLine : function(op){
					this.ctx.beginPath();
					this.ctx.moveTo(op.begin.x,op.begin.y);
					this.ctx.lineTo(op.end.x	,op.end.y );
					this.ctx.stroke();
					this.ctx.save();
			},
			
			//�ۤ쾀�軭
			wrtGraph : function(op){	//200:y=700:100
			
				if(typeof op =='object')this.op =  op ;
				this.init(this.op);
				var op =this.op;
				var it =this ;
				//if(!its.ctx)return
				
				$.each(op.data,function(index,value){
				
					op.rows=op.data[index];
					op.subclass = 'labelData';
					op.color		= 'orange'	;
			
					var strokeStyle=(index==0)?'red':'#FF9114';
					its.ctx.strokeStyle=strokeStyle;//�ۤ쾀ɫ ���Ȥ�optionָ����
					 var x = op.paddingL,
							y = - op.rows[0]*op.height/op.dataYmax ; 
			 
					its.ctx.beginPath();
					
					for (var i = 0; i < op.rows.length; i++) {	
					
						x += op.xGap;
						y = op.paddingT+ op.chartHeight
								-op.rows[i]*op.chartHeight/op.dataYmax ;
			
						if(i==0) its.ctx.moveTo(x,y);
						else		 its.ctx.lineTo(x,y);
			
						//�ǩ`��
						if( x <= op.width){
							var dx=x-op.paddingL,dy=y-op.paddingT;
							it.wrtText(
								dx+op.labelDataOffsetX+ 'px',
								dy+op.labelDataOffsetY+ 'px',
								op.rows[i],op,
								"#jQchart-data-D-"+op.id
							).css('color',(op.data.length==1)?'#333':strokeStyle);
						}//alert(op.rows[i],it.op)
						
					}
					
					its.ctx.stroke();
				});
				
				//�����ȥ��
				this.wrtTitle(this.op);
				//X��٥��
				if(this.op.labelX)this.wrtXscale(this.op);
				//Y��٥��
				this.wrtYscale(this.op);
		
				return $("#"+this.op.id);
				
			},
		
			//���ֳ���
			wrtText : function(x,y,text,op,scope){
		
				var op=op||this.op;
				if(op.subclass)var subclass= op.subclass;
		
				return $('<div class="jQchart-'+subclass+'-'+op.id+' jQchart-'+subclass+'"></div>')
					.append(text)
					.css({
						position	: op.position	||'absolute',
						left		: x 		||'10px',
						top			: y 		||'10px'
					})
						
					.appendTo(scope||document.body)
		
			},
			 hoverDataEffect : function(){
			
				$(".jQchart-labelData-"+this.op.id).hover(hover,unhover)
				
					function hover(){$(this).css("font-size","2em")}
					function unhover(){$(this).css("font-size",1+"em")}
			},
			
			//�ǥХå���
			_debugShowPos4 : function(){
				var htm='Properties of<br>'
							 +'<font color="orange">$("#'+this.op.id+'").jQchart.op</font><hr>'
				for(var i in this.op)htm+=i+" : "+this.op[i]+"<br>"
				$("<div></div>").html(htm)
				.appendTo(document.body)
				.draggable({opacity:0.5 })
					.css({
						position		: 'absolute',
						top				: '10px',
						left			: '70%',
						margin			: '10px',
						padding			: '10px',
						backgroundColor	: '#eee'
					})
			},
			
			util : new function(){
				return {
					getMinMax : function(ary){
						//for (var i = 0,a=[]; i < ary.length; i++)a.push(ary[i].split(',')[3])
						//return { min:a.sort()[0],max:a.reverse()[0]	}
	
						return { 
							min:function(){return ary.sort(function(a,b){return a-b})[0]},
							max:function(){return ary.sort(function(a,b){return b-a})[0]}
						}
					},
					getBasePoint : function(op){
						return { 
							x	: op.scaleXLeft,
							y	: op.scaleYBottom
						}
					},
					setNextX	: function(op){op.begin.x = op.end.x += op.xGap;},
					setNextY	: function(op){op.begin.y = op.end.y -= op.yGap;}
				}
			}
		
		});
	
		$(that).jQchart.draw(_option);//�軭
		return $(this);
	}
	
	return $(this);
}})(jQuery);


