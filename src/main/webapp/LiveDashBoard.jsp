<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
<link rel="icon" type="image/png" sizes="16x16" href="common/images/1-hanbit.png">
<!-- Material Design for Bootstrap CSS -->
<!-- <link rel="stylesheet" href="common/css/style.css"> -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">
	
	<link href="common/css/materialize.fontawsome.css"
	rel="stylesheet">
<title>Customer Details</title>
</head>


<body class ="innerbody">


<%
		String user_id = (String) session.getAttribute("roleID");

	%>

	<%
		if (user_id == null) {
			response.sendRedirect("login.jsp");
		}else {
	%>
	<div id="preloader">
	  <div id="status">&nbsp;</div>
	</div>

	<jsp:include page="header.jsp" />
	
	<jsp:include page="slidebar.jsp" />
	 <main class="col-md-10 float-left col px-5 pl-md-3 pt-2 pb-6 main">
            <a href="#" class="sideHide"><i class="text-dark fa fa-navicon fa-lg py-2 p-1"></i></a>
            <div class="page-header">
                <h2 class="display-4 font-weight-light">Dashboard</h2>
            </div>
			<div class="row">
			<div class="col-md-12">
			<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Monthly Recap Report</h3>
			</div>
            <div class="box-body">
              <div class="row">
                <div class="col-md-8">
                  <p class="text-center">
                    <strong>Sales: 1 Jan, 2014 - 30 Jul, 2014</strong>
                  </p>
				  <div class="chart">
					 <canvas id="myChart" style="height:155px; width: 703px;" height="155" width="703"></canvas>
				  </div>
                </div>
                <div class="col-md-4">
                  <p class="text-center">
                    <strong>Goal Completion</strong>
                  </p>
				  <div class="chart">
					 <canvas id="myChart1" height="100" width="205" style="width: 205px; height: 100px;"></canvas>
				  </div>
				</div>
              </div>
            </div>
            <div class="box-footer">
              <div class="row">
                <div class="col-sm-3 col-xs-6">
                  <div class="description-block border-right text-center">
                    <span class="description-percentage text-green"><i class="fa fa-caret-up"></i> 17%</span>
                    <h5 class="description-header">$35,210.43</h5>
                    <span class="description-text">TOTAL REVENUE</span>
                  </div>
                </div>
               <div class="col-sm-3 col-xs-6">
                  <div class="description-block border-right text-center">
                    <span class="description-percentage text-yellow"><i class="fa fa-caret-left"></i> 0%</span>
                    <h5 class="description-header">$10,390.90</h5>
                    <span class="description-text">TOTAL COST</span>
                  </div>
                </div>
                <div class="col-sm-3 col-xs-6">
                  <div class="description-block border-right text-center">
                    <span class="description-percentage text-green"><i class="fa fa-caret-up"></i> 20%</span>
                    <h5 class="description-header">$24,813.53</h5>
                    <span class="description-text">TOTAL PROFIT</span>
                  </div>
                </div>
               <div class="col-sm-3 col-xs-6 moreBtnBlock">
                  <div class="description-block text-center">
				    <div class="center-con">
						<div class="round">
						<div id="cta">
							<span class="arrow primera next "></span>
							<span class="arrow segunda next "></span>
						</div>
						</div>
					</div>
                    <a href="#" class="moreBtn"><p>View More <i class="fa fa-arrow-right" aria-hidden="true"></i></p></a>
                  </div>
                </div>
              </div>
             </div>
           </div>
         </div>
        </div>
		
		
		
		
		<!-- End-->
		
		
		
		</main>
  
	<jsp:include page="footer.jsp" />
	
	<%} %>
<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

	<script src="js/community.js"></script>
	<script src="js/common.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
		
	<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.colVis.min.js"></script>
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
		
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>
		
		<script
		src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
		
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.bootstrap.min.js"></script>	
		
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
		
		<script
		src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>	
		
		<script
		src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
		
		
		
	<script
		src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
		
	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
		
		

	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>
		
		
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
		
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		
	<script>
		$(document).ready(function() {
			$('#communityTable').DataTable();
		});
	</script>
	
<script>
$(document).ready(function(){
	  $('[data-toggle="tooltip"]').tooltip();   
	});
</script>	


<script type="text/javascript">
		$(document).ready(function()
		{
			var date = new Date();
            var currentMonth = date.getMonth();
            var currentDate = date.getDate();
            var currentYear = date.getFullYear();
			$('#start_date').bootstrapMaterialDatePicker
			({
				time: false,
				clearButton: true,
				 maxDate: new Date(currentYear, currentMonth, currentDate)
			});

			$('#end_date').bootstrapMaterialDatePicker
			({
				time: false,
				clearButton: true,
				 maxDate: new Date(currentYear, currentMonth, currentDate)
			});
			
			/* $('#time').bootstrapMaterialDatePicker
			({
				date: false,
				shortTime: false,
				format: 'HH:mm'
			});

			$('#date-format').bootstrapMaterialDatePicker
			({
				format: 'dddd DD MMMM YYYY - HH:mm'
			});
			$('#date-fr').bootstrapMaterialDatePicker
			({
				format: 'DD/MM/YYYY HH:mm',
				lang: 'en',
				weekStart: 1, 
				cancelText : 'ANNULER',
				nowButton : true,
				switchOnClick : true
			});

			$('#date-end').bootstrapMaterialDatePicker
			({
				weekStart: 0, format: 'DD/MM/YYYY HH:mm'
			});
			$('#date-start').bootstrapMaterialDatePicker
			({
				weekStart: 0, format: 'DD/MM/YYYY HH:mm', shortTime : true
			}).on('change', function(e, date)
			{
				$('#date-end').bootstrapMaterialDatePicker('setMinDate', date);
			});

			$('#min-date').bootstrapMaterialDatePicker({ format : 'DD/MM/YYYY HH:mm', minDate : new Date() }); */

			$.material.init()
		});
		</script>
		
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script>
	 
	$(document).ready(function(){
	  var table = $('#datetable').DataTable( {
			fixedHeader: true
		} );
		$("#sidebar").addClass('whiteColor');    
        $('.sideHide').on('click', function(e){
			$("#sidebar").toggle();
		  $("#sidebar").toggleClass('whiteColor yelolowCollor')
		});
		
		
	});
</script>
<script>
var xValues = ["Italy", "France", "Spain", "USA", "Argentina"];
var yValues = [55, 49, 44, 24, 15];
var barColors = ["red", "green","blue","orange","brown"];

new Chart("myChart", {
  type: "bar",
  data: {
    labels: xValues,
    datasets: [{
      backgroundColor: barColors,
      data: yValues
    }]
  },
  options: {
    legend: {display: false},
    title: {
      display: true,
      text: "World Wine Production 2018"
    }
  }
});
</script>
<script>
var xValues = ["Italy", "France", "Spain", "USA", "Argentina"];
var yValues = [55, 49, 44, 24, 15];
var barColors = [
  "#b91d47",
  "#00aba9",
  "#2b5797",
  "#e8c3b9",
  "#1e7145"
];

new Chart("myChart1", {
  type: "pie",
  data: {
    labels: xValues,
    datasets: [{
      backgroundColor: barColors,
      data: yValues
    }]
  },
  options: {
    title: {
      display: true,
      text: "World Wide Wine Production 2018"
    }
  }
});
</script>
		
</body>

</html>