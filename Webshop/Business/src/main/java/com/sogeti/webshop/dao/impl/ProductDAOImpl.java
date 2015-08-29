package com.sogeti.webshop.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sogeti.webshop.dao.ProductDAO;
import com.sogeti.webshop.model.Category;
import com.sogeti.webshop.model.Orders;
import com.sogeti.webshop.model.Product;

@Named("productDAO")
@Stateless
public class ProductDAOImpl implements ProductDAO {

	@PersistenceContext(unitName = "webShopPU")
	EntityManager em;

	public List<Category> getCategories() {
		return em.createQuery("from Category").getResultList();
	}

	public List<Product> getProducts(Integer categoryId) {
		Query query = em
				.createQuery("from Product p where p.category.categoryId=:categoryId");
		query.setParameter("categoryId", categoryId);
		List<Product> products = query.getResultList();
		return products;
	}

	public Orders addOrder(Orders order) {
		Orders orderentity = em.merge(order);
		return orderentity;
	}

}
