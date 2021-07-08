<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="javax.servlet.jsp.JspException" %> 

<c:set var="language" value="${pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" var="msg"/>

<html>
    <head>
        <title><fmt:message key="forgotPassword" bundle="${msg}" /></title>
    </head>

    <body bgcolor="#FFFFFF">
        
        <fmt:message key="forgotPassword" bundle="${msg}" />
        
    </body>
    
</html>
