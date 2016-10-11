<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>



<h2>Administrer personer</h2>
<p>

    <form:form action="endrePersoner" method="post" modelAttribute="personFormBackingBean"> 
    <h5>Checkboxene trengs kun å brukes ved sletting og henting, ikke ved oppdatering av personer</h5>
    <table border="1" width="100%">
        <tr>
            <th>Personnummer</th>
            <th>Fornavn</th>
            <th>Etternavn</th>
            <th>Velg </th>
        </tr>
                     
        <c:forEach var="person" items="${personFormBackingBean.allePersoner}" varStatus="status">
          
            <tr>
                <td><c:out value="${person.personnr}"/>
                    <form:hidden path="allePersoner[${status.index}].personnr" />
                </td> 
                                    
                <td> <form:input path="allePersoner[${status.index}].fornavn" /> 
                     <form:errors path="allePersoner[${status.index}].fornavn" />
                </td>
                <td> <form:input path="allePersoner[${status.index}].etternavn" /> 
                     <form:errors path="allePersoner[${status.index}].etternavn" />
                </td>
                <td> <form:checkbox path="valgtePersoner" value="${person}" /> </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Hent Personer" name="hentPersoner" />  <input type="submit" value="Oppdater Personer" name="oppdaterPersoner" />    <input type="submit" value="Slett valgte personer" name="slettPersoner" />
</form:form>
<hr>