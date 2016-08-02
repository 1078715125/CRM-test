package com.atguigu.crm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装menu菜单的模型
 * 
 * 
 * @author GYX09
 * @data 2016年7月23日下午2:17:24
 */
public class Navigate {

	private Long id;
	private String text;
	private String state;
	private String url;

	private List<Navigate> children;

	{
		children = new ArrayList<Navigate>();
	}

	public Navigate() {
	}

	public Navigate(Long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public Navigate(Long id, String text, String url) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Navigate> getChildren() {
		return children;
	}

	public void setChildren(List<Navigate> children) {
		this.children = children;
	}

}
