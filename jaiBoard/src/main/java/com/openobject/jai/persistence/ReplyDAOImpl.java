package com.openobject.jai.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.openobject.jai.commons.paging.Criteria;
import com.openobject.jai.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
  private static String NAMESPACE = "com.openobject.jai.mappers.reply.ReplyMapper";
  
  private final SqlSession sqlSession;
  
  @Inject
  public ReplyDAOImpl(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }
  
  @Override
  public List<ReplyVO> list(Integer article_no) throws Exception {
    return sqlSession.selectList(NAMESPACE + ".list", article_no);
  }
  
  @Override
  public void create(ReplyVO replyVO) throws Exception {
    sqlSession.insert(NAMESPACE + ".create", replyVO);
  }
  
  @Override
  public void update(ReplyVO replyVO) throws Exception {
    sqlSession.update(NAMESPACE + ".update", replyVO);
  }
  
  @Override
  public void delete(Integer reply_no) throws Exception {
    sqlSession.delete(NAMESPACE + ".delete", reply_no);
  }
  
  @Override
  public List<ReplyVO> listPaging(Integer article_no, Criteria criteria) throws Exception {
    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("article_no", article_no);
    paramMap.put("criteria", criteria);
    return sqlSession.selectList(NAMESPACE + ".listPaging", paramMap);
  }
  
  @Override
  public int countReplies(Integer article_no) throws Exception {
    return sqlSession.selectOne(NAMESPACE + ".countReplies", article_no);
  }
  
  @Override
  public int getArticleNo(Integer reply_no) throws Exception {
    return sqlSession.selectOne(NAMESPACE + ".getArticleNo", reply_no);
  }
  
  @Override
  public List<ReplyVO> userReplies(String userId) throws Exception {
    System.out.println(sqlSession.selectList(NAMESPACE + ".userReplies", userId));
    return sqlSession.selectList(NAMESPACE + ".userReplies", userId);
  }
  
}
