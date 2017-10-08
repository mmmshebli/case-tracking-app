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
	<label for="locationform">Enter New location and click save:</label> 
	<form action="newlocation" method="POST" class="form-inline" id="locationform">
		<fieldset class="form-group">
			<input name="locationname" type="text" placeholder="Enter new location" class="form-control"/>
			<input type="submit" value="Save Location" class="btn btn-default">
		</fieldset>
	</form>
	
	</br>
		<table class="table table-striped">
			<tr>
				<th>Location</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="location" items="${locations}" varStatus="status">
				<tr>
					<td>${location.name}</td>
					<td><a class="btn btn-primary" href="${pageContext.request.contextPath}/worker/searchworkerylocation?location=${location.id}">Workers <span class="badge">${workersCount[status.index]}</span></a></td>
					<td><a class="btn btn-primary" href="${pageContext.request.contextPath}/case/listcasesbylocation?locationId=${location.id}">Cases <span class="badge">${casesCount[status.index]}</span></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script src="../webjars/jquery/1.12.4/jquery.min.js"></script>
	<script src="../webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>$("#tab4").addClass("active");</script>
	</div>
</body>
</html>