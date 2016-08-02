package com.atguigu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.mapper.ProductMapper;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.service.ProductService;
import com.atguigu.crm.utils.ParamsUtil;

@Service
public class ProductServiceimpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override
	public Page<Product> getPage(int pageNo, Map<String, Object> reqParams) {
		Map<String, Object> params = ParamsUtil
				.parserRequestParams2MyBatisParams(reqParams);

		int totalElements = productMapper.getTotalElements(params);
		int pageSize = 3;
		Page<Product> page = new Page<Product>(pageNo, pageSize, totalElements);
		pageNo = page.getPageNo();

		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<Product> content = productMapper.getContent(params);
		page.setContent(content);
		return page;
	}

	@Override
	public void create(Product product) {
		productMapper.create(product);
	}

	@Override
	public Product getById(Long id) {
		return productMapper.getById(id);
	}

	@Override
	public void update(Product product) {
		productMapper.update(product);
	}

	@Override
	public void delete(Long id) {
		productMapper.delete(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return productMapper.getAllProducts();
	}
}
