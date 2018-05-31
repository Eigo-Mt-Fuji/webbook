<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新宿図書館 - 会員を検索する</title>
	</head>
	<body>
		<div>${title}</div>
		<c:if test="${action} eq 'search'">
			<table>
				<tr>
					<th>会員ID</th>
					<th>名前</th>
					<th>住所</th>
					<th>電話番号</th>
					<th>メールアドレス</th>
				</tr>
				<c:forEach items="${items}" var="item">
					<tr>
						<td>${item.user_id}</td>
						<td>${item.user_name}</td>
						<td>${item.user_address}</td>
						<td>${item.user_tel}</td>
						<td>${item.user_email}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</body>
</html>