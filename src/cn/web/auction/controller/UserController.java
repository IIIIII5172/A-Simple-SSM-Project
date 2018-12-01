package cn.web.auction.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.web.auction.pojo.Auctionuser;
import cn.web.auction.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/doLogin")
	public String doLogin(String username,String userpassword,
								String inputCode,HttpSession session,Model mv){
//		ModelAndView mv=new ModelAndView();
		//先判断验证码
		if(!inputCode.equals(session.getAttribute("numrand"))){
//			mv.addObject("errorMsg","验证码错误！");
//			mv.setViewName("login");
			mv.addAttribute("errorMsg","验证码错误！");
			return "login";
		}
		//验证用户名
		Auctionuser loginUser=userService.login(username, userpassword);
		if(loginUser!=null){
			//在session中保存用户对象
			session.setAttribute("user", loginUser);
			return "redirect:/auction/queryAuctions";
		}else{
//			mv.addObject("errorMsg","用户名或密码错误！");
//			mv.setViewName("login");
			mv.addAttribute("errorMsg","用户名或密码错误！");
			return "login";
		}
	}

}
