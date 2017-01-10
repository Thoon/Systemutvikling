<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>Registrer ny gassmonitor</h2>

<form:form action="RegisterGasMonitor.htm" method="post" modelAttribute="gasMonitor" >
    <table>
        <tr>  
            <td> Serienummer: </td>
            <td> <form:input path="id" />
                 <form:errors path="id" />
            </td> 
        </tr>
        <tr>  
            <td> Maksvekt i kg: </td>
            <td> <form:input path="maxWeight" />
                 <form:errors path="maxWeight" />
            </td> 
        </tr>
        <tr>
            <td> Leverandør ID: </td>
            <td> <form:input path="supplierId" />
                 <form:errors path="supplierId" />
            </td>
        </tr>
        <tr>
            <td> Kunde ID: </td>
            <td> <form:input path="customerId" />
                 <form:errors path="customerId" />
            </td>
        </tr>
        <tr>
            <td> Antall gasstanker: </td>
            <td> <form:input path="gasTanks" />
                 <form:errors path="gasTanks" />
            </td>
        </tr>
        <tr><td colspan="2"><input type="submit" value="Registrer Gassmonitor"</td></tr>
    </table>
</form:form>
