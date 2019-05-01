/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.model.GoodsReceivedNote;
import java.util.List;

/**
 *
 * @author User
 */
public interface IGoodsReceivedNoteService {

    public void add(GoodsReceivedNote grn) throws Exception;

    public void delete(int id) throws Exception;

    public List<GoodsReceivedNote> findAll() throws Exception;

    public GoodsReceivedNote find(int id) throws Exception;

    public void save(GoodsReceivedNote grn) throws Exception;

    public int getNumber() throws Exception;

    public String existGoodsReceivedNote(int id);

}
