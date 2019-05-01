/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.model.Employee;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author User
 */
@Controller
public class EmployeeController {
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {       
        return "login";
    }
 
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView("main");
        return mv;
    }

    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public ModelAndView loginFailed() {
        System.out.println("login failed");
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("error", "true");
        return mv;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }
    @RequestMapping(value = "/find_employee", method = RequestMethod.GET)
    public ModelAndView find_employee(HttpSession session) {
        Employee employee = new Employee();
        employee = (Employee) session.getAttribute("employee");
        ModelAndView mv = new ModelAndView("find_employee");
        mv.addObject("employee", employee);
        return mv;
    }

}
