<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../webjars/jquery-ui/1.12.1/jquery-ui.min.css">
<link rel="stylesheet" href="../webjars/bootstrap/3.3.7/css/bootstrap.min.css">
<title>New Case</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<form:form action="newcase" modelAttribute="casee" method="POST" class="form">
				<fieldset class="form-group">
					<legend>New Case</legend>
					<form:label path="caseNumber">Case Number:</form:label>
					<form:input path="caseNumber" class="form-control"/>
					<form:label path="location.id">Location:</form:label>
					<form:select path="location.id" class="form-control">
						<c:forEach var="loc" items="${locations}">
							<form:option value="${loc.id}">${loc.name}</form:option>
						</c:forEach>
					</form:select>
					<form:label path="applicant.firstName">Applicant FIrst Number:</form:label>
					<form:input path="applicant.firstName" class="form-control"/>
					<form:label path="applicant.lastName">Applicant Last Number:</form:label>
					<form:input path="applicant.lastName" class="form-control"/>
					<form:label path="applicant.dob">Applicant Date of Birth:</form:label>
					<form:input path="applicant.dob" class="form-control" id="datepicker"/>
					</br>
					<input type="submit" value="Save" class="btn btn-success" />
				</fieldset>
			</form:form>
		</div>
	</div>
	<script src="../webjars/jquery/1.12.4/jquery.min.js"></script>
	<script src="../webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="../webjars/jquery-ui/1.12.1/jquery-ui.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(function () {
			$("#datepicker").datepicker();
		});
	</script>
</body>
</html>