package es.isst.ca.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import es.isst.ca.dao.EventDAO;
import es.isst.ca.dao.EventDAOImpl;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String apellidos;
	private String nombre;
	private String nacimiento;
	private String dni;
	private String sexo;
	private String telefono;
	private String movil;
	private String domicilio;
	private String cp;
	private String localidad;
	private String provincia;
	private String datos;
	private String IMEI;
	private String persona;
	private String pnombre;
	private String papellidos;
	private String pmovil;
	private String pdni;
	boolean finished;
	String conectado;
	public Usuario(String apellidos, String nombre, String nacimiento, 
			String dni, String sexo, String telefono, String movil, String domicilio, String cp,
			String localidad, String provincia, String datos, String IMEI, String persona, String pnombre, 
			String papellidos, String pmovil, String pdni) {
		this.apellidos = apellidos;
		this.nombre = nombre;
		this.nacimiento = nacimiento;
		this.dni = dni;
		this.sexo = sexo;
		this.telefono = telefono;
		this.movil = movil;
		this.domicilio = domicilio;
		this.cp = cp;
		this.localidad = localidad;
		this.provincia = provincia;
		this.datos = datos;
		this.IMEI = IMEI;
		this.persona = persona;
		this.pnombre = pnombre;
		this.papellidos = papellidos;
		this.pmovil = pmovil;
		this.pdni = pdni;
		finished = false;
		conectado = "danger";
		
	}
	public Long getId() {
		return id;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMovil() {
		return movil;
	}
	public void setMovil(String movil) {
		this.movil = movil;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getDatos() {
		return datos;
	}
	public void setDatos(String datos) {
		this.datos = datos;
	}
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String IMEI) {
		this.IMEI = IMEI;
	}
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
	}
	public String getPnombre() {
		return pnombre;
	}
	public void setPnombre(String pnombre) {
		this.pnombre = pnombre;
	}
	public String getPapellidos() {
		return papellidos;
	}
	public void setPapellidos(String papellidos) {
		this.papellidos = papellidos;
	}
	public String getPmovil() {
		return pmovil;
	}
	public void setPMovil(String pmovil) {
		this.pmovil = pmovil;
	}
	public String getPdni() {
		return pdni;
	}
	public void setPdni(String pdni) {
		this.pdni = pdni;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public String getConectado() {
		return conectado;
	}
	public void setConectado(String conectado) {
		this.conectado = conectado;
	}
}