

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>Registreringsskjema for ny forhandlerkjede</h2>

<form:form action="RegisterSupplierChain.htm" method="post" modelAttribute="supplierChain" >
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

<h2>Registrer Person</h2>
<form:form action="registerSupplierChainPerson.htm" method="post" modelAttribute="supplierChainPerson" >
    <table>
        <td> Forhandlerkjede id </td>
            <td> <form:input path="scId" />
                 <form:errors path="scId" />
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