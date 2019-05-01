/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.model.Employee;
import com.aleksandra.service.IEmployeeService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author User
 */
@Controller
public class IndexController {
    @Autowired 
private IEmployeeService employeeService;
    
    @RequestMapping(value = "/")
    public ModelAndView index(HttpSession session) {
        ModelAndView mv = new ModelAndView("main");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        System.out.println("NAMEE: "+name);
        Employee employee = new Employee();
        try {
            employee = employeeService.find(name);
        } catch (Exception ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("employee", employee);
        return mv;
    }

    @RequestMapping(value = "/index")
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

}
