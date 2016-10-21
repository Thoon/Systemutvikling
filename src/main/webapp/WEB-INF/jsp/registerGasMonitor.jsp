<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2>Registrer ny gassmonitor</h2>

<form:form action="registerGasMonitor.htm" method="post" modelAttribute="gasMonitor" >
    <table>
        <tr>  
            <td> Maksvekt i kg: </td>
            <td> <form:input path="maxWeight" />
                 <form:errors path="maxWeight" />
            </td> 
        </tr>
        <tr>
            <td> Nåværende vekt </td>
            <td> <form:input path="currentWeight" />
                 <form:errors path="currentWeight" />
            </td>
        </tr>
        <tr>
            <td>Batteri: </td>
            <td> <form:input path="battery" />
                 <form:errors path="battery" />
            </td>
        </tr>
        <tr>
            <td> Kunde ID: </td>
            <td> <form:input path="customerId" />
                 <form:errors path="customerId" />
            </td>
        </tr>
        <tr>
            <td> Leverandør ID: </td>
            <td> <form:input path="supplierId" />
                 <form:errors path="supplierId" />
            </td>
        </tr>
        <tr><td colspan="2"><input type="submit" value="Registrer Gasmonitor"</td></tr>
    </table>
</form:form>
