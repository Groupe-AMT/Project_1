
<%@include file="WEB-INF/views/fragments/Header.jsp"%>
<style>
    .content-middle{
        max-width: 800px;
        margin: auto;
    }
</style>

<!-- Page Content -->
<div class="content-middle">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h1 class="mt-5" >Acceuil</h1>
        </div>
    </div>
</div>
<div class="content-middle">
    <div class="intro">
        <h2>Introduction</h2>
        <h3>Bienvenue sur le site Buffer Overflow. Site d&#233;di&#233; aux questions informatique de tous genres.</h3>
    </div>

    <div class="annonce">
        <h2>&#192; venir </h2>
        <p>Voici la liste des futures features qui seront disponibles sur le site dans les prochaines semaines.</p>
        <ul>
            <li>Connexion avec compte utilisateur</li>
            <li>Visualisations des questions par sujet</li>
            <li>Votez pour la question ayant la plus pertinante</li>
            <li>... et bien d'autre</li>
        </ul>
    </div>
</div>

<%@include file="WEB-INF/views/fragments/Footer.jsp"%>

