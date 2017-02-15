<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <title>Available trains: ${requestScope.depStation.getName()}-${requestScope.arrStation.getName()}</title>
    <script type="text/javascript">
        function setValuesAndSubmit(trainId) {
            document.getElementById("trainId").value =
                                                        document.getElementById('item_train_'+trainId).value;
            document.getElementById('depDate').value =
                                                        document.getElementById('item_depDate_'+trainId).value;
            document.getElementById('arrDate').value =
                                                        document.getElementById('item_arrDate_'+trainId).value;
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

<div style="margin-top: 20px;">
    <strong>Departure station: </strong>${requestScope.depStation.getName()} <br/>
    <strong>Arrival station: </strong>${requestScope.arrStation.getName()}
</div>


<form name="timetable" action="../controller/show-train" method="get">

    <input type="hidden" id="trainId" name="trainId"/>
    <input type="hidden" id="depDate" name="depDate"/>
    <input type="hidden" id="arrDate" name="arrDate"/>
    <input type="hidden" id="distance" name="distance" />
    <input type="hidden" id="travelTime" name="travelTime" />
    <input type="hidden" name="depStationId" value="${requestScope.depStation.getId()}"/>
    <input type="hidden" name="arrStationId" value="${requestScope.arrStation.getId()}"/>


    <table id="main_table">
        <tr>
            <th>Train</th>
            <th>Departure date</th>
            <th>Arrival date</th>
            <th>Distance</th>
            <th>Travel time</th>
            <th>Choice</th>
        </tr>

        <c:forEach var="route_" items="${requestScope.routes}">
            <c:set var="trainName" value="${route_.getTrain().getName()}"/>
            <c:set var="trainId" value="${route_.getTrain().getId()}"/>
            <c:set var="depDate" value="${route_.getFormattedDepartureDate()}" />
            <c:set var="arrDate" value="${route_.getFormattedArrivalDate()}" />
            <c:set var="distance" value="${route_.getCourse().getDistance()}" />
            <c:set var="travelTime" value="${route_.getTravelTime()}" />

            <tr>
                <input type="hidden" id="route_${trainName}" value="${route_}"/>
                <td>
                        ${trainName}
                        <input type="hidden" id="item_train_${trainId}" value="${trainId}" />
                </td>

                <td>
                        ${depDate}
                        <input type="hidden" id="item_depDate_${trainId}" value="${depDate}" />
                </td>
                <td>
                        ${arrDate}
                        <input type="hidden" id="item_arrDate_${trainId}" value="${arrDate}" />
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
