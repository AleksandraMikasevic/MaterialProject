/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.IGoodsReceivedNoteRep;
import com.aleksandra.model.GoodsReceivedNote;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("goodsReceivedNoteService")
public class GoodsReceivedNoteService implements IGoodsReceivedNoteService {

    @Autowired
    private IGoodsReceivedNoteRep goodsReceivedNoteRep;

    @Override
    public void add(GoodsReceivedNote goodsReceivedNote) throws Exception {
        goodsReceivedNote.setEntryDate(new Date());
        goodsReceivedNoteRep.save(goodsReceivedNote);
    }

    @Override
    public void delete(int id) throws Exception {
        goodsReceivedNoteRep.deleteById(id);
    }

    @Override
    public List<GoodsReceivedNote> findAll() throws Exception {
        return goodsReceivedNoteRep.findAll();
    }

    @Override
    public GoodsReceivedNote find(int id) throws Exception {
        GoodsReceivedNote grn = goodsReceivedNoteRep.findById(id).orElse(null);
        if(grn == null) {
            throw new Exception("Goods received note with given id does not exist.");
        }
        else
        return grn;
    }

    @Override
    public void save(GoodsReceivedNote goodsReceivedNote) throws Exception {
        System.out.println("Service update");
        System.out.println(goodsReceivedNote.getEmployee().getId());
        goodsReceivedNoteRep.save(goodsReceivedNote);
    }

    @Override
    public int getNumber() throws Exception {
        System.out.println("get number");
        List<GoodsReceivedNote> grns = goodsReceivedNoteRep.findAll();
        if (grns.isEmpty()) {
            return 1;
        }
        return goodsReceivedNoteRep.getNumber() + 1;
    }

    @Override
    public String existGoodsReceivedNote(int id) {
        GoodsReceivedNote grn = goodsReceivedNoteRep.existGoodsReceivedNote(id);
        if (grn == null) {
            return "";
        } else {
            return grn.getId().toString();
        }
    }

}
