<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://fonts.googleapis.com/css?family=Montserrat:100,200,300,500,600" rel="stylesheet">

<title>Insert title here</title>
</head>
<body>

<%
		String user_id = (String) session.getAttribute("roleID");

	%>

	<%
		if (null == user_id) {
			response.sendRedirect("login.jsp");
		}
	%>
<!--Header Start-->
 <!-- bg-info -->
	 <nav class="navbar navbar-expand-md navbar-dark fixed-top header">
      <a class="navbar-brand mr-auto" href="#"><img src="common/images/logo-white.png" alt="logo" class="logo"></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
        <ul class="navbar-nav">
		<li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-user-circle-o" aria-hidden="true"></i> User
        </a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#"><i class="fa fa-user" aria-hidden="true"></i> Action</a>
          <a class="dropdown-item" href="#"><i class="fa fa-user" aria-hidden="true"></i> Profile</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</a>
        </div>
      </li>
	  </ul>
      
    </nav>
	<!--Header end-->
</body>

<script>


</script>

</html>