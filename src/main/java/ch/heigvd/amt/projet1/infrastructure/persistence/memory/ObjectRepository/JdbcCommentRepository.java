package ch.heigvd.amt.projet1.infrastructure.persistence.memory.ObjectRepository;

import ch.heigvd.amt.projet1.domain.Id;
import ch.heigvd.amt.projet1.domain.answer.Answer;
import ch.heigvd.amt.projet1.domain.answer.AnswerId;
import ch.heigvd.amt.projet1.domain.comment.Comment;
import ch.heigvd.amt.projet1.domain.comment.CommentId;
import ch.heigvd.amt.projet1.domain.comment.ICommentRepository;
import ch.heigvd.amt.projet1.domain.question.QuestionId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@Named("JdbcCommentRepository")
public class JdbcCommentRepository implements ICommentRepository {
    @Resource(lookup = "jdbc/AMTDS")
    private DataSource dataSource;

    // Function for saving an Answer in the data base
    public int save(Comment comment) {
        /*
        This function aims to insert in the database a Answer
        */
        try{
            Connection con = dataSource.getConnection();

            String uuid = comment.getId().asString();
            //uuid = uuid.substring(uuid.lastIndexOf("@"+1));

            // Pour ajouter le message dans la bdd
            PreparedStatement ps1 = con.prepareStatement("INSERT INTO Comment (id, author, content, type, questionId,answerId) VALUES('"+
                    uuid+"','"+
                    comment.getAuthor()+"','"+
                    comment.getContent()+"','"+
                    comment.getType()+"','"+
                    comment.getQuestionId()+"','"+
                    comment.getAnswerId()+
                    "')");
            ps1.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcCommentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // Function for removing an answer by its id
    public int remove(CommentId id) {
        /*
        This function aims to remove an answer by its id
         */
        try{
            Connection con = dataSource.getConnection();

            PreparedStatement ps1 = con.prepareStatement("DELETE FROM Comment WHERE id = '" + id.asString()+"'");
            ps1.executeQuery();
            ps1.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcCommentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // Function for finding an answer by its id
    public Optional<Comment> findById(CommentId id){
        /*
        This function aims to find and return an answer by its id
         */
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Comment WHERE id = '" + id.asString()+"'");
            ResultSet rs = ps.executeQuery();

            Comment result = Comment.builder()
                    .id(new CommentId(rs.getString("id")))
                    .author(rs.getString("author"))
                    .content(rs.getString("content"))
                    .type(rs.getString("type"))
                    .questionId(rs.getString("questionId"))
                    .answerId(rs.getString("answerId"))
                    .build();

            ps.close();
            con.close();
            return Optional.of(result);

        } catch (SQLException ex) {
            Logger.getLogger(JdbcCommentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    // Find all the answers
    public List<Comment> findAll() {
        List<Comment> result = new LinkedList<Comment>();
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Comment");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Comment newComment = Comment.builder()
                        .id(new CommentId(rs.getString("id")))
                        .author(rs.getString("author"))
                        .content(rs.getString("content"))
                        .type(rs.getString("type"))
                        .questionId( rs.getString("questionId"))
                        .answerId(rs.getString("answerId"))
                        .build();
                result.add(newComment);
            }

            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(JdbcCommentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Function for finding all related answer of a question
    public List<Comment> findAllbySource(Id id, String type){
        List<Comment> result = new LinkedList<Comment>();
        try{
            Connection con = dataSource.getConnection();
            String sql = "SELECT * FROM Comment WHERE "+type+"Id LIKE '" + id.asString()+"'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Comment newComment = Comment.builder()
                        .id(new CommentId(rs.getString("id")))
                        .author(rs.getString("author"))
                        .content(rs.getString("content"))
                        .type(rs.getString("type"))
                        .questionId(rs.getString("questionId"))
                        .answerId(rs.getString("answerId"))
                        .build();
                result.add(newComment);
            }

            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(JdbcCommentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Function Statistics
    public int Size(){
        /**
         * This function count the number of comments in the data base
         */
        int res = 0;

        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT count(id) FROM Comment");
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
         * This function count the number of comments in the data base for a specific username
         */
        int res = 0;

        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT count(id) FROM Comment WHERE author LIKE '"+username+"'");
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
