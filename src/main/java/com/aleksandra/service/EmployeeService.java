/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.IEmployeeRep;
import com.aleksandra.model.Employee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("employeeService")
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRep employeeRep;

    @Override
    public List<Employee> findAll() throws Exception {
        List<Employee> employees = employeeRep.findAll();
        return employees;
    }

    @Override
    public Employee checkEmployee(String username, String password) throws Exception {
//        List<Zaposleni> zaposleni = zaposleniDAO.fi(username, password);
        List<Employee> employees = new ArrayList<>();
        if (employees.size() == 0) {
            throw new Exception("Wrong username and/or password.");
        }
        return employees.get(0);
    }

    @Override
    public Employee find(String username) throws Exception {
        return employeeRep.findByUsername(username);
    }

}
