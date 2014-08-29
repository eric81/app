<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>登录页</title>

<style type="text/css">
#loginForm {
	max-width: 520px;
	margin: 0 auto;
}
</style>

<script>
	$(document).ready(function() {
		$("#loginForm").validate();
	});
</script>
</head>

<body>
	<form id="loginForm" action="${ctx}/user/login" method="post" class="form-horizontal">
		<fieldset>
			<legend>
				<small>用户登陆</small>
			</legend>
			<%
				String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
				if (error != null) {
					out.print(error);
			%>
			<div class="alert alert-error input-medium controls">
				<button class="close" data-dismiss="alert">×</button>
				登录失败，请重试.${FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME}
			</div>
			<%
				}
			%>
			<div class="control-group">
				<label for="username" class="control-label">名称:</label>
				<div class="controls">
					<input type="text" id="username" name="username" value="${username}" class="input-medium required" />
				</div>
			</div>
			<div class="control-group">
				<label for="password" class="control-label">密码:</label>
				<div class="controls">
					<input type="password" id="password" name="password" class="input-medium required" />
				</div>
			</div>
			<div class="controls">
				<label class="checkbox" for="rememberMe"><input type="checkbox" id="rememberMe" name="rememberMe" /> 记住我</label>
			</div>

			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="登录" /> <a class="btn btn-primary" href="${ctx}/user/register">注册</a>
			</div>
			
			<span class="help-block">Tips :(管理员: <b>admin/admin</b>,普通用户:<b>user/user</b>)</span>
		</fieldset>
	</form>
</body>
</html>
