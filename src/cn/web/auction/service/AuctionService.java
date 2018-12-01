package cn.web.auction.service;

import java.util.List;

import cn.web.auction.pojo.Auction;

public interface AuctionService {
	/**
	 * @return
	 * 查询所有商品
	 */
	public List<Auction> findAuctions(Auction auction);
	
}
