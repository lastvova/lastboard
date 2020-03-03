<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>Board</title>
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
                <div class="form-group">
                    <input type="text" placeholder="login" class="form-control" name="userLogin">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="password" class="form-control" name="userPassword">
                </div>
                <button type="submit" class="btn btn-success">Login</button>
            </form>
        </div>
    </div>
</div>
<div class="jumbotron">
    <div class="container">
        <h1>Some greetings head</h1>
        <p>Some greetings text</p>
    </div>
</div>

<div class="container">
    <c:forEach var="advertisement" items="${advertisementList}">
        <div class="panel panel-success">
            <div class="panel-heading">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-6">Subject: ${advertisement.subject}</div>
                        <div class="col-md-6">Tag: ${advertisement.tag}</div>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="container-fluid">
                        ${advertisement.body}
                </div>
            </div>
            <div class="panel-footer">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-4">Date: ${advertisement.date}</div>
                        <div class="col-md-4">Author: ${advertisement.user.name}</div>
                        <div class="col-md-4">Author email: ${advertisement.user.email}</div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</div>

<footer>
    <p>Contacts admin@board.com</p>
    <p>Copyright board.com, 2020</p>
</footer>

</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
