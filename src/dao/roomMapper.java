package dao;

import DO.room;

public interface roomMapper {
    int deleteByPrimaryKey(Integer roomid);

    int insert(room record);

    int insertSelective(room record);

    room selectByPrimaryKey(Integer roomid);

    room selectByLandlordIdAndRoomType(Integer landlordId,String roomType);

    int updateByPrimaryKeySelective(room record);

    int updateByPrimaryKey(room record);
}