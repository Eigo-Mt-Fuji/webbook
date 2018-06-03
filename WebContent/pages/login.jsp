<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="common/header.jsp" />
	</head>

	<body>
		<div class="container">
			<div class="login-form-box">
				<div class="login-form-title">
					${system_title}
				</div>
				<form action="<%= request.getContextPath() %>/session" method="POST">
					<input type="hidden" name="action" value="login" />
					<div class="form-group">
						<label for="user_email">メールアドレス</label>
						<input id="user_email" type="email" class="form-control" name="user_email" placeholder="xxx@abc.com" />
					</div>
					<div class="form-group">
						<label for="user_password">パスワード</label>
						<input id="user_password" type="password" class="form-control" name="user_password" placeholder="himitu">
					</div>
					<button type="submit" class="btn btn-primary">ログイン</button>
					<c:if test="${not empty message}">
						<p class="error-message">${message}</p>
					</c:if>
				</form>
			</div>
			<jsp:include page="layout/content-footer.jsp" />
		</div>
	</body>
</html>
