/**
 * 
 */
package com.diet.processor;

import java.util.List;

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
@Component("getPendEmployeeDetails")
public class GetPendEmployeeDetails extends BaseProcessor {

	@Autowired
	private MyDietDAOImpl myDietDAOImpl;

	public Object processRequest(HttpServletRequest request, HttpServletResponse response) {

		List<EmployeeDetailsBean> empDtlsBeanList = null;

		try {
			empDtlsBeanList = myDietDAOImpl.getPendingEmployeeDetails();

		} catch (Exception excep) {
			excep.printStackTrace();
		}

		return empDtlsBeanList;
	
	}

	public Object processRequest(HttpServletRequest request, HttpServletResponse response, Object jsonRequest) {
		return null;
	}

}
