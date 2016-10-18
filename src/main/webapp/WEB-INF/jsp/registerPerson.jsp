<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2>Registreringsskjema for ny personbruker</h2>

<form:form action="registerPerson.htm" method="post" modelAttribute="person" >
    <table>
        <tr><td> PersonId</td>
            <td>  <form:input path="personId" /> 
                  <form:errors path="personId" />
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
        <tr>
            <td> Brukernavn: </td>
            <td> <form:input path="username" />
                 <form:errors path="username" />
            </td>
        </tr>
        <tr>
            <td> Passord: </td>
            <td> <form:input path="password" />
                 <form:errors path="password" />
            </td>
        </tr>
        <tr>
            <td> E-mail: </td>
            <td> <form:input path="email" />
                 <form:errors path="email" />
            </td>
        </tr>
        <tr>
            <td> Telefonnummer: </td>
            <td> <form:input path="phoneNumber" />
                 <form:errors path="phoneNumber" />
            </td>
        </tr>

        <tr><td colspan="2"><input type="submit" value="Registrer person"</td></tr>
    </table>
</form:form>



