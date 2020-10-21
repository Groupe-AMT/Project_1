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
    <div class="row">
        <div class="col-lg-12 text-center">
            <h2>Question</h2>

            <div class="w3-bar w3-light-grey" style="width:90%; margin:auto;">
                <form action="${pageContext.request.contextPath}/search">
                         <input type="text" class="w3-bar-item w3-input w3-rightbar" style="width:25%;" placeholder="tags: Java Ordinateur ...">
                         <button class="w3-bar-item w3-button w3-rightbar" style="width:25%;" type="submit" >Chercher</button>
                </form>
                <form action="${pageContext.request.contextPath}/ask">
                    <button class="w3-bar-item w3-button w3-border-right" style="width:50%;" type="submit" >Poser une question</button>
                </form>
            </div>

            <c:forEach items="${Qs}" var="Q">
                <form action="${pageContext.request.contextPath}/question">
                    <input type="text" name="id" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                    <button class="w3-bar w3-bottombar w3-button" type="submit" style="width:90%; height:70px; background-color: Gray; overflow:hidden; color:white;">
                      <div class="w3-bar-item" style="width:100%; padding:0px;">
                        <span class="w3-large"><c:out value="${Q.getSubject()}"/></span><br>
                        <span>par <c:out value="${Q.getAuthor()}"/> le <c:out value="${Q.getDate()}"/></span>
                      </div>
                    </button>
                </form>
            </c:forEach>
        </div>

        <div class="w3-center" style="margin:auto;">
            <form action="${pageContext.request.contextPath}/questions">
                 <select class="w3-select" name="currentPage">
                  <option value="" disabled selected><c:out value="${currentPage}"/></option>
                  <c:forEach items="${Pages}" var="P">
                  <option value="<c:out value="${P}"/>"><c:out value="${P}"/></option>
                  </c:forEach>
                </select>
                <button type="submit">Changer de page</button>
            </form>
        </div>

    </div>
</div>
<%@include file="fragments/Footer.jsp"%>