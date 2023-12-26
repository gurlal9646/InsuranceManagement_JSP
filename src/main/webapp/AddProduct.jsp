<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add Product</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="NavMenuUser.jsp"%>

    <div class="container">
        <h1>Add Product</h1>

        <form action="AddProduct" method="post">
            <div class="form-group">
                <label for="productName">Product Name:</label>
                <select class="form-control" id="productName" name="productName" required>
                    <option value="" disabled selected>Select a product</option>
                    <% 
                        List<String> productList = (List<String>) request.getAttribute("ProductNameList");

                        if (productList != null && !productList.isEmpty()) {
                            for (String product : productList) { 
                    %>
                                <option value="<%= product %>"><%= product %></option>
                    <% 
                            }
                        } else {
                    %>
                            <option value="">No products available</option>
                    <% } %>
                </select>
            </div>

            <div class="form-group">
                <label for="serialNumber">Serial Number:</label>
                <input type="text" class="form-control" id="serialNumber" name="serialNumber" required>
            </div>

            <div class="form-group">
                <label for="purchaseDate">Purchase Date:</label>
                <input type="date" class="form-control" id="purchaseDate" name="purchaseDate" required>
            </div>

            <button type="submit" class="btn btn-primary">Add Product</button>
            <a href='Product.jsp' class="btn btn-primary">Cancel</a>
        </form>
    </div>
</body>
</html>
