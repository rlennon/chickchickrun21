<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="javax.servlet.jsp.JspException" %>

<c:set var="language" value="${pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" var="msg"/>

<html>
    <head>
        <title>
            <fmt:message key="welcome" bundle="${msg}" />
        </title>
        <meta http-equiv="refresh" content="590">
        <meta name="description" content="<fmt:message key="landingPageMessage" bundle="${msg}" />">
        <meta name="keywords" content="pet, petshop, pet shop, kennel, cat, fish, rodent, cattle, haras, horse, reptile, bird, cricket, ferrent, bird">
    </head>

    <body bgcolor="#FFFFFF">

        <form method="POST" action="j_security_check">

            <table border="0" cellspacing="0" cellpadding="0" align="center">

                <tr align="center">
                    <td>
                        <!-- img src="lyit.jpg" align="center" alt="lyit.ie" width="200" height="171" -->
                    </td>
                </tr>

                <tr align="center">
                    <td>
                        <br>
                        <h3>
                            <b>
                                <fmt:message key="landingPageMessage" bundle="${msg}" />
                            </b>
                        </h3>
                    </td>
                </tr>

                <tr align="center">
                    <td>
                        <!-- img src="ccr.png" align="center" alt="Chick Chick Run" width="817" height="358" -->
                    </td>
                </tr>

                <tr align="center">
                    <td>
                        <h3>
                            <font color="#FF6600"> 
                            <br>
                            <fmt:message key="loginPageActMsg" bundle="${msg}" /> <fmt:message key="ccr" bundle="${msg}" />!
                            </font>
                        </h3>
                    </td>
                </tr>

                <tr align="center">
                    <td>
                        <b><fmt:message key="email" bundle="${msg}" /></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="text" name="j_username" value="juarez"/>
                    </td>                    
                </tr>

                <tr align="center">
                    <td>
                        <b><fmt:message key="password" bundle="${msg}" /></b>&nbsp;
                        <input type="password" name="j_password" value="ccr"/>
                    </td>                
                </tr>

                <tr align="center">
                    <td>
                        <a href="/ccr/forgotPassword.jsp">
                            <fmt:message key="forgotPassword" bundle="${msg}" />                            
                        </a>
                        <input type="checkbox" name="forgotPwdCheckBox"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="submit" value="<fmt:message key="login" bundle="${msg}" />" />
                    </td>
                </tr>

            </table>

        </form>



        <form method="POST" action="http://localhost:8080/ccr/Registration">

            <table border="0" cellspacing="0" cellpadding="0" align="center">

                <tr align="center">
                    <td>
                        <h3>
                            <font color="#FF6600">
                            <fmt:message key="newToCCR" bundle="${msg}" />

                            <fmt:message key="alwaysFree" bundle="${msg}" />
                            </font>
                        </h3>
                    </td>
                </tr>

                <tr align="center">
                    <td>
                        <b>
                            <fmt:message key="userName" bundle="${msg}" />
                        </b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="text" name="username" value="juarez"/>
                    </td>
                </tr>

                <tr align="center">
                    <td>
                        <b>
                            <fmt:message key="name" bundle="${msg}" /> 
                        </b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;               
                        <input type="text" name="name" value="Juarez"/>
                    </td>
                </tr>

                <tr align="center">
                    <td>
                        <b><fmt:message key="surName" bundle="${msg}" />
                        </b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                 
                        <input type="text" name="surname" value="Alvares"/>
                    </td>
                </tr>

                <tr align="center">
                    <td>
                        <b>
                            <fmt:message key="email" bundle="${msg}" /> 
                        </b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                      
                        <input type="text" name="email" value="L00162879@student.lyit.ie"/>
                    </td>
                </tr>

                <tr align="center">
                    <td>
                        <b>
                            <fmt:message key="confirm" bundle="${msg}" /> 
                            <fmt:message key="email" bundle="${msg}" />
                        </b>&nbsp;&nbsp;                    
                        <input type="text" name="emailConfirmation" value="L00162879@student.lyit.ie"/>
                    </td>
                </tr>

                <tr align="center">
                    <td>
                        <b>
                            <fmt:message key="password" bundle="${msg}" /> 
                        </b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                      
                        <input type="text" name="password" value="test"/>
                    </td>
                </tr>

                <tr align="center">
                    <td>
                        <b>
                            <fmt:message key="confirmPassword" bundle="${msg}" />                            
                        </b>
                        <input type="text" name="passwordConfirmation" value="test"/>
                    </td>
                </tr>

   

                <tr align="center">                    
                    <td>
                        <a href="/ccr/userAgreement.jsp">
                            <fmt:message key="userAgreement" bundle="${msg}" />
                        </a>    
                        <input type="checkbox" name="userAgreeementCheckBox"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
                
                <tr align="center">
                    <td>
                        <input type="submit" value="<fmt:message key="register" bundle="${msg}" />" />
                    </td>
                </tr>

            </table>
        </form>

    </body>
</html>