<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>Login Form</title>
<style>
body {
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 0;
}

.container {
	margin-top: 50px; /* Add some space from the top */
}

.card {
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-title {
	margin-bottom: 20px;
}

.signup-link {
	text-align: center;
	margin-top: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title text-center">Login</h5>
						<form action="LoginController" method="post">
							<%
							String error = (String) request.getAttribute("error");
							error = (error != null) ? error : "";
							%>
							<div class="text-danger"><%=error%></div>
							<div class="form-group">
								<label for="usernameEmail">Username/Email</label> <input
									type="text" class="form-control" id="usernameEmail"
									name="usernameEmail" placeholder="Enter username or email">
							</div>
							<div class="form-group">
								<label for="password">Password</label> <input type="password"
									class="form-control" id="password" name="password"
									placeholder="Enter password">
							</div>
							<button type="submit" class="btn btn-primary btn-block">Login</button>
						</form>
						<div class="signup-link">
							<p>
								Don't have an account? <a href="Signup.jsp">Sign up here</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
