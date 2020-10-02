<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="fragments/Header.jsp"%>
<c:set var ="pageTitle" value="register stackFlow"/>
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

    <form id ="register-form" action="register.do" method="post">
        <label for="username"><b>Username</b></label>
        <input type="text" placeholder="Enter your Username" name="username" required>
        <label for="firstname"><b>First name</b></label>
        <input type="text" placeholder="Enter your Firstname" name="firstname" required>
        <label for="lastname"><b>Last name</b></label>
        <input type="text" placeholder="Enter your Lastname" name="lastname" required>
        <label for="email"><b>email</b></label>
        <input type="text" placeholder="Enter your email" name="email" required>
        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter your Password" name="password" required>

        <button type="submit">Register</button>
    </form>
</div>
<%@include file="fragments/Footer.jsp"%>