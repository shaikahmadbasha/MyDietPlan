/**
 * 
 */
package com.diet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.diet.bean.EmpDietMenuBean;
import com.diet.bean.EmpDiteConsultation;
import com.diet.bean.EmployeeDetailsBean;

/**
 * @author learning
 *
 */
@Component("myDietDAOImpl")
public class MyDietDAOImpl implements MyDietDAO {

	private Connection connection = null;

	public void createConnection() {
		try {
			if (connection == null) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
			}

		} catch (Exception excep) {
			excep.printStackTrace();
		}
	}

	@Override
	public EmployeeDetailsBean getEmployeeDetails(String empId) {

		EmployeeDetailsBean empDtlsBean = new EmployeeDetailsBean();

		try {
			createConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from EMP_PERSONAL_DETAILS where Emp_Id = '" + empId + "'");
			System.out.println("*******************************rs called 5 ");

			while (rs.next()) {
				empDtlsBean.setId(rs.getString("Emp_Id"));
				empDtlsBean.setName(rs.getString("Name"));
				empDtlsBean.setEmail(rs.getString("Email_Id"));

				empDtlsBean.setContactNum(rs.getInt("Contact_No"));
				empDtlsBean.setGender(rs.getString("Gender"));
				empDtlsBean.setAge(rs.getInt("Age"));
				empDtlsBean.setHeight(rs.getInt("Height"));
				empDtlsBean.setWeight(rs.getInt("WEIGHT"));
				empDtlsBean.setDailyAct(rs.getString("Daily_Activities"));
				empDtlsBean.setStatus(rs.getString("Status"));
			}

			List<EmpDiteConsultation> ditCulsList = new ArrayList<>();
			ResultSet rs2 = stmt.executeQuery("select * from EMP_CONSULT_DETAILS where Emp_Id = '" + empId + "'");
			while (rs2.next()) {
				EmpDiteConsultation emConBean = new EmpDiteConsultation();
				emConBean.setDate(rs2.getString("Consult_Date"));
				emConBean.setDietitionName(rs2.getString("Dietition"));
				emConBean.setDetails(rs2.getString("Consultation_Details"));
				ditCulsList.add(emConBean);
			}
			empDtlsBean.setEmpDiteConsulDetails(ditCulsList);

			List<EmpDietMenuBean> empMenuList = new ArrayList<>();
			ResultSet rs3 = stmt.executeQuery("select * from EMP_DIET_PLAN_DETAILS  where Emp_Id = '" + empId + "'");
			while (rs3.next()) {
				EmpDietMenuBean empMenuBean = new EmpDietMenuBean();
				empMenuBean.setMeal1(rs3.getString("Meal1"));
				empMenuBean.setMeal2(rs3.getString("Meal2"));
				empMenuList.add(empMenuBean);
			}
			empDtlsBean.setEmpDietMenuList(empMenuList);

		} catch (Exception excep) {
			excep.printStackTrace();
		} finally {

		}

		/*
		 * EmployeeDetailsBean empDtlBean = new EmployeeDetailsBean();
		 * 
		 * List<EmpDietMenuBean> dietHistoryBean = new ArrayList<>();
		 * EmpDietMenuBean empDietBean = new EmpDietMenuBean();
		 * empDietBean.setDate("12/10/2016"); empDietBean.
		 * setDietChart("Yaa.. I can see you are over weighted..hmm.. eat 50 grms of Potato"
		 * ); empDietBean.setDietitionName("Vishal");
		 * 
		 * dietHistoryBean.add(empDietBean);
		 * 
		 * empDtlBean = new EmployeeDetailsBean();
		 * empDtlBean.setContactNum("9866942970"); empDtlBean.setName("Basha");
		 * empDtlBean.setDietHistoryBean(dietHistoryBean);
		 */
		return empDtlsBean;
	}

	@Override
	public boolean updateEmpDetails(EmployeeDetailsBean empDtlsBean) {
		Statement stmt = null;
		boolean successs = false;

		try {
			createConnection();
			stmt = connection.createStatement();

			String query = "INSERT INTO EMP_PERSONAL_DETAILS () VALUES('" + empDtlsBean.getId() + "', '"
					+ empDtlsBean.getName() + "', '" + empDtlsBean.getEmail() + "', " + empDtlsBean.getContactNum()
					+ ", '" + empDtlsBean.getGender() + "', " + empDtlsBean.getAge() + ", " + empDtlsBean.getHeight()
					+ ", " + empDtlsBean.getWeight() + ", '" + empDtlsBean.getDailyAct() + "', '', '', '"
					+ empDtlsBean.getStatus() + "') ON DUPLICATE KEY UPDATE name='" + empDtlsBean.getName()
					+ "', Email_Id='" + empDtlsBean.getEmail() + "', Contact_No=" + empDtlsBean.getContactNum()
					+ ", Age=" + empDtlsBean.getAge() + ", WEIGHT=" + empDtlsBean.getWeight() + ", Daily_Activities='"
					+ empDtlsBean.getDailyAct() + "',Status = '" + empDtlsBean.getStatus() + "' ";

			stmt.execute(query);
			successs = true;
			System.out.println("******Employee Details inserted");

		} catch (Exception excep) {
			excep.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception ex) {

			}
		}

		return successs;
	}

}
