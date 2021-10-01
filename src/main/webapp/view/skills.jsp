<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Skills</title>
    </head>
    <body>
        <h2>Skills</h2>
        <p>
        <table border=1 wide=100>
        <tr>
             <th>id</th>
             <th>Name (Technology)</th>
             <th>Level</th>
        </tr>
        <c:forEach var="skill" items="${skills}">
             <tr>
                  <td><c:out value="${skill.id}"/></td>
                  <td><c:out value="${skill.name}"/></td>
                  <td><c:out value="${skill.skillLevel}"/></td>
             </tr>
        </c:forEach>
        </table>
        </p>
        <button onclick="location.href='/pm'">Back to main page</button>
    </body>
</html>