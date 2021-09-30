<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Companies</title>
    </head>
    <body>
        <head>Companies</head>
        <p>
        <table border=1 wide=100>
        <tr>
             <th>id</th>
             <th>Name</th>
             <th>Code</th>
        </tr>
        <c:forEach var="company" items="${companies}">
             <tr>
                  <td><c:out value="${company.id}"/></td>
                  <td><c:out value="${company.name}"/></td>
                  <td><c:out value="${company.code}"/></td>
             </tr>
        </c:forEach>
        </table>
        </p>
    </body>
</html>