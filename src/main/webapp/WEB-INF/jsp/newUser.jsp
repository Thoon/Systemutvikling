<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>Ny bruker</h1>

<form:form action="newuser" method="post" modelAttribute="person"> 
    <table>
        <tr>
            <td>E-post:</td>
        </tr>
        <tr>
            <td colspan="2"><form:input path="email"/></td>
        </tr>
        <tr>
            <td><a href="login">Tilbake</a></td>
            <td><input type="submit" value="Aktiver bruker" id="submit"> </td>
        </tr>
    </table>
</form:form>

<div class="error">
    <h4>${melding}</h4>
    
</div>