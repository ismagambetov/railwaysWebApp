<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <title>Train page</title>
    <script type="text/javascript">
        function setPlace(place) {
            document.getElementById("placeNum").value = place;
        }
    </script>
</head>
<body>
    <div id="main_div">

        <h3>Train - ${requestScope.train}</h3>

        <h4>
            Route: ${requestScope.depStation} - ${requestScope.arrStation} <br/>
            Departure date: ${requestScope.depDate}
        </h4>
        <p>
            Departure time: ${requestScope.depTime} <br/>
            Arrival time: ${requestScope.arrTime}
        </p>

        <table id="main_table">
            <tr>
                <th>Wagon Num.</th>
                <th>Category</th>
                <th>Free places</th>
            </tr>
            <c:forEach var="wagon" items="${requestScope.wagons}">
                <tr>
                    <c:set var="unBookedPlaces" value=""/>
                    <td>${wagon.getWagonNum()}</td>
                    <td>${wagon.getWagonCategory()}</td>
                    <td>
                        <c:forEach var="place" items="${wagon.getPlaces()}">
                            <c:if test="${!place.isBooked()}">
                                <c:set var="p" value="${place.getPlace()}"/>
                                <a href="#" onclick="setPlace(${p});">${p}</a>,
                            </c:if>
                        </c:forEach>
                    </td>

                </tr>
            </c:forEach>
        </table>

        <form name="order_form" action="controller/order" method="post" id="form_style">
            <input name="wagonNum" type="text" placeholder="wagon number" />
            <input name="placeNum" type="text" id="placeNum" placeholder="place number" />
            <input type="submit" value="Book" />
        </form>

    </div>

</body>
</html>
