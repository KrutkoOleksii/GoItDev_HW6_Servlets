<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Projects</title>
    </head>
    <body>
        <head>Projects</head>
        <p>
        <table border=1 wide=100>
        <tr>
             <th>id</th>
             <th>Name</th>
             <th>Cost</th>
             <th>Start date</th>
             <th>Company ID</th>
             <th>Customer ID</th>
        </tr>
        <c:forEach var="project" items="${projects}">
             <tr>
                  <td><c:out value="${project.id}"/></td>
                  <td><c:out value="${project.name}"/></td>
                  <td><c:out value="${project.cost}"/></td>
                  <td><c:out value="${project.startDate}"/></td>
                  <td><c:out value="${project.companyId}"/></td>
                  <td><c:out value="${project.customerId}"/></td>
             </tr>
        </c:forEach>
        </table>
        </p>
    </body>
</html>