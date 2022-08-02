package com.openobject.jai.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.openobject.jai.commons.paging.Criteria;
import com.openobject.jai.commons.paging.PageMaker;
import com.openobject.jai.domain.ArticleVO;
import com.openobject.jai.service.ArticleService;

@Controller
@RequestMapping("/article/paging")
public class ArticlePagingController {
	private static final Logger logger = LoggerFactory.getLogger(ArticlePagingController.class);

	private final ArticleService articleService;

	@Inject
	public ArticlePagingController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeGET() {
		logger.info("write GET");
		return "/article/paging/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePost(ArticleVO articleVO, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("write POST");
		logger.info(articleVO.toString());
		articleService.create(articleVO);
		redirectAttributes.addFlashAttribute("msg", "regSuccess");
		return "redirect:/article/paging/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPaging(Model model, Criteria criteria) throws Exception {
		logger.info("listPaging");

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(articleService.countArticles(criteria));

		model.addAttribute("articles", articleService.listCriteria(criteria));
		model.addAttribute("pageMaker", pageMaker);

		return "/article/paging/list";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String readPaging(@RequestParam("article_no") int article_no, @ModelAttribute("criteria") Criteria criteria,
			Model model) throws Exception {
		model.addAttribute("article", articleService.read(article_no));

		return "/article/paging/read";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyGETPaging(@RequestParam("article_no") int article_no,
			@ModelAttribute("criteria") Criteria criteria, Model model) throws Exception {
		logger.info("modifyGetPaging");
		model.addAttribute("article", articleService.read(article_no));

		return "/article/paging/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOSTPaging(ArticleVO articleVO, Criteria criteria, RedirectAttributes redirectAttributes)
			throws Exception {
		logger.info("modifyPOSTPaging");
		articleService.update(articleVO);
		redirectAttributes.addAttribute("page", criteria.getPage());
		redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
		redirectAttributes.addFlashAttribute("msg", "modSuccess");

		return "redirect:/article/paging/list";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removePagign(@RequestParam("article_no") int article_no, Criteria criteria,
			RedirectAttributes redirectAttributes) throws Exception {
		logger.info("remove");
		articleService.delete(article_no);
		redirectAttributes.addAttribute("page", criteria.getPage());
		redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
		redirectAttributes.addFlashAttribute("msg", "delSuccess");

		return "redirect:/article/paging/list";
	}
}
