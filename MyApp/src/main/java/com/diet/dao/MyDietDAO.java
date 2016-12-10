/**
 * 
 */
package com.diet.dao;

import java.util.List;

import com.diet.bean.EmpDietMenuBean;
import com.diet.bean.EmployeeDetailsBean;

/**
 * @author learning
 *
 */
public interface MyDietDAO {

	public EmployeeDetailsBean getEmployeeDetails(String userid);
	public boolean updateEmpDetails(EmployeeDetailsBean empDtlsBean);
	public boolean updateDietDetails(EmployeeDetailsBean empDtlsBean);
	public boolean updateDietCost(List<EmpDietMenuBean> dietMenuList);
	public List<EmployeeDetailsBean> getPendingEmployeeDetails();
	
}
