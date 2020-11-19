package com.dy.memory.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserDao<T> extends BaseDao<T>{
	
	@Override
	public List<?> retrieve(T t, String str) {
		DetachedCriteria dc = DetachedCriteria.forClass(t.getClass());
		Disjunction dis = Restrictions.disjunction();
		dis.add(Property.forName("id").eq(str));
		dis.add(Property.forName("account").eq(str));
		dc.add(dis);
		List<?> list = findAllByCriteria(dc);
		return list;
	}
	
	/**
	 * 查询是否有相同账号
	 * 
	 */
	public Integer getAccountCount(T t, String str){
		return retrieve(t, str).size();
	}
}
