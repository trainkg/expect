/**
 * $ 2014-8-12 事件响应库 mode;
 */
define("zevent", ['backbone', 'underscore'], function(Backbone, _) {
  var utils = {};

  /**
   * window 页面关闭事件支持, 提供hook:{before,after}
   * utils.initWinEvent("#select",{before:function(){alert('before
   * hide')},after:function(){'do after'}});
   */
  utils.initWinEvent = function($el, hook) {
    var hook = _.extend({}, hook);
    $el.find(".a-close").bind("click.jquery", function() {
      var before = hook.before;
      if (before != undefined && _.isFunction(before)) {
        before($el);
      }
      $el.hide();
      var after = hook.after;
      if (after != undefined && _.isFunction(after)) {
        after($el);
      }
    });
  };

  /**
   * 选项卡事件监听 满足标准结构 .a-table > .a-item[data-index] & .tab-pane[data-index]
   * active
   *
   * 开放afterChange(event) 钩子
   */
  utils.initTableEvent = function($el, config) {
    // init
    var default_config = {
      cur: 0
    };
    var config = _.extend(default_config, config);
    var contents = $el.find(".a-table .tab-pane");
    var items = $el.find(".a-table .a-item");
    contents.hide();
    items.removeClass("active");
    $el.find(".a-table .a-item[data-index=" + config.cur + "]").addClass("active");
    $el.find(".a-table .tab-pane[data-index=" + config.cur + "]").show();
    // event
    $el.find(".a-table .a-item").bind("click", function(event) {
	  if (_.isFunction(config.beforeChange)) {
        config.beforeChange(event);
      }
      var index = $(this).data("index");
      items.removeClass("active");
      $(this).addClass("active");
      contents.hide();
      $el.find(".a-table .tab-pane[data-index=" + index + "]").show();
      if (_.isFunction(config.afterChange)) {
        config.afterChange(event);
      }
    });
  }

  /**
   * 单击一行 实现该行checkbook选择或不选择
   *
   * 行标示 class="a-row"
   */
  utils.keepCheckbox = function($el) {
    $el.find(".a-row a").bind('click', function(event) {
      event.stopPropagation();
    });
    var isMsie = utils.isMsie();
    $el.find(".a-row").on('click', function(event) {
      var srcType = $(event.srcElement).attr('type');
      if (srcType != 'checkbox' && (!isMsie || $el.find(".a-row label").size() == 0)) {
        var checkVal = $(this).find("input:checkbox").attr('checked');
        if (checkVal == 'checked' || checkVal == 'true' || checkVal == true) {
          $(this).find("input:checkbox").removeAttr('checked');
        } else {
          $(this).find("input:checkbox").attr({
            'checked': 'checked'
          });
        }
      }
    });
  };
  /**
   * 点击表格头部的checkebox选中所有的checkbox
   * 点击tbody中每行的checkbox的选中状态
   *
   */

  utils.selectAllcheckbox = function(el) {
    $(el).on("click.zsq.api", "thead input[type=checkbox]", function(event) {
      event.stopPropagation();
      if ($(this).attr("checked") == "checked") {
    	  $(el).find('tbody').find('input[type=checkbox]').each(function() {
          this.checked = true;
        })
      } else {
    	  $(el).find('tbody').find('input[type=checkbox]').each(function() {
          this.checked = false;
        })
      }
    });
    $(el).on("click.zsq.api", "tbody input[type=checkbox]", function(event) {
       event.stopPropagation();
      if ($(this).attr("checked") == "checked") {
        var checkAllFag = true;
       $(el).find('tbody').find('input[type=checkbox]').each(function() {
          if ($(this).attr("checked") == "checked") {

          } else {
            checkAllFag = false;
          }
        });
        if (checkAllFag) {
          $(el).find('thead').find('input[type=checkbox]').attr("checked", "checked")
        } else {
          $(el).find('thead').find('input[type=checkbox]').attr("checked", false)
        }
      } else {
       $(el).find('thead').find('input[type=checkbox]').attr("checked", false)
      }
    });
  };

  /**
   * 是否是IE JQ 1.8(包含) 前可以使用
   */
  utils.isMsie = function() {
    return !!navigator.userAgent.match(/Trident\//);
  };

  /**
   * 获取指定区域所有checkbox 的选中值 返回选中值数组
   */
  utils.getCheckedValue = function($el) {
    var retValue = [];
    var group = $el.find("input:checked");
    group.each(function(ind, element) {
      var value = Backbone.$(element).val();
      retValue.push(value);
    });
    return retValue;
  };

  /**
   * 创建cookie
   */
  utils.createCookie = function(name, value, days) {
    if (days) {
      var date = new Date();
      date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
      var expires = "; expires=" + date.toGMTString();
    } else var expires = "";
    document.cookie = name + "=" + value + expires + "; path=/";
  };

  /**
   * 读取Cookie
   */
  utils.readCookie = function(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') c = c.substring(1, c.length);
      if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
  };

  /**
   * 删除cookie
   */
  utils.eraseCookie = function(name) {
    utils.createCookie(name, "", -1);
  };
  
  /**
   * 判断参数是否为空，为空返回TRUE
   */
  utils.isEmpty = function(value){
      if(value == '' || value == null || typeof value == 'undefined'){
          return true;
      } else{
          return false;
      }
  }

  /**
   * 将$form 的数据进行JSON 化
   */
  utils.serializeJson = function($from){
	  var serializeObj={};  
      var array=$from.serializeArray();  
      var str=$from.serialize();  
      Backbone.$(array).each(function(){  
          if(serializeObj[this.name]){  
              if(Backbone.$.isArray(serializeObj[this.name])){  
                  serializeObj[this.name].push(this.value);  
              }else{  
                  serializeObj[this.name]=[serializeObj[this.name],this.value];  
              }  
          }else{  
              serializeObj[this.name]=this.value;   
          }  
      });  
      return serializeObj;  
  }
  
  /**
   * 对象的深度克隆支持
   */
  utils.cloneObj = function(obj){
	    var str, newobj = obj.constructor === Array ? [] : {};
	    if(typeof obj !== 'object'){
	        return;
	    } else if(window.JSON){
	        str = JSON.stringify(obj), 
	        newobj = JSON.parse(str);
	    } else {
	        for(var i in obj){
	            newobj[i] = typeof obj[i] === 'object' ? 
	            cloneObj(obj[i]) : obj[i]; 
	        }
	    }
	    return newobj;
	};
	
  /**
   * 更新SELECT下拉选项
   *
   * config:{label['显示名称'] | key['值']}
   */
   utils.update_select_options = function(select, options, config) { // helper function
		var config = _.extend({'label':'name','key':'id'}, config);
		select.options.length = 0;
		for (var i=0; i<options.length; i++) {
			var option = options[i];
			select[i] = new Option(option[config.label], option[config.key]);
		}
	};

  return utils;
});