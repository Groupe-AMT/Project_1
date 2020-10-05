package ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao;

import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;
import ch.heigvd.amt.projet1.domain.question.Question;
import ch.heigvd.amt.projet1.domain.question.QuestionId;

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
@Named("QuestionDAO")
public class QuestionDAO implements QuestionDAOLocal{
    @Resource(lookup = "jdbc/AMT")
    private DataSource dataSource;

    public long save(Question question) {
        /*
        This function aims to insert in the database a Person
        */
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT OR IGNORE INTO Question(id, subject, author, content, tags) VALUES("+
                    question.getId().toString()+
                    question.getSubject().toString()+
                    question.getAuthor().toString()+
                    question.getContent().toString()+
                    question.getTags().toString()+
                    ")");
            ResultSet rs = ps.executeQuery();

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

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

    public Question findById(QuestionId id) {
        /*
        This function aims to find and return an user by its id
         */
        Question result = new Question();
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Person WHERE id = " + id.toString());
            ResultSet rs = ps.executeQuery();

            Question newQuestion = Question.builder()
                    .id(rs.getInt("id"))
                    .subject(rs.getString("subject"))
                    .author(rs.getString("author"))
                    .content(rs.getString("content"))
                    .tags(rs.getString("tags"))
                    .build();
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
                        .id(rs.getInt("id"))
                        .subject(rs.getString("subject"))
                        .author(rs.getString("author"))
                        .content(rs.getString("content"))
                        .tags(rs.getString("tags"))
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
