package service;

import DO.user;
import jade.core.AID;
import multiAgent.ontology.Order;
import service.impl.managerServiceImpl;
import service.impl.tenantServiceImpl;

import java.util.Date;

/**
 * Created by H77 on 2017/5/15.
 */
public class mainTest {

    public static void main(String[] args){
         tenantService tenant = new tenantServiceImpl();
//         user user = new user("h88","123456","male","student",18);
//         tenant.createTenant(user);
         managerService manager = new managerServiceImpl();
         manager.initSystem();
         user user = tenant.findTenant(1);
         Date dStart = new Date(2017,5,9);
         Date dEnd = new Date(2017,5,12);
         Order order = new Order("1","小明","南京市南大","豪华套件",1,dStart,dEnd,120,new AID("f2",false));
         tenant.createAgent(user.getUsername());
         tenant.putOrder(user.getUsername(),order);
    }
}
