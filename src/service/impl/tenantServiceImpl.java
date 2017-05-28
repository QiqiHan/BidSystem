package service.impl;

import DO.orderRecord;
import DO.tenant;
import DO.user;
import dao.orderRecordMapper;
import dao.tenantMapper;
import dao.userMapper;
import jade.core.AID;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import multiAgent.ontology.Bid;
import multiAgent.ontology.Order;
import org.apache.ibatis.session.SqlSession;
import service.common.agentHandler;
import service.tenantService;
import util.CondVar;
import util.DBTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H77 on 2017/5/15.
 */
public class tenantServiceImpl implements tenantService {

    private static tenantService tenantImpl = new tenantServiceImpl();
    private tenantServiceImpl(){
    }
    public static tenantService getInstance(){return tenantImpl;}
    public boolean createTenant(tenant user) {
        SqlSession sqlSession = DBTools.getSession();
        tenantMapper mapper = sqlSession.getMapper(dao.tenantMapper.class);
        mapper.insert(user);
        sqlSession.commit();
        return true;
    }

    public tenant findTenant(int tenantId) {
        SqlSession sqlSession = DBTools.getSession();
        tenantMapper mapper = sqlSession.getMapper(dao.tenantMapper.class);
        tenant user = mapper.selectByPrimaryKey(tenantId);
        return user;
    }
    public void createAgent(int tenantId) {
        tenant user = this.findTenant(tenantId);
        String name = user.getName();
        AgentContainer container = agentHandler.containers.get("main");
        try {
            CondVar startUpLatch = new CondVar();
            AgentController tenantAgent = container.createNewAgent(name,"multiAgent.agent.tenantAgent",new Object[] { startUpLatch ,user});
            tenantAgent.start();
            try {
                startUpLatch.waitOn();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            agentHandler.agents.put(name,tenantAgent);
            agentHandler.aids.put(name,new AID(name,false));
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
    public void closeAgent(int tenantId) {
        tenant user = this.findTenant(tenantId);
        String name = user.getName();
        AgentController tenantAgent = agentHandler.agents.get(name);
        try {
            tenantAgent.kill();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
        agentHandler.agents.remove(name);
        agentHandler.aids.remove(name);
    }

    public void putOrder(String name,Order o) {
        AgentController tenantAgent = agentHandler.agents.get(name);
        try {
            tenantAgent.putO2AObject(o,false);
//            List<Bid> bids = null;
//            while(bids == null){
//                bids = agentHandler.results.get(o.getId());
//            }
//            agentHandler.results.remove(o.getId());
//            try {
//                Thread.sleep(2500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("---------------------");
//            System.out.println(bids.get(0).getRoom().toString());
//            System.out.println("价格："  +bids.get(0).getPrice());
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }


}
