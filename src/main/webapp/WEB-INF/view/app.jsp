
<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="${pageContext.request.contextPath}/node_modules/core-js/client/shim.min.js"></script>
<script src="${pageContext.request.contextPath}/node_modules/zone.js/dist/zone.js"></script>
<script src="${pageContext.request.contextPath}/node_modules/systemjs/dist/system.src.js"></script>
<script src="${pageContext.request.contextPath}/systemjs.config.js"></script>


<script>
	System.import('main.js').catch(function(err){ console.error(err); });
</script>
<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<title>Angular App</title>
</head>
<body>
	ANGULAR APP!!!!!
	<app-root>Loading AppComponent content here ...</app-root>
</body>
</html>