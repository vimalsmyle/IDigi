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

<title>Community Management</title>
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
            <div class="page-header">
                <h3 class="headname">Reports</h3>
            </div>
			<div class="row">
				<div class="col-xl-3 col-md-6 mb-4">
				
				
					<div class="card border-left-warning shadow sidingBlock">
					<a href="userReport.jsp"><div class="card-body p-3">
							<div class="row no-gutters justify-content-start f-30"><i class="fa fa-dashboard"></i></div>
							<div class="row no-gutters justify-content-start">
								<div class="text-xs font-weight-bold text-uppercase">
                                    User Management Report
								</div>
							</div>
						</div></a>
					</div>
					
				</div>
				<div class="col-xl-3 col-md-6 mb-4">
					<div class="card border-left-warning shadow sidingBlock">
						<a href="#"><div class="card-body p-3">
							<div class="row no-gutters justify-content-start f-30"><i class="fa fa-dashboard"></i></div>
							<div class="row no-gutters justify-content-start">
								<div class="text-xs font-weight-bold text-uppercase">
                                    ReCharge Summary
								</div>
							</div>
						</div></a>
					</div>
				</div>
				<div class="col-xl-3 col-md-6 mb-4">
					<div class="card border-left-warning shadow sidingBlock">
						<a href="#"><div class="card-body p-3">
							<div class="row no-gutters justify-content-start f-30"><i class="fa fa-dashboard"></i></div>
							<div class="row no-gutters justify-content-start">
								<div class="text-xs font-weight-bold text-uppercase">
                                    Financial Report
								</div>
							</div>
						</div></a>
					</div>
				</div>
				<div class="col-xl-3 col-md-6 mb-4">
					<div class="card border-left-warning shadow sidingBlock">
						<a href="#"><div class="card-body p-3">
							<div class="row no-gutters justify-content-start f-30"><i class="fa fa-dashboard"></i></div>
							<div class="row no-gutters justify-content-start">
								<div class="text-xs font-weight-bold text-uppercase">
                                   Alarm Report
								</div>
							</div>
						</div></a>
					</div>
				</div>
				
			
			</div>
		</main>
	<jsp:include page="footer.jsp" />

	

	
	<%} %>
   
	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

	
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
		


</body>

</html>