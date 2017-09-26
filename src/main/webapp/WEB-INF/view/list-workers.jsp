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
			<legend>Search Worker</legend>
				<label for="workersearchform">Use any fields to search</label>
				<form action="searchworker" method="GET" id="workersearchform">
					<fieldset class="form-group">	
						<div class="col-xs-4">
							<label for="firstName">Worker first name</label>
							<input name="firstName" type="text" class="form-control"/>
						</div>
						<div class="col-xs-4">
							<label for="lastName">Worker last name</label>
							<input name="lastName" type="text" class="form-control" />
						</div>
						<div class="col-xs-3">
							<label for="workerNumber">Worker Number</label>
							<input name="workerNumber" type="text" class="form-control" />
						</div>
						<div class="col-xs-8"></div>	
						<div class="col-xs-2">
							</br>
							<input type="submit" value="Search" class="btn btn-default"/>
						</div>						
					</fieldset>
				</form>
		</div>
		
		<div class="container">
			<label for="searchworkerbylocation" class="col-form-label">Search users in locations:</label>
			<form action="searchworkerylocation"  method="GET" class="form-inline"  id="searchworkerbylocation">	
				<div class="col-xs-2">	
					<select name="location" class="form-control">
						<c:forEach var="location" items="${locations}">
							<option value="${location.id}">${location.name}</option>
						</c:forEach>
					</select>
				</div>									
				<input id="updatestatusbutton" type="submit" value="Search" class="btn btn-default" />
			</form>
			</br>
			<a href="addworker" class="btn btn-primary">Add Worker</a>
			<a href="workerswithnocases" class="btn btn-primary">Delete Worker</a>
		</div>
			
		</div>
		</br>
		<div class="container">
			<table class="table table-striped">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Worker number</th>
					<th>Location</th>
					<th></th>
				</tr>
				<c:forEach var="worker" items="${workers}" varStatus="status">
					<tr>
						<td>${worker.firstName}</td>
						<td>${worker.lastName}</td>
						<td>${worker.workerNumber}</td>
						<td>${worker.location.name}</td>
						<td><a class="btn btn-primary" href="${pageContext.request.contextPath}/case/listcasesbyworker?workerId=${worker.id}">Assigned Cases <span class="badge">${casesCount[status.index]}</span></a></td>
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