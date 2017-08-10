package com.devlabs.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment implements Serializable {

    private Long id;
    private Record record;
    private Double amount;
    
    public Payment() {
        
    }

    public Payment(Record record, Double amount) {
       this.record = record;
       this.amount = amount;
    }
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="record_id")
    public Record getRecord() {
        return this.record;
    }
    
    public void setRecord(Record record) {
        this.record = record;
    }
    
    @Column(name="amount", precision=22, scale=0)
    public Double getAmount() {
        return this.amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
