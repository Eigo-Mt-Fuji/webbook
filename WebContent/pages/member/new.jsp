<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../common/header.jsp"/>
    <script>

        function onSubmit() {

            if (confirm("登録しますか?")) {
                document.content.submit();
            }
        }
    </script>
</head>

<body>
<div class="container">
    <jsp:include page="../layout/content-header.jsp"/>

    <form name="content" action="<%= request.getContextPath() %>/member" method="POST">
        <jsp:include page="./form.jsp" />
        <button type="button" class="btn btn-primary" onclick="onSubmit();">新規登録</button>
        <input type="hidden" name="action" value="register" />
    </form>

    <jsp:include page="../layout/content-footer.jsp"/>
</div>
</body>
</html>
