package com.openobject.jai.service;

import java.sql.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.openobject.jai.domain.LoginDTO;
import com.openobject.jai.domain.UserVO;
import com.openobject.jai.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
  
  private final UserDAO userDAO;
  
  @Inject
  public UserServiceImpl(UserDAO userDAO) {
    this.userDAO = userDAO;
  }
  
  @Override
  public void register(UserVO userVO) throws Exception {
    userDAO.register(userVO);
  }
  
  @Override
  public UserVO login(LoginDTO loginDTO) throws Exception {
    return userDAO.login(loginDTO);
  }
  
  @Override
  public void keepLogin(String userId, String sessionId, Date next) throws Exception {
    userDAO.keepLogin(userId, sessionId, next);
  }
  
  @Override
  public UserVO checkLoginBefore(String value) throws Exception {
    return userDAO.checkuserWithSessionKey(value);
  }
  
  @Override
  public void modifyUser(UserVO userVO) throws Exception {
    userDAO.updateUser(userVO);
  }
  
  @Override
  public UserVO getUser(String uid) throws Exception {
    return userDAO.getUser(uid);
  }
  
  @Override
  public void modifyPw(UserVO userVO) throws Exception {
    userDAO.updatePw(userVO);
  }
  
  @Override
  public void modifyUimage(String uid, String uimage) throws Exception {
    userDAO.updateUimage(uid, uimage);
  }

  @Override
  public void deleteUser(String uid) throws Exception {
    userDAO.deleteUser(uid);
  }
  
}
