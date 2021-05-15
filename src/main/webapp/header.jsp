<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	 <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark header">
      <a class="navbar-brand" href="#"><img src="common/images/logo-white.png" alt="logo" class="logo"></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      
    </nav>
	<!--Header end-->
</body>

<script>


</script>

</html>