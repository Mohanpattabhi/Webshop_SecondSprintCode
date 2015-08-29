package com.sogeti.webshop.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLE")
public class UserRole {
	@Id
	@SequenceGenerator(name = "WEBSHOPSEQ", sequenceName = "WEBSHOPSEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WEBSHOPSEQ")
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "ROLE_NAME")
	private String roleName;

	@Column(name = "ROLE_DESCRIPTION")
	private String roleDescription;

	@OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<User> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

}