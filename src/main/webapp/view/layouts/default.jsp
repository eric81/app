<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ include file="/view/common/taglib.jsp"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>EudemonTaurus:<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link href="${ctx}/static/images/favicon.ico" type="image/x-icon" rel="shortcut icon">
<link href="${ctx}/static/css/default.css" type="text/css" rel="stylesheet" />

<link href="${ctx}/static/bootstrap/3.0.3/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap/3.0.3/css/bootstrap-theme.min.css" type="text/css" rel="stylesheet" />

<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>

<link href="${ctx}/static/jquery-validation/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/messages_bs_zh.js" type="text/javascript"></script>


<sitemesh:head/>
</head>

<body>
	<div class="container">
		<%@ include file="/view/layouts/header.jsp"%>
		<div id="content">
			<sitemesh:body/>
		</div>
		<%@ include file="/view/layouts/footer.jsp"%>
	</div>
	
	<!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="${ctx}/static/bootstrap/3.0.3/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>