<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Customers</title>
    </head>
    <body>
        <h2>Customers</h2>
        <p>
        <table border=1 cellpadding=8>
        <tr>
             <th>id</th>
             <th>Name</th>
             <th>Code</th>
        </tr>
        <c:forEach var="customer" items="${customers}">
             <tr>
                  <td><c:out value="${customer.id}"/></td>
                  <td><c:out value="${customer.name}"/></td>
                  <td><c:out value="${customer.code}"/></td>
             </tr>
        </c:forEach>
        </table>
        </p>
        <button onclick="location.href='/pm'">Back to main page</button>
    </body>
</html>