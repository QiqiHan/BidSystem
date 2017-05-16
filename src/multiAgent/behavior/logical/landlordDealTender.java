package multiAgent.behavior.logical;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import multiAgent.agent.landlordAgent;
import multiAgent.behavior.message.landlordPropose;
import multiAgent.ontology.Bid;
import multiAgent.ontology.Tender;

/**
 * Created by H77 on 2017/5/8.
 * 这个Behaviour主要是处理招标书，并生成相应结果。
 */
public class landlordDealTender extends OneShotBehaviour{

    private Tender tender;
    private AID receive;
    private landlordAgent agent;
    public landlordDealTender(Agent agent , Tender tender, AID receive){
        super(agent);
        this.agent = (landlordAgent) agent;
        this.tender = tender;
        this.receive = receive;
    }
    public void action() {
        int type = (int)(Math.random()*2);
        Bid bid = null;
        if(type == 1) {
            bid = new Bid(tender.getSource(), "南京大酒店", 100, myAgent.getAID() ,type);
        }else{
            bid = new Bid(tender.getSource(), "", 0, myAgent.getAID() ,type);
        }
        myAgent.addBehaviour(new landlordPropose(myAgent,bid,receive));
    }
}
