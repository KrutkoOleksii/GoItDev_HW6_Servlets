<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Customer</title>
    </head>
    <body>
        <h2>Customer parameters:</h2>
        <p>
            <form name="customerForm" method="post" action="create">
                Name: <input type="text" name="name"/> <br/>
                Code: <input type="text" name="code"/> <br/>

                <input type="submit" value="Add customer" />
            </form>
        </p>
    </body>
</html>