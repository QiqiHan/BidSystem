package multiAgent.agentHelper;

import multiAgent.ontology.Bid;

/**
 * Created by zy on 17/5/8.
 */
public class ValueCal {

    //评估房源的类，目前为止只评估price，因为暂时没有别的因素
    public static double ValCal(Bid bid){
        return bid.getPrice();
    }
}
