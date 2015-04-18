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
                    <li><a href="<c:url value="/usuarios"/>"><c:out value="Usuarios"/></a></li>
                    <li class="active"><a href="<c:url value="/registrar"/>"><c:out value="Registro"/></a></li>
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
				<h3>Información del Usuario</h3>
				<br>
				<table class="table table-striped">
					<tr>
						<th class="text-center">Nombre</th>
						<th class="text-center">Apellidos</th>
						<th class="text-center">Fecha de Nacimiento</th>
						<th class="text-center">Sexo</th>
						<th class="text-center">DNI/NIF</th>
					</tr>
					<tr>
						<td><c:out value="${usuario.nombre}"/></td>
						<td><c:out value="${usuario.apellidos}"/></td>
						<td><c:out value="${usuario.nacimiento}"/></td>
						<td><c:out value="${usuario.sexo}"/></td>
						<td><c:out value="${usuario.dni}"/></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="col-lg-6 text-center">
		<h5><strong>Dirección Postal</strong></h5>
				<ul>
					<li>Domicilio: <c:out value="${usuario.domicilio}"/></li>
					<li>Localidad: <c:out value="${usuario.localidad}"/></li>
					<li>Provincia: <c:out value="${usuario.provincia}"/></li>
					<li>C.P: <c:out value="${usuario.cp}"/></li>
				</ul>
				<c:out value="${usuario.datos}" />
				<br>
				
				<h5><i class="fa fa-map-marker"></i><strong>Localización</strong></h5>
				<script src="http://maps.googleapis.com/maps/api/js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script>
	function initialize() {
		var mapProp = {
			center : new google.maps.LatLng(${latlon.latitude}, ${latlon.longitude}),
			zoom :16,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
		var marker = new google.maps.Marker({
			position: new google.maps.LatLng(${latlon.latitude}, ${latlon.longitude}),
			map: map
		});
		var latlng = ""+${latlon.latitude}+", "+${latlon.longitude}+"";
		var url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latlng + "&sensor=false";
		var infowindow = new google.maps.InfoWindow();
		$.getJSON(url, function (data) {
			infowindow.setContent(data.results[0].formatted_address.toString());
			//alert(adress);
		});
		infowindow.open(map, marker);
	}
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
				<div id="googleMap" style="width:540px;height:380px;border-style:solid;border-width:1px;"></div>
				<br>
				
				<h5><strong>Lista de Alarmas Atendidas</strong></h5>
				<table class="table table-striped table-condensed table-bordered">
					<tr>
						<th class="text-center">Nombre</th>
						<th class="text-center">Tipo</th>
						<th class="text-center">Severidad</th>
						<th class="text-center">Sello de tiempo</th>
					</tr>
					<c:forEach items="${alarms}" var="alarm">
						<tr>
							<td><c:out value="${alarm.name}" /></td>
							<td><c:out value="${alarm.type}" /></td>
							<td><c:out value="${alarm.severity}" /></td>
							<td><c:out value="${alarm.timestamp}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
			<div class="col-lg-6 text-center">
				<h5><strong>Comentarios</strong></h5><br>
				<c:out value="${usuario.datos}" />
				<br>
				<h5><strong>Lista de Sensores</strong></h5>
				<table class="table table-striped table-condensed table-bordered">
					<tr>
						<th class="text-center">Sensor</th>
						<th class="text-center">Valor</th>
					</tr>
					<tr>
						<td>Sensor 1</td>
						<td class="danger">Valor Fuera de Rango</td>
					</tr>
					<tr>
						<td>Sensor 2</td>
						<td class="success">Valor dentro de los Rangos correctos</td>
					</tr>
				</table>
			</div>
		</div>
	</div>		
	</body>
</html>