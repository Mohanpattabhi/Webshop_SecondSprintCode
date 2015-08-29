package com.sogeti.webshop.dao;

import com.sogeti.webshop.model.User;
import com.sogeti.webshop.model.UserRole;

public interface RegistrationDAO {

	public void addUser(User user);

	public UserRole getUserRole(String roleName);

	public Boolean getUser(User user);

	public User getUserByUserName(String userName);
}
