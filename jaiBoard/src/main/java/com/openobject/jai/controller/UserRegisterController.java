package com.openobject.jai.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.openobject.jai.domain.UserVO;
import com.openobject.jai.service.UserService;

@Controller
@RequestMapping("/user")
public class UserRegisterController {
  private final UserService userService;
  
  @Inject
  public UserRegisterController(UserService userService) {
    this.userService = userService;
  }
  
  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String registerGET() throws Exception {
    return "/user/register";
  }
  
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String registerPOST(UserVO userVO, RedirectAttributes redirectAttributes) throws Exception {
    String hashedPw = BCrypt.hashpw(userVO.getUserPw(), BCrypt.gensalt());
    userVO.setUserPw(hashedPw);
    userService.register(userVO);
    redirectAttributes.addFlashAttribute("msg", "REGISTERD");
    
    return "redirect:/user/login";
  }
}
