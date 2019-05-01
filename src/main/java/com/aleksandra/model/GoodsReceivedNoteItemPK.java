/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Embeddable
public class GoodsReceivedNoteItemPK implements Serializable{
        @Basic(optional = false)
    @NotNull
    @Column(name = "grcnID")
    private int grcnID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itemNum")
    private int itemNum;

    public GoodsReceivedNoteItemPK() {
    }

    public GoodsReceivedNoteItemPK(int grcnID, int itemNum) {
        this.grcnID = grcnID;
        this.itemNum = itemNum;
    }

    public int getGrcnID() {
        return grcnID;
    }

    public void setGrcnID(int grcnID) {
        this.grcnID = grcnID;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    @Override
    public String toString() {
        return "GoodsReceivedNoteItemPK{" + "grcnID=" + grcnID + ", itemNum=" + itemNum + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.grcnID;
        hash = 17 * hash + this.itemNum;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GoodsReceivedNoteItemPK other = (GoodsReceivedNoteItemPK) obj;
        if (this.grcnID != other.grcnID) {
            return false;
        }
        if (this.itemNum != other.itemNum) {
            return false;
        }
        return true;
    }
    
    

}
