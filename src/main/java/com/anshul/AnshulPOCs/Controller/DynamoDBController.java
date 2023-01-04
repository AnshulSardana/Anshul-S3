package com.anshul.AnshulPOCs.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anshul.AnshulPOCs.Service.DynamoDBService;
import com.anshul.AnshulPOCs.Entity.Employee;

@RestController
@RequestMapping("/api/dynamodb/")
public class DynamoDBController {

	@Autowired
	DynamoDBService dynamoDBService;

	@PostMapping("saveEmployee")
	public Employee saveEmployee(@RequestBody Employee employee) {

		return dynamoDBService.saveEmployee(employee);
	}
	
	@PostMapping("saveEmployeeMultipleTimes")
	public String saveEmployeeMultipleTimes(@RequestBody Employee employee) {

		return dynamoDBService.saveEmployeeMultipleTimes(employee);
	}

	@GetMapping("getAllEmployees")
	public List<Employee> getAllEmployees() {

		return dynamoDBService.getAllEmployees();
	}
	
	@GetMapping("getEmployeeById/{id}")
	public Employee getEmployeeDetails(@PathVariable String id) {

		return dynamoDBService.getEmployeeById(id);
	}

	@DeleteMapping("deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable String id) {

		return dynamoDBService.deleteEmployee(id);
	}

}
