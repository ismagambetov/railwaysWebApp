<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <title>Train page</title>
    <script type="text/javascript">
        function setPlace(place,wagonNum,category) {
            document.getElementById("placeNum").value = place;
            document.getElementById("wagonNum").value = wagonNum;
            document.getElementById("category").value = category;
        }
    </script>
</head>
<body>
    <c:set var="trainId" value="${requestScope.route.getTrain().getId()}"/>
    <c:set var="trainName" value="${requestScope.route.getTrain().getName()}"/>
    <c:set var="depStationId" value="${requestScope.route.getCourse().getDepartureStation().getId()}" />
    <c:set var="depStationName" value="${requestScope.route.getCourse().getDepartureStation().getName()}" />
    <c:set var="arrStationId" value="${requestScope.route.getCourse().getArrivalStation().getId()}" />
    <c:set var="arrStationName" value="${requestScope.route.getCourse().getArrivalStation().getName()}" />
    <c:set var="depDate" value="${requestScope.route.getFormattedDepartureDate()}" />
    <c:set var="arrDate" value="${requestScope.route.getFormattedArrivalDate()}" />

    <div id="main_div">

        <div id="login">
            <strong>Username: </strong>${sessionScope.user.getUsername()} <br/>
            <strong>Role: </strong>${sessionScope.user.getRole()}
        </div>

        <h3>Train - ${trainName}</h3>

        <div>
            <strong>Route:</strong> ${depStationName} - ${arrStationName} <br/>
            <strong>Departure date:</strong> ${depDate} <br/>
            <strong>Arrival date:</strong> ${arrDate}
        </div>

        <table id="main_table">
            <tr>
                <th>Wagon Num.</th>
                <th>Category</th>
                <th>Free places</th>
            </tr>
            <c:forEach var="wagon" items="${requestScope.wagons}">
                <tr>
                    <c:set var="wagonNum" value="${wagon.getWagonNum()}" />
                    <td>${wagonNum}</td>
                    <c:set var="category" value="${wagon.getWagonCategory()}" />
                    <td>${category}</td>
                    <td>
                        <c:forEach var="place" items="${wagon.getPlaces()}">
                            <c:if test="${!place.isBooked()}">
                                <c:set var="p" value="${place.getPlace()}"/>
                                <a href="#" onclick="setPlace(${p},${wagonNum});">${p}</a>,
                            </c:if>
                        </c:forEach>
                    </td>

                </tr>
            </c:forEach>
        </table>

        <form name="book_form" action="../secured/booking-page" method="get" id="form_style">
            <strong>Place: </strong><input name="placeNum" type="text" id="placeNum" placeholder="place number" />
            <strong style="margin-left: 50px;">Wagon: </strong><input name="wagonNum" type="text"
                                                                      id="wagonNum" placeholder="wagon number" />
            <input name="trainId" type="hidden" value="${trainId}" />
            <input name="depStationId" type="hidden" value="${depStationId}" />
            <input name="arrStationId" type="hidden" value="${arrStationId}" />
            <input name="depDate" type="hidden" value="${depDate}" />
            <input name="arrDate" type="hidden" value="${arrDate}" />

            <input type="submit" value="Go to the booking page" />
        </form>

    </div>

    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>

</body>
</html>
