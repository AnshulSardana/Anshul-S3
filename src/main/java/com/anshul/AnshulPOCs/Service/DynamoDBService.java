package com.anshul.AnshulPOCs.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.anshul.AnshulPOCs.Entity.Employee;

@Service
public class DynamoDBService {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	Logger logger =  LoggerFactory.getLogger(DynamoDBService.class);


	public Employee saveEmployee(Employee employee) {

		logger.info("Inside Method: saveEmployee,Class:DynamoDBService");
		dynamoDBMapper.save(employee);

		return employee;
	}

	public Employee getEmployeeById(String employeeId) {
		
		logger.info("Inside Method: getEmployeeById,Class:DynamoDBService");

		return dynamoDBMapper.load(Employee.class, employeeId);
	}

	public String deleteEmployee(String employeeId) {
		
		logger.info("Inside Method: deleteEmployee,Class:DynamoDBService");

		Employee emp = dynamoDBMapper.load(Employee.class, employeeId);

		dynamoDBMapper.delete(emp);

		return "Employee Deleted Successfully";
	}

	public String updateEmployeeDetails(String employeeId, Employee employee) {

		return "Employee Updated Successfully";

	}

	public List<Employee> getAllEmployees() {
		
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		
		List<Employee> scanResult = new ArrayList<Employee>(dynamoDBMapper.scan(Employee.class, scanExpression));
		
		return scanResult;
	}

	public String saveEmployeeMultipleTimes(Employee employee) {
		
		for(int i=1;i<500;i++) {
		
			System.out.println("Inserting " + i+"th record");
			dynamoDBMapper.save(employee);

		}

		return "Records Added Successfully";
	}

}
