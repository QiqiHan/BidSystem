package dao.daoImpl;

import DO.bid;
import DO.orderRecord;
import dao.bidMapper;
import dao.orderRecordMapper;
import org.apache.ibatis.session.SqlSession;
import util.DBTools;

import java.util.List;

/**
 * Created by sam on 2017/5/28.
 */
public class tenantDao {

    public List<orderRecord> getrecords(int tenantId){
        SqlSession sqlSession = DBTools.getSession();
        orderRecordMapper recordMapper = sqlSession.getMapper(dao.orderRecordMapper.class);
        List<orderRecord> allrecord = recordMapper.getallorder(tenantId);
        return allrecord;
    }

    public boolean saveBid(bid result){
        SqlSession sqlSession = DBTools.getSession();
        bidMapper bids = sqlSession.getMapper(dao.bidMapper.class);
        bids.insert(result);
        return true;
    }
}
