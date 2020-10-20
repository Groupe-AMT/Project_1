<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="fragments/Header.jsp"%>
<c:set var ="pageTitle" value="register stackFlow"/>
<style>
    .ID{
        max-width: 800px;
        margin: auto;
    }

    button {
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 100%;
    }

    input[type=text], input[type=password] {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        box-sizing: border-box;
    }

    button:hover {
        opacity: 0.8;
    }
</style>
<div id="login" class="ID" style="margin-top: 50px">
    <c:if test="${errors!=null}">
        <ul>
            <c:forEach items="${errors}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>
    </c:if>

    <form id ="register-form" action="register.do" method="post">
        <label for="username"><b>Nom d utilisateur</b></label>
        <input type="text" placeholder="Entrez votre nom d'utilisateur" name="username" required>
        <label for="firstname"><b>Prenom</b></label>
        <input type="text" placeholder="Entrez votre prenom" name="firstname" required>
        <label for="lastname"><b>Nom</b></label>
        <input type="text" placeholder="Entrez votre nom" name="lastname" required>
        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Entrez votre email" name="email" required>
        <label for="password"><b>Mot de passe (1 minuscule, 1 majuscule, 1 chiffre, 8 caractères minimum)</b></label>
        <input type="password" placeholder="Entrez votre mot de passe" name="password" required>

        <button type="submit">Enregistrement</button>
    </form>
    <form method="get" action="/Project_1/login">
        <button type="submit">Se connecter</button>
    </form>
</div>
<%@include file="fragments/Footer.jsp"%>