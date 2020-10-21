<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="fragments/Header.jsp"%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- Style of the page -->
<style>

    div.QuestionDiv{
        width: 100%;
        margin: 5px;

        box-shadow: 0 0 50px 0 rgba(0, 0, 0, 0.2), 0 2px 2px 0 rgba(0, 0, 0, 0.25);
    }
    input[type=textarea]{
        width: 90%;
        height:100px;
        margin: auto;
    }
</style>
<!-- Page Content -->
<div class="container">
    <form action="${pageContext.request.contextPath}/search">
        <input type="text" title="Rechercher"/>
        <button type="submit" >Rechercher</button>
    </form>
    <form action="${pageContext.request.contextPath}/ask">
        <button type="submit" >Poser une question</button>
    </form>
    <div class="row">
        <div class="col-lg-12 text-center">
            <h2>Question</h2>

                <div class="QuestionsList">
                    <c:forEach items="${Qs}" var="Q">
                        <div class="QuestionDiv">
                            <td>Author: <c:out value="${Q.getAuthor()}"/> </td><td>Subject: <c:out value="${Q.getSubject()}"/></td>
                            <form action="${pageContext.request.contextPath}/question?id=<c:out value="${Q.getId().asString()}"/>">
                                <button type="submit" >Voir la question</button>
                                <input type="text" name="id" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                            </form>
                        </div>
                    </c:forEach>
                </div>
        </div>
    </div>
</div>
<%@include file="fragments/Footer.jsp"%>