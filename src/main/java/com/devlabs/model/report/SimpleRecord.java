package com.devlabs.model.report;

import com.devlabs.model.Record;
import com.devlabs.util.DateUtil;
import java.util.Objects;

public class SimpleRecord {

    private Long id;
    private String member;
    private String provider;
    private String service;
    private String currentDate;
    private String date;
    private String description;
    private String comment;
    
    public SimpleRecord() {
        
    }
    
    public SimpleRecord(Record record) {
        this.id = record.getId();
        this.member = record.getMember().getName();
        this.provider = record.getProvider().getName();
        this.service = record.getService().getName();
        this.currentDate = DateUtil.getDateString(
                record.getCurrentDate());
        this.date = DateUtil.getDateString(
                record.getDate());
        this.description = record.getDescription();
        this.comment = record.getComment();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.id);
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
        
        final SimpleRecord other = (SimpleRecord) obj;
        
        return Objects.equals(this.id, other.id);
    }
    
}
