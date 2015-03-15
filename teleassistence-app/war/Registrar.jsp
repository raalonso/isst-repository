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
				<div style="float: left;" class="headline">Formulario de registro</div>
				<div style="float: right;">
					<a
						href="<c:url value="/main"/>"><c:out value="Home"/></a>
				</div>
				<br><br>
				<form action="/registrarUsuario" method="post" accept-charset="utf-8">
					<table BORDER=0>
						<tr>
							<td>Nombre</td>
							<td><input type=text name="nombre"></td>
							<td>Nacimiento</td>
							<td><input type=date name="nacimiento"></td>
						</tr>
						<tr>
							<td>Apellidos</td>
							<td><input type=text name="apellido1"></td>
							<td><input type=text name="apellido2"></td>
						</tr>
						<tr>
							<td>DNI/NIF</td>
							<td><input type=text name="dni"></td>
							<td>Sexo</td>
							<td>Hombre <input type=radio name="sexo" value="M"><br>
								Mujer <input type=radio name="sexo" value="F">
							</td>
						</tr>
						<tr>
							<td>Teléfono</td>
							<td><input type="number" name="telefono"></td>
							<td>Móvil</td> 
							<td><input type="number" name="movil"></td>
						</tr>
						<tr>
							<td>Domicilio</td>
							<td><input type="text" name="domicilio"></td>
							<td>CP</td> 
							<td><input type="number" name="cp"></td>
						</tr>
						<tr>
							<td>Localidad</td>
							<td><input type="text" name="localidad"></td>
							<td>Provincia</td> 
							<td>
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
							</td>
						</tr>
						<tr>
							<td>Comentarios</td>
							<td>
								<textarea rows="3" name="comentarios">Enfermedades, alergias, datos adicionales</textarea>
							</td>
						</tr>
						<tr>
						<td colspan="2" align="right"><input type="submit"
									value="Finalizar registro" /></td>
							</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>