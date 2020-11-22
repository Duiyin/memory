package com.dy.memory;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.dy.memory.dao.UserDao;
import com.dy.memory.entity.User;

@SpringBootApplication
public class MemoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoryApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.CHINA);
		return slr;
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setCacheSeconds(3600); // refresh cache once per hour
		return messageSource;
	}

	/**
	 * 初始化 账号数据插入
	 * 
	 * @param userDao
	 * @return
	 */
	@Bean
	public CommandLineRunner initialAdmin(@Autowired UserDao<User> userDao) {
		return args -> {
			User user = new User();
			user.init();
			if (0 == userDao.getAccountCount(user, user.getAccount())) {
				userDao.save(user);
				System.err.println("初始账号成功创建");
			} else {
				System.err.println("初始账号已经存在");
			}
		};
	}
}
