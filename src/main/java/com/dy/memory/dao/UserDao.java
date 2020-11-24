package com.dy.memory.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dy.memory.entity.User;

@Component
@Transactional
public class UserDao extends BaseDao<User> {

	/**
	 * 指定条件查用户
	 */
	@Override
	public List<User> retrieve(String str) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		Disjunction dis = Restrictions.disjunction();
		dis.add(Property.forName("id").eq(str));
		dis.add(Property.forName("account").eq(str));
		dc.add(dis);
		return findAllByCriteria(dc);
	}

	/**
	 * 返回已查用户的数量
	 * 
	 */
	public Integer getUserTotal(String str) {
		return retrieve(str).size();
	}
}
