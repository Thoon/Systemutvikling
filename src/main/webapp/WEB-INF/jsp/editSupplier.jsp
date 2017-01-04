<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<h1>Forhandleradministrasjon</h1>
<p>

    <form:form action="editSupplier.htm" method="post" modelAttribute="supplierFormBackingBean"> 
    <h5>Checkboxene trengs kun å brukes ved sletting, ikke ved oppdatering av forhandlere</h5>
    <table border="1" width="100%">
        <tr>
            <th>Forhandler-ID</th>
            <th>Navn på forhandler</th>
            <th>Adresse</th>
            <th>Velg</th>
        </tr>
                     
        <c:forEach var="supplier" items="${supplierFormBackingBean.everyone}" varStatus="status">
          
            <tr>
                <td><c:out value="${supplier.supplierId}"/>
                    <form:hidden path="everyone[${status.index}].supplierId" />
                </td> 
                                    
                <td> <form:input path="everyone[${status.index}].supplierName" /> 
                     <form:errors path="everyone[${status.index}].supplierName" />
                </td>
                <td> <form:input path="everyone[${status.index}].address" /> 
                     <form:errors path="everyone[${status.index}].address" />
                </td>
                <td> <form:checkbox path="selectedSuppliers" value="${supplier.supplierId}" /> </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Oppdater forhandlere" name="updateSuppliers" />    
    <input type="submit" value="Slett valgte forhandlere" name="deleteSuppliers" />
</form:form>
<hr>