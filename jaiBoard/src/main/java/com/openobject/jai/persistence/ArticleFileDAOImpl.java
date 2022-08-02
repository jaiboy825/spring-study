package com.openobject.jai.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleFileDAOImpl implements ArticleFileDAO {
  
  private static final String NAMESPACE = "com.openobject.jai.mappers.upload.ArticleFileMapper";
  
  @Inject
  private SqlSession sqlSession;
  
  @Override
  public void addAttach(String fullName, Integer article_no) throws Exception {
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("fullName", fullName);
    paramMap.put("article_no", article_no);
    sqlSession.insert(NAMESPACE + ".addAttach", paramMap);
    
  }
  
  @Override
  public List<String> getAttach(Integer article_no) throws Exception {
    return sqlSession.selectList(NAMESPACE + ".getAttach", article_no);
  }
  
  @Override
  public void replaceAttach(String fullName, Integer article_no) throws Exception {
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("fullName", fullName);
    paramMap.put("article_no", article_no);
    sqlSession.insert(NAMESPACE + ".replaceAttach", paramMap);
  }
  
  @Override
  public void deleteAttach(String fullName) throws Exception {
    sqlSession.delete(NAMESPACE + ".deleteAttach", fullName);
  }
  
  @Override
  public void deleteAllAttach(Integer article_no) throws Exception {
    sqlSession.delete(NAMESPACE + ".deleteAllAttach", article_no);
  }
  
  @Override
  public void updateAttachCnt(Integer article_no) throws Exception {
    sqlSession.update(NAMESPACE + ".updateAttachCnt", article_no);
  }
  
}
