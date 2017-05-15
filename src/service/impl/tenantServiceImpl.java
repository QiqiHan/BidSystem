package service.impl;

import DO.user;
import dao.userMapper;
import org.apache.ibatis.session.SqlSession;
import service.tenantService;
import util.DBTest;
import util.DBTools;

/**
 * Created by H77 on 2017/5/15.
 */
public class tenantServiceImpl implements tenantService {

    public boolean createTenant(user user) {
        SqlSession sqlSession = DBTools.getSession();
        userMapper mapper = sqlSession.getMapper(dao.userMapper.class);
        mapper.insert(user);
        sqlSession.commit();
        return true;
    }

    public user findTenant(int tenantId) {
        SqlSession sqlSession = DBTools.getSession();
        userMapper mapper = sqlSession.getMapper(dao.userMapper.class);
        user user = mapper.selectByPrimaryKey(tenantId);
        return user;
    }


}
