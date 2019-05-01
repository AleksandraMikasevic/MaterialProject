/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.IGoodsReceivedNoteRep;
import com.aleksandra.dao.IMaterialRep;
import com.aleksandra.model.GoodsReceivedNote;
import com.aleksandra.model.GoodsReceivedNoteItem;
import com.aleksandra.model.Material;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("materialService")
public class MaterialService implements IMaterialService {

    @Autowired
    private IMaterialRep materialRep;
    @Autowired
    private IGoodsReceivedNoteRep receivedNoteRep;

    @Override
    public void add(Material material) throws Exception {
        materialRep.save(material);
    }

    @Override
    public void delete(String materialID) throws Exception {
        List<GoodsReceivedNote> grcns = receivedNoteRep.findAll();
        List<GoodsReceivedNote> deleteGrcns = new ArrayList<GoodsReceivedNote>();
        for (GoodsReceivedNote grn : grcns) {
            for (GoodsReceivedNoteItem grnItem : grn.getGoodsReceivedNoteItems()) {
                if (grnItem.getMaterial().getId().equals(materialID)) {
                    receivedNoteRep.deleteById(grn.getId());
                    break;
                }
            }
        }
        materialRep.deleteById(materialID);
    }

    @Override
    public List<Material> findAll() throws Exception {
        return materialRep.findAll();
    }

    @Override
    public Material find(String id) throws Exception {
        Material material = materialRep.findById(id).orElse(null);
        if (material == null) {
            throw new Exception("Material with given id does not exist.");
        } else {
            return material;
        }
    }

    @Override
    public void save(Material material) throws Exception {
        materialRep.save(material);
    }

}
