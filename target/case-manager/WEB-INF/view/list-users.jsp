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
		<table class="table table-striped">
			<tr>
				<th>First Name:</th>
				<th>Last Name:</th>
				<th>Login name</th>
				<th>Disable</th>
				<th>Reset Password</th>
				<th>Temporary Password</th>
				<th>Roles</th>
				<th>Update Roles</th>
			</tr>
			<c:forEach var="worker" items="${workers}">
				<tr>
					<td>${worker.firstName}</td>
					<td>${worker.lastName}</td>
					<td>${worker.user.userName}</td>
					<c:choose>
						<c:when test="${worker.user.enabled == 1}">
							<td><a href="disableenableuser?userName=${worker.user.userName}" class="btn btn-default">Disable</a></td>
						</c:when>
						<c:otherwise>
							<td><a href="disableenableuser?userName=${worker.user.userName}" class="btn btn-danger">Enable</a></td>
						</c:otherwise>
					</c:choose>
					<form action="resetPassword" method="POST">
						<input type="hidden" value="${worker.user.userName}" name="username" />
						<td><input type="submit" value="Reset Password" class="btn btn-default" /></td>
						<td><input name="newpassword" type="text" class="form-control" placeholder="Enter Temporary Password and click Reset"/></td>
					</form>
					<td>
						<c:forEach var="role" items="${worker.user.roles}">
							<div>${role.role}</div>
						</c:forEach>		
					</td>
					<form action="updateroles" method="POST">
					<input type="hidden" name="userName" value="${worker.user.userName}"/>
						<td>
							<div class="dropdown">
							  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							    Select Roles
							    <span class="caret"></span>
							  </button>
							  <ul class="dropdown-menu" aria-labelledby="dropdownMenu2" style="padding-left:10px;">
							    <li>
							    	<input type="checkbox" id="admin" name="roles" value=1>
	    							<label for="music">Administrator</label>
							    </li>
							    <li>
							    	<input type="checkbox" id="supervisor" name="roles" value=2>
	    							<label for="music">Supervisor</label>
							    </li>
							    <li>
							    	<input type="checkbox" id="worker" name="roles" value=3>
	    							<label for="music">Worker</label>
							    </li>
							    <li>
							    	<input type="submit" value="Save New Roles" class="btn btn-default">
							    </li>
							  </ul>
							</div>
	    					
						</td>
					</form>
					
				</tr>
			</c:forEach>
		</table>
	</div>
	<script src="../webjars/jquery/1.12.4/jquery.min.js"></script>
	<script src="../webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>$("#tab5").addClass("active");</script>
	</div>
</body>
</html>