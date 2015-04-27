<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="utf-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit VRM</title>
</head>

<body>


<div>
    <div>
        <div>
            <div>
                <h1>Edit VRM</h1>
            </div>
          			
            <c:if  test="${!empty vrmList}">
                <h3>VRMS to edit</h3>
                <table>
                 
                    <tbody>
                    <c:forEach items="${vrmList}" var="vrm">
                        <tr>
                            <td>${vrm.vrmName}</td>
                            <td>xyz</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            
        </div>
    </div>
</div>

</body>
</html>
