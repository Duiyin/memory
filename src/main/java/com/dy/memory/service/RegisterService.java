package com.dy.memory.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dy.memory.dao.UserDao;
import com.dy.memory.entity.User;
import com.dy.memory.entity.UserDto;
import com.dy.memory.util.ID;
import com.dy.memory.util.MailUtil;
import com.dy.memory.util.ServiceException;

@Service
public class RegisterService {

	@Autowired
	private UserDao userDao;

	public User register(HttpSession session, UserDto userDto) {

		User user = new User();
		if (0 != userDao.getUserTotal(userDto.getAccount())) {
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

	public User resetPassword(HttpSession session, UserDto userDto) {
		List<User> oldUserList = userDao.retrieve(userDto.getAccount());
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
