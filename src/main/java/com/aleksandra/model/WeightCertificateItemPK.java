/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Embeddable
public class WeightCertificateItemPK implements Serializable{

    @Basic(optional = false)
    @NotNull
    @Column(name = "weightCertificateID")
    private int weightCertificateID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itemNum")
    private int itemNum;

    public WeightCertificateItemPK() {
    }

    public WeightCertificateItemPK(int weightCertificateID, int itemNum) {
        this.weightCertificateID = weightCertificateID;
        this.itemNum = itemNum;
    }

    public int getWeightCertificateID() {
        return weightCertificateID;
    }

    public void setWeightCertificateID(int weightCertificateID) {
        this.weightCertificateID = weightCertificateID;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.weightCertificateID;
        hash = 83 * hash + this.itemNum;
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
        final WeightCertificateItemPK other = (WeightCertificateItemPK) obj;
        if (this.weightCertificateID != other.weightCertificateID) {
            return false;
        }
        if (this.itemNum != other.itemNum) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WeightCertificateItemPK{" + "weightCertificateID=" + weightCertificateID + ", itemNum=" + itemNum + '}';
    }
    
}
