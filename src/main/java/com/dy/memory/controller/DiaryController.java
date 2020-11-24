package com.dy.memory.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dy.memory.entity.Diary;
import com.dy.memory.entity.DiaryDto;
import com.dy.memory.entity.User;
import com.dy.memory.service.DiaryService;
import com.dy.memory.util.MyPage;

@Controller
public class DiaryController {

	@Autowired
	private DiaryService diaryService;

	@PostMapping("/diary/add")
	public String addDiary(HttpSession session, @Valid DiaryDto diaryDto) {

		User user = (User) session.getAttribute("user");
		diaryService.addDiary(user, diaryDto);
		return "redirect:/";
	}

	@GetMapping("/")
	public String getDiaryList(HttpSession session, Model model,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "pagesize", defaultValue = "10", required = false) int pagesize) {

		User user = (User) session.getAttribute("user");
		MyPage<Diary> diaryList = diaryService.getDiaryList(user, page, pagesize);
		model.addAttribute("diarylist", diaryList);
		return "index";
	}
}