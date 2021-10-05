<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Find Project</title>
        <style>
            <%@include file="/style.css" %>
        </style>
    </head>
    <body>
    <c:import url="/navibar.jsp"/>
        <h2>Find project by name:</h2>
        <p>
        <form name="projectFindForm" method="get" action="find">
        <table>
            <tr>
                <th>Enter project name:</th>
                <td> <input type="text" name="name"/> <br/> </td>
            </tr>
            <%--
            <tr>
                <th>Enter project ID:</th>
                <td><input type="numeric" name="id"/> <br/></td>
            </tr>
            --%>
        </table>
        <input type="submit" value="Find project"  class="button"/>
        </form>
        </p>
        <p>${message}</p>
    </body>
</html>