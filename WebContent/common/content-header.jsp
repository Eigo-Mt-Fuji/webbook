<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- システムタイトル -->
<div class="content-header-systemtitle">
	<span style="display:block;">${system_title}</span>
</div>

<c:if test="${not empty action}">
	<!--  メニュー -->
	<div class="content-header-menu">
		<span><a href="#">会員管理</a></span>
		<span><a href="#">書籍管理</a></span>
	</div>
</c:if>

<hr/>

<!-- コンテンツタイトル -->
<div class="content-header-title">
	<span>${content_title}</span>
</div>
