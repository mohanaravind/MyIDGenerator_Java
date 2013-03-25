<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.expedients.worker.*"%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title>Welcome to Prime Counter</title>
<link rel="stylesheet" type="text/css" href="styles/styles.css" />
<link rel="stylesheet" type="text/css" href="styles/kube.css" />
</head>
<body>

	<% 
		//Create the worker object
		Worker objWorker = new Worker();	
	%>

	<header class="header">
		<div id="logo">			
			&nbsp;
		</div>
		<div id="title">
			Prime Counter
		</div>
	</header>
	<div id="content" class="container">
		<section>
			<h3>Submit your request:</h3>
			<div class="separator">&nbsp;</div>
			<form action="submit" method="post" class="forms">
				<ul>
					<li><label for="user_email" class="bold">Email</label> <input
						type="email" required name="user_email"
						placeholder="yourname@domain.com" id="user_email" /></li>
					<li><label for="prime_limit" class="bold">Limiting Number</label> <input
						type="number" required name="prime_limit" id="prime_limit" /></li>
					<li><input type="submit" name="send" class="btn"
						value="Submit" /></li>
				</ul>
			</form>
		</section>
		<section>
			<h3>Results:</h3>
			<div class="separator">&nbsp;</div>
			<table class="width-100 bordered">
				<thead class="thead-black">
					<tr>
						<th>Submitted By</th>
						<th>Limiting Number</th>
						<th>Prime Count</th>
						<th>Time taken</th>
					</tr>
				</thead>
				<tbody>					
						<%= objWorker.getResultsData() %>					
				</tbody>
			</table>
		</section>
	</div>

	<footer>© 2012 Expedients, MIS - Stuti, Aravind, Shreya,
		Swathi, Anjali</footer>

</body>
</html>