package multiAgent.behavior.logical;

import DO.landlord;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import multiAgent.agent.landlordAgent;
import multiAgent.behavior.message.landlordPropose;
import multiAgent.ontology.Bid;
import multiAgent.ontology.Room;
import multiAgent.ontology.RoomType;
import multiAgent.ontology.Tender;

import java.math.BigDecimal;
import java.util.Date;

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
//        landlord lord = agent.get
        int type = (int)(Math.random()*2);
        Bid bid;
        if(type == 1) {
            bid = new Bid(tender.getSource(),
                    new Room(1,1, RoomType.Business+"",null,200,new Date(2017,5,2),new Date(2017,5,9),null,new BigDecimal(200),2),
                    100,
                    null,
                    null,
                    myAgent.getAID() ,
                    type);
        }else{
            bid = new Bid(tender.getSource(),
                    new Room(1,1, RoomType.Business+"",null,200,new Date(2017,5,2),new Date(2017,5,9),null,new BigDecimal(200),2),
                    0,
                    null,
                    null,
                    myAgent.getAID() ,
                    type);
        }
        myAgent.addBehaviour(new landlordPropose(myAgent,bid,receive));
    }
}
