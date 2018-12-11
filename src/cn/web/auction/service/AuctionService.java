package cn.web.auction.service;

import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;

import cn.web.auction.pojo.Auction;

public interface AuctionService {
	/**
	 * @return
	 * 查询所有商品
	 */
	public List<Auction> findAuctions(Auction auction);
	public void addAuction(Auction auction);
	public void updateAuction(Auction auction);
	public Auction getAuctionById(int auctionid);
	
}
