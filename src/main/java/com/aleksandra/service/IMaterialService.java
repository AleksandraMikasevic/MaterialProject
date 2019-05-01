/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.model.Material;
import java.util.List;

/**
 *
 * @author User
 */
public interface IMaterialService {

    public void add(Material material) throws Exception;

    public void delete(String id) throws Exception;

    public List<Material> findAll() throws Exception;

    public Material find(String id) throws Exception;

    public void save(Material material) throws Exception;

}
