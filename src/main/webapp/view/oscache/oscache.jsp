<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/view/common/taglib.jsp"%>
<%@ taglib uri="/static/tld/oscache.tld" prefix="cache"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"/>

<html>
<body> 
<p>
 没有缓存的日期: <%= new Date() %> <p>

<cache:cache time="30">
每30秒刷新缓存一次的日期: <%= new Date() %> <p>
</cache:cache>

<cache:cache key="testcache">
手动刷新缓存的日期：<%= new Date() %> <p>
</cache:cache>
<a href="${ctx}/view/oscache/oscache-flush.jsp">手动刷新</a>
</body>
</html>

