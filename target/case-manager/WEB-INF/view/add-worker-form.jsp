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
			<form:form action="addworker" modelAttribute="worker" method="POST" class="form">
				<fieldset class="form-group">
					<legend>Add Worker</legend>
					<form:label path="workerNumber">Woker Number:</form:label>
					<form:input path="workerNumber" class="form-control"/></br>
					<form:label path="firstName">First Name:</form:label>
					<form:input path="firstName" class="form-control"/></br>
					<form:errors path="firstName" class="alert-danger"/>
					<form:label path="lastName">Last Name:</form:label>
					<form:input path="lastName" class="form-control"/></br>
					<form:label path="user.userName">Login Name:</form:label>
					<form:input path="user.userName" class="form-control"/></br>
					<form:label path="user.password">Initial Password:</form:label>
					<form:input path="user.password" class="form-control"/></br>
					<div class="radio">
  						<label><form:radiobutton path="user.enabled" value="1" />Enabled</label>
					</div>
					<div class="radio">
  						<label><form:radiobutton path="user.enabled" value="0" />Disabled </label>
					</div></br>
					<form:select path="user.roles[0].id" multiple="true" class="form-control">
						<form:options items="${roles}" itemValue="id" itemLabel="role"/>
						<%-- <form:option value="ROLE_WORKER">Worker</form:option>
						<form:option value="ROLE_WSUPERVISOR">Supervisor</form:option>
						<form:option value="ROLE_ADMIN">Administrator</form:option> --%>
					</form:select>
					<%-- <form:select path="location" items="${locations}" itemValue="id" itemLabel="name"></form:select> --%>
					<form:label path="location.id">Location:</form:label>
					<form:select path="location.id" class="form-control">
						<c:forEach var="loc" items="${locations}">
							<form:option value="${loc.id}">${loc.name}</form:option>
						</c:forEach>
					</form:select>
					</br>
					<input type="submit" value="Save" class="btn btn-success" />
				</fieldset>
			</form:form>
		</div>
	</div>
	<script src="../webjars/jquery/1.12.4/jquery.min.js"></script>
	<script src="../webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>