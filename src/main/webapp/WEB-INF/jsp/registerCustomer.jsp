

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>Registreringsskjema for ny kunde</h2>

<form:form action="registerCustomer.htm" method="post" modelAttribute="customer" >
    <table>
        <tr>  
            <td> Kundenavn </td>
            <td> <form:input path="customerName" />
                 <form:errors path="customerName" />
            </td> 
        </tr>
        <tr>
            <td> Adresse </td>
            <td> <form:input path="address" />
                 <form:errors path="address" />
            </td>
        </tr>
        <tr>
            <td> Leverandør-ID: </td>
            <td> <form:input path="supplierId" />
                 <form:errors path="supplierId" />
            </td>
        </tr>
        <tr><td colspan="2"><input type="submit" value="Registrer kunde"></td></tr>
    </table>
</form:form>


<h2>Registrer Person</h2>
<form:form action="registerCustomerPerson.htm" method="post" modelAttribute="customerPerson" >
    <table>
        <tr>  
            <td> Kundenavn </td>
            <td>
                    <form:select path="customer.customerName">         
                    <form:options items="${customerFormBackingBean.getEveryone}" itemValue="customerId" itemLabel="customerName"/>
                    </form:select>  
            </td> 
        </tr>
        <tr>  
            <td> Person </td>
            <td>
                    <form:select path="person">         
                    <form:options items="${personFormBackingBean.getEveryone}" itemValue="email" itemLabel="fullName"/>
                    </form:select>  
            </td> 
        </tr>
        <tr><td colspan="2"><input type="submit" value="Registrer kundeforhold"></td></tr>
    </table>

</form:form>