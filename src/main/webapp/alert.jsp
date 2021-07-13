<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
<link rel="icon" type="image/png" sizes="16x16" href="common/images/1-hanbit.png">
<!-- Material Design for Bootstrap CSS -->
<!-- <link rel="stylesheet" href="common/css/style.css"> -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">
	
	<link href="common/css/materialize.fontawsome.css"
	rel="stylesheet">
<title>Alert</title>
</head>


<body  class="main-sidebar-show">
<%
		String user_id = (String) session.getAttribute("roleID");

	%>

	<%
		if (user_id == null) {
			response.sendRedirect("login.jsp");
		}else {
	%>
<div id="preloader">
  <div id="status">&nbsp;</div>
</div>
<jsp:include page="header.jsp" />
	<jsp:include page="slidebar.jsp" />
			 <div class="top-spacing"></div>
	 <div class="main-content side-content pt-0">
			<div class="container-fluid">
				<div class="inner-body custom-scrollbar-js" id="content-5">
				  <div class="row custom-scrollbar-css">
	<div class="col-md-12">
		<div class="row">
				<div class="col-md-12">
					<a class="text-dark" href="#">Home</a>
					<span>/</span>
					<span class="activeurl">Alert Settings</span>
				</div>
			</div>
			
			
		<div class="row mr-0 ml-0">
			
			<div class="right_data col-md-12 mt-4 mb-4">
				<!--Right start-->
				<div class="row">
					<div class="col-md-12">
						<table id="alertTable"
							class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							style="width: 100%">
							<thead>
								<tr>
									<th>MIU ID Interval (Min)</th>
									<th>Timeout</th>
									<th>ReConnection Charge</th>
									<th>Per Unit Value (KG)</th>
									<th>Per Late Fee</th>
									<th>GST</th>
									<th>Due Date Count</th>
									<th>Date</th>
									<th>Billing Date</th>
									<th>Edit</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>

				<!--Right end-->
			</div>
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	<jsp:include page="footer.jsp" />

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Alert Add
						Form</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="alertDetails">
						<div class="row">
							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">No MIU ID Interval</label> <input
										type="text" class="form-control" name="noamrintervalAdd"
										id="noamrintervalAdd">
								</div>
							</div>
							

							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">ReCharge Time Out</label> <input
										type="text" class="form-control" name="rechargetimeoutAdd"
										id="rechargetimeoutAdd">
								</div>
							</div>
							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">ReConnection Charge</label> <input
										type="text" class="form-control" name="reconnectionAdd"
										id="reconnectionAdd">
								</div>
								<input type = "hidden" id="alertIdhidden">
							</div>

								<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">Per Unit Charge</label> <input
										type="text" class="form-control" name="perUnitAdd"
										id="perUnitAdd">
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">Per Late Fee</label> <input
										type="text" class="form-control" name="lateFeeAdd"
										id="lateFeeAdd">
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">GST</label> <input
										type="text" class="form-control" name="gstAdd"
										id="gstAdd">
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="group form-group">
									<label class="bmd-label-floating">Due Date Count</label> <input
										type="text" class="form-control" name="duedatecountAdd"
										id="duedatecountAdd">
								</div>
							</div>
							<div class="col-md-6">
								
							</div>

							<div class="col-md-4">
							
									
									<button class="btn btn-secondary submit-button"
									 value="Save!" id="alertAdd"
									type="button" disabled>Save</button>
							</div>

							<div class="col-md-3">
								<button type="button" class="btn btn-secondary btn-raised mr-3 resetFilter" id="resetFilter">Reset</button>
							</div>


							<div class="col-md-4">
								<button type="button" class="btn btn-danger btn-raised mr-4"
									data-dismiss="modal">
									Close
									<div class="ripple-container"></div>
								</button>
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>




<div class="modal fade" id="myAlertEdit" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" align="center">Edit Alert</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form id="alertEdit">
						<div class="row">
							<div class="col-md-6">
								<div id="formnoamrintervalEdit" class="group form-group">
									<label class="bmd-label-floating">No MIU ID Interval</label> <input
										type="text" class="form-control" name="noamrintervalEdit"
										id="noamrintervalEdit">
								</div>
							</div>
						

							<div class="col-md-6">
								<div id="formrechargetimeoutEdit" class="group form-group">
									<label class="bmd-label-floating">ReCharge Time Out</label> <input
										type="text" class="form-control" name="rechargetimeoutEdit1"
										id="rechargetimeoutEdit1">
								</div>
							</div>
							
							<div class="col-md-6">
								<div id="formreconnectionEdit" class="group form-group">
									<label class="bmd-label-floating">ReConnection Charge</label> <input
										type="text" class="form-control" name="connectionEdit1"
										id="connectionEdit1">
								</div>
							</div>
							
							<div class="col-md-6">
								<div id="formperUnitEdit" class="group form-group">
									<label class="bmd-label-floating">Per Unit Charge</label> <input
										type="text" class="form-control" name="perUnitEdit1"
										id="perUnitEdit1">
								</div>
							</div>
							
							
							<div class="col-md-6">
								<div id="formlateFeeEdit" class="group form-group">
									<label class="bmd-label-floating">Late Fee</label> <input
										type="text" class="form-control" name="lateFeeEdit1"
										id="lateFeeEdit1">
								</div>
							</div>
							
							<div class="col-md-6">
								<div id="formgstEdit" class="group form-group">
									<label class="bmd-label-floating">GST</label> <input
										type="text" class="form-control" name="gstEdit1"
										id="gstEdit1">
								</div>
							</div>
							
							<div class="col-md-6">
								<div id="formduedatecountEdit" class="group form-group">
									<label class="bmd-label-floating">Due Date Count</label> <input
										type="text" class="form-control" name="duedatecountEdit1"
										id="duedatecountEdit1">
								</div>
							</div>
							
							</div> 
						<div class="row">
							<div class="col-md-12 text-right">
									<button class="btn btn-primary submit-button"
									 value="Save!" id="alertEditsave"
									type="button" disabled>Update</button>
							
								<button type="button" class="btn btn-danger btn-raised mr-3 resetFilter" id="resetFilter">Reset</button>
							
								<button type="button" class="btn btn-danger btn-raised mr-4"
									data-dismiss="modal">
									Close
									<div class="ripple-container"></div>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	<%} %>
	
	<!-- 	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
    integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
    crossorigin="anonymous"></script> -->

	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

<!-- 	<script src="common/js/bootstrap.min.js"></script>
 -->

	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

	<script src="js/alert.js"></script>
	<script src="js/common.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://unpkg.com/popper.js@1.12.6/dist/umd/popper.js"
		integrity="sha384-fA23ZRQ3G/J53mElWqVJEGJzU0sTs+SvzG8fXVWP+kJQ1lwFAOkcUOysnlKJC33U"
		crossorigin="anonymous"></script>
	<script
		src="https://unpkg.com/bootstrap-material-design@4.1.1/dist/js/bootstrap-material-design.js"
		integrity="sha384-CauSuKpEqAFajSpkdjv3z9t8E7RlpJ1UP0lKM/+NdtSarroVKu069AlsRPKkFBz9"
		crossorigin="anonymous"></script>
	<script>
		$(document).ready(function() {
			$('body').bootstrapMaterialDesign();
		});
	</script>
	<!-- <script src="https://code.jquery.com/jquery-3.3.1.js"></script> -->
	<script
		src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>

</html>