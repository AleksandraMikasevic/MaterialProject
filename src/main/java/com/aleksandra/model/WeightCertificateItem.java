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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "weightCertificateItem")
@XmlRootElement
public class WeightCertificateItem implements Serializable {

    @Size(max = 50)
    @Column(name = "registrationNumber")
    private String registrationNumber;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WeightCertificateItemPK weightCertificateItemPK;
    @Column(name = "serNum")
    private Integer serNum;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "grossWeight")
    private Double grossWeight;
    @Column(name = "netWeight")
    private Double netWeight;
    @JoinColumn(name = "weightCertificateID", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private  WeightCertificate weightCertificate;

    public WeightCertificateItem() {
    }

    public WeightCertificateItem(WeightCertificateItemPK weightCertificateItemPK) {
        this.weightCertificateItemPK = weightCertificateItemPK;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public WeightCertificateItemPK getWeightCertificateItemPK() {
        return weightCertificateItemPK;
    }

    public void setWeightCertificateItemPK(WeightCertificateItemPK weightCertificateItemPK) {
        this.weightCertificateItemPK = weightCertificateItemPK;
    }

    public Integer getSerNum() {
        return serNum;
    }

    public void setSerNum(Integer serNum) {
        this.serNum = serNum;
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    @Override
    public String toString() {
        return "WeightCertificateItem{" + "weightCertificateItemPK=" + weightCertificateItemPK + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.weightCertificateItemPK);
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
        final WeightCertificateItem other = (WeightCertificateItem) obj;
        if (!Objects.equals(this.weightCertificateItemPK, other.weightCertificateItemPK)) {
            return false;
        }
        return true;
    }

    public WeightCertificate getWeightCertificate() {
        return weightCertificate;
    }

    public void setWeightCertificate(WeightCertificate weightCertificate) {
        this.weightCertificate = weightCertificate;
    }

}
