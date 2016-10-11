<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<h3><spring:message code="feilside.topp" /> </h3>

<h4>
    
    <spring:message code="feilside.internasjonalisering" /> <br>

    <spring:message code="${melding}" /> <!-- melding er satt i kontroller  -->

</h4>

<spring:message code="feilside.unntak" /> ${unntak}

