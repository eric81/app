<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/common/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<html>
<head>
<title>修改用户</title>
</head>

<body>
	<div class="panel panel-default">
	<div class="panel-heading">用户信息</div>
		<div class="panel-body">
<!-- 			<form class="form-horizontal" role="form">
				<div class="table-responsive">
					<table class="table table-striped">
						<tr>
							<td><label for="password" class="label label-info">密码</label>
							</td>
							<td><div class="col-sm-10">
									<input type="password" class="form-control" id="password" placeholder="密码">
								</div>
							</td>
						</tr>
						<tr>
							<td><label for="photo" class="control-label">照片</label>
							</td>
							<td><div class="col-sm-10">
									<img alt="" src="" class="img-rounded">
								</div>
							</td>
						</tr>
					</table>
				</div>
			</form> -->
			<fieldset>
			<form role="form" class="form-horizontal">
				<div class="form-group">
					<label for="exampleInputEmail1">Email address</label><input type="email" class="form-control" id="exampleInputEmail1"
						placeholder="Enter email">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Password</label> <input type="password" class="form-control" id="exampleInputPassword1"
						placeholder="Password">
				</div>
				<div class="form-group">
					<label for="exampleInputFile">File input</label> <input type="file" id="exampleInputFile">
					<p class="help-block">Example block-level help text here.</p>
				</div>
				<div class="checkbox">
					<label> <input type="checkbox"> Check me out </label>
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
			</fieldset>
		</div>
	</div>
</body>
</html>