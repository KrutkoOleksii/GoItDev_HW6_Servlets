<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Company</title>
    </head>
    <body>
        <h2>Company parameters:</h2>
        <p>
        <form name="companyForm" method="post" action="companies">
            Name: <input type="text" name="name"/> <br/>
            Code: <input type="text" name="code"/> <br/>

            <input type="submit" value="Add company" />
        </form>
        </p>
    </body>
</html>