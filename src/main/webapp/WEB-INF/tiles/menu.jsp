<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<ul>
    <li><a href="<c:url value="index"/>" >           <spring:message code="menyvalg1" />  </a></li>
    <li><a href="<c:url value="editPerson"/>" >   <spring:message code="menyvalg2" />  </a></li>
    <li><a href="<c:url value="registerPerson"/>">         <spring:message code="menyvalg3" />  </a></li>
    <li><a href="<c:url value="editGasMonitor"/>">         <spring:message code="menyvalg4" />  </a></li>
    <li><a href="<c:url value="registerGasMonitor"/>">         <spring:message code="menyvalg5" />  </a></li>
    <li><a href="<c:url value="editCustomer"/>">          <spring:message code="menyvalg6" /> </a></li>
    <li><a href="<c:url value="registerCustomer"/>">      <spring:message code="menyvalg7" /> </a></li>
    <li><a href="<c:url value="editSupplier"/>">      <spring:message code="menyvalg8" /> </a></li>
    <li><a href="<c:url value="registerSupplier"/>">      <spring:message code="menyvalg9" /> </a></li>

</ul>