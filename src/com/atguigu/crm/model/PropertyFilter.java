package com.atguigu.crm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;

/**
 * 将传入的name和value进行处理，并对value进行类型转换
 * 
 * 传入的参数类似于: LIKES_custName=abc, GTD_birth=1990-12-12
 * 
 * @author GYX09
 * @data 2016年7月16日上午11:18:05
 */
@SuppressWarnings("rawtypes")
public class PropertyFilter {

	private String propertyName;
	private Object propertyVal;

	private Class propertyType;
	private MatchType matchType;

	static {
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(new String[] { "yyyy-MM-dd",
				"yyyy-MM-dd HH:mm:ss" });
		// 使 ConvertUtils 的 convert 还可以转换字符串到 Date 类型.
		ConvertUtils.register(dateConverter, Date.class);
	}

	// 匹配方式的枚举类
	public enum MatchType {
		LIKE, EQ, LT, LE, GT, GE;
	}

	// 属性类型的枚举类
	public enum PropertyType {
		S(String.class), I(Integer.class), L(Long.class), D(Date.class), F(
				Float.class), ;

		private Class propertyType;

		private PropertyType(Class type) {
			this.propertyType = type;
		}

		public Class getPropertyType() {
			return propertyType;
		}
	}

	// 传入的类似于：LIKES_custName=abc
	public PropertyFilter(String propertyName, Object propertyVal) {
		String preStr = StringUtils.substringBefore(propertyName, "_");
		this.propertyName = StringUtils.substringAfter(propertyName, "_");

		String matchStr = StringUtils.substring(preStr, 0, preStr.length() - 1);
		// Enum.valueOf 可以把一个字符串转为对应的枚举类的对象.
		this.matchType = Enum.valueOf(MatchType.class, matchStr);

		String typeStr = StringUtils.substring(preStr, preStr.length() - 1);
		this.propertyType = Enum.valueOf(PropertyType.class, typeStr)
				.getPropertyType();

		// 把变量的类型直接转为需要的类型.
		this.propertyVal = ConvertUtils.convert(propertyVal, this.propertyType);

		if (this.matchType == MatchType.LIKE) {
			Object temp = (this.propertyVal == null ? "" : this.propertyVal
					.toString().trim());
			this.propertyVal = "%" + temp + "%";
		}
	}

	// 把请求参数的 Map 转为 PropertyFilter 的集合.
	public static List<PropertyFilter> parseRequestParam2PropertyFilter(
			Map<String, Object> requestParams) {
		List<PropertyFilter> list = new ArrayList<PropertyFilter>();

		if (requestParams == null || requestParams.size() == 0) {
			return list;
		}
		Set<Entry<String, Object>> entrySet = requestParams.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			String propertyName = entry.getKey();
			Object propertyVal = entry.getValue();
			if (propertyVal == null || propertyVal.toString().trim().equals("")) {
				continue;
			}
			PropertyFilter filter = new PropertyFilter(propertyName,
					propertyVal);
			list.add(filter);
		}
		return list;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Object getPropertyVal() {
		return propertyVal;
	}

	public Class getPropertyType() {
		return propertyType;
	}

	public MatchType getMatchType() {
		return matchType;
	}

}
