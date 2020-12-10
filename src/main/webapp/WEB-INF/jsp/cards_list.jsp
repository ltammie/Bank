<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP List Users Records</title>
</head>
<body>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of cards</h2></caption>
            <tr>
                <th>ID</th>
                <th>Account ID</th>
                <th>Client ID</th>
                <th>Expiration ID</th>
                <th>Card number</th>
            </tr>
            <c:forEach var="card" items="${listCards.rows}">
            <tr>
            <td><c:out value="${card.id}" /></td>
            <td><c:out value="${card.account_id}" /></td>
            <td><c:out value="${card.client_id}" /></td>
            <td><c:out value="${card.expiration_date}" /></td>
            <td><c:out value="${card.number}" /></td>
            </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>