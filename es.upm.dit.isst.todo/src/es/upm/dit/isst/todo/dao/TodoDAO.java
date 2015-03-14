package es.upm.dit.isst.todo.dao;

import java.util.Date;
import java.util.List;

import es.upm.dit.isst.todo.model.Todo;

public interface TodoDAO {

	public List<Todo> listTodos();
	public void add (String userId, String summary, String description, Date deadline, String url);
	public List<Todo> getTodos(String userId);
	public void remove (long id);
	public List<String> getUsers();
	
}
