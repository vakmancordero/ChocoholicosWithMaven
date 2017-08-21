package com.devlabs.service;

import com.devlabs.model.Member;
import com.devlabs.model.Provider;
import com.devlabs.model.Record;
import com.devlabs.model.Service;
import com.devlabs.persistence.dao.MemberDao;
import com.devlabs.persistence.dao.ProviderDao;

import com.devlabs.persistence.dao.RecordDao;
import com.devlabs.persistence.dao.ServiceDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author VakSF
 */
public class ConsultationService {
    
    private final RecordDao recordDao = new RecordDao();
    private final ServiceDao serviceDao = new ServiceDao();
    private final MemberDao memberDao = new MemberDao();
    private final ProviderDao providerDao = new ProviderDao();
    
    public ConsultationService() {
        
    }
    
    public List<Record> getConsultationsById(Long id) {
        
        Criteria recordsCriteria = this.recordDao.getSession().createCriteria(
                this.recordDao.clazz()).add(
                        Restrictions.eq("provider.id", id)
                );
        
        return this.recordDao.readByCriteria(recordsCriteria);
    }
    
    public List<Record> getRecords() {
        return this.recordDao.readAll();
    }
    
    public boolean createConsultation(Record record) {
        return this.recordDao.create(record) != null;
    }
    
    public Service getService(Long serviceId) {
        return this.serviceDao.read(serviceId);
    }
    
    public Member getMember(Long memberId) {
        return this.memberDao.read(memberId);
    }
    
    public Provider getProvider(Long providerId) {
        return this.providerDao.read(providerId);
    }
    
}
