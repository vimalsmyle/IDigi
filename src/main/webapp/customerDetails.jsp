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
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">
	
	<link href="common/css/materialize.fontawsome.css"
	rel="stylesheet">

<title>Customer Management</title>
</head>


<body class="innerbody">
<%
		String user_id = (String) session.getAttribute("roleID");

	%>

	<%
		if (user_id == null) {
			response.sendRedirect("login.jsp");
		}else {
	%>
<div id="preloader" style="display:none;">
  <div id="status">&nbsp;</div>
</div>

	<jsp:include page="header.jsp" />
	<jsp:include page="slidebar.jsp" />
	
	
		<main class="col-md-10 float-left col px-5 pl-md-3 pt-2 pb-6 main">
		
		
	<div
		class="container-fluid topspacing bottomspacing pl-0 pr-0 mr-0 ml-0">
		
		<div class="row">
				<div class="col-md-12">
					<a class="text-dark" href="#">Home</a>
					<span>/</span>
					<span class="activeurl">Customer Details</span>
				</div>
			</div>
			
			
		<div class="row mr-0 ml-0">
			
			<div class="right_data col-md-12 mt-4 mb-4">
				<!--Right start-->
				<div class="row">
					<div class="col-md-12">
						<table id="customerTable"
							class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							style="width: 100%">
							<thead>
								<tr>
									<th>Community</th>
									<th>Block</th>
									<th>CRN Number</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>House No</th>
									<th>Meters</th>
									<th>Mobile</th>
									<th>Email</th>
									<th>Created By UserName</th>
									<th>Created By Role</th>
									<th>Registration Date</th>
									<th>Action</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
						
						
						<table id="customerTable1"
							class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							style="width: 100%">
							<thead>
								<tr>
									<th>Community</th>
									<th>Block</th>
									<th>CRN Number</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>House No</th>
									<th>Meters</th>
									<th>Mobile</th>
									<th>Email</th>
									<th>Created By UserName</th>
									<th>Created By Role</th>
									<th>Registration Date</th>
									<th>Action</th>
									<th>Action</th>
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
	</main>
	<jsp:include page="footer.jsp" />

	<div class="modal fade" id="filter" tabindex="-1" role="dialog"
		aria-labelledby="filterModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Customer Management Filter</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="row">
          <div class="col-md-6">
            <div class="form-group has-feedback has-success bmd-form-group is-filled">
									<label class="bmd-label-floating select-label">Select Community</label> 
									<select
										class="form-control" id="filterselectcommunityName" name="filterselectcommunityName" onchange="showBlockbyCommunity(this.value);">
									</select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group has-feedback has-success bmd-form-group is-filled">
									<label class="bmd-label-floating select-label">Select Block</label> <select
										class="form-control" id="filterselectBlockBasedonCommunity" name="filterselectBlockBasedonCommunity">
									</select>
								</div>
							</div>
        </div>
        
        <div class="modal-footer m-auto">
          <button type="button" class="btn btn-primary btn-raised mr-4" id="customerFilter">Filter</button>
          <button type="button" class="btn btn-danger btn-raised mr-4" data-dismiss="modal">Close<div class="ripple-container"></div></button>
          <button type="button" class="btn btn-secondary btn-raised mr-4" id="resetFilter">Reset</button>
          
        </div>
      </div>
    </div>
    </div>
  </div>

	<div class="modal fade" id="myCustomerEdit" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" align="center">Edit Customer</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form id="customerEdit">
						<div class="row">
							<div class="col-md-6">
								<div id="formcommunityNameEdit" class="form-group">
									<label class="bmd-label-floating">Community</label> 
									 <input
										type="text" class="form-control" name="communityNameEdit"
										id="communityNameEdit" disabled>
								</div>
							</div>
							<div class="col-md-6">
								<div id="formblockNameEdit" class="form-group">
									<label class="bmd-label-floating">Block</label> <input
										type="text" class="form-control" name="blockNameEdit"
										id="blockNameEdit" disabled>
								</div>
							</div>

							<div class="col-md-6">
								<div id="formfirstNameEdit" class="form-group">
									<label class="bmd-label-floating">First Name</label> <input
										type="text" class="form-control" name="firstNameEdit"
										id="firstNameEdit">
								</div>
							</div>
							<div class="col-md-6">
								<div id="formlastNameEdit" class="form-group">
									<label class="bmd-label-floating">Last Name</label> <input
										type="text" class="form-control" name="lastNameEdit"
										id="lastNameEdit" disabled>
								</div>
							</div>
							
							
							
							<div class="col-md-6">
								<div id="formhouseNoEdit" class="form-group">
									<label class="bmd-label-floating">House No.</label> <input
										type="text" class="form-control" name="houseNoEdit"
										id="houseNoEdit">
								</div>
							</div>
							<div class="col-md-6">
								<div id="formmobileNoEdit" class="form-group">
									<label class="bmd-label-floating">Mobile No</label> <input
										type="text" class="form-control" name="mobileNoEdit"
										id="mobileNoEdit">
								</div>
							</div>
							
							
							
							
							<div class="col-md-6">
								<div id="formemailEdit" class="form-group">
									<label class="bmd-label-floating">Email</label> <input
										type="email" class="form-control" name="emailEdit"
										id="emailEdit">
								</div>
							</div>
							<div class="col-md-6">
								<div id="formmeterSerialEdit" class="form-group">
									<label class="bmd-label-floating">MSN</label> <input
										type="text" class="form-control" name="meterSerialEdit"
										id="meterSerialEdit" disabled>
								</div>
							</div>
							
							
							
							
							<div class="col-md-6">
								<div id="formamrEdit" class="form-group">
									<label class="bmd-label-floating">MIU ID</label> <input
										type="text" class="form-control" name="amrEdit"
										id="amrEdit">
								</div>
							</div>
							
							<div class="col-md-6">
								<div id="formCRNEdit" class="form-group">
									<label class="bmd-label-floating">CRN Number</label> <input
										type="text" class="form-control" name="CRNEdit"
										id="CRNEdit" disabled>
										<input type = "hidden" id="customerIdhidden" />
								</div>
							</div>

							<div class="col-md-4">
								<input class="btn btn-success submit-button"
									 value="Update" id="customerEditsave"
									type="button" disabled />
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
	
	
	
	
	
	
	
	
	
	
	
	<div class="modal fade bd-example-modal-xl" id="myCustomerMeters" role="dialog">
		<div class="modal-dialog modal-xl
		">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" align="center">List of Meters</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					
					
					<table id="customerMeterTable"
							class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							style="width: 100%">
							<thead>
								<tr>
									<th>Customer Meter ID</th>
									<th>MIU ID</th>
									<th>Meter Serial Number</th>
									<th>Meter Type</th>
									<th>Meter Size</th>
										<th>Pay Type</th>
									<th>Tariff Name</th>
									<th>Gateway ID</th>
									<th>Location</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					
					
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<%} %>
   
	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>


	<script src="js/dropdown.js"></script>
	<script src="js/customer.js"></script>
	<script src="js/common.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
		
	<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.colVis.min.js"></script>
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
		
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>
		
		<script
		src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
		
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.bootstrap.min.js"></script>	
		
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
		
		<script
		src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>	
		
		<script
		src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
		
		
		
	<script
		src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
		
	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
		
		

	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>
		
		
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
		
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		
	<script>
		$(document).ready(function() {
			$('#customerTable').DataTable();
		});
	</script>

</body>

</html>