
package ch.heigvd.amt.projet1.model;


import java.util.ArrayList;
import java.util.List;

class Answer extends VotableMessage{
    // Variables 
    List<Comment> Comments = new ArrayList<Comment>();

    // GetterX
    public List<Comment> getComments(){
        return this.Comments;
    }

    // Function for adding a comment to the answer
    public List<Comment> addComment(Comment com){
        /*
        This function aims to add a comment to the comments list of the answer
        */
        this.Comments.add(com);
        return this.Comments;
    }
    public Answer(String author, String content){
        super(author,content);
    }

}