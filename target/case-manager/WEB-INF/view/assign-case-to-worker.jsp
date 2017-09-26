<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../webjars/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<h2>Those are the workers in Location: ${location}</h2>
			<table class="table table-striped">
				<tr>
					<th>First Name:</th>
					<th>Last Name:</th>
					<th>Worker number:</th>
					<th>Location:</th>
				</tr>
				<c:forEach var="worker" items="${workers}">
					<tr>
						<td><a href = "processassigntoworker?caseId=${caseId}&workerId=${worker.id}">Assign to worker number: ${worker.id}</a></td>
						<td>${worker.firstName}</td>
						<td>${worker.lastName}</td>
						<td>${worker.workerNumber}</td>
						<td>${worker.location.name}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<script src="../webjars/jquery/1.12.4/jquery.min.js"></script>
		<script src="../webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</div>
</body>
</html>