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



<title>DashBoard</title>
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
            <!-- <div class="box-header with-border">
              <h3 class="box-title">Monthly Recap Report</h3>
			</div> -->
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
        	 Highcharts.chart('container', {
        		    chart: {
        		        type: 'column'
        		    },
					title : {
						text : ''
					},
        		    xAxis: {
        		        categories: ['com1', 'com2', 'com3', 'com4']
        		    },

        		    plotOptions: {
        		        series: {
        		            pointWidth: 20
        		        }
        		    },

        		    series: [{
        		        data: [29.9, 71.5, 106.4, 129.2]
        		    }]
        		});
            
        	 Highcharts.chart('container1', {
        		    chart: {
        		        type: 'column'
        		    },
					title : {
						text : ''
					},
        		    xAxis: {
        		    	 categories: ['com1', 'com2', 'com3', 'com4']
        		    },

        		    plotOptions: {
        		        series: {
        		            pointWidth: 20
        		        }
        		    },

        		    series: [{
        		    	 data: [29.9, 71.5, 106.4, 129.2]
        		    }]
        		});
        	 
  
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