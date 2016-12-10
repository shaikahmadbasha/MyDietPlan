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
				//connection = DriverManager.getConnection("jdbc:mysql://aa1wkuw3g4plunp.cwgf3i9nxgiq.ap-southeast-2.rds.amazonaws.com:3306/test", "root", "Capgemini123");
				System.out.println();
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

				empDtlsBean.setContactNum(rs.getString("Contact_No") + "");
				empDtlsBean.setGender(rs.getString("Gender"));
				empDtlsBean.setAge(rs.getInt("Age"));
				empDtlsBean.setHeight(rs.getInt("Height"));
				empDtlsBean.setWeight(rs.getInt("WEIGHT"));
				empDtlsBean.setDailyAct(rs.getString("Daily_Activities"));
				empDtlsBean.setStatus(rs.getString("Status"));
			}

			List<EmpDiteConsultation> ditCulsList = new ArrayList<EmpDiteConsultation>();
			ResultSet rs2 = stmt.executeQuery("select * from EMP_CONSULT_DETAILS where Emp_Id = '" + empId + "'");
			while (rs2.next()) {
				EmpDiteConsultation emConBean = new EmpDiteConsultation();
				emConBean.setDate(rs2.getString("Consult_Date"));
				emConBean.setDietitionName(rs2.getString("Dietition"));
				emConBean.setDetails(rs2.getString("Consultation_Details"));
				ditCulsList.add(emConBean);
			}
			empDtlsBean.setEmpDiteConsulDetails(ditCulsList);

			List<EmpDietMenuBean> empMenuList = new ArrayList<EmpDietMenuBean>();
			ResultSet rs3 = stmt.executeQuery("select * from EMP_DIET_PLAN_DETAILS  where Emp_Id = '" + empId + "'");
			while (rs3.next()) {
				EmpDietMenuBean empMenuBean = new EmpDietMenuBean();
				empMenuBean.setMeal1(rs3.getString("Meal1"));
				empMenuBean.setMeal2(rs3.getString("Meal2"));
				empMenuBean.setCost(Integer.parseInt(rs3.getString("Price")));
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
	public List<EmployeeDetailsBean> getPendingEmployeeDetails() {

		List<EmployeeDetailsBean> empDtlsBeanList = new ArrayList<EmployeeDetailsBean>();

		try {
			createConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from EMP_PERSONAL_DETAILS");
			System.out.println("*******************************rs called 5 ");

			while (rs.next()) {
				EmployeeDetailsBean empDtlsBean = new EmployeeDetailsBean();
				empDtlsBean.setId(rs.getString("Emp_Id"));
				empDtlsBean.setName(rs.getString("Name"));
				empDtlsBean.setEmail(rs.getString("Email_Id"));

				empDtlsBean.setContactNum(rs.getString("Contact_No") + "");
				empDtlsBean.setGender(rs.getString("Gender"));
				empDtlsBean.setAge(rs.getInt("Age"));
				empDtlsBean.setHeight(rs.getInt("Height"));
				empDtlsBean.setWeight(rs.getInt("WEIGHT"));
				empDtlsBean.setDailyAct(rs.getString("Daily_Activities"));
				empDtlsBean.setStatus(rs.getString("Status"));
				empDtlsBeanList.add(empDtlsBean);
			}

			

		} catch (Exception excep) {
			excep.printStackTrace();
		} finally {

		}


		return empDtlsBeanList;
	}

	@Override
	public boolean updateEmpDetails(EmployeeDetailsBean empDtlsBean) {
		Statement stmt = null;
		boolean successs = false;

		try {
			createConnection();
			stmt = connection.createStatement();

			String query = "INSERT INTO EMP_PERSONAL_DETAILS () VALUES('" + empDtlsBean.getId() + "', '"
					+ empDtlsBean.getName() + "', '" + empDtlsBean.getEmail() + "', '" + empDtlsBean.getContactNum()
					+ "' , '" + empDtlsBean.getGender() + "', " + empDtlsBean.getAge() + ", " + empDtlsBean.getHeight()
					+ ", " + empDtlsBean.getWeight() + ", '" + empDtlsBean.getDailyAct() + "', '', '', '"
					+ empDtlsBean.getStatus() + "') ON DUPLICATE KEY UPDATE name='" + empDtlsBean.getName()
					+ "', Email_Id='" + empDtlsBean.getEmail() + "', Contact_No='" + empDtlsBean.getContactNum()
					+ "', Age=" + empDtlsBean.getAge() + ", WEIGHT=" + empDtlsBean.getWeight() + ", Daily_Activities='"
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

	@Override
	public boolean updateDietDetails(EmployeeDetailsBean empDtlsBean) {

		Statement stmt = null;
		boolean successs = false;

		try {
			createConnection();
			
			stmt = connection.createStatement();
			
			List<EmpDiteConsultation> dietConsList = empDtlsBean.getEmpDiteConsulDetails();
			
			if(null != dietConsList && !dietConsList.isEmpty()) {
				EmpDiteConsultation dietConsBean = dietConsList.get(0);
				String query = "insert into EMP_CONSULT_DETAILS() values ('"+empDtlsBean.getId()+"', '"+dietConsBean.getDate()+"', '"+dietConsBean.getDietitionName()+"', '"+dietConsBean.getDetails()+"')";
				stmt.execute(query);
			}
			
			
			List<EmpDietMenuBean> dietMenuList = empDtlsBean.getEmpDietMenuList();
			if(null != dietMenuList && !dietMenuList.isEmpty()) {
				String delQuery = "delete from EMP_DIET_PLAN_DETAILS where Emp_Id='" + empDtlsBean.getId() +"'";
				stmt.execute(delQuery);
				
				for(EmpDietMenuBean empDtMen : dietMenuList) {
					String query = "insert into EMP_DIET_PLAN_DETAILS() values ('"+empDtlsBean.getId()+"' , '"+empDtMen.getMeal1()+"', '"+empDtMen.getMeal2()+"', 0)";
					stmt.execute(query);	
				}
			}

			successs = true;
			System.out.println("******Employee Diet Details inserted");

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
	
	
	@Override
	public boolean updateDietCost(List<EmpDietMenuBean> dietMenuList) {

		Statement stmt = null;
		boolean successs = false;

		try {
			createConnection();
			
			stmt = connection.createStatement();
			
			
			if(null != dietMenuList && !dietMenuList.isEmpty()) {
				
				for(EmpDietMenuBean empDtMen : dietMenuList) {
					String query = "update EMP_DIET_PLAN_DETAILS set Price="+ empDtMen.getCost() +" where Emp_Id='" + empDtMen.getEmpId() +"' and Meal1='" + empDtMen.getMeal1() +"' and Meal2 = '" + empDtMen.getMeal2() +"'";
					//String query = "update EMP_DIET_PLAN_DETAILS set Price="+ empDtMen.getCost() +" where Emp_Id='" + empDtMen.getEmpId() +"'   & ";
					stmt.execute(query);	
				}
			}

			successs = true;
			System.out.println("******Employee cost Details inserted");

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
