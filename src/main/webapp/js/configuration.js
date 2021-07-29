/**
 * 
 */

$(document)
		.ready(
				function() {
					
					if(sessionStorage.getItem("roleID") == 2){
						$("#communityNameAdd").val(sessionStorage.getItem("communityName"));
						$("#formcommunityNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
						$("#blockNameAdd").val(sessionStorage.getItem("blockName"));
						$("#formblockNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
					}
										$(document).on('click', '#configuration', function () {
									    
										var data1 = {}										
										if ($("#selectcommunityName").val() == "-1") {
											
											swal.fire({
												  title: "error",
												  text: "Select Community Id",
												  icon: "error"
												})
											return false;
										}

										if ($("#selectBlockBasedonCommunity").val() == "null" || $("#selectBlockBasedonCommunity").val() == "Select Block") {

											swal.fire({
												  title: "error",
												  text: "Select Block Name",
												  icon: "error"
												})
											return false;
											
										}
										
										if ($("#selectHouseBasedonBlock").val() == "null" || $("#selectHouseBasedonBlock").val() == "Select CRN") {

											swal.fire({
												  title: "error",
												  text: "Select CRN Number",
												  icon: "error"
												})
											return false;
											
										}
										
										if ($("#selectMeters").val() == "null" || $("#selectMeters").val() == "Select Meters") {

											swal.fire({
												  title: "error",
												  text: "Select meters",
												  icon: "error"
												})
											return false;
											
										}


										if ($("#selectcommandType").val() == "null" || $("#selectcommandType").val() == -1 || $("#selectcommandType").val() == "Select Command Type") {

											swal.fire({
												  title: "error",
												  text: "Select Command Type",
												  icon: "error"
												})
											return false;
											
										}
										
										var array=[];
										
										if($("#selectcommandType").val()=="3"){
											if($("#valueText").val() == "Select Tariff" || $("#valueText").val() == -1){
												
												swal.fire({
													  title: "error",
													  text: "Select Tariff",
													  icon: "error"
													})
												return false;
												
											}	
											var json={};
											json["value"] = $("#valueText").val();
											json["parameter_id"] = $("#selectcommandType").val();
											}
										
										if($("#selectcommandType").val()=="5"){
											var json={};
											json["value"] = $("#valueText").val();
											json["parameter_id"] = $("#selectcommandType").val();
											}
										
										else if($("#selectcommandType").val()=="6"){
											if($("#valueText").val() == "Select Tariff" || $("#valueText").val() == ""){
												
												swal.fire({
													  title: "error",
													  text: "Select Start Date",
													  icon: "error"
													})
												return false;
												
											}	
											var json={};
											json["value"] = $("#valueText").val();
											json["parameter_id"] = $("#selectcommandType").val();
											
											}else if($("#selectcommandType").val()=="7"){
												
												
												}else if($("#selectcommandType").val()=="8"){
													if($("#valueText").val() <= "2" && $("#valueText").val() >= "1440"){
														
														swal.fire({
															  title: "error",
															  text: "Please enter between 2 and 1440",
															  icon: "error"
															})
														return false;
														
													}	
													var json={};
													json["value"] = $("#valueText").val();
													json["parameter_id"] = $("#selectcommandType").val();
													
													}else if($("#selectcommandType").val()=="9"){
														var reg =/[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/;
														if($("#valueText").val() == ""){
														
															swal.fire({
																  title: "error",
																  text: "Enter Default Reading",
																  icon: "error"
																})
															return false;
															
														}	
														
															else if(!reg.test($("#valueText").val())){
																
																swal.fire({
																	  title: "error",
																	  text: "The Default Reading can only consist of number",
																	  icon: "error"
																	})
																return false;
																
															}	
														
														var json={};
														json["value"] = $("#valueText").val();
														json["parameter_id"] = $("#selectcommandType").val();
														
														}else if($("#selectcommandType").val()=="13"){
															$("#confValue").remove();
															$("#row").append(`<div class="col-md-4" id="confValue" class="valueText"
																												>
																												<div id="formtariff" class="group form-group">
																													<label class="bmd-label-floating select-label">Sync Time<sup
																														class="imp">*</sup></label> 
																														
																									<input type="text" id="reading" name="reading" class="form-control">
																														
																												</div>
																											</div>`);
															}
										array.push(json);
										data1["communityID"]=$("#selectcommunityName").val();
										data1["blockID"]=$("#selectBlockBasedonCommunity").val();
										data1["customerID"] = $("#selectHouseBasedonBlock").val();
										var e = document.getElementById("selectHouseBasedonBlock");
										var value=e.options[e.selectedIndex].value;// get selected option value
										var text=e.options[e.selectedIndex].text;
										data1["customerUniqueID"] = text;
										data1["miuID"] = $("#selectMeters").val();
										var e = document.getElementById("selectMeters");
										var value=e.options[e.selectedIndex].value;// get selected option value
										var text=e.options[e.selectedIndex].text;
										data1["customerMeterID"] = text;
										data1["commands"] = array;
										
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./configuration/add",
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(data) {
														
														if (data.result == "Success") {

															swal.fire({
																  title: "Saved",
																  text: data.Message,
																  icon: "success"
																}).then(function() {
																    window.location = "configurationStatus.jsp";
																    
																});
															return false;

															

														} else if (data.result == "Failure") {

															
															swal.fire({
																  title: "Error",
																  text: data.Message,
																  icon: "success"
																}).then(function() {
																    window.location = "configurationStatus.jsp";
																    
																});
															return false;
														}
													}
												});
										return false;
									});
				});



