package com.itsc.LabExam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LabExamApplication {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		StudentRepo student_repo = context.getBean("stu", StudentRepo.class);
		student_repo.createDBAndTable();

		Student student = new Student();
		student.setName("haileab");
		student.setEmail("haileabambaye@gmail.com");

		student_repo.insertIntoTable(student);
	}
}