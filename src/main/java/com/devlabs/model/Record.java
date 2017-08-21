package com.devlabs.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="record")
public class Record implements Serializable {

    private Long id;
    private Member member;
    private Provider provider;
    private Service service;
    private Date currentDate;
    private Date date;
    private String description;
    private String comment;
    
    public Record() {
        
    }
    
    public Record(Member member, Provider provider, Service service) {
        this.member = member;
        this.provider = provider;
        this.service = service;
    }
    
    public Record(Member member, Provider provider, Service service, Date currentDate, 
            Date date, String description, String comment) {
        this.member = member;
        this.provider = provider;
        this.service = service;
        this.currentDate = currentDate;
        this.date = date;
        this.description = description;
        this.comment = comment;
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
    
    @ManyToOne(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="member_id", nullable=false)
    public Member getMember() {
        return this.member;
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
    
    @ManyToOne(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="provider_id", nullable=false)
    public Provider getProvider() {
        return this.provider;
    }
    
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    
    @ManyToOne(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="service_id", nullable=false)
    public Service getService() {
        return this.service;
    }
    
    public void setService(Service service) {
        this.service = service;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="currentDate", length=19)
    public Date getCurrentDate() {
        return this.currentDate;
    }
    
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", length=19)
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="comment", length=65535)
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
