<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/view/common/taglib.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证码测试</title>

</head>

<body>
	<img id="code" src="${ctx}/verifycode/show">
	<input type="text" name="checkcode" id="checkcode""/>
	<input id="submitCodeBtn" type="button" value="提交" />
	
	<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
	<script type="text/javascript">
		$(function() {
			//点击图片更换验证码
			$("#code").click(function() {
				getcode();
			});
			
			$("#submitCodeBtn").click(function() {
				checkcode();
			});
		});
		
		//此getXMLRequest()方法是根据不同浏览器来取得XMLHttpRequest对象
		function getXMLRequest() {
			var request;
			try {
				//for火狐等浏览器
				request = new XMLHttpRequest();
			} catch (e) {
				try {
					//for IE
					request = new ActiveXObject("Microsoft.XMLHttp");
				} catch (e) {
					alert("您的浏览器不支持AJAX!!!");
					return null;
				}
			}
			return request;
		}

		//此checkcode()方法是更换验证码图片的要点
		function getcode() {
			var request = getXMLRequest();//得到XMLHttpRequest对象
			request.onreadystatechange = function() {
				if (request.readyState == 4) {
					document.getElementById("code").src = "${ctx}/verifycode/show";//改变验证码图片
				}
			}
			//将请求发送出去
			request.open("GET", "${ctx}/verifycode/show", true);
			request.send(null);
		}
		
		function checkcode(){
			$.ajax({
				url : "${ctx}/verifycode/check",
				type : 'POST',
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				dataType : "json",
				async : false,
				data : {
					checkcode : $("#checkcode").val()
				},
				cache : false,
				success : function(rs) {
					alert(rs.message);
				}
			});
		}
	</script>
</body>

</html>