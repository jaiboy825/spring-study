package com.openobject.jai.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.openobject.jai.commons.paging.Criteria;
import com.openobject.jai.domain.ArticleVO;
import com.openobject.jai.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {
	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	private final ArticleService articleService;

	@Inject
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeGET() {
		logger.info("write GET");
		return "/article/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePost(ArticleVO articleVO, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("write POST");
		logger.info(articleVO.toString());
		articleService.create(articleVO);
		redirectAttributes.addFlashAttribute("msg", "regSuccess");
		return "redirect:/article/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		logger.info("list");
		model.addAttribute("articles", articleService.listAll());
		return "/article/list";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam("article_no") int article_no, Model model) throws Exception {
		logger.info("read");
		model.addAttribute("article", articleService.read(article_no));

		return "/article/read";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyGET(@RequestParam("article_no") int article_no, Model model) throws Exception {
		logger.info("modifyGet");
		model.addAttribute("article", articleService.read(article_no));
		return "/article/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(ArticleVO articleVO, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("modifyPost");
		articleService.update(articleVO);
		redirectAttributes.addFlashAttribute("msg", "modSuccess");
		return "redirect:/article/list";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("article_no") int article_no, RedirectAttributes redirectAttributes)
			throws Exception {
		logger.info("remove");
		articleService.delete(article_no);
		redirectAttributes.addFlashAttribute("msg", "delSuccess");
		return "redirect:/article/list";
	}

	@RequestMapping(value = "/listCriteria", method = RequestMethod.GET)
	public String listCriteria(Model model, Criteria criteria) throws Exception {
		logger.info("listCritea");
		model.addAttribute("articles", articleService.listCriteria(criteria));
		return "/article/list_criteria";
	}



}
