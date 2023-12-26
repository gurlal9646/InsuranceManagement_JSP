<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Product"%>
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

	<%
	Integer RoleId = (Integer) session.getAttribute("RoleId");
	%>

	<%
	if (RoleId != null && RoleId == 1) {
	%>
	<%@ include file="NavMenuAdmin.jsp"%>
	<%
	} else {
	%>
	<%@ include file="NavMenuUser.jsp"%>
	<%
	}
	%>
	<div class="container custom-margin">

		<%
		if (RoleId != null && RoleId == 2) {
		%>
		<a class="btn btn-primary" href='AddProduct'>Add Product</a>
		<%
		}
		%>

		<div class="row">
			<div class="col">
				<h1>Products</h1>
			</div>
			<div class="col-md-4 search-bar">
				<input type="text" id="searchInput" class="form-control"
					placeholder="Search" onkeyup="filterProducts()">
			</div>
		</div>

		<table class="table" id="productTable">
			<thead>
				<tr>

					<%
					if (RoleId != null && RoleId == 1) {
					%>
					<th>User Name</th>
					<%
					}
					%>
					<th>Product Name</th>
					<th>Serial Number</th>
					<th>Purchase Date</th>
					<%
					if (RoleId != null && RoleId == 2) {
					%>
					<th>Action</th>
					<%
					}
					%>
				</tr>
			</thead>
			<tbody>
				<%
				ArrayList<Product> productList = (ArrayList<Product>) (request.getAttribute("UserProductList") != null
						? request.getAttribute("UserProductList")
						: new ArrayList<Product>());

				for (Product product : productList) {
				%>
				<tr>
					<%
					if (RoleId != null && RoleId == 1) {
					%>
					<td><%=product.getUserName()%></td>
					<%
					}
					%>
					<td><%=product.getProductName()%></td>
					<td><%=product.getSerialNo()%></td>
					<td><%=product.getPurchaseDate()%></td>
					<%
					if (RoleId != null && RoleId == 2) {
					%>
					<td><a href='update.jsp?productId=<%=product.getProductId()%>'
						class="btn btn-primary">Update</a> <a
						href='delete.jsp?productId=<%=product.getProductId()%>'
						class="btn btn-danger">Delete</a></td>
					<%
					}
					%>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>


	</div>
	<script>
		function filterProducts() {
			var input, filter, table, tr, td, i, j, txtValue;
			input = document.getElementById("searchInput");
			filter = input.value.toUpperCase();
			table = document.getElementById("productTable");
			tr = table.getElementsByTagName("tr");

			for (i = 1; i < tr.length; i++) {
				var display = false;
				for (j = 0; j < tr[i].cells.length; j++) {
					td = tr[i].cells[j];
					if (td) {
						txtValue = td.textContent || td.innerText;
						if (txtValue.toUpperCase().indexOf(filter) > -1) {
							display = true;
							break;
						}
					}
				}
				tr[i].style.display = display ? "" : "none";
			}
		}
	</script>
</body>
</html>
