package junit.test;

import org.apache.commons.beanutils.ConvertUtils;
import org.junit.Test;

import com.atguigu.crm.model.PropertyFilter;

public class CommonTest {

	@Test
	public void testPropertyFilter2() {
		Object s1 = 1;
		Object obj = ConvertUtils.convert(s1, String.class);
		System.out.println(obj);
	}

	@Test
	public void testPropertyFilter() {

		PropertyFilter filter = new PropertyFilter("EQD_custDate",
				"1991-06-24 12:10:02");
		System.out.println(filter.getPropertyName());
		System.out.println(filter.getPropertyVal());
		System.out.println(filter.getMatchType());
		System.out.println(filter.getPropertyType());

	}

}
