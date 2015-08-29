package com.sogeti.webshop.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "WEBSHOPSEQ", sequenceName = "WEBSHOPSEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WEBSHOPSEQ")
	@Column(name = "ID", nullable = false)
	private Integer orderId;

	@Column(name = "ORDER_NO")
	private Integer orderNo;

	@Column(name = "ORDER_DATE")
	private Timestamp orderDate;

	@Column(name = "SHIPPING_ADRESS")
	private String shippingAddress;

	@Column(name = "SHIPPING_CITY")
	private String shippingCity;

	@Column(name = "STATUS")
	private String status;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductOrders> productOrders;

	/*
	 * @OneToMany(mappedBy="order",cascade =
	 * CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER) private
	 * List<Product> products;
	 */

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ProductOrders> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(List<ProductOrders> productOrders) {
		this.productOrders = productOrders;

		this.productOrders = productOrders;

		for (ProductOrders productOrder : productOrders) {
			productOrder.setOrder(this);

		}
	}

	/*
	 * public List<Product> getProducts() { return products; }
	 * 
	 * public void setProducts(List<Product> products) { this.products =
	 * products; }
	 */

}
