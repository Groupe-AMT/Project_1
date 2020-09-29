


class Message() {
    // Variables
    String author;
    String content;

    // Constructor
    public Message (String author, String content){
        this.author = author;
        this.content = content;

        addToDB();
    }

    // Getter
    public String getAuthor(){
        return this.author;
    }

    public String getContent(){
        return this.content;
    }

    // Changing the addToDB function
    @Override
    public addToDB(){
        /* 
        This method is use to add to the database the created VotableMessage 
        with all its information.
        */

    }

    // Changing the toString function
    @Override
    public String toString() 
    { 
        return("Author = " + this.author +"\n"+
                "Content = \n" + this.content); 
    } 
}