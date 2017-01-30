package com.andersen.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String login;

	private Set<Cart> carts;

	public Client() {
	}

	public Client(String login) {
		this.login = login;
	}

	@Id
	@Column
	@GeneratedValue(generator = "client_increment")
	@GenericGenerator(name = "client_increment", strategy = "increment")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(unique = true, nullable = false, length = 1)
	public String getLogin() {
		return login;
	}

	public void setLogin(String name) {
		this.login = name;
	}

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

}
