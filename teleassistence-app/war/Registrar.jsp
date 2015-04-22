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
     	<form action="/registrarUsuario" method="post" accept-charset="utf-8">
     	<div class="row">
     		<h3 class="text-center">Formulario de Registro</h3>
				<div class="col-lg-4 text-center">
					<div class="form-group">
						<label for="nombre">Nombre del Paciente</label>
						<input type="text" class="form-control" name="nombre">
					</div>
					<div class="form-group">
						<label for="nacimiento">Fecha de Nacimiento</label>
						<input type="date" class="form-control" name="nacimiento">
					</div>
					<div class="form-group">
						<label for="domicilio">Domicilio</label>
						<input type="text" class="form-control" name="domicilio">
					</div>	
					<div class="form-group">
						<label for="provincia">Provincia</label></br>
						<select name="provincia">
									<option VALUE="Albacete">Albacete</option>
									<option VALUE="Alicante">Alicante</option>
									<option VALUE="Almeria">Almería</option>
									<option VALUE="Araba">Araba</option>
									<option VALUE="Asturias">Asturias</option>
									<option VALUE="Avila">Ávila</option>
									<option VALUE="Aadajoz">Badajoz</option>
									<option VALUE="Islas baleares">Islas Baleares</option>
									<option VALUE="Barcelona">Barcelona</option>
									<option VALUE="Bizkaia">Bizkaia</option>
									<option VALUE="Burgos">Burgos</option>
									<option VALUE="Caceres">Cáceres</option>
									<option VALUE="Cadiz">Cádiz</option>
									<option VALUE="Cantabria">Cantabria</option>
									<option VALUE="Castellon">Castellón</option>
									<option VALUE="Ciudad Real">Ciudad Real</option>
									<option VALUE="Cordoba">Córdoba</option>
									<option VALUE="Coruna">Coruña</option>
									<option VALUE="Cuenca">Cuenca</option>
									<option VALUE="Gipuzkoa">Gipuzkoa</option>
									<option VALUE="Girona">Girona</option>
									<option VALUE="Granada">Granada</option>
									<option VALUE="Guadalajara">Guadalajara</option>
									<option VALUE="Huelva">Huelva</option>
									<option VALUE="Huesca">Huesca</option>
									<option VALUE="Jaen">Jaén</option>
									<option VALUE="Leon">León</option>
									<option VALUE="Lleida">Lleida</option>
									<option VALUE="Lugo">Lugo</option>
									<option VALUE="Madrid">Madrid</option>
									<option VALUE="Malaga">Málaga</option>
									<option VALUE="Murcia">Murcia</option>
									<option VALUE="Navarra">Navarra</option>
									<option VALUE="Ourense">Ourense</option>
									<option VALUE="Palencia">Palencia</option>
									<option VALUE="Las Palmas">Las Palmas</option>
									<option VALUE="Pontevedra">Pontevedra</option>
									<option VALUE="La Rioja">La Rioja</option>
									<option VALUE="Salamanca">Salamanca</option>
									<option VALUE="Santa Cruz de Tenerife">Santa Cruz de Tenerife</option>
									<option VALUE="Segovia">Segovia</option>
									<option VALUE="Sevilla">Sevilla</option>
									<option VALUE="Soria">Soria</option>
									<option VALUE="Tarragona">Tarragona</option>
									<option VALUE="Teruel">Teruel</option>
									<option VALUE="Toledo">Toledo</option>
									<option VALUE="Valencia">Valencia</option>
									<option VALUE="Valladolid">Valladolid</option>
									<option VALUE="Zamora">Zamora</option>
									<option VALUE="Zaragoza">Zaragoza</option>
									<option VALUE="Ceuta">Ceuta</option>
									<option VALUE="Melilla">Melilla</option>
								</select>
					</div>			
				</div>
				<div class="col-lg-4 text-center">
					<div class="form-group">
						<label for="apellido1">Primer Apellido</label>
						<input type="text" class="form-control" name="apellido1">
					</div>
					<div class="form-group">
						<label for="sexo">Sexo</label><br>
						<input type=radio name="sexo" value="M"> Hombre <input type=radio name="sexo" value="F"> Mujer
					</div>
					<div class="form-group">
						<label for="localidad">Localidad</label>
						<input type="text" class="form-control" name="localidad">
					</div>
					<div class="form-group">
						<label for="cp">Código Postal</label>
						<input type="number" class="form-control" name="cp">
					</div>
					<div class="form-group">
						<label for="IMEI">IMEI</label>
						<input type="text" class="form-control" name="IMEI">
					</div>
				</div>
				<div class="col-lg-4 text-center">
					<div class="form-group">
						<label for="apellido2">Segundo Apellido</label>
						<input type="text" class="form-control" name="apellido2">
					</div>
					<div class="form-group">
						<label for="dni">DNI/NIF</label>
						<input type="text" class="form-control" name="dni">
					</div>
					<div class="form-group">
						<label for="telefono">Teléfono</label>
						<input type="number" class="form-control" name="telefono">
					</div>
					<div class="form-group">
						<label for="movil">Móvil</label>
						<input type="number" class="form-control" name="movil">
					</div>					
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="form-group">
						<label for="datos">Comentarios</label><br>
						<textarea style="width:100%;"rows="3" name="datos">Enfermedades, alergias, datos adicionales</textarea>
					</div>
					<div class="form-group">
						<label for="persona">Tipo</label></br>
						<select name="persona">
									<option VALUE="Pariente">Pariente</option>
									<option VALUE="Cuidador">Cuidador</option>
									<option VALUE="Vecino">Vecino</option>
						</select>
					</div>	
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 text-center">
					<div class="form-group">
						<label for="pnombre">Nombre</label>
						<input type="text" class="form-control" name="pnombre">
					</div>
				</div>
				<div class="col-lg-4 text-center">
					<div class="form-group">
						<label for="papellido1">Primer Apellido</label>
						<input type="text" class="form-control" name="papellido1">
					</div>
				</div>
				<div class="col-lg-4 text-center">
					<div class="form-group">
						<label for="papellido2">Segundo Apellido</label>
						<input type="text" class="form-control" name="papellido2">
					</div>
				</div>	
			</div>
			<div class="row">
				<div class="col-lg-4 text-center">
					<div class="form-group">
						<label for="pmovil">Móvil</label>
						<input type="number" class="form-control" name="pmovil">
					</div>
				</div>
				<div class="col-lg-4 text-center">
					<div class="form-group">
						<label for="pdni">DNI</label>
						<input type="text" class="form-control" name="pdni">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="form-group">
						<input class="btn btn-success btn-lg" type="submit" value="Finalizar registro" />
					</div>
				</div>
			</div>
			</form>										
		</div>
	</body>
</html>