<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2>Registrer ny person</h2>

<form:form action="nyperson.htm" method="post" modelAttribute="person" >
    <table>
        <tr><td> Personnummer </td>
            <td>  <form:input path="personnr" /> 
                  <form:errors path="personnr" />
            </td>
        </tr>
        <tr>  
            <td> Fornavn: </td>
            <td> <form:input path="fornavn" />
                 <form:errors path="fornavn" />
            </td> 
        </tr>
        <tr>
            <td> Etternavn: </td>
            <td> <form:input path="etternavn" />
                 <form:errors path="etternavn" />
            </td>
        </tr>

        <tr><td colspan="2"><input type="submit" value="Registrer person"</td></tr>
    </table>
</form:form>



