package util;

import DO.bid;
import dao.bidMapper;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by zy on 17/5/15.
 */
public class DBTest {

    //调用数据库示例
    public static void main(String[] args){
        SqlSession sqlSession = DBTools.getSession();
        bidMapper bidMapper = sqlSession.getMapper(dao.bidMapper.class);
        bid bid = new bid();
        bid.setLandlordid(1);
        bid.setOrderid("1");
        bid.setPrice(1);
        bid.setResult(1);
        bid.setRoomid(1);
        int i = bidMapper.insert(bid);
        System.out.println(i);
        sqlSession.commit();//如果是增删改，记得一定要commit
    }
}
