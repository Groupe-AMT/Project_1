package ch.heigvd.amt.projet1.infrastructure.persistence.memory.ObjectRepository;

import ch.heigvd.amt.projet1.domain.question.IQuestionRepository;
import ch.heigvd.amt.projet1.domain.question.Question;
import ch.heigvd.amt.projet1.domain.question.QuestionId;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class JdbcQuestionRepository implements IQuestionRepository {
    @Resource(lookup = "jdbc/AMTDS")
    private DataSource dataSource;

    public int save(Question question) {
        /*
        This function aims to insert in the database a Person
        */
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO Question(id, subject, author, content, tags) VALUES('" +
                    question.getId().asString() + "','" +
                    question.getSubject().toString() + "','" +
                    question.getAuthor().toString() + "','" +
                    question.getContent().toString() + "','" +
                    question.getTags().toString() +
                    "')");
            boolean rs = ps.execute();

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int remove(QuestionId id) {
        /*
        This function aims to remove an user by its id
         */
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("DELETE FROM Question WHERE id = " + id.toString());
            ResultSet rs = ps.executeQuery();

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private static ArrayList<String> fromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        ArrayList<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(strings));
        return result;
    }

    public Optional<Question> findById(QuestionId id) {
        /*
        This function aims to find and return an user by its id
         */
        Question result = null;
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Person WHERE id = " + id.toString());
            ResultSet rs = ps.executeQuery();

            Question newQuestion = Question.builder()
                    .id(new QuestionId(rs.getString("id")))
                    .Subject(rs.getString("subject"))
                    .author(rs.getString("author"))
                    .content(rs.getString("content"))
                    .Tags(fromString(rs.getString("tags"))).build();

            ps.close();
            con.close();
            return Optional.of(newQuestion);

        } catch (SQLException ex) {
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    public List<Question> findAll() {
        List<Question> result = new LinkedList<Question>();
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Question");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Collection<Question> findByTag(String tag) {
        return findAll().stream()
                .filter(question -> question.getTags().contains(tag))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Question> findByAuthor(String author) {
        return findAll().stream()
                .filter(question -> question.getAuthor().equals(author))
                .collect(Collectors.toList());
    }
}
