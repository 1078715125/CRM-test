package com.atguigu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.mapper.DictMapper;
import com.atguigu.crm.model.Page;
import com.atguigu.crm.service.DictService;
import com.atguigu.crm.utils.ParamsUtil;

@Service
public class DictServiceImpl implements DictService {

	@Autowired
	private DictMapper dictMapper;

	@Override
	@Transactional(readOnly = true)
	public List<String> getRegions() {
		return dictMapper.getRegions();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getLevels() {
		return dictMapper.getLevels();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getSatisfies() {
		return dictMapper.getSatisfies();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getCredits() {
		return dictMapper.getCredits();
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getServiceTypes() {
		return dictMapper.getServiceTypes();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Dict> getPage(int pageNo, Map<String, Object> reqParams) {
		Map<String, Object> params = ParamsUtil
				.parserRequestParams2MyBatisParams(reqParams);

		int totalElements = dictMapper.getTotalElements(params);
		int pageSize = Page.NORMAL_PAGESIZE;
		Page<Dict> page = new Page<Dict>(pageNo, pageSize, totalElements);
		pageNo = page.getPageNo();

		int fromIndex = (pageNo - 1) * pageSize + 1;
		int endIndex = fromIndex + pageSize;
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<Dict> content = dictMapper.getContent(params);
		page.setContent(content);
		return page;
	}

	@Override
	@Transactional
	public void create(Dict dict) {
		dictMapper.create(dict);
	}

	@Override
	@Transactional(readOnly = true)
	public Dict getById(Long dictId) {
		return dictMapper.getById(dictId);
	}

	@Override
	@Transactional
	public void update(Dict dict) {
		dictMapper.update(dict);
	}

	@Override
	@Transactional
	public void delete(Long dictId) {
		dictMapper.delete(dictId);
	}

}
