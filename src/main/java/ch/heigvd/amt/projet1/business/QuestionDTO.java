package ch.heigvd.amt.projet1.business;

import java.util.List;

public class QuestionDTO {
    private String author;
    private String content;
    private String subject;
    private List<String> tags;

    public QuestionDTO(String author, String content, String subject, List<String> tags){
        this.author = author;
        this.content = content;
        this.subject = subject;
        this.tags = tags;
    }
}
