package com.entities;
// Generated 31 mai 2022, 02:26:55 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "smi", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
public class User implements java.io.Serializable {

	private Integer id;
	private String userName;
	private String password;
	private String nom;
	private String prenom;
	private byte[] picture;
	private Set<Filiere> filieres = new HashSet<Filiere>(0);

	public User() {
	}

	public User(String userName, String password, String nom, String prenom, byte[] picture) {
		this.userName = userName;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.picture = picture;
	}

	public User(String userName, String password, String nom, String prenom, byte[] picture, Set<Filiere> filieres) {
		this.userName = userName;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.picture = picture;
		this.filieres = filieres;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "user_name", unique = true, nullable = false)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "nom", nullable = false)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "prenom", nullable = false)
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Column(name = "picture", nullable = false)
	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Filiere> getFilieres() {
		return this.filieres;
	}

	public void setFilieres(Set<Filiere> filieres) {
		this.filieres = filieres;
	}

}