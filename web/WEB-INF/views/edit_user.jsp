<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
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
<div class="jumbotron"></div>
<div class="container">
    <h2>Edit user</h2>
    <div class="form-group">
        <form role="form" method="post">
            <label for="select1" class="control-label">User role</label>
            <select class="form-control" id="select1" name="userRole">
                <option value="USER">USER</option>
                <option value="MODERATOR">MODERATOR</option>
            </select>
            <br>
            <label for="select2" class="control-label">Account status</label>
            <select class="form-control" id="select2" name="userAccountStatus">
                <option value="NONACTIVE">NONACTIVE</option>
                <option value="ACTIVE">ACTIVE</option>
                <option value="BLOCKED">BLOCKED</option>
            </select>
            <button type="submit" class="btn btn-info">Send</button>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
