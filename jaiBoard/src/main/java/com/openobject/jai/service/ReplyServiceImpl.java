package com.openobject.jai.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.openobject.jai.commons.paging.Criteria;
import com.openobject.jai.domain.ReplyVO;
import com.openobject.jai.persistence.ArticleDAO;
import com.openobject.jai.persistence.ReplyDAO;

@Repository
public class ReplyServiceImpl implements ReplyService {
  
  private final ReplyDAO replyDAO;
  
  private final ArticleDAO articleDAO;
  
  @Inject
  public ReplyServiceImpl(ReplyDAO replyDAO, ArticleDAO articleDAO) {
    this.replyDAO = replyDAO;
    this.articleDAO = articleDAO;
  }
  
  @Override
  public List<ReplyVO> list(Integer article_no) throws Exception {
    return replyDAO.list(article_no);
  }
  
  @Transactional
  @Override
  public void addReply(ReplyVO replyVO) throws Exception {
    replyDAO.create(replyVO);
    articleDAO.updateReplyCnt(replyVO.getArticle_no(), 1);
  }
  
  @Override
  public void update(ReplyVO replyVO) throws Exception {
    replyDAO.update(replyVO);
  }
  
  @Transactional
  @Override
  public void removeReply(Integer reply_no) throws Exception {
    replyDAO.delete(reply_no);
    articleDAO.updateReplyCnt(reply_no, -1);
  }
  
  @Override
  public List<ReplyVO> getRepliesPaging(Integer article_no, Criteria criteria) throws Exception {
    return replyDAO.listPaging(article_no, criteria);
  }
  
  @Override
  public int countReplies(Integer article_no) throws Exception {
    return replyDAO.countReplies(article_no);
  }
  
  @Override
  public List<ReplyVO> userReplies(String userId) throws Exception {
    System.out.println("여기닷2");
    return replyDAO.userReplies(userId);
  }
}
