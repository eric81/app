<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/common/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>App start</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<style>
.carousel img {
	min-width: 100%;
	height: 340px;
}

.carousel-caption {
	background-color: transparent;
}
</style>

<script>
	$(function() {
		var myCarousel = $("#myCarousel").carousel({
			interval : 2000,
			pause : 'hover'
		});
		$("button#btn1").click(function() {
			$("#myCarousel").carousel(0);
		});
		$("button#btn2").click(function() {
			$("#myCarousel").carousel(1);
		});
		$("button#btn3").click(function() {
			$("#myCarousel").carousel(2);
		});
	});
</script>

</head>

<body>

	<div id="myCarousel" class="carousel slide">
		<div class="carousel-inner">
			<div class="item active">
				<a href="http://www.news.cn"><img src="${ctx}/static/images/slides/4.jpg" alt="slide1"> </a>
				<div class="container">
					<div class="carousel-caption">
						<h1 class="">Example headline.</h1>
						<button id="btn1" class="btn btn-primary">1</button>
						<button id="btn2" class="btn btn-primary">2</button>
						<button id="btn3" class="btn btn-primary">3</button>
					</div>
				</div>
			</div>
			<div class="item">
				<a href="http://www.news.cn"><img src="${ctx}/static/images/slides/5.jpg" alt="slide2"> </a>
				<div class="container">
					<div class="carousel-caption">
						<h1>Another example headline.</h1>
						<button id="btn1" class="btn btn-primary">1</button>
						<button id="btn2" class="btn btn-primary">2</button>
						<button id="btn3" class="btn btn-primary">3</button>
					</div>
				</div>
			</div>
			<div class="item">
				<a href="http://www.news.cn"><img src="${ctx}/static/images/slides/6.jpg" alt="slide3"> </a>
				<div class="container">
					<div class="carousel-caption">
						<h1>One more for good measure.</h1>
						<button id="btn1" class="btn btn-primary">1</button>
						<button id="btn2" class="btn btn-primary">2</button>
						<button id="btn3" class="btn btn-primary">3</button>
					</div>
				</div>
			</div>
		</div>
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a> <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
	</div>

	<div class="row">
		<div class="col-md-4">
			<h2>Banner</h2>
			<p>carousel</p>
			<p>
				<a class="btn btn-default" href="${ctx}/view/banner.jsp?width=930&height=340">View details &raquo;</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>发展论坛</h2>
			<p>3669主题 / 25438回帖</p>
			<p>
				<a class="btn btn-default" href="${ctx}/forum/list">View details &raquo;</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>oscache</h2>
			<p>example of oscache</p>
			<p>
				<a class="btn btn-default" href="${ctx}/view/oscache/oscache.jsp">View details &raquo;</a>
			</p>
		</div>
	</div>
</body>
</html>
