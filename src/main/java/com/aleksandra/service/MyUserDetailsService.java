/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.IEmployeeRep;
import com.aleksandra.model.Employee;
import com.aleksandra.model.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IEmployeeRep employeeRep;

    /*   public void setZaposleniDAO(IZaposleniDAORep zaposleniDAO) {
        this.zaposleniDAO = zaposleniDAO;
    }*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRep.findByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : employee.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String enString = passwordEncoder.encode(employee.getPassword());
        return new org.springframework.security.core.userdetails.User(employee.getUsername(), enString, grantedAuthorities);
    }

}
