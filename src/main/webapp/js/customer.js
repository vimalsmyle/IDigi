/**
 * 
 */
$(document).ready(function() {

	var rowCount = 0;
	$.getJSON("./customer/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1", function(data) {
	$.each(data.data, function(i, item) {
		if ($("#custUniqueId").val() == item.CustomerUniqueID) {
			$('#communityNameEdit').val(item.communityName).trigger("change");
			$("#formcommunityNameEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			$('#blockNameEdit').val(item.blockName).trigger("change");
			$("#formblockNameEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			$('#firstNameEdit').val(item.firstName).trigger("change");
			$("#formfirstNameEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			$('#lastNameEdit').val(item.lastName).trigger("change");
			$("#formlastNameEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			$('#houseNoEdit').val(item.houseNumber).trigger("change");
			$("#formhouseNoEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			
			$('#mobileNoEdit').val(item.mobileNumber).trigger("change");
			$("#formmobileNoEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			
			$('#emailEdit').val(item.email).trigger("change");
			$("#formemailEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			
			$('#meterSerialEdit').val(item.meterSerialNumber).trigger("change");
			$("#formmeterSerialEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			
			$('#amrEdit').val(item.meterID).trigger("change");
			$("#formamrEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			
			if(sessionStorage.getItem("roleID") == 3){
				$('#amrEdit')
				.attr('disabled',
						true);
			}
			$('#CRNEdit').val(item.CustomerUniqueID).trigger("change");
			$("#formCRNEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		    
			$("#customerIdhidden").val(item.CRNNumber);
		
			$('#customerEditsave')
			.attr('disabled',
					false);
			if(sessionStorage.getItem("roleID") == 3){
			
				$('#amrEdit', '#amrEdit')
				.attr('disabled',
						true);
				$('#houseNoEdit')
				.attr('disabled',
						true);
				
			}
			
			
		} 
		$.each(item.meters, function(i, meter) {
		rowCount++;
		
		$("#template").append("<div class=row> " +
				"<div class=col-md-4>" +
				"<div class='group form-group'>"
								+"<label class=bmd-label-floating>MIU ID</label> <input "
								+"type=text class='form-control form-control-sm' name=miuIDEdit["+rowCount+"]"
								+" id=miuIDEdit-"+rowCount+">"
								+"</div></div>"+
					"<div class=col-md-4>" +
					"<div class='group form-group'>"
					+"<label class=bmd-label-floating>Meter Serial Number</label> <input "
					+"type=text class='form-control form-control-sm' name=meterSerialNumberEdit["+rowCount+"]"
					+" id=meterSerialNumberEdit-"+rowCount+">"
					+"</div></div>"+
				"<div class=col-md-4>" +
				"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
				+"<label class=bmd-label-floating>Meter Type</label> " +
				"<select class='form-control form-control-sm select2'  id=selectMeterType-"+rowCount+" name=selectMeterType["+rowCount+"]>"+
					"<option value='-1'>Select Meter Type</option>"+
					"<option value='Gas'>Gas</option>"+
					"<option value='Water'>Water</option>"+
					"<option value='Energy'>Energy</option>"+
				"</select>"
				+"</div></div>"+
				"<div class=col-md-4>" +
				"<div class='group form-group'>"
				+"<label class=bmd-label-floating>Meter Size</label> <input "
				+"type=text class='form-control form-control-sm' name=meterSizeEdit["+rowCount+"]"
				+" id=meterSizeEdit-"+rowCount+">"
				+"</div></div>"+
				"<div class=col-md-4>" +
				"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
				+"<label class=bmd-label-floating>Pay Type</label>" +
				"<select class='form-control form-control-sm select2' id=payTypeEdit-"+rowCount+" name=payTypeEdit["+rowCount+"]>"+
				"<option value='-1'>Select Pay Type</option>"+
				"<option value='Prepaid'>Prepaid</option>"+
				"<option value='Postpaid'>Postpaid</option>"+
			"</select>"
				+"</div></div>"+
				"<div class=col-md-4>" +
				"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
				+"<label class=bmd-label-floating>Tariff Name</label> " +
				"<select "+
				"class='form-control form-control-sm' id=selectTariffName-"+rowCount+" name=selectTariffName["+rowCount+"]>"+
				 
				"</select>"
				+"</div></div>"+
				"<div class=col-md-4>" +
				"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
				+"<label class=bmd-label-floating>Gateway ID</label> " +
				"<select "+
				"class='form-control form-control-sm' id=gatewayIDEdit-"+rowCount+" name=gatewayIDEdit["+rowCount+"]>"+
				 
				"</select>"
				+"</div></div>"+
				"<div class=col-md-4>" +
				"<div class='group form-group'>"
				+"<label class=bmd-label-floating>Location</label> <input "
				+"type=text class='form-control form-control-sm' name=locationEdit["+rowCount+"]"
				+" id=locationEdit-"+rowCount+">"
				+"</div></div>   " +
						" <div class='col-md-12 text-right'>" 
				+" <button type=button class='btn btn-danger' id='removeMeter'>Remove</button></div></div>" +
						"</div>");
		
		
		});
		
		
	});
});

});


$(document).ready(function() {
	
	if(sessionStorage.getItem("roleID") == 1 || sessionStorage.getItem("roleID") == 2){
		if(sessionStorage.getItem("roleID") == 2){
			$("#communityNameAdd").val(sessionStorage.getItem("communityName"));
			$("#formcommunityNameAdd").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			$("#blockNameAdd").val(sessionStorage.getItem("blockName"));
			$("#formblockNameAdd").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
		}
		$("#blockAddButton").show();
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
	}else{
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
	}
	$("#customerTable1").hide();
table = $('#customerTable')
.DataTable(
{
	"dom": dom1,
	"responsive" : true,
	"serverSide" : false,
	"bDestroy" : true,
	"bPaginate": true,
	"pagging" : true,
	"bProcessing" : true,
	"ordering" : true,
	"order" : [ 0, "desc" ],
	"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
	"pageLength" : 5,
	
	"scrollX" : true,
"ajax" : {
"url":"./customer/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1",
"type" : "GET",
"data" : function(search) {
},
"complete" : function(json) {
	console.log(json);
return json.data;
},
},
"columns" : [
{
"data" : "communityName"
},{
"data" : "blockName"
},{
"data" : "CustomerUniqueID"
},{
"data" : "firstName"
},{
"data" : "lastName"
},{
"data" : "houseNumber"
},
{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		return "<a href=# id=CustomerMeters data-toggle=modal data-target=#myCustomerMeters onclick='getCustomerMeters(\""
		+ row.CustomerUniqueID
		+ "\")'>"
		+ "Multiple"
		+ "</a>"
		
	}

},
{
"data" : "mobileNumber"
},{
"data" : "email"
},{
"data" : "createdByUserName"
},{
"data" : "createdByRoleDescription"
},{
"data" : "date"
}
,{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		return "<a href='customerEdit.jsp?cust='"+row.CustomerUniqueID+"'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a>"
																	+"<a onclick='getCustomerFormDelete(\""
																	+ row.CustomerUniqueID
																	+ "\")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer;'>delete</i>"
																	+ "</a>"
																	
																	
	}
	},{
		"mData" : "action",
		"render" : function(data, type, row) {
			
			return "<a href='customerEdit.jsp?cust="+row.CustomerUniqueID+"'>"
																		+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																		+ "</a>"
		}
		}



],
"columnDefs" : [ {
	targets : 11, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4))),
},{
	targets : 12, visible: (sessionStorage.getItem("roleID") == 3)
//(!(sessionStorage.getItem("roleID") == 1) && (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4))))
},
{
	"className": "dt-center", "targets": "_all"
}], "buttons": [
	{
        className: 'customButton',
        text : "Filter",
         action: function ( e, dt, button, config ) {
         	$('.customButton').attr(
                 {
                     "data-toggle": "modal",
                     "data-target": "#filter"
                 }
             );
         }
     }
]
});

$("div.headname").html('<h3>Customer Managemnent</h3>');

if(sessionStorage.getItem("roleID") == 3 || sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
	table.buttons( $('a.customButton') ).remove();	
}

$("div.addevent").html('<button type="button" id="customerAddd"'
		+'class="btn btn-raised btn-primary float-right"'
			+'data-toggle="modal" data-target="#exampleModal">'
		+'<i class="fa fa-user-plus"></i>'
		+'</button>');

var rowCount = 0;
$("#addMeter")
.click(
		function() {
			rowCount++;
			
			if(rowCount>2){
				$("#addMeter").hide();
			}else{
				$("#addMeter").show();
			}
			
			
			$("#template").append("<div class=row> " +
									"<div class=col-md-4>" +
									"<div class='group form-group'>"
													+"<label class=bmd-label-floating>MIU ID</label> <input "
													+"type=text class='form-control form-control-sm' name=miuIDAdd["+rowCount+"]"
													+" id=miuIDAdd-"+rowCount+">"
													+"</div></div>"+
										"<div class=col-md-4>" +
										"<div class='group form-group'>"
										+"<label class=bmd-label-floating>Meter Serial Number</label> <input "
										+"type=text class='form-control form-control-sm' name=meterSerialNumberAdd["+rowCount+"]"
										+" id=meterSerialNumberAdd-"+rowCount+">"
										+"</div></div>"+
									"<div class=col-md-4>" +
									"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
									+"<label class=bmd-label-floating>Meter Type</label> " +
									"<select class='form-control form-control-sm select2'  id=selectMeterType-"+rowCount+" name=selectMeterType["+rowCount+"]>"+
										"<option value='-1'>Select Meter Type</option>"+
										"<option value='Gas'>Gas</option>"+
										"<option value='Water'>Water</option>"+
										"<option value='Energy'>Energy</option>"+
									"</select>"
									+"</div></div>"+
									"<div class=col-md-4>" +
									"<div class='group form-group'>"
									+"<label class=bmd-label-floating>Meter Size</label> <input "
									+"type=text class='form-control form-control-sm' name=meterSizeAdd["+rowCount+"]"
									+" id=meterSizeAdd-"+rowCount+">"
									+"</div></div>"+
									"<div class=col-md-4>" +
									"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
									+"<label class=bmd-label-floating>Pay Type</label>" +
									"<select class='form-control form-control-sm select2' id=payTypeAdd-"+rowCount+" name=payTypeAdd["+rowCount+"]>"+
									"<option value='-1'>Select Pay Type</option>"+
									"<option value='Prepaid'>Prepaid</option>"+
									"<option value='Postpaid'>Postpaid</option>"+
								"</select>"
									+"</div></div>"+
									"<div class=col-md-4>" +
									"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
									+"<label class=bmd-label-floating>Tariff Name</label> " +
									"<select "+
									"class='form-control form-control-sm' id=selectTariffName-"+rowCount+" name=selectTariffName["+rowCount+"]>"+
									 
									"</select>"
									+"</div></div>"+
									"<div class=col-md-4>" +
									"<div class='group form-group has-feedback has-success bmd-form-group is-filled'>"
									+"<label class=bmd-label-floating>Gateway ID</label> " +
									"<select "+
									"class='form-control form-control-sm' id=gatewayIDAdd-"+rowCount+" name=gatewayIDAdd["+rowCount+"]>"+
									 
									"</select>"
									+"</div></div>"+
									"<div class=col-md-4>" +
									"<div class='group form-group'>"
									+"<label class=bmd-label-floating>Location</label> <input "
									+"type=text class='form-control form-control-sm' name=locationAdd["+rowCount+"]"
									+" id=locationAdd-"+rowCount+">"
									+"</div></div>   " +
											" <div class='col-md-12 text-right'>" 
									+" <button type=button class='btn btn-danger' id='removeMeter'>Remove</button></div></div>" +
											"</div>");

			
			
			 $('#customerDetails').bootstrapValidator('addField' ,
		        'miuIDAdd['+rowCount+']', {
		        	message : 'MUI ID is not valid',
					validators : {
						notEmpty : {
							message : 'MUI ID is required and cannot be empty'
						},
						stringLength : {
							min : 4,
							max : 30,
							message : 'MUI ID must be more than 4 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
							message : 'MUI ID can only consist of Alphanumaric'
						}
					}
		        });
			 $('#customerDetails').bootstrapValidator('addField',
		        'meterSerialNumberAdd['+rowCount+']', {
		        	message : 'Meter Serial Number is not valid',
					validators : {
						notEmpty : {
							message : 'Meter Serial Number is required and cannot be empty'
						},
						stringLength : {
							min : 4,
							max : 30,
							message : 'Meter Serial Number must be more than 4 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
							message : 'Meter Serial Number can only consist of Alphanumaric'
						}
					}
		        });
			 $('#customerDetails').bootstrapValidator('addField',
		        'meterSizeAdd['+rowCount+']', {
		        	message : 'Meter Size is not valid',
					validators : {
						notEmpty : {
							message : 'Meter Size is required and cannot be empty'
						},
						stringLength : {
							min : 4,
							max : 30,
							message : 'Meter Size must be more than 4 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[0-9]+$/,
							message : 'Meter Size can only consist of Alphanumaric'
						}
					}
		        });
			 $('#customerDetails').bootstrapValidator('addField',
		        'tariffNameAdd['+rowCount+']', {
		        	message : 'Tariff Name is not valid',
					validators : {
						notEmpty : {
							message : 'Tariff Name is required and cannot be empty'
						},
						stringLength : {
							min : 4,
							max : 30,
							message : 'Tariff Name must be more than 4 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
							message : 'Tariff Name can only consist of Alphanumaric'
						}
					}
		        });
			
			 $('#customerDetails').bootstrapValidator('addField','locationAdd['+rowCount+']', {
		        	message : 'Location is not valid',
					validators : {
						notEmpty : {
							message : 'Location is required and cannot be empty'
						},
						stringLength : {
							min : 4,
							max : 30,
							message : 'Location must be more than 4 and less than 30 characters long'
						},
						regexp : {
							regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
							message : 'Location can only consist of Alphanumaric'
						}
					}
		        }
			 );
		     
			 
			 $("#rowCount").val(rowCount);
			 
			 $.getJSON("./tariffs", function(data) {
					var Options = '<option value=-1>Select  Tariff</option>';
					$.each(data.dropDownTariffs, function(key, value) {
						Options = Options + '<option value=' + key + '>' + value
								+ '</option>';
					});
					$('#selectTariffName-'+rowCount).append(Options);
				});
			 
			 
			 $.getJSON("./gateways", function(data) {
					var Options = "<option value='-1'>Select  Gateway</option>";
					$.each(data.dropDownGateways, function(key, value) {
						Options = Options + "<option value='" + key + "'>" + value
								+ "</option>";
					});
					$('#gatewayIDAdd-'+rowCount).append(Options);
				});
			 

		});

$("body").on("click", "#removeMeter", function(e) {
	rowCount--;
	if(rowCount>2){
		$("#addMeter").hide();
	}else{
		$("#addMeter").show();
	}
	$(this).parent().parent().remove();
	
	$("#rowCount").val(rowCount);
	
  });



$("#customerAddd").click(function(){
	
window.location ="customer.jsp";	
	

});
});






$(document)
				.ready(
						function() {
							$('#customerDetails').bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													
													firstNameAdd : {
														message : 'First Name is not valid',
														validators : {
															notEmpty : {
																message : 'First Name is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'First Name must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
																message : 'First Name can only consist of Alphanumaric'
															}
														}
													},
													lastNameAdd : {
														message : 'Last Name is not valid',
														validators : {
															notEmpty : {
																message : 'Last Name is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'Last Name must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
																message : 'Last Name can only consist of Alphanumaric'
															}
														}
													},
													houseNoAdd : {
														message : 'House No. is not valid',
														validators : {
															notEmpty : {
																message : 'House No is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'House No must be more than 4 and less than 30 characters long'
															}
														}
													},
													mobileNoAdd : {
														message : 'Mobile No. is not valid',
														validators : {
															notEmpty : {
																message : 'Mobile No. is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]{10}$/,
																message : 'Mobile No. can only consist of number'
															}
														}
													},
													emailAdd : {
														message : 'Email is not valid',
														validators : {
															notEmpty : {
																message : 'Email is required and cannot be empty'
															}
														}
													},
									                CRNAdd : {
														message : 'CRN No. is not valid',
														validators : {
															notEmpty : {
																message : 'CRN No. is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'CRN No. must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$;]+$/,
																message : 'CRN No. can only consist of Alphanumaric'
															}
														}
													}
												}
											});
							
							
							
							
							$('#customerEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											
											firstNameEdit : {
												message : 'First Name is not valid',
												validators : {
													notEmpty : {
														message : 'First Name is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'First Name must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'First Name can only consist of Alphanumaric'
													}
												}
											},
											lastNameEdit : {
												message : 'Last Name is not valid',
												validators : {
													notEmpty : {
														message : 'Last Name is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'Last Name must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'First Name can only consist of Alphanumaric'
													}
												}
											},
											houseNoEdit : {
												message : 'House No. is not valid',
												validators : {
													notEmpty : {
														message : 'House No is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'House No. must be more than 4 and less than 30 characters long'
													}
												}
											},
											mobileNoEdit: {
												message : 'Mobile No. is not valid',
												validators : {
													notEmpty : {
														message : 'Mobile No. is required and cannot be empty'
													},
													regexp : {
														regexp : /^[0-9]{10}$/,
														message : 'Mobile No. can only consist of number'
													}
												}
											},
											emailEdit : {
												message : 'Email is not valid',
												validators : {
													notEmpty : {
														message : 'Email is required and cannot be empty'
													}/*,
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'Community Address can only consist of alphabetical and number'
													}*/
												}
											},
											meterSerialEdit : {
												message : 'MSN is not valid',
												validators : {
													notEmpty : {
														message : 'MSN is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 15,
														message : 'MSN must be more than 4 and less than 15 characters long'
													},
													regexp : {
														regexp : /^[^0][a-zA-Z0-9.,$; ]+$/,
														message : 'MSN can only consist of Alphanumaric and Could not start with zero'
													}
												}
											},
											amrEdit : {
												message : 'MIU ID is not valid',
												validators : {
													notEmpty : {
														message : 'MIU ID is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 16,
														message : 'MIU ID must be more than 4 and less than 16 characters long'
													},
													regexp : {
														regexp : /^[^0][a-zA-Z0-9.,$; ]+$/,
														message : 'MIU ID can only consist of Alphanumaric and Could not start with zero'
													}
												}
											},
											
											/*selectTariffNameEdit: {
							                    validators: {
							                        notEmpty: {
							                            message: 'Please select your Tariff language.'
							                        }
							                    }
							                },*/
							                CRNEdit : {
												message : 'CRN No. is not valid',
												validators : {
													notEmpty : {
														message : 'CRN No. is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'CRN No. must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$;]+$/,
														message : 'CRN No. can only consist of Alphanumaric'
													}
												}
											}
										}
									});
							
							
							
							
							$("#customerFilter")
							.click(
									function() {

										var url = $("#filterselectcommunityName").val() == "-1" ? sessionStorage.getItem("roleID")+"/0/-1" : $("#filterselectBlockBasedonCommunity").val() == "Select Block" ? 
												$("#filterselectcommunityName").val() == "-1" ? 
												sessionStorage.getItem("roleID")+"/0/-1":sessionStorage.getItem("roleID")+"/0/"+$("#filterselectcommunityName").val():
											"2/"+$("#filterselectBlockBasedonCommunity").val()+"/-1"
												
										$
												.ajax({
													type : "GET",
													contentType : "application/json",
													url : "./customer/"+url,
													dataType : "JSON",

													success : function(d) {
														
														//if (data.result == "Success") {
														$('#customerTable').dataTable()._fnAjaxUpdate();
														//$("#form").hide();
														//$("#tablereport").show();
															console.log(JSON.stringify(d));
															$("#customerTable_wrapper").hide();
															$("#filter").modal("hide");
															$("#customerTable1").show();
															var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
															var hCols = [ 3, 4 ];
															table = $('#customerTable1')
															.DataTable(
																	{
																		
																		"dom": dom1,
																		   "responsive" : true,
																			/*"processing" : true,*/
																			"serverSide" : false,
																			"bDestroy" : true,
																			"bPaginate": true,
																			"pagging" : true,
																			"bProcessing" : true,
																			"ordering" : true,
																			"order" : [ 0, "desc" ],
																			"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
																			"pageLength" : 5,
																			"scrollX" : false,
																			"data" : d.data,
																			"columns" : [
																	{
																	"data" : "communityName"
																	},{
																	"data" : "blockName"
																	},{
																	"data" : "CustomerUniqueID"
																	},{
																	"data" : "firstName"
																	},{
																	"data" : "lastName"
																	},{
																	"data" : "houseNumber"
																	},
																	{
																		"mData" : "action",
																		"render" : function(data, type, row) {
																			
																			return "<a href=# id=CustomerMeters data-toggle=modal data-target=#myCustomerMeters onclick='getCustomerMeters(\""
																			+ row.CustomerUniqueID
																			+ "\")'>"
																			+ "Multiple"
																			+ "</a>"
																			
																		}
																	
																	},
																	
																	{
																	"data" : "mobileNumber"
																	},{
																	"data" : "email"
																	},{
																	"data" : "createdByUserName"
																	},{
																	"data" : "createdByRoleDescription"
																	},{
																	"data" : "date"
																	}
																	,{
																		"mData" : "action",
																		"render" : function(data, type, row) {
																			
																			return "<a href=# id=CustomerEdit data-toggle=modal data-target=#myCustomerEdit onclick='getCustomerFormEdit(\""
																		}
																		},{
																			"mData" : "action",
																			"render" : function(data, type, row) {
																				
																				return "<a href=# id=CustomerEdit data-toggle=modal data-target=#myCustomerEdit onclick='getCustomerFormEdit(\""
																																			+ row.CustomerUniqueID
																																			+ "\")'>"
																																			+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																																			+ "</a>"
																			}
																			}



																	],
																	"columnDefs" : [ {
																		//orderable : false,
																		targets : 11, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4))),
																		//targets : 14, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4))),
																	},{
																		//orderable : false,
																		//targets : 13, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4))),
																		targets : 12, visible: ( !(sessionStorage.getItem("roleID") == 1) && (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4))))
																	},
																	{
																		"className": "dt-center", "targets": "_all"
																	}], "buttons": [
																		{
															                text: 'Reset',
															                action: function ( e, dt, node, config ) {
															                    alert( 'Button activated' );
															                },
															                className: 'customButton',
															               
															                action: function ( e, dt, button, config ) {
															                   
															                	window.location = "customerDetails.jsp"
															                }
															            }
																	]
																	})
																	$("div.headname").html('<h3>Customer Managemnent</h3>');
													}
												});
										return false;
									});
							
							$("#resetFilter")
							.on(
									function() {
										
										 $("input:text").val("");
							
									});	
							

							$('#customerDetails')
									.on(
											'status.field.bv',
											function(e, data) {
												formIsValid = true;
												$('.group.form-group', $(this))
														.each(
																function() {
																//	alert(this+"@@=>"+formIsValid);
																	formIsValid = formIsValid
																			&& $(this).hasClass(
																							'has-success');
																	//alert("!!@@=>"+formIsValid);
																	
																});

												if (formIsValid) {
													$('.submit-button', $(this))
															.attr('disabled',
																	false);
												} else {
													$('.submit-button', $(this))
															.attr('disabled',
																	true);
												}
											});

							
							
							
							$('#customerEdit').on(
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
											$('#customerEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#customerEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
												$(document).on('click', '#customerAdd', function () {
													if(sessionStorage.getItem("roleID") != 2){
												if($("#selectcommunityName").val() == -1 || $("#selectcommunityName").val() == null || $("#selectcommunityName").val() == "Select Community"){
													bootbox
													.alert("Select Community Id");
													return false;
												} 
												
												if($("#selectBlockBasedonCommunity").val() == -1 || $("#selectBlockBasedonCommunity").val() == null || $("#selectBlockBasedonCommunity").val() == "Select Block"){
													bootbox
													.alert("Select Block Id");
													return false;
												}
}			
												let communityId = sessionStorage.getItem("roleID") == 2 ? sessionStorage.getItem("communityID") : $("#selectcommunityName").val();
												let blockId = sessionStorage.getItem("roleID") == 2 ? sessionStorage.getItem("ID") : $("#selectBlockBasedonCommunity").val();
												
												var meterDetails = [];
												for(var i=1;parseInt($("#rowCount").val())>=i;i++){
													var array ={};
													
													array["miuID"] = $("#miuIDAdd-"+i).val();
													array["meterSerialNumber"] = $("#meterSerialNumberAdd-"+i).val();
													array["meterType"] = $("#selectMeterType-"+i).val();
													array["meterSize"] = $("#meterSizeAdd-"+i).val();
													array["payType"] = $("#payTypeAdd-"+i).val();
													array["tariffID"] = $("#selectTariffName-"+i).val();
													array["gatewayID"] = $("#gatewayIDAdd-"+i).val();
													array["location"] = $("#locationAdd-"+i).val();
													meterDetails.push(array);
												}
												
												
												
												var data1 = {}
												data1["communityID"] = communityId;
												data1["blockID"] = blockId;
												data1["firstName"] = $("#firstNameAdd").val();
												data1["lastName"] = $("#lastNameAdd").val();
												data1["houseNumber"] = $("#houseNoAdd").val();
												data1["mobileNumber"] = $("#mobileNoAdd").val();
												data1["email"] = $("#emailAdd").val();
												data1["customerUniqueID"] = $("#CRNAdd").val();
												data1["meters"] = meterDetails;
												data1["createdByID"] = sessionStorage.getItem("createdByID");
												data1["loggedInUserID"] = sessionStorage.getItem("userID");
												data1["loggedInRoleID"] = sessionStorage.getItem("roleID");
												
												/*alert("===>"
														+ JSON.stringify(data1));*/
												
												$('#customerAdd').prop('disabled', true).addClass('disabled').off( "click" );
												console.log(JSON
														.stringify(data1));
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./customer/add",
															data : JSON
																	.stringify(data1),
															dataType : "JSON",

															success : function(
																	data) {
																/*alert("data"
																		+ JSON
																				.stringify(data));*/
																if (data.result == "Success") {

																	/*alert( "data"
																			+ data.result);*/
																	
																	bootbox.alert(data.Message,
																			function(
																					result) {
																					
																		//alert();
																		window.location = "customerDetails.jsp";
																		return false
																	});
																	
																	

																} else if(data.result == "Failure"){
																	
																	bootbox.alert(data.Message);
																	return false;
																				//});
																}else {
																	
																	bootbox.alert(data.Message);
																	return false;
																}
															}
														});
												return false;
											});
							
							
										$(document).on('click', '#customerEditsave', function () {
											
										var data1 = {}
										
										data1["firstName"] = $("#firstNameEdit").val();
										//data1["houseNumber"] = $("#houseNoEdit").val();
										data1["mobileNumber"] = $("#mobileNoEdit").val();
										data1["email"] = $("#emailEdit").val();
										data1["CRNNumber"] = $("#CRNEdit").val();
										data1["houseNumber"] = $("#houseNoEdit").val();
										if(sessionStorage.getItem("roleID") == 1 || sessionStorage.getItem("roleID") == 2 ){
											data1["meterID"] = $("#amrEdit").val();
											
											//data1["tariffID"] = $("#selectTariffNameEdit").val();	
										}
										data1["createdByID"] = sessionStorage.getItem("createdByID");
										data1["loggedInUserID"] = sessionStorage.getItem("userID");
										data1["loggedInRoleID"] = sessionStorage.getItem("roleID");
										$('#customerEditsave').prop('disabled', true).addClass('disabled').off( "click" );
										/*alert("===>"
												+ JSON.stringify(data1));*/
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./customer/edit/"+$("#CRNEdit").val(),
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(
															data) {
														/*alert("data"
																+ JSON
																		.stringify(data));*/
														if (data.result == "Success") {

															/*alert( "data"
																	+ data.result);*/
															
															bootbox.alert(data.Message,
																	function(
																			result) {
																location.reload();		
																//alert();
																//window.location = "customerDetails.jsp";
																return false
															});
														} else if(data.result == "Failure" && data.Message == undefined){
															
															bootbox.alert(data.Message);
															return false;
															
														}else {
															
															bootbox.alert(data.Message);
															return false;
															//return false;
														}
													}
												});
										return false;
									});
							
							
						});




function getCustomerFormEdit(id) {

	window.location("customerEdit.jsp?cust="+id);

}


function getCustomerFormDelete(CRNNumber){
	
	bootbox
	.confirm(
			"ARE YOU SURE TO DELETE CUSTOMER",
		function(
			result) {
			//	alert(result);
			if(result == true){
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "./customer/delete/" + CRNNumber,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							bootbox
							.confirm(
									data.Message,
								function(
									result) {
									window.location = "customerDetails.jsp";
								});

						} else {
							bootbox
							.alert(data.Message);
							return false;
						}
					}
				});
			}else if(result==false){
				//alert("@"+false)
				
			}
		});
	
}

function getCustomerMeters(CRNNumber){
	$.getJSON("./customer/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1", function(data) {
		$.each(data.data, function(i, item) {
			if (CRNNumber == item.CustomerUniqueID) {
				
				$('#customerMeterTable')
				.DataTable(
						{
							"dom" : "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
									
									"language": {
									      "emptyTable": "No data available in table"
									    },
									 
									 "responsive" : true,
										"serverSide" : false,
										"bDestroy" : true,
										"bPaginate": true,
										"pagging" : true,
										"bProcessing" : true,
										"ordering" : true,
										"order" : [ 0, "desc" ],
										"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
										"pageLength" : 5,
										
										"scrollX" : true,
										"data":item.meters,
						
							"columns" : [
								
									{
										"data" : "customerMeterID"
									},
									{
										"data" : "miuID"
										
									},
									 {
											"data" : "meterSerialNumber"
									},
									 {
										"data" : "meterType"
									}
									,
									{
										"data" : "meterSize"
										
									},
									 {
											"data" : "payType"
									},
									 {
										"data" : "tariffName"
									},
									 {
										"data" : "gatewayID"
								},
								 {
									"data" : "location"
								}
									
									
									],
										"columnDefs" : [/* {
											orderable : false,
											//targets: 5, visible: !(sessionStorage.getItem("roleID") == 4)
											"className": "dt-center", "targets": "_all"
										},
										{
											targets: 4, 
											visible: !(sessionStorage.getItem("roleID") == 5)
										}*/],

							"buttons" : [
									]
						})  
				
				
			} 
			else {
			}
		});
		$('#myCustomerMeters').modal('show');
	});
	
	
}


