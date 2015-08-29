package com.sogeti.webshop.business;

import com.sogeti.webshop.model.User;

public interface RegistrationService {

	public void addUser(User user);

	public Boolean validateLogin(User user);

}
