<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@include file="fragments/Header.jsp"%>

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

<!-- Page Content -->

<div class="w3-container w3-center" style="overflow: hidden; margin-top:20px; ">
        <h2>Changement de votre mot de passe</h2>
        <form id ="register-form" action="updatePass.do" method="post">
            <label for="pass"><b>Ancien mot de passe</b></label>
            <input type="password" placeholder="Entrez votre ancien mot de passe" name="pass" required>
            <label for="newpass"><b>Nouveau mot de passe</b></label>
            <input type="password" placeholder="Entrez votre nouveau mot de passe" name="newpass" required>
            <button type="submit">Changez votre mot de passe</button>
        </form>
        <form method="get" action="/Project_1/profile">
            <button type="submit">Retour</button>
        </form>
<br>
<h3>
    <c:if test="${errors!=null}">
        <ul>
            <c:forEach items="${errors}" var="error">
                <li>Error : ${error}</li>
            </c:forEach>
        </ul>
    </c:if>
</h3>
</div>

<%@include file="fragments/Footer.jsp"%>