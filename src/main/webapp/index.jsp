<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>

    <form name="TrainFindForm" action="controller/find-trains" method="get">
        <p>Please fill out all required information before search.</p>
        <input type="text" name="from" placeholder="From" />
        <input type="text" name="to" placeholder="To" />
        <input type="text" name = "departureDate" placeholder="departure date..." />
        <input type="submit" value="search" />
    </form>



</body>
</html>
