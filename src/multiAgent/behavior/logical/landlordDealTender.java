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
        //决定是否要竞标
        //首先查看该房间类型是否有，如果有房间数量是否满足。如果没有该类型房间或者房间数量不满足，则放弃竞标
        //其次查看招标价格与该房间价格的差值，如果招标价格高于等于房间价格，直接参与竞标
        //如果招标价格低于房间价格，如果差值大于某个阀值，则放弃竞标
        //阀值由该房东的经济情况确定。
        //比如tension的threshold = 0
        //affordable的threshold = 10
        //amiable的threshold = 20
        //promotion的threshold = 40
//        landlord lord = agent.get
        int price_tender = tender.getPrice();
        int type = (int)(Math.random()*2);
        Bid bid;
        if(type == 1) {
            bid = new Bid(tender.getOrderId(),
                    new Room(1,1, RoomType.Business+"",null,200,new Date(2017,5,2),new Date(2017,5,9),"200",2),
                    100,
                    null,
                    null,
                    tender.getSource(),
                    myAgent.getAID() ,
                    type);
        }else{
            bid = new Bid(tender.getOrderId(),
                    new Room(1,1, RoomType.Business+"",null,200,new Date(2017,5,2),new Date(2017,5,9),"200",2),
                    0,
                    null,
                    null,
                    tender.getSource(),
                    myAgent.getAID() ,
                    type);
        }
        myAgent.addBehaviour(new landlordPropose(myAgent,bid,receive));
    }
}
