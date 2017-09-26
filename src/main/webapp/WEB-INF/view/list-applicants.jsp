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
		<legend>Search Applicant</legend>
			<label for="applicanrtsearchform">Use any fields to search</label>
			<form action="searchapplicant" method="GET" id="applicanrtsearchform">
				<fieldset class="form-group">	
					<div class="col-xs-4">
						<label for="firstName">Applicant first name</label>
						<input name="firstName" type="text" class="form-control"/>
					</div>
					<div class="col-xs-4">
						<label for="lastName">Applicant last name</label>
						<input name="lastName" type="text" class="form-control" />
					</div>
					<div class="col-xs-3">
						<label for="caseNumber">Case Number</label>
						<input name="caseNumber" type="text" class="form-control" />
					</div>
					<div class="col-xs-2">
						</br>
						<input type="submit" value="Search" class="btn btn-default"/>
					</div>						
				</fieldset>
			</form>
		</div>

	<%-- <div class="container">
		</form>
		<form action="searchworkerylocation" method="GET" class="form-inline">
			<fieldset class="form-group">
				<label for="location">Search users in locations:</label> 
				<select class="form-control" name="location">
					<c:forEach var="location" items="${locations}">
						<option value="${location.id}">${location.name}</option>
							</c:forEach>
					<input type="submit" value="Search" class="btn btn-success" />
				</select>
			</fieldset>
		</form>
		</br>
		<a href="addworker">Add Worker</a>
	</div> --%>
	<div class="container">
		<table class="table table-striped">
			<tr>
				<th>First Name:</th>
				<th>Last Name:</th>
				<th>Date of Birth:</th>
				<th>Case:</th>
			</tr>
			<c:forEach var="applicant" items="${applicants}">
				<tr>
					<td>${applicant.firstName}</td>
					<td>${applicant.lastName}</td>
					<td>${applicant.dob}</td>
					<td>${applicant.casee.caseNumber}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script src="../webjars/jquery/1.12.4/jquery.min.js"></script>
	<script src="../webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>$("#tab3").addClass("active");</script>
	</div>
</body>
</html>