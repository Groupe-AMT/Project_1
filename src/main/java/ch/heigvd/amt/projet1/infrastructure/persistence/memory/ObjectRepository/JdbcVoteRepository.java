package ch.heigvd.amt.projet1.infrastructure.persistence.memory.ObjectRepository;

import ch.heigvd.amt.projet1.domain.Id;
import ch.heigvd.amt.projet1.domain.vote.IVoteRepository;
import ch.heigvd.amt.projet1.domain.vote.Vote;
import ch.heigvd.amt.projet1.domain.vote.VoteId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
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
@Named("JdbcVoteRepository")
public class JdbcVoteRepository implements IVoteRepository {

    @Resource(lookup = "jdbc/AMTDS")
    private DataSource dataSource;

    public int save(Vote vote) {
        /*
        This function aims to insert in the database a Person
        */
        try {
            Connection con = dataSource.getConnection();

            String uuid = vote.getId().asString();
            //uuid = uuid.substring(uuid.lastIndexOf("@"+1));

            PreparedStatement ps = con.prepareStatement("INSERT INTO Vote (id, author, type, questionId, answerId, vote) VALUES('" +
                    uuid + "','" +
                    vote.getAuthor() + "','" +
                    vote.getType() + "','" +
                    vote.getQuestionId() + "','" +
                    vote.getAnswerId() + "','" +
                    vote.isNote() +
                    "')");
            boolean rs = ps.execute();

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcVoteRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int remove(VoteId id) {
        /*
        This function aims to remove an user by its id
         */
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("DELETE FROM Vote WHERE id = " + id.asString());
            ResultSet rs = ps.executeQuery();

            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(JdbcVoteRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Optional<Vote> findById(VoteId id) {
        /*
        This function aims to find and return an user by its id
         */
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Vote WHERE id = " + id.asString());
            ResultSet rs = ps.executeQuery();

            Vote result = Vote.builder()
                    .id(new VoteId(rs.getString("id")))
                    .author(rs.getString("author"))
                    .type(rs.getString("type"))
                    .questionId(rs.getString("questionId"))
                    .answerId(rs.getString("answerId"))
                    .note(rs.getBoolean("vote"))
                    .build();

            ps.close();
            con.close();
            return Optional.of(result);

        } catch (SQLException ex) {
            Logger.getLogger(JdbcVoteRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Optional.empty();
    }


    public List<Vote> findAll() {
        List<Vote> result = new LinkedList<Vote>();
        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Vote");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vote newVote = Vote.builder()
                        .id(new VoteId(rs.getString("id")))
                        .author(rs.getString("author"))
                        .type(rs.getString("type"))
                        .questionId(rs.getString("questionId"))
                        .answerId(rs.getString("answerId"))
                        .note(rs.getBoolean("vote"))
                        .build();
                result.add(newVote);
            }

            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(JdbcVoteRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int Size() {
        int res = 0;

        try {
            Connection con = dataSource.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT count(id) FROM Vote");
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

    @Override
    public int count(Id id, String type, boolean up) {
        int res = 0;
        int upDown= up?1:0;
        try {
            Connection con = dataSource.getConnection();
            String sql = "SELECT count(id) as total FROM Vote  WHERE "+type+"Id LIKE '" + id.asString()+"' and vote = "+upDown;
            PreparedStatement ps = con.prepareStatement(sql);
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
