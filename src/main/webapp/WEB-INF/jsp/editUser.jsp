<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="utf-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User</title>
</head>

<body>


<div>
    <div>
        <div>
            <div>
                <h1>Create New User</h1>
            </div>
            <form:form method="post" action="saveUserJsp" commandName="user">
                <form:label path="username">User Name (email)</form:label>
                <form:input path="username" />
                <form:label path="password">Passwort</form:label>
                <form:input path="password" />
                <input type="submit" value="Create New User"/>
            </form:form>

			<!--  
            <c:if  test="${!empty peopleList}">
                <h3>People</h3>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${peopleList}" var="person">
                        <tr>
                            <td>${person.lastName}, ${person.firstName}</td>
                            <td><form action="delete/${person.id}" method="post"><input type="submit" value="Delete"/></form></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            -->
        </div>
    </div>
</div>

</body>
</html>
