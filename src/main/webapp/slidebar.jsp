<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="common/css/style1.css">
  </head>
   <body>
   <%
		String user_id = (String) session.getAttribute("roleID");

	%>

	<%
		if (user_id == null) {
			response.sendRedirect("login.jsp");
		}else {
	%>
	<div class="container-fluid">
    <div class="row d-flex d-md-block flex-nowrap wrapper">
        <div class="col-md-2 float-left col-1 pl-0 pr-0 collapse width show" id="sidebar">
            <div class="list-group border-0 text-center text-md-left">
            
             <%
			if (user_id.equalsIgnoreCase("1") || user_id.equalsIgnoreCase("4")) {
				%>	
            
                <a href="dashboard.jsp" class="list-group-item d-inline-block collapsed"><i class="fa fa-film"></i> <span class="d-none d-md-inline">DashBoard</span></a>
                <a href="#menu3" class="list-group-item d-inline-block collapsed" data-toggle="collapse" aria-expanded="false"><i class="fa fa-book"></i> <span class="d-none d-md-inline">Customer Setup </span></a>
                <div class="collapse" id="menu3" data-parent="#sidebar">
                    <a href="communityDetails.jsp" class="list-group-item" data-parent="#menu3">Community Details</a>
                     <a href="blockDetails.jsp" class="list-group-item" data-parent="#menu3">Block Details</a>
                      <a href="customerDetails.jsp" class="list-group-item" data-parent="#menu3">Customer Details</a>
                       <a href="gateway.jsp" class="list-group-item" data-parent="#menu3">Gateway Details</a>
                </div>
                <a href="tariff.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-heart"></i> <span class="d-none d-md-inline">Tariff </span></a>
                <a href="alert.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-list"></i> <span class="d-none d-md-inline">Alert Configuration</span></a>
                
                <%if(user_id.equalsIgnoreCase("1")){ %>
                <a href="configuration.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-clock-o"></i> <span class="d-none d-md-inline">Meter Commands</span></a>
                <%}%>
                
                <a href="configurationStatus.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-th"></i> <span class="d-none d-md-inline">Meter Commands Status</span></a>
                
                <%if(user_id.equalsIgnoreCase("1")){ %>
                <a href="topup.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-gear"></i> <span class="d-none d-md-inline">ReCharge</span></a>
                <%}%>
                <a href="topupStatus.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-calendar"></i> <span class="d-none d-md-inline">ReCharge Status</span></a>
                <a href="holiday.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-envelope"></i> <span class="d-none d-md-inline">Vacation</span></a>
                <a href="reports.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-bar-chart-o"></i> <span class="d-none d-md-inline">Reports</span></a>
                
                	<%if(user_id.equalsIgnoreCase("1")){ %>
                <a href="Mgmt.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-star"></i> <span class="d-none d-md-inline">User Management</span></a>
            <%}%>
            <% } else if (user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("5")) {
                %>
             <a href="approval.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-calendar"></i> <span class="d-none d-md-inline">Approval</span></a>
              <a href="customerDetails.jsp" class="list-group-item" data-parent="#menu3">Customer Details</a>   
                 <a href="tariff.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-heart"></i> <span class="d-none d-md-inline">Tariff </span></a>
                <a href="alert.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-list"></i> <span class="d-none d-md-inline">Alert Configuration</span></a>
                
                
                   <%if(user_id.equalsIgnoreCase("2")){ %>
                <a href="configuration.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-clock-o"></i> <span class="d-none d-md-inline">Meter Commands</span></a>
                <%}%>
                
                 <a href="configurationStatus.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-th"></i> <span class="d-none d-md-inline">Meter Commands Status</span></a>
                 <a href="dashboard.jsp" class="list-group-item d-inline-block collapsed"><i class="fa fa-film"></i> <span class="d-none d-md-inline">DashBoard</span></a>
                
                <%if(user_id.equalsIgnoreCase("2")){ %>
                <a href="topup.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-gear"></i> <span class="d-none d-md-inline">ReCharge</span></a>
                <%}%>
                
                 <a href="topupStatus.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-calendar"></i> <span class="d-none d-md-inline">ReCharge Status</span></a>
                <a href="holiday.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-envelope"></i> <span class="d-none d-md-inline">Vacation</span></a>
                <a href="reports.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-bar-chart-o"></i> <span class="d-none d-md-inline">Reports</span></a>
                
                		<%if(user_id.equalsIgnoreCase("2")){ %>
                 <a href="feedbackStatus.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-envelope"></i> <span class="d-none d-md-inline">Feedback Status</span></a>
               <%}%>
                <% } else if (user_id.equalsIgnoreCase("3")) {%>
                
                <a href="topup.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-gear"></i> <span class="d-none d-md-inline">ReCharge</span></a>
                 <a href="topupStatus.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-calendar"></i> <span class="d-none d-md-inline">ReCharge Status</span></a>
                <a href="holiday.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-envelope"></i> <span class="d-none d-md-inline">Vacation</span></a>
                 <a href="userConsumptions.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-envelope"></i> <span class="d-none d-md-inline">User Consumptions</span></a>
                
                <a href="feedback.jsp" class="list-group-item d-inline-block collapsed" data-parent="#sidebar"><i class="fa fa-bar-chart-o"></i> <span class="d-none d-md-inline">Feedback/Compliant</span></a>
              <% } %>
            
            </div>
        </div>
    </div>
</div>
	<%
		}
	%>
	
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
	 
	$(document).ready(function(){
	  var table = $('#datetable').DataTable( {
			fixedHeader: true
		} );
		$(".sideHide").toggle(
		function(){$(".footer").css({"margin-left": "0px"});},
		function(){$(".footer").css({"margin-left": "16.66%"});
	});
	});
</script>

  </body>
</html>