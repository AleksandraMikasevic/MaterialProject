/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.model.Employee;
import java.util.List;

/**
 *
 * @author User
 */
public interface IEmployeeService {

    public List<Employee> findAll() throws Exception;

    public Employee checkEmployee(String username, String password) throws Exception;

    public Employee find(String username) throws Exception;

}
