<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="common/css/bootstrap.min.css">
<link rel="icon" type="image/png" sizes="16x16" href="common/images/1-hanbit.png">
<!-- Material Design for Bootstrap CSS -->
<!-- <link rel="stylesheet" href="common/css/style.css"> -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css">
	
	<link href="common/css/materialize.fontawsome.css"
	rel="stylesheet">
</head>
<body>

<body class="main-sidebar-show">
		<%
		String user_id = (String) session.getAttribute("roleID");

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
		
		
		<div id="accordion">
    <div class="card">
      <div class="card-header">
      <span class="mr-auto">Report 1</span>
        <a class="card-link float-right" data-toggle="collapse" href="#collapseOne">
          <i class="fa fa-angle-down reportarrow" aria-hidden="true"></i>
        </a>
      </div>
      <div id="collapseOne" class="collapse show" data-parent="#accordion">
        <div class="card-body">
          <div class="card-body">
						<form>
							<div class="row">
								<div class="col-md-4">
                            <div class="group form-group">
                              <label class="bmd-label-floating select-label">Community<sup class="imp">*</sup></label>
                              <select class="form-control form-control-sm select2" id="selectcommunityName" name="selectcommunityName" onchange="showBlockbyCommunity(this.value);">
                              </select>
                            </div>
                          </div>
                          <div class="col-md-4">
                            <div class="group form-group">
                              <label class="bmd-label-floating select-label">Select Block<sup class="imp">*</sup></label>
                              <select class="form-control form-control-sm select2" id="selectBlockBasedonCommunity" name="selectBlockBasedonCommunity" onchange="showCustomerbyBlock(this.value);">
                              
                              </select>
                            </div>
                          </div>
                          <div class="col-md-4">
                            <div class="group form-group">
                              <label class="bmd-label-floating select-label">Select CRN<sup class="imp">*</sup></label>
                              <select class="form-control form-control-sm" id="selectHouseBasedonBlock" name="selectHouseBasedonBlock" onchange="showMetersDetails(this.value);">
                              </select>
                            </div>
                          </div>
                          
                           <div class="col-md-4">
                            <div id="formcurrentBalance_topup" class="input-group form-group">
                            <label class="bmd-label-floating">Start Date Time<sup class="imp">*</sup></label> 
                             <!--  <input type="text" class="form-control datepicker" id="start_date" name="start_date"> -->
                             <input type="text" id="start_date" name="start_date" class="form-control" >
                            </div>
                          </div>
                          <div class="col-md-4">
                            <div id="formdateTime_topup" class="input-group form-group">
                              <label class="bmd-label-floating">End Date Time<sup class="imp">*</sup></label>
                              <input type="text" class="form-control" id="end_date" name="end_date">
                            </div>
                          </div>
                          </div>
                          
                          <div class="row">
                        <div class="col-md-11">
                            <button type="button" id="userconsumption" class="btn btn-primary submit-button btn-raised float-right mr-4">Submit<div class="ripple-container"></div></button>
                        </div>
                        
                         <div class="col-md-1">
								<button type="button" class="btn btn-secondary btn-raised mr-3 resetFilter" id="resetFilter">Reset</button>
							</div>
							
                    </div>
                          
                          
							
						</form>
						</div>
						 </div>
      </div>
    </div>
    
 
  </div>
		
		
		
		
		<div class="row mt-4">
				<div class="col-md-12">
            <table id="datetable" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Age</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Tiger Nixon</td>
                <td>System Architect</td>
                <td>Edinburgh</td>
                <td>61</td>
                <td>2011/04/25</td>
                <td>$320,800</td>
            </tr>
            <tr>
                <td>Garrett Winters</td>
                <td>Accountant</td>
                <td>Tokyo</td>
                <td>63</td>
                <td>2011/07/25</td>
                <td>$170,750</td>
            </tr>
            <tr>
                <td>Ashton Cox</td>
                <td>Junior Technical Author</td>
                <td>San Francisco</td>
                <td>66</td>
                <td>2009/01/12</td>
                <td>$86,000</td>
            </tr>
            <tr>
                <td>Cedric Kelly</td>
                <td>Senior Javascript Developer</td>
                <td>Edinburgh</td>
                <td>22</td>
                <td>2012/03/29</td>
                <td>$433,060</td>
            </tr>
            <tr>
                <td>Airi Satou</td>
                <td>Accountant</td>
                <td>Tokyo</td>
                <td>33</td>
                <td>2008/11/28</td>
                <td>$162,700</td>
            </tr>
            <tr>
                <td>Brielle Williamson</td>
                <td>Integration Specialist</td>
                <td>New York</td>
                <td>61</td>
                <td>2012/12/02</td>
                <td>$372,000</td>
            </tr>
            <tr>
                <td>Herrod Chandler</td>
                <td>Sales Assistant</td>
                <td>San Francisco</td>
                <td>59</td>
                <td>2012/08/06</td>
                <td>$137,500</td>
            </tr>
            <tr>
                <td>Rhona Davidson</td>
                <td>Integration Specialist</td>
                <td>Tokyo</td>
                <td>55</td>
                <td>2010/10/14</td>
                <td>$327,900</td>
            </tr>
            <tr>
                <td>Colleen Hurst</td>
                <td>Javascript Developer</td>
                <td>San Francisco</td>
                <td>39</td>
                <td>2009/09/15</td>
                <td>$205,500</td>
            </tr>
            <tr>
                <td>Sonya Frost</td>
                <td>Software Engineer</td>
                <td>Edinburgh</td>
                <td>23</td>
                <td>2008/12/13</td>
                <td>$103,600</td>
            </tr>
            <tr>
                <td>Jena Gaines</td>
                <td>Office Manager</td>
                <td>London</td>
                <td>30</td>
                <td>2008/12/19</td>
                <td>$90,560</td>
            </tr>
            <tr>
                <td>Quinn Flynn</td>
                <td>Support Lead</td>
                <td>Edinburgh</td>
                <td>22</td>
                <td>2013/03/03</td>
                <td>$342,000</td>
            </tr>
        </tbody>
     </table>
	 </div>
	 </div>
		
		
	</div>
	</div>
	</div>
	
	</div>
	</div>
	<jsp:include page="footer.jsp" />
	
	<%} %>
</body>
</html>