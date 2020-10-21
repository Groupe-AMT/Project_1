<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@include file="fragments/Header.jsp"%>

<div class="newQuestion">
     <div class="w3-card-4" style="width:80%; margin:auto; padding-top:50px;">

        <div class="w3-container w3-green">
          <h2>Poser une question</h2>
        </div>

        <form id ="question-form" action="questions.do" method="post">
            <div class="QuestionDesc">
                <label for="subject_form"><b>   Sujet</b></label>
                <input type="text" class="w3-input" placeholder="Entrez un sujet" name="subject_form" required>

                <label for="tags_form"><b>  Tags</b></label>
                <input type="text" class="w3-input" placeholder="adulte cinema musique" name="tags_form" required>

                <input type="textarea" class="content_input" style="width:100%; height:500px; margin:auto;" placeholder="Ecrivez votre question" name="content_form" required>

                <button type="submit" class="w3-input" name="send_form">Envoyez</button><br>
            </div>
        </form>
    </div>
</div>

<%@include file="fragments/Footer.jsp"%>