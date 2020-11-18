package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepo;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@GetMapping("/wish")
	public String wishMsg() {
		return "welcome to new practise";
	}
	
	//save data in db
	@PostMapping("/saveOne")
	public ResponseEntity<Employee> saveData(@RequestBody Employee employee){
		System.out.println(employee.toString());
		ResponseEntity<Employee> responseEntity=null;
		
		responseEntity=new ResponseEntity<Employee>(employeeRepo.save(employee),HttpStatus.OK);
		
		return responseEntity;
	}
	//get data from db by using id
	@GetMapping("/getById")
	public ResponseEntity<?> getData(@RequestParam Integer empId){
		ResponseEntity<Employee> responseEntity=null;
		Optional<Employee> optional=employeeRepo.findById(empId);
		if(optional.isPresent()) {
			responseEntity=new ResponseEntity<Employee>(optional.get(),HttpStatus.OK);
		}else {
			responseEntity=new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}
		
		return responseEntity;
	}
	//delete data based on id
	@DeleteMapping("deleteById")
	public ResponseEntity<String> deleteData(@RequestParam Integer deleteId){
		ResponseEntity<String> responseEntity=null;
		boolean id=employeeRepo.existsById(deleteId);
		if(id) {
			employeeRepo.deleteById(deleteId);
			responseEntity=new ResponseEntity<String>(deleteId+"rec removed",HttpStatus.OK);
		}else {
			responseEntity=new ResponseEntity<String>(deleteId+" not exists",HttpStatus.BAD_REQUEST);
		}
		
		return responseEntity; 
	}
	//get All employes Data
	@GetMapping("/getAllEmployes")
	public ResponseEntity getAll(){
		ResponseEntity responseEntity=null;
		List list=(List) employeeRepo.findAll();
		
		responseEntity=new ResponseEntity(list,HttpStatus.OK);
		
		return responseEntity;
	}
	//update record by using id
	@PutMapping("/update")
	public ResponseEntity<String> updateData(@RequestBody Employee employeeUpdate){
		ResponseEntity<String> responseEntity=null;
		boolean id=employeeRepo.existsById(employeeUpdate.getEmpId());
		if(id) {
			employeeRepo.save(employeeUpdate);
			responseEntity=new ResponseEntity<String>(employeeUpdate.getEmpId()+ "rec updated",HttpStatus.OK);
		}else {
			responseEntity=new ResponseEntity<String>(employeeUpdate.getEmpId()+ " not exists",HttpStatus.BAD_REQUEST);
		}
		
		return responseEntity; 
	}
	
	
	

}
