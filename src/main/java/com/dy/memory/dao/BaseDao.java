package com.dy.memory.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class BaseDao<T> {

	@PersistenceContext
	private EntityManager entityManager;

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	/**
	 * 保存一条记录
	 * 
	 * @param obj
	 */
	public void save(Object obj) {
		getSession().save(obj);
	}

	/**
	 * 更新一条记录
	 * 
	 * @param obj
	 */
	public void update(Object obj) {
		getSession().update(obj);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param obj type
	 * @param id
	 */
	public void delete(T t, String id) {
		Object obj = retrieve(t, id);
		getSession().delete(obj);
	}

	/**
	 * 查找一条数据
	 * 
	 * @param obj type
	 * @param id
	 * @return obj
	 */
	public Object retrieve(T t, String id) {
		Object obj = (Object) getSession().get(t.getClass(), id);
		return obj;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		return criteria.list();
	}
}
