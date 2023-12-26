<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<%@ include file="NavMenuUser.jsp"%>

	<div class="container">
		<h1>Add Claim</h1>

		<form action="AddClaim" method="post">
			<%
			String error = (String) request.getAttribute("error");
			error = (error != null) ? error : "";
			%>
			<div class="text-danger"><%=error %></div>
			<div class="form-group">
				<label for="productId">Product:</label> <select class="form-control"
					id="productId" name="productId" required>
					<option value="" disabled selected>Select a product</option>
					<%
					List<Product> productList = (List<Product>) request.getAttribute("productList");

					if (productList != null && !productList.isEmpty()) {
						for (Product product : productList) {
					%>
					<option value="<%=product.getProductId()%>"><%=product.getProductName()%></option>
					<%
					}
					} else {
					%>
					<option value="">No products available</option>
					<%
					}
					%>
				</select>
			</div>

			<div class="form-group">
				<label for="claimDescription">Description:</label>
				<textarea type="text" class="form-control" id="claimDescription"
					name="claimDescription" required></textarea>
			</div>

			<div class="form-group">
				<label for="claimDate">Claim Date:</label> <input type="date"
					class="form-control" id="claimDate" name="claimDate" required>
			</div>

			<button type="submit" class="btn btn-primary">Add Claim</button>
			<a href='Product' class="btn btn-primary">Cancel</a>
		</form>
	</div>
</body>
</html>
