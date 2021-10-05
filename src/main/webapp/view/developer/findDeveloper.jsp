<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Find Developer</title>
        <style>
            <%@include file="/style.css" %>
        </style>
    </head>
    <body>
    <c:import url="/navibar.jsp"/>
        <h2>Find company by name:</h2>
        <p>
        <form name="developerFindForm" method="get" action="find">
        <table>
            <tr>
                <th>Enter developer name:</th>
                <td> <input type="text" name="name"/> <br/> </td>
            </tr>
            <%--
            <tr>
                <th>Enter developer ID:</th>
                <td><input type="numeric" name="id"/> <br/></td>
            </tr>
            --%>
        </table>
        <input type="submit" value="Find developer"  class="button"/>
        </form>
        </p>
        <p>${message}</p>
    </body>
</html>