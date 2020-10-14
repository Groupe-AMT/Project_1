package ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao;

import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;
import ch.heigvd.amt.projet1.domain.question.Question;
import ch.heigvd.amt.projet1.domain.question.QuestionId;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class QuestionDAO implements QuestionDAOLocal{
    @Resource(lookup = "jdbc/AMTDS")
    private DataSource dataSource;

    public long save(Question question) {
        /*
        This function aims to insert in the database a Person
        */
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO Question(id, subject, author, content, tags) VALUES('"+
                    question.getId().asString()+"','"+
                    question.getSubject().toString()+"','"+
                    question.getAuthor().toString()+"','"+
                    question.getContent().toString()+"','"+
                    question.getTags().toString()+
                    "')");
            boolean rs = ps.execute();

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // TODO: NE SAIT PAS SI FONCTIONNE
    public long remove(QuestionId id) {
        /*
        This function aims to remove an user by its id
         */
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("DELETE FROM Question WHERE id = " + id.toString());
            ResultSet rs = ps.executeQuery();

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private static ArrayList<String> fromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < strings.length; i++) {
            result.add(strings[i]);
        }
        return result;
    }

    // TODO: NE SAIT PAS SI FONCTIONNE
    public Question findById(QuestionId id) {
        /*
        This function aims to find and return an user by its id
         */
        Question result = null;
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Person WHERE id = " + id.toString());
            ResultSet rs = ps.executeQuery();

            Question newQuestion = Question.builder()
                    .id(new QuestionId(rs.getString("id")))
                    .Subject(rs.getString("subject"))
                    .author(rs.getString("author"))
                    .content(rs.getString("content"))
                    .Tags(fromString(rs.getString("tags"))).build();
            result = newQuestion;

            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<Question> findAll() {
        List<Question> result = new LinkedList<Question>();
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Question");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Question newQuestion = Question.builder()
                        .id(new QuestionId(rs.getString("id")))
                        .Subject(rs.getString("subject"))
                        .author(rs.getString("author"))
                        .content(rs.getString("content"))
                        .Tags(fromString(rs.getString("tags")))
                        .build();
                result.add(newQuestion);
            }

            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
