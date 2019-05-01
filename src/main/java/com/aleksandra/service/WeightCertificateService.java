/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.IGoodsReceivedNoteRep;
import com.aleksandra.dao.IWeightCertificateRep;
import com.aleksandra.model.GoodsReceivedNote;
import com.aleksandra.model.WeightCertificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("weightCertificateService")
public class WeightCertificateService implements IWeightCertificateService {

    @Autowired
    private IWeightCertificateRep weightCertificateRep;
    @Autowired
    private IGoodsReceivedNoteRep goodsReceivedNoteRep;

    @Override
    public void add(WeightCertificate wec) throws Exception {
        weightCertificateRep.save(wec);
    }

    @Override
    public List<WeightCertificate> findAll() throws Exception {
        return weightCertificateRep.findAll();
    }

    @Override
    public WeightCertificate find(int id) throws Exception {
        WeightCertificate weightCertificate = weightCertificateRep.findById(id).orElse(null);
        if(weightCertificate == null) {
            throw new Exception("Weight certificate with given id does not exist.");
        }
        else
        return weightCertificate;
    }

    @Override
    public void save(WeightCertificate wec) throws Exception {
        weightCertificateRep.save(wec);
    }

    @Override
    public List<WeightCertificate> findPossibilities() throws Exception {
        List<WeightCertificate> weightCertificates = weightCertificateRep.findAll();
        List<WeightCertificate> weightCertificatesNotPoss = new ArrayList<>();
        List<GoodsReceivedNote> goodsReceivedNotes = goodsReceivedNoteRep.findAll();
        for (WeightCertificate wec : weightCertificates) {
            for (GoodsReceivedNote grn : goodsReceivedNotes) {
                if (wec.getId()== grn.getWeightCertificate().getId()) {
                    weightCertificatesNotPoss.add(wec);
                }
            }
        }
        weightCertificates.removeAll(weightCertificatesNotPoss);
        return weightCertificates;
    }

}
