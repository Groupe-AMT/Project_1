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

                <div class="QuestionsList">

                        <div class="QuestionDiv">
                            <td>Author: <c:out value="${Q.getAuthor()}"/> </td><td>Subject: <c:out value="${Q.getSubject()}"/></td>
                            <td>Content :<c:out value="${Q.getContent()}"/> </td>
                            <!-- vote -->
                            <td>vote :<c:out value="${Vs[0]}"/> </td>
                            <!-- vote up -->
                            <form action="${pageContext.request.contextPath}/question.do"method="post">
                            <button type="submit" >↑</button>
                                <input type="text" name="vid" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                                <input type="text" name="id" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                                <input type="text" name="type" hidden="hidden" value="question"/>
                                <input type="text" name="vote" hidden="hidden" value="true"/>
                            </form>
                            <!-- vote down -->
                            <form action="${pageContext.request.contextPath}/question.do"method="post">
                                <button type="submit" >↓</button>
                                <input type="text" name="vid" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                                <input type="text" name="id" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                                <input type="text" name="type" hidden="hidden" value="question"/>
                                <input type="text" name="vote" hidden="hidden" value="false"/>
                            </form>
                            <!-- comment -->
                            <c:forEach items="${Cs[0]}" var="C">
                                <div class="comment">
                                <td>Author: <c:out value="${C.getAuthor()}"/> </td><td>Content :<c:out value="${C.getContent()}"/> </td>
                                </div>
                            </c:forEach>
                            <!-- comment question form-->
                            <form action="${pageContext.request.contextPath}/question.do"method="post">
                                <input type="text" class="content_input" placeholder="Commenter" name="comment" required>
                                <button type="submit" >Commenter</button>
                                <input type="text" name="id" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                                <input type="text" name="aid" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                                <input type="text" name="type" hidden="hidden" value="question"/>
                            </form>
                        </div>
                    <h2>answer</h2>

                    <c:forEach items="${As}" var="A" varStatus="i" >  <div class="AnswerDiv">
                        <td>Author: <c:out value="${A.getAuthor()}"/> </td><td>Subject: <c:out value="${A.getContent()}"/></td>
                        <td>vote :<c:out value="${Vs[i.count]}"/> </td>
                        <!-- vote up -->
                        <form action="${pageContext.request.contextPath}/question.do"method="post">
                            <button type="submit" >↑</button>
                            <input type="text" name="vid" hidden="hidden" value="<c:out value="${A.getId().asString()}"/>">
                            <input type="text" name="type" hidden="hidden" value="answer"/>
                            <input type="text" name="id" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                            <input type="text" name="vote" hidden="hidden" value="true"/>
                        </form>
                        <!-- vote down -->
                        <form action="${pageContext.request.contextPath}/question.do"method="post">
                            <button type="submit" >↓</button>
                            <input type="text" name="vid" hidden="hidden" value="<c:out value="${A.getId().asString()}"/>">
                            <input type="text" name="type" hidden="hidden" value="answer"/>
                            <input type="text" name="id" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                            <input type="text" name="vote" hidden="hidden" value="false"/>
                        </form>
                        <!-- comment for every answer-->
                        <c:forEach items="${Cs[i.count]}" var="C">
                            <div class="comment">
                                <td>Author: <c:out value="${C.getAuthor()}"/> </td><td>Content :<c:out value="${C.getContent()}"/> </td>
                            </div>

                        </c:forEach>
                        <!-- comment anwer form-->
                        <form action="${pageContext.request.contextPath}/question.do"method="post">
                            <input type="text" class="content_input" placeholder="Commenter" name="comment" required>
                            <button type="submit" >Commenter</button>
                            <input type="text" name="aid" hidden="hidden" value="<c:out value="${A.getId().asString()}"/>">
                            <input type="text" name="id" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                            <input type="text" name="type" hidden="hidden" value="answer"/>
                        </form>
                    </div>
                    </c:forEach>
                    <!-- answer form-->
                    <form action="${pageContext.request.contextPath}/question.do"method="post">
                        <input type="textarea" class="content_input" placeholder="Répondre" name="answer" required>
                        <button type="submit" >Répondre</button>
                        <input type="text" name="id" hidden="hidden" value="<c:out value="${Q.getId().asString()}"/>">
                    </form>
                </div>
        </div>
    </div>
</div>
<%@include file="fragments/Footer.jsp"%>
