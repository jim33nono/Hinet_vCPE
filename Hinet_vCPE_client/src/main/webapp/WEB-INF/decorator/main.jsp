<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Hinet vCPE:<sitemesh:write property="title"/></title>

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=PT+Sans"
	rel="stylesheet" type="text/css">

<!-- CSS -->
<link href="../resources/css/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
	
<!-- Custom CSS -->
<link href="../resources/css/yic-style.css" rel="stylesheet">
<link href="../resources/css/index.css" rel="stylesheet">
<link href="<c:url value="../resources/css/bootstrap-table.css"/>" rel="stylesheet">

<script src='<c:url value='/resources/js/jquery/jquery.min.js' />'></script>
<script src='<c:url value='/resources//js/bootstrap/bootstrap.min.js' />'></script>
<script src='<c:url value='/resources//js/bootstrap/bootstrap-table.js' />'></script>
<script src='<c:url value='/resources/js/bootbox/bootboxForm.js' />'></script>
<script src='<c:url value='/resources/js/validator/validator.js' />'></script>
<script src='<c:url value='/resources/js/chart/Chart.min.js' />'></script>

</head>

<body class="yic">
	<div id="app">
		<div class="container-fluid">
			<!-- Header
  ================================================== -->
			<header class="navbar navbar-fixed-top">
			<div class="navbar-header">
				<!-- Brand -->
				<a href="#" class="navbar-brand">
					<h3 style="color: white">Hinet vCPE</h3>
				</a>

			</div>
			<div class="header-box">
				<div class="user-tools pull-right">
					<ul class="nav nav-pills pull-right">
						<li class="warning hide"><a href="#"
							data-show-pane="WarnLogs"> <i
								class="glyphicon glyphicon-fire text-danger blink"></i>
						</a></li>
						<li class="dropdown nav-item-my"><a href="#"
							data-toggle="dropdown" data-title="username"
							title="Administrator"> <i class="yic-icon-user"></i>
						</a>
							<ul class="dropdown-menu pull-right">
								<li><a href="../login/logout" title="登出" data-action="logout"> 登出 </a></li>
							</ul></li>
					</ul>
				</div>
			</div>
			</header>
			<!-- Contents
  ================================================== -->
			<div class="main-container">
				<!-- Sidebar Menu
    ================================================== -->
				<div class="ps-container yic-left-side">
					<div class="yic-left-nav">
						<ul class="nav nav-stacked">
							<li class=""><a href="../internetSetting/index"
								title="Internet Setting" data-show-pane="dashboardPane"> <i
									class="yic-icon-th"></i> <span>網路設定</span>
							</a></li>
							<li class=""><a href="../gatewaySwitch/index"
								title="Gateway Switch" data-show-pane="dashboardPane"> <i
									class="yic-icon-th"></i> <span>Gateway切換</span>
							</a></li>
							<li class=""><a href="../dashboard/index"
								title="Dashboard" data-show-pane="dashboardPane"> <i
									class="yic-icon-th"></i> <span>Dashboard</span>
							</a></li>
						</ul>
					</div>
				</div>
				<!-- Content Panes
    ================================================== -->
				<div class="container-fluid tab-content yic-contents">
					<div class="tab-pane fade in active" style="display: block;">
						<div class="row">
							<sitemesh:write property="body"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>

</html>