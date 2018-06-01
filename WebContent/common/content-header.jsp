<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

	/**
	 * ログアウト処理
	 */
	function logout() {

		if(confirm("ログアウトしますか？")) {

			document.logoutForm.submit();
		}
	}
</script>

<!-- システムタイトル -->
<div class="content-header-systemtitle">
	<span style="display:block;">

		<span style="font-size:20px;display:inline;">${system_title}&nbsp;&nbsp;&nbsp;</span>
		<c:if test="${not empty member}">
			<span style="font-size:20px;display:inline;">ようこそ ${member.userFamilyName} ${member.userName}さん</span>
			<span style="font-size:20px;display:inline;"><a href="#" onclick="logout(); return false;">ログアウト</a></span>
			<form name="logoutForm" action="<%= request.getContextPath() %>/session" method="POST">
				<input type="hidden" name="action" value="logout"/>
			</form>
		</c:if>
	</span>
</div>

<c:if test="${not empty action}">
	<!--  メニュー -->
	<div class="content-header-menu">
		<span><a href="#">会員管理</a></span>
		<span><a href="#">書籍管理</a></span>
	</div>
</c:if>

<hr/>
<c:if test="${not empty content_title}">

	<!-- コンテンツタイトル -->
	<div class="content-header-title">
		<span>${content_title}</span>
	</div>
</c:if>