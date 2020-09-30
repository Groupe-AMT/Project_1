package ch.heigvd.amt.projet1.persistence;

import ch.heigvd.amt.projet1.business.QuestionDTO;
import ch.heigvd.amt.projet1.model.IQuestionRepository;
import ch.heigvd.amt.projet1.model.Question;
import ch.heigvd.amt.projet1.model.QuestionId;

import java.util.*;

import static java.util.Optional.empty;

public class InMemoryQuestionRepository implements IQuestionRepository {
    QuestionDTO content_list; //contient la mémoire de la base de donnée en mémoire
    public void Save(Question question){

    }
    public void Remove(QuestionId id){

    }
    public Optional<Question> findById(QuestionId id){
        Optional<Question> answer = null;
        //TODO: fill answer with question that has the correct id
        return answer;

    }
    public List<Question> findAll() {
        List<Question> answer = new ArrayList<Question>();
        //TODO: fill answer with all questions in db
        return answer;
    }
    public List<Question> findByTag(String tag){
        List<Question> answer = new ArrayList<Question>();
        //TODO : fill answer with rows that have tag in their tag list
        return answer;
    }
    public List<Question> findByAuthor(String author){
        List<Question> answer = new ArrayList<Question>();
        //TODO : fill with rows that have author in their author column
        return answer;
    }
}
