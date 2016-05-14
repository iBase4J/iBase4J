<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<script type="text/javascript">
	function redirect() {
		var retUrl = "${retUrl}";
		if (retUrl != null && retUrl != "-1") {
			document.getElementById("msgDiv").style.display = "block";
			window.opener.g_snsCallback();
			//window.opener.location.href =retUrl;
			window.close();
		} else {
			document.getElementById("failDiv").style.display = "block";
		}
	}
</script>
</head>
<body onload="redirect()">
	<div id="msgDiv" style="display: none">
		页面长时间未跳转，<a href="javascript:void(0);" onclick="redirect()">点击此处</a>
	</div>
	<div id="failDiv" style="display: none;">
		登录失败，请关闭此页面重试，<a href="javascript:window.open('','_self').close();">关闭</a>
	</div>
</body>
</html>
