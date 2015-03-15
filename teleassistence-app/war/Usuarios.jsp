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
                    <li><a href="<c:url value="/"/>">Home</a>
                    <li class="active"><a href="<c:url value="/usuarios"/>"><c:out value="Usuarios"/></a></li>
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
		<div class="row">
			<div class="col-lg-12 text-center">
				<br>
				<h3>Listado de Usuarios</h3>
				<br>
				<table class="table table-striped">
					<tr>
						<th class="text-center">ID</th>
						<th class="text-center">Nombre Completo</th>
						<th></th>
						<th class="text-center">Acciones</th>
						<th></th>
					</tr>
		
					<c:forEach items="${usuarios}" var="usuario">
						<tr>
							<td><c:out value="${usuario.id}"/></td>
							<td><c:out value="${usuario.apellidos}, ${usuario.nombre}" /></td>
							<td><button type="button" class="btn btn-xs"><a href="<c:url value="/info?id=${usuario.id}"/>">Info</a></button></td>
							<td><button type="button" class="btn btn-xs"><a href="<c:url value="/editar?id=${usuario.id}"/>">Editar</a></button></td>
							<td><button type="button" class="btn btn-xs"><a href="<c:url value="/borrar?id=${usuario.id}"/>">Borrar</a></button></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	

</body>
</html>