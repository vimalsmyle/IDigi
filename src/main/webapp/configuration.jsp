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

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css"
	rel="stylesheet" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css">
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
								<a class="text-dark" href="home.jsp">Home</a> <span>/</span> <span
									class="activeurl">Configuration</span>
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
													<div class="row" id="row">
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
																	onchange="showCustomerbyBlockForConfig(this.value);">

																</select>
															</div>
														</div>


														<div class="col-md-4">
															<div class="group form-group">
																<label class="bmd-label-floating select-label">Select
																	CRN<sup class="imp">*</sup>
																</label> <select class="form-control form-control-sm"
																	id="selectHouseBasedonBlock"
																	name="selectHouseBasedonBlock"
																	onchange="showAllMetersDetails(this);">
																</select>
															</div>
														</div>

														<%
															} else if (user_id.equalsIgnoreCase("2")) {
														%>

														<div class="col-md-4">
															<div id="formcommunityNameAdd" class="group form-group">
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
																</label> <select class="form-control form-control-sm"
																	id="selectHouseBasedonBlock"
																	name="selectHouseBasedonBlock"
																	onchange="showMetersDetails(this.value);">
																</select>
															</div>
														</div>

														<%
															}
														%>
														<div class="col-md-4">
															<div class="group form-group">
																<label class="bmd-label-floating select-label">Select
																	Meters<sup class="imp">*</sup>
																</label> <select class="form-control form-control-sm select2"
																	id="selectMeters" name="selectMeters"
																	>
																</select>
															</div>
														</div>

														<div class="col-md-4" id="individualCommandId">
															<div
																class="group form-group has-feedback has-success bmd-form-group is-filled">
																<label class="bmd-label-floating select-label">Command
																	Type<sup class="imp">*</sup>
																</label> <select class="form-control" id="selectcommandType"
																	name="selectcommandType"
																	onchange="showFieldsBasedONCommand(this.value);">
																	<option style="color: Red" value="-1" selected>Select
																		Command Type</option>
																	<option value="1">Meter Reset</option>
																	<option value="3">Tariff</option>
																	<option value="4">Emergency Credit</option>
																	<option value="5">Valve</option>
																	<option value="6">RTC</option>
																	<option value="7">Schedule Disconnect</option>
																	<option value="8">Sync Interval</option>
																	<option value="9">Reading</option>
																	<option value="12">Clear Tamper</option>
																	<option value="13">Sync Time</option>

																</select>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>


	<script src="js/dropdown.js"></script>
	<script src="js/configuration.js"></script>
	<script src="js/common.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>

	

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.13.0/locale/nl.js"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>



	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>

	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


	<script>
		$(document).ready(function() {
			 $('#datetimepicker1').datetimepicker({
				    format: 'DD/MM/YYYY HH:mm'
				  });

		});
	</script>

</body>

</html>