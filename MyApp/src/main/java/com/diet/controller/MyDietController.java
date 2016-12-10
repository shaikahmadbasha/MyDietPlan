package com.diet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diet.bean.EmployeeDetailsBean;
import com.diet.processor.EmployeeDetailsProcessor;
import com.diet.processor.UpdateEmployeeDetailsProcessor;

/**
 * 
 * @author learning
 *
 */
@Controller
public class MyDietController {

	@Autowired
	private EmployeeDetailsProcessor employeeDetailsProcessor;
	
	@Autowired
	private UpdateEmployeeDetailsProcessor updateEmployeeDetailsProcessor;
	

	@RequestMapping("/employeeLogin")
	public @ResponseBody Object employeeLogin(HttpServletRequest request, HttpServletResponse response) {

		return employeeDetailsProcessor.processRequest(request, response);

	}

	@RequestMapping(value = "/getEmployeeDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getEmployeeDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Object jsonData) {
		return employeeDetailsProcessor.processRequest(request, response, jsonData);
	}

	
	@RequestMapping(value = "/updateEmployeeDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object updateEmployeeDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestBody EmployeeDetailsBean employeeDetailsBean) {
		return updateEmployeeDetailsProcessor.processRequest(request, response, employeeDetailsBean);
	}
}
