package springmvc.respository;

import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;
import springmvc.domene.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author tomash
 * Klasser for å hente/lagre/slette personer fra DB.
 */

public class PersonDatabaseJdbcTemplateRepositoryImpl implements PersonRepository{
    
    private final String sqlDeletePerson = "Delete from person where personnr = ?";
    private final String sqlSelectPerson = "Select * from person where personnr = ?";
    private final String sqlSelectAllePersoner = "Select * from person";
    
    private final String sqlInsertPerson = "insert into person values(?,?,?)";
    private final String sqlUpdatePerson = "update person set fornavn=?, etternavn = ? where personnr = ?";

    
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    public PersonDatabaseJdbcTemplateRepositoryImpl() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public Person getPerson(String personnr ){
        return (Person)jdbcTemplateObject.queryForObject(sqlSelectPerson, new Object[]{personnr}, new PersonMapper());
    }
    
    public List<Person> getAllePersoner(){
        return jdbcTemplateObject.query(sqlSelectAllePersoner, new PersonMapper());
    }

    public boolean slettPerson(Person person) {
        jdbcTemplateObject.update(sqlDeletePerson, person.getPersonId() );
        return true;
    }
    
    public boolean oppdaterPerson(Person person){
        jdbcTemplateObject.update(sqlUpdatePerson, new Object[]{
            person.getFirstName(),
            person.getSurname(),
            person.getPersonId()
        });
        return true;
    }
    
    public boolean registrerPerson(Person person){
        jdbcTemplateObject.update(sqlInsertPerson, 
            new Object[]{
                person.getPersonId(), 
                person.getFirstName(), 
                person.getSurname()
        });
        return true;
    }
}
