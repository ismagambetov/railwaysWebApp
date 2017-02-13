<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <title>Booking page</title>
</head>
<body>
    <%--<h1> Your booking details </h1>--%>
    <form name="book" action="/controller/book" method="get">
        <table id="main_table">
            <caption>Your booking details</caption>
            <tr>
                <td>Passenger </td>
                <td>${requestScope.passenger}</td>
            </tr>

            <tr>
                <td>Route </td>
                <td>${requestScope.route}</td>
            </tr>

            <tr>
                <td>Departure date/time </td>
                <td>${requestScope.depDate}</td>
            </tr>

            <tr>
                <td>Arrival date/time </td>
                <td>${requestScope.arrDate}</td>
            </tr>

            <tr>
                <td>Train </td>
                <td>${requestScope.train}</td>
            </tr>

            <tr>
                <td>Wagon Num. </td>
                <td>${requestScope.wagonNum}</td>
            </tr>

            <tr>
                <td>Wagon Category </td>
                <td>${requestScope.wagonCat}</td>
            </tr>

            <tr>
                <td>Place </td>
                <td>${requestScope.placeNum}</td>
            </tr>

            <tr>
                <td>Cost </td>
                <td>${requestScope.cost}</td>
            </tr>

        </table>

        <input type="submit" value="Book">

    </form>
</body>
</html>
