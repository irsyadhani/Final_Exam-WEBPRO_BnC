<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Music TC</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="#" class="navbar-brand"> Music TC </a>
			</div>

			<ul class="navbar-nav">
				<li>
					<a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/list_instrument"	class="nav-link">Instrument</a>
				</li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${instrument != null}">
					<form action="update_instrument" method="post">
				</c:if>
				<c:if test="${instrument == null}">
					<form action="insert_instrument" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${instrument != null}">
            			Edit Instrument
            		</c:if>
						<c:if test="${instrument == null}">
            			Add New Instrument
            		</c:if>
					</h2>
				</caption>

				<c:if test="${instrument != null}">
					<input type="hidden" name="id" value="<c:out value='${instrument.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Instrument Name</label> <input type="text"
						value="<c:out value='${instrument.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Instrument Price</label> <input type="text"
						value="<c:out value='${instrument.price}' />" class="form-control"
						name="price">
				</fieldset>

				<fieldset class="form-group">
					<label>Instrument Status</label> <input type="text"
						value="<c:out value='${instrument.status}' />" class="form-control"
						name="status">
				</fieldset>
				
				<a href="<%=request.getContextPath()%>/list_instrument" class="btn btn-light">Back</a>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
