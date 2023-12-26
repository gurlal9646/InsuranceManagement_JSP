<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <title>Admin - Product Management</title>
  <style>
    body {
      padding-top: 56px;
      background-color: #f4f4f4; /* Background color for the body */
    }

    .navbar {
      background-color: #2c3e50; /* Dark blue background color */
      box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.1);
    }

    .navbar-brand {
      color: #ecf0f1 !important; /* Light gray text color */
    }

    .navbar-nav .nav-link {
      color: #ecf0f1 !important;
    }

    .navbar-text {
      color: #ecf0f1;
      margin-right: 20px;
    }

    .btn-logout {
      color: #2c3e50;
      border-color: #2c3e50;
      background-color: #ecf0f1;
    }

    .btn-logout:hover {
      background-color: #34495e;
      color: #ecf0f1;
    }
  </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light fixed-top">
  <a class="navbar-brand" href="#">Admin - Product Management</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="CompanyProduct">Products <span class="sr-only">(current)</span></a>
      </li>
        <li class="nav-item active">
        <a class="nav-link" href="Product">User Products <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="Claim">User Claims</a>
      </li>
    </ul>
    <a class="btn btn-outline-secondary my-2 my-sm-0 btn-logout" href="Login.jsp">Logout</a>
  </div>
</nav>
</body>
</html>
