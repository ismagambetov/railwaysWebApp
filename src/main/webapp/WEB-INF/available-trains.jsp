<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <title>Available trains: ${requestScope.depStation}-${requestScope.arrStation}</title>
    <script type="text/javascript">
        function setValuesAndSubmit(trainId) {
            document.getElementById("trainId").value =
                                                        document.getElementById('item_train_'+trainId).value;
            document.getElementById('depStationId').value =
                                                        document.getElementById('item_depStation_'+trainId).value;
            document.getElementById('arrStationId').value =
                                                        document.getElementById('item_arrStation_'+trainId).value;
            document.getElementById('depTime').value =
                                                        document.getElementById('item_depTime_'+trainId).value;
            document.getElementById('arrTime').value =
                                                        document.getElementById('item_arrTime_'+trainId).value;
            document.getElementById('distance').value =
                                                        document.getElementById('item_distance_'+trainId).value;
            document.getElementById('travelTime').value =
                                                        document.getElementById('item_travelTime_'+trainId).value;

            document.forms[0].submit();
        }
    </script>

</head>
<body>
<div id="login">
    <strong>Username: </strong>${sessionScope.user.getUsername()} <br/>
    <strong>Role: </strong>${sessionScope.user.getRole()}
</div>

<div>
    <strong>Departure station: </strong>${requestScope.depStation} <br/>
    <strong>Arrival station: </strong>${requestScope.arrStation}
</div>


<form name="timetable" action="../controller/show-train" method="get">

    <input type="hidden" id="trainId" name="trainId"/>
    <input type="hidden" id="depStationId" name="depStationId"/>
    <input type="hidden" id="arrStationId" name="arrStationId"/>
    <input type="hidden" id="depTime" name="depTime"/>
    <input type="hidden" id="arrTime" name="arrTime"/>
    <input type="hidden" name="depDate" value="${requestScope.depDate}"/>
    <input type="hidden" id="distance" name="distance" />
    <input type="hidden" id="travelTime" name="travelTime" />


    <table id="main_table">
        <tr>
            <th>Train Num.</th>
            <th>Departure station</th>
            <th>Arrival station</th>
            <th>Departure time</th>
            <th>Arrival time</th>
            <th>Distance</th>
            <th>Travel time</th>
            <th>Choice</th>
        </tr>

        <c:forEach var="route_" items="${requestScope.routes}">
            <c:set var="trainName" value="${route_.getTrain().getName()}"/>
            <c:set var="trainId" value="${route_.getTrain().getId()}"/>
            <c:set var="depStationId" value="${route_.getCourse().getDepartureStation().getId()}"/>
            <c:set var="arrStationId" value="${route_.getCourse().getArrivalStation().getId()}"/>
            <c:set var="depStationName" value="${route_.getCourse().getDepartureStation().getName()}" />
            <c:set var="arrStationName" value="${route_.getCourse().getArrivalStation().getName()}" />
            <c:set var="distance" value="${route_.getCourse().getDistance()}" />
            <c:set var="travelTime" value="${route_.getTravelTime()}" />

            <tr>
                <input type="hidden" id="route_${trainName}" value="${route_}"/>
                <td>
                        ${trainName}
                        <input type="hidden" id="item_train_${trainId}" value="${trainId}" />
                </td>

                <td>
                        ${depStationName}
                        <input type="hidden" id="item_depStation_${trainId}" value="${depStationId}" />
                </td>
                <td>
                        ${arrStationName}
                        <input type="hidden" id="item_arrStation_${trainId}" value="${arrStationId}" />
                </td>
                <td>
                        ${route_.getDepartureTime()}
                        <input type="hidden" id="item_depTime_${trainId}" value="${route_.getDepartureTime()}" />
                </td>
                <td>
                        ${route_.getArrivalTime()}
                        <input type="hidden" id="item_arrTime_${trainId}" value="${route_.getArrivalTime()}" />
                </td>

                <td>
                    ${distance}
                    <input type="hidden" id="item_distance_${trainId}" value="${distance}" />
                </td>

                <td>
                    ${travelTime}
                    <input type="hidden" id="item_travelTime_${trainId}" value="${travelTime}" />
                </td>

                <td>
                    <button type="button" onclick="setValuesAndSubmit('${trainId}');">-></button>
                </td>

            </tr>
        </c:forEach>
    </table>

</form>
</body>
</html>
