<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Contact Manager</title>
</head>
<body>


<header>
	<div class="container">
		<div id="branding">
			<h1><span class="color">Contact</span> Manager</h1>
		</div>
		<nav>
			<ul>
				<li><a href="LLLLLLLLLLLLLLLLLLLLLL">LOGOUT</a></li>
				<li><a href="SSSSSSSSSSSSSSSSSSSSSS">SETTINGS</a></li>
			</ul>
		</nav>
	</div>
</header>

<section id="addandsearch">
	<div class="container">
		<div id="search">
			<form action="QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ">
				<input type="search" placeholder="Search for your contact..." name="search">
				<input type="submit" value="SEARCH" >
			</form>
		</div>

		<div id="add">
			<form action="add-contact.jsp">
				<input type="submit" value="ADD CONTACT">
			</form>
		</div>
	</div>
</section>


<section id="viewform">
	<div class="container">
		<div id="radio">
			<form action="ContactControllerServlet" method="GET">
				<input type="hidden" name="command" value="SORT">	
				
				<input type="radio" name="circle" value="friend" id="1" ${GROUP == 'friend'? 'checked':''}><label for="1">Friends</label> 
				<input type="radio" name="circle" value="family" id="2" ${GROUP == 'family'? 'checked':''}> <label for="2">Family</label> 
				<input type="radio" name="circle" value="coworker" id="3" ${GROUP == 'coworker'? 'checked':''}> <label for="3">Coworkers</label>
				<input type="radio" name="circle" value="other" id="4" ${GROUP == 'other'? 'checked':''}> <label for="4">Others</label>
				<input type="radio" name="circle" value="allcircles" id="5" ${GROUP == 'allcircles'? 'checked':''}> <label for="5">All contacts</label> 
				<input type="submit" value="Show contacts"> 
			</form> 
		</div>
	</div>
</section>


<section id="viewtable">
	<div class="container">
		<div id="result">
			<table>
				<tr>
					<th>FIRST NAME</th>
					<th>LAST NAME</th>
					<th>E-MAIL</th>
					<th>MOBILE</th>
					<th>GROUP</th>
				</tr>
				
				<c:forEach var="tempContact" items="${CONTACT_LIST}">
		
					<c:url var="tempLink" value="ContactControllerServlet" >
						<c:param name="command" value="PREPOPULATE"/>
						<c:param name="contactID" value="${tempContact.id}"/>
					</c:url>
					
					<c:url var="tempLink_delete" value="ContactControllerServlet" >
						<c:param name="command" value="DELETE"/>
						<c:param name="contactID" value="${tempContact.id}"/>
					</c:url>

				<tr>
					<td> ${tempContact.firstName} </td>
					<td> ${tempContact.lastName} </td>
					<td> ${tempContact.email} </td>
					<td> ${tempContact.phoneNumber} </td>
					<td> ${tempContact.circle} </td>
					
<%-- another way of passing parameters	
					<td>
					<form action="ContactControllerServlet">
					<input type="hidden" name="id" value="${tempContact.id}">
					<input type="hidden" name="command" value="PREPOPULATE">
					<input type="submit" value="EDIT">
					</form>
					</td>
--%>
					<td class="contact_action"><a href="${tempLink}">EDIT</a></td>
					<td class="contact_action"><a href="${tempLink_delete}" onclick="return confirm('Are you sure you want to delete this Contact?');">DELETE</a></td>
					<td class="contact_action">MESSAGE</td>
				</tr>
		
				</c:forEach>
					
			</table>
		</div>
	</div>
</section>

<footer>
	<div class="container">
		<p>Piotr Szewczul<br>
		piotr.szewczul@gmaii.com<br>
		605182777</p>
	</div>	
</footer>


</body>
</html>