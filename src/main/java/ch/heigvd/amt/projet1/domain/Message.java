/*
package ch.heigvd.amt.projet1.domain;


import ch.heigvd.amt.projet1.domain.person.Person;

public abstract class Message<ENTITY,ID>{
    // Variables
    protected Person author;
    protected String content;
    protected ID msg_id;
    public ID getId(){return this.msg_id;}

    // Constructor
    public Message (Person author, String content){
        this.author = author;
        this.content = content;
        this.msg_id = (ID) new MessageId();
    }

    // Getter
    public Person getAuthor(){
        return this.author.deepClone();
    }

    public String getContent(){
        return this.content;
    }


    // Changing the toString function
    @Override
    public String toString() 
    { 
        return("Author = " + this.author.getUsername() +"\n"+
                "Content = \n" + this.content); 
    }

    protected abstract Object deepClone();
}
*/