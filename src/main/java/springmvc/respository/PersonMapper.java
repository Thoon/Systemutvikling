/* @author Tomas Holt
Klasse for å mappe data fra databaserad (person-tabell)
til Person-objekt. Brukes i forbindelse med 
springmvc.respository.PersonDatabaseJdbcTemplateRepositoryImpl
*/

package springmvc.respository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domene.Person;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int i) throws SQLException {
        Person person = new Person();
        person.setPersonId(rs.getInt("personId"));
        person.setFirstName(rs.getString("firstName"));
        person.setSurname(rs.getString("surname"));
        return person;
    }
}
