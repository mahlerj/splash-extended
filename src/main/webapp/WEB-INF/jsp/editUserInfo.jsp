<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="utf-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Info</title>
</head>

<body>


<div>
    <div>
        <div>
        	<!--  
            <div>
                <h1>User</h1>
                  <h2>${userEntity.userId}</h2> 
                  <h2>${userEntity.username}</h2>
                  <h2>${userEntity.password}</h2>
                  <h2>${userEntity.role}</h2>
            </div>
            <div>
                <h1>User Info</h1>
                  <h2>${userInfoEntity.customerId}</h2> 
                  <h2>${userInfoEntity.personalId}</h2>
                  <h2>${userInfoEntity.company}</h2>
                  <h2>${userInfoEntity.firstName}</h2>
            </div>
            -->
				<h1>Edit your User Info</h1>
				<div>
					<form:form method="post" action="updateUserInfoJsp"
						commandName="userInfoEntity">
						<form:label path="userInfoId">userInfoId</form:label>
						<form:input path="userInfoId" />
						<form:label path="userIdFk">userIdFk</form:label>
						<form:input path="userIdFk" />
						<form:label path="customerId">Customer ID</form:label>
						<form:input path="customerId" />
						
						<input type="submit" value="Update User" />
					</form:form>
				</div>
				<div>
				<spring:url var="vrm_url" value="/vrmJsp/{id}">
				<spring:param name="id" value="${userInfoEntity.userIdFk}"></spring:param>
				</spring:url>
              	<a href="${vrm_url}">edit vrms</a>
              </div>
            
            
        </div>
    </div>
</div>

</body>
</html>
