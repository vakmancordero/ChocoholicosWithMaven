package com.devlabs.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.devlabs.persistence.dao.ProviderDao;

/**
 *
 * @author VakSF
 */
public class LoginService {
    
    private final ProviderDao providerDao = new ProviderDao();
    
    public LoginService() {
        
    }
    
    public boolean login(String user, String password) {
        
        Criteria loginCriteria = this.providerDao.getSession().createCriteria(
                this.providerDao.clazz());
        
        loginCriteria.add(Restrictions.eq("user", user));
        loginCriteria.add(Restrictions.eq("password", password));
        
        return this.providerDao.readUniqueByCriteria(loginCriteria) != null;
    }
    
}
