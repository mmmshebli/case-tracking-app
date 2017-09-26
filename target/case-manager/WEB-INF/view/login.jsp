<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
	<title>Case Management Login</title>
	<style>
		body {
			background: #eee !important;	
		}
		.wrapper {	
			margin-top: 40px;
		  	margin-bottom: 80px;
		}
		.errorblock {
			color: #ff0000;
			background-color: #ffEEEE;
		}
		.form-signin {
		  max-width: 380px;
		  padding: 15px 35px 45px;
		  margin: 0 auto;
		  background-color: #fff;
		  border: 1px solid rgba(0,0,0,0.1);  
		}
		.tablesimage{
			 margin: 0 auto;
			 padding: 15px 35px 45px;
			 max-width: 590px;
		}
		.login-table{
			max-width: 420px;
			margin: 0 auto;
			padding: 15px 20px 10px;
		}
	</style>
</head>
<body onload='document.loginForm.j_username.focus();'>
	<div class="wrapper">
		<div class="container">
			<form name='loginForm' action="<c:url value='login' />" method="POST" class="form-signin">
				<h2 class="form-signin-heading">Please login</h2>
				<%
	        		String errorString = (String) request.getAttribute("error");
	        		if (errorString != null && errorString.trim().equals("true")) {
	            		out.println("<span class=\"errorblock\">Incorrect login name or password. Please try again.</span>");
	        		}
	    		%>
				<input type="text" class="form-control" name="username" placeholder="User Name" required="" autofocus="" id="j_username"/> 
				<input type="password" class="form-control" name="password" placeholder="Password" required="" />
				</br>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
			</form>
			<div class="login-table">
				<table class="table  table-bordered">
					<thead>
						<tr>
							<th>Username</th>
							<th>Password</th>
							<th>Role</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>admin1</td>
							<td>admin1WeakPassword</td>
							<td>admin</td>
						</tr>
						<tr>
							<td>supervisor1</td>
							<td>supervisor1WeakPassword</td>
							<td>supervisor</td>	
						</tr>
						<tr>
							<td>worker1</td>
							<td>worker1WeakPassword</td>
							<td>worker</td>
						</tr>
						<tr>
							<td>worker2</td>
							<td>worker2WeakPassword</td>
							<td>worker</td>
						</tr>
					</tbody>
				</table>
			</div>				
			<p>
				<ul>
					<li>This application is a Case management system, it has Cases, Workers, Applicants and Locations.</li>
					<li>The case has a worker, aplicant and location as shown in the relational diagram below</li>
					<li>The worker has a list of cases assigned to them, the worker has a location and they can only be assigned cases in their location.</li>
					<li>The Application securoty is Role based, with three roles: Admin, Supervisor and Worker, the admin can see all tabs and all cases and workers, including
					the user management like disable/enable and passwords resets, the superisor can see all tabs and cases but not the user management, and the wirker can see only 
					the case management tab and can only see the cases that are assigned to that specific worker.</li>
					<li>Use the provided accounts in the table above to access and test the application</li>
				</ul>			 
			</p>
			<div class="tablesimage"><img src="${pageContext.request.contextPath}/resources/images/image.png" height="500" width="500"></div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/webjars/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>