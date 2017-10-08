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
		<div class="container" style="margin-top:150px; ">
			<div class="row">
        		<div class="col-xs-7">
        			
        			<%-- <form action="searchworker" method="GET">
						<fieldset class="form-group">
							<legend>Search Case</legend>
							<table class="table">
								<tbody>
									<h5>Use any fields to search</h5>
									<tr>
										<td><label for="firstName">Applicant First Name</label></td>
										<td><input name="firstName" type="text" class="form-control" /></td>
										<td><label for="lastName">Applicant Last Name</label></td>
										<td><input name="lastName" type="text" class="form-control" /></td>
										<td><label for="caseNumber">Case Number</label></td>
										<td><input name="caseNumber" type="text" class="form-control" /></td>
									</tr>
									<tr>
										<td><label></label></td>
										<td><input type="submit" value="Search" class="btn btn-default" /></td>
									</tr>
								</tbody>
							</table>
						</fieldset>
					</form> --%>
					<legend>Search Case</legend>
					<label for="casesearchform">Use any fields to search</label>
					<form action="searchcase" method="GET" id="casesearchform">
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
							<div class="col-xs-8"></div>	
							<div class="col-xs-2">
								</br>
								<input type="submit" value="Search" class="btn btn-default"/>
							</div>						
						</fieldset>
					</form>
					
        			<c:forEach var="tab" items="${tabs}">
						<c:choose>
							<c:when test="${tab == 'workers'}">
							<a href="newcase" class="btn btn-primary">New Case</a>
							</br></br>
							</c:when>
						</c:choose>
					</c:forEach>





				<nav aria-label="Page navigation">
				
				
				<ul class="pagination">
					
					
				<!-- ******************PAGINATION************* -->
					<c:choose>
						<c:when test="${listBy == 'worker' }">
							<c:set var = "basePageUrl" value = "/case/listcasesbyworker?workerId=${workerId}"/>
						</c:when>
						<c:when test="${listBy == 'location'}">
							<c:set var = "basePageUrl" value = "/case/listcasesbylocation?locationId=${locationId}"/>
						</c:when>
						<c:otherwise>
							<c:set var = "basePageUrl" value = "/case/list"/>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${currentPage == 1}">
							<li class="disabled"><a href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
						</c:when>
						<c:otherwise>
							<c:url var="prevPageLink" value="${basePageUrl}">
								<c:param name="pageNumber" value="${currentPage - 1}" />
							</c:url>
							<li><a href="${prevPageLink}" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach begin="1" end="${totalCaseCount}" step="${pageSize}"
						varStatus="loop">
						<c:choose>
							<c:when
								test="${loop.count > (currentPage-5) && loop.count <= (currentPage+5) || (currentPage <= 5 && loop.count <= 10) || totalPages <=10 || (totalPages - loop.count <= 10)}">
								<c:url var="pageLink" value="${basePageUrl}">
									<c:param name="pageNumber" value="${loop.count}" />
								</c:url>
								<c:choose>
									<c:when test="${loop.count == currentPage}">
										<li class="active"><a href="${pageLink}"><c:out value="${loop.count}" /></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageLink}"><c:out value="${loop.count}" /></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${currentPage < (totalPages-5) && (totalPages > 10)}">
							<c:url var="nextFive" value="${basePageUrl}">
								<c:param name="pageNumber" value="${currentPage + 5}" />
							</c:url>
							<li><a href="${nextFive}"><c:out value="....." /></a></li>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${currentPage == totalPages}">
							<li class="disabled"><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
						</c:when>
						<c:otherwise>
							<c:url var="nextPageLink" value="${basePageUrl}">
								<c:param name="pageNumber" value="${currentPage + 1}" />
							</c:url>
							<li><a href="${nextPageLink}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
						</c:otherwise>
					</c:choose>
					
					
					
					
					</ul>
					</nav>
					
					
					
					
					
					<table class="table table-striped">
						<tr>
							<th>Case Number:</th>
							<th>Status:</th>
							<th>Date Opened:</th>
							<th>Last updated:</th>
							<th>Worker Number:</th>
							<th>Loction :</th>
						</tr>
						<c:forEach var="casee" items="${cases}">
							<tr>
								<td><a href="list?caseId=${casee.id}&pageNumber=${currentPage}"><b>${casee.caseNumber}</b></a></td>
								<td>${casee.status}</td>
								<td>${casee.dateOpened}</td>
								<td>${casee.lastUpdated}</td>
								<c:choose>
									<c:when test="${casee.worker == null}">
										<td><a href="assigntoworker?caseId=${casee.id}&locationId=${casee.location.id}">Assign to Worker</a></td>
									</c:when>
									<c:otherwise>
										<td>${casee.worker.workerNumber}</td>
									</c:otherwise>
								</c:choose>
								<td>${casee.location.name}</td>
							</tr>
						</c:forEach>
					</table>

			</div>
       			<div class="col-xs-5">
       				<fieldset>
       					<legend class="col-form-legend"><h2>Case ${casee.caseNumber} Details</h2></legend>
	       				<fieldset class="form-group">
							<%-- <div class="col-xs-4">
								<label for="caseNumber" class="col-form-label">Case Number</label>
	       						<input id="caseNumber" value="${casee.caseNumber}" type="text" class="form-control"/>
							</div> --%>
							<%-- <div class="col-xs-4">
								<label for="status" class="col-form-label">Status</label>
	       						<input id="status" value="${casee.status}" type="text" class="form-control"/>
							</div> --%>
							
							<div class="col-xs-4">
								<label for="dateOpened" class="col-form-label">Date Opened:</label>
	       						<input id="dateOpened" value="${casee.dateOpened}" type="text" class="form-control"/>
							</div>
							<div class="col-xs-4">
								<label for="lastUpdated" class="col-form-label">Last Updated:</label>
	       						<input id="lastUpdated" value="${casee.lastUpdated}" type="text" class="form-control"/>
							</div>
							<div class="col-xs-3">
								<label for="locationname" class="col-form-label">Location:</label>
	       						<input id="locationname" value="${casee.location.name}" type="text" class="form-control"/>
							</div>
							<div class="col-xs-11">
								<label for="description" class="col-form-label">Case Details:</label>
	       						<textarea id="description" rows="5" class="form-control">${casee.description}</textarea>
							</div>
						</fieldset>
						
						<fieldset class="form-group">
							<legend class="col-form-legend">Status:</legend>
							<div class="col-xs-4">
								<label for="currentstatus" class="col-form-label">Current Status:</label>
		       					<input id="currentstatus" value="${casee.status}" type="text" class="form-control"/>
							</div>
							<label for="newstatus" class="col-form-label">New Status:</label>
							<form action="updatecase"  method="POST" class="form-inline"  id="newstatus">	
								<input type="hidden" name="caseId" value="${casee.id}"/>	
								<input type="hidden" name="pageNumber" value="${currentPage}"/>
								<div class="col-xs-4">
									
									<select name="newstatus" class="form-control">
										<option value="New">New</option>
										<option value="Initial Review">Initial Review</option>
										<option value="Pending">Pending</option>
										<option value="Approved">Approved</option>
										<option value="Declined">Declined</option>
										<option value="Closed">Closed</option>
									</select>
								</div>									
								<input id="updatestatusbutton" type="submit" value="Save new status" class="btn btn-primary" />
							</form>
						</fieldset>
						
						

						<fieldset class="form-group">
							<legend class="col-form-legend">Applicant:</legend>
							<div class="col-xs-4">
								<label for="applicantfirstName" class="col-form-label">First Name:</label>
	       						<input id="applicantfirstName" value="${casee.applicant.firstName}" type="text" class="form-control"/>
							</div>
							<div class="col-xs-4">
								<label for="applicantlastName" class="col-form-label">Last Name:</label>
	       						<input id="applicantlastName" value="${casee.applicant.lastName}" type="text" class="form-control"/>
							</div>
							<div class="col-xs-3">
								<label for="applicantdob" class="col-form-label">DOB:</label>
	       						<input id="applicantdob" value="${casee.applicant.dob}" type="text" class="form-control"/>
							</div>
						</fieldset>
							
						<fieldset class="form-group">
						<legend class="col-form-legend">Worker:</legend>
							<div class="col-xs-4">
								<label id="workerfirstName" class="col-form-label">First Name:</label>
	       						<input id="workerfirstName" value="${casee.worker.firstName}" type="text" class="form-control"/>
							</div>
							<div class="col-xs-4">
								<label for="workerlastName" class="col-form-label">Last Name:</label>
	       						<input id="workerlastName" value="${casee.worker.lastName}" type="text" class="form-control"/>
							</div>
							<div class="col-xs-3">
								<label id="workerworkerNumber" class="col-form-label">Number:</label>
       							<input id="workerworkerNumber" value="${casee.worker.workerNumber}" type="text" class="form-control"/>
							</div>
							<div class="col-xs-4"></br><a href="assigntoworker?caseId=${casee.id}&locationId=${casee.location.id}" class="btn btn-primary">Re Assign</a></div>
						</fieldset>
	       				<legend>Case Updates:</legend>
	       				<c:forEach var="caseUpdate" items="${casee.caseUpdates}">
							<div class="list-group">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">${caseUpdate.updateDate}</h3>
								</div>
								<div class="panel-body">
									<h4>
										<important>Inernal Use Update:</important>
									</h4>
									${caseUpdate.internalUpdateDetail}
								</div>
								<div class="panel-body">
									<h4>
										<important>Update To The Applicant:</important>
									</h4>
									${caseUpdate.applicantFacingUpdateDetail}
								</div>
							</div>
						</c:forEach>			
	       					
	       				
	       				<form action="createcseupdate" modelAttribute="caseUpdate" method="POST" class="form">
	       					<input type="hidden" name="caseId" value="${casee.id}"/>
	       					<input type="hidden" name="pageNumber" value="${currentPage}"/>
							<fieldset class="form-group">
								<label for="internalUpdateDetail">Internal Update:</label>
								<textarea name="internalUpdateDetail" rows="5" class="form-control"></textarea>
								<label for="applicantFacingUpdateDetail">Update to Applicant:</label>
								<textarea type="text" name="applicantFacingUpdateDetail" rows="5" class="form-control"></textarea>
								</br>
								<input type="submit" value="Submit New Update" class="btn btn-primary" />
							</fieldset>
						</form>
						
						
						
       				</fieldset>
       			</div>
    		</div>
		
			
		</div>
		<script src="../webjars/jquery/1.12.4/jquery.min.js"></script>
		<script src="../webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script>$("#tab1").addClass("active");</script>
	</div>
</body>
</html>