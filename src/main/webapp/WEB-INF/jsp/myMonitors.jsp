<%-- 
    Document   : myMonitors
    Created on : 08.jan.2017, 11:44:26
    Author     : ganon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mine målere</title>
    </head>
    <body>
        <form:form action="myMonitors.htm" method="post" modelAttribute="MonitorResultsBackingBean">
        <table border="1" width="100%">
            
            <c:forEach var="monitorResults" items="${monitorResultsBackingBean.allResults}" varStatus="status">
            
            <h2><c:out value="${monitorResults.customerId}"/> <c:out value="${monitorResults.customerName}"/> <c:out value="${monitorResults.customerAddress}"/> </h2>
            <tr>
                <td><c:out value="${monitorResults.serialnumber}"/>
                    <form:hidden path="allResults[${status.index}].serialnumber" />
                </td>               
                <td> 
                    <form:input path="allResults[${status.index}].percentage" /> 
                    <form:errors path="allResults[${status.index}].percentage" />
                </td>
                <td> 
                    <form:input path="allResults[${status.index}].timestamp" /> 
                    <form:errors path="allResults[${status.index}].timestamp" />
                </td>
            </tr>
        </c:forEach>
        </table>
        </form:form>
    </body>
</html>