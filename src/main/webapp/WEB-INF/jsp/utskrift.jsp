<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Valgte personer</h2>
<h5>Utskrift via modelattribute valgtePersoner </h5>

<c:forEach items="${personFormBackingBean.valgtePersoner}" var="person">
    ${person}<br>
</c:forEach>
    
<hr>

<h5>Utskrift via request- objektet </h5>

<c:forEach items="${paramValues.valgtePersoner}" var="person">
    ${person}<br>
</c:forEach>



      



