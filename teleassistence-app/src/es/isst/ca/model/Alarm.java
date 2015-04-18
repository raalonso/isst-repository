package es.isst.ca.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;

@Entity
public class Alarm implements Serializable {

	private static final long serialVersionUID = 4278752646313777210L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private Long id;
	
	private String name;
	private Integer type;
	private String originator;
	private Long timestamp;
	private Integer severity;
	
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private Boolean attended;
	
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private List<Number> location;
	@ApiResourceProperty(name = "location", ignored = AnnotationBoolean.FALSE)
	private UserLocation apiLocation;

	
	public Alarm() {
		super();
		this.attended = Boolean.FALSE;
	}

	public Alarm(String name, Integer type, String originator, Long timestamp,
			Integer severity, List<Number> location) {
		super();
		this.name = name;
		this.type = type;
		this.originator = originator;
		this.timestamp = timestamp;
		this.severity = severity;
		this.attended = Boolean.FALSE;
		this.location = location;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getOriginator() {
		return originator;
	}
	
	public void setOriginator(String originator) {
		this.originator = originator;
	}
	
	public Long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Integer getSeverity() {
		return severity;
	}
	
	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	public Boolean getAttended() {
		return attended;
	}

	public void setAttended(Boolean attended) {
		this.attended = attended;
	}
	
	public List<Number> getLocation() {
		return location;
	}

	public void setLocation(List<Number> location) {
		this.location = location;
	}

	public UserLocation getApiLocation() {
		return apiLocation;
	}
	
	public void setApiLocation(UserLocation apiLocation) {
		this.apiLocation = apiLocation;
		Number lat = apiLocation.latitude;
		Number lon = apiLocation.longitude;
		this.location = Arrays.asList(lat, lon);
	}
	
}
