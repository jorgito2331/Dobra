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
        <link href="Estilos/login.css" rel="stylesheet">
    </head>
    <body>
        <div class="banner">
            <h1>Dobra</h1>
        </div>
        <div class="contenedor">
            <h1>Inicio de sesión</h1>
            <form  action="./login" method="POST">
                <div class="in">
                    <input type="text" name="indentificacion" min="10" maxlength="15" placeholder="Id"></div>
                <div class="in">
                    <input type="password" name="clave" min="8" maxlength="15" placeholder="Clave"></div>
                <div class="control">
                    <input type="submit" class="enviar" value="Iniciar sesión">

                </div>
            </form>
        </div>



    </body>
</html>
