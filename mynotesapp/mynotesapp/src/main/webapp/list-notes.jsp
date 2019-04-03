<! -- jstl tag for loop -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- first add support for using spring mvc form tags -->
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

<html>

<head>
	<title>List Notes</title>
	
	<link type="text/css"
			rel="stylesheet"
			href="${contextPath}/resources/css/style.css"/>
	
</head>

<body>

	<div id="wrapper">
		<div id = "header">
			<h2>Notes Manager</h2>
		</div>
  <div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
  </div>
	</div>

	<div id = "container">
		<div id = "content">
		
		<!-- put new button: Add Notes -->
		
		<input type="button" value = "Add Note"
			onclick="window.location.href='showFormForAdd'; return false"
			class="add-button"
			/>
		<br><br>
		<!-- add a search box -->
		<form:form action ="search" method = "POST">
			Search Note: <input type="text" name = "theSearchName"/>
			<input type="submit" value = "Search" class="add-button"/>
		</form:form>
		
		<!--  add html table -->
		
		<table>
			<tr>
				<th>Title</th>
				<th>Description</th>
				<th>Creation Date</th>
				<th>Updation Date</th>
				<th>Action</th>
			</tr>
	
			<!-- loop over and print our  customers -->
			<c:forEach var = "tempNote" items = "${notes}">
			
				<!-- construct an "update" link with customer id -->
				<c:url var = "updateLink" value = "/note/showFormForUpdate">
					<c:param name = "noteId" value = "${tempNote.id}"/>
				</c:url>
				
				<!-- construct an "delete" link with customer id -->
				<c:url var = "deleteLink" value = "/note/delete">
					<c:param name = "noteId" value = "${tempNote.id}"/>
				</c:url>
								
				<tr>
					<td>${tempNote.title}</td>
					<td>${tempNote.desc}</td>
					<td>${tempNote.creationDate}</td>
					<td>${tempNote.updationDate}</td>
					<td>
						<a href="${updateLink}">Update</a>
						|
						<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		</div>
	</div>
	
</body>

</html>