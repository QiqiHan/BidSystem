package service;

import DO.tenant;
import jade.core.AID;
import jade.util.leap.ArrayList;
import multiAgent.ontology.Order;
import service.impl.managerServiceImpl;
import service.impl.tenantServiceImpl;

import java.util.Date;

/**
 * Created by H77 on 2017/5/15.
 */
public class mainTest {

    public static void main(String[] args){
         tenantService tenant = tenantServiceImpl.getInstance();
//        user user = new user("h77","123456","male","student",18);
//         tenant.createTenant(user);
         managerService manager = managerServiceImpl.getInstance();
         manager.initSystem();
         tenant user = tenant.findTenant(1);
         Date dStart = new Date(2017,5,28);
         Date dEnd = new Date(2017,5,29);
         Date dCreate = new Date(2017,5,28);
         Order order = new Order("1",
                 "小明",
                 "南京市南大",
                 "Theme",
                 "Business",
                 1,
                 dStart,
                 dEnd,
                 dCreate,
                 100,
                 900,
                 new ArrayList(),
                 new AID(user.getName(),false));
         tenant.createAgent(user.getId());
         tenant.putOrder(user.getName(),order);
    }
}
