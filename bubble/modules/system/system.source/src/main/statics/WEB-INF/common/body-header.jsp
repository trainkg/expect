<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	require(["jquery",'editPasswd',"bootstrap"],function($,seed){
	    //view context
	    var vc = seed.ui.view;
	    $(function(){
            $('#editUserPasswd').click(function(){
                var updatePwdView = new vc.UpdatePwdView({el:'#editPasswd-model', context : {
                }});
                updatePwdView.render();
                updatePwdView.$el.show();
            });
	    });
	});
</script>
<div class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div>
      <!-- <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button> -->
      <a class="navbar-brand" href="${_CP}">曙光绩效管理系统</a>
    </div>
  	<div>
	   <%--  <div class="dropdown pull-right" style="margin-top:12px;">
		  <button id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		       用户设置
		   <span class="caret"></span>
		  </button>
		  <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
            <li class="light-blue">
                <a href="javascript:;" id="editUserPasswd"><span class="userinfo">修改密码</span></a>
            </li>
             <li class="light-blue">
                <a href="${_CP}/logout"><span class="userinfo">退出登录</span></a>
            </li>
		  </ul>
		</div> --%>
		<div class="dropdown pull-right" style="margin-top:12px;">
			<a href="${_CP}/logout" class="btn btn-xs btn-success"><span class="userinfo">退出登录</span></a>
		</div>
		<div class="pull-right" style="color:white;margin-top: 12px;margin-right: 10px;">
  			当前登陆人员：${E_C_USER.orgs} - ${E_C_USER.userDetail.userName}
  		</div>
    </div>
  </div><!-- /.container-fluid -->
</div>
<!-- 修改密码 -->
<div id="editPasswd-model"></div>
<!-- 修改密码结束 -->
