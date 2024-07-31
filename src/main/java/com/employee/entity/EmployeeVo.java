package com.employee.entity;

public class EmployeeVo {
	private Integer empCode;
	private String firstName;
	private String lastName;
	private Double yearSal;
	private Double taxAmount;
	private Double cess;

	public Integer getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Integer empCode) {
		this.empCode = empCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getYearSal() {
		return yearSal;
	}

	public void setYearSal(Double yearSal) {
		this.yearSal = yearSal;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getCess() {
		return cess;
	}

	public void setCess(Double cess) {
		this.cess = cess;
	}

}
