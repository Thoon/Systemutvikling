<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>Skriv nytt passord</h1>
<div id="formDiv">
    <form:form action="newpassword" method="post" modelAttribute="person"> 
        <table>
            <tr>
                <td>Nytt passord:</td>
            </tr>
            <tr>
                <td colspan="2"><form:password path="password"/></td>
            </tr>
            <tr>
                <td>Gjenta passord:</td>
            </tr>
            <tr>
                <td colspan="2"><form:password path="firstName"/></td>
            </tr>
             <tr>
                <td colspan="2"><form:hidden path="lastName" value="${token}"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Fullfør" id="submit"> </td>
            </tr>
        </table>
    </form:form>
</div> 
<div class="error">
    <h4>${melding}</h4>
    
</div>
