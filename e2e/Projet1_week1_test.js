Feature('Projet1_week1');

let root = "http://localhost:8080/Projet_1/";

let usr = "laure";
let pass = "dinateur";

let usr_reg = "laure";
let pass_reg = "dinateur";


Scenario('Test Registration', (I) => {
    I.amOnPage(root);
    I.see("Register right Here!");
    I.click("Register right Here!");
    I.fillField("uname", usr_reg);
    I.fillField("psw", pass_reg);
    I.click("Register");
    I.see("this login is already used");
});

Scenario('Test Browse without being logged', (I) => {
    I.amOnPage(root);
    I.see("Register right Here!");
    I.click("Acceuil");
    I.see("Register right Here!");
    I.click("Login");
    I.see("Register right Here!");
    I.click("Questions");
    I.see("Register right Here!");
    I.click("Profil");
    I.see("Register right Here!");
});

Scenario('Test Login', (I) => {
    I.amOnPage(root);
    I.fillField("uname", usr);
    I.fillField("psw", pass);
    I.pressKey('Enter');
    I.click("Acceuil");
    I.wait(2);
});

Scenario('Test Browse being logged', (I) => {
    I.amOnPage(root);

    I.fillField("uname", usr);
    I.fillField("psw", pass);
    I.pressKey('Enter');
    I.see("Acceuil");
    I.click("Acceuil");
    I.see("Acceuil");
    I.click("Login");
    I.see("Logout");
    I.click("Questions");
    I.see("Question");
    I.click("Profil");
    I.see("John Doe");
});

Scenario('Test login logout', (I) => {
    I.amOnPage(root);

    I.fillField("uname", usr);
    I.fillField("psw", pass);
    I.pressKey('Enter');
    I.see("Acceuil");
    I.click("Login");
    I.click("logout");
    I.see("Username");
});
