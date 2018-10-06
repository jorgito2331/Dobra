<%-- 
    Document   : login
    Created on : 05-ago-2018, 19:45:44
    Author     : Jorge-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dobra</title>
    </head>
    <body>
        <form action="./login" method="POST">
            <input type="text" name="indentificacion" min="10" maxlength="15">
            <input type="password" name="clave" min="8" maxlength="15">
            <button type="submit">Guardar</button>
        </form>
    </body>
</html>
