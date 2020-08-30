package io.powerfulspring.controller;

import io.powerfulspring.model.Video;
import io.powerfulspring.service.HelperService;
import io.powerfulspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.powerfulspring.utils.MethodUtils;

@Controller
public class WelcomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HelperService<Video> videoService;
	
	@GetMapping("/")
	public String welcome(Model model) {
		model.addAttribute("user", userService.findByUsername(MethodUtils.findLoggedInUser().getUsername()));
		return "welcome";
	}

	@GetMapping("/home/list")
	public String welcome(Model model, Pageable pageable) {
		Page<Video> pages = videoService.findAll(pageable);
		model.addAttribute("videos", pages.getContent());
		MethodUtils.pageModel(model, pages);
		return "/video/videos";
	}
}
