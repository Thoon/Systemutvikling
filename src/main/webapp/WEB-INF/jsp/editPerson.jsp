<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<h2>Administrer personer</h2>
<p>

    <form:form action="editPerson.htm" method="post" modelAttribute="personFormBackingBean"> 
    <p>
        Checkboxene under "Velg" trengs kun å brukes ved sletting, ikke ved oppdatering av kunder.<br><br>
        Tilgangsnivåene tilsvarer følgende brukertyper: <br><br>
        0 = Admin <br>
        1 = Forhandlerkjede<br>
        2 = Forhandler<br>
        3 = Kunde
    </p>
    <table border="1" width="100%">
        <tr>
            <th>Epost</th>
            <th>Fornavn</th>
            <th>Etternavn</th>
            <th>Telefon</th>
            <th>Tilgangs nivå</th>
            <th>Velg </th>
        </tr>
                     
        <c:forEach var="person" items="${personFormBackingBean.everyone}" varStatus="status">
          
            <tr>
                <td><c:out value="${person.email}"/>
                    <form:hidden path="everyone[${status.index}].email" />
                </td> 
                                    
                <td> <form:input path="everyone[${status.index}].firstName" /> 
                     <form:errors path="everyone[${status.index}].firstName" />
                </td>
                <td> <form:input path="everyone[${status.index}].lastName" /> 
                     <form:errors path="everyone[${status.index}].lastName" />
                </td>
                <td> <form:input path="everyone[${status.index}].phoneNumber" /> 
                     <form:errors path="everyone[${status.index}].phoneNumber" />
                </td>
                <td> <form:input path="everyone[${status.index}].permission" /> 
                     <form:errors path="everyone[${status.index}].permission" />
                </td>
                <td> <form:checkbox path="selectedPersons" value="${person.email}" /> </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Oppdater Personer" name="updatePersons" />    
    <input type="submit" value="Slett valgte personer" name="deletePersons" />
</form:form>
<hr>