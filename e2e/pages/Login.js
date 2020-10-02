const I = actor();

module.exports = {

  // insert your locators and methods here

  // Locators
  fields: {
    usr_name: "username",
    password: "password"
  },
  submitButton: "Login",

  // Methods
  sendForm(usr, pass){
    I.fillField(this.fields.usr_name, usr);
    I.fillField(this.fields.password, pass);
    I.pressKey('Enter');
  }
}
