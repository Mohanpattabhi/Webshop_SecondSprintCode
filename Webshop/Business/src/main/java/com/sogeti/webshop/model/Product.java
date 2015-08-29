package com.sogeti.webshop.model;

import java.io.Serializable;
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
import javax.persistence.Transient;

@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "WEBSHOPSEQ", sequenceName = "WEBSHOPSEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WEBSHOPSEQ")
	@Column(name = "ID", nullable = false)
	private Integer productId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PRICE")
	private String price;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_CATEGORY_ID")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ORDER_ID")
	private Orders order;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductOrders> productOrders;

	@Transient
	private boolean selected;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public List<ProductOrders> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(List<ProductOrders> productOrders) {

		this.productOrders = productOrders;

		this.productOrders = productOrders;

		for (ProductOrders productOrder : productOrders) {
			productOrder.setProduct(this);

		}

	}

}
