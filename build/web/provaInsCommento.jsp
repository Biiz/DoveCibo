<%-- 
    Document   : provaInsCommento
    Created on : 25-ott-2016, 11.52.03
    Author     : postal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ServletAggiungiCommento" method="post" >
            global_value: <input type="text" name="global_value"><br>
            food <input type="text" name="food"><br>
            service <input type="text" name="service"><br>
            value_for_money <input type="text" name="value_for_money"><br>
            atmospere <input type="text" name="atmospere"><br>
            name <input type="text" name="name"><br>
            description <input type="text" name="description"><br>
            <input type="submit">

        </form>
    </body>
</html>
