package multiAgent.behavior;

import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import multiAgent.ontology.Order;
import multiAgent.ontology.Tender;
import multiAgent.util.AMSUtil;
import multiAgent.util.DFUtil;

import java.util.List;

/**
 * Created by H77 on 2017/5/6.
 * 这个Behaviour主要是根据订单请求信息，筛选搜索对应的潜在房东
 * 并向这些房东发送招标书
 */
public class selectAnalysis extends OneShotBehaviour {


    private Order order = null;
    private Agent myAgent = null;

    public selectAnalysis(Agent agent,Order order){
        this.order = order;
        myAgent = agent;
    }
    public void action() {
        Tender tender = new Tender(order.getAddress(),order.getPrice(),order.getSource());
        List<AID> aids = DFUtil.searchServiceAIDs(myAgent,"landlord");
        myAgent.addBehaviour(new selectPropose(myAgent,tender,aids));
    }
}
