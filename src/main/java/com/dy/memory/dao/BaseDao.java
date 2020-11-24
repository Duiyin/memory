package com.dy.memory.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dy.memory.util.MyPage;

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
	 * @param obj
	 *            type
	 * @param id
	 */
	public void delete(String id) {
		Object obj = retrieve(id);
		getSession().delete(obj);
	}

	/**
	 * 根据id查数据
	 * 
	 * @param obj
	 *            type
	 * @param id
	 * @return obj
	 */
	public List<T> retrieve(String id) {
		return (List<T>) getSession().get(Object.class, id);
	}

	public MyPage<T> findPageByCriteria(final DetachedCriteria detachedCriteria) {
		return findPageByCriteria(detachedCriteria, MyPage.PAGESIZE, 0);
	}

	public MyPage<T> findPageByCriteria(final DetachedCriteria detachedCriteria, int page) {
		return findPageByCriteria(detachedCriteria, MyPage.PAGESIZE, page);
	}

	public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		return criteria.list();
	}

	public List<T> findAllByCriteria(Session session, final DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		return criteria.list();
	}

	public MyPage<T> findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, int page) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		Object object = criteria.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = 0;
		try {
			totalCount = (Long) object;
		} catch (Exception e) {
		}
		MyPage<T> ps = new MyPage<T>((int) totalCount, pageSize, page);
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<T> items = criteria.setFirstResult(ps.getStartindex()).setMaxResults(pageSize).list();
		ps.setItems(items);
		return ps;
	}

	public MyPage<T> findPageByCriteria(Session session, final DetachedCriteria detachedCriteria, final int pageSize,
			int page) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		Object object = criteria.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = 0;
		try {
			totalCount = (Long) object;
		} catch (Exception e) {
		}
		MyPage<T> ps = new MyPage<T>((int) totalCount, pageSize, page);
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<T> items = criteria.setFirstResult(ps.getStartindex()).setMaxResults(pageSize).list();
		ps.setItems(items);
		return ps;
	}
}
