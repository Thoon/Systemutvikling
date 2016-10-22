package springmvc.respository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import springmvc.domene.Person;
import springmvc.repository.utils.Opprydder;

/**
 *
 * @author Grethe, endret/utvidet av Tomas.
 */

public class PersonDatabaseRepositoryImpl implements PersonRepository{
    private Connection connection;
    private final String sqlDeletePerson = "Delete from Person where email = ?";
    private final String sqlSelectPerson = "Select * from Person where email = ?";
    private final String sqlSelectEveryone = "Select * from Person";
    
    private final String sqlInsertPerson = "insert into Person values(?,?,?)";
    private final String sqlUpdatePerson = "update Person set firstName=?, surname = ? where email = ?";

    
    private DataSource dataSource;
    
    public PersonDatabaseRepositoryImpl() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
    }
    
    private void openConnection() throws Exception {
        try {
            System.out.println("Skal åpne forbindelse");
            connection = dataSource.getConnection();
            System.out.println("**** Databaseforbindelse opprettet***");
        } catch (SQLException e) {
            System.out.println(" SqlFeil: " + e);
            Opprydder.write(e, "Konstruktøren");
            Opprydder.closeConnection(connection);
        } catch( Exception ee){
            System.out.println(" Feil " + ee);
        }
    }

    private void closeConnection() {
        System.out.println("Lukker databaseforbindelse");
        Opprydder.closeConnection(connection);
    }
    
    public Person getPerson(String email){
        System.out.println("Database.getPerson()");
        PreparedStatement stmt = null;
        ResultSet res;
        Person person = null;
        try {
            openConnection();
            stmt = connection.prepareStatement(sqlSelectPerson);
            stmt.setString(1, email);
            res = stmt.executeQuery();
            if (res.next()) {
                person = new Person(res.getString("firstName"), res.getString("surname"), res.getString("password"), res.getString("email"), 
                        res.getInt("phoneNumber"), res.getString("permission"), res.getBoolean("isActive"));
            }
        } catch (SQLException e) {
            Opprydder.rollback(connection);
            Opprydder.write(e, "getEveryone()");
        } catch (Exception e) {
            Opprydder.write(e, "getEveryone - ikke sqlfeil");
        } finally {
            Opprydder.setAutoCommit(connection);
            Opprydder.closeSentence(stmt);
        }
        closeConnection();
        System.out.println(" Database.getPerson(). Har funnet person=" + person );
        return person;
    }

    public ArrayList<Person> getEveryone() {
        System.out.println("Database.getEveryone()");
        PreparedStatement psSelectAlle = null;
        ResultSet res;
        ArrayList<Person> personList = null;
        try {
            openConnection();
            psSelectAlle = connection.prepareStatement(sqlSelectEveryone);
            res = psSelectAlle.executeQuery();
            while (res.next()) {
                Person p = new Person(res.getString("firstName"), res.getString("surname"), res.getString("password"), res.getString("email"), 
                        res.getInt("phoneNumber"), res.getString("permission"), res.getBoolean("isActive"));
                if (personList == null) {
                    personList = new ArrayList<Person>();
                }
                personList.add(p);
            }
        } catch (SQLException e) {
            Opprydder.rollback(connection);
            Opprydder.write(e, "getEveryone()");
        } catch (Exception e) {
            Opprydder.write(e, "getEveryone - ikke sqlfeil");
        } finally {
            Opprydder.setAutoCommit(connection);
            Opprydder.closeSentence(psSelectAlle);
        }
        closeConnection();
        System.out.println(" Database.getEveryone(). Har funnet #personer=" + personList.size());
        return personList;
    }

    public synchronized boolean registerPerson(Person p) {
        boolean ok = false;
        System.out.println("Database.registerPerson()");
        PreparedStatement psInsertPerson = null;

        try {
            openConnection();
            psInsertPerson = connection.prepareStatement(sqlInsertPerson);
            psInsertPerson.setString(1, p.getEmail());
            psInsertPerson.setString(2, p.getFirstName());
            psInsertPerson.setString(3, p.getSurname());

            int i = psInsertPerson.executeUpdate();
            if (i > 0) {
                ok = true;
            }
        } catch (SQLException e) {
            Opprydder.rollback(connection);
            Opprydder.write(e, "*** registerPerson()");
        } catch (Exception e) {
            Opprydder.write(e, "**** registerPerson - ikke sqlfeil");
        } finally {
            Opprydder.setAutoCommit(connection);
            Opprydder.closeSentence(psInsertPerson);
        }
        closeConnection();
        return ok;
    }

    public synchronized boolean updatePerson(Person p) {
        boolean ok = false;
        System.out.println("updatePerson()");
        PreparedStatement psUpdatePerson = null;

        try {
            openConnection();
            psUpdatePerson = connection.prepareStatement(sqlUpdatePerson);
            psUpdatePerson.setString(3, p.getEmail());
            psUpdatePerson.setString(1, p.getFirstName());
            psUpdatePerson.setString(2, p.getSurname());

            int i = psUpdatePerson.executeUpdate();
            if (i > 0) {
                ok = true;
            }
        } catch (SQLException e) {
            Opprydder.rollback(connection);
            Opprydder.write(e, "updatePerson()");
        } catch (Exception e) {
            Opprydder.write(e, "updatePerson - ikke sqlfeil");
        } finally {
            Opprydder.setAutoCommit(connection);
            Opprydder.closeSentence(psUpdatePerson);
        }
        closeConnection();
        return ok;
    }

    public synchronized boolean deletePerson(Person p) {
        boolean ok = false;
        System.out.println("deletePerson()");
        PreparedStatement psDeletePerson = null;

        try {
            openConnection();
            psDeletePerson = connection.prepareStatement(sqlDeletePerson);
            psDeletePerson.setString(1, p.getEmail());

            int i = psDeletePerson.executeUpdate();
            if (i > 0) {
                ok = true;
            }
        } catch (SQLException e) {
            Opprydder.rollback(connection);
            Opprydder.write(e, "deletePerson()");
        } catch (Exception e) {
            Opprydder.write(e, "deletePerson - ikke sqlfeil");
        } finally {
            Opprydder.setAutoCommit(connection);
            Opprydder.closeSentence(psDeletePerson);
        }
        closeConnection();
        return ok;

    }
}