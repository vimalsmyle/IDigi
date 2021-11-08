/**
 * 
 */

$(document)
				.ready(
						function() {
							$('#manualDetails')
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													
													mui : {
														message : 'MUI is not valid',
														validators : {
															notEmpty : {
																message : 'MUI ID is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'MUI ID must be more than 2 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z0-9.,$; ]+$/,
																message : 'MUI ID can only consist of Alphanumaric'
															}
														}	
													},
													batteryVoltage : {
														message : 'Battery Voltage is not valid',
														validators : {
															notEmpty : {
																message : 'Battery Voltage is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 5,
																message : 'Battery Voltage must be more than 2 and less than 5 characters long'
															},
															regexp : {
																regexp : /^[0-9.]+$/,
																message : 'Battery Voltage can only consist of numeric'
															}
														}
													},
													credit : {
														message : 'Credit is not valid',
														validators : {
															notEmpty : {
																message : 'Credit is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 10,
																message : 'Credit must be more than 2 and less than 10 characters long'
															},
															regexp : {
																regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
																message : 'Credit can only consist of number'
															}
														}
													},emergencyCredit : {
														message : 'Emergency Credit is not valid',
														validators : {
															notEmpty : {
																message : 'Emergency Credit is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 10,
																message : 'Emergency Credit must be more than 2 and less than 10 characters long'
															},
															regexp : {
																regexp : /[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/,
																message : 'Emergency Credit can only consist of number'
															}
														}
													}
												}
											});

							$('#manualDetails')
									.on(
											'status.field.bv',
											function(e, data) {
												formIsValid = true;
												$('.group.form-group', $(this))
														.each(
																function() {
																//	alert(this+"@@=>"+formIsValid);
																	formIsValid = formIsValid
																			&& $(
																					this)
																					.hasClass(
																							'has-success');
																	
																	//alert("!!@@=>"+formIsValid);
																	
																});

												if (formIsValid) {
													$('.submit-button', $(this))
															.attr('disabled',
																	false).attr('class', 'btn btn-success submit-button');;
												} else {
													$('.submit-button', $(this))
															.attr('disabled',
																	true);
												}
											});
							
												$(document).on('click', '#manual', function () {

												if($("#timestamp").val() == "" || $("#timestamp").val() == null ){
													bootbox
													.alert("Enter timestamp");
													return false;
												}
												
												
												if($("#syncTime").val() == "" || $("#syncTime").val() == null ){
													bootbox
													.alert("Enter Sync Time");
													return false;
												}
												
												
												if($("#syncInterval").val() == "" || $("#syncInterval").val() == null ){
													bootbox
													.alert("Enter Sync Interval");
													return false;
												}else if($("#syncInterval").val().length>5){
													bootbox
													.alert("Sync should not be more than 5 numbers");
													return false;
													
												}
												
												var reg = /^(0|[1-9]\d*)(\.\d+)?[0-5]$/;
												if($("#batteryVoltage").val() == "" || $("#batteryVoltage").val() == null ){
													bootbox
													.alert("Enter Battery voltage");
													return false;
												}else if (!reg.test($("#batteryVoltage")
														.val()) || $("#batteryVoltage")
														.val().length>5) {

													swal
															.fire({
																title : "error",
																text : "Enter valid battery voltage",
																icon : "error"
															})
													return false;
												}
												
												
												if($("#credit").val() == "" || $("#credit").val() == null ){
													bootbox
													.alert("Enter credit");
													return false;
												}else if (!reg.test($("#credit")
														.val()) || $("#credit")
														.val().length>5) {

													swal
															.fire({
																title : "error",
																text : "Enter valid credit",
																icon : "error"
															})
													return false;
												}
												
												
												if($("#emergencyCredit").val() == "" || $("#emergencyCredit").val() == null ){
													bootbox
													.alert("Select Emergency credit");
													return false;
												}else if (!reg.test($("#emergencyCredit")
														.val()) || $("#emergencyCredit")
														.val().length>5) {

													swal
															.fire({
																title : "error",
																text : "Enter valid Emergency credit",
																icon : "error"
															})
													return false;
												}
												
												
												if ($("#reading").val() == "") {

													swal
															.fire({
																title : "error",
																text : "Enter Default Reading",
																icon : "error"
															})
													return false;

												}

												else if (!reg.test($("#reading")
														.val())) {

													swal
															.fire({
																title : "error",
																text : "The Default Reading can only consist of number with max 10 digit",
																icon : "error"
															})
													return false;

												}
												
												
												var data1 = {}
												var data2={}
												data2["door_open"] = document.getElementById("door").checked==true?"1":"0";
												data2["magnetic"] = document.getElementById("magnetic").checked==true?"1":"0";
												data2["schedule_disconnect"] = document.getElementById("schedule").checked==true?"1":"0";
												data2["rtc_fault"] = document.getElementById("rtc").checked==true?"1":"0";
												data2["low_bat"] = document.getElementById("low").checked==true?"1":"0";
												data2["low_bal"] = document.getElementById("balance").checked==true?"1":"0";
												data1["timestamp"] = $("#timestamp").val();
												data1["type"] = $("#type").val();
												data1["sync_time"] = $("#syncTime").val();
												data1["sync_interval"] = $("#syncInterval").val();
												data1["pre_post_paid"] = $("#paid").val();
												data1["bat_volt"] = $("#batteryVoltage").val();
												data1["valve_live_status"] = $("#valveStatus").val();
												data1["valve_configuration"] = $("#valve").val();
												data1["credit"] = $("#credit").val();
												data1["tariff"] = $("#tariff").val();
												data1["emergency_credit"] = $("#emergencyCredit").val();
												data1["min_elapsed_after_valve_trip"] = $("#minElapsed").val();
												data1["reading"] = $("#reading").val();
												data1["status"] = data2;
												
												
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