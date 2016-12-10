package com.diet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diet.bean.EmpDietMenuBean;
import com.diet.bean.EmployeeDetailsBean;
import com.diet.processor.GetEmployeeDetailsProcessor;
import com.diet.processor.GetPendEmployeeDetails;
import com.diet.processor.UpdateCostProcessor;
import com.diet.processor.UpdateDietDetailsProcessor;
import com.diet.processor.UpdateEmployeeDetailsProcessor;

/**
 * 
 * @author learning
 *
 */
@Controller
public class MyDietController {

	@Autowired
	private GetEmployeeDetailsProcessor getEmployeeDetailsProcessor;
	
	@Autowired
	private UpdateEmployeeDetailsProcessor updateEmployeeDetailsProcessor;
	
	@Autowired
	private UpdateDietDetailsProcessor updateDietDetailsProcessor;
	
	@Autowired
	private UpdateCostProcessor updateCostProcessor;
	
	@Autowired
	private GetPendEmployeeDetails getPendEmployeeDetails;
	

	@RequestMapping("/employeeLogin")
	public @ResponseBody Object employeeLogin(HttpServletRequest request, HttpServletResponse response) {

		return getEmployeeDetailsProcessor.processRequest(request, response);

	}

	@RequestMapping(value = "/getEmployeeDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getEmployeeDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestBody EmployeeDetailsBean employeeDetailsBean) {
		return getEmployeeDetailsProcessor.processRequest(request, response, employeeDetailsBean);
	}

	@RequestMapping(value = "/getEmployeeList", method = RequestMethod.POST)
	public @ResponseBody Object getEmployeeList(HttpServletRequest request, HttpServletResponse response) {
		return getPendEmployeeDetails.processRequest(request, response);
	}
	
	@RequestMapping(value = "/updateEmployeeDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object updateEmployeeDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestBody EmployeeDetailsBean employeeDetailsBean) {
		return updateEmployeeDetailsProcessor.processRequest(request, response, employeeDetailsBean);
	}
	
	@RequestMapping(value = "/updateDietDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object updateDietDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestBody EmployeeDetailsBean employeeDetailsBean) {
		return updateDietDetailsProcessor.processRequest(request, response, employeeDetailsBean);
	}
	
	@RequestMapping(value = "/updateCost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object updateCost(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<EmpDietMenuBean> empDietCostList) {
		return updateCostProcessor.processRequest(request, response, empDietCostList);
	}
}
