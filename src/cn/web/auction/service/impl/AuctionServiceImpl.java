package cn.web.auction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.web.auction.mapper.AuctionMapper;
import cn.web.auction.pojo.Auction;
import cn.web.auction.pojo.AuctionExample;
import cn.web.auction.pojo.AuctionExample.Criteria;
import cn.web.auction.service.AuctionService;

@Service
public class AuctionServiceImpl implements AuctionService {
	@Autowired
	private AuctionMapper auctionMapper;

	@Override
	public List<Auction> findAuctions(Auction auction) {
		AuctionExample example = new AuctionExample();
		AuctionExample.Criteria criteria = example.createCriteria();
		if (auction != null) {
			if (auction.getAuctionname() != null && !"".equals(auction.getAuctionname())) {
				criteria.andAuctionnameLike("%" + auction.getAuctionname() + "%");
			}
			if (auction.getAuctiondesc() != null && !"".equals(auction.getAuctiondesc())) {
				criteria.andAuctionnameLike("%" + auction.getAuctiondesc() + "%");
			}
			if(auction.getAuctionstarttime()!=null){
				criteria.andAuctionstarttimeGreaterThanOrEqualTo(auction.getAuctionstarttime());
			}
			if(auction.getAuctionendtime()!=null){
				criteria.andAuctionendtimeLessThanOrEqualTo(auction.getAuctionendtime());
			}
			if(auction.getAuctionstartprice()!=null){
				criteria.andAuctionstartpriceGreaterThanOrEqualTo(auction.getAuctionstartprice());
			}
			example.setOrderByClause("auctionstarttime");
		}
		//排序,按照起拍时间降序排列
		List<Auction> list = auctionMapper.selectByExample(example);
		return list;
	}

	@Override
	public void addAuction(Auction auction) {
		auctionMapper.insert(auction);
	}

	@Override
	public void updateAuction(Auction auction) {
		auctionMapper.updateByPrimaryKey(auction);
	}

	@Override
	public Auction getAuctionById(int auctionid) {
		return auctionMapper.selectByPrimaryKey(auctionid);
	}
}
