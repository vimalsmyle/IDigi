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
<!-- <link rel="stylesheet" href="common/css/style.css"> -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-datetimepicker/2.7.1/css/bootstrap-material-datetimepicker.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


<link href="common/css/materialize.fontawsome.css" rel="stylesheet">

<title>Customer Management</title>
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
	<div id="preloader" style="display: none;">
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
						<div class="row mr-0 ml-0">

							<div class="right_data col-md-12 mt-4 mb-4">
								<!--Right start-->
								<div class="row">
									<div class="col-md-12">
										<h5 class="modal-title" id="exampleModalLabel">Manual
											Data Entry</h5>

										<form id="customerDetails">
											<div id="template">
												<div class="row">

												<div class="col-md-4">
														<div class="group form-group">
															<label class="bmd-label-floating select-label">MUI ID<span
																class="impp"><sup>*</sup></span></label> <input type="text"
																class="form-control form-control-sm" name="mui"
																id="mui">
														</div>
													</div>



													<div class="col-md-4">
														<div class="group form-group">
															<label class="bmd-label-floating select-label">Timestamp<span
																class="impp"><sup>*</sup></span></label> <input type="text"
																class="form-control form-control-sm" name="timestamp"
																id="timestamp">
														</div>
													</div>
													<div class="col-md-4">
														<div
															class="form-group has-feedback has-success bmd-form-group is-filled">
															<label class="bmd-label-floating select-label">Select
																type<span class="impp"><sup>*</sup></span>
															</label> <select class="form-control form-control-sm select2"
																id="type" name="type">
																<option value="1">Water</option>
																<option value="2">Gas</option>
															</select>
														</div>
													</div>
													<div class="col-md-4">
														<div class="group form-group">
															<label class="bmd-label-floating select-label">Sync
																Time<span class="impp"><sup>*</sup></span>
															</label> <input type="text" class="form-control form-control-sm"
																name="syncTime" id="syncTime">
														</div>
													</div>

													<div class="col-md-4">
														<div class="group form-group">
															<label class="bmd-label-floating select-label">Sync
																Interval<span class="impp"><sup>*</sup></span>
															</label> <input type="text" class="form-control form-control-sm"
																name="syncInterval" id="syncInterval">
														</div>
													</div>


													<div class="col-md-4">
														<div
															class="form-group has-feedback has-success bmd-form-group is-filled">
															<label class="bmd-label-floating select-label">Select
																Paid<span class="impp"><sup>*</sup></span>
															</label> <select class="form-control form-control-sm select2"
																id="paid" name="paid">
																<option value="0">Prepaid</option>
																<option value="1">Postpaid</option>
															</select>
														</div>
													</div>

													<div class="col-md-4">
														<div class="group form-group">
															<label class="bmd-label-floating select-label">Battery
																Voltage<span class="impp"><sup>*</sup></span>
															</label> <input type="text" class="form-control form-control-sm"
																name="batteryVoltage" id="batteryVoltage">
														</div>
													</div>



													<div class="col-md-4">
														<div
															class="form-group has-feedback has-success bmd-form-group is-filled">
															<label class="bmd-label-floating select-label">Select
																Valve<span class="impp"><sup>*</sup></span>
															</label> <select class="form-control form-control-sm select2"
																id="valve" name="valve">
																<option value="0">Close</option>
																<option value="1">Open</option>
																<option value="2">Auto</option>
															</select>
														</div>
													</div>

													<div class="col-md-4">
														<div class="group form-group">
															<label class="bmd-label-floating select-label">Credit<span
																class="impp"><sup>*</sup></span></label> <input type="text"
																class="form-control form-control-sm" name="credit"
																id="credit">
														</div>
													</div>

													<div class="col-md-4">
														<div
															class="form-group has-feedback has-success bmd-form-group is-filled">
															<label class="bmd-label-floating select-label">Select
																Tariff<span class="impp"><sup>*</sup></span>
															</label> <select class="form-control form-control-sm select2"
																id="tariff" name="tariff">

															</select>
														</div>
													</div>

													<div class="col-md-4">
														<div class="group form-group">
															<label class="bmd-label-floating select-label">Emergency
																Credit<span class="impp"><sup>*</sup></span>
															</label> <input type="text" class="form-control form-control-sm"
																name="emergencyCredit" id="emergencyCredit">
														</div>
													</div>


													<div class="col-md-4">
														<div class="group form-group">
															<label class="bmd-label-floating select-label">Min
																Elapsed<span class="impp"><sup>*</sup></span>
															</label> <input type="text" class="form-control form-control-sm"
																name="minElapsed" id="minElapsed">
														</div>
													</div>


													<div class="col-md-4">
														<div class="group form-group">
															<label class="bmd-label-floating select-label">Reading<span
																class="impp"><sup>*</sup></span></label> <input type="text"
																class="form-control form-control-sm" name="reading"
																id="reading">
														</div>
													</div>

<div class="custom-control custom-checkbox">
    <input type="checkbox" class="custom-control-input" id="door">
    <label class="custom-control-label" for="defaultUnchecked">Door Open</label>
</div>

<div class="custom-control custom-checkbox">
    <input type="checkbox" class="custom-control-input" id="magnetic">
    <label class="custom-control-label" for="defaultUnchecked">Magnetic</label>
</div>

<div class="custom-control custom-checkbox">
    <input type="checkbox" class="custom-control-input" id="schedule">
    <label class="custom-control-label" for="defaultUnchecked">Schedule Disconnect</label>
</div>

<div class="custom-control custom-checkbox">
    <input type="checkbox" class="custom-control-input" id="rtc">
    <label class="custom-control-label" for="defaultUnchecked">RTC</label>
</div>

<div class="custom-control custom-checkbox">
    <input type="checkbox" class="custom-control-input" id="low">
    <label class="custom-control-label" for="defaultUnchecked">Low Battery</label>
</div>

<div class="custom-control custom-checkbox">
    <input type="checkbox" class="custom-control-input" id="balance">
    <label class="custom-control-label" for="defaultUnchecked">Low balance</label>
</div>
												</div>
											</div>

											<div class="row mt-2">

												<div class="col-md-12 text-right">
													<button class="btn btn-primary submit-button" value="Save!"
														id="customerAdd" type="button" disabled>Save</button>
													<button type="button"
														class="btn btn-danger btn-raised resetFilter"
														id="resetFilter">Reset</button>

												</div>
											</div>
										</form>

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

	<%
		}
	%>


<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-datetimepicker/2.7.1/js/bootstrap-material-datetimepicker.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/locale/ja.js"></script>


	<script src="js/dropdown.js"></script>
	<script src="js/manualdata.js"></script>
	<script src="js/common.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
		

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>

	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script>
$(function () {
$('#timestamp').bootstrapMaterialDatePicker({
	 format: 'YYYY-MM-DD HH:mm:ss',
	clearBtn : true,
	todayBtn : "linked",
	weekStart : 1,
})


$('#syncTime').bootstrapMaterialDatePicker({
	 format: 'HH:mm:ss',
	clearBtn : true,
	todayBtn : "linked",
	weekStart : 1,
})

});
</script>
</body>

</html>