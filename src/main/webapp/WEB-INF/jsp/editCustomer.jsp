<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Mine kunder</h1>
<p>

    <form:form action="editCustomer.htm" method="post" modelAttribute="customerFormBackingBean"> 
    <h5>Checkboxene trengs kun å brukes ved sletting, ikke ved oppdatering av kunder</h5>
    <table border="1" width="100%">
        <tr>
            <th>Kunde-ID</th>
            <th>Kundenavn</th>
            <th>Adresse</th>
            <th>Velg</th>
        </tr>
                     
        <c:forEach var="customer" items="${customerFormBackingBean.everyone}" varStatus="status">
          
            <tr>
                <td><c:out value="${customer.customerId}"/>
                    <form:hidden path="everyone[${status.index}].customerId" />
                </td> 
                                    
                <td> 
                    <form:input path="everyone[${status.index}].customerName" /> 
                    <form:errors path="everyone[${status.index}].customerName" />
                </td>
                <td> 
                    <form:input path="everyone[${status.index}].address" /> 
                    <form:errors path="everyone[${status.index}].address" />
                </td>
                <td>
                    <form:input path="everyone[${status.index}].supplierId" /> 
                    <form:errors path="everyone[${status.index}].supplierId" />
                </td>
                <td> <form:checkbox path="selectedCustomers" value="${customer.customerId}" /> </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Oppdater kunder:" name="updateCustomers" />    
    <input type="submit" value="Slett valgte kunder" name="deleteCustomers" />
</form:form>
<hr>