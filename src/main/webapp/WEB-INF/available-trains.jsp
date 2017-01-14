<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <style type="text/css">
        #main_table, #main_table th, #main_table td {
            border:1px solid black;
            border-collapse: collapse;
            font-size: 12px;
            color: #949494;
            width:750px;
            margin-left: 15px;
        }

        #main_table th {
            text-align: left;
            background-color: #f9f9f9;
        }

    </style>
    <title>Available trains - ${requestScope.route}</title>
</head>
<body>

    <table id="main_table">
        <tr>
            <th>Train Num.</th>
            <th>Departure station</th>
            <th>Arrival station</th>
            <th>Departure time</th>
            <th>Arrival time</th>
        </tr>

        <c:forEach var="route_" items="${requestScope.routes}">
        <c:set value="${route_.getTrain().getName()}" var="train"  />

            <tr>
                <td>
                    <a href="/controller/check-train?name=${train}"> ${train} </a>
                </td>
                <td>${route_.getCourse().getDepartureStation().getName()}</td>
                <td>${route_.getCourse().getArrivalStation().getName()}</td>
                <td>${route_.getDepartureTime()}</td>
                <td>${route_.getArrivalTime()}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
