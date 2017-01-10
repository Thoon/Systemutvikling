<%-- 
    Document   : registerSupplier
    Created on : 04.jan.2017, 11:47:50
    Author     : ganon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>Registreringsskjema for ny forhandler</h2>

<form:form action="RegisterSupplier.htm" method="post" modelAttribute="supplier" >
    <table>
        <tr>  
            <td> Navn på forhandler </td>
            <td> <form:input path="supplierName" />
                 <form:errors path="supplierName" />
            </td> 
        </tr>
        <tr>
            <td> Adresse </td>
            <td> <form:input path="address" />
                 <form:errors path="address" />
            </td>
        </tr>
        <tr>
            <td> Leverandørkjede-ID: </td>
            <td> <form:input path="supplierChainId" />
                 <form:errors path="supplierChainId" />
            </td>
        </tr>
        <tr><td colspan="2"><input type="submit" value="Registrer forhandler"></td></tr>
    </table>
</form:form>


<h2>Registrer Person</h2>
<form:form action="registerSupplierPerson.htm" method="post" modelAttribute="supplierPerson" >
    <table>
        <td> Forhandler id </td>
            <td> <form:input path="supplierId" />
                 <form:errors path="supplierId" />
            </td> 
        </tr>
        <tr>
            <td> Epost </td>
            <td> <form:input path="email" />
                 <form:errors path="email" />
            </td>
        </tr>
        
        <tr><td colspan="2"><input type="submit" value="Registrer forhandler"></td></tr>
    </table>

</form:form>