/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.IGoodsReceivedNoteRep;
import com.aleksandra.dao.ISupplierRep;
import com.aleksandra.model.Supplier;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("supplierService")
public class SupplierService implements ISupplierService{
        @Autowired
    private ISupplierRep supplierRep;
    @Autowired
    private IGoodsReceivedNoteRep goodsReceivedNoteRep;

    @Override
    public List<Supplier> findAll() throws Exception {
        return supplierRep.findAll();
    }

    @Override
    public void add(Supplier supplier) throws Exception {
        supplierRep.save(supplier);
    }

    @Override
    public void delete(String s) throws Exception {
        supplierRep.deleteById(s);
    }

    @Override
    public Supplier find(String id) throws Exception {
        Supplier supplier = supplierRep.findById(id).orElse(null);
        if(supplier == null) {
            throw new Exception("Supplier with given id does not exist.");
        }
        else
        return supplier;
    }

    @Override
    public void save(Supplier supplier) throws Exception {
        supplierRep.save(supplier);
    }

}
