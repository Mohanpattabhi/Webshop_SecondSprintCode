package com.sogeti.webshop.userweb.backingbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sogeti.webshop.business.RegistrationService;
import com.sogeti.webshop.business.utitlity.Constants;
import com.sogeti.webshop.model.Product;
import com.sogeti.webshop.model.User;

@ManagedBean(name = "registrationBean")
@RequestScoped
public class RegistrationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	RegistrationService registrationService;

	private String firstName;

	private String lastName;

	private String userName;

	private String password;

	private String Address;

	private String city;

	private String email;

	private String state;

	private List<Product> shoppingCart;

	private String country;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String doLogin() {
		String outCome = "login";
		User user = prepareUser(this);
		Boolean userPresent = registrationService.validateLogin(user);
		if (userPresent) {

			ProductBean productBean = (ProductBean) FacesContext
					.getCurrentInstance().getExternalContext().getSessionMap()
					.get("productBean");
			List<Product> products = productBean.getShoppingCart();
			this.setShoppingCart(products);

			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			request.getSession().setAttribute(Constants.LOGGED_IN_USER,
					user.getUserName());
			outCome = "order";
		}

		return outCome;
	}

	public String doRegister() {
		User user = prepareUser(this);
		registrationService.addUser(user);
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().setAttribute(Constants.LOGGED_IN_USER,
				user.getUserName());
		return "order";

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public RegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	private User prepareUser(RegistrationBean registrationBean) {
		User user = new User();
		user.setFirstName(registrationBean.getFirstName());
		user.setLastName(registrationBean.getLastName());
		user.setUserName(registrationBean.getUserName());
		user.setPassWord(registrationBean.getPassword());
		user.setEmail(registrationBean.getEmail());
		user.setAddress(registrationBean.getAddress());
		user.setCity(registrationBean.getCity());
		user.setState(registrationBean.getState());
		user.setCountry(registrationBean.getCountry());
		return user;
	}

	public List<Product> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(List<Product> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public String logOut() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(true)).invalidate();
		return "productDetails";
	}
}