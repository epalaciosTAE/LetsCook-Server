package com.tae.letscook.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="chef_table")
public class Chef {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="uuid")
 	private String uuid;
	
	@Column(name="name")
    private String name;
	
	@Column(name="email")
    private String email;
	
	@Column(name="picture")
    private String picture;
	
	@OneToMany()
	@JoinColumn(name="chef_uuid",referencedColumnName="uuid")
	private List<Event> events;
    
    public Chef(){}
    
    public Chef(String uuid){
    	this.uuid = uuid;
    }

    public Chef(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public Chef(String uuid, String name, String email, String picture) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
    
    
    public Chef(long id, String uuid, String name, String email, String picture) {
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}

	public void setId(String uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
    
    

}
