package multiAgent.behavior.logical;

import jade.core.behaviours.OneShotBehaviour;
import multiAgent.ontology.Bid;
import multiAgent.ontology.Order;
import service.common.agentHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H77 on 2017/5/15.
 */
public class tenantBackOrderResult extends OneShotBehaviour {

    private Order order = null;
    private List<Bid> bids = null;

    public tenantBackOrderResult(Order order , List<Bid> bids){
        this.order = order;
        this.bids = bids;
    }
    public void action() {
        //这部分代码是将agent的处理结果返回给外部程序。
        Bid bid = new Bid();
        bid.setRoom("____订单信息____：布丁酒店");
        bid.setPrice(120);
        List<Bid> bids = new ArrayList<Bid>();
        bids.add(bid);
        agentHandler.results.put("1",bids);
    }
}
