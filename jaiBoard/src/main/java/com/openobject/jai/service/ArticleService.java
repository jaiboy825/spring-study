package com.openobject.jai.service;

import java.util.List;

import com.openobject.jai.commons.paging.Criteria;
import com.openobject.jai.commons.paging.SearchCriteria;
import com.openobject.jai.domain.ArticleVO;

public interface ArticleService {

	void create(ArticleVO articleVO) throws Exception;

	ArticleVO read(Integer article_no) throws Exception;

	void update(ArticleVO articleVO) throws Exception;

	void delete(Integer article_no) throws Exception;

	List<ArticleVO> listAll() throws Exception;

	List<ArticleVO> listCriteria(Criteria criteria) throws Exception;

	int countArticles(Criteria criteria) throws Exception;

	List<ArticleVO> listSearch(SearchCriteria searchCriteria) throws Exception;

	int countSearchArticles(SearchCriteria searchCriteria) throws Exception;
	
	List<String> getAttach(Integer article_no) throws Exception;
	
	void deleteAttach(String fileName) throws Exception;
	
	void updateAttachCnt(Integer article_no) throws Exception;
	
	List<ArticleVO> userBoardList(String uid) throws Exception;
	
}
