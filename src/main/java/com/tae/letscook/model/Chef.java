package com.tae.letscook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    public Chef(){}

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

}
