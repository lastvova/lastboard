<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Verificate your email</title>
    <title>Login</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/main-page">Board</a>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/alladvertisements">All advertisements</a>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/findadvertisement">Find advertisements</a>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/myprofile">My profile</a>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/registration">Registration</a>
        </div>
        <div class="navbar-collapse collapse">
            <form class="navbar-form navbar-right" action="login">
                <div class="navbar-collapse collapse">
                    <button type="button" class="btn btn-info" onclick="history.back()">Back</button>
                    <a href="logout" class="btn btn-warning" role="button">Logout</a>
                </div>
            </form>
        </div>
    </div>
</div>
<h1>Check your email, and verificate your account it for use this functional</h1>

</body>
</html>
