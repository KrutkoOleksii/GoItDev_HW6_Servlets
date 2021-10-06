<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Customer</title>
        <style>
            <%@include file="/style.css" %>
        </style>
    </head>
    <body>
    <c:import url="/navibar.jsp"/>
        <h2>Customer parameters:</h2>
        <p>
            <form name="customerSaveForm" method="post" action="create">
                Name: <input type="text" name="name"/> <br/>
                Code: <input type="text" name="code"/> <br/>

                <input type="submit" value="Add customer"  class="button"/>
            </form>
        </p>
    </body>
</html>