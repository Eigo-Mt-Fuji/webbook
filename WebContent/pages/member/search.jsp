<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../common/header.jsp"/>
    <script>

        function onDelete(formName, userFullName) {
            if(confirm(userFullName + "さんを削除しますか?")) {
                document[formName].submit();
            }
        }
    </script>
</head>

<body>
<div class="container">
    <jsp:include page="../layout/content-header.jsp"/>
    <c:if test="${action eq \"search\"}">
        <div class="toolbar">
            <form action="<%= request.getContextPath() %>/member" method="GET">
                <input type="hidden" name="action" value="new" />
                <button type="submit" class="btn btn-primary">新規登録</button>
            </form>
        </div>

        <table id="items" class="table">
            <thead>
            <tr>
                <th>会員ID</th>
                <th>名前</th>
                <th>住所</th>
                <th>電話番号</th>
                <th>メールアドレス</th>
                <th>削除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="item">
                <tr>
                    <td>${item.userId}</td>
                    <td><a href="<%= request.getContextPath() %>/member?action=edit&user_id=${item.userId}">${item.userFamilyName} ${item.userName}</a></td>
                    <td>${item.userAddress}</td>
                    <td>${item.userTel}</td>
                    <td>${item.userEmail}</td>
                    <td>
                        <button type="button" class="btn btn-danger" onclick="onDelete('form_${item.userId}', '${item.userFamilyName} ${item.userName}');">
                            削除
                        </button>
                        <form name="form_${item.userId}" action="<%= request.getContextPath() %>/member" method="POST">
                            <input type="hidden" value="delete" name="action" />
                            <input type="hidden" value="${item.userId}" name="user_id" />
                        </form>
                    </td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:if>
    <jsp:include page="../layout/content-footer.jsp"/>
</div>
</body>
</html>
