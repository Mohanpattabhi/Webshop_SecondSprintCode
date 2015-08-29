package com.sogeti.webshop.business.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.sogeti.webshop.business.RegistrationService;
import com.sogeti.webshop.business.utitlity.Constants;
import com.sogeti.webshop.dao.RegistrationDAO;
import com.sogeti.webshop.model.User;
import com.sogeti.webshop.model.UserRole;

@Stateless
public class RegistrationServiceImpl implements RegistrationService {

	@Inject
	RegistrationDAO registrationDAO;

	public void addUser(User user) {

		UserRole userRole = registrationDAO
				.getUserRole(Constants.USER_ROLE_USER);
		user.setUserRole(userRole);
		registrationDAO.addUser(user);
	}

	public Boolean validateLogin(User user) {
		Boolean userPresent = false;
		if (registrationDAO.getUser(user)) {
			userPresent = true;
		}
		return userPresent;
	}

}
