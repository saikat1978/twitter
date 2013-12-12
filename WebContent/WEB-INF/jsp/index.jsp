<%-- 
    Document   : home
    Created on : 26-Nov-2013, 23:27:32
    Author     : saikatsakura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <td><input type="text" name="text"></td>
                <td><input type="submit" value="Search"></td>
            </tr> 
        </form>
    </body>
</html>
