package com.atguigu.crm.service;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.model.Page;

public interface ProductService {

	Page<Product> getPage(int pageNo, Map<String, Object> reqParams);

	void create(Product product);

	Product getById(Long id);

	void update(Product product);

	void delete(Long id);

	List<Product> getAllProducts();

}
