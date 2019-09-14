/**
 * $ 2014-8-12 ZSQ SEED 库
 * @author zhuyy
 */
define("zseed",['zmsg','backbone', 'underscore','ui-unit',"zevent",'sds'],function(message,Backbone, _,unit, event) {
	
	// ZSQ SEED
	var ZSQ = {
		event:{},
		ui:{},
		router:{},
		data:{},
		statics:{
			//model 打开状态
			status_create:0,
			// 编辑状态
			status_edit:1,
			// 浏览状态
			status_view:2
		}
	}; 
	window.ZSQ = ZSQ; // window hook
	
	// UNIT
	ZSQ.ui.unit = _.extend({},unit);
	// model 级别VIEW
	ZSQ.ui.model = {};
	// view
	ZSQ.ui.view = {};
	// EVENT
	ZSQ.utils = _.extend({},event);
	
	// message
	ZSQ.utils.sendMsg = message;
	// router
	ZSQ.router = {};
	// data model
	ZSQ.data.model = {};
	
	//JQuery.ajax setup
	/*$(document).ajaxSuccess(function(event, xhr, settings){
		var r = $.parseJSON(xhr.responseText);
		var url  = document.location.href;
		if(r.data+'' == '0'){
			if(url.indexOf('main.jsp') != -1){
				window.location.href = BA.bp+'/index.jsp';
			}else{
				window.location.href = BA.bp+'/';
			}
		}
	}).ajaxStop(function(){
	}).ajaxComplete(function(){
	}).ajaxStart(function(){
	});*/
	return ZSQ;
});