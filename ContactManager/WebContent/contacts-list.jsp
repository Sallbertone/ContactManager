<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta>
<title>Contact Manager</title>
</head>
<body>

<div class="container">

	<table>

		<tr>
			<th>ID</th>
			<th>Imię</th>
			<th>Nazwisko</th>
			<th>E-mail</th>
			<th>Telefon</th>
			<th>Grupa</th>
		</tr>
		
		<c:forEach var="tempContact" items="${CONTACT_LIST}">
		
		<tr>
			<td> ${tempContact.id} </td>
			<td> ${tempContact.firstName} </td>
			<td> ${tempContact.lastName} </td>
			<td> ${tempContact.email} </td>
			<td> ${tempContact.phoneNumber} </td>
			<td> ${tempContact.circle} </td>
		</tr>

		</c:forEach>

	</table>
</div>

</body>
</html>