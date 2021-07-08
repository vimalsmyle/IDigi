<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
<link rel="icon" type="image/png" sizes="16x16"
	href="common/images/1-hanbit.png">
<!-- Material Design for Bootstrap CSS -->
<link rel="stylesheet" href="common/css/bootstrap.min.css">
<link rel="icon" type="image/png" sizes="16x16"
	href="common/images/1-hanbit.png">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">

<link href="common/css/materialize.fontawsome.css" rel="stylesheet">
<title>Configuration</title>
</head>


<body class="main-sidebar-show">
	<%
		String user_id = (String) session.getAttribute("roleID");
	%>

	<%
		if (user_id == null) {
			response.sendRedirect("login.jsp");
		} else {
	%>
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
								<a class="text-dark" href="home.jsp">Home</a> <span>/</span>
								<span class="activeurl">Configuration</span>
							</div>
						</div>

						<div class="row mr-0 ml-0">
							<div class="right_data col-md-12 mt-4 mb-4">
								<!--Right start-->
								<div class="row mb-4">
									<div class="col-md-10 m-auto">
										<div class="card">
											<div class="card-header bg-primary cardHeading">Configuration</div>
											<div class="card-body scroll right-block">
												<form id="configurationDetails1">
													<div class="row">
														<%
															if (user_id.equalsIgnoreCase("1")) {
														%>

														<div class="col-md-4">
															<div class="group form-group">
																<label class="bmd-label-floating select-label">Community<sup
																	class="imp">*</sup></label> <select class="form-control"
																	id="selectcommunityName" name="selectcommunityName"
																	onchange="showBlockbyCommunity(this.value);">
																</select>
															</div>
														</div>
														<div class="col-md-4">
															<div class="group form-group">
																<label class="bmd-label-floating select-label">Select
																	Block<sup class="imp">*</sup>
																</label> <select class="form-control"
																	id="selectBlockBasedonCommunity"
																	name="selectBlockBasedonCommunity"
																	onchange="showCustomerbyBlock(this.value);">

																</select>
															</div>
														</div>
														<div class="col-md-4">
															<div class="group form-group">
																<label class="bmd-label-floating select-label">Select
																	CRN<sup class="imp">*</sup>
																</label> <select class="form-control"
																	id="selectHouseBasedonBlock"
																	name="selectHouseBasedonBlock"
																	onchange="showTopupDetails(this.value);">
																</select>
															</div>
														</div>
														<%
															} else if (user_id.equalsIgnoreCase("2")) {
														%>

														<div class="col-md-4">
															<div id="formcommunityNameAdd"
																class="group form-group">
																<label class="bmd-label-floating">Community Name</label>
																<input type="text" class="form-control"
																	name="communityNameAdd" id="communityNameAdd" disabled>
															</div>
														</div>
														<div class="col-md-4">
															<div id="formblockNameAdd" class="group form-group">
																<label class="bmd-label-floating">Block Name</label> <input
																	type="text" class="form-control" name="blockNameAdd"
																	id="blockNameAdd" disabled>
															</div>
														</div>

														<div class="col-md-4">
															<div class="group form-group">
																<label class="bmd-label-floating select-label">Select
																	CRN<sup class="imp">*</sup>
																</label> <select class="form-control"
																	id="selectHouseBasedonBlock"
																	name="selectHouseBasedonBlock"
																	onchange="showTopupDetails(this.value);">
																</select>
															</div>
														</div>

														<%
															}
														%>
														<div class="col-md-4">
															<div id="formAMR_topup" class="group form-group">
																<label class="bmd-label-floating">MIU ID</label> <input
																	type="text" class="form-control" id="AMR_topup"
																	name="AMR_topup" disabled>
															</div>
														</div>

														<div class="col-md-4">
															<div
																class="group form-group has-feedback has-success bmd-form-group is-filled">
																<label class="bmd-label-floating select-label">Command
																	Type<sup class="imp">*</sup>
																</label> <select class="form-control" id="selectcommandType"
																	name="selectcommandType"
																	onchange="showFieldsBasedONCommand(this.value);">
																	<option style="color: Red" value="-1" selected>Select
																		Command Type</option>
																	<option value="5">Set RTC</option>
																	<option value="3">Clear Meter</option>
																	<!-- <option value="7">Active Mode</option> -->
																	<!-- <option value="10">Set Weekend</option> -->
																	<option value="1">Clear Tamper</option>
																	<option value="40">Valve Open</option>
																	<option value="0">Valve Close</option>
																	<!--  <option value="8">Shutdown Mode</option> -->
																	<option value="6">Set Meter Index</option>
																	<option value="10">Set Tariff</option>
																	<!-- <option value="9">Maintenance Mode</option> -->
																</select>
															</div>
														</div>


														<div class="col-md-4" id="conftariff"
															style="display: none">
															<div id="formtariff" class="group form-group">
																<label class="bmd-label-floating select-label">Tariff<sup
																	class="imp">*</sup></label> <select class="form-control"
																	id="selectTariffName" name="selectTariffName">
																</select>
															</div>
														</div>

														<div class="col-md-4" id="confdefaultReading"
															style="display: none">
															<div id="formdefaultReading"
																class="group form-group">
																<label class="bmd-label-floating">Default
																	Reading<sup class="imp">*</sup>
																</label> <input type="text" class="form-control"
																	id="defaultReading" name="defaultReading">
															</div>
														</div>
													</div>

													<div class="row">
														<div class="col-md-11">
															<button type="button" id="configuration"
																class="btn btn-primary submit-button btn-raised float-right mr-4">
																Submit
																<div class="ripple-container"></div>
															</button>
														</div>
														<div class="col-md-1">
															<button type="button"
																class="btn btn-secondary btn-raised mr-3 resetFilter"
																id="resetFilter">Reset</button>
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!--Right end-->
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />

	<%
		}
	%>

	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>


	<script src="js/dropdown.js"></script>
	<script src="js/configuration.js"></script>
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
		
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
		
	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
		
		

	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>
		
		
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
		
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

	<script>
		$(document).ready(function() {
			$('.button-left').click(function() {
				$('.left ').toggleClass('fliph');

			});

		});
	</script>
	<script>
		$(document).ready(function() {
			$('#communityTable').DataTable();
		});
	</script>

</body>

</html>