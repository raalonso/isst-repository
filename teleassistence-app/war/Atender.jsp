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
			<div style="float: left;" class="headline">Alarma
				${alarma.tipo}</div>
				<div style="float: right;">
					<a
						href="<c:url value="/main"/>"><c:out value="Home"/></a>
				</div>
		</div>
	</div>
	<br />
	<div style="float: left;">${alarma.apellidos}, ${alarma.nombre}     612 345 678</div>
	<br />
	<br />Apellidos, Nombre (tipo pariente) 612 345 678
	<br />Apellidos, Nombre (tipo pariente) 612 345 678
	<br /><br />Apellidos, Nombre (vecino 1) 612 345 678
	<br />Apellidos, Nombre (vecino 2) 612 345 678
	<br />
	<h2>Centro de Emergencia más cercano</h2>
	<div> Nombre Centro de Emergencia 912 345 678</div>
	<br />
	<h2>Sensores disparados</h2>
	<div>-Sensor 1 (nombre)</div><div> valor </div>
	<br /><div>-Sensor 2 (nombre)</div><div> valor </div>
	<br />
	<h2>Enfermedades, alergias y datos adicionales</h2>
	<div>enfermedades graves o crónicas, historial clínico</div>
	<br />
	<h2>Localización</h2>
	<script src="http://maps.googleapis.com/maps/api/js"></script>
	<script>
        function initialize() {
			var mapProp = {
				center : new google.maps.LatLng(40.415363, -3.707398),
				zoom : 14,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			var map = new google.maps.Map(document.getElementById("googleMap"),
					mapProp);
		}
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>
	<div id="googleMap" style="width:500px;height:380px;"></div>
	<br />
<a
	href="<c:url value="/done?id=${alarma.id}"/>"><c:out value="Finalizar"/></a>
		
</body>

</html>