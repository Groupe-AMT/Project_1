Feature('Projet1_week3');

let now = new Date();

let root = "http://localhost:9000/Projet_1/";

let usr = "laure";
let pass = "dinateur";
let fname = "Pierrot";
let lname = "Dupont";
let mail = "pierrotdupont@mail.ch"

let usr_reg = "laure";
let pass_reg = "dinateur";

let test_message={
    subject:"TEST",
    tags:"Testing / CodeceptJS",
    content:"Here is the content of the test"
}

// Tests d'enregistrement
let registeringU = "bouboule";
let registeringP = "mange";
Scenario('Registration - new account', (I, Register) => {
    I.amOnPage(root);
    I.see("Register right Here!");
    I.click("Register right Here!");
    Register.sendForm(usr+now.getMilliseconds().toString(), pass, fname, lname, now.getMilliseconds().toString()+mail);
    I.see("Logout");
});

Scenario('Registration - existing account', (I, Register) => {
    I.amOnPage(root);
    I.see("Register right Here!");
    I.click("Register right Here!");
    Register.sendForm("laure", "dinateur", fname, lname, now.getMilliseconds().toString()+mail);
    I.see("Username is already used");
});

// Tests de login
let workingU = "laure";
let workingP = "dinateur";
Scenario('Login - good credentials', (I, Login) => {
    I.amOnPage(root);
    Login.sendForm(workingU, WorkingP);
    I.see("Accueil");
});

let notWorkingU = "pasbon";
let workingP = "dinateur";
Scenario('Login - wrong User', (I, Login) => {
    I.amOnPage(root);
    Login.sendForm(workingU, WorkingP);
    I.see("Error : user not found register");
});

let workingU = "laure";
let notWorkingP = "dinateureeeeeeeeeeee";
Scenario('Login - wrong Pass', (I, Login) => {
    I.amOnPage(root);
    Login.sendForm(workingU, WorkingP);
    I.see("Verification of credentials failed");
});

Scenario('Login Logout', (I, Login) => {
    I.amOnPage(root);

    Login.sendForm(workingU, workingP);
    
    I.see("Acceuil");
    I.click("Logout");
    I.click("logout");
    I.see("Username");
});

// Tests de parcours des pages

Scenario('Browse - NOT logged', (I) => {
    I.amOnPage(root);
    I.see("Register right Here!");
    I.click("Accueil");
    I.see("Register right Here!");
    I.click("Login");
    I.see("Register right Here!");
    I.click("Questions");
    I.see("Send");
    I.click("Profil");
    I.see("Register right Here!");
});

Scenario('Browse - logged', (I, Login) => {
    I.amOnPage(root);

    Login.sendForm(usr, pass);
    
    I.see("Acceuil");
    I.click("Acceuil");
    I.see("Acceuil");
    I.click("Logout");
    I.see("Logout");
    I.click("Questions");
    I.see("Question");
    I.click("Profil");
    I.see("John Doe");
});

// Tests de Questions

Scenario('Question - logging and sending it', (I, Login, SendQuestion) => {
    I.amOnPage(root);

    Login.sendForm(usr, pass);
    
    I.click("Questions");

    SendQuestion.sendForm(test_message.subject, test_message.tags, test_message.content);

    I.see(test_message.subject);
});

Scenario('Question - find it without being logged', (I) => {
    I.amOnPage(root);
    
    I.click("Questions");

    I.see(test_message.subject);
});

Scenario('Question - logging and seeing it', (I, Login) => {
    I.amOnPage(root);

    Login.sendForm(usr, pass);
    
    I.click("Questions");
    
    I.see(test_message.subject);
});