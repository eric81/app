<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/common/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String width = request.getParameter("width");
	String height = request.getParameter("height");
%>

<!DOCTYPE html>
<html>
<head>

<title>新一届中央政治局常委同中外记者见面 - 大图滚动 - 新华网直播</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="publishid" content="1143796.0.102" />
<meta name="publishid" content="1120644.0.3" />
<meta name="keywords" content="新一届中央政治局常委同中外记者见面 - 大图滚动 - 新华网直播" />
<meta name="description" content="新一届中央政治局常委同中外记者见面 - 大图滚动 - 新华网直播" />

<style type="text/css">
body {
	padding: 0;
	margin: 0;
}

#picnews {
	height: 0px;
	background-color: #FFFFFF;
}

#focus {
	WIDTH: 0px;
	POSITION: relative;
	HEIGHT: 0px
}

#au {
	FILTER: progid:DXImagetransform.Microsoft.Fade(duration=0.5, overlap=1.0
		);
	WIDTH: 0px;
	HEIGHT: 0px
}

#conau {
	MARGIN-TOP: 0px;
	FONT-WEIGHT: normal;
	FONT-SIZE: 18px;
	LEFT: 7px;
	WIDTH: 528px;
	COLOR: #fff;
	FONT-FAMILY: Georgia, "Times New Roman", Times, serif;
	POSITION: absolute;
	TOP: 199px;
	HEIGHT: 47px;
	TEXT-ALIGN: left
}

#conau A {
	COLOR: #fff
}

#conau A:hover {
	COLOR: #fff
}

.lunbo {
	RIGHT: 5px;
	WIDTH: 68px;
	POSITION: absolute;
	TOP: 0px;
	HEIGHT: 21px
}

.lunbo TABLE {
	HEIGHT: 21px
}

.lunbo TD {
	FONT-WEIGHT: bold;
	COLOR: #000;
	LINE-HEIGHT: 12px
}

.lunbo.bg {
	padding: 0px;
	BACKGROUND-IMAGE: url('../static/images/slides/pic_2.gif');
	WIDTH: 18px;
	LINE-HEIGHT: 21px;
	HEIGHT: 21px;
	TEXT-ALIGN: center;
	COLOR: #030100;
}

.lunbo.active {
	padding: 0px;
	BACKGROUND-IMAGE:url(../static/images/slides/pic_1.gif);
	WIDTH: 18px;
	LINE-HEIGHT: 21px;
	HEIGHT: 21px;
	TEXT-ALIGN: center;
	COLOR: #a8471c;
}

a img {
	border: 0;
}
</style>

<script>
	var width = request('width');
	var height = request('height');
	if(!width){
		width = 930;
	}
	if(!height){
		height = 340;
	}
	var top = height - 20;

	$(document).ready(function() {
		$("#picnews").css("height", height);
		$("#focus").css("width", width);
		$("#focus").css("height", height);
		$("#au").css("width", width);
		$("#au").css("height", height);
		$(".lunbo").css("top", top);
	});

	function request(strParame) {
		var args = new Object();
		var query = location.search.substring(1);

		var pairs = query.split("&"); // Break at ampersand 
		for ( var i = 0; i < pairs.length; i++) {
			var pos = pairs[i].indexOf('=');
			if (pos == -1)
				continue;
			var argname = pairs[i].substring(0, pos);
			var value = pairs[i].substring(pos + 1);
			value = decodeURIComponent(value);
			args[argname] = value;
		}
		return args[strParame];
	}
</script>

</head>
<body>
	<table width="<%=width%>" border="0" cellspacing="0" cellpadding="0"
		style="margin:0 auto">
		<tr>
			<td height="<%=height%>" valign="top">
				<div id="picnews">
					<div id="focus">
						<div id="au">
							<div style="display: block">
								<a
									href="http://news.xinhuanet.com/photo/18cpcnc/2012-11/16/c_123959474.htm"
									target="_blank"><img src="${ctx}/static/images/slides/1.jpg"
									width="<%=width%>" height="<%=height%>"
									alt="胡锦涛、习近平等领导同志亲切会见出席党的十八大代表、特邀代表和列席人员并发表重要讲话" /> </a>
							</div>
							<div style="display: none">
								<a
									href="http://news.xinhuanet.com/photo/18cpcnc/2012-11/16/c_123959471.htm"
									target="_blank"><img src="${ctx}/static/images/slides/2.jpg"
									width="<%=width%>" height="<%=height%>"
									alt="十七届、十八届中共中央政治局常委合影" /> </a>
							</div>
							<div style="display: none">
								<a
									href="http://news.xinhuanet.com/photo/18cpcnc/2012-11/15/c_123957806.htm"
									target="_blank"><img src="${ctx}/static/images/slides/3.jpg"
									width="<%=width%>" height="<%=height%>" alt="新一届中央政治局常委同中外记者见面" />
								</a>
							</div>
						</div>
						<div class="lunbo">
							<table border="0" cellpadding="0" cellspacing="0" align="right">
								<tr>
									<td class="active" id="t0" onmouseover="Mea(0);clearAuto();"
										onmouseout="setAuto();">1</td>
									<td width="8"></td>
									<td class="bg" id="t1" onmouseover="Mea(1);clearAuto();"
										onmouseout="setAuto();">2</td>
									<td width="8"></td>
									<td class="bg" id="t2" onmouseover="Mea(2);clearAuto();"
										onmouseout="setAuto();">3</td>
									<td width="8"></td>
								</tr>
							</table>
						</div>
						<div id="conau">
							<div></div>
							<div style="display: none"></div>
							<div style="display: none"></div>
							<div style="display: none"></div>
						</div>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		var n = 0;
		function Mea(value) {
			n = value;
			setBg(value);
			plays(value);
			conaus(n)
		}
		function setBg(value) {
			for ( var i = 0; i < 3; i++)
				document.getElementById("t" + i + "").className = "bg";
			document.getElementById("t" + value + "").className = "active"
		}
		function plays(value) {
			try {
				with (au) {
					filters[0].Apply();
					for (i = 0; i < 3; i++)
						i == value ? children[i].style.display = "block"
								: children[i].style.display = "none";
					filters[0].play()
				}
			} catch (e) {
				var d = document.getElementById("au").getElementsByTagName(
						"div");
				for (i = 0; i < 3; i++)
					i == value ? d[i].style.display = "block"
							: d[i].style.display = "none"
			}
		}
		function conaus(value) {
			try {
				with (conau) {
					for (i = 0; i < 3; i++)
						i == value ? children[i].style.display = "block"
								: children[i].style.display = "none"
				}
			} catch (e) {
				var d = document.getElementById("conau").getElementsByTagName(
						"div");
				for (i = 0; i < 3; i++)
					i == value ? d[i].style.display = "block"
							: d[i].style.display = "none"
			}
		}
		function clearAuto() {
			clearInterval(autoStart)
		}
		function setAuto() {
			autoStart = setInterval("auto(n)", 4000)
		}
		function auto() {
			n++;
			if (n > 2)
				n = 0;
			Mea(n);
			conaus(n)
		}
		setAuto();
	</script>
</body>
</html>