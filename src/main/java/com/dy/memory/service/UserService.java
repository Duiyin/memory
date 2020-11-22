package com.dy.memory.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dy.memory.dao.UserDao;
import com.dy.memory.entity.User;
import com.dy.memory.entity.UserDto;
import com.dy.memory.util.ServiceException;

@Component
@Transactional
public class UserService {

	@Autowired
	private UserDao<User> userDao;

	public User passwordReset(HttpSession session, UserDto userDto) {
		List<User> oldUserList = (List<User>) userDao.retrieve(new User(), userDto.getAccount());
		if (1 == oldUserList.size()) {
			User user = oldUserList.get(0);
			user.setPassword(userDto.getPassword());
			userDao.update(user);
			return user;
		} else {
			throw new ServiceException("unregistered", "account_unregistered");
		}
	}
}
