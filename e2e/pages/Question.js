const I = actor();
module.exports = {
    Upvote_button : "↑",
    Downvote_button : "↓",
    Answer_field_name : 'answer',
    Answer_submit_button : "Répondre",
    Answer_field_placeholder : 'Votre réponse',
    Comment_opening_button : "Commentaires",
    Comment_field_name : 'answer',
    Comment_field_placeholder : 'Commenter',
    Comment_submit_button : "Commenter",
    sendAnswer(answer){
        I.fillField(locate('textarea').withAttr({ placeholder: this.Answer_field_placeholder }), answer);
        I.click(this.Answer_submit_button);
    },
    sendComment(comment){
        I.fillField(locate('input').withAttr({ placeholder: this.Comment_field_placeholder }).last(), comment); 
        I.click(locate('button').withText('Commenter').last());
    },
    sendComment_hidden(comment){ //permet de commenter une question
        I.click(this.Comment_opening_button);
        I.fillField(locate('input').withAttr({ placeholder: this.Comment_field_placeholder }).first(), comment); 
        I.click(locate('button').withText('Commenter').first());
    }

}