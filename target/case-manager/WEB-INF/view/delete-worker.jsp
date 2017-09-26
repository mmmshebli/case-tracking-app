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
	<%@ include file="common/navigation.jspf" %>
		<div class="container" style="margin-top:150px;">
			
		</div>
		<div class="container">
			<important>A List of Workers without cases assigned to them, you can only delete a worker that has no case assigned to them</important>
		</div>
		</br>
		<div class="container">
			<table class="table table-striped">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Worker number</th>
					<th>Location</th>
					<th>Location</th>
					<th></th>
				</tr>
				<c:forEach var="worker" items="${workers}">
					<tr>
						<td>${worker.firstName}</td>
						<td>${worker.lastName}</td>
						<td>${worker.workerNumber}</td>
						<td>${worker.location.name}</td>
						<td><a href="deleteworker?workerId=${worker.id}" class="btn btn-danger">Delete Worker</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<script src="../webjars/jquery/1.12.4/jquery.min.js"></script>
		<script src="../webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script>$("#tab2").addClass("active");</script>
	</div>
</body>
</html>