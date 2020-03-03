<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Moderator Menu</title>
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
<div class="jumbotron">
    <div class="container">
        <h1>Some greetings head</h1>
        <p>Some greetings text</p>
    </div>
</div>
<h2>Create advertisement</h2>
<div>
    <form role="form" method="post" action="moderatormenu">
        <div class="form-group">
            <label for="Tag">Tag</label>
            <input type="text" class="form-control" id="Tag" placeholder="enter tag" name="advertisementTag">
            <p class="help-block">Maximum 50 characters</p>
        </div>
        <div class="form-group">
            <label for="Subject">Subject</label>
            <input type="text" class="form-control" id="Subject" placeholder="enter subject"
                   name="advertisementSubject">
            <p class="help-block">Maximum 50 characters</p>
        </div>
        <div class="form-group">
            <label for="Body">Body</label>
            <input type="text" class="form-control" id="Body" placeholder="enter body" name="advertisementBody">
            <p class="help-block">Maximum 200 characters</p>
        </div>
        <button type="submit" class="btn btn-success" action="moderatormenu">Create</button>
    </form>
</div>

<h1>Not approved advertisements</h1>
<table class="table table-striped">
    <th>Date</th>
    <th>Status</th>
    <th>Tag</th>
    <th>Subject</th>
    <th>Body</th>
    <th>Author</th>
    <c:forEach var="advertisement" items="${advertisementsList}">
        <tr>
            <td>${advertisement.date}</td>
            <td>${advertisement.advertisementStatus}</td>
            <td>${advertisement.tag}</td>
            <td>${advertisement.subject}</td>
            <td>${advertisement.body}</td>
            <td>${advertisement.user.name}</td>
            <td><a href='<c:url value="/approve-or-reject?id=${advertisement.id}&approve=true"/>' class="btn btn-info">Approve</a></td>
            <td><a href='<c:url value="/approve-or-reject?id=${advertisement.id}&approve=false"/>'class="btn btn-danger">Reject</a></td>
        </tr>

    </c:forEach>
</table>

<h1>My advertisements</h1>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Date</th>
        <th>Status</th>
        <th>Tag</th>
        <th>Subject</th>
        <th>Body</th>
    </tr>
    </thead>
    <c:forEach var="advertisement" items="${advertisementList}">
        <tr>
            <td>${advertisement.date}</td>
            <td>${advertisement.advertisementStatus}</td>
            <td>${advertisement.tag}</td>
            <td>${advertisement.subject}</td>
            <td>${advertisement.body}</td>
            <td><a href='<c:url value="/advertisementcontroller?id=${advertisement.id}"/>' class="btn btn-info">Edit</a></td>
            <td><a href='<c:url value="/advertisementdelete?id=${advertisement.id}"/>' class="btn btn-danger">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<footer>
    <p>Contacts admin@board.com</p>
    <p>Copyright board.com, 2020</p>
</footer>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
