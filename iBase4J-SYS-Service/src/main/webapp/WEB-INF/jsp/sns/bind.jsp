<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>bind</title>
</head>
<body>
	<script type="text/javascript">
		if (window.opener) {
			window.opener.location.href = '${domain_host}/binding?type=${type}';
			window.close();
		}
	</script>
</body>
</html>