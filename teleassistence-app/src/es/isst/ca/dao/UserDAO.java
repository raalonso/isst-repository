package es.isst.ca.dao;

import java.util.List;

import es.isst.ca.model.Usuario;

public interface UserDAO {
	public List<String> getUsers();
	public List<Usuario> listUsuarios();
	public void addUsuario (String apellidos, String nombre, String nacimiento, 
			String dni, String sexo, String telefono, String movil, String domicilio, String cp,
			String localidad, String provincia, String datos, String IMEI, String persona,
			String pnombre, String papellidos, String pmovil, String pdni);
	public Usuario getUsuarioById (long id);
	public void removeUsuario (long id);
	public Usuario getUsuarioByIMEI(String IMEI);
}
