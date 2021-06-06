
$(document)
		.ready(
				function() {

					if (sessionStorage.getItem("roleID") == 2
							|| sessionStorage.getItem("roleID") == 5) {
						$("#communityNameAdd").val(
								sessionStorage.getItem("communityName"));
						$("#formcommunityNameAdd")
								.addClass(
										"group form-group has-feedback has-success bmd-form-group is-filled")
						$("#blockNameAdd").val(
								sessionStorage.getItem("blockName"));
						$("#formblockNameAdd")
								.addClass(
										"group form-group has-feedback has-success bmd-form-group is-filled")
					}

					if (sessionStorage.getItem("roleID") == 1
							|| sessionStorage.getItem("roleID") == 2) {
						$("#blockAddButton").show();
						var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
								+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>"
								+ "<'row'<'col-sm-12'tr>>"
								+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
					} else if (sessionStorage.getItem("roleID") == 3) {
						var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
								+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-5 total'>>"
								+ "<'row'<'col-sm-12'tr>>"
								+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
					} else {
						$("#customerAddd").remove();
						var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
								+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>"
								+ "<'row'<'col-sm-12'tr>>"
								+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
					}

					// alert((sessionStorage.getItem("roleID") == 3) ?
					// [0,1,2,11]:11);
					// alert(((sessionStorage.getItem("roleID") == 3)) ?
					// (sessionStorage.getItem("roleID") == 3)
					// :(((sessionStorage.getItem("roleID") == 1) ||
					// (sessionStorage.getItem("roleID") == 2) ||
					// (sessionStorage.getItem("roleID") == 3)) &&
					// (!(sessionStorage.getItem("roleID") == 5) ||
					// !(sessionStorage.getItem("roleID") == 4))));

					var filterId = sessionStorage.getItem("day") == "day" ? "1"
							: 0;
					sessionStorage.removeItem("day")

					$('#billingstatusTable1').hide();
					table = $('#billingstatusTable')
							.DataTable(
									{
										"dom" : dom1,
										"responsive" : true,
										/* "processing" : true, */
										"serverSide" : false,
										"bDestroy" : true,
										"bPaginate" : true,
										"pagging" : true,
										"bProcessing" : true,
										"ordering" : true,
										"order" : [ 0, "desc" ],
										"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
										"pageLength" : 5,
										"scrollY" : 324,
										"scrollX" : true,
										"ajax" : {
											"url" : "./billing/"
													+ sessionStorage
															.getItem("roleID")
													+ "/"
													+ sessionStorage
															.getItem("ID")
													+ "/" + filterId,
											"type" : "GET",
											"data" : function(search) {
											},
											"complete" : function(json) {
												console.log(json);

												/*if (sessionStorage
														.getItem("roleID") == 3) {
													$("div.total")
															.html(
																	'<b>MIU ID:</b> '
																			+ json.responseJSON.data[0].meterID
																			+ ' <b>CRN Number:</b> '
																			+ sessionStorage
																					.getItem("ID"));
												}*/
												return json.data;
											},
										},
										"columns" : [
												{
													"data" : "communityName",
													"defaultContent" : ""
												},
												{
													"data" : "blockName",
													"defaultContent" : ""
												},
												{
													"data" : "houseNumber",
													"defaultContent" : ""
												},
												{
													"data" : "totalConsumption",
													"defaultContent" : ""
												},
												{
													"data" : "totalAmount",
													"defaultContent" : ""
												},
												{
													"data" : "emergencyCredit",
													"defaultContent" : ""
												},
												{
													"data" : "alarmCredit",
													"defaultContent" : ""
												},
												{
													"data" : "modeOfPayment",
													"defaultContent" : ""
												},
												{
													"data" : "razorPayOrderID",
													"defaultContent" : ""
												},
												{
													"data" : "razorPayPaymentID",
													"defaultContent" : ""
												},
												{
													"data" : "razorPayRefundID",
													"defaultContent" : ""
												},
												{
													"data" : "RazorPayRefundStatus",
													"defaultContent" : ""
												},
												{
													"data" : "transactionDate",
													"defaultContent" : ""
												},
												{
													"data" : "transactedByUserName",
													"defaultContent" : ""
												},
												{
													"data" : "transactedByRoleDescription",
													"defaultContent" : ""
												},
												{
													"data" : "Status",
													"defaultContent" : ""
												},
												{
													"mData" : "action",
													"render" : function(data,
															type, row) {
														if (row.Status == "Failed") {
															return "<a onclick='getDeleteTransactionID("
																	+ row.transactionID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>delete</i>"
																	+ "</a>";

														} else if (row.Status == "Passed"
																|| row.Status == "Pending") {
															return "<a onclick='getReceiptTransactionID("
																	+ row.transactionID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>receipt</i>"
																	+ "</a>"
														} else if (row.Status == "Pending...waiting for acknowledge") {
															return "---"
														}

													}
												} ],
										"columnDefs" : [
												{
													// orderable : false,
													targets : (sessionStorage
															.getItem("roleID") == 3) ? [
															0, 1, 2, 3 ]
															: 11,
													visible : ((sessionStorage
															.getItem("roleID") == 3)) ? false
															: (((sessionStorage
																	.getItem("roleID") == 1)
																	|| (sessionStorage
																			.getItem("roleID") == 2) || (sessionStorage
																	.getItem("roleID") == 3)) && (!(sessionStorage
																	.getItem("roleID") == 5) || !(sessionStorage
																	.getItem("roleID") == 4)))

												}, {
													"className" : "dt-center",
													"targets" : "_all"
												} ],
										"buttons" : [
												{
													extend : 'excel',
													footer : 'true',
													//text : 'Excel',
													exportOptions : {
														columns : [ 0, 1, 2, 3,
																4, 5, 6, 7, 8,
																9, 10, 11, 12,
																13, 14, 15 ]
													},
													//className: 'custom-btn fa fa-file-excel-o',
													title : 'ReCharge Status'
												},

												{
													extend : 'pdf',
													footer : 'true',
													exportOptions : {
														columns : [ 0, 1, 2, 3,
																4, 5, 6, 7, 8,
																9, 10, 11, 12,
																13, 14, 15 ]
													},
													//className: 'custom-btn fa fa-file-pdf-o',
													//text : 'pdf',
													orientation : 'landscape',
													title : 'ReCharge Status',
													pageSize : 'LEGAL'
												},
												{

													className : 'customButton',
													text : "Filter",
													action : function(e, dt,
															button, config) {
														$('.customButton')
																.attr(
																		{
																			"data-toggle" : "modal",
																			"data-target" : "#filter"
																		});
													}
												} ],
												 initComplete: function() {
													   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
													   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
													  }
									});

					if (sessionStorage.getItem("roleID") == 3
							|| sessionStorage.getItem("roleID") == 2
							|| sessionStorage.getItem("roleID") == 5) {
						if(sessionStorage.getItem("roleID") == 3){
							
						}else{
						table.buttons($('a.customButton')).remove();
					}}
					$("div.headname").html('<h3>ReCharge Status</h3>');
					// $("div.total").html('MUI ID: '+data.meterID+ ' Total
					// Units: '+data.meterID);

					$("#customerFilter")
							.click(
									function() {

										var url = $(
												"#filterselectcommunityName")
												.val() == "-1" ? sessionStorage
												.getItem("roleID")
												+ "/0/-1/0"
												: $(
														"#filterselectBlockBasedonCommunity")
														.val() == "Select Block" ? $(
														"#filterselectcommunityName")
														.val() == "-1" ? sessionStorage
														.getItem("roleID")
														+ "/0/-1/-1"
														: sessionStorage
																.getItem("roleID")
																+ "/0/"
																+ $(
																		"#filterselectcommunityName")
																		.val()
																+ "/0"
														: "2/"
																+ $(
																		"#filterselectBlockBasedonCommunity")
																		.val()
																+ "/-1/0"

										$
												.ajax({
													type : "GET",
													contentType : "application/json",
													url : "./billing/"
															+ url,
													dataType : "JSON",

													success : function(d) {

														$('#billingstatusTable')
																.dataTable()
																._fnAjaxUpdate();
														$(
																"#billingstatusTable_wrapper")
																.hide();
														$("#filter").modal(
																"hide");
														$("#billingstatusTable1")
																.show();
														var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
																+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>"
																+ "<'row'<'col-sm-12'tr>>"
																+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
														var hCols = [ 3, 4 ];
														table = $(
																'#billingstatusTable1')
																.DataTable(
																		{

																			"dom" : dom1,
																			"responsive" : true,
																			/*
																			 * "processing" :
																			 * true,
																			 */
																			"serverSide" : false,
																			"bDestroy" : true,
																			"bPaginate" : true,
																			"pagging" : true,
																			"bProcessing" : true,
																			"ordering" : true,
																			"order" : [
																					0,
																					"desc" ],
																			"lengthMenu" : [
																					5,
																					10,
																					25,
																					30,
																					50,
																					75 ],
																			"pageLength" : 5,
																			"scrollY" : 324,
																			"scrollX" : false,
																			"data" : d.data,
																			"columns" : [
																					{
																						"data" : "communityName",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "blockName",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "houseNumber",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "meterID",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "amount",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "emergencyCredit",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "alarmCredit",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "modeOfPayment",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "razorPayOrderID",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "razorPayPaymentID",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "razorPayRefundID",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "RazorPayRefundStatus",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "transactionDate",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "transactedByUserName",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "transactedByRoleDescription",
																						"defaultContent" : ""
																					},
																					{
																						"data" : "RazorPayPaymentStatus",
																						"defaultContent" : ""
																					},
																					{
																						"mData" : "action",
																						"render" : function(
																								data,
																								type,
																								row) {
																							if (row.Status == "Failed") {
																								return "<a onclick='getDeleteTransactionID("
																										+ row.transactionID
																										+ ")'>"
																										+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>delete</i>"
																										+ "</a>";

																							} else if (row.Status == "Passed"
																									|| row.Status == "Pending") {
																								return "<a onclick='getReceiptTransactionID("
																										+ row.transactionID
																										+ ")'>"
																										+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer'>receipt</i>"
																										+ "</a>"
																							} else if (row.Status == "Pending...waiting for acknowledge") {
																								return "---"
																							}

																						}
																					} ],
																			"columnDefs" : [
																					{
																						// orderable
																						// :
																						// false,
																						targets : 11,
																						visible : (((sessionStorage
																								.getItem("roleID") == 1)
																								|| (sessionStorage
																										.getItem("roleID") == 2) || (sessionStorage
																								.getItem("roleID") == 3)) && (!(sessionStorage
																								.getItem("roleID") == 5) || !(sessionStorage
																								.getItem("roleID") == 4)))
																					},
																					{
																						"className" : "dt-center",
																						"targets" : "_all"
																					} ],
																			"buttons" : [
																					{
																						extend : 'excel',
																						footer : 'true',
																						//text : 'Excel',
																						//className: 'custom-btn fa fa-file-excel-o',
																						exportOptions : {
																							columns : [
																									0,
																									1,
																									2,
																									3,
																									4,
																									5,
																									6,
																									7,
																									8,
																									9,
																									10,
																									11,
																									12,
																									13,
																									14,
																									15 ]
																						},
																						title : 'Billing Status'
																					},

																					{
																						extend : 'pdf',
																						footer : 'true',
																						//className: 'custom-btn fa fa-file-excel-o',
																						exportOptions : {
																							columns : [
																									0,
																									1,
																									2,
																									3,
																									4,
																									5,
																									6,
																									7,
																									8,
																									9,
																									10,
																									11,
																									12,
																									13,
																									14,
																									15 ]
																						},
																						//text : 'pdf',
																						orientation : 'landscape',
																						title : 'Billing Status',
																						pageSize : 'LEGAL'
																					},
																					{
																						text : 'Reset',
																						action : function(
																								e,
																								dt,
																								node,
																								config) {
																							alert('Button activated');
																						},
																						className : 'customButton',

																						action : function(
																								e,
																								dt,
																								button,
																								config) {

																							window.location = "billingDetails.jsp"
																						}
																					} ],
																					 initComplete: function() {
																						   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
																						   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
																						  }
																		});
														if (sessionStorage
																.getItem("roleID") == 3) {
															table
																	.buttons(
																			$('a.customButton'))
																	.remove();
														}
														$("div.headname")
																.html(
																		'<h3>Billing Status</h3>');
													}
												});
										return false;
									});

					$("#resetFilter").on(function() {
						$("input:text").val("");
					});
				});