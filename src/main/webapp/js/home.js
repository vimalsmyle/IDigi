/**
 * 
 */
$(document)
		.ready(
				function() {

					$("#highchart_container2").hide();

					if (sessionStorage.getItem("roleID") != 3) {

						$
								.getJSON(
										"./homedashboard/Gas/"
												+ sessionStorage
														.getItem("roleID")
												+ "/"
												+ sessionStorage.getItem("ID"),
										function(data) {
											// var Options = "";
											document
													.querySelector("#gasActive").innerText = data.active;
											document
													.querySelector("#gasInactive").innerText = data.inActive;
											document.querySelector("#gasLive").innerText = data.nonLive;
											document
													.querySelector("#gasnonLive").innerText = data.live;
											document
													.querySelector("#gasemergency").innerText = data.emergency;
											document
													.querySelector("#gasLowbattery").innerText = data.lowBattery;
											// document.querySelector("#gasActivePercentage").innerText
											// = data.activePercentage;
											// document.querySelector("#gasinactivePercentage").innerText
											// = data.inActivePercentage;

										});

						$
								.getJSON(
										"./homedashboard/water/"
												+ sessionStorage
														.getItem("roleID")
												+ "/"
												+ sessionStorage.getItem("ID"),
										function(data) {
											// var Options = "";
											document
													.querySelector("#waterActive").innerText = data.active;
											document
													.querySelector("#waterInactive").innerText = data.inActive;
											document
													.querySelector("#waterLive").innerText = data.nonLive;
											document
													.querySelector("#waternonLive").innerText = data.live;
											document
													.querySelector("#wateremergency").innerText = data.emergency;
											document
													.querySelector("#waterLowbattery").innerText = data.lowBattery;
											// document.querySelector("#waterActivePercentage").innerText
											// = data.activePercentage;
											// document.querySelector("#waterinactivePercentage").innerText
											// = data.inActivePercentage;

										});

						$
								.ajax({
									type : "GET",
									contentType : "application/json",
									url : "./graph/Gas/0/0/"
											+ sessionStorage.getItem("ID"),
									dataType : "JSON",

									success : function(d) {

										$('#container')
												.highcharts(
														{
															chart : {
																type : 'column'
															},
															title : {
																text : ''
															},
															xAxis : {
																categories : d.xAxis
															},
															plotOptions : {
																series : {
																	cursor : 'pointer',
																	pointWidth : 20,
																	point : {
																		events : {
																			click : function() {
																				// alert('Category:
																				// ' +
																				// this.category
																				// + ',
																				// value:
																				// ' +
																				// this.y);
																				window.location = "blockDashboard.jsp?com="
																						+ this.category;

																			}
																		}
																	}
																}
															},

															series : [ {
																data : d.yAxis,
																name : 'Consumption'

															} ]

														});
									}
								});

						$
								.ajax({
									type : "GET",
									contentType : "application/json",
									url : "./graph/Water/0/0/"
											+ sessionStorage.getItem("ID"),
									dataType : "JSON",

									success : function(d) {

										$('#container1')
												.highcharts(
														{
															chart : {
																type : 'column'
															},
															title : {
																text : ''
															},
															xAxis : {
																categories : d.xAxis
															},

															plotOptions : {
																series : {
																	cursor : 'pointer',
																	pointWidth : 20,
																	point : {
																		events : {
																			click : function() {
																				// alert('Category:
																				// ' +
																				// this.category
																				// + ',
																				// value:
																				// ' +
																				// this.y);
																				window.location = "blockDashboard.jsp?com="
																						+ this.category;

																			}
																		}
																	}
																}
															},

															series : [ {
																data : d.yAxis,
																name : 'Consumption'
															} ]

														});
									}
								});

					}
					if (sessionStorage.getItem("roleID") == 3) {

						$.ajax({
							type : "GET",
							contentType : "application/json",
							url : "./all/0/0" + "/"
									+ sessionStorage.getItem("ID"),
							dataType : "JSON",

							success : function(d) {

								Highcharts.chart('container', {

									title : {
										text : 'CRN/CAN/CID: '
												+ sessionStorage
														.getItem("userID")
									},

									yAxis : {
										title : {
											text : 'Number of Consumption'
										}
									},

									xAxis : {
										/*
										 * accessibility: { rangeDescription:
										 * 'Range: 2010 to 2017' }
										 */
										categories : d.xAxis
									},

									legend : {
										layout : 'vertical',
										align : 'right',
										verticalAlign : 'middle'
									},

									plotOptions : {
										series : {
											label : {
												connectorAllowed : false
											},
											pointStart : 2010
										}
									},

									series : [
											{
												name : 'Installation',
												data : [ 43934, 52503, 57177,
														69658, 97031, 119931,
														137133, 154175 ]
											},
											{
												name : 'Manufacturing',
												data : [ 24916, 24064, 29742,
														29851, 32490, 30282,
														38121, 40434 ]
											},
											{
												name : 'Sales & Distribution',
												data : [ 11744, 17722, 16005,
														19771, 20185, 24377,
														32147, 39387 ]
											},
											{
												name : 'Project Development',
												data : [ null, null, 7988,
														12169, 15112, 22452,
														34400, 34227 ]
											},
											{
												name : 'Other',
												data : [ 12908, 5948, 8105,
														11248, 8989, 11816,
														18274, 18111 ]
											} ],

									responsive : {
										rules : [ {
											condition : {
												maxWidth : 500
											},
											chartOptions : {
												legend : {
													layout : 'horizontal',
													align : 'center',
													verticalAlign : 'bottom'
												}
											}
										} ]
									}

								});
							}

						});

						$(document)
								.on(
										'click',
										'#view',
										function() {

											var month = $("#month").val();
											var year = $("#start_date").val();

											$("#highchart_container1").hide();
											$("#highchart_container2").show();

											$
													.ajax({
														type : "GET",
														contentType : "application/json",
														url : "./graph/"
																+ $(
																		"#start_date")
																		.val()
																+ "/"
																+ $("#month")
																		.val()
																+ "/"
																+ sessionStorage
																		.getItem("ID"),
														dataType : "JSON",

														success : function(d) {

															$(
																	'#highchart_container2')
																	.highcharts(
																			{
																				chart : {
																					type : 'line',
																					backgroundColor : 'transparent'
																				},
																				title : {
																					text : 'Consumption Graph'
																				},
																				subtitle : {
																					text : sessionStorage
																							.getItem("ID")
																				},
																				xAxis : {
																					categories : d.xAxis,

																					title : {
																						text : null
																					},
																				},
																				yAxis : {
																					min : 0,
																					title : {
																						text : 'Chart',
																						align : 'high'
																					},
																					labels : {
																						overflow : 'justify'
																					}

																				},
																				tooltip : {
																					valueSuffix : ''
																				},
																				plotOptions : {
																					bar : {
																						dataLabels : {
																							enabled : true
																						}
																					}
																				},

																				legend : {
																					layout : 'vertical',
																					align : 'right',
																					verticalAlign : 'top',
																					x : -40,
																					y : 100,
																					floating : true,
																					borderWidth : 1,
																					backgroundColor : ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
																					shadow : true
																				},

																				credits : {
																				// enabled
																				// :
																				// false
																				},
																				series : [ {
																					data : d.yAxis,
																					name : ''
																				} ]

																			});
														}
													});

										});
						$
								.getJSON(
										"./dashboard/"
												+ sessionStorage
														.getItem("roleID")
												+ "/"
												+ sessionStorage.getItem("ID")
												+ "/-1",
										function(data) {
											$
													.each(
															data.data,
															function(i, item) {
																// alert();
																document
																		.querySelector("#lastBillAmount").innerText = item.lastTopupAmount == undefined ? ""
																		: item.lastTopupAmount;
																document
																		.querySelector("#lastBillDate").innerText = item.lastRechargeDate == undefined ? ""
																		: item.lastRechargeDate;
																document
																		.querySelector("#community").innerText = item.communityName;
																document
																		.querySelector("#block").innerText = item.blockName;
																document
																		.querySelector("#CRN_Number").innerText = item.CRNNumber;
																document
																		.querySelector("#balance").innerText = item.balance;
																document
																		.querySelector("#valveStatus").innerText = item.valveStatus;
																document
																		.querySelector("#meterStatus").innerText = item.tamperStatus;
																document
																		.querySelector("#batteryStatus").innerText = item.battery;

															});
											if (data.data.length == 0) {
												document
														.querySelector(".balance").innerText = "---";
												document
														.querySelector(".valveStatus").innerText = "---";
												document
														.querySelector("#lastBillAmount").innerText = "---";
												document
														.querySelector("#lastBillDate").innerText = "---";
											}

										});

						$
								.getJSON(
										"./customer/"
												+ sessionStorage
														.getItem("roleID")
												+ "/"
												+ sessionStorage.getItem("ID")
												+ "/-1",
										function(data) {
											$
													.each(
															data.data,
															function(i, item) {

																document
																		.querySelector(".community").innerText = item.communityName;
																document
																		.querySelector(".block").innerText = item.blockName;
																document
																		.querySelector(".CRN_Number").innerText = item.CRNNumber;

															});
										});

						$
								.ajax({
									type : "GET",
									contentType : "application/json",
									url : "./graph/" + 0 + "/" + 0 + "/"
											+ sessionStorage.getItem("ID"),
									dataType : "JSON",

									success : function(d) {

										$('#highchart_container1')
												.highcharts(
														{
															chart : {
																type : 'line'
															},
															title : {
																text : 'Consumption Graph'
															},
															subtitle : {
																text : sessionStorage
																		.getItem("ID")
															},
															xAxis : {
																categories : d.xAxis,

																title : {
																	text : null
																},
															},
															yAxis : {
																min : 0,
																title : {
																	text : 'Chart',
																	align : 'high'
																},
																labels : {
																	overflow : 'justify'
																},
																min : 0

															},
															tooltip : {
																valueSuffix : ''
															},
															plotOptions : {
																bar : {
																	dataLabels : {
																		enabled : true
																	}
																}
															},

															legend : {
																layout : 'vertical',
																align : 'right',
																verticalAlign : 'top',
																x : -40,
																y : 100,
																floating : true,
																borderWidth : 1,
																backgroundColor : ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
																shadow : true
															},

															credits : {
															// enabled : false
															},
															series : [ {
																data : d.yAxis,
																name : ''
															} ]

														});
									}
								});
					}

				});