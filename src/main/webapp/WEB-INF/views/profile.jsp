<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@include file="fragments/Header.jsp"%>

<!-- Page Content -->

<div class="w3-container w3-center" style="overflow: hidden;">
    <div class="w3-container">
        <div class="w3-card-4" style="margin-top:50px; ">
          <div class="w3-container w3-center">
            <h1><c:out value="${name}"/></h1>
            <h6>prénom: <c:out value="${firstname}"/></h6>
            <h6>nom: <c:out value="${lastname}"/></h6>
            <h6>email: <c:out value="${email}"/></h6>
          </div>
        </div>
    </div>
    <div class="w3-container" style="margin-top: 20px;">
         <div class="w3-bar-block w3-dark-grey w3-center" style="width:50%;margin:auto;">
          <a href="/Project_1/updateProfile" class="w3-bar-item w3-button w3-mobile" style="width:100%;">Changer ses informations personnels</a>
          <a href="/Project_1/updatePass" class="w3-bar-item w3-button w3-mobile" style="width:100%;">Changer son mot de passe</a>
        </div>
    </div>
    <div class="w3-container" style="margin-top: 20px;">
        <div class="w3-container w3-light-grey">
          <h2>Statistiques</h2>
        </div>
        <div class="w3-container w3-light-grey">
            <ul class="w3-ul">
                <li>Nombre d utilisateurs : <c:out value="${stats.getNbUsers()}"/></li>
                <li>Nombre de questions : <c:out value="${stats.getNbQuestions()}"/></li>
                <li>Nombre de réponses : <c:out value="${stats.getNbAnswers()}"/></li>
                <li>Nombre de commentaires : <c:out value="${stats.getNbComments()}"/></li>
            </ul>
        </div>
        <div class="w3-container w3-light-grey" style="margin-top: 20px;">
          <h2>Vos statistiques</h2>
        </div>
        <div class="w3-container w3-light-grey">
            <ul class="w3-ul">
                <li>Votre nombre de questions : <c:out value="${stats.getNbSelfQuestions()}"/></li>
                <li>Votre nombre de réponses : <c:out value="${stats.getNbSelfAnswers()}"/></li>
                <li>Votre nombre de commentaires : <c:out value="${stats.getNbSelfComments()}"/></li>
            </ul>
        </div>
    </div>
</div>

<%@include file="fragments/Footer.jsp"%>