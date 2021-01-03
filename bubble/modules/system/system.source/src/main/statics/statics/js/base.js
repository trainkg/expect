//var _ZSQ = {cp:'/steering',tokenId:'_USER_ID_'};
require.config({
	baseUrl : _ZSQ.cp + "/statics",
	waitSeconds:60,
	urlArgs: 'v.1.0.2',
	paths : {
		//'keditor':'kindeditor/kindeditor-all',
		//'keditor-zh':'kindeditor/lang/zh_CN',
		//"nicescroll":"jquery/jquery.nicescroll.min",
        //"qTip2": "qtip2/jquery.qtip.min",
        'bootstrap':'zsq/bootstrap/js/bootstrap.min',
		//ZSEED
        'zseed':'zsq/seed',
        'ui-unit':'zsq/ui-unit',
        'zevent':'zsq/zevent',
        'jquery-validate':'zsq/jquery/jquery.validate',
        'jquery-validate-extend':'zsq/jquery/jquery.validate.plugin',
        'jquery-multiselect-zh':'zsq/jquery/jquery.multiselect.zh-cn',
        'jquery-multiselect':'zsq/jquery/jquery.multiselect.min',
		'jquery' : 'zsq/jquery/jquery.min',
		'backbone' : 'zsq/backbone/backbone1.1.2',
		'underscore' : 'zsq/backbone/underscore.16.0',
		'text':'zsq/backbone/text',
		'domReady':'zsq/backbone/domReady',
		'form':'zsq/jquery/jquery.form',
		'dataTablesR':'zsq/dataTable/js/jquery.dataTables.min',
        'dataTables':'zsq/dataTable/js/datatable.bootstrap3',
        'ztree':'zsq/ztree/js/jquery.ztree.all-3.5.min',
        'md5':'zsq/jquery/jQuery.md5',
        'uploadify':'zsq/uploadify/jquery.uploadify.min',
		'dhtml':'zsq/codebase/dhtmlxscheduler',
		'dhtmlRecurring':'zsq/codebase/ext/dhtmlxscheduler_recurring',
		'jquery-ui':'zsq/jquery/jquery-ui-1.9.2.custom.min',
		'sds':'zsq/default_setting',
		'cq':'zsq/request_url',
		'win':'zsq/window',
		'zmsg':'zsq/zsq-message',
		'echarts':'zsq/echart/echarts',
		'echarts/chart/line':'zsq/echart/echarts',
		'pdfobject':'zsq/pdf/pdfobject',
		'curUser':'js/curUser',
		//application
		'mutil-check':'js/mutil-check',
		'coin-item-list':'js/coin-item-list',
		'user-check':'js/user-check',
		'batchImportFile':'js/batchImportFile',
		'uploadFile':'js/uploadFile',
		'plupload':'zsq/plupload-2.1.2/js/plupload.full.min',
		'editPasswd':'js/editPasswd',
		'resetPwd':'js/resetPassword',
		'orgMgr':'js/org',
		'userMgr':'js/user',
		'supersized':'js/supersized.min',
		'supersized-init':'js/supersized-init',
		'baseData':'js/baseData',
		'contract':'js/contract',
		'uploadImg':'js/uploadImg',
		'nomVerifyMgr':'js/nomVerify',
		'verifyViewMgr':'js/userNomVerify',
		'deliveryImport':'js/delieryImport',
		'department':'js/department',
		'preformanceMgr':'js/performance',
		'scheduleMgr':'js/schedule',
		'perResultMgr':'js/perResult',
		'dataScope':'js/dataScope',
		'addDataScopeMgr':'js/addDataScope',
		'adjustPositionMgr':'js/adjustPositionRecord',
		'proxyJS' : 'js/proxyIndex',
		'templateMgr':'js/nomDeliveryTemplate',
		'levelMgr':'js/level',
		'templatePickerMgr':'js/template',
		'orgNomDetailMgr':'js/orgNomDetail',
		'logMgr' : 'js/log',
		'handOverMgr' : 'js/handOver'
	},
	shim : {
		"nicescroll":{
			deps:["jquery"],
			exports:"jQuery"
		},
		'backbone' : {
			deps : [ 'underscore', 'jquery' ],
			exports : 'Backbone'
		},
		'underscore' : {
			exports : '_'
		},
		'form' : {
			deps:['jquery'],
			exports : 'jQuery'
		},
		/*'keditor':{
			deps:['jquery','keditor-zh'],
			exports :'keditor'
		},*/
		'jquery-validate':{
			deps:['jquery'],
			exports:'jQuery'
		},
		'jquery-validate-extend':{
			deps:['jquery','jquery-validate'],
			exports:'jQuery'
		},
		'bootstrap':{
			deps:['jquery'],
			exports:'bootstrap'
		},
		'ztree':{
			deps:['jquery'],
			exports:'jQuery'
		},
		'dataTablesR':{
			deps:['jquery'],
			exports:'jQuery'
		},
		'dataTables':{
			deps:['dataTablesR'],
			exports:'jQuery'
		},
		'md5':{
			deps:['jquery'],
			exports:'jQuery'
		},
		'dhtml':{
			deps:['jquery'],
			exports:'jQuery'
		},
		'dhtmlRecurring':{
			deps:['dhtml'],
			exports:'scheduler'
		},
        'jquery-multiselect':{
        	deps:['jquery-ui'],
        	exports:'jQuery'
        },
        'jquery-multiselect-zh':{
			deps:['jquery-multiselect'],
			exports:'jQuery'
		},
		'jquery-ui':{
			deps:['jquery'],
			exports:'jQuery'
		},
		'plupload':{
			exports:'plupload'
		}
	}
});
