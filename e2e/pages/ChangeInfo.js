const I = actor();

module.exports = {

  fields: {
    username: "username",
    firstname: "firstname",
    lastname: "lastname",
    mail: "email"
  },
  submitButton: "Changez vos informations",
  changeMessage: "Changements effectués",

  // Methods
  sendForm(usr, fname, lname, mail){
    I.fillField(this.fields.username, usr);
    I.fillField(this.fields.firstname, fname);
    I.fillField(this.fields.lastname, lname);
    I.fillField(this.fields.mail, mail);
    I.click(this.submitButton);
  }
}
