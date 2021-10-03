<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Project</title>
    </head>
    <body>
        <h2>Project parameters:</h2>
        <p>
        <form name="projectForm" method="post" action="projects">
            Name: <input type="text" name="name"/> <br/>
            Cost: <input type="numeric" name="cost"/> <br/>
            Start date: <input type="date" name="startDate" pattern = "yyyy-MM-dd"/> <br/>
            Company ID: <input type="numeric" name="companyId"/> <br/>
            Customer ID: <input type="numeric" name="customerId"/> <br/>

            <input type="submit" value="Add project" />
        </form>
        </p>
    </body>
</html>