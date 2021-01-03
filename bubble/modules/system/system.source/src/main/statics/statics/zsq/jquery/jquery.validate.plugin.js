//计算包含中英文字符混合的字符串的长度
function getZhStrLength(str) {
	var totalLength = 0;
	if(!!str) {
		var list = str.split("");
		for(var i = 0; i < list.length; i++) {
			var s = list[i];
			if (s.match(/[\u0000-\u00ff]/g)) {
				//半角
			    totalLength += 1; 
			} else if (s.match(/[\u4e00-\u9fa5]/g)) {
				//中文  
			    totalLength += 2;
			} else if (s.match(/[\uff00-\uffff]/g)) {
				//全角 
			    totalLength += 2;
			}
		}
	}
	return totalLength;
}
/* 自定义验证规则 */
$(document).ready(function() {
	
	$.extend($.validator.messages, {
		required: "必选字段",
		remote: "请修正该字段",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: $.validator.format("请输入一个长度最多是 {0} 的字符串"),
		minlength: $.validator.format("请输入一个长度最少是 {0} 的字符串"),
		rangelength: $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: $.validator.format("请输入一个最大为 {0} 的值"),
		min: $.validator.format("请输入一个最小为 {0} 的值")
	});
	
	// 手机号码验证
	jQuery.validator.addMethod("isMobile", function(value, element){
		var length = value.length;
//		var mobileRex = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		var mobileRex = /^0?1[3|4|5|8]\d{9}$/;
		return this.optional(element) || (length == 11 && mobileRex.test(value));
	}, "手机号码不正确!");
	
	// 电话号码验证       
	jQuery.validator.addMethod("isTel", function(value, element){       
		var telRex = /^(0[0-9]{2,3}\-?)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;    //电话号码格式010-12345678   
		return this.optional(element) || (telRex.test(value));       
	}, "电话号码不正确(区号+电话)!");   
	jQuery.validator.addMethod("email", function(value, element){       
		var telRex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;    
		return this.optional(element) || (telRex.test(value));       
	}, "邮箱格式不正确!");   
	   
	   
	 // 联系电话(手机/电话皆可)验证   
	jQuery.validator.addMethod("isPhone", function(value,element){   
//		var phoneRex = /(0?1[3|4|5|8]\d{9})|^(0[0-9]{2,3}\-?)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
//		return this.optional(element) || (phoneRex.test(value));
		var length = value.length;
	    var mobile =  /^(1(([35][0-9])|(47)|[8][0126789]))\d{8}$/
	    return this.optional(element) || (length == 11 && mobile.test(value));
	 }, "电话号码不正确!");

	// 字符最大长度验证（一个中文字符长度为2）
	jQuery.validator.addMethod("ZhStrMaxLength", function(value, element, param){
		var length = getZhStrLength(value);
		return this.optional(element) || (length <= param);
	}, $.validator.format("字符个数大于{0}!"));

	// 字符最小长度验证（一个中文字符长度为2）
	jQuery.validator.addMethod("ZhStrMinLength", function(value, element, param){
		var length = getZhStrLength(value);
		return this.optional(element) || (length >= param);
	}, $.validator.format("字符个数小于{0}!"));
	
	// 字符长度区间验证（一个中文字符长度为2）不能在用class属性定义验证规则时使用,取不到区间的值
	jQuery.validator.addMethod("ZhStrRangeLength", function(value, element, param){       
		var length = getZhStrLength(value);
		return this.optional(element) || (length >= param[0] && length <= param[1]);       
	}, "输入的字符个数在{0}-{1}之间");
	// 字符验证       
	jQuery.validator.addMethod("strCheck", function(value, element){  
		return this.optional(element) || /^[\u4E00-\u9FA5a-zA-Z0-9_]+$/.test(value);       
	}, "只能包括中文字、英文字母、数字和下划线");

	// 字符验证       
	jQuery.validator.addMethod("strENCheck", function(value, element){       
		return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);       
	}, "只能包括英文字母、数字");   
	   
	 // 身份证号码验证       
	jQuery.validator.addMethod("isIdCardNo", function(value, element){
		var idCardNoRex_15 = /^[1-9]\d{7}((0[1-9])|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
		var idCardNoRex_18 = /^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}(x|X))$/;
		return this.optional(element) || idCardNoRex_15.test(value) || idCardNoRex_18.test(value);       
	}, "身份证号码不正确");
	      
	 // 邮政编码验证       
	jQuery.validator.addMethod("isZipCode", function(value, element){       
		var zipCodeRex = /^[0-9]{6}$/;       
		return this.optional(element) || (zipCodeRex.test(value));       
	}, "邮政编码不正确");
	
	// ip地址验证       
	jQuery.validator.addMethod("isIp", function(value, element){       
		var ipRex = /((?:(?:25[0-5]|2[0-4]\d|[01]?\d?\d)\.){3}(?:25[0-5]|2[0-4]\d|[01]?\d?\d))/;       
		return this.optional(element) || (ipRex.test(value));       
	}, "ip地址不正确");

    $.validator.addMethod("notnull", function (value, element) {
        if (!value) return true;
        return !$(element).hasClass("l-text-field-null");
    }, "不能为空");

    // 汉字
    jQuery.validator.addMethod("chcharacter", function (value, element) {
        var tel = /^[\u4e00-\u9fa5]+$/;
        return this.optional(element) || (tel.test(value));
    }, "请输入汉字");

    // QQ
    jQuery.validator.addMethod("qq", function (value, element) {
        var tel = /^[1-9][0-9]{4,}$/;
        return this.optional(element) || (tel.test(value));
    }, "请输入正确的QQ");
    // 请输入数字
//    jQuery.validator.addMethod("number", function (value, element) {
//        var tel = /^[0-9]\d*$/;
//        return this.optional(element) || (tel.test(value));
//    }, "请输入数字");

    // 用户名
    jQuery.validator.addMethod("username", function (value, element) {
        return this.optional(element) || /^[a-zA-Z][a-zA-Z0-9_]+$/.test(value);
    }, "用户名格式不正确");
    
    // 小于当前时间
    jQuery.validator.addMethod("ltCurDate", function (value, element) {
        var startdate = new Date((value).replace(/-/g,"/"));  
        var date = new Date();  
        if(startdate < date) 
        {
            return this.optional(element) || false;
        }
        else
        {
            return this.optional(element) || true;
        }
    }, "时间不能小于当前时间");
    
    //编号验证方法
    $.validator.addMethod("charNumber",function(value,element) {
        var length = value.length;
        var your_tel =/[\W]/g;
        return this.optional(element) || (length<=50&&!your_tel.test(value));   
    },"编号请输入英文字符或数字");
	
    //年龄验证方法
    $.validator.addMethod("isAge",function(value,element) {
        return this.optional(element) || (/^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value));   
    },"年龄必须是0到120之间的整数");
    
    // 覆盖自带的邮件验证
    $.validator.addMethod('email',function(value,element){
    	return this.optional(element) || (/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(value));
    },"邮件格式不正确");
    
    // 非负浮点数验证
    $.validator.addMethod('nonnegativeNumber',function(value,element){
        var length = 0;
        // 获取参数整数位位数
        if(value != null && value != ""){
            var indexVal = value.indexOf('\.');
            var num = null;
            if(indexVal != -1){
                var strArr = value.split('.');
                num = strArr[0];
            } else {
                num = value;
            }
            length = num.length;
        }
        return this.optional(element) || (length <= 7 && (/^([1-9]\d*|\d+\.\d+|0?)$/.test(value)));
    },"输入的值只能为非负数且整数位不能超过7位");
	
	// APP DEFALUT  WITH bootstrap3.0 DOM structure
		
	jQuery.validator.setDefaults({
		errorClass:'has-error',
		highlight:function(element,errorClass){
			$($(element).parents('.form-group')[0]).addClass(errorClass);
			
		},
		unhighlight: function(element, errorClass, validClass) {
			var item = $($(element).parents('.form-group')[0]);
			item.removeClass(errorClass).addClass(validClass);
		},
		errorPlacement : function(error, element) {
			error.appendTo($(element).parent());
		}
	});
	
});


