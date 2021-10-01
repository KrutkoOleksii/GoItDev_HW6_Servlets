<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Skill</title>
    </head>
    <body>
        <h2>Skill parameters:</h2>
        <p>
        <form name="skillForm" method="post" action="skills">
            Name (Technology): <input type="text" name="name"/> <br/>
            Level: <input type="text" name="level"/> <br/>
            <input type="submit" value="Add skill" />
        </form>
        </p>
    </body>
</html>