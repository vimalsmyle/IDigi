/**
 * 
 */


$(document).ready(function() {
	if(sessionStorage.getItem("roleID") == 1){
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
	}else{
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
		
	}
	
table = $('#communityTable')
.DataTable(
{
	
	/*"initComplete": function(settings, json) {            
	    if (sessionStorage.getItem("roleID") == 1){
	        oTable.columns([0,1,3,4]).visible(false);
	        oTable.columns([]).visible(true);
	    }
	    else if (sessionStorage.getItem("roleID") == 4){
	        oTable.columns([0,1,3]).visible(false);
	        oTable.columns([4]).visible(true);
	    }
	},*/

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
"url":"./community/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
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
"data" : "address"
},{
"data" : "email"
},{
"data" : "mobileNumber"
}
,{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		return "<div id=tdfiled><a href=# id=CommunityEdit data-toggle=modal data-target=#myCommunityEdit onclick='getCommunityFormEdit("
																	+ row.communityID
																	+ ")'>"
																	+ "<i class='fa fa-edit'></i>"
																	+ "</a></div>"
	}
	}



],
"columnDefs" : [ {
	targets: 4, visible: ((sessionStorage.getItem("roleID") == 1) && ((sessionStorage.getItem("roleID") == 1) && !(sessionStorage.getItem("roleID") == 2) || !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4) || !(sessionStorage.getItem("roleID") == 5)))  
},
{
	"className": "dt-center", "targets": "_all"
}], "buttons": [
   
]
});
$("div.headname").html('<h3>Community Management</h3>');

$("div.addevent").html('<button type="button" id="communitypopup"' 
		+'class="btn btn-raised btn-primary float-right"'
		+'	data-toggle="modal" data-target="#exampleModal">'
		+'<i class="fa fa-user-plus"></i>'
		+'</button>');


});






$(document)
				.ready(
						function() {							/*alert(sessionStorage.getItem("roleID"));
							if(sessionStorage.getItem("roleID") == 1){
								$("#thfiled,#tdfiled").show();
							}else if(sessionStorage.getItem("roleID") == 4){
								$("#thfiled").hide();
								$("#tdfiled").hide();
								
							}*/
								$('#communityDetails')
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													communityNameAdd : {
														message : 'Name is not valid',
														validators : {
															notEmpty : {
																message : 'Name is required and cannot be empty'
															},
															stringLength : {
																min : 6,
																max : 30,
																message : 'Name must be more than 6 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z ]*$/,
																message : 'Name can only consist of alphabet'
															}
														}
													},
													communityAddressAdd : {
														message : 'Address is not valid',
														validators : {
															notEmpty : {
																message : 'Community Address is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'Address must be more than 6 and less than 30 characters long'
															}
														}
													},
													communityMobileAdd : {
														message : 'Mobile is not valid',
														validators : {
															notEmpty : {
																message : 'Mobile is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]{10}$/,
																message : 'Mobile can only consist of number'
															}
														}
													},
													communityEmailAdd : {
														message : 'Email is not valid',
														validators : {
															notEmpty : {
																message : 'Email is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The Community Address can only consist of alphabetical and number'
															}*/
														}
													}
												}
											});
							
							
							
							
							$('#communityEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											communityNameEdit : {
												message : 'Name is not valid',
												validators : {
													notEmpty : {
														message : 'Name is required and cannot be empty'
													},
													stringLength : {
														min : 6,
														max : 30,
														message : 'Name must be more than 6 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z ]*$/,
														message : 'Name can only consist of alphabet'
													}
												}
											},
											communityAddressEdit : {
												message : 'Address is not valid',
												validators : {
													notEmpty : {
														message : 'Address is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 30,
														message : 'Address must be more than 6 and less than 30 characters long'
													}
												}
											},
											communityMobileEdit : {
												message : 'Mobile is not valid',
												validators : {
													notEmpty : {
														message : 'Mobile is required and cannot be empty'
													},
													regexp : {
														regexp : /^[0-9]{10}$/,
														message : 'Mobile can only consist of number'
													}
												}
											},
											communityEmailEdit : {
												message : 'Email is not valid',
												validators : {
													notEmpty : {
														message : 'Email is required and cannot be empty'
													}/*,
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'The Community Address can only consist of alphabetical and number'
													}*/
												}
											}
										}
									});
							
							
							
							

							$('#communityDetails')
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
																	false);
												} else {
													$('.submit-button', $(this))
															.attr('disabled',
																	true);
												}
											});

							
							
							
							$('#communityEdit').on(
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
											$('#communityEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#communityEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
												$(document).on('click', '#communityAdd', function () {

												var data1 = {}
												data1["communityName"] = $("#communityNameAdd")
														.val();
												data1["email"] = $("#communityEmailAdd").val();
												data1["mobileNumber"] = $("#communityMobileAdd")
												.val();
												data1["address"] = $("#communityAddressAdd").val();
										
												$('#communityAdd').prop('disabled', true).addClass('disabled').off( "click" );
												
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./community/add",
															data : JSON
																	.stringify(data1),
															dataType : "JSON",

															success : function(
																	data) {
																
																if (data.result == "Success") {

																	/*alert( "data"
																			+ data.result);*/
																	swal({
																		  title: "Saved",
																		  text: data.Message,
																		  icon: "success"
																		}).then(function() {
																		    window.location = "communityDetails.jsp";
																		});
																	return false
																	

																} else if(data.result == "Failure"){
																	
																	swal({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		}).then(function() {
																		    window.location = "communityDetails.jsp";
																		});
																	return false;
																}
															}
														});
												return false;
											});
							
							
							
										$(document).on('click', '#communityEditsave', function () {
											
										var data1 = {}
										data1["communityName"] = $("#communityNameEdit")
												.val();
										data1["email"] = $("#communityEmailEdit").val();
										data1["mobileNumber"] = $("#communityMobileEdit")
										.val();
										data1["address"] = $("#communityAddressEdit").val();
								
										$('#communityEditsave').prop('disabled', true).addClass('disabled').off( "click" );
										
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./community/edit/"+$("#communityIdhidden").val(),
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(
															data) {
														
														if (data.result == "Success") {

															/*alert( "data"
																	+ data.result);*/
															
															swal({
																  title: "Saved",
																  text: data.Message,
																  icon: "success"
																}).then(function() {
																    window.location = "communityDetails.jsp";
																});
															return false
															

														} else if(data.result == "Failure"){
															
															swal({
																  title: "error",
																  text: data.Message,
																  icon: "error"
																}).then(function() {
																    window.location = "communityDetails.jsp";
																    return false;
																});
															
														}
													}
												});
										return false;
									});
							
							
						});




function getCommunityFormEdit(id) {

//	 alert(id);

	$.getJSON("./community/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.communityID) {
				$('#communityNameEdit').val(item.communityName).trigger("change");
				$("#formcomunityName").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#communityEmailEdit').val(item.email).trigger("change");
				$("#formcomunityEmail").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$('#communityMobileEdit').val(item.mobileNumber).trigger("change");
				$("#formcomunityMobile").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
			    $('#communityAddressEdit').val(item.address).trigger("change");
				$("#formcomunityAddress").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
				$("#communityIdhidden").val(item.communityID);
			
				$('#communityEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myCommunityEdit').modal('show');
	});
}