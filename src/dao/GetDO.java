package dao;

import DO.room;
import org.apache.ibatis.session.SqlSession;
import util.DBTools;

/**
 * Created by zcy on 2017/5/22.
 *
 */
public class GetDO {
    public static room findRoomByLandlordAndType(int landlordid, String type){
        SqlSession sqlSession = DBTools.getSession();
        roomMapper mapper = sqlSession.getMapper(roomMapper.class);
        room room = mapper.selectByLandlordIdAndRoomType(landlordid,type);
        return room;
    }
}
