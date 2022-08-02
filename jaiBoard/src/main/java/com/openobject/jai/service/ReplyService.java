package com.openobject.jai.service;

import java.util.List;

import com.openobject.jai.commons.paging.Criteria;
import com.openobject.jai.domain.ReplyVO;

public interface ReplyService {
	List<ReplyVO> list(Integer article_no) throws Exception;

	void update(ReplyVO replyVO) throws Exception;

	List<ReplyVO> getRepliesPaging(Integer article_no, Criteria criteria) throws Exception;

	int countReplies(Integer article_no) throws Exception;

  void addReply(ReplyVO replyVO) throws Exception;

  void removeReply(Integer reply_no) throws Exception;
  
  List<ReplyVO> userReplies(String userId) throws Exception;
}
