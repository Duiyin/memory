package com.dy.memory.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dy.memory.dao.UserDao;
import com.dy.memory.entity.User;
import com.dy.memory.entity.UserDto;
import com.dy.memory.util.MailUtil;
import com.dy.memory.util.Result;

@Component
@Transactional
public class RegisterService {

	@Autowired
	private UserDao<User> userDao;

	public User register(HttpSession session, UserDto userDto) {
		try {
			User user = new User();
			if (0 != userDao.getById(user, userDto.getAccount())) {
				return (User) Result.failed();
			} else {
				if (MailUtil.isEmail(userDto.getAccount())) {
					user.setAccount(userDto.getAccount());
				}
				user.setPassword(userDto.getPassword());
				BeanUtils.copyProperties(userDto, user, User.class);
				userDao.save(user);
			}
			return user;
		} catch (Exception e) {
			throw e;
		}
	}
}
