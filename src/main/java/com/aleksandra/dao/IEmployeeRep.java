/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao;

import com.aleksandra.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository("employeeRep")
public interface IEmployeeRep extends JpaRepository<Employee, String> {

    @Query("SELECT e FROM Employee e WHERE e.username = ?1")
    Employee findByUsername(String username);

}
