package ch.heigvd.amt.projet1.persistence;

import ch.heigvd.amt.projet1.business.QuestionDTO;
import ch.heigvd.amt.projet1.model.IQuestionRepository;
import ch.heigvd.amt.projet1.model.Question;
import ch.heigvd.amt.projet1.model.QuestionId;

import java.util.*;

import static java.util.Optional.empty;

public class InMemoryQuestionRepository implements IQuestionRepository {
    QuestionDTO content_list; //contient la mémoire de la base de donnée en mémoire
    public void Save(Question question){ //met question dans content_list

    }
    public void Remove(QuestionId id){ //enlève la question avec l'identifiant id de content_list

    }
    public Optional<Question> findById(QuestionId id){ //trouve la question avec l'identifiant id dans content_list et renvoie un optional rempli avec soit un null soit la réponse
        Optional<Question> answer = null;
        //TODO: fill answer with question that has the correct id
        return answer;

    }
    public List<Question> findAll() { //renvoie content_list
        List<Question> answer = new ArrayList<Question>();
        //TODO: fill answer with all questions in db
        return answer;
    }
    public List<Question> findByTag(String tag){ //renvoie les questions dans content_list qui contiennent tag dans leur liste de tags
        List<Question> answer = new ArrayList<Question>();
        //TODO : fill answer with rows that have tag in their tag list
        return answer;
    }
    public List<Question> findByAuthor(String author){ //renvoie les questions dans content_list qui ont pour auteur author
        List<Question> answer = new ArrayList<Question>();
        //TODO : fill with rows that have author in their author column
        return answer;
    }
}
