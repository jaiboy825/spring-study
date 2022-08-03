package com.openobject.jai.service;

import java.sql.Date;

import com.openobject.jai.domain.LoginDTO;
import com.openobject.jai.domain.UserVO;

public interface UserService {
  void register(UserVO userVO) throws Exception;
  
  UserVO login(LoginDTO loginDTO) throws Exception;
  
  void keepLogin(String userId, String sessionId, Date next) throws Exception;
  
  UserVO checkLoginBefore(String value) throws Exception;
  
  public void modifyUser(UserVO userVO) throws Exception;
  
  public UserVO getUser(String uid) throws Exception;
  
  public void modifyPw(UserVO userVO) throws Exception;
  
  public void modifyUimage(String uid, String uimage) throws Exception;
  
  public void deleteUser (String uid) throws Exception;
}
