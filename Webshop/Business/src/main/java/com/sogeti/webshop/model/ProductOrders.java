package com.sogeti.webshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_PRODUCT")
public class ProductOrders {
	@Id
	@SequenceGenerator(name = "WEBSHOPSEQ", sequenceName = "WEBSHOPSEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WEBSHOPSEQ")
	@Column(name = "ID", nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Orders order;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	@Column(name = "PRICE")
	private Integer totalPrice;

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}