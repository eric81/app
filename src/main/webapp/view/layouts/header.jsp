<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>

<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${ctx}/view/index.jsp">Web Application</a>
		</div>
		
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${ctx}/view/index.jsp">Home</a>
				</li>
				<li><a href="${ctx}/user/listView">User Management</a>
				</li>
				<li><a href="${ctx}/redis/view">Redis Management</a>
				</li>
				<li><a href="${ctx}/view/about.jsp">About</a>
				</li>
				<li><a href="${ctx}/view/bootstrap.jsp">Bootstrap</a>
				</li>
				<li><a href="#contact">Contact</a>
				</li>
			</ul>
			
			<shiro:user>
			<div class="btn-group navbar-right">
				<a class="btn btn-default navbar-btn dropdown-toggle" data-toggle="dropdown" href="#"> <i class="icon-user"></i> <shiro:principal
						property="name" /> <span class="caret"></span> </a>
				<ul class="dropdown-menu">
					<shiro:hasRole name="admin">
						<li><a href="${ctx}/user/listView">Admin Users</a></li>
						<li class="divider"></li>
					</shiro:hasRole>
					<li><a href="${ctx}/view/user/profile.jsp">Edit Profile</a></li>
					<li><a href="${ctx}/logout">Logout</a></li>
				</ul>
			</div>
			</shiro:user>
		</div>
	</div>
</div>


<div id="header">
	<div id="title" align="center">
		<h1>Web Application Quick Start</h1>
	</div>
</div>