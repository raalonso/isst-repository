<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<!DOCTYPE html>


<html>
	<head>
		<title>Centro de Atención</title>
		<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="css/main.css" />
		<script type="text/javascript" src="<c:url value="/bootstrap/js/jquery-1.11.2.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/bootstrap/js/bootstrap.js" />"></script>
		<meta charset="utf-8">
	</head>
	<body>

	<nav class="navbar navbar-default">
  		<div class="container-fluid">
	    	<div class="navbar-header">
	      		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        	<span class="sr-only">Toggle navigation</span>
	        	<span class="icon-bar"></span>
	        	<span class="icon-bar"></span>
	        	<span class="icon-bar"></span>
	      		</button>
	      		<a class="navbar-brand" href="#">Centro de Atención</a>
	    	</div>
	    	
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="<c:url value="/"/>">Home</a>
                    <li><a href="<c:url value="/usuarios"/>"><c:out value="Usuarios"/></a></li>
                    <li><a href="<c:url value="/registrar"/>"><c:out value="Registro"/></a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                	<li><a><c:if test="${user != null}">Bienvenido, <c:out value="${user.nickname}"/></c:if></a></li>
                	<li> <a href="<c:url value="${url}"/>"><c:out value="${urlLinktext}"/></a></li>
                </ul>
            </div>
        </div>
    </nav>
	
	<div class ="container panel panel-default">
		<c:choose>
			<c:when test="${user != null}">
			
				<div class="row">
					<div class="col-lg-12 text-center">
						<br>
						<h3>Tienes ${fn:length(alarms)} alarmas pendientes</h3>
						<br>
						<table class="table table-striped">
							<tr>
								<th class="text-center">Alarma</th>
								<th class="text-center">Timestamp</th>
								<th class="text-center">Severity</th>
								<th class="text-center">Location</th>
								<th class="text-center"> </th>
							</tr>
				
							<c:forEach items="${alarms}" var="alarm">
								<tr>
									<td><c:out value="${alarm.name}" /></td>
									<td><c:out value="${alarm.timestamp}" /></td>
									<td><c:out value="${alarm.severity}" /></td>
									<td><c:out value="${alarm.location}" /></td>
									<td><button type="button" class="btn btn-success btn-xs"><a href="<c:url value="/atender?id=${alarm.id}" />">Atender</a></button></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<hr />
		
				<div class="row">
					<div class="col-lg-12 text-center">
						<h3>Añadir una nueva alarma DUMMY</h3>
						<br>

						<form action="/new" method="post" accept-charset="utf-8">
							<table class="table">
								<tr>						
									<td><input type="submit" value="Crear DUMMY" /></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
		</c:when>
		<c:otherwise>
			Por favor, inicie sesión con su cuenta de Google.
		</c:otherwise>
		</c:choose>
		</div>
	</body>
</html>