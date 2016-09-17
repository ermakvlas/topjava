<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

    </style>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<table>
    <c:forEach var="meal" items="${mealsWithExceed}">
        <c:choose>
            <c:when test="${meal.exceed}">
                <c:set var="color" value="#DD615B"/>
            </c:when>
            <c:otherwise>
                <c:set var="color" value="#5EDD69"/>
            </c:otherwise>
        </c:choose>
        <tr bgcolor="${color}">
            <c:set var="cleanedDateTime" value="${fn:replace(meal.dateTime, 'T', ' ')}"/>
            <td><c:out value="${cleanedDateTime}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><c:out value="${meal.exceed}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
