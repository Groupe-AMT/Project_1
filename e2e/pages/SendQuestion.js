const I = actor();

module.exports = {

  // insert your locators and methods here
    // Locators
  fields: {
    subject: "subject_form",
    tags: "tags_form",
    content: "content_form"
  },
  submitButton: "send_form",

  // Methods
  sendForm(subject, tags, content){
    I.fillField(this.fields.subject, subject);
    I.fillField(this.fields.tags, tags);
    I.fillField(this.fields.content, content);
    I.click(this.submitButton);
  }
}
