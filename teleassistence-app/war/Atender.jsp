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
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
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
				<h2><strong>${alarm.name}</strong></h2>
				<br>
				<h3><i class="fa fa-user"></i> Datos personales y de contacto</h3>
				<br>
				<table class="table table-striped">
					<tr>
						<th></th>
						<th class="text-center">Nombre completo</th>
						<th class="text-center">Teléfono de contacto</th>
					</tr>
					<tr class="danger">
						<td><strong>PACIENTE</strong></td>
						<td><strong>${alarm.originator}</strong></td>
						<td><strong><font color="green">612 345 678</strong</font></td>
					</tr>
					<tr>
						<td><strong>Pariente 1</strong></td>
						<td>Apellidos, Nombre</td>
						<td>612 345 678</td>
					</tr>
					<tr>
						<td><strong>Pariente 2</strong></td>
						<td>Apellidos, Nombre</td>
						<td>612 345 678</td>
					</tr>					
					<tr>
						<td><strong>Vecino 1</strong></td>
						<td>Apellidos, Nombre</td>
						<td>612 345 678</td>
					</tr>
					<tr>
						<td><strong>Vecino 2</strong></td>
						<td>Apellidos, Nombre</td>
						<td>612 345 678</td>
					</tr>											
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 text-center">
				<br>
				<h3><i class="fa fa-hospital-o"></i> Centros de Emergencia más cercanos</h3>
				<br>
				<div>
					<i class="fa fa-h-square"></i> Nombre del Centro de Emergencia | <font color="red">912 345 678</font><br>
					<i class="fa fa-h-square"></i> Nombre del Centro de Emergencia | <font color="red">912 345 678</font>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12 text-center">
				<br>
				<h3><i class="fa fa-info-circle"></i> Sensores Disparados</h3>
				<br>
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
		<div class="row">
			<div class="col-lg-6 text-center">
			<br>
				<h3><i class="fa fa-map-marker"></i> Localización</h3>
				<script src="http://maps.googleapis.com/maps/api/js"></script>
				<script>
			        function initialize() {
						var mapProp = {
							center : new google.maps.LatLng(${alarm.location[0]}, ${alarm.location[1]}),
							zoom : 16,
							mapTypeId : google.maps.MapTypeId.ROADMAP
						};
						var map = new google.maps.Map(document.getElementById("googleMap"),
								mapProp);
						var marker = new google.maps.Marker({
						      position: new google.maps.LatLng(${alarm.location[0]}, ${alarm.location[1]}),
						      map: map,
						      title: 'Aquí!'
						  });
					}
					google.maps.event.addDomListener(window, 'load', initialize);
				</script>
				<div id="googleMap" style="width:500px;height:380px;border-style:solid;border-width:1px;"></div>
			</div>
			<div class ="col-lg-6 text-center">
			<br>
				<h3><i class="fa fa-user-md"></i> Enfermedades, alergias y más datos</h3>
				<br>
				<div>
					<ul class="text-left">
						<li><i class="fa fa-medkit"></i> Enfermedad 1</li>
						<li><i class="fa fa-medkit"></i> Enfermedad 2</li>
					</ul>
				</div>
				<br>
				<div>
					<h5>Historial Clínico... Alergias...</h5>
				</div>
				<br>
				<br>
				<div>
					<button class="btn btn-success btn-lg">
						<a href="<c:url value="/done?id=${alarm.id}"/>">FINALIZAR ALARMA</a>
					</button>
				</div>
			</div>
		</div>
		<br>

	</div>
</body>

</html>