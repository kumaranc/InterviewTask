package com.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TaskApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
	//Roles
//		Role role = new Role();
//		role.setRolename("Admin");
//		role = roleRepository.save(role);
//		System.out.println(role);
//
//		role = new Role();
//		role.setRolename("Employee");
//		role = roleRepository.save(role);
//		System.out.println(role);

	//Department
//		Department department = new Department();
//		department.setDeptname("ECE");
//		department = departmentRepository.save(department);
//		System.out.println(department);
//
//		department = new Department();
//		department.setDeptname("CSE");
//		departmentRepository.save(department);
//		System.out.println(department);
//
//		department = new Department();
//		department.setDeptname("IT");
//		departmentRepository.save(department);
//		System.out.println(department);
//	}
}
