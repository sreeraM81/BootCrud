
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepo;

@RestController
@RequestMapping("/s")
public class StudentController {

	@Autowired
	private StudentRepo repo;
	
	@PostMapping("/h")
	public ResponseEntity<Student> saveData(@RequestBody Student std){
		ResponseEntity<Student> re=null;
		
		repo.save(std);
		re=new ResponseEntity<Student>(HttpStatus.OK);
		
		return re;
		
	}
}
