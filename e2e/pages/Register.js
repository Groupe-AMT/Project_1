const I = actor();

module.exports = {

  // insert your locators and methods here

  // Locators
  fields: {
    username: "uname",
    password: "psw",
    firstname: "fname",
    lastname: "lname",
    mail: "mail"
  },
  submitButton: "Register",

  // Methods
  sendForm(usr, pass, fname, lname, mail){
    I.fillField(this.fields.username, usr);
    I.fillField(this.fields.password, pass);
    I.fillField(this.fields.fname, fname);
    I.fillField(this.fields.lname, lname);
    I.fillField(this.fields.mail, mail);
    I.pressKey('Enter');
  }
}