/*
 * 确认对话框
 */
define("win", ['backbone', 'underscore', 'zseed','text!js/template/common/window-modal.html'],function(Backbone, _, seed,winHtml) {
    
    //view context
    var vc = seed.ui.view;
    
    vc.Window = Backbone.View.extend({
		config :{
		},
		initialize: function(context){
			this.config = {
				id:'',
				title:'确认对话框',
				content:'',
				closeBtn: true,
				buttons:[{
					name:'关闭',
					id:"close",
					'class':'btn',
					f:function(event,$el){
						$el.hide();
					}
				}]
			};
			if(context.show != undefined){
				this.show = context.show;
			}
			if(context.config != undefined){
				_.extend(this.config, context.config);
			}
		},
		render:function(){
			var template = _.template(winHtml);
			this.$el.html(template(this.config));
			this.bindEvent();
			this.$el.find('.modal').show();
		},
		bindEvent:function(){
			var bts = this.config.buttons;
			var $el = this.$el;
			_.each(bts,function(button){
				var id =  button.id;
				$el.find("#"+id).bind("click.jquery",function(event){
					button.f(event,$el);
				});
			});
			if(this.config.closeBtn){
				var view = this;
				$el.find(".a-close").bind('click.jquery',function(){
					view.close();
				});
			}
		},
		open:function(){
			this.beforeOpen();
			this.render();
			this.show();
			this.afterOpen();
		},
		close:function(){
			this.$el.hide();
		},
		show : function(){
			this.$el.show();
		},
		beforeOpen : function(){
		},
		afterOpen : function(){
		}
	});
	return seed;
});