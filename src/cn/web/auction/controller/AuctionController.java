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
		//��ҳ���ز���
		PageHelper.startPage(pageNo, PAGE_SIZE);
		List<Auction> list = auctionService.findAuctions(condition);
		mv.addObject("auctionList",list);
		//��ȡ��ҳbean pageinfo
		PageInfo pageInfo=new PageInfo<>(list);
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("index");
		return mv;
	}
	
	/**
	 * �ļ��ϴ�
	 * @param auction
	 * @param pic
	 * @return
	 */
	@RequestMapping("publishAuctions")
	public String publicAuctions(Auction auction,MultipartFile pic,HttpSession session){
		//������������pic���浽tomcatĿ¼
		try {
			//ָ���ļ��ı���Ŀ¼���ڷ������˵�һ������·��)
			//��--eclipseĬ����Ŀ������workplace�У���Ҫ��Ϊ������tomcat��
			String path=session.getServletContext().getRealPath("upload");
			System.out.println(path);
			if(pic.getSize()>0){
				File targetFile=new File(path,pic.getOriginalFilename());
				//���ļ�д��Ŀ¼
				pic.transferTo(targetFile);
				//���ļ������ļ��������õ�pojo
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
}

