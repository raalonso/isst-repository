package es.upm.dit.isst.todo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String author;

	private String summary;
	private String description;
	private Date deadline; // added for exercise 3
	private String url;
	boolean finished;

	public Todo(String author, String summary, String description,
			Date deadline, String url) {
		this.author = author;
		this.summary = summary;
		this.description = description;
		this.deadline = deadline;
		this.url = url;
		finished = false;
	}

	public Long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getShortDescription() {
		return summary;
	}

	public void setShortDescription(String shortDescription) {
		this.summary = shortDescription;
	}

	public String getLongDescription() {
		return description;
	}

	public void setLongDescription(String longDescription) {
		this.description = longDescription;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	// Added for Exercise 3
	public Date getDeadline() {
		return deadline;
	}

	// Added for Exercise 3
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

} 
