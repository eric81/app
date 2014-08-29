<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/common/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<html>
<head>
<title>用户管理</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui-1.3.4/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui-1.3.4/themes/icon.css">
</head>

<body>

	<div style="margin:10px 0;"></div>
	<table id="dg" class="easyui-datagrid" title="用户列表" style="height:350px"
		data-options="url:'${ctx}/user/list',rownumbers:true,singleSelect:true,pagination:true,pageSize:8,pagePosition:'both'" fitColumns="true">
		<thead>
			<tr>
				<th field="name" width="50" align="center" sortable="true">用户名</th>
				<th field="roles" width="50" align="center" sortable="true">角色</th>
				<th field="permissions" width="100" align="center" sortable="true">权限</th>
				<th field="registerDate" width="50" align="center" sortable="true">注册时间</th>
			</tr>
		</thead>
	</table>
	
	<div style="margin:30px 0;"></div>

	<div class="panel panel-default">
	<div class="panel-heading">用户信息</div>
		<div class="panel-body">

			<form class="form-horizontal" role="form">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-3">
						<p class="form-control-static">admin</p>
					</div>
					<label for="role" class="col-sm-2 control-label">角色</label>
					<div class="col-sm-2">
						<select id="role" class="form-control">
							<option>user</option>
							<option>admin</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="permission" class="col-sm-2 control-label">权限</label>
					<div id="permission" class="col-sm-2">
  					<label><input type="checkbox" value="">read</label>
  					<label><input type="checkbox" value="">modify</label>
  					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">图片</label>
					<div class="col-sm-3">
						<img src="http://tpic.home.news.cn/xhForum/xhdisk001/M00/07/01/wKhJEFJ8JA8EAAAAAAAAAAAAAAA999.jpg" class="img-responsive img-rounded" alt="image">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">上传图片</label>
					<div class="col-sm-3">
						<input type="file" class="form-control" id="exampleInputFile">
					</div>
				</div>
			</form>
		</div>
	</div>


	<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		$(function() {
			var pager = $('#dg').datagrid('getPager'); // get the pager of datagrid
			pager.pagination({
				pageSize : 8,
				showPageList : false,

				buttons : [ {
					iconCls : 'icon-search',
					handler : function() {
						alert('search');
					}
				}, {
					iconCls : 'icon-add',
					handler : function() {
						alert('add');
					}
				}, {
					iconCls : 'icon-edit',
					handler : function() {
						alert('edit');
					}
				}, {
					iconCls : 'icon-remove',
					handler : function() {
						var row = $('#dg').datagrid('getSelected');
						if (row) {
							$.messager.confirm('Confirm', 'Are you sure you want to destroy this user?', function(r) {
								if (r) {
									$.post('${ctx}/user/delete', {
										id : row.id
									}, function(data) {
										if (data.success) {
											$('#dg').datagrid('reload'); // reload the user data  
										} else {
											$.messager.alert('delete faild', data.msg, 'error');
											$.messager.show({ // show error message  
												title : 'Error',
												msg : data.msg
											});
										}
									}, 'json');
								}
							});
						}
					}
				} ],

				onBeforeRefresh : function() {
					alert('before refresh');
					return true;
				}
			});
		})
	</script>
</body>
</html>
