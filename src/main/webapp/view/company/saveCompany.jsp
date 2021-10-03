<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Company</title>
        <style>
            <%@include file="/style.css" %>
        </style>
    </head>
    <body>
    <c:import url="/navibar.jsp"/>
        <h2>Company parameters:</h2>
        <p>
        <form name="companyForm" method="post" action="create">
            Name: <input type="text" name="name"/> <br/>
            Code: <input type="text" name="code"/> <br/>

            <input type="submit" value="Add company" />
        </form>
        </p>
    </body>
</html>