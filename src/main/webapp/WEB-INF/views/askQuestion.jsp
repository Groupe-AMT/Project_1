<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@include file="fragments/Header.jsp"%>

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
<%@include file="fragments/Footer.jsp"%>