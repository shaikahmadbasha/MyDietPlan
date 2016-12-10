/**
 * 
 */
package com.diet.bean;

import java.util.List;

/**
 * @author learning
 *
 */
public class EmployeeDetailsBean extends BaseBean {

	private String id;
	private String name;

	private int height;
	private int weight;
	private String gender;
	private String contactNum;
	private String email;
	private String status;
	private String userId;
	private int age;
	private String dailyAct;
	
	private List<EmpDiteConsultation> empDiteConsulDetails;
	private List<EmpDietMenuBean> empDietMenuList;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the dailyAct
	 */
	public String getDailyAct() {
		return dailyAct;
	}

	/**
	 * @param dailyAct the dailyAct to set
	 */
	public void setDailyAct(String dailyAct) {
		this.dailyAct = dailyAct;
	}

	/**
	 * @return the empDiteConsulDetails
	 */
	public List<EmpDiteConsultation> getEmpDiteConsulDetails() {
		return empDiteConsulDetails;
	}

	/**
	 * @param empDiteConsulDetails the empDiteConsulDetails to set
	 */
	public void setEmpDiteConsulDetails(List<EmpDiteConsultation> empDiteConsulDetails) {
		this.empDiteConsulDetails = empDiteConsulDetails;
	}

	/**
	 * @return the empDietMenuList
	 */
	public List<EmpDietMenuBean> getEmpDietMenuList() {
		return empDietMenuList;
	}

	/**
	 * @param empDietMenuList the empDietMenuList to set
	 */
	public void setEmpDietMenuList(List<EmpDietMenuBean> empDietMenuList) {
		this.empDietMenuList = empDietMenuList;
	}

	/**
	 * @return the contactNum
	 */
	public String getContactNum() {
		return contactNum;
	}

	/**
	 * @param contactNum the contactNum to set
	 */
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}


}
