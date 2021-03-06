<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>Registreringsskjema for ny personbruker</h2>

<form:form action="registerPerson.htm" method="post" modelAttribute="person" >
    <table>
        <tr>  
            <td> Fornavn: </td>
            <td> <form:input path="firstName" />
                 <form:errors path="firstName" />
            </td> 
        </tr>
        <tr>
            <td> Etternavn: </td>
            <td> <form:input path="lastName" />
                 <form:errors path="lastName" />
            </td>
        </tr>
        <form:hidden path="password" value="0"/>
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
        <tr>
            <td> Brukerrettigheter: </td>
            <td> <form:radiobutton path="permission" value="0" /> Admin
                 <form:radiobutton path="permission" value="1" /> Forhandler
                 <form:radiobutton path="permission" value="2" /> Kunde
            </td>
            <form:hidden path="isActive" value="false"/>
        </tr>
        <tr><td colspan="2"><input type="submit" value="Registrer person"></td></tr>
    </table>
</form:form>



