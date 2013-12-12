<%-- 
    Document   : home
    Created on : 26-Nov-2013, 23:27:32
    Author     : saikatsakura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Twitter Data Analytics</h1>
        <form action="/techathon/analytics/search" method="post">
            <tr>
                <td><input type="text" name="text" width="100"></td>
                <td><input type="submit" value="Search"></td>
            </tr> 
        </form>
        <p>
            <a href="/techathon/sentiment/analyze?q=${key}">Analyze sentiments</a>
        </p>
        <c:forEach items="${tweets}" var="tweet">
            ${tweet}<br>
        </c:forEach>
    </body>
</html>
