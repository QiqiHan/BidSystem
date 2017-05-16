package service;

import DO.user;
import jade.core.AID;
import multiAgent.ontology.Order;
import service.impl.managerServiceImpl;
import service.impl.tenantServiceImpl;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by H77 on 2017/5/15.
 */
public class mainTest {

    public static void main(String[] args){
         tenantService tenant = tenantServiceImpl.getInstance();
//         user user = new user("h88","123456","male","student",18);
//         tenant.createTenant(user);
         managerService manager = managerServiceImpl.getInstance();
         manager.initSystem();
         user user = tenant.findTenant(1);
         Date dStart = new Date(2017,5,9);
         Date dEnd = new Date(2017,5,12);
         Date dCreate = new Date(2017,5,3);
         Order order = new Order("1",
                 "小明",
                 1,
                 "南京市南大",
                 "Theme Hotel",
                 "豪华套件",
                 1,
                 dStart,
                 dEnd,
                 dCreate,
                 120,
                 200,
                 new ArrayList<String>(),
                 new AID("f2",false));
         tenant.createAgent(user.getUsername());
         tenant.putOrder(user.getUsername(),order);
    }
}
