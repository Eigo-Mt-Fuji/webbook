<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="header.jsp" />
	</head>
	<body>
		<div class="container">
			<jsp:include page="../layout/content-header.jsp" />
			<c:if test="${not empty message}">
				<div class="contents">
					<p class="error-message">${message}</p>
				</div>

			</c:if>
			<jsp:include page="../layout/content-footer.jsp" />
		</div>
	</body>
</html>
