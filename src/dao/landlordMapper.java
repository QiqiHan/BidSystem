package dao;

import DO.landlord;

public interface landlordMapper {
    int deleteByPrimaryKey(Integer landlordid);

    int insert(landlord record);

    int insertSelective(landlord record);

    landlord selectByPrimaryKey(Integer landlordid);

    int updateByPrimaryKeySelective(landlord record);

    int updateByPrimaryKey(landlord record);
}