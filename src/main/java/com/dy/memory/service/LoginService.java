package com.dy.memory.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dy.memory.dao.UserDao;
import com.dy.memory.entity.User;
import com.dy.memory.util.PasswordUtil;
import com.dy.memory.util.ServiceException;

@Component
@Transactional
public class LoginService {

	@Autowired
	private UserDao<User> userDao;

	public User login(HttpSession session, String account, String password, String vcode) {

		List<?> list = userDao.retrieve(new User(), account);
		if (null != list && list.size() == 1) {
			User user = (User) list.get(0);
			if (!PasswordUtil.authenticatePassword(user.getPassword(), password)) {
				throw new ServiceException("login", "account_or_password_error");
			}
			session.setAttribute("user", user);
			return user;
		} else {
			throw new ServiceException("login", "account_or_password_error");
		}
	}
}
