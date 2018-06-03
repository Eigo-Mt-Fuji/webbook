<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col">
        <div class="form-group">
            <label for="user_family_name">姓</label>
            <input id="user_family_name" type="text" class="form-control" name="user_family_name" placeholder="姓" value="${content.userFamilyName}" />
        </div>
    </div>
    <div class="col">
        <div class="form-group">
            <label for="user_name">名</label>
            <input id="user_name" type="text" class="form-control" name="user_name" placeholder="名" value="${content.userName}" />
        </div>
    </div>
</div>

<div class="row">
    <div class="col">
        <div class="form-group">
            <label for="user_postal">郵便番号</label>
            <input id="user_postal" type="text"  pattern="\d{7}" class="form-control" name="user_postal" placeholder="1234567" value="${content.userPostal}" />
        </div>
    </div>
    <div class="col">
        <div class="form-group">
            <label for="user_address">住所</label>
            <input id="user_address" type="text" maxlength="100" class="form-control" name="user_address" placeholder="市区町村・番地号" value="${content.userAddress}" />
        </div>
    </div>
</div>


<div class="row">
    <div class="col">
        <div class="form-group">
            <label for="user_email">メールアドレス</label>
            <input id="user_email" type="email" class="form-control" name="user_email" placeholder="xxx@dd.co.jp" value="${content.userEmail}" />
        </div>
    </div>
    <div class="col">
        <div class="form-group">
            <label for="user_tel">電話番号</label>
            <input id="user_tel" type="tel" maxlength="20" class="form-control" name="user_tel" placeholder="090-xxxx-xxxx" value="${content.userTel}" />
        </div>
    </div>
</div>
<div class="row">
    <div class="col">
        <div class="form-group">
            <label for="user_birthday">生年月日</label>
            <input id="user_birthday" type="date" class="form-control" name="user_birthday" placeholder="19xx-xx-xx" value="${content.userBirthday}" />
        </div>
    </div>
    <div class="col">
        <div class="form-group">
            <label for="user_password">パスワード</label>
            <input id="user_password" type="password" class="form-control" name="user_password" placeholder="himitu" value="${content.userPassword}"/>
        </div>
    </div>
</div>

<input type="hidden" id="user_role" type="text" class="form-control" name="user_role" value="${content.userRole}" />
<c:if test="${not empty message}">
    <p class="error-message">${message}</p>
</c:if>
