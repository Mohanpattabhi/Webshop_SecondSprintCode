package com.sogeti.webshop.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sogeti.webshop.dao.RegistrationDAO;
import com.sogeti.webshop.model.User;
import com.sogeti.webshop.model.UserRole;

@Named("registrationDAO")
@Stateless
public class RegistrationDAOImpl implements RegistrationDAO {
	@PersistenceContext(unitName = "webShopPU")
	EntityManager em;

	public void addUser(User user) {
		em.merge(user);
	}

	public UserRole getUserRole(String roleName) {
		Query query = em
				.createQuery("from UserRole ur where ur.roleName=:roleName");
		query.setParameter("roleName", roleName);
		List<UserRole> userRoles = query.getResultList();
		return userRoles.get(0);
	}

	public Boolean getUser(User user) {
		Boolean userPresent = false;
		Query query = em
				.createQuery("from User u where u.userName=:userName AND  u.passWord=:passWord");
		query.setParameter("userName", user.getUserName());
		query.setParameter("passWord", user.getPassWord());
		List<User> users = query.getResultList();
		if (null != users && users.size() > 0) {
			userPresent = true;
		}
		return userPresent;

	}

	public User getUserByUserName(String userName) {
		Query query = em
				.createQuery("from User ur where ur.userName=:userName");
		query.setParameter("userName", userName);
		List<User> users = query.getResultList();
		return users.get(0);
	}

}
