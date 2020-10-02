package ch.heigvd.amt.projet1.domain;


public class Message implements IEntity{
    // Variables
    protected String author;
    protected String content;
    protected MessageId msg_id;
    public MessageId getId(){return this.msg_id;}
    public Message deepClone(){
        return new Message(this.author, this.content);
    }
    // Constructor
    public Message (String author, String content){
        this.author = author;
        this.content = content;
        this.msg_id = new MessageId();
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