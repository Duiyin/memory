package com.dy.memory.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dy.memory.entity.Diary;
import com.dy.memory.entity.User;
import com.dy.memory.util.MyPage;

@Component
@Transactional
public class DiaryDao extends BaseDao<Diary> {

	public MyPage<Diary> getDiaryList(User user, int page, int pagesize) {
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Diary.class);
			dc.add(Property.forName("user.id").eq(user.getId()));
			try {
				if (pagesize <= 0) {
					pagesize = 20;
				}
			} catch (Exception e) {
			}
			dc.addOrder(Order.desc("ctime"));
			return findPageByCriteria(dc, pagesize, page);
		} catch (Exception e) {
			throw e;
		}
	}
}
