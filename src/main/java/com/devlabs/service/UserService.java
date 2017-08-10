package com.devlabs.service;

import com.devlabs.model.Member;
import com.devlabs.model.Provider;
import com.devlabs.model.Service;

import com.devlabs.persistence.dao.MemberDao;
import com.devlabs.persistence.dao.ProviderDao;
import com.devlabs.persistence.dao.ServiceDao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

/**
 *
 * @author VakSF
 */
public class UserService {
    
    private final MemberDao memberDao = new MemberDao();
    private final ProviderDao providerDao = new ProviderDao();
    private final ServiceDao serviceDao = new ServiceDao();
    
    public UserService() {
        
    }
    
    public boolean createMember(Member member) {
        return this.memberDao.create(member) != null;
    }
    
    public boolean editMember(Member member) {
        return this.memberDao.update(member);
    }
    
    public boolean deleteMember(Long id) {
        return this.memberDao.delete(this.memberDao.read(id));
    }
    
    public boolean createProvider(Provider provider) {
        return this.providerDao.create(provider) != null;
    }
    
    public boolean editProvider(Provider provider) {
        return this.providerDao.update(provider);
    }
    
    public boolean deleteProvider(Long id) {
        return this.providerDao.delete(this.providerDao.read(id));
    }
    
    public List<Provider> getProviders() {
        return this.providerDao.readAll();
    }
    
    public List<Member> getMembers() {
        return this.memberDao.readAll();
    }
    
    public List<Provider> searchProviders(String word) {
        
        Criteria add = this.providerDao.getSession().createCriteria(
                this.providerDao.clazz()).add(
                        Restrictions.like("name", "%" + word + "%")
                );
        
        return this.providerDao.readByCriteria(add);
    }
    
    public List<Member> searchMembers(String word) {
        
        Criteria add = this.memberDao.getSession().createCriteria(
                this.memberDao.clazz()).add(
                        Restrictions.like("name", "%" + word + "%")
                );
        
        return this.memberDao.readByCriteria(add);
    }
    
    public List<Service> searchServices(String word) {
        
        SimpleExpression name = Restrictions.like("name", "%" + word + "%");
        SimpleExpression code = Restrictions.like("code", "%" + word + "%");
        
        LogicalExpression orExp = Restrictions.or(name, code);
        
        Criteria add = this.serviceDao.getSession().createCriteria(
                this.serviceDao.clazz()).add(orExp);
        
        return this.serviceDao.readByCriteria(add);
    }
    
}
