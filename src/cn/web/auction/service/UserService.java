package cn.web.auction.service;

import cn.web.auction.pojo.Auctionuser;

public interface UserService {
	
	/**
	 * ��¼ҵ�񷽷�
	 * @param username
	 * @param password
	 * @return �û����� null��ʾ��¼ʧ��
	 */
	public Auctionuser login(String username,String password);

}
