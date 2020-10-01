package ch.heigvd.amt.projet1.domain;


public class Message implements IEntity{
    // Variables
    protected String author;
    protected String content;

    // Constructor
    public Message (String author, String content){
        this.author = author;
        this.content = content;
    }

    // Getter
    public String getAuthor(){
        return this.author;
    }

    public String getContent(){
        return this.content;
    }


    // Changing the toString function
    @Override
    public String toString() 
    { 
        return("Author = " + this.author +"\n"+
                "Content = \n" + this.content); 
    } 
}