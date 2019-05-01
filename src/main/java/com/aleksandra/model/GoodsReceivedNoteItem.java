/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "grcnItem")
@XmlRootElement
public class GoodsReceivedNoteItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private GoodsReceivedNoteItemPK goodsReceivedNoteItemPK;
    @Column(name = "serNum")
    private Integer serNum;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity")
    private Double quantity;
    @JoinColumn(name = "materialID", referencedColumnName = "id")
    @ManyToOne
    private Material material;
    @JoinColumn(name = "grcnID", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GoodsReceivedNote grn;

    public GoodsReceivedNoteItem() {
        material = new Material();
    }

    public GoodsReceivedNoteItem(GoodsReceivedNoteItemPK goodsReceivedNoteItemPK) {
        this.goodsReceivedNoteItemPK = goodsReceivedNoteItemPK;
    }

    public GoodsReceivedNoteItemPK getGoodsReceivedNoteItemPK() {
        return goodsReceivedNoteItemPK;
    }

    public void setGoodsReceivedNoteItemPK(GoodsReceivedNoteItemPK goodsReceivedNoteItemPK) {
        this.goodsReceivedNoteItemPK = goodsReceivedNoteItemPK;
    }

    public Integer getSerNum() {
        return serNum;
    }

    public void setSerNum(Integer serNum) {
        this.serNum = serNum;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "GoodsReceivedNoteItem{" + "goodsReceivedNoteItemPK=" + goodsReceivedNoteItemPK + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.goodsReceivedNoteItemPK);
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
        final GoodsReceivedNoteItem other = (GoodsReceivedNoteItem) obj;
        if (!Objects.equals(this.goodsReceivedNoteItemPK, other.goodsReceivedNoteItemPK)) {
            return false;
        }
        return true;
    }

    public GoodsReceivedNote getGrn() {
        return grn;
    }

    public void setGrn(GoodsReceivedNote grn) {
        this.grn = grn;
    }

}
