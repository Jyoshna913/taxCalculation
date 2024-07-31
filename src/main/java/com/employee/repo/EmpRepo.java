package com.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.entity.EmployeeEntity;

@Repository
public interface EmpRepo extends JpaRepository<EmployeeEntity, Integer> {

//	EmployeeEntity findById(String employeeId);

	@Query(value = "select * from EMP where employeeid =:employeeId", nativeQuery = true)
	EmployeeEntity findById(@Param("employeeId") String employeeId);
}
