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
        <h2>Changements des informations personnels</h2>
        <form id ="register-form" action="updateProfile.do" method="post">
            <label for="username"><b>Nom d utilisateur</b></label>
            <input type="text" placeholder="Entrez votre nom d'utilisateur si vous voulez le modifier" name="username" value=<c:out value="${name}"/>>
            <label for="firstname"><b>Prenom</b></label>
            <input type="text" placeholder="Entrez votre prenom si vous voulez le modifier" name="firstname" value=<c:out value="${firstname}"/>>
            <label for="lastname"><b>Nom</b></label>
            <input type="text" placeholder="Entrez votre nom si vous voulez le modifier" name="lastname" value=<c:out value="${lastname}"/>>
            <label for="email"><b>Email</b></label>
            <input type="text" placeholder="Entrez votre email si vous voulez le modifier" name="email" value=<c:out value="${email}"/>>
            <button type="submit">Changez vos informations</button>
        </form>
        <form method="get" action="/Project_1/profile">
            <button type="submit">Retour</button>
        </form>
<br>
<h3>
    <c:if test="${errors!=null}">
        <ul>
            <c:forEach items="${errors}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>
    </c:if>
</h3>
</div>

<%@include file="fragments/Footer.jsp"%>