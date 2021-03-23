<%-- 
    Document   : Primer
    Created on : 18-mar-2014, 16:50:12
    Author     : francis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>






<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Hola mundo</title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    </head>
    <body>
        
        <h1>Hola mundo!</h1>
        <p>Aleatorio: <%= Math.random() %>        
        <p><img src="./logoUMA.jpg" alt="logo UMA">

    </body>
</html>


