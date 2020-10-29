# Project_1

_Jérôme Arn / Canipel Vincent / Clément Semblat / Quentin Saucy_

# Pages du site et fonctionnalités

Dans cette partie, nous allons décrire le fonctionnement des différentes pages de notre serveur d'application:
* La page /login permet de se connecter en rentrant ses identifiants dans le formulaire. Un servlet prendra en compte votre demande via un servlet, une facade et un repository. Ainsi, le serveur d'application vous ouvrira une session.
* La page /register permet de s'enregistrer sur le serveur d'application via un formulaire fait pour. Ainsi, les informations seront conservées dans la base de donnée.
* La page /profile permet de se renseigner sur les différentes statistiques sur le site internet et sur les informations personnelles du compte. Les informations personnelles du compte sont récupérés via le repository Person. Nous avons décidé, pour obtenir les statistiques, d'utiliser les repositories de Question, Answer et Comment afin de bien séparer les tables entre elles et ne pas faire un repository sans table propre du nom de Statistique.
    * A partir de la page profile, nous pouvons atteindre la page de changement de mot de passe, cette page permet de changer de mot de passe en vérifiant le mot de passe actuel et en mettant un place un nouveau mot de passe. Le changement est ensuite statué dans la base de donnée pour l'utilisateur connecté.
    * A partir de la page profile, nous pouvons atteindre la page de changement des informations personnels, cette page permet de modifier les informations personnels sur la base de donnée dans la table Person pour l'utilisateur connecté.
* La page /questions permet de voir les questions dans l'ordre de la question la plus récente à la plus ancienne avec un système de pagination pour éviter de surcharger le serveur.
* En clicquant via la pages /questions sur une question, on atteint le contenue, les réponses et les commentaires de la question choisis. On peut commenter, répondre et voter dans cetter partie de l'application web.
* En cliquant sur "Poser une question" via la liste des questions, on atteint alors la page /ask pour poser une question pour laquelle on peut définir un sujet, un contenu et des tags pour pouvoir être retrouvé plus facilement avec la barre de recherche de tag de la liste des questions.

# Fonctionnement et structure de l'application web

L'application web cherche à séparer les parties de manière distinctes et indépendantes. Ainsi, on retrouve:

* un **tier de présentation** avec le code html et css dans des fichiers jsp qui permettent de mettre en place les vues sur le serveur d'application avec les servlets qui envoient des informations aux fichiers jsp et qui traitent les requêtes des utilisateurs.
* un **tier business** avec les managements facades qui implémentent la logique métier de notre application avec par exemple la connexion d'un utilisateur et la création de sa session.
* un **tier d'intégration** avec les différentes classes repositories qui implémentent la communication avec la base de donnée par l'utilisation d'une jdbc pour MySQL. La classe service registry s'occupe de fournir à la logique métier via de l'injection de dépendance, des accès au tiers d'intégration et donc à la base de donnée.
* un **tier de ressource** avec la base de donnée sur un serveur MySQL.

La base de donnée est un serveur MySQL contenue dans un container docker est ayant un lien avec le container OpenLiberty. En cas de première utilisation, la base de donnée MySQL est créée avec les tables pour pouvoir se rapprocher au maximum d'un lancement clef en main du serveur d'application.

# Explication d'un cas d'utilisation de l'application

Dans cette partie, nous verrons un cas d'utilisation classique pouvant avoir lieu sur notre application web.

Premièrement, nous arrivons sur le serveur en utilisant l'adresse _ip:port/Project_1/_ , la page nous demande une connection, cependant nous pouvons utiliser la barre de navigation pour pouvoir visualiser les questions et ensuite les questions et commentaires propres à une question.

![nav-bar](mdImages/123.png?raw=true "Navigation hors ligne")

Maintenant, nous allons voir comment poster soit même un message. On commence par retourner sur login puis on click pour s'enregistrer. On rentre les informations et nous envoyons le formulaire.

![reg](mdImages/4.png?raw=true "Formulaire d'enregistrement")

Les champs du formulaire seront alors envoyé à un servlet **RegisterPageEndpoint** qui va créer une commande et l'envoyer à **l'IdentityManagementFacade** dans le tier logique métier. La facade va s'occuper de vérifier que le mot de passe est assez compliqué et qu'aucun compte n'ayant le même nom d'utilisateur n'existe avant de passer les informations au tier d'intégration via **PersonRepository**. Le tier d'intégration s'occupera de lister notre nouvel utilisateur dans la **base de donnée** soit le tier de ressource.

Le fait de s'enregistrer, nous connectera directement sur l'application Web. La connexion passe par la création d'une session sur le serveur d'application. En cas d'utilisateur non connecté voulant utiliser des fonctions recquiérant une connexion celui ci sera redirigé par un filtre sur la page de connexion.

Maintenant, nous cliquons sur _Poser une question_ et nous remplissons les champs afin de poster une nouvelle question.

![ask](mdImages/5.png?raw=true "Envoyer une question sur le serveur")

Une fois la question envoyé nous serons redirigé vers la liste des questions avec notre question en première position (car la plus récente des questions). Que c'est-il passé ? Le formulaire à été envoyé à la classe servlet **QuestionsServlet** qui va créer la commande via les informations de la requête et l'envoyer à la classe du tier business **QuestionManagementFacade** via la classe **ServiceRegistry** responsable de fournir aux servlets de l'application leurs logiques métier. La facade va ensuite vérifier les informations faire un objet de la classe Question avant de l'envoyer au tiers d'intégration via la classe **QuestionRepository** qui va sauvegarder cette question dans la base de donnée (tier de ressource).
