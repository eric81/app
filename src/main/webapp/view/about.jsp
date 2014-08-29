<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/common/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<html>
<head>
<title>About</title>
</head>

<body>
	<div class="container row">
		<div class="col-sm-6 col-md-3" align="center">
			<img src="http://tpic.home.news.cn/xhForum/xhdisk001/M00/07/01/wKhJElJ8I04EAAAAAAAAAAAAAAA924.jpg" class="img-responsive img-rounded"
				alt="image">
		</div>
		<div style="margin:10px 0;"></div>
		<div class="col-sm-6 col-md-3" align="center">
			<img src="http://tpic.home.news.cn/xhForum/xhdisk001/M00/07/01/wKhJEFJ8JA8EAAAAAAAAAAAAAAA999.jpg" class="img-responsive img-rounded"
				alt="image">
		</div>
		<div style="margin:10px 0;"></div>
		<div class="col-sm-6 col-md-3" align="center">
			<img src="http://tpic.home.news.cn/xhForum/xhdisk001/M00/07/01/wKhJEVJ8JsIEAAAAAAAAAAAAAAA686.jpg" class="img-responsive img-rounded"
				alt="image">
		</div>
	</div>
	
<form class="form-horizontal" role="form">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> Remember me
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Sign in</button>
    </div>
  </div>
</form>
</div>
</body>
</html>