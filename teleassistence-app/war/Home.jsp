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
				<div style="float: left;" class="headline">Lista de alarmas pendientes</div>
				<div style="float: right;">
					<a
						href="<c:url value="/usuarios"/>"><c:out value="Usuarios"/></a>
					<a
						href="<c:url value="/registrar"/>"><c:out value="Registro"/></a>
					<a
						href="<c:url value="${url}"/>"><c:out value="${urlLinktext}"/></a>
					<c:if test="${user != null}"><c:out value="${user.nickname}"/></c:if>
				</div>
			</div>
		</div>
		<c:choose>
				<c:when test="${user != null}">
		<div style="clear: both;" />
		Tienes ${fn:length(alarmas)} alarmas pendientes.
		
		<table>
			<tr>
				<th>Alarma</th>
				<th>Apellidos</th>
				<th>Nombre</th>	
			</tr>
			
			<c:forEach items="${alarmas}" var="alarma">
				<tr>
					<td><c:out value="${alarma.tipo}" /></td>
					<td><c:out value="${alarma.apellidos}" /></td>
					<td><c:out value="${alarma.nombre}" /></td>
					<td><a class="done"
							href="<c:url value="/atender?id=${alarma.id}"
/>">Atender</a></td>
				</tr>
			</c:forEach>
		</table>
		
		<hr />
		
		<div class="main">
		
			<div class="headline">Nueva alarma</div>

					<form action="/new" method="post" accept-charset="utf-
8">
						<table>
							<tr>
								<td><label
for="tipo">Tipo</label></td>
								<td><input type="text"
name="tipo" id="tipo" size="65" /></td>
							</tr><td><label
for="apellidos">Apellidos</label></td>
								<td><input type="text"
name="apellidos" id="apellidos" size="65" /></td>
							</tr><td><label
for="nombre">Nombre</label></td>
								<td><input type="text"
name="nombre" id="nombre" size="65" /></td>
							</tr>
						
								<td colspan="2"
align="right"><input type="submit"
									value="Create" /></td>
							</tr>
						</table>
					</form>
				</c:when>
				<c:otherwise>
		Please login with your Google account
				</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>