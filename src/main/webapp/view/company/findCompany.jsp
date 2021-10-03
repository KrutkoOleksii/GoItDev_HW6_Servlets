<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Company</title>
    </head>
    <body>
        <h2>Company parameters:</h2>
        <p>
        <form name="companyForm" method="get" action="find">
            <-- Name: <input type="text" name="name"/> <br/> --/>
            Enter company ID: <input type="numeric" name="id"/> <br/>

            <input type="submit" value="Find company" />
        </form>
        </p>
        <p>${message}</p>
    </body>
</html>