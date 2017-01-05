<%-- 
    Document   : registerSupplier
    Created on : 04.jan.2017, 11:47:50
    Author     : ganon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>Registreringsskjema for ny forhandlerkjede</h2>

<form:form action="registerSupplierChain.htm" method="post" modelAttribute="supplierChain" >
    <table>
        <tr>  
            <td> Navn på forhandlerkjede </td>
            <td> <form:input path="name" />
                 <form:errors path="name" />
            </td> 
        </tr>
        <tr><td colspan="2"><input type="submit" value="Registrer forhandlerkjede"></td></tr>
    </table>
</form:form>
