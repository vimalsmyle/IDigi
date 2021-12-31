/**
 * 
 */

$(document)
				.ready(
						function() {
							
							
							
							$.getJSON("./customers", function(data) {
								var Options = "<option value='-1'>Select  CRN</option>";
								
								$.each(data.dropDownCustomers, function(key, value) {
									Options = Options + "<option value='" + value + "'>" + value
											+ "</option>";
								});
								$('#manualCRNAll').append(Options);
							});
							
							
												$(document).on('click', '#manual', function () {
													var data1 = {}
												if($("#manualCRNAll").val() == "" || $("#manualCRNAll").val() == "-1" ){
													swal
													.fire({
														title : "error",
														text : "Select CRN",
														icon : "error"
													})
													return false;
												}
												
												
												if($("#allMeters").val() == "" || $("#allMeters").val() == 'Select MUI' ){
													swal
													.fire({
														title : "error",
														text : "Select MUI",
														icon : "error"
													})
													return false;
												}
												
												
												var reg = /^\d+(\.\d{1,2})?$/;
												if($("#currentreading").val() == "" || $("#currentreading").val() == null ){
													swal
													.fire({
														title : "error",
														text : "Select Reading",
														icon : "error"
													})
													return false;
												}else if (!reg.test($("#currentreading")
														.val())) {
													swal
															.fire({
																title : "error",
																text : "Enter correct reading",
																icon : "error"
															})
													return false;
												}
												if($("#currentreading")
														.val().split(".")[1]!=undefined){
													if($("#currentreading")
															.val().split(".")[1].length>3){
														swal
														.fire({
															title : "error",
															text : "Enter correct reading",
															icon : "error"
														})
												return false;
													}
													
												}

												
												var dataLoad;
												$.getJSON("./meterreading/"+$("#allMeters").val(), function(data) {
													dataLoad=data;
													console.log(dataLoad);
												
												
											
												var data2={}
												/*data2["door_open"] = document.getElementById("door").checked==true?"1":"0";
												data2["magnetic"] = document.getElementById("magnetic").checked==true?"1":"0";
												data2["schedule_disconnect"] = document.getElementById("schedule").checked==true?"1":"0";
												data2["rtc_fault"] = document.getElementById("rtc").checked==true?"1":"0";
												data2["low_bat"] = document.getElementById("low").checked==true?"1":"0";
												data2["low_bal"] = document.getElementById("balance").checked==true?"1":"0";*/
												//data1["timestamp"] = $("#timestamp").val();
												data1["type"] = dataLoad.lastReadingDetails.type;
												//data1["sync_time"] = $("#syncTime").val();
												data1["sync_interval"] = dataLoad.lastReadingDetails.sync_interval;
												data1["pre_post_paid"] = dataLoad.lastReadingDetails.pre_post_paid;
												data1["bat_volt"] =dataLoad.lastReadingDetails.bat_volt;
												data1["valve_live_status"] = dataLoad.lastReadingDetails.valve_live_status;
												data1["valve_configuration"] = dataLoad.lastReadingDetails.valve_configuration;
												//data1["credit"] = $("#credit").val();
												data1["tariff"] = dataLoad.lastReadingDetails.tariff;
												data1["topupSMS"] = dataLoad.lastReadingDetails.topupSMS;
												data1["emergency_credit"] = dataLoad.lastReadingDetails.emergency_credit;
												data1["min_elapsed_after_valve_trip"] = dataLoad.lastReadingDetails.days_elapsed_after_valve_trip;
												data1["reading"] = $("#currentreading").val();
												data1["status"] = data2;
												});
												
												$("#loader").show();
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./server/api/"+$("#mui").val()+"/status",
															data : JSON
																	.stringify(data1),
															dataType : "JSON",

															success : function(
																	data) {

																$("#loader").hide();
																if (data.result == "Success") {

																	swal.fire({
																		  title: "Saved",
																		  text: data.Message,
																		  icon: "success"
																		}).then(function() {
																		    window.location = "manualdata.jsp";
																		    
																		});
																	return false;
																
																	

																} else if(data.result == "Failure"){
									
																	swal.fire({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		}).then(function() {
																		    window.location = "manualdata.jsp";
																		    
																		});
																	return false;
																	
																	
																}else {
																	
																	swal.fire({
																		  title: "error",
																		  text: "Something went Wrong",
																		  icon: "error"
																		}).then(function() {
																		    window.location = "manualdata.jsp";
																		    
																		});
																	
																}
															}
														});
												return false;
											});
						});



function showMetersbyCRN(val){
	
	$("#allMeters").find('option').remove();

	$("#allMeters").append("<option>" + "Select MUI" + "</option>");
	
	$.getJSON("./customermeters/"+val, function(data) {
		var Options = "";
		$.each(data.dropDownAllCustomerMeters, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#allMeters').append(Options);
	});
	
}

function showDetailsbyMeter(val){
	$.getJSON("./meterreading/"+val, function(data) {
		console.log(data);
		$("#oldreading").val(data.lastReadingDetails.reading);
		$("#lastUpdatedTime").val(data.lastReadingDetails.reading);
	});
}