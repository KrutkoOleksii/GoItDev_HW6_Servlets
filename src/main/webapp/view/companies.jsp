<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Companies</title>
    </head>
    <body>
        <h2>Companies</h2>
        <p>
        <table border=1 cellpadding=8>
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
                  <td><button onclick="location.href='/pm/company/${company.id}'"/>UPDATE</button></td>
             </tr>
        </c:forEach>
        </table>
        </p>
        <button onclick="location.href='/pm'">Back to main page</button>
    </body>
</html>