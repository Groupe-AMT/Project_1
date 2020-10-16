package ch.heigvd.amt.projet1.infrastructure.persistence.memory;

import ch.heigvd.amt.projet1.domain.question.IQuestionRepository;
import ch.heigvd.amt.projet1.domain.question.Question;
import ch.heigvd.amt.projet1.domain.question.QuestionId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
@Named("InMemoryQuestionRepository")
public class InMemoryQuestionRepository extends InMemoryRepository<Question,QuestionId> implements IQuestionRepository {

    public List<Question> findByTag(String tag){ //renvoie les questions dans content_list qui contiennent tag dans leur liste de tags
        List<Question> taggedList=findAll().stream()
                .filter(question -> question.getTags().contains(tag))
                .collect(Collectors.toList());
        return taggedList;
    }

    public List<Question> findByAuthor(String author){ //renvoie les questions dans content_list qui ont pour auteur author
        List<Question> authorList=findAll().stream()
                .filter(question -> question.getAuthor().equals(author))
                .collect(Collectors.toList());
        return authorList;
    }
}
