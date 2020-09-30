package ch.heigvd.amt.projet1.model;


public class VotableMessage extends Message{
    // Variables
    int upVote = 0;
    int downVote = 0;

    // Getter
    public int getupVote(){
        return this.upVote;
    }

    public int getdownVote(){
        return this.downVote;
    }

    public VotableMessage(String author, String content){
        super(author, content);
        }

    // add votes
    public int addupVote(){
        /*
        This method is used to add one upVote
        It returns the upVote number
        */
        this.upVote = this.upVote + 1;
        return this.upVote;
    }

    public int addDownVote(){
        /*
        This method is used to add one downVote
        It returns the downVote number
        */
        this.downVote = this.downVote + 1;
        return this.downVote;
    }

    // Changing the addToDB function


    // Changing the toString function
    @Override
    public String toString()
    { 
        return("Author = " + this.author +"\n"+
                "Up votes = " + String.valueOf(this.upVote) + "\n"+
                "Down votes = " + String.valueOf(this.downVote) + "\n"+
                "Content = \n" + this.content); 
    } 
}