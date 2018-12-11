package cn.web.auction.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	
	/**
	 * 文件上传
	 * @param auction
	 * @param pic
	 * @return
	 */
	@RequestMapping("publishAuctions")
	public String publicAuctions(Auction auction,MultipartFile pic,HttpSession session){
		//将二进制数据pic保存到tomcat目录
		try {
			//指定文件的保存目录（在服务器端的一个绝对路径)
			//坑--eclipse默认项目部署在workplace中，需要改为部署在tomcat里
			String path=session.getServletContext().getRealPath("upload");
			System.out.println(path);
			if(pic.getSize()>0){
				File targetFile=new File(path,pic.getOriginalFilename());
				//将文件写入目录
				pic.transferTo(targetFile);
				//把文件名和文件类型设置到pojo
				auction.setAuctionpic(pic.getOriginalFilename());
				auction.setAuctionpictype(pic.getContentType());
			}
			auctionService.addAuction(auction);
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/auction/queryAuctions ";
	}
	
	@RequestMapping("/toUpdate/{auctionid}")
	public ModelAndView toUpdate(@PathVariable int auctionid){
		Auction auction = auctionService.getAuctionById(auctionid);
		ModelAndView mView=new ModelAndView();
		mView.addObject("auction", auction);
		mView.setViewName("updateAuction");
		return mView;
	}
	
	@RequestMapping("/updateAuctoinSubmit")
	public String updateAuctionSubmit(Auction auction,MultipartFile pic,HttpSession session){
		//将二进制数据pic保存到tomcat目录
		try {
			String path=session.getServletContext().getRealPath("upload");
			//判断是否要重新上传图片
			if(pic.getSize()>0){
				//删除旧图片
				File oldFile=new File(path,auction.getAuctionpic());
				if(oldFile.exists()){
					oldFile.delete();
				}
			}
			if(pic.getSize()>0){
				File targetFile=new File(path,pic.getOriginalFilename());
				pic.transferTo(targetFile);
				auction.setAuctionpic(pic.getOriginalFilename());
				auction.setAuctionpictype(pic.getContentType());
			}
			auctionService.updateAuction(auction);
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/auction/queryAuctions ";
	}
}

