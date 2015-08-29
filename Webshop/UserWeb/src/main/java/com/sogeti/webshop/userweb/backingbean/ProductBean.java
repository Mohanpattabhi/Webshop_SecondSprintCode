package com.sogeti.webshop.userweb.backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.sogeti.webshop.business.ProductService;
import com.sogeti.webshop.business.utitlity.Constants;
import com.sogeti.webshop.model.Category;
import com.sogeti.webshop.model.Orders;
import com.sogeti.webshop.model.Product;
import com.sogeti.webshop.model.ProductOrders;
import com.sogeti.webshop.model.User;

@ManagedBean(name = "productBean")
@SessionScoped
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ProductService productService;

	private List<Product> productList;

	private List<Category> categories;

	private String value;

	private List<Product> shoppingCartList;

	private List<Product> shoppingCart;

	private boolean selected;

	private double totalPrice;

	private String userName;

	private String password;

	private Orders orderEntity;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public List<Product> getProductList() {
		setProductList(productList);
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public String displayProduct() {

		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String selectedCategory = params.get("selectedCategory");
		List<Product> products = productService.getProducts(Integer
				.valueOf(selectedCategory));
		setProductList(products);
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().setAttribute("productList", products);
		request.getSession().setAttribute("selectedCategoryId",
				selectedCategory);
		this.productList = products;
		return "productDetails";
	}

	public void populateCategories() {
		categories = new ArrayList<Category>();

		List<Category> categoryEntities = productService.getCategories();

		setCategories(categoryEntities);

	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void getSelectedItems() {

		// Get selected items.

		if (getShoppingCartList() == null) {
			shoppingCartList = new ArrayList<Product>();
		}
		for (Product dataItem : productList) {
			if (dataItem.isSelected()) {

				if (!isDuplicate(shoppingCartList, dataItem.getProductId()))
					shoppingCartList.add(dataItem);
			}
		}

	}

	public String doLogin() {
		String loginSuccess = "success";
		String uri = "";
		System.out.println("Entered Login");
		if (loginSuccess.equals("success")) {

			uri = "displayCart";
		}
		return uri;
	}

	public String doOrder() {
		String uri = "invoiceDisplay";
		Orders order = prepareOrderEntity(this.getShoppingCart());
		Orders orderEntity = productService.createOrders(order);
		this.setOrderEntity(orderEntity);
		return uri;
	}

	public List<Category> getCategories() {
		categories = new ArrayList<Category>();

		List<Category> categoryEntities = productService.getCategories();

		categories.addAll(categoryEntities);
		return categories;
	}

	public String displayOrder() {

		return "login";

	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Product> getShoppingCartList() {
		return shoppingCartList;
	}

	public void setShoppingCartList(List<Product> shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}

	public List<Product> getShoppingCart() {
		return shoppingCartList;

	}

	public void setShoppingCart(List<Product> shoppingCart) {

		this.shoppingCart = shoppingCart;
	}

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

	public double getTotalPrice() {

		List<Product> productList = getShoppingCart();
		if (productList != null)
			if (productList.size() > 0) {
				for (Product product : productList) {
					this.totalPrice = this.totalPrice
							+ Double.parseDouble(product.getPrice());
				}
			}
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isDuplicate(List<Product> productList, Integer productId) {
		Boolean dupicateValue = false;
		if (productList != null)
			if (productList.size() > 0) {
				for (Product product : productList) {
					if (productId == product.getProductId()) {
						dupicateValue = true;
					}
				}
			}
		return dupicateValue;
	}

	private Orders prepareOrderEntity(List<Product> products) {
		Orders order = new Orders();
		order.setOrderDate(new java.sql.Timestamp(System.currentTimeMillis()));
		order.setOrderNo(0 + (int) Math.random() * 10000);
		order.setStatus(Constants.ORDER_STATUS_SHIPPED);
		User user = new User();

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		if (request.getSession().getAttribute(Constants.LOGGED_IN_USER) != null) {
			userName = (String) request.getSession().getAttribute(
					Constants.LOGGED_IN_USER);
		}

		user.setUserName(userName);
		order.setUser(user);
		List<ProductOrders> productOrders = new ArrayList<ProductOrders>();
		for (Product product : products) {
			ProductOrders productOrder = new ProductOrders();
			productOrder.setOrder(order);
			productOrder.setProduct(product);
			productOrders.add(productOrder);
		}
		order.setProductOrders(productOrders);
		return order;
	}

	public Orders getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(Orders orderEntity) {
		this.orderEntity = orderEntity;
	}

}