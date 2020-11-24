package com.dy.memory.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dy.memory.dao.DiaryDao;
import com.dy.memory.entity.Diary;
import com.dy.memory.entity.DiaryDto;
import com.dy.memory.entity.User;
import com.dy.memory.util.MyPage;

@Component
@Transactional
public class DiaryService {

	@Autowired
	private DiaryDao diaryDao;

	public Diary addDiary(User user, DiaryDto diaryDto) {

		Diary diary = new Diary();
		diary.setTitle(diaryDto.getDiarytitle());
		diary.setContent(diaryDto.getDiarycontent());
		diary.setUser(user);
		BeanUtils.copyProperties(diaryDto, diary, Diary.class);
		diaryDao.save(diary);
		return diary;
	}

	// 分页查询
	public MyPage<Diary> getDiaryList(User user, int page, int pagesize) {
		return diaryDao.getDiaryList(user, page, pagesize);
	}
}
