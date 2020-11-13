package com.dy.memory.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

public class BaseDao<T> {
	
	@PersistenceContext
	private EntityManager entityManager;

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	/**
	 * 保存一条记录
	 * @param obj
	 */
	public void save(Object obj) {
		getSession().save(obj);
	}
	
	/**
	 * 更新一条记录
	 * @param obj
	 */
	public void update(Object obj){
		getSession().update(obj);
	}
	
	/**
	 * 删除一条记录
	 * @param t
	 * @param id
	 */
	public void delete(T t, String id){
		Object obj = getById(t, id);
		getSession().delete(obj);
	}
	
	/**
	 * 查询一条数据
	 * @param t
	 * @param id
	 * @return obj
	 */
	public Object getById(T t,String id){
		Object obj = (Object) getSession().get(t.getClass(),id);
		return obj;
	}
	
	public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		return criteria.list();
	}
}
