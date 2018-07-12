package com.gaomin.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaomin.crm.entity.Storage;
import com.gaomin.crm.mapper.StorageMapper;
import com.gaomin.crm.model.Page;
import com.gaomin.crm.service.StorageService;
import com.gaomin.crm.utils.ParamsUtil;

@Service
public class StorageServiceImpl implements StorageService {

	@Autowired
	private StorageMapper storageMapper;
	
	@Override
	@Transactional(readOnly=true)
	public Page<Storage> getPage(int pageNo, Map<String, Object> reqParams) {
		Map<String, Object> params = ParamsUtil
				.parserRequestParams2MyBatisParams(reqParams);

		int totalElements = storageMapper.getTotalElements(params);
		int pageSize = 3;
		Page<Storage> page = new Page<Storage>(pageNo, pageSize, totalElements);
		pageNo = page.getPageNo();

		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<Storage> content = storageMapper.getContent(params);
		page.setContent(content);
		return page;
	}

	@Override
	@Transactional
	public void create(Storage storage) {
		storageMapper.create(storage);
	}

	@Override
	@Transactional(readOnly=true)
	public Storage getById(Long id) {
		return storageMapper.getById(id);
	}

	@Override
	@Transactional
	public void update(Storage storage) {
		storageMapper.update(storage);		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		storageMapper.delete(id);		
	}

	
}
