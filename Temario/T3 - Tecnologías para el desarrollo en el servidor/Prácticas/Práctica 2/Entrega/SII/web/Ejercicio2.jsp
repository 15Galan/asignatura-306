<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        <h1>Hello World!</h1>
        
        <form name="divisores">
            Número: <input type='text' name='num'>
            <br><br>
            
            <input type='submit' value='Calcular'>
            <br><br><br>
            
            <% int numero = Integer.parseInt("num"); %>
            
            <p><%= numero %></p>
        </form>
    </body>
</html>
