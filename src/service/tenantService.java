package service;

import DO.user;
import multiAgent.ontology.Order;

/**
 * Created by H77 on 2017/5/15.
 */
public interface tenantService {
    public boolean createTenant(user user);
    public user findTenant(int tenantId);
    public void createAgent(String name);
    public void closeAgent(String name);
    public void putOrder(String name,Order o);
}
