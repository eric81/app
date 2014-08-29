<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/view/common/taglib.jsp"%>
<%@ taglib uri="/static/tld/oscache.tld" prefix="cache"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"/>

<html>
<body>

缓存已刷新...<p>

<cache:flush key="testcache" scope="application"/>

<a href="${ctx}/view/oscache/oscache.jsp">返回</a>

</body>
</html>
