
package ch.heigvd.amt.projet1.model;


class Answer extends VotableMessage{
    // Variables 
    List<Comment> Comments = new ArrayList<Comment>();

    // Getter
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

    // Changing the addToDB function
    //@Override
    public void addToDB(){
        /* 
        This method is use to add to the database the created VotableMessage 
        with all its information.
        */

    }
}