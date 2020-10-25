const I = actor();
module.exports = {
    fields:{
    tags_form : "tags_form"
    },
    search_button : "Chercher",
    ask_button : "Poser une question",

    search_tags(tags){
        I.fillField(this.fields.tags_form, tags);
        I.click(this.search_button);

    }

}
