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
        person.setEmail(rs.getString("email"));
        person.setFirstName(rs.getString("firstName"));
        person.setLastName(rs.getString("lastName"));
        person.setPhoneNumber(rs.getInt("phone"));
        person.setPassword(rs.getString("password"));
        person.setPermission(rs.getInt("permissions"));
        person.setIsActive(rs.getBoolean("active"));
        return person;
    }
}
