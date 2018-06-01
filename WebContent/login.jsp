<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=UTF-8">
	  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	  	<link rel="stylesheet" href="/webbook/assets/stylesheets/style.css" />
		<title>system_title</title>
	</head>
	<%
		request.setAttribute("system_title", la.webbook.util.Constant.SYSTEM_TITLE);
		request.setAttribute("content_title", la.webbook.util.Constant.CONTENT_TITLE_LOGIN);
	%>
	<body>
		<div class="container" style="height:600px">
			<jsp:include page="/common/content-header.jsp" />
			<form action="/webbook/session" method="POST">
				<input type="hidden" name="action" value="login" />
				<div class="form-group">
				    <label for="user_email">メールアドレス</label>
				    <input type="email" class="form-control" id="user_email" placeholder="xxx@abc.com" />
				</div>
				<div class="form-group">
				    <label for="exampleInputPassword1">パスワード</label>
				    <input type="password" class="form-control" id="user_password" placeholder="ヒント: himitu">
				</div>
				<button type="submit" class="btn btn-primary">ログイン</button>
				<c:if test="${not empty message}">
					<p class="error-message">${message}</p>
				</c:if>
			</form>
			<jsp:include page="/common/content-footer.jsp" />
		</div>
	</body>
</html>
