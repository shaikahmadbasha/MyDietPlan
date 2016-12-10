/**
 * 
 */
package com.diet.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.diet.bean.EmployeeDetailsBean;
import com.diet.dao.MyDietDAOImpl;

/**
 * @author learning
 *
 */
@Component("getEmployeeDetailsProcessor")
public class GetEmployeeDetailsProcessor extends BaseProcessor {

	@Autowired
	private MyDietDAOImpl myDietDAOImpl;

	public Object processRequest(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	public Object processRequest(HttpServletRequest request, HttpServletResponse response, Object jsonRequest) {
		EmployeeDetailsBean empDtlsBean = null;

		try {
			EmployeeDetailsBean employeeDetailsBean = (EmployeeDetailsBean)jsonRequest;
			empDtlsBean = myDietDAOImpl.getEmployeeDetails(employeeDetailsBean.getId());

		} catch (Exception excep) {
			excep.printStackTrace();
		}

		return empDtlsBean;
	}

}
