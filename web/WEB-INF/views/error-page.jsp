<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Error</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>
<body>
<div id="header">
    <h1>board.com - advertisements for you</h1>
    <div id="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/main-page">Main page</a></li>
            <li><a href="${pageContext.request.contextPath}/alladvertisements">All advertisements</a></li>
            <li><a href="${pageContext.request.contextPath}/findadvertisement">Find advertisements</a></li>
            <li><a href="${pageContext.request.contextPath}/myprofile">My profile</a></li>
            <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
            <li><a href="${pageContext.request.contextPath}/registration">Registration</a></li>
        </ul>
    </div>
</div>
<h2>Oops, something wrong. Try your operation with correct data. If it was repeated then write to us about this
    error.</h2>
<div id="footer">
    <p>Contacts admin@board.com</p>
    <p>Copyright board.com, 2020</p>
</div>
<button type="button" name="back" onclick="history.back()">back</button>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
