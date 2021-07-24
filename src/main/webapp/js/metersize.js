/**
 * 
 */


$(document).ready(function() {
	if(sessionStorage.getItem("roleID") == 1){
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
	}else{
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
		
	}
	
table = $('#metersizeTable')
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
"ajax" : {
"url":"./metersize",
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
"data" : "meterType"
},{
"data" : "meterSize"
},{
"data" : "perUnitValue"
},{
"data" : "modifiedDate"
}
,{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		return "<div id=tdfiled><a href=# id=metersizeEdit data-toggle=modal data-target=#mymetersizeEdit onclick='getMeterSizeFormEdit("
																	+ row.meterSizeID
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
   
],
language: {
    paginate: {
      next: '<i class="fa fa-angle-right"></i>', // or '→'
      previous: '<i class="fa fa-angle-left"></i>;' // or '←' 
    }
  }

});
$("div.headname").html('<h3>Meter Size</h3>');

$("div.addevent").html('<button type="button" id="metersizepopup"' 
		+'class="btn btn-raised btn-primary float-right"'
		+'	data-toggle="modal" data-target="#exampleModal">'
		+'<i class="fa fa-user-plus"></i>'
		+'</button>');


});






$(document)
				.ready(
						function() {
								$('#metersizeDetails')
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													meterSizeAdd : {
														message : 'Meter Size is not valid',
														validators : {
															notEmpty : {
																message : 'Meter Size is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 10,
																message : 'Name must be more than 2 and less than 10 characters long'
															},
															regexp : {
																regexp : /^[0-9 ]*$/,
																message : 'Name can only consist of number'
															}
														}
													},
													perUnitValueAdd : {
														message : 'Per unit value is not valid',
														validators : {
															notEmpty : {
																message : 'Per unit value is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 10,
																message : 'Per unit value must be more than 2 and less than 10 characters long'
															},
															regexp : {
																regexp : /^[0-9]*$/,
																message : 'Per Unit Value can only consist of number'
															}
														}
													}
												}
											});
							
							
							
							
							$('#meterSizeEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											meterSizeeEdit : {
												message : 'Meter size is not valid',
												validators : {
													notEmpty : {
														message : 'Meter size is required and cannot be empty'
													},
													stringLength : {
														min : 3,
														max : 10,
														message : 'Meter size must be more than 6 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[0-9]*$/,
														message : 'Meter size can only consist of number'
													}
												}
											},
											perUnitValueEdit : {
												message : 'Per Unit Value is not valid',
												validators : {
													notEmpty : {
														message : 'Per Unit Value is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 10,
														message : 'Per Unit Value must be more than 2 and less than 10 characters long'
													},
													regexp : {
														regexp : /^[0-9]*$/,
														message : 'Per Unit Value can only consist of number'
													}
												}
											}
										}
									});
							
							
							
							

							$('#metersizeDetails')
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

							
							
							
							$('#meterSizeEdit').on(
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
											$('#metersizeEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#metersizeEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
												$(document).on('click', '#metersizeAdd', function () {

												var data1 = {}
												data1["meterType"] = $("#meterTypeAdd")
														.val();
												data1["meterSize"] = $("#meterSizeAdd").val();
												data1["perUnitValue"] = $("#perUnitValueAdd")
												.val();
												data1["gatewayPort"] = $("#gatewayPortAdd").val();
										
												$('#metersizeAdd').prop('disabled', true).addClass('disabled').off( "click" );
												
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./metersize/add",
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
																		    window.location = "MeterSize.jsp";
																		});
																	return false
																	

																} else if(data.result == "Failure"){
																	
																	swal({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		}).then(function() {
																		    window.location = "MeterSize.jsp";
																		});
																	return false;
																}
															}
														});
												return false;
											});
							
							
							
										$(document).on('click', '#metersizeEditsave', function () {
											
										var data1 = {}
										data1["meterType"] = $("#meterTypeEdit")
												.val();
										data1["meterSize"] = $("#meterSizeeEdit").val();
										data1["perUnitValue"] = $("#perUnitValueEdit")
										.val();
								
										$('#metersizeEditsave').prop('disabled', true).addClass('disabled').off( "click" );
										
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./metersize/edit/"+$("#meterSizeIDhidden").val(),
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
																    window.location = "MeterSize.jsp";
																});
															return false;
															

														} else if(data.result == "Failure"){
															
															swal({
																  title: "error",
																  text: data.Message,
																  icon: "error"
																}).then(function() {
																    window.location = "MeterSize.jsp";
																    return false;
																});
															
														}
													}
												});
										return false;
									});
							
							
						});




function getMeterSizeFormEdit(id) {

//	 alert(id);

	$.getJSON("./metersize", function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.meterSizeID) {
				$('#meterTypeEdit').val(item.meterType).trigger("change");
				$("#formmeterType").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#meterSizeeEdit').val(item.meterSize).trigger("change");
				$("#formmeterSize").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#perUnitValueEdit').val(item.perUnitValue).trigger("change");
				$("#formperUnitValue").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			   
				$("#meterSizeIDhidden").val(item.meterSizeID);
			
				$('#metersizeEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#mymetersizeEdit').modal('show');
	});
}