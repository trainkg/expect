
/**
 * 该JS 定义了所有的 系统所有的AJAX请求链接 
 * 链接定义 
 * URL.a = '';
 * 功能定义
 * URL.fa = function(){...}
 */
define("cq",["zseed" ,'backbone', 'underscore'],function(seed, Backbone, _) {
	/*
	 * 系统请求默认配置
	 */
	var default_option = {
		async: false,
		dataType:'json',
		type:'post',
		data:{}
	};
	
	
	// 首字母大写正则
	var wordReg = /\b(\w)|\s(\w)/g;
	
	
	// 内部请求处理
	function internalRequest(url, params, success, async){
		var option = {
			'url':_ZSQ.cp + url,
			'success':success
		};
		if(params){
			option.data = params;
		}
		if(async){
			option.async = async;
		}
		option = _.extend({}, default_option, option);
		Backbone.$.ajax(option);
	}
	
	// CRM请求列表
	var URL = {};
	/*获取当前登录用户信息*/
	URL.getCurUser = '/base/getCurUser';
	//根据id获取角色信息
	URL.getUserRole = '/base/getUserRole';
	//获取指标信息
	URL.getNomInfoList = '/nomInfo/getNomInfos';
	
	//获取人员范围信息
	URL.searchDS = '/nomInfo/search-DS';
	//维护人员范围（新增）
	URL.maintDS = '/nomInfo/maint-DS';
	//删除人员范围
	URL.delDS = '/nomInfo/del-DS';
	//获取人员范围
	URL.findDSOrg= '/nomInfo/find-DS';
	//导入人员信息
	URL.importDS= '/excelController/importDs';
	//获取所有部门
	URL.getAllOrg = '/pfu/get-all-orgs';
	//创建一个部门
	URL.createOrg ='/pfu/create-org';
	//维护部门信息
	URL.updateOrg ='/pfu/maint-org';
	//删除部门
	URL.delOrg ='/pfu/del-org';
	//根据id获取部门信息
	URL.getOrgById='/pfu/get-one-org';
	//获取人员信息列表
	URL.getUserList='/pfu/getUsers';
	//获取人员信息列表
	URL.getUserListScope='/nomInfo/getUsers';
	
	//重置密码服务
	URL.resetPwd="/pfu/resetPwd";
	//修改密码服务
	URL.updatePassword="/pfu/updatePassword";
	//创建人员
	URL.createUser='/pfu/addUser';
	//删除人员
	URL.removeUser='/pfu/delUsers';
	//根据userId获取user信息
	URL.getUserInfoById='/pfu/getUserInfoById';
	//维护人员信息
	URL.updateUser='/pfu/maintUser';
	//获取所有的角色
	URL.getRoles='/pfu/getAllRoles';
	//批量导入
	URL.importUsers='/pfu/importUsers';
	//获取所有基础数据
	URL.getAllBaseData='/base/getAllBaseData';
	//获取所有月度基础数据
	URL.getAllMonBaseData='/base/getAllMonthBaseData';
	//维护基础数据时间
	URL.maintBaseData='/base/maintBaseData';
	//维护基础数据时间
	URL.getOneInfo='/base/getOne';
	//折算周期
	URL.maintPer="/base/maintPer";
	//获取配置
	URL.getConfig="/base/getConfig";
	//获取考核周期
	URL.getAllKhzq="/base/getAllKhzq";
	//维护配置
	URL.mainSystemConfig="/base/mainSystemConfig";
	//创建指标
	URL.createDelivery="/nomInfo/createNomDelivery";
    //查询详细的指标项信息
	URL.searchDelivery="/nomInfo/nomdelivery/info";
	//审核指标
	URL.auditNom="/nomInfo/auditNom";
	//获取审核详情
	URL.getNom="/nomInfo/getNom";
	//删除指标
	URL.deleteNomDelivery="/nomInfo/deleteNomDelivery";
	//更新指标信息
	URL.updateDelivery="/nomInfo/updateNomDelivery";
	//提交指标
	URL.submit="/nomInfo/submit";
	//指标审核
	URL.getNomVerify="/nomInfo/getNomVerify";
	//指标审核
	URL.getNomVeriryProxy = "/nomInfo/getNomVerifyProxy";
	//批量导入指标
	URL.batchNomDeliveries="/nomInfo/addBatchNomDeliveries";
	//获取指标详情
	URL.getNvInfo="/nomInfo/getNvInfo";
	//获取指标特殊审批人
	URL.getSpeailVerify="/nomInfo/getSpeailVerify";
	
	/*
	 * 上传绩效数据页面的Ajax接口
	 */
	
	//获取绩效考核页面加载数据
	URL.getPerformanceList="/nomInfo/getPerformanceList";
	//获取绩效考核页面加载数据
	URL.getPerformanceListProxy="/nomInfo/getPerformanceListProxy";
	
	//批量上传绩效考核数据
	URL.importPerformanceData="/nomInfo/importKhData";
	//根据id获取绩效数据
	URL.getOnePerformance="/nomInfo/getPerformance";
	//填写绩效数据--提交
	URL.submitPerformance="/nomInfo/writeNomDev";
	//获取绩效查询数据
	URL.getJxkhList="/nomInfo/doSearchJxkh";
	//获取绩效进度查询
	URL.getJxkhProgress="/nomInfo/doSearchJxkhProgress";
	//导入修正数据
	URL.importRepairData="/excelController/importRds";
	//获取指标
	URL.getNomDelivery = "/nomInfo/getNomDelivery";
	//删除指标评审人
	URL.delMasters = "/nomInfo/delMasters";
	//添加指标评审人
	URL.addMasters = "/nomInfo/addMasters";
	//维护指标评审人
	URL.maintMasters = "/nomInfo/maintMasters";
	//更新数据上报人
	URL.maintUploader = "/nomInfo/maintUploader";
	//改变评审人/达成值
	URL.changeKhMaster = "/nomInfo/changeMaster";
	//评估
	URL.evalScore = "/nomInfo/evalscore";
	
	/*以上是上传绩效模块定义的Ajax接口*/

	/*转岗AJAX请求URL*/
	URL.getAdjustPositionRecordList = "/adjustPosition/getAdjustPositionRecordList";
	//新增转岗记录
	URL.createAdjustPositionRecord = "/adjustPosition/create";
	//根据ID，查找转岗记录
	URL.findRecord = "/adjustPosition/find";
	//修改转岗记录
	URL.updateRecord = "/adjustPosition/update";
	
	//委派
	//获取委派列表
	URL.getProxyTableData = "/proxy/getProxyTableData";
	URL.saveProxy = "/proxy/saveProxy";
	URL.updateProxy = "/proxy/updateProxy";
	URL.findById = "/proxy/find";
	URL.closeProxy = "/proxy/closeProxy";
	URL.getPgTaskCount = "/proxy/getPgTaskCount";
	URL.delProxy = "/proxy/del";
	
	/*指标库AJAX请求URL*/
	URL.getTemplates = "/template/getTemplates";
	//创建指标模板
	URL.createTemplate="/template/create";
	//修改指标模板
	URL.updateTemplate="/template/update";
	//删除指标模板
	URL.deleteTemplate="/template/del";
	//根据模板id，获取关联的级别
	URL.findTemplateLevel = "/template/findTemplateLevel";
	
	//根据一级id，获取关联的所有二级级别
	URL.findSecondTemplateLevel = "/template/findSecondTemplateLevel"
		
	//修改指标模板应用范围
	URL.updateTemplateScope="/template/updateScope";
	
	//获取级别信息列表
	URL.getLevelList='/template/getLevelsScope';
		
	//删除级别
	URL.removeLevel='/template/deleteLevel';
	
	//删除二级级别
	URL.removeSecondLevel='/template/deleteSecondLevel';
	
	//创建级别
	URL.createLevel="/template/createLevel";
	//修改级别
	URL.updateLevel="/template/updateLevel";
	
	//根据id获取级别
	URL.findLevel = "/template/findLevel";
	
	/*指标库AJAX请求URL（需要分页）*/
	URL.getTemplatesNeedPage = "/template/getTemplatesNeedPage";

	/*上传承诺书模板URL*/
	URL.uploadPromiseTemp = "/promiseReport/upload";
	/*删除承诺书模板URL*/
	URL.deletePromiseTemp = "/promiseReport/delete";
	
	//根据部门id获取部门指标模板权限设定
	URL.getOrgNomDetail = "/template/findOrgNomDetail";
	
	//创建部门指标模板权限设定
	URL.createONDetail = "/template/createOrgNomDetail";

	//修改部门指标模板权限设定
	URL.updateONDetail = "/template/updateOrgNomDetail";
	
	//接收合同
	URL.rceiveHt="/nomInfo/rceiveHt";
	
	//撤销合同接收
	URL.resetHt="/nomInfo/resetHt";

	//获取日志表格数据
	URL.getLogTableData = "/logger/getTable";
	//获取日志模块类型
	URL.getAllBusType = "/logger/allBusType";
	//获取操作类型
	URL.getAllOpType = "/logger/allOpType";
	
	//保存交接的设置
	URL.saveHandOver = "/nomInfo/saveHandOver";

	//批量导入转岗记录
	URL.batchImportAdjust = "/excelController/importAdjust";

	//删除未生效的转岗记录
	URL.delAdjustPoition = '/adjustPosition/delNoAction';

	URL.getAdjustPerURL = "/adjustPosition/getAdjustPer";
	
	URL.setCheckHandOver = "/nomInfo/setCheckHandOver";
	
	//下载所有未生效的转岗记录
	URL.downloadUnActivationAdjust = "/excelController/downloadUnActivationAdjust";

	/*
	 * end
	 */
	_.each(_.keys(URL),function(name){
		var url = URL[name];
		if(_.isString(url)){
		   var bigName = name.replace(wordReg,function(word){
			  return word.substring(0,1).toUpperCase()+word.substring(1);}
			);
			URL['f'+bigName] = function(params, success, async){
				internalRequest(url, params, success, async);
			};
		}
	});
	return URL;
});