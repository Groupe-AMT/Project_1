package ch.heigvd.amt.projet1.persistence;

import ch.heigvd.amt.projet1.business.QuestionDTO;
import ch.heigvd.amt.projet1.model.IQuestionRepository;
import ch.heigvd.amt.projet1.model.Question;
import ch.heigvd.amt.projet1.model.QuestionId;

import java.util.*;

import static java.util.Optional.empty;

public class InMemoryQuestionRepository implements IQuestionRepository {
    QuestionDTO content_list; //contient la mémoire de la base de donnée en mémoire
    public QuestionDTO getContentList(){
        return content_list;
    }
    public void Save(Question question){ //met question dans content_list
        this.content_list.addContent(question);
    }
    public void Remove(QuestionId id){ //enlève la question avec l'identifiant id de content_list
        Question answer = null;
        List<Question> temp_content = content_list.getContent();
        Iterator iterator = temp_content.iterator();
        while(iterator.hasNext()){
            Question temp = (Question) iterator.next();
            if(temp.getQuestionId().equals(id)){
                answer = temp;
            }
        }
        temp_content.remove(answer);
        content_list.setContent(temp_content);
    }
    public Optional<Question> findById(QuestionId id){ //trouve la question avec l'identifiant id dans content_list et renvoie un optional rempli avec soit un null soit la réponse
        Question temp_answer = null; //content of answer;
        List<Question> temp_content = content_list.getContent();
        Iterator iterator = temp_content.iterator();
        while(iterator.hasNext()){
            Question temp = (Question) iterator.next();
            if(temp.getQuestionId().getId().equals(id)){ //if we find the right element we put it in the answer
                temp_answer = temp;
            }
        }
        Optional<Question> answer = Optional.ofNullable(temp_answer);
        return answer;

    }
    public List<Question> findAll() { //renvoie content_list
        List<Question> answer = new ArrayList<Question>();
        List<Question> temp_content = content_list.getContent();
        Iterator iterator = temp_content.iterator();
        while(iterator.hasNext()){
            Question temp = (Question) iterator.next();
            answer.add(temp);
        }
        return answer;
    }
    public List<Question> findByTag(String tag){ //renvoie les questions dans content_list qui contiennent tag dans leur liste de tags
        List<Question> answer = new ArrayList<Question>();
        List<Question> temp_content = content_list.getContent();
        Iterator iterator = temp_content.iterator();
        while(iterator.hasNext()){
            Question temp = (Question) iterator.next();
            if(temp.getTags().contains(tag) == true){
                answer.add(temp);
            }
        }
        return answer;
    }
    public List<Question> findByAuthor(String author){ //renvoie les questions dans content_list qui ont pour auteur author
        List<Question> answer = new ArrayList<Question>();
        List<Question> temp_content = content_list.getContent();
        Iterator iterator = temp_content.iterator();
        while(iterator.hasNext()){
            Question temp = (Question) iterator.next();
            if(temp.getAuthor().equals(author)){
                answer.add(temp);
            }
        }
        return answer;
    }
    public InMemoryQuestionRepository(){
        this.content_list = new QuestionDTO();
    }
}
