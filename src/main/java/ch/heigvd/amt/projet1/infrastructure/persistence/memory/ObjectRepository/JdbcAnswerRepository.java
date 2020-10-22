package ch.heigvd.amt.projet1.infrastructure.persistence.memory.ObjectRepository;

import ch.heigvd.amt.projet1.domain.answer.Answer;
import ch.heigvd.amt.projet1.domain.answer.AnswerId;
import ch.heigvd.amt.projet1.domain.answer.IAnswerRepository;
import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.domain.person.PersonId;
import ch.heigvd.amt.projet1.domain.question.QuestionId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@Named("JdbcAnswerRepository")
public class JdbcAnswerRepository implements IAnswerRepository {

    @Resource(lookup = "jdbc/AMTDS")
    private DataSource dataSource;

    // Function for saving an Answer in the data base
    public int save(Answer answer) {
        /*
        This function aims to insert in the database a Answer
        */
        try{
            Connection con = dataSource.getConnection();

            String uuid = answer.getId().asString();
            //uuid = uuid.substring(uuid.lastIndexOf("@"+1));

            // Pour ajouter le message dans la bdd
            PreparedStatement ps1 = con.prepareStatement("INSERT INTO Answer (id, author, content, questionId, date) VALUES('"+
                    uuid+"' ,'"+
                    answer.getAuthor()+"','"+
                    answer.getContent()+"','"+
                    answer.getQuestionId()+"','"+
                    answer.getDate()+
                    "')");
            ps1.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcAnswerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // Function for removing an answer by its id
    public int remove(AnswerId id) {
        /*
        This function aims to remove an answer by its id
         */
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps1 = con.prepareStatement("DELETE FROM Answer WHERE id = " + id.asString());
            ps1.executeQuery();
            ps1.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcAnswerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // Function for finding an answer by its id
    public Optional<Answer> findById(AnswerId id){
        /*
        This function aims to find and return an answer by its id
         */
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Answer WHERE id = " + id.asString());
            ResultSet rs = ps.executeQuery();

            Answer result = Answer.builder()
                    .id(new AnswerId(rs.getString("id")))
                    .author(rs.getString("author"))
                    .content(rs.getString("content"))
                    .questionId(new QuestionId(rs.getString("questionId")).asString())
                    .build();

            ps.close();
            con.close();
            return Optional.of(result);

        } catch (SQLException ex) {
            Logger.getLogger(JdbcAnswerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    // Find all the answers
    public List<Answer> findAll() {
        List<Answer> result = new LinkedList<Answer>();
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Answer");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Answer newAnswer = Answer.builder()
                        .id(new AnswerId(rs.getString("id")))
                        .author(rs.getString("author"))
                        .content(rs.getString("content"))
                        .questionId(new QuestionId(rs.getString("questionId")).asString())
                        .build();
                result.add(newAnswer);
            }

            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(JdbcAnswerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Function for finding all related answer of a question
    public List<Answer> findAllforQuestion(QuestionId id){
        List<Answer> result = new LinkedList<Answer>();
        try{
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Answer WHERE questionId LIKE '" + id.asString()+"'");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Answer newAnswer = Answer.builder()
                        .id(new AnswerId(rs.getString("id")))
                        .author(rs.getString("author"))
                        .content(rs.getString("content"))
                        .questionId(new QuestionId(rs.getString("questionId")).asString())
                        .build();
                result.add(newAnswer);
            }

            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(JdbcAnswerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Function Statistics
    public int Size(){
        /**
         * This function count the number of answers in the data base
         */
        int res = 0;

        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT count(id) FROM Answer");
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

    public int SizeFor(String username){
        /**
         * This function count the number of answers in the data base for a specific username
         */
        int res = 0;

        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT count(id) FROM Answer WHERE author LIKE '"+username+"'");
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
