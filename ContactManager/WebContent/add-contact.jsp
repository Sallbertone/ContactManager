<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Contact Manager | Add Contact</title>
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
				<li><a href="settings.jsp">SETTINGS</a></li>
			</ul>
		</nav>
	</div>
</header>

<section id="new-contact">
	<div class="container">
		<div id="add-form">
			<form action="ContactControllerServlet" method="POST">
				<input type="hidden" name="command" value="ADD">
				
				<label class="fields">First name:</label><input type="text" placeholder="First name" name="firstName"><br>
				<p>${FIRST_NAME_INVALID}</p>
				<label class="fields">Last name:</label><input type="text" placeholder="Last name" name="lastName"><br>
				<p>${LAST_NAME_INVALID}</p>
				<label class="fields">E-mail address:</label><input type="email" placeholder="E-mail" name="email"><br>
				<p>${EMAIL_INVALID}</p>
				<label class="fields">Mobile:</label><input type="text" placeholder="Phone number" name="phoneNumber"><br>
				<p>${PHONE_NUMBER_INVALID}</p>
				<div id="field-group">Group:</div>
					<div id="list-group">
						<ul>
							<li><input type="radio" name="circle" value="friend" id="1"><label class="radio-label" for="1">Friends</label></li>
							<li><input type="radio" name="circle" value="family" id="2"><label class="radio-label" for="2">Family</label></li>
							<li><input type="radio" name="circle" value="coworker" id="3"><label class="radio-label" for="3">Coworkers</label></li>
							<li><input type="radio" name="circle" value="other" id="4" checked><label class="radio-label" for="4">Others</label></li>
						</ul>
					</div>
				<div id="save-button">
					<input type="submit" value="SAVE" >
				</div>
			</form>

			<form action="ContactControllerServlet" method="GET">
				<input type="hidden" name="command" value="LIST">
				<div id="back-button">
					<input type="submit" value="BACK" >
				</div>
			</form>
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