const I = actor();

module.exports = {

  fields: {
    pass: "pass",
    newpass: "newpass"
  },
  submitButton: "Changez votre mot de passe (1 minuscule, 1 majuscule, 1 chiffre, 8 caractères minimum)",
  changeMessage: "Changements effectués",

  // Methods
  sendForm(password, new_password){
    I.fillField(this.fields.pass, password);
    I.fillField(this.fields.newpass, new_password);
    I.click(this.submitButton)
  }
}

