
$(document)
		.ready(
				function() {

					var dom1 = "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-6'f>>"
							+ "<'row'<'col-sm-12'tr>>"
							+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";

					var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
							+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>"
							+ "<'row'<'col-sm-12'tr>>"
							+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";

					var filterId = (sessionStorage.getItem("filterId") == null) ? 0
							: (sessionStorage.getItem("filterId"));
					// sessionStorage.remove("filterId");
					//var hCols = [ 3, 4 ];
					// DataTable initialisation
					$('#liveTable')
							.DataTable(
									{
										"dom" : dom1,

										responsive : {
											details : {
												renderer : function(api, rowIdx) {
													var data = api
															.cells(rowIdx,
																	':hidden')
															.eq(0)
															.map(
																	function(
																			cell) {
																		var header = $(api
																				.column(
																						cell.column)
																				.header());
																		return '<p style="color:#00A">'
																				+ header
																						.text()
																				+ ' : '
																				+ api
																						.cell(
																								cell)
																						.data()
																				+ '</p>'; // changing
																							// details
																							// mark
																							// up.
																	})
															.toArray().join('');

													return data ? $('<table/>')
															.append(data)
															: false;
												}
											}
										},
										"language" : {
											"emptyTable" : "No data available in table"
										},

										processing : true,
										serverSide : false,
										fixedColumns : true,
										autoWidth : true,
										responsive : true,
										deferRender : true,
										processing : true,
										paging : true,
										pageLength : 5,
										searching : true,
										info : true,
										"ordering" : true,
										"order" : [ 0, "desc" ],
										"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
										bPaginate : false,
										"scrollY" : 300,
										"scrollX" : true,
										"ajax" : {
											"url" : "./dashboard/"
													+ $("#type").val() + "/"
													+ $("#comName").val() + "/"
													+ $("#blockName").val()
													+ "/0/0",
											"type" : "GET",
											"data" : function(search) {
											},
											"complete" : function(json) {
												console.log(json.data);
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
													"data" : "HouseNumber",
													"defaultContent" : ""

												},
												{
													// "data": "timeStamp",

													"mData" : "action",
													"render" : function(data,
															type, row) {
														return "<span id=color"
																+ ">"
																+ row.firstName
																+ " "
																+ row.lastName
																+ "</span>"
													},
													"defaultContent" : ""
												},
												{
													"data" : "customerUniqueID",
													"defaultContent" : ""
												},
												{
													"mData" : "action",
													"render" : function(data,
															type, row) {

														return "<a href=# id=CustomerMeters data-toggle=modal data-target=#myCustomerMeters onclick='getCustomerMeters(\""
																+ row.customerUniqueID
																+ "\")'>"
																+ "Multiple"
																+ "</a>"

													}

												},
												{
													"data" : "communicating",
													"defaultContent" : ""
												},
												{
													"data" : "nonCommunicating",
													"defaultContent" : ""
												}, {
													"data" : "total",
													"defaultContent" : ""
												} ],
										"columnDefs" : [
												/*{
													"orderable" : true,
													"targets" : [ 0, 1, 2, 3,
															4, 5, 6, 7,8 ],
													"className" : "dt-center",
													"targets" : "_all"
												}, {
													orderable : false,
													targets : [9 ]
												} */],

										"buttons" : [
												{
													extend : 'excel',
													footer : 'true',
													// text : 'Excel',
													title : 'Dashboard',
												// className: 'custom-btn fa
												// fa-file-excel-o'
												},

												{
													extend : 'pdf',
													footer : 'true',
													exportOptions : {
														columns : [ 0, 1, 2, 3,
																4, 5, 6, 7,8 ]
													},
													orientation : 'landscape',
													title : 'Dashboard',
													// className: 'custom-btn fa
													// fa-file-pdf-o',
													pageSize : 'LEGAL'
												} ],
										initComplete : function() {
											$('.buttons-excel')
													.html(
															'<i class="fa fa-file-excel-o" />')
											$('.buttons-pdf')
													.html(
															'<i class="fa fa-file-pdf-o" />')
										}

									})

				
				});

function getCustomerMeters(CRNNumber) {
	$
			.getJSON(
					"./dashboard/" + $("#type").val() + "/"
							+ $("#comName").val() + "/" + $("#blockName").val()
							+ "/"+CRNNumber+"/0",
					function(data) {
						$
								.each(
										data.data,
										function(i, item) {
											if (CRNNumber == item.customerUniqueID) {

												$('#customerMeterTable')
														.DataTable(
																{
																	"dom" : "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>"
																			+ "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>"
																			+ "<'row'<'col-sm-12'tr>>"
																			+ "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",

																	"language" : {
																		"emptyTable" : "No data available in table"
																	},

																	"responsive" : true,
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

																	"scrollX" : true,
																	"data" : item.dasboarddata,

																	"columns" : [
																			{
																				"data" : "miuID"

																			},
																			{
																				"data" : "meterSerialNumber"
																			},
																			{
																				"data" : "reading"
																			},
																			{
																				"data" : "consumption"

																			},
																			{
																				"data" : "balance"
																			},
																			{
																				"data" : "emergencyCredit"
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.batteryColor
																							+ ">"
																							+ row.battery
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.valveStatusColor
																							+ ">"
																							+ row.valveStatus
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"data" : "tariff"
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.dooropentamperColor
																							+ ">"
																							+ row.doorOpenTamper
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.magneticTamper
																							+ ">"
																							+ row.magnetictamperColor
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.dateColor
																							+ ">"
																							+ row.timeStamp
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"data" : "communicationStatus"
																			},
																			{
																				"mData" : "action",
																				"render" : function(
																						data,
																						type,
																						row) {
																					return "<span id=color style = color:"
																							+ row.vacationColor
																							+ ">"
																							+ row.vacationStatus
																							+ "</span>"
																				},
																				"defaultContent" : ""
																			},
																			{
																				"data" : "lastTopupAmount"
																			}

																	],
																	"columnDefs" : [],

																	"buttons" : []
																})

											} else {
											}
										});
						$('#myCustomerMeters').modal('show');
					});

}
