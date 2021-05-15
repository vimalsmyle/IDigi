
/**
 * 
 */


$(document).ready(function() {
	if(sessionStorage.getItem("roleID") == 1){
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
	}else{
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>";
		
	}
table = $('#gatewayTable')
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
"scrollY" : 324,
"scrollX" : true,
"ajax" : {
"url":"./gateway",
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
"data" : "gatewayName"
},{
"data" : "gatewaySerialNumber"
},{
"data" : "gatewayIP"
},{
"data" : "gatewayPort"
}
,{
"data" : "modifiedDate"
}
,{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		/*<button type="button"
			class="btn btn-raised btn-primary float-right"
			data-toggle="modal" data-target="#exampleModal">
			<i class="fa fa-user"></i>
		</button>*/
	//return "<a href='#communityEditModal' class='teal modal-trigger' data-toggle='modal' data-target='#communityEditModal' id='communityEditModal' onclick='getSocietyFormEdit("+row.communityID+")'><i class='material-icons' style='color:#17e9e9'>edit</i></a>"
		
		return "<div id = actionfield> <a href=# id=GatewayEdit data-toggle=modal data-target=#myBlockEdit onclick='getGatewayFormEdit("
																	+ row.gatewayID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a>"
																	+"<a onclick='getGatewayFormDelete("
																	+ row.gatewayID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer;'>delete</i>"
																	+ "</a></div>"
	}
	}



],
"columnDefs" : [ {
//	orderable : false,
	targets: 5, visible: (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4) || !(sessionStorage.getItem("roleID") == 5)))
},
{
	"className": "dt-center", "targets": "_all"
}], "buttons": [
	   
	]
});

$("div.headname").html('<h3>Gateway Managemnent</h3>');

$("div.addevent").html('<button type="button" id="gatewayAddButton"'
		+'class="btn btn-raised btn-primary float-right"'
			+'data-toggle="modal" data-target="#exampleModal">'
			+'<i class="fa fa-user-plus"></i>'
			+'</button>');

});






$(document)
				.ready(
						function() {
							$('#gatewayDetails')
							
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													gatewayNameAdd : {
														message : 'Gateway Name is not valid',
														validators : {
															notEmpty : {
																message : 'Gateway Name is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'Gateway Name must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
																message : 'Gateway Name can only consist of Alphanumaric'
															}
														}
													},
													serialNumberAdd : {
														message : 'Serial Number is not valid',
														validators : {
															notEmpty : {
																message : 'Serial Number is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'Locaton must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z ]+$/,
																message : 'Serial Number can only consist of alphabetical'
															}
														}
													},
													ipAdd : {
														message : 'ip is not valid',
														validators : {
															notEmpty : {
																message : 'IP is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]{10}$/,
																message : 'IP can only consist of number'
															}
														}
													},
													portAdd : {
														message : 'Port is not valid',
														validators : {
															notEmpty : {
																message : 'Port is required and cannot be empty'
															}
														}
													}
												}
											});
							
							
							
							
							$('#gatewayEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											gatewayNameEdit : {
												message : 'Gateway Name is not valid',
												validators : {
													notEmpty : {
														message : 'Gateway Name is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'Gateway Name must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'Gateway Name can only consist of Alphanumaric'
													}
												}
											},
											serialNumberEdit : {
												message : 'Serial Number is not valid',
												validators : {
													notEmpty : {
														message : 'Serial Number is required and cannot be empty'
													},
													stringLength : {
														min : 6,
														max : 30,
														message : 'Serial Number must be more than 6 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'Serial Number can only consist of Alphanumaric'
													}
												}
											},
											ipEdit : {
												message : 'IP is not valid',
												validators : {
													notEmpty : {
														message : 'IP is required and cannot be empty'
													},
													regexp : {
														regexp : /^\d{10}$/,
														message : 'IP can only consist of number'
													}
												}
											},
											portEdit : {
												message : 'Port is not valid',
												validators : {
													notEmpty : {
														message : 'Port is required and cannot be empty'
													}
												}
											}
										}
									});
							
							
							
							

							$('#gatewayDetails')
									.on(
											'status.field.bv',
											function(e, data) {
												formIsValid = true;
												$('.form-group', $(this))
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

							
							
							
							$('#gatewayEdit').on(
									'status.field.bv',
									function(e, data) {
										formIsValid = true;
										$('.form-group', $(this))
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
											$('#gatewayEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#gatewayEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
							
												$(document).on('click', '#gatewayAdd', function () {
													 
												var data1 = {}
												data1["gatewayName"] = $("#gatewayNameAdd").val();
												data1["gatewaySerialNumber"] = $("#serialNumberAdd").val();
												data1["gatewayIP"] = $("#ipAdd").val();
												data1["gatewayPort"] = $("#portAdd").val();
												data1["createdByID"] = sessionStorage.getItem("createdByID");
												data1["loggedInUserID"] = sessionStorage.getItem("userID");
												data1["roleID"] = sessionStorage.getItem("roleID");

												$('#gatewayAdd').prop('disabled', true).addClass('disabled').off( "click" );
												
												/*console.log("===>"
														+ JSON.stringify(data1));*/
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./gateway/add",
															data : JSON
																	.stringify(data1),
															dataType : "JSON",

															success : function(
																	data) {

																if (data.result == "Success") {

																	swal.fire({
																		  title: "Saved",
																		  text: data.Message,
																		  icon: "success"
																		}).then(function() {
																		    window.location = "gateway.jsp";
																		    
																		});
																	return false;
																
																	

																} else if(data.result == "Failure"){
									
																	swal.fire({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		}).then(function() {
																		    window.location = "gateway.jsp";
																		    
																		});
																	return false;
																	
																	
																}else {
																	
																	swal.fire({
																		  title: "error",
																		  text: "Something went Wrong",
																		  icon: "error"
																		}).then(function() {
																		    window.location = "gateway.jsp";
																		    
																		});
																	
																}
															}
														});
												return false;
											});
							
							
							
							$("#gatewayEditsave")
							.click(
									function() {

										var data1 = {}
										
										var data1 = {}
										data1["gatewayName"] = $("#gatewayNameEdit").val();
										data1["gatewaySerialNumber"] = $("#serialNumberEdit").val();
										data1["gatewayIP"] = $("#ipEdit").val();
										data1["gatewayPort"] = $("#portEdit").val();
										data1["createdByID"] = sessionStorage.getItem("createdByID");
										data1["loggedInUserID"] = sessionStorage.getItem("userID");
										data1["roleID"] = sessionStorage.getItem("roleID");
								
										/*alert("===>"
												+ JSON.stringify(data1));*/
										$('#blockEditsave').prop('disabled', true).addClass('disabled').off( "click" );
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./gateway/edit/"+$("#gatewayIdhidden").val(),
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
															
															swal.fire({
																  title: "Saved",
																  text: data.Message,
																  icon: "success"
																}).then(function() {
																    window.location = "gateway.jsp";
																    
																});
															return false;
															

														} else if(data.result == "Failure"){
															
															swal.fire({
																  title: "error",
																  text: data.Message,
																  icon: "error"
																}).then(function() {
																    window.location = "gateway.jsp";
																    
																});
															return false;
															//});
														}
													}
												});
										return false;
									});
							
							
						});




function getGatewayFormEdit(id) {

 // alert(id);

	$.getJSON("./gateway/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.gatewayID) {
				
				$('#gatewayNameEdit').val(item.communityName).trigger("change");
				$("#formgatewayName").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#serialNumberEdit').val(item.blockName).trigger("change");
				$("#formserialNumber").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#ipEdit').val(item.Location).trigger("change");
				$("#formip").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
			    $('#portEdit').val(item.mobile).trigger("change");
				$("#formport").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$("#gatewayIdhidden").val(item.gatewayID);
			
				$('#gatewayEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myGatewayEdit').modal('show');
	});
}



function getBlockFormDelete(blockId){
	
	swal.fire({
		  title: "Are you sure?",
		  text: "ARE YOU SURE TO DELETE BLOCK!",
		  icon: "warning",
		  showCancelButton: true,
		  confirmButtonClass: "btn-danger",
		  confirmButtonText: "Yes, delete it!",
		  cancelButtonText: "No, cancel plx!",
		  closeOnConfirm: false,
		  closeOnCancel: false
		}).then(
		function(isConfirm) {
		  if (isConfirm.isConfirmed) {
			  
			  $.ajax({
					type : "POST",
					contentType : "application/json",
					url : "./block/delete/" + blockId,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							swal.fire({
								  title: "Deleted",
								  text: data.Message,
								  icon: "success"
								}).then(function() {
								    window.location = "blockDetails.jsp";
								});
							return false;

						} else {
							swal.fire({
								  title: "error",
								  text: data.Message,
								  icon: "error"
								}).then(function() {
								    window.location = "blockDetails.jsp";
								    
								});
							return false;
						}
					}
				});
		  } else {
		    swal.fire("Cancelled", "Your Block Details is safe :)", "error");
		  }
		});

	
}