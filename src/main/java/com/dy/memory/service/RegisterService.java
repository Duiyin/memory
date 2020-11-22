package com.dy.memory.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dy.memory.dao.UserDao;
import com.dy.memory.entity.User;
import com.dy.memory.entity.UserDto;
import com.dy.memory.util.ID;
import com.dy.memory.util.MailUtil;
import com.dy.memory.util.ServiceException;

@Component
@Transactional
public class RegisterService {

	@Autowired
	private UserDao<User> userDao;

	public User register(HttpSession session, UserDto userDto) {

		User user = new User();
		if (0 != userDao.getAccountCount(user, userDto.getAccount())) {
			throw new ServiceException("register", "account_registered");
		} else {
			if (MailUtil.isEmail(userDto.getAccount())) {
				user.setAccount(userDto.getAccount());
			}
			user.setPassword(userDto.getPassword());
			user.setUsername(ID.initName(userDto.getAccount()));
			BeanUtils.copyProperties(userDto, user, User.class);
			userDao.save(user);
		}
		return user;
	}
	
	public User passwordReset(HttpSession session, UserDto userDto) {
		List<?> oldUserList = userDao.retrieve(new User(), userDto.getAccount());
		if (1 == oldUserList.size()) {
			User user = (User) oldUserList.get(0);
			user.setPassword(userDto.getPassword());
			userDao.update(user);
			return user;
		} else {
			throw new ServiceException("unregistered", "account_unregistered");
		}
	}
}
