package service;

import DO.user;

/**
 * Created by H77 on 2017/5/15.
 */
public interface tenantService {
    public boolean createTenant(user user);
    public user findTenant(int tenantId);

}
