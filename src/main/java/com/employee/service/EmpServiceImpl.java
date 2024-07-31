package com.employee.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.EmployeeEntity;
import com.employee.entity.EmployeeVo;
import com.employee.repo.EmpRepo;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpRepo employeeRepository;

//	public List getAllEmployees() {
//		List employees = new ArrayList();
//		employeeRepository.findAll().forEach(em -> employees.add(em));
//		return employees;
//	}

	public void createEmp(EmployeeEntity employee) {
		employeeRepository.save(employee);
	}

	private double getTax(double salary) {
		double tax = 0.0;
		try {
			if (salary <= 250000) {
				System.out.println("no Tax - salary is less than 2,50,000");
				tax = 0.0;
			} else if (salary > 250000 && salary <= 500000) {
				System.out.println(" salary is between 2,50,000/- and 5,00,000");
				tax = (salary - 250000) * 0.05;
				System.out.println(" calculated Tax is : " + tax);
			} else if (salary > 500000 && salary <= 1000000) {
				System.out.println(" salary is between 5,00,000/- and 1,00,000");
				double slab2 = salary - 500000;
				double slab1 = 500000 - 250000;
				tax = slab1 * 0.05 + slab2 * 0.1;
				System.out.println(" calculated Tax is : " + tax);
			} else if (salary > 1000000) {
				System.out.println(" salary is more then 10,00,000/-");
				double slab3 = salary - 1000000;
				double slab2 = salary - 500000 - slab3;
				double slab1 = slab2 - 250000;
				tax = slab3 * 0.2 + slab2 * 0.1 + slab1 * 0.05;
				System.out.println(" calculated Tax is : " + tax);
			}
			return tax;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tax;
	}

	private Double getCess(double income) {
		double cess = 0.0;
		double amount = 0.0;
		try {
			System.out.println("Salary : " + income);
			if (income > 2500000) {
				amount = income * 0.11;
				cess = amount * 0.02;
			}
			return cess;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cess;
	}

	@Override
	public List<EmployeeVo> calEmpTax(String employeeId) {

		List<EmployeeVo> employVoList = new ArrayList<EmployeeVo>();
		try {
			String finYear = "2024";
			String finYearStartDate = finYear + "-04-01";
			String finYearEndDate = String.valueOf(Integer.parseInt(finYear) + 1) + "-03-31";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = sdf.parse(finYearStartDate);
			Date endDate = sdf.parse(finYearEndDate);
			EmployeeEntity emp = employeeRepository.findById(employeeId);
			if (emp != null) {
					Date doj = sdf.parse(emp.getDoj().toString());
					EmployeeVo employVo = new EmployeeVo();
					employVo.setFirstName(emp.getFirstName());
					employVo.setEmpCode(emp.getEmployeeId());
					employVo.setLastName(emp.getLastName());
					if (doj.after(startDate) && doj.before(endDate)) {
						long months = ChronoUnit.MONTHS.between(
								YearMonth.from(LocalDate.parse(sdf.format(emp.getDoj()))),
								YearMonth.from(LocalDate.parse(finYearEndDate))

						);
						employVo.setYearSal(emp.getSalary() * months);
						employVo.setTaxAmount(getTax(emp.getSalary() * months));
						employVo.setCess(getCess(emp.getSalary() * months));

					} else {
						employVo.setYearSal(emp.getSalary() * 12);
						employVo.setTaxAmount(getTax(emp.getSalary() * 12));
						employVo.setCess(getCess(emp.getSalary() * 12));
					}
					employVoList.add(employVo);
				}
				System.out.println(" Employee Tax Details : " + employVoList);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return employVoList;
	}

}
