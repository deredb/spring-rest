package com.dere.rest.restspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="authors")
public class Author {

	@Id
	@Column(name="author_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotNull(message="Provide valid first name")
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	@NotNull(message="Provide valid first name")
	private String lastName;
	
	public Author() {
		
	}

	public Author(Integer id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
