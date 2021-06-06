/**
 * 
 */
$(document)
		.ready(
				function() {
					
					$("#highchart_container2").hide();

  if(sessionStorage.getItem("roleID") != 3){
	  
	  $.getJSON("./homedashboard/Gas/" +sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
			//var Options = "";
  	  document.querySelector("#gasActive").innerText = data.active;
  	  document.querySelector("#gasInactive").innerText = data.inActive;
  	  document.querySelector("#gasLive").innerText = data.nonLive;
  	  document.querySelector("#gasnonLive").innerText = data.live;
  	  document.querySelector("#gasemergency").innerText = data.emergency;
  	  document.querySelector("#gasLowbattery").innerText = data.lowBattery;
  	  document.querySelector("#gasActivePercentage").innerText = data.activePercentage;
  	  document.querySelector("#gasinactivePercentage").innerText = data.inActivePercentage;
			
		});
	  
	  
	  
	  $.getJSON("./homedashboard/water/" +sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
			//var Options = "";
		  document.querySelector("#waterActive").innerText = data.active;
	  	  document.querySelector("#waterInactive").innerText = data.inActive;
	  	  document.querySelector("#waterLive").innerText = data.nonLive;
	  	  document.querySelector("#waternonLive").innerText = data.live;
	  	  document.querySelector("#wateremergency").innerText = data.emergency;
	  	  document.querySelector("#waterLowbattery").innerText = data.lowBattery;
	  	  document.querySelector("#waterActivePercentage").innerText = data.activePercentage;
	  	  document.querySelector("#waterinactivePercentage").innerText = data.inActivePercentage;
			
		});
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "./graph/"
					+ sessionStorage.getItem("roleID") + "/2021/06"
					+ sessionStorage.getItem("ID"),
			dataType : "JSON",

			success : function(d) {

				$('#container12').highcharts(
						{
							chart : {
								type : 'bar',
									backgroundColor: 'transparent'
							},
							title : {
								text : ''
							},
							/*subtitle : {
								text : 'Status'
							},*/
							xAxis : {
								categories : [ 'Active',
										'In-Active', 'Live',
										'Non-Live', 'Low Battery',
										'Emergency Credit' ],
										labels: {
							                style: {
							                    fontWeight: 'bold',
							                    color: 'black'
							                }
							            },

								title : {
									text : null
								},
							},
							yAxis : {
								min : 0,
								title : {
									text : 'Chart',
									align : 'high'
								},
								labels : {
									overflow : 'justify'
								},
								min : 0,
								max : 100

							},
							tooltip : {
								valueSuffix : ''
							},
							plotOptions : {
								bar : {
									dataLabels : {
										enabled : true
									}
								}
							},
							credits : {
								enabled : false
							},
							series : [ {
								data : [ d.activePercentage,
										d.inActivePercentage,
										d.livePercentage,
										d.nonLivePercentage,
										d.lowBatteryPercentage, 
										d.emergencyPercentage
										],
										labels: {
							                style: {
							                    fontWeight: 'bold',
							                    color: 'black'
							                }
							            },
								name : '(%)'
							} ]

						});
			}
		});
		}
		if(sessionStorage.getItem("roleID") == 3){
			
			
			$(document).on('click', '#view', function () {
				
				var month = $("#month").val();
				var year = $("#start_date").val();
				
				$("#highchart_container1").hide();
				$("#highchart_container2").show();
				
				
				$.ajax({
					type : "GET",
					contentType : "application/json",
					url : "./graph/"
							+ $("#start_date").val() + "/"
							+ $("#month").val()+"/"+sessionStorage.getItem("ID"),
					dataType : "JSON",

					success : function(d) {

						$('#highchart_container2').highcharts(
								{
									chart : {
										type : 'line',
											backgroundColor: 'transparent'
									},
									title : {
										text : 'Consumption Graph'
									},
									subtitle : {
										text : sessionStorage.getItem("ID")
									},
									xAxis : {
										categories : d.xAxis,

										title : {
											text : null
										},
									},
									yAxis : {
										min : 0,
										title : {
											text : 'Chart',
											align : 'high'
										},
										labels : {
											overflow : 'justify'
										}
										

									},
									tooltip : {
										valueSuffix : ''
									},
									plotOptions : {
										bar : {
											dataLabels : {
												enabled : true
											}
										}
									},
									
									legend: {
									    layout: 'vertical',
									    align: 'right',
									    verticalAlign: 'top',
									    x: -40,
									    y: 100,
									    floating: true,
									    borderWidth: 1,
									    backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
									    shadow: true
									},
									 
									credits : {
										//enabled : false
									},
									series : [ {
										data : d.yAxis ,
										name : ''
									} ]

								});
					}
				});
				
				
			});
			 $.getJSON("./dashboard/" +sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1", function(data) {
				 $.each(data.data, function(i, item) {
//					 alert();
		    	  document.querySelector("#lastBillAmount").innerText = item.lastTopupAmount;
		    	  document.querySelector("#lastBillDate").innerText = item.lastRechargeDate;
		    	  document.querySelector("#community").innerText = item.communityName;
		    	  document.querySelector("#block").innerText = item.blockName;
		    	  document.querySelector("#CRN_Number").innerText = item.CRNNumber;
		    	  document.querySelector("#balance").innerText = item.balance;
		    	  document.querySelector("#valveStatus").innerText = item.valveStatus;
		    	  document.querySelector("#meterStatus").innerText = item.tamperStatus;
		    	  document.querySelector("#batteryStatus").innerText = item.battery;
				
				 });
				 if(data.data.length == 0){
					 document.querySelector(".balance").innerText = "---";
			    	 document.querySelector(".valveStatus").innerText = "---";
			    	 document.querySelector("#lastBillAmount").innerText = "---";
			    	  document.querySelector("#lastBillDate").innerText = "---";
				 }
				 
				});
			 
			 
			 $.getJSON("./customer/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID")+"/-1", function(data) {
					$.each(data.data, function(i, item) {
						
							document.querySelector(".community").innerText = item.communityName;
				    	  document.querySelector(".block").innerText = item.blockName;
				    	  document.querySelector(".CRN_Number").innerText = item.CRNNumber;
				    	  
						
					});
				});
			 
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "./graph/"
					+ 0 + "/"
					+ 0+"/"+sessionStorage.getItem("ID"),
			dataType : "JSON",

			success : function(d) {

				$('#highchart_container1').highcharts(
						{
							chart : {
								type : 'line'
							},
							title : {
								text : 'Consumption Graph'
							},
							subtitle : {
								text : sessionStorage.getItem("ID")
							},
							xAxis : {
								categories : d.xAxis,

								title : {
									text : null
								},
							},
							yAxis : {
								min : 0,
								title : {
									text : 'Chart',
									align : 'high'
								},
								labels : {
									overflow : 'justify'
								},
								min : 0
								

							},
							tooltip : {
								valueSuffix : ''
							},
							plotOptions : {
								bar : {
									dataLabels : {
										enabled : true
									}
								}
							},
							
							legend: {
							    layout: 'vertical',
							    align: 'right',
							    verticalAlign: 'top',
							    x: -40,
							    y: 100,
							    floating: true,
							    borderWidth: 1,
							    backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
							    shadow: true
							},
							 
							credits : {
								//enabled : false
							},
							series : [ {
								data : d.yAxis ,
								name : ''
							} ]

						});
			}
		});
		}
		
 });