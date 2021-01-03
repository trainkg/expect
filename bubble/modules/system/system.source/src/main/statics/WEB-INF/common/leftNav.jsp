<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div class="navbar-default sidebar">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav nav-list">
			<li><a href="javascript:;"> <span>考核指标</span></a>
				<ul class="nav nav-second-level">
					<li><a href="${_CP}/nom/info">考核表签订</a></li>
					<li><a href="${_CP}/nom/nomVerify">考核表审核</a></li>
					<li><a href="${_CP}/nom/contract">考核表查询</a></li>
				</ul>
			</li>
			<li><a href="javascript:;"> <span>绩效评估</span></a>
				<ul class="nav nav-second-level">
					<li><a href="${_CP}/performance/go-p"><span>绩效评估打分</span></a></li>
					<li><a href="${_CP}/performance/go-r"><span>打分进度查询</span></a></li>
					<li><a href="${_CP}/performance/go-s"><span>考核结果查询</span></a></li>
				</ul>
			</li>
			
				<li><a href="javascript:;"> <span>系统功能</span><i
					class="fa fa-line-down"></i></a>
					<ul class="nav nav-second-level">
					<shiro:hasAnyRoles name="2,9,6">
						<shiro:hasAnyRoles name="2,9">
							<li><a href="${_CP}/template/goTemplate"><span>指标库管理</span></a></li>
							<li><a href="${_CP}/sys/goOrg"><span>人员组织管理</span></a></li>
						</shiro:hasAnyRoles>
						<shiro:hasAnyRoles name="2,9,6">
							<li><a href="${_CP}/template/goOrgNomSet"><span>部门设定</span></a></li>
						</shiro:hasAnyRoles>
						<shiro:hasAnyRoles name="2,9">
							<li><a href="${_CP}/sys/goDataScope"><span>角色范围管理</span></a></li>
							<li><a href="${_CP}/sys/goJustPosition"><span>人员转岗管理</span></a></li>
							<li><a href="${_CP}/sys/goBaseData"><span>考核配置</span></a></li>
							<li><a href="${_CP}/sys/goLogPage"><span>日志查看</span></a></li>
						</shiro:hasAnyRoles>
						</shiro:hasAnyRoles>
						<li><a href="${_CP}/sys/goProxyIndex"><span>委派管理</span></a></li>
					</ul>
				</li>
			
		</ul>
	</div>
</div>