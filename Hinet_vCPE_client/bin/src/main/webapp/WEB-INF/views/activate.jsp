<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not active}">
<div class="jumbotron">
	<h1>Active your service!</h1>
	<p></p>
	<p>
		<a class="btn btn-primary btn-lg" href="#" role="button">Active</a>
	</p>
</div>
</c:if>
<c:if test="${active}">
<div class="jumbotron">
	<h1>Your service is actvied!</h1>
	
</div>
</c:if>