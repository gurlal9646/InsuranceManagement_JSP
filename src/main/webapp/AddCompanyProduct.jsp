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
<title>Your Form Title</title>
</head>
<body>
	<%@ include file="NavMenuAdmin.jsp"%>


	<div class="container mt-5">
	    <h1>Add Company Product</h1>
		<form action="AddCompanyProduct" method="post">
			<div class="form-group">
				<label for="productName">Product Name</label> <input type="text"
					class="form-control" id="productName" name="productName"
					placeholder="Enter product name">
			</div>
			<div class="form-group">
				<label for="category">Category</label> <input type="text"
					class="form-control" id="category" name="category" placeholder="Enter category">
			</div>
			<div class="form-group">
				<label for="price">Price</label> <input type="text"
					class="form-control" id="price" name="price" placeholder="Enter price">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>
