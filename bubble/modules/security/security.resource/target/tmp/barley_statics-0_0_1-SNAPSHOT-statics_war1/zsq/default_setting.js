/**
 * 系统相关的默认设置
 */
define('sds',['dataTables','backbone','underscore'],function(jQuery,Backbone,_){
	
	//underscore 模板脚本处理
	_.templateSettings = {
	    interpolate: /\<\@\=(.+?)\@\>/gim,
	    evaluate: /\<\@(.+?)\@\>/gim,
	    escape: /\<\@\-(.+?)\@\>/gim
	};
	
	//datatable defalutsetting
	
	jQuery(function(){
		jQuery.extend(jQuery.fn.dataTable.defaults, {
		    "searching": false,
		    "ordering": false,
		    'lengthChange':false,
		    "processing": false,
		    'serverSide':true,
	        'pageLength':20,
	        "autoWidth": false,
	        //"stateSave":true,
	        "language": {
	        	'emptyTable':'没有数据',
	            "lengthMenu": "Display _MENU_ records per page",
	            "zeroRecords": "记录为空",
	            "info": "显示第  _PAGE_ 页,总页数  _PAGES_ 页",
	            "infoEmpty": "记录为空",
	            "infoFiltered": "(filtered from _MAX_ total records)",
	            "paginate":{
	            	 "first":      "第一页",
	                 "last":       "最后一页",
	                 "next":       "下一页",
	                 "previous":   "上一页"
	            }
	        }
		});
	});
});