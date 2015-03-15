<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<!DOCTYPE html>


<html>
	<head>
		<title>Centro de Atención</title>
		<link rel="stylesheet" type="text/css" href="css/main.css" />
		<meta charset="utf-8">
	</head>
	<body>
	
		<div style="width: 100%;">
		<div class="line"></div>
		<div class="topLine">
			<div style="float: left;" class="headline">Info del usuario</div>
			<div style="float: right;">
					<a
						href="<c:url value="/main"/>"><c:out value="Home"/></a>
		</div>
		<br /><br />	

		<table>
			<tr>
							<td>Nombre</td>
							<td><c:out value="${usuario.nombre}" /></td>
							<td>Nacimiento</td>
							<td><c:out value="${usuario.nacimiento}" /></td>
						</tr>
						<tr>
							<td>Apellidos</td>
							<td><c:out value="${usuario.apellidos}" /></td>
						</tr>
						<tr>
							<td>DNI/NIF</td>
							<td><c:out value="${usuario.dni}" /></td>
							<td>Sexo</td>
							<td><c:out value="${usuario.sexo}" /></td>
						</tr>
						<tr>
							<td>Teléfono</td>
							<td><c:out value="${usuario.telefono}" /></td>
							<td>Móvil</td> 
							<td><c:out value="${usuario.movil}" /></td>
						</tr>
						<tr>
							<td>Domicilio</td>
							<td><c:out value="${usuario.domicilio}" /></td>
							<td>CP</td> 
							<td><c:out value="${usuario.cp}" /></td>
						</tr>
						<tr>
							<td>Localidad</td>
							<td><c:out value="${usuario.localidad}" /></td>
							<td>Provincia</td> 
							<td><c:out value="${usuario.provincia}" /></td>
						</tr>
						<tr>
							<td>Comentarios</td>
							<td><c:out value="${usuario.datos}" /></td>
						</tr>
						<tr>
							<td>Listado de sendores</td>
							<td>Sensor X: valor</td>
							<td>Sensor Y: valor</td>
							<td>Sensor Z: valor</td>
							<td>...</td>
						</tr>
		</table>
		
	</body>
</html>