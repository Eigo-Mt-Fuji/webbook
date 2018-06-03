<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../common/header.jsp"/>
</head>

<body>
<div class="container">
    <jsp:include page="../layout/content-header.jsp"/>
    <c:if test="${action eq \"search\"}">
        <table id="items" class="table">
            <thead>
            <tr>
                <th>ISBN番号</th>
                <th>タイトル</th>
                <th>著者</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="item">
                <tr>
                    <td>${item.bookinfoIsbn}</td>
                    <td>${item.bookinfoName}</td>
                    <td>${item.bookinfoAuthor}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:if>
    <jsp:include page="../layout/content-footer.jsp"/>
</div>
</body>
</html>
