<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Registrer ny gassmonitor</h2>

<form:form action="editGasMonitor.htm" method="post" modelAttribute="gasMonitor">
    <table>
        <tr>
            <th>Gassmonitor ID</th>
            <th>Maks Vekt</th>
            <th>Målt Vekt</th>
            <th>Batteri</th>
            <th>Kunde</th>
        </tr>
        <c:forEach var="gasMonitor" items="${gasMonitorFormBackingBean.allGasMonitors}" varStatus="status">
            <tr>
                <td> <c:out value="${gasMonitor.id}"/>
                    <form:hidden path="allGasMonitors[${status.index}].id" value="${gasMonitor.id}"/></td>
                
                 <td> <form:input path="allGasMonitors[${status.index}].maxWeight" /> 
                    <form:errors path="allGasMonitors[${status.index}].maxWeight" />
                
                <td> <c:out value="${gasMonitor.currentWeight}"/>
                    <form:hidden path="allGasMonitors[${status.index}].currentWeight" value="${gasMonitor.currentWeight}"/></td>
                
                <td> <c:out value="${gasMonitor.battery}"/>
                    <form:hidden path="allGasMonitors[${status.index}].battery" value="${gasMonitor.battery}"/></td>
                
                <td> <c:out value="${gasMonitor.customerId}"/>
                    <form:hidden path="allGasMonitors[${status.index}].customerId" value="${gasMonitor.customerId}"/></td>
                
                <td> <c:out value="${gasMonitor.supplierId}"/>
                    <form:hidden path="allGasMonitors[${status.index}].supplierId" value="${gasMonitor.supplierId}"/></td>
            </tr>
    </table>
</form:form>
