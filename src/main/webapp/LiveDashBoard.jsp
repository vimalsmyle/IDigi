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
	
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">
	
	<link href="common/css/materialize.fontawsome.css"
	rel="stylesheet">



<title>Customer Details</title>
</head>


<body class ="main-sidebar-show">


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
	   <div class="top-spacing"></div>
	 <div class="main-content side-content pt-0 custom-scrollbar-js">
			<div class="container-fluid">
				<div class="inner-body custom-scrollbar-js" id="content-5">
				  <div class="row custom-scrollbar-css">
						<div class="col-md-12">
            <div class="page-header">
                <h3>Gas</h3>
            </div>
			<div class="row">
			<div class="col-md-12">
			<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Monthly Recap Report</h3>
			</div>
             <div class="box-body">
              <div class="row">
                <div class="col-md-12">
                   <div id = "container" style = "width:100%; height: 400px; margin: 0 auto"></div>
                </div>
              </div>
            </div>
           <div class="box-footer">
          
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                
                <section class="logo-carousel slider" data-arrows="true">
      <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                <h5 class="description-header" id="gasActive"></h5>
                    			  <span class="description-text">Active</span>
								</div>
									
	  </div>
      
		      <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                  <h5 class="description-header" id="gasInactive"></h5>
                    			  <span class="description-text">In-Active</span>
								</div>
									
	  </div>
	        <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                <h5 class="description-header" id="gasLive"></h5>
                    			  <span class="description-text">Live</span>
								</div>
									
	  </div>
	  
	        <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                 <h5 class="description-header" id="gasnonLive"></h5>
                    			  <span class="description-text">Non-Live</span>
								</div>
									
	  </div>
	  
	        <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                  <h5 class="description-header" id="gasemergency"></h5>
                    			  <span class="description-text">Emergency</span>
								</div>
									
	  </div>
	  
	        <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                  <h5 class="description-header" id="gasLowbattery"></h5>
                    			  <span class="description-text">Low Battery</span>
								</div>
									
	  </div>
	  
	        <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                <h5 class="description-header" id="gasActivePercentage"></h5>
                    			  <span class="description-text">Active Percentage</span>
								</div>
									
	  </div>
	  
	  
	        <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                 <h5 class="description-header" id="gasinactivePercentage"></h5>
                    			  <span class="description-text">in-Active Percentage</span>
								</div>
									
	  </div>
	  
	  
	      

	  </section>
                
                
            </div>
        </div>
    </div>

             </div>
           </div>
         </div>
        </div>
		
		
		
		
		
		 <div class="page-header">
                <h3>Water</h3>
            </div>
			<div class="row">
			<div class="col-md-12">
			<div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Monthly Recap Report</h3>
			</div>
             <div class="box-body">
              <div class="row">
                <div class="col-md-12">
                   <div id = "container1" style = "width:100%; height: 400px; margin: 0 auto"></div>
                </div>
              </div>
            </div>
           <div class="box-footer">
              <div class="box-footer">
          
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                
                <section class="logo-carousel slider" data-arrows="true">
      <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                <h5 class="description-header" id="waterActive"></h5>
                    			  <span class="description-text">Active</span>
								</div>
									
	  </div>
      
		      <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                  <h5 class="description-header" id="waterInactive"></h5>
                    			  <span class="description-text">In-Active</span>
								</div>
									
	  </div>
	        <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                <h5 class="description-header" id="waterLive"></h5>
                    			  <span class="description-text">Live</span>
								</div>
									
	  </div>
	  
	        <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                 <h5 class="description-header" id="waternonLive"></h5>
                    			  <span class="description-text">Non-Live</span>
								</div>
									
	  </div>
	  
	        <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                  <h5 class="description-header" id="wateremergency"></h5>
                    			  <span class="description-text">Emergency</span>
								</div>
									
	  </div>
	  
	        <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                  <h5 class="description-header" id="waterLowbattery"></h5>
                    			  <span class="description-text">Low Battery</span>
								</div>
									
	  </div>
	  
	        <div class="slide sliding-block">
	  
	    <div class="text-xs font-weight-bold text-uppercase">
                                <h5 class="description-header" id="waterActivePercentage"></h5>
                    			  <span class="description-text">Active Percentage</span>
								</div>
									
	  </div>
	  
	  
	  </section>
                
                
            </div>
        </div>
    </div>
             </div>
             </div>
           </div>
         </div>
        </div>
		
		
		<!-- End-->
		
		
		
		</div>
             </div>
           </div>
         </div>
        </div>
  
	<jsp:include page="footer.jsp" />
	
	<%} %>
<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

	<script src="js/home.js"></script>
	<script src="js/common.js"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	
		<script
		src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
		
		
		<script
		src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.bootstrap.min.js"></script>	
		
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
		
		
		
	

	<script
		src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>
		
		
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
		
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		
			<script src = "https://code.highcharts.com/highcharts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>


              <script language = "JavaScript">
         $(document).ready(function() {  
            var chart = {
               type: 'column'
            };
            var title = {
               text: 'Monthly Average Rainfall'   
            };
            var subtitle = {
               text: 'Source: WorldClimate.com'  
            };
            var xAxis = {
               categories: ['Jan','Feb','Mar','Apr','May','Jun','Jul',
                  'Aug','Sep','Oct','Nov','Dec'],
               crosshair: true
            };
            var yAxis = {
               min: 0,
               title: {
                  text: 'Rainfall (mm)'         
               }      
            };
            var tooltip = {
               headerFormat: '<span style = "font-size:10px">{point.key}</span><table>',
               pointFormat: '<tr><td style = "color:{series.color};padding:0">{series.name}: </td>' +
                  '<td style = "padding:0"><b>{point.y:.1f} mm</b></td></tr>',
               footerFormat: '</table>',
               shared: true,
               useHTML: true
            };
            var plotOptions = {
               column: {
                  pointPadding: 0.2,
                  borderWidth: 0
               }
            };  
            var credits = {
               enabled: false
            };
            var series= [
               {
                  name: 'Tokyo',
                  data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6,
                     148.5, 216.4, 194.1, 95.6, 54.4]
               }, 
               {
                  name: 'New York',
                  data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3,
                     91.2, 83.5, 106.6, 92.3]
               }, 
               {
                  name: 'London',
                  data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6,
                     52.4, 65.2, 59.3, 51.2]
               }, 
               {
                  name: 'Berlin',
                  data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4,
                     47.6, 39.1, 46.8, 51.1]
               }
            ];     
         
            var json = {};   
            json.chart = chart; 
            json.title = title;   
            json.subtitle = subtitle; 
            json.tooltip = tooltip;
            json.xAxis = xAxis;
            json.yAxis = yAxis;  
            json.series = series;
            json.plotOptions = plotOptions;  
            json.credits = credits;
            $('#container').highcharts(json);
            $('#container1').highcharts(json);
  
         });
       

         $(document).ready(function() {
           $('.logo-carousel').slick({
        	   infinite: false,
             slidesToShow: 6,
             slidesToScroll: 1,
             autoplay: false,
             autoplaySpeed: 1000,
             arrows: true,
             dots: false,
             pauseOnHover: false,
             responsive: [{
               breakpoint: 768,
               settings: {
                 slidesToShow: 4
               }
             }, {
               breakpoint: 520,
               settings: {
                 slidesToShow: 2
               }
             }]
           });
         });
         
      </script>
	





</body>

</html>