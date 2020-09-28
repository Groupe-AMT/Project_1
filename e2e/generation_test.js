Feature('generation');

Scenario('Browsing some pages', (I) => {
    //Ouvrir le forum
    I.amOnPage("https://www.generations-plus.ch/?q=communaute/forum");
    I.wait(2);
    I.see('Liste des forums');
    //Lire une question
    I.click('//div[@class=\'cols-0 views-row\']/div[1]/div[1]/a');
    I.wait(1);
    //Lire un profil
    I.click('//div[@class=\'user-picture\']/a');
    I.wait(1);
    //Changer d'onglet
    I.click('Communauté');
    I.wait(1);
    //Aller sur la page de connection
    I.click("S'inscrire");
    I.wait(1);
    I.click("Je m'inscris gratuitement");
    I.wait(1);
});

let mail = 'test@gmail.com';
let pass = 'coucou';

Scenario('Attempt to connect', (I) => {
    I.amOnPage('https://www.generations-plus.ch/?q=user/login&destination=user/register/free');
    I.wait(2);
    I.fillField('//input[@id=\'edit-mail\']', mail);
    I.fillField('//input[@id=\'edit-pass\']', pass);
    I.click('Se connecter');
    I.see('incorrect');
    I.wait(2);
});