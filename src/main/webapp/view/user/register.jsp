<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/common/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>用户注册</title>

<style type="text/css">
#inputForm {
	max-width: 520px;
	margin: 0 auto;
}
</style>

<script>
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#loginName").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
		alert("dd");
			rules : {
				name : {
					remote : "${ctx}/user/checkLoginName"
				}
			},
			messages : {
				name : {
					remote : "用户登录名已存在"
				}
			}
		});
	});
</script>
</head>

<body>
	<form id="inputForm" action="${ctx}/user/register" method="post" class="form-horizontal">
		<fieldset>
			<legend>
				<small>用户注册</small>
			</legend>
			<div class="control-group">
				<label for="name" class="control-label">用户名:</label>
				<div class="controls">
					<input type="text" id="name" name="name" class="input-large required" />
				</div>
			</div>
			<div class="control-group">
				<label for="plainPassword" class="control-label">密码:</label>
				<div class="controls">
					<input type="password" id="plainPassword" name="password" class="input-large required" />
				</div>
			</div>
			<div class="control-group">
				<label for="confirmPassword" class="control-label">确认密码:</label>
				<div class="controls">
					<input type="password" id="confirmPassword" name="confirmPassword" class="input-large required" equalTo="#plainPassword" />
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交" />&nbsp; <input id="cancel_btn" class="btn" type="button" value="返回"
					onclick="history.back()" />
			</div>
		</fieldset>
	</form>
</body>
</html>
