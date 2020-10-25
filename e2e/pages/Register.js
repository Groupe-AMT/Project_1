const I = actor();

module.exports = {

  // insert your locators and methods here

  // Locators
  fields: {
    username: "username",
    password: "Entrez votre mot de passe",
    firstname: "firstname",
    lastname: "lastname",
    mail: "email"
  },
  submitButton: "Enregistrement",

  // Methods
  sendForm(usr, pass, fname, lname, mail){
    I.fillField(this.fields.username, usr);
    I.fillField(this.fields.password, pass);
    I.fillField(this.fields.firstname, fname);
    I.fillField(this.fields.lastname, lname);
    I.fillField(this.fields.mail, mail);
    I.click(this.submitButton)
  }
}
