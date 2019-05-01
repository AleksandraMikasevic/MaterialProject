/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author User
 */
@Entity
@Table(name = "goodsReceivedNote")
@XmlRootElement
public class GoodsReceivedNote implements Serializable{
        @Column(name = "entryDate")
    @Temporal(TemporalType.DATE)
    private Date entryDate;

    @Column(name = "sumWithTax")
    private Double sumWithTax;
    @Column(name = "taxSum")
    private Double taxSum;
    @JoinColumn(name = "supplierID", referencedColumnName = "id")
    @ManyToOne
    private Supplier supplier;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @DateTimeFormat(pattern = "yy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sum")
    private Double sum;
    @JoinColumn(name = "employeeID", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Employee employee;
    @JoinColumn(name = "weightCertificateID", referencedColumnName = "id")
    @OneToOne
    @JsonIgnore
    private WeightCertificate weightCertificate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grn", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<GoodsReceivedNoteItem> goodsReceivedNoteItems;
    @Transient
    private String dateS;

    public GoodsReceivedNote() {
        entryDate = new Date();
        goodsReceivedNoteItems = new ArrayList<>();
    }

    public GoodsReceivedNote(Integer id) {
        this.id = id;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Double getSumWithTax() {
        return sumWithTax;
    }

    public void setSumWithTax(Double sumWithTax) {
        this.sumWithTax = sumWithTax;
    }

    public Double getTaxSum() {
        return taxSum;
    }

    public void setTaxSum(Double taxSum) {
        this.taxSum = taxSum;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public WeightCertificate getWeightCertificate() {
        return weightCertificate;
    }

    public void setWeightCertificate(WeightCertificate weightCertificate) {
        this.weightCertificate = weightCertificate;
    }
    
    @XmlTransient
    @JsonIgnore
    public List<GoodsReceivedNoteItem> getGoodsReceivedNoteItems() {
        return goodsReceivedNoteItems;
    }

    public void setGoodsReceivedNoteItems(List<GoodsReceivedNoteItem> goodsReceivedNoteItems) {
        this.goodsReceivedNoteItems = goodsReceivedNoteItems;
    }

    public String getDateS() {
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = dateS;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final GoodsReceivedNote other = (GoodsReceivedNote) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GoodsReceivedNote{" + "entryDate=" + entryDate + ", sumWithTax=" + sumWithTax + ", taxSum=" + taxSum + ", supplier=" + supplier + ", id=" + id + ", date=" + date + ", sum=" + sum + ", employee=" + employee + ", weightCertificate=" + weightCertificate + ", goodsReceivedNoteItem=" + goodsReceivedNoteItems + ", dateS=" + dateS + '}';
    }
    
    
    
    

}
