package com.dy.memory.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserDao<T> extends BaseDao<T>{
	
	public Integer getById(T t, String account){
		DetachedCriteria dc = DetachedCriteria.forClass(t.getClass());
		Disjunction dis = Restrictions.disjunction();
		dis.add(Property.forName("id").eq(account));
		dis.add(Property.forName("account").eq(account));
		dc.add(dis);
		return findAllByCriteria(dc).size();
	}
}
