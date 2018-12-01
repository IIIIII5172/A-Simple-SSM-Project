package cn.web.auction.controller;

import java.sql.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.tracing.dtrace.ModuleAttributes;

import cn.web.auction.pojo.Auction;
import cn.web.auction.service.AuctionService;
import sun.net.www.protocol.http.AuthenticationInfo;

@Controller
@RequestMapping("/auction")
public class AuctionController {
	@Autowired
	private AuctionService auctionService;

	public static final int PAGE_SIZE=5;
	   
	@RequestMapping("/queryAuctions")
	public ModelAndView queryAuctions(
						@ModelAttribute("condition")Auction condition,
						@RequestParam(value="pageNo",required=false,defaultValue="1")int  pageNo ){
		ModelAndView mv=new ModelAndView();
		//分页拦截参数
		PageHelper.startPage(pageNo, PAGE_SIZE);
		List<Auction> list = auctionService.findAuctions(condition);
		mv.addObject("auctionList",list);
		//获取分页bean pageinfo
		PageInfo pageInfo=new PageInfo<>(list);
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("index");
		return mv;
	}
}
