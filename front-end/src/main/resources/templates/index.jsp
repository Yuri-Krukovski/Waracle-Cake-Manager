<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html lang="en">
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=yes">
<meta name="Description" content="Allocation Information." />
<meta name="Distribution" content="internal" />
<meta name="Author" content="Alvaro" />

<link rel="stylesheet" href="css/bootstrap/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap/bootstrap-responsive.css">
<link rel="stylesheet" href="css/bootstrap/bootstrap-responsive.min.css">
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="css/datatable/jquery.dataTables.min.css">

<script src="js/jquery/jquery-3.3.1.slim.min.js"></script>
<script src="js/jquery/jquery-3.3.1.js"></script>
<script src="js/popper/popper.min.js"></script>
<script src="js/bootstrap/bootstrap.js"></script>
<script src="js/datatable/jquery.dataTables.min.js"></script>

<title>Home - Cake Manager</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="span4">
				<h1>Cake Manager</h1>
				<p>Cake Manager App </p>
			</div>
			<!--  -->
			
			
			<div class="span12">
				<ul class="breadcrumb">
					<li class="active"><a
						href="<c:url value="/" />">Home</a> <span
						class="divider">/</span></li>

				</ul>
			</div>
		</div>

		<div class="row">
			<div class="table-responsive" style="text-align: center;">

				<c:choose>
					<c:when test="${not empty cakes}">
						<table id="tableResult" class="table-striped" border="0.2"
							style="margin: 0 auto;">
							<thead style="background-color: #0063be; color: #e8e9e9;">
								<th></th>
								<th>Cake ID</th>
								<th>Cake Name</th>
								<th>Cake Description</th>
								<th>Cake Price</th>
							</thead>
							<tbody style="">
								<c:forEach items='${cakes}' var='item' varStatus='loopStatus'>
									<tr>
										<td><input type="checkbox" name="users"
											value="<c:out value='${item.id}' />"></td>
										<td><c:out value='${item.id}' /></td>
										<td><c:out value='${item.cakeName}' /></td>
										<td><c:out value='${item.cakeDescription}' /></td>
										<td><c:out value='${item.cakePrice}' /></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<hr></hr>
						<center>
							<span class="label label-default NoResults">No results
								found</span>
							</center>
						<hr></hr>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<hr></hr>

		<div class="col-sm-4" style="text-align: center;">
		
		<div id="errorIndex" class="row" style="color:red;">${error}</div>
		<br></br>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newCakeModal">New Cake</button>
				<button type="button" class="btn btn-secondary" id="buttonDonwload">Download File</button>
		</div>

	<hr></hr>

	<!-- Modal -->
	<div class="modal fade" id="newCakeModal" tabindex="-1" role="dialog" aria-labelledby="newCake" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">New Cake</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="text-align:center;">
					<form id="FormNewCake" method="POST" action='<c:url value="/cakes" />' class="formName">
						

						<div class="form-group">

							<input type="text" id="cakeName" name="cakeName" maxlength="50"
								class="form-control" placeholder="cake name" required>
						</div>
						<div class="form-group">
							<input type="text" id="cakeDescription" name="cakeDescription" maxlength="100"
								class="form-control" placeholder="cake description" required>
						</div>
						<div class="form-group">
							<input type="text" id="cakePrice" name="cakePrice" maxlength="10" type="number"
								step="0.01" min="0" max="99999"class="form-control" placeholder="cake price"
								required>
						</div>
						<div id="errorModal">
						</div>
					</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="buttonNew">Save Changes</button>
			</div>
		</div>
	</div>
  </div> <!-- Modal -->
</div> <!-- Container -->

	<script type="text/javascript">
	
		$("#buttonDonwload").click(function() {
			var sURL = "<c:url value="/cakes" />";
			window.open(sURL, "_self","location=yes,height=570,width=520,scrollbars=yes,status=yes");
		});
	
		$("#buttonNew").click(function() {
			$("#errorModal").empty();
			
			if ($("#cakePrice").val()=="" || $("#cakeName").val()==""){
				$("#errorModal").append("<div style='color:red;'>Empty mandatory fields</div>");
			}else{
				if ($("#cakePrice").val().match(/[a-z]/i)){
					$("#errorModal").append("<div style='color:red;'>Incorrect Cake Price Format</div>");
				}else{
					$("#FormNewCake").submit();
				}
			}

		});

		$(document).ready(function() {
			$('#tableResult').DataTable();
		});
		
		
	</script>
</body>
</html>