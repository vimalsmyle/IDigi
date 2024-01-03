							// community - > CommunityId		
									$(document).ready(function() {
										
										if(sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
											$("#communityNameAdd").val(sessionStorage.getItem("communityName"));
											$("#formcommunityNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
											$("#blockNameAdd").val(sessionStorage.getItem("blockName"));
											$("#formblockNameAdd").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
										}
										
										$("#sensorTable1").hide();
										
										table = $('#sensorTable')
										.DataTable(
										{
											"dom": "<'row'<'col-sm-4 headname'><'col-sm-3'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>",
											/*"processing" : false,*/
											"serverSide" : false,
											"bDestroy" : true,
											"pagging" : true,
											"bPaginate": true,
											"bProcessing" : false,
											"ordering" : true,
											"order" : [ 0, "desc" ],
											"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
											"pageLength" : 25,
										"scrollY" : 324,
										"scrollX" : true,
										"ajax" : {
										"url":"./sensordashboard/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"),
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
											"mData" : "action",
											"render" : function(data, type, row) {
												
												return '<div class=btn btn-secondary data-toggle="tooltip" data-placement=top ' +
														'title='+row.blockName+'>' 
												+row.houseNumber +
												 '</div>';
											}
											},{
										"data" : "meterID"
										},{
										"data" : "batteryVoltage"
										},{
										"data" : "tamper"
										},{
										"data" : "dateTime"
										},{
										"data" : "difference"
										}
										],
										"columnDefs" : [ {
											//targets : 11, visible:  (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 3)) && (!(sessionStorage.getItem("roleID") == 5) || !(sessionStorage.getItem("roleID") == 4)))
											"className": "dt-center", "targets": "_all"
										}], "buttons": [
											{
												extend: 'excel',
										        footer: 'true',
										        //text: 'Excel',
										        //className : 'custom-btn fa fa-file-excel-o',
										        title:'Sensor Details'  },
										         
										        {
										        extend: 'pdf',
										        footer: 'true',
										        exportOptions: {
										            columns: [0,1,2,3,4,5,6]
										        },
										       // text: 'pdf',
										        //className : 'custom-btn fa fa-file-pdf-o',
										        orientation: 'landscape',
										        title:'Sensor Details'  },
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
										],
										 initComplete: function() {
											   $('.buttons-excel').html('<i class="fa fa-file-excel-o" />')
											   $('.buttons-pdf').html('<i class="fa fa-file-pdf-o" />')
											  }
										});
										 $("div.headname").html('<h3>Sensor Details</h3>');
										 
										 (sessionStorage.getItem("roleID") == 2) || (sessionStorage.getItem("roleID") == 5) ? $(".customButton").remove() : ""
											 
										});