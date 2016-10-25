<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Registrer ny gassmonitor</h2>

<form:form action="EditGasMonitor" method="post" modelAttribute="gasMonitor" >
    <table>
        <tr>
            <th>Epost</th>
            <th>Fornavn</th>
            <th>Etternavn</th>
            <th>Rettighet</th>
            <th>Aktiv</th>
            <th>Velg </th>
        </tr>
        <c:forEach var="gasMonitor" items="${gasMonitorFormBackingBean.allGasMonitors}" varStatus="status">
            <tr>
                <td> <c:out value="${gasMonitor.id}"/><form:hidden path="allGasMonitors[${status.index}].id" value="${gasMonitor.id}"/></td>
                
                <td> <c:out value="${gasMonitor.maxWeight}"/><form:hidden path="allGasMonitors[${status.index}].maxWeight" value="${gasMonitor.maxWeight}"/></td>
                
                <td> <c:out value="${gasMonitor.currentWeight}"/><form:hidden path="allGasMonitors[${status.index}].currentWeight" value="${gasMonitor.currentWeight}"/></td>
                
                <td> <c:out value="${gasMonitor.battery}"/><form:hidden path="allGasMonitors[${status.index}].battery" value="${gasMonitor.battery}"/></td>
                
                <td> <c:out value="${gasMonitor.customerId}"/><form:hidden path="allGasMonitors[${status.index}].customerId" value="${gasMonitor.customerId}"/></td>
            </tr>
    </table>
</form:form>
