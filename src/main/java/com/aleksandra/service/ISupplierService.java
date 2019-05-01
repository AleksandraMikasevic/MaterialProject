/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.model.Supplier;
import java.util.List;

/**
 *
 * @author User
 */
public interface ISupplierService {

    public void add(Supplier supplier) throws Exception;

    public void delete(String id) throws Exception;

    public List<Supplier> findAll() throws Exception;

    public Supplier find(String id) throws Exception;

    public void save(Supplier supplier) throws Exception;

}
