<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>Glemt passord</h1>

    <form:form action="forgotPassword" method="post" modelAttribute="person"> 
        <table>
            <tr>
                <td>E-post:</td>
            </tr>
            <tr>
                <td colspan="2"><form:input path="email" /></td>
            </tr>
            <tr>
                <td><a href="login">Tilbake</a></td>
                <td><input type="submit" value="Send instrukser" id="submit"> </td>
            </tr>
        </table>
    </form:form>