<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="fragments/Header.jsp"%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
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
            <h2><a class="w3-button w3-light-grey" href="${pageContext.request.contextPath}/questions">Question</a></h2>

                <div class="Question" style="margin:auto;">
                    <li class="w3-bar w3-bottombar" style="margin:auto; width:90%; background-color: Gray; overflow:hidden; color:white;">
                      <div class="w3-bar-item" style="width:100%; padding:0px;">
                        <span class="w3-large"><c:out value="${Q.getSubject()}"/></span><br>
                        <span>par <c:out value="${Q.getAuthor()}"/> le <c:out value="${Q.getDate()}"/></span>
                        <div class="w3-white" style="margin:auto; margin-top:25px; width:80%;">
                            <p style="color:black;">
                                <c:out value="${Q.getContent()}"/>
                            </p>
                      </div>
                        <button class="w3-button" data-toggle="collapse" href="#collapseQC">Commentaires</button>
                      <div>
                      <div class="collapse w3-left-align" id="collapseQC" style="margin-left:20px; margin-bottom:15px;">
                        <c:forEach items="${Cs[0]}" var="C">
                            <div class="comment w3-">
                            <td>Par: <c:out value="${C.getAuthor()}"/> le <c:out value="${C.getDate()}"/></td><br><td><c:out value="${C.getContent()}"/> </td>
                            </div>
                        </c:forEach>
                        <form action="${pageContext.request.contextPath}/question.do" method="post">
                            <input type="text" class="content_input" placeholder="Commenter" name="answer" required>
                            <button type="submit" >Commenter</button>
                            <input type="text" name="id" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                            <input type="text" name="type" hidden="hidden" value="question">
                        </form>

                      </div>
                    </li>
                </div>
                <div >
                    <form action="${pageContext.request.contextPath}/question.do" method="post" accept-charset="utf-8">
                        <textarea class="content_input" placeholder="Votre réponse" name="answer" style="width:100%; height: 100px;" required></textarea><br>
                        <button class="w3-button" type="submit" style="margin:auto; margin-top:20px; background-color:CornflowerBlue;" >Répondre</button>
                        <input type="textarea" name="id" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                    </form>
                </div>
               <c:forEach items="${As}" var="A" varStatus="i">
               <div class="Reponse" style="margin:auto; padding-top:5px;">
                   <div class="w3-panel w3-leftbar w3-border-blue w3-pale-blue w3-left-align">
                         <div class="w3-bar-item" style="width:100%; padding:0px;">
                           <span>par <c:out value="${A.getAuthor()}"/> le <c:out value="${A.getDate()}"/></span><br>
                               <p style="color:black;">
                                   <c:out value="${A.getContent()}"/>
                               </p>
                        <div>
                            <div class="w3-left-align w3-border-left w3-border-blue" style="margin-left:20px; margin-bottom:15px;">
                                <c:forEach items="${Cs[i.count]}" var="C">
                                    <td>Par: <c:out value="${C.getAuthor()}"/> le <c:out value="${C.getDate()}"/></td><br><td><c:out value="${C.getContent()}"/> </td><br>
                                </c:forEach>
                            </div>
                            <form action="${pageContext.request.contextPath}/question.do" method="post">
                                <input type="text" class="content_input" placeholder="Commenter" name="answer" required>
                                <button type="submit" >Commenter</button>
                                <input type="text" name="ida" hidden="hidden" value="<c:out value="${A.getId().asString()}"/>">
                                <input type="text" name="id" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                                <input type="text" name="type" hidden="hidden" value="answer">
                            </form>
                        </div>
                   </div>
                </div>
               </c:forEach>
        </div>
    </div>
</div>
<%@include file="fragments/Footer.jsp"%>
