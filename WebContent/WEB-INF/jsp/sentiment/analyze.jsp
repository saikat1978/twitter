<%-- 
    Document   : analyze
    Created on : 01-Dec-2013, 01:58:41
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
        <h1>Sentiment analysis</h1>
        <form action="/techathon/sentiment/analyze" method="post">
        <table>
            
                <tr>
                    <td>Enter text to classify</td>
                    <td><input type="text" size="50" name="text"></td>
                </tr>
                <tr>
                    <td><input type="submit"></td>
                </tr>
           
        </table>
        </form>
        <c:forEach items="${sentiments}" var="s">
            ${s.key} - ${s.value}<br>
        </c:forEach>
    </body>
</html>
