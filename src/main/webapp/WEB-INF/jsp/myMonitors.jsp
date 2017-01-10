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
        <form:form action="myMonitors.htm" method="POST" modelAttribute="monitorResultsBackingBean">
        <table border="1" width="100%">
            
            <c:forEach var="monitorResults" items="${monitorResultsBackingBean.allResults}" varStatus="status">
            <tr>
                <td>
                <c:out value="${monitorResults.customerId}"/> 
                    <form:hidden path="allResults[${status.index}].customerId" />
                </td>
                
                <td> <form:input path="monitorResults[${status.index}].customerName" /> 
                     <form:errors path="monitorResults[${status.index}].customerName" />
                </td>
                
                <td> <form:input path="monitorResults[${status.index}].customerAddress" /> 
                     <form:errors path="monitorResults[${status.index}].customerAddress" />
                </td>
                    
                 <td> <form:input path="monitorResults[${status.index}].serialnumber" /> 
                     <form:errors path="monitorResults[${status.index}].serialnumber" />
                </td>                   
 
                <td> <form:input path="monitorResults[${status.index}].percentage" /> 
                     <form:errors path="monitorResults[${status.index}].percentage" />
                </td>                    
                    
                <td> <form:input path="monitorResults[${status.index}].timestamp" /> 
                     <form:errors path="monitorResults[${status.index}].timestamp" />
                </td>                      
            </tr>
        </c:forEach>
        </table>
        </form:form>
    </body>
</html>