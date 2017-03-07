<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class='container'>
	<h3 id='555'>請設定你的Gateway切換 ${account}</h3>

	<div class='form-group'>
			<div class='pull-left'>
					<form action="back">	
						<input class='btn btn-default btnBackPage' type='submit' value='返回'>
					</form>	
			</div>
	</div>
	<div class='form-group'>
			<div class='pull-right'>
					<input class='btn btn-default btnCreateGatewaySwitch' type='button' value='新增Gateway Switch'>
			</div>
	</div>
	<table id='tableGatewaySwitchList' class="table table-striped table-bordered"
		cellspacing="0" width="100%">

	</table>
	





</div>

<!-- including js -->
<script src='<c:url value='/resources/js/gatewaySwitch.js' />'></script>
