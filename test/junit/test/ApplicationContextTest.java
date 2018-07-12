package junit.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gaomin.crm.entity.Authority;
import com.gaomin.crm.entity.Contact;
import com.gaomin.crm.entity.Role;
import com.gaomin.crm.entity.User;
import com.gaomin.crm.mapper.AuthorityMapper;
import com.gaomin.crm.mapper.RoleMapper;
import com.gaomin.crm.mapper.SalesChanceMapper;
import com.gaomin.crm.mapper.TestBatch;
import com.gaomin.crm.mapper.UserMapper;
import com.gaomin.crm.service.SalesChanceService;
import com.gaomin.crm.service.UserService;

public class ApplicationContextTest {

	private ApplicationContext ioc;
	private UserMapper userMapper;
	private UserService userService;
	private SalesChanceMapper salesChanceMapper;
	private SalesChanceService salesChanceService;
	private RoleMapper roleMapper;
	private AuthorityMapper authorityMapper;

	private TestBatch testBatch;

	{
		ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		userMapper = ioc.getBean(UserMapper.class);
		userService = ioc.getBean(UserService.class);
		salesChanceMapper = ioc.getBean(SalesChanceMapper.class);
		salesChanceService = ioc.getBean(SalesChanceService.class);
		testBatch = ioc.getBean(TestBatch.class);
		roleMapper = ioc.getBean(RoleMapper.class);
		authorityMapper = ioc.getBean(AuthorityMapper.class);
	}

	@Test
	public void testUser() {
		User byName = userMapper.getByName("admin");
		System.out.println(byName);
	}
	
	
	@Test
	public void testAuth() {
		List<Authority> list = authorityMapper.getParentAuthorities();
		for (Authority authority : list) {
			System.out.println(authority);
		}
	}

	@Test
	public void testRole() {
		Role role = roleMapper.getWithAuthById(3L);

		// System.out.println(role.getName());
		List<Long> authorities2 = role.getAuthorities2();
		for (Long l : authorities2) {
			System.out.println(l);
		}

		/*List<Authority> authorities = role.getAuthorities();
		for (Authority authority : authorities) {
			System.out.println(authority.getId());
		}*/

	}

	@Test
	public void testBatch3() {
		List<Contact> list = new ArrayList<Contact>();
		for (int i = 1; i < 11; i++) {
			Contact contact = new Contact();
			contact.setMemo("wohaha" + i);
			contact.setName("批量" + i);
			list.add(contact);
		}
		testBatch.batchInsert(list);
	}

	@Test
	public void testBatch2() {
		List<Contact> list = new ArrayList<Contact>();
		for (int i = 1; i < 4; i++) {
			Contact contact = new Contact();
			contact.setMemo("wohaha" + i);
			contact.setName("批量" + i);
			list.add(contact);
		}
		testBatch.batchInsert(list);
	}

	@Test
	public void testBatch1() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1001);
		list.add(1002);

		List<Contact> list2 = testBatch.selectIn(list);
		for (Contact contact : list2) {
			System.out.println(contact.getName());
		}
	}

	@Test
	public void testSalesChanceMapper() {

		Map<String, Object> params = new HashMap<String, Object>();
		User user = new User(21L);
		params.put("createBy", user);
		params.put("status", 1);

		long l = salesChanceMapper.getTotalElements(params);
		System.out.println(l);
	}

	@Test
	public void testUserService() {
		System.out.println(userService.getClass().getName());
		User user = new User();
		user.setName("admin");
		user.setPassword("aa");
		User login = userService.login(user);
		System.out.println(login.getRole().getName());

	}

	@Test
	public void testUserMapper() {
		User user = userMapper.getByName("admin");
		System.out.println(user);
	}

	@Test
	public void testDataSource() throws SQLException {

		DataSource dataSource = ioc.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());

	}

}
