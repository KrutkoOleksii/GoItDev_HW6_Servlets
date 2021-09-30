<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Developers</title>
    </head>
    <body>
        <head>Developers</head>
        <p>
        <table border=1 wide=100>
        <tr>
             <th>id</th>
             <th>Name</th>
             <th>Age</th>
             <th>Gender</th>
             <th>Salary</th>
             <th>Company ID</th>
        </tr>
        <c:forEach var="developer" items="${developers}">
             <tr>
                  <td><c:out value="${developer.id}"/></td>
                  <td><c:out value="${developer.name}"/></td>
                  <td><c:out value="${developer.age}"/></td>
                  <td><c:out value="${developer.gender}"/></td>
                  <td><c:out value="${developer.salary}"/></td>
                  <td><c:out value="${developer.companyId}"/></td>
             </tr>
        </c:forEach>
        </table>
        </p>
    </body>
</html>