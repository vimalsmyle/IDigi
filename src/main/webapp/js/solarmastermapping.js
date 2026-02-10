$(document)
				.ready(
						function() {
												$(document).on('click', '#manual', function () {
													var data1 = {}
												if($("#selectcommunityName").val() == "" || $("#selectcommunityName").val() == "Select Community" ){
													swal
													.fire({
														title : "error",
														text : "Select Community",
														icon : "error"
													})
													return false;
												}
												
												
												if($("#selectBlockBasedonCommunity").val() == "" || $("#selectBlockBasedonCommunity").val() == 'Select Block' ){
													swal
													.fire({
														title : "error",
														text : "Select Block",
														icon : "error"
													})
													return false;
												}
												
												if($("#selectHouseBasedonBlock").val() == "" || $("#selectHouseBasedonBlock").val() == 'Select House' ){
														swal
														.fire({
															title : "error",
															text : "Select House",
															icon : "error"
														})
													return false;
												}
												
												if($("#selectmaster").val() == "" || $("#selectmaster").val() == 'Select Master' ){
													swal
													.fire({
														title : "error",
														text : "Select Master",
														icon : "error"
													})
													return false;
												}
												
												data1["communityID"] = $("#selectcommunityName").val();
												data1["blockID"] = $("#selectBlockBasedonCommunity").val();
												data1["houseNumber"] = $("#selectHouseBasedonBlock").val();
												data1["masterCustomerID"] = $("#selectmaster").val();
												
												$("#loader").show();
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "/customer/mapsolarmaster/add",
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
																		    window.location = "solarmastermapping.jsp";
																		    
																		});
																	return false;
																
																} else if(data.result == "Failure"){
									
																	swal.fire({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		}).then(function() {
																		    window.location = "solarmastermapping.jsp";
																		    
																		});
																	return false;
																	
																}else {
																	
																	swal.fire({
																		  title: "error",
																		  text: "Something went Wrong",
																		  icon: "error"
																		}).then(function() {
																		    window.location = "solarmastermapping.jsp";
																		    
																		});
																	
																}
															}
														});
												return false;
												});
						});