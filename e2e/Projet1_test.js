
Feature('Project_1');

Login = require('./pages/Login');
Register = require('./pages/Register');
Profil = require('./pages/Profil');
ChangePassword = require('./pages/ChangePassword');
ChangeInfo = require('./pages/ChangeInfo');
Question = require('./pages/Question');
SendQuestion = require('./pages/SendQuestion');
Questions = require('./pages/Questions');

let now = new Date();

let root = ":9080/Project_1/";

let usr = "laure"+now.getMilliseconds().toString();
let pass = "Dinateur1";
let pass2 = "Dinateur2";
let fname = "Pierrot";
let fname2 = "Pierre";
let lname = "Dupont";
let mail = "pierrotdupont@mail.ch"

let usr_reg = "laure";
let pass_reg = "Dinateur1";

let test_message={
    subject:"TEST",
    tags:"Testing",
    content:"Here is the content of the test",
    answer:"test_answer",
    comment1:"test_comment1",
    comment2:"test_comment2"
}

// Tests d'enregistrement
let registeringU = "laure";
let registeringP = "dinateur";
Scenario('Registration - new account', (I, Register) => {
    I.amOnPage(root);
    I.see(Login.registerButton);
    I.click(Login.registerButton);
    Register.sendForm(usr, pass, fname, lname, mail);
    I.see("Question");
});

Scenario('Registration - existing account', (I, Register) => {
    I.amOnPage(root);
    I.see(Login.registerButton);
    I.click(Login.registerButton);
    Register.sendForm(usr, pass, fname, lname, now.getMilliseconds().toString()+mail);
    I.see("Nom d'utilisateur déjà utilisé");
});

// Tests de login
let workingU = usr;
let workingP = pass;
Scenario('Login - good credentials', (I, Login) => {
    I.amOnPage(root);
    Login.sendForm(workingU, workingP);
    I.see("Question");
});

let notWorkingU = "pasbon";
Scenario('Login - wrong User', (I, Login) => {
    I.amOnPage(root);
    Login.sendForm(notWorkingU, workingP);
    I.see(Login.wrongUser_msg);
});

let notWorkingP = "dinateureeeeeeeeeeee";
Scenario('Login - wrong Pass', (I, Login) => {
    I.amOnPage(root);
    Login.sendForm(workingU, notWorkingP);
    I.see(Login.wrongUser_msg);
});

Scenario('Login Logout', (I, Login) => {
    I.amOnPage(root);

    Login.sendForm(workingU, workingP);
    
    I.see("Logout");
    I.click("Logout");
    I.see("Connexion");
});

// Tests de Questions

Scenario('Question - logging and sending it', (I, Login, SendQuestion) => {
    I.amOnPage(root);

    Login.sendForm(usr, pass);
    
    I.click("Questions");

    I.click(Questions.ask_button);

    SendQuestion.sendForm(test_message.subject, test_message.tags, test_message.content);

    I.see(test_message.subject);
});

/*
Scenario('Question - find it without being logged', (I) => {
    I.amOnPage(root);
    
    I.click("Questions");

    I.see(test_message.subject);
});
*/

Scenario('Question - logging and seeing it', (I, Login) => {
    I.amOnPage(root);

    Login.sendForm(usr, pass);
    
    I.click("Questions");
    
    I.see(test_message.subject);
});
//Test d'interaction avec les questions
Scenario('Question - upvote', (I, Login, Question) => {
    I.amOnPage(root);

    Login.sendForm(usr, pass);
    
    I.click("Questions");
    
    I.see(test_message.subject);

    I.click(test_message.subject);

    I.see("vote :0");

    I.click(Question.Upvote_button);

    I.see("vote :1");
});

Scenario('Question - downvote', (I, Login, Question) =>{
    I.amOnPage(root);

    Login.sendForm(usr, pass);
    
    I.click("Questions");
    
    I.see(test_message.subject);

    I.click(test_message.subject);

    I.see("vote :1");

    I.click(Question.Downvote_button);

    I.see("vote :-1");

});

Scenario('Question - answering', (I, Login, Question) =>{
    I.amOnPage(root);

    Login.sendForm(usr, pass);
    
    I.click("Questions");
    
    I.see(test_message.subject);

    I.click(test_message.subject);

    Question.sendAnswer(test_message.answer);

    I.see(test_message.answer);

});

Scenario('Question - commenting question', (I, Login, Question) =>{
    I.amOnPage(root);

    Login.sendForm(usr, pass);
    
    I.click("Questions");
    
    I.see(test_message.subject);

    I.click(test_message.subject);

    Question.sendComment_hidden(test_message.comment1);

    I.click(Question.Comment_opening_button);

    I.see(test_message.comment1);

});

Scenario('Question - commenting answer', (I, Login, Question) =>{
    I.amOnPage(root);

    Login.sendForm(usr, pass);
    
    I.click("Questions");
    
    I.see(test_message.subject);

    I.click(test_message.subject);

    Question.sendComment(test_message.comment2);

    I.see(test_message.comment2);

});
//Tests de changement d'information d'utilisateur
Scenario('Profil - change informations', (I, Login, Profil, ChangeInfo) =>{
    I.amOnPage(root);

    Login.sendForm(usr, pass);
    
    I.click("Profil");

    I.click(Profil.changeInfo_link);

    ChangeInfo.sendForm(usr,fname2, lname, mail);

    I.see(ChangeInfo.changeMessage);

});

Scenario('Profil - change password', (I, Login, Profil, ChangePassword) =>{
    I.amOnPage(root);

    Login.sendForm(usr, pass);
    
    I.click("Profil");

    I.click(Profil.changePassword_link);

    ChangePassword.sendForm(pass, pass2);

    I.see(ChangePassword.changeMessage);

});
//Test de recherche de question par tag
Scenario('Questions - search', (I, Login, Questions) =>{
    I.amOnPage(root);

    Login.sendForm(usr, pass2);
    
    I.click("Questions");
    
    I.see(test_message.subject);

    Questions.search_tags(test_message.tags);

    I.see(test_message.subject);

});

// Tests de parcours des pages

Scenario('Browse - NOT logged', (I) => {
    I.amOnPage(root);

    I.click("Questions");
    I.see("Question");
    I.click("TEST");
    I.see("Here is the content of the test");
});

Scenario('Browse - logged', (I, Login) => {
    I.amOnPage(root);

    Login.sendForm(usr, pass2);
    
    I.see("Logout");
    I.see(test_message.subject);
    I.click(test_message.subject);
    I.click("Profil");
    I.see(usr);
    I.click("Logout");
    I.see("Login");
});

//Test de statistiques
Scenario('Profil - statistics', (I, Login) =>{
    I.amOnPage(root);

    Login.sendForm(usr, pass2);
    
    I.click("Profil");

    I.see("Votre nombre de questions : 1");

});
