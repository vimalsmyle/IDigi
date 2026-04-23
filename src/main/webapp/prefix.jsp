<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
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

<title>Prefix Management</title>
</head>


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
	<div class="main-content side-content pt-0">
		<div class="container-fluid">
			<div class="inner-body custom-scrollbar-js" id="content-5">
				<div class="row custom-scrollbar-css">
					<div class="col-md-12">
						<div class="row">
							<div class="col-md-12">
								<a class="text-dark" href="home.jsp">Home</a> <span>/</span> <a
									class="text-dark" href="setup.jsp">Customer Setup</a> <span>/</span>
								<span class="activeurl">Prefix Management</span>
							</div>
						</div>

						<div class="row mr-0 ml-0">
							<div class="right_data col-md-12 mt-4 mb-4">
								<div id="loader" style="display: none;">
									<div id="status">&nbsp;</div>
								</div>
								<!--Right start-->
								<div class="row">
									<div class="col-md-12">
										<table id="blockTable"
											class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
											style="width: 100%">
											<thead class="bg-primary text-white">
												<tr>
													<th>Prefix Name</th>
													<th>Community</th>
													<th>Block</th>
													<th>MIU ID</th>
													<th>Meter Type</th>
													<th>Meter Size</th>
													<th>Pay Type</th>
													<th>Tariff</th>
													<th>Gateway</th>
													<th>Threshold Max</th>
													<th>Threshold Min</th>
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
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<h5>Add Prefix</h5>
				</div>

				<div class="modal-body">
					<form id="blockDetails">

						<div class="row">

							<div class="col-md-6">
								<div class="group form-group">
									<label>Prefix Name</label> <input type="text"
										class="form-control" id="prefixName" name="prefixName">
								</div>
							</div>

							<div class="col-md-6">
								<div
									class="form-group has-feedback has-success bmd-form-group is-filled">
									<label class="bmd-label-floating select-label">Community
										Name<span class="impp"><sup>*</sup></span>
									</label> <select class="form-control select3 form-control-sm select2"
										id="selectcommunityName" name="selectcommunityName"
										onchange="showBlockbyCommunity(this.value);">
									</select>
								</div>
							</div>
							<div class="col-md-6">
								<div
									class="form-group has-feedback has-success bmd-form-group is-filled">
									<label class="bmd-label-floating select-label">Select
										Block<span class="impp"><sup>*</sup></span>
									</label> <select class="form-control form-control-sm select2"
										id="selectBlockBasedonCommunity"
										name="selectBlockBasedonCommunity">
									</select>
								</div>
							</div>

							<div class="col-md-6">
								<label>MIU ID</label> <input type="text" class="form-control"
									id="miuID" name="miuID">
							</div>

							<div class="col-md-6">
								<label>Meter Type</label> <select class="form-control"
									id="meterType" name="meterType">
									<option>Water</option>
									<option>Gas</option>
								</select>
							</div>

							<div class="col-md-6">
								<label>Meter Size</label> <input type="number"
									class="form-control" id="meterSizeID" name="meterSizeID">
							</div>

							<div class="col-md-6">
								<label>Pay Type</label> <select class="form-control"
									id="payType" name="payType">
									<option>Prepaid</option>
									<option>Postpaid</option>
								</select>
							</div>

							<div class="col-md-6">
								<div
									class="group form-group has-feedback has-success bmd-form-group is-filled">
									<label class="bmd-label-floating">Tariff Name<span
										class="impp"><sup>*</sup></span></label> <select
										class="form-control form-control-sm" id="selectTariffName"
										name="selectTariffName">
									</select>
								</div>
							</div>

							<div class="col-md-6">
								<label>Gateway</label> <select class="form-control"
									id="gatewayIDAdd" name="gatewayIDAdd"></select>
							</div>

							<div class="col-md-6">
								<label>Threshold Max</label> <input type="number"
									class="form-control" id="thresholdMaximum"
									name="thresholdMaximum">
							</div>

							<div class="col-md-6">
								<label>Threshold Min</label> <input type="number"
									class="form-control" id="thresholdMinimum"
									name="thresholdMinimum">
							</div>

						</div>

						<div class="text-right mt-3">
							<button class="btn btn-primary submit-button" value="Save!"
								id="blockAdd" type="button" disabled>Save</button>
							<button type="button"
								class="btn btn-danger btn-raised resetFilter" id="resetFilter">Reset</button>
							<button type="button" class="btn btn-danger btn-raised"
								data-dismiss="modal">
								Close
								<div class="ripple-container"></div>
							</button>
						</div>

					</form>
				</div>

			</div>
		</div>
	</div>


	<div class="modal fade" id="editPrefixModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<h5>Edit Prefix</h5>
				</div>

				<div class="modal-body">
					<form id=blockEdit>

						<div class="row">

							<div class="col-md-6">
								<div class="group form-group" id="formprefixNameEdit">
									<label>Prefix Name</label> <input type="text"
										class="form-control" id="prefixNameEdit" name="prefixNameEdit">
								</div>
							</div>

							<div class="col-md-6">
								<div id="formcomunityNameEdit"
									class="form-group has-feedback has-success bmd-form-group is-filled">
									<label class="bmd-label-floating select-label">Community
										Name<span class="impp"><sup>*</sup></span>
									</label> <input type="text" class="form-control"
										name="selectcommunityNameEdit" id="selectcommunityNameEdit"
										disabled>

								</div>
								<input type="hidden" id="selectcommunityNameEditHidden"
									name="selectcommunityNameEditHidden" />

							</div>
							<div class="col-md-6">
								<div id="formblockNameEdit"
									class="form-group has-feedback has-success bmd-form-group is-filled">
									<label class="bmd-label-floating select-label">Select
										Block<span class="impp"><sup>*</sup></span>
									</label> <input type="text" class="form-control"
										name="selectBlockBasedonCommunityEdit"
										id="selectBlockBasedonCommunityEdit" disabled> <input
										type="hidden" id="selectBlockBasedonCommunityEditHidden"
										name="selectBlockBasedonCommunityEditHidden" />

								</div>
							</div>

							<div class="col-md-6">
								<div id="formMiuIdEdit"
									class="form-group has-feedback has-success bmd-form-group is-filled">
									<label>MIU ID</label> <input type="text" class="form-control"
										id="miuIDEdit" name="miuIDEdit">
								</div>
							</div>

							<div class="col-md-6" id="formmeterTypeEdit">
								<label>Meter Type</label> <select class="form-control"
									id="meterTypeEdit" name="meterTypeEdit">
									<option>Water</option>
									<option>Gas</option>
								</select>
							</div>

							<div class="col-md-6" id="formmeterSizeIDEdit">
								<label>Meter Size</label> <input type="number"
									class="form-control" id="meterSizeIDEdit"
									name="meterSizeIDEdit">
							</div>

							<div class="col-md-6" id="formpayTypeEdit">
								<label>Pay Type</label> <select class="form-control"
									id="payTypeEdit" name="payTypeEdit">
									<option>Prepaid</option>
									<option>Postpaid</option>
								</select>
							</div>

							<div class="col-md-6">
								<div
									class="group form-group has-feedback has-success bmd-form-group is-filled">
									<label class="bmd-label-floating">Tariff Name<span
										class="impp"><sup>*</sup></span></label> <select
										class="form-control form-control-sm" id="selectTariffNameEdit"
										name="selectTariffNameEdit">
									</select>
								</div>
							</div>

							<div class="col-md-6" id="formgatewayIDAddEdit">
								<label>Gateway</label> <select class="form-control"
									id="gatewayIDAddEdit" name="gatewayIDAddEdit"></select>
							</div>

							<div class="col-md-6" id="formthresholdMaximumEdit">
								<label>Threshold Max</label> <input type="number"
									class="form-control" id="thresholdMaximumEdit"
									name="thresholdMaximumEdit">
							</div>

							<div class="col-md-6" id="formthresholdMinimumEdit">
								<label>Threshold Min</label> <input type="number"
									class="form-control" id="thresholdMinimumEdit"
									name="thresholdMinimumEdit">
							</div>

						</div>

						<div class="text-right mt-3">
							<button class="btn btn-primary submit-button" value="Save!"
								id="blockEdit" type="button" disabled>Save</button>
							<button type="button"
								class="btn btn-danger btn-raised resetFilter" id="resetFilter">Reset</button>
							<button type="button" class="btn btn-danger btn-raised"
								data-dismiss="modal">
								Close
								<div class="ripple-container"></div>
							</button>
						</div>

					</form>

				</div>
			</div>
		</div>
	</div>

	<%
	}
	%>


	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>


	<script src="js/dropdown.js"></script>
	<script src="js/prefix.js"></script>
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
		src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>

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
			$('#blockTable').DataTable();
		});
	</script>

</body>

</html>