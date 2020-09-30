package ch.heigvd.amt.projet1.model;


public class Message {
    // Variables
    String author;
    String content;

    // Constructor
    public Message (String author, String content){
        this.author = author;
        this.content = content;
        //TODO: add to db here
    }

    // Getter
    public String getAuthor(){
        return this.author;
    }

    public String getContent(){
        return this.content;
    }

    // Changing the addToDB function

    // Changing the toString function
    @Override
    public String toString() 
    { 
        return("Author = " + this.author +"\n"+
                "Content = \n" + this.content); 
    } 
}