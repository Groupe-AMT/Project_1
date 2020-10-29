# Project_1

# Présentation de notre site web:

- Page de connection - nom d'utilisateur et mot de passe.
- Page d'enregistrement de compte - nom d'utilisateur et mot de passe.
- Page d'accueil - liste des questions, bouton de connection et d'inscription, statistiques
- Page de profils - nom d'utilisateur, changement de mot de passe, nombre de réponse, nombre de question, badges, image de profil
- Page de reponse à une question - affichage de la question, affichage des réponses et des mini profils utilisateurs, votes, autres questions (questions aleatoires), commentaires aux questions et réponses

Toutes les pages -> systeme de recherche de tag ou autre ...

# Lancer le serveur d'application

- Se mettre dans le dossier dockerizator,
- Lancer la commande bash build-image.sh
- En cas de modification du fichier .war le supprimer avant de construire l'image.

# E2E
Ici, se trouvent les informations pour lancer les tests CodeceptJS.
### Installation du server de test
Pour installer un serveur de test suivre le tutoriel suivant:
https://codecept.io/quickstart/
### Lancer un test
Pour lancer un test il faut utiliser:
```
npx codeceptjs run <test_file name>
```
### Tests disponibles
* Project1_week1_test.js , un test des fonctionnalitées de la première semaine.
* Project1_week3_test.js , un test des fonctionnalitées de la troisième semaine (ajout du système de questions).

## Arquillian

```sh

```



## Tests unitaires 

## Structure DB