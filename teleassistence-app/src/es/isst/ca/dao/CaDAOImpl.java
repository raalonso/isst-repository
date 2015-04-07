package es.isst.ca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.isst.ca.model.Alarma;
import es.isst.ca.model.Usuario;

public class CaDAOImpl implements CaDAO {
	
	private static CaDAOImpl instance;

	private CaDAOImpl() {
	}
	public static CaDAOImpl getInstance(){
		if (instance == null)
				instance = new CaDAOImpl();
		return instance;
	}

	@Override
	public List<Alarma> listAlarmas() {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select m from Alarma m");
		List<Alarma> alarmas = q.getResultList();
		return alarmas;
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
	public void add(String userId, String tipo, String apellidos,
			String nombre) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Alarma alarma = new Alarma(userId, tipo, apellidos, nombre);
			em.persist(alarma);
			em.close();
	}

	}

	@Override
	public List<Alarma> getAlarmas(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
					.createQuery("select t from Alarma t where t.author =:userId");
		q.setParameter("userId", userId);
		List<Alarma> alarmas = q.getResultList();
		return alarmas;
	}

	@Override
	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Alarma alarma = em.find(Alarma.class, id);
			em.remove(alarma);
		} finally {
			em.close();
		}

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
	public Alarma getAlarmaById(long id) {
		EntityManager em = EMFService.get().createEntityManager();
			Alarma alarma = em.find(Alarma.class, id);
			return alarma;
	}
	
	@Override
	public Usuario getUsuarioById(long id) {
		EntityManager em = EMFService.get().createEntityManager();
			Usuario usuario = em.find(Usuario.class, id);
			return usuario;
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
	public void addUsuario(String apellidos, String nombre, String nacimiento,
			String dni, String sexo, String telefono, String movil, String domicilio,
			String cp, String localidad, String provincia, String datos) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Usuario usuario = new Usuario(apellidos, nombre, nacimiento,
					dni, sexo, telefono, movil, domicilio,
				    cp, localidad, provincia, datos);
			em.persist(usuario);
			em.close();
	}
		
	}

}