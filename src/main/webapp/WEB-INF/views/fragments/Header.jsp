<%--
  Created by IntelliJ IDEA.
  User: jerome
  Date: 21.09.20
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Project_1 AMT</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
        <a class="navbar-brand" href="#">Buffer Overflow</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Accueil
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <c:choose>
                    <c:when test="${currentUser!=null}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout.do">Déconnexion</a>
                    </li>
                    </c:when>
                    <c:otherwise>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/login">Connexion</a>
                </li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/questions">Questions</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/profile">Profil</a>
                </li>
            </ul>
        </div>
    </div>
</nav>