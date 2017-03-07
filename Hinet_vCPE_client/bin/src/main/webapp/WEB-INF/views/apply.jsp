<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form class="form-horizontal" method="POST">
  <div class="form-group">
  	<h3>使用者資料</h3>
  </div>
  <div class="form-group">
    <label for="userId"" class="col-sm-2 control-label">使用者帳號</label>
    <div class="col-sm-10">
		<c:if test="${freshApply}">    	
      	 <input type="text" class="form-control" id="userId" name="userId" placeholder="使用者帳號">
        </c:if>
        <c:if test="${not freshApply}">    	
      	 <input type="text" class="form-control" id="userId" name="userId" readonly placeholder="使用者帳號">
        </c:if>
        
    </div>
  </div>
  <div class="form-group">
    <label for="userName" class="col-sm-2 control-label">使用者姓名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="userName" name="userName" placeholder="使用者帳號" value="${userInfo.userName}">
    </div>
  </div>
  <div class="form-group">
    <label for="pwd" class="col-sm-2 control-label">密碼</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="pwd" name="pwd" placeholder="密碼">
    </div>
  </div>
  <div class="form-group">
    <label for="pwd2" class="col-sm-2 control-label">密碼驗證</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="pwd2" name="pwd2" placeholder="密碼驗證">
    </div>
  </div>
  <div class="form-group">
    <label for="address" class="col-sm-2 control-label">住家地址</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="address" name="address" placeholder="住家地址" value="${userInfo.address}">
    </div>
  </div>
  <div class="form-group">
    <label for="phone" class="col-sm-2 control-label">聯絡電話</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="phone" name="phone" placeholder="聯絡電話" value="${userInfo.phone}">
    </div>
  </div>
  <div class="form-group">
  	<h3>服務設定</h3>
  </div>
  <div class="form-group">
  	 <label for="inputPhone" class="col-sm-2 control-label"></label>
    <div class="col-sm-10">
    	<form:checkboxes items="${configList}" path="userInfo.configs" />	
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">送出</button>
    </div>
  </div>
  
</form>