<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <title>Available trains - ${requestScope.route}</title>
    <script type="text/javascript">
        function setValuesAndSubmit(train) {
            document.getElementById("train").value = train;
            document.getElementById("trainId").value =
                                                        document.getElementById('item_trainId_'+train).value;
            document.getElementById('depStation').value =
                                                        document.getElementById('item_depStation_'+train).value;
            document.getElementById('arrStation').value =
                                                        document.getElementById('item_arrStation_'+train).value;
            document.getElementById('depTime').value =
                                                        document.getElementById('item_depTime_'+train).value;
            document.getElementById('arrTime').value =
                                                        document.getElementById('item_arrTime_'+train).value;

            document.forms[0].submit();
        }
    </script>

</head>
<body>
    <form name="timetable" action="../controller/show-train" method="post">

        <input type="hidden" id="train" name="train"/>
        <input type="hidden" id="trainId" name="trainId"/>
        <input type="hidden" id="depStation" name="depStation"/>
        <input type="hidden" id="arrStation" name="arrStation"/>
        <input type="hidden" id="depTime" name="depTime"/>
        <input type="hidden" id="arrTime" name="arrTime"/>
        <input type="hidden" name="depDate" value="${requestScope.depDate}">

        <table id="main_table">
            <tr>
                <th>Train Num.</th>
                <th>Departure station</th>
                <th>Arrival station</th>
                <th>Departure time</th>
                <th>Arrival time</th>
                <th>Choice</th>
            </tr>

            <c:forEach var="route_" items="${requestScope.routes}">
                <c:set value="${route_.getTrain().getName()}" var="train"  />
                <c:set value="${route_.getCourse().getDepartureStation().getName()}" var="depStation"  />
                <c:set value="${route_.getCourse().getArrivalStation().getName()}" var="arrStation"  />
                <tr>
                    <td>
                        ${train}
                        <input type="hidden" id="item_train" value="${train}" />
                        <input type="hidden" id="item_trainId_${train}" value="${route_.getTrain().getId()}" />
                    </td>

                    <td>
                        ${depStation}
                        <input type="hidden" id="item_depStation_${train}" value="${depStation}" />
                    </td>
                    <td>
                        ${arrStation}
                        <input type="hidden" id="item_arrStation_${train}" value="${arrStation}" />
                    </td>
                    <td>
                        ${route_.getDepartureTime()}
                        <input type="hidden" id="item_depTime_${train}" value="${route_.getDepartureTime()}" />
                    </td>
                    <td>
                        ${route_.getArrivalTime()}
                        <input type="hidden" id="item_arrTime_${train}" value="${route_.getArrivalTime()}" />
                    </td>

                    <td>
                        <button type="button" onclick="setValuesAndSubmit('${train}');">-></button>
                    </td>

                </tr>
            </c:forEach>
        </table>

    </form>
</body>
</html>
