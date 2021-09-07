<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.pet.votesystem.model.Restaurant, java.util.List"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Test Page</title>
</head>
<body>
<h1>List of Restaurants ${today}</h1>

<%
    List<Restaurant> list = (List<Restaurant>)request.getAttribute("restaurants");
    for(Restaurant u : list) {
        out.println(u.getRestaurantName());
        out.println("<br/>");
    }
%>

</body>
</html>