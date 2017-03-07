<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class='container-fluid'>
	<h3>Dashboard</h3>

	<table id='tableDashboard' class="table table-striped table-bordered"
		cellspacing="0" width="100%">

	</table>
	<div class="row">
    	<div class="col-xs-8 col-sm-6">
                <h2>下載速率統計</h2>
			  	<div>
			    <canvas id="downloadRateChart"> </canvas>
			  	</div>
        </div>
        <div class="col-xs-8 col-sm-6">
                <h2>上傳速率統計</h2>
			  	<div>
			    <canvas id="uploadRateChart"> </canvas>
			  	</div>
        </div>
    </div>
  	


</div>

<!-- including js -->
<script src='<c:url value='/resources/js/dashboard.js' />'></script>
