package ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao;

import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless

@ApplicationScoped
@Named("PersonDAO")
public class PersonDAO implements PersonDAOLocal {

    @Resource(lookup = "jdbc/AMT")
    private DataSource dataSource;

    public long save(Person person) {
        /*
        This function aims to insert in the database a Person
        */
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT OR IGNORE INTO Person(id, username, email, firstname, lastname, password) VALUES("+
                    person.getId().toString()+
                    person.getUsername()+
                    person.getFirstname()+
                    person.getLastName()+
                    person.getHashedPassword()+
                    ")");
            ResultSet rs = ps.executeQuery();

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public long remove(PersonId id) {
        /*
        This function aims to remove an user by its id
         */
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("DELETE FROM Person WHERE id = " + id.toString());
            ResultSet rs = ps.executeQuery();

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public long removeByUsername(String username) {
        /*
        This function aims to remove an user by its username
         */
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("DELETE FROM Person WHERE username LIKE '" + username + "'");
            ResultSet rs = ps.executeQuery();

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Person findById(PersonId id) {
        /*
        This function aims to find and return an user by its id
         */
        Person result = null;
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Person WHERE id = " + id.toString());
            ResultSet rs = ps.executeQuery();

            result = Person.builder()
                    .id(new PersonId(rs.getString("id")))
                    .username(rs.getString("username"))
                    .firstname(rs.getString("firstname"))
                    .lastName(rs.getString("lastname"))
                    .email(rs.getString("email"))
                    .hashedPassword(rs.getString("password"))
                    .build();

            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public Person findByUsername(String username) {
        /*
        This function aims to find and return an user by its id
         */
        Person result = null;
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Person WHERE username LIKE '" + username + "'");
            ResultSet rs = ps.executeQuery();

            Person newPerson = Person.builder()
                    .id(new PersonId(rs.getString("id")))
                    .username(rs.getString("username"))
                    .firstname(rs.getString("firstname"))
                    .lastName(rs.getString("lastname"))
                    .email(rs.getString("email"))
                    .hashedPassword(rs.getString("password"))
                    .build();
            result = newPerson;

            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<Person> findAll() {
        List<Person> result = new LinkedList<Person>();
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Person");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
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
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
