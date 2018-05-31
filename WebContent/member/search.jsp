<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	  	<link rel="stylesheet" href="/webbook/assets/stylesheets/style.css"/>
		<title>新宿図書館 - 会員を検索する</title>
	</head>
	<body>
		<div class="container" style="height:600px">
			<jsp:include page="/common/content-header.jsp" />
			<c:if test="${action eq \"search\"}">
				<table class="table">
				    <thead>
				      <tr>
							<th>会員ID</th>
							<th>名前</th>
							<th>住所</th>
							<th>電話番号</th>
							<th>メールアドレス</th>
					 </tr>
				    </thead>
				    <tbody>
						<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.userId}</td>
								<td>${item.userName}</td>
								<td>${item.userAddress}</td>
								<td>${item.userTel}</td>
								<td>${item.userEmail}</td>
							</tr>
						</c:forEach>

				    </tbody>
			  	</table>
			</c:if>
			<jsp:include page="/common/content-footer.jsp" />
		</div>
	</body>
</html>