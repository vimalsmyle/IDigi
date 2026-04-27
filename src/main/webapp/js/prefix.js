
/**
 * 
 */


$(document).ready(function() {


 $('#meterType').on('change', function () {
        onChangeMeterSizeNew();
    });
    
     $('#meterTypeEdit').on('change', function () {
        onChangeMeterSizeEditNew();
    });
    
    
    
    function onChangeMeterSizeNew() {
		
			$('#meterSizeID').find('option').remove();
			$('#meterTypeEdit').find('option').remove();
		
		$.getJSON("./metersizes/" + $("#meterType").val(), function(data) {
			var Options = '<option value=-1>Select  Meter Size</option>';
			$.each(data.dropDownMeterSizes, function(key, value) {
				Options = Options + '<option value=' + key + '>' + value
					+ '</option>';
			});
			
				$('#meterSizeID').append(Options);
			
		});

	}
	
	
	function onChangeMeterSizeEditNew(selectedValue) {
		
			$('#meterSizeIDEdit').find('option').remove();
		
		$.getJSON("./metersizes/" + $("#meterTypeEdit").val(), function(data) {
			var Options = '<option value=-1>Select  Meter Size</option>';
			$.each(data.dropDownMeterSizes, function(key, value) {
				Options = Options + '<option value=' + key + '>' + value
					+ '</option>';
			});
			
				$('#meterSizeIDEdit').append(Options);
			
		});
		
		if (selectedValue) {
            $('#meterSizeIDEdit').val(String(selectedValue)).trigger("change");
        }

	}

	$.getJSON("./gateways", function(data) {
		var Options = "<option value='-1'>Select  Gateway</option>";
		$.each(data.dropDownGateways, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
				+ "</option>";
		});
		$('#gatewayIDAdd').append(Options);
	});


	if (sessionStorage.getItem("roleID") == 1) {
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" + "<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
	} else {
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" + "<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"

	}
	table = $('#blockTable')
		.DataTable(
			{
				"dom": dom1,
				"responsive": true,
				/*"processing" : true,*/
				"serverSide": false,
				"bDestroy": true,
				"bPaginate": true,
				"pagging": true,
				"bProcessing": true,
				"ordering": true,
				"order": [0, "desc"],
				"lengthMenu": [5, 10, 25, 30, 50, 75],
				"pageLength": 25,
				"scrollX": true,
				"ajax": {
					"url": "./prefix",
					"type": "GET",
					"data": function(search) {
					},
					"complete": function(json) {
						console.log(json);
						return json.data;
					},
				},
				"columns": [{
					"data": "prefixName"
				},
				{
					"data": "communityName"
				}, {
					"data": "blockName"
				}, {
					"data": "miuID"
				}, {
					"data": "meterType"
				}
					, {
					"data": "meterSize"
				}
					, {
					"data": "payType"
				}

					, {
					"data": "tariffName"
				}

					, {
					"data": "gatewayName"
				}

					, {
					"data": "thresholdMaximum"
				}
					, {
					"data": "thresholdMinimum"
				}
					, {
					"mData": "action",
					"render": function(data, type, row) {

						/*<button type="button"
							class="btn btn-raised btn-primary float-right"
							data-toggle="modal" data-target="#exampleModal">
							<i class="fa fa-user"></i>
						</button>*/
						//return "<a href='#communityEditModal' class='teal modal-trigger' data-toggle='modal' data-target='#communityEditModal' id='communityEditModal' onclick='getSocietyFormEdit("+row.communityID+")'><i class='material-icons' style='color:#17e9e9'>edit</i></a>"

						return "<div id = actionfield> <a href=# id=BlockEdit data-toggle=modal data-target=#myBlockEdit onclick='getBlockFormEdit("
							+ row.prefixID
							+ ")'>"
							+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
							+ "</a>"
							+ "<a onclick='getBlockFormDelete("
							+ row.prefixID
							+ ")'>"
							+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer;'>delete</i>"
							+ "</a></div>"
					}
				}



				],
				"columnDefs": [{
					//	orderable : false,
					targets: 5, visible: (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4) || !(sessionStorage.getItem("roleID") == 5)))
				},
				{
					"className": "dt-center", "targets": "_all"
				}], "buttons": [

				]
			});

	$("div.headname").html('<h3>Prefilled Management</h3>');

	$("div.addevent").html('<button type="button" id="blockAddButton"'
		+ 'class="btn btn-raised btn-primary float-right"'
		+ 'data-toggle="modal" data-target="#exampleModal">'
		+ '<i class="fa fa-plus">Add</i>'
		+ '</button>');

});






$(document)
	.ready(
		function() {
			$('#blockDetails')

				/*.find('[name="selectcommunityName"]')
.selectpicker()
.change(function(e) {
	$('#blockDetails').bootstrapValidator('revalidateField', 'selectcommunityName');
})
.end()*/

				.bootstrapValidator(
					{
						feedbackIcons: {
							valid: 'glyphicon glyphicon-ok',
							invalid: 'glyphicon glyphicon-remove',
							validating: 'glyphicon glyphicon-refresh'
						},
						fields: {
							/*selectcommunityName: {
								validators: {
									notEmpty: {
										message: 'Please select your native language.'
									}
								}
							},*/
							prefixName: {
								message: 'Prefix Name is not valid',
								validators: {
									notEmpty: {
										message: 'Prefix Name is required and cannot be empty'
									},
									stringLength: {
										min: 2,
										max: 35,
										message: 'Prefix Name must be more than 2 and less than 35 characters long'
									},
									regexp: {
										regexp: /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
										message: 'Prefix Name can only consist of Alphanumaric'
									}
								}
							},
							miuID: {
								message: 'MUI ID is not valid',
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
							meterSizeID: {
								message: 'MeterSIze is not valid',
								validators: {
									notEmpty: {
										message: 'MeterSIze is required and cannot be empty'
									}	,								stringLength : {
																min : 1,
																max : 30,
																message : 'Meter Size must be more than 1 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[0-9]+$/,
																message : 'Meter Size can only consist of Numaric'
															}
								}
							}
						}
					});




			$('#blockEditForm')
				.bootstrapValidator(
					{
						feedbackIcons: {
							valid: 'glyphicon glyphicon-ok',
							invalid: 'glyphicon glyphicon-remove',
							validating: 'glyphicon glyphicon-refresh'
						},
						fields: {
							prefixNameEdit: {
								message: 'Prefix Name is not valid',
								validators: {
									notEmpty: {
										message: 'Prefix Name is required and cannot be empty'
									},
									stringLength: {
										min: 2,
										max: 35,
										message: 'Prefix Name must be more than 2 and less than 35 characters long'
									},
									regexp: {
										regexp: /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
										message: 'Prefix Name can only consist of Alphanumaric'
									}
								}
							},
							miuIDEdit: {
								message: 'MUI ID is not valid',
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
							meterSizeIDEdit: {
								message: 'MeterSIze is not valid',
								validators: {
									notEmpty: {
										message: 'MeterSIze is required and cannot be empty'
									}	,								stringLength : {
																min : 1,
																max : 30,
																message : 'Meter Size must be more than 1 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[0-9]+$/,
																message : 'Meter Size can only consist of Numaric'
															}
								}
							}
						}
					});





			$('#blockDetails')
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




			$('#blockEdit').on(
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
						$('#blockEdit', $(this))
							.attr('disabled',
								false);
					} else {
						$('#blockEdit', $(this))
							.attr('disabled',
								true);
					}
				});



			$(document).on('click', '#blockAdd', function() {


				//alert(""+$("#selectcommunityName").val());

				if($("#selectcommunityName").val() == -1 || $("#selectcommunityName").val() == null || $("#selectcommunityName").val() == "Select Community"){
																	
																	swal.fire({
																		  title: "error",
																		  text: "Select Community Id",
																		  icon: "error"
																		});
																	return false;
																} 
																
																if($("#selectBlockBasedonCommunity").val() == -1 || $("#selectBlockBasedonCommunity").val() == null || $("#selectBlockBasedonCommunity").val() == "Select Block"){
																	
																	swal.fire({
																		  title: "error",
																		  text: "Select block id",
																		  icon: "error"
																		});
																	return false;
																}
																
																
																if($("#meterType").val() == -1 || $("#meterType").val() == null || $("#meterType").val() == "-1"){
																	
																	swal.fire({
																		  title: "error",
																		  text: "Select Meter type",
																		  icon: "error"
																		});
																	return false;
																}
																
																
																if($("#meterSizeID").val() == -1 || $("#meterSizeID").val() == null || $("#meterSizeID").val() == "-1"){
																	
																	swal.fire({
																		  title: "error",
																		  text: "Select Meter Size",
																		  icon: "error"
																		});
																	return false;
																}
																
																
																
																
																
																if($("#selectTariffName").val() == -1 || $("#selectTariffName").val() == null || $("#selectTariffName").val() == "Select Tariff"){
																	
																	swal.fire({
																		  title: "error",
																		  text: "Select Tariff id",
																		  icon: "error"
																		});
																	return false;
																}
																
																	if($("#gatewayIDAdd").val() == -1 || $("#gatewayIDAdd").val() == null || $("#gatewayIDAdd").val() == "Select Gateway"){
																	
																	swal.fire({
																		  title: "error",
																		  text: "Select Gateway Id",
																		  icon: "error"
																		});
																	return false;
																}
																
																
																

				var requestBody = {
				        prefixName: $("#prefixName").val(),

				        // dropdowns
				        communityID: parseInt($("#selectcommunityName").val()),
				        blockID: parseInt($("#selectBlockBasedonCommunity").val()),

				        miuID: $("#miuID").val(),
				        meterType: $("#meterType").val(),

				        meterSizeID: parseInt($("#meterSizeID").val()),
				        payType: $("#payType").val(),

				        tariffID: parseInt($("#selectTariffName").val()),
				        gatewayID: parseInt($("#gatewayIDAdd").val()),

				        thresholdMaximum: parseInt($("#thresholdMaximum").val()),
				        thresholdMinimum: parseInt($("#thresholdMinimum").val())
				    };

				$('#blockAdd').prop('disabled', true).addClass('disabled').off("click");
				$("#loader").show();
				/*console.log("===>"
						+ JSON.stringify(data1));*/
				$
					.ajax({
						type: "POST",
						contentType: "application/json",
						url: "./prefix/add",
						data: JSON
							.stringify(requestBody),
						dataType: "JSON",

						success: function(
							data) {
							$("#loader").hide();
							if (data.result == "Success") {

								swal.fire({
									title: "Saved",
									text: data.Message,
									icon: "success"
								}).then(function() {
									window.location = "prefix.jsp";

								});
								return false;



							} else if (data.result == "Failure") {

								swal.fire({
									title: "error",
									text: data.Message,
									icon: "error"
								}).then(function() {
									window.location = "prefix.jsp";

								});
								return false;


							} else {

								swal.fire({
									title: "error",
									text: "Something went Wrong",
									icon: "error"
								}).then(function() {
									window.location = "blockDetails.jsp";

								});

							}
						}
					});
				return false;
			});



			$("#blockEdit")
				.click(
					function() {


						if($("#meterSizeIDEdit").val() == -1 || $("#meterSizeIDEdit").val() == null || $("#meterSizeIDEdit").val() == "-1"){
																	
																	swal.fire({
																		  title: "error",
																		  text: "Select Meter Size",
																		  icon: "error"
																		});
																	return false;
																}

				

						
						if($("#selectTariffNameEdit").val() == -1 || $("#selectTariffNameEdit").val() == null || $("#selectTariffNameEdit").val() == "Select Tariff"){
																	
																	swal.fire({
																		  title: "error",
																		  text: "Select Tariff id",
																		  icon: "error"
																		});
																	return false;
																}
																
																	if($("#gatewayIDAddEdit").val() == -1 || $("#gatewayIDAddEdit").val() == null || $("#gatewayIDAddEdit").val() == "Select Gateway"){
																	
																	swal.fire({
																		  title: "error",
																		  text: "Select Gateway Id",
																		  icon: "error"
																		});
																	return false;
																}
																
																
																
																
																
																

						var requestBody = {
				        prefixName: $("#prefixNameEdit").val(),

				        // dropdowns
				        communityID: parseInt($("#selectcommunityNameEditHidden").val()),
				        blockID: parseInt($("#selectBlockBasedonCommunityEditHidden").val()),

				        miuID: $("#miuIDEdit").val(),
				        meterType: $("#meterTypeEdit").val(),

				        meterSizeID: parseInt($("#meterSizeIDEdit").val()),
				        payType: $("#payTypeEdit").val(),

				        tariffID: parseInt($("#selectTariffNameEdit").val()),
				        gatewayID: parseInt($("#gatewayIDAddEdit").val()),

				        thresholdMaximum: parseInt($("#thresholdMaximumEdit").val()),
				        thresholdMinimum: parseInt($("#thresholdMinimumEdit").val())
				    };

						/*alert("===>"
								+ JSON.stringify(blockEditForm));*/
						$('#blockEdit').prop('disabled', true).addClass('disabled').off("click");
						$("#loader").show();
						$
							.ajax({
								type: "POST",
								contentType: "application/json",
								url: "./prefix/edit/" + $("#prefixIdhidden").val(),
								data: JSON
									.stringify(requestBody),
								dataType: "JSON",

								success: function(
									data) {
									/*alert("data"
											+ JSON
													.stringify(requestBody));*/
									$("#loader").hide();
									if (data.result == "Success") {

										/*alert( "data"
												+ data.result);*/

										swal.fire({
											title: "Saved",
											text: data.Message,
											icon: "success"
										}).then(function() {
											window.location = "prefix.jsp";

										});
										return false;


									} else if (data.result == "Failure") {

										swal.fire({
											title: "error",
											text: data.Message,
											icon: "error"
										}).then(function() {
											window.location = "prefix.jsp";

										});
										return false;
										//});
									}
								}
							});
						return false;
					});


		});




function getBlockFormEdit(id) {

	// alert(id);

	$.getJSON("./prefix", function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.prefixID) {
				
				$('#prefixNameEdit').val(item.prefixName).trigger("change");
				$("#formprefixNameEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				
				$('#selectcommunityNameEdit').val(item.communityName);
				$("#formcomunityNameEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				$("#selectcommunityNameEditHidden").val(item.communityID);
				
				
				$("#selectBlockBasedonCommunityEditHidden").val(item.blockID);
				
				$('#selectBlockBasedonCommunityEdit').val(item.blockName);
				
				$("#formblockNameEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				
				$('#miuIDEdit').val(item.miuID).trigger("change");
				
				$("#formMiuIdEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				
				$('#meterTypeEdit').val(item.meterType).trigger("change");
				$("#formmeterTypeEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
					$('#prefixIdhidden').val(item.prefixID)
				
				$('#meterSizeIDEdit').val(item.meterSize).trigger("change");
				$("#formmeterSizeIDEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				$('#payTypeEdit').val(item.payType).trigger("change");
				$("#formpayTypeEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				$('#selectTariffNameEdit').val(item.tariffID).trigger("change");
				
				
				$.getJSON("./gateways", function(data) {

    var options = "<option value='-1'>Select Gateway</option>";

    $.each(data.dropDownGateways, function(key, value) {
        options += "<option value='" + key + "'>" + value + "</option>";
    });

    $('#gatewayIDAddEdit').html(options); // better than append

    // ✅ Set value AFTER options are added
    $('#gatewayIDAddEdit').val(item.gatewayID).trigger("change");

    $("#formgatewayIDAddEdit")
        .addClass("group form-group has-feedback has-success bmd-form-group is-filled");
});
				
				$('#thresholdMaximumEdit').val(item.thresholdMaximum).trigger("change");
				$("#formthresholdMaximumEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				$('#thresholdMinimumEdit').val(item.thresholdMinimum).trigger("change");
				$("#formthresholdMinimumEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")

				$('#blockEdit')
					.attr('disabled',
						false);

			} 
		});
		$('#editPrefixModal').modal('show');
	});
}



function getBlockFormDelete(blockId) {

	swal.fire({
		title: "Are you sure?",
		text: "ARE YOU SURE TO DELETE Prefill!",
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
					type: "POST",
					contentType: "application/json",
					url: "./prefix/delete/" + blockId,
					dataType: "JSON",
					success: function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							swal.fire({
								title: "Deleted",
								text: data.Message,
								icon: "success"
							}).then(function() {
								window.location = "prefix.jsp";
							});
							return false;

						} else {
							swal.fire({
								title: "error",
								text: data.Message,
								icon: "error"
							}).then(function() {
								window.location = "prefix.jsp";

							});
							return false;
						}
					}
				});
			} else {
				swal.fire("Cancelled", "Your Prefilled Details is safe :)", "error");
			}
		});


}