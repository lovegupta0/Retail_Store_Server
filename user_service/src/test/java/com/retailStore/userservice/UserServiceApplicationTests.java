package com.retailStore.userservice;

import com.retailStore.userservice.DTO.UserDTOV1;
import com.retailStore.userservice.DTO.UserDTOV2;
import com.retailStore.userservice.Model.Employee;
import com.retailStore.userservice.Model.User;
import com.retailStore.userservice.Service.EmployeeService;
import com.retailStore.userservice.Service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private EmployeeService employeeService;
	@Test
	@Order(1)
	void createUser() {
		User usr=new User();
		usr.setFirstName("Love");
		usr.setLastName("Gupta");
		usr.setEmail("admin@admin.com");
		usr.setPassword("123456");
		usr.setRole("View");
		Assertions.assertEquals(userService.saveUser(usr).getStatusCode(),HttpStatus.CREATED);
	}
	@Test
	@Order(2)
	void getUser(){
		UserDTOV1 user=new UserDTOV1("","admin@admin.com","Love","Gupta","View");
		UserDTOV1 data=userService.getUser("admin@admin.com").getBody();
		Assertions.assertEquals(HttpStatus.OK,userService.getUser("admin@admin.com").getStatusCode());
		Assertions.assertEquals(user.getEmail(),data.getEmail());
		Assertions.assertEquals(user.getFirstName(),data.getFirstName());
		Assertions.assertEquals(user.getLastName(),data.getLastName());
		Assertions.assertEquals(user.getRole(),data.getRole());
	}
	String getUID(){
		return userService.getUser("admin@admin.com").getBody().getUid();
	}
	@Test
	@Order(3)
	void updateUser(){
		User usr=new User();
		usr.setUid(getUID());
		usr.setFirstName("Love");
		usr.setLastName("Gupta");
		usr.setEmail("admin@admin.com");
		usr.setRole("Edit");
		Assertions.assertEquals(HttpStatus.OK,userService.updateUser(usr).getStatusCode());
		UserDTOV1 data=userService.getUser("admin@admin.com").getBody();
		Assertions.assertEquals(usr.getRole(),data.getRole());
		Assertions.assertEquals(usr.getEmail(),data.getEmail());
		Assertions.assertEquals(usr.getFirstName(),data.getFirstName());
		Assertions.assertEquals(usr.getLastName(),data.getLastName());
	}
	@Test
	@Order(4)
	void updatePassword(){
		User usr=new User();
		usr.setPassword("123456");
		usr.setUid(getUID());
		Assertions.assertEquals(HttpStatus.OK,userService.updatePassword(usr).getStatusCode());
		UserDTOV1 data=userService.getUser("admin@admin.com").getBody();
		Assertions.assertEquals(usr.getRole(),data.getRole());
		Assertions.assertEquals(usr.getEmail(),data.getEmail());
		Assertions.assertEquals(usr.getFirstName(),data.getFirstName());
		Assertions.assertEquals(usr.getLastName(),data.getLastName());
		UserDTOV2 dtov2=userService.getUserV2("admin@admin.com").getBody();
		Assertions.assertEquals(usr.getPassword(),dtov2.getPassword());
	}

	@Test
	@Order(6)
	void deleteUser(){
		Assertions.assertEquals(HttpStatus.OK,userService.deleteUser(getUID()).getStatusCode());
		Assertions.assertEquals(HttpStatus.NOT_FOUND,userService.getUser("admin@admin.com").getStatusCode());
	}
	@Test
	@Order(5)
	void createEmp(){
		Employee emp=new Employee();
		emp.setRole("manager");
		emp.setMobileNo(String.valueOf(1234567809));
		emp.setSalary(100000);
		User usr=new User();
		usr.setUid(getUID());
		emp.setUser(usr);
		Assertions.assertEquals(HttpStatus.CREATED,employeeService.saveEmployee(emp).getStatusCode());
	}



}
