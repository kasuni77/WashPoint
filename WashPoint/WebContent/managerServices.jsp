<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<% 
if(session.getAttribute("userId") != null) {
	String userId = (String)session.getAttribute("userId");
	if(userId.charAt(0) != 'M') {
	response.sendRedirect("index.jsp");
	}
} else {
	response.sendRedirect("index.jsp");
}
%>
<html>
<head>
<link rel="StyleSheet" type="text/css" href="CSS/stylesAd.css">
<link rel="StyleSheet" type="text/css" href="CSS/homeCSS.css">
<meta charset="ISO-8859-1">
<title>Manager Profile</title>
<link rel="icon" href="Images/logo.png">
    <link rel="stylesheet" href="CSS/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body style="backgrou11	nd-color: rgb(255, 255, 255); height: 100%; width: 100%;">
    <div class="container">
        <jsp:include page="WEB-INF/views/header.jsp"></jsp:include>
	<div class="sidenav">
            <div>
                    <img src="Images/avatar.png" height="150px" width="150px">
            </div>
            <div class="sidenav-links link2">
            <form method="post" id="list" action="listEmployees">
                <a onClick="submit()">View Employees</a>
                </form>
                <a href="managerInsertService.jsp">Create new Service</a>
                <a href="managerFindServices.jsp">Update Service</a>
                <a href="managerFindServices.jsp">Delete Service</a>
                <a href="managerFindServices.jsp">View items</a>
        </div>
        </div>
<div class="contentManager">
	<table>
	<c:forEach var="cus" items="${serDetails}">
	<c:set var="serviceId" value="${cus.serviceId}"/>
	<c:set var="serviceName" value="${cus.name}"/>
	<c:set var="description" value="${cus.description}"/>
	<c:set var="price" value="${cus.unitPrice}"/>
	<c:set var="serviceImg" value="${cus.serviceImg}"/>
	
	
	<tr>
		<td>Service ID</td>
		<td>${cus.serviceId}</td>
	</tr>
	<tr>
		<td>Service Name</td>
		<td>${cus.name}</td>
	</tr>
	<tr>
		<td>Description</td>
		<td>${cus.description}</td>
	</tr>
	<tr>
		<td>Price</td>
		<td>${cus.unitPrice}</td>
	</tr>
	<tr>
		<td>Service Image</td>
		<td>${cus.serviceImg}</td>
	</tr>
	

	</c:forEach>
	</table>
	
	
	<c:url value="managerUpdateService.jsp" var="serUpdate">
		<c:param name="uname" value="${serviceId}"/>
		<c:param name="pass" value="${serviceName}"/>
		<c:param name="description" value="${description}"/>
		<c:param name="price" value="${price}"/>
		<c:param name="serviceImg" value="${serviceImg}"/>
	</c:url>
	
	<br>
	
	<a href="${serUpdate}">
	<button type="button" class="btn btn-primary" name="update">Update</button>
	</a>
	
	<br><br>
	
	<form  method="post" action="DeleteService">
			<div class="form-group">
				<input class="form-control" type="text" name="serviceId" placeholder="Service Id" value="${serviceId}" hidden>
				<input value="manager" name="url" hidden>
			</div>

			<button type="submit" class="btn btn-primary">Delete</button>
		</form>
	</div>

	
	<!--Footer-->
		<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>
    </div>
</body>
<script type="text/javascript">
function submit() {
	var form = document.getElementById('list');
	form.submit();
}
</script>
</html>