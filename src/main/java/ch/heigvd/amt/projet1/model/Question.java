package ch.heigvd.amt.projet1.model;


import java.util.ArrayList;
import java.util.List;

public class Question extends VotableMessage implements IEntity{
    // Variables
    String Subject;
    QuestionId id; //contient un UUID qui identifie la question de façon unique (à utiliser comme id dans la DB)
    List<String> Tags = new ArrayList<String>();
    List<Answer> Answers = new ArrayList<Answer>();

    public Question(String author, String content, String Subject, List<String> Tags){
        super(author, content);
        this.author = author;
        this.content = content;
        this.Subject = Subject;
        this.Tags = Tags;
        this.id = new QuestionId();
    }

    // Getter
    public String getSubject(){
        return this.Subject;
    }

    public List<String> getTags(){
        return this.Tags;
    }

    public List<Answer> getAnswers(){
        return this.Answers;
    }

    public QuestionId getId(){return this.id;} //pas de setter pour id car unique et crée à l'instanciation

    // Function for adding an answer
    public List<Answer> addAnswer(Answer answer){
        /*
        This function aims to add an answer to the list of answers of the following question
        It returns the list of answers with the added one.
        */
        this.Answers.add(answer);
        return this.Answers;
    } 

    // Changing the addToDB function


}