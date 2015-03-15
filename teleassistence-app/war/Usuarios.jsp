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
			<div style="float: left;" class="headline">Listado de Usuarios</div>
			<div style="float: right;">
					<a
						href="<c:url value="/main"/>"><c:out value="Home"/></a>
		</div>
	</div>
	<table>
			<c:forEach items="${usuarios}" var="usuario">
				<tr>
					<td><c:out value="${usuario.apellidos}, ${usuario.nombre}" /></td>
					<td><a class="done"
							href="<c:url value="/info?id=${usuario.id}"
/>">Info</a></td>
					<td><a class="done"
							href="<c:url value="/editar?id=${usuario.id}"
/>">Editar</a></td>
					<td><a class="done"
							href="<c:url value="/borrar?id=${usuario.id}"
/>">Borrar</a></td>
				</tr>
			</c:forEach>
		</table>

</body>
</html>