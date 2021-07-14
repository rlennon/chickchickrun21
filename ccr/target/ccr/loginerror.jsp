<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="javax.servlet.jsp.JspException" %>

<c:set var="language" value="${pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" var="msg"/>

<html>

    <head>
        <title><fmt:message key="welcome" bundle="${msg}" /></title>
    </head>

    <body bgcolor="FF6600">

        <table align="center">

            <tr align="center">
                <td>       
                    <b>
                        <fmt:message key="logonError" bundle="${msg}" />
                        <br><br>
                        <fmt:message key="logonErrorMessage" bundle="${msg}" />
                    </b>
                </td>
            </tr>

            <tr align="center">
                <td>
                    <a href="/ccr/index.jsp">    
                        <br><br>
                        <b>
                            <<< <fmt:message key="return" bundle="${msg}" />
                        </b>
                    </a>
                </td>
            </tr>


        </table>

    </body>

</html>