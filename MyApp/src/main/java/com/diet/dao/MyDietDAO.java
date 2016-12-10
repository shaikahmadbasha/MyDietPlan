/**
 * 
 */
package com.diet.dao;

import com.diet.bean.EmployeeDetailsBean;

/**
 * @author learning
 *
 */
public interface MyDietDAO {

	public EmployeeDetailsBean getEmployeeDetails(String userid);
	public boolean updateEmpDetails(EmployeeDetailsBean empDtlsBean);
	
}
