<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Skill</title>
        <style>
            <%@include file="/style.css" %>
        </style>
    </head>
    <body>
    <c:import url="/navibar.jsp"/>
        <h2>Skill parameters:</h2>
        <p>
            <form name="skillForm" method="post" action="create">
                Name (Technology): <input type="text" name="name"/> <br/>
                Level: <input type="text" name="level"/> <br/>

                <input type="submit" value="Add skill"  class="button"/>
            </form>
        </p>
    </body>
</html>