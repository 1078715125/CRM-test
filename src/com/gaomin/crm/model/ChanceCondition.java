package com.gaomin.crm.model;

/**
 * 封装销售机会列表的条件
 * 
 * 
 * @author GYX09
 * @data 2016年7月13日下午7:51:47
 */
public class ChanceCondition {

	private String searchLikeCustName;
	private String searchLikeTitle;
	private String searchLikeContact;
	private String holdOn;// 判断是否保持条件：不为空则保持

	public ChanceCondition(String searchLikeCustName, String searchLikeTitle,
			String searchLikeContact, String holdOn) {
		super();
		this.searchLikeCustName = searchLikeCustName;
		this.searchLikeTitle = searchLikeTitle;
		this.searchLikeContact = searchLikeContact;
		this.holdOn = holdOn;
	}

	public ChanceCondition() {
		super();
	}

	public String getSearchLikeCustName() {
		return searchLikeCustName;
	}

	public void setSearchLikeCustName(String searchLikeCustName) {
		this.searchLikeCustName = searchLikeCustName;
	}

	public String getSearchLikeTitle() {
		return searchLikeTitle;
	}

	public void setSearchLikeTitle(String searchLikeTitle) {
		this.searchLikeTitle = searchLikeTitle;
	}

	public String getSearchLikeContact() {
		return searchLikeContact;
	}

	public void setSearchLikeContact(String searchLikeContact) {
		this.searchLikeContact = searchLikeContact;
	}

	public String getHoldOn() {
		return holdOn;
	}

	public void setHoldOn(String holdOn) {
		this.holdOn = holdOn;
	}

	@Override
	public String toString() {
		return "ChanceCondition [searchLikeCustName=" + searchLikeCustName
				+ ", searchLikeTitle=" + searchLikeTitle
				+ ", searchLikeContact=" + searchLikeContact + ", holdOn="
				+ holdOn + "]";
	}

}
