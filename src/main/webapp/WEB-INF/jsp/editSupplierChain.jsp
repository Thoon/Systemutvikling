<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<h1>Forhandlerkjedeadministrasjon</h1>
<p>

    <form:form action="editSupplierChain.htm" method="post" modelAttribute="supplierChainFormBackingBean"> 
    <h5>Checkboxene trengs kun å brukes ved sletting, ikke ved oppdatering av forhandlerkjeder</h5>
    <table border="1" width="100%">
        <tr>
            <th>Forhandlerkjede-ID</th>
            <th>Navn på forhandlerkjede</th>
            <th>Velg</th>
        </tr>
                     
        <c:forEach var="supplierChain" items="${supplierChainFormBackingBean.everyone}" varStatus="status">
          
            <tr>
                <td><c:out value="${supplierChain.scId}"/>
                    <form:hidden path="everyone[${status.index}].scId" />
                </td> 
                                    
                <td> <form:input path="everyone[${status.index}].name" /> 
                     <form:errors path="everyone[${status.index}].name" />
                </td>
                <td> <form:checkbox path="selectedSupplierChains" value="${supplierChain.scId}" /> </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Oppdater forhandlerkjeder" name="updateSupplierChains" />    
    <input type="submit" value="Slett valgte forhandlerkjeder" name="deleteSupplierChains" />
</form:form>
<hr>