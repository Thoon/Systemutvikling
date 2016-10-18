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
    private Connection forbindelse;
    private final String sqlDeletePerson = "Delete from person where personnr = ?";
    private final String sqlSelectPerson = "Select * from person where personnr = ?";
    private final String sqlSelectAllePersoner = "Select * from person";
    
    private final String sqlInsertPerson = "insert into person values(?,?,?)";
    private final String sqlUpdatePerson = "update person set fornavn=?, etternavn = ? where personnr = ?";

    
    private DataSource dataSource;
    
    public PersonDatabaseRepositoryImpl() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
    }
    
    private void åpneForbindelse() throws Exception {
        try {
            System.out.println("Skal åpne forbindelse");
            forbindelse = dataSource.getConnection();
            System.out.println("**** Databaseforbindelse opprettet***");
        } catch (SQLException e) {
            System.out.println(" SqlFeil: " + e);
            Opprydder.skrivMelding(e, "Konstruktøren");
            Opprydder.lukkForbindelse(forbindelse);
        } catch( Exception ee){
            System.out.println(" Feil " + ee);
        }
    }

    private void lukkForbindelse() {
        System.out.println("Lukker databaseforbindelsen");
        Opprydder.lukkForbindelse(forbindelse);
    }
    
    public Person getPerson(String personNr){
        System.out.println("Database.getPerson()");
        PreparedStatement stmt = null;
        ResultSet res;
        Person person = null;
        try {
            åpneForbindelse();
            stmt = forbindelse.prepareStatement(sqlSelectPerson);
            stmt.setString(1, personNr);
            res = stmt.executeQuery();
            if (res.next()) {
                person = new Person(res.getInt("personId"),res.getString("firstName"), res.getString("surname"), res.getString("password"), res.getString("email"), res.getInt("phoneNumber"));
            }
        } catch (SQLException e) {
            Opprydder.rullTilbake(forbindelse);
            Opprydder.skrivMelding(e, "getAllePersoner()");
        } catch (Exception e) {
            Opprydder.skrivMelding(e, "getAllePersoner - ikke sqlfeil");
        } finally {
            Opprydder.settAutoCommit(forbindelse);
            Opprydder.lukkSetning(stmt);
        }
        lukkForbindelse();
        System.out.println(" Database.getPerson(). Har funnet person=" + person );
        return person;
    }

    public ArrayList<Person> getAllePersoner() {
        System.out.println("Database.getAllePersoner()");
        PreparedStatement psSelectAlle = null;
        ResultSet res;
        ArrayList<Person> personListe = null;
        try {
            åpneForbindelse();
            psSelectAlle = forbindelse.prepareStatement(sqlSelectAllePersoner);
            res = psSelectAlle.executeQuery();
            while (res.next()) {
                Person p = new Person(res.getInt("personId"),res.getString("firstName"), res.getString("surname"), res.getString("password"), res.getString("email"), res.getInt("phoneNumber"));
                if (personListe == null) {
                    personListe = new ArrayList<Person>();
                }
                personListe.add(p);
            }
        } catch (SQLException e) {
            Opprydder.rullTilbake(forbindelse);
            Opprydder.skrivMelding(e, "getAllePersoner()");
        } catch (Exception e) {
            Opprydder.skrivMelding(e, "getAllePersoner - ikke sqlfeil");
        } finally {
            Opprydder.settAutoCommit(forbindelse);
            Opprydder.lukkSetning(psSelectAlle);
        }
        lukkForbindelse();
        System.out.println(" Database.getAllePersoner(). Har funnet #personer=" + personListe.size());
        return personListe;
    }

    public synchronized boolean registrerPerson(Person p) {
        boolean ok = false;
        System.out.println("Database.registrerPerson()");
        PreparedStatement psInsertPerson = null;

        try {
            åpneForbindelse();
            psInsertPerson = forbindelse.prepareStatement(sqlInsertPerson);
            psInsertPerson.setInt(1, p.getPersonId());
            psInsertPerson.setString(2, p.getFirstName());
            psInsertPerson.setString(3, p.getSurname());

            int i = psInsertPerson.executeUpdate();
            if (i > 0) {
                ok = true;
            }
        } catch (SQLException e) {
            Opprydder.rullTilbake(forbindelse);
            Opprydder.skrivMelding(e, "*** registrerPerson()");
        } catch (Exception e) {
            Opprydder.skrivMelding(e, "**** registrerPerson - ikke sqlfeil");
        } finally {
            Opprydder.settAutoCommit(forbindelse);
            Opprydder.lukkSetning(psInsertPerson);
        }
        lukkForbindelse();
        return ok;
    }

    public synchronized boolean oppdaterPerson(Person p) {
        boolean ok = false;
        System.out.println("oppdaterPerson()");
        PreparedStatement psUpdatePerson = null;

        try {
            åpneForbindelse();
            psUpdatePerson = forbindelse.prepareStatement(sqlUpdatePerson);
            psUpdatePerson.setInt(3, p.getPersonId());
            psUpdatePerson.setString(1, p.getFirstName());
            psUpdatePerson.setString(2, p.getSurname());

            int i = psUpdatePerson.executeUpdate();
            if (i > 0) {
                ok = true;
            }
        } catch (SQLException e) {
            Opprydder.rullTilbake(forbindelse);
            Opprydder.skrivMelding(e, "oppdaterPerson()");
        } catch (Exception e) {
            Opprydder.skrivMelding(e, "oppdaterPerson - ikke sqlfeil");
        } finally {
            Opprydder.settAutoCommit(forbindelse);
            Opprydder.lukkSetning(psUpdatePerson);
        }
        lukkForbindelse();
        return ok;
    }

    public synchronized boolean slettPerson(Person p) {
        boolean ok = false;
        System.out.println("slettPerson()");
        PreparedStatement psDeletePerson = null;

        try {
            åpneForbindelse();
            psDeletePerson = forbindelse.prepareStatement(sqlDeletePerson);
            psDeletePerson.setInt(1, p.getPersonId());

            int i = psDeletePerson.executeUpdate();
            if (i > 0) {
                ok = true;
            }
        } catch (SQLException e) {
            Opprydder.rullTilbake(forbindelse);
            Opprydder.skrivMelding(e, "slettPerson()");
        } catch (Exception e) {
            Opprydder.skrivMelding(e, "slettPerson - ikke sqlfeil");
        } finally {
            Opprydder.settAutoCommit(forbindelse);
            Opprydder.lukkSetning(psDeletePerson);
        }
        lukkForbindelse();
        return ok;

    }
}