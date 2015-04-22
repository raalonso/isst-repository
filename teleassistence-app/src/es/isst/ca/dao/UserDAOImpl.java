package es.isst.ca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.isst.ca.model.Usuario;

public class UserDAOImpl implements UserDAO {

	private static UserDAOImpl instance;

	private UserDAOImpl() {
	}
	public static UserDAOImpl getInstance(){
		if (instance == null)
				instance = new UserDAOImpl();
		return instance;
	}
	
	@Override
	public List<String> getUsers() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select distinct t.author from Alarma t");
		List<String> users = q.getResultList();
		return users;
	}

	@Override
	public List<Usuario> listUsuarios() {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select m from Usuario m");
		List<Usuario> usuarios = q.getResultList();
		return usuarios;
	}

	@Override
	public void addUsuario(String apellidos, String nombre, String nacimiento,
			String dni, String sexo, String telefono, String movil,
			String domicilio, String cp, String localidad, String provincia,
			String datos, String IMEI, String persona, String pnombre, String papellidos,
			String pmovil, String pdni) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Usuario usuario = new Usuario(apellidos, nombre, nacimiento, dni,
					sexo, telefono, movil, domicilio, cp, localidad, provincia,
					datos, IMEI, persona, pnombre, papellidos, pmovil, pdni);
			em.persist(usuario);
			em.close();
		}
	}

	@Override
	public Usuario getUsuarioById(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Usuario usuario = em.find(Usuario.class, id);
		return usuario;
	}

	@Override
	public void removeUsuario(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Usuario usuario = em.find(Usuario.class, id);
			em.remove(usuario);
		} finally {
			em.close();
		}

	}
	@Override
	public Usuario getUsuarioByIMEI(String IMEI) {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select m from Usuario m where m.IMEI = :IMEI");
		q.setParameter("IMEI", IMEI);
		Usuario usuario = (Usuario) q.getSingleResult();
		return usuario;
	}

}
