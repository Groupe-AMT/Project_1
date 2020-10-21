<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="fragments/Header.jsp"%>
<c:set var ="pageTitle" value="login stackFlow"/>
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

    <form id ="login-form" action="login.do" method="post">
    <label for="username"><b>Nom d utilisateur</b></label>
    <input type="text" placeholder="Entrez votre nom d'utilisateur" name="username" required>

    <label for="password"><b>Mot de passe</b></label>
    <input type="password" placeholder="Entrez votre mot de passe" name="password" required>

    <button type="submit">Connexion</button>
    </form>
    <form action="${pageContext.request.contextPath}/register">
        <button type="submit" >S enregistrer ici !</button>
    </form>
</div>

<%@include file="fragments/Footer.jsp"%>