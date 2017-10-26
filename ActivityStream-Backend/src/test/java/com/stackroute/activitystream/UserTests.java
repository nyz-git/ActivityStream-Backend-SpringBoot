package com.stackroute.activitystream;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activitystream.dao.UserDAO;
import com.stackroute.activitystream.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class UserTests {

	private static User user;

	@Autowired
	private UserDAO userDAO;

	@BeforeClass
	public static void init() {
		user = new User();
	}

	//@Test
	public void registerTest() {

		user.setEmail("niyaz.ahmed@niit.com");
		user.setFirstName("Niyaz");
		user.setLastName("Ahmed");
		user.setPassword("nyz123");
		user.setContact("9870333008");
		assertEquals("User Registered Sucessfully", true, userDAO.register(user));
	}

	//@Test
	public void updateTest() {
		user.setContact("9876543210");
		user.setFirstName("Niyaz");
		user.setLastName("Ahmed");
		user.setEmail("niyaz.ahmed@niit.com");
		user.setPassword("nyz123");
		user.setStatus("A");
		assertEquals("User Updated Sucessfully", true, userDAO.update(user));
	}

	@Test
	public void validateTest() {
		user.setEmail("niyaz.ahmed@niit.com");
		user.setPassword("nyz123");
		assertEquals("User Validated", User.class, userDAO.validate(user).getClass());

	}

	//@Test
	public void deleteTest() {
		user.setEmail("niyaz.ahmed@niit.com");
		assertEquals("User Deleted ", true, userDAO.delete(user.getEmail()));
	}

}
