<!DOCTYPE html>
<html lang="en">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/jspTagLibs.jsp"%>
<title>麦芒大战气泡</title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript">
	var _ZSQ = {
		cp : '',
		tokenId : '_USER_ID_'
	}
</script>
<link rel="shortcut icon" href="/favicons.ico" type=image/x-icon>
<link href="/statics/css/bootstrap.min.css" rel="stylesheet">
<link href="/statics/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="/statics/css/ace.min.css" rel="stylesheet">
<link href="/statics/css/function.css" rel="stylesheet">
<link href="/statics/css/main.css" rel="stylesheet">
<script type="text/javascript"
	src="/statics/zsq/bootstrap/js/respond.src.js"></script>
<script type="text/javascript" src="/statics/zsq/backbone/r.js"></script>
<script type="text/javascript" src="/statics/js/base.js"></script>
<style type="text/css">
html, body {
	overflow: hidden;
}

body {
	/* 背景图片铺满 IE8及以下的还不支持  */
	background-image: url('/statics/images/backgrounds/bg.jpg');
	-moz-background-size: cover;
	-webkit-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

.login-box {
	background-image: url('/statics/images/1.png');
	background-color: transparent !important;
}

.login-box .toolbar {
	background: transparent;
}

.widget-body {
	background: transparent;
	background-color: transparent;
}

.widget-main {
	background: transparent !important;
}

.login-box .toolbar {
	border-top-color: #D5E3EF !important;
}

.bottom_footer {
	position: fixed;
	width: 100%;
	bottom: 10px;
	color: #fff;
	font-size: 12px;
	text-align: center;
}
</style>
<script type="text/javascript">
	require([ 'zmsg', 'jquery', 'jquery-validate-extend' ],
			function(message, $) {

				var $form = $('form');
				//validate
				var validOption = {
					rules : {
						username : {
							required : true
						},
						password : {
							required : true
						}
					},
					messages : {
						username : {
							required : "请填写用户名"
						},
						password : {
							required : "请填写密码"
						}
					}
				};
				$form.validate(validOption);
			});
</script>
</head>
<body class="login-layout" style="height: 500px;">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="space-6"></div>
						<div class="position-relative">
							<form action="/login" method="post">
								<div id="login-box"
									class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main" style="padding: 36px;">
											<h2 class="header blue lighter bigger"
												style="color: black !important; font-weight: bolder; margin-bottom: 25px; margin-top: 5px; line-height: 34px; border-width: 2px;">
												<!-- <img src="/statics/img/logo.png" > -->
												<i class="icon-coffee green"></i> 麦芒大战气泡
											</h2>
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" name="username" autocomplete="off"
														class="form-control" placeholder="用户名"> <i
														class="icon-user"></i>
												</span>
												</label> 
												<label class="block clearfix" style="margin-bottom: 20px;">
													<span class="block input-icon input-icon-right"> <input
														autocomplete="off" type="password" name="password"
														class="form-control" placeholder="密码"> <i
														class="icon-lock"></i>
												</span>
												</label>
												<button id="login" type="submit"
													class="width-35 pull-right btn btn-sm btn-primary">
													<i class="icon-key"></i> 登录
												</button>
											</fieldset>
										</div>
									</div>
									<!-- /widget-body -->
								</div>
								<!-- /login-box -->
							</form>
						</div>
						<!-- /position-relative -->
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<div class="bottom_footer">Copyright &copy; 2020 Triankg</div>
	</div>
	<%@include file="/WEB-INF/common/index-footer.jsp"%>
</body>
</html>