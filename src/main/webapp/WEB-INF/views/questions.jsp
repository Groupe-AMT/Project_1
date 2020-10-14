<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@include file="fragments/Header.jsp"%>
<!-- Style of the page -->
<style>
    div.newQuestion{
        width: 100%;

        min_width: 400px;
        height: 200px;

        margin-top:20px;
        padding: 24px;
        box-shadow: 0 0 50px 0 rgba(0, 0, 0, 0.2), 0 2px 2px 0 rgba(0, 0, 0, 0.25);
    }
    div.QuestionDiv{
        width: 100%;
        height: 40px;

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
                <div class="newQuestion">
                    <form id ="question-form" action="questions.do" method="post">
                        <div class="QuestionDesc">
                            <label for="subject_form"><b>Subject</b></label>
                            <input type="text" placeholder="Enter a subject" name="subject_form" required>

                            <label for="tags_form"><b>Tags</b></label>
                            <input type="text" placeholder="adult/cinema/music" name="tags_form" required>

                            <button type="submit" name="send_form">Send</button><br>
                        </div>
                        <input type="textarea" class="content_input" placeholder="Type your question" name="content_form" required>
                    </form>
                </div>
                <div class="QuestionsList">
                    <c:forEach items="${Qs}" var="Q">
                        <div class="QuestionDiv">
                            <td>Author: <c:out value="${Q.getAuthor()}"/> Subject: <c:out value="${Q.getSubject()}"/></td><br>
                            <td><c:out value="${Q.getContent()}"/></td>
                        </div>
                    </c:forEach>
                </div>
        </div>
    </div>
</div>
<%@include file="fragments/Footer.jsp"%>
