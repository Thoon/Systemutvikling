<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h1>Logg inn</h1>
<form:form action="login.htm" method="post" modelAttribute="person" >
    <table>
        <tr>
            <td>E-post:</td>
        </tr>
        <tr>
            <td colspan="2"><form:input path="email"/></td>
        </tr>
        <tr>
            <td>Passord:</td>
        </tr>
        <tr>
            <td colspan="2"><form:password path="password"/></td>
        </tr>
        <tr>
            <td><a href="newuser">Ny bruker/Glemt passord</a></td>
            <td><input type="submit" value="Logg inn" id="submit"> </td>
        </tr>
    </table>
</form:form>

<div class="error">
    <h4>${melding}</h4>
    
</div>