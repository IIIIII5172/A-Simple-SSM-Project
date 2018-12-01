package cn.web.auction.service;

import cn.web.auction.pojo.Auctionuser;

public interface UserService {
	
	/**
	 * 登录业务方法
	 * @param username
	 * @param password
	 * @return 用户对象 null表示登录失败
	 */
	public Auctionuser login(String username,String password);

}
