package cn.web.auction.service;

import java.util.List;

import cn.web.auction.pojo.Auction;

public interface AuctionService {
	/**
	 * @return
	 * ��ѯ������Ʒ
	 */
	public List<Auction> findAuctions(Auction auction);
	public void addAuction(Auction auction);
	
}
