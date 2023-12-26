<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.CompanyProduct"%>
<%@ page import="java.util.ArrayList"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Management</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<style>
.custom-margin {
	margin-top: 40px; /* Custom top margin */
}
</style>
</head>
<body>
	<%@ include file="NavMenuAdmin.jsp"%>

	<div class="container custom-margin">
		<a class="btn btn-primary" href='AddCompanyProduct.jsp'>Add Product</a>

		<h1>Company Products</h1>

		<table class="table">
			<thead>
				<tr>
					<th>Product Name</th>
					<th>Category</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				ArrayList<CompanyProduct> productList = (ArrayList<CompanyProduct>) (request.getAttribute("CompanyProductList") != null
						? request.getAttribute("CompanyProductList")
						: new ArrayList<CompanyProduct>());

				for (CompanyProduct product : productList) {
				%>
				<tr>
					<td><%=product.getCompanyProductName()%></td>
					<td><%=product.getCategory()%></td>
					<td><%=product.getPrice()%></td>
					<td><a href='update.jsp?productId=<%=product.getCompanyProductId()%>'
						class="btn btn-primary">Update</a> <a
						href='delete.jsp?productId=<%=product.getCompanyProductId()%>'
						class="btn btn-danger">Delete</a>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>


	</div>
</body>
</html>
