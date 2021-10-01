<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Developer</title>
    </head>
    <body>
        <h2>Developer parameters:</h2>
        <p>
            <form name="developerForm" method="post" action="developers">
                Name: <input type="text" name="name"/> <br/>
                Age: <input type="numeric" name="age"/> <br/>
                Gender: <input type="text" name="gender"/> <br/>
                Salary: <input type="numeric" name="salary"/> <br/>
                Company ID: <input type="numeric" name="companyId"/> <br/>

                <input type="submit" value="Add developer" />
            </form>
        </p>
    </body>
</html>