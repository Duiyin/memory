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
	
	/**
	 * 查询是否有相同记录
	 * 
	 */
	public Integer getById(T t, String str){
		DetachedCriteria dc = DetachedCriteria.forClass(t.getClass());
		Disjunction dis = Restrictions.disjunction();
		dis.add(Property.forName("id").eq(str));
		dis.add(Property.forName("account").eq(str));
		dc.add(dis);
		return findAllByCriteria(dc).size();
	}
}
