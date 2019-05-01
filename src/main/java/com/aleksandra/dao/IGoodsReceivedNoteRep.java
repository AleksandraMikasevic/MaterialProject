/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao;

import com.aleksandra.model.GoodsReceivedNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository("goodsReceivedNoteRep")
public interface IGoodsReceivedNoteRep extends JpaRepository<GoodsReceivedNote, Integer>{

    @Query("SELECT max(g.id) FROM GoodsReceivedNote g")
    int getNumber();

    @Query("SELECT p FROM GoodsReceivedNote p WHERE p.weightCertificate.id = ?1")
    GoodsReceivedNote existGoodsReceivedNote(int id);

}
