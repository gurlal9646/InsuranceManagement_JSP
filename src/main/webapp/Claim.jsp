<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Claim"%>
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
		<a class="btn btn-primary" href='AddClaim'>Add Claim</a>
		<%
		}
		%>

		<div class="row">
			<div class="col">
				<h1>Claims</h1>
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
					<th>Description</th>
					<th>Claim Date</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				ArrayList<Claim> claimList = (ArrayList<Claim>) (request.getAttribute("claimList") != null
						? request.getAttribute("claimList")
						: new ArrayList<Claim>());

				for (Claim claim : claimList) {
				%>
				<tr>
					<%
					if (RoleId != null && RoleId == 1) {
					%>
					<td><%=claim.getUserName()%></td>
					<%
					}
					%>
					<td><%=claim.getProductName()%></td>
					<td><%=claim.getDescription()%></td>
					<td><%=claim.getClaimDate()%></td>
					<td>
						<%
						int status = claim.getStatus();
						String statusText = "";

						if (status == 1) {
							statusText = "Approved";
						} else if (status == 0) {
							statusText = "Pending";
						} else if (status == 2) {
							statusText = "Rejected";
						} else {
							statusText = "Unknown"; // Handle other status values if needed
						}

						out.print(statusText);
						%>
					</td>
					<%
					if (RoleId != null && RoleId == 2) {
					%>
					<td><a href='update.jsp?productId=<%=claim.getClaimId()%>'
						class="btn btn-primary">Update</a> <a
						href='delete.jsp?productId=<%=claim.getClaimId()%>'
						class="btn btn-danger">Delete</a></td>
					<%
					}
					%>
					<%
					if (RoleId != null && RoleId == 1) {
					%>
					<td>
						<!-- Button to trigger modal -->
						<button type="button" class="btn btn-info" data-toggle="modal"
							data-target="#updateStatusModal<%=claim.getClaimId()%>">
							Update Status</button> <!-- Modal -->
						<div class="modal fade"
							id="updateStatusModal<%=claim.getClaimId()%>" tabindex="-1"
							role="dialog" aria-labelledby="updateStatusModalLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="updateStatusModalLabel">Update
											Status</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<form action="UpdateClaim" method="post">
											<input type="hidden" name="claimId"
												value="<%=claim.getClaimId()%>">

											<!-- Radio buttons for status selection -->
											<div class="form-check">
												<input class="form-check-input" type="radio"
													name="statusRadios"
													id="pendingRadio<%=claim.getClaimId()%>" value="0"
													<%=(claim.getStatus() == 0) ? "checked" : ""%>> <label
													class="form-check-label"
													for="pendingRadio<%=claim.getClaimId()%>"> Pending
												</label>
											</div>
											<div class="form-check">
												<input class="form-check-input" type="radio"
													name="statusRadios"
													id="approveRadio<%=claim.getClaimId()%>" value="1"
													<%=(claim.getStatus() == 1) ? "checked" : ""%>> <label
													class="form-check-label"
													for="approveRadio<%=claim.getClaimId()%>"> Approve
												</label>
											</div>
											<div class="form-check">
												<input class="form-check-input" type="radio"
													name="statusRadios" id="rejectRadio<%=claim.getClaimId()%>"
													value="2" <%=(claim.getStatus() == 2) ? "checked" : ""%>>
												<label class="form-check-label"
													for="rejectRadio<%=claim.getClaimId()%>"> Reject </label>
											</div>

											<button type="submit" class="btn btn-primary">Save</button>
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">Close</button>
										</form>
									</div>

								</div>
							</div>
						</div>
					</td>
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
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
