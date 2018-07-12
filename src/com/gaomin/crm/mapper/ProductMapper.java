package com.gaomin.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gaomin.crm.entity.Product;

public interface ProductMapper {

	int getTotalElements(Map<String, Object> params);

	List<Product> getContent(Map<String, Object> params);

	void create(Product product);

	Product getById(@Param("id") Long id);

	void update(Product product);

	void delete(@Param("id") Long id);

	List<Product> getAllProducts();

}
