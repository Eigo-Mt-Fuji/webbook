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
<div class="content-header-system-title">
	<div class="area">
		<div class="item item-left system-title">${system_title}&nbsp;&nbsp;&nbsp;</div>
		<c:if test="${not empty member}">
			<div class="item item-right">
				<span>ユーザ名: ${member.userFamilyName} ${member.userName}</span>
				<span><a href="#" onclick="logout(); return false;">ログアウト</a></span>
				<form name="logoutForm" action="<%= request.getContextPath() %>/session" method="POST">
					<input type="hidden" name="action" value="logout"/>
				</form>
			</div>
		</c:if>
	</div>
</div>

<c:if test="${not empty member and member.userRole ne '1'}">
	<!--  メニュー -->
	<div class="content-header-menu">
		<span><a href="<%= request.getContextPath() %>/member?action=search">会員管理</a></span>
		<span><a href="<%= request.getContextPath() %>/book?action=search">書籍管理</a></span>
	</div>
</c:if>

<hr/>
<c:if test="${not empty content_title}">

	<!-- コンテンツタイトル -->
	<div class="content-header-title">
		<span>${content_title}</span>
	</div>
</c:if>