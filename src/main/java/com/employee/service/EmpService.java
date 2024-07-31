package com.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.entity.EmployeeEntity;
import com.employee.entity.EmployeeVo;

@Service
public interface EmpService {
//	public List getAllEmployees();

	public void createEmp(EmployeeEntity employee);

	List<EmployeeVo> calEmpTax(String finYear);
}
