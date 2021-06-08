<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
<link rel="icon" type="image/png" sizes="16x16"
	href="common/images/1-hanbit.png">
<!-- Material Design for Bootstrap CSS -->
<!-- <link rel="stylesheet" href="common/css/style.css"> -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">

<link href="common/css/materialize.fontawsome.css" rel="stylesheet">

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.css"
	rel="stylesheet" type="text/css" />

</head>
<body>
<body class="main-sidebar-show">
	<%
		String user_id = (String) session.getAttribute("roleID");

		if (user_id == null) {
			response.sendRedirect("login.jsp");
		} else {
	%>
	<div id="preloader">
		<div id="status">&nbsp;</div>
	</div>
	<jsp:include page="header.jsp" />
	<jsp:include page="slidebar.jsp" />
	<div class="top-spacing"></div>
	<div class="main-content side-content pt-0 custom-scrollbar-js">
		<div class="container-fluid">
			<div class="inner-body custom-scrollbar-js" id="content-5">
				<div class="row custom-scrollbar-css">
					<div class="col-md-12">


						<div id="accordion">
							<div class="card">
								<div class="card-header">
									<span class="mr-auto">Report 1</span> <a
										class="card-link float-right" data-toggle="collapse"
										href="#collapseOne"> <i
										class="fa fa-angle-down reportarrow" aria-hidden="true"></i>
									</a>
								</div>
								<div id="collapseOne" class="collapse show"
									data-parent="#accordion">
									<div class="card-body">
										<div class="card-body">
											<form>
												<div class="row">
													<%
														if (user_id.equalsIgnoreCase("1") || user_id.equalsIgnoreCase("4")) {
													%>

													<div class="col-md-4">
														<div class="group form-group">
															<label class="bmd-label-floating select-label">Type<sup
																class="imp">*</sup></label> <select class="form-control"
																id="reportType" name="reportType"
																onchange="showReportType(this.value);">
																<option value='-1'>Select Type</option>
																<option value='Graph'>Graph</option>
																<option value='Tabular'>Tabular</option>
															</select>
														</div>
													</div>


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
														} else if (user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("5")) {
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
															</label> <select class="form-control"
																id="selectHouseBasedonBlock"
																name="selectHouseBasedonBlock">
															</select>
														</div>
													</div>

													<%
														}
															if (user_id.equalsIgnoreCase("3")) {
													%>

													<div class="col-md-4">
														<div id="formCRNNumber" class="group form-group">
															<label class="bmd-label-floating">CRN Number</label> <input
																type="text" class="form-control"
																id="selectHouseBasedonBlock"
																name="selectHouseBasedonBlock" disabled>
														</div>
													</div>

													<%
														}
													%>

													<div class="col-md-4" id="divStartDate">
														<div id="formcurrentBalance_topup"
															class="group form-group">
															<label class="bmd-label-floating">Start Date<sup
																class="imp">*</sup></label>
															<!--  <input type="text" class="form-control datepicker" id="start_date" name="start_date"> -->
															<input type="text" id="start_date" name="start_date"
																class="form-control">
														</div>
													</div>
													<div class="col-md-4" id="divEndDate">
														<div id="formdateTime_topup" class="group form-group">
															<label class="bmd-label-floating">End Date Time<sup
																class="imp">*</sup></label> <input type="text"
																class="form-control" id="end_date" name="end_date">
														</div>
													</div>


													<div class="col-md-4" id="fromMonth">
														<div id="formfromMonth_topup" class="group form-group">
															<label class="bmd-label-floating">From Month<sup
																class="imp">*</sup></label> <select class="form-control"
																id="fromMonth_topup" name="fromMonth_topup">
																<option value='-1'>Select Month</option>
																<option value='01'>January</option>
																<option value='02'>February</option>
																<option value='03'>March</option>
																<option value='04'>April</option>
																<option value='05'>May</option>
																<option value='06'>June</option>
																<option value='07'>July</option>
																<option value='08'>August</option>
																<option value='09'>September</option>
																<option value='10'>October</option>
																<option value='11'>November</option>
																<option value='12'>December</option>
															</select>
														</div>
													</div>
													
													
													<div class="col-md-4" id="toMonth">
														<div id="formfromMonth_topup" class="group form-group">
															<label class="bmd-label-floating">To Month<sup
																class="imp">*</sup></label> <select class="form-control"
																id="toMonth_topup" name="toMonth_topup">
																<option value='-1'>Select Month</option>
																<option value='01'>January</option>
																<option value='02'>February</option>
																<option value='03'>March</option>
																<option value='04'>April</option>
																<option value='05'>May</option>
																<option value='06'>June</option>
																<option value='07'>July</option>
																<option value='08'>August</option>
																<option value='09'>September</option>
																<option value='10'>October</option>
																<option value='11'>November</option>
																<option value='12'>December</option>
															</select>
														</div>
													</div>


													<div class="col-md-4" id="year">
														<div id="formtoYear_topup" class="group form-group">
															<label class="bmd-label-floating">To Year<sup
																class="imp">*</sup></label> <select class="form-control"
																id="toYear_topup" name="toYear_topup">
																<option value='-1'>Select Year</option>
																<option value='2019'>2019</option>
																<option value='2020'>2020</option>
																<option value='2021'>2021</option>
																<option value='2022'>2022</option>
																<option value='2023'>2023</option>
																<option value='2024'>2024</option>
																<option value='2025'>2025</option>
																<option value='2026'>2026</option>
																<option value='2027'>2027</option>
																<option value='2028'>2028</option>
																<option value='2029'>2029</option>
																<option value='2030'>2030</option>
															</select>
														</div>
													</div>

												</div>

												<div class="row">
													<div class="col-md-11">
														<button type="button" id="userconsumption"
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




						<div class="row mt-4">
							<div class="col-md-12">
								<table id=userConsumptionsTable
							class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							style="width: 100%">
							<thead id="theadBody">
							</thead>
							<tbody>
							</tbody>
						</table>
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

	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>


	<script src="js/dropdown.js"></script>
	<script src="js/userConsumptions.js"></script>
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
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>

	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>



	<script type="text/javascript">
		$(document).ready(
				function() {
					var date = new Date();
					var currentMonth = date.getMonth();
					var currentDate = date.getDate();
					var currentYear = date.getFullYear();
					/* $('#start_date').bootstrapMaterialDatePicker
					({
						time: true,
						clearButton: true,
						format: 'YYYY-MM-DD HH:mm',
						 maxDate: new Date(currentYear, currentMonth, currentDate)
					}); */

					$('#start_date').datepicker({
						todayHighlight : true,
						autoclose : true,
						format : "yyyy-mm-dd",
						clearBtn : true,
						todayBtn : "linked",
						weekStart : 1
					}).on('changeDate', function(e) {
						var startDate = $('#start_date').datepicker('getDate');
						$('#end_date').datepicker('setStartDate', startDate);
					});
					var lastDate = new Date(new Date().getFullYear(),
							new Date().getMonth() + 1, 0);

					$('#end_date').datepicker({
						todayHighlight : true,
						autoclose : true,
						format : "yyyy-mm-dd",
						clearBtn : true,
						todayBtn : "linked",
						weekStart : 1
					}).on('changeDate', function(e) {
						var endDate = $('#end_date').datepicker('getDate');
						$('#start_date').datepicker('setEndDate', lastDate);
					});
				});
	</script>

</body>
</html>