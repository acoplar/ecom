package com.ecom.auth.session;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ecom.auth.bean.User;

@Repository("userDetailsDAO")
public class UserDetailsFacade implements UserDetailsFacadeLocal {

    @Autowired
    private SessionFactory sessionFactory;
    private HibernateTemplate hibernateTemplate;

    @PostConstruct
    public void init() {
        hibernateTemplate = new HibernateTemplate(this.sessionFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public User findByUsername(String username) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class).add(Restrictions.eq("username", username));
        List<User> result = this.hibernateTemplate.findByCriteria(criteria);
        return result.isEmpty() ? null : (User) result.get(0);
    }
    
}
