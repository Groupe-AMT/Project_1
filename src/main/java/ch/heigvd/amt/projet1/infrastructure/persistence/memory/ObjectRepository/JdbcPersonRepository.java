package ch.heigvd.amt.projet1.infrastructure.persistence.memory.ObjectRepository;

import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@Named("JdbcUserRepository")
public class JdbcPersonRepository implements IPersonRepository {

    @Resource(lookup = "jdbc/AMTDS")
    private DataSource dataSource;

    public int save(Person person) {
        /*
        This function aims to insert in the database a Person
        */
        try {
            Connection con = dataSource.getConnection();

            String uuid = person.getId().asString();
            //uuid = uuid.substring(uuid.lastIndexOf("@"+1));

            PreparedStatement ps = con.prepareStatement("INSERT INTO Person (id, username, email, firstname, lastname, password) VALUES('" +
                    uuid + "','" +
                    person.getUsername() + "','" +
                    person.getEmail() + "','" +
                    person.getFirstname() + "','" +
                    person.getLastName() + "','" +
                    person.getHashedPassword() +
                    "')");
            boolean rs = ps.execute();

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int remove(PersonId id) {
        /*
        This function aims to remove an user by its id
         */
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("DELETE FROM Person WHERE id = " + id.asString());
            ResultSet rs = ps.executeQuery();

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public long removeByUsername(String username) {
        /*
        This function aims to remove an user by its username
         */
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("DELETE FROM Person WHERE username LIKE '" + username + "'");
            ResultSet rs = ps.executeQuery();

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Optional<Person> findById(PersonId id) {
        /*
        This function aims to find and return an user by its id
         */
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Person WHERE id = " + id.asString());
            ResultSet rs = ps.executeQuery();

            Person result = Person.builder()
                    .id(new PersonId(rs.getString("id")))
                    .username(rs.getString("username"))
                    .firstname(rs.getString("firstname"))
                    .lastName(rs.getString("lastname"))
                    .email(rs.getString("email"))
                    .hashedPassword(rs.getString("password"))
                    .build();

            ps.close();
            con.close();
            return Optional.of(result);

        } catch (SQLException ex) {
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Optional.empty();
    }

    public Optional<Person> findByUsername(String username) {
        /*
        This function aims to find and return an user by its id
         */
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Person WHERE username = '" + username + "'");
            ResultSet rs = ps.executeQuery();

            ArrayList<Person> results = new ArrayList<>();

            if (rs.next()) {
                Person result = Person.builder()
                        .id(new PersonId(rs.getString("id")))
                        .username(rs.getString("username"))
                        .firstname(rs.getString("firstname"))
                        .lastName(rs.getString("lastname"))
                        .email(rs.getString("email"))
                        .hashedPassword(rs.getString("password"))
                        .build();
                results.add(result);
            }
            if (results.size() != 1)
                throw new SQLException("Too Much same Username");

            ps.close();
            con.close();
            return Optional.of(results.get(0));

        } catch (SQLException ex) {
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Optional.empty();
    }

    public List<Person> findAll() {
        List<Person> result = new LinkedList<Person>();
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Person");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Person newPerson = Person.builder()
                        .id(new PersonId(rs.getString("id")))
                        .username(rs.getString("username"))
                        .firstname(rs.getString("firstname"))
                        .lastName(rs.getString("lastname"))
                        .email(rs.getString("email"))
                        .hashedPassword(rs.getString("password"))
                        .build();
                result.add(newPerson);
            }

            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Functions for changing ones identity and password
    public void updating(CurrentUserDTO user, String username, String firstname, String lastname, String email){
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Person WHERE username LIKE '" + user.getUsername() + "'");
            ResultSet rs = ps.executeQuery();

            rs.next();
            Person currentPerson = null;
            currentPerson = Person.builder()
                    .id(new PersonId(rs.getString("id")))
                    .username(rs.getString("username"))
                    .firstname(rs.getString("firstname"))
                    .lastName(rs.getString("lastname"))
                    .email(rs.getString("email"))
                    .hashedPassword(rs.getString("password"))
                    .build();
            ps.close();

            String uuid = currentPerson.getId().asString();

            PreparedStatement psu = con.prepareStatement("UPDATE Person SET " +
                    "username = '"+ username + "', " +
                    "firstname = '"+ firstname + "', " +
                    "lastname = '"+ lastname + "', " +
                    "email = '"+ email + "' " +
                    "WHERE id LIKE '"+ uuid +"';");
            boolean rsu = psu.execute();

            psu.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changingPass(CurrentUserDTO user, String new_pass){
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Person WHERE username = '" + user.getUsername() + "'");
            ResultSet rs = ps.executeQuery();
            rs.next();
            Person currentPerson = Person.builder()
                    .id(new PersonId(rs.getString("id")))
                    .username(rs.getString("username"))
                    .firstname(rs.getString("firstname"))
                    .lastName(rs.getString("lastname"))
                    .email(rs.getString("email"))
                    .hashedPassword(rs.getString("password"))
                    .build();
            ps.close();

            String uuid = currentPerson.getId().asString();
            PreparedStatement psu = con.prepareStatement("UPDATE Person SET " +
                    "password = '"+ new_pass + "'" +
                    "WHERE id = '"+ uuid +"'");
            boolean rsu = psu.execute();

            psu.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Function Statistics
    public int Size(){
        /**
         * This function count the number of persons in the data base
         */
        int res = 0;

        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT count(id) FROM Person");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                res = rs.getInt(1);
            }

            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(JdbcAnswerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }
}
