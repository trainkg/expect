<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/jspTagLibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/WEB-INF/common/indexHttpHeader.jsp"%>
<link href="${_CP}/statics/zsq/ztree/css/zTreeStyle/zTreeStyle.css"
	rel="stylesheet">
<script type="text/javascript" src="${_CP}/statics/zsq/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${_CP}/statics/zsq/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript">
	require(["logMgr"], function(ZSQ) {
		new ZSQ.router.logMgr();
	}); 
</script>
<style>
.form-group .col-label{
	width:100px;
	vertical-align: top;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/common/body-header.jsp"%>
	<div class="g-bd clearfix" id="content">
		<div>
			<div class="g-sd">
				<%@include file="/WEB-INF/common/leftNav.jsp"%>
			</div>
			<div class="g-mn">
				<div class="g-mnc container-fluid">
					<div class="row m-section">
						<div class="col-md-12">
							<div class="nav nav-pill">
								<span>当前位置：</span> <span>系统管理 <i class="fa fa-angle-right"></i></span> <span>日志查看 <i class="fa fa-angle-right"></i></span>
							</div>
						</div>
					</div>
					<div class='row'>
						<div class="col-md-2">
							<label class="control-label lab" for="">模块名：</label>
							<select class="form-control businessTypeAid" name="businessTypeAid">
				          </select>
						</div>
						<div class="col-md-2">
							<label class="control-label lab" for="">操作类型：</label>
							<select class="form-control a-base operationTypeAid" name="operationTypeAid">
				          		
				          	</select>
						</div>
						<div class="col-md-2">
							<label class="control-label lab">开始时间：</label>
							<div class="input-group">
								<input type="text"  value="" readonly="readonly" id="fromDate" class="form-control" name="fromDate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'toDate\')}'})">
							</div>
						</div>
						<div class="col-md-2">
							<label class="control-label lab">结束时间：</label>
							<div class="input-group">
								<input type="text" value="" readonly="readonly" id="toDate" class="form-control" name="toDate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'fromDate\')}'})">
							</div>
						</div>
						<div class="col-md-1">
						<label class="control-label lab" >&nbsp;&nbsp;</label>
						<a class="btn btn-primary form-control" id="searchInputBtn">查询</a>
						</div>
					</div>
					<div class="row">
						<div class="a-table col-md-12">
							<table class="table table-bordered"
								style="background-color: white; margin-top: 5px" id="logDataTable">

							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="perImport_model"></div>
	<div id="write_model"></div>
	<div id="user_pick"></div>
	<div id="showLogDetail_model"></div>
	<%@include file="/WEB-INF/common/index-footer.jsp"%>
</body>