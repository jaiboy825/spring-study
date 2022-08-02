package com.openobject.jai.persistence;

import java.sql.Date;

import com.openobject.jai.domain.LoginDTO;
import com.openobject.jai.domain.UserVO;

public interface UserDAO {
  void register(UserVO userVO) throws Exception;
  
  UserVO login(LoginDTO loginDTO) throws Exception;
  
  void keepLogin(String userId, String sessionid, Date sessionLimit) throws Exception;
  
  UserVO checkuserWithSessionKey(String value) throws Exception;
  
  public void updateUser(UserVO userVO) throws Exception;
  
  public UserVO getUser(String uid) throws Exception;
  
  public void updatePw(UserVO userVO) throws Exception;
  
  public void updateUimage(String uid, String uimage) throws Exception;
  
  public void updateLoginDate(String userId) throws Exception;
}
