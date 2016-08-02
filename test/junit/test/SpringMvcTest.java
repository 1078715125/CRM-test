package junit.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.service.UserService;

public class SpringMvcTest {

	private ApplicationContext ioc;
	private UserService userService;
	
	{
		ioc = new ClassPathXmlApplicationContext("spring-servlet.xml");
		userService = ioc.getBean(UserService.class);
	}
	
	@Test
	public void test01() {
		System.out.println(userService.getClass().getName());
	}

}
