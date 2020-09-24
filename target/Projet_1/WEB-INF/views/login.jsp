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

<div id="login" class="ID" style="margin-top: 300px">
    <c:if test="${errors!=null}">
        <ul>
            <c:forEach items="${errors}" var="error">
                <li>Error : ${error}</li>
            </c:forEach>
        </ul>
    </c:if>

    ${login}
    ${uname}
    <form id ="login-form" action="login.do" method="post">
    <label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="uname" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>

    <button type="submit">Login</button>
    </form>
    <form action="/Projet_1/register"">
        <button type="submit" >Register right Here!</button>
    </form>
</div>

<%@include file="fragments/Footer.jsp"%>