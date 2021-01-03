/**
 * 系统消息提示
 * @author zhuyy 
 */
define("zmsg",['jquery','underscore','zevent'],function($,_,utils) {
	var zmsg_config = {
		'select':'#_stip :hidden',
		'tipDom':'<div id="_ZTMSG">${MSG}</div>'
 	}
	//# JQUERY 
	var msgTarget = $(zmsg_config.select);
	
	if(!msgTarget && console){
		console.log('not fount message target');
		return null;
	}
	/**
	 * 显示消息
	 */
	function showMsg(autoClose){
		if(_.isUndefined(autoClose)){
			autoClose = true;
		}
		var message = getMsg();
		if(_.isString(message)){
			showStrMessage(message);
			autoClose = true;
		}else if(_.isArray(message)){
			showArrayMessage(message,autoClose);
		}else{
			//throw new Error("invalid message data");
		}
	}
	
	/**
	 * 显示数组类别的信息  用于大量信息
	 */
	function showArrayMessage(message,autoClose){
		if(message.length > 0){
			require(['text!zsq/template/tip-message.html'],function(msgtml){
				var ftl = _.template(msgtml);
				var old = $("#_ZTMSG");
				if(old){
					old.remove();
				}
				$('body').append(ftl({'data':message}));
				utils.initWinEvent($('body').find(".zsq-msg-model"));
				if(autoClose){
					$('#_ZTMSG').fadeIn(0,function(){
						setTimeout(function(){
							$('#_ZTMSG').fadeOut();
						}, 2000)	
					});
				}
			})
		}
	}
	
	/**
	 * 显示简单STRING 类型的提示信息
	 */
	function showStrMessage(message){
		if(message && '' != $.trim(message)){
			var html = zmsg_config.tipDom.replace('${MSG}',message)
			var old = $("#_ZTMSG");
			if(old){
				old.remove();
			}
			$('body').append(html);
			var css = "position: fixed;display:none;top: 4px;margin: 0 auto;left: 50%;height: 36px;width: 600px;margin-left: -300px;background: #041052;" +
					"line-height: 36px;color:white;font-size: 14px;text-align: center;border-radius: 4px;z-index: 9999;";
			$('#_ZTMSG').attr('style',css);
			$('#_ZTMSG').fadeIn(0,function(){
				setTimeout(function(){
					$('#_ZTMSG').fadeOut();
				}, 2000)	
			});
		}
	}
	
//	
	/**
	 * 获取消息信息
	 */
	function getMsg(){
		return zmsg_config.data || msgTarget.val();
	}
	
	
	// EVENT 
	$(zmsg_config.select).bind('change.zsq.api',function(event){
		showMsg();
	})
	
	/**
	 * 发送消息接口 JS 
	 * autoClose 是否自动关闭
	 */
	function sendMsg(msg,autoClose){
		zmsg_config.data = msg;
		showMsg(autoClose);
	}
	
	
	// 初始化  
	if($.trim(getMsg()) != ''){
		showMsg();
	}
	
	return sendMsg;
});