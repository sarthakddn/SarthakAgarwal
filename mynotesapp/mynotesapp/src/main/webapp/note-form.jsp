<!-- first add support for using spring mvc form tags -->
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
	<head>
		<title>Customer Form</title>
		
		<link type="text/css"
				rel="stylesheet"
				href="${contextPath}/resources/css/style.css"/>
		
		<link type="text/css"
				rel="stylesheet"
				href="${contextPath}/resources/css/add-customer-style.css"/>			
				
		<style>.error{color:red}</style>			
		
	</head>
	<body>
		<div id="wrapper">
			<div id = "header">
				<h2>Notes Manager</h2>
			</div>
		</div>	

		<div id = "container">
			<h3>Save Note</h3>
			
			<form:form action="saveNote" modelAttribute="note" method= "POST">
			
			<!-- need to associate this data with customer id -->
			<form:hidden path = "id"/>
			
			<table>
				<tbody>
					<tr>
						<td><label>Title:</label></td>
						<td><form:input path = "title"/></td>
						<td><form:errors path = "title" cssClass="error"/></td>
					</tr>
					<tr>
						<td><label>Description:</label></td>
						<td><form:input path = "desc"/></td>
						<td><form:errors path = "desc" cssClass="error"/></td>
					</tr>			
					<tr>
						<td><label></label></td>
						<td><input type = "submit" value="Save" class="save"/></td>
					</tr>												
				</tbody>
			</table>			
			</form:form>
			
			<div style="clear; both;"></div>
			
			<p>
				<a href="${pageContext.request.contextPath}/note/list">Back to List</a>
			
		</div>	
	</body>
</html>