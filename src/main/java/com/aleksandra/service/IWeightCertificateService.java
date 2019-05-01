/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.model.WeightCertificate;
import java.util.List;

/**
 *
 * @author User
 */
public interface IWeightCertificateService {

    public void add(WeightCertificate weightCertificate) throws Exception;

    public List<WeightCertificate> findAll() throws Exception;

    public WeightCertificate find(int id) throws Exception;

    public void save(WeightCertificate weightCertificate) throws Exception;

    public List<WeightCertificate> findPossibilities() throws Exception;

}
