<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/common/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<html>
<head>
<title>Redis Manager</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<form class="form-horizontal" role="form" action="${ctx}/redis/get" method="post">
		<div class="form-group">
			<label for="key" class="col-sm-2 control-label">Key</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="key" name="key" placeholder="key">
			</div>
		</div>
 <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Get Object From Redis</button>
    </div>
  </div>
  <div class="form-group">
        <label for="" class="col-sm-2 control-label">Result : </label>
      <label for="" class="col-sm-2 control-label">${rs}</label>
  </div>
		<%-- 		<label for="inputEmail3" class="col-sm-2 control-label">Get Post from Redis : </label> 
		<input name="key" type="text" class="form-control" placeholder="Post ID">
		<button type="submit" class="btn btn-default">Get</button>
		<label for="inputEmail3" class="col-sm-2 control-label">Result : ${rs}</label>  --%>
	</form>
</body>
</html>
