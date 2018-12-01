package cn.web.auction.mapper;

import cn.web.auction.pojo.Auctionuser;
import cn.web.auction.pojo.AuctionuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuctionuserMapper {
    int countByExample(AuctionuserExample example);

    int deleteByExample(AuctionuserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(Auctionuser record);

    int insertSelective(Auctionuser record);

    List<Auctionuser> selectByExample(AuctionuserExample example);

    Auctionuser selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") Auctionuser record, @Param("example") AuctionuserExample example);

    int updateByExample(@Param("record") Auctionuser record, @Param("example") AuctionuserExample example);

    int updateByPrimaryKeySelective(Auctionuser record);

    int updateByPrimaryKey(Auctionuser record);
}