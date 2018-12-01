package cn.web.auction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.web.auction.mapper.AuctionuserMapper;
import cn.web.auction.pojo.Auctionuser;
import cn.web.auction.pojo.AuctionuserExample;
import cn.web.auction.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private AuctionuserMapper userMapper;
	@Override
	public Auctionuser login(String username, String password) {
		AuctionuserExample example=new AuctionuserExample();
		AuctionuserExample.Criteria criteria=example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andUserpasswordEqualTo(password);
		List<Auctionuser> list=userMapper.selectByExample(example);
		
		if(list !=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
