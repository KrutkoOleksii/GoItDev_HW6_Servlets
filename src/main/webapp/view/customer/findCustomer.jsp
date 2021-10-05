<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Find Customer</title>
        <style>
            <%@include file="/style.css" %>
        </style>
    </head>
    <body>
    <c:import url="/navibar.jsp"/>
        <h2>Find customer by name:</h2>
        <p>
        <form name="customerFindForm" method="get" action="find">
        <table>
            <tr>
                <th>Enter customer name:</th>
                <td> <input type="text" name="name"/> <br/> </td>
            </tr>
            <%--
            <tr>
                <th>Enter customer ID:</th>
                <td><input type="numeric" name="id"/> <br/></td>
            </tr>
            --%>
        </table>
        <input type="submit" value="Find customer"  class="button"/>
        </form>
        </p>
        <p>${message}</p>
    </body>
</html>