<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2>Rediger målere</h2>

<form:form action="editGasMonitor" method="post" modelAttribute="gasMonitorFormBackingBean">
    <table>
        <tr>
            <th>Gassmonitor ID</th>
            <th>Maks Vekt</th>
            <th>Leverandør</th>
            <th>Kunde</th>
            <th>Antall gasstanker</th>
            <th>Velg</th>
        </tr>
        
        <c:forEach var="gasMonitor" items="${gasMonitorFormBackingBean.allGasMonitors}" varStatus="status">
            <tr>
                <td> <c:out value="${gasMonitor.id}"/>
                    <form:hidden path="allGasMonitors[${status.index}].id"/></td>
                
                <td> <form:input path="allGasMonitors[${status.index}].maxWeight" /> 
                     <form:errors path="allGasMonitors[${status.index}].maxWeight" />
                </td>
                <td> <form:input path="allGasMonitors[${status.index}].supplierId" /> 
                     <form:errors path="allGasMonitors[${status.index}].supplierId" />
                </td>
                <td> <form:input path="allGasMonitors[${status.index}].customerId" /> 
                     <form:errors path="allGasMonitors[${status.index}].customerId" />
                </td>
                <td> <form:input path="allGasMonitors[${status.index}].gasTanks" /> 
                     <form:errors path="allGasMonitors[${status.index}].gasTanks" />
                </td>
                
                <td> <form:checkbox path="selectedGasMonitors" value="${gasMonitor.id}" /> </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Oppdater gassmonitor" name="updateGasmonitor" />    
    <input type="submit" value="Slett valgte gassmonitorer" name="deleteGasMonitor" />
</form:form>
