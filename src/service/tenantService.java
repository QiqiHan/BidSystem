package service;

import DO.orderRecord;
import DO.tenant;
import DO.user;
import multiAgent.ontology.Order;

import java.util.List;

/**
 * Created by H77 on 2017/5/15.
 */
public interface tenantService {
    public boolean createTenant(tenant user);
    public tenant findTenant(int tenantId);
    public void createAgent(int tenantId);
    public void closeAgent(int tenantId);
    public void putOrder(String name,Order o);

    public List<orderRecord> getrecords(int tenantId);
}
