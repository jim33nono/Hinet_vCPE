<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hinet vCPE</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- The above 2 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="viewport" content="width=device-width, initial-scale=1">



<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=PT+Sans"
	rel="stylesheet" type="text/css">

<!-- CSS -->
<link href="../resources/css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="../resources/css/yic-style.css" rel="stylesheet">
<link href="../resources/css/index.css" rel="stylesheet">


</head>
<body class="yic yic-login">
	<div id="app">
		<div class="container">
			<div class="row">
				<div class="col-sm-offset-3 col-sm-6 col-md-offset-4 col-md-4">
					<div class="panel panel-yic yic-login-panel">
						<div class="panel-heading text-center">
							<h3 style="color:white">Hinet vCPE</h3>
						</div>
						<div class="panel-body">
							<form class="form-signin" method="post" >
								<div>
									<div class="form-group">
										<input type="text" name="userId"
											class="form-control input-lg" placeholder="請輸入帳號">
									</div>
									<div class="form-group">
										<input type="password" name="password"
											class="form-control input-lg" placeholder="請輸入密碼">
									</div>
									
									<div class="form-group">
										<button class="btn btn-lg btn-yic btn-block" type="submit">
											送出</button>
									</div>
									<div class="form-group">
										<c:if test="${Failed}">
											<label class="text-danger">帳號密碼輸入錯誤</label>
										</c:if>
										
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="modalOverlay" class="modal-backdrop fade"
		style="display: none;"></div>
	<script>
		window.isLogIn = 0;
	</script>
	

	<div id="loadingOverlay">
		<div class="background"></div>
		<div class="loading"></div>
	</div>
</body>
</html>