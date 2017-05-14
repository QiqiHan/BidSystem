package dao;

import DO.orderRecord;

public interface orderRecordMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(orderRecord record);

    int insertSelective(orderRecord record);

    orderRecord selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(orderRecord record);

    int updateByPrimaryKey(orderRecord record);
}