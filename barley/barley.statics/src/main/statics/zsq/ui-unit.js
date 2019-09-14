/**
 * $ 2014-8-12 UNIT库
 */
define("ui-unit",[ 'backbone', 'underscore', 'jquery-validate'],function(Backbone, _) {
	var unit = {};
	
	//underscore 模板脚本处理
	_.templateSettings = {
	    interpolate: /\<\@\=(.+?)\@\>/gim,
	    evaluate: /\<\@(.+?)\@\>/gim,
	    escape: /\<\@\-(.+?)\@\>/gim
	};
	
	/**
	 * UNIT -- SELECT
	 */
	unit.select = Backbone.View.extend({
		defVal:null,
		/**
		 * SELECT 数据源
		 */
		items:[],
		/**
		 * @param config
		 * name: 数据显示域 默认是name
		 * title: 标题 空选择项提示信息
		 * defval: select 默认值
		 */
		initialize:function(context,config){
			var defalut_confg = {name:'name',title:null,defVal:null};
			this.config = _.extend(defalut_confg, config);
			this.initFlg = true;
		},
		template :  _.template('<@if(config.title != null){@>'
				+'<option value=""><@=config.title@></option>'
				+'<@}@>'
				+ '<@_.each(items,function(item){@>'
				+ '<@if(!_.isUndefined(item)){@>'
				+ '<option <@=item.id==config.defVal?"selected":""@> value="<@=item.id@>"><@=item[config.name]@></option>'
				+ '<@}@>'
				+ '<@});@>'
		),
		/**
		 * 渲染UNIT
		 * @param config
		 */
		render : function(config){
					
			if(config){
				this.config = _.extend(this.config, config);
				this.items = _.extend([],this.items);
			}
			this.$el.html(this.template({items:this.items,config: this.config}));
		},
		
		/**
		 * 获取当前UNIT 数据
		 */
		getValue:function(){
			return this.$el.val();
		},
		
		/**
		 * 清除UNIT 默认值
		 */
		clearDef:function(){
			this.config.defVal = null;
		},
		/**
		 * 开放设置下拉数据源
		 */
		setItems:function(its){
			this.items = its;
		}
	});
	return unit;
});