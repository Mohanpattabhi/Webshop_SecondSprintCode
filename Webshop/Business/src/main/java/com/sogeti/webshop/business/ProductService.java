package com.sogeti.webshop.business;

import java.util.List;

import com.sogeti.webshop.model.Category;
import com.sogeti.webshop.model.Orders;
import com.sogeti.webshop.model.Product;

public interface ProductService {

	public List<Category> getCategories();

	public List<Product> getProducts(Integer categoryId);

	public Orders createOrders(Orders order);
}
