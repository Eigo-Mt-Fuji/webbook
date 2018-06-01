<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta content="text/html; charset=UTF-8">
	  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	  	<link rel="stylesheet" href="/webbook/assets/stylesheets/style.css" />
		<title>${system_title}</title>
	</head>
	<c:if test="${empty member}">
		<jsp:forward page="/session" />
	</c:if>
	<body>
		<div class="container" style="height:600px">
			<jsp:include page="/common/content-header.jsp" />
			<p style="font-size:20px;color:#dddddd;">
				新宿図書館（SL）は良い図書館
			</p>
			<jsp:include page="/common/content-footer.jsp" />
		</div>
	</body>
</html>
