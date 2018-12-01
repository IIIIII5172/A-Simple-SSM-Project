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
		//���ж���֤��
		if(!inputCode.equals(session.getAttribute("numrand"))){
//			mv.addObject("errorMsg","��֤�����");
//			mv.setViewName("login");
			mv.addAttribute("errorMsg","��֤�����");
			return "login";
		}
		//��֤�û���
		Auctionuser loginUser=userService.login(username, userpassword);
		if(loginUser!=null){
			//��session�б����û�����
			session.setAttribute("user", loginUser);
			return "redirect:/auction/queryAuctions";
		}else{
//			mv.addObject("errorMsg","�û������������");
//			mv.setViewName("login");
			mv.addAttribute("errorMsg","�û������������");
			return "login";
		}
	}

}
