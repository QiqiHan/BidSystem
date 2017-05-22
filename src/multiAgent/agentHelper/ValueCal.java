package multiAgent.agentHelper;

import DO.tenant;
import multiAgent.ontology.Bid;
import sun.management.resources.agent;

import java.util.List;

/**
 * Created by zy on 17/5/8.
 */
public class ValueCal {
    private String character = null;

    //评估房源的类，目前为止只评估price，因为暂时没有别的因素
    public double ValCal(Bid bid){
        return bid.getPrice();
    }

    public double RoomMark(List bids, tenant user){



        return 0;
    }
}
