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
				<h2><strong>Información del Usuario</strong></h2>
				<br>
				<h3><i class="fa fa-user"></i> Datos personales y de contacto</h3>
				<br>
				<table class="table table-striped">
					<tr>
						<th class="text-center">Nombre, Apellidos</th>
						<th class="text-center">Fecha de Nacimiento</th>
						<th class="text-center">Sexo</th>
						<th class="text-center">DNI/NIF</th>
						<th class="text-center">Móvil</th>
						<th class="text-center">Fijo</th>
					</tr>
					<tr class="success">
						<td><strong><font color="green"><c:out value="${usuario.nombre}, ${usuario.apellidos}"/></font></strong></td>
						<td><c:out value="${usuario.nacimiento}"/></td>
						<td><c:out value="${usuario.sexo}"/></td>
						<td><c:out value="${usuario.dni}"/></td>
						<td><strong><font color="green"><c:out value="${usuario.movil}"/></font></strong></td>
						<td><c:out value="${usuario.telefono}"/></td>
					</tr>
				</table>
				
				<table class="table table-striped">
					<tr>
						<th class="text-center">Domicilio</th>
						<th class="text-center">Localidad</th>
						<th class="text-center">Provincia</th>
						<th class="text-center">Código Postal</th>
					</tr>
					<tr>
						<td><c:out value="${usuario.domicilio}"/></td>
						<td><c:out value="${usuario.localidad}"/></td>
						<td><c:out value="${usuario.provincia}"/></td>
						<td><c:out value="${usuario.cp}"/></td>
					</tr>
				</table>

				<h3>Información del ${usuario.persona}</h3>
				<br>
				<table class="table table-striped">
					<tr>
						<th class="text-center">Nombre, Apellidos</th>
						<th class="text-center">DNI</th>
						<th class="text-center">Móvil</th>
					</tr>
					<tr>
						<td><c:out value="${usuario.pnombre}, ${usuario.papellidos}"/></td>
						<td><c:out value="${usuario.pdni}"/></td>
						<td><c:out value="${usuario.pmovil}"/></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="col-lg-6 text-center">
				
				<h3><i class="fa fa-map-marker"></i> Localización</h3>
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
				
				<h3><i class="fa fa-list-ul"></i> Lista de Alarmas Atendidas</h3>
				<table class="table table-striped table-condensed table-bordered">
					<tr>
						<th class="text-center">Nombre</th>
						<th class="text-center">Tipo</th>
						<th class="text-center">Severidad</th>
						<th class="text-center">Fecha y hora</th>
					</tr>
					<c:forEach items="${alarms}" var="alarm">
						<tr>
							<td><c:out value="${alarm.name}" /></td>
							<td><c:out value="${alarm.type}" /></td>
							<td><c:out value="${alarm.severity}" /></td>
							<td><c:out value="${alarm.date}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
			<div class="col-lg-6 text-center">
				<h3><i class="fa fa-user-md"></i> Enfermedades, alergias y más datos</h3>
				<div>
					<ul class="text-left">
						<h5><i class="fa fa-medkit"></i> &nbsp; ${usuario.datos}</h5>
					</ul>
				</div>
				<br>
				<h3><i class="fa fa-info-circle"></i> Lista de Sensores de Glucosa</h3>
				<table class="table table-striped table-condensed table-bordered">
					<tr>
						<th class="text-center">Timestamp</th>
						<th class="text-center">Fecha y hora</th>
						<th class="text-center">Valor</th>
					</tr>
					<c:forEach items="${tenglucoses}" var="tenglucose">
						<tr>
							<td><c:out value="${tenglucose.timestamp}" /></td>
							<td><c:out value="${tenglucose.date}" /></td>
							<td><c:out value="${tenglucose.glucose}" /></td>
						</tr>
					</c:forEach>
				</table>
				<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
				<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
				<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
				<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
				<div id="myfirstchart" style="height: 250px;"></div>
				<script type="text/javascript">
			
				var a = [
					<c:forEach items="${glucosesld}" var="glucoseld" varStatus="status">  
    					{Time: ${glucoseld.timestamp},
    					Glucose: ${glucoseld.glucose}}
    				<c:if test="${!status.last}">    
      				,    
    				</c:if>  
    				</c:forEach>  
				];
				new Morris.Line({
					  // ID of the element in which to draw the chart.
					  element: 'myfirstchart',
					  // Chart data records -- each entry in this array corresponds to a point on
					  // the chart.
					  data: a,
					  // The name of the data record attribute that contains x-values.
					  xkey: 'Time',
					  // A list of names of data record attributes that contain y-values.
					  ykeys: ['Glucose'],
					  // Labels for the ykeys -- will be displayed when you hover over the
					  // chart.
					  labels: ['Value']
					});
				</script>
			</div>
		</div>
	</div>		
	</body>
</html>